package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesnomenclatures;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierInexistantRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierNullRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierRepertoireRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.FichierVideRunTimeException;

/**
 * CLASSE ConfigurationNomenclaturesHistoF07Manager :<br/>
 * Classe UTILITAIRE 
 * chargée de gérer la configuration des 
 * <b>NOMENCLATURES DE TOUS LES CHAMPS A NOMENCLATURE DU FICHIER HISTO_F07</b>.<br/>
 * Les nomenclatures sont des <i>ressources</i> 
 * indispensables à l'application <b>positionnées dans le classpath</b>.<br/>
 * Les nomenclatures utilisées dans la présente application 
 * sont toutes au <b>format CSV</b> et <b>encodées en UTF-8 avec BOM</b> 
 * pour être facilement lisibles dans Microsoft Excel.
 * <p>
 * Les nomenclatures seront donc <b>intégrées au livrable</b> 
 * (jar ou war) à chaque build de l'application.
 * </p>
 * <p>
 * Les nomenclatures étant des <b>ressources internes
 * intégrées dans le classpath</b>, elle doivent être accédées 
 * en <i>mode RESSOURCES</i> 
 * (et pas en mode FILE) puisque leur localisation finale (contexte) dépendra 
 * de l'installation du livrable (jar ou war) par le centre-serveur.<br/>
 * <br/>
 * <code> // Récupération du ClassLoader dans le contexte.</code><br/>
 * <code><b>ClassLoader classloader = Thread.currentThread().getContextClassLoader();</b></code><br/>
 * <code> // Récupération auprès du ClassLoader de l'URL de la ressource dans le contexte.</code><br/>
 * <code><b>URL urlRessources = classloader.getResource("ressources/ressourceXXX.csv");</b></code><br/>
 * <code> // Transformation de l'URL (Uniform Resource Locator) en URI (Uniform Resource Identifier).</code><br/>
 * <code><b>URI uriRessources = urlRessources.toURI();</b></code><br/>
 * <code> // Récupération de la ressource sous forme de File.</code><br/>
 * <code><b>File ressourceFile = new File(uriRessources.getPath());</b></code><br/>
 * </p>
 * 
 * <p>
 * Met à disposition de l'ensemble de l'application 
 * des <b>Singletons</b>.
 * </p>
 * 
 * <ul>
 * <li>La méthode <code>getCheminNomenclaturesHistoF07Utf8()</code> 
 * fournit un Singleton 
 * du chemin du répertoire parent contenant les nomenclatures 
 * encodées en UTF-8 des champs à nomenclature du fichier HISTO_F07.<br/>
 * Elle retourne en principe 
 * 'ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8'.</li>
 * <li>Les méthodes getNomNomenclatureXXX fournissent un singleton  
 * du nom du fichier de nomenclature du champXXX 
 * encodé en UTF-8 dans le HISTO_F07.</li>
 * <li>Les méthodes getFichierNomenclatureXXX fournissent un singleton  
 * du fichier de nomenclature du champXXX 
 * encodé en UTF-8 dans le HISTO_F07.</li>
 * </ul>
 *  
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ConfigurationNomenclaturesHistoF07Manager : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../javadoc/images/apptechnic/configurationmanagers/gestionnairesnomenclatures/classe_GestionnaireNomenclaturesHistoF07Manager.png" 
 * alt="Diagramme de classe du ConfigurationNomenclaturesHistoF07Manager" />
 * </p>
 * 
 * <br/>
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <code> // Récupération de la nomenclature du SENS sous forme de File.</code><br/>
 * <code><b>File fichierNomenclatureHistoF07Sens = ConfigurationNomenclaturesHistoF07Manager.getFichierNomenclatureHistoF07SensUtf8();</b></code><br/>
 * <code> // Récupération de l'éventuel message d'erreur de la méthode (null si OK)</code><br/>
 * <code><b>String messageIndividuelRapport = ConfigurationNomenclaturesHistoF07Manager.getMessageIndividuelRapport();</b></code><br/>
 * <code> // Récupération de l'éventuel message d'erreur de la classe pour l'ensemble des méthodes (null si OK)</code><br/>
 * <code><b>String rapportConfigurationCsv = ConfigurationNomenclaturesHistoF07Manager.getRapportConfigurationCsv();</b></code><br/>
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
 * @since 16 avr. 2016
 *
 */
public final class ConfigurationNomenclaturesHistoF07Manager {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe ConfigurationNomenclaturesHistoF07Manager".<br/>
	 */
	public static final String CLASSE_CONFIGURATIONNOMENCLATURESHISTO_F07 
		= "Classe ConfigurationNomenclaturesHistoF07Manager";
		
	/**
	 * "Méthode getCheminNomenclaturesHistoF07Utf8".<br/>
	 */
	public static final String METHODE_GET_CHEMINNOMENCLATURES_HISTO_F07 
		= "Méthode getCheminNomenclaturesHistoF07Utf8";
	
	/**
	 * "Méthode getNomNomenclatureHistoF07Sens()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F07_SENS 
		= "Méthode getNomNomenclatureHistoF07Sens()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF07Nature()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F07_NATURE 
		= "Méthode getNomNomenclatureHistoF07Nature()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF07CatAdminRoute()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CATADMINROUTE 
		= "Méthode getNomNomenclatureHistoF07CatAdminRoute()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF07TypeComptage()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F07_TYPECOMPTAGE 
		= "Méthode getNomNomenclatureHistoF07TypeComptage()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF07ClassementRoute()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CLASSEMENTROUTE 
		= "Méthode getNomNomenclatureHistoF07ClassementRoute()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF07ClasseLargeurChausseeU()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CLASSELARGEURCHAUSSEEU 
		= "Méthode getNomNomenclatureHistoF07ClasseLargeurChausseeU()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF07ClasseLargeurChausseesS()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CLASSELARGEURCHAUSSEESS 
		= "Méthode getNomNomenclatureHistoF07ClasseLargeurChausseesS()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF07TypeReseau()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F07_TYPERESEAU 
		= "Méthode getNomNomenclatureHistoF07TypeReseau()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF07CodeConcession()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CODECONCESSION 
		= "Méthode getNomNomenclatureHistoF07CodeConcession()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF07ProfilTravers()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F07_PROFILTRAVERS 
		= "Méthode getNomNomenclatureHistoF07ProfilTravers()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF07SousReseauIndice()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F07_SOUSRESEAUINDICE 
		= "Méthode getNomNomenclatureHistoF07SousReseauIndice()";
	
	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/
	/**
	 * ";"<br/>
	 * Séparateur pour les CSV ";".<br/>
	 */
	public static final String SEP_PV = ";";
    
	/**
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/	
	/**
	 * NEWLINE : String :<br/>
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");

	// ******************************************************************
	// NOMENCLATURES.****************************************************
	// ******************************************************************
		
	// HISTO_F07.*********************
	
	/**
	 * <b>Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.histof07.utf8".<br/>
	 */
	private static transient String cheminNomenclaturesHistoF07Utf8;

	/**
	 * <b>Path relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.histof07.utf8".<br/>
	 */
	private static transient Path pathNomenclaturesHistoF07Utf8;
	
