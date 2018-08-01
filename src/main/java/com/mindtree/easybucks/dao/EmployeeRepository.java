package com.mindtree.easybucks.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.easybucks.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

      @Query("SELECT emp FROM Employee emp  WHERE emp.userName=(:uName) or emp.email=(:email)")
	  Employee findDistinctEmployee( @Param("uName") String uName , @Param("email") String email );

}
