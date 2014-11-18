package controllers

import play.api._
import play.api.mvc._

import java.io.File
import global.Global

import services.ScreenshotsService

object Screenshots extends Controller {

	val dir: String = Global.vault

	val screenshotsService = ScreenshotsService

  	def download(rev: String, key: String) = Action {
	  	val file = new File(dir+rev+'/'+key)
	  	if(file.exists)
	    	Ok.sendFile(
	    		content = file,
	    		fileName = _ => rev+"_"+key+".png"
	    	).as("image/png")
	    else
	    	NotFound("Not found")
	}

	def upload(rev: String, key: String) = Action(parse.multipartFormData) { request =>
			val revDir: File = new File(dir+rev)
			revDir.mkdirs
			val screen: File = new File(revDir, key)

			request.body.file("file").map { file =>
		    file.ref.moveTo(screen)
		    Ok("File uploaded")
		}.getOrElse {
			BadRequest("Missing file")
	  	}
	}

	def list(rev: String) = Action {
	  	val file = new File(dir, rev)
	  	if(file.exists)
	    	Ok(views.html.screenshots(rev, screenshotsService.list(rev)))
	    else
	    	NotFound("Not found")
	}
}