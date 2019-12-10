package levy.daniel.application.model.services.valideurs;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE ErreursMaps :<br/>
 * Encapsulation (PURE FABRICATION) chargée de contenir deux Maps 
 * contenant les erreurs lors de la validation d'un OBJET METIER 
 * par un service.
 * <ul>
 * <li>une Map&lt;String,String&gt; <code>errorsMap</code> contenant les 
 * éventuels messages d'erreur pour chaque attribut avec 
 * toutes les violations des Règles de Gestion (RG) sur une seule ligne :
 * <ul>
 * <li>String : le nom de l'attribut</li>
 * <li>String : le message d'erreur pour l'attribut concaténé 
 * sur une seule ligne</li>
 * </ul>
 * </li>
 * <li>une Map&lt;String,List&lt;String&gt;&gt; <code>errorsMapDetaille</code> 
 * contenant les éventuels messages d'erreur pour chaque attribut avec 
 * chaque violation des Règles de Gestion (RG) dans une liste de lignes 
 * pour chaque attribut :
 * <ul>
 * <li>String : le nom de l'attribut</li>
 * <li>List&lt;String&gt; : les messages d'erreur pour l'attribut 
 * dans une liste avec une entrée par RG violée par l'attribut</li>
 * </ul>
 * </li>
 * </ul>
 * 
 * <hr/>
 * 
 * <p>
 * <img src="../../../../../../../../../javadoc/images/model/services/valideurs/ErreursMaps_diagramme_de_classes.png" 
 * alt="diagramme de classes du ErrorsMaps" />
 * </p>
 * 
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 25 mars 2019
 *
 */
public class ErreursMaps {

	// ************************ATTRIBUTS************************************/

	/**
	 * séparateur utilisé pour la concaténation 
	 * des divers messages de violation des RG pour 
	 * un attribut dans une Map&lt;String,String&gt; 
	 * <code>errorsMap</code><br/>
	 * System.getProperty("line.separator")
	 */
	public static final String SEPARATEUR_MESSAGES 
		= System.getProperty("line.separator");
		
	/**
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE = '\n';
	
	/**
	 * " - ".<br/>
	 */
	public static final String MOINS_ESPACE = " - ";

	/**
	 * boolean qui stipule si la validation est OK (toutes RG OK).<br/>
	 * true si la validation est OK.<br/>
	 */
	private boolean valide;

	/**
	 * boolean qui stipule si la validation est Admissible 
	 * (toutes RG indispensables OK).<br/>
	 * true si la validation des RG indispensables est OK.<br/>
	 */
	private boolean admissible;
	
	/**
	 * Map&lt;String,String&gt; contenant les 
	 * éventuels messages d'erreur pour chaque attribut avec 
	 * toutes les violations des Règles de Gestion (RG) sur une seule ligne :
	 * <ul>
	 * <li>String : le nom de l'attribut</li>
	 * <li>String : le message d'erreur pour l'attribut concaténé 
	 * sur une seule ligne</li>
	 * </ul>
	 */
	private Map<String, String> errorsMap 
		= new LinkedHashMap<String, String>();
	
