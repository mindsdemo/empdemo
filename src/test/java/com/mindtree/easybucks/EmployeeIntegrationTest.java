package com.mindtree.easybucks;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.mindtree.easybucks.dao.EmployeeRepository;
import com.mindtree.easybucks.entity.Employee;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



@Transactional
public class EmployeeIntegrationTest extends ApplicationBootStartupTest {
	
	@Autowired
    private EmployeeRepository employeeRepository;

	//@Autowired
	private MockMvc mvc;
		
	@Autowired
    private WebApplicationContext wac;
		 
    @Before
    public void setUp() {
    	employeeRepository.save(new Employee("emp1" , "emp1.blr@gmail.com" ,"19-jan-1990"));
    	employeeRepository.save(new Employee("emp2" , "emp2.blr@gmail.com" ,"20-jan-1990"));
    }
    
    @Test
    public void testFindByName() {
    	    	 
	    List<Employee> employeesList = employeeRepository.findAll();
	    System.out.println("==employeesList=="+employeesList);
        assertThat(employeesList, notNullValue());
 
    	
    }
    
    @Test
	public void testCreateEmployee() {
		
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		 
        Employee emp = new Employee("emp1" , "emp1.blr@gmail.com" ,"19-jan-1990");

		try {
			mvc.perform(post("/EmpMgt/addEmp").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(emp)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        List<Employee> found = employeeRepository.findAll();
        assertThat(found).extracting(Employee::getUserName).contains("emp1");
        
    }


    @Test
	public void testNoEmployeeFound() {
		
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		 
        Employee emp = new Employee("emp1" , "emp1.blr@gmail.com" ,"19-jan-1990");

		try {
			mvc.perform(post("/EmpMgt/checkLogin").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(emp)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        List<Employee> found = employeeRepository.findAll();
        assertThat(found).extracting(Employee::getUserName).contains("emp1");
        
    }

	@Test
	public void testDeleteEmployee() {
		
		testCreateEmployee();
		employeeRepository.deleteAll();
        List<Employee> found = employeeRepository.findAll();
        assertThat(found).isEmpty();        
    }

    
    @org.junit.After
    public void clean() {
    	
    	System.out.print("===Cleaned==");
    	employeeRepository.deleteAll();
    	//delete(new Employee("emp1" , "emp1.blr@gmail.com" ,"19-jan-1990"));
    	//employeeRepository.delete(new Employee("emp2" , "emp2.blr@gmail.com" ,"20-jan-1990"));
    }

}
