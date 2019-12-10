package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespaths;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE <b>ManagerPaths</b> :<br/>
 * <ul>
 * <li>Classe <b>UTILITAIRE (méthodes static)</b> chargée de fournir
 * les <b>caractéristiques du projet ECLIPSE courant</b> 
 * (unité du projet Eclipse courant, chemin du Workspace courant
 * , nom du projet courant, ...).</li>
 * <li>
 * classe chargée de fournir des <b>SINGLETONS</b> pour :
 * <ol>
 * <li><b>l'unité de disque dur</b> sur laquelle est située 
 * le projet courant <code><b>pathUniteCourante</b></code>.</li>
 * <li>l'emplacement du <b>Workspace Eclipse</b> courant 
 * <code><b>pathPresentWorkspace</b></code>.</li>
 * <li>l'emplacement du <b>projet Eclipse</b> courant 
 * <code><b>pathPresentProjet</b></code>.</li>
 * <li>le nom du <b>projet Eclipse</b> courant 
 * <code><b>nomPresentProjet</b></code>.</li>
 * </ol>
 * </li>
 * </ul>
 * 
 * <p>
 * <b><span style="text-decoration:underline;">
 * Diagramme de classe du ManagerPaths : 
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../javadoc/images/apptechnic/configurationmanagers/gestionnairespaths/managerpaths/classe_ManagerPaths.png" 
 * alt="Diagramme de classe du ManagerPaths" />
 * </p>
 * 
 * <br/>
 *
 * <p>
 * - Exemple d'utilisation :
 * </p>
 * <code> // Récupère le PATH de l'unité courante du présent projet ECLIPSE (par exemple D:)</code><br/>
 * <code><b>Path pathUniteCourante = ManagerPaths.getPathUniteCourante();</b></code><br/>
 * <code> // Récupère le PATH du WORKSPACE du présent projet ECLIPSE (par exemple D:/Donnees/eclipse/eclipseworkspace)</code><br/>
 * <code><b>Path pathPresentWorkspace = ManagerPaths.getPathPresentWorkspace();</b></code><br/>
 * <code> // Récupère le PATH du présent projet ECLIPSE (par exemple D:/Donnees/eclipse/eclipseworkspace/traficweb_v1)</code><br/>
 * <code><b>Path pathPresentProjet = ManagerPaths.getPathPresentProjet();</b></code><br/>
 * <code> // Récupère le NOM du présent projet ECLIPSE (par exemple traficweb_v1)</code><br/>
 * <code><b>String nomPresentProjet = ManagerPaths.getNomPresentProjet();</b></code><br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * séparateur de fichiers, separateur, antislash, <br/>
 * Singleton, singleton, <br/>
 * transformer String en Path, Paths.get(String), <br/>
 * Path du projet courant, récupérer projet eclipse courant,<br/>
 * nom du projet courant, unité courante, projet ECLIPSE courant,
 * workspace du projet ECLIPSE courant, <br/>
 * Paths.get(".").toAbsolutePath().normalize();<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 27 janv. 2018
 *
 */
public final class ManagerPaths {

	// ************************ATTRIBUTS************************************/

