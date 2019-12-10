package levy.daniel.application.model.persistence.metier.client.entities.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.IConstantesApplicatives;
import levy.daniel.application.model.persistence.metier.compte.entities.jpa.AbstractCompteEntityJPA;

/**
 * CLASSE ClientEntityJPA :<br/>
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
 * @since 19 nov. 2019
 *
 */
@Entity(name="ClientEntityJPA")
@Table(name="CLIENTS", schema="PUBLIC"
, uniqueConstraints=@UniqueConstraint(name="UNICITE_EMAIL_CLIENT"
, columnNames={"EMAIL"})
, indexes={@Index(name="INDEX_NOM", columnList="NOM")})
public class ClientEntityJPA implements Serializable {

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
	 * nom du client.
	 */
	private String nom;
	
	/**
	 * email du client.
	 */
	private String email;
	
	/**
	 * comptes du client.
	 */
	private Collection<AbstractCompteEntityJPA> comptes;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(ClientEntityJPA.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public ClientEntityJPA() {
		this(null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	 /**
	 * CONSTRUCTEUR COMPLET
	 * 
	 * @param pNom : String : nom du client.
	 * @param pEmail : String : email du client.
	 */
	public ClientEntityJPA(
			final String pNom, final String pEmail) {
		
		super();
		
		this.nom = pNom;
		this.email = pEmail;
		
	} // Fin de ClientEntityJPA(...).______________________________________

	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		return Objects.hash(this.getNom(), this.getEmail());
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
		if (!(pObject instanceof ClientEntityJPA)) {
			return false;
		}
		
		final ClientEntityJPA other = (ClientEntityJPA) pObject;
		
		return Objects.equals(this.getNom(), other.getNom()) 
				&& Objects.equals(this.getEmail(), other.getEmail());
		
	} // Fin de equals(...)._______________________________________________

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		builder.append("ClientEntityJPA [");
		
		builder.append("id=");
		if (this.getId() != null) {			
			builder.append(this.getId());
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("nom=");
		if (this.getNom() != null) {			
			builder.append(this.getNom());
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("email=");
		if (this.getEmail() != null) {
			builder.append(this.getEmail());
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append("]");
		
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
	 * Getter du nom du client.
	 *
	 * @return this.nom : String.<br/>
	 */
	@Column(name="NOM"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	public String getNom() {
		return this.nom;
	} // Fin de getNom().__________________________________________________


	
	/**
	* Setter du nom du client.
	*
	* @param pNom : String : 
	* valeur à passer à this.nom.<br/>
	*/
	public void setNom(
			final String pNom) {
		this.nom = pNom;
	} // Fin de setNom(...)._______________________________________________


	
	/**
	 * Getter de l'email du client.
	 *
	 * @return this.email : String.<br/>
	 */
	@Column(name="EMAIL"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	public String getEmail() {
		return this.email;
	} // Fin de getEmail().________________________________________________


	
	/**
	* Setter de l'email du client.
	*
	* @param pEmail : String : 
	* valeur à passer à this.email.<br/>
	*/
	public void setEmail(
			final String pEmail) {
		this.email = pEmail;
	} // Fin de setEmail(...)._____________________________________________


	
	/**
	 * Getter des comptes du client.
	 *
	 * @return this.comptes : Collection&lt;AbstractCompteEntityJPA&gt;.<br/>
	 */
	@OneToMany(mappedBy="client", fetch=FetchType.LAZY)
	public Collection<AbstractCompteEntityJPA> getComptes() {
		return this.comptes;
	} // Fin de getComptes().______________________________________________



	/**
	* Setter des comptes du client.
	*
	* @param pComptes : Collection&lt;AbstractCompteEntityJPA&gt; : 
	* valeur à passer à this.comptes.<br/>
	*/
	public void setComptes(
			final Collection<AbstractCompteEntityJPA> pComptes) {
		this.comptes = pComptes;
	} // Fin de setComptes(...).___________________________________________

	
		
} // FIN DE LA CLASSE ClientEntityJPA.---------------------------------------
