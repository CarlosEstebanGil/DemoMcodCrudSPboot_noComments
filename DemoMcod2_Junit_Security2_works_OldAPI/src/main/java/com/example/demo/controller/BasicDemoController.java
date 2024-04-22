package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
/* import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; */
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.stereotype.Controller; //**va 
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.example.demo.data.dao.IPersonaRepo;
//import com.example.demo.data.model.IPersona;
import com.example.demo.data.model.Persona;


// @Controller	//**va
// public class BasicDemoController {

//**viene : el ejemplo de https://spring.io/guides/gs/rest-service/ (sitio oficial ) usa otros paquetes porque rest es parte de la vista
										// y x eso configura el spring boot starter para mvc !! 
	//			(hay varios starter x c/modulo capa etc en el pom, faltaba el web (q incluye a los restcontrollers!)  

/* @EnableMethodSecurity	// new : xa api security */

@RestController // Obs: OJO: Tuve que hacer un File -> maven -> update project + un file -> run as -> maven clean 
				 		 		// xa que tome mis cambios en el pom y tome esta api !!!!!
@RequestMapping("/personas") 	// <- esta conf xa que no sea x ej de una localhost:9898/mapping method api rest name endpoin sino xa  
public class BasicDemoController {			// agregar a la url de request un path /personas/ metodos del api de pers ( mas real, separado)!  
	
	private static Logger LOG = LoggerFactory.getLogger(BasicDemoController.class);
	
	//atribs: 
	
	@Autowired						// Usa (inyecta) el dao ( muy basico, salteo capas. ) 
	private IPersonaRepo persRepo; //  ( deberia usar un service (capa business) y no dao de una. )   
	
	// methods:
	
	// uno x GET ..
	
	@GetMapping("/savePers")
	public void guardarPersona() {
		
		//Persona pers = new Persona();
		// no se puede las entitys trabajarlas <<i>>Oriented o + bn no repositoy.save porque espera un @entity ej Persona 
		// y no una interfase no bean ej Ipersona y las Is no pueden ser beans logicamente..asi que lo dejo como estaba
		//IPersona pers = new Persona();
		Persona pers = new Persona();
		pers.setIdPersona(1);
		pers.setNombre("charly san");
		
		persRepo.save(pers);
		
		LOG.info("Persona correctamente insertada por GET");
	}
	
	//-- otro x GET ..  
	

	/*@PreAuthorize("hasAuthority('ADMIN')")*/
	@GetMapping("/listAllPers")
	public Authentication listarPersonas() {//void listarPersonas() {
		
		List<Persona> lPersonas = persRepo.findAll();
		// ( jpa trabaja con entidades su api ej repository.save espera un @entity ( un bean, no una <i> ej IPers sino @entity Persona. )
		//for (IPersona persona : lPersonas) {		// xa esta prueba no estoy devolviendo json al cli,
		//	LOG.info(persona.toString());		// 	(solo me lo muestro a mi mismo x la consola del server ) 
		//}
		
		for (Persona persona : lPersonas) {		// xa esta prueba no estoy devolviendo json al cli,
			LOG.info(persona.toString());		// 	(solo me lo muestro a mi mismo x la consola del server ) 
		}
		return SecurityContextHolder.getContext().getAuthentication();
		 
		
	}
	
	
	@GetMapping("/listAllPersJSON")
	//public void listarPersonasJSON() {
	public List<Persona>listarPersonasJSON() {
		
		List<Persona> lPersonas = persRepo.findAll();

		// ( jpa trabaja con entidades su api ej repository.save espera un @entity ( un bean, no una <i> ej IPers sino @entity Persona. )
		//for (IPersona persona : lPersonas) {		// xa esta prueba no estoy devolviendo json al cli,
		//	LOG.info(persona.toString());		// 	(solo me lo muestro a mi mismo x la consola del server ) 
		//}
		
		
		return lPersonas; // new : added by me : con retornar objetos o colls de objectos (listas etc) auto el fmk devuelve json 
											//(objs data auto mapped representation a json analogo, todo auto x el fmk por spring )  

	}
	
	// --otro por POST: // por post y por put reciben el objeto json.
	
	//Insert: (post)		( actuan simil en lo basico, pero podrian customizarse mas y cambiar x eso  si ins llamar y 
	@PostMapping		//  	recibir con post y si upd con put osea usar bien los verbos y programar en base a estos tmb )	
	public void insertarPersona(@RequestBody Persona per) {
		
		persRepo.save(per);
		
		LOG.info("Persona correctamente insertada por POST");  // (@requestbody =automagia de json post req obj a java obj entity bean pers)
		
	}														
														// post (insert) y put (update) ambos usan repositoryPers.SAVE ya q save() de repos    
	//Update: (put)										//	internamente si el id existe hace un update y si no existe hace un insert		
	@PutMapping											//	osea q save() de repos es un updorinsert auto de una, = usar http methods bien.
	public void modificar(@RequestBody Persona per) {
		persRepo.save(per);
		
		LOG.info("Persona correctamente actualizada o insertada por PUT"); 
	}

	//Delete: (delete) ( delete recibe por get idem listar; pero viene el req con otro verbo y eso identifica tmb qNO es un GET(de listar) )
	//(Si lo dejo asi ent la url de req http q activa este metodo rest api de delete es http..:9898/id=xx   ademas xq recibe verbo delete)
	@DeleteMapping(value ="/{id}")  
	public void eliminarPersona(@PathVariable("id") Integer id) { //aca no importa q el param en la url se llame y venga como id xq 
		persRepo.deleteById(id);						// 			indico q recibo id aca y luego el repo.deleteById ya sabe cual es su entity y 
														//				su entity ya sabe (tiene conf) cual es su clave (@id) .
		LOG.info("Persona correctamente borrada por DELETE"); 
	}
	
	
}
