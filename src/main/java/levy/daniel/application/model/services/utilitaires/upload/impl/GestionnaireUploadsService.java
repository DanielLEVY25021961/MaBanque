package levy.daniel.application.model.services.utilitaires.upload.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.IConstantesSautsLigne;
import levy.daniel.application.IConstantesSeparateurs;
import levy.daniel.application.apptechnic.exceptions.technical.impl.ExceptionTechnique;

/**
 * CLASSE GestionnaireUploadsService :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * lister tous fichiers simples sous répertoire.<br/>
 * lister tous fichiers simples sous répertoire et sous arborescence.<br/>
 * afficher lists strings, affichier Liste String,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 2 nov. 2019
 *
 */
public class GestionnaireUploadsService {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe GestionnaireUploadsService".
	 */
	public static final String CLASSE_GESTIONNAIRE_UPLOADS_SERVICE 
		= "Classe GestionnaireUploadsService";

	/**
	 * "Méthode recupererRootUploadsDansProperties()".
	 */
	public static final String METHODE_RECUPERER_ROOT_UPLOADS 
		= "Méthode recupererRootUploadsDansProperties()";
	
	/**
	 * "méthode recupererListeFichiersSousRepertoire(Path pPath)".
	 */
	public static final String METHODE_RECUPERER_LISTE_FICHIERS_SOUS_REPERTOIRE 
		= "méthode recupererListeFichiersSousRepertoire(Path pPath)";
	
	/**
	 * "méthode recupererListeFichiersMemePoidsSousRepertoire(Path pPath, File pFile)".
	 */
	public static final String METHODE_RECUPERER_LISTE_FICHIERS_MEME_POIDS_SOUS_REPERTOIRE 
	= "méthode recupererListeFichiersMemePoidsSousRepertoire(Path pPath, File pFile)";
	
