#coding: utf-8

from organs import *
__author__ = "Ionesio Junior"

class OrganBank(object):
	
	__organBank = None
	def __init__(self):
		self.__organBank = []
		self.__amount = 0

	def addOrgan(self,organName,bloodType):
		self.__organBank.append(Organs(organName,bloodType))

	def removeOrgan(self,organName,bloodType):
		organ = Organ(organName,bloodType)
		if(organ in self.__organBank):
			self.__organBank.remove(organ)
		else:
			raise OrganException("Organ not registered!")
	
	def searchOrganByName(self,organName):
		result = ",".join([organ.getBloodType() for organ in self.__organBank if organ.getName() == organName])
		if(len(result) == 0):
			raise OrganException("Organ not registered!")
		else:
			return result
	
	def searchOrganByBloodType(self,bloodType):
		result = ",".join([organ.getName() for organ in self.__organBank if organ.getBloodType() == bloodType])
		if(len(result) == 0):
			raise OrganException("Don't have any organ with this blood type")
		else:
			return result
	
	def searchOrgan(self,organName,bloodType):
		return Organs(organName,bloodType) in self.__organBank
	
	def getOrganBankAmount(self):
		return len(self.__organBank)
	
	def getAmountOfOrgan(self,organName):
		amount = 0
		for organ in self.__organBank:
			 if organ.getName() == organName:
				amount += 1
		return amount

	
