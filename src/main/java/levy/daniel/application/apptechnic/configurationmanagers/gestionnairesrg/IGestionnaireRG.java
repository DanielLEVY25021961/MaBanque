package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg;

import java.nio.file.Path;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * INTERFACE IGestionnaireRG :<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 5 déc. 2017
 *
 */
public interface IGestionnaireRG {

	
	
	/**
	 * <ul>
	 * <li>Retourne l'<b>en-tête csv</b> d'une <b>LigneRG</b>.</li>
	 * <li>"id;Actif;activité des contrôles sur l'attribut;activité de la RG;
	 * RG implémentée;clé du type de contrôle;type de contrôle;Message d'erreur;
	 * Objet Métier concerné;Attribut concerné;Classe implémentant la RG;
	 * Méthode implémentant la RG;properties;clé;"</li>
	 * </ul>
	 *
	 * @return : String : "id;Actif;
	 * activité des contrôles sur l'attribut;activité de la RG;
	 * RG implémentée;clé du type de contrôle;type de contrôle
	 * ;Message d'erreur;
	 * Objet Métier concerné;Attribut concerné;Classe implémentant la RG;
	 * Méthode implémentant la RG;properties;clé;"<br/>
	 */
	String getEnTeteCsv();
	
	
	
	/**
	 * <ul>
	 * <li>Retourne une String pour l'affichage de la liste 
	 * des RG implémentées dans le GestionnaireRG.</li>
	 * <li>La String contient la liste des LignesRG au format csv.</li>
	 * <li>affiche chaque RG sur une nouvelle ligne.</li>
	 * </ul>
	 * Trie la Map this.mapRG en fonction du numéro et du 
	 * nom des Règles de Gestion (RG).<br/>
	 * <br/>
	 * retourne null si mapRG == null.<br/>
	 * <br/>
	 *
	 * @return : String : liste csv des RG implémentées.<br/>
	 */
	String afficherListeRGImplementeesCsv();

		
	
	/**
	 * <ul>
	 * Getter de la Map&lt;String, LigneRG&gt; contenant 
	 * toutes les RG implémentées dans 
	 * le Gestionnaire de RG avec :
	 * <li>String : nom de la RG</li>
	 * <li>LigneRG : Encapsulation des éléments relatifs à la RG</li>
	 * </ul>
	 * Une ligne RG encapsule :<br/>
	 * [id;Actif;activité des contrôles sur l'attribut;activité de la RG
	 * ;RG implémentée;clé du type de contrôle;type de contrôle
	 * ;Message d'erreur;Objet Métier concerné;Attribut concerné
	 * ;Classe implémentant la RG;Méthode implémentant la RG;
	 * properties;clé;].<br/>
	 * <br/>
	 *
	 * @return mapRG : Map&lt;String, LigneRG&gt;.<br/>
	 */
	Map<String, LigneRG> getMapRG();
	

		
	/**
	 * method getBundleExterneRG() :<br/>
	 * <ul>
	 * <li>
	 * Fournit le ResourceBundle associé au fichier <i>externe</i> 
	 * (hors classpath) <b>objet_RG.properties</b> 
	 * avec la Locale sélectionnée dans l'application 
	 * <code><b>LocaleManager.getLocaleApplication()</b></code>.
	 * </li>
	 * </ul>
	 * <br/>
	 *
	 * @return : ResourceBundle : objet_RG.properties.<br/>
	 * 
	 * @throws Exception 
	 */
	ResourceBundle getBundleExterneRG() throws Exception;
	
	
	
	/**
	 * retourne le Path absolu du fichier .properties de RG 
	 * utilisé dans le ResourceBundle externe pour l'OBJET METIER.<br/>
	 *
	 * @return Path : Path absolu du fichier .properties de RG.
	 * 
	 * @throws Exception
	 */
	Path getPathAbsoluPropertiesRG() throws Exception;

	

} // FIN DE L'INTERFACE IGestionnaireRG.-----------------------------------
