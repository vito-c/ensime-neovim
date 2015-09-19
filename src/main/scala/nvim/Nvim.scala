package nvim

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import msgpack4z._
import msgpack4z.MsgpackUnion._
import macrame.enum

object Nvim {
  val BufferId = 0
  val WindowId = 1
  val TabpageId = 2
}

final case class Nvim(connection: Connection) extends {

  import Nvim._

  def sendVimCommand(cmd: String): Unit =
    connection.sendNotification("vim_command", string(cmd))

}
