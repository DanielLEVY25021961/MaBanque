package levy.daniel.application.model.utilitaires.gestionnairesiofichiers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.IConstantesCharsets;
import levy.daniel.application.IConstantesSautsLigne;


/**
 * class GestionnaireFichiers :<br/>
 * <ul>
 * <li>Utilitaire contenant des méthodes de gestion des fichiers pour :<br/>
 * - créer un fichier simple sur disque.<br/>
 * - détruire un fichier simple sur disque.<br/>
 * - écrire du contenu dans un fichier simple.<br/>
 * - lire le contenu d'un fichier simple texte.<br/>
 * - lire la n-iéme ligne d'un fichier simple texte.<br/>
 * - créer un répertoire et l'arborescence (chemin) nécessaire<br/>
 * - détruire un répertoire et tout son contenu.<br/>
 * - vider un répertoire de son contenu sans l'écraser.<br/>
 * - vider un répertoire de son contenu et écraser le répertoire.<br/>
 * - recopier un fichier sur le disque.<br/>
 * - recopier un répertoire et tout son contenu sur le disque.<br/>
 * - extraire et recopier l'arborescence 
 * (système de répertoires sans les fichiers simples) 
 * contenue sous un répertoire source.<br/>
 * - déplacer le contenu d'un répertoire sur le disque.<br/>
 * - concaténer des fichiers simples dans un fichier simple destination.<br/>
 * - lister les sous-répertoires contenus dans un répertoire.<br/>
 * - lister les fichiers simples contenus dans un répertoire.<br/>
 * - calculer la taille en octets du contenu d'un répertoire.<br/>
 * - renommer une liste de fichiers en otant une même partie 
 * au nom de chaque fichier de la liste.<br/>
 * - insérer une ligne dans un fichier textuel simple.<br/>
 * - retirer une ligne dans un fichier textuel simple.<br/>
 * </li>
 * <br/>
 * <ul>
 * <li>la méthode <b>creerFichierSurDisque(File pFile)</b> crée sur le disque 
 * et retourne le File (fichier simple) 
 * pFile si il n'existe pas déjà.<br/>
 * Crée sur le disque l'arborescence (chemin) 
 * de pFile si elle n'existe pas déjà.</li><br/>
 * <li>la méthode <b>ecraserFichierSurDisque(File pFile)</b> 
 * écrase un fichier existant sur le disque.<br/>
 * Elle retourne le boolean true si le fichier simple 
 * a bien été écrasé.</li><br/>
 * <li>la méthode <b>ecrireStringDansFile(
 * File pFile, String pString, Charset pCharset, String pSautLigne)</b> 
 * permet d'écrire une String pString 
 * dans le File pFile 
 * (à la fin du fichier si il est déjà rempli).<br/>
 * La String est encodée en pCharset et les sauts de ligne 
 * qu'elle contient sont substitués par pSautLigne.<br/>
 * Le fichier pFile est créé sur le disque si il n'existe pas déjà.</li><br/>
 * <li>la méthode <b>lireStringsDansFile(File pFile, Charset pCharset)</b> 
 * lit le contenu d'un fichier simple texte avec le Charset pCharset.</li><br/>
 * <li>la méthode <b>lireLigneDansFichier(
 * File pFile, Charset pCharsetLecture, int pNumLigne)</b> 
 * lit la pNumLigne-ième ligne d'un fichier simple texte.</li><br/>
 * <li>la méthode <b>creerArborescence(File pRep)</b> 
 * crée sur disque le répertoire pRep et toute l'arborescence nécessaire.<br/>
 * Elle retourne le répertoire créé sur disque 
 * si tout s'est bien passé.</li><br/>
 * <li>la méthode <b>creerArborescence(String pArborescence)</b> 
 * crée sur disque une arborescence.<br/>
 * Elle retourne un boolean true si tout se passe bien.</li><br/>
 * <li>la méthode <b>detruireArborescence(String pChemin)</b> détruit 
 * le répertoire situé à pChemin 
 * et toute l'arborescence située sous le répertoire.<br/>
 * Elle retourne un boolean true si la destruction du répertoire 
 * et de son contenu s'est bien déroulée</li><br/>
 * <li>la méthode <b>viderRepertoire(File pRep)</b> 
 * vide tout le contenu du répertoire pRep sans écraser pRep.</li><br/>
 * <li>la méthode <b>viderRepertoireADetruire(File pRep)</b> 
 * vide tout le contenu du répertoire pRep sans écraser pRep.<br/>
 * Elle retourne un boolean true si le répertoire a bien été détruit</li><br/>
 * <li>la méthode <b>ecraserRepertoireEtSousArbo(File pRep)</b> 
 * vide tout le contenu de pRep et ECRASE pRep.<br/>
 * Elle retourne le boolean true si l'écrasement de pRep 
 * et de tout son contenu a bien eu lieu.</li><br/>
 * <li>la méthode <b>copierFichierAvecRemplacement(
 * File pFileSource, File pFileDestination)</b> 
 * copie le fichier pFileSource dans pFileDestination.<br/>
 * Si pFileDestination existe déjà, il est écrasé et remplacé.</li><br/>
 * <li>la méthode <b>copierFichierSansRemplacement(
 * File pFileSource, File pFileDestination)</b> 
 * copie le fichier pFileSource dans pFileDestination.<br/>
 * Si pFileDestination existe déjà, il est retourné tel quel.</li><br/>
 * <li>la méthode <b>copierRepertoireAvecRemplacement(
 * File pRepSource, File pRepDestination)</b> recopie 
 * tout le contenu du répertoire pRepSource sous pRepDestination.<br/>
 * Elle retourne le répertoire destination copié.<br/>
 * Elle écrase le contenu du répertoire destination 
 * si il existait déjà.</li><br/>
 * <li>la méthode <b>copierRepertoireSansRemplacement(
 * File pRepSource, File pRepDestination)</b> recopie 
 * tout le contenu du répertoire pRepSource sous pRepDestination.<br/>
 * Elle retourne le répertoire destination copié ou déjà existant.<br/>
 * Elle n'écrase pas le contenu du répertoire destination si il existait 
 * déjà et le retourne tel quel.</li><br/>
 * <li>la méthode <b>copierArborescenceAvecRemplacement(
 * File pRepSource, File pRepDestination)</b> extrait et 
 * recopie toute l'arborescence (système de répertoires 
 * sans les fichiers simples) située sous pRepSource 
 * sous pRepDestination.</li><br/>
 * <li>la méthode <b>deplacerRepertoire(
 * File pRepADeplacer, String pCheminDestination)</b> 
 * déplace le contenu du répertoire pRepADeplacer 
 * sous le chemin pCheminDestination.</li><br/>
 * <li>la méthode <b>concatenerFichiers(
 * List&lt;File&gt; pListFichiersAConcatener, Charset pCharsetLecture
 * , File pFileDestination, Charset pCharsetEcriture
 * , boolean pAvecRemplacement, boolean pRetirerBOMUtf8
 * , boolean pRetirerEntete)</b> concatène des fichiers 
 * dans un fichier destination pFileDestination.</li><br/>
 * <li>la méthode <b>fournirListeSousRepertoiresDeRepertoire(File pRepertoire)</b> 
 * fournit la liste des sous-répertoires contenus 
 * dans le répertoire pRepertoire.</li><br/>
 * <li>la méthode <b>fournirListeFichiersDansRepertoire(File pRepertoire)</b> 
 * fournit la liste des fichiers simples contenus 
 * dans le répertoire pRepertoire.</li><br/>
 * <li>la méthode <b>calculerTailleRepertoire(File pRep)</b> 
 * calcule la taille en octets du contenu d'un répertoire pRep.</li><br/>
 * <li>la méthode <b>renommerFichiersEnEnlevantPartie(
 * List&lt;File&gt; pListFiles, String pPartieAOter)</b> 
 * renomme une liste de fichiers en ôtant pPartieAOter 
 * au nom de chaque fichier.</li><br/>
 * <li> la méthode <b>insererLigneDansFichier(File pFile, ...)</b> 
 * insère une ligne dans un fichier textuel simple.</li><br/>
 * <li>la méthode <b>retirerLigneDansFichier(
 * File pFile, Charset pCharsetLecture, int pNumLigne)</b> 
 * retire la ligne pNumLigne dans un fichier textuel simple.</li><br/>
 * </ul>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * récursif, appel récursif, recursif, méthode récursive,<br/>
 * DateFormat.setLenient(false), Extraire date de Calendar<br/>
 * fournir date, fournirDate(), <br/>
 * lister les fichiers dans un répertoire, <br/>
 * créer fichier, détruire fichier, erase,<br/>
 * lire contenu fichier texte, lire ligne fichier texte,<br/>
 * liste fichiers, afficher liste de fichiers,<br/>
 * Renommer fichier, renommer liste de fichiers, <br/>
 * créer répertoire sous grand-père, créer répertoire sous père, <br/>
 * copier fichier, recopier fichier,<br/> 
 * copier répertoire, recopier répertoire, <br/>
 * extraire arborescence, recopier arborescence,<br/>
 * concaténation, concaténer fichiers, concatener, <br/>
 * calculer taille contenu répertoire, <br/>
 * insérer ligne dans fichier, retirer ligne dans fichier,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 11 févr. 2016
 *
 */
