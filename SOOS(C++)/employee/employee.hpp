#ifndef _EMPLOYEE_H
#define _EMPLOYEE_H

#include <set>
#include <string>
#include "permissions.h"

namespace Employees{
	class Employee{
		protected:
			std::string name;
			std::string register_number;
			std::string password;
			std::string date;
			std::string job;
			std::set<PERMISSIONS> permissions_list;
			virtual bool verify_string_parameter( std::string param );
			virtual void define_permissions() = 0;
		public:
			virtual bool verify_permission( PERMISSIONS permission );		
			virtual bool confirm_password( std::string password );
			virtual std::string get_name();
			virtual std::string get_job();
			virtual std::string get_date();
			virtual std::string get_register_number();
			virtual std::set<PERMISSIONS> get_permissions();
			virtual void set_name( std::string new_name );
			virtual void set_password( std::string new_password );
			virtual void set_date( std::string new_date );
			virtual void set_permissions( std::set<PERMISSIONS> new_permissions );
	};
	std::ostream& operator<<( std::ostream& stream, Employees::Employee& emp );
}


#endif
