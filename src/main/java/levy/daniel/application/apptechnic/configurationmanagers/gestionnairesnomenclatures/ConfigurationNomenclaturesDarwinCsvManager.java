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
 * CLASSE ConfigurationNomenclaturesDarwinCsvManager :<br/>
 * Classe UTILITAIRE 
 * chargée de gérer la configuration des 
 * <b>NOMENCLATURES DE TOUS LES CHAMPS A NOMENCLATURE DU FICHIER DARWIN_CSV</b>.<br/>
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
 * <li>La méthode <code>getCheminNomenclaturesDarwinCsvUtf8()</code> 
 * fournit un Singleton 
 * du chemin du répertoire parent contenant les nomenclatures 
 * encodées en UTF-8 des champs à nomenclature du fichier DARWIN_CSV.<br/>
 * Elle retourne en principe 
 * 'ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8'.</li>
 * <li>Les méthodes getNomNomenclatureXXX fournissent un singleton  
 * du nom du fichier de nomenclature du champXXX 
 * encodé en UTF-8 dans le DARWIN_CSV.</li>
 * <li>Les méthodes getFichierNomenclatureXXX fournissent un singleton  
 * du fichier de nomenclature du champXXX 
 * encodé en UTF-8 dans le DARWIN_CSV.</li>
 * </ul>
 *  
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ConfigurationNomenclaturesDarwinCsvManager : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../javadoc/images/apptechnic/configurationmanagers/gestionnairesnomenclatures/classe_GestionnaireNomenclaturesDarwinCsvManager.png" 
 * alt="Diagramme de classe du ConfigurationNomenclaturesDarwinCsvManager" />
 * </p>
 * 
 * <br/>
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <code> // Récupération de la nomenclature du SENS sous forme de File.</code><br/>
 * <code><b>File fichierNomenclatureDarwinCsvSens = ConfigurationNomenclaturesDarwinCsvManager.getFichierNomenclatureDarwinCsvSensUtf8();</b></code><br/>
 * <code> // Récupération de l'éventuel message d'erreur de la méthode (null si OK)</code><br/>
 * <code><b>String messageIndividuelRapport = ConfigurationNomenclaturesDarwinCsvManager.getMessageIndividuelRapport();</b></code><br/>
 * <code> // Récupération de l'éventuel message d'erreur de la classe pour l'ensemble des méthodes (null si OK)</code><br/>
 * <code><b>String rapportConfigurationCsv = ConfigurationNomenclaturesDarwinCsvManager.getRapportConfigurationCsv();</b></code><br/>
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
public final class ConfigurationNomenclaturesDarwinCsvManager {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe ConfigurationNomenclaturesDarwinCsvManager".<br/>
	 */
	public static final String CLASSE_CONFIGURATIONNOMENCLATURESDARWIN_CSV 
		= "Classe ConfigurationNomenclaturesDarwinCsvManager";
		
	/**
	 * "Méthode getCheminNomenclaturesDarwinCsvUtf8".<br/>
	 */
	public static final String METHODE_GET_CHEMINNOMENCLATURES_DARWIN_CSV 
		= "Méthode getCheminNomenclaturesDarwinCsvUtf8";
	
	/**
	 * "Méthode getNomNomenclatureDarwinCsvCodeConcession()".
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_CODECONCESSION 
		= "Méthode getNomNomenclatureDarwinCsvCodeConcession()";
	
	/**
	 * "Méthode getNomNomenclatureDarwinCsvSens()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_SENS 
		= "Méthode getNomNomenclatureDarwinCsvSens()";
	
	/**
	 * "Méthode getNomNomenclatureDarwinCsvTypeComptage()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_TYPECOMPTAGE 
		= "Méthode getNomNomenclatureDarwinCsvTypeComptage()";
	
	/**
	 * "Méthode getNomNomenclatureDarwinCsvClassementRoute()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_CLASSEMENTROUTE 
		= "Méthode getNomNomenclatureDarwinCsvClassementRoute()";
	
	/**
	 * "Méthode getNomNomenclatureDarwinCsvProfilTravers()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_PROFILTRAVERS 
		= "Méthode getNomNomenclatureDarwinCsvProfilTravers()";
	
	/**
	 * "Méthode getNomNomenclatureDarwinCsvSousReseauIndice()".<br/>
	 */
	public static final String METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_SOUSRESEAUINDICE 
		= "Méthode getNomNomenclatureDarwinCsvSousReseauIndice()";
	
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
		
