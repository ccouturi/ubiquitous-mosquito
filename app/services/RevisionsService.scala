package services

import play.api._
import play.api.mvc._
import play.api.libs.json._

import java.io.File
import global.Global

object RevisionsService  {

	val dir: String = Global.vault

  	def list: Array[String] = {
	  	val file = new File(dir)
	  	if(file.exists)
	    	return file.listFiles.filter(_.isDirectory).map( file => file.getName).sorted
	    return Array()
	}
}