    /**
     * Path du répertoire racine des fichiers uploadés.<br/>
     */
    private static transient Path rootLocationPath;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(GestionnaireUploadsService.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 * <br/>
	 * private pour bloquer l'instanciation.
	 * <br/>
	 */
	private GestionnaireUploadsService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * retourne la <strong>liste des chemins</strong> 
	 * <i>sous forme de String</i> 
	 * de <strong>tous les fichiers <i>simples</i> contenus dans l'arborescence 
	 * sous le <i>répertoire</i> <code>rootLocationPath</code></strong> 
	 * ainsi que 
	 * <strong>dans tous ses sous-répertoires</strong>.
	 * <ul>
	 * <li>récupère la racine des fichiers uploadés 
	 * <code>rootLocationPath</code>et la crée 
	 * ainsi que son arborescence si nécessaire.</li>
	 * <li>Récupère tous les fichiers simples dans tous les 
	 * sous-répertoires situés sous le répertoire racine des fichiers 
	 * uploadés <code>rootLocationPath</code>.</li>
	 * <li>Ne récupère pas les sous-répertoires.</li>
	 * </ul>
	 *
	 * @return : List&lt;String&gt; : 
	 * liste des chemins de tous les fichiers SIMPLES (pas les répertoires)
	 * contenus dans l'arborescence  sous le répertoire pPath 
	 * quel que soit le niveau d'arborescence.
	 * 
	 * @throws Exception 
	 */
	public static List<String> recupererListeFichiersUploades() 
			throws Exception {
		
		synchronized (GestionnaireUploadsService.class) {
			
			/* récupère la racine des fichiers uploadés 
			 * et la crée ainsi que son arborescence si nécessaire. */
			init();
			
			return recupererListeFichiersSousRepertoire(rootLocationPath);
			
		} // Fin de Synchronized._______________________________
		
	} // Fin de recupererListeFichiersUploades().__________________________


	
	/**
	 * retourne la <strong>liste des chemins</strong> 
	 * <i>sous forme de String</i> 
	 * de <strong>tous les fichiers <i>simples</i> contenus dans l'arborescence 
	 * sous le <i>répertoire</i> pPath</strong> ainsi que 
	 * <strong>dans tous ses sous-répertoires</strong>.
	 * <ul>
	 * <li>Récupère tous les fichiers simples dans tous les 
	 * sous-répertoires situés sous le répertoire racine situé à pPath.</li>
	 * <li>Ne récupère pas les sous-répertoires.</li>
	 * </ul>
	 * - retourne null si pPath == null.<br/>
	 * - retourne null si pPath n'existe pas.<br/>
	 * - retourne null si pPath n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pPath : java.nio.file.Path : Path du <strong>répertoire</strong> 
	 * dont on veut lister tous les fichiers simples.
	 * 
	 * @return List&lt;String&gt; : 
	 * liste des chemins de tous les fichiers SIMPLES (pas les répertoires)
	 * contenus dans l'arborescence  sous le répertoire pPath 
	 * quel que soit le niveau d'arborescence.
	 * 
	 * @throws Exception
	 */
	public static List<String> recupererListeFichiersSousRepertoire(
			final Path pPath) throws Exception {
		
		synchronized (GestionnaireUploadsService.class) {
			
			/* retourne null si pPath == null. */
			if (pPath == null) {
				return null;
			}
			
			/* retourne null si pPath n'existe pas. */
			if (!Files.exists(pPath)) {
				return null;
			}
			
			/* retourne null si pPath n'est pas un répertoire. */
			if (!Files.isDirectory(pPath)) {
				return null;
			}
			
			/* retourne la liste des chemins de tous les fichiers simples 
			 * sous pPath et son arborescence. */
			try (Stream<Path> streamPath 
					= Files.walk(Paths.get(pPath.toString()))) {

				final List<String> resultat 
					= streamPath
						.filter(Files::isRegularFile)
						.map(path -> path.toAbsolutePath()
						.normalize().toString())
						.collect(Collectors.toList());

				return resultat;

			} catch (IOException e) {
				
				final String message 
					= CLASSE_GESTIONNAIRE_UPLOADS_SERVICE 
						+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
						+ METHODE_RECUPERER_LISTE_FICHIERS_SOUS_REPERTOIRE 
						+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
						+ "impossible de parcourir le répertoire " 
						+ pPath.toAbsolutePath().normalize().toString();
				
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message, e);
				}
				
				throw new ExceptionTechnique(message, e);
			}

		} // Fin de Synchronized._______________________________
		
	} // Fin de recupererListeFichiersSousRepertoire(...)._________________
	
	
	
	/**
	 * retourne la <strong>liste des chemins</strong> 
	 * <i>sous forme de String</i> 
	 * de <strong>tous les fichiers <i>simples</i> contenus dans l'arborescence 
	 * sous le <i>répertoire</i> pPath</strong> ainsi que 
	 * <strong>dans tous ses sous-répertoires</strong> 
	 * de <strong>même poids que pFile</strong>.
	 * <ul>
	 * <li>Récupère tous les fichiers simples (de même poids que pFile) 
	 * dans tous les 
	 * sous-répertoires situés sous le répertoire racine situé à pPath.</li>
	 * <li>Ne récupère pas les sous-répertoires.</li>
	 * </ul>
	 * - retourne null si pPath == null.<br/>
	 * - retourne null si pPath n'existe pas.<br/>
	 * - retourne null si pPath n'est pas un répertoire.<br/>
	 * - retourne null si pFile == null.<br/>
	 * - retourne null si pFile n'existe pas.<br/>
	 * - retourne null si pFile est un répertoire.<br/>
	 * <br/>
	 *
	 * @param pPath : java.nio.file.Path : Path du <strong>répertoire</strong> 
	 * dont on veut lister tous les fichiers simples de même poids que pFile. 
	 * @param pFile : java.io.File.
	 * 
	 * @return List&lt;String&gt; :
	 * 
	 * @throws Exception
	 */
	public static List<String> recupererListeFichiersMemePoidsSousRepertoire(
			final Path pPath, final File pFile) throws Exception {
		
		synchronized (GestionnaireUploadsService.class) {
			
			/* retourne null si pPath == null. */
			if (pPath == null) {
				return null;
			}
			
			/* retourne null si pPath n'existe pas. */
			if (!Files.exists(pPath)) {
				return null;
			}
			
			/* retourne null si pPath n'est pas un répertoire. */
			if (!Files.isDirectory(pPath)) {
				return null;
			}
			
			/* retourne null si pFile == null. */
			if (pFile == null) {
				return null;
			}
			
			/* retourne null si pFile n'existe pas. */
			if (!pFile.exists()) {
				return null;
			}
			
			/* retourne null si pFile est un répertoire. */
			if (pFile.isDirectory()) {
				return null;
			}
			
			/* retourne la liste des chemins de tous les fichiers simples 
			 * sous pPath et son arborescence de même poids que pFile. */
			try (Stream<Path> streamPath 
					= Files.walk(Paths.get(pPath.toString()))) {

				final List<String> resultat 
					= streamPath
						.filter(Files::isRegularFile)
						.filter(path -> path.toFile().length() == pFile.length())
						.map(path -> path.toAbsolutePath()
						.normalize().toString())
						.collect(Collectors.toList());

				return resultat;

			} catch (IOException e) {
				
				final String message 
					= CLASSE_GESTIONNAIRE_UPLOADS_SERVICE 
						+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
						+ METHODE_RECUPERER_LISTE_FICHIERS_MEME_POIDS_SOUS_REPERTOIRE 
						+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
						+ "impossible de parcourir le répertoire " 
						+ pPath.toAbsolutePath().normalize().toString();
				
				if (LOG.isFatalEnabled()) {
					LOG.fatal(message, e);
				}
				
				throw new ExceptionTechnique(message, e);
			}

		} // Fin de Synchronized._______________________________
		
	} // Fin de recupererListeFichiersMemePoidsSousRepertoire(...).________
	

	
	/**
	 * affiche une Liste de Strings.
	 *
	 * @param pList : List&lt;String&gt;
	 * 
	 * @return : String : pour affichage.<br/>
	 */
	public static final String afficherListeString(
			final List<String> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		String resultat = null;
		
		final Stream<String> resultatStream = pList.stream();
		
		resultat = resultatStream
			.map(s -> s + IConstantesSautsLigne.NEWLINE)
			.collect(Collectors.joining());
		
		return resultat;
		
	} // Fin de afficherListeString(...).__________________________________
	
	
	
	/**
	 * retourne un <strong>SINGLETON</strong> 
	 * du Path de la racine des fichiers uploadés indiqué normalement 
	 * dans <code>configuration_ressources_externes_fr_FR.properties</code> 
	 * <i>("context/televersements/originaux" sinon)</i>.
	 * <ul>
	 * <li>récupère si possible auprès du 
	 * <code>ConfigurationApplicationManager</code> 
	 * le Path du répertoire <strong>racine</strong> des fichiers uploadés
     * et alimente automatiquement <code>rootLocationPath</code> 
     * avec le path indiqué dans le fichier properties 
     * <code>configuration_ressources_externes_fr_FR.properties</code>.</li>
     * <li>retourne Paths.get("televersements/originaux") 
     * si le ConfigurationApplicationManager n'a pu fournir le Path.</li>
     * </ul>
	 *
	 * @return : Path : rootLocationPath.<br/>
	 */
	private static Path recupererRootUploadsDansProperties() {
		
		synchronized (GestionnaireUploadsService.class) {
			
			if (rootLocationPath == null) {
				
				/* tente de récupérer auprès du ConfigurationApplicationManager 
				 * le Path du répertoire racine des fichiers uploadés. */
				try {
					
					rootLocationPath = Paths.get(
							ConfigurationApplicationManager.getPathTeleversements())
							.normalize().toAbsolutePath();
				
				/* retourne Paths.get("televersements/originaux") 
				 * si le ConfigurationApplicationManager n'a pu fournir le Path. */
				} catch (Exception e) {
					
					if (LOG.isFatalEnabled()) {
						
						final String message 
							= CLASSE_GESTIONNAIRE_UPLOADS_SERVICE 
							+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
							+ METHODE_RECUPERER_ROOT_UPLOADS 
							+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
							+ "configuration_ressources_externes_fr_FR.properties "
							+ "n'est pas présent sous le classpath "
							+ "ou ne contient pas la clé 'televersements'";
						
						LOG.fatal(message, e);
						
					}
					
					rootLocationPath = Paths.get("televersements/originaux")
							.normalize().toAbsolutePath();
					
				}
				
			}
			
			return rootLocationPath;

		} // Fin de Synchronized._______________________________
		
	} // Fin de recupererRootUploadsDansProperties().______________________
	

	
	/**
	 * crée la racine des fichiers uploadés côté serveur
	 * et son arborescence <i>si le répertoire n'existe pas déjà</i>.
	 * <br/>
	 * 
	 * @throws IOException 
	 */
	private static void creerRootUploads() throws IOException {
		
		synchronized (GestionnaireUploadsService.class) {
			
			if (rootLocationPath == null) {
				recupererRootUploadsDansProperties();
			}
			
			if (!Files.exists(rootLocationPath)) {
				Files.createDirectories(rootLocationPath);
			}
			
		} // Fin de Synchronized._______________________________
		
	} // Fin de creerRootUploads().________________________________________
	

	
	/**
	 * <ul>
	 * <li>récupère la racine des fichiers uploadés par appel 
	 * de <code>recupererRootUploadsDansProperties()</code></li>
	 * <li>crée si nécessaire la racine des fichiers uploadés 
	 * dans le File System côté serveur par appel de 
	 * <code>creerRootUploads()</code></li>
	 * </ul>
	 * 
	 * @throws IOException 
	 */
	private static void init() throws IOException {
		
		synchronized (GestionnaireUploadsService.class) {
			
			recupererRootUploadsDansProperties();
			
			creerRootUploads();
			
		} // Fin de Synchronized._______________________________
		
	} // Fin de init().____________________________________________________

	
	
} // FIN DE LA CLASSE GestionnaireUploadsService.----------------------------
