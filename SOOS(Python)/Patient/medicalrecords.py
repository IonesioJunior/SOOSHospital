#coding: utf-8
import datetime
__author__ = "Ionesio Junior"

class MedicalRecords(object):

	__name = None
	__birth = None
	__weight = None
	__bloodType = None
	__sex = None
	__gender = None
	__id = None
	__proceduresList = None
	def __init__(self,name,birth,weight,sex,gender,bloodType,idNumber):
		self.__date = datetime.datetime.now()
		self.__name = name
		self.__birth = map(int,birth.split("/"))
		self.__weight = weight
		self.__bloodType = bloodType
		self.__sex = sex
		self.__gender = gender
		self.__id = idNumber
		self.__proceduresList = []
	
	
	def getName(self):
		return self.__name

	def getBirth(self):
		birth = map(str,self.__birth)
		return "/".join(birth)

	def getWeight(self):
		return self.__weight

	def getBloodType(self):
		return self.__bloodType

	def getGender(self):
		return self.__gender

	def getId(self):
		return self.__id

	def getSex(self):
		return self.__sex

	def getAge(self):
		yearsToDays = self.__birth[0] + self.__birth[1] * 30 + self.__birth[2] * 365
		today = self.__date.day + self.__date.month * 30 + self.__date.year * 365
		age = today - yearsToDays
		age /= (30 * 12)
		return str(age)

	def __eq__(self,other):
		return self.getId() == other.getId()
	
	def __str__(self):
		text = ""
		text += "Nome: " + self.__name + "\n"
		text += "ID: " + str(self.__id) + "\n"
		text += "Birth: " + self.getBirth()  + "\n"
		text += "Blood Type: " + self.__bloodType + "\n"
		text += "Weight: " + str(self.__weight) + '\n'
		text += "Age: " + self.getAge() + "\n"
		text += "Gender: "  + self.__gender + "\n"
		text += "Sex: " + self.__sex + "\n"  
		text += "Procedures: " + "\n".join(self.__proceduresList) + "\n"
		return text

	def addProcedure(self, procedure):
		self.__proceduresList.append(procedure)

	def setWeight(self,newWeight):
		self.__weight = newWeight

	def setGender(self,newGender):
		self.__gender = newGender
	
	def getAmountOfProcedures(self):
		return len(self.__proceduresList)
