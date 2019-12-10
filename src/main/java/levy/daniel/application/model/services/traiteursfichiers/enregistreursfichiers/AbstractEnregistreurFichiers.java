package levy.daniel.application.model.services.traiteursfichiers.enregistreursfichiers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.traiteursfichiers.enregistreursfichiers.rapportsenregistrements.LigneRapportEnregistrement;

/**
 * CLASSE AbstractEnregistreurFichiers :<br/>
 * Abstraction qui centralise les attributs et méthodes de toutes les classes 
 * qui fournissent des services d'enregistrement de fichiers sur disque.<br/>
 * SERVICE CHARGE D'ENREGISTRER UNE STRING SUR DISQUE.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>
 *  // Instanciation d'un EnregistreurFichier.<br/>
 *  final IEnregistreurFichiers enregistreur 
 *  = new EnregistreurFichiers(
 *  pDateEnregistrement, pUserName, pObjet, pFichier);<br/>
 *  // Enregistrement de la chaine aEcrire dans pFichier 
 *  avec le Charset pCharset et en substituant éventuellement 
 *  pSautLigne aux sauts de ligne existants dans la chaîne aEcrire.<br/>
 *  final File resultat = enregistreur.ecrireStringDansFile(pFichier, aEcrire, pCharset, pSautLigne);<br/>
 * </code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * FileOutputStream, OutputStreamWriter, BufferedWriter, Charset,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * levy.daniel.application.IExportateurCsv 
 * (sous-entendu, pas d'implémentation directe).<br/>
 * levy.daniel.application.IExportateurJTable 
 * (sous-entendu, pas d'implémentation directe).<br/>
 * levy.daniel.application.IResetable 
 * (sous-entendu, pas d'implémentation directe).<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.rapportsenregistrements.LigneRapportEnregistrement.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.IRapporteurEnregistrement.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.IEnregistreurFichiers.<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 3 mars 2016
 *
 */
