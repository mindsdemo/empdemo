package com.mindtree.easybucks;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.mindtree.easybucks.controller.UserInformationControllerImpl;
import com.mindtree.easybucks.dao.EmployeeRepository;
import com.mindtree.easybucks.entity.Employee;

// http://www.baeldung.com/spring-boot-testing

@RunWith(SpringRunner.class)
@WebMvcTest(UserInformationControllerImpl.class)
public class UserInformationControllerTest {

	@MockBean
	private EmployeeRepository employeeRepository;

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
	  throws Exception {
	     
	    Employee emp = new Employee("krupakar" , "krupakar.blr@gmail.com" , "19-jan-1984");
	 
	    List<Employee> allEmployees = Arrays.asList(emp);	 
	    
	    given(employeeRepository.findAll()).willReturn(allEmployees);
	 
	    mvc.perform(get("/EmpMgt/employees")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())
	      .andExpect(jsonPath("$", hasSize(1)))
	      .andExpect(jsonPath("$[0].userName", is(emp.getUserName())));
	    
        verify(employeeRepository, VerificationModeFactory.times(1)).findAll();
        reset(employeeRepository);

	}
	
	@Test
    public void whenPostEmployee_thenCreateEmployee() throws Exception {
        Employee emp = new Employee("emp1" , "emp1.blr@gmail.com" ,"19-jan-1990");
        given(employeeRepository.save(Mockito.anyObject())).willReturn(emp);

        mvc.perform(post("/EmpMgt/addEmp").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(emp))).
        	andExpect(status().isCreated());
        verify(employeeRepository, VerificationModeFactory.times(1)).save(Mockito.anyObject());
        reset(employeeRepository);
    }
	
	@Test
	public void givenEmployee_whenFindEmployee_thenReturnJson()
	  throws Exception {
	     
	    Optional<Employee> employee = Optional.of(new Employee("krupakar" , "krupakar.blr@gmail.com" , "19-jan-1984"));
	    employee.get().setId(1l);
	   // given(employeeRepository.findDistinctEmployee("krupakar", "krupakar.blr@gmail.com")).willReturn(employee);
	    given(employeeRepository.findById(1l)).willReturn(employee);
		 
	    mvc.perform(get("/EmpMgt/employees/1")
	      .contentType(MediaType.APPLICATION_JSON))
	      .andExpect(status().isOk())	      
	      .andExpect(jsonPath("$.userName", is(employee.get().getUserName())));
	    
        verify(employeeRepository, VerificationModeFactory.times(1)).findById(new Long(1l));
        reset(employeeRepository);

	}
	
	@Test
    public void whenPostEmployee_thenCheckLogin() throws Exception {
        Employee emp = new Employee("emp1" , "emp1.blr@gmail.com" ,"19-jan-1990");
        emp.setPassword("kerrpwd");
        given(employeeRepository.findDistinctEmployee(Mockito.anyObject(),Mockito.anyObject())).willReturn(emp);
        mvc.perform(post("/EmpMgt/checkLogin").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(emp))).
        	andExpect(status().is3xxRedirection());
        verify(employeeRepository, VerificationModeFactory.times(1)).findDistinctEmployee(Mockito.anyObject(),Mockito.anyObject());
        reset(employeeRepository); 
    }
	
	@Test
    public void whenPostEmployee_thenInvalidCheckLogin() throws Exception {
        Employee emp = new Employee("emp1" , "emp1.blr@gmail.com" ,"19-jan-1990");
        //emp.setPassword("kerrpwd");
        given(employeeRepository.findDistinctEmployee(Mockito.anyObject(),Mockito.anyObject())).willReturn(emp);
        mvc.perform(post("/EmpMgt/checkLogin").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(emp))).
        	andExpect(status().is4xxClientError()).andExpect(jsonPath("$.message", is("please enter username/Password")));
        verify(employeeRepository, VerificationModeFactory.times(0)).findDistinctEmployee(Mockito.anyObject(),Mockito.anyObject());
        reset(employeeRepository); 
    }
	
	@Test
    public void whenPostAlreadyExistEmployee_thenReportException() throws Exception {
        Employee emp = new Employee("emp1" , "emp1.blr@gmail.com" ,"19-jan-1990");
        given(employeeRepository.findDistinctEmployee(Mockito.anyObject(), Mockito.anyObject())).willReturn(emp);

        mvc.perform(post("/EmpMgt/addEmp").contentType(MediaType.APPLICATION_JSON).content(JsonUtil.toJson(emp))).
        	andExpect(status().is3xxRedirection());
        verify(employeeRepository, VerificationModeFactory.times(1)).findDistinctEmployee(Mockito.anyObject(),Mockito.anyObject());
        reset(employeeRepository);
    }
	
}
