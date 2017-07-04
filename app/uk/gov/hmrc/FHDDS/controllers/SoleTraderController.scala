package uk.gov.hmrc.FHDDS.controllers

import javax.inject.{Inject, Singleton}

import play.api.data.Form
import play.api.i18n.{I18nSupport, Messages, MessagesApi}
import play.api.mvc.{Action, AnyContent}
import uk.gov.hmrc.FHDDS.models.{Confirm, Forms, User}
import uk.gov.hmrc.play.frontend.controller.FrontendController
import uk.gov.hmrc.FHDDS.views.html.sole_trader_views._

import scala.concurrent.Future

@Singleton
class SoleTraderController @Inject()(override val messagesApi: MessagesApi)
  extends FrontendController with I18nSupport {

  val confirmForm: Form[Confirm] = Forms.confirmForm

  def information(): Action[AnyContent] = Action.async { implicit request =>
    Future.successful(Ok(inf(Forms.confirmForm)))
  }

  def submitCheckResult(): Action[AnyContent] = Action.async { implicit request =>
    Forms.confirmForm.bindFromRequest().fold(
      formWithErrors => {
        Future.successful(Ok(inf(formWithErrors)))
      },
      register => {
        Future.successful(Ok(summary()))
      }
    )
  }
}