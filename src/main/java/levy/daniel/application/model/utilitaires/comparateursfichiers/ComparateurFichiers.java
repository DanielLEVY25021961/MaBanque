package levy.daniel.application.model.utilitaires.comparateursfichiers;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale.LocaleManager;
import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionImport;

/**
 * CLASSE ComparateurFichiers :<br/>
 * <p>Classe chargée de comparer 2 fichiers avec des méthodes 
 * qui retournent true si les 2 fichiers sont égaux.</p>
 * <p>génère un rapport de comparaison dans <code>rapportComparaison</code> 
 * si les deux fichiers ne sont pas égaux ou sont incorrects 
 * (null, inexistant, ou répertoire).</p>
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
 * @author dan Lévy
 * @version 1.0
 * @since 6 juin 2019
 *
 */
public final class ComparateurFichiers {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe ComparateurFichiers".
	 */
	public static final String CLASSE_COMPARATEURS_FICHIERS 
		= "Classe ComparateurFichiers";
	
	/**
	 * "Méthode compareFichiersLigneALigne(
	 * File pFile1, Charset pCharset1, File pFile2, Charset pCharset2".
	 */
	public static final String METHODE_COMPARE_FICHIERS_LIGNE_A_LIGNE 
		= "Méthode compareFichiersLigneALigne(File pFile1, Charset pCharset1"
				+ ", File pFile2, Charset pCharset2";
	
	//*****************************************************************/
	//**************************** SEPARATEURS ************************/
	//*****************************************************************/
	/**
	 * Séparateur point virgule pour les CSV.<br/>
	 * ";"
	 */
	public static final String SEP_PV = ";";
    
	/**
	 * " - ".<br/>
	 */
	public static final String SEPARATEUR_MOINS_AERE = " - ";
		
	/**
	 * "_".<br/>
	 */
	public static final String UNDERSCORE = "_";
	
	/**
	 * "."
	 */
	public static final String POINT = ".";
	
	/**
	 * Tabulation "\t".<br/>
	 */
	public static final String TAB = "\t";
	
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/

	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	public static final String NEWLINE = System.getProperty("line.separator");
	
