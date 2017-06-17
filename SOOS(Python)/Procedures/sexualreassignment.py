#coding: utf-8

__author__ = "Ionésio Junior"

class SexualReassignment(object):
	__price = 9300
	__doctor = None
	__time = None
	__credit = 130	
	def __init__(self,name,time):
		self.__doctor = name
		self.__time = time
	
	def performProcedure(self,patient):
		if(patient.getGender().upper() == "MALE"):
			patient.setGender("Female")
		else:
			patient.setGender("Male")
		return self.__price
	
	def getCredit(self):
		return self.__credit

	def __str__(self):
		text = ""
		text += "-> Sexual Reassignment ..."  + "\n"
		text += "Time: " + self.__time + "\n"
		text += "Doctor: "+ self.__doctor + "\n"
		return text
