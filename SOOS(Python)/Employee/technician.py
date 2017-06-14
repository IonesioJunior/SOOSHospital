#coding: utf-8

from PERMISSIONS import *
from employee import *

__author__ = "Ionesio Junior"


class Technician(Employee):
	
	
	def __init__(self,name,registration,password,job,birth):
		super(Technician,self).__init__(name,registration,password,job,birth)
		self.definePermissions()

	def definePermissions(self):
		permList = []
		permList.append(Permissions.REGISTERPATIENT)
		permList.append(Permissions.REGISTERMED)
		self.setPermissions(set(permList))