	//*****************************************************************/
	//**************************** BOM_UTF-8 **************************/
	//*****************************************************************/
	/**
	 * '\uFEFF'<br/>
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	public static final char BOM_UTF_8 = '\uFEFF';

	/**
	 * rapport de comparaison non null lorsque les 2 fichiers 
	 * comparés ne sont pas égaux.<br/>
	 * instancié à chaque appel de la méthode 
	 * compareFichiersLigneALigne(...).<br/>
	 */
	private static String rapportComparaison;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(ComparateurFichiers.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation.<br/>
	 */
	private ComparateurFichiers() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * détermine si deux fichiers <strong>textuels</strong> 
	 * sont absolument identiques <i>en lisant leur contenu ligne par ligne</i> 
	 * et retourne true si c'est le cas.<br/>
	 * lit le contenu de chaque fichier avec le Charset pCharset correspondant.<br/>
	 * <i>- ne prend pas en compte les noms des fichiers pFile1 et pFile2</i> 
	 * lors de la comparaison. Un fichier file2 = file1_renommé 
	 * sera donc détecté comme ayant le même contenu 
	 * ligne par ligne que file1.<br/>
	 * - compare les poids des 2 fichiers et émet un message 
	 * dans <code>rapportComparaison</code> si les poids sont différents. 
	 * Ne sort pas de la méthode pour permettre les comparaisons suivantes.<br/>
	 * - détecte et rapporte dans <code>rapportComparaison</code> 
	 * si 2 fichiers textuels n'ont PAS le même nombre de lignes. 
	 * retourne false.<br/>
	 * - détecte et rapporte dans <code>rapportComparaison</code> 
	 * si 2 lignes de même numéro d'ordre de 2 fichiers 
	 * - ayant le même nombre de lignes - 
	 * ont une longueur différente. retourne false.<br/>
	 * <pre>les 2 fichiers n'ont pas le même poids - 
	 * taille du fichier 1 : 230202 bytes - taille du fichier 2 : 230201 bytes.
	 * La ligne 1 a une longueur de 520 caractères dans le 1er fichier 
	 * et une longueur de 519 caractères dans le 2ème fichier.</pre>
	 * - détecte et rapporte dans <code>rapportComparaison</code> 
	 * si 2 lignes de même numéro d'ordre de 2 fichiers 
	 * - ayant le même nombre de lignes - 
	 * ne sont pas equals(). retourne false.<br/>
	 * <br/>
	 * <ul>
	 * <li>rafraîchit le rapport de comparaison <code>rapportComparaison</code> 
	 * à chaque appel.</li>
	 * <li>utilise <code>Files.readAllLines(pFile.toPath(), charset);</code> 
	 * pour lire les lignes de chaque fichier.<br/>
	 * Peut donc jeter une MalformedInputException si le 
	 * charset ne correspond pas au Charset d'encodage d'un fichier.</li>
	 * <ul>
	 * <li>passe automatiquement charset1 à UTF-8 si pCharset1 == null.</li>
	 * <li>passe automatiquement charset2 à UTF-8 si pCharset2 == null.</li>
	 * </ul>
	 * <li>retourne false et rapporte dans <code>rapportComparaison</code> 
	 * si les deux fichiers n'ont pas 
	 * le même nombre de lignes.</li>
	 * <li>retourne false et rapporte dans <code>rapportComparaison</code> 
	 * si 2 lignes de même numéro d'ordre n'ont pas la 
	 * même longueur dans les deux fichiers.</li>
	 * <li>retourne false et rapporte dans <code>rapportComparaison</code> 
	 * si 2 lignes de même numéro d'ordre 
	 * ne sont pas égales dans les deux fichiers.</li>
	 * <ul>
	 * <li>retourne systématiquement false si pFile1 == null ou pFile2 == null 
	 * et rapporte dans <code>rapportComparaison</code>.</li>
	 * <li>retourne systématiquement false si pFile2 est inexistant 
	 * ou pFile2 est inexistant 
	 * et rapporte dans <code>rapportComparaison</code>.</li>
	 * <li>retourne systématiquement false si pFile2 est un répertoire 
	 * ou pFile2 est un répertoire 
	 * et rapporte dans <code>rapportComparaison</code>.</li>
	 * <li>retourne systématiquement true et ne rapporte pas 
	 * si pFile1 == pFile2 (même instance).</li>
	 * <li>retourne systématiquement true et ne rapporte pas 
	 * si pFile1 equals pFile2 (même absolutePath).</li>
	 * </ul>
	 * </ul>
	 *
	 * @param pFile1 : File : 1er fichier à comparer.
	 * @param pCharset1 : Charset : Charset d'encodage du 1er fichier.
	 * @param pFile2 : File : 2ème fichier à comparer.
	 * @param pCharset2 : Charset : Charset d'encodage du 2ème fichier.
	 * 
	 * @return : boolean : 
	 * true si les deux fichiers textuels sont absolument identiques.<br/>
	 * 
	 * @throws Exception 
	 */
	public static boolean compareFichiersLigneALigne(
			final File pFile1, final Charset pCharset1
				, final File pFile2, final Charset pCharset2) throws Exception {
		
		synchronized (ComparateurFichiers.class) {
			
			/* rafraîchit le rapport de comparaison à chaque appel. */
			rapportComparaison = null;
			
			// NULLITE
			if (!traiterNullite(pFile1, pFile2)) {
				return false;
			}

			// INEXISTANT
			if (!traiterInexistant(pFile1, pFile2)) {
				return false;
			}
			
			// REPERTOIRE
			if (!traiterRepertoire(pFile1, pFile2)) {
				return false;
			}
			
			/* retourne systématiquement true et ne rapporte pas 
			 * si pFile1 == pFile2 (même instance). */
			if (pFile1 == pFile2) {
				return true;
			}
			
			/* retourne systématiquement true et ne rapporte pas 
			 * si pFile1 equals pFile2 (même absolute path). */
			if (pFile1.equals(pFile2)) {
				return true;
			}
			
			final StringBuffer stb = new StringBuffer();
			
			boolean ok = true;
			
			/* compare les poids des 2 fichiers et émet un message 
			 * dans rapportComparaison si les poids sont différents. 
			 * Ne sort pas de la méthode pour permettre 
			 * les comparaisons suivantes. */
			if (pFile1.length() != pFile2.length()) {
				
				final String message 
				= "les 2 fichiers n'ont pas le même poids - taille du fichier 1 : " 
						+ pFile1.length() + " bytes"
						+ " - taille du fichier 2 : " 
						+ pFile2.length() + " bytes";
				
				stb.append(message);
				stb.append(NEWLINE);
				ok = false;
			}
			
			/* passe automatiquement charset1 à UTF-8 si pCharset1 == null. */
			Charset charset1 = null;
			
			if (pCharset1 == null) {
				charset1 = StandardCharsets.UTF_8;
			}
			else {
				charset1 = pCharset1;
			}
			
			/* passe automatiquement charset2 à UTF-8 si pCharset2 == null. */
			Charset charset2 = null;
			
			if (pCharset2 == null) {
				charset2 = StandardCharsets.UTF_8;
			}
			else {
				charset2 = pCharset2;
			}

			List<String> liste1 = null;
			List<String> liste2 = null;
			
			try {
				liste1 = Files.readAllLines(pFile1.toPath(), charset1);
			} catch (Exception e1) {
				
				final String message 
				= CLASSE_COMPARATEURS_FICHIERS 
				+ SEPARATEUR_MOINS_AERE 
				+ METHODE_COMPARE_FICHIERS_LIGNE_A_LIGNE 
				+ SEPARATEUR_MOINS_AERE 
				+ "Il est impossible de lire le fichier : " 
				+ pFile1.getAbsolutePath() 
				+ " avec le Charset : " 
				+ charset1.displayName(LocaleManager.getLocaleApplication());
			
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message, e1);
				}
				
				throw new ExceptionImport(message, e1);
				
			}
			
			try {
				liste2 = Files.readAllLines(pFile2.toPath(), charset2);
			} catch (Exception e2) {
				
				final String message 
				= CLASSE_COMPARATEURS_FICHIERS 
				+ SEPARATEUR_MOINS_AERE 
				+ METHODE_COMPARE_FICHIERS_LIGNE_A_LIGNE 
				+ SEPARATEUR_MOINS_AERE 
				+ "Il est impossible de lire le fichier : " 
				+ pFile2.getAbsolutePath() 
				+ " avec le Charset : " 
				+ charset2.displayName(LocaleManager.getLocaleApplication());
			
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message, e2);
				}
				
