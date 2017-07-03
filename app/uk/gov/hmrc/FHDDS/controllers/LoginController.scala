package uk.gov.hmrc.FHDDS.controllers

import play.api.Logger
import play.api.Play.current
import play.api.data.Form
import play.api.i18n.Messages.Implicits._
import play.api.mvc._
import uk.gov.hmrc.FHDDS.models.{Forms, User}
import uk.gov.hmrc.play.frontend.controller.FrontendController
import uk.gov.hmrc.FHDDS.views.html.login_page.login

import scala.concurrent.Future


object LoginController extends Controller with FrontendController {

  val userLoginForm: Form[User] = Forms.userLoginForm

  implicit val anyContentBodyParser: BodyParser[AnyContent] = parse.anyContent

  def loginPage(continueUrl: String): Action[AnyContent] = Action.async {
    implicit request => {
      Future.successful(Ok(login(userLoginForm, continueUrl)(request, applicationMessages)))
    }
  }

  def check(continueUrl: String) = Action {
    implicit request =>

      val loginForm = userLoginForm.bindFromRequest()
      val name = loginForm.data("name")
      val password = loginForm.data("password")

      println(name)
      println(password)

      val formWithError = userLoginForm.withGlobalError("Please check and re-enter your user name and password")
      Ok(login(formWithError, continueUrl)(request, applicationMessages))
  }

}
