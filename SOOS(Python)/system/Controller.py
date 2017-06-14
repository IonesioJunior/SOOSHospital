#coding: utf-8

__author__ = "Ionésio Junior"

class Controller(object):
	
	__systemReleased = None
	__loggedUser = None
	def __init__(self):
		self.__systemReleased = False
	
	
	def releaseSystem(self,key,name,birth):
		verifySystem();
		self.verifyKey(key);
		registration = EmployeeBank.createFirstAcc(name,birth);
		self.__systemReleased = True
		return registration
