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
			try:
				self.__verifyCurrentUser();
				self.__employeeBank.authenticateUser(registration,password)
				self.__user = self.__employeeBank.getEmployee(registration)
			except EmployeeException:
				raise ControllerException("Error!!")
		else:
			raise ControllerException("System need to be released!")


	def logout(self):
		self.__user = None







	def getInfoEmployee(self,registration,attribute):
		try:		
			return self.__employeeBank.getInfoEmployee(registration,attribute)
		except EmployeeException:
			raise ControllerException("Error!!")


	def changeInfoEmployee(self,registration,attribute,newValue):
		try:
			return self.__employeeBank.changeInfoEmployee(registration,attribute,newValue)
		except:
			raise ControllerException("Error!!")

		
	def changePassword(self,oldPassword,newPassword):
			if(self.__user != None):
				if(self.__user.getPassword() == oldPassword):
					self.__user.setPassword(newPassword)
				else:
					raise ControllerException("Error!")
			else:
				raise ControllerException("Error!")


	def registerEmployee(self,name,job,birth):
		if(self.__user != None):
			if(self.__user.verifyPermission(Permissions.REGISTEREMPLOYEE)):
				return self.__employeeBank.registerEmployee(name,job,birth)
			else:
				raise ControllerException("The User " + self.__user.getName() + "don't have permission to register new employeers!")
		else:
			raise ControllerException("You need to log in  to acess this function!")


	def deleteEmployee(self,registration):
		try:
			if(self.__user.verifyPermission(Permissions.DELETEEMPLOYEE)):
				self.__employeeBank.deleteEmployee(registration)
		except EmployeeException:
			raise ControllerException("Erro!")
			






	def __verifySystem(self):
		if(self.__systemReleased):
			raise ControllerException("System already released!")

	def __verifyCurrentUser(self):
		if(self.__user != None):
			raise ControllerException("Some user already logged in!")
	
	def __verifyKey(self,key):
		if(key != "SOOS"):
			raise ControllerException("Wrong Key!")
