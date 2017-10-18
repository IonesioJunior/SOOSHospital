#include "manager.hpp"
#include "doctor.hpp"
#include "technician.hpp"
#include <ctime>

int account_number = 1;
time_t current_time;

Employees::Employee create_user( std::string name, std::string job, std::string date )
{
	std::string register_number = generate_register_number( job );
	std::string date = format_date( date );
	std::string password = generate_password( register_number, date );
	
	std::string lowercase_job = to_lower(job);
	switch( lowercase_job )
	{
		case "manager":
			Employees::Employee account = new Employees::Manager( register_number, name, password, date, job );
			account_number++;
			return account;
		case "doctor":
			Employees::Employee account = new Employees::Doctor( register_number, name, password, date, job );
			account_number++;
			return account;
		case "technician":
			Employees::Employee account = new Employees::Technician( register_number, name, password, date, job );
			account_number++;
			return account;
	}
	return NULL;
}

std::string generate_register_number( std::string job )
{
	std::string lowercase_job = to_lower(job);
	std::string register_value;

	current_time = time(NULL);
	tm *p_time = localtime(&current_time);
	switch( job )
	{
		case "manager":
			 //
		case "doctor":
			 // 
		case "technician":
			 // 
	}
}

std::string to_lower(std::string value)
{
	char *value_in_char_format = value.c_str();
	int i = 0;
	while( value_in_char_format[i] )
	{
		tolower( value_in_char_format[i++] );
	}
	return std::string(value_in_char_format);
}
