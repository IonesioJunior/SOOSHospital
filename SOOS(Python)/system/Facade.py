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
	
	def releaseSysteam(self,key,name,birth):
		try:
			message = self.__controller.releaseSystem(key,name,birth)
			return message
		except e:
			raise e.message()	
