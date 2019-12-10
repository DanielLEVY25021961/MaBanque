package levy.daniel.application.model.services.traiteursfichiers;

import java.io.File;
import java.nio.charset.Charset;
import java.util.SortedMap;

import levy.daniel.application.IConstantesCharsets;


/**
 * INTERFACE ILecteurDecodeurFile :<br/>
 * <p>Interface centralisant toutes les constantes et les méthodes des objets 
 * capables de lire un fichier selon un certain encodage (Charset).</p>
 * <ul>
 * comporte essentiellement : <br/>
 * <li>une méthode <code>lireFichier(File pFile, Charset pCharset)</code> 
 * qui fournit 
 * le contenu de pFile lu caractère par caractère 
 * décodé avec pCharset sous forme de String. 
 * Cette méthode ne modifie pas les éventuels sauts de ligne.</li>
 * <li>une méthode <code>lireFichierLigneParLigne(
 * File pFile, Charset pCharset)</code>  
 * qui fournit le contenu de pFile lu ligne par ligne 
 * décodé avec pCharset sous forme de String. 
 * Cette méthode substitue aux sauts de ligne rencontrés 
 * les sauts de ligne de la plateforme.</li>
 * <li>une méthode <code>lireLigneFichier(
 * int pNumeroLigne, File pFile, Charset pCharset)</code>  
 * qui fournit la ligne pNumeroLigne de pFile
 * décodé avec pCharset sous forme de String.</li>
 * <li>une méthode <code>determinerSiEncodagePossible(
 * String pString, Charset pCharset, int pNumeroLigne)</code>  
 * qui retourne true si la ligne pString décodée 
 * avec pCharset ne contient pas de caractères indésirables 
 * (impossibles à écrire au clavier).</li>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * lire un fchier, lire un fichier ligne par ligne,<br/>
 * lire une ligne caractère par caractère, <br/>
 * InputStreamReader, BufferedReader, Charset,<br/>
 * read(), readLine(),<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 2 mars 2016
 *
 */
public interface ILecteurDecodeurFile extends IConstantesCharsets {
		
	/**
	 * "Méthode lireFichier(File pFile, Charset pCharset)".
	 */
	String METHODE_LIREFICHIER 
		= "Méthode lireFichier(File pFile, Charset pCharset)";
	
	/**
	 * "Méthode lireFichierLigneParLigne(File pFile, Charset pCharset".
	 */
	String METHODE_LIREFICHIERLIGNE_PAR_LIGNE 
		= "Méthode lireFichierLigneParLigne(File pFile, Charset pCharset";
		
	/**
	 * "Méthode lireLigneFichier(int pNumeroLigne, File pFile, Charset pCharset)".
	 */
	String METHODE_LIRELIGNEFICHIER 
	= "Méthode lireLigneFichier(int pNumeroLigne, File pFile, Charset pCharset)";
		
	/**
	 * Message retourné par la méthode lireFichier(File pFile) 
	 * si le fichier est null.<br/>
	 * "Le fichier passé en paramètre est null".<br/>
	 */
	String MESSAGE_FICHIER_NULL 
		= "Le fichier passé en paramètre est null";
	
	/**
	 * Message retourné par la méthode lireFichier(File pFile) 
	 * si le fichier est inexistant.<br/>
	 * "Le fichier passé en paramètre est inexistant : "
	 */
	String MESSAGE_FICHIER_INEXISTANT 
		= "Le fichier passé en paramètre est inexistant : ";
	
	/**
	 * Message retourné par la méthode lireFichier(File pFile) 
	 * si le fichier est un répertoire.<br/>
	 * "Le fichier passé en paramètre est un répertoire : ".<br/>
	 */
	String MESSAGE_FICHIER_REPERTOIRE 
		= "Le fichier passé en paramètre est un répertoire : ";
	
	/**
	 * Message retourné par la méthode lireFichier(File pFile) 
	 * si le fichier est vide.<br/>
	 * "Le fichier passé en paramètre est vide : "
	 */
	String MESSAGE_FICHIER_VIDE 
		= "Le fichier passé en paramètre est vide : ";
		
