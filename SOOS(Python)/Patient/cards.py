#coding: utf-8

from abc import ABCMeta,abstractmethod

__author__ = "Ionesio Junior"

class Card(object):
	__metaclass__ = ABCMeta
	__credit = None
	__type = None
	def __init__(self,credit):
		self.__credit = credit
	
	def addCredit(self,points):
		self.__credit += points
	def getCredits(self):
		return self.__credit
	
	def getType(self):
		return self.__type

	@abstractmethod
	def applyDiscount(self,price):
		pass

class Master(Card):	
	def __init__(self,credit):
		super(Card,self).__init__(credit)
		self.__type = "Master"

	def applyDiscount(self,price):
		return price * 0.85

class Vip(Card):
	def __init__(self,credit):
		super(Card,self).__init__(credit)
		self.__type = "Vip"

	def applyDiscount(self,price):
		return price * 0.7

class Normal(Card):
	def __init__(self,credit):
		self.__credit  = credit
		self.__type = "Normal"
	
	def applyDiscount(self,price):
		return price * 1
	
	def getType(self):
		return "Normal"
