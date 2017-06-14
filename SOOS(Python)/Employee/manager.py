#coding: utf-8

from employee import *
from PERMISSIONS import *
__author__ = "Ionesio Junior"


class Manager(Employee):
	
	def __init__(self,name,registration,password,job,birth):
		super(Manager,self).__init__(name,registration,password,job,birth)
		self.definePermissions();

	def definePermissions(self):
		permList = []
		permList.append(Permissions.REGISTEREMPLOYEE)
		permList.append(Permissions.REGISTERPATIENT)
		permList.append(Permissions.REGISTERMED)
		permList.append(Permissions.DELETE)
		permList.append(Permissions.CHANGEDATA)
		self.setPermissions(set(permList))
