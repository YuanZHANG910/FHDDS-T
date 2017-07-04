package uk.gov.hmrc.FHDDS.models

import play.api.data.Form
import play.api.data.Forms._
import play.api.i18n.Messages
import play.api.libs.json.{Json, OFormat}
import uk.gov.hmrc.FHDDS.models.CustomFormatters._

case class User(name: String, password: String)
case class Confirm(value: Boolean) extends AnyVal

object Forms {

  implicit val userFormat: OFormat[User] = Json.format[User]
  implicit val confirmFormat: OFormat[Confirm] = Json.format[Confirm]

  val userLoginForm = Form (
    mapping (
      "name" -> nonEmptyText,
      "password" -> nonEmptyText
    )
    (User.apply)(User.unapply)
  )

  def confirmForm(implicit messages: Messages) = Form(
    mapping(
      "confirm" -> of(requiredBooleanFormatter)
    )(Confirm.apply)(Confirm.unapply))

}

