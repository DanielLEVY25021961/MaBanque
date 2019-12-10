package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnairesbundles.BundleConfigurationProjetManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;


/**
 * CLASSE <b>GestionnairePreferences</b> :<br/>
 * Classe Utilitaire chargée de gérer les 
 * <b>préférences de l'application</b>.<br/>
 * <br/>
 * Les préférences de l'application sont des données très générales 
 * qu'un <b>administrateur doit pouvoir paramétrer</b>. Par exemple :
 * <ul>
 * <li>La langue par défaut de l'application (Locale par défaut)</li>
 * <li>L'encodage par défaut de l'application (Charset par défaut)</li>
 * <li>...</li>
 * </ul>
 * <p>Plus généralement, les préférences de l'application sont tous 
 * les paramètres que l'administrateur doit ne 
 * <b>saisir qu'une seule fois</b> 
 * et qui doivent rester <b>mémorisés dans l'application</b> tant que 
 * l'Administrateur n'a pas décidé d'en changer.</p>
 * <br/>
 * <div>
 * <p><span style="text-decoration: underline;"><b>Exigences du gestionnaire des préférences</b></span></p>
 * 
 * <table border=4 cellspacing=4 cellpadding=4
 * style="border-width:1px;border-style:solid;border-color:black;border-collapse:collapse;" 
 * summary="colonne 1 : les identifiants des exigences, colonne 2 : les exigences"> 
 * 
 * <caption>Tableau des exigences</caption>
 * 
 * <colgroup>
 * 	<col style="border: 1px solid black; border-collapse: collapse;" />
 * 	<col style="border: 1px solid black; border-collapse: collapse;" />
 * </colgroup>
 * 
 * <thead>
 * 	<tr>
 * 		<th>Identifiant</th> <th>exigence</th>
 * 	</tr>
 * </thead>
 * 
 * <tbody>
 * <tr> 
 * 	<td >EX_FONCT_PARAMETRAGE_01</td> <td>l'application doit pouvoir présenter à l'Administrateur les préférences stockées</td>
 * </tr>
 * 	
 * <tr>
 *  <td>EX_TEC_PARAMETRAGE_02</td> <td>l'application doit pouvoir lire les préférences dans le stockage et les présenter</td>
 * </tr>
 * 
 * <tr> 
 * 	<td>EX_FONCT_PARAMETRAGE_03</td> <td>l'application doit permettre à l'Administrateur de paramétrer les préférences</td>
 * </tr>
 * 
 * <tr> 
 * 	<td>EX_TEC_PARAMETRAGE_04</td> <td>l'application doit savoir modifier les préférences dans le stockage</td>
 * </tr>
 * 
 * <tr> 
 * 	<td>EX_FONCT_MEMORISATION_05</td> <td>l'application doit mémoriser les préférences choisies par l'Administrateur dans le stockage</td>
 * </tr>
 * 
 * <tr> 
 * 	<td>EX_TEC_MEMORISATION_06</td> <td>l'application doit savoir écrire les préférences choisies par l'Administrateur dans le stockage</td>
 * </tr>
 * 
 * <tr> 
 * 	<td>EX_TEC_INITIALISATION_07</td> <td>L'application doit fabriquer un stockage de préférences à partir de valeurs en dur (valeurs d'usine) au cas où un stockage ne serait pas fourni</td>
 * </tr>
 * 
 * <tr>
 * 	<td>EX_TEC_INITIALISATION_08</td> <td>l'application doit pouvoir fournir chaque préférence "en dur" au cas où elle ne parviendrait pas à fabriquer un stockage</td> 
 * </tr>
 * </tbody>
 * 
 * </table>
 * </div>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * répertoire du projet, System.getProperty("user.dir"),<br/>
 * Properties, préférences, Preferences, <br/>
 * Template, template, lire dans fichier,<br/>
 * enregistrer des préférences dans un Properties, fichier properties, <br/>
 * commentaire dans un fichier properties, écrire commentaire, <br/>
 * lire dans un fichier properties, écrire dans un fichier properties, <br/>
 * créer une String à partir d'une liste de lignes, <br/>
 * REGEX, Regex, 
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 24 juil. 2018
 *
 */
public final class GestionnairePreferences {
	
	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe GestionnairePreferences".<br/>
	 */
	public static final String CLASSE_GESTIONNAIRE_PREFERENCES 
		= "Classe GestionnairePreferences";

	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/

	/**
	 * '/'.<br/>
	 */
	public static final char SEPARATEUR_FICHIER = '/';
		
	/**
	 * SEPARATEUR_MOINS_AERE : String :<br/>
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";

	
	//*****************************************************************/
	//**************************** CHARSETS ***************************/
	//*****************************************************************/
	/**
	 * Charset.forName("UTF-8").<br/>
	 * Eight-bit Unicode (or UCS) Transformation Format.<br/> 
	 */
	public static final Charset CHARSET_UTF8 
		= Charset.forName("UTF-8");
	
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
	 * SAUT_LIGNE_PLATEFORME : char :<br/>
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE_JAVA = '\n';


	/**
	 * METHODE_LIRE_STRINGS_DANS_FILE : String :<br/>
	 * "méthode lireStringsDansFile(File pFile, Charset pCharset)".<br/>
	 */
	public static final String METHODE_LIRE_STRINGS_DANS_FILE 
		= "méthode lireStringsDansFile(File pFile, Charset pCharset)";
	
	
	/**
	 * EGAL : char :<br/>
	 * '='.<br/>
	 */
	public static final char EGAL = '=';
	

	
	/**
	 * clé du charset de l'application dans preferences.properties<br/>
	 * "application.charset"<br/>
	 */
	public static final String KEY_CHARSET_APPLICATION 
		= "application.charset";
	
	/**
	 * clé de la locale de l'application dans preferences.properties<br/>
	 * "application.locale"<br/>
	 */
	public static final String KEY_LOCALE_APPLICATION 
		= "application.locale";
	
	/**
	 * clé du répertoire sur lequel s'ouvre le FileChooser 
	 * dans preferences.properties<br/>
	 * "repertoire.filechooser"<br/>
	 */
	public static final String KEY_REPERTOIRE_FILECHOOSER 
		= "repertoire.filechooser";
	
	/**
	 * Charset par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le Charset 
	 * indiqué dans preferences.properties.<br/>
	 * "UTF-8".<br/>
	 */
	public static final String CHARSET_STRING_PAR_DEFAUT_EN_DUR 
		= CHARSET_UTF8.name();
	
	/**
	 * Locale par défaut de l'application en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire la Locale 
	 * indiquée dans preferences.properties.<br/>
	 */
	public static final String LOCALE_STRING_PAR_DEFAUT_EN_DUR 
		= fournirLangueEtPaysEnFrancais(Locale.FRANCE);
	
