package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences;

import java.io.File;
import java.nio.file.Path;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * CLASSE GestionnaireMessagesControleProperties :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Preferences, preferencces, properties, Properties,<br/>
 * sauver Properties, br/>
 * fichier properties, fichier Properties, <br/>
 * créer fichier sur disque dur, HDD, créer arborescence sur disque dur,<br/>
 * enregistrer Properties dans fichier .properties,<br/>
 * lire fichier .properties, <br/>
 * fournir nom properties, reconstituer nom properties Locale,<br/>
 * fournirNomFichierProperties(Locale pLocale), <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 14 juil. 2018
 *
 */
public final class GestionnaireMessagesControleProperties {
	
	// ************************ATTRIBUTS************************************/

	//*****************************************************************/
	//**************************** BOM_UTF-8 **************************/
	//*****************************************************************/
	/**
	 * BOM_UTF : char :<br/>
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	public static final char BOM_UTF_8 = '\uFEFF';

	
	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/
	
	/**
	 * PREVENIR_CS : String :<br/>
	 * "veuillez prévenir le centre-serveur svp.".<br/>
	 */
	public static final String PREVENIR_CS 
		= "veuillez prévenir le centre-serveur svp.";
	

	/**
	 * SEP_PV : String :<br/>
	 * Séparateur pour les CSV ";".<br/>
	 */
	public static final String SEP_PV = ";";

    
	/**
	 * SEPARATEUR_MOINS_AERE : String :<br/>
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";
	
	
	/**
	 * UNDERSCORE : String :<br/>
	 * "_".<br/>
	 */
	public static final String UNDERSCORE = "_";


	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/	
	/**
	 * NEWLINE : String :<br/>
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");


	/**
	 * .<br/>
	 */
	private static Properties properties;
	
	/**
	 * <b>nom de base du fichier .properties externe</b> paramétrable 
	 * situé dans le répertoire <i>hors classpath</i> 
	 * ("ressources_externes" par exemple) 
	 * indiqué par le centre-serveur dans 
	 * "configuration_ressources_externes.properties".<br/>
	 * "messagescontrole".<br/>
	 */
	private static String nomBaseFichierProperties = "messagescontrole";
	
	/**
	 * .<br/>
	 */
	private static Path pathAbsoluFichierProperties;
	
	/**
	 * .<br/>
	 */
	private static File fichierProperties;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(GestionnaireMessagesControleProperties.class);
	
	// *************************METHODES************************************/
	
	
	 /**
	 * .<br/>
	 * <br/>
	 *
	 */
	private GestionnaireMessagesControleProperties() {
		super();
	}
	
	
}
