package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._

import services.RevisionsService

import java.io.File
import global.Global

object Revisions extends Controller {

	val revisionsService = RevisionsService

	val dir: String = Global.vault

  	def list = Action {
	  	val file = new File(dir)
	  	if(file.exists)
	    	Ok(views.html.revisions(revisionsService.listWithDate))
	    else
	    	NotFound("Not found")
	}
}