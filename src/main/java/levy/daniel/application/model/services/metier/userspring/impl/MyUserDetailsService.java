package levy.daniel.application.model.services.metier.userspring.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import levy.daniel.application.model.persistence.metier.userspring.dao.jpaspring.UserSpringDao;
import levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl.MyUserDetails;
import levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl.UserSpringEntityJPA;

/**
 * CLASSE MyUserDetailsService :<br/>
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
 * @since 29 nov. 2019
 *
 */
@Service(value="MyUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * UserSpringDao injecté par SPRING.
	 */
	@Autowired
	@Qualifier("UserSpringDao")
	private UserSpringDao userSpringDao;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(MyUserDetailsService.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public MyUserDetailsService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserDetails loadUserByUsername(final String pUsername) 
			throws UsernameNotFoundException {
		
		if (StringUtils.isBlank(pUsername)) {
			
			final String messageBadUsername = "pUsername ne peut être blank";
			throw new UsernameNotFoundException(messageBadUsername);
		}
		
		final String messageKO 
			= "Objet métier introuvable dans le stockage : " + pUsername;
		
		UserSpringEntityJPA userSpringEntityJPA = null;
		
		try {
			
			userSpringEntityJPA 
				= this.userSpringDao.findByUsername(pUsername);
			
		} catch (Exception e) {
			throw new UsernameNotFoundException(messageKO);
		}
		
		if (userSpringEntityJPA == null) {
			throw new UsernameNotFoundException(messageKO);
		}
		
		final UserDetails userDetails 
			= new MyUserDetails(userSpringEntityJPA);
		
		return userDetails;
		
	} // Fin de loadUserByUsername(...).___________________________________


	
} // FIN DE LA CLASSE MyUserDetailsService.----------------------------------
