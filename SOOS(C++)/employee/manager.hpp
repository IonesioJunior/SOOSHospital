#ifndef _MANAGER_H
#define _MANAGER_H


#include "employee.hpp"
#include "permissions.h"

namespace Employees{
	class Manager : public Employee {
		public:
			Manager( int register_number, std::string name, std::string password, std::string date, std::string job )
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
				this->permissions_list.insert( REGISTER_EMPLOYEE );
				this->permissions_list.insert( REGISTER_PATIENT );
				this->permissions_list.insert( DELETE );
				this->permissions_list.insert( UPDATE_INFOS );
				this->permissions_list.insert( REGISTER_DRUG );
				this->permissions_list.insert( REGISTER_ORGAN );
				this->permissions_list.insert( MAKE_PROCEDURE );
			}			
	};

}

#endif
