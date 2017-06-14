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
	
	def getInfoEmployee(self,registration,attribute):
		try:
			return self.__controller.getInfoEmployee(registration,attribute)
		except ControllerException:
			print "Error!!"
	
	def registerEmployee(self,name,job,birth):
		try:
			self.__controller.registerEmployee(name,job,birth)
		except ControllerException:
			print "Error!!"			
