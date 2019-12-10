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
 * CLASSE ConfigurationNomenclaturesHitManager :<br/>
 * Classe UTILITAIRE 
 * chargée de gérer la configuration des 
 * <b>NOMENCLATURES DE TOUS LES CHAMPS A NOMENCLATURE DU FICHIER HIT</b>.<br/>
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
 * <li>La méthode <code>getCheminNomenclaturesHitUtf8()</code> 
 * fournit un Singleton 
 * du chemin du répertoire parent contenant les nomenclatures 
 * encodées en UTF-8 des champs à nomenclature du fichier HIT.<br/>
 * Elle retourne en principe 
 * 'ressources/Nomenclatures/Hit/Nomenclatures en UTF-8'.</li>
 * <li>Les méthodes getNomNomenclatureXXX fournissent un singleton  
 * du nom du fichier de nomenclature du champXXX 
 * encodé en UTF-8 dans le HIT.</li>
 * <li>Les méthodes getFichierNomenclatureXXX fournissent un singleton  
 * du fichier de nomenclature du champXXX 
 * encodé en UTF-8 dans le HIT.</li>
 * </ul>
 * 
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ConfigurationNomenclaturesHitManager : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../javadoc/images/apptechnic/configurationmanagers/gestionnairesnomenclatures/classe_GestionnaireNomenclaturesHitManager.png" 
 * alt="Diagramme de classe du ConfigurationNomenclaturesHitManager" />
 * </p>
 * 
 * <br/>
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <code> // Récupération de la nomenclature du SENS sous forme de File.</code><br/>
 * <code><b>File fichierNomenclatureHitSens = ConfigurationNomenclaturesHitManager.getFichierNomenclatureHitSensUtf8();</b></code><br/>
 * <code> // Récupération de l'éventuel message d'erreur de la méthode (null si OK)</code><br/>
 * <code><b>String messageIndividuelRapport = ConfigurationNomenclaturesHitManager.getMessageIndividuelRapport();</b></code><br/>
 * <code> // Récupération de l'éventuel message d'erreur de la classe pour l'ensemble des méthodes (null si OK)</code><br/>
 * <code><b>String rapportConfigurationCsv = ConfigurationNomenclaturesHitManager.getRapportConfigurationCsv();</b></code><br/>
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
public final class ConfigurationNomenclaturesHitManager {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe ConfigurationNomenclaturesHitManager".<br/>
	 */
	public static final String CLASSE_CONFIGURATIONNOMENCLATURESHIT 
		= "Classe ConfigurationNomenclaturesHitManager";
		
	/**
	 * "Méthode getCheminNomenclaturesHitUtf8".<br/>
	 */
	public static final String METHODE_GET_CHEMINNOMENCLATURES_HIT 
		= "Méthode getCheminNomenclaturesHitUtf8";
	
	/**
	 * "Méthode getNomNomenclatureHitSens()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_SENS 
		= "Méthode getNomNomenclatureHitSens()";
	
	/**
	 * "Méthode getNomNomenclatureHitNature()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_NATURE 
		= "Méthode getNomNomenclatureHitNature()";
	
	/**
	 * "Méthode getNomNomenclatureHitCatAdminRoute()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_CATADMINROUTE 
		= "Méthode getNomNomenclatureHitCatAdminRoute()";
	
	/**
	 * "Méthode getNomNomenclatureHitTypeComptage()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_TYPECOMPTAGE 
		= "Méthode getNomNomenclatureHitTypeComptage()";
	
	/**
	 * "Méthode getNomNomenclatureHitClassementRoute()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_CLASSEMENTROUTE 
		= "Méthode getNomNomenclatureHitClassementRoute()";
	
	/**
	 * "Méthode getNomNomenclatureHitClasseLargeurChausseeU()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_CLASSELARGEURCHAUSSEEU 
		= "Méthode getNomNomenclatureHitClasseLargeurChausseeU()";
	
	/**
	 * "Méthode getNomNomenclatureHitClasseLargeurChausseesS()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_CLASSELARGEURCHAUSSEESS 
		= "Méthode getNomNomenclatureHitClasseLargeurChausseesS()";
	
	/**
	 * "Méthode getNomNomenclatureHitTypeReseau()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_TYPERESEAU 
		= "Méthode getNomNomenclatureHitTypeReseau()";
	
	/**
	 * "Méthode getNomNomenclatureHitPrPk()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_HIT_PRPK 
		= "Méthode getNomNomenclatureHitPrPk()";
	
	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/
	/**
	 * ";"
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
		
	// HIT.*********************
	
	/**
	 * <b>Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.hit.utf8".<br/>
	 */
	private static transient String cheminNomenclaturesHitUtf8;

