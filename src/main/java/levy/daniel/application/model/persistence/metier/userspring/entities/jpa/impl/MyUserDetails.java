package levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * CLASSE MyUserDetails :<br/>
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
public class MyUserDetails implements UserDetails {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * Login (identifiant) du User en session.
	 */
	private transient String username;
	
	/**
	 * password (mot de passe) du User en session.
	 */
	private transient String password;
	
	/**
	 * rôles du User en session.
	 */
	private Collection<RoleEntityJPA> roles;
	

	/**
	 * détermine si le USER est activé.
	 */
	private transient boolean active;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(MyUserDetails.class);

	// *************************METHODES************************************/
	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public MyUserDetails() {
		this(null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 * 
	 * @param pUser : UserSpringEntityJPA.
	 */
	public MyUserDetails(final UserSpringEntityJPA pUser) {
		
		super();
		
		if (pUser != null) {
			
			this.username = pUser.getUsername();
			this.password = pUser.getPassword();
			this.active = pUser.isActive();
			this.roles = pUser.getRoles();
			
		}		
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		final List<GrantedAuthority> resultat 
			= new ArrayList<GrantedAuthority>();
		
		for (final RoleEntityJPA role : this.roles) {
			
			final GrantedAuthority grantedAuthority 
				= new SimpleGrantedAuthority(role.getRole());
			resultat.add(grantedAuthority);
			
		}
		
		return resultat;
		
	} // Fin de getAuthorities().__________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getUsername() {
		return this.username;
	} // Fin de getUsername()._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPassword() {
		return this.password;
	} // Fin de getPassword()._____________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	} // Fin de isAccountNonExpired()._____________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	} // Fin de isAccountNonLocked().______________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	} // Fin de isCredentialsNonExpired()._________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		return this.active;
	} // Fin de isEnabled()._______________________________________________
	


} // FIN DE LA CLASSE MyUserDetails.-----------------------------------------
