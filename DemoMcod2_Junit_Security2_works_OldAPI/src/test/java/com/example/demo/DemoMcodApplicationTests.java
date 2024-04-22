package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
//import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.data.dao.IUsuarioRepo;
import com.example.demo.data.model.Usuario;

// En springboot se anotan con:  @SPRINGBOOTTEST 
// Las ops a DB no perduran post ejecucion xq x default hace :  AUTO ROLLBACK 	luego de testear
// Se debe correr los test desde Eclipse springboot tools desde:	 FILE -> RUN AS  -> MAVEN TEST 
//				( run as -> maven test, NO: maven build c/goal test NI: run as junit test solo tampoco , es run as maven test ) .

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoMcodApplicationTests {

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	IUsuarioRepo usuRepo;
	
	@Test
	public void crearUsuarioTest() {
	
		Usuario usuPru = new Usuario();

		usuPru.setId(1);  
		
		usuPru.setNombre("charly");
		usuPru.setClave(encoder.encode("1234"));
		
		Usuario usuRet= usuRepo.save(usuPru); 
				
		assertThat(usuRet.getClave().equalsIgnoreCase(usuPru.getClave()));
	}
	
		
	//TODO mock
	@WithMockUser(username = "charly", roles = {"USER", "ADMIN"})
	public void testAdminAccess() {

	    // Test logic that requires admin access
	    // ...
	}
	
} 