	/**
	 * <b>Path relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.hit.utf8".<br/>
	 */
	private static transient Path pathNomenclaturesHitUtf8;
	
	/**
	 * Nom du fichier de nomenclature du SENS pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_Sens_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sens.hit".<br/>
	 */
	private static transient String nomNomenclatureHitSens;
	
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le SENS dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_Sens_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitSensUtf8;
	
	/**
	 * Nom du fichier de nomenclature de la NATURE DU COMPTAGE 
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_Nature_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.nature.hit".<br/>
	 */
	private static transient String nomNomenclatureHitNature;
	
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour la NATURE DU COMPTAGE dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_Nature_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitNatureUtf8;
	
	/**
	 * Nom du fichier de nomenclature de la CATEGORIE ADMINISTRATIVE 
	 * de la route pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.catadminroute.hit"<br/>	 
	 * */
	private static transient String nomNomenclatureHitCatAdminRoute;
		
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CATEGORIE ADMINISTRATIVE de la route 
	 * dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitCatAdminRouteUtf8;
	
	/**
	 * Nom du fichier de nomenclature du TYPE DE COMPTAGE
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typecomptage.hit"<br/>
	 */
	private static transient String nomNomenclatureHitTypeComptage;
		
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le TYPE DE COMPTAGE dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitTypeComptageUtf8;
	
	/**
	 * Nom du fichier de nomenclature du CLASSEMENT DE LA ROUTE 
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classementroute.hit"<br/>
	 */
	private static transient String nomNomenclatureHitClassementRoute;
		
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le CLASSEMENT DE LA ROUTE dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitClassementRouteUtf8;
	
	/**
	 * Nom du fichier de nomenclature de la 
	 * CLASSE DE LARGEUR DE CHAUSSEE UNIQUE pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.hit"<br/>
	 */
	private static transient String nomNomenclatureHitClasseLargeurChausseeU;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE 
	 * dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitClasseLargeurChausseeUUtf8;
	
	/**
	 * Nom du fichier de nomenclature de la 
	 * CLASSE DE LARGEUR DE CHAUSSEES SEPAREES pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseess.hit"<br/>
	 */
	private static transient String nomNomenclatureHitClasseLargeurChausseesS;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES 
	 * dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitClasseLargeurChausseesSUtf8;
	
	/**
	 * Nom du fichier de nomenclature du TYPE DE RESEAU
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typereseau.hit"<br/>
	 */
	private static transient String nomNomenclatureHitTypeReseau;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le TYPE DE RESEAU 
	 * dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitTypeReseauUtf8;
	
