package levy.daniel.application.model.persistence.metier.operation.entities.jpa.impl;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.persistence.metier.compte.entities.jpa.AbstractCompteEntityJPA;
import levy.daniel.application.model.persistence.metier.operation.entities.jpa.AbstractOperationEntityJPA;

/**
 * CLASSE VersementEntityJPA :<br/>
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
@Entity(name="VersementEntityJPA")
@Table(name="VERSEMENTS", schema="PUBLIC")
@PrimaryKeyJoinColumn(
		foreignKey=@ForeignKey(name="FK_ABSTRACTOPERATION_VERSEMENT")
		, name="ID_VERSEMENT")
public class VersementEntityJPA extends AbstractOperationEntityJPA {

	// ************************ATTRIBUTS************************************/

	/**
	 * .
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(VersementEntityJPA.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public VersementEntityJPA() {
		this(null, 0, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 * 
	 * @param pDateOperation : java.time.LocalDateTime : date de l'opération. 
	 * @param pMontant : double : montant de l'opération.
	 * @param pCompte : AbstractCompteEntityJPA : 
	 * compte sur lequel s'effectue l'opération.
	 */
	public VersementEntityJPA(
			final LocalDateTime pDateOperation
				, final double pMontant
					, final AbstractCompteEntityJPA pCompte) {
		
		super(pDateOperation, pMontant, pCompte);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
			
	
} // FIN DE LA CLASSE VersementEntityJPA.------------------------------------
