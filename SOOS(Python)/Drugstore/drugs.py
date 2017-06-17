#coding: utf-8
from abc import ABCMeta,abstractmethod
from CATEGORY import *

__author__ = "Ionesio Junior"

class DrugsException(Exception):
	def __init__(self,value):
		self.value = value
	def __str__(self):
		return repr(self.value)


class Drugs:
	__metaclass__ = ABCMeta

	__name = None
	__price = None
	__amount = None
	__categoryList = ""
	__drugType = None
	def __init__(self,name,price,amount,category):
		self.__verifyParameters(name,price,amount,category)
		self.__name = name
		self.__price = price
		self.__amount = amount
		self.__defineCategory(category)
	

	def __defineCategory(self,category):
		category = category.split(",")
		enumList = [e.name for e in CATEGORY]
		for i in range(len(category)):
			if category[i].upper() in enumList:
				self.__categoryList += category[i].lower() + ","
		self.__categoryList = self.__categoryList[0:len(self.__categoryList) - 1]


	def containsCategory(self,category):
		return category.lower() in self.__categoryList

	
	def addAmount(self,newAmount):
		self.__amount += newAmount
	
	def getName(self):
		return self.__name

	def getPrice(self):
		return self.__price

	@abstractmethod
	def getDrugTypePrice(self):
		pass

	def getAmount(self):
		return self.__amount


	def getCategory(self):
		return self.__categoryList

	def getType(self):
		return self.__drugType

	def setPrice(self,newPrice):
		self.__price = newPrice
	
	def setAmount(self,newAmount):
		self.__amount = newAmount

	def setDrugType(self,drugType):
		self.__drugType = drugType
	
	def __str__(self):
		message = "Drug Name: " + self.__name + "\n" 
		message += "Price: " + str(self.getDrugTypePrice()) + "\n" 
		message += "Category: " + str(self.__categoryList) + "\n" 
		message += "Amount: " + str(self.__amount) + "\n"
		message += "Type: " + self.__drugType
		return message

	def __lt__(self,other):
		return self.getDrugTypePrice() < other.getDrugTypePrice()
	def __le__(self,other):
		return self.getDrugTypePrice() <= other.getDrugTypePrice()
	def __gt__(self,other):
		return self.getDrugTypePrice() > other.getDrugTypePrice()
	def __ge__(self,other):
		return self.getDrugTypePrice() >= other.getDrugTypePrice()
	def __eq__(self,other):
		return self.getName() == other.getName() and self.getType() == other.getType() and self.getPrice() == other.getPrice()
	def __ne__(self,other):
		return not(self.__eq__(other))

	
	def __verifyParameters(self,name,price,amount,category):
		self.__verifyName(name)
		self.__verifyPrice(price)
		self.__verifyAmount(amount)
		self.__verifyCategory(category)


	def __verifyName(self,name):
		if(type(name) != str or name == ""):
			raise DrugsException("Invalid Name!")
	def __verifyPrice(self,price):
		if(type(price) != float or price < 0):
			raise DrugsException("Invalid Price!")
	def __verifyAmount(self,amount):
		if(type(amount) != int or amount < 1):
			raise DrugsException("Invalid Amount!")
	def __verifyCategory(self,category):
		if(type(category) != str):
			raise DrugsException("Invalid Category!")