	// DARWIN_CSV.*********************
	
	/**
	 * <b>Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.darwincsv.utf8".<br/>
	 */
	private static transient String cheminNomenclaturesDarwinCsvUtf8;

	/**
	 * <b>Path relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.darwincsv.utf8".<br/>
	 */
	private static transient Path pathNomenclaturesDarwinCsvUtf8;
	
	/**
	 * Nom du fichier de nomenclature du CODE CONCESSION pour les DARWIN_CSV en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_CodeConcession_DarwinCsv_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.codeconcession.darwincsv".<br/>
	 */
	private static transient String nomNomenclatureDarwinCsvCodeConcession;
	
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le CODE CONCESSION dans un DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_CodeConcession_DarwinCsv_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureDarwinCsvCodeConcessionUtf8;
	
	/**
	 * Nom du fichier de nomenclature du SENS pour les DARWIN_CSV en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sens.darwincsv".<br/>
	 */
	private static transient String nomNomenclatureDarwinCsvSens;
	
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le SENS dans un DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureDarwinCsvSensUtf8;
	
	/**
	 * Nom du fichier de nomenclature du TYPE DE COMPTAGE
	 * pour les DARWIN_CSV en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typecomptage.darwincsv"<br/>
	 */
	private static transient String nomNomenclatureDarwinCsvTypeComptage;
		
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le TYPE DE COMPTAGE dans un DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureDarwinCsvTypeComptageUtf8;
	
	/**
	 * Nom du fichier de nomenclature du CLASSEMENT DE LA ROUTE 
	 * pour les DARWIN_CSV en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classementroute.darwincsv"<br/>
	 */
	private static transient String nomNomenclatureDarwinCsvClassementRoute;
		
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le CLASSEMENT DE LA ROUTE dans un DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureDarwinCsvClassementRouteUtf8;
	
	/**
	 * Nom du fichier de nomenclature du PROFIL EN TRAVERS
	 * pour les DARWIN_CSV en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.profiltravers.darwincsv"<br/>
	 */
	private static transient String nomNomenclatureDarwinCsvProfilTravers;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le PROFIL EN TRAVERS 
	 * dans un DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureDarwinCsvProfilTraversUtf8;
	
	/**
	 * Nom du fichier de nomenclature du SOUS RESEAU INDICE
	 * pour les DARWIN_CSV en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sousreseauindice.darwincsv"<br/>
	 */
	private static transient String nomNomenclatureDarwinCsvSousReseauIndice;
			