	/**
	 * "LIGNE A TRANSCODER".<br/>
	 */
	String ACTION_LIGNE_A_TRANSCODER 
		= "LIGNE A TRANSCODER";
	

	
	/**
	 * Lit un fichier pFile en UTF-8 et 
	 * retourne son contenu sous forme de chaîne de caractères.<br/>
	 * Lit le fichier en utilisant la méthode read() 
	 * de BufferedReader appliqué à un InputStreamReader 
	 * avec le Charset de décodage UTF-8.<br/>
	 * Lit chaque caractère quoi qu'il arrive 
	 * (même si le fichier n'est pas un fichier texte).<br/>
	 * Ne modifie pas les sauts de ligne.<br/>
	 * <br/>
	 * - passe pFile à <code>this.fichier</code> et 
	 * rafraîchit automatiquement <code>this.nomFichier</code>.<br/>
	 * <br/>
	 * - retourne MESSAGE_FICHIER_NULL si le pFile est null.<br/>
	 * - retourne MESSAGE_FICHIER_INEXISTANT si le pFile est inexistant.<br/>
	 * - retourne MESSAGE_FICHIER_REPERTOIRE si le pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier à lire.<br/>
	 * 
	 * @return : String : Chaine de caractères avec le contenu du fichier.<br/>
	 */
	String lireFichierEnUTF8(File pFile);


	
	/**
	 * Lit un fichier pFile en US-ASCII et 
	 * retourne son contenu sous forme de chaîne de caractères.<br/>
	 * Lit le fichier en utilisant la méthode read() 
	 * de BufferedReader appliqué à un InputStreamReader 
	 * avec le Charset de décodage US-ASCII.<br/>
	 * Lit chaque caractère quoi qu'il arrive 
	 * (même si le fichier n'est pas un fichier texte).<br/>
	 * Ne modifie pas les sauts de ligne.<br/>
	 * <br/>
	 * - passe pFile à <code>this.fichier</code> et 
	 * rafraîchit automatiquement <code>this.nomFichier</code>.<br/>
	 * <br/>
	 * - retourne MESSAGE_FICHIER_NULL si le pFile est null.<br/>
	 * - retourne MESSAGE_FICHIER_INEXISTANT si le pFile est inexistant.<br/>
	 * - retourne MESSAGE_FICHIER_REPERTOIRE si le pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier à lire.<br/>
	 * 
	 * @return : String : Chaine de caractères avec le contenu du fichier.<br/>
	 */
	String lireFichierEnAscii(File pFile);
	

	
	/**
	 * Lit un fichier pFile en ISO-8859-1 (LATIN1) et 
	 * retourne son contenu sous forme de chaîne de caractères.<br/>
	 * Lit le fichier en utilisant la méthode read() 
	 * de BufferedReader appliqué à un InputStreamReader 
	 * avec le Charset de décodage  ISO-8859-1.<br/>
	 * Lit chaque caractère quoi qu'il arrive 
	 * (même si le fichier n'est pas un fichier texte).<br/>
	 * Ne modifie pas les sauts de ligne.<br/>
	 * <br/>
	 * - passe pFile à <code>this.fichier</code> et 
	 * rafraîchit automatiquement <code>this.nomFichier</code>.<br/>
	 * <br/>
	 * - retourne MESSAGE_FICHIER_NULL si le pFile est null.<br/>
	 * - retourne MESSAGE_FICHIER_INEXISTANT si le pFile est inexistant.<br/>
	 * - retourne MESSAGE_FICHIER_REPERTOIRE si le pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier à lire.<br/>
	 * 
	 * @return : String : Chaine de caractères avec le contenu du fichier.<br/>
	 */
	String lireFichierEnLatin1(File pFile);
	

	
	/**
	 * Lit un fichier pFile en ISO-8859-2 (LATIN2) et 
	 * retourne son contenu sous forme de chaîne de caractères.<br/>
	 * Lit le fichier en utilisant la méthode read() 
	 * de BufferedReader appliqué à un InputStreamReader 
	 * avec le Charset de décodage  ISO-8859-2.<br/>
	 * Lit chaque caractère quoi qu'il arrive 
	 * (même si le fichier n'est pas un fichier texte).<br/>
	 * Ne modifie pas les sauts de ligne.<br/>
	 * <br/>
	 * - passe pFile à <code>this.fichier</code> et 
	 * rafraîchit automatiquement <code>this.nomFichier</code>.<br/>
	 * <br/>
	 * - retourne MESSAGE_FICHIER_NULL si le pFile est null.<br/>
	 * - retourne MESSAGE_FICHIER_INEXISTANT si le pFile est inexistant.<br/>
	 * - retourne MESSAGE_FICHIER_REPERTOIRE si le pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier à lire.<br/>
	 * 
	 * @return : String : Chaine de caractères avec le contenu du fichier.<br/>
	 */
	String lireFichierEnLatin2(File pFile);
	
	
	
