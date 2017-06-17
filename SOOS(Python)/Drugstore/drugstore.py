#coding: utf-8

from drugs import *
from genericdrugs import *
from patenteddrugs import *

__author__ = "Ionésio Junior"

class Drugstore(object):
	
	__drugstore = None	
	def __init__(self):
		self.__drugstore = {}
	
	def registerDrug(self,name,drugtype,price,amount,category):
		drug = self.__createDrugs(name,drugtype,price,amount,category)
		self.__drugstore[drug.getName().upper()] = drug
		return drug.getName()

	def getDrugByName(self,name):
		drug = self.__drugstore.get(name.upper())
		if(drug != None):
			return drug
		else:
			raise DrugsException("This name not registered in our Drugstore!")
	
	def getDrugInfo(self,name,info):
		drug = self.getDrugByName(name)
		if(info.upper() == "TYPE"):
			return drug.getType()
		elif(info.upper() == "PRICE"):
			return drug.getDrugTypePrice()			
		elif(info.upper() == "AMOUNT"):
			return drug.getAmount()
		elif(info.upper() == "CATEGORY"):
			return drug.getCategory()
		else:
			raise DrugsException("Invalid Attribute!")
	
	def changeDrugPrice(self,name,newPrice):
		self.getDrugByName(name).setPrice(newPrice)

	def changeDrugAmount(self,name,newAmount):
		self.getDrugByName(name).setAmount(newAmount)
	
	def getAllDrugInfo(self,name):
		return self.getDrugByName(name).__str__()
	
	def getDrugsByCategory(self,category):
		drugs_category = [categ.name for categ in CATEGORY]
		if(not(category.upper() in drugs_category)):
			raise DrugsException("Invalid Category!")
		else:
			available_drugs = self.__drugstore.values()
			drugs_with_category = []
			for drugs in available_drugs:
				if(drugs.containsCategory(category)):
					drugs_with_category.append(drugs.getName())
			if(len(drugs_with_category) == 0):
				raise DrugsException("Don't have any drug with this category!")
			else:
				return ",".join(drugs_with_category)

	def sortDrugs(self,type_of_sort):
		if(type_of_sort.upper() == "LEXICOGRAPHICAL"):
			drugNames = [ drug.getName() for drug in self.__drugstore.values()]
			drugNames.sort()
			return drugNames
		elif(type_of_sort.upper() == "PRICES"):
			drugs = self.__drugstore.values()
			drugs.sort()
			return [drug.getName() for drug in drugs]
		else:
			raise DrugsException("Invalid type of sort!")
	
	def __createDrugs(self,name,drugtype,price,amount,category):
		if(drugtype.upper() == "GENERIC"):
			return GenericDrug(name,price,amount,category)
		elif(drugtype.upper() == "PATENTED"):
			return PatentedDrug(name,price,amount,category)
		else:
			raise DrugsException("Invalid Drug Type!")		
