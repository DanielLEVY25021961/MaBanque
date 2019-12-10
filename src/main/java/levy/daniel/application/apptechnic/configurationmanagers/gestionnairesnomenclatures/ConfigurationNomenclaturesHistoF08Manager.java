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
 * CLASSE ConfigurationNomenclaturesHistoF08Manager :<br/>
 * Classe UTILITAIRE 
 * chargée de gérer la configuration des 
 * <b>NOMENCLATURES DE TOUS LES CHAMPS A NOMENCLATURE DU FICHIER HISTO_F08</b>.<br/>
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
 * <li>La méthode <code>getCheminNomenclaturesHistoF08Utf8()</code> 
 * fournit un Singleton 
 * du chemin du répertoire parent contenant les nomenclatures 
 * encodées en UTF-8 des champs à nomenclature du fichier HISTO_F08.<br/>
 * Elle retourne en principe 
 * 'ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8'.</li>
 * <li>Les méthodes getNomNomenclatureXXX fournissent un singleton  
 * du nom du fichier de nomenclature du champXXX 
 * encodé en UTF-8 dans le HISTO_F08.</li>
 * <li>Les méthodes getFichierNomenclatureXXX fournissent un singleton  
 * du fichier de nomenclature du champXXX 
 * encodé en UTF-8 dans le HISTO_F08.</li>
 * </ul>
 *  
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ConfigurationNomenclaturesHistoF08Manager : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../javadoc/images/apptechnic/configurationmanagers/gestionnairesnomenclatures/classe_GestionnaireNomenclaturesHistoF08Manager.png" 
 * alt="Diagramme de classe du ConfigurationNomenclaturesHistoF08Manager" />
 * </p>
 * 
 * <br/>
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <code> // Récupération de la nomenclature du SENS sous forme de File.</code><br/>
 * <code><b>File fichierNomenclatureHistoF08Sens = ConfigurationNomenclaturesHistoF08Manager.getFichierNomenclatureHistoF08SensUtf8();</b></code><br/>
 * <code> // Récupération de l'éventuel message d'erreur de la méthode (null si OK)</code><br/>
 * <code><b>String messageIndividuelRapport = ConfigurationNomenclaturesHistoF08Manager.getMessageIndividuelRapport();</b></code><br/>
 * <code> // Récupération de l'éventuel message d'erreur de la classe pour l'ensemble des méthodes (null si OK)</code><br/>
 * <code><b>String rapportConfigurationCsv = ConfigurationNomenclaturesHistoF08Manager.getRapportConfigurationCsv();</b></code><br/>
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
public final class ConfigurationNomenclaturesHistoF08Manager {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe ConfigurationNomenclaturesHistoF08Manager".<br/>
	 */
	public static final String CLASSE_CONFIGURATIONNOMENCLATURESHISTO_F08 
		= "Classe ConfigurationNomenclaturesHistoF08Manager";
		
	/**
	 * "Méthode getCheminNomenclaturesHistoF08Utf8".<br/>
	 */
	public static final String METHODE_GET_CHEMINNOMENCLATURES_HISTO_F08 
		= "Méthode getCheminNomenclaturesHistoF08Utf8";
	
	/**
	 * "Méthode getNomNomenclatureHistoF08Sens()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F08_SENS 
		= "Méthode getNomNomenclatureHistoF08Sens()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF08Nature()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F08_NATURE 
		= "Méthode getNomNomenclatureHistoF08Nature()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF08CatAdminRoute()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CATADMINROUTE 
		= "Méthode getNomNomenclatureHistoF08CatAdminRoute()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF08TypeComptage()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F08_TYPECOMPTAGE 
		= "Méthode getNomNomenclatureHistoF08TypeComptage()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF08ClassementRoute()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CLASSEMENTROUTE 
		= "Méthode getNomNomenclatureHistoF08ClassementRoute()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF08ClasseLargeurChausseeU()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CLASSELARGEURCHAUSSEEU 
		= "Méthode getNomNomenclatureHistoF08ClasseLargeurChausseeU()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF08ClasseLargeurChausseesS()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CLASSELARGEURCHAUSSEESS 
		= "Méthode getNomNomenclatureHistoF08ClasseLargeurChausseesS()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF08TypeReseau()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F08_TYPERESEAU 
		= "Méthode getNomNomenclatureHistoF08TypeReseau()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF08CodeConcession()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CODECONCESSION 
		= "Méthode getNomNomenclatureHistoF08CodeConcession()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF08ProfilTravers()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F08_PROFILTRAVERS 
		= "Méthode getNomNomenclatureHistoF08ProfilTravers()";
	
	/**
	 * "Méthode getNomNomenclatureHistoF08SousReseauIndice()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HISTO_F08_SOUSRESEAUINDICE 
		= "Méthode getNomNomenclatureHistoF08SousReseauIndice()";
	
	
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
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");

	
	// ******************************************************************
	// NOMENCLATURES.****************************************************
	// ******************************************************************
		
	// HISTO_F08.*********************
	
	/**
	 * <b>Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.histof08.utf8".<br/>
	 */
	private static transient String cheminNomenclaturesHistoF08Utf8;