	/**
	 * Lit un fichier pFile en ISO-8859-15 (LATIN9) et 
	 * retourne son contenu sous forme de chaîne de caractères.<br/>
	 * Lit le fichier en utilisant la méthode read() 
	 * de BufferedReader appliqué à un InputStreamReader 
	 * avec le Charset de décodage  ISO-8859-15.<br/>
	 * Lit chaque caractère quoi qu'il arrive 
	 * (même si le fichier n'est pas un fichier texte).<br/>
	 * Ne modifie pas les sauts de ligne.<br/>
	 * <br/>
	 * - passe pFile à <code>this.fichier</code> et 
	 * rafraîchit automatiquement <code>this.nomFichier</code>.<br/>
	 * <br/>
	 * - retourne MESSAGE_FICHIER_NULL si le pFile est null.<br/>
	 * - retourne MESSAGE_FICHIER_INEXISTANT si le pFile est inexistant.<br/>
	 * - retourne MESSAGE_FICHIER_REPERTOIRE si le pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier à lire.<br/>
	 * 
	 * @return : String : Chaine de caractères avec le contenu du fichier.<br/>
	 */
	String lireFichierEnLatin9(File pFile);


	
	/**
	 * Lit un fichier pFile en Windows-1252 (ANSI) et 
	 * retourne son contenu sous forme de chaîne de caractères.<br/>
	 * Lit le fichier en utilisant la méthode read() 
	 * de BufferedReader appliqué à un InputStreamReader 
	 * avec le Charset de décodage  Windows-1252.<br/>
	 * Lit chaque caractère quoi qu'il arrive 
	 * (même si le fichier n'est pas un fichier texte).<br/>
	 * Ne modifie pas les sauts de ligne.<br/>
	 * <br/>
	 * - passe pFile à <code>this.fichier</code> et 
	 * rafraîchit automatiquement <code>this.nomFichier</code>.<br/>
	 * <br/>
	 * - retourne MESSAGE_FICHIER_NULL si le pFile est null.<br/>
	 * - retourne MESSAGE_FICHIER_INEXISTANT si le pFile est inexistant.<br/>
	 * - retourne MESSAGE_FICHIER_REPERTOIRE si le pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier à lire.<br/>
	 * 
	 * @return : String : Chaine de caractères avec le contenu du fichier.<br/>
	 */
	String lireFichierEnAnsi(File pFile);

	
	
