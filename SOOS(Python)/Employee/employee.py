#coding: utf-8

from abc import ABCMeta,abstractmethod

__author__ = "Ionesio Junior"

class EmployeeException(Exception):
	def __init__(self,value):
		self.value = value
	def __str__(self):
		return repr(self.value)

class Employee:
	__metaclass__ = ABCMeta

	__name = None
	__registration = None
	__password = None
	__job = None
	__birth = None
	__permissions = None
	def __init__(self,name,registration,password,job,birth):
		self.__verify(name,registration,password,job,birth)
		self.__name = name
		self.__registration = registration
		self.__password = password
		self.__birth = birth
		self.__job = job

	@abstractmethod
	def definePermissions(self):
		pass
	
	def __verify(self,name,registration,password,job,birth):
		self.__verifyName(name)
		self.__verifyRegistration(registration)
		self.__verifyPassword(password)
		self.__verifyBirth(birth)
	
	def __verifyName(self,name):
		if(type(name) != str or name == ""):
			raise EmployeeException("Invalid Name!");

	def __verifyRegistration(self,registration):
		if(type(registration) != str or registration == ""):
			raise EmployeeException("Invalid Registration!")
	
	def __verifyPassword(self,password):
		if(type(password) != str or password == ""):
			raise EmployeeException("Invalid Password!")

	def __verifyBirth(self,birth):
		if(type(birth) != list):
			raise EmployeeException("Inválid Birth. Wrong Type")
		elif(birth[2] < 1900 or birth[2] > 2017):
			raise EmployeeException("Inválid Birth. Invalid Birth Year")
		elif(birth[1] < 1 or birth[1] > 12):
			raise EmployeeException("Inválid Birth. Invalid Month")
		elif(birth[0] < 1 or birth[1] == 2 and birth[0] > 29 or birth[0] > 31):
			raise EmployeeException("Inválid Birth. Invalid Day")

	def confirmPassword(self,password):
		if(self.__password != password):
			raiseEmployeeException("Invalid Password!")
	
	def getRegistration(self):
		return self.__registration
	
	def getName(self):
		return self.__name
	
	def getPassword(self):
		return self.__password
	
	def getBirth(self):
		return self.__birth
	
	def getJob(self):
		return self.__job
	
	def getPermissions(self):
		return self.__permissions
	
	def setName(self,newName):
		self.__verifyName(newName)
		self.__name = newName
	
	def setPassword(self,newPassword):
		self.__verifyPassword(newPassword)
		self.__password = newPassword
	
	def setJob(self,newJob):
		if(type(newJob) == str and newJob != ""):
			self.__job = newJob
		else:
			raise EmployeeException("Invalid Job!")
	
	
	
	def setBirth(self,newBirth):
		self.__verifyBirth(newBirth)
		self.__birth = newBirth

	def setPermissions(self,newPermissions):
		self.__permissions = newPermissions
		
		
	def __str__(self):
		message =   "Name: " + self.__name  + "\n" 
		message +=  "Job: " + self.__job + "\n"
		message +=   "Registration: " + self.__registration + "\n"
		message	 += "Birth: " + str(self.__birth[0]) + "/" + str(self.__birth[1]) + "/" +  str(self.__birth[2])
		return message			

	def __eq__(self,other):
		if isinstance(other,Employee):
			if(self.__name == other.getName() and self.__password == other.getPassword() and self.__registration == other.getRegistration()):
				return True
			else:
				return False
		else:
			return False
