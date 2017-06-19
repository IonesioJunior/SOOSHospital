#coding: utf-8
import sys,os
sys.path.append("../Employee/")
sys.path.append("../Patient/")
sys.path.append("../Drugstore/")
sys.path.append("../Organs/")

from drugstore import *
from PatientBank import *
from OrganBank import *
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
	__drugstore = None
	__patientBank = None
	__organBank = None
	__user = None
	def __init__(self):
		self.__systemReleased = False
		self.__employeeBank = EmployeeBank()
		self.__drugstore = Drugstore()
		self.__patientBank = PatientBank()
		self.__organBank = OrganBank()

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
			except EmployeeException as e:
				raise ControllerException("Error: " + str(e))
		else:
			raise ControllerException("System need to be released!")


	def logout(self):
		self.__verifyLogout()
		self.__user = None


	def getInfoEmployee(self,registration,attribute):
		self.__verifyUser()
		try:		
			return self.__employeeBank.getInfoEmployee(registration,attribute)
		except EmployeeException as e:
			raise ControllerException("Error : " + str(e))


	def changeInfoEmployee(self,registration,attribute,newValue):
		self.__verifyUser()
		try:
			if(self.__user.verifyPermission(Permissions.CHANGEDATA)):
				return self.__employeeBank.changeUserInfo(registration,attribute,newValue)
			else:
				ControllerException("You can't change data!")
		except EmployeeException as e:
			raise ControllerException("Error: " + str(e))

		
	def changeUserInfo(self,attribute,newValue):
		self.__verifyUser()
		try:
			if(attribute.upper() == "NAME"):
				self.__user.setName(newValue)
			elif(attribute.upper() == "BIRTH"):
				self.__user.setBirth(newValue)
			else:
				raise ControllerException("Invalid attribute!")
		except EmployeeException as e:
			raise ControllerException("Error : " + str(e))


	def changePassword(self,oldPassword,newPassword):
		self.__verifyUser()
		if(self.__user.getPassword() == oldPassword):
				self.__user.setPassword(newPassword)
		else:
				raise ControllerException("Invalid password!")


	def registerEmployee(self,name,job,birth):
		self.__verifyUser()
		try:
			if(self.__user.verifyPermission(Permissions.REGISTEREMPLOYEE)):
				return self.__employeeBank.registerEmployee(name,job,birth)
			else:
				raise ControllerException("The User " + self.__user.getName() + "don't have permission to register new employeers!")
		except EmployeeException as e:
			return e.__str__()

	def deleteEmployee(self,registration):
		self.__verifyUser()
		try:
			if(self.__user.verifyPermission(Permissions.DELETE)):
				self.__employeeBank.deleteEmployee(registration)
			else:
				raise ControllerException("You can't delete employee!")
		except EmployeeException as e:
			raise ControllerException("Error : " + str(e))
			

	def getDrugInfo(self,drugName,attribute):
		self.__verifyUser()
		try:
			return self.__drugstore.getDrugInfo(drugName,attribute)
		except DrugsException as e:
			raise ControllerException("Error : " + str(e))
	

	def getDrugsByCategory(self,category):
		self.__verifyUser()
		try:
			return self.__drugstore.getDrugsByCategory(category)
		except DrugsException as e:
			raise ControllerException("Error: " + str(e))

	
	def getDrugByName(self,name):
		self.__verifyUser()
		try:
			return self.__drugstore.getDrugByName(name).__str__()
		except DrugsException as e:
			raise ControllerException("Error : " + str(e))
	

	def registerDrug(self,name,drugType,price,amount,category):
		self.__verifyUser()
		try:
			if(self.__user.verifyPermission(Permissions.REGISTERMED)):
				return self.__drugstore.registerDrug(name,drugType,price,amount,category)
			else:
				raise ControllerException("You can't register new drugs!")
		except EmployeeException as e:
			raise ControllerException("Error : " + str(e))
		except DrugsException as e:
			raise ControllerException("Error : " + str(e))
	
	
	def changeDrugInfo(self,name,attribute,newValue):
		self.__verifyUser()
		attribute = attribute.upper()
		try:
			if(self.__user.verifyPermission(Permissions.CHANGEDATA)):
				if(attribute == "NAME"):
					raise ControllerException("Drug Exception Problem, Drug name can't be change")
				elif(attribute == "TYPE"):
					raise ControllerException("Drug Exception Problem,Drug type can't be change")
				elif(attribute == "AMOUNT"):
					self.__drugstore.changeDrugAmount(name,newValue)
				elif(attribute == "PRICE"):
					self.__drugstore.changeDrugPrice(newValue)
			else:
				raise ControllerException("You can't change drugs data!")
		except DrugsException as e:
			raise ControllerException("Error: " + str(e))


	def registerPatient(self,name,birth,weight,sex,gender,bloodType):
		self.__verifyUser()
		try:
			if(self.__user.verifyPermission(Permissions.REGISTERPATIENT)):
				return self.__patientBank.registerPatient(name,birth,weight,sex,gender,bloodType)
			else:
				raise ControllerException("You can't register new patient")
		except PatientException as e:
			raise ControllerException("Error: " + str(e))
	

	def getMedicalRecords(self,patientId):
		self.__verifyUser()
		try:
			return self.__patientBank.getMedicalRecords(patientId).__str__()
		except PatientException as e:
			raise ControllerException("Error: " + str(e))
	

	def drugStorage(self,sort):
		self.__verifyUser()
		try:
			return ",".join(self.__drugstore.sortDrugs(sort))
		except DrugsException as e:
			raise ControllerException("Error: " + str(e))


	def registerOrgan(self,name,bloodType):
		self.__verifyUser()
		try:
			if(self.__user.verifyPermission(Permissions.REGISTERORGAN)):
				self.__organBank.addOrgan(name,bloodType)
			else:
				raise ControllerException("You can't register new organ!")
		except OrgansException as e:
			raise ControllerException("Error: " + str(e))
	

	def searchOrganByBlood(self,bloodType):
		self.__verifyUser()
		try:
			return self.__organBank.searchOrganByBloodType(bloodType)
		except OrgansException as e:
			raise ControllerException("Error: " + str(e))
	

	def searchOrganByName(self,name):
		self.__verifyUser()
		try:
			return self.__organBank.searchOrganByName(name)
		except OrgansException as e:
			raise ControllerException("Error: " + str(e))
	

	def searchOrgan(self,name,bloodType):
		self.__verifyUser()
		try:
			return self.__organBank.searchOrgan(organName,bloodType)
		except OrgansException as e:
			raise ControllerException("Error: " + str(e))
	

	def getAmountOfOrgan(self,name):
		self.__verifyUser()
		return self.__organBank.getAmountOfOrgan(name)
	

	def amountOfOrgans(self):
		self.__verifyUser()
		return self.__organBank.getOrganBankAmount()
	

	def removeOrgan(self,name,bloodType):
		self.__verifyUser()
		try:
			if(self.__user.verifyPermission(PERMISSION.REGISTERORGAN)):
				self.__organBank.removeOrgan(name,bloodType)
			else:
				raise ControllerException("You can't remove organs!")
		except OrgansException as e:
			raise ControllerException("Error: " + str(e))
	

	def performProcedure(self,procedureName,patientId,drugs = None,organ = None):
		self.__verifyUser()
		try:
			if(self.__user.verifyPermission(Permissions.SURGICALSURGEON)):
				if(type(organ) != type(None)):
					if(self.__organBank.searchOrgan(organ,self.__patientBank.getInfoPatient(patientId,"bloodtype"))):
						drugsPrice = 0
						self.__patientBank.performProcedure(patientId,self.__user.getName(),procedureName,drugsPrice,organ)
					else:
						raise ControllerException("Sorry, this organ isn't registered in our bank!")
				else:
					drugsPrice = 0
					self.__patientBank.performProcedure(patientId,self.__user.getName(),procedureName,drugsPrice)
			else:
				raise ControllerException("You can't do surgical surgeon")

		except PatientException as e:
			raise ControllerException("Error: " + str(e))
		except DrugsException as e:
			raise ControllerException("Error: " + str(e))
		except OrgansException:
			raise ControllerException("Error: " + str(e))

	
	def __verifySystem(self):
		if(self.__systemReleased):
			raise ControllerException("System already released!")


	def __verifyCurrentUser(self):
		if(self.__user != None):
			raise ControllerException("Some user already logged in!")
	
	def __verifyLogout(self):
		if(self.__user == None):
			raise ControllerException("Nobody is logged in!")	

	def __verifyKey(self,key):
		if(key != "SOOS"):
			raise ControllerException("Wrong Key!")
	
	def verifyLogin(self):
		if(self.__user != None):
			raise ControllerException("Someone is logged in")

	def __verifyUser(self):
		if(self.__user == None):
			raise ControllerException("You need to login!")