	/**
	 * Lit un fichier pFile en IBM-850 (OEM) et 
	 * retourne son contenu sous forme de chaîne de caractères.<br/>
	 * Lit le fichier en utilisant la méthode read() 
	 * de BufferedReader appliqué à un InputStreamReader 
	 * avec le Charset de décodage  IBM-850.<br/>
	 * Lit chaque caractère quoi qu'il arrive 
	 * (même si le fichier n'est pas un fichier texte).<br/>
	 * Ne modifie pas les sauts de ligne.<br/>
	 * <br/>
	 * - passe pFile à <code>this.fichier</code> et 
	 * rafraîchit automatiquement <code>this.nomFichier</code>.<br/>
	 * <br/>
	 * - retourne MESSAGE_FICHIER_NULL si le pFile est null.<br/>
	 * - retourne MESSAGE_FICHIER_INEXISTANT si le pFile est inexistant.<br/>
	 * - retourne MESSAGE_FICHIER_REPERTOIRE si le pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier à lire.<br/>
	 * 
	 * @return : String : Chaine de caractères avec le contenu du fichier.<br/>
	 */
	String lireFichierEnIbm850(File pFile);
	
	
	
	/**
	 * Lit un fichier pFile caractère par caractère et 
	 * retourne son contenu sous forme de chaîne de caractères.<br/>
	 * Lit le fichier en utilisant la méthode read() 
	 * de BufferedReader appliqué à un InputStreamReader 
	 * avec le Charset de décodage pCharset.<br/>
	 * Lit chaque caractère quoi qu'il arrive 
	 * (même si le fichier n'est pas un fichier texte).<br/>
	 * Ne modifie pas les sauts de ligne.<br/>
	 * <br/>
	 * - Choisit automatiquement le CHARSET_UTF8 si pCharset == null.<br/>
	 * - passe pFile à <code>this.fichier</code> et 
	 * rafraîchit automatiquement <code>this.nomFichier</code>.<br/>
	 * <br/>
	 * - retourne MESSAGE_FICHIER_NULL, LOG de niveau INFO et rapport 
	 * si pFile est null.<br/>
	 * - retourne MESSAGE_FICHIER_INEXISTANT, LOG de niveau INFO et rapport 
	 * si pFile est inexistant.<br/>
	 * - retourne MESSAGE_FICHIER_REPERTOIRE, LOG de niveau INFO et rapport 
	 * si pFile est un répertoire.<br/>
	 * - retourne MESSAGE_FICHIER_VIDE, LOG de niveau INFO et rapport 
	 * si pFile est vide.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier à lire.<br/>
	 * @param pCharset : Charset : Charset utilisé par l'InputStreamReader 
	 * pour lire dans le fichier.<br/>
	 * 
	 * @return : String : Chaine de caractères avec le contenu du fichier.<br/>
	 */
	String lireFichier(
			File pFile
				, Charset pCharset);
	

	/**
	 * <ul>
	 * <li>Lit ligne par ligne un fichier pFile en UTF-8 et 
	 * retourne son contenu sous forme de chaîne de caractères.</li>
	 * <li>Lit le fichier en utilisant la méthode readLine() 
	 * de BufferedReader appliqué à un InputStreamReader 
	 * avec le Charset de décodage UTF-8.</li>
	 * <li>remplit <code>this.fichierEnMap</code>.</li>
	 * <li>Lit chaque caractère quoi qu'il arrive 
	 * (même si le fichier n'est pas un fichier texte).</li>
	 * <li>Ajoute le numéro de ligne devant chaque ligne dans 
	 * la String resultat.</li>
	 * <li>Remplace les sauts de ligne par le saut de ligne 
	 * de la plateforme (NEWLINE).</li>
	 * </ul>
	 * <br/>
	 * - rafraîchit <code>this.fichierEnMap</code>.<br/>
	 * - passe pFile à <code>this.fichier</code> et 
	 * rafraîchit automatiquement <code>this.nomFichier</code>.<br/>
	 * <br/>
	 * - retourne MESSAGE_FICHIER_NULL, LOG de niveau INFO et rapport 
	 * si pFile est null.<br/>
	 * - retourne MESSAGE_FICHIER_INEXISTANT, LOG de niveau INFO et rapport 
	 * si pFile est inexistant.<br/>
	 * - retourne MESSAGE_FICHIER_REPERTOIRE, LOG de niveau INFO et rapport 
	 * si pFile est un répertoire.<br/>
	 * - retourne MESSAGE_FICHIER_VIDE, LOG de niveau INFO et rapport 
	 * si pFile est vide.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier à lire.<br/>
	 * 
	 * @return : String : Chaine de caractères avec le contenu du fichier.<br/>
	 */
	String lireFichierLigneParLigne(File pFile);
	
	
	
