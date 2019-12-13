package levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.IConstantesApplicatives;

/**
 * CLASSE UserSpringEntityJPA :<br/>
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
 * @since 24 nov. 2019
 *
 */
@Entity(name="UserSpringEntityJPA")
@Table(name="USERS_SPRING", schema="PUBLIC"
, uniqueConstraints=@UniqueConstraint(name="UNICITE_USER_SPRING"
, columnNames={"USERNAMESPRING"})
, indexes={@Index(name="INDEX_USER_SPRING", columnList="USERNAMESPRING")})
//public class UserSpringEntityJPA implements UserDetails {
public class UserSpringEntityJPA implements Serializable {

	// ************************ATTRIBUTS************************************/

	/**
	 * 1L.
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * ID en base.
	 */
	private Long id;
	
	/**
	 * Login (identifiant) du User en session.
	 */
	private String username;
	
	/**
	 * password (mot de passe) du User en session.
	 */
	private String password;
	
	/**
	 * rôles du User en session.
	 */
	private Collection<RoleEntityJPA> roles = new ArrayList<RoleEntityJPA>();
	

	/**
	 * détermine si le USER est activé.
	 */
	private boolean active;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UserSpringEntityJPA.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public UserSpringEntityJPA() {
		this(null, null, false);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________



	/**
	 * CONSTRUCTEUR COMPLET.
	 * 
	 * @param pUsername : String : Login (identifiant) du User en session.
	 * @param pPassword : String : password (mot de passe) du User en session.
	 * @param pActive : boolean : détermine si le USER est actif.
	 */
	public UserSpringEntityJPA(
			final String pUsername
				, final String pPassword
					, final boolean pActive) {
		
		super();
		
		this.username = pUsername;
		this.password = pPassword;
		this.active = pActive;
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		return Objects.hash(this.getPassword(), this.getUsername());
	} // Fin de hashCode().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(
			final Object pObject) {
		
		if (!super.equals(pObject)) {
			return false;
		}
		
		if (this == pObject) {
			return true;
		}
		if (pObject == null) {
			return false;
		}
		if (!(pObject instanceof UserSpringEntityJPA)) {
			return false;
		}
		
		final UserSpringEntityJPA other = (UserSpringEntityJPA) pObject;
		
		return Objects.equals(this.getPassword(), other.getPassword()) 
				&& Objects.equals(this.getUsername(), other.getUsername());
		
	} // Fiin de equals(...).______________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("UserSpringEntityJPA [");
		
