#coding: utf-8

from drugs import *
__author__ = "Ionesio Junior"

class PatentedDrugs(Drugs):
	
	def __init__(self,name,price,amount,category):
		super(PatentedDrugs,self).__init__(name,price,amount,category)
		self.setDrugType("Patented")
	
	def getDrugTypePrice(self):
		return self.getPrice()
