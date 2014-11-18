package services

import play.api._
import play.api.mvc._
import play.api.libs.json._

import java.io.File
import global.Global

import java.security.MessageDigest
import scala.io.Source

object ScreenshotsService  {

	val dir: String = Global.vault

	def list(rev: String): Array[String] = {
	  	val file = new File(dir, rev)
	  	if(file.exists)
	    	return file.listFiles.filter(_.isFile).map( file => file.getName ).sorted
		return Array()
	}

	def listChecksum(rev: String): Map[String, String] = {
	  	val file = new File(dir, rev)
	  	if(file.exists)
	    	return file.listFiles.filter(_.isFile).sorted.map( file => (file.getName, checksum(file)) ).toMap
		return Map()
	}

	def checksum(file: File): String = {
		val bytes: Array[Byte] = Source.fromFile(file, "iso-8859-1").map(_.toByte).toArray
		return MessageDigest.getInstance("SHA-1").digest(bytes).map("%02X".format(_)).mkString
	}

}