		builder.append("id=");
		if (this.id != null) {			
			builder.append(this.id);
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("username=");
		if (this.username != null) {			
			builder.append(this.username);
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("password=");
		if (this.password != null) {
			builder.append(this.password);
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("activé=");
		builder.append(this.active);
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("roles=");
		if (this.roles != null) {			
			builder.append(this.roles.toString());
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________


	
	/**
	 * ajoute le RoleEntityJPA pRole à la Collection de Roles 
	 * du present User.<br/>
	 *
	 * @param pRole : RoleEntityJPA.
	 * 
	 * @return : boolean : true si l'ajout a été effectué.
	 */
	public final boolean ajouterRole(
			final RoleEntityJPA pRole) {
		return this.getRoles().add(pRole);
	} // Fin de ajouterRole(...).__________________________________________
	
	

	/**
	 * retire le RoleEntityJPA pRole à la Collection de Roles 
	 * du present User.
	 *
	 * @param pRole : RoleEntityJPA.
	 * 
	 * @return : boolean : true si l'ajout a été effectué.
	 */
	public final boolean retirerRole(
			final RoleEntityJPA pRole) {
		return this.getRoles().remove(pRole);
	} // Fin de retirerRole(...).__________________________________________
	
	
	
	/**
	 * Getter de l'ID en base.
	 *
	 * @return this.id : Long.<br/>
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	public Long getId() {
		return this.id;
	} // Fin de getId().___________________________________________________



	/**
	* Setter de l'ID en base.
	*
	* @param pId : Long : 
	* valeur à passer à this.id.<br/>
	*/
	public void setId(
			final Long pId) {
		this.id = pId;
	} // Fin de setId(...).________________________________________________

	
	
	/**
	 * Getter du Login (identifiant) du User en session.
	 *
	 * @return this.username : String.<br/>
	 */
//	@Override
	@Column(name="USERNAMESPRING"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	public String getUsername() {
		return this.username;
	} // Fin de getUsername()._____________________________________________


	
	/**
	* Setter du Login (identifiant) du User en session.
	*
	* @param pUsername : String : 
	* valeur à passer à this.username.<br/>
	*/
	public void setUsername(
			final String pUsername) {
		this.username = pUsername;
	} // Fin de setUsername(...).__________________________________________


	
	/**
	 * Getter du password (mot de passe) du User en session.
	 *
	 * @return this.password : String.<br/>
	 */
//	@Override
	@Column(name="PASSWORD"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	public String getPassword() {
		return this.password;
	} // Fin de getPassword()._____________________________________________
	

	
	/**
	* Setter du password (mot de passe) du User en session.
	*
	* @param pPassword : String : 
	* valeur à passer à this.password.<br/>
	*/
	public void setPassword(
			final String pPassword) {
		this.password = pPassword;
	} // Fin de setPassword(...).__________________________________________


		
	/**
	 * Getter du boolean qui détermine si le USER est activé.
	 *
	 * @return this.active : boolean.<br/>
	 */
	public boolean isActive() {
		return this.active;
	} // Fin de isActive().________________________________________________


	
	/**
	* Setter du boolean qui détermine si le USER est activé.
	*
	* @param pActive : boolean : 
	* valeur à passer à this.active.<br/>
	*/
	public void setActive(final boolean pActive) {
		this.active = pActive;
	} // Fin de setActive(...).____________________________________________



	/**
	 * Getter des rôles du User en session.<br/>
	 * ATTENTION : doit être fetch=FetchType.EAGER 
	 * pour fonctionner avec SPRING DECURITY.
	 *
	 * @return this.roles : Collection&lt;RoleEntityJPA&gt;.<br/>
	 */
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="USERSSPRING_ROLES",
			joinColumns=@JoinColumn(name="USERSPRING_ID", referencedColumnName="ID", foreignKey=@ForeignKey(name="FK_USERSPRING")),
			inverseJoinColumns=@JoinColumn(name="ROLE_ID", referencedColumnName="ID", foreignKey=@ForeignKey(name="FK_ROLE"))
	)
	public Collection<RoleEntityJPA> getRoles() {
		return this.roles;
	} // Fin de getRoles().________________________________________________


	
	/**
	* Setter des rôles du User en session.
	*
	* @param pRoles : Collection&lt;RoleEntityJPA&gt; : 
	* valeur à passer à this.roles.<br/>
	*/
	public void setRoles(
			final Collection<RoleEntityJPA> pRoles) {
		this.roles = pRoles;
	} // Fin de setRoles(...)._____________________________________________



//	/**
//	 * {@inheritDoc}
//	 */
//	@Transient
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		
//		 final String mesRoles 
//		 	= StringUtils.collectionToCommaDelimitedString(
//		 			this.getRoles().stream()
//		 		.map(role -> role.getRole())
//		 		.collect(Collectors.toList()));
//		
//		 final List<GrantedAuthority> listAuthorities 
//		 	= AuthorityUtils.commaSeparatedStringToAuthorityList(mesRoles);
//		 	
//		 return listAuthorities;
//		 
//	} // Fin de getAuthorities().__________________________________________



//	/**
//	 * {@inheritDoc}
//	 */
//	@Transient
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	} // Fin de isAccountNonExpired()._____________________________________



//	/**
//	 * {@inheritDoc}
//	 */
//	@Transient
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	} // Fin de isAccountNonLocked().______________________________________



//	/**
//	 * {@inheritDoc}
//	 */
//	@Transient
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	} // Fin de isCredentialsNonExpired()._________________________________



//	/**
//	 * {@inheritDoc}
//	 */
//	@Transient
//	@Override
//	public boolean isEnabled() {
//		return this.isActive();
//	} // Fin de isEnabled()._______________________________________________
	


} // FIN DE LA CLASSE UserSpringEntityJPA.-----------------------------------
