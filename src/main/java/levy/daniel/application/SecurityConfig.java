package levy.daniel.application;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * CLASSE SecurityConfig :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 23 nov. 2019
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true, 
		securedEnabled = true, 
		jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * UserDetailsService injecté par SPRING.
	 */
	@Autowired
	@Qualifier("MyUserDetailsService")
    private UserDetailsService userDetailsService;
	
	
	/**
	 * PasswordEncoder pour les mots de passe (SPRING SECURITY 5.2.1).
	 */
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(SecurityConfig.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public SecurityConfig() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
    /**
     * BCryptPasswordEncoder.
     *
     * @return : PasswordEncoder :  .<br/>
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } // Fin de passwordEncoder()._________________________________________

  
    

    /**
     * .<br/>
     * <br/>
     *
     * @param pAuth : 
	 * <code>org.springframework.security.config.annotation.authentication
	 * .builders.AuthenticationManagerBuilder</code>.
	 * @param pDataSource : <code>javax.sql.DataSource</code> : 
     * DataSource déclaré dans application.properties injecté par SPRING.
     * 
     * @throws Exception
     */
    @Autowired
    protected void globalConfig(
    		final AuthenticationManagerBuilder pAuth
    			, final DataSource pDataSource) throws Exception {
		
    	/* Authentification en MEMOIRE. */
//        pAuth.inMemoryAuthentication()
//        .passwordEncoder(this.passwordEncoder)
//        .withUser("user").password(this.passwordEncoder.encode("user")).roles("LECTEUR").authorities("LECTEUR")
//        .and()
//        .withUser("gestionnaire").password(this.passwordEncoder.encode("gestionnaire")).roles("LECTEUR", "GESTIONNAIRE").authorities("LECTEUR", "GESTIONNAIRE")
//        .and()
//        .withUser("admin").password(this.passwordEncoder.encode("admin")).roles("LECTEUR", "GESTIONNAIRE", "ADMIN").authorities("LECTEUR", "GESTIONNAIRE", "ADMIN");
    	
    	/* Authentification par USERS en BASE DE DONNEES.*/
        pAuth
        	.userDetailsService(this.userDetailsService)
        	.passwordEncoder(this.passwordEncoder);
           	
    	pAuth.jdbcAuthentication()
    		.dataSource(pDataSource)       	  		
    		.usersByUsernameQuery("select u.usernamespring as principal, u.password as credentials, 'true' from users_spring u where u.usernamespring = ? ")
    		.authoritiesByUsernameQuery("select u.usernamespring as principal, r.role as role from roles r join usersspring_roles ur on r.id=ur.role_id join users_spring u on ur.userspring_id=u.id where u.usernamespring = ? ")
    		.rolePrefix("ROLE_");
    	
    } // Fin de configure(...).____________________________________________

    
     
    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(
    		final HttpSecurity pHttpSecurity) throws Exception {
    	
    	/* laisse passer toutes les requêtes. */
//    	pHttpSecurity.authorizeRequests().antMatchers("/**").permitAll();
    	
//    	pHttpSecurity    	
//        .authorizeRequests()
//        .antMatchers("/static/**", "/css/**", "/").permitAll()         
//        .anyRequest().authenticated()        
//        .and().formLogin();  

    	
    	
    	/* utilise un formulaire PERSONNALISE (formLogin().loginPage("/LoginController/versLogin")). */
       	pHttpSecurity
        .authorizeRequests()
        .antMatchers("/MaBanqueController/enregistrerOperation").hasAnyRole("GESTIONNAIRE", "ADMIN")
        .antMatchers("/MaBanqueController/consulterCompte", "/MaBanqueController/mabanque").hasAnyRole("LECTEUR", "GESTIONNAIRE", "ADMIN")
        .antMatchers("/resources/**", "/static/**", "/css/**", "/", "/AccueilController/**", "/LoginController/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin().loginPage("/LoginController/versLogin").loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password")
		.defaultSuccessUrl("/MaBanqueController/mabanque")  
		.failureUrl("/LoginController/versLogin?error").permitAll()
		.and()
		.logout().logoutSuccessUrl("/LoginController/versLogin?logout").invalidateHttpSession(true).deleteCookies("JSESSIONID").permitAll()
		.and()
		.csrf().disable();  

       	/* utilise le formulaire de SPRING SECURITY (formLogin()) AVEC EnableGlobalMethodSecurity(securedEnabled=true). */
//       	pHttpSecurity
//        .authorizeRequests()
//        .antMatchers("/static").permitAll()
//        .antMatchers("/", "/AccueilController/**").permitAll()
//        .anyRequest().authenticated()
//		.and()
//		.formLogin().usernameParameter("username").passwordParameter("password")
//		.and()
//		.csrf().disable();  
    	
       	/* utilise un formulaire PERSONNALISE (formLogin().loginPage("/accueil/login")). */
//       	pHttpSecurity
//        .authorizeRequests()
//        .antMatchers("/MaBanqueController/enregistrerOperation").hasAnyRole("GESTIONNAIRE", "ADMIN")
//        .antMatchers("/MaBanqueController/consulterCompte", "/MaBanqueController/mabanque").hasAnyRole("LECTEUR", "GESTIONNAIRE", "ADMIN")
//        .antMatchers("/static/**", "/css/**", "/", "/AccueilController/**").permitAll()
//		.anyRequest().authenticated()
//		.and()
//		.formLogin().usernameParameter("username").passwordParameter("password").loginPage("/AccueilController/versLogin")
//		.defaultSuccessUrl("/MaBanqueController/mabanque")  
//		.failureUrl("/AccueilController/versLogin?error='true'").permitAll()
//		.and()
//		.logout().logoutSuccessUrl("/AccueilController/versLogin?logout='true'").invalidateHttpSession(true).permitAll()
//		.and()
//		.csrf().disable();  
    	
    } // Fin de configure(...).____________________________________________
    
    
	
} // FIN DE LA CLASSE SecurityConfig.----------------------------------------
