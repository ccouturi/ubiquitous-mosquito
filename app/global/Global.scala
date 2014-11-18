package global

import play.api._

import java.io.File

object Global extends GlobalSettings {

	val vault: String = "./MINI_VAULT/"

	override def onStart(app: Application) {
	    val file = new File(vault)
	    if(!file.exists)
	    	file.mkdirs
	  }

	  override def onStop(app: Application) {
	    Logger.info("Bye.")
	  }
}