	/**
	 * Répertoire par défaut pour le FileChooser en dur.<br/>
	 * N'est utilisé que si l'application ne peut lire le répertoire 
	 * indique dans preferences.properties.<br/>
	 * Répertoire du projet : "user.dir"<br/>
	 */
	public static final String REPERTOIRE_FILECHOOSER_STRING_PAR_DEFAUT_EN_DUR 
		= System.getProperty("user.dir");
	
	
	/**
	 * Properties encapsulant les préférences.<br/>
	 */
	private static Properties preferences = new Properties();
	
	/**
	 * Path absolu vers preferences.properties.<br/>
	 */
	private static Path pathAbsoluPreferencesProperties;
	
	/**
	 * commentaire à ajouter en haut du fichier properties.<br/>
	 */
	private static String commentaire;
	
	/**
	 * Chemin relatif (par rapport à src/main/resources) 
	 * du template contenant le commentataire à ajouter 
	 * au dessus de preferences.properties.<br/>
	 * "commentaires_properties/commentaires_preferences_properties.txt"
	 */
	private static String cheminRelatifTemplateCommentaire 
		= "commentaires_properties/commentaires_preferences_properties.txt";
	
	/**
	 * filePreferencesProperties : File :<br/>
	 * Modélisation Java du fichier preferences.properties.<br/>
	 */
	private static File filePreferencesProperties;
		
	/**
	 * <b>SINGLETON de Charset par défaut 
	 * dans l'application</b>.<br/>
	 */
	private static Charset charsetApplication;

	/**
	 * <b>SINGLETON de la Locale par défaut 
	 * dans l'application</b>.<br/>
	 */
	private static Locale localeDefautApplication;
	
	/**
	 * <b>SINGLETON du répertoire sur lequel va pointer 
	 * le FileChooser à son ouverture.</b>.<br/>
	 */
	private static File repertoirePrefereFileChooser;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(GestionnairePreferences.class);
	
	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation<br/>
	 */
	private GestionnairePreferences() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * <b>sauvegarde sur disque un fichier 
	 * preferences.properties initial</b> alimenté par des 
	 * propriétés [clé-valeur] écrites en dur 
	 * dans la présente classe.<br/>
	 * <ul>
	 * <li>remplit le Properties Java <code>preferences</code> 
	 * avec des [clé-valeur] stockées en dur dans la classe.</li>
	 * <li>remplit le fichier <code>filePreferencesProperties</code> 
	 * (preferences.properties) avec le contenu de <code>preferences</code> 
	 * ([clé-valeur] stockées en dur dans la classe).</li>
	 * <li>Ecrit en UTF8 le Properties <code>preferences</code> dans 
	 * le File <code>filePreferencesProperties</code> 
	 * modélisant le fichier preferences.properties en positionnant 
	 * le <code>commentaire</code> au dessus.</li>
	 * <li>Utilise <code>preferences.store(writer, commentaire);</code> 
	 * avec un try-with-resource.</li>
	 * <li>ré-écrit (écrase) tout le fichier à chaque appel.</li>
	 * <li>trace EX_TEC_INITIALISATION_07.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private static void creerFichierPropertiesInitial() 
											throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* Ajoute les propriétés initiales à preferences. */
			ajouterPropertiesEnDur();
			
			/* initialise les fichiers preferences. */
			instancierAttributsFichierProperties();
			
			/* ECRITURE SUR DISQUE. */
			/* try-with-resource qui se charge du close(). */
			try (Writer writer = Files.newBufferedWriter(
					pathAbsoluPreferencesProperties, CHARSET_UTF8)) {
				
				/* enregistre le Properties preferences sur disque dur 
				 * dans le fichier preferences.properties correspondant. */
				preferences.store(writer, commentaire);
				
			}
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de creerFichierPropertiesInitial().___________________________

	

	/**
	 * <b>Ajoute des propriétés initiales stockées en dur</b> 
	 * dans la classe au Properties <b>preferences</b>.<br/>
	 * <ul>
	 * <li>ajoute le charset par défaut stocké en dur CHARSET_UTF8.</li>
	 * <li>ajoute la Locale par défaut stockée en dur Locale.FRANCE.</li>
	 * <li>ajoute le répertoire par défaut stockée en dur user.dir.</li>
	 * </ul>
	 */
	private static void ajouterPropertiesEnDur() {
		
		synchronized (GestionnairePreferences.class) {
			
			/* ajoute le charset par défaut stocké en dur CHARSET_UTF8. */
			preferences.setProperty(
					KEY_CHARSET_APPLICATION
						, CHARSET_STRING_PAR_DEFAUT_EN_DUR);
			
			/* ajoute la Locale par défaut stockée en dur Locale.FRANCE.*/
			preferences.setProperty(
					KEY_LOCALE_APPLICATION
						, LOCALE_STRING_PAR_DEFAUT_EN_DUR);
			
			/* ajoute le répertoire par défaut stockée en dur user.dir.*/
			preferences.setProperty(
					KEY_REPERTOIRE_FILECHOOSER
						, REPERTOIRE_FILECHOOSER_STRING_PAR_DEFAUT_EN_DUR);
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de ajouterProperties()._______________________________________
	

	
	/**
	 * <b>Instancie tous les attributs</b> relatifs 
	 * au fichier de preferences <b>si ils sont null</b>.<br/>
	 * <b>Crée le fichier preferences.properties vide</b> 
	 * (et son arborescence) 
	 * sur HDD <b>si il n'existe pas déjà</b>.<br/>
	 * <b>obtient le path du preferences.properties</b> 
	 * dans les ressources externes auprès du 
	 * <b>ConfigurationApplicationManager</b>.<br/>
	 * <ul>
	 * <li>instancie pathAbsoluPreferencesProperties.</li>
	 * <li>instancie filePreferencesProperties.</li>
	 * <li>Crée sur le disque dur l'arborescence et le fichier 
	 * filePreferencesProperties si nécessaire.</li>
	 * <li>ajoute le commentaire au début du preferences.properties.</li>
	 * </ul>
	 *
	 * @throws Exception
	 */
	private static void instancierAttributsFichierProperties() 
			throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* obtient le path du properties dans les 
			 * ressources externes auprès du 
			 * ConfigurationApplicationManager. */
			final Path pathRessourcesExternes 
			= Paths.get(ConfigurationApplicationManager
					.getPathRessourcesExternes());
		
			if (pathAbsoluPreferencesProperties == null) {
				pathAbsoluPreferencesProperties 
				= pathRessourcesExternes
					.resolve("preferences/preferences.properties");
			}
			
			if (filePreferencesProperties == null) {
				filePreferencesProperties 
				= pathAbsoluPreferencesProperties.toFile();
				
				if (!filePreferencesProperties.exists()) {
					creerRepertoiresArbo(filePreferencesProperties);
					Files.createFile(pathAbsoluPreferencesProperties);
				}				
			}
			
			if (commentaire == null) {
				commentaire 
					= lireTemplateString(cheminRelatifTemplateCommentaire);
			}
						
		} // Fin du bloc synchronized.__________________
		
	} // Fin de instancierAttributsFichierProperties().____________________
	

	
	/**
	 * Crée sur disque dur l'arborescence des répertoires 
	 * parent de pFile si elle n'existe pas déjà.<br/>
	 * <ul>
	 * <li><code>Files.createDirectories(pathParent);</code></li>
	 * </ul>
	 * - ne fait rien si pFile == null.<br/>
	 * - ne fait rien si pFile est une racine (pas de parent).<br/>
	 * </br/>
	 *
	 * @param pFile : File : 
	 * fichier dont on veut créer l'arborescence sur disque dur.<br/>
	 * 
	 * @throws IOException
	 */
	private static void creerRepertoiresArbo(
			final File pFile) throws IOException {
		
		synchronized (GestionnairePreferences.class) {
			
			/* ne fait rien si pFile == null. */
			if (pFile == null) {
				return;
			}
			
			final Path pathFile = pFile.toPath();
			final Path pathParent = pathFile.getParent();
			
			/* ne fait rien si pFile est une racine (pas de parent). */
			if (pathParent != null) {
				Files.createDirectories(pathParent);
			}

		} // Fin du bloc synchronized.__________________
						
	} // Fin de creerRepertoiresArbo(...)._________________________________
	
	
	
