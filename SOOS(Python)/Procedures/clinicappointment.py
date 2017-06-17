#coding: utf-8

__author__ =  "Ionésio Junior"

class ClinicAppointment(object):
	
	__price = 350
	__doctor = None
	__time = None
	__credit = 50
	def __init__(self,name,time):
		self.__doctor = name
		self.__time = time
	
	def performProcedure(self,patient):
		return self.__price
	
	def getCredit(self):
		return self.__credit

	def __str__(self):
		text = ""
		text += "->Clinic Appointment ..." + "\n" 
		text += "Time: " + self.__time + "\n"
		text += "Doctor: " + self.__doctor + "\n"
		return text
