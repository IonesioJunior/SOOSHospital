#coding: utf-8

__author__ = "Ionesio Junior"

from Controller import *
import pickle

class Facade(object):
	
	__controller = None;
	def __init__(self):
		self.__startSystem()
	
	
	def __startSystem(self):
		try:
			self.__controller = open("SOOS.obj",'r')
			self.__controller = pickle.load(self.__controller)
		except:
			self.__controller = Controller()

	
	def releaseSystem(self,key,name,birth):
		try:
			message = self.__controller.releaseSystem(key,name,birth)
			return message
		except ControllerException as e:
			return str(e)

	
	def login(self,registration,password):
		try:
			self.__controller.login(registration,password)
		except ControllerException as e:
			return str(e)


	def logout(self):
		try:
			self.__controller.logout()
		except ControllerException as e:
			return str(e)


	
	def closeSystem(self):
		try:
			self.__controller.verifyLogin()
			data = open('SOOS.obj','w')
			pickle.dump(self.__controller,data)
		except ControllerException as e:
			return str(e)





		
	def getInfoEmployee(self,registration,attribute):
		try:
			return self.__controller.getInfoEmployee(registration,attribute)
		except ControllerException as e:
			return str(e)


	def changeInfoEmployee(self,registration,attribute,newValue):
		try:
			return self.__controller.changeInfoEmployee(registration,attribute,newValue)
		except ControllerException as e:
			return str(e)


	def changePassword(self,oldPassword,newPassword):
		try:
			self.__controller.changePassword(oldPassword,newPassword)			
		except ControllerException as e:
			return str(e)


	def registerEmployee(self,name,job,birth):
		try:
			return self.__controller.registerEmployee(name,job,birth)
		except ControllerException as e:
			return str(e)

	
	def deleteEmployee(self,registration):
		try:
			self.__controller.deleteEmployee(registration)
		except ControllerException as e:
			return str(e)






	def registerPatient(self,name,birth,weight,sex,gender,bloodType):
		try:
			self.__controller.registerPatient(name,birth,weight,sex,gender,bloodType)
		except ControllerException as e:
			return str(e)
	
	
	def getInfoPatient(self,patientID):
		try:
			return self.__controller.getMedicalRecords(patientID).__str__()
		except ControllerException as e:
			return str(e)
	
	
	def registerNewDrug(self,name,drugType,price,amount,category):
		try:
			self.__controller.registerDrug(name,drugType,price,amount,category)
		except ControllerException as e:
			return str(e)
	
	
	
	def getDrugInfo(self,drugName,attribute):
		try:
			self.__controller.getDrugInfo(drugName,attribute)
		except ControllerException as e:
			return str(e)
	
	
	def changeDrugInfo(self,drugName,attribute,newValue):
		try:
			self.__controller.changeDrugInfo(drugName,attribute,newValue)
		except ControllerException as e:
			return str(e)
	
	
	def getDrugByCategory(self,category):
		try:
			self.__controller.getDrugByCategory(category)
		except ControllerException as e:
			return str(e)
	
	def getDrugByName(self,drugName):
		try:
			self.__controller.getDrugByName(drugName)
		except ControllerException as e:
			return str(e)
