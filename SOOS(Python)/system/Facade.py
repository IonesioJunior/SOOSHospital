#coding: utf-8
__author__ = "Ionesio Junior"

from Controller import *

class Facade(object):
	
	__controller = None;
	def __init__(self):
		self.__startSystem()
	
	
	def __startSystem(self):
		try:
			self.__controller = open("SOOS.txt",'r')
		except:
			self.__controller = Controller()

	
	def releaseSystem(self,key,name,birth):
		try:
			message = self.__controller.releaseSystem(key,name,birth)
			return message
		except ControllerException:
			print "Error!!"

	
	def login(self,registration,password):
		try:
			self.__controller.login(registration,password)
		except ControllerException:
			print "Error!!"


	def logout(self):
		self.__controller.logout()






		
	def getInfoEmployee(self,registration,attribute):
		try:
			return self.__controller.getInfoEmployee(registration,attribute)
		except ControllerException:
			print "Error!!"


	def changeInfoEmployee(self,registration,attribute,newValue):
		try:
			return self.__controller.changeInfoEmployee(registration,attribute,newValue)
		except ControllerException:
			print "Error!!"	


	def changePassword(self,oldPassword,newPassword):
		try:
			self.__controller.changePassword(oldPassword,newPassword)			
		except ControllerException:
			print "Error!"


	def registerEmployee(self,name,job,birth):
		try:
			self.__controller.registerEmployee(name,job,birth)
		except ControllerException:
			print "Error!!"	

	
	def deleteEmployee(self,registration):
		try:
			self.__controller.deleteEmployee(registration)
		except ControllerException:
			print "Error!"





