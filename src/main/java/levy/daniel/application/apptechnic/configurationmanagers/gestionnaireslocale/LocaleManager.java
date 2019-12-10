package levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE <b>LocaleManager</b> :<br/>
 * <ul>
 * <li>Classe utilitaire de CONFIGURATION (finale avec des méthodes statiques)
 * chargée de <b>fournir une Locale unique à toute l'application</b>.</li>
 * <li>En principe, une application internationalisée met à disposition 
 * de l'utilisateur une liste des Locales disponibles.<br/>
 * Lorsque l'utilisateur a choisi une Locale dans la liste, 
 * toute l'application doit s'afficher dans la langue voule.</li>
 * <li>LocaleManager met à disposition de toute l'application 
 * la Locale <b>localeApplication</b> choisie par l'utilisateur.</li>
 * <li>ATTENTION : cette classe ne convient que si un administrateur 
 * modifie la langue de toute l'application pour tous les utilisateurs. 
 * Tous les utiisateurs utilisent la même langue. 
 * Il faudrait une classe avec des méthodes non statiques (objet) 
 * pour gérer l'internationalisation individualisée par utilisateur.</li>
 * <li>Il suffit de faire <code>LocaleManager.getLocaleApplication()</code> 
 * depuis n'importe où dans l'application pour obtenir le SINGLETON 
 * de Locale choisi par l'utilisateur. </li>
 * </ul>
 * <br/>
 * <img src="../../../../../../../../../javadoc/images/apptechnic/LocaleManager.png" 
 * alt="LocaleManager" border="1" align="center" />
 * <br/
 * <br/><br/>
 * <img src="../../../../../../../../../javadoc/images/apptechnic/LocaleManager_Utilisation.png" 
 * alt="Utilisation du LocaleManager" border="1" align="center" />
 * <br/><br/>
 * <br/>
 *
 *<p>
 * - Exemples d'utilisation :
 * </p>
 * <code><i>// Récupération de la Locale américaine depuis l'IHM.</i></code><br/>
 * <b><code>final Locale localeEu = 
 * LocaleManager.recupererLocaleIHM("anglais (Etats-Unis)");</code></b><br/>
 * <br/>
 * <code><i>// SELECTION de la Locale américaine depuis l'IHM.</i></code><br/>
 * <b><code>LocaleManager.selectionnerLocaleApplication("anglais (Etats-Unis)");
 * </code></b><br/>
 * <code><i>// Récupération du SINGLETON de Locale en cours 
 * depuis n'importe où dans l'application.</i></code><br/>
 * <code>final Locale localeApplication 
 * = <b>LocaleManager.getLocaleApplication()</b>;</code><br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Locale, Locale("fr", "FR") en France, <br/>
 * instancier une Locale, SINGLETON, singleton,<br/>
 * sélectionner une Locale, selectionner une locale,<br/>
 * afficherListString, afficherList<String>, afficher Liste String, <br/>
 * afficherListeString, afficher liste String<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 9 août 2017
 *
 */
public final class LocaleManager {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe LocaleManager".<br/>
	 */
	public static final String CLASSE_LOCALEMANAGER 
		= "Classe LocaleManager";

	/**
	 * '\n'.<br/>
	 */
	public static final char SAUT_LIGNE_JAVA_CHAR = '\n';
		
	/**
	 * "_".
	 */
	public static final String UNDERSCORE_STRING = "_";
	
	/**
	 * <b>Locale par défaut de la plate-forme</b>.<br/>
	 * Dépend de la localisation de la machine 
	 * hébergeant l'application.<br/>
	 * <code>Locale.getDefault();</code>
	 */
	private static final Locale LOCALE_PAR_DEFAUT_PLATEFORME 
		= Locale.getDefault();
	
	/**
	 * <b>Locale par défaut de l'application</b> 
	 * paramétrée par l'utilisateur (administrateur).<br/>
	 */
	private static Locale localeParDefautApplication;
	