	/**
	 * SEPARATEUR_FICHIERS : String :<br/>
	 * <ul>
	 * <li>Séparateur de fichiers de la plateforme.</li>
	 * <li>fourni par System.getProperty("file.separator").</li>
	 * <li>antislash '\' sur la plateforme Windows.</li>
	 * </ul>
	 */
	public static final String SEPARATEUR_FICHIERS 
		= System.getProperty("file.separator");
	
	
	/**
	 * SLASH : char :<br/>
	 * Séparateur générique des fichiers slash.<br/>
	 * '/'.<br/>
	 */
	public static final char SLASH = '/';
	
	
	/**
	 * pathUniteCouranteString : String :<br/>
	 * <ul>
	 * <li><b>path "générique"</b> (avec des séparateurs slash) 
	 * de <b>l'unité courante sur laquelle est situé le présent projet
	 * </b> sous forme de <i>String</i>.</li>
	 * <li>calculé à l'aide de la position de File("\")</li>
	 * <li>Par exemple : <br/>
	 * D:/
	 * </li>
	 * </ul>
	 */
	private static String pathUniteCouranteString;
	
	
	/**
	 * pathUniteCourante : Path :<br/>
	 * <ul>
	 * <li><b>path "générique"</b> (avec des séparateurs slash) 
	 * de <b>l'unité courante sur laquelle est situé le présent projet
	 * </b> sous forme de <i>Path</i>.</li>
	 * <li>calculé à l'aide de la position de File("\")</li>
	 * <li>Par exemple : <br/>
	 * D:/
	 * </li>
	 * </ul>
	 */
	private static Path pathUniteCourante;
	
	
	/**
	 * pathPresentWorkspaceString : String :<br/>
	 * <ul>
	 * <li><b>path "générique"</b> (avec des séparateurs slash) 
	 * du <b>présent workspace Eclipse</b> sous forme de <i>String</i>.</li>
	 * <li>calculé avec pathPresentProjet.getParent().</li>
	 * <li>Par exemple : <br/>
	 * D:/Donnees/eclipse/eclipseworkspace_neon
	 * </li>
	 * </ul>
	 */
	private static String pathPresentWorkspaceString;
	
	
	/**
	 * pathPresentWorkspace : Path :<br/>
	 * <ul>
	 * <li><b>path "générique"</b> (avec des séparateurs slash) 
	 * du <b>présent workspace Eclipse</b> sous forme de <i>Path</i>.</li>
	 * <li>calculé avec pathPresentProjet.getParent().</li>
	 * <li>Par exemple : <br/>
	 * D:/Donnees/eclipse/eclipseworkspace_neon
	 * </li>
	 * </ul>
	 */
	private static Path pathPresentWorkspace;
	

