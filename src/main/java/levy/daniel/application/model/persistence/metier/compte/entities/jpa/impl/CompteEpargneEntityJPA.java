package levy.daniel.application.model.persistence.metier.compte.entities.jpa.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.IConstantesApplicatives;
import levy.daniel.application.model.persistence.metier.client.entities.jpa.ClientEntityJPA;
import levy.daniel.application.model.persistence.metier.compte.entities.jpa.AbstractCompteEntityJPA;

/**
 * CLASSE CompteEpargneEntityJPA :<br/>
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
@Entity(name="CompteEpargneEntityJPA")
@Table(name="COMPTES_EPARGNE", schema="PUBLIC")
@PrimaryKeyJoinColumn(
		foreignKey=@ForeignKey(name="FK_ABSTRACTCOMPTE_COMPTEEPARGNE")
		, name="ID_COMPTE_EPARGNE")
public class CompteEpargneEntityJPA extends AbstractCompteEntityJPA {

	// ************************ATTRIBUTS************************************/

	/**
	 * 1L.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * taux d'épargne du compte.
	 */
	private double taux;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(CompteEpargneEntityJPA.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public CompteEpargneEntityJPA() {
		this(null, null, 0, null, 0);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 * 
	 *@param pCodeCompte : String : ID du compte.
	 * @param pDateCreation : java.time.LocalDateTime : 
	 * date de création du compte.
	 * @param pSolde : double : solde du compte.
	 * @param pClient : ClientEntityJPA : client détenteur du compte.
	 * @param pTaux : double : taux de l'épargne du compte.
	 */
	public CompteEpargneEntityJPA(
			final String pCodeCompte
				, final LocalDateTime pDateCreation
					, final double pSolde
						, final ClientEntityJPA pClient
							, final double pTaux) {
		
		super(pCodeCompte, pDateCreation, pSolde, pClient);
		
		this.taux = pTaux;
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(this.getTaux());
		
		return result;
		
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
		if (!super.equals(pObject)) {
			return false;
		}
		if (!(pObject instanceof CompteEpargneEntityJPA)) {
			return false;
		}
		
		final CompteEpargneEntityJPA other = (CompteEpargneEntityJPA) pObject;
		
		return Double.doubleToLongBits(this.getTaux()) 
				== Double.doubleToLongBits(other.getTaux());
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {

		final StringBuilder builder = new StringBuilder();
		
		builder.append("CompteEpargneEntityJPA [");
		
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
		
		builder.append(IConstantesApplicatives.VIRGULE_ESPACE);
		
		builder.append("taux=");
		builder.append(this.getTaux());
		
		builder.append(']');
		
		return builder.toString();

	} // Fin de toString().________________________________________________



	/**
	 * Getter du taux d'épargne du compte.
	 *
	 * @return this.taux : double.<br/>
	 */
	@Column(name = "TAUX"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	public double getTaux() {
		return this.taux;
	} // Fin de getTaux()._________________________________________________


	
	/**
	* Setter du taux d'épargne du compte.
	*
	* @param pTaux : double : 
	* valeur à passer à this.taux.<br/>
	*/
	public void setTaux(
			final double pTaux) {
		this.taux = pTaux;
	} // Fin de setTaux(...).______________________________________________

	
	
} // FIN DE LA CLASSE CompteEpargneEntityJPA.--------------------------------
