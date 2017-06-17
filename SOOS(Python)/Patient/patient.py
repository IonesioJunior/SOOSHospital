#coding: utf-8

__author__ = "Ionesio Junior"
from cards import Card,Vip,Master,Normal
from medicalrecords import *

class PatientException(Exception):
	def __init__(self,value):
		self.__value = value
	def __str__(self):
		return repr(self.__value)


class Patient(object):
	__medicalRecords = None
	__cash = None
	__spending = None
	
	def __init__(self,name,birth,weight,sex,gender,bloodType,idNumber):
		self.__medicalRecords = MedicalRecords(name,birth,weight,sex,gender,bloodType,idNumber)
		self.__spending = 0
		self.__card = Normal(0)
	
	def getInfo(self,info):
		if(info.upper() == "NAME"):
			return self.__medicalRecords.getName()
		elif(info.upper() == "BIRTH"):	
			return self.__medicalRecords.getBirth()
		elif(info.upper() == "BLOODTYPE"):
			return self.__medicalRecords.getBloodType()
		elif(info.upper() == "SEX"):
			return self.__medicalRecords.getSex()
		elif(info.upper() == "AGE"):
			return self.__medicalRecords.getAge()
		elif(info.upper() == "WEIGHT"):
			return self.__medicalRecords.getWeight()
		else:
			raise PatientException("Invalid Attribute!")


	def setWeight(self,newWeight):
		self.__medicalRecords.setWeight(newWeight)

	def getMedicalRecords(self):
		return self.__medicalRecords

	def setGender(self,newGender):
		self.__medicalRecords.setGender(newGender)


	def storeSpending(self,spending):
		self.__spending += self.__card.applyDiscount(spending)


	def getSpending(self):
		return self.__spending

	def __strategy(self):
		if(self.__card.getCredits() >= 150 and self.__card.getCredits() < 350):
			self.__card = Master(self.__card.getCredits())
		elif(self.__card.getCredits() >= 350):
			self.__card = Vip(self.__card.getCredits())

	def __addCreditCard(self,procedure):
		if(type(procedure) == ClinicalAppointment):
			self.__card.addPoints()
		elif(type(procedure) == BariatricSurgery):
			self.__card.addPoints()
		elif(type(procedure) == OrganTransplantation):
			self.__card.addPoints()
		elif(type(procedure) == SexualReassignment):
			self.__card.addPoints()
		else:
			raise PatientException("Invalid Procedure!")
	
	def getCardCredits(self):
		return self.__card.getCredits()
	
	def getId(self):
		return self.__medicalRecords.getId()
	
	def getMedicalRecordsSize(self):
		return self.__medicalRecords.getAmountOfProcedures()
	
	def registerProcedure(self,procedure):
		self.__addCreditCard(procedure)
		self.__medicalRecords.addProcedure(procedure)
	
	def __str__(self):
		text = ""
		text += self.__medicalRecords.__str__()
		text += "Card: " + self.__card.getType() + "\n"
		text += "Spending: R$" + str(self.__spending) + "\n"
		return text
	def __eq__(self,other):
		return self.getMedicalRecords() == other.getMedicalRecords()
