#coding: utf-8

from drugs import *
__author__ = "Ionesio Junior"

class PatentedDrug(Drugs):
	
	def __init__(self,name,price,amount,category):
		super(PatentedDrug,self).__init__(name,price,amount,category)
		self.setDrugType("Patented")
	
	def getDrugTypePrice(self):
		return self.getPrice()
