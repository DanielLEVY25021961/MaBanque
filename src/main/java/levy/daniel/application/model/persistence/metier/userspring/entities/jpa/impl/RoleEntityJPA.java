package levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.IConstantesApplicatives;

/**
 * CLASSE RoleEntityJPA :<br/>
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
@Entity(name="RoleEntityJPA")
@Table(name="ROLES", schema="PUBLIC"
, uniqueConstraints=@UniqueConstraint(name="UNICITE_ROLE"
, columnNames={"ROLE"})
, indexes={@Index(name="INDEX_ROLE", columnList="ROLE")})
public class RoleEntityJPA implements Serializable {

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
	 * rôle d'un User en session.
	 */
	private String role;
	
	/**
	 * description du rôle d'un User en session.
	 */
	private String descriptionRole;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(RoleEntityJPA.class);
	
	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 */
	public RoleEntityJPA() {
		this(null, null);
	} // Fin de  CONSTRUCTEUR COMPLET._____________________________________


		
	 /**
	 * CONSTRUCTEUR COMPLET.
	 * 
	 * @param pRole : String : rôle d'un User en session.
	 * @param pDescriptionRole : description du rôle d'un User en session.
	 */
	public RoleEntityJPA(
			final String pRole
				, final String pDescriptionRole) {
		
		super();
		
		this.role = pRole;
		this.descriptionRole = pDescriptionRole;
		
	} // Fin du CONSTRUCTEUR COMPLET.______________________________________

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		return Objects.hash(this.getDescriptionRole(), this.getRole());
	} // Fin de hashCode().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(
			final Object pObject) {
		
		if (this == pObject) {
			return true;
		}
		if (pObject == null) {
			return false;
		}
		if (!(pObject instanceof RoleEntityJPA)) {
			return false;
		}
		
		final RoleEntityJPA other 
			= (RoleEntityJPA) pObject;
		
		return Objects.equals(
				this.getDescriptionRole(), other.getDescriptionRole()) 
					&& Objects.equals(this.getRole(), other.getRole());
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("RoleEntityJPA [");
		
		builder.append("id=");
		if (this.id != null) {			
			builder.append(this.id);
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("role=");
		if (this.role != null) {			
			builder.append(this.role);
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("descriptionRole=");
		if (this.descriptionRole != null) {			
			builder.append(this.descriptionRole);
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



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
	 * Getter du rôle d'un User en session.
	 *
	 * @return this.role : String.<br/>
	 */
	@Column(name="ROLE"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	public String getRole() {
		return this.role;
	} // Fin de getRole()._________________________________________________



	/**
	* Setter du rôle d'un User en session.
	*
	* @param pRole : String : 
	* valeur à passer à this.role.<br/>
	*/
	public void setRole(
			final String pRole) {
		this.role = pRole;
	} // Fin de setRole(...).______________________________________________
	

	
	/**
	 * Getter de la description du rôle d'un User en session.
	 *
	 * @return this.descriptionRole : String.<br/>
	 */
	@Column(name="DESCRIPTION_ROLE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	public String getDescriptionRole() {
		return this.descriptionRole;
	} // Fin de getDescriptionRole().______________________________________
	

	
	/**
	* Setter de la description du rôle d'un User en session.
	*
	* @param pDescriptionRole : String : 
	* valeur à passer à this.descriptionRole.<br/>
	*/
	public void setDescriptionRole(
			final String pDescriptionRole) {
		this.descriptionRole = pDescriptionRole;
	} // Fin de setDescriptionRole(...).___________________________________


	
} // FIN DE LA CLASSE RoleEntityJPA.-----------------------------------------