	/**
	 * <b>lit le fichier 
	 * ressources_externes/preferences/preferences.properties</b> 
	 * et alimente le <i>Properties</i> <b>preferences</b> 
	 * en le décodant en UTF8.<br/>
	 * <ul>
	 * <li>initialise les attributs relatifs 
	 * aux fichiers preferences.</li>
	 * <li>décode le fichier .properties en UTF8 et le charge 
	 * dans le Properties preferences.</li>
	 * <li><code>preferences.load(inputStream);</code></li>
	 * <li>trace EX_TEC_PARAMETRAGE_02.</li>
	 * </ul>
	 * @throws Exception 
	 */
	private static void lireFichierPreferencesProperties() 
												throws Exception {

		synchronized (GestionnairePreferences.class) {
			
			/* initialise les attributs relatifs aux fichiers preferences. */
			instancierAttributsFichierProperties();
					
			/* try-with-resource qui se charge du close(). */
			try (Reader reader = Files.newBufferedReader(
					pathAbsoluPreferencesProperties, CHARSET_UTF8)) {
				
				/* décode le fichier .properties en UTF8 
				 * et le charge dans le Properties preferences. */
				preferences.load(reader);
		
			}

		} // Fin du bloc synchronized.__________________
				
	} // Fin de lireFichierPreferences().__________________________________


	
	/**
	 * <b>Enregistre en UTF8</b> le <i>Properties</i> preferences dans 
	 * le <i>fichier</i> <b>ressources_externes/preferences/
	 * preferences.properties</b>.<br/>
	 * <ul>
	 * <li>initialise le <i>Properties</i> <b>preferences</b> 
	 * et remplit le <i>fichier</i> .properties si nécessaire.</li>
	 * <li>enregistre le <i>Properties</i> <b>preferences</b> 
	 * sur disque dur dans le <i>fichier</i> 
	 * .properties correspondant.</li>
	 * <li>ajoute le commentaire au début de preferences.properties.</li>
	 * <li>Prise en compte (stockage) 
	 * d'une modification d'une Property.</li>
	 * <li><code>preferences.store(writer, null);</code></li>
	 * <li>trace EX_FONCT_MEMORISATION_05.</li>
	 * <li>trace EX_TEC_MEMORISATION_06.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	private static void enregistrerFichierPreferencesProperties() 
			throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* crée le Properties preferences et 
			 * le remplit avec des valeurs en dur si nécessaire. */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				creerFichierPropertiesInitial();
			}
			
			/* initialise les fichiers preferences si nécessaire. */
			instancierAttributsFichierProperties();
			
			/* try-with-resource qui se charge du close(). */
			try (Writer writer = Files.newBufferedWriter(
					pathAbsoluPreferencesProperties, CHARSET_UTF8)) {
				
				/* enregistre le Properties preferences sur disque dur 
				 * dans le fichier .properties correspondant. */
				preferences.store(writer, commentaire);
				
			}

		} // Fin du bloc synchronized.__________________
		
	} // Fin de enregistrerFichierPreferences().___________________________
	

	
	/**
	 * <b>Lit un template situé à 
	 * <code>cheminAbsoluMainResources/pCheminRelatifTemplate</code> 
	 * et retourne une String unique 
	 * incorporant les sauts de lignes</b>.
	 * <ul>
	 * <li>lit le fichier template avec le Charset UTF8.</li>
	 * <li>utilise la méthode lireStringsDansFile(
	 * templateFile, CHARSET_UTF8).</li>
	 * <li><b>Ne fait aucune substitution de variables</b>. 
	 * Lit le template et le retourne sous forme 
	 * de String.</li>
	 * <li>incorpore dans la String résultat les 
	 * sauts de ligne de la plateforme.</li>
	 * </ul>
	 * - retourne null si pCheminRelatifTemplate est blank.<br/>
	 * - retourne null si le fichier template n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pCheminRelatifTemplate : String : 
	 * chemin relatif du template à lire par rapport à 
	 * cheminAbsoluMainResources (src/main/resources).<br/>
	 * 
	 * @return String : 
	 * template sous forme de String unique 
	 * incorporant les sauts de lignes.<br/>
	 * 
	 * @throws Exception 
	 */
	private static String lireTemplateString(
			final String pCheminRelatifTemplate) 
									throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* retourne null si pCheminRelatifTemplate est blank. */
			if (StringUtils.isBlank(pCheminRelatifTemplate)) {
				return null;
			}
			
			final List<String> templateListe 
				= lireTemplate(pCheminRelatifTemplate);
			
			final String resultat 
				= creerStringAPartirDeListe(templateListe);
			
			return resultat;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de lireTemplateString(...).___________________________________

	
	
	/**
	 * <b>Lit un template situé à 
	 * <code>cheminAbsoluMainResources/pCheminRelatifTemplate</code> 
	 * et retourne une liste de lignes</b>.
	 * <ul>
	 * <li>lit le fichier template avec le Charset UTF8.</li>
	 * <li>utilise la méthode lireStringsDansFile(
	 * templateFile, CHARSET_UTF8).</li>
	 * <li><b>Ne fait aucune substitution de variables</b>. 
	 * Lit le template et le retourne sous forme 
	 * de List&lt;String&gt;.</li>
	 * </ul>
	 * - retourne null si pCheminRelatifTemplate est blank.<br/>
	 * - retourne null si le fichier template n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pCheminRelatifTemplate : String : 
	 * chemin relatif du template à lire par rapport à 
	 * cheminAbsoluMainResources (src/main/resources).<br/>
	 * 
	 * @return List&lt;String&gt; : 
	 * template sous forme de liste de lignes.<br/>
	 * 
	 * @throws Exception 
	 */
	private static List<String> lireTemplate(
			final String pCheminRelatifTemplate) 
									throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* retourne null si pCheminRelatifTemplate est blank. */
			if (StringUtils.isBlank(pCheminRelatifTemplate)) {
				return null;
			}
			
			final String cheminAbsoluTemplate 
				= BundleConfigurationProjetManager.getRacineMainResources() 
					+ SEPARATEUR_FICHIER 
						+ pCheminRelatifTemplate;
			
			final File templateFile = new File(cheminAbsoluTemplate);
			
			/* retourne null si le fichier template n'existe pas. */
			if (!templateFile.exists()) {
				return null;
			}
			
			/* utilise la méthode 
			 * lireStringsDansFile(templateFile, CHARSET_UTF8). */
			final List<String> templateListe 
				= lireStringsDansFile(templateFile, CHARSET_UTF8);
			
			return templateListe;

		} // Fin du bloc synchronized.__________________
		
	} // Fin de lireTemplate(...)._________________________________________
	
	
	
	/**
	 * <b>Lit le contenu d'un fichier texte avec pCharset 
	 * et retourne une Liste de lignes</b>. 
	 * <ul>
	 * <li><b>Lit le contenu</b> d'un fichier texte 
	 * (fichier simple contenant du texte) pFile.</li>
	 * <li>Décode le contenu d'un fichier texte 
	 * (fichier simple contenant du texte) pFile 
	 * avec le Charset pCharset</li>
	 * <li><b>Retourne la liste des lignes</b> 
	 * du fichier simple texte pFile 
	 * lues avec le Charset pCharset.</li>
	 * <ul>
	 * <li>Utilise automatiquement le CHARSET_UTF8 
	 * si pCharset est null.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas.<br/>
	 * - Retourne null si pFile est un répertoire.<br/>
	 * - Retourne null en cas d'Exception loggée 
	 * (MalformedInputException, ...).<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier simple textuel à lire.<br/>
	 * @param pCharset : Charset : le Charset à utiliser pour 
	 * lire le fichier pFile.<br/>
	 * 
	 * @return : List&lt;String&gt; : Liste des lignes lues.<br/>
	 * 
	 * @throws Exception en cas d'Exception loggée 
	 * (IOException, MalformedInputException, ...).<br/>
	 */
	private static List<String> lireStringsDansFile(
			final File pFile
				, final Charset pCharset) throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* Retourne null si pFile est null. */
			if (pFile == null) {
				return null;
			}
			
			/* Retourne null si pFile n'existe pas. */
			if (!pFile.exists()) {
				return null;
			}
			
			/* Retourne null si pFile est un répertoire. */
			if (pFile.isDirectory()) {
				return null;
			}
			
			/* Utilise automatiquement le CHARSET_UTF8 si pCharset est null. */
			Charset charset = null;
			
			if (pCharset == null) {
				charset = CHARSET_UTF8;
			}
			else {
				charset = pCharset;
			}
			
			/* Récupère le Path de pFile. */
			final Path pathFichier = pFile.toPath();
			
			try {
				
				// *****************************************************
				/* Retourne la liste des lignes lues avec le charset. */
				return Files.readAllLines(pathFichier, charset);
				
			} 
			
			catch (MalformedInputException malformedInputException) {
				
				final String message 
				=  "Impossible de lire le contenu du fichier '" 
				+ pFile.getName() 
				+ "' avec le Charset " 
				+ charset.displayName(Locale.getDefault()) 
				+ " à cause d'un caractère qui ne peut être "
				+ "écrit dans ce Charset (MalformedInputException)";
				
				/* LOG de niveau Error. */
				loggerError(CLASSE_GESTIONNAIRE_PREFERENCES
						, METHODE_LIRE_STRINGS_DANS_FILE 
						+ SEPARATEUR_MOINS_AERE 
						+ message
						, malformedInputException);
				
				/* retourne null en cas d'Exception loggée 
				 * (IOException, MalformedInputException, ...). */
				return null;
		
			}
			
			catch (IOException ioe) {
				
				/* LOG de niveau Error. */
				loggerError(CLASSE_GESTIONNAIRE_PREFERENCES
						, METHODE_LIRE_STRINGS_DANS_FILE
						, ioe);
				
				final String message 
				= CLASSE_GESTIONNAIRE_PREFERENCES 
				+ SEPARATEUR_MOINS_AERE 
				+ METHODE_LIRE_STRINGS_DANS_FILE 
				+ SEPARATEUR_MOINS_AERE 
				+ "Impossible de lire le contenu du fichier '" 
				+ pFile.getName() 
				+ "' avec le Charset " 
				+ charset.displayName(Locale.getDefault());
				
				/* jette une Exception en cas d'Exception loggée 
				 * (IOException, MalformedInputException, ...). */
				throw new Exception(message, ioe);
			
			}
			
		} // Fin du bloc synchronized.__________________
			
	} // Fin de lireStringsDansFile(...).__________________________________
	

	
	/**
	 * <b>Crée une String à partir d'une liste de Strings</b>.
	 * <ul>
	 * <li>ajoute un saut de ligne de la plateforme 
	 * NEWLINE à chaque ligne.</li>
	 * </ul>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;String&gt;.<br/>
	 * 
	 * @return : String.<br/>
	 */
	private static String creerStringAPartirDeListe(
			final List<String> pList) {
		
		synchronized (GestionnairePreferences.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final StringBuilder stb = new StringBuilder();
			
			for (final String ligne : pList) {
				
				stb.append(ligne);
				
				/* ajoute un saut de ligne de la plateforme 
				 * NEWLINE à chaque ligne. */
				stb.append(NEWLINE);
			}
			
			return stb.toString();

		} // Fin du bloc synchronized.__________________
		
	} // Fin de creerStringAPartirDeListe(...).____________________________
	
	
	
	/**
	 * method loggerInfo(
	 * String pClasse
	 * , String pMethode
	 * , String pMessage) :<br/>
	 * <ul>
	 * <li>Crée un message de niveau INFO dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pMessage : String : Message particulier de la méthode.<br/>
	 */
	private static void loggerInfo(
			final String pClasse
				, final String pMethode
					, final String pMessage) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null || pMessage == null) {
			return;
		}
		
		/* LOG de niveau INFO. */			
		if (LOG.isInfoEnabled()) {
			
			final String message 
			= pClasse 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ pMessage;
			
			LOG.info(message);
		}
		
	} // Fin de la classe loggerInfo(...)._________________________________
	
	
	
	/**
	 * method loggerInfo(
	 * String pClasse
	 * , String pMethode
	 * , String pMessage
	 * , String pComplement) :<br/>
	 * <ul>
	 * <li>Créée un message de niveau INFO dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pMessage : String : Message particulier de la méthode.<br/>
	 * @param pComplement : String : Complément au message de la méthode.<br/>
	 */
	private static void loggerInfo(
			final String pClasse
				, final String pMethode
					, final String pMessage
						, final String pComplement) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null 
				|| pMessage == null || pComplement == null) {
			return;
		}
		
		/* LOG de niveau INFO. */			
		if (LOG.isInfoEnabled()) {
			
			final String message 
			= pClasse 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE
			+ pMessage
			+ pComplement;
			
			LOG.info(message);
		}
		
	} // Fin de loggerInfo(...).___________________________________________
	
	
	
	/**
	 * method loggerError(
	 * String pClasse
	 * , String pMethode
	 * , Exception pException) :<br/>
	 * <ul>
	 * <li>Crée un message de niveau ERROR dans le LOG.</li>
	 * </ul>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pException : Exception : Exception transmise 
	 * par la méthode appelante.<br/>
	 */
	private static void loggerError(
			final String pClasse
				, final String pMethode
					, final Exception pException) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null || pException == null) {
			return;
		}
		
		/* LOG de niveau ERROR. */			
		if (LOG.isErrorEnabled()) {
			
			final String message 
			= pClasse 
			+ SEPARATEUR_MOINS_AERE
			+ pMethode
			+ SEPARATEUR_MOINS_AERE 
			+ pException.getMessage();
			
			LOG.error(message, pException);
			
		}
		
	} // Fin de loggerError(...).__________________________________________


	
	/**
	 * fournit une String pour l'affichage de preferences.properties.<br/>
	 * <ul>
	 * <li>crée le fichier preferences.properties et alimente 
	 * le Properties preferences avec des valeurs en dur 
	 * si preferences est vide.</li>
	 * <li>trace EX_FONCT_PARAMETRAGE_01</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 * @throws Exception 
	 */
	public static String afficherPreferences() throws Exception {

		synchronized (GestionnairePreferences.class) {
			
			/* crée le fichier preferences.properties et alimente 
			 * le Properties preferences avec des valeurs en dur 
			 * si preferences est vide. */
			if (preferences.isEmpty()) {
				creerFichierPropertiesInitial();
			}
						
			final StringBuffer stb = new StringBuffer();
			
			for (final String key : preferences.stringPropertyNames()) {
				stb.append(key);
				stb.append(EGAL);
				stb.append(preferences.getProperty(key));
				stb.append(SAUT_LIGNE_JAVA);
			}
			
			return stb.toString();

		} // Fin du bloc synchronized.__________________
		
	} // Fin de afficherPreferences()._____________________________________
	

	
	/**
	 * <b>Crée ou met à jour une Property</b> dans 
	 * le <i>Properties</i> <b>preferences</b>.<br/>
	 * <ul>
	 * <li>initialise le <i>Properties</i> <b>preferences</b> 
	 * et remplit le <i>fichier</i> .properties si nécessaire.</li>
	 * <li>Crée ou maj dans l'objet Properties <b>preferences</b> 
	 * <i>sans enregistrer la modification sur le disque dur</i>.</li>
	 * <li><code>preferences.setProperty(pKey, pValue);</code></li>
	 * <li>trace EX_FONCT_PARAMETRAGE_03.</li>
	 * </ul>
	 * - retourne false si pKey == null.<br/>
	 * - retourne false si pValue == null.<br/>
	 * <br/>
	 *
	 * @param pKey : String : Clé.<br/>
	 * @param pValue : String : Valeur.<br/>
	 * 
	 * @return : boolean : true si la property a été créée.<br/>
	 * 
	 * @throws Exception 
	 */
	public static boolean creerOuModifierProperty(
			final String pKey
				, final String pValue) throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* crée le Properties preferences et 
			 * le remplit avec des valeurs en dur si nécessaire. */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				creerFichierPropertiesInitial();
			}
			
			/* retourne false si pKey == null. */
			if (pKey == null) {
				return false;
			}
			
			/* retourne false si pValue == null. */
			if (pValue == null) {
				return false;
			}
			
			/* crée ou met à jour la Property dans preferences. */
			preferences.setProperty(pKey, pValue);
			
			return true;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de creerOuModifierProperty(...).______________________________

	
	
	/**
	 * <b>Retire une Property</b> dans 
	 * le <i>Properties</i> <b>preferences</b>.<br/>
	 * <ul>
	 * <li>initialise le <i>Properties</i> <b>preferences</b> 
	 * et remplit le <i>fichier</i> .properties si nécessaire.</li>
	 * <li>retire dans l'objet Properties <b>preferences</b> 
	 * <i>sans enregistrer la modification sur le disque dur 
	 * (.properties)</i>.</li>
	 * <li><code>preferences.remove(pKey);</code>.</li>
	 * </ul>
	 * - retourne false si pKey == null.<br/>
	 * <br/>
	 *
	 * @param pKey : String : Clé.<br/>
	 * 
	 * @return : boolean : true si la property a été retirée.<br/>
	 * 
	 * @throws Exception 
	 */
	public static boolean retirerProperty(
			final String pKey) 
					throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* crée le Properties preferences et 
			 * le remplit avec des valeurs en dur si nécessaire. */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				creerFichierPropertiesInitial();
			}
			
			/* retourne false si pKey == null. */
			if (pKey == null) {
				return false;
			}
			
			/* retire la Property de preferences. */
			preferences.remove(pKey);
			
			return true;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de retirerProperty(...).______________________________________
	

	
	/**
	 * vide le <i>Properties</i> <b>preferences</b>.<br/>
	 * <ul>
	 * <li>initialise le <i>Properties</i> <b>preferences</b> 
	 * et remplit le <i>fichier</i> .properties si nécessaire.</li>
	 * <li>vide l'objet <i>Properties</i> <b>preferences</b> 
	 * sans vider le <i>fichier</i> .properties correspondant 
	 * sur le disque dur.</li>
	 * <li><code>preferences.remove(cle);</code>.</li>
	 * </ul>
	 * - retourne false si l'ensemble des clés du 
	 * Properties preferences est null.<br/>
	 * <br/>
	 *
	 * @return : boolean : true si preferences a été vidée.<br/>
	 * 
	 * @throws Exception 
	 */
	public static boolean viderPreferences() throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* crée le Properties preferences et 
			 * le remplit avec des valeurs en dur si nécessaire. */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				creerFichierPropertiesInitial();
			}
				
			final Set<String> clesSet 
				= preferences.stringPropertyNames();
			
			/* retourne false si l'ensemble des clés 
			 * du Properties preferences est null. */
			if (clesSet == null) {
				return false;
			}
			
			/* vidage du Properties preferences. */
			for (final String cle : clesSet) {
				preferences.remove(cle);
			}
			
			return true;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de viderPreferences().________________________________________
	
	

		
	/**
	 * Getter du commentaire à ajouter en haut du fichier properties.<br/>
	 * <br/>
	 *
	 * @return commentaire : String.<br/>
	 */
	public static String getCommentaire() {
		return commentaire;
	} // Fin de getCommentaire().__________________________________________
	
	
	
	/**
	* Setter du commentaire à ajouter en haut du fichier properties.<br/>
	* <br/>
	*
	* @param pCommentaire : String : 
	* valeur à passer à commentaire.<br/>
	*/
	public static void setCommentaire(
			final String pCommentaire) {
		commentaire = pCommentaire;
	} // Fin de setCommentaire(...)._______________________________________
	

			
	/**
	 * Getter du Chemin relatif (par rapport à src/main/resources) 
	 * du template contenant le commentataire à ajouter 
	 * au dessus de preferences.properties.<br/>
	 * "commentaires_properties/commentaires_preferences_properties.txt"
	 * <br/>
	 *
	 * @return cheminRelatifTemplateCommentaire : String.<br/>
	 */
	public static String getCheminRelatifTemplateCommentaire() {
		return cheminRelatifTemplateCommentaire;
	} // Fin de getCheminRelatifTemplateCommentaire()._____________________



	/**
	 * Getter du Properties encapsulant les préférences.<br/>
	 * SINGLETON.<br/>
	 * <ul>
	 * <li>crée le fichier preferences.properties et alimente 
	 * le Properties preferences avec des valeurs en dur 
	 * si preferences est vide.</li>
	 * <li>trace EX_FONCT_PARAMETRAGE_01</li>
	 * </ul>
	 *
	 * @return preferences : Properties.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Properties getPreferences() throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* crée le fichier preferences.properties et alimente 
			 * le Properties preferences avec des valeurs en dur 
			 * si preferences est vide. */
			if (preferences.isEmpty()) {
				creerFichierPropertiesInitial();
			}
			
			return preferences;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getPreferences().__________________________________________


		
	/**
	 * Getter du Path absolu vers preferences.properties.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return pathAbsoluPreferencesProperties : Path.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Path getPathAbsoluPreferencesProperties() 
											throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			if (pathAbsoluPreferencesProperties == null) {
				instancierAttributsFichierProperties();
			}
			
			return pathAbsoluPreferencesProperties;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getPathAbsoluPreferencesProperties().______________________


		
	/**
	 * Getter de la  Modélisation Java du fichier 
	 * preferences.properties.<br/>
	 * SINGLETON.<br/>
	 *
	 * @return filePreferencesProperties : File.<br/>
	 * 
	 * @throws Exception 
	 */
	public static File getFilePreferencesProperties() throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			if (filePreferencesProperties == null) {
				creerFichierPropertiesInitial();
			}
			
			return filePreferencesProperties;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de getFilePreferencesProperties().____________________________



	/**
	 * retourne le Charset par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit le charset stocké dans preferences.properties 
	 * si il n'est pas null.</li>
	 * <li>UTF-8 sinon (Charset stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le Charset stocké en dur dans la classe (UTF8) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Charset : Charset dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Charset fournirCharsetPrefere() 
			throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* instancie les attributs de fichier si nécessaire. */
			/* alimente Properties avec le contenu 
			 * du fichier properties. */
			lireFichierPreferencesProperties();
			
			/* crée le Properties preferences et 
			 * le remplit avec des valeurs en dur si nécessaire. */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				creerFichierPropertiesInitial();
			}
			
			if (charsetApplication == null) {
				
				/* lecture dans le properties. */
				final String charsetApplicationString 
					= preferences
						.getProperty(
								fournirKeyCharsetApplication());
				
				if (charsetApplicationString != null) {
					try {
						charsetApplication 
						= Charset.forName(charsetApplicationString);
					} catch (Exception e) {
						charsetApplication 
						= Charset.forName(CHARSET_STRING_PAR_DEFAUT_EN_DUR);
					}
					
				}
				else {
					charsetApplication 
						= Charset.forName(CHARSET_STRING_PAR_DEFAUT_EN_DUR);
				}
			}
			
			return charsetApplication;
			
		} // Fin du bloc synchronized.__________________
		
	} // Fin de fournirCharsetSortieParDefaut().___________________________
	

	
	/**
	 * Getter de la clé du charset par défaut de l'application 
	 * dans preferences.properties.<br/>
	 * "application.charset".<br/>
	 *
	 * @return KEY_CHARSET_APPLICATION : String.<br/>
	 */
	public static String fournirKeyCharsetApplication() {
		return KEY_CHARSET_APPLICATION;
	} // Fin de fournirKeyCharsetApplication().____________________________



	/**
	 * Getter du <b>SINGLETON de Charset par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li>lit le charset stocké dans preferences.properties 
	 * si il n'est pas null.</li>
	 * <li>UTF-8 sinon (Charset stocké en dur dans la classe).</li>
	 * </ul>
	 * - retourne le Charset stocké en dur dans la classe (UTF8) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return charsetApplication : Charset.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Charset getCharsetApplication() throws Exception {
		return fournirCharsetPrefere();
	} // Fin de getCharsetApplication().___________________________________
	

	
	/**
	* Setter du <b>SINGLETON de Charset par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* preferences.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier preferences.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si pCharsetApplication == null 
	* ou pCharsetApplication == charsetApplication.<br/>
	* <br/>
	*
	* @param pCharsetApplication : Charset : 
	* valeur à passer à charsetApplication.<br/>
	* 
	 * @throws Exception 
	*/
	public static void setCharsetApplication(
			final Charset pCharsetApplication) throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* ne fait rien si pCharsetApplication == null 
			 * ou pCharsetApplication == charsetApplication. */
			if (pCharsetApplication != null 
					&& !pCharsetApplication.equals(charsetApplication)) {
				
				charsetApplication = pCharsetApplication;
				
				final String nomCharset 
					= pCharsetApplication.displayName();
				
				/* crée le Properties preferences et le fichier 
				 * properties.preferences
				 * et les remplit avec des valeurs en dur si nécessaire. */
				if (filePreferencesProperties == null 
						|| !filePreferencesProperties.exists()) {
					creerFichierPropertiesInitial();
				}
				
				/* modifie preferences avec la nouvelle valeur 
				 * passée dans le setter. */
				creerOuModifierProperty(
						fournirKeyCharsetApplication(), nomCharset);
				
				/* ré-écrit entièrement le fichier preferences.properties mis à jour. */
				enregistrerFichierPreferencesProperties();

			}

		} // Fin du bloc synchronized.__________________
						
	} // Fin de setCharsetApplication(...).________________________________
	

	
	/**
	 * <b>Retourne le langage et le pays d'une Locale 
	 * exprimés en français</b> sous forme de String.<br/>
	 * <ul>
	 * Par exemple :
	 * <li><code>français (France)</code> pour une Locale.FRANCE.</li>
	 * <li><code>anglais (Etats-Unis)</code> pour une Locale.US.</li>
	 * </ul>
	 *
	 * @param pLocale : Locale.<br/>
	 * 
	 * @return : String  : langage et pays d'une Locale.<br/>
	 */
	private static String fournirLangueEtPaysEnFrancais(
			final Locale pLocale) {
		
		/* Bloc synchronized. */
		synchronized (GestionnairePreferences.class) {
			return 
					pLocale.getDisplayLanguage(Locale.FRANCE) 
					+ " (" + pLocale.getDisplayCountry(Locale.FRANCE) + ")";
			
		} // Fin de synchronized._____________________________
		
	} // Fin de fournirLangueEtPaysEnFrancais(...).________________________


	
	/**
	 * <b>Fournit une Locale 
	 * à partir d'une String UNIQUE comportant la langue et le pays
	 * </b> comme "français (France)" ou "anglais (Etats-Unis)" 
	 * conforme à un retour de la méthode 
	 * <code>fournirLangueEtPaysEnFrancais(Locale)</code>.
	 * <ul>
	 * <li>La String doit être conforme à un retour de la méthode 
	 * <code>fournirLangueEtPaysEnFrancais(Locale)</code> 
	 * et donc à la Regex "(\\S+) \\((\\S+)\\)".</li>
	 * <li>Utilise une Regex avec un motif 
	 * <code>"(\\S+) \\((\\S+)\\)"</code> et des groupes qui décompose 
	 * une String comme "anglais (Etats-Unis)" en 
	 * language = "anglais" et country = "Etats-Unis".</li>
	 * </ul>
	 * - retourne la Locale de l'application par défaut en dur 
	 * (Locale.FRANCE) si pLocaleString est blank.<br/>
	 * - retourne la Locale de l'application par défaut en dur 
	 * (Locale.FRANCE) si pLocaleString n'est pas conforme 
	 * à l'expression régulière (Regex) "(\\S+) \\((\\S+)\\)".<br/>
	 * <br/>
	 *
	 * @param pLocaleString : String : 
	 * String décrivant le langage et le pays d'une Locale 
	 * conforme à un retour de la méthode 
	 * <code>fournirLangueEtPaysEnFrancais(Locale)</code>.<br/>
	 * 
	 * @return : Locale : 
	 * Locale correspondant à la description pLocaleString 
	 * ("français (France)" ou "anglais (Etats-Unis)", ...).<br/>
	 */
	private static Locale fournirLocaleParLangueEtPays(
			final String pLocaleString) {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			
			/* retourne la Locale de la plateforme par défaut 
			 * en dur si pLocaleString est blank. */
			if (StringUtils.isBlank(pLocaleString)) {
				return Locale.FRANCE;
			}
			
			/* Décompose une String comme "anglais (Etats-Unis)" en 
			 * Language = "anglais" et coutry = "Etats-Unis". */
			final String motif = "(\\S+) \\((\\S+)\\)";
			final Pattern pattern = Pattern.compile(motif);
			
			final Matcher matcher = pattern.matcher(pLocaleString);
			
			String langue = null;
			String pays = null;
			Locale resultat = null;
			
			if (matcher.matches()) {
				
				langue = matcher.group(1);
				pays = matcher.group(2);
				
				/* Instancie une Locale. */
				resultat = new Locale(langue, pays);
				
				return resultat;
			} 
			
			/* retourne la Locale de l'application en dur
			 * (Locale.FRANCE) 
			 * si pLocaleString n'est pas conforme 
			 * à l'expression régulière (Regex) "(\\S+) \\((\\S+)\\)". */
			return Locale.FRANCE;
			
		} // Fin de synchronized._____________________________
		
	} // Fin de recupererLocaleIHM(...).___________________________________
	

	
	/**
	 * retourne la Locale par défaut de l'application.<br/>
	 * <ul>
	 * <li>lit la Locale stockée dans preferences.properties 
	 * si il n'est pas null.</li>
	 * <li>Locale par défaut en dur sinon (Locale.FRANCE).</li>
	 * </ul>
	 * - retourne la Locale stockée en dur dans la classe (Locale.FRANCE) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : Locale : Locale dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static Locale fournirLocalePreferee() throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* instancie les attributs de fichier si nécessaire. */
			/* alimente Properties avec le contenu 
			 * du fichier properties. */
			lireFichierPreferencesProperties();
			
			/* crée le Properties preferences et 
			 * le remplit avec des valeurs en dur si nécessaire. */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				creerFichierPropertiesInitial();
			}
			
			if (localeDefautApplication == null) {
				
				/* lecture dans le properties. */
				final String localeApplicationString 
					= preferences
						.getProperty(
								fournirKeyLocaleApplication());
				
				if (localeApplicationString != null) {
					
					localeDefautApplication 
					= fournirLocaleParLangueEtPays(localeApplicationString);
									
				}
				else {
					localeDefautApplication = Locale.FRANCE;
				}
			}
			
			return localeDefautApplication;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de fournirLocalePreferee().___________________________________
	
	
	
	/**
	 * getter de la clé de la Locale par défaut de l'application 
	 * dans preferences.properties.<br/>
	 * "application.locale".<br/>
	 *
	 * @return KEY_LOCALE_APPLICATION : String.<br/>
	 */
	public static String fournirKeyLocaleApplication() {
		return KEY_LOCALE_APPLICATION;
	} // Fin de fournirKeyLocaleApplication()._____________________________


	
	/**
	 * Getter du <b>SINGLETON de la Locale par défaut 
	 * dans l'application</b>.
	 * <ul>
	 * <li>lit la Locale stockée dans preferences.properties 
	 * si il n'est pas null.</li>
	 * <li>Locale par défaut en dur sinon (Locale.FRANCE).</li>
	 * </ul>
	 * - retourne la Locale stockée en dur dans la classe (Locale.FRANCE) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return localeDefautApplication : Locale.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Locale getLocaleDefautApplication() throws Exception {
		return fournirLocalePreferee();
	} // Fin de getLocaleDefautApplication().______________________________


	
	/**
	* Setter du <b>SINGLETON de la Locale par défaut 
	* dans l'application</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* preferences.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier preferences.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si pLocaleDefautApplication == null 
	* ou si pLocaleDefautApplication == localeDefautApplication.<br/>
	* <br/>
	*
	* @param pLocaleDefautApplication : Locale : 
	* valeur à passer à localeDefautApplication.<br/>
	* 
	* @throws Exception 
	*/
	public static void setLocaleDefautApplication(
			final Locale pLocaleDefautApplication) throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* ne fait rien si pLocaleDefautApplication == null 
			 * ou si pLocaleDefautApplication == localeDefautApplication. */
			if (pLocaleDefautApplication != null 
					&& !pLocaleDefautApplication.equals(
							localeDefautApplication)) {
				
				localeDefautApplication = pLocaleDefautApplication;
				
				final String nomLocale 
					= fournirLangueEtPaysEnFrancais(localeDefautApplication);
				
				/* crée le Properties preferences et 
				 * le remplit avec des valeurs en dur si nécessaire. */
				if (filePreferencesProperties == null 
						|| !filePreferencesProperties.exists()) {
					creerFichierPropertiesInitial();
				}
				
				creerOuModifierProperty(
						fournirKeyLocaleApplication(), nomLocale);
				
				enregistrerFichierPreferencesProperties();

			}

		} // Fin du bloc synchronized.__________________
		
	} // Fin de setLocaleDefautApplication(...).___________________________
	

	
	/**
	 * retourne le répertoire par défaut du FileChooser.<br/>
	 * <ul>
	 * <li>lit le répertoire stocké dans preferences.properties 
	 * si il n'est pas null.</li>
	 * <li>répertoire par défaut en dur sinon (user.dir).</li>
	 * </ul>
	 * - retourne le répertoire stockée en dur dans la classe (user.dir) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return : File : répertoire dans les préférences.<br/>
	 * 
	 * @throws Exception 
	 */
	private static File fournirRepertoirePrefere() throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* instancie les attributs de fichier si nécessaire. */
			/* alimente Properties avec le contenu 
			 * du fichier properties. */
			lireFichierPreferencesProperties();
			
			/* crée le Properties preferences et 
			 * le remplit avec des valeurs en dur si nécessaire. */
			if (filePreferencesProperties == null 
					|| !filePreferencesProperties.exists()) {
				creerFichierPropertiesInitial();
			}
			
			if (repertoirePrefereFileChooser == null) {
				
				/* lecture dans le properties. */
				final String repertoirePrefereFileChooserString 
					= preferences
						.getProperty(
								fournirKeyRepertoireFileChooser());
				
				if (repertoirePrefereFileChooserString != null) {
					
					repertoirePrefereFileChooser 
					= new File(repertoirePrefereFileChooserString);
									
				}
				else {
					repertoirePrefereFileChooser 
					= new File(
							REPERTOIRE_FILECHOOSER_STRING_PAR_DEFAUT_EN_DUR);
				}
			}
			
			return repertoirePrefereFileChooser;
			
		} // Fin du bloc synchronized.__________________
				
	} // Fin de fournirRepertoirePrefere().________________________________
	
	
	
	/**
	 * Getter de la clé du répertoire sur lequel s'ouvre le FileChooser 
	 * dans preferences.properties<br/>
	 * "repertoire.filechooser"<br/>
	 *
	 * @return : String : "repertoire.filechooser".<br/>
	 */
	public static String fournirKeyRepertoireFileChooser() {
		return KEY_REPERTOIRE_FILECHOOSER;
	} // Fin de fournirKeyRepertoireFileChooser()._________________________



	/**
	 * Getter du <b>SINGLETON de repértoire par défaut 
	 * du FileChooser</b>.
	 * <ul>
	 * <li>lit le répertoire stocké dans preferences.properties 
	 * si il n'est pas null.</li>
	 * <li>répertoire par défaut en dur sinon (user.dir).</li>
	 * </ul>
	 * - retourne le répertoire stockée en dur dans la classe (user.dir) 
	 * si le properties ne peut être lu 
	 * (trace EX_TEC_INITIALISATION_08).<br/>
	 * <br/>
	 *
	 * @return repertoirePrefereFileChooser : File.<br/>
	 * 
	 * @throws Exception 
	 */
	public static File getRepertoirePrefereFileChooser() 
											throws Exception {
		return fournirRepertoirePrefere();
	} // Fin de getRepertoirePrefereFileChooser()._________________________



	/**
	* Setter du <b>SINGLETON de repértoire par défaut 
	 * du FileChooser</b>.<br/>
	* <b>Enregistre la valeur sur disque</b>.<br/>
	* <ul>
	* <li>crée le Properties preferences et le fichier 
	* preferences.properties et les remplit avec des valeurs 
	* en dur si nécessaire.</li>
	* <li>modifie preferences avec la nouvelle valeur 
	* passée dans le setter.</li>
	* <li>ré-écrit entièrement le fichier preferences.properties 
	* mis à jour.</li>
	* <li>trace EX_TEC_PARAMETRAGE_04.</li>
	* </ul>
	* - ne fait rien si pRepertoirePrefereFileChooser == null 
	* ou si pRepertoirePrefereFileChooser == repertoirePrefereFileChooser.<br/>
	* - ne fait rien si pRepertoirePrefereFileChooser 
	* n'est pas un répertoire.<br/>
	* - ne fait rien si pRepertoirePrefereFileChooser n'existe pas.<br/>
	* <br/>
	*
	* @param pRepertoirePrefereFileChooser : File : 
	* valeur à passer à repertoirePrefereFileChooser.<br/>
	* 
	* @throws Exception 
	*/
	public static void setRepertoirePrefereFileChooser(
			final File pRepertoirePrefereFileChooser) 
											throws Exception {
		
		synchronized (GestionnairePreferences.class) {
			
			/* ne fait rien si pRepertoirePrefereFileChooser == null 
			 * ou si pRepertoirePrefereFileChooser == repertoirePrefereFileChooser. */
			if (pRepertoirePrefereFileChooser != null 
					&& !pRepertoirePrefereFileChooser.equals(
							repertoirePrefereFileChooser)) {
				
				/* ne fait rien si pRepertoirePrefereFileChooser 
				 * n'est pas un répertoire. */
				if (!pRepertoirePrefereFileChooser.isDirectory()) {
					return;
				}
				
				/* ne fait rien si pRepertoirePrefereFileChooser n'existe pas. */
				if (!pRepertoirePrefereFileChooser.exists()) {
					return;
				}
				
				repertoirePrefereFileChooser = pRepertoirePrefereFileChooser;
				
				final String nomRepertoire 
					= repertoirePrefereFileChooser.getAbsolutePath();
				
				/* crée le Properties preferences et 
				 * le remplit avec des valeurs en dur si nécessaire. */
				if (filePreferencesProperties == null 
						|| !filePreferencesProperties.exists()) {
					creerFichierPropertiesInitial();
				}
				
				creerOuModifierProperty(
						fournirKeyRepertoireFileChooser(), nomRepertoire);
				
				enregistrerFichierPreferencesProperties();

			}

		} // Fin du bloc synchronized.__________________
		
	} // Fin de setRepertoirePrefereFileChooser(...).______________________
	
		
	
} // FIN DE LA CLASSE GestionnairePreferences.-------------------------------
