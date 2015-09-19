package nvim.protocol
import msgpack4z._
import msgpack4z.CodecInstances.all._

final case class Notification(_type: Int, method: String, params: MsgpackUnion) {
  override def toString = {
    val sb = new StringBuilder
    sb.append("Notification(\n")
    sb.append("  type: ").append(_type).append(",\n")
    sb.append("  method: ").append(method).append(",\n")
    // sb.append("  params: ")
    //   .append( NvimHelper.msgpackUnionAsString(params, nest = 1) ).append("\n")
    sb.append(")")
    sb.toString
  }
}
object Notification {
  implicit val instance: MsgpackCodec[Notification] = 
    CaseCodec.codec(Notification.apply _, Notification.unapply _)
}
