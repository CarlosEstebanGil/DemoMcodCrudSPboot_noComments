package com.example.demo.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/*import jakart.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id; */

@Entity
public class Persona { //implements Ipersona ->  lo dejé como estaba xq las entitys no se puede jpa auto con Is solo entitys!  
								//  		( no se podia el fmk requiere usar entities xa funcionar )  
				// old:  le agrego que implemente iPersona modifico el source del controller y demas xa q usen Ipersona Orientado a <<i>>

	// Atributos:
	
	@Id 
	private int idPersona;
	 
	@Column(name="nombre",length = 50)
	private String nombre;
	
	
	// constructores:
	
	
	//Getters & Setters
	
//	@Override
	public int getIdPersona() {
		return idPersona;
	}
	
//	@Override
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	
//	@Override
	public String getNombre() {
		return nombre;
	}
	
//	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

//	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nombre=" + nombre + "]";
	}
	
}
