#coding: utf-8

__author__ = "Ionesio Junior"

class OrgansException(Exception):
	def __init__(self,value):
		self.__value = value
	def __str__(self):
		return repr(self.__value)

class Organs(object):

	__name = None
	__bloodType = None
	def __init__(self,name,bloodType):
		self.__verifyParameters(name,bloodType)
		self.__name = name
		self.__bloodType = bloodType

	def getName(self):
		return self.__name

	def getBloodType(self):
		return self.__bloodType

	def __eq__(self,other):
		return self.getName() == other.getName() and self.getBloodType() == other.getBloodType()
	
	def __verifyParameters(self,name,bloodType):
		self.__verifyName(name)
		self.__verifyBloodType(bloodType)
	
	def __verifyName(self,name):
		if(name == "" or type(name) != str):
			raise OrgansException("Invalid name!")
	
	def __verifyBloodType(self,bloodType):
		listOfBloodTypes = ["A+","A-","B+","B-","AB+","AB-","O-","O+"]
		if(not(bloodType in listOfBloodTypes)):
			raise OrgansException("Invalid blood type")


