package controllers

import play.api._
import play.api.mvc._

object Doc extends Controller {

  def index = Action {
    Ok(views.html.doc())
  }

}