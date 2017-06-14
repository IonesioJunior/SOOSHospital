#coding: utf-8
import sys,os
sys.path.append("../Employee/")
from EmployeeBank import *
from PERMISSIONS import Permissions

__author__ = "Ionésio Junior"

class ControllerException(Exception):
	def __init__(self,value):
		self.value = value
	def __str__(self):
		return repr(self.value)


class Controller(object):
	
	__systemReleased = None
	__loggedUser = None
	__employeeBank = None
	__user = None
	def __init__(self):
		self.__systemReleased = False
		self.__employeeBank = EmployeeBank()
	
	def releaseSystem(self,key,name,birth):
		self.__verifySystem()
		self.__verifyKey(key)
		registration = self.__employeeBank.registerFirstAcc(name,birth);
		self.__systemReleased = True
		return registration
	
	def login(self,registration,password):
		if(self.__systemReleased):
			self.__verifyCurrentUser();
			self.__employeeBank.authenticateUser(registration,password)
			self.__user = self.__employeeBank.getEmployee(registration)
		else:
			raise ControllerException("System need to be released!")

	def getInfoEmployee(self,registration,attribute):
		return self.__employeeBank.getInfoEmployee(registration,attribute)
	
	
	def registerEmployee(self,name,job,birth):
		if(self.__user.verifyPermission(Permissions.REGISTEREMPLOYEE)):
			return self.__employeeBank.registerEmployee(name,job,birth)
		else:
			raise ControllerExceptio("The User " + self.__user.getName() + "don't have permission to register new employeers!")	
	def __verifySystem(self):
		if(self.__systemReleased):
			raise ControllerException("System already released!")

	def __verifyCurrentUser(self):
		if(self.__user != None):
			raise ControllerException("Some user already logged in!")
	
	def __verifyKey(self,key):
		if(key != "SOOS"):
			raise ControllerException("Wrong Key!")
