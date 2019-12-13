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
 * CLASSE CompteCourantEntityJPA :<br/>
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
@Entity(name="CompteCourantEntityJPA")
@Table(name="COMPTES_COURANTS", schema="PUBLIC")
@PrimaryKeyJoinColumn(
		foreignKey=@ForeignKey(name="FK_ABSTRACTCOMPTE_COMPTECOURANT")
		, name="ID_COMPTE_COURANT")
public class CompteCourantEntityJPA extends AbstractCompteEntityJPA {

	// ************************ATTRIBUTS************************************/

	/**
	 * 1L.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * découvert autorisé du compte.
	 */
	private double decouvertAutorise;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(CompteCourantEntityJPA.class);

	// *************************METHODES************************************/
	

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public CompteCourantEntityJPA() {
		this(null, null, 0, null, 0);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 * 
	 * @param pCodeCompte : String : ID du compte.
	 * @param pDateCreation : java.time.LocalDateTime : 
	 * date de création du compte.
	 * @param pSolde : double : solde du compte.
	 * @param pClient : ClientEntityJPA : client détenteur du compte.
	 * @param pDecouvertAutorise : double : découvert autorisé du compte. 
	 */
	public CompteCourantEntityJPA(
			final String pCodeCompte
				, final LocalDateTime pDateCreation
					, final double pSolde
						, final ClientEntityJPA pClient
							, final double pDecouvertAutorise) {
		
		super(pCodeCompte, pDateCreation, pSolde, pClient);
		
		this.decouvertAutorise = pDecouvertAutorise;
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(this.getDecouvertAutorise());
		
		return result;
		
	} // Fin de hashCode().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(final Object pObject) {
		
		if (this == pObject) {
			return true;
		}
		if (!super.equals(pObject)) {
			return false;
		}
		if (!(pObject instanceof CompteCourantEntityJPA)) {
			return false;
		}
		
		final CompteCourantEntityJPA other = (CompteCourantEntityJPA) pObject;
		
		return Double.doubleToLongBits(this.getDecouvertAutorise()) 
				== Double.doubleToLongBits(other.getDecouvertAutorise());
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {

		final StringBuilder builder = new StringBuilder();
		
		builder.append("CompteCourantEntityJPA [");
		
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
		
		builder.append("decouvertAutorise=");
		builder.append(this.getDecouvertAutorise());
		
		builder.append(']');
		
		return builder.toString();

	} // Fin de toString().________________________________________________



	/**
	 * Getter du découvert autorisé du compte.
	 *
	 * @return this.decouvertAutorise : double.<br/>
	 */
	@Column(name = "DECOUVERT_AUTORISE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	public double getDecouvertAutorise() {
		return this.decouvertAutorise;
	} // Fin de getDecouvertAutorise().____________________________________


	
	/**
	* Setter du découvert autorisé du compte.
	*
	* @param pDecouvertAutorise : double : 
	* valeur à passer à this.decouvertAutorise.<br/>
	*/
	public void setDecouvertAutorise(
			final double pDecouvertAutorise) {
		this.decouvertAutorise = pDecouvertAutorise;
	} // Fin de setDecouvertAutorise(...)._________________________________

		
	
} // FIN DE LA CLASSE CompteCourantEntityJPA.--------------------------------