	/**
	 * Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le SOUS RESEAU INDICE 
	 * dans un DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv".<br/>
	 */
	private static transient File fichierNomenclatureDarwinCsvSousReseauIndiceUtf8;
	

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
			.getLog(ConfigurationNomenclaturesDarwinCsvManager.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour interdire l'instanciation.<br/>
	 */
	private ConfigurationNomenclaturesDarwinCsvManager() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________

	

	/**
	 * Getter du <b>Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirCheminNomenclaturesDarwinCsvUtf8EnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.chemin.darwincsv.utf8".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminNomenclaturesDarwinCsvUtf8EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirCheminNomenclaturesDarwinCsvUtf8EnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return cheminNomenclaturesDarwinCsvUtf8 : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getCheminNomenclaturesDarwinCsvUtf8() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (cheminNomenclaturesDarwinCsvUtf8 == null) {

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
										fournirCleCheminNomenclaturesDarwinCsvUtf8());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_CHEMINNOMENCLATURES_DARWIN_CSV,
								fournirCleCheminNomenclaturesDarwinCsvUtf8(),
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
							cheminNomenclaturesDarwinCsvUtf8 
								= fournirCheminNomenclaturesDarwinCsvUtf8EnDur();

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

							cheminNomenclaturesDarwinCsvUtf8 = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_CHEMINNOMENCLATURES_DARWIN_CSV,
								fournirCleCheminNomenclaturesDarwinCsvUtf8(),
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
						cheminNomenclaturesDarwinCsvUtf8 
							= fournirCheminNomenclaturesDarwinCsvUtf8EnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					cheminNomenclaturesDarwinCsvUtf8 
						= fournirCheminNomenclaturesDarwinCsvUtf8EnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (cheminNomenclaturesDarwinCsvUtf8 == null)._________

			return cheminNomenclaturesDarwinCsvUtf8;

		} // Fin de synchronized.________________________________________

	} // Fin de getCheminNomenclaturesDarwinCsvUtf8().______________________
	

	
	/**
	 * Getter du <b>Path relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties</code>.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.darwincsv.utf8".<br/>
	 *
	 * @return : Path.<br/>
	 * 
	 * @throws Exception 
	 */
	public static Path getPathNomenclaturesDarwinCsvUtf8() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
			return Paths.get(getCheminNomenclaturesDarwinCsvUtf8());
			
		} // Fin de synchronized.________________________________________
		
	} // Fin de getPathNomenclaturesDarwinCsvUtf8().________________________

	
	
	/**
	 * retourne la clé du 
	 * Chemin relatif par rapport au classpath (contexte) 
	 * du répertoire [Nomenclatures en UTF-8] sous forme de String</b> 
	 * stocké dans le fichier <code>application.properties.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.chemin.darwincsv.utf8".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.chemin.darwincsv.utf8".<br/>
	 */
	private static String fournirCleCheminNomenclaturesDarwinCsvUtf8() {
		return "application.repertoire.ressources.nomenclatures.chemin.darwincsv.utf8";
	} // Fin de fournirCleCheminNomenclaturesDarwinCsvUtf8()._______________
	

	
	/**
	 * Fournit une valeur stockée en dur 
	 * dans la classe pour <code>cheminNomenclaturesDarwinCsvUtf8</code>.<br/>
	 * <br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8".<br/>
	 */
	private static String fournirCheminNomenclaturesDarwinCsvUtf8EnDur() {
		return "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8";
	} // Fin de fournirCheminNomenclaturesDarwinCsvUtf8EnDur()._____________
	


	/**
	 * Getter du Nom du fichier de nomenclature du CODE CONCESSION
	 * pour les DARWIN_CSV en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_CodeConcession_DarwinCsv_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureDarwinCsvCodeConcessionEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.codeconcession.darwincsv".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinCsvCodeConcessionEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinCsvCodeConcessionEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureDarwinCsvCodeConcession : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureDarwinCsvCodeConcession() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureDarwinCsvCodeConcession == null) {

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
										fournirCleNomNomenclatureDarwinCsvCodeConcession());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_CODECONCESSION,
								fournirCleNomNomenclatureDarwinCsvCodeConcession(),
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
							nomNomenclatureDarwinCsvCodeConcession 
								= fournirNomNomenclatureDarwinCsvCodeConcessionEnDur();

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

							nomNomenclatureDarwinCsvCodeConcession 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_CODECONCESSION,
								fournirCleNomNomenclatureDarwinCsvCodeConcession(),
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
						nomNomenclatureDarwinCsvCodeConcession 
							= fournirNomNomenclatureDarwinCsvCodeConcessionEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureDarwinCsvCodeConcession 
						= fournirNomNomenclatureDarwinCsvCodeConcessionEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureDarwinCsvCodeConcession == null)._________

			return nomNomenclatureDarwinCsvCodeConcession;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureDarwinCsvCodeConcession().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du CODE CONCESSION
	 * dans le DARWIN_CSV 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_CodeConcession_DarwinCsv_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.codeconcession.darwincsv".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.codeconcession.darwincsv".<br/>
	 */
	private static String fournirCleNomNomenclatureDarwinCsvCodeConcession() {
		return "application.repertoire.ressources.nomenclatures.codeconcession.darwincsv";
	} // Fin de fournirCleNomNomenclatureDarwinCsvCodeConcession().__________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureDarwinCsvCodeConcession</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_CodeConcession_DarwinCsv_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureDarwinCsvCodeConcessionEnDur() {
		return "2014-07-15_Nomenclature_CodeConcession_DarwinCsv_Utf8.csv";
	} // Fin de fournirNomNomenclatureDarwinCsvCodeConcessionEnDur().________________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le CODE CONCESSION 
	 * dans un DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_CodeConcession_DarwinCsv_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureDarwinCsvCodeConcessionUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureDarwinCsvCodeConcessionUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureDarwinCsvCodeConcessionUtf8 == null) {

				final Path pathRelatifNomenclatureCodeConcessionDarwinCsv 
				= Paths.get(getNomNomenclatureDarwinCsvCodeConcession());
		
				final Path pathRelatifContextNomenclatureCodeConcessionDarwinCsv 
					= getPathNomenclaturesDarwinCsvUtf8()
						.resolve(pathRelatifNomenclatureCodeConcessionDarwinCsv);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureCodeConcessionDarwinCsv.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureCodeConcessionDarwinCsv);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureDarwinCsvCodeConcessionUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureDarwinCsvCodeConcessionUtf8
						, "Méthode getFichierNomenclatureDarwinCsvCodeConcessionUtf8()");
			}
			
			return fichierNomenclatureDarwinCsvCodeConcessionUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureDarwinCsvCodeConcessionUtf8().________
	


	/**
	 * Getter du 
	 * Nom du fichier de nomenclature du SENS pour les DARWIN_CSV en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureDarwinCsvSensEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.sens.darwincsv".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinCsvSensEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinCsvSensEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureDarwinCsvSens : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureDarwinCsvSens() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureDarwinCsvSens == null) {

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
										fournirCleNomNomenclatureDarwinCsvSens());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_SENS,
								fournirCleNomNomenclatureDarwinCsvSens(),
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
							nomNomenclatureDarwinCsvSens 
								= fournirNomNomenclatureDarwinCsvSensEnDur();

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

							nomNomenclatureDarwinCsvSens = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_SENS,
								fournirCleNomNomenclatureDarwinCsvSens(),
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
						nomNomenclatureDarwinCsvSens 
							= fournirNomNomenclatureDarwinCsvSensEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureDarwinCsvSens 
						= fournirNomNomenclatureDarwinCsvSensEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureDarwinCsvSens == null)._________

			return nomNomenclatureDarwinCsvSens;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureDarwinCsvSens().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du SENS dans le DARWIN_CSV 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sens.darwincsv".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.sens.darwincsv".<br/>
	 */
	private static String fournirCleNomNomenclatureDarwinCsvSens() {
		return "application.repertoire.ressources.nomenclatures.sens.darwincsv";
	} // Fin de fournirCleNomNomenclatureDarwinCsvSens().___________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureDarwinCsvSens</code>.<br/>
	 *
	 * @return : String : "2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureDarwinCsvSensEnDur() {
		return "2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv";
	} // Fin de fournirNomNomenclatureDarwinCsvSensEnDur()._________________

	
	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le SENS dans un DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_Sens_DarwinCsv_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureDarwinCsvSensUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureDarwinCsvSensUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureDarwinCsvSensUtf8 == null) {
				
				final Path pathRelatifNomenclatureSensDarwinCsv 
					= Paths.get(getNomNomenclatureDarwinCsvSens());
		
				final Path pathRelatifContextNomenclatureSensDarwinCsv 
					= getPathNomenclaturesDarwinCsvUtf8()
						.resolve(pathRelatifNomenclatureSensDarwinCsv);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureSensDarwinCsv.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureSensDarwinCsv);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureDarwinCsvSensUtf8 
					= new File(uriRessources.getPath());
				
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureDarwinCsvSensUtf8
						, "Méthode getFichierNomenclatureDarwinCsvSensUtf8()");
			}
			
			return fichierNomenclatureDarwinCsvSensUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureDarwinCsvSensUtf8().__________________
	

	
	/**
	 * Getter du Nom du fichier de nomenclature du TYPE DE COMPTAGE
	 * pour les DARWIN_CSV en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureDarwinCsvTypeComptageEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.typecomptage.darwincsv".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinCsvTypeComptageEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinCsvTypeComptageEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureDarwinCsvTypeComptage : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureDarwinCsvTypeComptage() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureDarwinCsvTypeComptage == null) {

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
										fournirCleNomNomenclatureDarwinCsvTypeComptage());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_TYPECOMPTAGE,
								fournirCleNomNomenclatureDarwinCsvTypeComptage(),
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
							nomNomenclatureDarwinCsvTypeComptage 
								= fournirNomNomenclatureDarwinCsvTypeComptageEnDur();

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

							nomNomenclatureDarwinCsvTypeComptage = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_TYPECOMPTAGE,
								fournirCleNomNomenclatureDarwinCsvTypeComptage(),
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
						nomNomenclatureDarwinCsvTypeComptage 
							= fournirNomNomenclatureDarwinCsvTypeComptageEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureDarwinCsvTypeComptage 
						= fournirNomNomenclatureDarwinCsvTypeComptageEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureDarwinCsvTypeComptage == null)._________

			return nomNomenclatureDarwinCsvTypeComptage;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureDarwinCsvTypeComptage()._______________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 de TYPE DE COMPTAGE 
	 * dans le DARWIN_CSV 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.typecomptage.darwincsv".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.darwincsv".<br/>
	 */
	private static String fournirCleNomNomenclatureDarwinCsvTypeComptage() {
		return "application.repertoire.ressources.nomenclatures.typecomptage.darwincsv";
	} // Fin de fournirCleNomNomenclatureDarwinCsvTypeComptage().________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour le <code>nomNomenclatureDarwinCsvTypeComptage</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureDarwinCsvTypeComptageEnDur() {
		return "2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv";
	} // Fin de fournirNomNomenclatureDarwinCsvTypeComptageEnDur().______________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le TYPE DE COMPTAGE dans un DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_TypeComptage_DarwinCsv_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureDarwinCsvTypeComptageUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureDarwinCsvTypeComptageUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureDarwinCsvTypeComptageUtf8 == null) {

				final Path pathRelatifNomenclatureTypeComptageDarwinCsv 
				= Paths.get(getNomNomenclatureDarwinCsvTypeComptage());
		
				final Path pathRelatifContextNomenclatureTypeComptageDarwinCsv 
					= getPathNomenclaturesDarwinCsvUtf8()
						.resolve(pathRelatifNomenclatureTypeComptageDarwinCsv);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureTypeComptageDarwinCsv.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureTypeComptageDarwinCsv);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureDarwinCsvTypeComptageUtf8 
					= new File(uriRessources.getPath());

				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureDarwinCsvTypeComptageUtf8
						, "Méthode getFichierNomenclatureDarwinCsvTypeComptageUtf8()");
			}
			
			return fichierNomenclatureDarwinCsvTypeComptageUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureDarwinCsvTypeComptageUtf8()._______________
	

	
	/**
	 * Getter du Nom du fichier de nomenclature du CLASSEMENT DE LA ROUTE 
	 * pour les DARWIN_CSV en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureDarwinCsvClassementRouteEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.classementroute.darwincsv".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinCsvClassementRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinCsvClassementRouteEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureDarwinCsvClassementRoute : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureDarwinCsvClassementRoute() throws Exception {

		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureDarwinCsvClassementRoute == null) {

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
										fournirCleNomNomenclatureDarwinCsvClassementRoute());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_CLASSEMENTROUTE,
								fournirCleNomNomenclatureDarwinCsvClassementRoute(),
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
							nomNomenclatureDarwinCsvClassementRoute 
								= fournirNomNomenclatureDarwinCsvClassementRouteEnDur();

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

							nomNomenclatureDarwinCsvClassementRoute = valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_CLASSEMENTROUTE,
								fournirCleNomNomenclatureDarwinCsvClassementRoute(),
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
						nomNomenclatureDarwinCsvClassementRoute 
							= fournirNomNomenclatureDarwinCsvClassementRouteEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureDarwinCsvClassementRoute 
						= fournirNomNomenclatureDarwinCsvClassementRouteEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureDarwinCsvClassementRoute == null)._________

			return nomNomenclatureDarwinCsvClassementRoute;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureDarwinCsvClassementRoute().____________________



	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 
	 * du CLASSEMENT DE LA ROUTE dans le DARWIN_CSV 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.classementroute.darwincsv".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.typecomptage.darwincsv".<br/>
	 */
	private static String fournirCleNomNomenclatureDarwinCsvClassementRoute() {
		return "application.repertoire.ressources.nomenclatures.classementroute.darwincsv";
	} // Fin de fournirCleNomNomenclatureDarwinCsvClassementRoute()._____________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureDarwinCsvClassementRoute</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureDarwinCsvClassementRouteEnDur() {
		return "2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv";
	} // Fin de fournirNomNomenclatureDarwinCsvClassementRouteEnDur().___________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la 
	 * Nomenclature pour le CLASSEMENT DE LA ROUTE dans un DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ClassementRoute_DarwinCsv_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureDarwinCsvClassementRouteUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureDarwinCsvClassementRouteUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureDarwinCsvClassementRouteUtf8 == null) {

				final Path pathRelatifNomenclatureClassementRouteDarwinCsv 
				= Paths.get(getNomNomenclatureDarwinCsvClassementRoute());
		
				final Path pathRelatifContextNomenclatureClassementRouteDarwinCsv 
					= getPathNomenclaturesDarwinCsvUtf8()
						.resolve(pathRelatifNomenclatureClassementRouteDarwinCsv);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureClassementRouteDarwinCsv.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureClassementRouteDarwinCsv);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureDarwinCsvClassementRouteUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureDarwinCsvClassementRouteUtf8
						, "Méthode getFichierNomenclatureDarwinCsvClassementRouteUtf8()");
			}
			
			return fichierNomenclatureDarwinCsvClassementRouteUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureDarwinCsvClassementRouteUtf8()._______________
	


	/**
	 * Getter du Nom du fichier de nomenclature du PROFIL EN TRAVERS
	 * pour les DARWIN_CSV en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureDarwinCsvProfilTraversEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.profiltravers.darwincsv".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinCsvProfilTraversEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinCsvProfilTraversEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureDarwinCsvProfilTravers : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureDarwinCsvProfilTravers() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureDarwinCsvProfilTravers == null) {

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
										fournirCleNomNomenclatureDarwinCsvProfilTravers());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_PROFILTRAVERS,
								fournirCleNomNomenclatureDarwinCsvProfilTravers(),
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
							nomNomenclatureDarwinCsvProfilTravers 
								= fournirNomNomenclatureDarwinCsvProfilTraversEnDur();

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

							nomNomenclatureDarwinCsvProfilTravers 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_PROFILTRAVERS,
								fournirCleNomNomenclatureDarwinCsvProfilTravers(),
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
						nomNomenclatureDarwinCsvProfilTravers 
							= fournirNomNomenclatureDarwinCsvProfilTraversEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureDarwinCsvProfilTravers 
						= fournirNomNomenclatureDarwinCsvProfilTraversEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureDarwinCsvProfilTravers == null)._________

			return nomNomenclatureDarwinCsvProfilTravers;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureDarwinCsvProfilTravers().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du PROFIL EN TRAVERS
	 * dans le DARWIN_CSV 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.profiltravers.darwincsv".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.profiltravers.darwincsv".<br/>
	 */
	private static String fournirCleNomNomenclatureDarwinCsvProfilTravers() {
		return "application.repertoire.ressources.nomenclatures.profiltravers.darwincsv";
	} // Fin de fournirCleNomNomenclatureDarwinCsvProfilTravers().__________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureDarwinCsvProfilTravers</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureDarwinCsvProfilTraversEnDur() {
		return "2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv";
	} // Fin de fournirNomNomenclatureDarwinCsvProfilTraversEnDur().________________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le PROFIL EN TRAVERS 
	 * dans un DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_ProfilTravers_DarwinCsv_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureDarwinCsvProfilTraversUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureDarwinCsvProfilTraversUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureDarwinCsvProfilTraversUtf8 == null) {

				final Path pathRelatifNomenclatureProfilTraversDarwinCsv 
				= Paths.get(getNomNomenclatureDarwinCsvProfilTravers());
		
				final Path pathRelatifContextNomenclatureProfilTraversDarwinCsv 
					= getPathNomenclaturesDarwinCsvUtf8()
						.resolve(pathRelatifNomenclatureProfilTraversDarwinCsv);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureProfilTraversDarwinCsv.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureProfilTraversDarwinCsv);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureDarwinCsvProfilTraversUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureDarwinCsvProfilTraversUtf8
						, "Méthode getFichierNomenclatureDarwinCsvProfilTraversUtf8()");
			}
			
			return fichierNomenclatureDarwinCsvProfilTraversUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureDarwinCsvProfilTraversUtf8().________
	


	/**
	 * Getter du Nom du fichier de nomenclature du SOUS RESEAU INDICE
	 * pour les DARWIN_CSV en UTF-8
	 * stocké dans application.properties.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv".<br/>
	 * <ul>
	 * <li>Essaie de fournir la valeur stockée dans 
	 * application_fr_FR.properties.</li>
	 * <li>Sinon, retourne la valeur stockée en dur 
	 * fournie par fournirNomNomenclatureDarwinCsvSousReseauIndiceEnDur().</li>
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
	 * Clé : "application.repertoire.ressources.nomenclatures.sousreseauindice.darwincsv".<br/>
	 * <br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinCsvSousReseauIndiceEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la clé n'existe pas dans le properties.<br/>
	 * - retourne la valeur en dur fournie par 
	 * fournirNomNomenclatureDarwinCsvSousReseauIndiceEnDur()
	 * , LOG.ERROR et rapporte 
	 * si la valeur associée à la clé n'existe pas dans le properties.<br/>
	 * <br/>
	 *
	 * @return nomNomenclatureDarwinCsvSousReseauIndice : String.<br/>
	 * 
	 * @throws Exception 
	 */
	public static String getNomNomenclatureDarwinCsvSousReseauIndice() throws Exception {
		
		/* Bloc synchronized. */
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {

			/* Reset du messageIndividuelRapport. */
			messageIndividuelRapport = null;

			if (nomNomenclatureDarwinCsvSousReseauIndice == null) {

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
										fournirCleNomNomenclatureDarwinCsvSousReseauIndice());

						/* Si la valeur est blank. */
						if (StringUtils.isBlank(valeur)) {

							/* Création du message. */
							messageIndividuelRapport 
							= creerMessageManqueValeur(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_SOUSRESEAUINDICE,
								fournirCleNomNomenclatureDarwinCsvSousReseauIndice(),
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
							nomNomenclatureDarwinCsvSousReseauIndice 
								= fournirNomNomenclatureDarwinCsvSousReseauIndiceEnDur();

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

							nomNomenclatureDarwinCsvSousReseauIndice 
								= valeurNettoyee;

						} // Fin de Valeur remplie dans le properties.____

					} catch (MissingResourceException mre) {

						/* Création du message. */
						messageIndividuelRapport 
							= creerMessageManqueCle(
								METHODE_GET_NOMNOMENCLATURE_DARWIN_CSV_SOUSRESEAUINDICE,
								fournirCleNomNomenclatureDarwinCsvSousReseauIndice(),
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
						nomNomenclatureDarwinCsvSousReseauIndice 
							= fournirNomNomenclatureDarwinCsvSousReseauIndiceEnDur();

					} // Fin de catch (MissingResourceException mre)._____

				} // Fin de if (getBundleApplication() != null)._____

				/* if (getBundleApplication() == null). */
				else {

					/* utilise la valeur fournie en dur. */
					nomNomenclatureDarwinCsvSousReseauIndice 
						= fournirNomNomenclatureDarwinCsvSousReseauIndiceEnDur();

				} // Fin de if (getBundleApplication() == null).___

			} // Fin de if (nomNomenclatureDarwinCsvSousReseauIndice == null)._________

			return nomNomenclatureDarwinCsvSousReseauIndice;

		} // Fin de synchronized.________________________________________

	} // Fin de getNomNomenclatureDarwinCsvSousReseauIndice().__________________________


	
	/**
	 * retourne la clé du 
	 * nom de la nomenclature en UTF-8 du SOUS RESEAU INDICE
	 * dans le DARWIN_CSV 
	 * stockée dans application_fr_FR.properties.<br/>
	 * "2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv".<br/>
	 * Clé = "application.repertoire.ressources.nomenclatures.sousreseauindice.darwincsv".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "application.repertoire.ressources.nomenclatures.sousreseauindice.darwincsv".<br/>
	 */
	private static String fournirCleNomNomenclatureDarwinCsvSousReseauIndice() {
		return "application.repertoire.ressources.nomenclatures.sousreseauindice.darwincsv";
	} // Fin de fournirCleNomNomenclatureDarwinCsvSousReseauIndice().__________________
	

	
	/**
	 * Fournit une valeur stockée en dur dans la classe 
	 * pour <code>nomNomenclatureDarwinCsvSousReseauIndice</code>.<br/>
	 *
	 * @return : String : 
	 * "2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv".<br/>
	 */
	private static String fournirNomNomenclatureDarwinCsvSousReseauIndiceEnDur() {
		return "2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv";
	} // Fin de fournirNomNomenclatureDarwinCsvSousReseauIndiceEnDur().________________


	
	/**
	 * Getter du Fichier dans les ressources de l'application 
	 * au format csv encodé en UTF-8 contenant la  
	 * Nomenclature pour le SOUS RESEAU INDICE 
	 * dans un DARWIN_CSV.<br/>
	 * <b>SINGLETON</b>.<br/>
	 * "ressources/Nomenclatures/DarwinCsv/Nomenclatures en UTF-8/
	 * 2014-07-15_Nomenclature_SousReseauIndice_DarwinCsv_Utf8.csv".<br/>
	 * <br/>
	 * - LOG.FATAL, rapporte 
	 * et jette une RunTimeException 
	 * si pFile est null, inexistant, répertoire ou vide.<br/>
	 * <br/>
	 *
	 * @return : File : fichierNomenclatureDarwinCsvSousReseauIndiceUtf8.<br/>
	 * 
	 * @throws Exception 
	 * 
	 * @throws FichierNullRunTimeException si pFile est null.<br/>
	 * @throws FichierInexistantRunTimeException si pFile est inexistant.<br/>
	 * @throws FichierRepertoireRunTimeException si pFile est un répertoire.<br/>
	 * @throws FichierVideRunTimeException si pFile est vide.<br/>
	 */
	public static File getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8() throws Exception {
				
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
			/* Instanciation du Singleton. */
			if (fichierNomenclatureDarwinCsvSousReseauIndiceUtf8 == null) {

				final Path pathRelatifNomenclatureSousReseauIndiceDarwinCsv 
				= Paths.get(getNomNomenclatureDarwinCsvSousReseauIndice());
		
				final Path pathRelatifContextNomenclatureSousReseauIndiceDarwinCsv 
					= getPathNomenclaturesDarwinCsvUtf8()
						.resolve(pathRelatifNomenclatureSousReseauIndiceDarwinCsv);
								
				final ClassLoader classloader 
					= Thread.currentThread().getContextClassLoader();
				
				final URL urlRessources 
					= classloader
						.getResource(
								pathRelatifContextNomenclatureSousReseauIndiceDarwinCsv.toString());
				
				/* traite le cas de la ressource manquante. */
				traiterRessourceManquante(
						urlRessources
							, pathRelatifContextNomenclatureSousReseauIndiceDarwinCsv);
				
				final URI uriRessources = urlRessources.toURI();
				
				fichierNomenclatureDarwinCsvSousReseauIndiceUtf8 
					= new File(uriRessources.getPath());
								
				/* LOG.FATAL, rapporte 
				 * et jette une RunTimeException 
				 * si pFile est null, inexistant, répertoire ou vide.*/
				traiterFichier(fichierNomenclatureDarwinCsvSousReseauIndiceUtf8
						, "Méthode getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8()");
			}
			
			return fichierNomenclatureDarwinCsvSousReseauIndiceUtf8;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de getFichierNomenclatureDarwinCsvSousReseauIndiceUtf8().________
	


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
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
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
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
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
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESDARWIN_CSV);
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
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESDARWIN_CSV);
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
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
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
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
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
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(CLASSE_CONFIGURATIONNOMENCLATURESDARWIN_CSV);
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
		
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
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
		
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
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
		
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
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
		
		synchronized (ConfigurationNomenclaturesDarwinCsvManager.class) {
			
			final String pathClassesString 
			= ConfigurationNomenclaturesDarwinCsvManager
				.retournerClassesSousTarget();
		
			final Path pathClasses = Paths.get(pathClassesString);
			
			final Path pathPFile = pFile.toPath();
			
			final Path pathRelatifPFile = pathClasses.relativize(pathPFile);
			
			return pathRelatifPFile;
			
		} // Fin de synchronized.________________________________________
				
	} // Fin de fournirPathRelatifSousTargetClasses(...).__________________	
	

	
} // FIN DE LA CLASSE ConfigurationNomenclaturesDarwinCsvManager.-------------
