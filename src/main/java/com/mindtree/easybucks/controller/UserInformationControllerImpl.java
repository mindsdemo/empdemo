package com.mindtree.easybucks.controller;

import java.net.URI;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.easybucks.dao.EmployeeRepository;
import com.mindtree.easybucks.dto.Matches;
import com.mindtree.easybucks.dto.RequestResponse;
import com.mindtree.easybucks.entity.Employee;
import com.mindtree.easybucks.exception.UserNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;


// reference : http://www.springboottutorial.com/spring-boot-crud-rest-service-with-jpa-hibernate

@RestController
@RequestMapping("/EmpMgt")
@Api(value="Employee Board", description="Operations pertaining to Employee Demo api")
public class UserInformationControllerImpl  {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public UserInformationControllerImpl() {
		
	}
	
	@PostMapping("/addEmp")
	@ApiOperation(value = "Add Employees to store", response = ResponseEntity.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	public ResponseEntity<Object> createStudent(@Validated @RequestBody Employee employee) {
		
		Employee foundUser = employeeRepository.findDistinctEmployee(employee.getUserName(),employee.getEmail());
		if(foundUser!=null) {
			RequestResponse response = new RequestResponse("User with username/EmailId already exist", "Success",employee);
			return new ResponseEntity(response ,HttpStatus.FOUND );
		}

		Employee savedEmployee = employeeRepository.save(employee);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(employee.getUserName()).toUri();

		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/createJMaddEmp")
	public ResponseEntity<Object> testCreateStudent(@Validated @RequestBody Employee employee) {
			
			Employee foundUser = employeeRepository.findDistinctEmployee(employee.getUserName(),employee.getEmail());
			if(foundUser!=null) {
				RequestResponse response = new RequestResponse("User with username/EmailId already exist", "Success",employee);
				return new ResponseEntity(response ,HttpStatus.FOUND );
			}
	
			System.out.println("===Hello Boss=Request from JMeter=");
			Random rand = new Random();
			employee.setUserName(employee.getUserName()+"_"+rand.nextInt(10000)+"_"+rand.nextLong());
			employee.setEmail((employee.getEmail()+"_"+rand.nextInt(10000)+"_"+rand.nextLong()));			

			Employee savedEmployee = employeeRepository.save(employee);
	
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(employee.getUserName()).toUri();
	
			return ResponseEntity.created(location).build();
		}

		
	// Get All Employees
//	@GetMapping("/employees")
	@RequestMapping(value="/employees",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE) 
	public List<Employee> getAllEmployees() {

		return employeeRepository.findAll();
	}
		
	// Get a Single Employee
	@GetMapping("/employees/{id}")
	public Employee getEmployeeById(@PathVariable(value = "id") Long employeeId) {
		
		System.out.println("====Entered==getEmployeeById===" + employeeId);
	    return employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new UserNotFoundException("Employee", "id", employeeId));
	}
	
	// Delete a Employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) {

		Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new UserNotFoundException("Employee", "id", employeeId));

	    employeeRepository.delete(employee);
	    return ResponseEntity.ok().build();
	}
			

	@PostMapping("/checkLogin")
	public ResponseEntity<Object> checkLogin( @RequestBody Employee employee) {	

		RequestResponse response ;
		
		if (StringUtils.isEmpty(employee.getUserName()) || StringUtils.isEmpty(employee.getPassword())) {
			response = new RequestResponse("please enter username/Password", "Success",employee);
			return new ResponseEntity(response ,HttpStatus.BAD_REQUEST );
		}		
		Employee foundUser = employeeRepository.findDistinctEmployee(employee.getUserName(),employee.getEmail());		
			 
		if( foundUser == null) {
			throw new UserNotFoundException("Employee", "User Name", employee.getUserName());
		}else {
			if ( !(foundUser.getPassword().equals(employee.getPassword()))){
				response = new RequestResponse("Invalid  username/Password", "Success",employee);
				return new ResponseEntity(response ,HttpStatus.UNAUTHORIZED );
			}
		}	 		
		response = new RequestResponse("Employee has authenticated successfully", "Success",employee);
	    return new ResponseEntity(response ,HttpStatus.FOUND );
	}
	
	// Get a Single Employee
	/*@RequestMapping(value="/employees",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE) 
	public List<Employee> getAllEmployees() {
*/
	@GetMapping("/cricapi/matches")
	public ResponseEntity getMatches( ) {
		
		String URI_STRING = "http://cricapi.com/api/cricket?apikey=GAebnHBorbTTtsbMaMfMzgvl9gD3" ;
		
		ResponseEntity<String> response = restTemplate().getForEntity(URI_STRING, String.class);
		
		return response; 
	}
		

	RestTemplate restTemplate() {
		
		RestTemplate restTemplate = new RestTemplate();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new ObjectMapper());
		restTemplate.getMessageConverters().add(converter);
		return restTemplate;
	}
	
	
	
}