	/**
	 * <b>Locale à utiliser dans toute l'application</b>.<br/>
	 * <ul>
	 * <li><b>SINGLETON</b> localeApplication à utiliser 
	 * dans toute l'application.</li>
	 * <li><b>modélise la Locale sélectionnée par l'utilisateur</b> 
	 * dans une liste de choix pour la langue à utiliser 
	 * dans l'application.</li>
	 * <li>pré-requis : la langue sélectionnée doit 
	 * avoir été préalablement implémentée par les développeurs 
	 * dans l'application (messages_fr_FR.properties, 
	 * messages_en_US.properties, ...)</li>
	 * <li>L'application doit être internationalisable.</li>
	 * </ul>
	 */
	private static Locale localeApplication;
		
	/**
	 * <b>Collection (Map) des descriptions des 
	 * langues implémentées dans l'application</b> 
	 * et de leurs Locales.<br/>
	 * <ul>
	 * Map contenant les Locales disponibles dans l'application avec :
	 * <li>String : nom de la Locale affiché dans l'IHM comme 
	 * "français (France)" ou "anglais (Etats-Unis)".</li>
	 * <li>Locale : la Locale correspondante comme 
	 * Locale("fr", "FR") ou Locale("en", "US").</li>
	 * </ul>
	 * DOIT ETRE ALIMENTEE PAR LE DEVELOPPEUR dans le bloc static 
	 * A CHAQUE FOIS 
	 * QU'UNE NOUVELLE LANGUE EST IMPLEMENTEE DANS L'APPLICATION 
	 * et les properties correspondant à la nouvelle langue fournis 
	 * (messages_fr_FR.properties, messages_en_US.properties, ...).
	 */
	private static Map<String, Locale> localesDisponibles 
		= new ConcurrentHashMap<String, Locale>();
	

static {
	
	localesDisponibles.put("default", Locale.getDefault());
	localesDisponibles.put(fournirLangueEtPaysEnFrancais(Locale.FRANCE), Locale.FRANCE);
	localesDisponibles.put(fournirLangueEtPaysEnFrancais(Locale.US), Locale.US);
}


