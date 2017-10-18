#ifndef _TECHNICIAN_HPP
#define _TECHNICIAN_HPP

#include "employee.hpp"
#include "permissions.h"


namespace Employees{
	class Technician : public Employee {
		public:
			Technician( int register_number, std::string name, std::string password, std::string date, std::string job )
			{
				this->register_number = register_number;
				this->name = name;
				this->password = password;
				this->date = date;
				this->job = job;
				define_permissions();
			}
		private:
			void define_permissions()
			{
				this->permissions_list.insert( REGISTER_PATIENT );
				this->permissions_list.insert( REGISTER_DRUG );
			}
	};

}
#endif
