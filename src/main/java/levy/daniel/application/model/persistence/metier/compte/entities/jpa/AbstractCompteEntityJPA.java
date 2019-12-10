package levy.daniel.application.model.persistence.metier.compte.entities.jpa;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.IConstantesApplicatives;
import levy.daniel.application.model.persistence.metier.client.entities.jpa.ClientEntityJPA;
import levy.daniel.application.model.persistence.metier.operation.entities.jpa.AbstractOperationEntityJPA;


/**
 * CLASSE AbstractCompteEntityJPA :<br/>
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
@Entity(name="AbstractCompteEntityJPA")
@Table(name="ABSTRACT_COMPTES", schema="PUBLIC"
, uniqueConstraints=@UniqueConstraint(name="UNICITE_CODECOMPTE"
, columnNames={"CODE_COMPTE"})
, indexes={@Index(name="INDEX_DATECREATION", columnList="DATE_CREATION")})
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class AbstractCompteEntityJPA implements Serializable {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * 1L.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID du compte.
	 */
	private String codeCompte;
	
	/**
	 * date de création du compte.
	 */
	private LocalDateTime dateCreation;
	
	/**
	 * solde du compte.
	 */
	private double solde;
	
	/**
	 * client détenteur du compte.
	 */
	private ClientEntityJPA client;
	
	/**
	 * opérations sur le compte.
	 */
	private Collection<AbstractOperationEntityJPA> operations;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(AbstractCompteEntityJPA.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public AbstractCompteEntityJPA() {
		this(null, null, 0, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 * 
	 * @param pCodeCompte : String : ID du compte.
	 * @param pDateCreation : java.time.LocalDateTime : 
	 * date de création du compte.
	 * @param pSolde : double : solde du compte.
	 * @param pClient : ClientEntityJPA : client détenteur du compte.
	 */
	public AbstractCompteEntityJPA(
			final String pCodeCompte
				, final LocalDateTime pDateCreation
					, final double pSolde
						, final ClientEntityJPA pClient) {
		
		super();
		
		this.codeCompte = pCodeCompte;
		this.dateCreation = pDateCreation;
		this.solde = pSolde;
		this.client = pClient;
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(this.getCodeCompte()
				, this.getDateCreation()
					, this.getSolde()
						, this.getClient());
		
	} // Fin de hashCode().________________________________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(
			final Object pObject) {
		
		if (this == pObject) {
			return true;
		}
		if (pObject == null) {
			return false;
		}
		if (!(pObject instanceof AbstractCompteEntityJPA)) {
			return false;
		}
		
		final AbstractCompteEntityJPA other 
			= (AbstractCompteEntityJPA) pObject;
		
		return Objects.equals(this.getCodeCompte(), other.getCodeCompte()) 
				&& Objects.equals(this.getDateCreation(), other.getDateCreation())
				&& Double.doubleToLongBits(this.getSolde()) == Double.doubleToLongBits(other.getSolde())
				&& Objects.equals(this.getClient(), other.getClient());
		
	} // Fin de equals(...)._______________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("AbstractCompteEntityJPA [");
		
		builder.append("codeCompte=");
		if (this.getCodeCompte() != null) {			
			builder.append(this.getCodeCompte());
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("dateCreation=");
		if (this.getDateCreation() != null) {
			
			final DateTimeFormatter formatter 
				= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			
			builder.append(this.getDateCreation().format(formatter));
			
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("solde=");
		builder.append(this.getSolde());
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("client=");
		if (this.getClient() != null) {			
			builder.append(this.getClient().toString());
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append("]");
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________
	


	/**
	 * Getter de l'ID du compte.
	 *
	 * @return this.codeCompte : String.<br/>
	 */
	@Id
	@Column(name="CODE_COMPTE")
	public String getCodeCompte() {
		return this.codeCompte;
	} // Fin de getCodeCompte().___________________________________________


	
	/**
	* Setter de l'ID du compte.
	*
	* @param pCodeCompte : String : 
	* valeur à passer à this.codeCompte.<br/>
	*/
	public void setCodeCompte(
			final String pCodeCompte) {
		this.codeCompte = pCodeCompte;
	} // Fin de setCodeCompte(...).________________________________________


	
	/**
	 * Getter de la date de création du compte.
	 *
	 * @return this.dateCreation : java.time.LocalDateTime.<br/>
	 */
	@Column(name = "DATE_CREATION"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	public LocalDateTime getDateCreation() {
		return this.dateCreation;
	} // Fin de getDateCreation()._________________________________________


	
	/**
	* Setter de la date de création du compte.
	*
	* @param pDateCreation : java.time.LocalDateTime : 
	* valeur à passer à this.dateCreation.<br/>
	*/
	public void setDateCreation(
			final LocalDateTime pDateCreation) {
		this.dateCreation = pDateCreation;
	} // Fin de setDateCreation(...).______________________________________


	
	/**
	 * Getter du solde du compte.
	 *
	 * @return this.solde : double.<br/>
	 */
	@Column(name = "SOLDE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	public double getSolde() {
		return this.solde;
	} // Fin de getSolde().________________________________________________


	
	/**
	* Setter du solde du compte.
	*
	* @param pSolde : double : 
	* valeur à passer à this.solde.<br/>
	*/
	public void setSolde(
			final double pSolde) {
		this.solde = pSolde;
	} // Fin de setSolde(...)._____________________________________________


	
	/**
	 * Getter du client détenteur du compte.
	 *
	 * @return this.client : ClientEntityJPA.<br/>
	 */
	@ManyToOne(fetch=FetchType.LAZY)	
	@JoinColumns(
			foreignKey=@ForeignKey(name="FK_ABSTRACTCOMPTE_CLIENT")
			, value = {@JoinColumn(name="ID_CLIENT", referencedColumnName="ID")})
	public ClientEntityJPA getClient() {
		return this.client;
	} // Fin de getClient()._______________________________________________


	
	/**
	* Setter du client détenteur du compte.
	*
	* @param pClient : ClientEntityJPA : 
	* valeur à passer à this.client.<br/>
	*/
	public void setClient(
			final ClientEntityJPA pClient) {
		this.client = pClient;
	} // Fin de setClient(...).____________________________________________


	
	/**
	 * Getter des opérations sur le compte.
	 *
	 * @return this.operations : Collection<AbstractOperationEntityJPA>.<br/>
	 */
	@OneToMany(mappedBy="compte", fetch=FetchType.LAZY)
	public Collection<AbstractOperationEntityJPA> getOperations() {
		return this.operations;
	} // Fin de getOperations().___________________________________________


	
	/**
	* Setter des opérations sur le compte.
	*
	* @param pOperations : Collection<AbstractOperationEntityJPA> : 
	* valeur à passer à this.operations.<br/>
	*/
	public void setOperations(
			final Collection<AbstractOperationEntityJPA> pOperations) {
		this.operations = pOperations;
	} // Fin de setOperations(...).________________________________________

	
	
} // FIN DE LA CLASSE AbstractCompteEntityJPA.-------------------------------
