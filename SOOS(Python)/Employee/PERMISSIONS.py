#coding: utf-8

from enum import Enum
__author__ = "Ionesio Junior"


class Permissions(Enum):
	REGISTEREMPLOYEE = 1
	REGISTERPATIENT = 2
	DELETE = 3
	CHANGEDATA = 4
	REGISTERMED = 5
	REGISTERORGAN = 6
	SURGICALSURGEON = 7