	/**
	 * <ul>
	 * <li>Lit ligne par ligne un fichier pFile et 
	 * retourne son contenu sous forme de chaîne de caractères.</li>
	 * <li>Lit le fichier en utilisant la méthode readLine() 
	 * de BufferedReader appliqué à un InputStreamReader 
	 * avec le Charset de décodage pCharset.</li>
	 * <li>remplit <code>this.fichierEnMap</code>.</li>
	 * <li>Lit chaque caractère quoi qu'il arrive 
	 * (même si le fichier n'est pas un fichier texte).</li>
	 * <li>Ajoute le numéro de ligne devant chaque ligne
	 * dans la String résultat.</li>
	 * <li>Remplace les sauts de ligne par le saut de ligne 
	 * de la plateforme (NEWLINE).</li>
	 * </ul>
	 * - Choisit automatiquement le CHARSET_UTF8 si pCharset == null.<br/>
	 * - rafraîchit <code>this.fichierEnMap</code>.<br/>
	 * - passe pFile à <code>this.fichier</code> et 
	 * rafraîchit automatiquement <code>this.nomFichier</code>.<br/>
	 * <br/>
	 * - retourne MESSAGE_FICHIER_NULL, LOG de niveau INFO et rapport 
	 * si pFile est null.<br/>
	 * - retourne MESSAGE_FICHIER_INEXISTANT, LOG de niveau INFO et rapport 
	 * si pFile est inexistant.<br/>
	 * - retourne MESSAGE_FICHIER_REPERTOIRE, LOG de niveau INFO et rapport 
	 * si pFile est un répertoire.<br/>
	 * - retourne MESSAGE_FICHIER_VIDE, LOG de niveau INFO et rapport 
	 * si pFile est vide.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier à lire.<br/>
	 * @param pCharset : Charset : Charset utilisé par l'InputStreamReader 
	 * pour lire dans le fichier.<br/>
	 * 
	 * @return : String : Chaine de caractères avec le contenu du fichier.<br/>
	 */
	String lireFichierLigneParLigne(
			File pFile
				, Charset pCharset);
	
	
	
	/**
	 * Lit avec l'encodage pCharset la pNumeroLigne-ème (1-based)
	 * ligne d'un fichier pFile.<br/>
	 * - Retourne la ligne lue ou null si la ligne n'existe pas.<br/>
	 * <ul>
	 * <li>retourne MESSAGE_FICHIER_NULL, LOG de niveau INFO et rapport 
	 * si pFile == null.</li>
	 * <li>retourne MESSAGE_FICHIER_INEXISTANT, LOG de niveau INFO et rapport 
	 * si pFile est inexistant.</li>
	 * <li>retourne MESSAGE_FICHIER_REPERTOIRE, LOG de niveau INFO et rapport 
	 * si pFile est un répertoire.</li>
	 * <li>retourne MESSAGE_FICHIER_VIDE, LOG de niveau INFO et rapport 
	 * si pFile est vide.</li>
	 * </ul>
	 * - Choisit automatiquement le CHARSET_UTF8 si pCharset == null.<br/>
	 * - passe pFile à <code>this.fichier</code> et 
	 * rafraîchit automatiquement <code>this.nomFichier</code>.<br/>
	 * <br/>
	 * 
	 *
	 * @param pNumeroLigne : int : numéro (1-based) 
	 * de la ligne que l'on veut lire.<br/>
	 * @param pFile : File : le fichier dans lequel on veut lire la ligne.<br/>
	 * @param pCharset : Charset : Charset avec lequel on lit la ligne.<br/>
	 * 
	 * @return : String : la pNumeroLigne-ème (1-based) ligne.<br/>
	 */
	String lireLigneFichier(
			int pNumeroLigne
				, File pFile
					, Charset pCharset);
	

	
	/**
	 * fournit une String pour Afficher <code>this.fichierEnMap</code>.<br/>
	 * - Rajoute les numéros de ligne dans l'affichage.<br/>
	 * <br/>
	 *
	 * @return : String.<br/>
	 */
	String afficherFichierEnMap();

	
	