	/**
	 * <b>Path relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.histof08.utf8".<br/>
	 */
	private static transient Path pathNomenclaturesHistoF08Utf8;
	
	/**
	 * Nom du fichier de nomenclature du SENS pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sens.histof08".<br/>
	 */
	private static transient String nomNomenclatureHistoF08Sens;
	
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le SENS dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF08SensUtf8;
	
	/**
	 * Nom du fichier de nomenclature de la NATURE DU COMPTAGE 
	 * pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.nature.histof08".<br/>
	 */
	private static transient String nomNomenclatureHistoF08Nature;
	
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la
	 * Nomenclature pour la NATURE du comptage 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF08NatureUtf8;
	
	/**
	 * Nom du fichier de nomenclature de la CATEGORIE ADMINISTRATIVE 
	 * de la route pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.catadminroute.histof08"<br/>	 
	 * */
	private static transient String nomNomenclatureHistoF08CatAdminRoute;
		
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CATEGORIE ADMINISTRATIVE de la route 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF08CatAdminRouteUtf8;
	
	/**
	 * Nom du fichier de nomenclature du TYPE DE COMPTAGE
	 * pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typecomptage.histof08"<br/>
	 */
	private static transient String nomNomenclatureHistoF08TypeComptage;
		
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le TYPE DE COMPTAGE dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF08TypeComptageUtf8;
	
	/**
	 * Nom du fichier de nomenclature du CLASSEMENT DE LA ROUTE 
	 * pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classementroute.histof08"<br/>
	 */
	private static transient String nomNomenclatureHistoF08ClassementRoute;
		
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le CLASSEMENT DE LA ROUTE dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF08ClassementRouteUtf8;
	
	/**
	 * Nom du fichier de nomenclature de la 
	 * CLASSE DE LARGEUR DE CHAUSSEE UNIQUE pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.histof08"<br/>
	 */
	private static transient String nomNomenclatureHistoF08ClasseLargeurChausseeU;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8;
	
	/**
	 * Nom du fichier de nomenclature de la 
	 * CLASSE DE LARGEUR DE CHAUSSEES SEPAREES pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseess.histof08"<br/>
	 */
	private static transient String nomNomenclatureHistoF08ClasseLargeurChausseesS;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8;
	
	/**
	 * Nom du fichier de nomenclature du TYPE DE RESEAU
	 * pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typereseau.histof08"<br/>
	 */
	private static transient String nomNomenclatureHistoF08TypeReseau;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le TYPE DE RESEAU 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF08TypeReseauUtf8;
	
	/**
	 * Nom du fichier de nomenclature du CODE CONCESSION
	 * pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.codeconcession.histof08"<br/>
	 */
	private static transient String nomNomenclatureHistoF08CodeConcession;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le CODE CONCESSION 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF08CodeConcessionUtf8;
	
	/**
	 * Nom du fichier de nomenclature du PROFIL EN TRAVERS
	 * pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.profiltravers.histof08"<br/>
	 */
	private static transient String nomNomenclatureHistoF08ProfilTravers;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le PROFIL EN TRAVERS 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF08ProfilTraversUtf8;
	
