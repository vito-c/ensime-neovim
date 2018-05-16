import msgpack4z._
import msgpack4z.CodecInstances.all._
import org.msgpack.core.MessagePack
import org.msgpack.core.MessageUnpacker
//import nvim.protocol._
import scala.collection.concurrent.TrieMap

import scala.collection.mutable
import java.net.Socket
import java.util.concurrent.ConcurrentSkipListSet
import java.util.concurrent.atomic.AtomicInteger

import org.apache.log4j.ConsoleAppender
import org.apache.log4j.Level
import org.apache.log4j.LogManager
import org.apache.log4j.PatternLayout

import nvim._

object NeoClient extends App with LoggerConfig {
// function! s:RequireHaskellHost(name)
//   return rpcstart("/path/to/executable")
// endfunction
//
// call remote#host#Register('haskell', function('s:RequireHaskellHost'))
// call remote#define#CommandOnHost('haskell', 'METHOD', 1, 'HaskellCommand', {})

  val host = "127.0.0.1"
  val port = 8000
  val nv = Nvim( new Connection( host, port) )

  // override def main(args : Array[String]) {
    nv.sendVimCommand("""echom "quotes"""")
  // }

  def ScalaCommand()
  {
    nv.sendVimCommand("echom 'hello'")
  }
}

// object Main extends App with LoggerConfig {
//   implicit val system = ActorSystem()
//   implicit val materializer = ActorMaterializer()
//   import system.dispatcher
//
//   val config = system.settings.config
//   val interface = config.getString("app.interface")
//   val port = config.getInt("app.port")
//
//   val service = new WebService
//
//   val binding = Http().bindAndHandle(service.route, interface, port)
//   binding.onComplete {
//     case Success(binding) ⇒
//       val addr = binding.localAddress
//       system.log.info(s"Server is listening on ${addr.getHostName}:${addr.getPort}")
//     case Failure(e) ⇒
//       system.log.error(e, "Failed to start server")
//       system.shutdown()
//   }
// }

trait LoggerConfig {
  val layout = new PatternLayout("%d %5p [%t] - %c - %m%n")
  val consoleAppender = new ConsoleAppender(layout, ConsoleAppender.SYSTEM_OUT)
  val rootLogger = LogManager.getRootLogger
  rootLogger.setLevel(Level.ALL)
  rootLogger.addAppender(consoleAppender)
}
