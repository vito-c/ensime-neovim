package org.ensime.neovim

import java.util.concurrent.atomic.AtomicInteger
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshalling.Marshal
import akka.http.scaladsl.model._
import akka.http.scaladsl.model.ws.WebSocketRequest
import akka.http.scaladsl.model.ws._
import akka.http.scaladsl.settings.ClientConnectionSettings
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._
import akka.util.ByteString
import akka.{ Done, NotUsed }
import nvim._
import org.apache.log4j.{ConsoleAppender, Level, LogManager, PatternLayout}
import scala.concurrent.Future
import spray.json._
import org.ensime.api._
import JsWriter.ops._
import JsReader.ops._
import java.io.File

object NeoClient extends App with LoggerConfig {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  import system.dispatcher

  val host = "127.0.0.1"
  val i:Int = 0
  val port = 8000
  val nv = Nvim( new Connection( host, port) )

  val enhost = "127.0.0.1"
  val enport = 65145 

  def ScalaCommand()
  {
    nv.sendVimCommand("echom \"send msg\"")
    println(s"i is ${i}")
  }

// {'callId': 1,
//  'req': {'file': '/Users/vitocutten/code/playground/ensime-neovim/src/main/scala/NeoClient.scala',
//          'point': 2519,
//          'typehint': 'SymbolAtPointReq'}}
  val myMsg = RpcRequestEnvelope(
    SymbolAtPointReq(Left(new File("/Users/vitocutten/code/playground/ensime-neovim/src/main/scala/NeoClient.scala")), 1745): RpcRequest, 20)

  val json = CompactPrinter(myMsg.toJson)
  val test = TextMessage(json)
  pprint.pprintln(json)

  val incoming: Sink[Message, Future[Done]] =
    Sink.foreach[Message] {
      case message: TextMessage.Strict =>
        println(message.text)
    }
  val outgoing = Source.single(test)
  // val outgoing = Source.single(tmsg)
  //
  // val outgoing = Source.fromFuture(Marshal(myMsg).to[TextMessage])
  val flow = Flow.fromSinkAndSourceMat(incoming, outgoing)(Keep.left)
  val (upgradeResponse, closed) =
    Http().singleWebSocketRequest(WebSocketRequest(
      uri = s"ws://${enhost}:${enport}/websocket", subprotocol = Some("jerky")), flow)

  // val outgoing = Source.single(tmsg)
  // val outgoing = Source.fromFuture(Marshal(myMsg).to[MessageEntity])
  // // flow to use (note: not re-usable!)
  // val webSocketFlow = Http().webSocketClientFlow(
  //   WebSocketRequest(
  //     uri = s"ws://${enhost}:${enport}/websocket", subprotocol = Some("jerky")
  //   ))

  //
  // the materialized value is a tuple with
  // upgradeResponse is a Future[WebSocketUpgradeResponse] that
  // completes or fails when the connection succeeds or fails
  // and closed is a Future[Done] with the stream completion from the incoming sink
  // val (upgradeResponse, closed) =
  //   outgoing
  //     .viaMat(webSocketFlow)(Keep.right) // keep the materialized Future[WebSocketUpgradeResponse]
  //     .toMat(incoming)(Keep.both) // also keep the Future[Done]
  //     .run()

  // just like a regular http request we can access response status which is available via upgrade.response.status
  // status code 101 (Switching Protocols) indicates that server support WebSockets
  val connected = upgradeResponse.flatMap { upgrade =>
    if (upgrade.response.status == StatusCodes.SwitchingProtocols) {
      Future.successful(Done)
    } else {
      throw new RuntimeException(s"Connection failed: ${upgrade.response.status}")
    }
  }

  // in a real application you would not side effect here
  connected.onComplete(println)
  closed.foreach(_ => println("closed"))
}


trait LoggerConfig {
  val layout = new PatternLayout("%d %5p [%t] - %c - %m%n")
  val consoleAppender = new ConsoleAppender(layout, ConsoleAppender.SYSTEM_OUT)
  val rootLogger = LogManager.getRootLogger
  rootLogger.setLevel(Level.ALL)
  rootLogger.addAppender(consoleAppender)
}
