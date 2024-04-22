package com.example.demo.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.data.model.Role;
import com.example.demo.data.model.Usuario;




/* Bloque hasta el fina lcommented new api mixed used to fail, now: old api tut mit
 *  
 * public class UserDetailsImpl implements UserDetails {
	 *
	 * private static final long serialVersionUID = 1L; //private User user ;
	 * private Usuario usu ;
	 * 
	 * public UserDetailsImpl(Usuario usu) { super(); this.usu = usu; }
	 * 
	 * public Usuario getUsu() { return usu; }
	 * 
	 * public void setUsu(Usuario usu) { this.usu = usu; }
	 * 
	 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
	 * 
	 * Set<Role> roles = usu.getRoles();
	 * 
	 * List<SimpleGrantedAuthority> authorities = new
	 * ArrayList<SimpleGrantedAuthority>(); //new ArrayList<>();
	 * 
	 * for (Role role : roles) { authorities.add(new
	 * SimpleGrantedAuthority(role.getName())); } return authorities; }
	 * 
	 * @Override public String getPassword() { return usu.getClave();
	 * //user.getPassword(); }
	 * 
	 * @Override public String getUsername() { return usu.getNombre();
	 * //user.getUsername(); }
	 * 
	 * @Override public boolean isAccountNonExpired() { return false; }
	 * 
	 * @Override public boolean isAccountNonLocked() { return false; }
	 * 
	 * @Override public boolean isCredentialsNonExpired() { return false; }
	 * 
	 * @Override public boolean isEnabled() { return true; }
	 *
} */