public abstract class AbstractEnregistreurFichiers implements
		IEnregistreurFichiers {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe AbstractEnregistreurFichiers".<br/>
	 */
	public static final String CLASSE_ABSTRACT_ENREGISTREUR_FICHIERS 
		= "Classe AbstractEnregistreurFichiers";
		
	/**
	 * "méthode ecrireStringDansFile(
	 * File pFile, String pString, Charset pCharset, String pSautLigne)".<br/>
	 */
	public static final String METHODE_ECRIRESTRINGDANSFILE 
		= "méthode ecrireStringDansFile(File pFile, String pString"
				+ ", Charset pCharset, String pSautLigne)";

	/**
	 * "Méthode lireFichierCaractereParCaractere(File pFile, Charset pCharset)".
	 */
	public static final String METHODE_LIREFICHIER_CARACTERE_PAR_CARACTERE 
		= "Méthode lireFichierCaractereParCaractere(File pFile, Charset pCharset)";
	
	/**
	 * "Méthode lireFichierLigneParLigne(File pFile, Charset pCharset".
	 */
	public static final String METHODE_LIREFICHIERLIGNE_PAR_LIGNE 
		= "Méthode lireFichierLigneParLigne(File pFile, Charset pCharset";
	
	/**
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si le fichier est null.<br/>
	 * "Le fichier passé en paramètre est null".<br/>
	 */
	public static final String MESSAGE_FICHIER_NULL 
		= "Le fichier passé en paramètre est null";
	
	/**
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si le fichier est inexistant.<br/>
	 * "Le fichier passé en paramètre est inexistant : "
	 */
	public static final String MESSAGE_FICHIER_INEXISTANT 
		= "Le fichier passé en paramètre est inexistant : ";
	
	/**
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si le fichier est un répertoire.<br/>
	 * "Le fichier passé en paramètre est un répertoire : ".<br/>
	 */
	public static final String MESSAGE_FICHIER_REPERTOIRE 
		= "Le fichier passé en paramètre est un répertoire : ";
	
	/**
	 * Message retourné par la METHODE_ECRIRESTRINGDANSFILE 
	 * si la String passée en paramètre est blank.<br/>
	 * "La chaine de caractères passée en paramètre est blank (null ou vide) : "
	 */
	public static final String MESSAGE_STRING_BLANK 
	= "La chaine de caractères passée en paramètre est blank (null ou vide) : ";
	
	/**
	 * "Exception GRAVE : ".<br/>
	 */
	public static final String MESSAGE_EXCEPTION = "Exception GRAVE : ";
	
	/**
	 * nom de la classe de contrôle concrète.<br/>
	 */
	protected transient String nomClasseConcrete;
	
	/**
	 * java.util.Date de l'enregistrement du fichier.<br/>
	 */
	protected Date dateEnregistrement;
	
	/**
	 * date de l'enregistrement du fichier 
	 * formattée au format dfDatetimemilliFrancaiseLexico.<br/>
	 * Format des dates-heures françaises avec millisecondes comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 */
	protected transient String dateEnregistrementStringFormatee;
		
	/**
	 * nom de l'utilisateur qui a déclenché l'enregistrement du fichier.<br/>
	 */
	protected String userName;
	
	/**
	 * objet (ou motif) ayant demandé la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/>
	 */
	protected String objet;
		
	/**
	 * fichier enregistré.<br/>
	 */
	protected File fichier;
	
	/**
	 * nom du fichier objet de l'enregistrement.<br/>
	 */
	protected transient String nomFichier;
		
	/**
	 * rapport fourni par l'enregistreur sous forme 
	 * de List&lt;LigneRapportEnregistrement&gt;.<br/>
	 */
	protected transient List<LigneRapportEnregistrement> rapport 
		= new ArrayList<LigneRapportEnregistrement>();
		
	/**
	 * Format des dates-heures françaises avec millisecondes comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 */
	public final transient DateFormat dfDatetimemilliFrancaise 
	= new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss.SSS", LOCALE_FR_FR);

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(AbstractEnregistreurFichiers.class);
	

	// *************************METHODES************************************/
	
	
	
	 /**
	 * method CONSTRUCTEUR AbstractEnregistreurFichiers() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 * - Remplit le nom de la classe concrète this.nomClasseConcrete 
	 * fourni par this.fournirNomClasseConcrete() 
	 * dans la classe concrète.<br/>
	 * - Met automatiquement dateEnregistrement à date système.<br/>
	 * - Met automatiquement userName à "Administrateur".<br/>
	 * <br/>
	 * - calcule automatiquement dateEnregistrementStringFormattee.<br/>
	 * - passe null à this.objet.<br/>
	 * - passe null à this.fichier.<br/>
	 * - calcule automatiquement nomFichier (null).<br/>
	 * <br/>
	 */
	public AbstractEnregistreurFichiers() {
		this(null, null, null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * method CONSTRUCTEUR AbstractEnregistreurFichiers(
	 * String pObjet) :<br/>
	 * Constructeur avec objet.<br/>
	 * <br/>
	 * - Remplit le nom de la classe concrète this.nomClasseConcrete 
	 * fourni par this.fournirNomClasseConcrete() 
	 * dans la classe concrète.<br/>
	 * - Met automatiquement dateEnregistrement à date système.<br/>
	 * - Met automatiquement userName à "Administrateur".<br/>
	 * <br/>
	 * - calcule automatiquement dateEnregistrementStringFormattee.<br/>
	 * - passe pObjet à this.objet.<br/>
	 * - passe null à this.fichier.<br/>
	 * - calcule automatiquement nomFichier (null).<br/>
	 * <br/>
	 *
	 * @param pObjet : String : objet (ou motif) ayant demandé 
	 * la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/>
	 */
	public AbstractEnregistreurFichiers(
			final String pObjet) {
		this(null, null, pObjet, null);
	} // Fin de CONSTRUCTEUR AbstractEnregistreurFichiers(
	 // String pObjet).____________________________________________________
	
	
	
	 /**
	 * method CONSTRUCTEUR AbstractEnregistreurFichiers(
	 * String pObjet
	 * , File pFichier) :<br/>
	 * Constructeur avec objet et fichier.<br/>
	 * <br/>
	 * - Remplit le nom de la classe concrète this.nomClasseConcrete 
	 * fourni par this.fournirNomClasseConcrete() 
	 * dans la classe concrète.<br/>
	 * - Met automatiquement dateEnregistrement à date système.<br/>
	 * - Met automatiquement userName à "Administrateur".<br/>
	 * <br/>
	 * - calcule automatiquement dateEnregistrementStringFormattee.<br/>
	 * - passe pObjet à this.objet.<br/>
	 * - passe pFichier à this.fichier.<br/>
	 * - calcule automatiquement nomFichier.<br/>
	 * <br/>
	 *
	 * @param pObjet : String : objet (ou motif) ayant demandé 
	 * la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/>
	 * @param pFichier : File : fichier enregistré.<br/>
	 */
	public AbstractEnregistreurFichiers(
				final String pObjet
						, final File pFichier) {
		
		this(null, null, pObjet, pFichier);
		
	} // Fin de CONSTRUCTEUR AbstractEnregistreurFichiers(
	 // String pObjet
	 // , File pFichier).__________________________________________________

	
	
	 /**
	 * method CONSTRUCTEUR AbstractEnregistreurFichiers(
	 * String pUserName
	 * , String pObjet
	 * , File pFichier) :<br/>
	 * Constructeur avec user, objet et fichier.<br/>
	 * <br/>
	 * - Remplit le nom de la classe concrète this.nomClasseConcrete 
	 * fourni par this.fournirNomClasseConcrete() 
	 * dans la classe concrète.<br/>
	 * - Met automatiquement dateEnregistrement à date système.<br/>
	 * <br/>
	 * - calcule automatiquement dateEnregistrementStringFormattee.<br/>
	 * - remplit userName avec pUserName si pUserName != null 
	 * ou 'Administrateur' sinon.<br/>
	 * - passe pObjet à this.objet.<br/>
	 * - passe pFichier à this.fichier.<br/>
	 * - calcule automatiquement nomFichier.<br/>
	 * <br/>
	 *
	 * @param pUserName : String : 
	 * nom de l'utilisateur qui a déclenché l'enregistrement du fichier.<br/>
	 * @param pObjet : String : objet (ou motif) ayant demandé 
	 * la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/>
	 * @param pFichier : File : fichier enregistré.<br/>
	 */
	public AbstractEnregistreurFichiers(
			final String pUserName
					, final String pObjet
						, final File pFichier) {
		
		this(null, pUserName, pObjet, pFichier);
		
	} // Fin de CONSTRUCTEUR AbstractEnregistreurFichiers(
	 // String pUserName
	 // , String pObjet
	 // , File pFichier).__________________________________________________
	
	
	
	 /**
	 * method CONSTRUCTEUR AbstractEnregistreurFichiers(COMPLET) :<br/>
	 * CONSTRUCTEUR COMPLET.<br/>
	 * <br/>
	 * - Remplit le nom de la classe concrète this.nomClasseConcrete 
	 * fourni par this.fournirNomClasseConcrete() 
	 * dans la classe concrète.<br/>
	 * - Remplit dateEnregistrement avec pDateEnregistrement 
	 * si pDateEnregistrement != null 
	 * ou la date système sinon.<br/>
	 * - calcule automatiquement dateEnregistrementStringFormattee.<br/>
	 * - remplit userName avec pUserName si pUserName != null 
	 * ou 'Administrateur' sinon.<br/>
	 * - passe pObjet à this.objet.<br/>
	 * - passe pFichier à this.fichier.<br/>
	 * - calcule automatiquement nomFichier.<br/>
	 * <br/>
	 *
	 * @param pDateEnregistrement : Date : 
	 * java.util.Date de l'enregistrement du fichier.<br/>
	 * @param pUserName : String : 
	 * nom de l'utilisateur qui a déclenché l'enregistrement du fichier.<br/>
	 * @param pObjet : String : objet (ou motif) ayant demandé 
	 * la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/>
	 * @param pFichier : File : fichier enregistré.<br/>
	 */
	public AbstractEnregistreurFichiers(
			final Date pDateEnregistrement
				, final String pUserName
					, final String pObjet
						, final File pFichier) {
		
		super();
		
		/* Remplit le nom de la classe concrète this.nomClasseConcrete 
		 * fourni par this.fournirNomClasseConcrete() 
		 * dans la classe concrète. */
		this.nomClasseConcrete = this.fournirNomClasseConcrete();
		
		/* Remplit dateEnregistrement avec pDateEnregistrement 
		 * si pDateEnregistrement != null 
		 * ou la date système sinon. */
		this.dateEnregistrement = this.fournirDate(pDateEnregistrement);
		
		/* calcule automatiquement dateEnregistrementStringFormattee. */
		this.dateEnregistrementStringFormatee
			= this.fournirDateFormattee(this.dateEnregistrement);
		
		/* remplit userName avec pUserName si pUserName != null 
		 * ou 'Administrateur' sinon. */
		this.userName = this.fournirUserName(pUserName);
		
		/* passe pObjet à this.objet. */
		this.objet = pObjet;
		
		/* passe pFichier à this.fichier. */
		this.fichier = pFichier;
		
		/* calcule automatiquement nomFichier. */
		this.nomFichier = this.fournirNomFichier(this.fichier);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File ecrireStringDansFileLatin9(
			final File pFile
				, final String pString) {
		
		return this.ecrireStringDansFile(pFile, pString
				, CHARSET_LATIN9, NEWLINE);
		
	} // Fin de ecrireStringDansFileLatin9(...).___________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File ecrireStringDansFileANSI(
			final File pFile
				, final String pString) {
		
		return this.ecrireStringDansFile(pFile, pString
				, CHARSET_ANSI, NEWLINE);
		
	} // Fin de ecrireStringDansFileANSI(...)._____________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File ecrireStringDansFileUTF8(
			final File pFile
				, final String pString) {
		
		return this.ecrireStringDansFile(pFile, pString
				, CHARSET_UTF8, NEWLINE);
		
	} // Fin de ecrireStringDansFileUTF8(...)._____________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File ecrireStringDansFile(
			final File pFile
				, final String pString
					, final Charset pCharset
						, final String pSautLigne) {
		
		/* rafraîchit le rapport (en instancie un nouveau à chaque appel 
		 * de la méthode ecrireStringDansFile(File pFile)). */
		this.rapport = new ArrayList<LigneRapportEnregistrement>();

		/* retourne null, LOG de niveau INFO 
		 * et rapport si pFile est null. */
		if (pFile == null) {

			/* LOG de niveau INFO. */
			loggerInfo(this.fournirNomClasseConcrete(),
					METHODE_ECRIRESTRINGDANSFILE
						, MESSAGE_FICHIER_NULL);
			
			/* rapport. */
			final LigneRapportEnregistrement ligne 
				= this.creerLigneRapport(
						MESSAGE_FICHIER_NULL
							, null
								, "KO");
			
			this.ajouterLigneRapport(ligne);
			
			/* retour de null. */
			return null;
		}

		/* retourne null, LOG de niveau INFO 
		 * et rapport si pFile est inexistant. */
		if (!pFile.exists()) {

			/* LOG de niveau INFO. */
			loggerInfo(this.fournirNomClasseConcrete(),
					METHODE_ECRIRESTRINGDANSFILE
						, MESSAGE_FICHIER_INEXISTANT
							, pFile.getAbsolutePath());
			
			/* rapport. */
			final LigneRapportEnregistrement ligne 
				= this.creerLigneRapport(
						MESSAGE_FICHIER_INEXISTANT + pFile.getAbsolutePath()
							, null
								, "KO");
			
			this.ajouterLigneRapport(ligne);

			/* retour de null. */
			return null;
		}

		/* retourne null, LOG de niveau INFO 
		 * et rapport si pFile est un répertoire. */
		if (pFile.isDirectory()) {

			/* LOG de niveau INFO. */
			loggerInfo(this.fournirNomClasseConcrete(),
					METHODE_ECRIRESTRINGDANSFILE
						, MESSAGE_FICHIER_REPERTOIRE
							, pFile.getAbsolutePath());
			
			/* rapport. */
			final LigneRapportEnregistrement ligne 
				= this.creerLigneRapport(
						MESSAGE_FICHIER_REPERTOIRE + pFile.getAbsolutePath()
							, null
								, "KO");
			
			this.ajouterLigneRapport(ligne);

			/* retour de null. */
			return null;
		}

		/* retourne null, LOG de niveau INFO 
		 * et rapport si pString est blank. */
		if (StringUtils.isBlank(pString)) {

			/* LOG de niveau INFO. */
			loggerInfo(this.fournirNomClasseConcrete(),
					METHODE_ECRIRESTRINGDANSFILE
						, MESSAGE_STRING_BLANK
							, pString);
			
			/* rapport. */
			final LigneRapportEnregistrement ligne 
			= this.creerLigneRapport(
					MESSAGE_STRING_BLANK + pString
						, null
							, "KO");
		
			this.ajouterLigneRapport(ligne);
			
			/* retour de null. */
			return null;
		}
		
		/* passe pFile à this.fichier et 
		 * rafraîchit automatiquement this.nomFichier. */
		this.setFichier(pFile);
				
		Charset charset = null;

		/* Passe automatiquement le charset à UTF-8 si pCharset est null. */
		if (pCharset == null) {
			charset = CHARSET_UTF8;
		} else {
			charset = pCharset;
		}

		String sautLigne = null;

		/*
		 * Passe automatiquement le saut de ligne à NEWLINE (saut de ligne de la
		 * plateforme) si pSautLigne est blank.
		 */
		if (StringUtils.isEmpty(pSautLigne)) {
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
			/* ré-écrit le contenu du fichier si il existe à chaque appel 
			 * (boolean false dans new FileOutputStream(pFile, false)). */
			fileOutputStream = new FileOutputStream(pFile, false);

			/*
			 * Ouverture d'un OutputStreamWriter sur le FileOutputStream en lui
			 * passant le Charset.
			 */
			outputStreamWriter = new OutputStreamWriter(fileOutputStream,
					charset);

			/*
			 * Ouverture d'un tampon d'écriture BufferedWriter sur le
			 * OutputStreamWriter.
			 */
			bufferedWriter = new BufferedWriter(outputStreamWriter);

			// ECRITURE.
			/*
			 * Substitue automatiquement sautLigne aux sauts de ligne dans
			 * pString si nécessaire.
			 */
			final String stringSubstituee 
				= substituerSautLigne(pString, sautLigne);
			
			bufferedWriter.write(stringSubstituee);
			bufferedWriter.flush();
			
			/* rapport. */
			final LigneRapportEnregistrement ligne 
				= this.creerLigneRapport(
						"Le fichier '" + pFile.getName() + "' a bien été créé"
							, pFile.getAbsolutePath()
								, "OK");
			
			this.ajouterLigneRapport(ligne);

			// Retour du fichier.
			return pFile;

		} catch (FileNotFoundException fnfe) {

			/* LOG de niveau ERROR et rapport. */
			loggerError(
					this.fournirNomClasseConcrete()
						, MESSAGE_EXCEPTION
							, fnfe);
			
			/* rapport. */
			final LigneRapportEnregistrement ligne 
			= this.creerLigneRapport(
					MESSAGE_EXCEPTION + fnfe
						, null
							, "KO");
		
			this.ajouterLigneRapport(ligne);

			/* retour de null. */
			return null;

		} catch (IOException ioe) {

			/* LOG de niveau ERROR et rapport. */
			loggerError(
					this.fournirNomClasseConcrete()
						, MESSAGE_EXCEPTION
							, ioe);
			
			/* rapport. */
			final LigneRapportEnregistrement ligne 
			= this.creerLigneRapport(
					MESSAGE_EXCEPTION + ioe
						, null
							, "KO");
		
			this.ajouterLigneRapport(ligne);

			/* retour de null. */
			return null;
		}

		finally {

			if (bufferedWriter != null) {
				try {

					bufferedWriter.close();

				} catch (IOException ioe1) {

					/* LOG de niveau ERROR et rapport. */
					loggerError(
							this.fournirNomClasseConcrete()
								, MESSAGE_EXCEPTION
									, ioe1);
					
					/* rapport. */
					final LigneRapportEnregistrement ligne 
					= this.creerLigneRapport(
							MESSAGE_EXCEPTION + ioe1
								, null
									, "KO");
				
					this.ajouterLigneRapport(ligne);
				}
			} // Fin de if (bufferedWriter != null).__________

			if (outputStreamWriter != null) {
				try {

					outputStreamWriter.close();

				} catch (IOException ioe2) {

					/* LOG de niveau ERROR et rapport. */
					loggerError(
							this.fournirNomClasseConcrete()
							, MESSAGE_EXCEPTION
								, ioe2);
					
					/* rapport. */
					final LigneRapportEnregistrement ligne 
					= this.creerLigneRapport(
							MESSAGE_EXCEPTION + ioe2
								, null
									, "KO");
				
					this.ajouterLigneRapport(ligne);
				}
			} // Fin de if (outputStreamWriter != null).______

			if (fileOutputStream != null) {
				try {

					fileOutputStream.close();

				} catch (IOException ioe3) {

					// * LOG de niveau ERROR et rapport. */
					loggerError(
							this.fournirNomClasseConcrete()
							, MESSAGE_EXCEPTION
								, ioe3);
					
					/* rapport. */
					final LigneRapportEnregistrement ligne 
					= this.creerLigneRapport(
							MESSAGE_EXCEPTION + ioe3
								, null
									, "KO");
				
					this.ajouterLigneRapport(ligne);
				}
			}

		} // Fin du finally.____________________________

	} // Fin de ecrireStringDansFile(...)._________________________________
	

	
	/**
	 * method substituerSautLignePlateforme(
	 * String pString) :<br/>
	 * SERVICE ACCESSOIRE.<br/>
	 * Substitue les sauts de ligne dans pString 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * par les sauts de ligne de la plate-forme
	 * sur laquelle le programme s'exécute.<br/>
	 * <br/>
	 * - retourne null si pString est blank (null ou vide).<br/>
	 * <br/>
	 *
	 * @param pString : String : String à corriger.<br/>
	 * 
	 * @return : String : La String dans laquelle les sauts de ligne 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * ont été substitués par les sauts de ligne de la plate-forme.<br/>
	 */
	public final String substituerSautLignePlateforme(
			final String pString) {
		
		return substituerSautLigne(pString, NEWLINE);
		
	} // Fin de method substituerSautLignePlateforme(
	 // String pString).___________________________________________________
	

	
	/**
	 * method substituerSautLigne(
	 * String pString
	 * , String pSautLigne) :<br/>
	 * SERVICE ACCESSOIRE.<br/>
	 * Substitue les sauts de ligne dans pString 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * par les sauts de ligne pSautLigne.<br/>
	 * <br/>
	 * - retourne null si pString est blank (null ou vide).<br/>
	 * - retourne null si pSautLigne est blank (null ou vide).
	 * <br/>
	 *
	 * @param pString : String : String à corriger.<br/>
	 * @param pSautLigne : String : saut de ligne à substituer.<br/>
	 * 
	 * @return : String : La String dans laquelle les sauts de ligne 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * ont été substitués par les sauts de ligne pSautLigne.<br/>
	 */
	public final String substituerSautLigne(
			final String pString,
				final String pSautLigne) {

		/* retourne null si pString est blank (null ou vide). */
		if (StringUtils.isBlank(pString)) {
			return null;
		}

		/* retourne null si pSautLigne est blank (null ou vide). */

		/* Recherche des sauts de ligne DOS/Windows. */
		if (StringUtils.contains(pString, SAUTDELIGNE_DOS_WINDOWS)) {

			final String resultat = StringUtils.replace(pString,
					SAUTDELIGNE_DOS_WINDOWS, pSautLigne);

			return resultat;
		}

		/* Recherche des sauts de ligne Mac. */
		if (StringUtils.contains(pString, SAUTDELIGNE_MAC)) {

			final String resultat = StringUtils.replace(pString,
					SAUTDELIGNE_MAC, pSautLigne);

			return resultat;
		}

		/* Recherche des sauts de ligne Unix. */
		if (StringUtils.contains(pString, SAUTDELIGNE_UNIX)) {

			final String resultat = StringUtils.replace(pString,
					SAUTDELIGNE_UNIX, pSautLigne);

			return resultat;
		}

		/*
		 * Retourne la chaîne inchangée si il n'y avait pas de saut de ligne.
		 */
		return pString;

	} // Fin de substituerSautLigne(
	 // String pString
	 // , String pSautLigne).______________________________________________
	

	
	/**
	 * <p>remplace un caractère pChar si il est un saut de ligne 
	 * (UNIX et JAVA = LF ='\n' = '\u000a', MAC = CR = '\r' = '\u000d', ..) 
	 * par le saut de ligne pSautLigne.</p>
	 * <p>- retourne pChar inchangé si ce n'est pas un saut de ligne.</p>
	 * 
	 * Par exemple, remplace un SAUTDELIGNE_MAC = '\r' = '\u000d' 
	 * par un SAUTDELIGNE_UNIX = '\n' = '\u000a'.<br/>
	 * <br/>
	 *
	 * @param pChar : char : cractère entrant supposé être un saut de ligne.
	 * @param pSautLigne : char : le saut de ligne voulu en sortie.
	 * 
	 * @return : char : pSautLigne à substituer à pChar.<br/>
	 */
	public final char substituerSautLigne(
			final char pChar,
				final char pSautLigne) {

		/* Recherche des sauts de ligne Mac. */
		if (StringUtils.equals(String.valueOf(pChar), SAUTDELIGNE_MAC)) {
			return pSautLigne;
		}

		/* Recherche des sauts de ligne Unix. */
		if (StringUtils.equals(String.valueOf(pChar), SAUTDELIGNE_UNIX)) {
			return pSautLigne;
		}

		/*
		 * Retourne le caractère inchangé si ce n'est pas un saut de ligne.
		 */
		return pChar;

	} // Fin de substituerSautLigne(...).__________________________________
	
	
	
	/**
	 * method afficherSautLigne(
	 * String pSautLigne) :<br/>
	 * SERVICE ACCESSOIRE.<br/>
	 * Affiche les caractères non imprimables 
	 * saut de ligne (\n ou \r ou \r\n).<br/>
	 * <br/>
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
	public final String afficherSautLigne(
			final String pSautLigne) {
			
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
			
	} // Fin de afficherSautLigne(
	 // String pSautLigne).________________________________________________
	

		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String lireFichierCaractereParCaractere(
			final File pFile
				, final Charset pCharset) {
		
		// TRAITEMENT DES MAUVAIS FICHIERS 
		// (null, inexistant, répertoire, vide).*************************
		if (pFile == null) {
			return null;
		}
		
		if (!pFile.exists()) {
			return null;
		}
		
		if (pFile.isDirectory()) {
			return null;
		}
		
		if (pFile.length() == 0) {
			return null;
		}
				
		// LECTURE ***************
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		int characterEntier = 0;
		Character character = null;
		
		final StringBuilder stb = new StringBuilder();

		Charset charset = null;

		/* Choisit automatiquement le CHARSET_UTF8 si pCharset == null. */
		if (pCharset == null) {
			charset = CHARSET_UTF8;
		} else {
			charset = pCharset;
		}

		try {

			/*
			 * Instancie un flux en lecture fileInputStream en lui passant
			 * pFile.
			 */
			fileInputStream 
				= new FileInputStream(pFile);

			/*
			 * Instancie un InputStreamReader 
			 * en lui passant le FileReader et le
			 * Charset.
			 */
			inputStreamReader 
				= new InputStreamReader(fileInputStream, charset);

			/*
			 * Instancie un tampon de flux de caractères en lecture en lui
			 * passant le flux inputStreamReader.
			 */
			bufferedReader 
				= new BufferedReader(inputStreamReader);
			
			/* Parcours du bufferedReader. */
			while (true) {
				
				/* Lecture de chaque caractère. */
				characterEntier = bufferedReader.read();
				
				/* Arrêt de la lecture si fin de fichier. */
				if (characterEntier < 0) {
					break;
				}
				
				/* Conversion de l'entier en caractère. */
				character = (char) characterEntier;
								
				/* Ajout du caractère au StringBuilder. */
				stb.append(character);
				
			} // Fin du parcours du bufferedReader._________

		} catch (FileNotFoundException fnfe) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasseConcrete()
						, METHODE_LIREFICHIER_CARACTERE_PAR_CARACTERE
							, fnfe);
			
			/* retourne le message de l'exception. */
			return fnfe.getMessage();
			
		} catch (IOException ioe) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasseConcrete()
						, METHODE_LIREFICHIER_CARACTERE_PAR_CARACTERE
							, ioe);
			
			/* retourne le message de l'exception. */
			return ioe.getMessage();
		}
		
		finally {
			
			/* fermeture du flux BufferedReader. */
			if (bufferedReader != null) {
				
				try {
					
					bufferedReader.close();
					
				} catch (IOException ioe2) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIREFICHIER_CARACTERE_PAR_CARACTERE
									, ioe2);
					
				}
				
			} // Fin de if (bufferedReader != null).____
			
			/* fermeture du flux inputStreamReader. */
			if (inputStreamReader != null) {
				
				try {
					
					inputStreamReader.close();
					
				} catch (IOException ioe4) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIREFICHIER_CARACTERE_PAR_CARACTERE
									, ioe4);
				}
				
			} // Fin de if (inputStreamReader != null).______
			
			/* fermeture du flux fileInputStream. */
			if (fileInputStream != null) {
				
				try {
					
					fileInputStream.close();
					
				} catch (IOException ioe3) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIREFICHIER_CARACTERE_PAR_CARACTERE
									, ioe3);
					
				}
				
			} // Fin de if (fileInputStream != null).________
			
		} // Fin du finally._____________________________
		
		return stb.toString();
		
	} // Fin de lireFichierCaractereParCaractere(
	 // File pFile
	 // , Charset pCharset)._______________________________________________
	
	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String lireFichierLigneParLigne(
			final File pFile
				, final Charset pCharset) {
		
		// TRAITEMENT DES MAUVAIS FICHIERS 
		// (null, inexistant, répertoire, vide).*************************
		if (pFile == null) {
			return null;
		}
		
		if (!pFile.exists()) {
			return null;
		}
		
		if (pFile.isDirectory()) {
			return null;
		}
		
		if (pFile.length() == 0) {
			return null;
		}
		
		// LECTURE LIGNE PAR LIGNE ***************
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		final StringBuilder stb = new StringBuilder();
		
		String ligneLue = null;
		Charset charset = null;

		/* Choisit automatiquement le CHARSET_UTF8 si pCharset == null. */
		if (pCharset == null) {
			charset = CHARSET_UTF8;
		} else {
			charset = pCharset;
		}

		/* récupère le nombre de lignes du fichier. */
		final int nombreLignes = this.compterLignesFichier(pFile, charset);
		
		try {

			/*
			 * Instancie un flux en lecture fileInputStream en lui passant
			 * pFile.
			 */
			fileInputStream = new FileInputStream(pFile);

			/*
			 * Instancie un InputStreamReader en lui passant le FileReader et le
			 * Charset.
			 */
			inputStreamReader = new InputStreamReader(fileInputStream, charset);

			/*
			 * Instancie un tampon de flux de caractères en lecture en lui
			 * passant le flux inputStreamReader.
			 */
			bufferedReader = new BufferedReader(inputStreamReader);
			
			int compteur = 0;
			
			/* Parcours du bufferedReader. */
			while (true) {
				
				compteur++;
				
				/* Lecture de chaque ligne. */
				ligneLue = bufferedReader.readLine();
								
				/* Arrêt de la lecture si fin de fichier. */
				if (ligneLue == null) {
					break;
				}
				
				stb.append(ligneLue);
				
				if (nombreLignes > 1 && compteur < nombreLignes) {
					stb.append(SAUTDELIGNE_UNIX);
				}
																
			} // Fin du parcours du bufferedReader._________

		} catch (FileNotFoundException fnfe) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasseConcrete()
						, METHODE_LIREFICHIERLIGNE_PAR_LIGNE
							, fnfe);
			
			/* retourne le message de l'exception. */
			return fnfe.getMessage();
			
		} catch (IOException ioe) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasseConcrete()
						, METHODE_LIREFICHIERLIGNE_PAR_LIGNE
							, ioe);
			
			/* retourne le message de l'exception. */
			return ioe.getMessage();
		}
		
		finally {
			
			/* fermeture du flux BufferedReader. */
			if (bufferedReader != null) {
				
				try {
					
					bufferedReader.close();
					
				} catch (IOException ioe2) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIREFICHIERLIGNE_PAR_LIGNE
									, ioe2);
					
				}
				
			} // Fin de if (bufferedReader != null).____
			
			/* fermeture du flux inputStreamReader. */
			if (inputStreamReader != null) {
				
				try {
					
					inputStreamReader.close();
					
				} catch (IOException ioe4) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIREFICHIERLIGNE_PAR_LIGNE
									, ioe4);
				}
				
			} // Fin de if (inputStreamReader != null).______
			
			/* fermeture du flux fileInputStream. */
			if (fileInputStream != null) {
				
				try {
					
					fileInputStream.close();
					
				} catch (IOException ioe3) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIREFICHIERLIGNE_PAR_LIGNE
									, ioe3);
					
				}
				
			} // Fin de if (fileInputStream != null).________
			
		} // Fin du finally._____________________________
		
		return stb.toString();
		
	} // Fin de lireFichierLigneParLigne(
	 // File pFile
	 // , Charset pCharset)._______________________________________________


	
	/**
	 * retourne le nombre de lignes dun fichier textuel.
	 * <ul>
	 * <li>Choisit automatiquement le CHARSET_UTF8 si pCharset == null.</li>
	 * <li>utilise <code>Files.readAllLines(pFile.toPath(), charset)</code> 
	 * pour obtenir la liste des lignes du fichier et les compter.</li>
	 * </ul>
	 * - retourne 0 si le fichier est mauvais 
	 * (null, inexistant, répertoire, vide) ou 
	 * si la lecture lève une IOException.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier textuel dont on veut compter les lignes.
	 * @param pCharset : Charset : Charset pour décoder le fichier.
	 * 
	 * @return : int : nombre de lignes.<br/>
	 */
	private int compterLignesFichier(
			final File pFile
				, final Charset pCharset) {
		
		// TRAITEMENT DES MAUVAIS FICHIERS 
		// (null, inexistant, répertoire, vide).*************************
		if (pFile == null) {
			return 0;
		}
		
		if (!pFile.exists()) {
			return 0;
		}
		
		if (pFile.isDirectory()) {
			return 0;
		}
		
		if (pFile.length() == 0) {
			return 0;
		}

		Charset charset = null;

		/* Choisit automatiquement le CHARSET_UTF8 si pCharset == null. */
		if (pCharset == null) {
			charset = CHARSET_UTF8;
		} else {
			charset = pCharset;
		}
		
		try {
			
			final List<String> listeLignes 
				= Files.readAllLines(pFile.toPath(), charset);
			
			return listeLignes.size();
			
		} catch (IOException e) {
			return 0;
		}

	} // Fin de compterLignesFichier(...)._________________________________
	
	
	
	/**
	 * method fournirNomClasseConcrete() :<br/>
	 * Retourne le nom de la classe concrète.<br/>
	 * <br/>
	 *
	 * @return : String : nom de la classe concrète.<br/>
	 */
	protected abstract String fournirNomClasseConcrete();
	
	
	
	/**
	 * method fournirDate(
	 * Date pDate) :<br/>
	 * - retourne la date système si pDate == null.<br/>
	 * - retourne pDate sinon.<br/>
	 * <br/>
	 *
	 * @param pDate : java.util.Date.<br/>
	 * @return : Date : date système ou pDate.<br/>
	 */
	private Date fournirDate(
			final Date pDate) {
		
		/* retourne la date système si pDate == null. */
		if (pDate == null) {
			return new Date();
		}
		
		/* retourne pDate sinon. */
		return pDate;
		
	} // Fin de fournirDate(
	 // Date pDate)._______________________________________________________
	

	
	/**
	 * method fournirDateFormattee(
	 * Date pDate) :<br/>
	 * Fournit une date sous forme de String formattée 
	 * au format dfDatetimemilliFrancaiseLexico.<br/>
	 * Format des dates-heures françaises avec millisecondes comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 * <br/>
	 * - retourne null si pDate == null.<br/>
	 * <br/>
	 *
	 * @param pDate
	 * @return : String : "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 */
	private String fournirDateFormattee(
			final Date pDate) {
		
		/* retourne null si pDate == null. */
		if (pDate == null) {
			return null;
		}
		
		final String dateFormattee 
			= this.dfDatetimemilliFrancaise.format(pDate);
		
		return dateFormattee;
		
	} // Fin de fournirDateFormattee(
	 // Date pDate)._______________________________________________________
	
	
	
	/**
	 * method fournirUserName(
	 * String pUserName) :<br/>
	 * - retourne 'Administrateur' si pUsername == null.<br/>
	 * - retourne pUserName sinon.<br/>
	 * <br/>
	 *
	 * @param pUserName
	 * @return : String :  .<br/>
	 */
	private String fournirUserName(
			final String pUserName) {
		
		/* retourne 'Administrateur' si pUsername == null. */
		if (pUserName == null) {
			return "Administrateur";
		}
		
		/* retourne pUserName sinon. */
		return pUserName;
		
	} // Fin de fournirUserName(
	 // String pUserName)._________________________________________________
	

	
	/**
	 * method fournirNomFichier(
	 * File pFile) :<br/>
	 * retourne le nom de pFile.<br/>
	 * <br/>
	 * - retourne null si pFile == null.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @return : String : nom du fichier.<br/>
	 */
	private String fournirNomFichier(
			final File pFile) {
		
		/* retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		/* retourne le nom de pFile. */
		return pFile.getName();
		
	} // Fin de fournirNomFichier(
	 // File pFile)._______________________________________________________
	
	

	/**
	 * method loggerInfo(
	 * String pClasse
	 * , String pMethode
	 * , String pMessage) :<br/>
	 * Créée un message de niveau INFO dans le LOG.<br/>
	 * <br/>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pMessage : String : Message particulier de la méthode.<br/>
	 */
	private void loggerInfo(
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
	 * Créée un message de niveau INFO dans le LOG.<br/>
	 * <br/>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pMessage : String : Message particulier de la méthode.<br/>
	 * @param pComplement : String : Complément au message de la méthode.<br/>
	 */
	private void loggerInfo(
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
	 * Créée un message de niveau ERROR dans le LOG.<br/>
	 * <br/>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pException : Exception : Exception transmise 
	 * par la méthode appelante.<br/>
	 */
	private void loggerError(
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
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportTextuel() {
		
		/* retourne null si this.rapport == null. */
		if (this.rapport == null) {
			return null;
		}
		
		return this.afficherRapportTextuel(this.rapport);
		
	} // Fin de afficherRapportTextuel().__________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportTextuel(
			final List<LigneRapportEnregistrement> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final LigneRapportEnregistrement ligne : pList) {
			stb.append(ligne.toString());
			stb.append(NEWLINE);
		}
		
		return stb.toString();
		
	} // Fin de afficherRapportTextuel(
	 // List<LigneRapportEnregistrement> pList).___________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteCsv() {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append("id;");
		stb.append("date de l'enregistrement;");
		stb.append("utilisateur;");
		stb.append("Objet;");
		stb.append("Fichier;");
		stb.append("Message de l'enregistrement;");
		stb.append("Chemin du fichier enregistré;");
		stb.append("Statut de l'enregistrement;");
		
		return stb.toString();
		
	} // Fin de getEnTeteCsv().____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportCsv() {
		
		/* retourne null si this.rapport == null. */
		if (this.rapport == null) {
			return null;
		}
		
		return this.afficherRapportCsv(this.rapport, false);
		
	} // Fin de afficherRapportCsv().______________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportCsvAvecEntete() {
		
		/* retourne null si this.rapport == null. */
		if (this.rapport == null) {
			return null;
		}
		
		return this.afficherRapportCsv(this.rapport, true);
		
	} // Fin de afficherRapportCsvAvecEntete().____________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportCsv(
			final List<LigneRapportEnregistrement> pList
				, final boolean pAjouterEntete) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		int compteur = 0;
		
		for (final LigneRapportEnregistrement ligne : pList) {
			
			compteur++;
			
			/* Ajout de l'en-tête. */
			if (compteur == 1 && pAjouterEntete) {
				stb.append(this.fournirEnTeteCsv());
				stb.append(NEWLINE);
			}
			
			stb.append(ligne.fournirStringCsv());
			stb.append(NEWLINE);
		}
		
		return stb.toString();
				
	} // Fin de afficherRapportCsv(
	 // List<LigneRapportEnregistrement> pList
	// , boolean pAjouterEntete).__________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteRapportJTable(final int pI) {
		
		final LigneRapportEnregistrement ligne 
			= new LigneRapportEnregistrement();
				
		return ligne.fournirEnTeteColonne(pI);
		
	} // Fin de getEnTeteRapportJTable(
	// int pI).____________________________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Object fournirValeurRapportJTable(
			final int pLigne
				, final int pColonne) {
		
		/* retourne null si this.rapport == null. */
		if (this.rapport == null) {
			return null;
		}
		
		return this.rapport.get(pLigne).fournirValeurColonne(pColonne);
		
	} // Fin de getValeurRapportJTable(
	// int pLigne
	// , int pColonne).____________________________________________________
	
	
	
	/**
	 * method ajouterLigneRapport(
	 * LigneRapportEnregistrement pLigneRapport) :<br/>
	 * Ajoute une LigneRapportEnregistrement 
	 * au rapport de l'enregistrement.<br/>
	 * <br/>
	 * - retourne false si pLigneRapport == null.<br/>
	 * - retourne false si rapport == null.<br/>
	 * <br/>
	 *
	 * @param pLigneRapport : LigneRapportEnregistrement.<br/>
	 * 
	 * @return : boolean : true si la ligne de rapport 
	 * a été ajoutée au rapport.<br/>
	 */
	protected final boolean ajouterLigneRapport(
			final LigneRapportEnregistrement pLigneRapport) {
		
		/* retourne false si pLigneRapport == null. */
		if (pLigneRapport == null) {
			return false;
		}
		
		/* retourne false si rapport == null. */
		if (this.rapport == null) {
			return false;
		}
		
		/* Ajout de la ligne de rapport. */
		return this.rapport.add(pLigneRapport);
		
	} // Fin de ajouterLigneRapport(
	 // LigneRapportEnregistrement pLigneRapport)._________________________


	
	/**
	 * method retirerLigneRapport(
	 * LigneRapportEnregistrement pLigneRapport) :<br/>
	 * Retire une LigneRapportEnregistrement au rapport du contrôle.<br/>
	 * <br/>
	 * - retourne false si pLigneRapport == null.<br/>
	 * - retourne false si rapport == null.<br/>
	 * <br/>
	 *
	 * @param pLigneRapport : LigneRapportEnregistrement.<br/>
	 * 
	 * @return : boolean : true si la ligne de rapport
	 *  a été retirée du rapport.<br/>
	 */
	protected final boolean retirerLigneRapport(
			final LigneRapportEnregistrement pLigneRapport) {
		
		/* retourne false si pLigneRapport == null. */
		if (pLigneRapport == null) {
			return false;
		}
		
		/* retourne false si rapport == null. */
		if (this.rapport == null) {
			return false;
		}
		
		/* retrait de la ligne de rapport. */
		return this.rapport.remove(pLigneRapport);
		
	} // Fin de retirerLigneRapport(
	 // LigneRapportEnregistrement pLigneRapport)._________________________
	

	
	/**
	 * method creerLigneRapport(
	 * String pMessage
	 * , String pChemin
	 * , String pStatut) :<br/>
	 * Crée et retourne une ligne de rapport LigneRapportEnregistrement 
	 * avec des attributs pré-remplis et les valeurs passées en paramètre.<br/>
	 * <br/>
	 * Liste des attributs pré-remplis : <br/>
	 * - Met automatiquement this.dateEnregistrementStringFormatee 
	 * dans la date de l'enregistrement 'dateEnregistrement'.<br/>
	 * - Met automatiquement this.userName dans le nom 
	 * de l'utilisateur qui a déclenché l'enregistrement 'userName'.<br/>
	 * - Met automatiquement this.objet dans 'objet'.<br/>
	 * - Met automatiquement this.nomFichier dans le nom du fichier 
	 * enregistré 'nomFichier'.<br/>
	 * <br/>
	 *
	 * @param pMessage : String : message à l'attention de l'utilisateur 
	 * indiquant si le fichier a bien été enregistré.<br/>
	 * @param pChemin : String : chemin de création du fichier 
	 * enregistré sur le disque.<br/>
	 * @param pStatut : String : statut de la création du fichier 
	 * (OK si créé, KO sinon).<br/>
	 * 
	 * @return : LigneRapportEnregistrement :  .<br/>
	 */
	protected final LigneRapportEnregistrement creerLigneRapport(
			final String pMessage
				, final String pChemin
					, final String pStatut) {
		
		return new LigneRapportEnregistrement(
				this.dateEnregistrementStringFormatee
				, this.userName
				, this.objet
				, this.nomFichier
				, pMessage, pChemin, pStatut);
		
	} // Fin de creerLigneRapport(
	 // String pMessage
	 // , String pChemin
	 // , String pStatut)._________________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Date getDateEnregistrement() {
		return (Date) this.dateEnregistrement.clone();
	} // Fin de getDateEnregistrement().___________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setDateEnregistrement(
			final Date pDateEnregistrement) {
		
		if (pDateEnregistrement != null) {
			
			this.dateEnregistrement = (Date) pDateEnregistrement.clone();
			
			/* calcule automatiquement dateControleStringFormattee. */
			this.dateEnregistrementStringFormatee
				= this.fournirDateFormattee(this.dateEnregistrement);
			
		}
				
	} // Fin de setDateEnregistrement(
	 // Date pDateEnregistrement)._________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getDateEnregistrementStringFormatee() {
		return this.dateEnregistrementStringFormatee;
	} // Fin de getDateEnregistrementStringFormatee()._____________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getUserName() {
		return this.userName;
	} // Fin de getUserName()._____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setUserName(
			final String pUserName) {
		
		/* remplit userName avec pUserName si pUserName != null 
		 * ou 'Administrateur' sinon. */
		this.userName = this.fournirUserName(pUserName);
		
	} // Fin de setUserName(
	 // String pUserName)._________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getObjet() {
		return this.objet;
	} // Fin de getObjet().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setObjet(
			final String pObjet) {
		this.objet = pObjet;
	} // Fin de setObjet(
	 // String pObjet).____________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getFichier() {
		return this.fichier;
	} // Fin de getFichier().______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setFichier(
			final File pFichier) {
		
		this.fichier = pFichier;
		
		/* calcule automatiquement nomFichier. */
		this.nomFichier = this.fournirNomFichier(this.fichier);
		
	} // Fin de setFichier(
	 // File pFichier).____________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomFichier() {
		return this.nomFichier;
	} // Fin de getNomFichier().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<LigneRapportEnregistrement> getRapport() {
		return this.rapport;
	} // Fin de getRapport().______________________________________________



} // FIN DE LA CLASSE AbstractEnregistreurFichiers.--------------------------
