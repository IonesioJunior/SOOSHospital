#coding: utf-8
import datetime
from employee import *
from doctor import Doctor
from manager import Manager
from technician import Technician

class EmployeeBank(object):
	__numberOfAccounts = None
	__accounts = None
	
	def __init__(self):
		self.__numberOfAccounts = 1
		self.__accounts = {}
		self.__now = datetime.datetime.now()

	
	def registerEmployee(self,name,job,birth):
		employee = self.__createUser(name,job,birth)
		self.__accounts[employee.getRegistration()] = employee
		return employee.getRegistration()

	
	def registerFirstAcc(self,name,birth):
		job = "MANAGER"
		return self.registerEmployee(name,job,birth)
		
		
	def changeUserInfo(self,registration,attribute,value):
		self.__verifyRegistration(registration)
		user = self.__accounts[registration]
		attribute = attribute.upper()
		if(attribute == 'NAME'):
			user.setName(value)
			message = "User attribute has been changed successfully!"
		elif(attribute == 'BIRTH'):
			birth = map(int,value.split("/"))
			user.setBirth(birth)
			message = "User attribute has been changed successfully!"
		else:
			raise EmployeeException("Invalid Attribute!!")
		return message


	def authenticateUser(self,registration,password):
		self.__verifyRegistration(registration)
		user = self.__accounts[registration]
		if(user.getPassword() != password):
			raise EmployeeException("Wrong Password!");

	
	def getEmployee(self,registration):
		self.__verifyRegistration(registration)
		return self.__accounts[registration]

	
	def deleteEmployee(self,registration):
		self.__verifyRegistration(registration)
		del self.__accounts[registration]


	def getInfoEmployee(self,registration,attribute):
		self.__verifyRegistration(registration)
		user = self.__accounts[registration]
		attribute = attribute.upper()
		if(attribute == "NAME"):
			result = user.getName()
		elif(attribute == "JOB"):
			result = user.getJob()
		elif(attribute == "BIRTH"):
			result = user.getBirth()[0]  + "/" + user.getBirth()[1] + "/" + user.getBirth()[2]
		elif(attribute == "PASSWORD"):
			result = "You are not allowed to see user password!"
		else:
			raise EmployeeExceptio("Invalid Attribute!")
		return result
	
	def __verifyRegistration(self,registration):
		if(self.__accounts.get(registration) == None):
			raise EmployeeException("Employee not registered!")
	

	def __createUser(self,name,job,birth):
		birth = map(int,birth.split("/"));
		registration = self.__generateRegistration(job);
		password = str(birth[2]) + registration[0:4];
		if(job.upper() == "MANAGER"):
			user = Manager(name,registration,password,job.upper(),birth);
		elif(job.upper() == "DOCTOR"):
			user = Doctor(name,registration,password,job.upper(),birth);
		elif(job.upper() == "TECHNICIAN"):
			user = Technician(name,registration,password.upper(),job,birth);
		else:
			raise EmployeeException("Invalid job!");
		self.__numberOfAccounts += 1
		return user
			
			
	
	def __generateRegistration(self,job):
		job = job.upper()
		registration = ""
		if(job == "MANAGER"):
			registration += "1" + str(self.__now.year) + str(self.__numberOfAccounts)
		elif(job == "DOCTOR"):
			registration += "2" + str(self.__now.year) + str(self.__numberOfAccounts)
		elif(job == "TECHNICIAN"):
			registration += "3" + str(self.__now.year) + str(self.__numberOfAccounts)
		return registration