				throw new ExceptionImport(message, e2);
				
			}
			
			/* retourne false si les deux fichiers n'ont pas 
			 * le même nombre de lignes. */
			if (liste1.size() != liste2.size()) {
				
				final String message 
					= "Les 2 fichiers n'ont pas le même nombre de lignes : " 
							+ liste1.size() 
							+ " lignes pour le 1er fichier et " 
							+ liste2.size() 
							+ " lignes pour le 2ème fichier";
				
				stb.append(message);
				
				rapportComparaison = stb.toString();

				return false;
			}
			
			/* COMPARAISON LIGNE A LIGNE. */
			final int nombreDeLignes = liste1.size();
			String ligne1 = null;
			String ligne2 = null;
			
			for (int i = 0; i < nombreDeLignes; i++) {
				
				ligne1 = liste1.get(i);
				ligne2 = liste2.get(i);
				
				if (ligne1 != null) {
					
					if (ligne2 == null) {
						
						final String message 
						= "La ligne " 
						+ (i + 1) 
						+ " est nulle dans le 2ème fichier et "
						+ "pas dans le 1er fichier";

						stb.append(message);
						
						rapportComparaison = stb.toString();
						
						return false;
					}
					
					/* retourne false si 2 lignes de même numéro d'ordre 
					 * n'ont pas la même longueur dans les deux fichiers. */
					if (ligne1.length() != ligne2.length()) {
											
						final String message 
						= "La ligne " 
						+ (i + 1) 
						+ " a une longueur de " 
						+ ligne1.length() 
						+ " caractères dans le 1er fichier et "
						+ "une longueur de " 
						+ ligne2.length() 
						+ " caractères dans le 2ème fichier.";

						stb.append(message);
						
						rapportComparaison = stb.toString();
						
						return false;
					}
					
					/* retourne false si 2 lignes de même numéro d'ordre 
					 * ne sont pas égales dans les deux fichiers. */
					if (!StringUtils.equals(ligne1, ligne2)) {
																	
						final String message 
						= "La ligne " 
						+ (i + 1) 
						+ " dans le 1er fichier n'est pas égale à la ligne "
						+ "correspondante dans le deuxième fichier : " 
						+ ligne1 
						+ NEWLINE
						+ ligne2;
					
						stb.append(message);
						
						rapportComparaison = stb.toString();

						return false;
					}
		
				} else {
					
					if (ligne2 != null) {
						
						final String message 
						= "La ligne " 
						+ (i + 1) 
						+ " est nulle dans le 1er fichier et "
						+ "pas dans le 2ème fichier";

						stb.append(message);
						
						rapportComparaison = stb.toString();

						return false;
					}
					
				}
				
			} // Fin de la boucle sur les lignes.______________
			
			if (!ok) {
				rapportComparaison = stb.toString();
				return false;
			}
			
			return true;
			
		} // Fin de synchronized.___________________________
		
	} // Fin de compareFichiersLigneALigne(...).___________________________


	
	/**
	 * compare le contenu de 2 fichiers <strong>bit à bit</strong> 
	 * et retourne true si les contenus sont égaux.<br/>
	 * <i>- ne prend pas en compte les noms des fichiers pFile1 et pFile2</i> 
	 * lors de la comparaison. Un fichier file2 = file1_renommé 
	 * sera donc détecté comme ayant le même contenu bit à bit que file1.<br/>
	 * - utilise 
	 * <code>org.apache.commons.io.FileUtils.contentEquals(pFile1, pFile2)</code>
	 * pour comparer les contenus des 2 fichiers bit à bit.<br/>
	 * - retourne un rapport de comparaison <code>rapportComparaison</code> 
	 * si les deux fichiers diffèrent 
	 * ou ne sont pas corrects (null, inexistant, ou repertoire).
	 * <ul>
	 * <li>rafraîchit le rapport de comparaison <code>rapportComparaison</code> 
	 * à chaque appel.</li>
	 * <li>retourne systématiquement false si pFile1 == null ou pFile2 == null 
	 * et rapporte dans <code>rapportComparaison</code>.</li>
	 * <li>retourne systématiquement false si pFile2 est inexistant 
	 * ou pFile2 est inexistant 
	 * et rapporte dans <code>rapportComparaison</code>.</li>
	 * <li>retourne systématiquement false si pFile2 est un répertoire 
	 * ou pFile2 est un répertoire 
	 * et rapporte dans <code>rapportComparaison</code>.</li>
	 * <li>retourne systématiquement true et ne rapporte pas 
	 * si pFile1 == pFile2 (même instance).</li>
	 * <li>retourne true et ne rapporte pas 
	 * si pFile1 a le même contenu que pFile2.</li>
	 * <li>retourne false et ne rapporte pas 
	 * si pFile1 n'a PAS le même contenu que pFile2.</li>
	 * <li>retourne systématiquement true et ne rapporte pas 
	 * si pFile1 equals pFile2 (même absolutePath).</li>
	 * </ul>
	 *
	 * @param pFile1 : File : le premier fichier à comparer.
	 * @param pFile2 : File : le deuxième fichier à comparer.
	 * 
	 * @return boolean : true si les fichiers ont le même contenu bit à bit.
	 * 
	 * @throws Exception
	 */
	public static boolean compareContenuFichiers(
			final File pFile1
				, final File pFile2) throws Exception {
		
		synchronized (ComparateurFichiers.class) {
			
			/* rafraîchit le rapport de comparaison à chaque appel. */
			rapportComparaison = null;
			
			// NULLITE
			if (!traiterNullite(pFile1, pFile2)) {
				return false;
			}

			// INEXISTANT
			if (!traiterInexistant(pFile1, pFile2)) {
				return false;
			}
			
			// REPERTOIRE
			if (!traiterRepertoire(pFile1, pFile2)) {
				return false;
			}
			
			/* retourne systématiquement true et ne rapporte pas 
			 * si pFile1 == pFile2 (même instance). */
			if (pFile1 == pFile2) {
				return true;
			}
			
			/* retourne systématiquement true et ne rapporte pas 
			 * si pFile1 equals pFile2 (même absolute path). */
			if (pFile1.equals(pFile2)) {
				return true;
			}
			
			return FileUtils.contentEquals(pFile1, pFile2);
			
		} // Fin de synchronized.___________________________
				
	} // Fin de compareContenuFichiers(...)._______________________________

	
	
	/**
	 * contrôle qu'aucun des fichiers pFile1 et pFile2 n'est null.<br/>
	 * retourne true et ne rapporte pas dans <code>rapportComparaison</code> 
	 * si aucun des fichiers n'est null.
	 * <ul>
	 * <li>retourne false si pFile1 == null 
	 * et rapporte dans <code>rapportComparaison</code>.</li>
	 * <li>retourne false si pFile2 == null 
	 * et rapporte dans <code>rapportComparaison</code>.</li>
	 * </ul>
	 *
	 * @param pFile1 : File : le premier fichier à comparer.
	 * @param pFile2 : File : le deuxième fichier à comparer.
	 * 
	 * @return : boolean : false si l'un des fichiers est null.<br/>
	 */
	private static boolean traiterNullite(
			final File pFile1, final File pFile2) {
		
		/* retourne systématiquement false si pFile1 == null 
		 * et rapporte dans rapportComparaison. */
		if (pFile1 == null) {
			
			rapportComparaison 
				= "le premier fichier passé en paramètre est null."
						+ " COMPARAISON DE CONTENU IMPOSSIBLE.";
			
			return false;
		}
		
		/* retourne systématiquement false si pFile2 == null 
		 * et rapporte dans rapportComparaison. */
		if (pFile2 == null) {
			
			rapportComparaison 
				= "le deuxième fichier passé en paramètre est null."
						+ " COMPARAISON DE CONTENU IMPOSSIBLE.";
			
			return false;
		}
		
		return true;

	} // Fin de traiterNullite(...)._______________________________________

	
	
	/**
	 * contrôle qu'aucun des fichiers pFile1 et pFile2 n'est inexistant.<br/>
	 * retourne true et ne rapporte pas dans <code>rapportComparaison</code> 
	 * si aucun des fichiers n'est inexistant.
	 * <ul>
	 * <li>retourne false si pFile1 est inexistant 
	 * et rapporte dans <code>rapportComparaison</code>.</li>
	 * <li>retourne false si pFile2 est inexistant 
	 * et rapporte dans <code>rapportComparaison</code>.</li>
	 * </ul>
	 *
	 * @param pFile1 : File : le premier fichier à comparer.
	 * @param pFile2 : File : le deuxième fichier à comparer.
	 * 
	 * @return : boolean : false si l'un des fichiers est inexistant.<br/>
	 */
	private static boolean traiterInexistant(
			final File pFile1, final File pFile2) {
		
		/* retourne systématiquement false si pFile1 est inexistant 
		 * et rapporte dans rapportComparaison. */
		if (!pFile1.exists()) {
			
			rapportComparaison 
				= "le premier fichier passé en paramètre est inexistant : " 
						+ pFile1.getAbsolutePath()
						+ " . COMPARAISON DE CONTENU IMPOSSIBLE.";
			
			return false;
		}
		
		/* retourne systématiquement false si pFile2 est inexistant 
		 * et rapporte dans rapportComparaison. */
		if (!pFile2.exists()) {
			
			rapportComparaison 
				= "le deuxième fichier passé en paramètre est inexistant : "
						+ pFile2.getAbsolutePath()
						+ " . COMPARAISON DE CONTENU IMPOSSIBLE.";
			
			return false;
		}
		
		return true;

	} // Fin de traiterInexistant(...).____________________________________

	
	
	/**
	 * contrôle qu'aucun des fichiers pFile1 et pFile2 
	 * n'est un répertoire.<br/>
	 * retourne true et ne rapporte pas dans <code>rapportComparaison</code> 
	 * si aucun des fichiers n'est un répertoire.
	 * <ul>
	 * <li>retourne false si pFile1 est un répertoire 
	 * et rapporte dans <code>rapportComparaison</code>.</li>
	 * <li>retourne false si pFile2 est un répertoire 
	 * et rapporte dans <code>rapportComparaison</code>.</li>
	 * </ul>
	 *
	 * @param pFile1 : File : le premier fichier à comparer.
	 * @param pFile2 : File : le deuxième fichier à comparer.
	 * 
	 * @return : boolean : false si l'un des fichiers est un répertoire.<br/>
	 */
	private static boolean traiterRepertoire(
			final File pFile1, final File pFile2) {
		
		/* retourne systématiquement false si pFile1 est un répertoire 
		 * et rapporte dans rapportComparaison. */
		if (pFile1.isDirectory()) {
			
			rapportComparaison 
				= "le premier fichier passé en paramètre est un répertoire : " 
						+ pFile1.getAbsolutePath()
						+ " . COMPARAISON DE CONTENU IMPOSSIBLE.";
			
			return false;
		}
		
		/* retourne systématiquement false si pFile2 est un répertoire 
		 * et rapporte dans rapportComparaison. */
		if (pFile2.isDirectory()) {
			
			rapportComparaison 
				= "le deuxième fichier passé en paramètre est un répertoire : "
						+ pFile2.getAbsolutePath()
						+ " . COMPARAISON DE CONTENU IMPOSSIBLE.";
			
			return false;
		}
		
		return true;

	} // Fin de traiterRepertoire(...).____________________________________


	
	/**
	 * Getter du rapport de comparaison non null lorsque les 2 fichiers 
	 * comparés ne sont pas égaux.<br/>
	 * instancié à chaque appel des méthodes 
	 * compareFichiersLigneALigne(...) ...<br/>
	 *
	 * @return this.rapportComparaison : String.<br/>
	 */
	public static String getRapportComparaison() {
		return rapportComparaison;
	} // Fin de getRapportComparaison().___________________________________
	
	 
	
} // FIN DE LA CLASSE ComparateurFichiers.-----------------------------------
