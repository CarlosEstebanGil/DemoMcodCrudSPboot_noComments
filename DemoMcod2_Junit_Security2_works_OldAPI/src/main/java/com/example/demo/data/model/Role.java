package com.example.demo.data.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/*import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;*/

@Entity
@Table(name = "roles")	// HE DEFINIDO ROLE 1 ADMIN ROLE 2 USER Y en la tabla Usuario tengo id: 0 - nombre: esteban - clave: 345 
							//	( y he definido en la tabla de rel roles-users al user id:0 ( esteban clave 345 ) con el roleId:1 ( ADMIN) ymb con el 2 (USER) x las dudas xa este ejemplo.. 
public class Role implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
    // Constructors, getters, and setters
    
}