package controllers

import play.api._
import play.api.mvc._
import services.RevisionsService
import services.ScreenshotsService
import play.api.data._
import play.api.data.Forms._

object Compare extends Controller {

	val revisionsService = RevisionsService
	val screenshotsService = ScreenshotsService

  	def compare = Action {
    	Ok(views.html.choose(revisionsService.list))
  	}

  	def postRevs = Action { implicit request => {
			val chosenRevs = chosenRevsForm.bindFromRequest.get
    		Redirect(routes.Compare.choosePage(chosenRevs.revA, chosenRevs.revB))
    	}
  	}

  	def choosePage(revA: String, revB: String) = Action {
      val listA = screenshotsService.listChecksum(revA)
      val listB = screenshotsService.listChecksum(revB)
    	Ok(views.html.choosePage(listA.keySet.filter({listB.contains(_)}).map( key => (key, (listA(key), listB(key))) ).toMap,
        revA, listA.keySet.filter({!listB.contains(_)}),
        revB, listB.keySet.filter({!listA.contains(_)})
      ))
  	}

  	def diff(revA: String, revB: String, page: String) = Action {
    	Ok(views.html.diff(revA, revB, page))
  	}


	case class ChosenRevs(revA: String, revB: String)
	val chosenRevsForm = Form(
		mapping("revA" -> text, "revB" -> text)(ChosenRevs.apply)(ChosenRevs.unapply)
	)
}

