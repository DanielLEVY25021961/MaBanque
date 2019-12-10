package levy.daniel.application.model.services.metier.mabanque;

import org.springframework.data.domain.Page;

import levy.daniel.application.model.persistence.metier.compte.entities.jpa.AbstractCompteEntityJPA;
import levy.daniel.application.model.persistence.metier.operation.entities.jpa.AbstractOperationEntityJPA;

/**
 * INTERFACE IMaBanqueService :<br/>
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
public interface IMaBanqueService {

	/**
	 * consulte un compte d'ID pCodeCompte.
	 * <ul>
	 * <li>récupère l'objet métier via son ID 
	 * <strong>(sans Hibernate Proxy)</strong> auprès du DAO 
	 * en utilisant <code>Hibernate.unproxy(pProxy)</code>.</li>
	 * <li>throw une IdentifiantManquantRunTimeException 
	 * si pCodeCompte n'est pas renseigné (Blank).</li>
	 * <li>throw une ObjetManquantRunTimeException si l'objet 
	 * n'est pas trouvé ans le stockage.</li>
	 * </ul>
	 *
	 * @param pCodeCompte : String : ID du compte.
	 * 
	 * @return : AbstractCompteEntityJPA
	 * 
	 * @throws Exception 
	 */
	AbstractCompteEntityJPA consulterCompte(String pCodeCompte) throws Exception;

	
	
	/**
	 * verse un montant pMontant sur le compte d'ID pCodeCompte 
	 * et augmente le solde de pMontant.
	 * <ul>
	 * <li>pMontant doit être POSITIF.</li>
	 * <li>Crée et stocke une OPERATION de Versement.</li>
	 * <li>met à jour le solde du compte.</li>
	 * <li>sauvegarde le compte modifié.</li>
	 * <li>le versement est systématiquement effectué à la date système.</li>
	 * </ul>
	 * - throw une ObjetManquantRunTimeException si le compte est introuvable.<br/>
	 * - throw une MauvaisParametreRunTimeException si le montant est négatif.<br/>
	 * <br/>
	 *
	 * @param pCodeCompte : String : ID du compte.
	 * @param pMontant : double : montant à verser.
	 * 
	 * @throws Exception 
	 */
	void verser(String pCodeCompte, double pMontant) throws Exception;


	
	/**
	 * retire un montant pMontant sur le compte d'ID pCodeCompte
	 *  et le débite du solde.
	 * <ul>
	 * <li>pMontant doit être POSITIF.</li>
	 * <li>Crée et stocke une OPERATION de retrait.</li>
	 * <li>met à jour le solde du compte.</li>
	 * <li>sauvegarde le compte modifié.</li>
	 * <li>le retrait est systématiquement effectué à la date système.</li>
	 * </ul>
	 * - throw une ObjetManquantRunTimeException si le compte est introuvable.<br/>
	 * - throw une MauvaisParametreRunTimeException si le montant est négatif.<br/>
	 * - throw une MauvaisParametreRunTimeException si le retrait 
	 * excéderait les facilités de caisse.<br/>
	 * - nécessite de faire <code>Hibernate.unproxy(compte)</code> 
	 * pour connaitre l'instance réelle de compte.
	 * <br/>
	 *
	 * @param pCodeCompte : String : ID du compte.
	 * @param pMontant : double : montant à retirer.
	 * 
	 * @throws Exception 
	 */
	void retirer(String pCodeCompte, double pMontant) throws Exception;
	

	
	/**
	 * vire le montant pMontant depuis le compte source 
	 * vers le compte destinataire.
	 * <ul>
	 * <li>throw une ObjetManquantRunTimeException si le compte Source 
	 * est introuvable.</li>
	 * <li>throw une ObjetManquantRunTimeException si le compte Destinataire 
	 * est introuvable.</li>
	 * <li>throw une MauvaisParametreRunTimeException 
	 * si le montant est négatif.</li>
	 * <li>throw une MauvaisParametreRunTimeException 
	 * si les 2 comptes sont identiques.</li>
	 * <li>throw une MauvaisParametreRunTimeException 
	 * si le retrait excéderait les facilités de caisse.</li>
	 * <li>crée et stocke l'OPERATION de retrait sur le compte source.</li>
	 * <li>verse pMontant sur le compte destinataire.</li>
	 * <li>crée et stocke l'OPERATION de 
	 * versement sur le compte destinataire.</li>
	 * <li>le virement est systématiquement effectué à la date système.</li>
	 * </ul>
	 *
	 * @param pCodeCompteSource : String : ID du compte source.
	 * @param pCodeCompteDestinataire : String : ID du compte destinataire.
	 * @param pMontant : double : 
	 * montant à virer depuis la source vers le destinataire.
	 * 
	 * @throws Exception 
	 */
	void virer(String pCodeCompteSource, String pCodeCompteDestinataire, double pMontant) throws Exception;
	

	
	/**
	 * Liste les opérations du compte d'ID pCodeCompte avec pagination.
	 *
	 * @param pCodeCompte : String : ID du compte.
	 * @param pNumeroPage : int : numéro (0-based) de la page.
	 * @param pTaillePage : int : nombre d'enregistrements par page.
	 * 
	 * @return : Page&lt;AbstractOperationEntityJPA&gt;
	 * 
	 * @throws Exception 
	 */
	Page<AbstractOperationEntityJPA> listerOperationsParPage(String pCodeCompte, int pNumeroPage, int pTaillePage) throws Exception;
	
	
	
} // FIN DE L'INTERFACE IMaBanqueService.------------------------------------