	/**
	 * fournit une String pour afficher la pNumeroLigne-ième (1-based) ligne de 
	 * <code>this.fichierEnMap</code>.<br/>
	 * - Rajoute les numéros de ligne à l'affichage.<br/>
	 * <br/>
	 * - retourne null si la ligne n'existe pas.<br/>
	 *
	 * @param pNumeroLigne : int : 
	 * numéro (1-based) de la ligne du fichier à afficher.
	 * 
	 * @return : String : la pNumeroLigne-ième ligne (1-based) 
	 * de <code>this.fichierEnMap</code>.<br/>
	 */
	String afficherLigneDeFichierEnMap(int pNumeroLigne);

	
	
	/**
	 * fournit une String pour Afficher l'ensemble des lignes 
	 * d'une SortedMap&lt;Integer, String&gt;.<br/>
	 * - Rajoute les numéros de ligne dans l'affichage.<br/>
	 * <br/>
	 * - retourne null si pMap == null.<br/>
	 * <br/>
	 *
	 * @param pMap : SortedMap&lt;Integer, String&gt;.<br/>
	 * 
	 * @return : String : Affichage de la Map.<br/>
	 */
	String afficherMapIntegerString(SortedMap<Integer, String> pMap);
	

	
	/**
	 * fournit une String pour Afficher la pNumeroLigne-ième ligne (1-based)
	 * dans une SortedMap&lt;Integer, String&gt;.<br/>
	 * - Rajoute les numéros de ligne dans l'affichage.<br/>
	 * <br/>
	 * - retourne null si pMap == null.<br/>
	 * - retourne null si la ligne n'existe pas.<br/>
	 *
	 * @param pMap : SortedMap&lt;Integer, String&gt;.<br/>
	 * @param pNumeroLigne : int : numéro (1-based) de la ligne à lire.<br/>
	 * 
	 * @return : String : la pNumeroLigne-ième ligne (1-based).<br/>
	 */
	String afficherLignedeMapIntegerString(
			SortedMap<Integer, String> pMap
				, int pNumeroLigne);
	

	
	/**
	 * fournit une String pour Afficher <code>this.fichierEnMap</code>.<br/>
	 * - Ne Rajoute PAS les numéros de ligne.<br/>
	 *
	 * @return : String.<br/>
	 */
	String listerFichierEnMap();

	
	
	/**
	 * fournit une String pour afficher la pNumeroLigne-ième ligne (1-based) 
	 * de <code>this.fichierEnMap</code>.<br/>
	 * - Ne Rajoute PAS les numéros de ligne.<br/>
	 * <br/>
	 * - retourne null si la ligne n'existe pas.<br/>
	 *
	 * @param pNumeroLigne
	 * 
	 * @return : String : la pNumeroLigne-ième ligne de this.fichierEnMap.<br/>
	 */
	String listerLigneDeFichierEnMap(int pNumeroLigne);

	
	