public final class GestionnaireFichiers 
			implements IConstantesCharsets, IConstantesSautsLigne {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_GESTIONNAIRE_FICHIERS : String :<br/>
	 * "Classe GestionnaireFichiers".<br/>
	 */
	public static final String CLASSE_GESTIONNAIRE_FICHIERS 
		= "Classe GestionnaireFichiers";
	
	/**
	 * METHODE_CREER_FICHIER_SUR_DISQUE : String :<br/>
	 * "méthode creerFichierSurDisque(File pFile)".<br/>
	 */
	public static final String METHODE_CREER_FICHIER_SUR_DISQUE 
		= "méthode creerFichierSurDisque(File pFile)";

	/**
	 * METHODE_ECRASER_FICHIER_SUR_DISQUE : String :<br/>
	 * "méthode ecraserFichierSurDisque(File pFile)".<br/>
	 */
	public static final String METHODE_ECRASER_FICHIER_SUR_DISQUE 
		= "méthode ecraserFichierSurDisque(File pFile)";

	/**
	 * METHODE_ECRIRESTRINGDANSFILE : String :<br/>
	 * "méthode ecrireStringDansFile(
	 * File pFile, String pString, Charset pCharset)".<br/>
	 */
	public static final String METHODE_ECRIRESTRINGDANSFILE 
		= "méthode ecrireStringDansFile(File pFile, ....)";
	
	/**
	 * METHODE_ECRIREMAPDANSFILE : String :<br/>
	 * "méthode File pFile, Map<Integer, String> pMap, ...)".<br/>
	 */
	public static final String METHODE_ECRIREMAPDANSFILE 
		= "méthode File pFile, Map<Integer, String> pMap, ...)";
	
	/**
	 * METHODE_LIRE_STRINGS_DANS_FILE : String :<br/>
	 * "méthode lireStringsDansFile(File pFile, Charset pCharset)".<br/>
	 */
	public static final String METHODE_LIRE_STRINGS_DANS_FILE 
		= "méthode lireStringsDansFile(File pFile, Charset pCharset)";
	
	/**
	 * METHODE_LIRE_LIGNE_N_DANS_FICHIER : String :<br/>
	 * "méthode lireLigneDansFichier(File pFile, Charset pCharsetLecture, int pNumLigne)".<br/>
	 */
	public static final String METHODE_LIRE_LIGNE_N_DANS_FICHIER 
		= "méthode lireLigneDansFichier(File pFile, Charset pCharsetLecture, int pNumLigne)";
	
	/**
	 * METHODE_FOURNIRFILE : String :<br/>
	 * "méthode fournirFile(String pChemin, Date pDate, String pNomFichier)".<br/>
	 */
	public static final String METHODE_FOURNIRFILE 
		= "méthode fournirFile(String pChemin, Date pDate, String pNomFichier)";

	/**
	 * METHODE_CREER_ARBORESCENCE : String :<br/>
	 * "méthode creerArborescence(File pRep)".<br/>
	 */
	public static final String METHODE_CREER_ARBORESCENCE 
		= "méthode creerArborescence(File pRep)";
	
	/**
	 * METHODE_DETRUIRE_ARBORESCENCE : String :<br/>
	 * "méthode detruireArborescence(String pChemin)".<br/>
	 */
	public static final String METHODE_DETRUIRE_ARBORESCENCE 
		= "méthode detruireArborescence(String pChemin)";

	
	/**
	 * METHODE_VIDER_REPERTOIRE : String :<br/>
	 * "méthode viderRepertoireADetruire(File pFile)".<br/>
	 */
	public static final String METHODE_VIDER_REPERTOIRE 
		= "méthode viderRepertoireADetruire(File pFile)";

	/**
	 * METHODE_ECRASER_REP_ET_SOUS_ARBO : String :<br/>
	 * "méthode ecraserRepertoireEtSousArbo(File pFile)".<br/>
	 */
	public static final String METHODE_ECRASER_REP_ET_SOUS_ARBO 
		= "méthode ecraserRepertoireEtSousArbo(File pFile)";
	
	
	/**
	 * METHODE_COPIER_FICHIER_AVEC_REMPLACEMENT : String :<br/>
	 * "méthode copierFichierAvecRemplacement(File pFileSource, File pFileDestination)".<br/>
	 */
	public static final String METHODE_COPIER_FICHIER_AVEC_REMPLACEMENT 
	= "méthode copierFichierAvecRemplacement(File pFileSource, File pFileDestination)";
	
	/**
	 * METHODE_COPIER_FICHIER_SANS_REMPLACEMENT : String :<br/>
	 * "méthode copierFichierAvecRemplacement(File pFileSource, File pFileDestination)";.<br/>
	 */
	public static final String METHODE_COPIER_FICHIER_SANS_REMPLACEMENT 
	= "méthode copierFichierSansRemplacement(File pFileSource, File pFileDestination)";
	
	/**
	 * METHODE_COPIER_REPERTOIRE_AVEC_REMPLACEMENT : String :<br/>
	 * "méthode copierRepertoireAvecRemplacement(File pRepSource, File pRepDestination)".<br/>
	 */
	public static final String METHODE_COPIER_REPERTOIRE_AVEC_REMPLACEMENT 
	= "méthode copierRepertoireAvecRemplacement(File pRepSource, File pRepDestination)";
	
	/**
	 * METHODE_COPIER_REPERTOIRE_SANS_REMPLACEMENT : String :<br/>
	 * "méthode copierRepertoireSansRemplacement(File pRepSource, File pRepDestination)".<br/>
	 */
	public static final String METHODE_COPIER_REPERTOIRE_SANS_REMPLACEMENT 
	= "méthode copierRepertoireSansRemplacement(File pRepSource, File pRepDestination)";
	
	/**
	 * METHODE_COPIER_ARBORESCENCE_AVEC_REMPLACEMENT : String :<br/>
	 * "méthode copierArborescenceAvecRemplacement(File pRepSource, File pRepDestination)".<br/>
	 */
	public static final String METHODE_COPIER_ARBORESCENCE_AVEC_REMPLACEMENT 
	= "méthode copierArborescenceAvecRemplacement(File pRepSource, File pRepDestination)";

	/**
	 * METHODE_CONCATENER_FICHIERS : String :<br/>
	 * "méthode concatenerFichiers(...)".<br/>
	 */
	public static final String METHODE_CONCATENER_FICHIERS 
		= "méthode concatenerFichiers(...)";
	
	/**
	 * METHODE_CALCULER_TAILLE_REPERTOIRE : String :<br/>
	 * "méthode calculerTailleRepertoire(File pRep)".<br/>
	 */
	public static final String METHODE_CALCULER_TAILLE_REPERTOIRE 
		= "méthode calculerTailleRepertoire(File pRep)";
	
	/**
	 * METHODE_INSERER_LIGNE : String :<br/>
	 * "méthode insererLigneDansFichier(File pFile, ...)".<br/>
	 */
	public static final String METHODE_INSERER_LIGNE 
		= "méthode insererLigneDansFichier("
				+ "File pFile, Charset pCharsetLecture, int pNumLigne"
				+ ", Charset pCharsetEcriture, String pLigneAInserer";

	/**
	 * METHODE_RETIRER_LIGNE : String :<br/>
	 * "méthode retirerLigneDansFichier(
	 * File pFile, Charset pCharsetLecture, int pNumLigne)".<br/>
	 */
	public static final String METHODE_RETIRER_LIGNE 
		= "méthode retirerLigneDansFichier("
				+ "File pFile, Charset pCharsetLecture, int pNumLigne)";
	
	/**
	 * METHODE_COMPTER_LIGNES : String :<br/>
	 * "méthode compterLignes(File pFile)".<br/>
	 */
	public static final String METHODE_COMPTER_LIGNES 
		= "méthode compterLignes(File pFile)";
	
	
	/**
	 * MESSAGE_FICHIER_NULL : String :<br/>
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si le fichier est null.<br/>
	 * "Le fichier passé en paramètre est null".<br/>
	 */
	public static final String MESSAGE_FICHIER_NULL 
		= "Le fichier passé en paramètre est null";
		
	/**
	 * MESSAGE_FICHIER_INEXISTANT : String :<br/>
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si le fichier est inexistant.<br/>
	 * "Le fichier passé en paramètre est inexistant : "
	 */
	public static final String MESSAGE_FICHIER_INEXISTANT 
		= "Le fichier passé en paramètre est inexistant : ";
	
	/**
	 * MESSAGE_FICHIER_REPERTOIRE : String :<br/>
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si le fichier est un répertoire.<br/>
	 * "Le fichier passé en paramètre est un répertoire : ".<br/>
	 */
	public static final String MESSAGE_FICHIER_REPERTOIRE 
		= "Le fichier passé en paramètre est un répertoire : ";

	/**
	 * MESSAGE_STRING_BLANK : String :<br/>
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si la String passée en paramètre est blank.<br/>
	 * "La chaine de caractères passée en paramètre est blank (null ou vide) : "
	 */
	public static final String MESSAGE_STRING_BLANK 
	= "La chaine de caractères passée en paramètre est blank (null ou vide) : ";
	
	
	/**
	 * MESSAGE_EXCEPTION : String :<br/>
	 * "Exception GRAVE : ".<br/>
	 */
	public static final String MESSAGE_EXCEPTION = "Exception GRAVE : ";
	

	
	//*****************************************************************/
	//*************************** LOCALES *****************************/
	//*****************************************************************/
	
	/**
	 * LOCALE_FR_FR : Locale :<br/>
	 * new Locale("fr", "FR").<br/>
	 */
	public static final Locale LOCALE_FR_FR = new Locale("fr", "FR");

	
	/**
	 * LOCALE_SYSTEM : Locale :<br/>
	 * Locale de la plateforme.<br/>
	 * Locale.getDefault().<br/>
	 */
	public static final Locale LOCALE_SYSTEM = Locale.getDefault();
	

	//*****************************************************************/
	//************************* DATEFORMATS ***************************/
	//*****************************************************************/

	/**
	 * PATTERN_DF_DATE_LEXICOGRAPHIQUE : String :<br/>
	 * Format lexicographique des dates 
	 * comme "2012-01-16" pour le
	 * 16 Janvier 2012.<br/>
	 * "yyyy-MM-dd".<br/>
	 */
	public static final String PATTERN_DF_DATE_LEXICOGRAPHIQUE 
		= "yyyy-MM-dd";
	
		
	/**
	 * PATTERN_DF_DATETIME_LEXICOGRAPHIQUE : String :<br/>
	 * Format lexicographique des dates avec time 
	 * comme "2012-01-16_18-09-55" pour le
	 * 16 Janvier 2012 à 18 heures 9 minutes et 55 secondes.<br/>
	 * "yyyy-MM-dd_HH-mm-ss".<br/>
	 */
	public static final String PATTERN_DF_DATETIME_LEXICOGRAPHIQUE 
		= "yyyy-MM-dd_HH-mm-ss";
	

	/**
	 * PATTERN_DF_DATE_HEURE_MINUTE_SECONDE_UNDERSCORE : String :<br/>
	 * Format concentré des dates avec heures et secondes
	 * comme "2012-01-16_18-09-55" pour le
	 * 16 Janvier 2012 à 18 heures 9 minutes et 55 secondes.<br/>
	 * "yyyy-MM-dd_HH-mm-ss".<br/>
	 */
	public static final String PATTERN_DF_DATE_HEURE_MINUTE_SECONDE_UNDERSCORE 
		= "yyyy-MM-dd_HH-mm-ss";
	

	/**
	 * PATTERN_DF_DATE_FRANCAISE : String :<br/>
	 * Format classique des dates françaises comme
	 * '25/02/1961'.<br/>
	 * "dd/MM/yyyy".<br/>
	 */
	public static final String PATTERN_DF_DATE_FRANCAISE = "dd/MM/yyyy";
	
	
	/**
	 * PATTERN_DF_DATE_COMPLETE_FRANCAISE : String :<br/>
	 * Format complet des dates françaises comme
	 * 'samedi 25 février 1961'.<br/>
	 * "EEEE' 'dd' 'MMMM' 'yyyy".<br/>
	 */
	public static final String PATTERN_DF_DATE_COMPLETE_FRANCAISE 
		= "EEEE' 'dd' 'MMMM' 'yyyy";
	

	/**
	 * PATTERN_DF_DATETIME_FRANCAISE : String :<br/>
	 * Format classique des dates-heures françaises comme
	 * '25/02/1961-12:27:07'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss".<br/>
	 */
	public static final String PATTERN_DF_DATETIME_FRANCAISE 
		= "dd/MM/yyyy-HH:mm:ss";
	

	/**
	 * PATTERN_DF_DATETIMEMILLI_FRANCAISE : String :<br/>
	 * Format des dates-heures françaises avec millisecondes comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 */
	public static final String PATTERN_DF_DATETIMEMILLI_FRANCAISE 
		= "dd/MM/yyyy-HH:mm:ss.SSS";


	/**
	 * PATTERN_DF_MOIS_ANNEE : String :<br/>
	 * Format des dates françaises avec mois-année comme
	 * 'février 1961'.<br/>
	 * "MMMM' 'yyyy".<br/>
	 */
	public static final String PATTERN_DF_MOIS_ANNEE 
		= "MMMM' 'yyyy";
	

	/**
	 * PATTERN_DF_MOIS_ANNEE_SIMPLE : String :<br/>
	 * Format des dates françaises avec mois simplifié-année comme
	 * '02/1961'.<br/>
	 * "MM/yyyy".<br/>
	 */
	public static final String PATTERN_DF_MOIS_ANNEE_SIMPLE 
		= "MM/yyyy";
	

	/**
	 * PATTERN_DF_ANNEE : String :<br/>
	 * Format des dates avec juste l'année comme "1961".<br/>
	 * "yyyy".<br/>
	 */
	public static final String PATTERN_DF_ANNEE = "yyyy";
	

	/**
	 * PATTERN_DF_DATE_AVEC_HEURE_MINUTE_SECONDE : GestionnaireFichiers :<br/>
	 * Format classique des dates avec heures et secondes
	 * comme "2012-01-16 à 18 heures,09 minutes,55 secondes" pour le
	 * 16 Janvier 2012 à 18 heures 9 minutes et 55 secondes.<br/>
	 * "yyyy-MM-dd' à 'HH' heures,'mm' minutes,'ss' secondes'".<br/>
	 */
	public static final String PATTERN_DF_DATE_AVEC_HEURE_MINUTE_SECONDE 
		= "yyyy-MM-dd' " 
			+ "à 'HH' heures,'mm' minutes,'ss' secondes'";

	
	/**
	 * PATTERN_DF_DATE_HEURE_MINUTE_SECONDE : GestionnaireFichiers :<br/>
	 * Format concentré des dates avec heures et secondes
	 * comme "2012-01-16:18-09-55" pour le
	 * 16 Janvier 2012 à 18 heures 9 minutes et 55 secondes.<br/>
	 * "yyyy-MM-dd:HH-mm-ss".<br/>
	 */
	public static final String PATTERN_DF_DATE_HEURE_MINUTE_SECONDE 
		= "yyyy-MM-dd:HH-mm-ss";
	
	 
	/**
	 * PATTERN_SSDFYYYMMDD : GestionnaireFichiers :<br/>
	 * "yyyy-MM-dd" comme "2011-05-26" pour 26 Mai 2011.<br/>
	 */
	public static final String PATTERN_SSDFYYYMMDD 
		= "yyyy-MM-dd";
	
	
	/**
	 * PATTERN_SSDFYYYMMDDHHMMSS : GestionnaireFichiers :<br/>
	 * "yyyy-MM-dd HH:mm:ss" comme "2011-05-26 17:41:07"
	 * pour 26 Mai 2011 à 17h 41mn et7s.<br/>
	 */
	public static final String PATTERN_SSDFYYYMMDDHHMMSS 
		= "yyyy-MM-dd HH:mm:ss";
	

	/**
	 * PATTERN_SSDFDDMMYYYY : GestionnaireFichiers :<br/>
	 * "ddMMyyyy" comme "26052011" pour 26 Mai 2011.<br/>
	 */
	public static final String PATTERN_SSDFDDMMYYYY 
		= "ddMMyyyy";

	


	//*****************************************************************/
	//**************************** CHARSET ****************************/
	//*****************************************************************/
	// définis dans IConstantesCharsets
	
	
	//*****************************************************************/
	//**************************** BOM_UTF-8 **************************/
	//*****************************************************************/
	/**
	 * BOM_UTF : char :<br/>
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	public static final char BOM_UTF_8 = '\uFEFF';

	
	//*****************************************************************/
	//********************* SAUTS DE LIGNE ****************************/
	//*****************************************************************/
	// définis dans IConstantesSautsLigne

	
	//*****************************************************************/
	//********************* CARACTERES UTILES *************************/
	//*****************************************************************/
	
	/**
	 * UNDERSCORE : char :<br/>
	 * '_'.<br/>
	 */
	public static final char UNDERSCORE = '_';
	
	
	/**
	 * POINT : char :<br/>
	 * '.'.<br/>
	 */
	public static final char POINT = '.';

	
	/**
	 * FILE_SEPARATOR : String :<br/>
	 * "file.separator".<br/>
	 */
	public static final String FILE_SEPARATOR 
		= "file.separator";
	
	
	/**
	 * SEPARATEUR_FILE : String :<br/>
	 * "\\".<br/>
	 */
	public static final String SEPARATEUR_FILE = "\\";
	
	
	/**
	 * SEP_REP : String :<br/>
	 * Séparateur Java pour les répertoires "\\".<br/>
	 */
	public static final String SEP_REP = "\\";
	

	/**
	 * SEP_MOINS : String :<br/>
	 * " - ".<br/>
	 */
	public static final String SEP_MOINS = " - ";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(GestionnaireFichiers.class);

	
	// *************************METHODES************************************/
	
	
	/**
	 * method CONSTRUCTEUR GestionnaireFichiers() :
	 * Constructeur private pour empêcher l'instanciation.
	 */
	private GestionnaireFichiers() {
		super();
	} // Fin de CONSTRUCTEUR GestionnaireFichiers()._______________________


	
	/**
	 * method creerFichierSurDisque(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Crée sur le disque et retourne le File (Fichier simple vide) pFile 
	 * si il n'existe pas déjà.</li>
	 * <li>Crée le chemin (arborescence) vers le fichier à créer 
	 * si il n'existe pas déjà sur le disque.</li>
	 * <li>Retourne pFile (fichier simple vide) 
	 * créé sur disque et donc existant.</li>
	 * <li>Ne peut JAMAIS écraser un fichier existant 
	 * puisque cette méthode retourne le fichier existant.</li>
	 * </ul>
	 * - retourne null et LOG INFO si pFile est null.<br/>
	 * - retourne pFile si celui-ci est déjà existant sur le disque.<br/>
	 * <br/>
	 *
	 * @param pFile : File : Fichier à créer sur disque.<br/>
	 * 
	 * @return : File : pFile créé sur disque et donc existant.<br/>
	 */
	public static File creerFichierSurDisque(
			final File pFile) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si le pFile est null. */
			if (pFile == null) {
				
				/* LOG de niveau INFO. */
				loggerInfo(
						fournirNomClasseConcrete()
							, METHODE_CREER_FICHIER_SUR_DISQUE
								, MESSAGE_FICHIER_NULL);
				
				/* retour de null. */
				return null;
			}
			
			/* retourne pFile si celui-ci est déjà existant sur le disque. */
			if (pFile.exists()) {
				return pFile;
			}
						
			try {
				
				/* Récupération du path de pFile. */
				final Path pathFile = pFile.toPath();
				
				if (pathFile == null) {
					return null;
				}
				
				/* Récupération du parent de pFile. */
				final File repParentFile = pFile.getParentFile();
				
				if (repParentFile == null) {
					return null;
				}
				
				/* Récupération du Path du répertoire parent de pFile. */
				final Path pathRepParentFile = repParentFile.toPath();
				
				/* Crée le chemin (arborescence) du fichier à créer 
				 * si il n'existe pas déjà sur le disque. */
				if (!repParentFile.exists()) {
					Files.createDirectories(pathRepParentFile);
				}
				
				/* Création du Fichier sur disque si il n'existe pas déjà. */
				if (!pFile.exists()) {
					Files.createFile(pathFile);
				}
								
			} catch (IOException ioe) {
				
				loggerError(
						fournirNomClasseConcrete()
							, METHODE_CREER_FICHIER_SUR_DISQUE
								, ioe);
				
				return null;
				
			}
			
			return pFile;
			
		} // Fin du bloc static synchronized.________________________
				
	} // Fin de creerSurDisqueFile(
	 // File pFile)._______________________________________________________
	

	
	/**
	 * method creerFichierSurDisque(
	 * String pCheminFichier) :<br/>
	 * <ul>
	 * <li>Crée sur le disque et retourne le File (Fichier simple vide) 
	 * situé à pCheminFichier si il n'existe pas déjà.</li>
	 * <li>Crée le chemin (arborescence) vers le fichier à créer 
	 * si il n'existe pas déjà sur le disque.</li>
	 * <li>Retourne le fichier situé à pCheminFichier (fichier simple vide) 
	 * créé sur disque et donc existant.</li>
	 * <li>Ne peut JAMAIS écraser un fichier existant 
	 * puisque cette méthode retourne le fichier existant.</li>
	 * </ul>
	 * - Retourne null si pCheminFichier est blank.<br/>
	 * - retourne le fichier situé à pCheminFichier 
	 * si celui-ci est déjà existant sur le disque.<br/>
	 * <br/>
	 *
	 * @param pCheminFichier : String : le chemin complet du 
	 * Fichier à créer sur disque.<br/>
	 * 
	 * @return : File : fichier situé à pCheminFichier 
	 * créé sur disque et donc existant.<br/>
	 */
	public static File creerFichierSurDisque(
			final String pCheminFichier) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pCheminFichier est blank. */
			if (StringUtils.isBlank(pCheminFichier)) {
				return null;
			}
			
			final File fichier = new File(pCheminFichier);
			
			return creerFichierSurDisque(fichier);
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de creerFichierSurDisque(
	 // String pCheminFichier).____________________________________________
	
	
	
	/**
	 * method ecraserFichierSurDisque(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Ecrase un fichier simple existant sur le disque.</li>
	 * </ul>
	 * - Retourne false si pFile est null.<br/>
	 * - Retourne false si pFile n'existe pas sur le disque.<br/>
	 * - Retourne false si pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier simple à écraser sur le disque.<br/>
	 * 
	 * @return : boolean : true si le fichier simple
	 * a bien été détruit sur le disque.<br/>
	 */
	public static boolean ecraserFichierSurDisque(
			final File pFile) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne false si pFile est null. */
			if (pFile == null) {
				return false;
			}
			
			/* Retourne false si pFile n'existe pas sur le disque. */
			if (!pFile.exists()) {
				return false;
			}
			
			/* Retourne false si pFile est un répertoire. */
			if (pFile.isDirectory()) {
				return false;
			}
			
			final Path pathFile = pFile.toPath();
			
			if (pathFile == null) {
				return false;
			}
			
			try {
				
				// DESTRUCTION DU FICHIER SIMPLE. ************
				Files.deleteIfExists(pathFile);
				
				return true; 
				
			} catch (IOException ioe) {
				
				/* LOG de niveau ERROR. */
				loggerError(fournirNomClasseConcrete()
						, METHODE_ECRASER_FICHIER_SUR_DISQUE
							, ioe);
				
				return false;
				
			}
			
		}  // Fin du bloc static synchronized.________________________
		
	} // Fin de ecraserFichierSurDisque(
	 // File pFile)._______________________________________________________
	

	
	/**
	 * method ecraserFichierSurDisque(
	 * String pCheminFichier) :<br/>
	 * <ul>
	 * <li>Ecrase un fichier simple existant 
	 * sur le disque à la position pCheminFichier.</li>
	 * </ul>
	 * - Retourne false si pCheminFichier est blank.<br/>
	 * - Retourne false si le fichier simple situé à pCheminFichier 
	 * n'existe pas sur le disque.<br/>
	 * - Retourne false si pCheminFichier pointe sur un répertoire.<br/>
	 * <br/>
	 *
	 * @param pCheminFichier : String : 
	 * chemin du fichier simple à écraser sur le disque.<br/>
	 * 
	 * @return : boolean : true si le fichier simple
	 * a bien été détruit sur le disque.<br/>
	 */
	public static boolean ecraserFichierSurDisque(
			final String pCheminFichier) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne false si pCheminFichier est blank. */
			if (StringUtils.isBlank(pCheminFichier)) {
				return false;
			}
			
			final File fichier = new File(pCheminFichier);
			
			/* Retourne false si le fichier simple situé à pCheminFichier 
			 * n'existe pas sur le disque.*/
			if (!fichier.exists()) {
				return false;
			}
			
			/* Retourne false si pCheminFichier pointe sur un répertoire. */
			if (fichier.isDirectory()) {
				return false;
			}
			
			return ecraserFichierSurDisque(fichier);
			
		}  // Fin du bloc static synchronized.________________________
		
	} // Fin de ecraserFichierSurDisque(
	 // String pCheminFichier).____________________________________________
	

	
	/**
	 * method ecrireStringDansFileUtf8(
	 * File pFile
	 * , String pString) :<br/>
	 * <ul>
	 * <li>Ecrit la String pString à la fin de pFile 
	 * avec un encodage UTF-8 
	 * et en substituant 
	 * les sauts de ligne déjà existants 
	 * dans pString par les 
	 * sauts de ligne de la plateforme (NEWLINE).</li>
	 * <li>N'efface ni le fichier ni son contenu 
	 * si il est déjà existant.</li>
	 * <br/>
	 * <ul>
	 * <li>Crée pFile sur disque si il n'existe pas.</li>
	 * <li>Crée sur disque les répertoires parents de pFile 
	 * (arborescence) si il n'existent pas.</li>
	 * <li>Ecrit la String pString à la fin du fichier 
	 * pFile (utilisation de FileOutputStream(pFile, true)).</li>
	 * <li>Ne rajoute pas automatiquement 
	 * un saut de ligne NEWLINE à la fin de pString.</li>
	 * <li>Substitue automatiquement les sauts de ligne 
	 * de la plateforme (NEWLINE) aux sauts de ligne 
	 * EXISTANTS dans pString si nécessaire.</li>
	 * <li>Utilise FileOutputStream, 
	 * new OutputStreamWriter(fileOutputStream, CHARSET_UTF8) 
	 * et BufferedWriter pour écrire.</li>
	 * <li>Ecriture dans un fichier, écriture sur disque.</li>
	 * <li>Passe automatiquement le Charset à CHARSET_UTF8.</li>
	 * <li>Passe automatiquement le saut de ligne à NEWLINE 
	 * (saut de ligne de la plateforme).</li>
	 * </ul>
	 * </ul>
	 * <br/>
	 * - Retourne null si pFile est null.<br/>
	 * - retourne null si le pFile est un répertoire.<br/>
	 * - retourne null si pString est blank.<br/>
	 * - retourne null en cas d'Exception loggée 
	 * (FileNotFoundException, IOException).<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dans lequel on écrit.<br/>
	 * @param pString : String : String que l'on copie dans pFile.<br/>
	 * 
	 * @return : File : Le fichier dans lequel on a écrit pString.<br/>
	 */
	public static File ecrireStringDansFileUtf8(
			final File pFile
				, final String pString) {
				
		return ecrireStringDansFile(pFile, pString, CHARSET_UTF8, NEWLINE);
		
	} // Fin de ecrireStringDansFileUtf8(
	 // String pString).___________________________________________________
	

	
	/**
	 * method ecrireStringDansFile(
	 * File pFile
	 * , String pString
	 * , Charset pCharset
	 * , String pSautLigne) :<br/>
	 * <ul>
	 * <li>Ecrit la String pString à la fin du File pFile 
	 * avec un encodage pCharset et en substituant 
	 * les sauts de ligne déjà existants 
	 * dans pString par pSautLigne.</li>
	 * <li>N'efface ni le fichier ni son contenu 
	 * si il est déjà existant.</li>
	 * <br/>
	 * <ul>
	 * <li>Crée pFile sur disque si il n'existe pas.</li>
	 * <li>Crée sur disque les répertoires parents de pFile 
	 * (arborescence) si il n'existent pas.</li>
	 * <li>Ecrit la String pString à la fin du fichier 
	 * pFile si pFile est déjà existant et rempli
	 * (utilisation de FileOutputStream(pFile, true)).</li>
	 * <li>Ne rajoute pas automatiquement 
	 * un saut de ligne à la fin de pString.</li>
	 * <li>Substitue automatiquement pSautLigne aux sauts de ligne 
	 * EXISTANTS dans pString si nécessaire.</li>
	 * <li>Utilise FileOutputStream, 
	 * new OutputStreamWriter(fileOutputStream, charset) 
	 * et BufferedWriter pour écrire.</li>
	 * <li>Ecriture dans un fichier, écriture sur disque.</li>
	 * <li>Passe automatiquement le Charset à CHARSET_UTF8 
	 * si pCharset est null.</li>
	 * <li>Passe automatiquement le saut de ligne à NEWLINE 
	 * (saut de ligne de la plateforme) si pSautLigne est blank.</li>
	 * </ul>
	 * </ul>
	 * <br/>
	 * - retourne null si le pFile est null.<br/>
	 * - retourne null si le pFile est un répertoire.<br/>
	 * - retourne null si pString est blank.<br/>
	 * - retourne null en cas d'Exception loggée 
	 * (FileNotFoundException, IOException).<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dans lequel on écrit.<br/>
	 * @param pString : String : String que l'on copie dans pFile.<br/>
	 * @param pCharset : Charset : Charset pour encoder le fichier.<br/>
	 * @param pSautLigne : String : Saut de ligne que l'on veut 
	 * dans pFile de sortie 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix).<br/>
	 * 
	 * @return : File : Le fichier dans lequel on a écrit pString.<br/>
	 */
	public static File ecrireStringDansFile(
			final File pFile
				, final String pString
					, final Charset pCharset
						, final String pSautLigne) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si le pFile est null. */
			if (pFile == null) {
				
				/* LOG de niveau INFO. */
				loggerInfo(
						fournirNomClasseConcrete()
							, METHODE_ECRIRESTRINGDANSFILE
								, MESSAGE_FICHIER_NULL);
				
				/* retour de null. */
				return null;
				
			} // Fin de if (pFile == null).______________________
			
			/* retourne null si le pFile est un répertoire. */
			if (pFile.isDirectory()) {
				
				/* LOG de niveau INFO. */
				loggerInfo(
						fournirNomClasseConcrete()
							, METHODE_ECRIRESTRINGDANSFILE
								, MESSAGE_FICHIER_REPERTOIRE
									, pFile.getAbsolutePath());
				
				/* retour de null. */
				return null;
				
			} // Fin de if (pFile.isDirectory())._________________
			
			/* retourne null si pString est blank. */
			if (StringUtils.isBlank(pString)) {
				
				/* LOG de niveau INFO. */
				loggerInfo(
						fournirNomClasseConcrete()
							, METHODE_ECRIRESTRINGDANSFILE
								, MESSAGE_STRING_BLANK
									, pString);
				
				return null;
				
			} // Fin de if (StringUtils.isBlank(pString)).________

			
			final Path pathFichier = pFile.toPath();
			
			final Path pathRepertoirePere = pathFichier.getParent();
			
			if (pathRepertoirePere == null) {
				return null;
			}
			
			final File repertoirePere = pathRepertoirePere.toFile();
					
			/* Crée pFile sur disque si il n'existe pas. */
			if (!pFile.exists()) {
							
				try {
					
					/* Crée sur disque les répertoires parents de pFile 
					 * (arborescence) si il n'existent pas. */
					if (!repertoirePere.exists()) {
						Files.createDirectories(pathRepertoirePere);
					}
					
					/* Crée le fichier sur disque si il n'existe pas. */
					Files.createFile(pathFichier);
					
				} catch (IOException ioe) {
					
					/* LOG de niveau Error. */
					loggerError(
							fournirNomClasseConcrete()
								, METHODE_ECRIRESTRINGDANSFILE
									, ioe);
					
					/* retour de null. */
					return null;
				}
				
			} // Fin de if (!pFile.exists())._____________________
			
			
			
			Charset charset = null;
			
			/* Passe automatiquement le charset à UTF-8 si pCharset est null. */
			if (pCharset == null) {
				charset = CHARSET_UTF8;
			}
			else {
				charset = pCharset;
			}
			
			String sautLigne = null;
			
			/* Passe automatiquement le saut de ligne à NEWLINE 
			 * (saut de ligne de la plateforme) si pSautLigne est blank. */
			if (StringUtils.isBlank(pSautLigne)) {
				sautLigne = NEWLINE;
			} else {
				sautLigne = pSautLigne;
			}
			
			// ECRITURE SUR DISQUE ***************
			FileOutputStream fileOutputStream = null;
			OutputStreamWriter outputStreamWriter = null;
			BufferedWriter bufferedWriter = null;
			
			try {
				
				/* Ouverture d'un FileOutputStream sur le fichier. */
				/* Ecrit la String pString à la fin du fichier pFile 
				 * si pFile est déjà existant et rempli sur le disque. */
				fileOutputStream 
					= new FileOutputStream(pFile, true);
				
				/* Ouverture d'un OutputStreamWriter 
				 * sur le FileOutputStream en lui passant le Charset. */
				outputStreamWriter 
					= new OutputStreamWriter(fileOutputStream, charset);
				
				/* Ouverture d'un tampon d'écriture 
				 * BufferedWriter sur le OutputStreamWriter. */
				bufferedWriter 
					= new BufferedWriter(outputStreamWriter);
				
				// ECRITURE.
				/* Substitue automatiquement sautLigne aux sauts de ligne 
				 * dans pString si nécessaire. */
				bufferedWriter.write(substituerSautLigne(pString, sautLigne));
				bufferedWriter.flush();
				
				// Retour du fichier. 
				return pFile;
				
			} catch (FileNotFoundException fnfe) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
							, MESSAGE_EXCEPTION				
								, fnfe);
				
				/* retour de null. */
				return null;
				
			} catch (IOException ioe) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
							, MESSAGE_EXCEPTION				
								, ioe);
				
				/* retour de null. */
				return null;
			}
			
			finally {
				
				if (bufferedWriter != null) {
					try {
						
						bufferedWriter.close();
						
					} catch (IOException ioe1) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
									, MESSAGE_EXCEPTION				
										, ioe1);
					}
				} // Fin de if (bufferedWriter != null).__________
				
				if (outputStreamWriter != null) {
					try {
						
						outputStreamWriter.close();
						
					} catch (IOException ioe2) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
									, MESSAGE_EXCEPTION				
										, ioe2);
					}
				} // Fin de if (outputStreamWriter != null).______
				
				if (fileOutputStream != null) {
					try {
						
						fileOutputStream.close();
						
					} catch (IOException ioe3) {
						
						//* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
									, MESSAGE_EXCEPTION				
										, ioe3);
					}
				}
				
			} // Fin du finally.____________________________
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de ecrireStringDansFile(...)._________________________________
	

	
	/**
	 * method ecrireMapDansFile(
	 * File pFile
	 * , Map&lt;Integer, String&gt; pMap
	 * , Charset pCharset
	 * , String pSautDeLigne
	 * , boolean pAjouterAFinFichier) :<br/>
	 * <ul>
	 * <li><b>Ecrit</b> les lignes (sans les numéros de ligne) 
	 * d'une Map&lt;Integer, String&gt; dans un <b>fichier 
	 * pFile sur disque</b>.</li>
	 * <li><b>Encode</b> les lignes écrites dans pFile avec pCharset.</li>
	 * <li><b>Crée pFile</b> (et toute son arborescence) 
	 * sur disque si il n'existe pas déjà.</li>
	 * <li>Choisit automatiquement le Charset_UTF8 si pCharset est null.</li>
	 * <li>Choisit automatiquement le saut de ligne de la plateforme 
	 * (Newline) si pSautDeLigne est blank.</li>
	 * <li>Ecrit à la fin du fichier pFile si il était existant 
	 * et que pAjouterAFinFichier vaut true.</li>
	 * <ul>
	 * <br/>
	 * <li>retourne null si le pFile est null.</li>
	 * <li>retourne null si le pFile est un répertoire.</li>
	 * </ul>
	 * </ul>
	 *
	 * @param pFile
	 * @param pMap
	 * @param pCharset
	 * @param pSautDeLigne
	 * @param pAjouterAFinFichier
	 * @return : File :  .<br/>
	 */
	public static File ecrireMapDansFile(
			final File pFile 
			, final Map<Integer, String> pMap
			, final Charset pCharset
			, final String pSautDeLigne
			, final boolean pAjouterAFinFichier) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si le pFile est null. */
			if (pFile == null) {
				
				/* LOG de niveau INFO. */
				loggerInfo(
						fournirNomClasseConcrete()
							, METHODE_ECRIREMAPDANSFILE
								, MESSAGE_FICHIER_NULL);
				
				/* retour de null. */
				return null;
				
			} // Fin de if (pFile == null).______________________
			
			/* retourne null si le pFile est un répertoire. */
			if (pFile.isDirectory()) {
				
				/* LOG de niveau INFO. */
				loggerInfo(
						fournirNomClasseConcrete()
							, METHODE_ECRIREMAPDANSFILE
								, MESSAGE_FICHIER_REPERTOIRE
									, pFile.getAbsolutePath());
				
				/* retour de null. */
				return null;
				
			} // Fin de if (pFile.isDirectory())._________________
			
			final Path pathFichier = pFile.toPath();
			
			final Path pathRepertoirePere = pathFichier.getParent();
			
			if (pathRepertoirePere == null) {
				return null;
			}
			
			final File repertoirePere = pathRepertoirePere.toFile();
					
			/* Crée pFile sur disque si il n'existe pas. */
			if (!pFile.exists()) {
							
				try {
					
					/* Crée sur disque les répertoires parents de pFile 
					 * (arborescence) si il n'existent pas. */
					if (!repertoirePere.exists()) {
						Files.createDirectories(pathRepertoirePere);
					}
					
					/* Crée le fichier sur disque si il n'existe pas. */
					Files.createFile(pathFichier);
					
				} catch (IOException ioe) {
					
					/* LOG de niveau Error. */
					loggerError(
							fournirNomClasseConcrete()
								, METHODE_ECRIREMAPDANSFILE
									, ioe);
					
					/* retour de null. */
					return null;
				}
				
			} // Fin de if (!pFile.exists())._____________________
			
			Charset charset = null;
			
			/* Passe automatiquement le charset à UTF-8 
			 * si pCharset est null. */
			if (pCharset == null) {
				charset = CHARSET_UTF8;
			}
			else {
				charset = pCharset;
			}
			
			String sautLigne = null;
			
			/* Passe automatiquement le saut de ligne à NEWLINE 
			 * (saut de ligne de la plateforme) si pSautDeLigne est blank. */
			if (StringUtils.isBlank(pSautDeLigne)) {
				sautLigne = NEWLINE;
			} else {
				sautLigne = pSautDeLigne;
			}
			
			// ECRITURE SUR DISQUE ***************
			FileOutputStream fileOutputStream = null;
			OutputStreamWriter outputStreamWriter = null;
			BufferedWriter bufferedWriter = null;
			
			try {
				
				/* Ouverture d'un FileOutputStream sur le fichier. */
				fileOutputStream 
					= new FileOutputStream(pFile, pAjouterAFinFichier);
				
				/* Ouverture d'un OutputStreamWriter 
				 * sur le FileOutputStream en lui passant le Charset. */
				outputStreamWriter 
					= new OutputStreamWriter(fileOutputStream, charset);
				
				/* Ouverture d'un tampon d'écriture 
				 * BufferedWriter sur le OutputStreamWriter. */
				bufferedWriter 
					= new BufferedWriter(outputStreamWriter);
				
				// ECRITURE.
				
				final Set<Entry<Integer, String>> entrySet 
					= pMap.entrySet();
				final Iterator<Entry<Integer, String>> ite 
					= entrySet.iterator();
				
				// PARCOURS DE LA MAP.
				while (ite.hasNext()) {
					
					final Entry<Integer, String> entry = ite.next();
					final String stringLue = entry.getValue();
					
					bufferedWriter.write(stringLue);
					bufferedWriter.write(sautLigne);
					
				} // FIN DU PARCOURS DE LA MAP.________________
				
				/* Vide le tampon. */
				bufferedWriter.flush();
				
				// Retour du fichier. 
				return pFile;
				
			} catch (FileNotFoundException fnfe) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
							, MESSAGE_EXCEPTION				
								, fnfe);
				
				/* retour de null. */
				return null;
				
			} catch (IOException ioe) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
							, MESSAGE_EXCEPTION				
								, ioe);
				
				/* retour de null. */
				return null;
			}
			
			finally {
				
				if (bufferedWriter != null) {
					try {
						
						bufferedWriter.close();
						
					} catch (IOException ioe1) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
									, MESSAGE_EXCEPTION				
										, ioe1);
					}
				} // Fin de if (bufferedWriter != null).__________
				
				if (outputStreamWriter != null) {
					try {
						
						outputStreamWriter.close();
						
					} catch (IOException ioe2) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
									, MESSAGE_EXCEPTION				
										, ioe2);
					}
				} // Fin de if (outputStreamWriter != null).______
				
				if (fileOutputStream != null) {
					try {
						
						fileOutputStream.close();
						
					} catch (IOException ioe3) {
						
						//* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
									, MESSAGE_EXCEPTION				
										, ioe3);
					}
				}
				
			} // Fin du finally.____________________________

		} // Fin du bloc static synchronized.________________________
		
	} // Fin de ecrireMapDansFile(
	 // File pFile
	 // , Map<Integer, String> pMap
	 // , Charset pCharset
	 // , String pSautDeLigne
	 // , boolean pAjouterAFinFichier).____________________________________
	
	
	
	/**
	 * method lireStringsDansFileUtf8(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Lit le contenu d'un fichier texte 
	 * (fichier simple contenant du texte) pFile.</li>
	 * <li>Décode le contenu d'un fichier texte 
	 * (fichier simple contenant du texte) pFile 
	 * avec le Charset CHARSET_UTF8</li>
	 * <li>Retourne la liste des lignes du fichier simple texte pFile 
	 * lues avec le Charset CHARSET_UTF8.</li>
	 * <br/>
	 * <ul>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour la lecture.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas.<br/>
	 * - Retourne null si pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier simple textuel à lire.<br/>
	 * 
	 * @return : List&lt;String&gt; : Liste des lignes lues.<br/>
	 * 
	 * @throws Exception en cas d'Exception loggée 
	 * (IOException, MalformedInputException, ...).<br/>
	 */
	public static List<String> lireStringsDansFileUtf8(
			final File pFile) 
			throws Exception {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			return lireStringsDansFile(pFile, CHARSET_UTF8);
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de lireStringsDansFileUtf8(
	 // File pFile)._______________________________________________________
	
	
	
	/**
	 * method lireStringsDansFile(
	 * File pFile
	 * , Charset pCharset) :<br/>
	 * <ul>
	 * <li>Lit le contenu d'un fichier texte 
	 * (fichier simple contenant du texte) pFile.</li>
	 * <li>Décode le contenu d'un fichier texte 
	 * (fichier simple contenant du texte) pFile 
	 * avec le Charset pCharset</li>
	 * <li>Retourne la liste des lignes du fichier simple texte pFile 
	 * lues avec le Charset pCharset.</li>
	 * <br/>
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
	public static List<String> lireStringsDansFile(
			final File pFile
				, final Charset pCharset) throws Exception {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
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
				+ charset.displayName(LOCALE_SYSTEM) 
				+ " à cause d'un caractère qui ne peut être "
				+ "écrit dans ce Charset (MalformedInputException)";
				
				/* LOG de niveau Error. */
				loggerError(fournirNomClasseConcrete()
						, METHODE_LIRE_STRINGS_DANS_FILE + SEP_MOINS + message
						, malformedInputException);
				
				/* retourne null en cas d'Exception loggée 
				 * (IOException, MalformedInputException, ...). */
				return null;

			}
			
			catch (IOException ioe) {
				
				/* LOG de niveau Error. */
				loggerError(fournirNomClasseConcrete()
						, METHODE_LIRE_STRINGS_DANS_FILE
						, ioe);
				
				final String message 
				= fournirNomClasseConcrete() 
				+ SEP_MOINS 
				+ METHODE_LIRE_STRINGS_DANS_FILE 
				+ SEP_MOINS 
				+ "Impossible de lire le contenu du fichier '" 
				+ pFile.getName() 
				+ "' avec le Charset " 
				+ charset.displayName(LOCALE_SYSTEM);
				
				/* jette une Exception en cas d'Exception loggée 
				 * (IOException, MalformedInputException, ...). */
				throw new Exception(message, ioe);
			
			}
			
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de lireStringsDansFile(
	 // File pFile
	 // , Charset pCharset)._______________________________________________
	

	
	/**
	 * method lireLigneDansFichier(
	 * File pFile
	 * , Charset pCharsetLecture
	 * , int pNumLigne) :<br/>
	 * <ul>
	 * <li>Lit et retourne la pNumLigne-ième ligne 
	 * (1-based) du fichier textuel simple pFile.</li>
	 * <li>Lit le fichier textuel simple 
	 * pFile avec l'encodage pCharsetLecture.</li>
	 * <br/>
	 * <ul>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour la lecture 
	 * si pCharsetLecture est null.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas sur le disque.<br/>
	 * - Retourne null si pFile est un répertoire.<br/>
	 * - Retourne null si pNumLigne == 0.<br/>
	 * - Retourne null en cas d'Exception loggée (IOException, ...).<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier textuel simple dans lequel 
	 * on veut lire la pNumLigne-ième (1-based) ligne.<br/>
	 * @param pCharsetLecture : Charset : Charset dans lequel 
	 * le fichier simple textuel pFile est encodé. 
	 * On le lit donc avec ce Charset.<br/>
	 * @param pNumLigne : int : numéro (1-based) 
	 * de la ligne à lire dans pFile.<br/>
	 * 
	 * @return : String : la pNumLigne-ième ligne (1-based) de pFile.<br/>
	 */
	public static String lireLigneDansFichier(
			final File pFile
				, final Charset pCharsetLecture
					, final int pNumLigne) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pFile est null. */
			if (pFile == null) {
				return null;
			}
			
			/* Retourne null si pFile n'existe pas sur le disque. */
			if (!pFile.exists()) {
				return null;
			}
			
			/* Retourne null si pFile est un répertoire. */
			if (pFile.isDirectory()) {
				return null;
			}
			
			/* Retourne null si pNumLigne == 0. */
			if (pNumLigne == 0) {
				return null;
			}
			
			Charset charsetLecture = null;
			
			/* Utilise automatiquement le CHARSET_UTF8 si 
			 * pCharsetLecture est null. */			
			if (pCharsetLecture == null) {
				charsetLecture = CHARSET_UTF8;
			}
			else {
				charsetLecture = pCharsetLecture;
			}
			
			String ligneNumerotee = null;
			
			// ****************************************************
			/* LECTURE. */
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader bufferedReader = null;
			
			try {

				/* LECTURE DU FICHIER AVEC CHARSET charsetLecture. */
				inputStream = new FileInputStream(pFile);
				inputStreamReader 
					= new InputStreamReader(inputStream, charsetLecture);
				bufferedReader = new BufferedReader(inputStreamReader);


				String ligneLue = null;
				int numeroLigneLue = 0;
				
				/* BOUCLE SUR LES LIGNES DE pFile. */
				while (true) {
															
					/* Lecture de la ligne. */
					ligneLue = bufferedReader.readLine();
					
					/* Fin de boucle à la fin du fichier. */
					if (ligneLue == null) {
						break;
					}
					
					/* Incrémentation du compteur de ligne. */
					numeroLigneLue++;

					/* Saute la ligne 
					 * si la position dans le fichier pFile est pNumLigne. */
					if (numeroLigneLue == pNumLigne) {
						ligneNumerotee = ligneLue;
					}

				} // Fin de BOUCLE SUR LES LIGNES DE pFile._____
				
			} catch (FileNotFoundException e) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
						, METHODE_LIRE_LIGNE_N_DANS_FICHIER
						, e);
				
				/* retourne null. */
				return null;
				
			} catch (IOException e) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
						, METHODE_LIRE_LIGNE_N_DANS_FICHIER
						, e);
				
				/* retourne null. */
				return null;
				
			}
			
			finally {
				
				/* Fermeture des flux de lecture. */
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_LIRE_LIGNE_N_DANS_FICHIER
								, e);
					}
				}
				if (inputStreamReader != null) {
					try {
						inputStreamReader.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_LIRE_LIGNE_N_DANS_FICHIER
								, e);
					}
				}
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_LIRE_LIGNE_N_DANS_FICHIER
								, e);
					}
				}
												
			} // Fin du finally._______________________________

			return ligneNumerotee;
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de lireLigneDansFichier(
	 // File pFile
	 // , Charset pCharsetLecture
	 // , int pNumLigne).__________________________________________________
	
	
	
	/**
	 * method creerArborescence(
	 * File pRep) :<br/>
	 * <ul>
	 * <li>Crée toute l'arborescence relative au répertoire pRep 
	 * et retourne le répertoire créé sur disque 
	 * si la création a été effectuée.</li>
	 * </ul>
	 * - Retourne null si pRep est null.<br/>
	 * - Retourne null si pRep existe déjà.<br/>
	 * - retourne null en cas d'Exception loggée 
	 * (FileNotFoundException, IOException).<br/>
	 * <br/>
	 *
	 * @param pRep : File : le répertoire à créer 
	 * sur disque avec son arborescence.<br/>
	 * 
	 * @return : File : le répertoire crée sur disque.<br/>
	 */
	public static File creerArborescence(
			final File pRep) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pRep est null. */
			if (pRep == null) {
				return null;
			}
			
			/* Retourne null si pRep existe déjà. */
			if (pRep.exists()) {
				return null;
			}
			
			File resultat = null;
			
			final Path pathRep = pRep.toPath();
					
			try {
				
				Files.createDirectories(pathRep);
				resultat = pRep;
				
			} catch (IOException ioe) {
				
				loggerError(
						fournirNomClasseConcrete()
							, METHODE_CREER_ARBORESCENCE
								, ioe);
				
				return null;
			}
			
			return resultat;
			
		} // Fin du bloc static synchronized.________________________
				
	} // Fin de creerArborescence(
	 // File pRep).________________________________________________________
	
	
	
	/**
	 * method creerArborescence(
	 * String pChemin) :<br/>
	 * <ul>
	 * <li>Crée en une seule fois toute l'arborescence 
	 * de répertoires passée en paramètre.</li>
	 * <br/>
	 * <ul>
	 * Par exemple :<br/>
	 * <li>creerArborescence("C:\\NewRep1\\NewRep2\\NewRep3") 
	 * va créer sur le disque toute cette arborescence d'un seul coup.</li>
	 * <li>creerArborescence(".\\data2\\temp\\rapports") 
	 * va créer sur le disque toute cette arborescence à partir 
	 * du répertoire courant d'un seul coup.</li>
	 * </ul>
	 * </ul>
	 * <br/>
	 * - retourne false si pChemin est blank.<br/>
	 * - retourne false si l'arborescence existe déjà.<br/>
	 * - retourne false si pChemin ne contient pas '\\'.<br/>
	 * - retourne false si un des répertoires du chemin est blank.<br/>
	 * - retourne false si la racine du chemin n'existe pas.<br/>
	 * - retourne false si la racine du chemin n'est pas un répertoire.<br/>
	 * - retourne false si un répertoire a créer n'a pas été créé.<br/>
	 * <br/>
	 *
	 * @param pChemin : String : Chemin de l'arborescence à créer.<br/>
	 * 
	 * @return boolean : true si l'arborescence a été créée.<br/>
	 */
	public static boolean creerArborescence(
			final String pChemin) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne false si pChemin est blank. */
			if (StringUtils.isBlank(pChemin)) {
				return false;
			}
			
			final File cheminFile = new File(pChemin);
			
			/* retourne false si l'arborescence existe déjà. */
			if (cheminFile.exists()) {
				return false;
			}
			
			/* retourne false si pChemin ne contient pas '\\'. */
			if (!StringUtils.contains(pChemin, "\\")) {
				return false;
			}
			
			/* Récupération des répertoires par découpage de la chaine. */
			final String[] repertoires = StringUtils.split(pChemin, "\\");
			final int nombreRep = repertoires.length;
			
			/* retourne false si un des répertoires du chemin est blank. */
			for (final String rep : repertoires) {
				if (StringUtils.isBlank(rep)) {
					return false;
				}
			}
			
			/* Extraction de la racine. */
			final String repRacineString = repertoires[0];
			
			final File repRacine = new File(repRacineString);
			
			/* retourne false si la racine du chemin n'existe pas. */
			if (!repRacine.exists()) {
				return false;
			}
			
			/* retourne false si la racine du chemin n'est pas un répertoire. */
			if (!repRacine.isDirectory()) {
				return false;
			}
			
			final StringBuffer stb = new StringBuffer();
			
			stb.append(repRacineString);
			
			/* Boucle sur les répertoires du chemin. */
			for (int i = 1; i < nombreRep; i++) {
				
				/* Création du chemin du répertoire à créer. */
				stb.append(SEP_REP);
				stb.append(repertoires[i]);
				
				final File repertoireFile = new File(stb.toString());
				
				/* Créée le répertoire au chemin de création 
				 * si il n'existait pas.*/
				if (!repertoireFile.exists()) {
					
					if (!repertoireFile.mkdir()) {
						/* retourne false si un répertoire 
						 * a créer n'a pas été créé. */
						return false;
					}
				}
				
			} // Fin de boucle.________________________
			
			/* retourne true si l'arborescence a été créée. */
			return true;
			
		} // Fin du bloc static synchronized.________________________
				
	} // Fin de creerArborescence(
	 // String pChemin).___________________________________________________
	
	
	
	/**
	 * method detruireArborescence(
	 * String pChemin) :<br/>
	 * <ul>
	 * <li>Détruit le répertoire (et forcément toute l'arborescence sous lui) 
	 * situé au chemin pChemin.</li>
	 * <li>Retourne le boolean true si la destruction du répertoire 
	 * (et forcément de son contenu) s'est bien déroulée.</li>
	 * <li>Vide le contenu du répertoire si nécessaire avant de le supprimer 
	 * (Rappel : il est impossible de supprimer un répertoire 
	 * non vide en Java).</li>
	 * </ul>
	 * - retourne false si pChemin est blank.<br/>
	 * - retourne false si le répertoire à détruire n'existe pas.<br/>
	 * - retourne false si le File à détruire n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pChemin : String : Chemin du répertoire à détruire.<br/>
	 * 
	 * @return : boolean : true si le répertoire a été détruit.<br/>
	 */
	public static boolean detruireArborescence(
			final String pChemin) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne false si pChemin est blank. */
			if (StringUtils.isBlank(pChemin)) {
				return false;
			}
						
			final File repADetruire = new File(pChemin);
			
			/* retourne false si le répertoire à détruire n'existe pas. */
			if (!repADetruire.exists()) {
				return false;
			}
			
			/* retourne false si le File à détruire n'est pas un répertoire. */
			if (!repADetruire.isDirectory()) {
				return false;
			}
						
			/* Détruit le répertoire et retourne le boolean. */				
			try {
				
				/* Vide d'abord le contenu du répertoire. */
				viderRepertoireADetruire(repADetruire);
				
				/* Détruit le répertoire. */
				return repADetruire.delete();
				
			} catch (Exception e) {
				
				/* LOG de niveau INFO. */
				loggerInfo(
						fournirNomClasseConcrete()
							, METHODE_DETRUIRE_ARBORESCENCE
								, e.getMessage());
				
				return false;
				
			}
				
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de detruireArborescence(
	 // String pChemin).___________________________________________________
	


	/**
	 * method viderRepertoire(
	 * File pRep) :<br/>
	 * <ul>
	 * <li>Vide tout le contenu du répertoire pRep sans écraser pRep.</li>
	 * <ul>
	 * <li>méthode récursive.</li>
	 * <li>Il est indispensable de vider tout le contenu d'un répertoire 
	 * avant de pouvoir supprimer celui-ci en Java.</li>
	 * </ul>
	 * </ul>
	 * - ne fait rien si pRep == null.<br/>
	 * - ne fait rien si pRep n'existe pas.<br/>
	 * - ne fait rien si pRep n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pRep : File : Répertoire dont on veut vider 
	 * tout le contenu le contenu tout en le conservant.<br/>
	 */
	public static void viderRepertoire(
			final File pRep) {

		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* ne fait rien si pRep == null. */
			if (pRep == null) {
				return;
			}
						
			/* ne fait rien si pRep n'existe pas. */
			if (!pRep.exists()) {
				return;
			}
			
			/* ne fait rien si pRep n'est pas un répertoire. */
			if (!pRep.isDirectory()) {
				return;
			}
			
			/* Récupération des File dans pRep. */
			final File[] filesContenus = pRep.listFiles();
			
			if (filesContenus == null) {
				return;
			}
			
			/* ForEach (boucle) sur les File de pRep. ******/
			for (final File file : filesContenus) {
				
				/* Sort Si pRep est vide. */
				if (filesContenus.length == 0) {
					return;
				}
				
				/* APPEL RECURSIF si file est un répertoire. */
				if (file.isDirectory()) {
					viderRepertoire(file);
				}
				
				file.delete();
				
			} // Fin de ForEach (boucle) sur les File de pRep. ******__
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de viderRepertoire(
	 // File pRep).________________________________________________________
	
	

	/**
	 * method viderRepertoireADetruire(
	 * File pRep) :<br/>
	 * <ul>
	 * <li>Vide tout le contenu du répertoire pRep sans écraser pRep.</li>
	 * <li>Retourne un boolean à true si le 
	 * contenu du répertoire a bien été effacé.</li>
	 * <br/>
	 * <ul>
	 * <li>méthode récursive.</li>
	 * <li>Il est indispensable de vider tout le contenu d'un répertoire 
	 * avant de pouvoir supprimer celui-ci en Java.</li>
	 * </ul>
	 * </ul>
	 * 
	 * - retourne false si pRep == null.<br/>
	 * - retourne false si pRep n'existe pas.<br/>
	 * - retourne false si pRep n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pRep : File : Répertoire dont on veut vider 
	 * tout le contenu le contenu tout en le conservant.<br/>
	 * 
	 * @return : boolean : true si le contenu du répertoire a été vidé.<br/>
	 */
	public static boolean viderRepertoireADetruire(
			final File pRep) {
				
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
						
			/* retourne false si pRep == null. */
			if (pRep == null) {
				return false;
			}
			
			/* retourne false si pRep n'existe pas. */
			if (!pRep.exists()) {
				return false;
			}
			
			/* retourne false si pRep n'est pas un répertoire. */
			if (!pRep.isDirectory()) {
				return false;
			}
			
			/* Récupération des File dans pRep. */
			final File[] filesContenus = pRep.listFiles();
			
			if (filesContenus == null) {
				return true;
			}
			
			/* Sort Si pRep est vide. */
			if (filesContenus.length == 0) {
				return true;
			}

			boolean resultat = false;
			
			/* Si pRep non vide. */
			/* ForEach (boucle) sur les File de pRep. ******/
			for (final File file : filesContenus) {
				
				/* Appel récursif si file est un répertoire. */
				if (file.isDirectory()) {
					
					/* APPEL RECURSIF. */
					viderRepertoireADetruire(file);
					
					
				} // Fin de if (!file.isDirectory()).___________
				
				/* Destruction du file dans tous les cas. */					
				try {
					
					resultat = Files.deleteIfExists(file.toPath());
					
				} catch (Exception e) {
					
					/* LOG de niveau INFO. */
					final String message 
					= "Impossible de détruire le fichier '" 
					+ file.getName() 
					+ "' : ";
					
					loggerInfo(
							fournirNomClasseConcrete()
								, METHODE_VIDER_REPERTOIRE
									, message + e.getMessage());
					
					return false;
					
				}
									
			} // Fin du ForEach (boucle) sur les File de pRep.___
			
			return resultat;
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de viderRepertoireADetruire(
	 // File pRep).________________________________________________________
	

	
	/**
	 * method ecraserRepertoireEtSousArbo(
	 * File pRep) :<br/>
	 * <ul>
	 * <li>Vide tout le contenu du répertoire pRep et ECRASE pRep.</li>
	 * <br/>
	 * <ul>
	 * <li>méthode récursive.</li>
	 * <li>Il est indispensable de vider tout le contenu d'un répertoire 
	 * avant de pouvoir supprimer celui-ci en Java.</li>
	 * </ul>
	 * </ul>
	 * <br/>
	 * - retourne false si pRep est null.<br/>
	 * - retourne false si pRep n'existe pas sur le disque.<br/>
	 * - retourne false si pRep n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pRep : File : répertoire à écraser avec tout son contenu.<br/>
	 * 
	 * @return : boolean : true si pRep a bien été écrasé 
	 * ainsi que tout son contenu.<br/>
	 */
	public static boolean ecraserRepertoireEtSousArbo(
			final File pRep) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne false si pRep est null. */
			if (pRep == null) {
				return false;
			}
			
			/* retourne false si pRep n'existe pas sur le disque. */
			if (!pRep.exists()) {
				return false;
			}
			
			/* retourne false si pRep n'est pas un répertoire. */
			if (!pRep.isDirectory()) {
				return false;
			}
			
			boolean resultat = false;
			
			/* Vidage de tout le contenu du répertoire. */
			final boolean vidageRepertoire 
				= viderRepertoireADetruire(pRep);
			
			if (!vidageRepertoire) {
				return false;
			}
			
			final Path pathRep = pRep.toPath();
			
			try {
				
				Files.deleteIfExists(pathRep);
				resultat = true;
				
			} catch (IOException ioe) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
							, METHODE_ECRASER_REP_ET_SOUS_ARBO
								, ioe);
				
				return false;
			}
			
			return resultat;
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de ecraserRepertoireEtSousArbo(
	 // File pRep).________________________________________________________
	
		
	
	/**
	 * method copierFichierAvecRemplacement(
	 * File pFileSource
	 * , File pFileDestination) :<br/>
	 * <ul>
	 * <li>Copie sur disque le fichier source pFileSource 
	 * dans le fichier destination pFileDestination.</li>
	 * <li>Retourne le fichier copié dans pFileDestination.</li>
	 * <li>Crée l'arborescence (chemin) du fichier de destination 
	 * pFileDestination si elle n'existe pas déjà sur le disque.</li>
	 * <li>Ecrase et remplace le fichier destination 
	 * pFileDestination si il existe déjà.</li>
	 * </ul>
	 * - Retourne null si pFileSource est null.<br/>
	 * - Retourne null si pFileSource n'existe pas sur disque.<br/>
	 * - Retourne null si pFileSource est un répertoire.<br/>
	 * - Retourne null si pFileDestination est null.<br/>
	 * - retourne null en cas d'Exception loggée (ioeException).<br/>
	 * <br/>
	 *
	 * @param pFileSource : File : Fichier simple à copier.<br/>
	 * @param pFileDestination : File : Fichier simple recopié.<br/>
	 * 
	 * @return : File : Fichier simple copié.<br/>
	 */
	public static File copierFichierAvecRemplacement(
			final File pFileSource
				, final File pFileDestination) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pFileSource est null. */
			if (pFileSource == null) {
				return null;
			}
			
			/* Retourne null si pFileSource n'existe pas sur disque. */
			if (!pFileSource.exists()) {
				return null;
			}
			
			/* Retourne null si pFileSource est un répertoire. */
			if (pFileSource.isDirectory()) {
				return null;
			}
			
			/* Retourne null si pFileDestination est null. */
			if (pFileDestination == null) {
				return null;
			}
			
			/* Récupération du path du fichier source. */
			final Path pathSource = pFileSource.toPath();
			
			if (pathSource == null) {
				return null;
			}
			
			/* Récupération du path du fichier destination. */
			final Path pathDestination = pFileDestination.toPath();
						
			if (pathDestination == null) {
				return null;
			}
			
			/* Récupère le répertoire parent du fichier destination. */
			final File repParentDestination 
				= pFileDestination.getParentFile();
			
			if (repParentDestination == null) {
				return null;
			}
			
			/* Récupère le path du répertoire parent du fichier destination. */
			final Path pathRepParentDestination 
				= repParentDestination.toPath();
			
			try {
				
				/* Crée l'arborescence (chemin) du fichier de destination 
				 * si elle n'existe pas déjà sur le disque. */
				if (!repParentDestination.exists()) {
					Files.createDirectories(pathRepParentDestination);
				}
				
				
				// COPIE AVEC REMPLACEMENT ************************
				Files.copy(
						pathSource
							, pathDestination
								, StandardCopyOption.REPLACE_EXISTING);
				
			} catch (IOException ioe) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
							, METHODE_COPIER_FICHIER_AVEC_REMPLACEMENT
								, ioe);
				
				/* retourne null en cas d'Exception loggée (ioeException). */
				return null;
			}
			
			return pFileDestination;
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de copierFichierAvecRemplacement(
	 // File pFileSource
	 // , File pFileDestination).__________________________________________
	

	
	/**
	 * method copierFichierAvecRemplacement(
	 * File pFileSource
	 * , String pCheminDestination) :<br/>
	 * <ul>
	 * <li>Copie sur disque le fichier source pFileSource 
	 * dans le fichier destination situé à pCheminDestination.</li>
	 * <li>Retourne le fichier copié situé à pCheminDestination.</li>
	 * <li>Crée l'arborescence (chemin) du fichier de destination 
	 * pCheminDestination si elle n'existe pas déjà sur le disque.</li>
	 * <li>Ecrase et remplace le fichier destination situé à
	 * pCheminDestination si il existe déjà.</li>
	 * </ul>
	 * - Retourne null si pFileSource est null.<br/>
	 * - Retourne null si pFileSource n'existe pas sur disque.<br/>
	 * - Retourne null si pFileSource est un répertoire.<br/>
	 * - Retourne null si pCheminDestination est blank.<br/>
	 * <br/>
	 *
	 * @param pFileSource : File : Fichier simple à copier.<br/>
	 * @param pCheminDestination : String : Chemin complet du 
	 * fichier simple de destination copié.<br/>
	 * 
	 * @return : File : Fichier simple copié.<br/>
	 */
	public static File copierFichierAvecRemplacement(
			final File pFileSource
				, final String pCheminDestination) {
				
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pFileSource est null. */
			if (pFileSource == null) {
				return null;
			}
			
			/* Retourne null si pFileSource n'existe pas sur disque. */
			if (!pFileSource.exists()) {
				return null;
			}
			
			/* Retourne null si pFileSource est un répertoire. */
			if (pFileSource.isDirectory()) {
				return null;
			}
			
			/* Retourne null si pCheminDestination est blank. */
			if (StringUtils.isBlank(pCheminDestination)) {
				return null;
			}
			
			/* Récupération du path du fichier source. */
			final Path pathSource = pFileSource.toPath();
			
			if (pathSource == null) {
				return null;
			}
			
			/* Création du path du fichier destination. */
			final File fichierDestination 
				= new File(pCheminDestination);
			
			final Path pathDestination = fichierDestination.toPath();
			
			if (pathDestination == null) {
				return null;
			}
			
			/* Récupère le répertoire parent du fichier destination. */
			final File repParentDestination 
				= fichierDestination.getParentFile();
			
			if (repParentDestination == null) {
				return null;
			}
			
			/* Récupère le path du répertoire parent du fichier destination. */
			final Path pathRepParentDestination 
				= repParentDestination.toPath();
			
			try {
				
				/* Crée l'arborescence (chemin) du fichier de destination 
				 * si elle n'existe pas déjà sur le disque. */
				if (!repParentDestination.exists()) {
					Files.createDirectories(pathRepParentDestination);
				}
				
				
				// COPIE AVEC REMPLACEMENT ************************
				Files.copy(
						pathSource
							, pathDestination
								, StandardCopyOption.REPLACE_EXISTING);
				
			} catch (IOException ioe) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
							, "Méthode copierFichierAvecRemplacement("
								+ "File pFile Source, String pCheminDestination)"
								, ioe);
				
				/* retourne null en cas d'Exception loggée (ioeException). */
				return null;
			}
			
			return fichierDestination;
						
		} // Fin du bloc static synchronized.________________________

	} // Fin de copierFichierAvecRemplacement(
	 // File pFileSource
	 // , String pCheminDestination).______________________________________
	

	
	/**
	 * method copierFichierSansRemplacement(
	 * File pFileSource
	 * , File pFileDestination) :<br/>
	 * <ul>
	 * <li>Copie sur disque le fichier source pFileSource 
	 * dans le fichier destination pFileDestination 
	 * si il n'existe pas déjà.</li>
	 * <li>Retourne le fichier copié dans pFileDestination 
	 * ou le fichier déjà existant.</li>
	 * <li>Crée l'arborescence (chemin) du fichier de destination 
	 * pFileDestination si elle n'existe pas déjà sur le disque.</li>
	 * <li>N'écrase PAS le fichier destination 
	 * pFileDestination si il existe déjà.</li>
	 * </ul>
	 * - Retourne null si pFileSource est null.<br/>
	 * - Retourne null si pFileSource n'existe pas sur disque.<br/>
	 * - Retourne null si pFileSource est un répertoire.<br/>
	 * - Retourne null si pFileDestination est null.<br/>
	 * - retourne null en cas d'Exception loggée (ioeException).<br/>
	 * <br/>
	 *
	 * @param pFileSource : File : Fichier simple à copier.<br/>
	 * @param pFileDestination : File : Fichier simple recopié.<br/>
	 * 
	 * @return : File : Fichier simple copié.<br/>
	 */
	public static File copierFichierSansRemplacement(
			final File pFileSource
				, final File pFileDestination) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pFileSource est null. */
			if (pFileSource == null) {
				return null;
			}
			
			/* Retourne null si pFileSource n'existe pas sur disque. */
			if (!pFileSource.exists()) {
				return null;
			}
			
			/* Retourne null si pFileSource est un répertoire. */
			if (pFileSource.isDirectory()) {
				return null;
			}
			
			/* Retourne null si pFileDestination est null. */
			if (pFileDestination == null) {
				return null;
			}
			
			/* Récupération du path du fichier source. */
			final Path pathSource = pFileSource.toPath();
			
			if (pathSource == null) {
				return null;
			}
			
			/* Récupération du path du fichier destination. */
			final Path pathDestination = pFileDestination.toPath();
						
			if (pathDestination == null) {
				return null;
			}
			
			/* Récupère le répertoire parent du fichier destination. */
			final File repParentDestination 
				= pFileDestination.getParentFile();
			
			if (repParentDestination == null) {
				return null;
			}
			
			/* Récupère le path du répertoire parent du fichier destination. */
			final Path pathRepParentDestination 
				= repParentDestination.toPath();
			
			try {
				
				/* Crée l'arborescence (chemin) du fichier de destination 
				 * si elle n'existe pas déjà sur le disque. */
				if (!repParentDestination.exists()) {
					Files.createDirectories(pathRepParentDestination);
				}
				
				
				// COPIE SANS REMPLACEMENT ************************
				Files.copy(
						pathSource
							, pathDestination);

				
			} catch (FileAlreadyExistsException faeException) {
				
				/* retourne le fichier déjà existant dans la destination. */
				return pFileDestination;
				
			} catch (IOException ioe) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
							, METHODE_COPIER_FICHIER_SANS_REMPLACEMENT
								, ioe);
				
				/* retourne null en cas d'Exception loggée (ioeException). */
				return null;
			}
						
			return pFileDestination;
			
		} // Fin du bloc static synchronized.________________________

	} // Fin de copierFichierSansRemplacement(
	 // File pFileSource
	 // , File pFileDestination).__________________________________________
	

	
	/**
	 * method copierFichierSansRemplacement(
	 * File pFileSource
	 * , String pCheminDestination) :<br/>
	 * <ul>
	 * <li>Copie sur disque le fichier source pFileSource 
	 * dans le fichier destination situé à pCheminDestination 
	 * si il n'existe pas déjà..</li>
	 * <li>Retourne le fichier copié situé à pCheminDestination 
	 * ou le fichier déjà existant.</li>
	 * <li>Crée l'arborescence (chemin) du fichier de destination 
	 * pCheminDestination si elle n'existe pas déjà sur le disque.</li>
	 * <li>N'écrase PAS le fichier destination situé à
	 * pCheminDestination si il existe déjà.</li>
	 * </ul>
	 * - Retourne null si pFileSource est null.<br/>
	 * - Retourne null si pFileSource n'existe pas sur disque.<br/>
	 * - Retourne null si pFileSource est un répertoire.<br/>
	 * - Retourne null si pCheminDestination est blank.<br/>
	 * <br/>
	 *
	 * @param pFileSource : File : Fichier simple à copier.<br/>
	 * @param pCheminDestination : String : Chemin complet du 
	 * fichier simple de destination copié.<br/>
	 * 
	 * @return : File : Fichier simple copié.<br/>
	 */
	public static File copierFichierSansRemplacement(
			final File pFileSource
				, final String pCheminDestination) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pFileSource est null. */
			if (pFileSource == null) {
				return null;
			}
			
			/* Retourne null si pFileSource n'existe pas sur disque. */
			if (!pFileSource.exists()) {
				return null;
			}
			
			/* Retourne null si pFileSource est un répertoire. */
			if (pFileSource.isDirectory()) {
				return null;
			}
			
			/* Retourne null si pCheminDestination est blank. */
			if (StringUtils.isBlank(pCheminDestination)) {
				return null;
			}
			
			/* Récupération du path du fichier source. */
			final Path pathSource = pFileSource.toPath();
			
			if (pathSource == null) {
				return null;
			}
			
			/* Création du path du fichier destination. */
			final File fichierDestination 
				= new File(pCheminDestination);
			
			final Path pathDestination = fichierDestination.toPath();
			
			if (pathDestination == null) {
				return null;
			}
			
			/* Récupère le répertoire parent du fichier destination. */
			final File repParentDestination 
				= fichierDestination.getParentFile();
			
			if (repParentDestination == null) {
				return null;
			}
			
			/* Récupère le path du répertoire parent du fichier destination. */
			final Path pathRepParentDestination 
				= repParentDestination.toPath();
			
			try {
				
				/* Crée l'arborescence (chemin) du fichier de destination 
				 * si elle n'existe pas déjà sur le disque. */
				if (!repParentDestination.exists()) {
					Files.createDirectories(pathRepParentDestination);
				}
				
				
				// COPIE SANS REMPLACEMENT ************************
				Files.copy(
						pathSource
							, pathDestination);
				
			} catch (FileAlreadyExistsException faeException) {
				
				/* retourne le fichier déjà existant dans la destination. */
				return fichierDestination;	
				
			} catch (IOException ioe) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
							, "Méthode copierFichierSansRemplacement("
								+ "File pFile Source, String pCheminDestination)"
								, ioe);
				
				/* retourne null en cas d'Exception loggée (ioeException). */
				return null;
			}
			
			return fichierDestination;
									
		} // Fin du bloc static synchronized.________________________

	} // Fin de copierFichierSansRemplacement(
	 // File pFileSource
	 // , String pCheminDestination).______________________________________
	
	
	
	/**
	 * method copierRepertoireAvecRemplacement(
	 * File pRepSource
	 * , File pRepDestination) :<br/>
	 * <ul>
	 * <li>Recopie tout le contenu du répertoire source pRepSource 
	 * sous le répertoire de destination pRepDestination.</li>
	 * <li>Ne recopie pas le répertoire source lui même 
	 * (considéré comme une racine) mais tout son contenu.</li>
	 * <li>Crée le répertoire de destination pRepDestination 
	 * et son arborescence (chemin) si nécessaire.</li>
	 * <li>Retourne le répertoire destination pRepDestination 
	 * avec tout le contenu recopié.</li>
	 * <li>Ecrase et remplace le contenu du répertoire 
	 * destination pRepDestination si il existe déjà.</li>
	 * </ul>
	 * - retourne null si pRepSource est null.<br/>
	 * - retourne null si pRepSource n'existe pas.<br/>
	 * - retourne null si pRepDestination est null.<br/>
	 * - retourne null si Exception loggée (ioException, ...).<br/>
	 * <br/>
	 *
	 * @param pRepSource : File : Répertoire à copier.<br/>
	 * @param pRepDestination : File : Répertoire copié.<br/>
	 * 
	 * @return : File : Répertoire copié.<br/>
	 */
	public static File copierRepertoireAvecRemplacement(
			final File pRepSource
				, final File pRepDestination) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
						
			/* retourne null si pRepSource est null. */
			if (pRepSource == null) {
				return null;
			}
			
			/* retourne null si pRepSource n'existe pas. */
			if (!pRepSource.exists()) {
				return null;
			}
			
			/* retourne null si pRepDestination est null. */
			if (pRepDestination == null) {
				return null;
			}
			
			/* Crée le répertoire de destination et son 
			 * arborescence (chemin) si nécessaire. */			
			final Path pathRepDestination = pRepDestination.toPath();
			
			if (pathRepDestination == null) {
				return null;
			}
			
			if (!pRepDestination.exists()) {
				try {
					
					Files.createDirectories(pathRepDestination);
					
				} catch (IOException ioe1) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							fournirNomClasseConcrete()
							, METHODE_COPIER_REPERTOIRE_AVEC_REMPLACEMENT
							, ioe1);
					
					/* retourne null si Exception loggée 
					 * (ioException, ...). */
					return null;
				}
			}
			
			/* Récupération du Path de pRepSource 
			 * LORS DU PREMIER APPEL RECURSIF. */
			final Path pathRepSource = pRepSource.toPath();

			if (pathRepSource == null) {
				return null;
			}
			
			/* APPEL DE LA METHODE RECURSIVE. */
			try {
				
				recopierFilesAvecRemplacement(
						pathRepSource, pathRepSource, pathRepDestination);
				
			} catch (IOException ioe1) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
						, METHODE_COPIER_REPERTOIRE_AVEC_REMPLACEMENT
						, ioe1);
				
				/* retourne null si Exception loggée (ioException, ...). */
				return null;
			}
			
			return pRepDestination;
						
		} // Fin du bloc static synchronized.________________________

	} // Fin de copierRepertoireAvecRemplacement(
	 // File pRepSource
	 // , File pRepDestination).___________________________________________
	
	
	
	/**
	 * method copierRepertoireAvecRemplacement(
	 * File pRepSource
	 * , String pCheminDestination) :<br/>
	 * <ul>
	 * <li>Recopie tout le contenu du répertoire source pRepSource 
	 * sous le répertoire de destination situé à pCheminDestination.</li>
	 * <li>Ne recopie pas le répertoire source lui même 
	 * (considéré comme une racine) mais tout son contenu.</li>
	 * <li>Crée le répertoire de destination situé à pCheminDestination
	 * et son arborescence (chemin) si nécessaire.</li>
	 * <li>Retourne le répertoire destination situé à pCheminDestination
	 * avec tout le contenu recopié.</li>
	 * <li>Ecrase et remplace le contenu du répertoire 
	 * destination pRepDestination si il existe déjà.</li>
	 * </ul>
	 * - retourne null si pRepSource est null.<br/>
	 * - retourne null si pRepSource n'existe pas.<br/>
	 * - retourne null si pCheminDestination est blank.<br/>
	 * - retourne null si Exception loggée (ioException, ...).<br/>
	 * <br/>
	 *
	 * @param pRepSource : File : Répertoire à copier.<br/>
	 * @param pCheminDestination : String : chemin du répertoire copié.<br/>
	 * 
	 * @return : File : Répertoire copié.<br/>
	 */
	public static File copierRepertoireAvecRemplacement(
			final File pRepSource
				, final String pCheminDestination) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si pCheminDestination est blank. */
			if (StringUtils.isBlank(pCheminDestination)) {
				return null;
			}
			
			final File repDestination = new File(pCheminDestination);
			
			return copierRepertoireAvecRemplacement(pRepSource, repDestination);
			
		} // Fin du bloc static synchronized.________________________

	} // Fin de copierRepertoireAvecRemplacement(
	 // File pRepSource
	 // , String pCheminDestination).______________________________________
	

	
	/**
	 * method recopierFilesAvecRemplacement(
	 * Path pPathRepSource
	 * , Path pPathRepLu
	 * , Path pPathRepDestination) :<br/>
	 * <ul>
	 * <li>Méthode récursive qui recopie les répertoires et fichiers 
	 * simples contenus sous pPathRepSource sous pPathRepDestination.</li>
	 * <li>Recopie avec remplacement des fichiers existants.</li>
	 * </ul>
	 * <br/>
	 *
	 * @param pPathRepSource : Path : Path du répertoire source.<br/>
	 * @param pPathRepLu : Path : sert aux appels récursifs.<br/>
	 * @param pPathRepDestination : Path : Path du répertoire destination.<br/>
	 * 
	 * @throws IOException : 
	 * lorsqu'un fichier ne peut être recopié sur disque.<br/>
	 */
	private static void recopierFilesAvecRemplacement(
			final Path pPathRepSource
				, final Path pPathRepLu
					, final Path pPathRepDestination) throws IOException {
		
		// PARCOURS DES FILES DANS pPathRepLu.____
		try (DirectoryStream<Path> streamDeFiles 
				= Files.newDirectoryStream(pPathRepLu)){
			
			Path pathDeRecopie = null;
			
			/* Itération sur les files de pRepSource. */
			for (final Path pathFile : streamDeFiles) {
				
				/* Continue la boucle si le fichier lu 
				 * est le fichier destination. */
				if (pathFile.equals(pPathRepDestination)) {
					continue;
				}
				
				/* Extraction du chemin relatif de File 
				 * par rapport au répertoire source. */						
				final Path pathSousSource 
				= pPathRepSource.relativize(pathFile);
				
				/* Création du Path de recopie du fichier 
				 * sous le répertoire destination. */
				pathDeRecopie 
					= pPathRepDestination.resolve(pathSousSource);
				
				/* Vide le répertoire de recopie si il est déjà existant. */
				/* Java ne permet pas de copier dans un répertoire 
				 * qui n'est pas vide. */
				if (Files.exists(pathDeRecopie) 
						&& Files.isDirectory(pathDeRecopie)) {
					viderRepertoire(pathDeRecopie.toFile());
				}
				
				/* Si le fichier lu est un répertoire. */
				if (Files.isDirectory(pathFile)) {
					
					/* RECOPIE LE REPERTOIRE AVEC REMPLACEMENT
					 * A LA MÊME POSITION RELATIVE SOUS pRepDestination. */
					Files.copy(
							pathFile
							, pathDeRecopie
							, StandardCopyOption.REPLACE_EXISTING);
											
					// APPEL RECURSIF. 
					recopierFilesAvecRemplacement(
							pPathRepSource, pathFile, pPathRepDestination);
					
				} // Fin de Si le fichier lu est un répertoire.___
				
				/* Si le fichier lu est simple. */
				else {
					
					/* RECOPIE LE FICHIER SIMPLE AVEC REMPLACEMENT
					 * A LA MÊME POSITION RELATIVE SOUS pRepDestination. */
					Files.copy(
							pathFile
							, pathDeRecopie
							, StandardCopyOption.REPLACE_EXISTING);
					
				} // Fin de Si le fichier lu est simple.____________
								
			} // Fin de Itération sur les files de pRepSource.______
		
		// Fin de pPathRepLu.________________________________________
		} catch (IOException ioe2) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					fournirNomClasseConcrete()
					, "Méthode recopierFilesAvecRemplacement(...)"
					, ioe2);
			
			/* retourne une IOException si Exception loggée 
			 * (ioException, ...). */
			final String messaqe 
				= "Impossible de copier le fichier : " + pPathRepLu;
			
			throw new IOException(messaqe, ioe2);
		}

	} // Fin de recopierFilesAvecRemplacement(
	 // Path pPathRepSource
	 // , Path pPathRepLu
	 // , Path pPathRepDestination)._______________________________________
	

	
	/**
	 * method copierRepertoireSansRemplacement(
	 * File pRepSource
	 * , File pRepDestination) :<br/>
	 * <ul>
	 * <li>Recopie tout le contenu du répertoire source pRepSource 
	 * sous le répertoire de destination pRepDestination.</li>
	 * <li>Ne recopie pas le répertoire source lui même 
	 * (considéré comme une racine) mais tout son contenu.</li>
	 * <li>Crée le répertoire de destination pRepDestination 
	 * et son arborescence (chemin) si nécessaire.</li>
	 * <li>Retourne le répertoire destination pRepDestination 
	 * avec tout le contenu recopié.</li>
	 * <li>N'écrase PAS le contenu du répertoire 
	 * destination pRepDestination si il existe déjà.</li>
	 * </ul>
	 * - retourne null si pRepSource est null.<br/>
	 * - retourne null si pRepSource n'existe pas.<br/>
	 * - retourne null si pRepDestination est null.<br/>
	 * - retourne null si Exception loggée (ioException, ...).<br/>
	 * - retourne pRepDestination si il existe déjà.<br/>
	 * <br/>
	 *
	 * @param pRepSource : File : Répertoire à copier.<br/>
	 * @param pRepDestination : File : Répertoire copié.<br/>
	 * 
	 * @return : File : Répertoire copié ou le répertoire déjà existant.<br/>
	 */
	public static File copierRepertoireSansRemplacement(
			final File pRepSource
				, final File pRepDestination) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si pRepSource est null. */
			if (pRepSource == null) {
				return null;
			}
			
			/* retourne null si pRepSource n'existe pas. */
			if (!pRepSource.exists()) {
				return null;
			}
			
			/* retourne null si pRepDestination est null. */
			if (pRepDestination == null) {
				return null;
			}
			
			/* retourne pRepDestination si il existe déjà. */
			if (pRepDestination.exists()) {
				return pRepDestination;
			}
			
			/* Crée le répertoire de destination et son 
			 * arborescence (chemin) si nécessaire. */			
			final Path pathRepDestination = pRepDestination.toPath();
			
			if (pathRepDestination == null) {
				return null;
			}
			
			try {
				
				Files.createDirectories(pathRepDestination);
				
			} catch (IOException ioe1) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
						, METHODE_COPIER_REPERTOIRE_SANS_REMPLACEMENT
						, ioe1);
				
				/* retourne null si Exception loggée 
				 * (ioException, ...). */
				return null;
			}
			
			
			/* Récupération du Path de pRepSource 
			 * LORS DU PREMIER APPEL RECURSIF. */
			final Path pathRepSource = pRepSource.toPath();

			if (pathRepSource == null) {
				return null;
			}
			
			/* APPEL DE LA METHODE RECURSIVE. */
			try {
				
				recopierFilesSansRemplacement(
						pathRepSource, pathRepSource, pathRepDestination);
				
			} catch (IOException ioe1) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
						, METHODE_COPIER_REPERTOIRE_SANS_REMPLACEMENT
						, ioe1);
				
				/* retourne null si Exception loggée (ioException, ...). */
				return null;
			}
			
			return pRepDestination;
			
		} // Fin du bloc static synchronized.________________________

	} // Fin de copierRepertoireSansRemplacement(
	 // File pRepSource
	 // , File pRepDestination).___________________________________________
	

		
	/**
	 * method copierRepertoireSansRemplacement(
	 * File pRepSource
	 * , String pCheminDestination) :<br/>
	 * <ul>
	 * <li>Recopie tout le contenu du répertoire source pRepSource 
	 * sous le répertoire de destination situé à pCheminDestination.</li>
	 * <li>Ne recopie pas le répertoire source lui même 
	 * (considéré comme une racine) mais tout son contenu.</li>
	 * <li>Crée le répertoire de destination situé à pCheminDestination
	 * et son arborescence (chemin) si nécessaire.</li>
	 * <li>Retourne le répertoire destination situé à pCheminDestination
	 * avec tout le contenu recopié.</li>
	 * <li>N'écrase PAS le contenu du répertoire 
	 * destination pRepDestination si il existe déjà.</li>
	 * </ul>
	 * - retourne null si pRepSource est null.<br/>
	 * - retourne null si pRepSource n'existe pas.<br/>
	 * - retourne null si pCheminDestination est blank.<br/>
	 * - retourne null si Exception loggée (ioException, ...).<br/>
	 * <br/>
	 *
	 * @param pRepSource : File : Répertoire à copier.<br/>
	 * @param pCheminDestination : String : chemin du répertoire copié.<br/>
	 * 
	 * @return : File : Répertoire copié ou le répertoire déjà existant.<br/>
	 */
	public static File copierRepertoireSansRemplacement(
			final File pRepSource
				, final String pCheminDestination) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si pCheminDestination est blank. */
			if (StringUtils.isBlank(pCheminDestination)) {
				return null;
			}
			
			final File repDestination = new File(pCheminDestination);
			
			return copierRepertoireSansRemplacement(
					pRepSource, repDestination);
			
		} // Fin du bloc static synchronized.________________________

	} // Fin de copierRepertoireSansRemplacement(
	 // File pRepSource
	 // , String pCheminDestination).______________________________________

	
	
	/**
	 * method recopierFilesSansRemplacement(
	 * Path pPathRepSource
	 * , Path pPathRepLu
	 * , Path pPathRepDestination) :<br/>
	 * <ul>
	 * <li>Méthode récursive qui recopie les répertoires et fichiers 
	 * simples contenus sous pPathRepSource sous pPathRepDestination.</li>
	 * <li>Recopie SANS remplacement des fichiers existants.</li>
	 * </ul>
	 *
	 * @param pPathRepSource : Path : Path du répertoire source.<br/>
	 * @param pPathRepLu : Path : sert aux appels récursifs.<br/>
	 * @param pPathRepDestination : Path : Path du répertoire destination.<br/>
	 * 
	 * @throws IOException : 
	 * lorsqu'un fichier ne peut être recopié sur disque.<br/>
	 */
	private static void recopierFilesSansRemplacement(
			final Path pPathRepSource
				, final Path pPathRepLu
					, final Path pPathRepDestination) throws IOException {
		
		// PARCOURS DES FILES DANS pPathRepLu.____
		try (DirectoryStream<Path> streamDeFiles 
				= Files.newDirectoryStream(pPathRepLu)){
			
			Path pathDeRecopie = null;
			
			/* Itération sur les files de pRepSource. */
			for (final Path pathFile : streamDeFiles) {
				
				/* Continue la boucle si le fichier lu 
				 * est le fichier destination. */
				if (pathFile.equals(pPathRepDestination)) {
					continue;
				}
				
				/* Extraction du chemin relatif de File 
				 * par rapport au répertoire source. */						
				final Path pathSousSource 
				= pPathRepSource.relativize(pathFile);
				
				/* Création du Path de recopie du fichier 
				 * sous le répertoire destination. */
				pathDeRecopie 
					= pPathRepDestination.resolve(pathSousSource);
				
				/* Si le fichier lu est un répertoire. */
				if (Files.isDirectory(pathFile)) {
					
					/* RECOPIE LE REPERTOIRE SANS REMPLACEMENT
					 * A LA MÊME POSITION RELATIVE SOUS pRepDestination. */
					Files.copy(
							pathFile
							, pathDeRecopie);
											
					// APPEL RECURSIF. 
					recopierFilesSansRemplacement(
							pPathRepSource, pathFile, pPathRepDestination);
					
				} // Fin de Si le fichier lu est un répertoire.___
				
				/* Si le fichier lu est simple. */
				else {
					
					/* RECOPIE LE FICHIER SIMPLE SANS REMPLACEMENT
					 * A LA MÊME POSITION RELATIVE SOUS pRepDestination. */
					Files.copy(
							pathFile
							, pathDeRecopie);
					
				} // Fin de Si le fichier lu est simple.____________
								
			} // Fin de Itération sur les files de pRepSource.______
		
		// Fin de pPathRepLu.________________________________________
		} catch (IOException ioe2) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					fournirNomClasseConcrete()
					, "Méthode recopierFilesSansRemplacement(...)"
					, ioe2);
			
			/* retourne une IOException si Exception loggée 
			 * (ioException, ...). */
			final String messaqe 
				= "Impossible de copier le fichier : " + pPathRepLu;
			
			throw new IOException(messaqe, ioe2);
		}

	} // Fin de recopierFilesSansRemplacement(
	 // Path pPathRepSource
	 // , Path pPathRepLu
	 // , Path pPathRepDestination)._______________________________________
	

	
	/**
	 * method copierArborescenceAvecRemplacement(
	 * File pRepSource
	 * , File pRepDestination) :<br/>
	 * <ul>
	 * <li>Extrait et Recopie toute l'<b>arborescence</b> située 
	 * sous le répertoire source pRepSource 
	 * sous le répertoire de destination pRepDestination.</li>
	 * <li>l'arborescence est le système de <b>répertoires</b> 
	 * <i>(sans les fichiers simples contenus)</i> 
	 * situés sous pRepSource.</li>
	 * <li>Ne recopie pas le répertoire source lui même 
	 * (considéré comme une racine) mais toute son arborescence.</li>
	 * <li>Crée le répertoire de destination pRepDestination 
	 * et son arborescence ascendante (chemin) si nécessaire.</li>
	 * <li>Retourne le répertoire destination pRepDestination 
	 * avec toute l'arborescence sous pRepSource recopiée relativement 
	 * sous pRepDestination.</li>
	 * <li>Ecrase et remplace l'arborescence sous le répertoire 
	 * destination pRepDestination si elle existe déjà.</li>
	 * </ul>
	 * - retourne null si pRepSource est null.<br/>
	 * - retourne null si pRepSource n'existe pas.<br/>
	 * - retourne null si pRepDestination est null.<br/>
	 * - retourne null si Exception loggée (ioException, ...).<br/>
	 * <br/>
	 *
	 * @param pRepSource : File : Répertoire dont on veut copier 
	 * l'arborescence (système de répertoires sous pRepSource) 
	 * sous pRepDestination.<br/>
	 * @param pRepDestination : File : Répertoire contenant 
	 * la copie de l'arborescence située sous pRepSource.<br/>
	 * 
	 * @return : File : Répertoire contenant l'arborescence copiée.<br/>
	 */
	public static File copierArborescenceAvecRemplacement(
			final File pRepSource
				, final File pRepDestination) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
						
			/* retourne null si pRepSource est null. */
			if (pRepSource == null) {
				return null;
			}
			
			/* retourne null si pRepSource n'existe pas. */
			if (!pRepSource.exists()) {
				return null;
			}
			
			/* retourne null si pRepDestination est null. */
			if (pRepDestination == null) {
				return null;
			}
			
			/* Crée le répertoire de destination et son 
			 * arborescence ascendante (chemin) si nécessaire. */			
			final Path pathRepDestination = pRepDestination.toPath();
			
			if (pathRepDestination == null) {
				return null;
			}
			
			if (!pRepDestination.exists()) {
				try {
					
					Files.createDirectories(pathRepDestination);
					
				} catch (IOException ioe1) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							fournirNomClasseConcrete()
							, METHODE_COPIER_ARBORESCENCE_AVEC_REMPLACEMENT
							, ioe1);
					
					/* retourne null si Exception loggée 
					 * (ioException, ...). */
					return null;
				}
			}
			
			/* Récupération du Path de pRepSource 
			 * LORS DU PREMIER APPEL RECURSIF. */
			final Path pathRepSource = pRepSource.toPath();

			if (pathRepSource == null) {
				return null;
			}
			
			/* APPEL DE LA METHODE RECURSIVE. */
			try {
				
				recopierArboAvecRemplacement(
						pathRepSource, pathRepSource, pathRepDestination);
				
			} catch (IOException ioe1) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
						, METHODE_COPIER_ARBORESCENCE_AVEC_REMPLACEMENT
						, ioe1);
				
				/* retourne null si Exception loggée (ioException, ...). */
				return null;
			}
			
			return pRepDestination;
						
		} // Fin du bloc static synchronized.________________________

	} // Fin de copierArborescenceAvecRemplacement(
	 // File pRepSource
	 // , File pRepDestination).___________________________________________
	
	
	
	/**
	 * method copierArborescenceAvecRemplacement(
	 * File pRepSource
	 * , String pCheminDestination) :<br/>
	 * <ul>
	 * <li>Extrait et Recopie toute l'<b>arborescence</b> 
	 * située sous le répertoire source pRepSource 
	 * sous le répertoire de destination situé à pCheminDestination.</li>
	 * <li>l'arborescence est le système de <b>répertoires</b> 
	 * <i>(sans les fichiers simples contenus)</i> 
	 * situés sous pRepSource.</li>
	 * <li>Ne recopie pas le répertoire source lui même 
	 * (considéré comme une racine) mais toute son arborescence.</li>
	 * <li>Crée le répertoire de destination situé à pCheminDestination
	 * et son arborescence ascendante (chemin) si nécessaire.</li>
	 * <li>Retourne le répertoire destination situé à pCheminDestination
	 * avec toute l'arborescence 
	 * sous pRepSource recopiée relativement.</li>
	 * <li>Ecrase et remplace l'arborescence sous le répertoire 
	 * destination pRepDestination si elle existe déjà.</li>
	 * </ul>
	 * - retourne null si pRepSource est null.<br/>
	 * - retourne null si pRepSource n'existe pas.<br/>
	 * - retourne null si pCheminDestination est blank.<br/>
	 * - retourne null si Exception loggée (ioException, ...).<br/>
	 * <br/>
	 *
	 * @param pRepSource : File : Répertoire dont on veut copier 
	 * l'arborescence (système de répertoires sous pRepSource) 
	 * sous pRepDestination.<br/>
	 * @param pCheminDestination : String : chemin du répertoire copié.<br/>
	 * 
	 * @return : File : Répertoire contenant l'arborescence copiée.<br/>
	 */
	public static File copierArborescenceAvecRemplacement(
			final File pRepSource
				, final String pCheminDestination) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si pCheminDestination est blank. */
			if (StringUtils.isBlank(pCheminDestination)) {
				return null;
			}
			
			final File repDestination = new File(pCheminDestination);
			
			return copierArborescenceAvecRemplacement(
					pRepSource, repDestination);
			
		} // Fin du bloc static synchronized.________________________

	} // Fin de copierArborescenceAvecRemplacement(
	 // File pRepSource
	 // , String pCheminDestination).______________________________________
	

	
	/**
	 * method recopierArboAvecRemplacement(
	 * Path pPathRepSource
	 * , Path pPathRepLu
	 * , Path pPathRepDestination) :<br/>
	 * <ul>
	 * <li>Méthode récursive qui recopie les répertoires 
	 * (pas les fichiers simples) 
	 * contenus sous pPathRepSource sous pPathRepDestination.</li>
	 * <li>Recopie avec remplacement des répertoires existants.</li>
	 * </ul>
	 *
	 * @param pPathRepSource : Path : Path du répertoire source.<br/>
	 * @param pPathRepLu : Path : sert aux appels récursifs.<br/>
	 * @param pPathRepDestination : Path : Path du répertoire destination.<br/>
	 * 
	 * @throws IOException : 
	 * lorsqu'un répertoire ne peut être recopié sur disque.<br/>
	 */
	private static void recopierArboAvecRemplacement(
			final Path pPathRepSource
				, final Path pPathRepLu
					, final Path pPathRepDestination) throws IOException {
		
		// PARCOURS DES FILES DANS pPathRepLu.____
		try (DirectoryStream<Path> streamDeArbo 
				= Files.newDirectoryStream(pPathRepLu)){
			
			Path pathDeRecopie = null;
			
			/* Itération sur les files de pRepSource. */
			for (final Path pathFile : streamDeArbo) {
				
				/* Continue la boucle si le File n'est pas un répertoire. */
				if (!Files.isDirectory(pathFile)) {
					continue;
				}
				
				/* Continue la boucle si le fichier lu 
				 * est le fichier destination. */
				if (pathFile.equals(pPathRepDestination)) {
					continue;
				}
				
				/* Extraction du chemin relatif de File 
				 * par rapport au répertoire source. */						
				final Path pathSousSource 
				= pPathRepSource.relativize(pathFile);
				
				/* Création du Path de recopie du fichier 
				 * sous le répertoire destination. */
				pathDeRecopie 
					= pPathRepDestination.resolve(pathSousSource);
				
				/* Vide le répertoire de recopie si il est déjà existant. */
				/* Java ne permet pas de copier dans un répertoire 
				 * qui n'est pas vide. */
				if (Files.exists(pathDeRecopie) 
						&& Files.isDirectory(pathDeRecopie)) {
					viderRepertoire(pathDeRecopie.toFile());
				}
				
				/* Si le fichier lu est un répertoire. */
				if (Files.isDirectory(pathFile)) {
											
					/* RECOPIE LE REPERTOIRE AVEC REMPLACEMENT
					 * A LA MÊME POSITION RELATIVE SOUS pRepDestination. */
					Files.copy(
							pathFile
							, pathDeRecopie
							, StandardCopyOption.REPLACE_EXISTING);
											
					// APPEL RECURSIF. 
					recopierArboAvecRemplacement(
							pPathRepSource, pathFile, pPathRepDestination);
											
				} // Fin de Si le fichier lu est un répertoire.___
				
			} // Fin de Itération sur les files de pRepSource.______
		
		// Fin de pPathRepLu.________________________________________
		} catch (IOException ioe2) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					fournirNomClasseConcrete()
					, "Méthode recopierArboAvecRemplacement(...)"
					, ioe2);
			
			/* retourne une IOException si Exception loggée 
			 * (ioException, ...). */
			final String messaqe 
				= "Impossible de copier le répertoire : " + pPathRepLu;
			
			throw new IOException(messaqe, ioe2);
		}

	} // Fin de recopierArboAvecRemplacement(
	 // Path pPathRepSource
	 // , Path pPathRepLu
	 // , Path pPathRepDestination)._______________________________________
	

	
	/**
	 * method deplacerRepertoire(
	 * File pRepADeplacer
	 * , String pCheminDestination) :<br/>
	 * <ul>
	 * <li>Déplace tout le contenu du répertoire source pRepADeplacer 
	 * sous le répertoire de destination situé à pCheminDestination.</li>
	 * <li>Ne recopie pas le répertoire source pRepADeplacer lui même 
	 * (considéré comme une racine) mais tout son contenu.</li>
	 * <li>Crée le répertoire de destination situé à pCheminDestination
	 * et son arborescence (chemin) si nécessaire.</li>
	 * <li>Retourne le répertoire destination situé à pCheminDestination
	 * avec tout le contenu recopié.</li>
	 * <li>Ecrase et remplace le contenu du répertoire 
	 * destination pRepDestination si il existe déjà.</li>
	 * <li>Supprime le répertoire source pRepADeplacer 
	 * après le déplacement.</li>
	 * </ul>
	 * - retourne null si pRepADeplacer est null.<br/>
	 * - retourne null si pRepADeplacer n'existe pas.<br/>
	 * - retourne null si pCheminDestination est blank.<br/>
	 * - retourne null si Exception loggée (ioException, ...).<br/>
	 * <br/>
	 *
	 * @param pRepADeplacer : File : Répertoire à déplacer.<br/>
	 * @param pCheminDestination : String : chemin complet 
	 * du répertoire destination.<br/>
	 * 
	 * @return : File : Le répertoire avec tout son contenu déplacé.<br/>
	 */
	public static File deplacerRepertoire(
			final File pRepADeplacer
				, final String pCheminDestination) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Recopie le contenu du répertoire pRepADeplacer 
			 * à pCheminDestination. */
			final File resultat 
				= copierRepertoireAvecRemplacement(
						pRepADeplacer, pCheminDestination);
			
			/* retourne null si la copie du contenu du 
			 * répertoire source dans pCheminDestination n'a pas eu lieu. */
			if (resultat == null) {
				return null;
			}
			
			/* Ecrase le répertoire source pRepADeplacer. */
			if (ecraserRepertoireEtSousArbo(pRepADeplacer)) {
				return resultat;
			}
			
			return null;
			
		} // Fin du bloc static synchronized.________________________
				
	} // Fin de deplacerRepertoire(
	 // File pRepADeplacer
	 // , String pCheminDestination).______________________________________
	
	
	
	/**
	 * method concatenerFichiersUtf8RetireBomEntete(
	 * List&lt;File&gt; pListFichiersAConcatener
	 * , File pFileDestination) :<br/>
	 * <ul>
	 * <li>Concatène tous les fichiers d'une 
	 * liste pListFichiersAConcatener dans un seul fichier 
	 * de destination pFileDestination.</li>
	 * <li>Lit tous les fichiers de la liste pListFichiersAConcatener 
	 * avec le Charset CHARSET_UTF8.</li>
	 * <li>Encode tous les fichiers concaténés en CHARSET_UTF8.</li>
	 * <ul>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour la lecture.</li>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour l'écriture.</li>
	 * <li>Crée le chemin (arborescence) vers le fichier pFileDestination 
	 * si il n'existe pas déjà sur le disque.</li>
	 * <li>Crée le fichier pFileDestination 
	 * si il n'existe pas déjà sur le disque.</li>
	 * <li>Ecrase le fichier destination pFileDestination sur disque 
	 * si il existe déjà.</li>
	 * <li>Retire un caractère BOM_UTF8.</li>
	 * <li>N'écrit pas dans pFileDestination la première 
	 * ligne des fichiers lus (en-tête).</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si la liste de Fichiers à concaténer 
	 * pListFichiersAConcatener est null.<br/>
	 * - Retourne null si la liste de Fichiers à concaténer 
	 * pListFichiersAConcatener est vide.<br/>
	 * - Retourne null si pFileDestination est null.<br/>
	 * <br/>
	 *
	 * @param pListFichiersAConcatener : List&lt;File&gt; : 
	 * Liste des fichiers à concaténer.<br/>
	 * @param pFileDestination : File : Le fichier destination.<br/>
	 * <br/> 
	 * 
	 * @return : File : fichier concaténé.<br/>
	 * 
	 * @throws Exception en cas d'Exception loggée 
	 * (IOException, MalformedInputException, ...).<br/>
	 */
	public static File concatenerFichiersUtf8RetireBomEntete(
			final List<File> pListFichiersAConcatener
					, final File pFileDestination) 
											throws Exception {
		
		return concatenerFichiers(
				pListFichiersAConcatener, CHARSET_UTF8
				, pFileDestination, CHARSET_UTF8
				, true
				, true
				, true);
		
	} // Fin de concatenerFichiersUtf8RetireBomEntete(
	 // List<File> pListFichiersAConcatener
	 // , File pFileDestination).__________________________________________

	
	
	/**
	 * method concatenerFichiersAvecRemplacement(
	 * List&lt;File&gt; pListFichiersAConcatener
	 * , Charset pCharsetLecture
	 * , File pFileDestination
	 * , Charset pCharsetEcriture
	 * , boolean pRetirerBOMUtf8
	 * , boolean pRetirerEntete) :<br/>
	 * <ul>
	 * <li>Concatène tous les fichiers d'une 
	 * liste pListFichiersAConcatener dans un seul fichier 
	 * de destination pFileDestination.</li>
	 * <li>Lit tous les fichiers de la liste pListFichiersAConcatener 
	 * avec le Charset pCharsetLecture.</li>
	 * <li>Encode tous les fichiers concaténés en pCharsetEcriture.</li>
	 * <li>Remplace un fichier concaténé existant.</li>
	 * <ul>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour la lecture 
	 * si pCharsetLecture est null.</li>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour l'écriture
	 * si pCharsetEcriture est null.</li>
	 * <li>Crée le chemin (arborescence) vers le fichier pFileDestination 
	 * si il n'existe pas déjà sur le disque.
	 * <li>Crée le fichier pFileDestination 
	 * si il n'existe pas déjà sur le disque.</li>
	 * <li>Ecrase le fichier destination pFileDestination sur disque 
	 * si il existe déjà.</li>
	 * <li>Retire un caractère BOM_UTF8 si pRetirerBOMUtf8 
	 * vaut true et charsetLecture vaut CHARSET_UTF8.</li>
	 * <li>N'écrit pas dans pFileDestination la première 
	 * ligne des fichiers lus (en-tête) si pRetirerEntete vaut true.</li>
	 * </ul>
	 * </ul>
	 * <br/>
	 * - Retourne null si la liste de Fichiers à concaténer 
	 * pListFichiersAConcatener est null.<br/>
	 * - Retourne null si la liste de Fichiers à concaténer 
	 * pListFichiersAConcatener est vide.<br/>
	 * - Retourne null si pFileDestination est null.<br/>
	 * <br/>
	 *
	 * @param pListFichiersAConcatener : List&lt;File&gt; : 
	 * Liste des fichiers à concaténer.<br/>
	 * @param pCharsetLecture : Charset : Charset dans lequel doivent 
	 * avoit été encodés les fichiers à concaténer.<br/>
	 * @param pFileDestination : File : Le fichier destination.<br/>
	 * @param pCharsetEcriture : Charset : 
	 * Charset d'encodage du fichier concaténé (destination).<br/>
	 * @param pRetirerBOMUtf8 : boolean : 
	 * retire un éventuel BOM-UTF8 en début de ligne si true 
	 * et pCharsetLecture vaut CHARSET_UTF8 
	 * (si les fichiers à concaténer sont encodés en UTF-8).<br/>
	 * @param pRetirerEntete : boolean : retire la première ligne 
	 * de chaque fichier à concaténer si true.<br/>
	 * <br/> 
	 * 
	 * @return : File : fichier concaténé.<br/>
	 * 
	 * @throws Exception en cas d'Exception loggée 
	 * (IOException, MalformedInputException, ...).<br/>
	 */
	public static File concatenerFichiersAvecRemplacement(
			final List<File> pListFichiersAConcatener
				, final Charset pCharsetLecture
					, final File pFileDestination
						, final Charset pCharsetEcriture
							, final boolean pRetirerBOMUtf8
								, final boolean pRetirerEntete) 
										throws Exception {
		
		return concatenerFichiers(
				pListFichiersAConcatener, pCharsetLecture
				, pFileDestination, pCharsetEcriture
				, true
				, pRetirerBOMUtf8
				, pRetirerEntete);
		
	} // Fin de concatenerFichiersAvecRemplacement(
	 // List<File> pListFichiersAConcatener
	 // , Charset pCharsetLecture
	 // , File pFileDestination
	 // , Charset pCharsetEcriture
	 // , boolean pRetirerBOMUtf8
	 // , boolean pRetirerEntete)._________________________________________
	
	
	
	/**
	 * method concatenerFichiersSansRemplacement(
	 * List&lt;File&gt; pListFichiersAConcatener
	 * , Charset pCharsetLecture
	 * , File pFileDestination
	 * , Charset pCharsetEcriture
	 * , boolean pRetirerBOMUtf8
	 * , boolean pRetirerEntete) :<br/>
	 * <ul>
	 * <li>Concatène tous les fichiers d'une 
	 * liste pListFichiersAConcatener dans un seul fichier 
	 * de destination pFileDestination.</li>
	 * <li>Lit tous les fichiers de la liste pListFichiersAConcatener 
	 * avec le Charset pCharsetLecture.</li>
	 * <li>Encode tous les fichiers concaténés en pCharsetEcriture.</li>
	 * <li>Ne remplace PAS un fichier concaténé existant.</li>
	 * <ul>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour la lecture 
	 * si pCharsetLecture est null.</li>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour l'écriture
	 * si pCharsetEcriture est null.</li>
	 * <li>Crée le chemin (arborescence) vers le fichier pFileDestination 
	 * si il n'existe pas déjà sur le disque.</li>
	 * <li>Crée le fichier pFileDestination 
	 * si il n'existe pas déjà sur le disque.</li>
	 * <li>N'écrase le fichier destination pFileDestination sur disque 
	 * si il existe déjà et le retourne inchangé.</li>
	 * <li>Retire un caractère BOM_UTF8 si pRetirerBOMUtf8 
	 * vaut true et charsetLecture vaut CHARSET_UTF8.</li>
	 * <li>N'écrit pas dans pFileDestination la première 
	 * ligne des fichiers lus (en-tête) si pRetirerEntete vaut true.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si la liste de Fichiers à concaténer 
	 * pListFichiersAConcatener est null.<br/>
	 * - Retourne null si la liste de Fichiers à concaténer 
	 * pListFichiersAConcatener est vide.<br/>
	 * - Retourne null si pFileDestination est null.<br/>
	 * <br/>
	 *
	 * @param pListFichiersAConcatener : List&lt;File&gt; : 
	 * Liste des fichiers à concaténer.<br/>
	 * @param pCharsetLecture : Charset : Charset dans lequel doivent 
	 * avoit été encodés les fichiers à concaténer.<br/>
	 * @param pFileDestination : File : Le fichier destination.<br/>
	 * @param pCharsetEcriture : Charset : 
	 * Charset d'encodage du fichier concaténé (destination).<br/>
	 * @param pRetirerBOMUtf8 : boolean : 
	 * retire un éventuel BOM-UTF8 en début de ligne si true 
	 * et pCharsetLecture vaut CHARSET_UTF8 
	 * (si les fichiers à concaténer sont encodés en UTF-8).<br/>
	 * @param pRetirerEntete : boolean : retire la première ligne 
	 * de chaque fichier à concaténer si true.<br/>
	 * <br/> 
	 * 
	 * @return : File : fichier concaténé.<br/>
	 * 
	 * @throws Exception en cas d'Exception loggée 
	 * (IOException, MalformedInputException, ...).<br/>
	 */
	public static File concatenerFichiersSansRemplacement(
			final List<File> pListFichiersAConcatener
				, final Charset pCharsetLecture
					, final File pFileDestination
						, final Charset pCharsetEcriture
							, final boolean pRetirerBOMUtf8
								, final boolean pRetirerEntete) 
										throws Exception {
		
		return concatenerFichiers(
				pListFichiersAConcatener, pCharsetLecture
				, pFileDestination, pCharsetEcriture
				, false
				, pRetirerBOMUtf8
				, pRetirerEntete);
		
	} // Fin de concatenerFichiersSansRemplacement(
	 // List<File> pListFichiersAConcatener
	 // , Charset pCharsetLecture
	 // , File pFileDestination
	 // , Charset pCharsetEcriture
	 // , boolean pRetirerBOMUtf8
	 // , boolean pRetirerEntete)._________________________________________
	

	
	/**
	 * method concatenerFichiers(
	 * List&lt;File&gt; pListFichiersAConcatener
	 * , Charset pCharsetLecture
	 * , File pFileDestination
	 * , Charset pCharsetEcriture
	 * , boolean pAvecRemplacement
	 * , boolean pRetirerBOMUtf8
	 * , boolean pRetirerEntete) :<br/>
	 * <ul>
	 * <li>Concatène tous les fichiers d'une 
	 * liste pListFichiersAConcatener dans un seul fichier 
	 * de destination pFileDestination.</li>
	 * <li>Lit tous les fichiers de la liste pListFichiersAConcatener 
	 * avec le Charset pCharsetLecture.</li>
	 * <li>Encode tous les fichiers concaténés en pCharsetEcriture.</li>
	 * <ul>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour la lecture 
	 * si pCharsetLecture est null.</li>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour l'écriture
	 * si pCharsetEcriture est null.</li>
	 * <li>Crée le chemin (arborescence) vers le fichier pFileDestination 
	 * si il n'existe pas déjà sur le disque.</li>
	 * <li>Crée le fichier pFileDestination 
	 * si il n'existe pas déjà sur le disque.</li>
	 * <li>Retourne pFileDestination si il existe déjà sur le disque 
	 * et pAvecRemplacement vaut false (pas de remplacement).</li>
	 * <li>Ecrase le fichier destination pFileDestination sur disque 
	 * si il existe déjà et pAvecRemplacement vaut true 
	 * (remplacement).</li>
	 * <li>Retire un caractère BOM_UTF8 si pRetirerBOMUtf8 
	 * vaut true et charsetLecture vaut CHARSET_UTF8.</li>
	 * <li>N'écrit pas dans pFileDestination la première 
	 * ligne des fichiers lus (en-tête) si pRetirerEntete vaut true.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si la liste de Fichiers à concaténer 
	 * pListFichiersAConcatener est null.<br/>
	 * - Retourne null si la liste de Fichiers à concaténer 
	 * pListFichiersAConcatener est vide.<br/>
	 * - Retourne null si pFileDestination est null.<br/>
	 * <br/>
	 *
	 * @param pListFichiersAConcatener : List&lt;File&gt; : 
	 * Liste des fichiers à concaténer.<br/>
	 * @param pCharsetLecture : Charset : Charset dans lequel doivent 
	 * avoit été encodés les fichiers à concaténer.<br/>
	 * @param pFileDestination : File : Le fichier destination.<br/>
	 * @param pCharsetEcriture : Charset : 
	 * Charset d'encodage du fichier concaténé (destination).<br/>
	 * @param pAvecRemplacement : boolean : remplace le fichier 
	 * concaténé existant si true.<br/>
	 * @param pRetirerBOMUtf8 : boolean : 
	 * retire un éventuel BOM-UTF8 en début de ligne si true 
	 * et pCharsetLecture vaut CHARSET_UTF8 
	 * (si les fichiers à concaténer sont encodés en UTF-8).<br/>
	 * @param pRetirerEntete : boolean : retire la première ligne 
	 * de chaque fichier à concaténer si true.<br/>
	 * <br/> 
	 * 
	 * @return : File : fichier concaténé.<br/>
	 * 
	 * @throws Exception en cas d'Exception loggée 
	 * (IOException, MalformedInputException, ...).<br/>
	 */
	public static File concatenerFichiers(
			final List<File> pListFichiersAConcatener
				, final Charset pCharsetLecture
					, final File pFileDestination
						, final Charset pCharsetEcriture
							, final boolean pAvecRemplacement
								, final boolean pRetirerBOMUtf8
									, final boolean pRetirerEntete) 
											throws Exception {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si la liste de Fichiers à concaténer 
			 * pListFichiersAConcatener est null. */
			if (pListFichiersAConcatener == null) {
				return null;
			}
			
			/* Retourne null si la liste de Fichiers à concaténer 
			 * pListFichiersAConcatener est vide. */
			if (pListFichiersAConcatener.isEmpty()) {
				return null;
			}

			/* Retourne null si pFileDestination est null. */
			if (pFileDestination == null) {
				return null;
			}
			
			/* Retourne pFileDestination si il existe déjà sur le disque 
			 * et pAvecRemplacement vaut false (pas de remplacement). */
			if (pFileDestination.exists() && !pAvecRemplacement) {
				return pFileDestination;
			}
			
			/* Ecrase le fichier destination pFileDestination sur disque 
			 * si il existe déjà et pAvecRemplacement vaut true 
			 * (remplacement). */
			if (pFileDestination.exists() && pAvecRemplacement) {
				ecraserFichierSurDisque(pFileDestination);
			}
			
			/* Récupère le Path de pFileDestination. */
			final Path pathFileDestination = pFileDestination.toPath();
			
			if (pathFileDestination == null) {
				return null;
			}
			
			/* Crée le chemin vers le fichier pFileDestination 
			 * si il n'existe pas déjà sur le disque. */
			
			/* Récupère le parent de pFileDestination. */
			final File repPereFileDestination 
				= pFileDestination.getParentFile();
			
			if (repPereFileDestination == null) {
				return null;
			}
			
			/* Récupère le Path de repPereFileDestination. */
			final Path pathRepPereFileDestination 
				= repPereFileDestination.toPath();
			
			/* Création du chemin vers pFileDestination. */
			if (!Files.exists(pathRepPereFileDestination)) {
				Files.createDirectories(pathRepPereFileDestination);
			}
			
			
			/* Crée le fichier pFileDestination 
			 * si il n'existe pas déjà sur le disque. */
			if (!Files.exists(pathFileDestination)) {
				
				try {
					
					/* Création du fichier pFileDestination. */
					Files.createFile(pathFileDestination);
					
				} catch (IOException ioe1) {
					
					/* LOG de niveau Error. */
					loggerError(
							fournirNomClasseConcrete()
								, METHODE_CONCATENER_FICHIERS
									, ioe1);
					
					final String message 
					= fournirNomClasseConcrete() 
					+ SEP_MOINS 
					+ METHODE_CONCATENER_FICHIERS 
					+ SEP_MOINS 
					+ "Impossible de créer le fichier Destination : " 
					+ pathFileDestination;
					
					/* Jette une Exception en cas 
					 * d'Exception loggée (IOException, ...). */
					throw new Exception(message, ioe1);
				}
				
			} // Fin de if (!Files.exists(pathFileDestination)).______
			
			Charset charsetLecture = null;
			Charset charsetEcriture = null;
			
			/* Utilise automatiquement le CHARSET_UTF8 si 
			 * pCharsetLecture est null. */			
			if (pCharsetLecture == null) {
				charsetLecture = CHARSET_UTF8;
			}
			else {
				charsetLecture = pCharsetLecture;
			}
			
			/* Utilise automatiquement le CHARSET_UTF8 si 
			 * pCharsetEcriture est null. */			
			if (pCharsetEcriture == null) {
				charsetEcriture = CHARSET_UTF8;
			}
			else {
				charsetEcriture = pCharsetEcriture;
			}

			// ****************************************************
			/* LECTURE. */
			InputStream inputStream = null;  
			InputStreamReader inputStreamReader = null;  
			BufferedReader bufferedReader = null;  
			
			/* ECRITURE. */
			OutputStream outputStream = null;  
			OutputStreamWriter outputStreamWriter = null;  
			BufferedWriter bufferedWriter = null;  
			
						
			/* Parcours de la liste des fichiers à concaténer. */
			for (final File fichier : pListFichiersAConcatener) {
				
				/* Elimine les fichiers null contenus dans la liste. */
				if (fichier == null) {
					continue;
				}
				
				/* Elimine les fichiers inexistants 
				 * sur disque contenus dans la liste. */
				if (!fichier.exists()) {
					continue;
				}
				
				/* Elimine les répertoires contenus dans la liste. */
				if (fichier.isDirectory()) {
					continue;
				}
				
				/* Elimine les fichiers vides contenus dans la liste. */
				if (fichier.length() == 0) {
					continue;
				}
				
				/* LECTURE DU FICHIER AVEC CHARSET charsetLecture. */
				inputStream = new FileInputStream(fichier);
				inputStreamReader 
					= new InputStreamReader(inputStream, charsetLecture);
				bufferedReader = new BufferedReader(inputStreamReader);
				
				/* ECRITURE DANS LE FICHIER DESTINATION 
				 * AVEC LE CHARSET charsetEcriture. */
				/* Ecrit à la fin de pFileDestination. */
				outputStream = new FileOutputStream(pFileDestination, true);
				outputStreamWriter 
					= new OutputStreamWriter(outputStream, charsetEcriture);
				bufferedWriter = new BufferedWriter(outputStreamWriter);
				
				
				String ligneLue = null;
				int numeroLigneLue = 0;
				
				/* BOUCLE SUR LES LIGNES DU FICHIER fichier. */
				while (true) {
					
										
					try {
						
						/* LECTURE DE CHAQUE LIGNE. */
						ligneLue = bufferedReader.readLine();
						
						/* Incrémente le numéro de la ligne lue. */
						numeroLigneLue++;
						
						/* N'écrit pas dans pFileDestination la première 
						 * ligne des fichiers lus (en-tête) 
						 * si pRetirerEntete vaut true. */
						if (numeroLigneLue == 1 && pRetirerEntete) {
							continue;
						}
						
						/* Ferme les flux et Sort de la boucle 
						 * si fin de fichier. */
						if (ligneLue == null) {
							
							/* Fermeture des flux de lecture. */						
							bufferedReader.close();						
							inputStreamReader.close();
							inputStream.close();
							
							
							/* Fermeture des flux d'écriture. */
							bufferedWriter.close();						
							outputStreamWriter.close();						
							outputStream.close();
							
							break;
						}
						
						/* TRANSFORMATIONS DE LA LIGNE LUE AVANT ECRITURE. */
						String ligneAEcrire = null;
						
						/* Retire un caractère BOM_UTF8 si pRetirerBOMUtf8 
						 * vaut true et charsetLecture vaut CHARSET_UTF8. */
						if (StringUtils.contains(ligneLue, BOM_UTF_8) 
								&& pRetirerBOMUtf8 
									&& charsetLecture.equals(CHARSET_UTF8)) {
							
							ligneAEcrire 
								= StringUtils.remove(ligneLue, BOM_UTF_8);
							
						} // Fin du retrait éventuel du BOM_UTF8.______
						else {
							ligneAEcrire = ligneLue;
						}
												
						/* ECRITURE DE CHAQUE LIGNE. ********** */
						bufferedWriter.write(ligneAEcrire);
						/* Saut de ligne. */
						bufferedWriter.write(NEWLINE);
						bufferedWriter.flush();
						
					} catch (Exception e) {
						
						/* LOG de niveau Error. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_CONCATENER_FICHIERS
								, e);
						
						final String message 
						= fournirNomClasseConcrete() 
						+ SEP_MOINS 
						+ METHODE_CONCATENER_FICHIERS 
						+ SEP_MOINS 
						+ "Impossible de lire ou d'écrire la ligne numéro : " 
						+ numeroLigneLue + " : '" 
						+ StringUtils.abbreviate(ligneLue, 100) 
						+ "'";
						
						/* Fermeture des flux de lecture. */						
						bufferedReader.close();						
						inputStreamReader.close();
						inputStream.close();
						
						
						/* Fermeture des flux d'écriture. */
						bufferedWriter.close();						
						outputStreamWriter.close();						
						outputStream.close();
													
						/* Jette une Exception en cas de mauvaise 
						 * lecture/écriture d'une ligne. */
						throw new Exception(message, e);
					}
										
				} // Fin de BOUCLE SUR LES LIGNES DU FICHIER fichier._				
				
			} // Fin de Parcours de la liste 
			// des fichiers à concaténer.___________________________

			
			/* Fermeture des flux de lecture. */
			if (bufferedReader != null) {
				bufferedReader.close();
			}
			if (inputStreamReader != null) {
				inputStreamReader.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			
			/* Fermeture des flux d'écriture. */
			if (bufferedWriter != null) {
				bufferedWriter.close();
			}
			if (outputStreamWriter != null) {
				outputStreamWriter.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
						
			/* Retour du fichier concaténé. */
			return pFileDestination;
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de concatenerFichiers(
	 // List<File> pListFichiersAConcatener
	 // , Charset pCharsetLecture
	 // , File pFileDestination
	 // , Charset pCharsetEcriture
	 // , boolean pAvecRemplacement
	 // , boolean pRetirerBOMUtf8
	 // , boolean pRetirerEntete)._________________________________________

	
	
	/**
	 * method fournirListeSousRepertoiresDeRepertoire(
	 * File pRepertoire) :<br/>
	 * <ul>
	 * <li>fournit la liste des sous-répertoires (pas fichiers simples) 
	 * contenus dans un répertoire.</li>
	 * </ul>
	 * - retourne null si pRepertoire est null.<br/>
	 * - retourne null si pRepertoire n'existe pas.<br/>
	 * - retourne null si pRepertoire n'est pas un répertoire.<br/>
	 * - retourne null si le répertoire est vide.<br/>
	 * <br/>
	 *
	 * @param pRepertoire : File : répertoire contenant 
	 * les sous-répertoires dont on veut la liste.<br/>
	 * 
	 * @return : List<File> : Liste des sous-répertoires
	 * contenus dans pRepertoire.<br/>
	 */
	public static List<File> fournirListeSousRepertoiresDeRepertoire(
			final File pRepertoire) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si pRepertoire est null. */
			if (pRepertoire == null) {
				return null;
			}
			
			/* retourne null si pRepertoire n'existe pas. */
			if (!pRepertoire.exists()) {
				return null;
			}
			
			/* retourne null si pRepertoire n'est pas un répertoire. */
			if (!pRepertoire.isDirectory()) {
				return null;
			}
			
			/* récupère le tableau des enfants de pRepertoire. */
			final File[] tableauEnfants = pRepertoire.listFiles();
			
			/* retourne null si le répertoire est vide. */
			if (tableauEnfants == null) {
				return null;
			}
			
			final List<File> resultat = new ArrayList<File>();
			
			/* remplit la liste des sous-répertoires de pRepertoire. */		
			final List<File> listeSousFichiers = Arrays.asList(tableauEnfants);
			
			for (final File sousFichier : listeSousFichiers) {
				
				if (sousFichier.isDirectory()) {
					resultat.add(sousFichier);
				}
			}
			
			return resultat;
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de fournirListeSousRepertoiresDeRepertoire(
	 // File pRepertoire)._________________________________________________
	
	
	
	/**
	 * method fournirListeFichiersDansRepertoire(
	 * File pRepertoire) :<br/>
	 * <ul>
	 * <li>fournit la liste des fichiers simples (pas répertoire) 
	 * contenus dans un répertoire.</li>
	 * </ul>
	 * - retourne null si pRepertoire est null.<br/>
	 * - retourne null si pRepertoire n'existe pas.<br/>
	 * - retourne null si pRepertoire n'est pas un répertoire.<br/>
	 * - retourne null si le répertoire est vide.<br/>
	 * <br/>
	 *
	 * @param pRepertoire : File : répertoire contenant les fichiers 
	 * simples dont on veut la liste.<br/>
	 * 
	 * @return : List<File> : Liste des fichiers simples 
	 * contenus dans pRepertoire.<br/>
	 */
	public static List<File> fournirListeFichiersDansRepertoire(
			final File pRepertoire) {
				
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si pRepertoire est null. */
			if (pRepertoire == null) {
				return null;
			}
			
			/* retourne null si pRepertoire n'existe pas. */
			if (!pRepertoire.exists()) {
				return null;
			}
			
			/* retourne null si pRepertoire n'est pas un répertoire. */
			if (!pRepertoire.isDirectory()) {
				return null;
			}
			
			/* récupère le tableau des enfants de pRepertoire. */
			final File[] tableauEnfants = pRepertoire.listFiles();
			
			/* retourne null si le répertoire est vide. */
			if (tableauEnfants == null) {
				return null;
			}
			
			final List<File> resultat = new ArrayList<File>();
			
			/* remplit la liste des sous-fichiers simples de pRepertoire. */		
			final List<File> listeSousFichiers = Arrays.asList(tableauEnfants);
			
			for (final File sousFichier : listeSousFichiers) {
				
				if (sousFichier.isFile()) {
					resultat.add(sousFichier);
				}
			}
			
			return resultat;
			

		} // Fin du bloc static synchronized.________________________
		
	} // Fin de fournirListeFichiersDansRepertoire(
	 // File pRepertoire)._________________________________________________
	

	
	/**
	 * method calculerTailleRepertoire(
	 * File pRep) :<br/>
	 * <ul>
	 * <li>Calcule la taille en octets du contenu d'un répertoire.</li>
	 * </ul>
	 * - retourne null si pRep est null.<br/>
	 * - retourne null si pRep n'existe pas sur disque.<br/>
	 * <br/>
	 *
	 * @param pRep : File : répertoire dont on veut 
	 * calculer la taille en octets.<br/>
	 * 
	 * @return : Long : taille en octets.<br/>
	 */
	public static Long calculerTailleRepertoire(
			final File pRep) {

		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si pRep est null. */
			if (pRep == null) {
				return null;
			}
			
			/* retourne null si pRep n'existe pas sur disque. */
			if (!pRep.exists()) {
				return null;
			}
			
			Long tailleEnOctets = 0L;
			
			tailleEnOctets = calculerTaille(tailleEnOctets, pRep);
			
			return tailleEnOctets;
			
		} // Fin du bloc static synchronized.________________________
				
	} // Fin de calculerTailleRepertoire(
	 // File pRep).________________________________________________________
	

	
	/**
	 * method calculerTaille(
	 * Long pTailleDepartEnOctets
	 * , File pFile) :<br/>
	 * <ul>
	 * <li>Calcule (récursif) la taille du contenu d'un répertoire.</li>
	 * <br/>
	 * <ul>
	 * <li>Méthode récursive.</li>
	 * </ul>
	 * </ul>
	 * - retourne null si pFile est null.<br/>
	 * - retourne null si pFile n'existe pas sur disque.<br/>
	 * <br/>
	 *
	 * @param pTailleDepartEnOctets : Long : 
	 * taille sommée du contenu déjà lu.<br/>
	 * @param pFile : File : Fichier simple dont on veut la taille 
	 * ou répertoire qui provoque un appel récursif.<br/>
	 * 
	 * @return : Long : Taille de pFile.<br/>
	 */
	private static Long calculerTaille(
			final Long pTailleDepartEnOctets
				, final File pFile) {
		
		/* retourne null si pFile est null. */
		if (pFile == null) {
			return null;
		}
		
		/* retourne null si pFile n'existe pas sur disque. */
		if (!pFile.exists()){
			return null;
		}
		
		/* Récupère la liste des File contenus dans pFile. */
		final File[] filesContenus = pFile.listFiles();
		
		if (filesContenus == null) {
			return null;
		}
		
		Long tailleSommee = pTailleDepartEnOctets;
		
		/* Boucle sur chaque File de pFile. */
		for (final File fichier : filesContenus) {
			
			// APPEL RECURSIF si fichier est un répertoire.
			if (fichier.isDirectory()) {
				tailleSommee = calculerTaille(tailleSommee, fichier);
			}
			// Somme la taille si pFile est un fichier simple.
			else {
				
				/* récupération de la taille en octets du fichier simple. */
				final Long tailleFichier = fichier.length();
				
				/* Ajout de la taille du fichier à la taille calculée. */
				tailleSommee = tailleSommee + tailleFichier;
			}
			
		} // Fin de Boucle sur chaque File de pFile.________________
		
		return tailleSommee;
		
	} // Fin de calculerTaille(
	 // Long pTailleDepartEnOctets
	 // , File pFile)._____________________________________________________
	
	
	
	/**
	 * method renommerFichiersEnEnlevantPartie(
	 * List&lt;File&gt; pListFiles
	 * , String pPartieAOter) :<br/>
	 * <ul>
	 * <li>Renomme une liste de fichiers en retirant 
	 * la chaine pPartieAOter du nom simple de chaque fichier</li>
	 * </ul>
	 * - retourne sans rien faire si pListFiles == null.<br/>
	 * <br/>
	 *
	 * @param pListFiles : List&lt;File&gt; : 
	 * Liste des fichiers que l'on veut renommer 
	 * en ôtant à leur nom simple pPartieAOter.<br/>
	 * @param pPartieAOter : String :  .<br/>
	 */
	public static void renommerFichiersEnEnlevantPartie(
			final List<File> pListFiles
				, final String pPartieAOter) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne sans rien faire si pListFiles == null. */
			if (pListFiles == null) {
				return;
			}
			
			for (final File fichier : pListFiles) {
				
				if (fichier != null) {
					
					final String nomFichier = fichier.getName();
					final Path pathFichier = fichier.toPath();
					
					/* Continue si le nom ne contient pas la partie à ôter. */
					if (!StringUtils.contains(nomFichier, pPartieAOter)) {
						continue;
					}
					
					/* Constitution du nouveau nom. */
					final String nouveauNom 
						= StringUtils.remove(nomFichier, pPartieAOter);
					
					try {
						
						// *******************************
						// RENOMMAGE
						// *******************************
						Files.move(
								pathFichier
									, pathFichier.resolveSibling(nouveauNom));
						
					} catch (IOException ioe) {
						
						if (LOG.isFatalEnabled()) {
							LOG.fatal("Erreur dans "
									+ "renommerFichiersEnEnlevantPartie(..) : "
									, ioe);
						}
						
					}
					
				}
			}
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de renommerFichiersEnEnlevantPartie(
	 // List<File> pListFiles
	 // , String pPartieAOter).____________________________________________
	

	
	/**
	 * method insererLigneDansFichier(
	 * File pFile
	 * , Charset pCharsetLecture
	 * , int pNumLigne
	 * , Charset pCharsetEcriture
	 * , String pLigneAInserer) :<br/>
	 * <ul>
	 * <li>Insère la ligne pLigneAInserer à la pNumLigne-ième ligne 
	 * (1-based) du fichier textuel simple pFile.</li>
	 * <li>Lit le fichier textuel simple 
	 * pFile avec l'encodage pCharsetLecture.</li>
	 * <li>Ecrit la ligne pLigneAInserer dans le fichier simple textuel pFile 
	 * avec l'encodage pCharsetEcriture.</li>
	 * <ul>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour la lecture 
	 * si pCharsetLecture est null.</li>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour l'écriture
	 * si pCharsetEcriture est null.</li>
	 * </ul> 
	 * </ul>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas sur le disque.<br/>
	 * - Retourne null si pFile est un répertoire.<br/>
	 * - Retourne null si pNumLigne == 0.<br/>
	 * - Retourne null en cas d'Exception loggée (IOException, ...).<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier textuel simple dans lequel 
	 * on veut insérer pLigneAInserer.<br/>
	 * @param pCharsetLecture : Charset : Charset dans lequel 
	 * le fichier simple textuel pFile est encodé. 
	 * On le lit donc avec ce Charset.<br/>
	 * @param pNumLigne : int : numéro (1-based) 
	 * de la ligne à laquelle il faut insérer pLigneAInserer.<br/>
	 * @param pCharsetEcriture : Charset : Charset avec lequel 
	 * on écrit pLigneAInserer dans pFile.<br/>
	 * @param pLigneAInserer : String : ligne à insérer dans pFile.<br/>
	 * 
	 * @return : File : Fichier simple textuel dans lequel 
	 * on a inséré la ligne pLigneAInserer à la pNumLigne-ième ligne.<br/>
	 */
	public static File insererLigneDansFichier(
			final File pFile
				, final Charset pCharsetLecture
					, final int pNumLigne
						, final Charset pCharsetEcriture
							, final String pLigneAInserer) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pFile est null. */
			if (pFile == null) {
				return null;
			}
			
			/* Retourne null si pFile n'existe pas sur le disque. */
			if (!pFile.exists()) {
				return null;
			}
			
			/* Retourne null si pFile est un répertoire. */
			if (pFile.isDirectory()) {
				return null;
			}
			
			/* Retourne null si pNumLigne == 0. */
			if (pNumLigne == 0) {
				return null;
			}
			
			Charset charsetLecture = null;
			Charset charsetEcriture = null;
			
			/* Utilise automatiquement le CHARSET_UTF8 si 
			 * pCharsetLecture est null. */			
			if (pCharsetLecture == null) {
				charsetLecture = CHARSET_UTF8;
			}
			else {
				charsetLecture = pCharsetLecture;
			}
			
			/* Utilise automatiquement le CHARSET_UTF8 si 
			 * pCharsetEcriture est null. */			
			if (pCharsetEcriture == null) {
				charsetEcriture = CHARSET_UTF8;
			}
			else {
				charsetEcriture = pCharsetEcriture;
			}

			File fichierProvisoire = null;
			final Path pathPFile = pFile.toPath();
			
			try {
				
				/* Création d'un fichier provisoire pour l'insertion. */
				fichierProvisoire = fournirFichierAPartirDeFile(pFile, "1");
				
				if (!fichierProvisoire.exists()) {
					Files.createFile(fichierProvisoire.toPath());
				}
				
				
			} catch (IOException e1) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
						, METHODE_INSERER_LIGNE
						, e1);
				
				/* retourne null. */
				return null;
			}
			
			
			// ****************************************************
			/* LECTURE. */
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader bufferedReader = null;
			
			/* ECRITURE. */
			OutputStream outputStream = null;
			OutputStreamWriter outputStreamWriter = null;
			BufferedWriter bufferedWriter = null;
			
			
			try {

				/* LECTURE DU FICHIER AVEC CHARSET charsetLecture. */
				inputStream = new FileInputStream(pFile);
				inputStreamReader 
					= new InputStreamReader(inputStream, charsetLecture);
				bufferedReader = new BufferedReader(inputStreamReader);

				/*
				 * ECRITURE DANS LE FICHIER DESTINATION AVEC LE CHARSET
				 * charsetEcriture.
				 */
				/* Ecrit au début de fichierProvisoire. */
				outputStream = new FileOutputStream(fichierProvisoire);
				outputStreamWriter = new OutputStreamWriter(outputStream,
						charsetEcriture);
				bufferedWriter = new BufferedWriter(outputStreamWriter);

				String ligneLue = null;
				int numeroLigneLue = 0;
				
				/* BOUCLE SUR LES LIGNES DE pFile. */
				while (true) {
					
					/* Incrémentation du compteur de ligne. */
					numeroLigneLue++;
					
					/* Insère la ligne pLigneAInserer dans fichierProvisoire 
					 * si la position dans le fichier pFile est pNumLigne. */
					if (numeroLigneLue == pNumLigne) {
						bufferedWriter.write(pLigneAInserer);
						bufferedWriter.write(NEWLINE);
					}
					
					/* Lecture de la ligne. */
					ligneLue = bufferedReader.readLine();
					
					/* Fin de boucle à la fin du fichier. */
					if (ligneLue == null) {
						break;
					}
					
					/* Ecrit la ligne lue dans le fichierProvisoire. */
					bufferedWriter.write(ligneLue);
					bufferedWriter.write(NEWLINE);
					
					/* Flush du tampon. */
					bufferedWriter.flush();
					
				} // Fin de BOUCLE SUR LES LIGNES DE pFile._____
				
			} catch (FileNotFoundException e) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
						, METHODE_INSERER_LIGNE
						, e);
				
				/* retourne null. */
				return null;
				
			} catch (IOException e) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
						, METHODE_INSERER_LIGNE
						, e);
				
				/* retourne null. */
				return null;
				
			}
			
			finally {
				
				/* Fermeture des flux de lecture. */
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_INSERER_LIGNE
								, e);
					}
				}
				if (inputStreamReader != null) {
					try {
						inputStreamReader.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_INSERER_LIGNE
								, e);
					}
				}
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_INSERER_LIGNE
								, e);
					}
				}
				
				/* Fermeture des flux d'écriture. */
				if (bufferedWriter != null) {
					try {
						bufferedWriter.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_INSERER_LIGNE
								, e);
						
					}
				}
				if (outputStreamWriter != null) {
					try {
						outputStreamWriter.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_INSERER_LIGNE
								, e);
					}
				}
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_INSERER_LIGNE
								, e);
						
					}
				}
				
				
				try {
					
					/* Destruction du fichier original. */
					Files.deleteIfExists(pathPFile);
					
					/* Renommage du fichier provisoire en pFile. */
					Files.move(
							fichierProvisoire.toPath()
							, pathPFile
							, StandardCopyOption.REPLACE_EXISTING);
					
				} catch (IOException e) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							fournirNomClasseConcrete()
							, METHODE_INSERER_LIGNE
							, e);
				}
								
			} // Fin du finally._______________________________
			
			return pFile;
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de insererLigneDansFichier(
	 // File pFile
	 // , Charset pCharsetLecture
	 // , int pNumLigne
	 // , Charset pCharsetEcriture
	 // , String pLigneAInserer.___________________________________________
	

	
	/**
	 * method retirerLigneDansFichier(
	 * File pFile
	 * , Charset pCharsetLecture
	 * , int pNumLigne) :<br/>
	 * <ul>
	 * <li>Retire la pNumLigne-ième ligne 
	 * (1-based) du fichier textuel simple pFile.</li>
	 * <li>Lit le fichier textuel simple 
	 * pFile avec l'encodage pCharsetLecture.</li>
	 * <ul>
	 * <li>Utilise automatiquement le CHARSET_UTF8 pour la lecture 
	 * si pCharsetLecture est null.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas sur le disque.<br/>
	 * - Retourne null si pFile est un répertoire.<br/>
	 * - Retourne null si pNumLigne == 0.<br/>
	 * - Retourne null en cas d'Exception loggée (IOException, ...).<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier textuel simple dans lequel 
	 * on veut retirer la pNumLigne-ième (1-based) ligne.<br/>
	 * @param pCharsetLecture : Charset : Charset dans lequel 
	 * le fichier simple textuel pFile est encodé. 
	 * On le lit donc avec ce Charset.<br/>
	 * @param pNumLigne : int : numéro (1-based) 
	 * de la ligne à retirer à pFile.<br/>
	 * 
	 * @return : File : Fichier simple textuel 
	 * dans lequel on a retiré la ligne pNumLigne.<br/>
	 */
	public static File retirerLigneDansFichier(
			final File pFile
				, final Charset pCharsetLecture
					, final int pNumLigne) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pFile est null. */
			if (pFile == null) {
				return null;
			}
			
			/* Retourne null si pFile n'existe pas sur le disque. */
			if (!pFile.exists()) {
				return null;
			}
			
			/* Retourne null si pFile est un répertoire. */
			if (pFile.isDirectory()) {
				return null;
			}
			
			/* Retourne null si pNumLigne == 0. */
			if (pNumLigne == 0) {
				return null;
			}
			
			Charset charsetLecture = null;
			
			/* Utilise automatiquement le CHARSET_UTF8 si 
			 * pCharsetLecture est null. */			
			if (pCharsetLecture == null) {
				charsetLecture = CHARSET_UTF8;
			}
			else {
				charsetLecture = pCharsetLecture;
			}
			
			File fichierProvisoire = null;
			final Path pathPFile = pFile.toPath();
			
			try {
				
				/* Création d'un fichier provisoire pour l'insertion. */
				fichierProvisoire = fournirFichierAPartirDeFile(pFile, "2");
				
				if (!fichierProvisoire.exists()) {
					Files.createFile(fichierProvisoire.toPath());
				}
				
				
			} catch (IOException e1) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
						, METHODE_RETIRER_LIGNE
						, e1);
				
				/* retourne null. */
				return null;
			}

			// ****************************************************
			/* LECTURE. */
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader bufferedReader = null;
			
			/* ECRITURE. */
			OutputStream outputStream = null;
			OutputStreamWriter outputStreamWriter = null;
			BufferedWriter bufferedWriter = null;
			
			
			try {

				/* LECTURE DU FICHIER AVEC CHARSET charsetLecture. */
				inputStream = new FileInputStream(pFile);
				inputStreamReader 
					= new InputStreamReader(inputStream, charsetLecture);
				bufferedReader = new BufferedReader(inputStreamReader);

				/*
				 * ECRITURE DANS LE FICHIER DESTINATION AVEC LE CHARSET
				 * charsetEcriture.
				 */
				/* Ecrit au début de fichierProvisoire. */
				outputStream = new FileOutputStream(fichierProvisoire);
				outputStreamWriter = new OutputStreamWriter(outputStream,
						charsetLecture);
				bufferedWriter = new BufferedWriter(outputStreamWriter);

				String ligneLue = null;
				int numeroLigneLue = 0;
				
				/* BOUCLE SUR LES LIGNES DE pFile. */
				while (true) {
															
					/* Lecture de la ligne. */
					ligneLue = bufferedReader.readLine();
					
					/* Fin de boucle à la fin du fichier. */
					if (ligneLue == null) {
						break;
					}
					
					/* Incrémentation du compteur de ligne. */
					numeroLigneLue++;

					/* Saute la ligne 
					 * si la position dans le fichier pFile est pNumLigne. */
					if (numeroLigneLue == pNumLigne) {
						continue;
					}

					/* Ecrit la ligne lue dans le fichierProvisoire. */
					bufferedWriter.write(ligneLue);
					bufferedWriter.write(NEWLINE);
					
					/* Flush du tampon. */
					bufferedWriter.flush();
					
				} // Fin de BOUCLE SUR LES LIGNES DE pFile._____
				
			} catch (FileNotFoundException e) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
						, METHODE_RETIRER_LIGNE
						, e);
				
				/* retourne null. */
				return null;
				
			} catch (IOException e) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
						, METHODE_RETIRER_LIGNE
						, e);
				
				/* retourne null. */
				return null;
				
			}
			
			finally {
				
				/* Fermeture des flux de lecture. */
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_RETIRER_LIGNE
								, e);
					}
				}
				if (inputStreamReader != null) {
					try {
						inputStreamReader.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_RETIRER_LIGNE
								, e);
					}
				}
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_RETIRER_LIGNE
								, e);
					}
				}
				
				/* Fermeture des flux d'écriture. */
				if (bufferedWriter != null) {
					try {
						bufferedWriter.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_RETIRER_LIGNE
								, e);
						
					}
				}
				if (outputStreamWriter != null) {
					try {
						outputStreamWriter.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_RETIRER_LIGNE
								, e);
					}
				}
				if (outputStream != null) {
					try {
						outputStream.close();
					} catch (IOException e) {
						
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_RETIRER_LIGNE
								, e);
						
					}
				}
				
				
				try {
					
					/* Destruction du fichier original. */
					Files.deleteIfExists(pathPFile);
					
					/* Renommage du fichier provisoire en pFile. */
					Files.move(
							fichierProvisoire.toPath()
							, pathPFile
							, StandardCopyOption.REPLACE_EXISTING);
					
				} catch (IOException e) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							fournirNomClasseConcrete()
							, METHODE_RETIRER_LIGNE
							, e);
				}
								
			} // Fin du finally._______________________________
			
			return pFile;
					
		} // Fin du bloc static synchronized.________________________

	} // Fin de retirerLigneDansFichier(
	 // File pFile
	 // , Charset pCharsetLecture
	 // , int pNumLigne).__________________________________________________
	
	
	
	/**
	 * method compterLignes(
	 * File pFile) :<br/>
	 * <ul>
	 * <li>Compte le nombre de lignes dans un fichier.</li>
	 * </ul>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas sur le disque.<br/>
	 * - Retourne null si pFile est un répertoire.<br/>
	 * - Retourne null en cas d'Exception loggée (IOException, ...).<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @return : Integer : Nombre de lignes dans le fichier pFile.<br/>
	 */
	public static Integer compterLignes(
			final File pFile) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pFile est null. */
			if (pFile == null) {
				return null;
			}
			
			/* Retourne null si pFile n'existe pas sur le disque. */
			if (!pFile.exists()) {
				return null;
			}
			
			/* Retourne null si pFile est un répertoire. */
			if (pFile.isDirectory()) {
				return null;
			}
			
			String ligneLue = null;
			Integer numeroLigneLue = 0;
			
			// ****************************************************
			/* LECTURE. */
			InputStream inputStream = null;
			InputStreamReader inputStreamReader = null;
			BufferedReader bufferedReader = null;
			
			
			try {
				
				/* LECTURE DU FICHIER. */
				inputStream = new FileInputStream(pFile);
				inputStreamReader 
				= new InputStreamReader(inputStream);
				bufferedReader = new BufferedReader(inputStreamReader);
				
				/* BOUCLE SUR LES LIGNES DE pFile. */
				while (true) {
														
					/* Lecture de la ligne. */
					ligneLue = bufferedReader.readLine();
					
					/* Fin de boucle à la fin du fichier. */
					if (ligneLue == null) {
						break;
					}
					
					/* Incrémentation du compteur de ligne. */
					numeroLigneLue++;
									
				} // Fin de BOUCLE SUR LES LIGNES DE pFile._____
				
			} catch (FileNotFoundException e) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
						, METHODE_COMPTER_LIGNES
						, e);
				
				/* retourne null. */
				return null;
				
			} catch (IOException e) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						fournirNomClasseConcrete()
						, METHODE_COMPTER_LIGNES
						, e);
				
				/* retourne null. */
				return null;
				
			}
			finally {
				
				/* Fermeture des flux. */
				if (bufferedReader != null) {
					try {
						bufferedReader.close();
					} catch (IOException e) {
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_COMPTER_LIGNES
								, e);
					}
				}
				if (inputStreamReader != null) {
					try {
						inputStreamReader.close();
					} catch (IOException e) {
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_COMPTER_LIGNES
								, e);
					}
				}
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						/* LOG de niveau ERROR. */
						loggerError(
								fournirNomClasseConcrete()
								, METHODE_COMPTER_LIGNES
								, e);
					}
				}
			} // Fin du finally._____________________________
						
			return numeroLigneLue;
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de compterLignes(
	 // File pFile)._______________________________________________________
	
	
	
	/**
	 * method fournirFile(
	 * String pChemin
	 * , String pNomFichier
	 * , String pEncodage
	 * , String pExtension) :<br/>
	 * <ul>
	 * <li><b>Fabrique un fichier avec un nom composé automatiquement</b></li>
	 * <li>Insère automatiquement la date courante 
	 * dans le nom du fichier.</li>
	 * <li>Fabrique éventuellement l'arborescence pChemin 
	 * (".\\data\\temp\\rapports" par exemple).</li>
	 * <li>fabrique le nom du fichier sous la forme 
	 * [dateCourante_nom_encodage.extension] 
	 * comme "2016-02-25_14-27-07_RAPPORT_UTF8.txt" par exemple</li>
	 * <li>fabrique et retourne le fichier 
	 * (.\data2\temp\rapports\2016-02-25_14-27-07_RAPPORT_UTF8.txt 
	 * par exemple).</li>
	 * <li>crée un répertoire (ou toute l'arborescence) 
	 * pour le fichier si il n'existe pas.</li>
	 * <li>Prend automatiquement la date système.</li>
	 * <br/>
	 * Par exemple : <br/>
	 * <code>
	 * final String chemin1 = ".\\data2\\temp\\rapports";<br/>
	 * // Crée le fichier 
	 * .\data2\temp\rapports\2016-02-25_14-27-07_RAPPORT_UTF8.txt<br/>
	 * final File resultat = GestionnaireFichiers.fournirFile(
	 * chemin1,"RAPPORT", "UTF8", "txt");<br/>
	 * </code>
	 * <br/>
	 * - retourne null si pChemin est blank.<br/>
	 * - retourne null si pNomFichier est blank.<br/>
	 * - retourne null (et LOG ERROR) si il se produit une Exception 
	 * lors de la création du fichier.<br/>
	 * <br/>
	 *
	 *  @param pChemin : String : chemin (arborescence de répertoires) 
	 * pour le fichier.<br/>
	 *  @param pNomFichier : String : nom de base du fichier.<br/>
	 * @param pEncodage : String : encodage pour suffixer 
	 * le nom du fichier.<br/>
	 * @param pExtension : String : extension du fichier.<br/>
	 * 
	 * @return : File : Le File créé.<br/>
	 */
	public static File fournirFile(
			final String pChemin
				, final String pNomFichier
					, final String pEncodage
						, final String pExtension) {
		
		return fournirFile(pChemin, null, pNomFichier, pEncodage, pExtension);
		
	} // Fin de fournirFile(date courante).________________________________
	
	
	
	/**
	 * method fournirFile(
	 * String pChemin
	 * , Date pDate
	 * , String pNomFichier
	 * , String pEncodage
	 * , String pExtension) :<br/>
	 * <ul>
	 * <li><b>Crée sur disque et retourne le fichier 
	 * pChemin\\pDateFormatee_pNomFichier_pEncodage.pExtension</b> 
	 * comme .\data2\temp\rapports\1961-02-25_14-27-07_RAPPORT_UTF8.txt 
	 * par exemple.</li>
	 * <ul>
	 * <li>Fabrique éventuellement sur disque l'arborescence pChemin 
	 * si elle n'existe pas déjà 
	 * (".\\data\\temp\\rapports" par exemple).</li>
	 * <li>Prend automatiquement la date système si pDate est null.</li>
	 * <li>Formate la date avec DF_DATETIME_LEXICOGRAPHIQUE<br/> 
	 * (Format lexicographique des dates avec time 
	 * comme "2012-01-16_18-09-55" pour le
	 * 16 Janvier 2012 à 18 heures 9 minutes et 55 secondes. 
	 * Pattern = "yyyy-MM-dd_HH-mm-ss".</li>
	 * <li>Fabrique le nom du fichier sous la forme 
	 * [dateFormatee_nom_encodage.extension] 
	 * comme "1961-02-25_14-27-07_RAPPORT_UTF8.txt" par exemple.</li>
	 * <li>Fabrique sur disque et retourne le fichier 
	 * (.\data2\temp\rapports\1961-02-25_14-27-07_RAPPORT_UTF8.txt 
	 * par exemple).</li>
	 * </ul>
	 * <ul>
	 * Par exemple : <br/>
	 * <code>
	 * final String chemin1 = ".\\data2\\temp\\rapports";<br/>
	 * final Date date1 = GestionnaireDates.fournirDateAvecString(
	 * "25/02/1961-14:27:07.251", DF_DATETIMEMILLI_FRANCAISE);<br/>
	 * // Crée le fichier 
	 * .\data2\temp\rapports\1961-02-25_14-27-07_RAPPORT_UTF8.txt<br/>
	 * final File resultat = GestionnaireFichiers.fournirFile(
	 * chemin1, date1, "RAPPORT", "UTF8", "txt");<br/>
	 * </code>
	 * <br/>
	 * - retourne null si pChemin est blank.<br/>
	 * - retourne null si pNomFichier est blank.<br/>
	 * - retourne null (et LOG ERROR) si il se produit une Exception 
	 * lors de la création du fichier.<br/>
	 * <br/>
	 * 
	 * @param pChemin : String : chemin (arborescence de répertoires) 
	 * pour le fichier.<br/>
	 * @param pDate : Date : Date pour préfixer le nom du fichier. 
	 * La Date sera formattée sous la forme "yyyy-MM-dd_HH-mm-ss" 
	 * de DF_DATETIME_LEXICOGRAPHIQUE comme 2012-01-16_18-09-55 <br/>
	 * @param pNomFichier : String : nom de base du fichier.<br/>
	 * @param pEncodage : String : encodage pour suffixer 
	 * le nom du fichier.<br/>
	 * @param pExtension : String : extension du fichier.<br/>
	 * 
	 * @return : File : Le File créé.<br/>
	 */
	public static File fournirFile(
			final String pChemin
				, final Date pDate
					, final String pNomFichier
						, final String pEncodage
							, final String pExtension) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si pChemin est blank. */
			if (StringUtils.isBlank(pChemin)) {
				return null;
			}
			
			/* retourne null si pNomFichier est blank. */
			if (StringUtils.isBlank(pNomFichier)) {
				return null;
			}
						
			/* crée un répertoire pour le fichier si il n'existe pas. */
			creerArborescence(pChemin);
			
			/* crée le chemin complet du fichier en nommant le fichier. */
			final String cheminFichier 
				= pChemin 
				+ SEPARATEUR_FILE 
				+ fournirNomFichier(pDate, pNomFichier, pEncodage, pExtension);
			
			final File resultatFile = new File(cheminFichier);
			
			/* Création du fichier si il n'existe pas. */
			if (!resultatFile.exists()) {
				try {
					
					resultatFile.createNewFile();
					
				} catch (IOException ioe) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							fournirNomClasseConcrete()
								, METHODE_FOURNIRFILE
									, ioe);
					
					/* retourne null (et LOG ERROR) si il se produit 
					 * une Exception lors de la création du fichier. */
					return null;
					
				}
			}
			
			return resultatFile;
			
		} // Fin du bloc static synchronized.________________________
				
	} // Fin de fournirFile()._____________________________________________


	
	/**
	 * method fournirNomFichier(
	 * String pNom
	 * , String pEncodage
	 * , String pExtension) :<br/>
	 * <ul>
	 * <li><b>Fournit un nom pour un fichier 
	 * de la forme [dateCourante_nom_encodage.extension]</b>.</li>
	 * <li>Par exemple : <br/>
	 * <code>GestionnaireFichiers.fournirNomFichier(
	 * "RAPPORT", "UTF8", "txt");</code> 
	 * retourne "dateCourante_RAPPORT_UTF8.txt".</li>
	 * <li>La Date courante sera formattée sous la forme "yyyy-MM-dd_HH-mm-ss" 
	 * de DF_DATETIME_LEXICOGRAPHIQUE comme 2012-01-16_18-09-55 </li>
	 * </ul>
	 * - retourne null si pNom est blank.<br/>
	 * <br/>
	 *
	 * @param pNom : String : nom de base du fichier.<br/>
	 * @param pEncodage : String : encodage pour suffixer 
	 * le nom du fichier.<br/>
	 * @param pExtension : String : extension du fichier.<br/>
	 * 
	 * @return : String : Nom pour le fichier.<br/>
	 */
	public static String fournirNomFichier(
			final String pNom
					, final String pEncodage
						, final String pExtension) {
		
		return fournirNomFichier(null, pNom, pEncodage, pExtension);
		
	} // Fin de fournirNomFichier(
	 // String pNom
	 // , String pEncodage
	 // , String pExtension).______________________________________________
	
	
	
	/**
	 * method fournirNomFichier(
	 * Date pDate
	 * , String pNom
	 * , String pEncodage
	 * , String pExtension) :<br/>
	 * <ul>
	 * <li><b>Fournit un nom pour un fichier 
	 * de la forme [date_nom_encodage.extension]</b>.</li>
	 * <li>Par exemple : <br/>
	 * <code>final Date date1 = GestionnaireDates.fournirDateAvecString(
	 * "25/02/1961-14:27:07.251", DF_DATETIMEMILLI_FRANCAISE);</code> 
	 * instancie une date calée le 25/02/1961 à 14h27'07" 
	 * et 251 millisecondes.<br/>
	 * <code>GestionnaireFichiers.fournirNomFichier(
	 * date1, "RAPPORT", "UTF8", "txt");</code> 
	 * retourne "1961-02-25_14-27-07_RAPPORT_UTF8.txt".</li>
	 * </ul>
	 * - passe automatiquement la date à la date système si pDate == null.<br/>
	 * - retourne null si pNom est blank.<br/>
	 * <br/>
	 *
	 * @param pDate : Date : Date pour préfixer le chemin. 
	 * La Date sera formattée sous la forme "yyyy-MM-dd_HH-mm-ss" 
	 * de DF_DATETIME_LEXICOGRAPHIQUE comme 2012-01-16_18-09-55 <br/>
	 * @param pNom : String : nom de base du fichier.<br/>
	 * @param pEncodage : String : encodage pour suffixer 
	 * le nom du fichier.<br/>
	 * @param pExtension : String : extension du fichier.<br/>
	 * 
	 * @return : String : Nom pour le fichier.<br/>
	 */
	public static String fournirNomFichier(
			final Date pDate
				, final String pNom
					, final String pEncodage
						, final String pExtension) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			Date date = null;
			
			/* passe automatiquement la date 
			 * à la date système si pDate == null. */
			if (pDate == null) {
				date = new Date();
			}
			else {
				date = pDate;
			}
			
			/* retourne null si pNom est blank. */
			if (StringUtils.isBlank(pNom)) {
				return null;
			}
			
			/* Récupère la date  
			 * formattée sous la forme 2012-01-16_18-09-55. */
			final DateFormat dfDateTimeLexico 
				= new SimpleDateFormat(
						PATTERN_DF_DATETIME_LEXICOGRAPHIQUE, LOCALE_FR_FR);
			
			dfDateTimeLexico.setLenient(false);
			
			final String dateFormatteeString 
				= fournirDateFormattee(date, dfDateTimeLexico);
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append(dateFormatteeString);
			stb.append(UNDERSCORE);
			stb.append(pNom);
			
			if (!StringUtils.isBlank(pEncodage)) {
				stb.append(UNDERSCORE);
				stb.append(pEncodage);
			}
			
			if (!StringUtils.isBlank(pExtension)) {
				stb.append(POINT);
				stb.append(pExtension);
			}
			
			return stb.toString();
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de fournirNomFichier(...).____________________________________
	

	
	/**
	 * method fournirFichierAPartirDeFile(
	 * File pFile
	 * , String pSuffixe) :<br/>
	 * <ul>
	 * <li><b>Fournit un fichier (vide) dans le même répertoire 
	 * que pFile en le nommant pFile_suffixe.ext.</b></li>
	 * <ul>
	 * <li>Récupère le chemin de pFile avec 
	 * la méthode pFile.getParent().</li>
	 * <li>Récupère le nom simple de pFile 
	 * avec la méthode pFile.getName().</li>
	 * <li>Décompose le nom simple de pFile en nom de base et extension 
	 * avec la méthode StringUtils.split(nomSimple, '.').</li>
	 * <li>Récupère le séparateur de fichiers auprès de la plateforme 
	 * avec la méthode System.getProperty("file.separator").</li>
	 * <li>Construit le fichier suffixé dans 
	 * le même répertoire que pFile.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si pFile == null.<br/>
	 * - Retourne null si pSuffixe est blank.<br/>
	 * - Retourne null si le chemin de pFile == null.<br/>
	 * - Retourne null si le nom simple de pFile est blank.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier à partir duquel 
	 * on va créer un fichier suffixé 
	 * avec pSuffixe dans le même répertoire.<br/>
	 * @param pSuffixe : String : suffixe à ajouter.<br/>
	 * 
	 * @return : File : Fichier résultat.<br/>
	 */
	public static File fournirFichierAPartirDeFile(
			final File pFile
				, final String pSuffixe) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pFile == null. */
			if (pFile == null) {
				return null;
			}
			
			/* Retourne null si pSuffixe est blank. */
			if (StringUtils.isBlank(pSuffixe)) {
				return null;
			}
			
			/* Récupère le chemin de pFile 
			 * avec la méthode pFile.getParent(). */
			final String cheminFile = pFile.getParent();
			
			/* Retourne null si le chemin de pFile == null. */
			if (cheminFile == null) {
				return null;
			}
			
			/* Récupère le nom simple de pFile 
			 * avec la méthode pFile.getName(). */		
			final String nomSimpleFile = pFile.getName();
			
			/* Retourne null si le nom simple de pFile est blank. */
			if (StringUtils.isBlank(nomSimpleFile)) {
				return null;
			}
			
			/* Décompose le nom simple en nom de base et extension 
			 * avec la méthode StringUtils.split(nomSimple, '.'). */
			final String[] nomsTableau = StringUtils.split(nomSimpleFile, '.');
			
			if (nomsTableau == null) {
				return null;
			}
			
			if (nomsTableau.length == 0) {
				return null;
			}
			
			/* Récupère le séparateur de fichiers auprès de la plateforme 
			 * avec la méthode System.getProperty("file.separator"). */
			final String separateurFichiers 
				= System.getProperty(FILE_SEPARATOR);
			String nomBase = null;
			String extension = null;
			String nomSuffixe = null;
					
			if (nomsTableau.length == 1) {
				nomBase = nomsTableau[0];
				nomSuffixe = nomBase + '_' + pSuffixe;
			}
			else if (nomsTableau.length >= 2) {
				nomBase = nomsTableau[0];
				extension = nomsTableau[nomsTableau.length - 1];
				nomSuffixe = nomBase + '_' + pSuffixe + '.' + extension;
			}
			
			/* Construit le chemin du fichier suffixé. */
			final String cheminFichierResultat 
				= cheminFile + separateurFichiers + nomSuffixe;
			
			/* Construit le fichier suffixé 
			 * dans le même répertoire que pFile. */
			final File resultat = new File(cheminFichierResultat);
					
			return resultat;
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de fournirFichierAPartirDeFile(
	 // File pFile
	 // , String pSuffixe).________________________________________________
	

	
	/**
	 * method fournirFichierAPartirDeFile(
	 * File pFile
	 * , String pSuffixe
	 * , File pRepertoire) :<br/>
	 * <ul>
	 * <li><b>Fournit un fichier (vide) dans pRepertoire 
	 * en le nommant pFile_suffixe.ext</b>.</li>
	 * <ul>
	 * <li>Récupère le nom simple de pFile 
	 * avec la méthode pFile.getName().</li>
	 * <li>Décompose le nom simple de pFile en nom de base et extension 
	 * avec la méthode StringUtils.split(nomSimple, '.').</li>
	 * <li>Récupère le séparateur de fichiers auprès de la plateforme 
	 * avec la méthode System.getProperty("file.separator").</li>
	 * <li>Construit le fichier suffixé dans 
	 * pRepertoire.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si pFile == null.<br/>
	 * - Retourne null si pSuffixe est blank.<br/>
	 * - Retourne null si pRepertoire == null.<br/>
	 * - Retourne null si le nom simple de pFile est blank.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier à partir duquel 
	 * on va créer un fichier suffixé 
	 * avec pSuffixe dans pRepertoire.<br/>
	 * @param pSuffixe : String : suffixe à ajouter.<br/>
	 * @param pRepertoire : File : répertoire dans lequel 
	 * il faut créer le nouveau fichier suffixé.<br/>
	 * 
	 * @return : File : Fichier résultat.<br/>
	 */
	public static File fournirFichierAPartirDeFile(
			final File pFile
				, final String pSuffixe
					, final File pRepertoire) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pFile == null. */
			if (pFile == null) {
				return null;
			}
			
			/* Retourne null si pSuffixe est blank. */
			if (StringUtils.isBlank(pSuffixe)) {
				return null;
			}
			
			/* Retourne null si pRepertoire == null. */
			if (pRepertoire == null) {
				return null;
			}
					
			/* Récupère le nom simple de pFile 
			 * avec la méthode pFile.getName(). */		
			final String nomSimpleFile = pFile.getName();
			
			/* Retourne null si le nom simple de pFile est blank. */
			if (StringUtils.isBlank(nomSimpleFile)) {
				return null;
			}
			
			/* Décompose le nom simple en nom de base et extension 
			 * avec la méthode StringUtils.split(nomSimple, '.'). */
			final String[] nomsTableau = StringUtils.split(nomSimpleFile, '.');
			
			if (nomsTableau == null) {
				return null;
			}
			
			if (nomsTableau.length == 0) {
				return null;
			}
			
			/* Récupère le séparateur de fichiers auprès de la plateforme 
			 * avec la méthode System.getProperty("file.separator"). */
			final String separateurFichiers 
				= System.getProperty(FILE_SEPARATOR);
			String nomBase = null;
			String extension = null;
			String nomSuffixe = null;
					
			if (nomsTableau.length == 1) {
				nomBase = nomsTableau[0];
				nomSuffixe = nomBase + '_' + pSuffixe;
			}
			else if (nomsTableau.length >= 2) {
				nomBase = nomsTableau[0];
				extension = nomsTableau[nomsTableau.length - 1];
				nomSuffixe = nomBase + '_' + pSuffixe + '.' + extension;
			}
			
			/* Construit le chemin du fichier suffixé. */
			final String cheminFichierResultat 
				= pRepertoire.getAbsolutePath() 
				+ separateurFichiers 
				+ nomSuffixe;
			
			/* Construit le fichier suffixé dans pRepertoire. */
			final File resultat = new File(cheminFichierResultat);
					
			return resultat;
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de fournirFichierAPartirDeFile(
	 // File pFile
	 // , String pSuffixe
	 // , File pRepertoire)._______________________________________________
	

	
	/**
	 * method creerRepertoireSousPere(
	 * File pFile
	 * , String pNomRepertoire) :<br/>
	 * <ul>
	 * <li><b>Crée un répertoire nommé pNomRepertoire 
	 * sous le répertoire père de pFile</b>.</li>
	 * <ul>
	 * <li>Récupère le séparateur de fichiers auprès de la plateforme 
	 * avec la méthode System.getProperty("file.separator").</li>
	 * <li>Crée physiquement le répertoire pNomRepertoire 
	 * sur disque si il n'existe pas déjà.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si pFile == null.<br/>
	 * - Retourne null si pNomRepertoire est blank.<br/>
	 * - Retourne null si pFile n'a pas de répertoire père.<br/>
	 * <br/>
	 *
	 * @param pFile : File : Fichier dont on veut connaitre 
	 * le répertoire père afin d'y créer 
	 * le répertoire pNomRepertoire.<br/>
	 * @param pNomRepertoire : String : Nom du répertoire à créer.<br/>
	 * 
	 * @return : File : Répertoire nommé pNomRepertoire 
	 * sous le répertoire père de pFile.<br/>
	 */
	public static File creerRepertoireSousPere(
			final File pFile
				, final String pNomRepertoire) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pFile == null. */
			if (pFile == null) {
				return null;
			}
			
			/* Retourne null si pNomRepertoire est blank. */
			if (StringUtils.isBlank(pNomRepertoire)) {
				return null;
			}
			
			final File filePere = pFile.getParentFile();
			
			/* Retourne null si pFile n'a pas de répertoire père. */
			if (filePere == null) {
				return null;
			}
						
			/* Récupère le séparateur de fichiers auprès de la plateforme 
			 * avec la méthode System.getProperty("file.separator"). */
			final String separateurFichiers 
				= System.getProperty(FILE_SEPARATOR);
			
			final String cheminRepertoireACreer 
				= filePere.getAbsolutePath() 
				+ separateurFichiers 
				+ pNomRepertoire;
			
			final File repertoireACreer = new File(cheminRepertoireACreer);
			
			/* Crée physiquement le répertoire pNomRepertoire 
			 * sur disque si il n'existe pas déjà. */
			final Path repertoireACreerPath = repertoireACreer.toPath();
			
			try {
				Files.createDirectory(repertoireACreerPath);
			} catch (IOException e) {
				return repertoireACreer;
			}
			
			return repertoireACreer;
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de creerRepertoireSousPere(
	 // File pFile
	 // , String pNomRepertoire).__________________________________________
	

	
	/**
	 * method creerRepertoireSousGrandPere(
	 * File pFile
	 * , String pNomRepertoire) :<br/>
	 * <ul>
	 * <li><b>Crée un répertoire nommé pNomRepertoire 
	 * sous le répertoire grand-père de pFile</b>.</li>
	 * <ul>
	 * <li>Récupère le séparateur de fichiers auprès de la plateforme 
	 * avec la méthode System.getProperty("file.separator").</li>
	 * <li>Crée physiquement le répertoire pNomRepertoire 
	 * sur disque si il n'existe pas déjà.</li>
	 * </ul>
	 * </ul>
	 * - Retourne null si pFile == null.<br/>
	 * - Retourne null si pNomRepertoire est blank.<br/>
	 * - Retourne null si pFile n'a pas de répertoire père.<br/>
	 * - Retourne null si pFile n'a pas de répertoire grand-père.<br/>
	 * <br/>
	 *
	 * @param pFile : File : Fichier dont on veut connaitre 
	 * le répertoire grand-père afin d'y créer 
	 * le répertoire pNomRepertoire.<br/>
	 * @param pNomRepertoire : String : Nom du répertoire à créer.<br/>
	 * 
	 * @return : File : Répertoire nommé pNomRepertoire 
	 * sous le répertoire grand-père de pFile.<br/>
	 */
	public static File creerRepertoireSousGrandPere(
			final File pFile
				, final String pNomRepertoire) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pFile == null. */
			if (pFile == null) {
				return null;
			}
			
			/* Retourne null si pNomRepertoire est blank. */
			if (StringUtils.isBlank(pNomRepertoire)) {
				return null;
			}
			
			final File filePere = pFile.getParentFile();
			
			/* Retourne null si pFile n'a pas de répertoire père. */
			if (filePere == null) {
				return null;
			}
			
			final File fileGrandPere = filePere.getParentFile();
			
			/* Retourne null si pFile n'a pas de répertoire grand-père. */
			if (fileGrandPere == null) {
				return null;
			}
			
			/* Récupère le séparateur de fichiers auprès de la plateforme 
			 * avec la méthode System.getProperty("file.separator"). */
			final String separateurFichiers 
				= System.getProperty(FILE_SEPARATOR);
			
			final String cheminRepertoireACreer 
				= fileGrandPere.getAbsolutePath() 
				+ separateurFichiers 
				+ pNomRepertoire;
			
			final File repertoireACreer = new File(cheminRepertoireACreer);
			
			/* Crée physiquement le répertoire pNomRepertoire 
			 * sur disque si il n'existe pas déjà. */
			final Path repertoireACreerPath = repertoireACreer.toPath();
			
			try {
				Files.createDirectory(repertoireACreerPath);
			} catch (IOException e) {
				return repertoireACreer;
			}
			
			return repertoireACreer;
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de creerRepertoireSousGrandPere(
	 // File pFile
	 // , String pNomRepertoire).__________________________________________
	

	
	/**
	 * method fournirDateFormattee(
	 * Date pDate
	 * , DateFormat pDateFormat) :<br/>
	 * <ul>
	 * <li><b>Retourne une String représentant la java.util.Date pDate 
	 * au format pDateFormat</b>.</li>
	 * <li>Par exemple :<br/>
	 * Retourne la String "25/02/1961" 
	 * avec une Date au 25/02/1961 et un DateFormat 
	 * DF_DATE_FRANCAISE (
	 * new SimpleDateFormat("dd/MM/yyyy", LOCALE_FR_FR)).</li>
	 * </ul>
	 * - retourne null si pDate == null.<br/>
	 * - retourne null si pDateFormat == null.<br/>
	 * <br/>
	 *
	 * @param pDate : java.util.Date.<br/>
	 * @param pDateFormat : DateFormat.<br/>
	 * 
	 * @return : String : String pour affichage 
	 * formatté de pDate selon pDateFormat.<br/>
	 */
	private static String fournirDateFormattee(
			final Date pDate
				, final DateFormat pDateFormat) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si pDate == null. */
			if (pDate == null) {
				return null;
			}
			
			/* retourne null si pDateFormat == null. */
			if (pDateFormat == null) {
				return null;
			}
			
			pDateFormat.setLenient(false);
			
			return pDateFormat.format(pDate);
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de fournirDateFormattee(
	 // Date pDate
	 // DateFormat pDateFormat).___________________________________________
	

	
	/**
	 * method substituerSautLignePlateforme(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Substitue les sauts de ligne dans pString 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * par les sauts de ligne de la plate-forme
	 * sur laquelle le programme s'exécute.</li>
	 * </ul>
	 * - retourne null si pString est blank (null ou vide).<br/>
	 * <br/>
	 *
	 * @param pString : String : String à corriger.<br/>
	 * 
	 * @return : String : La String dans laquelle les sauts de ligne 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * ont été substitués par les sauts de ligne de la plate-forme.<br/>
	 */
	public static String substituerSautLignePlateforme(
			final String pString) {
		
		return substituerSautLigne(pString, NEWLINE);
		
	} // Fin de method substituerSautLignePlateforme(
	 // String pString).___________________________________________________
	

	
	/**
	 * method substituerSautLigne(
	 * String pString
	 * , String pSautLigne) :<br/>
	 * <ul>
	 * <li>Substitue les sauts de ligne dans pString 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * par les sauts de ligne pSautLigne.</li>
	 * </ul>
	 * - retourne null si pString est blank (null ou vide).<br/>
	 * - retourne null si pSautLigne est blank (null ou vide).<br/>
	 * <br/>
	 *
	 * @param pString : String : String à corriger.<br/>
	 * @param pSautLigne : String : saut de ligne à substituer.<br/>
	 * 
	 * @return : String : La String dans laquelle les sauts de ligne 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * ont été substitués par les sauts de ligne pSautLigne.<br/>
	 */
	public static String substituerSautLigne(
			final String pString
				, final String pSautLigne) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si pString est blank (null ou vide). */
			if (StringUtils.isBlank(pString)) {
				return null;
			}
			
			/* retourne null si pSautLigne est blank (null ou vide). */
			
			/* Recherche des sauts de ligne DOS/Windows. */
			if (StringUtils.contains(pString, SAUTDELIGNE_DOS_WINDOWS)) {
				
				final String resultat 
					= StringUtils.replace(
							pString, SAUTDELIGNE_DOS_WINDOWS, pSautLigne);
				
				return resultat;
			}
			
			/* Recherche des sauts de ligne Mac. */
			if (StringUtils.contains(pString, SAUTDELIGNE_MAC)) {
				
				final String resultat 
					= StringUtils.replace(
							pString, SAUTDELIGNE_MAC, pSautLigne);
				
				return resultat;
			}
			
			/* Recherche des sauts de ligne Unix. */
			if (StringUtils.contains(pString, SAUTDELIGNE_UNIX)) {
				
				final String resultat 
					= StringUtils.replace(
							pString, SAUTDELIGNE_UNIX, pSautLigne);
				
				return resultat;
			}
			
			/* Retourne la chaîne inchangée 
			 * si il n'y avait pas de saut de ligne. */
			return pString;
			
		} // Fin du bloc static synchronized.________________________
				
	} // Fin de substituerSautLigne(
	 // String pString
	 // , String pSautLigne).______________________________________________
	
	
	
	/**
	 * method afficherSautLigne(
	 * String pSautLigne) :<br/>
	 * <ul>
	 * <li>Affiche les caractères non imprimables 
	 * saut de ligne (\n ou \r ou \r\n).</li>
	 * </ul>
	 * - retourne null si pSautLigne est null.<br/>
	 * - retourne null si pSautLigne n'est pas un saut de ligne 
	 * (\n ou \r ou \r\n).<br/>
	 * <br/>
	 *
	 * @param pSautLigne : String : \n ou \r ou \r\n.<br/>
	 * 
	 * @return : String : Affichage des caractères non imprimables 
	 * saut de ligne (\n ou \r ou \r\n).<br/>
	 */
	public static String afficherSautLigne(
			final String pSautLigne) {
		
		/* block static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si pSautLigne est null. */
			if (pSautLigne == null) {
				return null;
			}
			
			final StringBuilder stb = new StringBuilder();
			
			final char[] newLineChars = pSautLigne.toCharArray();
			
			for (final char caractere : newLineChars) {
				
				if (caractere == '\n') {
					stb.append("\\n");
				}
				else if (caractere == '\r') {
					stb.append("\\r");
				}
				/* retourne null si pSautLigne n'est pas un saut de ligne. */
				else {
					return null;
				}
				
			}
			
			return stb.toString();
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de afficherSautLigne(
	 // String pSautLigne).________________________________________________
	


	/**
	 * method affichierTableauFiles(
	 * File[] pFiles) :<br/>
	 * <ul>
	 * <li>Retourne une String pour l'affichage d'un tableau de Files.</li>
	 * <li>Liste les fichiers contenus dans le tableau sous la forme 
	 * [.\rep_0\file_1_3.txt;.\rep_0\rep_1_1;.\rep_0\rep_1_2].</li>
	 * </ul>
	 * - retourne null si pFile == null.<br/>
	 * - retourne [] si pFile est vide.<br/>
	 * <br/>
	 * 
	 *
	 * @param pFiles : File[] : tableau de File à afficher.<br/>
	 * @return : String : String pour affichage.<br/>
	 */
	public static String affichierTableauFiles(
			final File... pFiles) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si pFile == null. */
			if (pFiles == null) {
				return null;
			}
			
			final StringBuilder stb = new StringBuilder();
			
			stb.append('[');
			
			for (int i = 0; i < pFiles.length; i++) {
				stb.append(pFiles[i].getPath());
				
				if (i < pFiles.length - 1) {
					stb.append(';');
				}				
			}
			
			stb.append(']');
			
			return stb.toString();
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de affichierTableauFiles(
	 // File[] pFiles).____________________________________________________
	

	
	/**
	 * method afficherListeFichiers(
	 * List&lt;File&gt; pListFile) :<br/>
	 * <ul>
	 * <li>Affiche à la console les noms simples 
	 * d'une liste de fichiers.</li>
	 * </ul>
	 * - retourne null si pListFile == null.<br/>
	 * <br/>
	 *
	 * @param pListFile : List&lt;File&gt; : liste de fichiers 
	 * dont on veut connaitre les noms simples.<br/>
	 * 
	 * @return : String : Pour affichage.<br/>
	 */
	public static String afficherListeFichiers(
			final List<File> pListFile) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* retourne null si pListFile == null. */
			if (pListFile == null) {
				return null;
			}
			
			final StringBuilder stb = new StringBuilder();
			
			for (final File fichier : pListFile) {
				
				if (fichier != null) {
					
					final String nomSimpleFichier = fichier.getName();
					stb.append(nomSimpleFichier);
					stb.append(NEWLINE);
					
				}
			}
			
			return stb.toString();
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de afficherListeFichiers(
	 // List<File> pListFile)._____________________________________________


	
	/**
	 * method afficherListeString(
	 * List&lt;String&gt; pListe) :<br/>
	 * <ul>
	 * <li>Fabrique une String à partir d'une List&lt;String&gt;.</li>
	 * </ul>
	 * - Retourne null si pListe est null.<br/>
	 * <br/>
	 *
	 * @param pListe : List&lt;String&gt; : liste de lignes.
	 * 
	 * @return : String : Pour affichage à la console.<br/>
	 */
	public static String afficherListeString(
			final List<String> pListe) {
		
		/* bloc static synchronized. */
		synchronized (GestionnaireFichiers.class) {
			
			/* Retourne null si pListe est null. */
			if (pListe == null) {
				return null;
			}
			
			final StringBuilder stb = new StringBuilder();
			
			for (final String ligne : pListe) {
				
				stb.append(ligne);
				stb.append(NEWLINE);
				
			}
			
			return stb.toString();
			
		} // Fin du bloc static synchronized.________________________
		
	} // Fin de afficherListeString(
	 // List<String> pListe).______________________________________________
	
	
	
	/**
	 * method fournirNomClasseConcrete() :<br/>
	 * Retourne le nom de la présente classe.<br/>
	 * "Classe GestionnaireFichiers".<br/>
	 * <br/>
	 *
	 * @return : String : "Classe GestionnaireFichiers".<br/>
	 */
	private static String fournirNomClasseConcrete() {
		return CLASSE_GESTIONNAIRE_FICHIERS;
	} // Fin de fournirNomClasseConcrete().________________________________
	

	
	/**
	 * method loggerInfo(
	 * String pClasse
	 * , String pMethode
	 * , String pMessage) :<br/>
	 * <ul>
	 * <li>Créée un message de niveau INFO dans le LOG.</li>
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
			+ SEP_MOINS
			+ pMethode
			+ SEP_MOINS
			+ pMessage;
			
			LOG.info(message);
		}
		
	} // Fin de la classe loggerInfo(
	 // String pClasse
	 // , String pMethode
	 // , String pMessage).________________________________________________
	

	
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
			+ SEP_MOINS
			+ pMethode
			+ SEP_MOINS
			+ pMessage
			+ pComplement;
			
			LOG.info(message);
		}
		
	} // Fin de loggerInfo(
	 // String pClasse
	 // , String pMethode
	 // , String pMessage
	 // , String pComplement)._____________________________________________
	
	
	
	/**
	 * method loggerError(
	 * String pClasse
	 * , String pMethode
	 * , Exception pException) :<br/>
	 * <ul>
	 * <li>Créée un message de niveau ERROR dans le LOG.</li>
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
			+ SEP_MOINS
			+ pMethode
			+ SEP_MOINS 
			+ pException.getMessage();
			
			LOG.error(message, pException);
			
		}
		
	} // Fin de loggerError(
	 // String pClasse
	 // , String pMethode
	 // , Exception pException).___________________________________________
	
	

	
} // FIN DE LA CLASSE GestionnaireFichiers.----------------------------------
