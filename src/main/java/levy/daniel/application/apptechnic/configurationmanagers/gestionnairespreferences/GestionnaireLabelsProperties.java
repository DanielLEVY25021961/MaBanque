package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences;

import java.io.File;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.IGestionnaireTemplates;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairestemplates.impl.GestionnaireTemplates;


/**
 * CLASSE GestionnaireLabelsProperties :<br/>
 * Classe chargée de <b>gérer les labels des IHM</b> 
 * à l'aide de <b>préférences</b>.<br/>
 * Les labels doivent être contenus dans un fichier labels.properties 
 * (nom de base "labels").<br/>
 * labels.properties doit être situé dans le répertoire <i>hors classpath</i> 
 * des ressources externes ("ressources_externes" par exemple) 
 * dont la localisation est indiquée par le centre-serveur dans 
 * "configuration_ressources_externes.properties".<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Preferences, preferences, properties, Properties,<br/>
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
public final class GestionnaireLabelsProperties {
	
	/**
	 * "ContactSimpleVue.prenomTableColumn.text".<br/>
	 */
	public static final String KEY_LABEL_PRENOM 
		= "ContactSimpleVue.prenomTableColumn.text";
	
	/**
	 * "ContactSimpleVue.nomTableColumn.text".<br/>
	 */
	public static final String KEY_LABEL_NOM 
		= "ContactSimpleVue.nomTableColumn.text";
	
	/**
	 * <b>nom de base du fichier .properties externe</b> paramétrable 
	 * situé dans le répertoire <i>hors classpath</i> 
	 * ("ressources_externes" par exemple) 
	 * indiqué par le centre-serveur dans 
	 * "configuration_ressources_externes.properties".<br/>
	 * "labels"<br/>
	 */
	private static final String NOM_BASE_FICHIER_PROPERTIES = "labels";
	
	/**
	 * <b>Chemin relatif du template</b> comportant le commentaire 
	 * à ajouter en haut du fichier properties.<br/>
	 * chemin relatif du template à lire par rapport à 
	 * cheminAbsoluMainResources (src/main/resources).<br/>
	 * "commentaires_properties/commentaires_labels_properties.txt"
	 */
	private static final String CHEMIN_RELATIF_COMMENTAIRE 
		= "commentaires_properties/commentaires_labels_properties.txt";

	/**
	 * Properties Java encapsulant le fichier .properties 
	 * externe <b>fichierProperties</b>.<br/>
	 */
	private static Properties properties;

	/**
	 * Path correspondant au fichier .properties externe 
	 * paramétrable à gérer.<br/>
	 */
	private static Path pathAbsoluFichierProperties;

	/**
	 * File correspondant au fichier .properties externe 
	 * paramétrable à gérer.<br/>
	 */
	private static File fichierProperties;
	
	/**
	 * commentaire à ajouter en haut du fichier properties.<br/>
	 */
	private static String commentaire;
	
	/**
	 * .<br/>
	 */
	private static Map<String, String> mapPropertiesInitiales 
		= new LinkedHashMap<String, String>();
			
	/**
	 * .<br/>
	 */
	private static GestionnaireProperties gestionnaireProperties;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(GestionnaireLabelsProperties.class);
	
	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation.<br/>
	 */
	private GestionnaireLabelsProperties() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * <b>Crée sur disque dur</b> le fichier labels_locale.properties.<br/>
	 * <ul>
	 * <li>crée le commentaire du properties à partir de son template 
	 * et en fonction de la Locale.</li>
	 * </ul>
	 *
	 * @param pLocale : Locale.<br/>
	 * 
	 * @throws Exception 
	 */
	public static void creerFichierProperties(
			final Locale pLocale) throws Exception {
		
		/* crée le commentaire du properties à partir de son template 
		 * et en fonction de la Locale. */
		creerCommentaire(CHEMIN_RELATIF_COMMENTAIRE, pLocale);
		
		/* remplit mapPropertiesInitiales en fonction de la Locale.*/
		ajouterPropertiesInitialesEnDur(pLocale);
		
		/* instancie le GestionnaireProperties. */
		gestionnaireProperties 
			= new GestionnaireProperties(
					NOM_BASE_FICHIER_PROPERTIES
						, pLocale
							, commentaire
								, mapPropertiesInitiales);
		
		gestionnaireProperties.creerFichierSurDisqueEtRemplir();
		
		alimenterAttributs();
		
	} // Fin de creerFichierProperties(...)._______________________________
	

	
	/**
	 * alimente le fichier labels_locale.properties avec 
	 * des properties en dur.<br/>
	 */
	private static void alimenterAttributs() {
		
		/* alimente 'properties' avec la valeur 
		 * fournie par le gestionnaireProperties. */
		properties = gestionnaireProperties.getProperties();
		
		/* alimente 'pathAbsoluFichierProperties' 
		 * avec la valeur fournie par le gestionnaireProperties. */
		pathAbsoluFichierProperties 
			= gestionnaireProperties.getPathAbsoluFichierProperties();
		
	} // Fin de alimenterAttributs().__________________________________
	
	
	
	/**
	 * remplit mapPropertiesInitiales en fonction de la Locale.<br/>
	 * <ul>
	 * <li>Initialise le properties en dur.</li>
	 * </ul>
	 *
	 * @param pLocale : Locale.<br/>
	 */
	private static void ajouterPropertiesInitialesEnDur(
			final Locale pLocale) {
		
		if (pLocale.equals(Locale.FRANCE)) {
			
			mapPropertiesInitiales.put(KEY_LABEL_PRENOM, "Prénom");
			mapPropertiesInitiales.put(KEY_LABEL_NOM, "Nom");
			
		} else if (pLocale.equals(Locale.US)){
			
			mapPropertiesInitiales.put(KEY_LABEL_PRENOM, "FirstName");
			mapPropertiesInitiales.put(KEY_LABEL_NOM, "LastName");
		} else {
			
			mapPropertiesInitiales.put(KEY_LABEL_PRENOM, "Prénom");
			mapPropertiesInitiales.put(KEY_LABEL_NOM, "Nom");
		}
		
	} // Fin de ajouterPropertiesInitialesEnDur(...).______________________
	

	
	/**
	 * <b>Crée l'attribut 'commentaire' à mettre au début du properties 
	 * en utilisant un template</b>.<br/>
	 *
	 * @param pCheminRelatifCommentaire : String : 
	 * chemin relatif du template par rapport aux ressources internes 
	 * src/main/resources.<br/>
	 * @param pLocale : Locale.<br/>
	 *  
	 * @throws Exception 
	 */
	private static void creerCommentaire(
			final String pCheminRelatifCommentaire
				, final Locale pLocale) throws Exception {
		
		if (pCheminRelatifCommentaire == null) {
			return;
		}
		/* variable à substituer dans le template. */
		final String[] variables = {"{$Locale}", "{$langue}"};
		/* valeur de substitution. */
		final String[] substituants = {pLocale.toString()
				, LocaleManager.fournirLangueEtPaysEnFrancais(pLocale)};
				
		/* Obtention du template substitué sous 
		 * forme de String dans le code java. */
		final IGestionnaireTemplates gestionnaireTemplate 
			= new GestionnaireTemplates();
		
		commentaire 
			= gestionnaireTemplate.fournirTemplateSubstitueSousFormeString(
					pCheminRelatifCommentaire, variables, substituants);
		
	} // Fin de creerCommentaire(...)._____________________________________

	
	
} // FIN DE LA CLASSE GestionnaireLabelsProperties.--------------------------
