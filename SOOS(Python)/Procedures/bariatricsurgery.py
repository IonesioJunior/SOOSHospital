#coding: utf-8

__author__ = "Ionesio Junior"

class BariatricSurgery(object):
	__price = 7600
	__doctor = None
	__time = None
	__credit = 100	
	def __init__(self,name,time):
		self.__doctor = name
		self.__time = time
	
	def performProcedure(self,patient):
		patient.setWeight(patient.getInfo("weight") * 0.85)
		return self.__price
	
	def getCredit(self):
		return self.__credit
	
	def __str__(self):
		text = ""
		text += "->Bariatric Surgery ..." + "\n"
		text += "Time: " + self.__time + "\n"
		text += "Doctor: " + self.__doctor + "\n"
		return text
