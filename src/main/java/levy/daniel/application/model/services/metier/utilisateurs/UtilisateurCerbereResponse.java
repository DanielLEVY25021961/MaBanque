package levy.daniel.application.model.services.metier.utilisateurs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;

/**
 * CLASSE UtilisateurCerbereResponse :<br/>
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
 * @since 10 mars 2019
 *
 */
public class UtilisateurCerbereResponse {

	// ************************ATTRIBUTS************************************/
	/**
	 * boolean stipulant si l'action concernée 
	 * par cette Reponse est valide.<br/>
	 * Par exemple, si la création d'un objet est possible.<br/>
	 */
	private boolean valide;
	
	/**
	 * DTO encapsulé dans la REPONSE à une requête.
	 */
	private IUtilisateurCerbereDTO dto;
	
	/**
	 * .
	 */
	private Map<String, String> errorsMap 
		= new ConcurrentHashMap<String, String>();
	

	/**
	 * Liste des messages d'erreur à l'intention de l'utilisateur.<br/>
	 * Ne peut jamis être null. <b>tester avec isEmpty()</b>.<br/>
	 */
	private List<String> messagesErrorUtilisateurList 
		= new ArrayList<String>(); 

	/**
	 * .
	 */
	private String messageGlobal;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereResponse.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereResponse() {
		this(false, null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR MALIN.<br/>
	 * 
	 * @param pValide
	 * @param pDto
	 * @param pMessageGlobal
	 */
	public UtilisateurCerbereResponse(
			final boolean pValide
				, final IUtilisateurCerbereDTO pDto
					, final String pMessageGlobal) {
		
		super();
		
		this.valide = pValide;
		this.dto = pDto;
		this.messageGlobal = pMessageGlobal;
		
	} // Fin de CONSTRUCTEUR MALIN.________________________________________


	
	/**
	 * ajoute une map d'erreurs pMap à <code>this.errorsMap</code>.<br/>
	 * <br/>
	 * - ne fait rien si pMap est null ou vide.<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;String,String&gt; : 
	 * map d'erreurs à ajouter à <code>this.errorsMap</code>.<br/>
	 */
	public final void ajouterMapAErrorsMap(
			final Map<String, String> pMap) {
		
		/* ne fait rien si pMap est null ou vide. */
		if (pMap == null || pMap.isEmpty()) {
			return;
		}
		
		this.errorsMap.putAll(pMap);
		
	} // Fin de ajouterMapAErrorsMap(...)._________________________________
	

	
	/**
	 * ajoute un message à 
	 * <code>this.messagesErrorUtilisateurList</code>.<br/>
	 * <br/>
	 * - ne fait rien si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String : message à ajouter à 
	 * <code>this.messagesErrorUtilisateurList</code>.<br/>
	 */
	public final void ajouterMessageAMessagesErrorUtilisateur(
			final String pString) {
		
		/* ne fait rien si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return;
		}
		
		this.messagesErrorUtilisateurList.add(pString);
		
	} // Fin de ajouterMessageAMessagesErrorUtilisateur(...)._______________
	

	
	/**
	 * ajoute une liste de messages à 
	 * <code>this.messagesErrorUtilisateurList</code>.<br/>
	 * <br/>
	 * - ne fait rien si pList est null ou vide.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;String&gt; : message à ajouter à 
	 * <code>this.messagesErrorUtilisateurList</code>.<br/>
	 */
	public final void ajouterListMessageAMessagesErrorUtilisateur(
			final List<String> pList) {
		
		/* ne fait rien si pList est null ou vide. */
		if (pList == null || pList.isEmpty()) {
			return;
		}
		
		this.messagesErrorUtilisateurList.addAll(pList);
		
	} // Fin de ajouterListMessageAMessagesErrorUtilisateur(...).__________
	
	
	
	/**
	 * Getter du boolean stipulant si l'action concernée 
	 * par cette Reponse est valide.<br/>
	 * Par exemple, si la création d'un objet est possible.<br/>
	 *
	 * @return this.valide : boolean.<br/>
	 */
	public final boolean isValide() {
		return this.valide;
	} // Fin de isValide().________________________________________________


	
	/**
	* Setter du boolean stipulant si l'action concernée 
	* par cette Reponse est valide.<br/>
	* Par exemple, si la création d'un objet est possible.<br/>
	*
	* @param pValide : boolean : 
	* valeur à passer à this.valide.<br/>
	*/
	public final void setValide(
			final boolean pValide) {
		this.valide = pValide;
	} // Fin de setValide(...).____________________________________________



	
	/**
	 * Getter .
	 *
	 * @return this.dto : IUtilisateurCerbereDTO.<br/>
	 */
	public final IUtilisateurCerbereDTO getDto() {
		return this.dto;
	}



	
	/**
	* .
	*
	* @param pDto : IUtilisateurCerbereDTO : 
	* valeur à passer à this.dto.<br/>
	*/
	public final void setDto(
			final IUtilisateurCerbereDTO pDto) {
		this.dto = pDto;
	}



	
	/**
	 * Getter .
	 *
	 * @return this.errorsMap : Map<String,String>.<br/>
	 */
	public final Map<String, String> getErrorsMap() {
		return this.errorsMap;
	}



	
	/**
	* .
	*
	* @param pErrorsMap : Map<String,String> : 
	* valeur à passer à this.errorsMap.<br/>
	*/
	public final void setErrorsMap(
			final Map<String, String> pErrorsMap) {
		this.errorsMap = pErrorsMap;
	}



	
	/**
	 * Getter .
	 *
	 * @return this.messagesErrorUtilisateurList : List<String>.<br/>
	 */
	public final List<String> getMessagesErrorUtilisateurList() {
		return this.messagesErrorUtilisateurList;
	}



	
	/**
	* .
	*
	* @param pMessagesErrorUtilisateurList : List<String> : 
	* valeur à passer à this.messagesErrorUtilisateurList.<br/>
	*/
	public final void setMessagesErrorUtilisateurList(
			final List<String> pMessagesErrorUtilisateurList) {
		this.messagesErrorUtilisateurList = pMessagesErrorUtilisateurList;
	}



	
	/**
	 * Getter .
	 *
	 * @return this.messageGlobal : String.<br/>
	 */
	public final String getMessageGlobal() {
		return this.messageGlobal;
	}



	
	/**
	* .
	*
	* @param pMessageGlobal : String : 
	* valeur à passer à this.messageGlobal.<br/>
	*/
	public final void setMessageGlobal(
			final String pMessageGlobal) {
		this.messageGlobal = pMessageGlobal;
	}
	
	
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereResponse.----------------------------
