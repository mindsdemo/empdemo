package com.mindtree.easybucks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.io.IOException;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

import com.mindtree.easybucks.ApplicationBootStartup;
import com.mindtree.easybucks.dao.EmployeeRepository;
import com.mindtree.easybucks.entity.Employee;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationBootStartup.class)
@WebAppConfiguration
@Profile("test")

public class ApplicationBootStartupTest {
		
	
	@Test
	public void contextLoads() {
		
		System.out.println("= #Change is the game# =");
	}

	
}
