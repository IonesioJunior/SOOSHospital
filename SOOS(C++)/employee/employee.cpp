#include "employee.hpp"
#include <iostream>

bool Employees::Employee::verify_permission( PERMISSIONS permission )
{
	std::set<PERMISSIONS>::iterator it;
	it = this->permissions_list.find( permission );
	if( it == this->permissions_list.end() )
	{
		return false;	
	}
	return true;
}

bool Employees::Employee::verify_string_parameter( std::string param )
{
	return ( param != "" && &param != NULL );
}

bool Employees::Employee::confirm_password( std::string password )
{
	return ( this->password == password );
}


std::string Employees::Employee::get_name()
{
	return this->name;
}

int Employees::Employee::get_register_number()
{
	return this->register_number;
}

std::string Employees::Employee::get_job()
{
	return this->job;
}

std::string Employees::Employee::get_date()
{
	return this->date;
}

std::set<PERMISSIONS> Employees::Employee::get_permissions()
{
	return this->permissions_list;
}

void Employees::Employee::set_name( std::string new_name )
{
	this->name = name;
}

void Employees::Employee::set_password( std::string password )
{
	this->password = password;
}

void Employees::Employee::set_date( std::string new_date )
{
	this->date = new_date;
}

void Employees::Employee::set_permissions( std::set<PERMISSIONS> new_permissions )
{
	this->permissions_list = new_permissions;
}

std::ostream& Employees::operator<<( std::ostream& stream, Employees::Employee& emp )
{
	stream << "Employee: " << emp.get_name() << std::endl
		<< "ID : " << emp.get_register_number() << std::endl
		<< "Job : " << emp.get_job() << std::endl 
		<< "Date : " << emp.get_date() << std::endl;
	return stream;
}
