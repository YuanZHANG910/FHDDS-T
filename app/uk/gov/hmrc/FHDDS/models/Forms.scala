package uk.gov.hmrc.FHDDS.models

import play.api.data.Form
import play.api.data.Forms._

case class User(name: String, password: String)

object Forms {

  val userLoginForm = Form (
    mapping (
      "name" -> nonEmptyText,
      "password" -> nonEmptyText
    )
    (User.apply)(User.unapply)
  )

}

