#ifndef _DOCTOR_HPP
#define _DOCTOR_HPP

#include "employee.hpp"
#include "permissions.h"

namespace Employees{
	class Doctor : public Employee {
		public: 
			Doctor( int register_number, std::string name, std::string password, std::string date, std::string job )
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
				this->permissions_list.insert( REGISTER_ORGAN );
				this->permissions_list.insert( MAKE_PROCEDURE );
			}
	};
}

#endif
