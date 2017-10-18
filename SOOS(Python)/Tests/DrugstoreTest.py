#coding: utf-8
import sys,os
sys.path.append('../Drugstore/')
from drugstore import *


__author__ = "Ionesio Junior"


class DrugstoreTest(unittest.TestCase):
	
	def setUp(self):
		self.drugstore = Drugstore();

