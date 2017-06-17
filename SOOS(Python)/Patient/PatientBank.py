#coding: utf-8

import sys
sys.path.append("../Resources/")
sys.path.append("../Procedures/")
from AVL import *
from proceduresmanager import *
from patient import *
__author__ = "Ionesio Junior"

class PatientBank(object):
	__procedureManager = None
	__patientTree = None
	__ID = None
	def __init__(self):
		self.__patientTree = AVL()
		self.__procedureManager = ProceduresManager()
		self.__ID = 1
	
	def registerPatient(self,name,birth,weight,sex,gender,bloodType):
		patient = Patient(name,birth,weight,sex,gender,bloodType,self.__ID)
		self.__ID += 1
		self.__patientTree.insert(patient)
		return str(patient.getId())

	def getInfoPatient(self,patientId,attribute):
		patient = self.searchPatient(patientId)
		if(type(patient) != type(None)):
			patient.getInfo(attribute)
		else:
			raise PatientException("Invalid Patient ID")
	
	def searchPatient(self,patientId):
		return self.__patientTree.search(patientId).getData()
		
	def performProcedure(self,patientId,doctorName,procedureName,drugsCost = 0,organ = None):
		patient = self.searchPatient(patientId)
		totalCost = self.__procedureManager.performProcedure(procedureName,patient,doctorName,organ) + drugsCost
		patient.storeSpending(totalCost)
		patient.changeCardType()
		
