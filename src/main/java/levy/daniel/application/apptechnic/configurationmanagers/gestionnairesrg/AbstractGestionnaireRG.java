package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesrg;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;

/**
 * CLASSE AbstractGestionnaireRG :<br/>
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
public abstract class AbstractGestionnaireRG implements IGestionnaireRG {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "_".
	 */
	public static final String UNDERSCORE_STRING = "_";
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/	
	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE 
		= System.getProperty("line.separator");
		
	/**
	 * Map&lt;String, LigneRG&gt; contenant toutes les RG 
	 * concernant l'OBJET METIER 
	 * implémentées dans l'application avec :
	 * <ul>
	 * <li>String : nom de la RG</li>
	 * <li>LigneRG : Encapsulation des éléments relatifs à la RG</li>
	 * </ul>
	 * Une ligne RG encapsule :<br/>
	 * [id;Actif;activité des contrôles sur l'attribut;activité de la RG
	 * ;RG implémentée;clé du type de contrôle;type de contrôle
	 * ;Message d'erreur;Objet Métier concerné;Attribut concerné
	 * ;Classe implémentant la RG;Méthode implémentant la RG;
	 * properties;clé;].<br/>
	 */
	protected final transient Map<String, LigneRG> mapRG 
		= new LinkedHashMap<String, LigneRG>();
	
	/**
	 * bundleExterneRG : ResourceBundle :<br/>
	 * ResourceBundle encapsulant objet_RG.properties.<br/>
	 * objet_RG.properties est un fichier EXTERNE (hors classpath) 
	 * qui doit être accessible à la Maîtrise d'Ouvrage (MOA).<br/>
	 */
	protected transient ResourceBundle bundleExterneRG;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
	= LogFactory.getLog(AbstractGestionnaireRG.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 * <ul>
	 * <li>Instancie this.bundleExterneRG.</li>
	 * </ul>
	 * 
	 * @throws Exception 
	 */
	public AbstractGestionnaireRG() throws Exception {
		
		super();
		
		try {
			/* Instancie this.bundleExterneRG. */
			this.bundleExterneRG = this.getBundleExterneRG();
		}
		catch (MalformedURLException e) {
			if (LOG.isFatalEnabled()) {
				LOG.info("Bundle externe introuvable : ", e);
			}
		}
				
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * <ul>
	 * <li>
	 * Fournit le ResourceBundle associé au fichier <i>externe</i> 
	 * (hors classpath) <b>objet_RG.properties</b> avec la Locale pLocale.
	 * </li>
	 * </ul>
	 * <br/>
	 *
	 * @param pLocale : Locale.<br/>
	 * 
	 * @return : ResourceBundle.<br/>
	 * 
	 * @throws Exception 
	 */
	private ResourceBundle getBundleExterneRG(
			final Locale pLocale) throws Exception {
	
		ResourceBundle resourceBundle = null;
	
		/* Accède au répertoire externe 
		 * contenant objet_RG.properties. */
		final File repertoireRessourcesParametrables 
			= new File(fournirCheminRessourceExterneRG());
	
		final URL[] urlsRessourcesParametrables 
			= { repertoireRessourcesParametrables.toURI().toURL() };
	
		final ClassLoader loaderRessourcesParametrables 
			= new URLClassLoader(urlsRessourcesParametrables);
	
		/* Récupère le ResourceBundle en utilisant le bon ClassLoader. */
		resourceBundle 
			= ResourceBundle.getBundle(
					this.fournirNomBasePropertiesRG()
						, pLocale
							, loaderRessourcesParametrables);
	
		return resourceBundle;
	
	} // Fin de getBundleExterneRG(...).___________________________________

	
	
	/**
	 * <ul>
	 * remplit et retourne la Map&lt;String, LigneRG&gt; mapRG 
	 * contenant toutes 
	 * les Règles de Gestion (RG) de l'UTILISATEUR implémentées 
	 * dans les services de l'application avec :
	 * <li>String : le nom de la RG.</li>
	 * <li>LigneRG : pure fabrication encapsulant 
	 * tous les éléments relatifs à la RG.</li>
	 * </ul>
	 * Une LigneRG encapsule :<br/>
	 * [id;Actif;activité des contrôles sur l'attribut;activité de la RG
	 * ;RG implémentée;clé du type de contrôle;type de contrôle
	 * ;Message d'erreur;Objet Métier concerné;Attribut concerné
	 * ;Classe implémentant la RG;Méthode implémentant la RG;].<br/>
	 * <br/>
	 *
	 * @return : Map&lt;String, LigneRG&gt; : mapRG.<br/>
	 * 
	 * @throws Exception 
	 */
	protected abstract Map<String, LigneRG> remplirMapRG() throws Exception;



	/**
	 * <ul>
	 * <li>fournit le chemin <b>externe</b> (hors classpath) du 
	 * <b>répertoire</b> contenant le fichier 
	 * <b>objet_RG.properties</b>.</li>
	 * <li>Ce chemin doit être écrit <b>EN ABSOLU</b> 
	 * (surtout pas relatif au projet Eclipse).</li>
	 * <li>Par exemple 
	 * <code><b>H:.../ressources_externes/rg/metier/usersimple</b></code> 
	 * pour le fichier "usersimple_RG_fr_FR.properties".</li>
	 * </ul>
	 *
	 * @return : String : chemin absolu vers le File 
	 * objet_RG.properties.<br/>
	 * 
	 * @throws Exception
	 */
	protected abstract String fournirCheminRessourceExterneRG() 
			throws Exception;


	
	/**
	 * <ul>
	 * <li>
	 * fournit le <b>nom de base</b> du objet_RG.properties 
	 * en fonction de l'objet traité par le <b>GestionnaireRGObjet</b>.
	 * </li>
	 * <li>Par exemple : "usersimple_RG" pour l'Object UserSimple.</li>
	 * </ul>
	 *
	 * @return : String : Nom de base du objet_RG.properties.<br/>
	 */
	protected abstract String fournirNomBasePropertiesRG();

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getEnTeteCsv() {
		
		return "id;Actif;activité des contrôles sur l'attribut;"
				+ "activité de la RG;RG implémentée;clé du type de contrôle;"
				+ "type de contrôle;"
				+ "Message d'erreur;Objet Métier concerné;"
				+ "Attribut concerné;Classe implémentant la RG;"
				+ "Méthode implémentant la RG;properties;clé;";
		
	} // Fin de getEnTeteCsv().____________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherListeRGImplementeesCsv() {

		/* retourne null si mapRG == null. */
		if (this.mapRG == null) {
			return null;
		}

//		/* Tri de la Map en fonction du numéro des Règles de Gestion. */
//		/*
//		 * Instanciation d'un comparateur de RG qui trie 
//		 * sur les numéros des RG.
//		 */
//		final ComparatorRG comparateurRG = new ComparatorRG();
//
//		/* Instanciation d'une SortedMap vide avec le comparateur */
//		final SortedMap<String, LigneRG> mapTriee 
//			= new TreeMap<String, LigneRG>(comparateurRG);
//
//		/* Remplissage de la map triée. */
//		mapTriee.putAll(this.mapRG);

		final StringBuilder stb = new StringBuilder();

		stb.append(getEnTeteCsv());
		stb.append(NEWLINE);

//		final Set<Entry<String, LigneRG>> entrySet = mapTriee.entrySet();
		final Set<Entry<String, LigneRG>> entrySet = this.mapRG.entrySet();

		final Iterator<Entry<String, LigneRG>> ite = entrySet.iterator();

		final int nbreEntry = entrySet.size();

		int compteur = 0;

		while (ite.hasNext()) {

			compteur++;

			final Entry<String, LigneRG> entry = ite.next();
			final LigneRG ligneRG = entry.getValue();

			stb.append(ligneRG.fournirStringCsv());

			if (compteur < nbreEntry) {
				stb.append(NEWLINE);
			}
		}

		return stb.toString();

	} // Fin de afficherListeRGImplementeesCsv().__________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Map<String, LigneRG> getMapRG() {
		return this.mapRG;
	} // Fin de getMapRG().________________________________________________
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final ResourceBundle getBundleExterneRG() 
			throws Exception {
		
			if (this.bundleExterneRG == null) {
				this.bundleExterneRG 
					= this.getBundleExterneRG(
							LocaleManager.getLocaleApplication());
			}
			
			return this.bundleExterneRG;
			
	} // Fin de getBundleExterneRG().______________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Path getPathAbsoluPropertiesRG() 
			throws Exception {
		
		Path resultat = null;
		
		final Path pathAbsoluRepProperties 
			= Paths.get(this.fournirCheminRessourceExterneRG());
		
		final String nomBaseProperties = this.fournirNomBasePropertiesRG();
		final Locale localeBundle = this.getBundleExterneRG().getLocale();
		
		final String localeAppliquee 
			= this.getExtensionLangageCountryLocale(localeBundle);
		
		String nomProperties = null;
		
		if (StringUtils.isBlank(localeAppliquee)) {
			nomProperties 
				= nomBaseProperties + localeAppliquee + ".properties";
		} else {
			nomProperties 
			= nomBaseProperties + "_" + localeAppliquee + ".properties";
		}
		
		final Path pathRelatifFichier = Paths.get(nomProperties);
		
		resultat 
			= pathAbsoluRepProperties
				.resolve(pathRelatifFichier)
					.toAbsolutePath().normalize();
		
		return resultat;
		
	} // Fin de getPathAbsoluPropertiesRG()._______________________________
	

	
	/**
	 * retourne l'extension d'une Locale pLocale comme "fr_FR" ou "en_US".<br/>
	 * <ul>
	 * <li>"fr" correspond au langage dans <code><b>fr_FR</b></code>.</li>
	 * <li>"FR" correspond au pays (Country) dans <code><b>fr_FR</b></code>.</li>
	 * </ul>
	 * - retourne null si pLocale == null.<br/>
	 * - retourne une chaîne vide si pLocale.getDisplayName() est blank 
	 * (cas ou le properties n'a pas d'extension de langue-pays comme 
	 * dans 'application.properties').<br/>
	 * <br/>
	 *
	 * @param pLocale : Locale.
	 * 
	 * @return : String : extension comme "fr_FR" 
	 * dans <code><b>application_fr_FR.properties</b></code>.<br/>
	 */
	private String getExtensionLangageCountryLocale(
			final Locale pLocale) {
				
		/* retourne null si pLocale == null. */
		if (pLocale == null) {
			return null;
		}
		
		/* retourne une chaîne vide si pLocale.getDisplayName() est blank. */
		if (StringUtils.isBlank(
				pLocale.getDisplayName(
						LocaleManager.getLocaleApplication()))) {
			return "";
		}

		final String resultat 
			= this.getLangageLocale(pLocale) 
			+ UNDERSCORE_STRING 
			+ this.getCountryLocale(pLocale);
		
		return resultat;
		
	} // Fin de getExtensionLangageCountryLocale(...)._____________________
	
	
	
	/**
	 * <b>retourne le langage d'une Locale pLocale</b>.<br/>
	 * Par exemple : "fr" dans <code><b>fr_FR</b></code> 
	 * ou "en" dans <code><b>en_US</b></code>.<br/>
	 * <br/>
	 * - retourne null si pLocale == null.<br/>
	 * <br/>
	 *
	 * @param pLocale : Locale.<br/>
	 * 
	 * @return : String : Le langage (fr, en, ...) 
	 * de pLocale.<br/>
	 */
	private String getLangageLocale(
			final Locale pLocale) {
		
		/* retourne null si pLocale == null. */
		if (pLocale == null) {
			return null;
		}
		
		return pLocale.getLanguage();
			
	} // Fin de getLangageLocale(...)._____________________________________


	
	/**
	 * <b>retourne le pays d'une Locale pLocale</b>.<br/>
	 * Par exemple : "FR" dans <code><b>fr_FR</b></code> 
	 * ou "US" dans <code><b>en_US</b>/code>.<br/>
	 * <br/>
	 * - retourne null si pLocale == null.<br/>
	 * <br/>
	 *
	 * @param pLocale : Locale.<br/>
	 * 
	 * @return : String : le country (FR, US, ...) 
	 * de pLocale.<br/>
	 */
	private String getCountryLocale(
			final Locale pLocale) {
		
		/* retourne null si pLocale == null. */
		if (pLocale == null) {
			return null;
		}
		
		return pLocale.getCountry();
			
	} // Fin de getCountryLocale(...)._____________________________________
	
	
		
} // FIN DE LA CLASSE AbstractGestionnaireRG.--------------------------------
