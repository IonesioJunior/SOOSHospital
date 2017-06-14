#coding: utf-8

from PERMISSIONS import *
from employee import *

__author__ = "Ionesio Junior"

class Doctor(Employee):
	
	def __init__(self,name,registration,password,job,birth):
		super(Doctor,self).__init__(name,registration,password,job,birth)
		self.definePermissions()
	
	def definePermissions(self):
		permList = []
		permList.append(Permissions.REGISTERORGAN)
		permList.append(Permissions.SURGICALSURGEON)
		self.setPermissions(set(permList))