	/**
	 * Nom du fichier de nomenclature du SENS pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sens.histof07".<br/>
	 */
	private static transient String nomNomenclatureHistoF07Sens;
	
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le SENS dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF07SensUtf8;
	
	/**
	 * Nom du fichier de nomenclature de la NATURE DU COMPTAGE 
	 * pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.nature.histof07".<br/>
	 */
	private static transient String nomNomenclatureHistoF07Nature;
	
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la
	 * Nomenclature pour la NATURE du comptage 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF07NatureUtf8;
	
	/**
	 * Nom du fichier de nomenclature de la CATEGORIE ADMINISTRATIVE 
	 * de la route pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.catadminroute.histof07"<br/>	 
	 * */
	private static transient String nomNomenclatureHistoF07CatAdminRoute;
		
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CATEGORIE ADMINISTRATIVE de la route 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF07CatAdminRouteUtf8;
	
	/**
	 * Nom du fichier de nomenclature du TYPE DE COMPTAGE
	 * pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typecomptage.histof07"<br/>
	 */
	private static transient String nomNomenclatureHistoF07TypeComptage;
		
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le TYPE DE COMPTAGE dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF07TypeComptageUtf8;
	
	/**
	 * Nom du fichier de nomenclature du CLASSEMENT DE LA ROUTE 
	 * pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classementroute.histof07"<br/>
	 */
	private static transient String nomNomenclatureHistoF07ClassementRoute;
		
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le CLASSEMENT DE LA ROUTE dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF07ClassementRouteUtf8;
	
	/**
	 * Nom du fichier de nomenclature de la 
	 * CLASSE DE LARGEUR DE CHAUSSEE UNIQUE pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.histof07"<br/>
	 */
	private static transient String nomNomenclatureHistoF07ClasseLargeurChausseeU;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF07_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8;
	
	/**
	 * Nom du fichier de nomenclature de la 
	 * CLASSE DE LARGEUR DE CHAUSSEES SEPAREES pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseess.histof07"<br/>
	 */
	private static transient String nomNomenclatureHistoF07ClasseLargeurChausseesS;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF07_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8;
	
	/**
	 * Nom du fichier de nomenclature du TYPE DE RESEAU
	 * pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_TypeReseau_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typereseau.histof07"<br/>
	 */
	private static transient String nomNomenclatureHistoF07TypeReseau;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le TYPE DE RESEAU 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_TypeReseau_HistoF07_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF07TypeReseauUtf8;
	
	/**
	 * Nom du fichier de nomenclature du CODE CONCESSION
	 * pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_CodeConcession_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.codeconcession.histof07"<br/>
	 */
	private static transient String nomNomenclatureHistoF07CodeConcession;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le CODE CONCESSION 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_CodeConcession_HistoF07_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF07CodeConcessionUtf8;
	
	/**
	 * Nom du fichier de nomenclature du PROFIL EN TRAVERS
	 * pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ProfilTravers_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.profiltravers.histof07"<br/>
	 */
	private static transient String nomNomenclatureHistoF07ProfilTravers;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le PROFIL EN TRAVERS 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ProfilTravers_HistoF07_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF07ProfilTraversUtf8;
	
	/**
	 * Nom du fichier de nomenclature du SOUS RESEAU INDICE
	 * pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_SousReseauIndice_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sousreseauindice.histof07"<br/>
	 */
	private static transient String nomNomenclatureHistoF07SousReseauIndice;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le SOUS RESEAU INDICE 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_SousReseauIndice_HistoF07_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF07SousReseauIndiceUtf8;
	

