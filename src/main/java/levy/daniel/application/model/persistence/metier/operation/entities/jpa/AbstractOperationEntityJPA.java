package levy.daniel.application.model.persistence.metier.operation.entities.jpa;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.Assert;

import levy.daniel.application.IConstantesApplicatives;
import levy.daniel.application.model.persistence.metier.compte.entities.jpa.AbstractCompteEntityJPA;

/**
 * CLASSE AbstractOperationEntityJPA :<br/>
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
@Entity(name="AbstractOperationEntityJPA")
@Table(name="ABSTRACT_OPERATIONS", schema="PUBLIC"
, indexes={@Index(name="INDEX_DATEOPERATION", columnList="DATE_OPERATION")})
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class AbstractOperationEntityJPA implements Serializable {

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
	 * date de l'opération.
	 */
	private LocalDateTime dateOperation;
	
	/**
	 * montant de l'opération.
	 */
	private double montant;
	
	/**
	 * compte sur lequel s'effectue l'opération.
	 */
	private AbstractCompteEntityJPA compte;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(AbstractOperationEntityJPA.class);

	// *************************METHODES************************************/
	
 
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public AbstractOperationEntityJPA() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________



	/**
	 * CONSTRUCTEUR COMPLET.
	 * <ul>
	 * <li>contrôle que pMontant >= 0 au moyen d'une 
	 * <code>org.springframework.util.Assert</code> 
	 * qui lèvera une RunTimeException (IllegalArgumentException).</li>
	 * </ul>
	 * 
	 * @param pDateOperation : java.time.LocalDateTime : date de l'opération. 
	 * @param pMontant : double : montant de l'opération.
	 * @param pCompte : AbstractCompteEntityJPA : 
	 * compte sur lequel s'effectue l'opération.
	 */
	public AbstractOperationEntityJPA(
			final LocalDateTime pDateOperation
				, final double pMontant
					, final AbstractCompteEntityJPA pCompte) {
		
		super();
		
		/* contrôle que pMontant >= 0 au moyen 
		 * d'une org.springframework.util.Assert 
		 * qui lèvera une RunTimeException (IllegalArgumentException). */
		Assert.isTrue(pMontant >= 0, "le montant doit être positif ou null");
		
		this.dateOperation = pDateOperation;
		this.montant = pMontant;
		this.compte = pCompte;
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		
		return Objects.hash(
				this.getDateOperation(), this.getMontant(), this.getCompte());
		
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
		if (!(pObject instanceof AbstractOperationEntityJPA)) {
			return false;
		}
		
		final AbstractOperationEntityJPA other 
			= (AbstractOperationEntityJPA) pObject;
		
		return Objects.equals(this.getDateOperation(), other.getDateOperation())
				&& Double.doubleToLongBits(this.getMontant()) 
					== Double.doubleToLongBits(other.getMontant())
				&& Objects.equals(this.getCompte(), other.getCompte());
				
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("AbstractOperationEntityJPA [");
		
		builder.append("id=");
		if (this.getId() != null) {			
			builder.append(this.getId());
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("dateOperation=");
		if (this.getDateOperation() != null) {
						
			final DateTimeFormatter formatter 
				= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			
			builder.append(this.getDateOperation().format(formatter));
	
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("montant=");
		builder.append(this.montant);
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("compte=");
		if (this.getCompte() != null) {			
			builder.append(this.getCompte().toString());
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
	 * Getter de la date de l'opération.
	 *
	 * @return this.dateOperation : java.time.LocalDateTime.<br/>
	 */
	@Column(name = "DATE_OPERATION"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	public LocalDateTime getDateOperation() {
		return this.dateOperation;
	} // Fin de getDateOperation().________________________________________


	
	/**
	* Setter de la date de l'opération.
	*
	* @param pDateOperation : java.time.LocalDateTime : 
	* valeur à passer à this.dateOperation.<br/>
	*/
	public void setDateOperation(
			final LocalDateTime pDateOperation) {
		this.dateOperation = pDateOperation;
	} // Fin de setDateOperation(...)._____________________________________


	
	/**
	 * Getter du montant de l'opération.
	 *
	 * @return this.montant : double.<br/>
	 */
	@Column(name = "MONTANT"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	public double getMontant() {
		return this.montant;
	} // Fin de getMontant().______________________________________________


	
	/**
	* Setter du montant de l'opération.
	* <ul>
	* <li>contrôle que pMontant >= 0 au moyen d'une 
	* <code>org.springframework.util.Assert</code> 
	* qui lèvera une RunTimeException (IllegalArgumentException).</li>
	* </ul>
	*
	* @param pMontant : double : 
	* valeur à passer à this.montant.<br/>
	*/
	public void setMontant(
			final double pMontant) {
		
		/* contrôle que pMontant >= 0 au moyen 
		 * d'une org.springframework.util.Assert 
		 * qui lèvera une RunTimeException (IllegalArgumentException). */
		Assert.isTrue(pMontant >= 0, "le montant doit être positif ou null");
		
		this.montant = pMontant;
	} // Fin de setMontant(...).___________________________________________


	
	/**
	 * Getter du compte sur lequel s'effectue l'opération.
	 *
	 * @return this.compte : AbstractCompteEntityJPA.<br/>
	 */
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns(
			foreignKey=@ForeignKey(name="FK_ABSTRACTOPERATION_COMPTE")
			, value = {@JoinColumn(name="ID_COMPTE", referencedColumnName="CODE_COMPTE")})
	public AbstractCompteEntityJPA getCompte() {
		return this.compte;
	} // Fin de getCompte()._______________________________________________


	
	/**
	* Setter du compte sur lequel s'effectue l'opération.
	*
	* @param pCompte : AbstractCompteEntityJPA : 
	* valeur à passer à this.compte.<br/>
	*/
	public void setCompte(
			final AbstractCompteEntityJPA pCompte) {
		this.compte = pCompte;
	} // Fin de setCompte(...).____________________________________________

	
	
} // FIN DE LA CLASSE AbstractOperationEntityJPA.----------------------------
