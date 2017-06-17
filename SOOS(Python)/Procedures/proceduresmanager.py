#coding: utf-8
import sys,os
sys.path.append("../Patient/")
import datetime
from patient import *
from clinicappointment import *
from bariatricsurgery import *
from organtransplantation import *
from sexualreassignment import *

__author = "Ionesio Junior"

class ProcedureException(Exception):
	def __init__(self,value):
		self.__value = value
	def __str__(self):
		return repr(self.__value)


class ProceduresManager(object):

	__procedure = None
	def __init__(self):
		self.__now = datetime.datetime.now()

	def performProcedure(self,procedureName,patient,doctorName,organ = None):
		self.__defineProcedure(procedureName,doctorName,organ)
		patient.registerProcedure(self.__procedure)
		procedureCost = self.__procedure.performProcedure(patient)
		return procedureCost

	def __defineProcedure(self,procedureName,doctorName,organ = None):
		time = "/".join(map(str,[self.__now.day,self.__now.month,self.__now.year]))
		if(procedureName.upper() == "CLINICAPPOINTMENT"):
			self.__procedure = ClinicAppointment(doctorName,time)
		elif(procedureName.upper() == "SEXUALREASSIGNMENT"):
			self.__procedure = SexualReassignment(doctorName,time)
		elif(procedureName.upper() == "BARIATRICSURGERY"):
			self.__procedure = BariatricSurgery(doctorName,time)
		elif(procedureName.upper() == "ORGANTRANSPLANTATION"):
			self.__procedure = OrganTransplantation(doctorName,time,organ)
		else:
			raise ProcedureException("Invalid Procedure!")

