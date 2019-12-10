package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;


/**
 * CLASSE GestionnaireProperties :<br/>
 * .<br/>
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
 * Files.createFile(pFile.toPath()),<br/>
 * enregistrer Properties dans fichier .properties,<br/>
 * lire fichier .properties, <br/>
 * fournir nom properties, reconstituer nom properties Locale,<br/>
 * reconstituer nom complet properties en fonction d'une locale, <br/>
 * fournirNomFichierProperties(Locale pLocale), <br/>
 * Path.resolve(...), agrégation de chemins,<br/>
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
public class GestionnaireProperties {
	
	// ************************ATTRIBUTS************************************/

	//*****************************************************************/
	//**************************** CHARSETS ***************************/
	//*****************************************************************/

	/**
	 * CHARSET_UTF8 : Charset :<br/>
	 * Charset.forName("UTF-8").<br/>
	 * Eight-bit Unicode (or UCS) Transformation Format.<br/> 
	 */
	public static final Charset CHARSET_UTF8 
		= Charset.forName("UTF-8");

	
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
	 * Properties Java encapsulant le fichier .properties 
	 * externe <b>this.fichierProperties</b>.<br/>
	 */
	private Properties properties = new Properties();
	
	/**
	 * <b>nom de base du fichier .properties externe</b> paramétrable 
	 * situé dans le répertoire <i>hors classpath</i> 
	 * ("ressources_externes" par exemple) 
	 * indiqué par le centre-serveur dans 
	 * "configuration_ressources_externes.properties".<br/>
	 * <br/>
	 * Par exemple : <br/><code><b>labels</b></code> pour 
	 * <code>label_fr_FR.properties</code> et 
	 * <code>labels_en_US.properties</code><br/>
	 */
	private String nomBaseFichierProperties;
	
	/**
	 * Locale correspondant au fichier .properties 
	 * externe paramétrable.<br/>
	 */
	private Locale locale;
	
	
	/**
	 * commentaire à ajouter en haut du fichier properties.<br/>
	 */
	private String commentaire;

	
	/**
	 * Map contenant les [key; value] en dur pour initialiser 
	 * le fichier properties.<br/>
	 */
	private Map<String, String> mapPropertiesInitiales;
	
	
	/**
	 * Path correspondant au fichier .properties externe 
	 * paramétrable.<br/>
	 * attribut calculé par la présente classe 
	 * dans le constructeur complet ou les setters.<br/>
	 */
	private transient Path pathAbsoluFichierProperties;
	
	/**
	 * File correspondant au fichier .properties externe 
	 * paramétrable.<br/>
	 * attribut calculé par la présente classe 
	 * dans le constructeur complet ou les setters.<br/>
	 */
	private transient File fichierProperties;
	



	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(GestionnaireProperties.class);
	
	
	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 *
	 * @throws Exception
	 */
	public GestionnaireProperties() throws Exception {
		this(null, null, null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * <ul>
	 * <li>passe les paramètres aux attributs 
	 * <code>this.nomBaseFichierProperties</code> 
	 * , <code>this.locale</code>, .</li>
	 * <li>alimente l'attribut 
	 * <code>this.pathAbsoluFichierProperties</code> 
	 * en fonction de <code>this.nomBaseFichierProperties</code> 
	 * et <code>this.locale</code>.</li>
	 * <li>alimente l'attribut <code>this.fichierProperties</code> 
	 * en fonction de <code>this.pathAbsoluFichierProperties</code>.</li>
	 * </ul>
	 *
	 * @param pNomBaseFichierProperties : String : 
	 * <b>nom de base du fichier .properties externe</b> paramétrable.<br/>
	 * @param pLocale : Locale : Locale correspondant au fichier .properties 
	 * externe paramétrable.<br/>
	 * @param pCommentaire : String : 
	 * commentaire à disposer en haut du fichier properties.<br/>
	 * @param pMapPropertiesInitiales : Map&lt;String, String&gt; : 
	 * Map contenant les [key; value] en dur pour initialiser 
	 * le fichier properties.<br/>
	 * 
	 * @throws Exception 
	 */
	public GestionnaireProperties(
			final String pNomBaseFichierProperties
				, final Locale pLocale
					, final String pCommentaire
						, final Map<String, String> pMapPropertiesInitiales) 
									throws Exception {
		
		super();
		
		this.nomBaseFichierProperties = pNomBaseFichierProperties;
		this.locale = pLocale;
		this.commentaire = pCommentaire;
		this.mapPropertiesInitiales = pMapPropertiesInitiales;
		
		/* alimente l'attribut this.pathAbsoluFichierProperties 
		 * en fonction de this.nomBaseFichierProperties et this.locale. */
		this.pathAbsoluFichierProperties 
			= this.fournirPathAbsoluFichierProperties();
		
		/* alimente l'attribut this.fichierProperties 
		 * en fonction de this.pathAbsoluFichierProperties. */
		this.fichierProperties = this.fournirFichierProperties();
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		
		final int prime = 31;
		int result = 1;
		
		result = prime * result
				+ ((this.nomBaseFichierProperties == null) ? 0 
						: this.nomBaseFichierProperties.hashCode());
		
		result 
			= prime * result 
			+ ((this.locale == null) ? 0 : this.locale.hashCode());
		
		return result;
		
	} // Fin de hashCode().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(
			final Object pObject) {
		
		if (this == pObject) {
			return true;
		}
		if (pObject == null) {
			return false;
		}
		if (!(pObject instanceof GestionnaireProperties)) {
			return false;
		}
		
		final GestionnaireProperties other 
			= (GestionnaireProperties) pObject;
		
		if (this.nomBaseFichierProperties == null) {
			if (other.nomBaseFichierProperties != null) {
				return false;
			}
		}
		else if (!this.nomBaseFichierProperties
				.equals(other.nomBaseFichierProperties)) {
			return false;
		}
		
		if (this.locale == null) {
			if (other.locale != null) {
				return false;
			}
		}
		else if (!this.locale.equals(other.locale)) {
			return false;
		}
		
		return true;
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * <b>retourne le path absolu du fichier .properties</b> 
	 * externe paramétrable en fonction de this.nomBaseFichierProperties 
	 * et this.locale.<br/>
	 * Par exemple : <br/>
	 * <code>chemin des ressources externes/labels_fr_FR.properties</code> 
	 * pour un nom de base "label" et une Locale française.<br/>
	 * <br/>
	 * utilise la Locale couramment sélectionnée 
	 * dans <code>LocaleManager</code> si this.locale == null, 
	 * this.locale sinon.<br/>
	 * <ul>
	 * <li>recupère le path des ressources externes auprès 
	 * du ConfigurationApplicationManager.</li>
	 * <li>reconstitue le nom complet du properties en fonction 
	 * de son nom de base et d'une Locale.</li>
	 * <li>fabrique le path absolu du fichier properties en agrégeant 
	 * le chemin des ressources externes et le 
	 * nom complet du properties.</li>
	 * </ul>
	 *
	 * 
	 * @return Path : path absolu du fichier properties externe 
	 * en fonction de la Locale.<br/>
	 * 
	 * @throws Exception 
	 */
	private Path fournirPathAbsoluFichierProperties() throws Exception {
		
		/* utilise la Locale couramment sélectionnée 
		 * dans LocaleManager si this.locale == null. */
		if (this.locale == null) {
			this.locale = LocaleManager.getLocaleApplication();
		}
		
		return this.fournirPathAbsoluFichierProperties(this.locale);
		
	} // Fin de fournirPathAbsoluFichierProperties().______________________
	
	
	
	/**
	 * <b>retourne le path absolu du fichier .properties</b> 
	 * externe paramétrable en fonction de this.nomBaseFichierProperties 
	 * et pLocale.<br/>
	 * Par exemple : <br/>
	 * <code>chemin des ressources externes/labels_en_US.properties</code> 
	 * pour un nom de base "labels" et une Locale US.<br/>
	 * <ul>
	 * <li>recupère le path des ressources externes auprès 
	 * du ConfigurationApplicationManager.</li>
	 * <li>reconstitue le nom complet du properties en fonction 
	 * de son nom de base et d'une Locale.</li>
	 * <li>fabrique le path absolu du fichier properties en agrégeant 
	 * le chemin des ressources externes et le 
	 * nom complet du properties.</li>
	 * </ul>
	 *
	 * @param pLocale : Locale :  .<br/>
	 * 
	 * @return Path : path absolu du fichier properties externe 
	 * en fonction de la Locale.<br/>
	 * 
	 * @throws Exception 
	 */
	private Path fournirPathAbsoluFichierProperties(
			final Locale pLocale) throws Exception {
		
		Path pathAbsoluFichierPropertiesLocal = null;
		
		/* recupère le path des ressources externes 
		 * auprès du ConfigurationApplicationManager. */
		final Path pathAbsoluRessourcesExternes 
			= Paths
				.get(ConfigurationApplicationManager
							.getPathRessourcesExternes());
		
		/* reconstitue le nom complet du properties en fonction 
		 * de son nom de base et d'une Locale. */
		final String nomCompletProperties 
			= reconstituerNomProperties(
					this.nomBaseFichierProperties, pLocale);
		
		/* fabrique le path absolu du fichier properties 
		 * en agrégeant le chemin des ressources externes 
		 * et le nom complet du properties. */
		if (nomCompletProperties != null) {
			pathAbsoluFichierPropertiesLocal 
				= pathAbsoluRessourcesExternes
					.resolve(nomCompletProperties);
		}
		
		
		return pathAbsoluFichierPropertiesLocal;
			
	} // Fin de fournirPathAbsoluFichierProperties(...).___________________
	

	
	/**
	 * <b>Retourne le File associé à this.pathAbsoluFichierProperties</b> 
	 * (<code>this.pathAbsoluFichierProperties.toFile()</code>).<br/>
	 * <ul>
	 * <li>à utiliser pour rafraîchir 
	 * <code>this.fichierProperties</code> 
	 * à chaque changement de 
	 * <code>this.pathAbsoluFichierProperties</code></li>
	 * </ul>
	 *
	 * @return : File : this.pathAbsoluFichierProperties.toFile().<br/>
	 */
	private File fournirFichierProperties() {
		
		if (this.pathAbsoluFichierProperties == null) {
			return null;
		}
		
		return this.pathAbsoluFichierProperties.toFile();
		
	} // Fin de fournirFichierProperties().________________________________
	
	
	
	/**
	 * <b>Fournit la Locale pLocale</b> si elle n'est pas nulle, 
	 * la Locale par défaut de la plateforme sinon.
	 * <ul>
	 * <li>fournit la Locale par défaut de la plateforme 
	 * (Locale.getDefault()) si pLocale == null.</li>
	 * <li>retourne pLocale si pLocale != null.</li>
	 * </ul>
	 *
	 * @param pLocale : Locale.<br/>
	 * 
	 * @return : Locale.<br/>
	 */
	private Locale fournirLocaleParDefaut(
			final Locale pLocale) {
		
		Locale localeInterne = null;
		
		if (pLocale == null) {
			localeInterne= Locale.getDefault();
		}
		else {
			localeInterne = pLocale;
		}
		
		return localeInterne;
			
	} // Fin de fournirLocaleParDefaut(...)._______________________________


	
	/**
	 * <b>Fournit le nom complet d'un fichier properties</b> 
	 * à partir de son nom de base et d'une Locale.
	 * <ul>
	 * <li>Reconstitue le nom complet d'un properties à partir 
	 * de son nom de base et d'une Locale.</li>
	 * <li>Par exemple, 
	 * <code>'application_fr_FR.properties'</code> à partir 
	 * de pNomBaseProperties = 'application' et 
	 * pLocale = Locale("fr", "FR").</li>
	 * <li>Retourne le nom du properties reconstitué 
	 * (par exemple "application_fr_FR.properties").</li>
	 * </ul>
	 * retourne null si pNomBaseProperties est blank.<br/>
	 * Choisit la Locale de la plateforme si pLocale == null.<br/>
	 * <br/>
	 *
	 * @param pNomBaseProperties : String : nom de base du properties 
	 * comme 'application' dans 'application_fr_FR.properties'.<br/>
	 * @param pLocale : Locale.<br/>
	 * 
	 * @return : String : Nom complet du properties.<br/>
	 */
	private String reconstituerNomProperties(
			final String pNomBaseProperties
				, final Locale pLocale) {
					
		/* retourne null si pNomBaseProperties est blank. */
		if (StringUtils.isBlank(pNomBaseProperties)) {
			return null;
		}
		
		final Locale localeInterne = fournirLocaleParDefaut(pLocale);
		
		final String langage = localeInterne.getLanguage();
		final String country = localeInterne.getCountry();
		final String suffixe = langage + UNDERSCORE + country;
		
		final String resultat 
		= pNomBaseProperties 
		+ UNDERSCORE 
		+ suffixe 
		+ ".properties";
		
		return resultat;

	} // Fin de reconstituerNomProperties(...).____________________________



	/**
	 * <b>Crée sur disque dur l'arborescence</b> des répertoires 
	 * <i>parents de pFile</i> si elle n'existe pas déjà.<br/>
	 * <ul>
	 * <li><code>Files.createDirectories(pathParent);</code></li>
	 * </ul>
	 * - ne fait rien si l'arborescence parente de pFile existe déjà.<br/>
	 * - ne fait rien si pFile == null.<br/>
	 * - ne fait rien si pFile est une racine (pas de parent).<br/>
	 * </br/>
	 *
	 * @param pFile : File : 
	 * fichier dont on veut créer l'arborescence parente 
	 * sur disque dur.<br/>
	 * 
	 * @throws IOException
	 */
	private void creerArborescenceParente(
			final File pFile) throws IOException {
		
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
				
	} // Fin de creerArborescenceParente(...)._____________________________
	

	
	/**
	 * <b>Crée sur disque et initialise avec les valeurs en dur 
	 * contenues dans  'this.mapPropertiesInitiales' 
	 * le fichier 'this.fichierProperties'</b>.<br/>
	 * retourne this.fichierProperties.<br/>
	 * <ul>
	 * <li>ne crée this.fichierProperties vide sur disque 
	 * que si il n'existe pas déjà.</li>
	 * <li>ne remplit this.properties avec les valeurs en dur 
	 * contenues dans this.mapPropertiesInitiales et le commentaire 
	 * this.commentaire que si this.properties est vide</li>
	 * </ul>
	 * - return null si this.fichierProperties == null.<br/>
	 * <br/>
	 * 
	 * @return File : this.fichierProperties 
	 * créé sur disque et initialisé.<br/>
	 * 
	 * @throws IOException 
	 */
	public File creerFichierSurDisqueEtRemplir() throws IOException {
		
		/* return null si this.fichierProperties == null. */
		if (this.fichierProperties == null) {
			return null;
		}
		
		/* ne crée this.fichierProperties vide sur disque 
		 * que si il n'existe pas déjà. */
		if (!this.fichierProperties.exists()) {
			this.creerFichierSurDisque(this.fichierProperties);
		}
		
		/* ne remplit this.properties avec les valeurs en dur contenues 
		 * dans this.mapPropertiesInitiales et le commentaire 
		 * this.commentaire que si this.properties est vide. */
		if (this.fichierProperties.length() == 0) {
			this.remplirEnDurFichierProperties(this.commentaire);
		}
		
		return this.fichierProperties;
		
	} // Fin de creerFichierSurDisqueEtRemplir().__________________________
	
	
	
	/**
	 * <b>Crée sur disque un fichier vide pFile et le retourne</b>.
	 * <ul>
	 * <li>Ne crée sur disque pFile que <i>si il n'existe pas déjà</i>.</li>
	 * <li>crée sur disque l'arborescence parente de pFile 
	 * <i>si elle n'existe pas déjà</i>.</li>
	 * <li>retourne pFile si il est déjà existant.</li>
	 * <li><b>n'écrase jamais un fichier déjà existant</b>.</li>
	 * <li>utilise <code>Files.createFile(pFile.toPath());</code></li>.
	 * </ul>
	 * - retourne null si pFile == null.<br/>
	 * <br/>
	 *
	 * @param pFile : File : Fichier à créer sur disque dur.<br/>
	 * 
	 * @return File : le fichier vide existant sur disque 
	 * ou le fichier déjà existant.<br/>
	 * 
	 * @throws IOException
	 */
	private File creerFichierSurDisque(
			final File pFile) 
					throws IOException {
		
		/* retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		/* crée sur disque l'arborescence parente de pFile 
		 * si elle n'existe pas déjà. */
		this.creerArborescenceParente(pFile);
		
		Path pathFile = null;
		
		try {
			
			pathFile = Files.createFile(pFile.toPath());
			
		/* Ne crée sur disque pFile que si il n'existe pas déjà.*/	
		} catch (FileAlreadyExistsException e) {
			return pFile;
		}
		
		return pathFile.toFile();
		
	} // Fin de creerFichierSurDisque(...).________________________________
	
	

	/**
	 * <b>Remplit this.properties avec des valeurs en dur 
	 * contenues dans pMap</b>.<br/>
	 * <br/>
	 * - ne fait rien si pMap == null.<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;String, String&gt; : 
	 * Map contenant des valeurs "en dur" à injecter dans 
	 * this.properties.<br/>
	 */
	private void ajouterPropertiesInitialesEnDur(
			final Map<String, String> pMap) {
		
		/* ne fait rien si pMap == null. */
		if (pMap == null) {
			return;
		}
		
		/* remplit this.properties (fonction lambda). */
		pMap.forEach((key, value) -> this.properties.put(key, value));
		
	} // Fin de ajouterPropertiesInitialesEnDur(...).______________________
	

	
	/**
	 * <b>remplit le fichier 'this.fichierProperties' avec les clés-values 
	 * présentes dans 'this.properties' alimenté 
	 * par 'this.mapPropertiesInitiales'</b>.<br/>
	 * écrit le commentaire pCommentaire en haut du fichier properties.<br/>
	 * <ul>
	 * <li>écrit dans le fichier properties en UTF8.</li>
	 * <li>utilise <code>this.properties.store(writer, pCommentaire);</code>.</li>
	 * <li>utilise un try-with-resource qui se charge 
	 * du close() du Writer.</li>
	 * </ul>
	 * - ne fait rien si this.pathAbsoluFichierProperties == null.<br/>
	 * <br/>
	 * 
	 * @param pCommentaire : String : 
	 *
	 * @throws IOException
	 */
	private void remplirEnDurFichierProperties(
			final String pCommentaire) throws IOException {
		
		/* ne fait rien si 
		 * this.pathAbsoluFichierProperties == null. */
		if (this.pathAbsoluFichierProperties == null) {
			return;
		}
		
		/* try-with-resource qui se charge du close(). */
		try (Writer writer = Files.newBufferedWriter(
				this.pathAbsoluFichierProperties, CHARSET_UTF8)) {
			
			/* ajoute les properties initiales codées en dur. */
			this.ajouterPropertiesInitialesEnDur(
					this.mapPropertiesInitiales);
			
			/* enregistre le Properties this.properties sur disque dur 
			 * dans le fichier .properties this.fichierProperties. */
			this.properties.store(writer, pCommentaire);			
		}
		
	} // Fin de remplirEnDurFichierProperties().___________________________
	

		
	/**
	 * Getter du Properties Java encapsulant le fichier .properties 
	 * externe <b>this.fichierProperties</b>.<br/>
	 * <br/>
	 *
	 * @return this.properties : Properties.<br/>
	 */
	public Properties getProperties() {
		return this.properties;
	} // Fin de getProperties().___________________________________________


	
	/**
	* Setter du Properties Java encapsulant le fichier .properties 
	* externe <b>this.fichierProperties</b>.<br/>
	* <br/>
	*
	* @param pProperties : Properties : 
	* valeur à passer à properties.<br/>
	*/
	public void setProperties(
			final Properties pProperties) {
		this.properties = pProperties;
	} // Fin de setProperties(...).________________________________________


	
	/**
	 * Getter du <b>nom de base du fichier .properties externe</b> 
	 * paramétrable situé dans le répertoire <i>hors classpath</i> 
	 * ("ressources_externes" par exemple) 
	 * indiqué par le centre-serveur dans 
	 * "configuration_ressources_externes.properties".<br/>
	 * <br/>
	 * Par exemple : <br/><code><b>labels</b></code> pour 
	 * <code>label_fr_FR.properties</code> et 
	 * <code>labels_en_US.properties</code>
	 * <br/>
	 *
	 * @return this.nomBaseFichierProperties : String.<br/>
	 */
	public String getNomBaseFichierProperties() {
		return this.nomBaseFichierProperties;
	} // Fin de getNomBaseFichierProperties()._____________________________


	
	/**
	 * Setter du <b>nom de base du fichier .properties externe</b> 
	 * paramétrable situé dans le répertoire <i>hors classpath</i> 
	 * ("ressources_externes" par exemple) 
	 * indiqué par le centre-serveur dans 
	 * "configuration_ressources_externes.properties".<br/>
	 * <br/>
	 * Par exemple : <br/><code><b>labels</b></code> pour 
	 * <code>label_fr_FR.properties</code> et 
	 * <code>labels_en_US.properties</code>
	 * <ul>
	 * <li>alimente l'attribut 
	 * <code>this.pathAbsoluFichierProperties</code> 
	 * en fonction de <code>this.nomBaseFichierProperties</code> 
	 * et <code>this.locale</code>.</li>
	 * <li>alimente l'attribut <code>this.fichierProperties</code> 
	 * en fonction de <code>this.pathAbsoluFichierProperties</code>.</li>
	 * </ul>
	 *
	 * @param pNomBaseFichierProperties : String : 
	 * valeur à passer à nomBaseFichierProperties.<br/>
	 * 
	 * @throws Exception 
	 */
	public void setNomBaseFichierProperties(
			final String pNomBaseFichierProperties) 
					throws Exception {
		
		this.nomBaseFichierProperties = pNomBaseFichierProperties;
				
		/* alimente l'attribut this.pathAbsoluFichierProperties 
		 * en fonction de this.nomBaseFichierProperties et this.locale. */
		this.pathAbsoluFichierProperties 
			= this.fournirPathAbsoluFichierProperties();
		
		/* alimente l'attribut this.fichierProperties 
		 * en fonction de this.pathAbsoluFichierProperties. */
		this.fichierProperties = this.fournirFichierProperties();

	} // Fin de setNomBaseFichierProperties(...).__________________________


	
	/**
	 * Getter de la Locale correspondant au fichier .properties 
	 * externe paramétrable.<br/>
	 *
	 * @return locale : Locale.<br/>
	 */
	public Locale getLocale() {
		return this.locale;
	} // Fin de getLocale()._______________________________________________



	/**
	 * Setter de la Locale correspondant au fichier .properties 
	 * externe paramétrable.<br/>
	 * <ul>
	 * <li>alimente l'attribut 
	 * <code>this.pathAbsoluFichierProperties</code> 
	 * en fonction de <code>this.nomBaseFichierProperties</code> 
	 * et <code>this.locale</code>.</li>
	 * <li>alimente l'attribut <code>this.fichierProperties</code> 
	 * en fonction de <code>this.pathAbsoluFichierProperties</code>.</li>
	 * </ul>
	 *
	 * @param pLocale : Locale : 
	 * valeur à passer à locale.<br/>
	 * 
	 * @throws Exception 
	 */
	public void setLocale(
			final Locale pLocale) throws Exception {
		
		this.locale = pLocale;
		
		/* alimente l'attribut this.pathAbsoluFichierProperties 
		 * en fonction de this.nomBaseFichierProperties et this.locale. */
		this.pathAbsoluFichierProperties 
			= this.fournirPathAbsoluFichierProperties();
		
		/* alimente l'attribut this.fichierProperties 
		 * en fonction de this.pathAbsoluFichierProperties. */
		this.fichierProperties = this.fournirFichierProperties();


	} // Fin de setLocale(...).____________________________________________


		
	/**
	 * Getter du commentaire à ajouter en haut du fichier properties.<br/>
	 * <br/>
	 *
	 * @return commentaire : String.<br/>
	 */
	public String getCommentaire() {
		return this.commentaire;
	} // Fin de getCommentaire().__________________________________________


	
	/**
	* Setter du commentaire à ajouter en haut du fichier properties.<br/>
	* <br/>
	*
	* @param pCommentaire : String : 
	* valeur à passer à commentaire.<br/>
	*/
	public void setCommentaire(
			final String pCommentaire) {
		this.commentaire = pCommentaire;
	} // Fin de setCommentaire(...)._______________________________________


	
	/**
	 * Getter de la Map contenant les [key; value] en dur pour initialiser 
	 * le fichier properties.<br/>
	 * <br/>
	 *
	 * @return mapPropertiesInitiales : Map&lt;String,String&gt;.<br/>
	 */
	public Map<String, String> getMapPropertiesInitiales() {
		return this.mapPropertiesInitiales;
	} // Fin de getMapPropertiesInitiales()._______________________________


	
	/**
	* Setter de la Map contenant les [key; value] en dur pour initialiser 
	* le fichier properties.<br/>
	* <br/>
	*
	* @param pMapPropertiesInitiales : Map<String,String> : 
	* valeur à passer à mapPropertiesInitiales.<br/>
	*/
	public void setMapPropertiesInitiales(
			final Map<String, String> pMapPropertiesInitiales) {
		this.mapPropertiesInitiales = pMapPropertiesInitiales;
	} // Fin de setMapPropertiesInitiales(...).____________________________



	/**
	 * Getter du Path correspondant au fichier .properties externe 
	 * paramétrable.<br/>
	 * attribut calculé par la présente classe 
	 * dans le constructeur complet ou les setters.<br/>
	 * <br/>
	 *
	 * @return pathAbsoluFichierProperties : Path.<br/>
	 */
	public Path getPathAbsoluFichierProperties() {
		return this.pathAbsoluFichierProperties;
	} // Fin de getPathAbsoluFichierProperties().__________________________


	
	/**
	 * Getter du File correspondant au fichier .properties externe 
	 * paramétrable.<br/>
	 * attribut calculé par la présente classe 
	 * dans le constructeur complet ou les setters.<br/>
	 * <br/>
	 *
	 * @return fichierProperties : File.<br/>
	 */
	public File getFichierProperties() {
		return this.fichierProperties;
	} // Fin de getFichierProperties().____________________________________

	
	
} // FIN DE LA CLASSE GestionnaireProperties.--------------------------------
