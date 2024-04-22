package com.example.demo.controller;
/* all new api ex commented , now old mit tut enabled  
 * import org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.example.demo.component.UserDetailsImpl; import
 * com.example.demo.data.dao.IUsuarioRepo; import
 * com.example.demo.data.model.Usuario;
 * 
 * @Service public class UserDetailsServiceImpl implements UserDetailsService {
 * 
 * private final IUsuarioRepo usuRepo;
 * 
 * public UserDetailsServiceImpl(IUsuarioRepo userRepository) { this.usuRepo =
 * userRepository; }
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException {
 * 
 * Usuario usu = usuRepo.findByNombre(username); if (usu == null) { throw new
 * UsernameNotFoundException("User not found"); } return new
 * UserDetailsImpl(usu); } }
 */