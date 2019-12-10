package levy.daniel.application.model.services.utilitaires.generateurpomxml.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.utilitaires.generateurpomxml.IGenerateurPOMTemplateService;
import levy.daniel.application.model.services.utilitaires.gestionnairestemplates.IGestionnaireTemplatesService;
import levy.daniel.application.model.services.utilitaires.gestionnairestemplates.impl.GestionnaireTemplatesService;
import levy.daniel.application.model.services.utilitaires.managerpaths.ManagerPaths;

/**
 * CLASSE GenerateurPOMTemplateService :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * System.getProperty("line.separator").<br/>
 * rapport textuel, rapport csv, ecrire dans fichier,<br/>
 * écrire dans fichier, ecrire String dans File, écrire String dans File,<br/>
 * Ecrire String dans File,<br/>
 * ecriture sur disque avec encodage, Charset, ecrireStringDansFile()<br/>
 * FileOutputStream, outputStreamWriter, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 7 déc. 2018
 *
 */
public class GenerateurPOMTemplateService 
					implements IGenerateurPOMTemplateService {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe GenerateurPOMTemplateService".
	 */
	public static final String CLASSE_GENERATEUR_POM 
		= "Classe GenerateurPOMTemplateService";
	
	/**
	 * SERVICE pour la gestion des templates.<br/>
	 */
	private final transient IGestionnaireTemplatesService templatesService 
		= new GestionnaireTemplatesService();
	
	/**
	 * Variables du template pour le POM.<br/>
	 * <code>{"${groupId}", "${nomProjet}"
	 * , "${version}", "${packaging}"}</code>
	 */
	public static final String[] VARIABLES_A_SUBSTITUER 
			= new String[] 
				{"${groupId}", "${nomProjet}", "${version}", "${packaging}"};
	
	/**
	 * Charset.forName("UTF-8").<br/>
	 * Eight-bit Unicode (or UCS) Transformation Format.<br/> 
	 */
	public static final Charset CHARSET_UTF8 
		= Charset.forName("UTF-8");
		
	/**
	 * "méthode ecrireStringDansFile(
	 * File pFile, String pString, Charset pCharset)".<br/>
	 */
	public static final String METHODE_ECRIRESTRINGDANSFILE 
		= "méthode ecrireStringDansFile(File pFile, String pString, Charset pCharset)";
	
	/**
	 * "Le fichier passé en paramètre est null".<br/>
	 */
	public static final String MESSAGE_FICHIER_NULL 
		= "Le fichier passé en paramètre est null";
		
	/**
	 * "Le fichier passé en paramètre est inexistant : "
	 */
	public static final String MESSAGE_FICHIER_INEXISTANT 
		= "Le fichier passé en paramètre est inexistant : ";
	
	/**
	 * "Le fichier passé en paramètre est un répertoire : ".<br/>
	 */
	public static final String MESSAGE_FICHIER_REPERTOIRE 
		= "Le fichier passé en paramètre est un répertoire : ";

	/**
	 * "La chaine de caractères passée en paramètre est blank (null ou vide) : "
	 */
	public static final String MESSAGE_STRING_BLANK 
	= "La chaine de caractères passée en paramètre est blank (null ou vide) : ";
	
	/**
	 * "Exception GRAVE : ".<br/>
	 */
	public static final String MESSAGE_EXCEPTION = "Exception GRAVE : ";
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/
	// définis dans IConstantesSautsLigne	
	
	/**
	 * '_'.<br/>
	 */
	public static final char UNDERSCORE = '_';
		
	/**
	 * " - ".<br/>
	 */
	public static final String SEP_MOINS = " - ";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(GenerateurPOMTemplateService.class);
	

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public GenerateurPOMTemplateService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String genererPOMStringAPartirTemplate(
			final Path pPathAbsoluTemplate
				, final String... pSubstituants) 
									throws Exception {
				
		/* retourne null si pPathAbsoluTemplate == null. */
		if (pPathAbsoluTemplate == null) {
			return null;
		}
		
		final File fileTemplate = pPathAbsoluTemplate.toFile();
		
		/* retourne null si le template situé à 
		 * pPathAbsoluTemplate n'existe pas. */
		if (!fileTemplate.exists()) {
			return null;
		}
		
		/* retourne null si le File situé à pPathAbsoluTemplate 
		 * n'est pas un fichier simple. */
		if (!fileTemplate.isFile()) {
			return null;
		}
		
		/* retourne null si pSubstituants == null. */
		if (pSubstituants == null) {
			return null;
		}
		
		String cheminRelatifTemplate = null;
		String pomString = null;
		
		/* extrait le path relatif*/
		final Path pathRelatifTemplate 
			= ManagerPaths
				.getPathAbsoluSrcMainResourcesPresentProjet()
					.relativize(pPathAbsoluTemplate)
						.normalize();
		
				
		if (pathRelatifTemplate != null) {

			cheminRelatifTemplate = pathRelatifTemplate.toString();

			// SUBSTITUE LES VARIABLES CONTENUES DANS LE TEMPLATE 
			// ET RETOURNE UNE STRING.
			pomString = 
					this.templatesService
					.fournirTemplateSubstitueSousFormeString(
							cheminRelatifTemplate
								, VARIABLES_A_SUBSTITUER
									, pSubstituants);
			
			return pomString;
			
		}
		
		/* retourne null si pPathAbsoluTemplate n'est pas 
		 * sous src/main/resources du présent projet. */
		return null;
		
	} //Fin de genererPOMStringAPartirTemplate(...)._______________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public File genererPOMAPartirTemplate(
			final Path pPathAbsoluTemplate
				, final Path pProjetCiblePath
					, final String... pSubstituants) 
							throws Exception {
		
		/* retourne null si pPathAbsoluTemplate == null. */
		if (pPathAbsoluTemplate == null) {
			return null;
		}
		
		final File fileTemplate = pPathAbsoluTemplate.toFile();
		
		/* retourne null si le template situé à 
		 * pPathAbsoluTemplate n'existe pas. */
		if (!fileTemplate.exists()) {
			return null;
		}
		
		/* retourne null si le File situé à pPathAbsoluTemplate 
		 * n'est pas un fichier simple. */
		if (!fileTemplate.isFile()) {
			return null;
		}
		
		/* retourne null si pProjetCiblePath == null. */
		if (pProjetCiblePath == null) {
			return null;
		}
		
		final File fileProjetCible = pProjetCiblePath.toFile();
		
		/* retourne null si le projet cible situé 
		 * à pProjetCiblePath n'existe pas. */
		if (!fileProjetCible.exists()) {
			return null;
		}

		/* retourne null si le projet cible situé 
		 * à pProjetCiblePath n'est pas un répertoire. */
		if (!fileProjetCible.isDirectory()) {
			return null;
		}
		
		/* retourne null si pSubstituants == null. */
		if (pSubstituants == null) {
			return null;
		}
		
		/* génère le POM.xml sous forme de String. */
		final String stringAEcrire 
			= this.genererPOMStringAPartirTemplate(
					pPathAbsoluTemplate, pSubstituants);
		
		final Path pomAGenererPath = pProjetCiblePath.resolve("pom.xml");
		
		final File pomAGenererFile = pomAGenererPath.toFile();
		
		/* crée le fichier pom.xml dansle projet cible si il n'existe pas. */
		if (!pomAGenererFile.exists()) {
			Files.createFile(pomAGenererPath);
		}
		
		/* écrit la String dans le fichier pom.xml sous le projet cible. */
		this.ecrireStringDansFile(
				pomAGenererFile, stringAEcrire, null, null);
		
		return pomAGenererFile;
		
	} // Fin de genererPOMAPartirTemplate(...).____________________________
	
	
	
	/**
	 * <b>Ecrit la String pString dans 
	 * le File pFile avec un encodage pCharset</b>.
	 * <ul>
	 * <li>écrit systématiquement la String 
	 * <b>au début du fichier pFile (pas de append 
	 * dans le FileOutputStream)</b>.</li>
	 * <li>Substitue automatiquement pSautLigne aux sauts de ligne 
	 * dans pString si nécessaire.</li>
	 * <li>Utilise <code>FileOutputStream(File, false)</code>, 
	 * <code>new OutputStreamWriter(fileOutputStream, charset)</code> 
	 * et <code>BufferedWriter</code> pour écrire.</li>
	 * <li>Ecriture dans un fichier, écriture sur disque.</li>
	 * <ul>
	 * <li>Passe automatiquement le Charset à CHARSET_UTF8 
	 * si pCharset est null.</li>
	 * <li>Passe automatiquement le saut de ligne à NEWLINE 
	 * (saut de ligne de la plateforme) si pSautLigne est blank.</li>
	 * </ul>
	 * </ul>
	 * - retourne null si le pFile est null.<br/>
	 * - retourne null si le pFile est inexistant.<br/>
	 * - retourne null si le pFile est un répertoire.<br/>
	 * - retourne null en cas d'Exception loggée 
	 * (FileNotFoundException, IOException).<br/>
	 * - retourne null si pString est blank.<br/>
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
	private File ecrireStringDansFile(
			final File pFile
				, final String pString
					, final Charset pCharset
			, final String pSautLigne) {

		/* retourne null si le pFile est null. */
		if (pFile == null) {

			/* LOG de niveau INFO. */
			loggerInfo(CLASSE_GENERATEUR_POM
					, METHODE_ECRIRESTRINGDANSFILE
					, MESSAGE_FICHIER_NULL);

			/* retour de null. */
			return null;
		}

		/* retourne null si le pFile est inexistant. */
		if (!pFile.exists()) {

			/* LOG de niveau INFO. */
			loggerInfo(CLASSE_GENERATEUR_POM
					, METHODE_ECRIRESTRINGDANSFILE
					, MESSAGE_FICHIER_INEXISTANT,
					pFile.getAbsolutePath());

			/* retour de null. */
			return null;
		}

		/* retourne null si le pFile est un répertoire. */
		if (pFile.isDirectory()) {

			/* LOG de niveau INFO. */
			loggerInfo(CLASSE_GENERATEUR_POM
					, METHODE_ECRIRESTRINGDANSFILE
					, MESSAGE_FICHIER_REPERTOIRE,
					pFile.getAbsolutePath());

			/* retour de null. */
			return null;
		}

		/* retourne null si pString est blank. */
		if (StringUtils.isBlank(pString)) {

			/* LOG de niveau INFO. */
			loggerInfo(CLASSE_GENERATEUR_POM
					, METHODE_ECRIRESTRINGDANSFILE
					, MESSAGE_STRING_BLANK
					, pString);

			return null;
		}

		Charset charset = null;

		/* Passe automatiquement le charset à UTF-8 
		 * si pCharset est null. */
		if (pCharset == null) {
			charset = CHARSET_UTF8;
		} else {
			charset = pCharset;
		}

		String sautLigne = null;

		/*
		 * Passe automatiquement le saut de ligne
		 *  à NEWLINE (saut de ligne de la
		 * plateforme) si pSautLigne est blank.
		 */
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
			fileOutputStream = new FileOutputStream(pFile, false);

			/*
			 * Ouverture d'un OutputStreamWriter 
			 * sur le FileOutputStream en lui passant le
			 * Charset.
			 */
			outputStreamWriter 
				= new OutputStreamWriter(fileOutputStream, charset);

			/*
			 * Ouverture d'un tampon d'écriture BufferedWriter 
			 * sur le OutputStreamWriter.
			 */
			bufferedWriter = new BufferedWriter(outputStreamWriter);

			// ECRITURE.
			/*
			 * Substitue automatiquement sautLigne aux sauts 
			 * de ligne dans pString si
			 * nécessaire.
			 */
			final String stringAEcrire 
				= this.substituerSautLigne(pString, sautLigne);
			
			if (stringAEcrire != null) {
				bufferedWriter.write(stringAEcrire);
				bufferedWriter.flush();
			} else {
				if (LOG.isFatalEnabled()) {
					LOG.fatal("La chaine à écrire dans le fichier est null");
				}
			}
			
			// Retour du fichier.
			return pFile;

		} catch (FileNotFoundException fnfe) {

			/* LOG de niveau ERROR. */
			loggerError(CLASSE_GENERATEUR_POM
					, MESSAGE_EXCEPTION
					, fnfe);

			/* retour de null. */
			return null;

		} catch (IOException ioe) {

			/* LOG de niveau ERROR. */
			loggerError(CLASSE_GENERATEUR_POM
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
					loggerError(CLASSE_GENERATEUR_POM
							, MESSAGE_EXCEPTION
							, ioe1);
				}
			} // Fin de if (bufferedWriter != null).__________

			if (outputStreamWriter != null) {
				try {

					outputStreamWriter.close();

				} catch (IOException ioe2) {

					/* LOG de niveau ERROR. */
					loggerError(CLASSE_GENERATEUR_POM
							, MESSAGE_EXCEPTION
							, ioe2);
				}
			} // Fin de if (outputStreamWriter != null).______

			if (fileOutputStream != null) {
				try {

					fileOutputStream.close();

				} catch (IOException ioe3) {

					// * LOG de niveau ERROR. */
					loggerError(CLASSE_GENERATEUR_POM
							, MESSAGE_EXCEPTION
							, ioe3);
				}
			}

		} // Fin du finally.____________________________

	} // Fin de ecrireStringDansFile(...)._________________________________
	

	
	/**
	 * <b>Substitue les sauts de ligne dans pString 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * par les sauts de ligne de la plate-forme
	 * sur laquelle le programme s'exécute</b>.<br/>
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
	private String substituerSautLignePlateforme(
			final String pString) {
		
		return substituerSautLigne(pString, NEWLINE);
		
	} // Fin de method substituerSautLignePlateforme(...)._________________
	

	
	/**
	 * <b>Substitue les sauts de ligne dans pString 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * par les sauts de ligne pSautLigne</b>.<br/>
	 * <br/>
	 * - retourne null si pString est blank (null ou vide).<br/>
	 * - retourne null si pSautLigne est null.<br/>
	 * <br/>
	 *
	 * @param pString : String : String à corriger.<br/>
	 * @param pSautLigne : String : saut de ligne à substituer.<br/>
	 * 
	 * @return : String : La String dans laquelle les sauts de ligne 
	 * (\r\n pour DOS/Windows, \r pour Mac, \n pour Unix) 
	 * ont été substitués par les sauts de ligne pSautLigne.<br/>
	 */
	private String substituerSautLigne(
			final String pString, final String pSautLigne) {

		/* retourne null si pString est blank (null ou vide). */
		if (StringUtils.isBlank(pString)) {
			return null;
		}

		/* retourne null si pSautLigne est null. */
		if (pSautLigne == null) {
			return null;
		}

		/* Recherche des sauts de ligne DOS/Windows. */
		if (StringUtils.contains(pString, SAUTDELIGNE_DOS_WINDOWS)) {

			if (!pSautLigne.equals(SAUTDELIGNE_DOS_WINDOWS)) {
				
				final String resultat 
				= StringUtils.replace(
					pString, SAUTDELIGNE_DOS_WINDOWS, pSautLigne);

				return resultat;
				
			} 
				
			return pString;			
			
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

		/*
		 * Retourne la chaîne inchangée si 
		 * il n'y avait pas de saut de ligne.
		 */
		return pString;

	} // Fin de substituerSautLigne(...).__________________________________
	
	

	/**
	 * <b>Créée un message de niveau INFO dans le LOG</b>.<br/>
	 * <br/>
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
		
	} // Fin de la classe loggerInfo(...)._________________________________
	

	
	/**
	 * <b>Créée un message de niveau INFO dans le LOG</b>.<br/>
	 * <br/>
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
		
	} // Fin de loggerInfo(...).___________________________________________
	
	
	
	/**
	 * <b>Créée un message de niveau ERROR dans le LOG</b>.<br/>
	 * <br/>
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
		
	} // Fin de loggerError(...).__________________________________________
	


} // FIN DE LA CLASSE GenerateurPOMTemplateService.--------------------------
