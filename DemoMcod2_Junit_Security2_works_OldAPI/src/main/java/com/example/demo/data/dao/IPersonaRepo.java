package com.example.demo.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;

//import com.example.demo.data.model.IPersona;
import com.example.demo.data.model.Persona;

//Obs: saber q hereda un <<i>> pero del fmk jpa x lo q auto el fmk si existen esos metodos ya tiene implentacion que ejecuta xa estos xa 
//			esta entidad x eso es como si heredase comportamiento pero mas q nada es una i yl auto por el fmk. 
//public interface IPersonaRepo extends JpaRepository<IPersona, Integer>{ 	 //<Persona, Integer>{ 	 // ya cuento con sabe listall etc auto!
public interface IPersonaRepo extends JpaRepository<Persona, Integer>{ 	 
	// no se puede las entitys trabajarlas <<i>>Oriented o + bn no repositoy.save porque espera un @entity ej Persona y 
	//		no una interfase no bean ej Ipersona y las Is no pueden ser beans logicamente..asi que lo dejo como estaba.  
}