	/**
	 * Nom du fichier de nomenclature du type PR/PK
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.prpk.hit"<br/>
	 */
	private static transient String nomNomenclatureHitPrPk;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le TYPE PR/PK
	 * dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * ""ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureHitPrPkUtf8;
	
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
			.getLog(ConfigurationNomenclaturesHitManager.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour interdire l'instanciation.<br/>
	 */
	private ConfigurationNomenclaturesHitManager() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________

	

	/**
	 * Getter du <b>Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirCheminNomenclaturesHitUtf8EnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.chemin.hit.utf8".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminNomenclaturesHitUtf8EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminNomenclaturesHitUtf8EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return cheminNomenclaturesHitUtf8 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getCheminNomenclaturesHitUtf8() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (cheminNomenclaturesHitUtf8 == null) {

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
										fournirCleCheminNomenclaturesHitUtf8());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_CHEMINNOMENCLATURES_HIT,
								fournirCleCheminNomenclaturesHitUtf8(),
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
							cheminNomenclaturesHitUtf8 
								= fournirCheminNomenclaturesHitUtf8EnDur();

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

							cheminNomenclaturesHitUtf8 = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_CHEMINNOMENCLATURES_HIT,
								fournirCleCheminNomenclaturesHitUtf8(),
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
						cheminNomenclaturesHitUtf8 
							= fournirCheminNomenclaturesHitUtf8EnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					cheminNomenclaturesHitUtf8 
						= fournirCheminNomenclaturesHitUtf8EnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (cheminNomenclaturesHitUtf8 == null)._________

			return cheminNomenclaturesHitUtf8;

		} // Fin de synchronized.________________________________________

	} // Fin de getCheminNomenclaturesHitUtf8().___________________________
	

	
	/**
	 * Getter du <b>Path relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.hit.utf8".<br/>
	 *
	 * @return : Path.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Path getPathNomenclaturesHitUtf8() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			return Paths.get(getCheminNomenclaturesHitUtf8());
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de getPathNomenclaturesHitUtf8()._____________________________

	
	
	/**
	 * retourne la 
	 * clé du Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8].<br/>
	 * la clé est stockée dans 
	 * <code>application_fr_FR.properties</code>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.hit.utf8".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.chemin.hit.utf8".<br/>
	 */
	private static String fournirCleCheminNomenclaturesHitUtf8() {
		return "application.repertoire.ressources.nomenclatures.chemin.hit.utf8";
	} // Fin de fournirCleCheminNomenclaturesHitUtf8().____________________
	

	
	/**
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour <code>cheminNomenclaturesHitUtf8</code>.<br/>
	 * <br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8".<br/>
	 */
	private static String fournirCheminNomenclaturesHitUtf8EnDur() {
		return "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8";
	} // Fin de fournirCheminNomenclaturesHitUtf8EnDur().__________________
	


	/**
	 * Getter du Nom du fichier de nomenclature 
	 * du SENS 
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Sens_Hit_Utf8.csv".<br/>
	 * <br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitSensEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.sens.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitSensEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitSensEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitSens : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitSens() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitSens == null) {

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
										fournirCleNomNomenclatureHitSens());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_SENS,
								fournirCleNomNomenclatureHitSens(),
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
							nomNomenclatureHitSens 
								= fournirNomNomenclatureHitSensEnDur();

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

							nomNomenclatureHitSens = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_SENS,
								fournirCleNomNomenclatureHitSens(),
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
						nomNomenclatureHitSens 
							= fournirNomNomenclatureHitSensEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitSens 
						= fournirNomNomenclatureHitSensEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitSens == null)._________

			return nomNomenclatureHitSens;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitSens()._______________________________


	
	/**
	 * retourne la clé du nom de la nomenclature en UTF-8 
	 * du SENS dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Sens_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sens.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.sens.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitSens() {
		return "application.repertoire.ressources.nomenclatures.sens.hit";
	} // Fin de fournirCleNomNomenclatureHitSens().________________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le Nom du fichier de nomenclature en UTF-8 
	 * concernant le SENS 
	 * dans un HIT.<br/>
	 * <br/>
	 *
	 * @return : String : "2014-07-15_Nomenclature_Sens_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitSensEnDur() {
		return "2014-07-15_Nomenclature_Sens_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitSensEnDur().______________________

	
	
	/**
	 * Getter du <b>Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8
	 * contenant la nomenclature des SENS dans le HIT</b>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "context/ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_Sens_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitSensUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitSensUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitSensUtf8 == null) {
				
				final Path pathRelatifNomenclatureSensHit 
					= Paths.get(getNomNomenclatureHitSens());
			
				final Path pathRelatifContextNomenclatureSensHit 
					= getPathNomenclaturesHitUtf8()
						.resolve(pathRelatifNomenclatureSensHit);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureSensHit.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureSensHit);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHitSensUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitSensUtf8
						, "Méthode getFichierNomenclatureHitSensUtf8()");
			}
			
			return fichierNomenclatureHitSensUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitSensUtf8()._______________________
	

	
	/**
	 * Getter du Nom du fichier de nomenclature de la NATURE DU COMPTAGE 
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Nature_Hit_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitNatureEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.nature.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitNatureEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitNatureEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitNature : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitNature() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitNature == null) {

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
										fournirCleNomNomenclatureHitNature());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_NATURE,
								fournirCleNomNomenclatureHitNature(),
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
							nomNomenclatureHitNature 
								= fournirNomNomenclatureHitNatureEnDur();

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

							nomNomenclatureHitNature = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_NATURE,
								fournirCleNomNomenclatureHitNature(),
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
						nomNomenclatureHitNature 
							= fournirNomNomenclatureHitNatureEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitNature 
						= fournirNomNomenclatureHitNatureEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitNature == null)._________

			return nomNomenclatureHitNature;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitNature()._____________________________


	
	/**
	 * retourne la clé 
	 * du Nom du fichier de nomenclature de la NATURE DU COMPTAGE 
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * "2014-07-15_Nomenclature_Nature_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.nature.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.nature.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitNature() {
		return "application.repertoire.ressources.nomenclatures.nature.hit";
	} // Fin de fournirCleNomNomenclatureHitNature().______________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le <code>nomNomenclatureHitNature</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_Nature_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitNatureEnDur() {
		return "2014-07-15_Nomenclature_Nature_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitNatureEnDur().____________________
	
	
	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour la NATURE DU COMPTAGE dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_Nature_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitNatureUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitNatureUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitNatureUtf8 == null) {

				final Path pathRelatifNomenclatureNatureHit 
				= Paths.get(getNomNomenclatureHitNature());
		
				final Path pathRelatifContextNomenclatureNatureHit 
					= getPathNomenclaturesHitUtf8()
						.resolve(pathRelatifNomenclatureNatureHit);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureNatureHit.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureNatureHit);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHitNatureUtf8 
					= new File(uriRessources.getPath());

				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitNatureUtf8
						, "Méthode getFichierNomenclatureHitNatureUtf8()");
			}
			
			return fichierNomenclatureHitNatureUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitNatureUtf8()._______________________
	


	/**
	 * Getter du Nom du fichier de nomenclature de la catégorie administrative 
	 * de la route pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitCatAdminRouteEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.catadminroute.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitCatAdminRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitCatAdminRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitCatAdminRoute : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitCatAdminRoute() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitCatAdminRoute == null) {

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
										fournirCleNomNomenclatureHitCatAdminRoute());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_CATADMINROUTE,
								fournirCleNomNomenclatureHitCatAdminRoute(),
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
							nomNomenclatureHitCatAdminRoute 
								= fournirNomNomenclatureHitCatAdminRouteEnDur();

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

							nomNomenclatureHitCatAdminRoute = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_CATADMINROUTE,
								fournirCleNomNomenclatureHitCatAdminRoute(),
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
						nomNomenclatureHitCatAdminRoute 
							= fournirNomNomenclatureHitCatAdminRouteEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitCatAdminRoute 
						= fournirNomNomenclatureHitCatAdminRouteEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitCatAdminRoute == null)._________

			return nomNomenclatureHitCatAdminRoute;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitCatAdminRoute().______________________


	
	/**
	 * retourne la clé du nom de la nomenclature en UTF-8 
	 * de CATEGORIE ADMINISTRATIVE de la route 
	 * dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.catadminroute.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.catadminroute.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitCatAdminRoute() {
		return "application.repertoire.ressources.nomenclatures.catadminroute.hit";
	} // Fin de fournirCleNomNomenclatureHitCatAdminRoute()._______________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHitCatAdminRoute</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitCatAdminRouteEnDur() {
		return "2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitCatAdminRouteEnDur()._____________
	

	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CATEGORIE ADMINISTRATIVE de la route 
	 * dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_CatAdminRoute_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitCatAdminRouteUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitCatAdminRouteUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitCatAdminRouteUtf8 == null) {

				final Path pathRelatifNomenclatureCatAdminRouteHit 
				= Paths.get(getNomNomenclatureHitCatAdminRoute());
		
				final Path pathRelatifContextNomenclatureCatAdminRouteHit 
					= getPathNomenclaturesHitUtf8()
						.resolve(pathRelatifNomenclatureCatAdminRouteHit);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureCatAdminRouteHit.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureCatAdminRouteHit);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHitCatAdminRouteUtf8 
					= new File(uriRessources.getPath());

				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitCatAdminRouteUtf8
						, "Méthode getFichierNomenclatureHitCatAdminRouteUtf8()");
			}
			
			return fichierNomenclatureHitCatAdminRouteUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitCatAdminRouteUtf8().______________
	

	
	/**
	 * Getter du Nom du fichier de nomenclature du TYPE DE COMPTAGE
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitTypeComptageEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.typecomptage.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitTypeComptageEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitTypeComptageEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitTypeComptage : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitTypeComptage() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitTypeComptage == null) {

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
										fournirCleNomNomenclatureHitTypeComptage());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_TYPECOMPTAGE,
								fournirCleNomNomenclatureHitTypeComptage(),
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
							nomNomenclatureHitTypeComptage 
								= fournirNomNomenclatureHitTypeComptageEnDur();

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

							nomNomenclatureHitTypeComptage = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_TYPECOMPTAGE,
								fournirCleNomNomenclatureHitTypeComptage(),
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
						nomNomenclatureHitTypeComptage 
							= fournirNomNomenclatureHitTypeComptageEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitTypeComptage 
						= fournirNomNomenclatureHitTypeComptageEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitTypeComptage == null)._________

			return nomNomenclatureHitTypeComptage;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitTypeComptage()._______________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 de TYPE DE COMPTAGE 
	 * dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typecomptage.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitTypeComptage() {
		return "application.repertoire.ressources.nomenclatures.typecomptage.hit";
	} // Fin de fournirCleNomNomenclatureHitTypeComptage().________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le <code>nomNomenclatureHitTypeComptage</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitTypeComptageEnDur() {
		return "2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitTypeComptageEnDur().______________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le TYPE DE COMPTAGE dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_TypeComptage_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitTypeComptageUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitTypeComptageUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitTypeComptageUtf8 == null) {

				final Path pathRelatifNomenclatureTypeComptageHit 
				= Paths.get(getNomNomenclatureHitTypeComptage());
		
				final Path pathRelatifContextNomenclatureTypeComptageHit 
					= getPathNomenclaturesHitUtf8()
						.resolve(pathRelatifNomenclatureTypeComptageHit);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureTypeComptageHit.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureTypeComptageHit);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHitTypeComptageUtf8 
					= new File(uriRessources.getPath());

				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitTypeComptageUtf8
						, "Méthode getFichierNomenclatureHitTypeComptageUtf8()");
			}
			
			return fichierNomenclatureHitTypeComptageUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitTypeComptageUtf8()._______________
	

	
	/**
	 * Getter du Nom du fichier de nomenclature du CLASSEMENT DE LA ROUTE 
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitClassementRouteEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.classementroute.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitClassementRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitClassementRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitClassementRoute : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitClassementRoute() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitClassementRoute == null) {

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
										fournirCleNomNomenclatureHitClassementRoute());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_CLASSEMENTROUTE,
								fournirCleNomNomenclatureHitClassementRoute(),
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
							nomNomenclatureHitClassementRoute 
								= fournirNomNomenclatureHitClassementRouteEnDur();

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

							nomNomenclatureHitClassementRoute = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_CLASSEMENTROUTE,
								fournirCleNomNomenclatureHitClassementRoute(),
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
						nomNomenclatureHitClassementRoute 
							= fournirNomNomenclatureHitClassementRouteEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitClassementRoute 
						= fournirNomNomenclatureHitClassementRouteEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitClassementRoute == null)._________

			return nomNomenclatureHitClassementRoute;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitClassementRoute().____________________



	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 
	 * du CLASSEMENT DE LA ROUTE dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classementroute.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitClassementRoute() {
		return "application.repertoire.ressources.nomenclatures.classementroute.hit";
	} // Fin de fournirCleNomNomenclatureHitClassementRoute()._____________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHitClassementRoute</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitClassementRouteEnDur() {
		return "2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitClassementRouteEnDur().___________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le CLASSEMENT DE LA ROUTE dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClassementRoute_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitClassementRouteUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitClassementRouteUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitClassementRouteUtf8 == null) {

				final Path pathRelatifNomenclatureClassementRouteHit 
				= Paths.get(getNomNomenclatureHitClassementRoute());
		
				final Path pathRelatifContextNomenclatureClassementRouteHit 
					= getPathNomenclaturesHitUtf8()
						.resolve(pathRelatifNomenclatureClassementRouteHit);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureClassementRouteHit.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureClassementRouteHit);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHitClassementRouteUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitClassementRouteUtf8
						, "Méthode getFichierNomenclatureHitClassementRouteUtf8()");
			}
			
			return fichierNomenclatureHitClassementRouteUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitClassementRouteUtf8()._______________
	

	
	/**
	 * Getter du Nom du fichier de nomenclature de la 
	 * CLASSE DE LARGEUR DE CHAUSSEE UNIQUE pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitClasseLargeurChausseeUEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitClasseLargeurChausseeUEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitClasseLargeurChausseeUEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitClasseLargeurChausseeU : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitClasseLargeurChausseeU() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitClasseLargeurChausseeU == null) {

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
										fournirCleNomNomenclatureHitClasseLargeurChausseeU());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_CLASSELARGEURCHAUSSEEU,
								fournirCleNomNomenclatureHitClasseLargeurChausseeU(),
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
							nomNomenclatureHitClasseLargeurChausseeU 
								= fournirNomNomenclatureHitClasseLargeurChausseeUEnDur();

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

							nomNomenclatureHitClasseLargeurChausseeU 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_CLASSELARGEURCHAUSSEEU,
								fournirCleNomNomenclatureHitClasseLargeurChausseeU(),
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
						nomNomenclatureHitClasseLargeurChausseeU 
							= fournirNomNomenclatureHitClasseLargeurChausseeUEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitClasseLargeurChausseeU 
						= fournirNomNomenclatureHitClasseLargeurChausseeUEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitClasseLargeurChausseeU == null)._________

			return nomNomenclatureHitClasseLargeurChausseeU;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitClasseLargeurChausseeU()._____________



	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 
	 * de la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE
	 * dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitClasseLargeurChausseeU() {
		return "application.repertoire.ressources.nomenclatures.classelargeurchausseeu.hit";
	} // Fin de fournirCleNomNomenclatureHitClasseLargeurChausseeU().______
	
	
	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHitClasseLargeurChausseeU</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitClasseLargeurChausseeUEnDur() {
		return "2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitClasseLargeurChausseeUEnDur().____
	
	
	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour la CLASSE DE LARGEUR DE CHAUSSEE UNIQUE 
	 * dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClasseLargeurChausseeU_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitClasseLargeurChausseeUUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitClasseLargeurChausseeUUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitClasseLargeurChausseeUUtf8 == null) {

				final Path pathRelatifNomenclatureClasseLargeurChausseeUHit 
					= Paths.get(getNomNomenclatureHitClasseLargeurChausseeU());
		
				final Path pathRelatifContextNomenclatureClasseLargeurChausseeUHit 
					= getPathNomenclaturesHitUtf8()
						.resolve(pathRelatifNomenclatureClasseLargeurChausseeUHit);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureClasseLargeurChausseeUHit.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureClasseLargeurChausseeUHit);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHitClasseLargeurChausseeUUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitClasseLargeurChausseeUUtf8
						, "Méthode getFichierNomenclatureHitClasseLargeurChausseeUUtf8()");
			}
			
			return fichierNomenclatureHitClasseLargeurChausseeUUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitClasseLargeurChausseeUUtf8()._______________
	


	/**
	 * Getter du Nom du fichier de nomenclature de la 
	 * CLASSE DE LARGEUR DE CHAUSSEES SEPAREES pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitClasseLargeurChausseesSEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.classelargeurchausseess.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitClasseLargeurChausseesSEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitClasseLargeurChausseesSEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitClasseLargeurChausseesS : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitClasseLargeurChausseesS() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitClasseLargeurChausseesS == null) {

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
										fournirCleNomNomenclatureHitClasseLargeurChausseesS());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_CLASSELARGEURCHAUSSEESS,
								fournirCleNomNomenclatureHitClasseLargeurChausseesS(),
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
							nomNomenclatureHitClasseLargeurChausseesS 
								= fournirNomNomenclatureHitClasseLargeurChausseesSEnDur();

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

							nomNomenclatureHitClasseLargeurChausseesS 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_CLASSELARGEURCHAUSSEESS,
								fournirCleNomNomenclatureHitClasseLargeurChausseesS(),
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
						nomNomenclatureHitClasseLargeurChausseesS 
							= fournirNomNomenclatureHitClasseLargeurChausseesSEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitClasseLargeurChausseesS 
						= fournirNomNomenclatureHitClasseLargeurChausseesSEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitClasseLargeurChausseesS == null)._________

			return nomNomenclatureHitClasseLargeurChausseesS;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitClasseLargeurChausseesS().____________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 
	 * de la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES
	 * dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classelargeurchausseess.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.classelargeurchausseess.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitClasseLargeurChausseesS() {
		return "application.repertoire.ressources.nomenclatures.classelargeurchausseess.hit";
	} // Fin de fournirCleNomNomenclatureHitClasseLargeurChausseesS()._____
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHitClasseLargeurChausseesS</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitClasseLargeurChausseesSEnDur() {
		return "2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitClasseLargeurChausseesSEnDur().___


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour 
	 * la CLASSE DE LARGEUR DE CHAUSSEES SEPAREES 
	 * dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClasseLargeurChausseesS_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitClasseLargeurChausseesSUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitClasseLargeurChausseesSUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitClasseLargeurChausseesSUtf8 == null) {

				final Path pathRelatifNomenclatureClasseLargeurChausseesSHit 
					= Paths.get(getNomNomenclatureHitClasseLargeurChausseesS());
		
				final Path pathRelatifContextNomenclatureClasseLargeurChausseesSHit 
					= getPathNomenclaturesHitUtf8()
						.resolve(pathRelatifNomenclatureClasseLargeurChausseesSHit);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureClasseLargeurChausseesSHit.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureClasseLargeurChausseesSHit);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHitClasseLargeurChausseesSUtf8 
					= new File(uriRessources.getPath());
																
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitClasseLargeurChausseesSUtf8
						, "Méthode getFichierNomenclatureHitClasseLargeurChausseesSUtf8()");
			}
			
			return fichierNomenclatureHitClasseLargeurChausseesSUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitClasseLargeurChausseesSUtf8().____
	


	/**
	 * Getter du Nom du fichier de nomenclature du TYPE DE RESEAU
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitTypeReseauEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.typereseau.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitTypeReseauEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitTypeReseauEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitTypeReseau : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitTypeReseau() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitTypeReseau == null) {

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
										fournirCleNomNomenclatureHitTypeReseau());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_HIT_TYPERESEAU,
								fournirCleNomNomenclatureHitTypeReseau(),
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
							nomNomenclatureHitTypeReseau 
								= fournirNomNomenclatureHitTypeReseauEnDur();

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

							nomNomenclatureHitTypeReseau 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_TYPERESEAU,
								fournirCleNomNomenclatureHitTypeReseau(),
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
						nomNomenclatureHitTypeReseau 
							= fournirNomNomenclatureHitTypeReseauEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitTypeReseau 
						= fournirNomNomenclatureHitTypeReseauEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitTypeReseau == null)._________

			return nomNomenclatureHitTypeReseau;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitTypeReseau().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du TYPE DE RESEAU
	 * dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typereseau.hit".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typereseau.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitTypeReseau() {
		return "application.repertoire.ressources.nomenclatures.typereseau.hit";
	} // Fin de fournirCleNomNomenclatureHitTypeReseau().__________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHitTypeReseau</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitTypeReseauEnDur() {
		return "2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitTypeReseauEnDur().________________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le TYPE DE RESEAU 
	 * dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_TypeReseau_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitTypeReseauUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitTypeReseauUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitTypeReseauUtf8 == null) {

				final Path pathRelatifNomenclatureTypeReseauHit 
				= Paths.get(getNomNomenclatureHitTypeReseau());
		
				final Path pathRelatifContextNomenclatureTypeReseauHit 
					= getPathNomenclaturesHitUtf8()
						.resolve(pathRelatifNomenclatureTypeReseauHit);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureTypeReseauHit.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureTypeReseauHit);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHitTypeReseauUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitTypeReseauUtf8
						, "Méthode getFichierNomenclatureHitTypeReseauUtf8()");
			}
			
			return fichierNomenclatureHitTypeReseauUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitTypeReseauUtf8()._______________
	


	/**
	 * Getter du Nom du fichier de nomenclature du type PR/PK
	 * pour les HIT en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureHitPrPkEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.prpk.hit".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitPrPkEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureHitPrPkEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureHitPrPk : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureHitPrPk() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesHitManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureHitPrPk == null) {

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
										fournirCleNomNomenclatureHitPrPk());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
									METHODE_GET_NOMNOMENCLATURE_HIT_PRPK,
								fournirCleNomNomenclatureHitPrPk(),
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
							nomNomenclatureHitPrPk 
								= fournirNomNomenclatureHitPrPkEnDur();

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

							nomNomenclatureHitPrPk 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_HIT_PRPK,
								fournirCleNomNomenclatureHitPrPk(),
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
						nomNomenclatureHitPrPk 
							= fournirNomNomenclatureHitPrPkEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureHitPrPk 
						= fournirNomNomenclatureHitPrPkEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureHitPrPk == null)._________

			return nomNomenclatureHitPrPk;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureHitPrPk()._______________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du TYPE PR/PK dans le HIT 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.prpk.hit".<br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.prpk.hit".<br/>
	 */
	private static String fournirCleNomNomenclatureHitPrPk() {
		return "application.repertoire.ressources.nomenclatures.prpk.hit";
	} // Fin de fournirCleNomNomenclatureHitPrPk().________________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureHitPrPk</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureHitPrPkEnDur() {
		return "2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv";
	} // Fin de fournirNomNomenclatureHitPrPkEnDur().______________________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le TYPE PR/PK
	 * dans un HIT.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * ""ressources/Nomenclatures/Hit/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_PrPk_Hit_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureHitPrPkUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureHitPrPkUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureHitPrPkUtf8 == null) {

				final Path pathRelatifNomenclaturePrPkHit 
				= Paths.get(getNomNomenclatureHitPrPk());
		
				final Path pathRelatifContextNomenclaturePrPkHit 
					= getPathNomenclaturesHitUtf8()
						.resolve(pathRelatifNomenclaturePrPkHit);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclaturePrPkHit.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclaturePrPkHit);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureHitPrPkUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureHitPrPkUtf8
						, "Méthode getFichierNomenclatureHitPrPkUtf8()");
			}
			
			return fichierNomenclatureHitPrPkUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureHitPrPkUtf8()._______________________
	


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
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
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
		synchronized (ConfigurationNomenclaturesHitManager.class) {
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
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESHIT);
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
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESHIT);
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
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
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
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
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
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESHIT);
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
		
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
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
		
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
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
		
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
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
		
		synchronized (ConfigurationNomenclaturesHitManager.class) {
			
			final String pathClassesString 
			= ConfigurationNomenclaturesHitManager
				.retournerClassesSousTarget();
		
			final Path pathClasses = Paths.get(pathClassesString);
			
			final Path pathPFile = pFile.toPath();
			
			final Path pathRelatifPFile = pathClasses.relativize(pathPFile);
			
			return pathRelatifPFile;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de fournirPathRelatifSousTargetClasses(...).__________________	
	

	
} // FIN DE LA CLASSE ConfigurationNomenclaturesHitManager.------------------