	/**
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(LocaleManager.class);

	// *************************METHODES************************************/
	

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation de cette classe utilitaire.<br/>
	 * <br/>
	 */
	private LocaleManager() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * <b>Fournit une Locale 
	 * à partir d'une String</b> appartenant aux clés de la 
	 * Map <b>localesDisponibles</b> contenant les langages 
	 * implémentés dans l'application (internationalisation).
	 * <ul>
	 * <li>Récupère dans l'IHM une String comme "français (France)" 
	 * et retourne la Locale correspondante.<br/>
	 * La String doit être conforme à un retour de la méthode 
	 * <code>fournirLangueEtPaysEnFrancais(Locale)</code>.</li>
	 * <li><b>pLocaleString doit appartenir aux clés de la 
	 * Map 'localesDisponibles'</b> qui contient les langues 
	 * implémentées dans l'application. 
	 * Sinon, la méthode retourne systématiquement la Locale 
	 * par défaut de la plateforme.</li>
	 * <li>retourne la Locale de la plateforme par défaut 
	 * si pLocaleString est blank.</li>
	 * <li>retourne la Locale de la plateforme par défaut 
	 * si pLocaleString  n'appartient pas aux locales disponibles 
	 * dans la Map localesDisponibles.</li>
	 * </ul>
	 *
	 * @param pLocaleString : String : 
	 * String décrivant la Locale sélectionnée dans l'IHM.<br/>
	 * 
	 * @return : Locale : 
	 * Locale correspondant à la description pLocaleString 
	 * ("français (France)" ou "anglais (Etats-Unis)", ...).<br/>
	 */
	public static Locale recupererLocaleIHM(
			final String pLocaleString) {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			
			/* retourne la Locale de la plateforme par défaut 
			 * si pLocaleString est blank. */
			if (StringUtils.isBlank(pLocaleString)) {
				return Locale.getDefault();
			}
			
			if (localesDisponibles.containsKey(pLocaleString)) {
				return localesDisponibles.get(pLocaleString);
			}
			
			/* retourne la Locale de la plateforme par défaut 
			 * si pLocaleString  n'appartient pas aux 
			 * locales disponibles. */
			return Locale.getDefault();
			
		} // Fin de synchronized._____________________________
		
	} // Fin de recupererLocaleIHM(...).___________________________________
	

	
	/**
	 * <b>Fournit une Locale à partir d'une langue et d'un Pays</b>.<br/>
	 * <ul>
	 * <li>Prend en paramètre (récupère dans la base par exemple) 
	 * un couple de  Strings [langue, pays] 
	 * comme ["fr", "FR"] ou ["en", "US"] 
	 * et instancie la Locale correspondante.</li>
	 * <li>Retourne la Locale instanciée si elle appartient 
	 * aux Locales disponibles dans l'application 
	 * (map <b>localesDisponibles</b>).</li>
	 * <li>La locale instanciée doit appartenir aux valeurs de la 
	 * Map 'localesDisponibles'.</li>
	 * <li>retourne la Locale de la plateforme par défaut 
	 * si pLangue ou pPays est blank.</li>
	 * <li>retourne la Locale de la plateforme par défaut 
	 * si la locale instanciée n'appartient pas 
	 * aux locales disponibles.</li>
	 * <li>retourne la locale de la plateforme par défaut si 
	 * [pLangue, pPays] ne correspond à aucune Locale.</li>
	 * </ul>
	 *
	 * @param pLangue : String : langue de la Locale comme 
	 * "fr", "en", ...<br/>
	 * @param pPays : String : pays de la Locale 
	 * comme "FR", "US", ...<br/>
	 * 
	 * @return : Locale.<br/>
	 */
	public static Locale recupererLocaleBase(
			final String pLangue
				, final String pPays) {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			
			/* retourne la Locale de la plateforme par défaut 
			 * si pLocaleString est blank. */
			if (StringUtils.isBlank(pLangue) 
					|| StringUtils.isBlank(pPays)) {
				return Locale.getDefault();
			}
			
			Locale locale = null;
			
			try {
				
				locale = new Locale(pLangue, pPays);
				
				/* retourne la Locale de la plateforme par défaut 
				 * si pLocaleString  n'appartient pas aux 
				 * locales disponibles. */
				if (!localesDisponibles.values().contains(locale)) {
					locale = Locale.getDefault();
				}
				
			}
			catch (Exception e) {
				
				/* retourne la locale de la plateforme par défaut 
				 * si [pLangue, pPays] ne correspond à aucune Locale. */
				locale = Locale.getDefault();
				
			}
							
			return locale;
			
		} // Fin de synchronized._____________________________
		
	} // Fin de recupererLocaleBase(...).__________________________________
	

	
	/**
	 * <b>Selectionne une Locale</b> décrite par la String pLocaleString 
	 * ("anglais (Etats-Unis)" par exemple) parmi les locales 
	 * disponibles dans l'application.<br/>
	 * <b>Affecte la locale sélectionnée au SINGLETON localeApplication</b> 
	 * disponible pour toute l'application.<br/>
	 * <ul>
	 * <li>récupère la Locale sélectionnée dans l'IHM par un 
	 * <b>Administrateur</b> (Locale applicable pour toute 
	 * l'application à la différence d'une Locale sélectionnable 
	 * pour chaque utilisateur).</li>
	 * <ul>
	 * <li>Récupère dans l'IHM une String comme "français (France)" 
	 * et retourne la Locale correspondante.<br/>
	 * La String doit être conforme à un retour de la méthode 
	 * <code>fournirLangueEtPaysEnFrancais(Locale)</code>.</li>
	 * <li><b>pLocaleString doit appartenir aux clés de la 
	 * Map 'localesDisponibles'</b> qui contient les langues 
	 * implémentées dans l'application.</li>
	 * <li>retourne la Locale de la plateforme par défaut 
	 * si pLocaleString est blank.</li>
	 * <li>retourne la Locale de la plateforme par défaut 
	 * si pLocaleString  n'appartient pas aux locales disponibles.</li>
	 * </ul>
	 * <li>passe la Locale sélectionnée dans l'IHM à 
	 * l'attribut <b>localeApplication</b> (SINGLETON).</li>
	 * <li>retourne la Locale <b>localeApplication</b> sélectionnée 
	 * pour toute l'application (SINGLETON).</li>
	 * </ul>
	 *
	 * @param pLocaleString : String : 
	 * String décrivant la Locale sélectionnée dans l'IHM et 
	 * appartenant aux clés de la Map 'localesDisponibles'.<br/>
	 * 
	 * @return : Locale : localeApplication.<br/>
	 */
	public static Locale selectionnerLocaleApplication(
			final String pLocaleString) {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			
			Locale localeSelectionnee = null;
			
			/* récupère la Locale sélectionnée dans l'IHM. */
			localeSelectionnee = recupererLocaleIHM(pLocaleString);
			
			/* passe la Locale sélectionnée dans l'IHM 
			 * à l'attribut localeApplication.*/
			setLocaleApplication(localeSelectionnee);
			
			return localeApplication;
			
		} // Fin de synchronized._____________________________
		
	} // Fin de selectionnerLocaleApplication(...).________________________
	

	
	/**
	 * <b>Retourne la liste des clés (String)</b> des Locales disponibles 
	 * dans l'application et insérées 
	 * dans la Map <b>localesDisponibles</b>.<br/>
	 * Utile pour l'affichage des langues implémentées 
	 * dans une liste de choix pour l'utilisateur.<br/>
	 * <ul>
	 * <li>
	 * Les langues disponibles sont affichées 
	 * sous la forme du retour de la méthode 
	 * <code>fournirLangueEtPaysEnFrancais(Locale)</code>.</li>
	 * Par exemple :
	 * <li><code>français (France)</code> pour une Locale.FRANCE.</li>
	 * <li><code>anglais (Etats-Unis)</code> pour une Locale.US.</li>
	 * <li>retourne null si localesDisponibles == null.</li>
	 * <lil>retourne "français (France)", "anglais (Etats-Unis)"
	 * , .... en fonction des langages internationalisés 
	 * dans l'application.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : 
	 * liste des langues à afficher à l'utilisateur 
	 * comme "français (France)", "anglais (Etats-Unis)", ....<br/>
	 */
	public static List<String> listerLocalesDisponibles() {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			
			/* retourne null si localesDisponibles == null. */
			if (localesDisponibles == null) {
				return null;
			}
			
			final Set<String> clesSet = localesDisponibles.keySet();
			
			return new ArrayList<String>(clesSet);
			
		} // Fin de synchronized._____________________________
		
	} // Fin de listerLocalesDisponibles().________________________________
	

	
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
	public static String getExtensionLangageCountryLocale(
			final Locale pLocale) {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
					
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
				= getLangageLocale(pLocale) 
				+ UNDERSCORE_STRING 
				+ getCountryLocale(pLocale);
			
			return resultat;

		} // Fin de synchronized._____________________________
		
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
	public static String getLangageLocale(
			final Locale pLocale) {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			
			/* retourne null si pLocale == null. */
			if (pLocale == null) {
				return null;
			}
			
			return pLocale.getLanguage();
			
		} // Fin de synchronized._____________________________
		
	} // Fin de getLangageLocale(...)._____________________________________
	
	
	
	/**
	 * <b>retourne le langage actuellement sélectionné 
	 * dans l'application</b>.<br/>
	 * <ul>
	 * <li>Retourne le langage (fr, en, ...) de localeApplication.</li>
	 * </ul>
	 *
	 * @return : String : Le langage (fr, en, ...) 
	 * de localeApplication.<br/>
	 */
	public static String getLangageLocaleApplication() {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			return localeApplication.getLanguage();
		} // Fin de synchronized._____________________________
		
	} // Fin de getLangageLocaleApplication()._____________________________


	
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
	public static String getCountryLocale(
			final Locale pLocale) {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			
			/* retourne null si pLocale == null. */
			if (pLocale == null) {
				return null;
			}
			
			return pLocale.getCountry();
			
		} // Fin de synchronized._____________________________
		
	} // Fin de getCountryLocale(...)._____________________________________



	/**
	 * <b>retourne le pays actuellement sélectionné 
	 * dans l'application</b>.<br/>
	 * <ul>
	 * <li>Retourne le country (FR, US, ...) de localeApplication.</li>
	 * </ul>
	 *
	 * @return : String : le country (FR, US, ...) 
	 * de localeApplication.<br/>
	 */
	public static String getCountryLocaleApplication() {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			return localeApplication.getCountry();
		} // Fin de synchronized._____________________________
		
	} // Fin de getCountryLocaleApplication()._____________________________

	

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
	public static String fournirLangueEtPaysEnFrancais(
			final Locale pLocale) {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			return 
					pLocale.getDisplayLanguage(Locale.FRANCE) 
					+ " (" + pLocale.getDisplayCountry(Locale.FRANCE) + ")";
			
		} // Fin de synchronized._____________________________
		
	} // Fin de fournirLangueEtPaysEnFrancais(...).________________________
	
	
	
	
	/**
	 * <b>retourne la Locale par défaut de la 
	 * plateforme courante</b>.<br/>
	 * <ul>
	 * <li>Retourne la Locale retournée par la JVM en fonction 
	 * de la position de la machine dans le monde.</li>
	 * <li>Locale("fr", "FR") en France.</li>
	 * </ul>
	 *
	 * @return : Locale : Locale("fr", "FR") en France.<br/>
	 */
	public static Locale getLocaleParDefaut() {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			return Locale.getDefault();
		} // Fin de synchronized._____________________________
		
	} // Fin de getLocaleParDefaut().______________________________________


	
	/**
	 * <b>Affiche sous forme de String la liste des langages disponibles 
	 * implémentés dans l'application</b>.<br/>
	 * <ul>
	 * <li>La String fournie incorpore les 
	 * sauts de lignes de la plateforme (SAUT_LIGNE_PLATEFORME).</li>
	 * <li>
	 * Les langues disponibles sont affichées 
	 * sous la forme du retour de la méthode 
	 * <code>fournirLangueEtPaysEnFrancais(Locale)</code>.</li>
	 * Par exemple :
	 * <li><code>français (France)</code> pour une Locale.FRANCE.</li>
	 * <li><code>anglais (Etats-Unis)</code> pour une Locale.US.</li>
	 * </ul>
	 *
	 * @return : String.<br/>
	 */
	public static String afficherLangagesDisponibles() {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			return afficherListeString(listerLocalesDisponibles());
		} // Fin de synchronized._____________________________
		
	} // Fin de afficherLangagesDisponibles()._____________________________
	
	
	
	/**
	 * Fournit une String pour l'affichage 
	 * d'une List&lt;String&gt;.<br/>
	 * <br/>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;String&gt;
	 * 
	 * @return : String.<br/>
	 */
	public static String afficherListeString(
			final List<String> pList) {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final StringBuffer stb = new StringBuffer();
			
			for (final String ligne : pList) {
				stb.append(ligne);
				stb.append(SAUT_LIGNE_JAVA_CHAR);
			}
			
			return stb.toString();
			
		} // Fin de synchronized._____________________________
				
	} // Fin de afficherListeString(...).__________________________________

	
		
	/**
	 * <b>retourne la Collection (Map) des langages implémentés 
	 * dans l'application "localesDisponibles"</b>.<br/>
	 *<ul>
	 * Getter de la Map contenant les Locales disponibles 
	 * dans l'application avec :
	 * <li>String : nom de la Locale affiché dans l'IHM 
	 * comme "français (France)" ou "anglais (Etats-Unis)".</li>
	 * <li>Locale : la Locale correspondante
	 *  comme Locale("fr", "FR") ou Locale("en", "US").</li>
	 * </ul>
	 *
	 * @return localesDisponibles : Map<String,Locale>.<br/>
	 */
	public static Map<String, Locale> getLocalesDisponibles() {
	
		return localesDisponibles;
		
	} // Fin de getLocalesDisponibles().___________________________________


	
	/**
	 * /**
	 * Getter de la <b>Locale par défaut de la plate-forme</b>.<br/>
	 * Dépend de la localisation de la machine 
	 * hébergeant l'application.<br/>
	 * <code>Locale.getDefault();</code>
	 * <br/>
	 *
	 * @return LOCALE_PAR_DEFAUT_PLATEFORME : Locale.<br/>
	 */
	public static Locale getLocaleParDefautPlateforme() {
		return LOCALE_PAR_DEFAUT_PLATEFORME;
	} // Fin de getLocaleParDefautPlateforme().____________________________



	/**
	 * Getter de la <b>Locale par défaut de l'application</b> 
	 * paramétrée par l'utilisateur (administrateur).<br/>
	 *
	 * @return localeParDefautApplication : Locale.<br/>
	 */
	public static Locale getLocaleParDefautApplication() {
		return localeParDefautApplication;
	} // Fin de getLocaleParDefautApplication().___________________________



	/**
	 * <b>retourne le SINGLETON de langage (Locale) couramment 
	 * sélectionné dans l'application</b>.<br/>
	 * <ul>
	 * <li>Getter du <b>SINGLETON</b> localeApplication à utiliser 
	 * dans toute l'application.</li>
	 * <li><b>modélise la Locale sélectionnée par l'Administrateur</b> 
	 * dans une liste de choix pour la langue à utiliser 
	 * dans l'application.</li>
	 * <li><b>retourne la Locale par défaut de la plateforme</b> 
	 * si l'Administrateur ne sélectionne pas explicitement une Locale.</li>
	 * <li>pré-requis : la langue sélectionnée doit 
	 * avoir été préalablement implémentée par les développeurs 
	 * dans l'application (messages_fr_FR.properties, 
	 * messages_en_US.properties, ...)</li>
	 * <li>L'application doit être internationalisable.</li>
	 * </ul>
	 * N'instancie localeApplication avec la Locale par 
	 * défaut de la plateforme que si elle est null (SINGLETON).<br/>
	 * <br/>
	 *
	 * @return localeApplication : Locale.<br/>
	 */
	public static Locale getLocaleApplication() {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			
			/* N'instancie localeApplication que 
			 * si elle est null (SINGLETON). */
			if (localeApplication == null) {
				localeApplication = recupererLocaleIHM(null);
			}
			
			return localeApplication;
			
		} // Fin de synchronized._____________________________
		
	} // Fin de getLocaleApplication().____________________________________
	

	
	/**
	 * <b>affecte le SINGLETON de langage (Locale) couramment 
	 * sélectionné dans l'application</b>.<br/>
	 * <ul>
	 * <li>Setter du <b>SINGLETON localeApplication</b> à utiliser 
	 * dans toute l'application.</li>
	 * <li><b>modélise la Locale sélectionnée par l'utilisateur</b> 
	 * dans une liste de choix pour la langue à utiliser 
	 * dans l'application.</li>
	 * <li>pré-requis : la langue sélectionnée doit 
	 * avoir été préalablement implémentée par les développeurs 
	 * dans l'application (messages_fr_FR.properties, 
	 * messages_en_US.properties, ...)</li>
	 * <li>L'application doit être internationalisable.</li>
	 * </ul>
	*
	* @param pLocaleApplication : Locale : 
	* valeur à passer à localeApplication.<br/>
	*/
	public static void setLocaleApplication(
			final Locale pLocaleApplication) {
		
		/* Bloc synchronized. */
		synchronized (LocaleManager.class) {
			localeApplication = pLocaleApplication;
		} // Fin de synchronized._____________________________
		
	} // Fin de setLocaleApplication(
	// Locale pLocaleApplication)._________________________________________
	
	
		
} // FIN DE LA CLASSE LocaleManager.-----------------------------------------
