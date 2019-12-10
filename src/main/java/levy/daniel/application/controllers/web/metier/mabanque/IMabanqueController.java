package levy.daniel.application.controllers.web.metier.mabanque;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * INTERFACE IMabanqueController :<br/>
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
 * @since 26 nov. 2019
 *
 */
public interface IMabanqueController {

	
	
	/**
	 * redirige vers la page de la banque 
	 * <code>/metier/mabanque/mabanque.html</code> 
	 * à chaque déclenchement de l'<strong>action</strong> 
	 * <code>/MaBanqueController/mabanque</code>.<br/>
	 * Le début de path "/MaBanqueController" est déjà indiqué dans l'annotation 
	 * RequestMapping au dessus de la classe.
	 *
	 * @return : String : "/metier/mabanque/mabanque".<br/>
	 */
	String versMaBanque();
	
	

	/**
	 * <strong>peuple un compte d'ID pCodeCompte</strong> 
	 * et <strong>aiguille</strong> vers la page HTML de la banque 
	 * <code>/metier/mabanque/mabanque.html</code> 
	 * à chaque déclenchement de l'<strong>action 
	 * <code>"/MaBanqueController/consulterCompte"</code></strong>.<br/>
	 * Le début de path "/MaBanqueController" est déjà indiqué dans l'annotation 
	 * RequestMapping au dessus de la classe.
	 * <ul>
	 * <li>passe l'identifiant de l'objet métier au 
	 * <code>org.springframework.ui.Model</code> 
	 * pour pouvoir l'utiliser dans toute la page.</li>
	 * <li>réclame l'objet métier (sans le Hibernate Proxy) au SERVICE.</li>
	 * <li>passe l'objet métier (sans le Hibernate Proxy) au 
	 * <code>org.springframework.ui.Model</code>.</li>
	 * <li>passe le nom simple (Type) de la classe réelle de l'objet métier 
	 * (sans le Hibernate Proxy) 
	 * au <code>org.springframework.ui.Model</code>.</li>
	 * <li>réclame la liste paginée des objets dépendants 
	 * de l'objet métier au SERVICE.</li>
	 * <li>passe le nombre total de pages d'opérations au Model.</li>
	 * <li>passe la liste paginée des objets dépendants de l'objet métier 
	 * au <code>org.springframework.ui.Model</code>.</li>
	 * <li>passe le message de l'exception 
	 * - si le SERVICE a retourné une Exception - au Model.</li>
	 * <li>aiguille vers la page de la banque avec le Model alimenté 
	 * et toutes les valeurs <strong>rafraîchies</strong>.</li>
	 * </ul>
	 *
	 * @param pCodeCompte : String : ID de l'objet métier.
	 * @param pPage : int :
	 * @param pSize : int :
	 * @param pModel : org.springframework.ui.Model.
	 * 
	 * @return String : chemin vers la page HTML : "/metier/mabanque/mabanque"
	 */
	String consulterCompte(String pCodeCompte, int pPage, int pSize, Model pModel);
	
	

	/**
	 * <strong>enregistre une opération sur un compte d'ID pCodeCompte</strong> 
	 * et <strong>redirige</strong> vers la page de la banque 
	 * <code>/metier/mabanque/mabanque.html</code> 
	 * à chaque déclenchement de l'<strong>action</strong> 
	 * <code>/MaBanqueController/enregistrerOperation</code>.<br/>
	 * Le début de path "/MaBanqueController" est déjà indiqué dans l'annotation 
	 * RequestMapping au dessus de la classe.
	 * <ul>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 *
	 * @param pCodeCompte : String : ID de l'objet métier.
	 * @param pTypeOperation  : String : Type de l'opération 
	 * (Versement, retrait, Virement).
	 * @param pMontant : double : montant de l'opération.
	 * @param pCodeCompteDestinataire : String : 
	 * ID du compte destinataire d'un virement. 
	 * @param pModel : org.springframework.ui.Model.
	 * @param pRedirectAttributes 
	 * 
	 * @return String : "/metier/mabanque/mabanque"
	 */
	String enregistrerOperation(String pCodeCompte, String pTypeOperation, double pMontant,
			String pCodeCompteDestinataire, Model pModel, RedirectAttributes pRedirectAttributes);
	

	
} // FIN DE L'INTERFACE IMabanqueController.---------------------------------