	/**
	 * rapportConfigurationCsv : String :<br/>
	 * Rapport du chargement de la configuration au format csv.<br/>
	 * Le rapport est null si il n'y a eu aucun 
	 * problème d'initialisation de l'application.<br/>
	 */
	private static transient String rapportConfigurationCsv;

	
	/**
	 * messageIndividuelRapport : String :<br/>
	 * Message pour le Rapport du chargement de la configuration au format csv 
	 * généré par chaque méthode individuellement.<br/>
	 */
	private static transient String messageIndividuelRapport;
	

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(ConfigurationNomenclaturesHistoF07Manager.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour interdire l'instanciation.<br/>
	 */
	private ConfigurationNomenclaturesHistoF07Manager() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________

	

	/**
	 * Getter du <b>Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirCheminNomenclaturesHistoF07Utf8EnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.chemin.histof07.utf8".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminNomenclaturesHistoF07Utf8EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminNomenclaturesHistoF07Utf8EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return cheminNomenclaturesHistoF07Utf8 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getCheminNomenclaturesHistoF07Utf8() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (cheminNomenclaturesHistoF07Utf8 == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleCheminNomenclaturesHistoF07Utf8());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_CHEMINNOMENCLATURES_HISTO_F07,
								fournirCleCheminNomenclaturesHistoF07Utf8(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							cheminNomenclaturesHistoF07Utf8 
								= fournirCheminNomenclaturesHistoF07Utf8EnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							cheminNomenclaturesHistoF07Utf8 = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_CHEMINNOMENCLATURES_HISTO_F07,
								fournirCleCheminNomenclaturesHistoF07Utf8(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						cheminNomenclaturesHistoF07Utf8 
							= fournirCheminNomenclaturesHistoF07Utf8EnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					cheminNomenclaturesHistoF07Utf8 
						= fournirCheminNomenclaturesHistoF07Utf8EnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (cheminNomenclaturesHistoF07Utf8 == null)._________

			return cheminNomenclaturesHistoF07Utf8;

		} // Fin de synchronized.________________________________________

	} // Fin de getCheminNomenclaturesHistoF07Utf8().______________________
	

	
	/**
	 * Getter du <b>Path relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.histof07.utf8".<br/>
	 *
	 * @return : Path.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Path getPathNomenclaturesHistoF07Utf8() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			return Paths.get(getCheminNomenclaturesHistoF07Utf8());
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de getPathNomenclaturesHistoF07Utf8().________________________

	
	
	/**
	 * retourne la clé du 
	 * Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.histof07.utf8".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.chemin.histof07.utf8".<br/>
	 */
	private static String fournirCleCheminNomenclaturesHistoF07Utf8() {
		return "application.repertoire.ressources.nomenclatures.chemin.histof07.utf8";
	} // Fin de fournirCleCheminNomenclaturesHistoF07Utf8()._______________
	

	
	/**
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour <code>cheminNomenclaturesHistoF07Utf8</code>.<br/>
	 * <br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8".<br/>
	 */
	private static String fournirCheminNomenclaturesHistoF07Utf8EnDur() {
		return "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8";
	} // Fin de fournirCheminNomenclaturesHistoF07Utf8EnDur()._____________
	


	/**
	 * Getter du 
	 * Nom du fichier de nomenclature du SENS pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF07SensEnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.sens.histof07".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07SensEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07SensEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF07Sens : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF07Sens() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF07Sens == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureHistoF07Sens());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_SENS,
								fournirCleNomNomenclatureHistoF07Sens(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureHistoF07Sens 
								= fournirNomNomenclatureHistoF07SensEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureHistoF07Sens = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_SENS,
								fournirCleNomNomenclatureHistoF07Sens(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureHistoF07Sens 
							= fournirNomNomenclatureHistoF07SensEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF07Sens 
						= fournirNomNomenclatureHistoF07SensEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF07Sens == null)._________

			return nomNomenclatureHistoF07Sens;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF07Sens().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du SENS dans le HISTO_F07 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sens.histof07".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.sens.histof07".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF07Sens() {
		return "application.repertoire.ressources.nomenclatures.sens.histof07";
	} // Fin de fournirCleNomNomenclatureHistoF07Sens().___________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF07Sens</code>.<br/>
	 *
	 * @return : String : "2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF07SensEnDur() {
		return "2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF07SensEnDur()._________________

	
	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le SENS dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_Sens_HistoF07_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF07SensUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF07SensUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF07SensUtf8 == null) {
				
				final Path pathRelatifNomenclatureSensHistoF07 
					= Paths.get(getNomNomenclatureHistoF07Sens());
		
				final Path pathRelatifContextNomenclatureSensHistoF07 
					= getPathNomenclaturesHistoF07Utf8()
						.resolve(pathRelatifNomenclatureSensHistoF07);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureSensHistoF07.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureSensHistoF07);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF07SensUtf8 
					= new File(uriRessources.getPath());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF07SensUtf8
						, "Méthode getFichierNomenclatureHistoF07SensUtf8()");
			}
			
			return fichierNomenclatureHistoF07SensUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF07SensUtf8().__________________
	

	
	/**
	 * Getter du Nom du fichier de nomenclature de la NATURE DU COMPTAGE 
	 * pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF07NatureEnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.nature.histof07".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07NatureEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07NatureEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF07Nature : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF07Nature() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF07Nature == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureHistoF07Nature());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_NATURE,
								fournirCleNomNomenclatureHistoF07Nature(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureHistoF07Nature 
								= fournirNomNomenclatureHistoF07NatureEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureHistoF07Nature = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_NATURE,
								fournirCleNomNomenclatureHistoF07Nature(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureHistoF07Nature 
							= fournirNomNomenclatureHistoF07NatureEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF07Nature 
						= fournirNomNomenclatureHistoF07NatureEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF07Nature == null)._________

			return nomNomenclatureHistoF07Nature;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF07Nature().________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 de NATURE du comptage 
	 * dans le HISTO_F07 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.nature.histof07".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.nature.histof07".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF07Nature() {
		return "application.repertoire.ressources.nomenclatures.nature.histof07";
	} // Fin de fournirCleNomNomenclatureHistoF07Nature()._________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le <code>nomNomenclatureHistoF07Nature</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF07NatureEnDur() {
		return "2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF07NatureEnDur()._______________
	
	
	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la
	 * Nomenclature pour la NATURE du comptage 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_Nature_HistoF07_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF07NatureUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF07NatureUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF07NatureUtf8 == null) {

				final Path pathRelatifNomenclatureNatureHistoF07 
					= Paths.get(getNomNomenclatureHistoF07Nature());
		
				final Path pathRelatifContextNomenclatureNatureHistoF07 
					= getPathNomenclaturesHistoF07Utf8()
						.resolve(pathRelatifNomenclatureNatureHistoF07);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureNatureHistoF07.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureNatureHistoF07);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF07NatureUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF07NatureUtf8
						, "Méthode getFichierNomenclatureHistoF07NatureUtf8()");
			}
			
			return fichierNomenclatureHistoF07NatureUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF07NatureUtf8().________________
	


	/**
	 * Getter du Nom du fichier de nomenclature de la catégorie administrative 
	 * de la route pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF07CatAdminRouteEnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.catadminroute.histof07".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07CatAdminRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07CatAdminRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF07CatAdminRoute : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF07CatAdminRoute() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF07CatAdminRoute == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureHistoF07CatAdminRoute());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CATADMINROUTE,
								fournirCleNomNomenclatureHistoF07CatAdminRoute(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureHistoF07CatAdminRoute 
								= fournirNomNomenclatureHistoF07CatAdminRouteEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureHistoF07CatAdminRoute = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CATADMINROUTE,
								fournirCleNomNomenclatureHistoF07CatAdminRoute(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureHistoF07CatAdminRoute 
							= fournirNomNomenclatureHistoF07CatAdminRouteEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF07CatAdminRoute 
						= fournirNomNomenclatureHistoF07CatAdminRouteEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF07CatAdminRoute == null)._________

			return nomNomenclatureHistoF07CatAdminRoute;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF07CatAdminRoute()._________________


	
	/**
	 * retourne la clé du nom de la nomenclature en UTF-8 
	 * de CATEGORIE ADMINISTRATIVE de la route 
	 * dans le HISTO_F07 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.catadminroute.histof07".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.catadminroute.histof07".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF07CatAdminRoute() {
		return "application.repertoire.ressources.nomenclatures.catadminroute.histof07";
	} // Fin de fournirCleNomNomenclatureHistoF07CatAdminRoute()._______________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF07CatAdminRoute</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF07CatAdminRouteEnDur() {
		return "2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF07CatAdminRouteEnDur()._____________
	

	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CATEGORIE ADMINISTRATIVE de la route 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_CatAdminRoute_HistoF07_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF07CatAdminRouteUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF07CatAdminRouteUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF07CatAdminRouteUtf8 == null) {

				final Path pathRelatifNomenclatureCatAdminRouteHistoF07 
				= Paths.get(getNomNomenclatureHistoF07CatAdminRoute());
		
				final Path pathRelatifContextNomenclatureCatAdminRouteHistoF07 
					= getPathNomenclaturesHistoF07Utf8()
						.resolve(pathRelatifNomenclatureCatAdminRouteHistoF07);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureCatAdminRouteHistoF07.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureCatAdminRouteHistoF07);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF07CatAdminRouteUtf8 
					= new File(uriRessources.getPath());

				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF07CatAdminRouteUtf8
						, "Méthode getFichierNomenclatureHistoF07CatAdminRouteUtf8()");
			}
			
			return fichierNomenclatureHistoF07CatAdminRouteUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF07CatAdminRouteUtf8().______________
	

	
	/**
	 * Getter du Nom du fichier de nomenclature du TYPE DE COMPTAGE
	 * pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF07TypeComptageEnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.typecomptage.histof07".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07TypeComptageEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07TypeComptageEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF07TypeComptage : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF07TypeComptage() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF07TypeComptage == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureHistoF07TypeComptage());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_TYPECOMPTAGE,
								fournirCleNomNomenclatureHistoF07TypeComptage(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureHistoF07TypeComptage 
								= fournirNomNomenclatureHistoF07TypeComptageEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureHistoF07TypeComptage = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_TYPECOMPTAGE,
								fournirCleNomNomenclatureHistoF07TypeComptage(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureHistoF07TypeComptage 
							= fournirNomNomenclatureHistoF07TypeComptageEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF07TypeComptage 
						= fournirNomNomenclatureHistoF07TypeComptageEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF07TypeComptage == null)._________

			return nomNomenclatureHistoF07TypeComptage;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF07TypeComptage()._______________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 de TYPE DE COMPTAGE 
	 * dans le HISTO_F07 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typecomptage.histof07".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.histof07".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF07TypeComptage() {
		return "application.repertoire.ressources.nomenclatures.typecomptage.histof07";
	} // Fin de fournirCleNomNomenclatureHistoF07TypeComptage().________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le <code>nomNomenclatureHistoF07TypeComptage</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF07TypeComptageEnDur() {
		return "2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF07TypeComptageEnDur().______________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le TYPE DE COMPTAGE dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_TypeComptage_HistoF07_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF07TypeComptageUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF07TypeComptageUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF07TypeComptageUtf8 == null) {

				final Path pathRelatifNomenclatureTypeComptageHistoF07 
				= Paths.get(getNomNomenclatureHistoF07TypeComptage());
		
				final Path pathRelatifContextNomenclatureTypeComptageHistoF07 
					= getPathNomenclaturesHistoF07Utf8()
						.resolve(pathRelatifNomenclatureTypeComptageHistoF07);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureTypeComptageHistoF07.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureTypeComptageHistoF07);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF07TypeComptageUtf8 
					= new File(uriRessources.getPath());

				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF07TypeComptageUtf8
						, "Méthode getFichierNomenclatureHistoF07TypeComptageUtf8()");
			}
			
			return fichierNomenclatureHistoF07TypeComptageUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF07TypeComptageUtf8()._______________
	

	
	/**
	 * Getter du Nom du fichier de nomenclature du CLASSEMENT DE LA ROUTE 
	 * pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF07ClassementRouteEnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.classementroute.histof07".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07ClassementRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07ClassementRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF07ClassementRoute : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF07ClassementRoute() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF07ClassementRoute == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureHistoF07ClassementRoute());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CLASSEMENTROUTE,
								fournirCleNomNomenclatureHistoF07ClassementRoute(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureHistoF07ClassementRoute 
								= fournirNomNomenclatureHistoF07ClassementRouteEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureHistoF07ClassementRoute = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CLASSEMENTROUTE,
								fournirCleNomNomenclatureHistoF07ClassementRoute(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureHistoF07ClassementRoute 
							= fournirNomNomenclatureHistoF07ClassementRouteEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF07ClassementRoute 
						= fournirNomNomenclatureHistoF07ClassementRouteEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF07ClassementRoute == null)._________

			return nomNomenclatureHistoF07ClassementRoute;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF07ClassementRoute().____________________



	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 
	 * du CLASSEMENT DE LA ROUTE dans le HISTO_F07 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classementroute.histof07".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.histof07".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF07ClassementRoute() {
		return "application.repertoire.ressources.nomenclatures.classementroute.histof07";
	} // Fin de fournirCleNomNomenclatureHistoF07ClassementRoute()._____________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF07ClassementRoute</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF07ClassementRouteEnDur() {
		return "2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF07ClassementRouteEnDur().___________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le CLASSEMENT DE LA ROUTE dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClassementRoute_HistoF07_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF07ClassementRouteUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF07ClassementRouteUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF07ClassementRouteUtf8 == null) {

				final Path pathRelatifNomenclatureClassementRouteHistoF07 
				= Paths.get(getNomNomenclatureHistoF07ClassementRoute());
		
				final Path pathRelatifContextNomenclatureClassementRouteHistoF07 
					= getPathNomenclaturesHistoF07Utf8()
						.resolve(pathRelatifNomenclatureClassementRouteHistoF07);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureClassementRouteHistoF07.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureClassementRouteHistoF07);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF07ClassementRouteUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF07ClassementRouteUtf8
						, "Méthode getFichierNomenclatureHistoF07ClassementRouteUtf8()");
			}
			
			return fichierNomenclatureHistoF07ClassementRouteUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF07ClassementRouteUtf8()._______________
	

	
	/**
	 * Getter du Nom du fichier de nomenclature de la 
	 * CLASSE DE LARGEUR DE CHAUSSEE UNIQUE pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF07_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF07ClasseLargeurChausseeUEnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.histof07".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07ClasseLargeurChausseeUEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07ClasseLargeurChausseeUEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF07ClasseLargeurChausseeU : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF07ClasseLargeurChausseeU() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF07ClasseLargeurChausseeU == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureHistoF07ClasseLargeurChausseeU());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CLASSELARGEURCHAUSSEEU,
								fournirCleNomNomenclatureHistoF07ClasseLargeurChausseeU(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureHistoF07ClasseLargeurChausseeU 
								= fournirNomNomenclatureHistoF07ClasseLargeurChausseeUEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureHistoF07ClasseLargeurChausseeU 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CLASSELARGEURCHAUSSEEU,
								fournirCleNomNomenclatureHistoF07ClasseLargeurChausseeU(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureHistoF07ClasseLargeurChausseeU 
							= fournirNomNomenclatureHistoF07ClasseLargeurChausseeUEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF07ClasseLargeurChausseeU 
						= fournirNomNomenclatureHistoF07ClasseLargeurChausseeUEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF07ClasseLargeurChausseeU == null)._________

			return nomNomenclatureHistoF07ClasseLargeurChausseeU;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF07ClasseLargeurChausseeU()._____________



	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 
	 * de la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE
	 * dans le HISTO_F07 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.histof07".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.histof07".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF07ClasseLargeurChausseeU() {
		return "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.histof07";
	} // Fin de fournirCleNomNomenclatureHistoF07ClasseLargeurChausseeU().______
	
	
	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF07ClasseLargeurChausseeU</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF07_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF07ClasseLargeurChausseeUEnDur() {
		return "2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF07_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF07ClasseLargeurChausseeUEnDur().____
	
	
	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF07_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8 == null) {

				final Path pathRelatifNomenclatureClasseLargeurChausseeUHistoF07 
					= Paths.get(getNomNomenclatureHistoF07ClasseLargeurChausseeU());
		
				final Path pathRelatifContextNomenclatureClasseLargeurChausseeUHistoF07 
					= getPathNomenclaturesHistoF07Utf8()
						.resolve(pathRelatifNomenclatureClasseLargeurChausseeUHistoF07);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureClasseLargeurChausseeUHistoF07.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureClasseLargeurChausseeUHistoF07);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8
						, "Méthode getFichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8()");
			}
			
			return fichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF07ClasseLargeurChausseeUUtf8()._______________
	


	/**
	 * Getter du Nom du fichier de nomenclature de la 
	 * CLASSE DE LARGEUR DE CHAUSSEES SEPAREES pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF07_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF07ClasseLargeurChausseesSEnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.classelargeurchausseess.histof07".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07ClasseLargeurChausseesSEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07ClasseLargeurChausseesSEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF07ClasseLargeurChausseesS : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF07ClasseLargeurChausseesS() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF07ClasseLargeurChausseesS == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureHistoF07ClasseLargeurChausseesS());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CLASSELARGEURCHAUSSEESS,
								fournirCleNomNomenclatureHistoF07ClasseLargeurChausseesS(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureHistoF07ClasseLargeurChausseesS 
								= fournirNomNomenclatureHistoF07ClasseLargeurChausseesSEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureHistoF07ClasseLargeurChausseesS 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CLASSELARGEURCHAUSSEESS,
								fournirCleNomNomenclatureHistoF07ClasseLargeurChausseesS(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureHistoF07ClasseLargeurChausseesS 
							= fournirNomNomenclatureHistoF07ClasseLargeurChausseesSEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF07ClasseLargeurChausseesS 
						= fournirNomNomenclatureHistoF07ClasseLargeurChausseesSEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF07ClasseLargeurChausseesS == null)._________

			return nomNomenclatureHistoF07ClasseLargeurChausseesS;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF07ClasseLargeurChausseesS()._______


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 
	 * de la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES
	 * dans le HISTO_F07 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseess.histof07".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.classelargeurchausseess.histof07".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF07ClasseLargeurChausseesS() {
		return "application.repertoire.ressources.nomenclatures.classelargeurchausseess.histof07";
	} // Fin de fournirCleNomNomenclatureHistoF07ClasseLargeurChausseesS().
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF07ClasseLargeurChausseesS</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF07_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF07ClasseLargeurChausseesSEnDur() {
		return "2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF07_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF07ClasseLargeurChausseesSEnDur().___


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF07_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8 == null) {

				final Path pathRelatifNomenclatureClasseLargeurChausseesSHistoF07 
					= Paths.get(getNomNomenclatureHistoF07ClasseLargeurChausseesS());
		
				final Path pathRelatifContextNomenclatureClasseLargeurChausseesSHistoF07 
					= getPathNomenclaturesHistoF07Utf8()
						.resolve(pathRelatifNomenclatureClasseLargeurChausseesSHistoF07);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureClasseLargeurChausseesSHistoF07.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureClasseLargeurChausseesSHistoF07);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8 
					= new File(uriRessources.getPath());
																
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8
						, "Méthode getFichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8()");
			}
			
			return fichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF07ClasseLargeurChausseesSUtf8().____
	


	/**
	 * Getter du Nom du fichier de nomenclature du TYPE DE RESEAU
	 * pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_TypeReseau_HistoF07_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF07TypeReseauEnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.typereseau.histof07".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07TypeReseauEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07TypeReseauEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF07TypeReseau : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF07TypeReseau() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF07TypeReseau == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureHistoF07TypeReseau());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_TYPERESEAU,
								fournirCleNomNomenclatureHistoF07TypeReseau(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureHistoF07TypeReseau 
								= fournirNomNomenclatureHistoF07TypeReseauEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureHistoF07TypeReseau 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_TYPERESEAU,
								fournirCleNomNomenclatureHistoF07TypeReseau(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureHistoF07TypeReseau 
							= fournirNomNomenclatureHistoF07TypeReseauEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF07TypeReseau 
						= fournirNomNomenclatureHistoF07TypeReseauEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF07TypeReseau == null)._________

			return nomNomenclatureHistoF07TypeReseau;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF07TypeReseau().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du TYPE DE RESEAU
	 * dans le HISTO_F07 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_TypeReseau_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typereseau.histof07".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typereseau.histof07".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF07TypeReseau() {
		return "application.repertoire.ressources.nomenclatures.typereseau.histof07";
	} // Fin de fournirCleNomNomenclatureHistoF07TypeReseau().__________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF07TypeReseau</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_TypeReseau_HistoF07_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF07TypeReseauEnDur() {
		return "2014-07-15_Nomenclature_TypeReseau_HistoF07_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF07TypeReseauEnDur().________________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le TYPE DE RESEAU 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_TypeReseau_HistoF07_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF07TypeReseauUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF07TypeReseauUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF07TypeReseauUtf8 == null) {

				final Path pathRelatifNomenclatureTypeReseauHistoF07 
				= Paths.get(getNomNomenclatureHistoF07TypeReseau());
		
				final Path pathRelatifContextNomenclatureTypeReseauHistoF07 
					= getPathNomenclaturesHistoF07Utf8()
						.resolve(pathRelatifNomenclatureTypeReseauHistoF07);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureTypeReseauHistoF07.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureTypeReseauHistoF07);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF07TypeReseauUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF07TypeReseauUtf8
						, "Méthode getFichierNomenclatureHistoF07TypeReseauUtf8()");
			}
			
			return fichierNomenclatureHistoF07TypeReseauUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF07TypeReseauUtf8()._______________
	


	/**
	 * Getter du Nom du fichier de nomenclature du CODE CONCESSION
	 * pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_CodeConcession_HistoF07_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF07CodeConcessionEnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.codeconcession.histof07".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07CodeConcessionEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07CodeConcessionEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF07CodeConcession : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF07CodeConcession() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF07CodeConcession == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureHistoF07CodeConcession());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CODECONCESSION,
								fournirCleNomNomenclatureHistoF07CodeConcession(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureHistoF07CodeConcession 
								= fournirNomNomenclatureHistoF07CodeConcessionEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureHistoF07CodeConcession 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_CODECONCESSION,
								fournirCleNomNomenclatureHistoF07CodeConcession(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureHistoF07CodeConcession 
							= fournirNomNomenclatureHistoF07CodeConcessionEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF07CodeConcession 
						= fournirNomNomenclatureHistoF07CodeConcessionEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF07CodeConcession == null)._________

			return nomNomenclatureHistoF07CodeConcession;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF07CodeConcession().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du CODE CONCESSION
	 * dans le HISTO_F07 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_CodeConcession_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.codeconcession.histof07".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.codeconcession.histof07".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF07CodeConcession() {
		return "application.repertoire.ressources.nomenclatures.codeconcession.histof07";
	} // Fin de fournirCleNomNomenclatureHistoF07CodeConcession().__________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF07CodeConcession</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_CodeConcession_HistoF07_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF07CodeConcessionEnDur() {
		return "2014-07-15_Nomenclature_CodeConcession_HistoF07_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF07CodeConcessionEnDur().________________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le CODE CONCESSION 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_CodeConcession_HistoF07_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF07CodeConcessionUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF07CodeConcessionUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF07CodeConcessionUtf8 == null) {

				final Path pathRelatifNomenclatureCodeConcessionHistoF07 
				= Paths.get(getNomNomenclatureHistoF07CodeConcession());
		
				final Path pathRelatifContextNomenclatureCodeConcessionHistoF07 
					= getPathNomenclaturesHistoF07Utf8()
						.resolve(pathRelatifNomenclatureCodeConcessionHistoF07);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureCodeConcessionHistoF07.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureCodeConcessionHistoF07);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF07CodeConcessionUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF07CodeConcessionUtf8
						, "Méthode getFichierNomenclatureHistoF07CodeConcessionUtf8()");
			}
			
			return fichierNomenclatureHistoF07CodeConcessionUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF07CodeConcessionUtf8().________
	


	/**
	 * Getter du Nom du fichier de nomenclature du PROFIL EN TRAVERS
	 * pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ProfilTravers_HistoF07_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF07ProfilTraversEnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.profiltravers.histof07".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07ProfilTraversEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07ProfilTraversEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF07ProfilTravers : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF07ProfilTravers() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF07ProfilTravers == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureHistoF07ProfilTravers());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_PROFILTRAVERS,
								fournirCleNomNomenclatureHistoF07ProfilTravers(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureHistoF07ProfilTravers 
								= fournirNomNomenclatureHistoF07ProfilTraversEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureHistoF07ProfilTravers 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_PROFILTRAVERS,
								fournirCleNomNomenclatureHistoF07ProfilTravers(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureHistoF07ProfilTravers 
							= fournirNomNomenclatureHistoF07ProfilTraversEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF07ProfilTravers 
						= fournirNomNomenclatureHistoF07ProfilTraversEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF07ProfilTravers == null)._________

			return nomNomenclatureHistoF07ProfilTravers;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF07ProfilTravers().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du PROFIL EN TRAVERS
	 * dans le HISTO_F07 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_ProfilTravers_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.profiltravers.histof07".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.profiltravers.histof07".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF07ProfilTravers() {
		return "application.repertoire.ressources.nomenclatures.profiltravers.histof07";
	} // Fin de fournirCleNomNomenclatureHistoF07ProfilTravers().__________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF07ProfilTravers</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_ProfilTravers_HistoF07_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF07ProfilTraversEnDur() {
		return "2014-07-15_Nomenclature_ProfilTravers_HistoF07_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF07ProfilTraversEnDur().________________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le PROFIL EN TRAVERS 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ProfilTravers_HistoF07_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF07ProfilTraversUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF07ProfilTraversUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF07ProfilTraversUtf8 == null) {

				final Path pathRelatifNomenclatureProfilTraversHistoF07 
				= Paths.get(getNomNomenclatureHistoF07ProfilTravers());
		
				final Path pathRelatifContextNomenclatureProfilTraversHistoF07 
					= getPathNomenclaturesHistoF07Utf8()
						.resolve(pathRelatifNomenclatureProfilTraversHistoF07);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureProfilTraversHistoF07.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureProfilTraversHistoF07);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF07ProfilTraversUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF07ProfilTraversUtf8
						, "Méthode getFichierNomenclatureHistoF07ProfilTraversUtf8()");
			}
			
			return fichierNomenclatureHistoF07ProfilTraversUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF07ProfilTraversUtf8().________
	


	/**
	 * Getter du Nom du fichier de nomenclature du SOUS RESEAU INDICE
	 * pour les HISTO_F07 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_SousReseauIndice_HistoF07_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF07SousReseauIndiceEnDur().</li>
	 * <li>Nettoie la valeur lue dans le .properties avec trim().</li>
	 * <li>fabrique un <code>messageIndividuelRapport</code> 
	 * si la clé ou la valeur sont manquantes dans 
	 * <code>application.properties</code>. <br/>
	 * <code>messageIndividuelRapport</code> est null sinon.</li>
	 * <li>ajoute le messageIndividuelRapport à 
	 * <code>rapportConfigurationCsv</code> le cas échéant.<br/> 
	 * <code>rapportConfigurationCsv</code> contient les éventuels 
	 * messages d'erreur de configuration de toutes 
	 * les méthodes de la présente classe.
	 * <br/><code>rapportConfigurationCsv</code> est null 
	 * si il n'y a aucune erreur de configuration.</li>
	 * </ul>
	 * Clé : "application.repertoire.ressources.nomenclatures.sousreseauindice.histof07".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07SousReseauIndiceEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF07SousReseauIndiceEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF07SousReseauIndice : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF07SousReseauIndice() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF07SousReseauIndice == null) {

				if (ConfigurationApplicationManager
						.getBundleApplication() != null) {

					try {

						/*
						 * Essaie de récupérer la valeur dans le properties.
						 */
						final String valeur 
							= ConfigurationApplicationManager
								.getBundleApplication()
									.getString(
										fournirCleNomNomenclatureHistoF07SousReseauIndice());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_SOUSRESEAUINDICE,
								fournirCleNomNomenclatureHistoF07SousReseauIndice(),
								ConfigurationApplicationManager
										.getBundleApplication());