	/**
	 * Map&lt;String,List&lt;String&gt;&gt;  
	 * contenant les éventuels messages d'erreur pour chaque attribut avec 
	 * chaque violation des Règles de Gestion (RG) dans une liste de lignes 
	 * pour chaque attribut :
	 * <ul>
	 * <li>String : le nom de l'attribut</li>
	 * <li>List&lt;String&gt; : les messages d'erreur pour l'attribut 
	 * dans une liste avec une entrée par RG violée par l'attribut</li>
	 * </ul>
	 */
	private Map<String, List<String>> errorsMapDetaille 
		= new LinkedHashMap<String, List<String>>();
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(ErreursMaps.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ErreursMaps() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * insère une entrée dans <code>this.errorsMap</code> pour pAttribut 
	 * avec le message concaténé pMessage.<br/>
	 * <br/>
	 * - ne fait rien si pAttribut est blank.<br/>
	 * - ne fait rien si pMessage == null.<br/>
	 * <br/>
	 *
	 * @param pAttribut : String : 
	 * nom de l'attribut.<br/>
	 * @param pMessage : String : 
	 * message concaténé à ajouter à <code>this.errorsMap</code> 
	 * pour pAttribut.<br/>
	 */
	public void ajouterEntreeAErrorsMap(
			final String pAttribut
				, final String pMessage) {
		
		/* ne fait rien si pAttribut est blank. */
		if (StringUtils.isBlank(pAttribut)) {
			return;
		}
		
		/* ne fait rien si pMessage == null. */
		if (pMessage == null) {
			return;
		}
		
		/* insère une entrée dans this.errorsMap pour pAttribut 
		 * avec le message concaténé pMessage. */
		this.errorsMap.put(pAttribut, pMessage);
		
	} // Fin de ajouterEntreeAErrorsMap(...).______________________________
	
	
	
	/**
	 * ajoute une <b>nouvelle</b> entrée dans 
	 * <code>this.errorsMapDetaille</code>.<br/>
	 * <ul>
	 * <li>ne fait rien si la clé existe déjà dans la map.</li>
	 * </ul>
	 *
	 * @param pAttribut : String : nom d'un attribut.<br/>
	 * @param pMessages : List&lt;String&gt; : liste de messages.<br/>
	 */
	public void ajouterEntreeAErrorsMapDetaille(
			final String pAttribut
				, final List<String> pMessages) {
		
		/* ne fait rien si la clé existe déjà dans la map. */
		if (!this.errorsMapDetaille.containsKey(pAttribut)) {
			this.errorsMapDetaille.put(pAttribut, pMessages);
		}
		
	} // Fin de ajouterEntreeAErrorsMapDetaille(...).______________________
	

	
	/**
	 * .<br/>
	 *
	 * @param pAttribut : String : nom d'un attribut.<br/>
	 * @param pMessage : String : 
	 * message concaténé à ajouter à <code>this.errorsMap</code> 
	 * pour pAttribut.<br/>
	 */
	public void ajouterMessageAAttributDansErrorsMapDetaille(
			final String pAttribut
				, final String pMessage) {
		
		final List<String> liste = this.errorsMapDetaille.get(pAttribut);
		
		if (liste != null) {
			if (!StringUtils.isBlank(pMessage)) {
				liste.add(pMessage);
			}
		}
	}

	
	
	/**
	 * retourne la liste des messages contenue dans 
	 * <code>this.errorsMapDetaille</code> pour l'attribut pAttribut.<br/>
	 * <br/>
	 * - retourne null si pAttribut n'existe pas dans la Map.<br/>
	 * <br/>
	 *
	 * @param pAttribut : String : nom d'un attribut.
	 * 
	 * @return : List&lt;String&gt; : 
	 * liste des essages de violation des RG.<br/>
	 */
	public List<String> fournirListeMessagesAttribut(
			final String pAttribut) {		
		return this.errorsMapDetaille.get(pAttribut);
	} // Fin de fournirListeMessagesAttribut(...)._________________________


	
	/**
	 * fournit une String pour l'affichage de 
	 * <code>this.errorsMap</code>.<br/>
	 * <br/>
	 * Par exemple, pour un attribut civilite :
	 * <pre>
	 * attribut : civilite
	 * erreurs concaténées : 
	 * la civilité doit obligatoirement être littérale
	 * la civilité doit obligatoirement comporter 15 caractères maximum
	 * la civilité doit obligatoirement respecter une liste finie de valeurs
	 * </pre>
	 *
	 * @return : String.<br/>
	 */
	public String afficherErrorsMap() {
		return this.afficherMapStringString(this.errorsMap);
	} // Fin de afficherErrorsMap()._______________________________________
	
	
	
	/**
	 * fournit une String pour l'affichage à la console 
	 * d'une Map&lt;String, String&gt;.<br/>
	 * <br/>
	 * Par exemple, pour un attribut civilite :
	 * <pre>
	 * attribut : civilite
	 * erreurs concaténées : 
	 * la civilité doit obligatoirement être littérale
	 * la civilité doit obligatoirement comporter 15 caractères maximum
	 * la civilité doit obligatoirement respecter une liste finie de valeurs
	 * </pre>
	 * retourne null si pMap == null.<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;String, String&gt;
	 * 
	 * @return : String.<br/>
	 */
	public String afficherMapStringString(
			final Map<String, String> pMap) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		final Set<Entry<String, String>> entrySet = pMap.entrySet();
		
		final Iterator<Entry<String, String>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<String, String> entry = ite.next();
			
			final String key = entry.getKey();
			final String value = entry.getValue();
			
			stb.append("attribut : ");
			stb.append(key);
			stb.append(SAUT_LIGNE);
			stb.append("erreurs concaténées : ");
			stb.append(SAUT_LIGNE);
			stb.append(value);
			stb.append(SAUT_LIGNE);
			stb.append(SAUT_LIGNE);
		}
		
