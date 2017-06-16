#coding: utf-8

from drugs import *
__author__ = "Ionesio Junior"

class GenericDrug(Drugs):
	
	def __init__(self,name,price,amount,category):
		super(GenericDrug,self).__init__(name,price,amount,category)
		self.setDrugType("Generic")
		self.__genericPercentPrice = 0.6

	def getDrugTypePrice(self):
		return self.getPrice() * self.__genericPercentPrice