							/* LOG.ERROR. */
							if (LOG.isErrorEnabled()) {
								LOG.error(messageIndividuelRapport);
							}

							/* Rapport. */
							ajouterMessageAuRapportConfigurationCsv(
									messageIndividuelRapport);

							/* utilise la valeur fournie en dur. */
							nomNomenclatureHistoF07SousReseauIndice 
								= fournirNomNomenclatureHistoF07SousReseauIndiceEnDur();

						} // Fin de Si la valeur est blank._________

						/* Valeur remplie dans le properties. */
						else {

							/*
							 * Nettoie la valeur lue dans le .properties avec
							 * trim().
							 */
							final String valeurNettoyee 
								= StringUtils
									.trim(valeur);

							nomNomenclatureHistoF07SousReseauIndice 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F07_SOUSRESEAUINDICE,
								fournirCleNomNomenclatureHistoF07SousReseauIndice(),
								ConfigurationApplicationManager
										.getBundleApplication());

						/* LOG.ERROR. */
						if (LOG.isErrorEnabled()) {
							LOG.error(messageIndividuelRapport, mre);
						}

						/* Rapport. */
						ajouterMessageAuRapportConfigurationCsv(
								messageIndividuelRapport);

						/* utilise la valeur fournie en dur. */
						nomNomenclatureHistoF07SousReseauIndice 
							= fournirNomNomenclatureHistoF07SousReseauIndiceEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF07SousReseauIndice 
						= fournirNomNomenclatureHistoF07SousReseauIndiceEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF07SousReseauIndice == null)._________

			return nomNomenclatureHistoF07SousReseauIndice;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF07SousReseauIndice().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du SOUS RESEAU INDICE
	 * dans le HISTO_F07 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_SousReseauIndice_HistoF07_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sousreseauindice.histof07".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.sousreseauindice.histof07".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF07SousReseauIndice() {
		return "application.repertoire.ressources.nomenclatures.sousreseauindice.histof07";
	} // Fin de fournirCleNomNomenclatureHistoF07SousReseauIndice().__________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF07SousReseauIndice</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_SousReseauIndice_HistoF07_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF07SousReseauIndiceEnDur() {
		return "2014-07-15_Nomenclature_SousReseauIndice_HistoF07_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF07SousReseauIndiceEnDur().________________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le SOUS RESEAU INDICE 
	 * dans un HISTO_F07.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF07/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_SousReseauIndice_HistoF07_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF07SousReseauIndiceUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF07SousReseauIndiceUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF07SousReseauIndiceUtf8 == null) {

				final Path pathRelatifNomenclatureSousReseauIndiceHistoF07 
				= Paths.get(getNomNomenclatureHistoF07SousReseauIndice());
		
				final Path pathRelatifContextNomenclatureSousReseauIndiceHistoF07 
					= getPathNomenclaturesHistoF07Utf8()
						.resolve(pathRelatifNomenclatureSousReseauIndiceHistoF07);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureSousReseauIndiceHistoF07.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureSousReseauIndiceHistoF07);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF07SousReseauIndiceUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF07SousReseauIndiceUtf8
						, "Méthode getFichierNomenclatureHistoF07SousReseauIndiceUtf8()");
			}
			
			return fichierNomenclatureHistoF07SousReseauIndiceUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF07SousReseauIndiceUtf8().________
	


	/**
	 * method getRapportConfigurationCsv() :<br/>
	 * Getter du Rapport du chargement de la configuration au format csv.<br/>
	 * <br/>
	 * - Le rapport est null si il n'y a eu aucun 
	 * problème d'initialisation de l'application.<br/>
	 * <br/>
	 *
	 * @return rapportConfigurationCsv : String.<br/>
	 */
	public static String getRapportConfigurationCsv() {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			return rapportConfigurationCsv;
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de getRapportConfigurationCsv().______________________________


	
	/**
	 * method getMessageIndividuelRapport() :<br/>
	 * Getter du Message pour le 
	 * Rapport du chargement de la configuration au format csv 
	 * généré par chaque méthode individuellement.<br/>
	 * <br/>
	 *
	 * @return messageIndividuelRapport : String.<br/>
	 */
	public static String getMessageIndividuelRapport() {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			return messageIndividuelRapport;
		} // Fin de synchronized.________________________________________
		
	} // Fin de getMessageIndividuelRapport()._____________________________


	
	/**
	 * method creerMessageManqueCle(
	 * String pMethode
	 * , String pCle
	 * , ResourceBundle pBundle) :<br/>
	 * Crée un message pour le LOG et le rapport de configuration csv 
	 * si une clé est absente dans un ResourceBundle.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * "Classe ConfigurationApplicationManager 
	 * - Méthode getStatsActivees() 
	 * - La clé 'abstractdao.statsactivees' 
	 * n'existe pas dans messagestechniquesfr_FR.properties".<br/>
	 * <br/>
	 *
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * @param pCle : String : Clé dans le ResourceBundle.<br/>
	 * @param pBundle : ResourceBundle.<br/>
	 * 
	 * @return : String : message pour le LOG 
	 * et le rapport de configuration csv.<br/>
	 */
	private static String creerMessageManqueCle(
			final String pMethode
			 	, final String pCle
			 		, final ResourceBundle pBundle) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESHISTO_F07);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMethode);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append("La clé '");
			stb.append(pCle);
			stb.append("' n'existe pas dans ");
			stb.append(pBundle.getBaseBundleName());
			stb.append("fr_FR.properties");
			
			return stb.toString();
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de creerMessageManqueCle(
	 // String pMethode
	 // , String pCle
	 // , ResourceBundle pBundle)._________________________________________
	

	
	/**
	 * method creerMessageManqueValeur(
	 * String pMethode
	 * , String pCle
	 * , ResourceBundle pBundle) :<br/>
	 * Crée un message pour le LOG et le rapport de configuration csv 
	 * si une valeur en face d'une clé est absente 
	 * dans un ResourceBundle.<br/>
	 * <br/>
	 *
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * @param pCle : String : Clé dans le ResourceBundle.<br/>
	 * @param pBundle : ResourceBundle.<br/>
	 * 
	 * @return : String : message pour le LOG 
	 * et le rapport de configuration csv.<br/>
	 */
	private static String creerMessageManqueValeur(
			final String pMethode
			 	, final String pCle
			 		, final ResourceBundle pBundle) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESHISTO_F07);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMethode);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append("La valeur associée à la clé '");
			stb.append(pCle);
			stb.append("' n'existe pas (null ou vide) dans ");
			stb.append(pBundle.getBaseBundleName());
			stb.append("fr_FR.properties");
			
			return stb.toString();
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de creerMessageManqueValeur(
	 // String pMethode
	 // , String pCle
	 // , ResourceBundle pBundle)._________________________________________


	
	/**
	 * method ajouterMessageAuRapportConfigurationCsv(
	 * String pMessage) :<br/>
	 * Rajoute le message pMessage au rapport 
	 * de chargement de la configuration au format csv (à la ligne).<br/>
	 * <br/>
	 * - Ne fait rien si pMessage est blank.<br/>
	 * - Ne Rajoute PAS l'en-tête (avec BOM_UTF-8) 
	 * pour le rapport de chargement de la configuration si nécessaire.<br/>
	 * <br/>
	 *
	 * @param pMessage : String : Message à rajouter 
	 * au rapport de chargement de la configuration.<br/>
	 */
	private static void ajouterMessageAuRapportConfigurationCsv(
			final String pMessage) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			/* Ne fait rien si pMessage est blank. */
			if (StringUtils.isBlank(pMessage)) {
				return;
			}
			
			final StringBuilder stb = new StringBuilder();
						
			/* Rajoute le message au rapport de 
			 * chargement de la configuration au format csv (à la ligne). */
			if (!StringUtils.isBlank(rapportConfigurationCsv)) {
				stb.append(rapportConfigurationCsv);
				stb.append(NEWLINE);
			}
			
			stb.append(pMessage);
			stb.append(SEP_PV);
			
			rapportConfigurationCsv = stb.toString();
			
		} // Fin de synchronized.________________________________________
			
	} // Fin de ajouterMessageAuRapportConfigurationCsv(
	 // String pMessage).__________________________________________________
	

	
	/**
	 * method traiterFichier(
	 * File pFile
	 * , String pMethode) :<br/>
	 * LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 * <ul>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierNullRunTimeException si pFile est null.</li><br/>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierInexistantRunTimeException si pFile est inexistant.</li><br/>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierRepertoireRunTimeException si pFile est un répertoire.</li><br/>
	 * <li>LOG.FATAL, rapporte et jette 
	 * une FichierVideRunTimeException si pFile est vide.</li><br/>
	 * </ul>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : Nom de la méthode appelante.<br/>
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	private static void traiterFichier(
			final File pFile
				, final String pMethode) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;
			
			/* si pFile est null.*******/
			if (pFile == null) {
				
				messageIndividuelRapport 
				= creerMessageMauvaisFichier(
						pMethode
						, "Le Fichier passé en paramètre est null"); 

				/* LOG.FATAL. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(messageIndividuelRapport);
				}
				
				/* Rapport. */
				ajouterMessageAuRapportConfigurationCsv(
						messageIndividuelRapport);
				
				/* Jette une FichierNullRunTimeException. */
				throw new FichierNullRunTimeException(
						messageIndividuelRapport);
				
			} // Fin de if (pFile == null).__________

			
			/* si pFile est inexistant. *******************/
			if (!pFile.exists()) {
				
				messageIndividuelRapport 
				= creerMessageMauvaisFichier(
						pMethode
						, "Le Fichier passé en paramètre est inexistant : " 
						+ pFile.getAbsolutePath() 
						+ " - Ce fichier est INDISPENSABLE à l'application");
				
				/* LOG.FATAL. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(messageIndividuelRapport);
				}
				
				/* Rapport. */
				ajouterMessageAuRapportConfigurationCsv(
						messageIndividuelRapport);
				
				/* Jette une FichierInexistantRunTimeException. */
				throw new FichierInexistantRunTimeException(
						messageIndividuelRapport);
				
			} // Fin de if (!pFile.exists()).____________

			
			/* si pFile est un répertoire. *******************/
			if (pFile.isDirectory()) {
				
				messageIndividuelRapport 
				= creerMessageMauvaisFichier(
						pMethode
						, "Le Fichier passé en paramètre est un répertoire : " 
						+ pFile.getAbsolutePath() 
						+ " - Ce fichier est INDISPENSABLE à l'application");
				
				/* LOG.FATAL. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(messageIndividuelRapport);
				}
				
				/* Rapport. */
				ajouterMessageAuRapportConfigurationCsv(
						messageIndividuelRapport);
				
				/* Jette une FichierRepertoireRunTimeException. */
				throw new FichierRepertoireRunTimeException(
						messageIndividuelRapport);
				
			} // Fin de if (pFile.isDirectory()).__________

			
			/* si pFile est vide. ***********************/
			if (pFile.length() == 0) {
				
				messageIndividuelRapport 
				= creerMessageMauvaisFichier(
						pMethode
						, "Le Fichier passé en paramètre est vide : " 
						+ pFile.getAbsolutePath() 
						+ " - Ce fichier est INDISPENSABLE à l'application");
				
				/* LOG.FATAL. */
				if (LOG.isFatalEnabled()) {
					LOG.fatal(messageIndividuelRapport);
				}
				
				/* Rapport. */
				ajouterMessageAuRapportConfigurationCsv(
						messageIndividuelRapport);
				
				/* Jette une FichierVideRunTimeException. */
				throw new FichierVideRunTimeException(
						messageIndividuelRapport);
				
			} // Fin de if (pFile.length() == 0)._________________
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de traiterFichier(
	 // File pFile
	 // , String pMethode).________________________________________________
	

	
	/**
	 * method creerMessageMauvaisFichier(
	 * String pMethode
	 * , String pMessage) :<br/>
	 * Crée un message pour le LOG et le rapport de configuration csv 
	 * si un Fichier de ressources (Description de fichier, nomenclature, ...) 
	 * est introuvable.<br/>
	 * <br/>
	 *
	 * @param pMethode : String : nom de la méthode appelante.<br/>
	 * @param pMessage : String : message ciconstancié 
	 * de la méthode appelante.<br/>
	 * 
	 * @return : String : message pour le LOG 
	 * et le rapport de configuration csv.<br/>
	 */
	private static String creerMessageMauvaisFichier(
				final String pMethode
					, final String pMessage) {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESHISTO_F07);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMethode);
			stb.append(SEPARATEUR_MOINS_AERE);
			stb.append(pMessage);
			
			return stb.toString();
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de creerMessageMauvaisFichier(
	 // String pMethode
	// , String pMessage)._________________________________________________
	
	
	
	/**
	 * Log Fatal et jette une FichierInexistantRunTimeException 
	 * si le fichier ressource de NOMENCLATURE 
	 * situé à pPathRelatifContextNomenclature est manquant
	 * , ce qui annule pUrlRessources.<br/>
	 * <br/>
	 *
	 * @param pUrlRessources : URL
	 * @param pPathRelatifContextNomenclature : Path
	 * 
	 * @throws FichierInexistantRunTimeException
	 */
	private static void traiterRessourceManquante(
			final URL pUrlRessources
				, final Path pPathRelatifContextNomenclature) 
						throws FichierInexistantRunTimeException {
		
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			if (pUrlRessources == null) {
				
				final String message 
				= "le fichier ressource de NOMENCLATURE : " 
				+ pPathRelatifContextNomenclature.toString() 
				+ " est MANQUANT";
				
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message);
				}
				
				throw new FichierInexistantRunTimeException(message);
			}
		} // Fin de synchronized._________________________
		
	} // Fin de traiterRessourceManquante(...).____________________________
	

	
	/**
	 * retourne le <b>chemin sous forme de String 
	 * du répertoire ressources dans le classpath</b> sous target/classes.<br/>
	 * "D:\Donnees\eclipse\eclipseworkspace\traficweb_v1\
	 * target\classes\ressources"<br/>
	 *
	 * @return : String : 
	 * chemin sous forme de String du répertoire 
	 * ressources dans le classpath.<br/>
	 * 
	 * @throws URISyntaxException 
	 */
	public static String retournerRessourcesSousTargetClasses() 
			throws URISyntaxException {
		
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			final ClassLoader classloader 
			= Thread.currentThread().getContextClassLoader();
		
			final URL urlRessources 
				= classloader
					.getResource("ressources");
			
			final URI uriRessources = urlRessources.toURI();
					
			final String uriRessourcesString = uriRessources.getPath();
			
			final File ressourcesFile = new File(uriRessourcesString);
			
			final String pathRessourcesString 
				= ressourcesFile.getAbsolutePath();
			
			return pathRessourcesString;

		} // Fin de synchronized.________________________________________

	} // Fin de retournerRessourcesSousTargetClasses().____________________
	

	
	/**
	 * retourne le <b>chemin sous forme de String 
	 * du répertoire classes dans le classpath</b> sous target.<br/>
	 * "D:\Donnees\eclipse\eclipseworkspace\traficweb_v1\
	 * target\classes"<br/>
	 *
	 * @return : String : 
	 * chemin sous forme de String du répertoire 
	 * classes dans le classpath.<br/>
	 * 
	 * @throws URISyntaxException
	 */
	public static String retournerClassesSousTarget() 
			throws URISyntaxException {
		
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			final String pathRessourcesString 
				= retournerRessourcesSousTargetClasses();
			
			final Path pathRessources = Paths.get(pathRessourcesString);
			
			final Path pathClasses = pathRessources.getParent();
			
			String pathClassesString = null;
			
			if (pathClasses != null) {
				pathClassesString = pathClasses.toString();
			}
					
			return pathClassesString;
			
		} // Fin de synchronized.________________________________________

	} // Fin de retournerClassesSousTarget().______________________________
	
	

	/**
	 * retourne le 
	 * <b>path relatif de pFile par rapport à target/classes</b> (contexte).<br/>
	 * <ul>
	 * <li>Par exemple :<br/>
	 * <code>fournirPathRelatifSousTargetClasses(fichierDescriptionHit)</code> 
	 * retourne 
	 * 'ressources/Descriptions de fichier/Hit/Descriptions en UTF-8/2014-07-19_Description_HIT_Utf8.csv'
	 * </li>
	 * </ul>
	 *
	 * @param pFile : File : ressource dans le classpath.<br/>
	 * 
	 * @return Path : path relatif de pFile par rapport à target/classes.<br/>
	 * 
	 * @throws URISyntaxException
	 */
	public static Path fournirPathRelatifSousTargetClasses(final File pFile) 
						throws URISyntaxException {
		
		synchronized (ConfigurationNomenclaturesHistoF07Manager.class) {
			
			final String pathClassesString 
			= ConfigurationNomenclaturesHistoF07Manager
				.retournerClassesSousTarget();
		
			final Path pathClasses = Paths.get(pathClassesString);
			
			final Path pathPFile = pFile.toPath();
			
			final Path pathRelatifPFile = pathClasses.relativize(pathPFile);
			
			return pathRelatifPFile;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de fournirPathRelatifSousTargetClasses(...).__________________	
	

	
} // FIN DE LA CLASSE ConfigurationNomenclaturesHistoF07Manager.-------------
