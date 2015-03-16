package controllers

import play.api.mvc._
import play.api._

object Collateral extends Controller {
  def index() = Action {
    Ok(views.html.collat())
  }
}