	/**
	 * Nom du fichier de nomenclature du SOUS RESEAU INDICE
	 * pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sousreseauindice.histof08"<br/>
	 */
	private static transient String nomNomenclatureHistoF08SousReseauIndice;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le SOUS RESEAU INDICE 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHistoF08SousReseauIndiceUtf8;
	

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
			.getLog(ConfigurationNomenclaturesHistoF08Manager.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour interdire l'instanciation.<br/>
	 */
	private ConfigurationNomenclaturesHistoF08Manager() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________

	

	/**
	 * Getter du <b>Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirCheminNomenclaturesHistoF08Utf8EnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.chemin.histof08.utf8".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminNomenclaturesHistoF08Utf8EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminNomenclaturesHistoF08Utf8EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return cheminNomenclaturesHistoF08Utf8 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getCheminNomenclaturesHistoF08Utf8() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (cheminNomenclaturesHistoF08Utf8 == null) {

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
										fournirCleCheminNomenclaturesHistoF08Utf8());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_CHEMINNOMENCLATURES_HISTO_F08,
								fournirCleCheminNomenclaturesHistoF08Utf8(),
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
							cheminNomenclaturesHistoF08Utf8 
								= fournirCheminNomenclaturesHistoF08Utf8EnDur();

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

							cheminNomenclaturesHistoF08Utf8 = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_CHEMINNOMENCLATURES_HISTO_F08,
								fournirCleCheminNomenclaturesHistoF08Utf8(),
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
						cheminNomenclaturesHistoF08Utf8 
							= fournirCheminNomenclaturesHistoF08Utf8EnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					cheminNomenclaturesHistoF08Utf8 
						= fournirCheminNomenclaturesHistoF08Utf8EnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (cheminNomenclaturesHistoF08Utf8 == null)._________

			return cheminNomenclaturesHistoF08Utf8;

		} // Fin de synchronized.________________________________________

	} // Fin de getCheminNomenclaturesHistoF08Utf8().______________________
	

	
	/**
	 * Getter du <b>Path relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.histof08.utf8".<br/>
	 *
	 * @return : Path.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Path getPathNomenclaturesHistoF08Utf8() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			return Paths.get(getCheminNomenclaturesHistoF08Utf8());
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de getPathNomenclaturesHistoF08Utf8().________________________

	
	
	/**
	 * retourne la clé du 
	 * Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.histof08.utf8".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.chemin.histof08.utf8".<br/>
	 */
	private static String fournirCleCheminNomenclaturesHistoF08Utf8() {
		return "application.repertoire.ressources.nomenclatures.chemin.histof08.utf8";
	} // Fin de fournirCleCheminNomenclaturesHistoF08Utf8()._______________
	

	
	/**
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour <code>cheminNomenclaturesHistoF08Utf8</code>.<br/>
	 * <br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8".<br/>
	 */
	private static String fournirCheminNomenclaturesHistoF08Utf8EnDur() {
		return "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8";
	} // Fin de fournirCheminNomenclaturesHistoF08Utf8EnDur()._____________
	


	/**
	 * Getter du 
	 * Nom du fichier de nomenclature du SENS pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF08SensEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.sens.histof08".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08SensEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08SensEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF08Sens : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF08Sens() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF08Sens == null) {

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
										fournirCleNomNomenclatureHistoF08Sens());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_SENS,
								fournirCleNomNomenclatureHistoF08Sens(),
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
							nomNomenclatureHistoF08Sens 
								= fournirNomNomenclatureHistoF08SensEnDur();

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

							nomNomenclatureHistoF08Sens = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_SENS,
								fournirCleNomNomenclatureHistoF08Sens(),
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
						nomNomenclatureHistoF08Sens 
							= fournirNomNomenclatureHistoF08SensEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF08Sens 
						= fournirNomNomenclatureHistoF08SensEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF08Sens == null)._________

			return nomNomenclatureHistoF08Sens;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF08Sens().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du SENS dans le HISTO_F08 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sens.histof08".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.sens.histof08".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF08Sens() {
		return "application.repertoire.ressources.nomenclatures.sens.histof08";
	} // Fin de fournirCleNomNomenclatureHistoF08Sens().___________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF08Sens</code>.<br/>
	 *
	 * @return : String : "2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF08SensEnDur() {
		return "2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF08SensEnDur()._________________

	
	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le SENS dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_Sens_HistoF08_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF08SensUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF08SensUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF08SensUtf8 == null) {
				
				final Path pathRelatifNomenclatureSensHistoF08 
					= Paths.get(getNomNomenclatureHistoF08Sens());
		
				final Path pathRelatifContextNomenclatureSensHistoF08 
					= getPathNomenclaturesHistoF08Utf8()
						.resolve(pathRelatifNomenclatureSensHistoF08);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureSensHistoF08.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureSensHistoF08);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF08SensUtf8 
					= new File(uriRessources.getPath());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF08SensUtf8
						, "Méthode getFichierNomenclatureHistoF08SensUtf8()");
			}
			
			return fichierNomenclatureHistoF08SensUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF08SensUtf8().__________________
	

	
	/**
	 * Getter du Nom du fichier de nomenclature de la NATURE DU COMPTAGE 
	 * pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF08NatureEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.nature.histof08".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08NatureEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08NatureEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF08Nature : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF08Nature() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF08Nature == null) {

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
										fournirCleNomNomenclatureHistoF08Nature());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_NATURE,
								fournirCleNomNomenclatureHistoF08Nature(),
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
							nomNomenclatureHistoF08Nature 
								= fournirNomNomenclatureHistoF08NatureEnDur();

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

							nomNomenclatureHistoF08Nature = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_NATURE,
								fournirCleNomNomenclatureHistoF08Nature(),
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
						nomNomenclatureHistoF08Nature 
							= fournirNomNomenclatureHistoF08NatureEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF08Nature 
						= fournirNomNomenclatureHistoF08NatureEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF08Nature == null)._________

			return nomNomenclatureHistoF08Nature;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF08Nature().________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 de NATURE du comptage 
	 * dans le HISTO_F08 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.nature.histof08".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.nature.histof08".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF08Nature() {
		return "application.repertoire.ressources.nomenclatures.nature.histof08";
	} // Fin de fournirCleNomNomenclatureHistoF08Nature()._________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le <code>nomNomenclatureHistoF08Nature</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF08NatureEnDur() {
		return "2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF08NatureEnDur()._______________
	
	
	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la
	 * Nomenclature pour la NATURE du comptage 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_Nature_HistoF08_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF08NatureUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF08NatureUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF08NatureUtf8 == null) {

				final Path pathRelatifNomenclatureNatureHistoF08 
					= Paths.get(getNomNomenclatureHistoF08Nature());
		
				final Path pathRelatifContextNomenclatureNatureHistoF08 
					= getPathNomenclaturesHistoF08Utf8()
						.resolve(pathRelatifNomenclatureNatureHistoF08);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureNatureHistoF08.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureNatureHistoF08);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF08NatureUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF08NatureUtf8
						, "Méthode getFichierNomenclatureHistoF08NatureUtf8()");
			}
			
			return fichierNomenclatureHistoF08NatureUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF08NatureUtf8().________________
	


	/**
	 * Getter du Nom du fichier de nomenclature de la catégorie administrative 
	 * de la route pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF08CatAdminRouteEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.catadminroute.histof08".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08CatAdminRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08CatAdminRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF08CatAdminRoute : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF08CatAdminRoute() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF08CatAdminRoute == null) {

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
										fournirCleNomNomenclatureHistoF08CatAdminRoute());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CATADMINROUTE,
								fournirCleNomNomenclatureHistoF08CatAdminRoute(),
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
							nomNomenclatureHistoF08CatAdminRoute 
								= fournirNomNomenclatureHistoF08CatAdminRouteEnDur();

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

							nomNomenclatureHistoF08CatAdminRoute = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CATADMINROUTE,
								fournirCleNomNomenclatureHistoF08CatAdminRoute(),
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
						nomNomenclatureHistoF08CatAdminRoute 
							= fournirNomNomenclatureHistoF08CatAdminRouteEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF08CatAdminRoute 
						= fournirNomNomenclatureHistoF08CatAdminRouteEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF08CatAdminRoute == null)._________

			return nomNomenclatureHistoF08CatAdminRoute;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF08CatAdminRoute()._________________


	
	/**
	 * retourne la clé du nom de la nomenclature en UTF-8 
	 * de CATEGORIE ADMINISTRATIVE de la route 
	 * dans le HISTO_F08 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.catadminroute.histof08".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.catadminroute.histof08".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF08CatAdminRoute() {
		return "application.repertoire.ressources.nomenclatures.catadminroute.histof08";
	} // Fin de fournirCleNomNomenclatureHistoF08CatAdminRoute()._______________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF08CatAdminRoute</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF08CatAdminRouteEnDur() {
		return "2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF08CatAdminRouteEnDur()._____________
	

	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CATEGORIE ADMINISTRATIVE de la route 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_CatAdminRoute_HistoF08_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF08CatAdminRouteUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF08CatAdminRouteUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF08CatAdminRouteUtf8 == null) {

				final Path pathRelatifNomenclatureCatAdminRouteHistoF08 
				= Paths.get(getNomNomenclatureHistoF08CatAdminRoute());
		
				final Path pathRelatifContextNomenclatureCatAdminRouteHistoF08 
					= getPathNomenclaturesHistoF08Utf8()
						.resolve(pathRelatifNomenclatureCatAdminRouteHistoF08);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureCatAdminRouteHistoF08.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureCatAdminRouteHistoF08);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF08CatAdminRouteUtf8 
					= new File(uriRessources.getPath());

				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF08CatAdminRouteUtf8
						, "Méthode getFichierNomenclatureHistoF08CatAdminRouteUtf8()");
			}
			
			return fichierNomenclatureHistoF08CatAdminRouteUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF08CatAdminRouteUtf8().______________
	

	
	/**
	 * Getter du Nom du fichier de nomenclature du TYPE DE COMPTAGE
	 * pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF08TypeComptageEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.typecomptage.histof08".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08TypeComptageEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08TypeComptageEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF08TypeComptage : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF08TypeComptage() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF08TypeComptage == null) {

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
										fournirCleNomNomenclatureHistoF08TypeComptage());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_TYPECOMPTAGE,
								fournirCleNomNomenclatureHistoF08TypeComptage(),
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
							nomNomenclatureHistoF08TypeComptage 
								= fournirNomNomenclatureHistoF08TypeComptageEnDur();

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

							nomNomenclatureHistoF08TypeComptage = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_TYPECOMPTAGE,
								fournirCleNomNomenclatureHistoF08TypeComptage(),
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
						nomNomenclatureHistoF08TypeComptage 
							= fournirNomNomenclatureHistoF08TypeComptageEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF08TypeComptage 
						= fournirNomNomenclatureHistoF08TypeComptageEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF08TypeComptage == null)._________

			return nomNomenclatureHistoF08TypeComptage;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF08TypeComptage()._______________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 de TYPE DE COMPTAGE 
	 * dans le HISTO_F08 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typecomptage.histof08".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.histof08".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF08TypeComptage() {
		return "application.repertoire.ressources.nomenclatures.typecomptage.histof08";
	} // Fin de fournirCleNomNomenclatureHistoF08TypeComptage().________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le <code>nomNomenclatureHistoF08TypeComptage</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF08TypeComptageEnDur() {
		return "2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF08TypeComptageEnDur().______________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le TYPE DE COMPTAGE dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_TypeComptage_HistoF08_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF08TypeComptageUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF08TypeComptageUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF08TypeComptageUtf8 == null) {

				final Path pathRelatifNomenclatureTypeComptageHistoF08 
				= Paths.get(getNomNomenclatureHistoF08TypeComptage());
		
				final Path pathRelatifContextNomenclatureTypeComptageHistoF08 
					= getPathNomenclaturesHistoF08Utf8()
						.resolve(pathRelatifNomenclatureTypeComptageHistoF08);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureTypeComptageHistoF08.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureTypeComptageHistoF08);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF08TypeComptageUtf8 
					= new File(uriRessources.getPath());

				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF08TypeComptageUtf8
						, "Méthode getFichierNomenclatureHistoF08TypeComptageUtf8()");
			}
			
			return fichierNomenclatureHistoF08TypeComptageUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF08TypeComptageUtf8()._______________
	

	
	/**
	 * Getter du Nom du fichier de nomenclature du CLASSEMENT DE LA ROUTE 
	 * pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF08ClassementRouteEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.classementroute.histof08".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08ClassementRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08ClassementRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF08ClassementRoute : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF08ClassementRoute() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF08ClassementRoute == null) {

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
										fournirCleNomNomenclatureHistoF08ClassementRoute());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CLASSEMENTROUTE,
								fournirCleNomNomenclatureHistoF08ClassementRoute(),
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
							nomNomenclatureHistoF08ClassementRoute 
								= fournirNomNomenclatureHistoF08ClassementRouteEnDur();

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

							nomNomenclatureHistoF08ClassementRoute = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CLASSEMENTROUTE,
								fournirCleNomNomenclatureHistoF08ClassementRoute(),
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
						nomNomenclatureHistoF08ClassementRoute 
							= fournirNomNomenclatureHistoF08ClassementRouteEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF08ClassementRoute 
						= fournirNomNomenclatureHistoF08ClassementRouteEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF08ClassementRoute == null)._________

			return nomNomenclatureHistoF08ClassementRoute;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF08ClassementRoute().____________________



	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 
	 * du CLASSEMENT DE LA ROUTE dans le HISTO_F08 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classementroute.histof08".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.histof08".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF08ClassementRoute() {
		return "application.repertoire.ressources.nomenclatures.classementroute.histof08";
	} // Fin de fournirCleNomNomenclatureHistoF08ClassementRoute()._____________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF08ClassementRoute</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF08ClassementRouteEnDur() {
		return "2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF08ClassementRouteEnDur().___________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le CLASSEMENT DE LA ROUTE dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClassementRoute_HistoF08_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF08ClassementRouteUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF08ClassementRouteUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF08ClassementRouteUtf8 == null) {

				final Path pathRelatifNomenclatureClassementRouteHistoF08 
				= Paths.get(getNomNomenclatureHistoF08ClassementRoute());
		
				final Path pathRelatifContextNomenclatureClassementRouteHistoF08 
					= getPathNomenclaturesHistoF08Utf8()
						.resolve(pathRelatifNomenclatureClassementRouteHistoF08);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureClassementRouteHistoF08.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureClassementRouteHistoF08);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF08ClassementRouteUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF08ClassementRouteUtf8
						, "Méthode getFichierNomenclatureHistoF08ClassementRouteUtf8()");
			}
			
			return fichierNomenclatureHistoF08ClassementRouteUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF08ClassementRouteUtf8()._______________
	

	
	/**
	 * Getter du Nom du fichier de nomenclature de la 
	 * CLASSE DE LARGEUR DE CHAUSSEE UNIQUE pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF08ClasseLargeurChausseeUEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.histof08".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08ClasseLargeurChausseeUEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08ClasseLargeurChausseeUEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF08ClasseLargeurChausseeU : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF08ClasseLargeurChausseeU() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF08ClasseLargeurChausseeU == null) {

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
										fournirCleNomNomenclatureHistoF08ClasseLargeurChausseeU());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CLASSELARGEURCHAUSSEEU,
								fournirCleNomNomenclatureHistoF08ClasseLargeurChausseeU(),
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
							nomNomenclatureHistoF08ClasseLargeurChausseeU 
								= fournirNomNomenclatureHistoF08ClasseLargeurChausseeUEnDur();

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

							nomNomenclatureHistoF08ClasseLargeurChausseeU 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CLASSELARGEURCHAUSSEEU,
								fournirCleNomNomenclatureHistoF08ClasseLargeurChausseeU(),
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
						nomNomenclatureHistoF08ClasseLargeurChausseeU 
							= fournirNomNomenclatureHistoF08ClasseLargeurChausseeUEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF08ClasseLargeurChausseeU 
						= fournirNomNomenclatureHistoF08ClasseLargeurChausseeUEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF08ClasseLargeurChausseeU == null)._________

			return nomNomenclatureHistoF08ClasseLargeurChausseeU;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF08ClasseLargeurChausseeU()._____________



	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 
	 * de la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE
	 * dans le HISTO_F08 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.histof08".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.histof08".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF08ClasseLargeurChausseeU() {
		return "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.histof08";
	} // Fin de fournirCleNomNomenclatureHistoF08ClasseLargeurChausseeU().______
	
	
	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF08ClasseLargeurChausseeU</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF08ClasseLargeurChausseeUEnDur() {
		return "2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF08ClasseLargeurChausseeUEnDur().____
	
	
	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClasseLargeurChausseeU_HistoF08_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8 == null) {

				final Path pathRelatifNomenclatureClasseLargeurChausseeUHistoF08 
					= Paths.get(getNomNomenclatureHistoF08ClasseLargeurChausseeU());
		
				final Path pathRelatifContextNomenclatureClasseLargeurChausseeUHistoF08 
					= getPathNomenclaturesHistoF08Utf8()
						.resolve(pathRelatifNomenclatureClasseLargeurChausseeUHistoF08);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureClasseLargeurChausseeUHistoF08.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureClasseLargeurChausseeUHistoF08);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8
						, "Méthode getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8()");
			}
			
			return fichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF08ClasseLargeurChausseeUUtf8()._______________
	


	/**
	 * Getter du Nom du fichier de nomenclature de la 
	 * CLASSE DE LARGEUR DE CHAUSSEES SEPAREES pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF08ClasseLargeurChausseesSEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.classelargeurchausseess.histof08".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08ClasseLargeurChausseesSEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08ClasseLargeurChausseesSEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF08ClasseLargeurChausseesS : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF08ClasseLargeurChausseesS() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF08ClasseLargeurChausseesS == null) {

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
										fournirCleNomNomenclatureHistoF08ClasseLargeurChausseesS());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CLASSELARGEURCHAUSSEESS,
								fournirCleNomNomenclatureHistoF08ClasseLargeurChausseesS(),
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
							nomNomenclatureHistoF08ClasseLargeurChausseesS 
								= fournirNomNomenclatureHistoF08ClasseLargeurChausseesSEnDur();

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

							nomNomenclatureHistoF08ClasseLargeurChausseesS 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CLASSELARGEURCHAUSSEESS,
								fournirCleNomNomenclatureHistoF08ClasseLargeurChausseesS(),
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
						nomNomenclatureHistoF08ClasseLargeurChausseesS 
							= fournirNomNomenclatureHistoF08ClasseLargeurChausseesSEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF08ClasseLargeurChausseesS 
						= fournirNomNomenclatureHistoF08ClasseLargeurChausseesSEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF08ClasseLargeurChausseesS == null)._________

			return nomNomenclatureHistoF08ClasseLargeurChausseesS;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF08ClasseLargeurChausseesS()._______


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 
	 * de la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES
	 * dans le HISTO_F08 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseess.histof08".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.classelargeurchausseess.histof08".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF08ClasseLargeurChausseesS() {
		return "application.repertoire.ressources.nomenclatures.classelargeurchausseess.histof08";
	} // Fin de fournirCleNomNomenclatureHistoF08ClasseLargeurChausseesS().
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF08ClasseLargeurChausseesS</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF08ClasseLargeurChausseesSEnDur() {
		return "2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF08ClasseLargeurChausseesSEnDur().___


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClasseLargeurChausseesS_HistoF08_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8 == null) {

				final Path pathRelatifNomenclatureClasseLargeurChausseesSHistoF08 
					= Paths.get(getNomNomenclatureHistoF08ClasseLargeurChausseesS());
		
				final Path pathRelatifContextNomenclatureClasseLargeurChausseesSHistoF08 
					= getPathNomenclaturesHistoF08Utf8()
						.resolve(pathRelatifNomenclatureClasseLargeurChausseesSHistoF08);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureClasseLargeurChausseesSHistoF08.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureClasseLargeurChausseesSHistoF08);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8 
					= new File(uriRessources.getPath());
																
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8
						, "Méthode getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8()");
			}
			
			return fichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF08ClasseLargeurChausseesSUtf8().____
	


	/**
	 * Getter du Nom du fichier de nomenclature du TYPE DE RESEAU
	 * pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF08TypeReseauEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.typereseau.histof08".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08TypeReseauEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08TypeReseauEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF08TypeReseau : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF08TypeReseau() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF08TypeReseau == null) {

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
										fournirCleNomNomenclatureHistoF08TypeReseau());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_TYPERESEAU,
								fournirCleNomNomenclatureHistoF08TypeReseau(),
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
							nomNomenclatureHistoF08TypeReseau 
								= fournirNomNomenclatureHistoF08TypeReseauEnDur();

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

							nomNomenclatureHistoF08TypeReseau 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_TYPERESEAU,
								fournirCleNomNomenclatureHistoF08TypeReseau(),
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
						nomNomenclatureHistoF08TypeReseau 
							= fournirNomNomenclatureHistoF08TypeReseauEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF08TypeReseau 
						= fournirNomNomenclatureHistoF08TypeReseauEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF08TypeReseau == null)._________

			return nomNomenclatureHistoF08TypeReseau;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF08TypeReseau().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du TYPE DE RESEAU
	 * dans le HISTO_F08 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typereseau.histof08".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typereseau.histof08".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF08TypeReseau() {
		return "application.repertoire.ressources.nomenclatures.typereseau.histof08";
	} // Fin de fournirCleNomNomenclatureHistoF08TypeReseau().__________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF08TypeReseau</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF08TypeReseauEnDur() {
		return "2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF08TypeReseauEnDur().________________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le TYPE DE RESEAU 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_TypeReseau_HistoF08_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF08TypeReseauUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF08TypeReseauUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF08TypeReseauUtf8 == null) {

				final Path pathRelatifNomenclatureTypeReseauHistoF08 
				= Paths.get(getNomNomenclatureHistoF08TypeReseau());
		
				final Path pathRelatifContextNomenclatureTypeReseauHistoF08 
					= getPathNomenclaturesHistoF08Utf8()
						.resolve(pathRelatifNomenclatureTypeReseauHistoF08);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureTypeReseauHistoF08.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureTypeReseauHistoF08);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF08TypeReseauUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF08TypeReseauUtf8
						, "Méthode getFichierNomenclatureHistoF08TypeReseauUtf8()");
			}
			
			return fichierNomenclatureHistoF08TypeReseauUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF08TypeReseauUtf8()._______________
	


	/**
	 * Getter du Nom du fichier de nomenclature du CODE CONCESSION
	 * pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF08CodeConcessionEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.codeconcession.histof08".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08CodeConcessionEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08CodeConcessionEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF08CodeConcession : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF08CodeConcession() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF08CodeConcession == null) {

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
										fournirCleNomNomenclatureHistoF08CodeConcession());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CODECONCESSION,
								fournirCleNomNomenclatureHistoF08CodeConcession(),
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
							nomNomenclatureHistoF08CodeConcession 
								= fournirNomNomenclatureHistoF08CodeConcessionEnDur();

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

							nomNomenclatureHistoF08CodeConcession 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_CODECONCESSION,
								fournirCleNomNomenclatureHistoF08CodeConcession(),
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
						nomNomenclatureHistoF08CodeConcession 
							= fournirNomNomenclatureHistoF08CodeConcessionEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF08CodeConcession 
						= fournirNomNomenclatureHistoF08CodeConcessionEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF08CodeConcession == null)._________

			return nomNomenclatureHistoF08CodeConcession;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF08CodeConcession().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du CODE CONCESSION
	 * dans le HISTO_F08 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.codeconcession.histof08".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.codeconcession.histof08".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF08CodeConcession() {
		return "application.repertoire.ressources.nomenclatures.codeconcession.histof08";
	} // Fin de fournirCleNomNomenclatureHistoF08CodeConcession().__________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF08CodeConcession</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF08CodeConcessionEnDur() {
		return "2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF08CodeConcessionEnDur().________________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le CODE CONCESSION 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_CodeConcession_HistoF08_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF08CodeConcessionUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF08CodeConcessionUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF08CodeConcessionUtf8 == null) {

				final Path pathRelatifNomenclatureCodeConcessionHistoF08 
				= Paths.get(getNomNomenclatureHistoF08CodeConcession());
		
				final Path pathRelatifContextNomenclatureCodeConcessionHistoF08 
					= getPathNomenclaturesHistoF08Utf8()
						.resolve(pathRelatifNomenclatureCodeConcessionHistoF08);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureCodeConcessionHistoF08.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureCodeConcessionHistoF08);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF08CodeConcessionUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF08CodeConcessionUtf8
						, "Méthode getFichierNomenclatureHistoF08CodeConcessionUtf8()");
			}
			
			return fichierNomenclatureHistoF08CodeConcessionUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF08CodeConcessionUtf8().________
	


	/**
	 * Getter du Nom du fichier de nomenclature du PROFIL EN TRAVERS
	 * pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF08ProfilTraversEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.profiltravers.histof08".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08ProfilTraversEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08ProfilTraversEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF08ProfilTravers : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF08ProfilTravers() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF08ProfilTravers == null) {

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
										fournirCleNomNomenclatureHistoF08ProfilTravers());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_PROFILTRAVERS,
								fournirCleNomNomenclatureHistoF08ProfilTravers(),
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
							nomNomenclatureHistoF08ProfilTravers 
								= fournirNomNomenclatureHistoF08ProfilTraversEnDur();

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

							nomNomenclatureHistoF08ProfilTravers 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_PROFILTRAVERS,
								fournirCleNomNomenclatureHistoF08ProfilTravers(),
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
						nomNomenclatureHistoF08ProfilTravers 
							= fournirNomNomenclatureHistoF08ProfilTraversEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF08ProfilTravers 
						= fournirNomNomenclatureHistoF08ProfilTraversEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF08ProfilTravers == null)._________

			return nomNomenclatureHistoF08ProfilTravers;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF08ProfilTravers().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du PROFIL EN TRAVERS
	 * dans le HISTO_F08 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.profiltravers.histof08".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.profiltravers.histof08".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF08ProfilTravers() {
		return "application.repertoire.ressources.nomenclatures.profiltravers.histof08";
	} // Fin de fournirCleNomNomenclatureHistoF08ProfilTravers().__________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF08ProfilTravers</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF08ProfilTraversEnDur() {
		return "2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF08ProfilTraversEnDur().________________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le PROFIL EN TRAVERS 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ProfilTravers_HistoF08_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF08ProfilTraversUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF08ProfilTraversUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF08ProfilTraversUtf8 == null) {

				final Path pathRelatifNomenclatureProfilTraversHistoF08 
				= Paths.get(getNomNomenclatureHistoF08ProfilTravers());
		
				final Path pathRelatifContextNomenclatureProfilTraversHistoF08 
					= getPathNomenclaturesHistoF08Utf8()
						.resolve(pathRelatifNomenclatureProfilTraversHistoF08);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureProfilTraversHistoF08.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureProfilTraversHistoF08);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF08ProfilTraversUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF08ProfilTraversUtf8
						, "Méthode getFichierNomenclatureHistoF08ProfilTraversUtf8()");
			}
			
			return fichierNomenclatureHistoF08ProfilTraversUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF08ProfilTraversUtf8().________
	


	/**
	 * Getter du Nom du fichier de nomenclature du SOUS RESEAU INDICE
	 * pour les HISTO_F08 en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHistoF08SousReseauIndiceEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.sousreseauindice.histof08".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08SousReseauIndiceEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHistoF08SousReseauIndiceEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHistoF08SousReseauIndice : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHistoF08SousReseauIndice() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHistoF08SousReseauIndice == null) {

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
										fournirCleNomNomenclatureHistoF08SousReseauIndice());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_SOUSRESEAUINDICE,
								fournirCleNomNomenclatureHistoF08SousReseauIndice(),
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
							nomNomenclatureHistoF08SousReseauIndice 
								= fournirNomNomenclatureHistoF08SousReseauIndiceEnDur();

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

							nomNomenclatureHistoF08SousReseauIndice 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HISTO_F08_SOUSRESEAUINDICE,
								fournirCleNomNomenclatureHistoF08SousReseauIndice(),
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
						nomNomenclatureHistoF08SousReseauIndice 
							= fournirNomNomenclatureHistoF08SousReseauIndiceEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHistoF08SousReseauIndice 
						= fournirNomNomenclatureHistoF08SousReseauIndiceEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHistoF08SousReseauIndice == null)._________

			return nomNomenclatureHistoF08SousReseauIndice;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHistoF08SousReseauIndice().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du SOUS RESEAU INDICE
	 * dans le HISTO_F08 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sousreseauindice.histof08".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.sousreseauindice.histof08".<br/>
	 */
	private static String fournirCleNomNomenclatureHistoF08SousReseauIndice() {
		return "application.repertoire.ressources.nomenclatures.sousreseauindice.histof08";
	} // Fin de fournirCleNomNomenclatureHistoF08SousReseauIndice().__________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHistoF08SousReseauIndice</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHistoF08SousReseauIndiceEnDur() {
		return "2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv";
	} // Fin de fournirNomNomenclatureHistoF08SousReseauIndiceEnDur().________________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le SOUS RESEAU INDICE 
	 * dans un HISTO_F08.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/HistoF08/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_SousReseauIndice_HistoF08_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHistoF08SousReseauIndiceUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHistoF08SousReseauIndiceUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHistoF08SousReseauIndiceUtf8 == null) {

				final Path pathRelatifNomenclatureSousReseauIndiceHistoF08 
				= Paths.get(getNomNomenclatureHistoF08SousReseauIndice());
		
				final Path pathRelatifContextNomenclatureSousReseauIndiceHistoF08 
					= getPathNomenclaturesHistoF08Utf8()
						.resolve(pathRelatifNomenclatureSousReseauIndiceHistoF08);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureSousReseauIndiceHistoF08.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureSousReseauIndiceHistoF08);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHistoF08SousReseauIndiceUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHistoF08SousReseauIndiceUtf8
						, "Méthode getFichierNomenclatureHistoF08SousReseauIndiceUtf8()");
			}
			
			return fichierNomenclatureHistoF08SousReseauIndiceUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHistoF08SousReseauIndiceUtf8().________
	


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
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
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
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
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
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESHISTO_F08);
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
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESHISTO_F08);
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
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
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
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
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
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESHISTO_F08);
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
		
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
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
		
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
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
		
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
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
	 * 'ressources/Descriptions de fichier/Hit/Descriptions en UTF-8/2014-08-19_Description_HIT_Utf8.csv'
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
		
		synchronized (ConfigurationNomenclaturesHistoF08Manager.class) {
			
			final String pathClassesString 
			= ConfigurationNomenclaturesHistoF08Manager
				.retournerClassesSousTarget();
		
			final Path pathClasses = Paths.get(pathClassesString);
			
			final Path pathPFile = pFile.toPath();
			
			final Path pathRelatifPFile = pathClasses.relativize(pathPFile);
			
			return pathRelatifPFile;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de fournirPathRelatifSousTargetClasses(...).__________________	
	

	
} // FIN DE LA CLASSE ConfigurationNomenclaturesHistoF08Manager.-------------
