package uk.gov.hmrc.FHDDS

import uk.gov.hmrc.play.config.ServicesConfig

trait AppConfig {
  val analyticsToken: String
  val analyticsHost: String
  val reportAProblemPartialUrl: String
  val reportAProblemNonJSUrl: String
  val betaFeedbackUrl: String
}

object FrontendAppConfig extends AppConfig with ServicesConfig {

  def configuration(key: String): Option[String] = Some(play.Configuration.root().getString(key))

  private def loadConfig(key: String) = configuration(key: String).getOrElse(throw new Exception(s"Missing configuration key: $key"))

  private val contactHost = configuration(s"contact-frontend.host").getOrElse("")
  private val contactFormServiceIdentifier = "FHDDS"

  override lazy val analyticsToken = loadConfig(s"google-analytics.token")
  override lazy val analyticsHost = loadConfig(s"google-analytics.host")
  override lazy val reportAProblemPartialUrl = s"$contactHost/contact/problem_reports_ajax?service=$contactFormServiceIdentifier"
  override lazy val reportAProblemNonJSUrl = s"$contactHost/contact/problem_reports_nonjs?service=$contactFormServiceIdentifier"
  override lazy val betaFeedbackUrl: String = s"$contactHost/contact/beta-feedback-unauthenticated?service=$contactFormServiceIdentifier"
}
