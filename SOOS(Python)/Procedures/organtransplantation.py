#coding: utf-8

__author__ = "Ionésio Junior"


class OrganTransplantation(object):
	
	__doctor = None
	__time = None
	__organ = None
	__price = 12500
	__credit = 160	
	def __init__(self,name,time,organ):
		self.__doctor = name
		self.__time = time
		self.__organ = organ
	
	def performProcedure(self,patient):
		return self.__price
	
	def getCredit(self):
		return self.__credit
	
	def __str__(self):
		text = ""
		text += "->Organ Transplantation ..." + "\n"
		text += "Time: " + self.__time + "\n"
		text += "Doctor: " + self.__doctor + "\n"
		text += "Transplanted Organ: " + self.__organ +"\n"
		return text