	/**
	 * pathPresentProjetString : String :<br/>
	 * <ul>
	 * <li><b>path "générique"</b> (avec des séparateurs slash) 
	 * du <b>présent projet Eclipse</b> sous forme de <i>String</i>.</li>
	 * <li>calculé avec la position de File("").</li>
	 * <li>Par exemple : <br/>
	 * D:/Donnees/eclipse/eclipseworkspace_neon/generation_code
	 * </li>
	 * </ul>
	 */
	private static String pathPresentProjetString;
	
	
	/**
	 * pathPresentProjet : Path :<br/>
	 * <ul>
	 * <li><b>path "générique"</b> (avec des séparateurs slash) 
	 * du <b>présent projet Eclipse</b> sous forme de <i>Path</i>.</li>
	 * <li>calculé avec la position de File("").</li>
	 * <li>Par exemple : <br/>
	 * D:/Donnees/eclipse/eclipseworkspace_neon/generation_code
	 * </li>
	 * </ul>
	 */
	private static Path pathPresentProjet;
	
	
	/**
	 * nomPresentProjet : String :<br/>
	 * <ul>
	 * <li><b>nom du présent projet Eclipse</b>.</li>
	 * </ul>
	 */
	private static String nomPresentProjet;

	
	/**
	 * <b>path absolu de src/main/resources 
	 * dans le présent projet</b>.<br/>
	 * <ul>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * generation_code/src/main/resources</code>
	 * </li>
	 * </ul>
	 */
	private static Path pathAbsoluSrcMainResourcesPresentProjet;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(ManagerPaths.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR ManagerPaths() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	private ManagerPaths() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * <ul>
	 * <li>retourne le <b>path "générique"</b> (avec des séparateurs slash) 
	 * de <b>l'unité courante sur laquelle est situé le présent projet
	 * </b> sous forme de <i>String</i>.</li>
	 * <li>calculé à l'aide de la position de File("\")</li>
	 * <li>Par exemple : <br/>
	 * D:/
	 * </li>
	 * </ul>
	 *
	 * @return : pathUniteCouranteString : String.<br/>
	 * 
	 * @throws IOException 
	 */
	public static String getPathUniteCouranteString() throws IOException {
		
		synchronized (ManagerPaths.class) {
			
			if (pathUniteCouranteString == null) {
				
				/* Unité courante. */
				final File fileSousUnite = new File("\\");
				final Path pathWindowsUnite = fileSousUnite.toPath();
				final Path realPath = pathWindowsUnite.toRealPath();
				
				/* passage de Path à String. */
				final String cheminWindowsString = realPath.toString();
				
				/* remplacement des antislash par des slash. */
				pathUniteCouranteString 
					= retournerPathGenerique(cheminWindowsString);
												
			}
			
			return pathUniteCouranteString;
			
		} //Fin de synchronized. ________________________________
		
	} // Fin de getPathUniteCouranteString().______________________________


		
	/**
	 * <ul>
	 * <li>retourne le <b>path "générique"</b> (avec des séparateurs slash) 
	 * de <b>l'unité courante sur laquelle est situé le présent projet
	 * </b> sous forme de <i>Path</i>.</li>
	 * <li>calculé à l'aide de la position de File("\")</li>
	 * <li>Par exemple : <br/>
	 * D:/
	 * </li>
	 * </ul>
	 *
	 * @return pathUniteCourante : Path.<br/>
	 * 
	 * @throws IOException
	 */
	public static Path getPathUniteCourante() throws IOException {
		
		synchronized (ManagerPaths.class) {
			
			if (pathUniteCourante == null) {
				
				getPathUniteCouranteString();
				
				/* String vers Path. */
				pathUniteCourante = Paths.get(pathUniteCouranteString);
			}
			
			return pathUniteCourante;
			
		} //Fin de synchronized. ________________________________
		
	} // Fin de getPathUniteCourante().____________________________________
	

	
	/**
	 * <ul>
	 * <li>retourne le <b>path "générique"</b> (avec des séparateurs slash) 
	 * du <b>présent workspace Eclipse</b> sous forme de <i>String</i>.</li>
	 * <li>retourne un <b>Singleton</b>.</li>
	 * <li>calculé avec pathPresentProjet.getParent().</li>
	 * <li>Par exemple : <br/>
	 * D:/Donnees/eclipse/eclipseworkspace_neon
	 * </li>
	 * </ul>
	 *
	 * @return : pathPresentWorkspaceString : String.<br/>
	 * 
	 * @throws IOException 
	 */
	public static String getPathPresentWorkspaceString() throws IOException {
		
		synchronized (ManagerPaths.class) {
			
			if (pathPresentWorkspaceString == null) {
				
				getPathPresentProjet();
				
				/* calcule le parent du path du présent projet. */
				final Path pathWorkspace = pathPresentProjet.getParent();
				
				if (pathWorkspace != null) {
					
					/* passage de Path à String. */
					final String cheminWindowsString = pathWorkspace.toString();
					
					/* remplacement des antislash par des slash. */
					pathPresentWorkspaceString 
						= retournerPathGenerique(cheminWindowsString);
				}
																				
			}
			
			return pathPresentWorkspaceString;
			
		} //Fin de synchronized. ________________________________
		
	} // Fin de getPathPresentWorkspaceString().______________________________


		
	/**
	 * <ul>
	 * <li>retourne le <b>path "générique"</b> (avec des séparateurs slash) 
	 * du <b>présent workspace Eclipse</b> sous forme de <i>Path</i>.</li>
	 * <li>calculé avec pathPresentProjet.getParent().</li>
	 * <li>Par exemple : <br/>
	 * D:/Donnees/eclipse/eclipseworkspace_neon
	 * </li>
	 * </ul>
	 *
	 * @return pathPresentWorkspace : Path.<br/>
	 * 
	 * @throws IOException
	 */
	public static Path getPathPresentWorkspace() throws IOException {
		
		synchronized (ManagerPaths.class) {
			
			if (pathPresentWorkspace == null) {
				
				getPathPresentWorkspaceString();
				
				/* String vers Path. */
				pathPresentWorkspace = Paths.get(pathPresentWorkspaceString);
			}
			
			return pathPresentWorkspace;
			
		} //Fin de synchronized. ________________________________
		
	} // Fin de getPathPresentWorkspace().____________________________________
	
	
	
	/**
	 * <ul>
	 * <li>retourne le <b>path "générique"</b> (avec des séparateurs slash) 
	 * du <b>présent projet Eclipse</b> sous forme de <i>String</i>.</li>
	 * <li>retourne un <b>Singleton</b>.</li>
	 * <li>calculé avec la position de File("").</li>
	 * <li>Par exemple : <br/>
	 * D:/Donnees/eclipse/eclipseworkspace_neon/generation_code
	 * </li>
	 * </ul>
	 *
	 * @return : pathPresentProjetString : String.<br/>
	 * 
	 * @throws IOException 
	 */
	public static String getPathPresentProjetString() throws IOException {
		
		synchronized (ManagerPaths.class) {
			
			if (pathPresentProjetString == null) {
				
				/* File désignant le projet courant. */
				final File fileSousProjet = new File(".");
				final Path pathWindowsProjet = fileSousProjet.toPath();
				final Path realPath = pathWindowsProjet.toRealPath();
				
				/* passage de Path à String. */
				final String cheminWindowsString = realPath.toString();
				
				/* remplacement des antislash par des slash. */
				pathPresentProjetString 
					= retournerPathGenerique(cheminWindowsString);
												
			}
			
			return pathPresentProjetString;
			
		} //Fin de synchronized. ________________________________
		
	} // Fin de getPathPresentProjetString().______________________________


		
	/**
	 * <ul>
	 * <li>retourne le <b>path "générique"</b> (avec des séparateurs slash) 
	 * du <b>présent projet Eclipse</b> sous forme de <i>Path</i>.</li>
	 * <li>calculé avec la position de File("").</li>
	 * <li>Par exemple : <br/>
	 * D:/Donnees/eclipse/eclipseworkspace_neon/generation_code
	 * </li>
	 * </ul>
	 * - retourne l'équivalent de 
	 * <code>Paths.get(".").toAbsolutePath().normalize()</code><br/>
	 * <br/>
	 *
	 * @return pathPresentProjet : Path.<br/>
	 * 
	 * @throws IOException
	 */
	public static Path getPathPresentProjet() throws IOException {
		
		synchronized (ManagerPaths.class) {
			
			if (pathPresentProjet == null) {
				
				getPathPresentProjetString();
				
				/* String vers Path. */
				pathPresentProjet = Paths.get(pathPresentProjetString);
			}
			
			return pathPresentProjet;
			
		} //Fin de synchronized. ________________________________
		
	} // Fin de getPathPresentProjet().____________________________________
	

		
	/**
	 * <ul>
	 * <li>retourne le <b>nom du présent projet Eclipse</b>.</li>
	 * <li>utilise <code>
	 * pathPresentWorkspace.relativize(pathPresentProjet)
	 * </code></li>
	 * </ul>
	 *
	 * @return nomPresentProjet : String.<br/>
	 * 
	 * @throws IOException 
	 */
	public static String getNomPresentProjet() 
			throws IOException {
		
		synchronized (ManagerPaths.class) {
			
			if (nomPresentProjet == null) {
				
				getPathPresentWorkspace();
				getPathPresentProjet();
				
				final Path path 
					= pathPresentWorkspace.relativize(pathPresentProjet);
				
				nomPresentProjet = path.toString();
				
			}
			
			return nomPresentProjet;
						
		} //Fin de synchronized. ________________________________
		
	} // Fin de getNomPresentProjet()._____________________________________


	
	/**
	 * Getter du <b>path absolu de src/main/resources</b> 
	 * dans le présent projet.<br/>
	 * <ul>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/
	 * generation_code/src/main/resources</code>
	 * </li>
	 * </ul>
	 *
	 * @return pathAbsoluSrcMainResourcesPresentProjet : Path.<br/>
	 */
	public static Path getPathAbsoluSrcMainResourcesPresentProjet() {
		
		synchronized (ManagerPaths.class) {
			
			if (pathAbsoluSrcMainResourcesPresentProjet == null) {
				
				final Path pathAbsoluPresentProjet 
					= Paths.get(".").toAbsolutePath().normalize();
				
				pathAbsoluSrcMainResourcesPresentProjet 
					= pathAbsoluPresentProjet.resolve(
							"src/main/resources");
			}
			
			return pathAbsoluSrcMainResourcesPresentProjet
								.toAbsolutePath().normalize();
			
		} //Fin de synchronized. ________________________________
		
	} // Fin de getPathAbsoluSrcMainResourcesPresentProjet().______________



	/**
	 * <ul>
	 * <li><b>Remplace les séparateurs de fichier antislash</b> 
	 * dans pPath par des <b>séparateurs génériques slash '/'</b>.</li>
	 * <li>Par exemple : <br/>
	 * <code>retournerPathGenerique("D:\Donnees
	 * \eclipse\eclipseworkspace_neon\generation_code")</code> 
	 * retourne 
	 * <code>
	 * "D:/Donnees/eclipse/eclipseworkspace_neon/generation_code"
	 * </code>
	 * </li>
	 * </ul>
	 *
	 * @param pPathString : String.<br/>
	 * 
	 * @return : String : String avec des slashes
	 *  à la place des antislash.<br/>
	 */
	private static String retournerPathGenerique(
			final String pPathString) {
		
		/* retourne null si pPathString est blank. */
		if (StringUtils.isBlank(pPathString)) {
			return null;
		}
		
		final String resultat 
			= StringUtils.replaceChars(pPathString, '\\', SLASH);
		
		return resultat;
		
	} // Fin de retournerPathGenerique(...)._______________________________
	
	
	
} // FIN DE LA CLASSE ManagerPaths.------------------------------------------
