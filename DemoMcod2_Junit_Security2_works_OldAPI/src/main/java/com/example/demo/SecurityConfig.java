package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
/* import org.springframework.security.config.Customizer;
 import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; */
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.business.UserService;

//---------------------------------------------------------------------------------------------------------------------------
// tutorial old ( deprecated api ) NEW API: -> https://howtodoinjava.com/spring-security/spring-boot-role-based-authorization/
// -------------------------------------------------------------------------------------------------------------------------- 

/*
 * @Configuration annotation (en springboot):
 * -------------- ---------- 
 * La anotación @Configuration en Spring Boot le dice al contenedor de Spring que debe procesar esta clase durante la inicialización 
 * del contexto de la aplicación para definir beans y configuraciones. 
 
 * Se utiliza para marcar una clase como una fuente de definiciones de beans y configuraciones para el contenedor de Spring, facilitando 
 * la definición y gestión de beans en una aplicación Spring.
 
 * Por ejemplo, se puede usar para configurar beans relacionados con bases de datos, servicios externos, etc.

 * */

/*
 * We used two annotations : “@Configuration” and “@EnableWebSecurity”. These annotations tells spring security to use our custom security 
 * configuration instead of default one. We created a Bean of SecurityFilterChain which implements our custom filter logic.
 */

//Clase:
@Configuration
@EnableWebSecurity // Esta annot xqvoyausar DCrypt (el desencriptador) ent debe estar habilitada la
					// seguridad web en la clase @Configuration
/* @EnableMethodSecurity(securedEnabled = true, prePostEnabled = true) */
public class SecurityConfig
		extends WebSecurityConfigurerAdapter { /*
												 * { // esto xa dcrypt tmb pero deprecated! -> extends
												 * webSecurityConfigurerAdapter ya no existe. hacerlo asi**{ // {
												 */
	@Autowired
	private UserService userDetailsService;

	@Autowired
	BCryptPasswordEncoder bcrypt;
	
	// conf:

	// saber!: estoy definiendo beans en esta configuracion ( no son metodos!) //

	/*
	 * @Bean annotation (en springboot): ----- ---------- sirve xa tener 1 sola
	 * instancia (singleton) en el container de spring xa q + adelante cualquier
	 * capa o cosa q necesite la obtenga a travez de un @autorired (di) singleton.
	 */

	// clase bean le puse passwordEncoder pero saber: se inyectará como clase
	// BcryptPasswordEncoder xqel nombre passwordEncoder esxa spring nomas,
	// singleton container managed xa q puedan usarla otras capas y clases x DI.
	// (ser inyectada)

	@Bean
	public BCryptPasswordEncoder passwordEncoder() { // tut mit
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/* auth.
			inMemoryAuthentication()
			.withUser("gil gerosa")
			.password("789")
			.roles("USER")
			.and()
			.withUser("gil gerosa")
			.password("789")
			.roles("USER","ADMIN"); */
		auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.
			authorizeRequests()
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic(); 
		
	}
	
	/* block commented new api mixed used to fal, this is mit tut so new api commened , by charly san
	 * @SuppressWarnings("removal")
	 * 
	 * @Bean // este componente bean implementa mi logica de filter personalizada (
	 * customized filter logic by me ) public SecurityFilterChain
	 * securityFilterChain(HttpSecurity http) throws Exception {
	 * 
	 * //These lines tells spring boot to authenticate every http requests:
	 * 
	 * http .authorizeHttpRequests()
	 * .anyRequest().authenticated().and().httpBasic();//.anyRequest().authenticated
	 * (); http .formLogin();
	 * 
	 * return http.build(); }
	 */

	/*
	 * 
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity
	 * httpSecurity) throws Exception {
	 * 
	 * httpSecurity.authorizeHttpRequests()
	 * .requestMatchers("/public/**").permitAll()
	 * .requestMatchers("/admin/**").hasRole("ADMIN") .anyRequest().authenticated()
	 * .and() .formLogin();
	 * 
	 * return httpSecurity.build();
	 * 
	 * httpSecurity.formLogin(Customizer.withDefaults());
	 * httpSecurity.csrf().disable();
	 * httpSecurity.headers().frameOptions().disable(); httpSecurity
	 * .authorizeHttpRequests() .requestMatchers("/test").hasAuthority("ADMIN")
	 * .requestMatchers("/h2-console/**").permitAll() .anyRequest().authenticated();
	 * 
	 * return httpSecurity.build(); return null; }
	 * 
	 * @Bean public UserDetailsService users() {
	 * 
	 * UserBuilder users = User.withDefaultPasswordEncoder();
	 * 
	 * UserDetails user = users .username("user") .password("password")
	 * .roles("USER") .authorities("READ") .build();
	 * 
	 * UserDetails admin = users .username("admin") .password("password")
	 * .roles("ADMIN") .authorities("READ", "CREATE", "DELETE") .build();
	 * 
	 * return new InMemoryUserDetailsManager(user, admin); }
	 */
}
