package controllers

import play.api.libs.json.JsValue
import play.api.mvc._
import play.api.libs.json.Json._

object Collateral extends Controller {
  def index() = Action {
    Ok(views.html.collat())
  }

  def amortize() = Action(parse.json) { request =>
    for(
      originalBalance<-(request.body \ "originalBalance").asOpt[Double];
      originalTerm<-(request.body \ "originalTerm").asOpt[Int];
      rate<-(request.body \ "rate").asOpt[Double];
      originationDate<-(request.body \ "originationDate").asOpt[String];
      firstPayDate<-(request.body \ "originationDate").asOpt[String]){

    }


    maybeDouble.map { dbl =>
      Ok(toJson(
        Map("status" -> "OK", "amount" -> ("Hello " + dbl))
      ))
    }.getOrElse {
      BadRequest(toJson(
        Map("status" -> "KO", "message" -> "Missing parameter [name]")
      ))
    }
  }
}