		return stb.toString();
		
	} // Fin de afficherMapStringString(...).______________________________
	

	
	/**
	 * fournit une String pour l'affichage de 
	 * <code>this.errorsMapDetaille</code>.<br/>
	 * <br/>
	 * Par exemple, pour un attribut civilite :
	 * <pre>
	 * attribut : civilite
	 * liste des messages d'erreur : 
	 *  - la civilité doit obligatoirement être littérale
	 *  - la civilité doit obligatoirement comporter 15 caractères maximum
	 *  - la civilité doit obligatoirement respecter une liste finie de valeurs
	 * </pre>
	 *
	 * @return : String.<br/>
	 */
	public String afficherErrorsMapDetaille() {
		return this.afficherMapStringList(this.errorsMapDetaille);
	} // Fin de afficherErrorsMapDetaille()._______________________________
	
	
	
	/**
	 * fournit une String pour l'affichage 
	 * d'une Map&lt;String, List&lt;String&gt;&gt;<br/>
	 * <br/>
	 * Par exemple, pour un attribut civilite :
	 * <pre>
	 * attribut : civilite
	 * liste des messages d'erreur : 
	 *  - la civilité doit obligatoirement être littérale
	 *  - la civilité doit obligatoirement comporter 15 caractères maximum
	 *  - la civilité doit obligatoirement respecter une liste finie de valeurs
	 * </pre>
	 * return null si pMap == null.<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;String, List&lt;String&gt;&gt;.<br/>
	 * 
	 * @return : String.<br/>
	 */
	public String afficherMapStringList(
			final Map<String, List<String>> pMap) {
		
		/* return null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		
		final StringBuilder stb = new StringBuilder();
		
		final Set<Entry<String, List<String>>> entrySet 
			= pMap.entrySet();
		
		final Iterator<Entry<String, List<String>>> ite 
			= entrySet.iterator();
		
		while (ite.hasNext()) {
			
			final Entry<String, List<String>> entry = ite.next();
			final String nomAttribut = entry.getKey();
			final List<String> listeMessages = entry.getValue();
			
			if (!listeMessages.isEmpty()) {
				stb.append("attribut : ");
				stb.append(nomAttribut);
				stb.append(SAUT_LIGNE);
			}
			
			
			int compteur = 0;
			
			stb.append("liste des messages d'erreur : ");
			stb.append(SAUT_LIGNE);
			
			for (final String message : listeMessages) {
				
				compteur++;
				
				stb.append(MOINS_ESPACE);
				stb.append(message);
				
				if (compteur < listeMessages.size()) {
					stb.append(SAUT_LIGNE);
				}
			}
			
			stb.append(SAUT_LIGNE);
			
		}
		
		return stb.toString();
		
	} // Fin de afficherMapStringList(...).________________________________
	
	
		
	/**
	 * Getter du boolean qui stipule si la validation est OK.<br/>
	 * true si la validation est OK.<br/>
	 *
	 * @return this.valide : boolean.<br/>
	 */
	public final boolean isValide() {
		return this.valide;
	} // Fin de isValide().________________________________________________


	
	/**
	* Setter du boolean qui stipule si la validation est OK.<br/>
	* true si la validation est OK.<br/>
	*
	* @param pValide : boolean : 
	* valeur à passer à this.valide.<br/>
	*/
	public final void setValide(
			final boolean pValide) {
		this.valide = pValide;
	} // Fin de setValide(...).____________________________________________


	
	/**
	 * Getter du boolean qui stipule si la validation est Admissible 
	 * (toutes RG indispensables OK).<br/>
	 * true si la validation des RG indispensables est OK.<br/>
	 *
	 * @return this.admissible : boolean.<br/>
	 */
	public final boolean isAdmissible() {
		return this.admissible;
	} // Fin de isAdmissible().____________________________________________


	
	/**
	* Setter du boolean qui stipule si la validation est Admissible 
	* (toutes RG indispensables OK).<br/>
	* true si la validation des RG indispensables est OK.<br/>
	*
	* @param pAdmissible : boolean : 
	* valeur à passer à this.admissible.<br/>
	*/
	public final void setAdmissible(
			final boolean pAdmissible) {
		this.admissible = pAdmissible;
	} // Fin de setAdmissible(...).________________________________________



	/**
	 * Getter de la Map&lt;String,String&gt; contenant les 
	 * éventuels messages d'erreur pour chaque attribut avec 
	 * toutes les violations des Règles de Gestion (RG) sur une seule ligne :
	 * <ul>
	 * <li>String : le nom de l'attribut</li>
	 * <li>String : le message d'erreur pour l'attribut concaténé 
	 * sur une seule ligne</li>
	 * </ul>
	 *
	 * @return this.errorsMap : Map&lt;String,String&gt;.<br/>
	 */
	public final Map<String, String> getErrorsMap() {
		return this.errorsMap;
	} // Fin de getErrorsMap().____________________________________________


	
	/**
	* Setter de la Map&lt;String,String&gt; contenant les 
	* éventuels messages d'erreur pour chaque attribut avec 
	* toutes les violations des Règles de Gestion (RG) sur une seule ligne :
	* <ul>
	* <li>String : le nom de l'attribut</li>
	* <li>String : le message d'erreur pour l'attribut concaténé 
	* sur une seule ligne</li>
	* </ul>
	*
	* @param pErrorsMap : Map&lt;String,String&gt; : 
	* valeur à passer à this.errorsMap.<br/>
	*/
	public final void setErrorsMap(
			final Map<String, String> pErrorsMap) {
		this.errorsMap = pErrorsMap;
	} // Fin de setErrorsMap(...)._________________________________________

	
	
	/**
	 * Getter de la Map&lt;String,List&lt;String&gt;&gt;  
	 * contenant les éventuels messages d'erreur pour chaque attribut avec 
	 * chaque violation des Règles de Gestion (RG) dans une liste de lignes 
	 * pour chaque attribut :
	 * <ul>
	 * <li>String : le nom de l'attribut</li>
	 * <li>List&lt;String&gt; : les messages d'erreur pour l'attribut 
	 * dans une liste avec une entrée par RG violée par l'attribut</li>
	 * </ul>
	 *
	 * @return this.errorsMapDetaille : 
	 * Map&lt;String,List&lt;String&gt;&gt;.<br/>
	 */
	public final Map<String, List<String>> getErrorsMapDetaille() {
		return this.errorsMapDetaille;
	} // Fin de getErrorsMapDetaille().____________________________________


	
	/**
	* Setter de la Map&lt;String,List&lt;String&gt;&gt;  
	* contenant les éventuels messages d'erreur pour chaque attribut avec 
	* chaque violation des Règles de Gestion (RG) dans une liste de lignes 
	* pour chaque attribut :
	* <ul>
	* <li>String : le nom de l'attribut</li>
	* <li>List&lt;String&gt; : les messages d'erreur pour l'attribut 
	* dans une liste avec une entrée par RG violée par l'attribut</li>
	* </ul>
	*
	* @param pErrorsMapDetaille : Map&lt;String,List&lt;String&gt;&gt; : 
	* valeur à passer à this.errorsMapDetaille.<br/>
	*/
	public final void setErrorsMapDetaille(
			final Map<String, List<String>> pErrorsMapDetaille) {
		this.errorsMapDetaille = pErrorsMapDetaille;
	} // Fin de setErrorsMapDetaille(...)._________________________________
	
		
	
} // FIN DE LA CLASSE ErreursMaps.-------------------------------------------