	/**
	 * fournit une String pour Affichre l'ensemble des lignes 
	 * d'une SortedMap&lt;Integer, String&gt;.<br/>
	 * - Ne Rajoute PAS les numéros de ligne.<br/>
	 * <br/>
	 * - retourne null si pMap == null.<br/>
	 *
	 * @param pMap : SortedMap&lt;Integer, String&gt;.<br/>
	 * 
	 * @return : String : Affichage de la Map.<br/>
	 */
	String listerMapIntegerString(SortedMap<Integer, String> pMap);
	

	
	/**
	 * fournit une String pour Afficher la pNumeroLigne-ième ligne (1-based)
	 * dans une SortedMap&lt;Integer, String&gt;.<br/>
	 * - Ne Rajoute PAS les numéros de ligne.<br/>
	 * <br/>
	 * - retourne null si pMap == null.<br/>
	 * - retourne null si la ligne n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pMap : SortedMap&lt;Integer, String&gt;.<br/>
	 * @param pNumeroLigne : int : numéro (1-based) de la ligne à lire.<br/>
	 * 
	 * @return : String : la pNumeroLigne-ième ligne (1-based).<br/>
	 */
	String listerLignedeMapIntegerString(
			SortedMap<Integer, String> pMap
				, int pNumeroLigne);
	

	/**
	 * <ul>
	 * <li>Transcode la ligne pString initialement encodée 
	 * en pCharsetEncodage en pCharsetDecodage.</li>
	 * <li>Retire un éventuel BOM_UTF-8 en début de ligne 
	 * si l'encodage cible n'est pas CHARSET_UTF8.</li>
	 * <li>Crée un rapport de transcodage si pRapporte vaut true.</li>
	 * </ul>
	 * - retourne null si pString == null.<br/>
	 * - passe automatiquement le charsetEncodage à CHARSET_ANSI 
	 * si pCharsetEncodage == null. pCharsetEncodage sinon.<br/>
	 * - passe automatiquement le charsetDecodage à CHARSET_IBM850 
	 * si pCharsetDecodage == null. pCharsetDecodage sinon.<br/>
	 * <br/>
	 *
	 * @param pString : String : ligne à encoder en pCharsetDecodage
	 * @param pCharsetEncodage : Charset : charset ayant servi 
	 * initialement à écrire pString.<br/>
	 * @param pCharsetDecodage : Charset : charset dans lequel 
	 * on veut transcoder pString.<br/>
	 * @param pRapporte : boolean : true si on veut que 
	 * la méthode rapporte.<br/>
	 * @param pNumeroLigne  : Integer : Numéro de la ligne (1-based) 
	 * dans un fichier.<br/>
	 * 
	 * @return : String : String transcodée en pCharsetDecodage.<br/>
	 */
	String transcoder(
			String pString
				, Charset pCharsetEncodage
					, Charset pCharsetDecodage
						, boolean pRapporte
							, Integer pNumeroLigne);
	
	
	
	/**
	 * Détermine si la ligne pString a pu être encodée 
	 * avec le Charset pCharset.<br/>
	 * La ligne ne doit contenir aucun caractère indésirable 
	 * si c'est la cas.<br/>
	 * - retourne true si la ligne pString a pu être initialement encodée 
	 * avec le Charset pCharset.<br/>
	 * <br/>
	 * - retourne false si pString == null.<br/>
	 * - retourne false si pCharset est null.<br/>
	 * <br/>
	 *
	 * @param pString : String.<br/>
	 * @param pCharset : Charset.<br/>
	 * @param pNumeroLigne : int : numéro (1-based) 
	 * de la ligne dans le fichier 
	 * (ne sert que pour les rapports).<br/>
	 * 
	 * @return : boolean : true si la ligne pString a pu potentiellement 
	 * être encodée 
	 * avec le Charset pCharset.<br/>
	 */
	boolean determinerSiEncodagePossible(
			String pString
				, Charset pCharset
					, int pNumeroLigne);
	
	
	
	/**
	 * method getFichierEnMap() :<br/>
	 * Getter de la SortedMap&lt;Integer,String&gt; 
	 * encapsulant un Fichier texte avec :<br/>
	 * <ul>
	 * <li>Integer : le numéro de la ligne.</li><br/>
	 * <li>String : la ligne.</li><br/>
	 * </ul>
	 * <br/>
	 *
	 * @return fichierEnMap : SortedMap<Integer,String>.<br/>
	 */
	SortedMap<Integer, String> getFichierEnMap();
	
	

} // FIN DE L'INTERFACE ILecteurDecodeurFile.--------------------------------
