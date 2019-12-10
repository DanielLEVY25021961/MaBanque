package levy.daniel.application.model.services.utilitaires.copieurcontenurepertoire.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.utilitaires.copieurcontenurepertoire.ICopieurContenuRepertoireService;

/**
 * CLASSE CopieurContenuRepertoireService :<br/>
 * classe CONCRETE nécessaire 
 * à l'<b>extraction et à la copie 
 * de l'arborescence et des fichiers simples contenus 
 * sous un répertoire (racine origine)</b>.<br/>
 * <ul>
 * <li>Le contenu recopié contient l'arborescence (<b>répertoires</b>) 
 * <i>sous</i> la racine origine <b>et les fichiers simples</b>.</li>
 * <li><b>L'ensemble du contenu (répertoires et fichiers simples) 
 * sous le répertoire origine 
 * est recopié sous le répertoire destination en respectant l'arborescence</b>.</li>
 * <li>La racine origine n'est pas recopiée. Seul son contenu l'est.</li>
 * <li>Ne recopie un fichier de l'arborescence que si il n'existe 
 * pas déjà sous la destination.</li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * PRINCIPE DE FONCTIONNEMENT de copieurContenuRepertoire :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/copieurcontenurepertoire/principe_fonctionnement_copieurContenuRepertoire.png" 
 * alt="principe de fonctionnement de copieurContenuRepertoire" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE CLASSES de copieurcontenurepertoire :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/copieurcontenurepertoire/diagramme_classes_copieurContenuRepertoire.png" 
 * alt="diagramme de classes de copieurContenuRepertoire" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE SEQUENCE de la méthode <b>copierContenu(File racineOrigine, Path cheminDestination)</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/copieurcontenurepertoire/methode_copieurContenuRepertoire.png" 
 * alt="diagramme de séquence de copierContenu(...)" border="1" align="center" />
 * </div>
 * 
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>
 *  // Détermination de la racine ORIGINE dont on veut recopier tout le contenu (javadoc).<br/>
 * <b>File repRacineACopier = new File("D:/Donnees/eclipse/eclipseworkspace_neon/generation_code/javadoc");</b><br/>
 *  // Détermination de la racine DESTINATION (javadoc dans un autre projet).<br/>
 * <b>Path pathRepDestinationPath =  Paths.get("D:/Donnees/eclipse/eclipseworkspace/test_generation/javadoc");</b><br/>
 *  // Instanciation d'un SERVICE CopieurContenuRepertoireService<br/>
 * <b>ICopieurContenuRepertoireService copieur = new CopieurContenuRepertoireService();</b><br/>
 *  // EXECUTION DE LA COPIE.<br/>
 * <b>copieur.copierContenu(repRacineACopier, pathRepDestinationPath);</b><br/>
 * </code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * afficherListPaths, afficher liste de Paths, afficher list<Path>, <br/>
 * afficher list paths, afficher liste paths, <br/>
 * afficherMapPathsBoolean, afficher map de Paths, Boolean
 * , afficher Map<Path, Boolean>, <br/>
 * afficher list paths, afficher liste paths, <br/>
 * afficher map paths, afficher Map paths, <br/>
 * trier map sur key, trier Map sur Keys, <br/>
 * retourner contenu sous répertoire, <br/> 
 * retourner contenu sous repertoire,<br/>
 * path relatif, <br/>
 * path relatif de path enfant par rapport à path parent,<br/>
 * fournir Path RELATIF, <br/>
 * Path absolu du projet Eclipse courant, path courant, <br/>
 * PATH COURANT, Path Courant, <br/>
 * resolve, pathProjetCourant.resolve(pathRelatif), <br/>
 * ajouter un path, <br/>
 * pathParent.relativize(pathEnfant), <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 1 déc. 2018
 *
 */
public class CopieurContenuRepertoireService 
				implements ICopieurContenuRepertoireService {
	

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "line.separator".
	 */
	public static final String LINE_SEPARATOR 
		= "line.separator";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(CopieurContenuRepertoireService.class);
	

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public CopieurContenuRepertoireService() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void copierContenu(
			final File pRacineOrigine
				, final Path pRepDestinationPath) 
						throws Exception {
		
		/* ne fait rien si pRacineOrigine == null. */
		if (pRacineOrigine == null) {
			return;
		}
				
		/* ne fait rien si pRacineOrigine n'existe pas. */
		if (!pRacineOrigine.exists()) {
			return;
		}
		
		/* ne fait rien si pRacineOrigine n'est pas un répertoire. */
		if (!pRacineOrigine.isDirectory()) {
			return;
		}
		
		/* récupère le contenu sous le répertoire pRacineOrigine. */
		final Map<Path, Boolean> contenuMap 
			= this.recupererContenuSous(pRacineOrigine);

		/* copie le contenu sous le répertoire pRacineOrigine 
		 * sous le répertoire pDestinationPath. */
		this.copierContenuVersDestination(
				contenuMap, pRepDestinationPath, pRacineOrigine);
		
	} // Fin de copierContenu(...).________________________________________
	
	
	
	/**
	 * <b>retourne une Map&lt;Path, Boolean&gt; des Paths RELATIFS 
	 * <i>(par rapport à pRacineOrigine)</i>  de toute l'arborescence 
	 * <i>(répertoires et fichiers simples)</i> contenue
	 * sous pRacineOrigine et qui contient 
	 * la nature (Directory ou SimpleFile) des paths relatifs</b>.<br/>
	 * <ul>
	 * La Map&lt;Path, Boolean&gt; précise :
	 * <li>Path : le Path RELATIF 
	 * (par rapport à pRacineOrigine).</li>
	 * <li>Boolean : 
	 * true si le Path RELATIF correspond à un Directory.</li>
	 * </ul>
	 * par exemple :<br/>
	 * <code>recupererContenuSous(".../javadoc)</code> retourne :<br/>
	 * [images, true]<br/>
	 * [images\abstract_ids_insee.png, false]<br/>
	 * [images\apptechnic, true]<br/>
	 * [images\apptechnic\LocaleManager.png, false]<br/>
	 * ...<br/>
	 * 
	 * <ul>
	 * <li><b>ne RETOURNE PAS pRacineOrigine</b>.</li>
	 * <li>originePath.relativize(path).</li>
	 * <li>retourne null si une IOException se produit 
	 * lors de la lecture de l'arborescence.</li>
	 * </ul>
	 * - retourne null si pRacineOrigine == null.<br/>
	 * - retourne null si pRacineOrigine n'existe pas.<br/>
	 * - retourne null si pRacineOrigine n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pRacineOrigine : File : 
	 * répertoire dont on récupère le contenu.<br/> 
	 * 
	 * @return : Map&lt;Path, Boolean&gt; : 
	 * Map des Paths RELATIFS (par rapport à pRacineOrigine) 
	 * avec leur nature
	 *  de toute l'arborescence 
	 * <i>(répertoires et fichiers simples)</i> 
	 * contenue sous pRacineOrigine.<br/>
	 */
	private Map<Path, Boolean> recupererContenuSous(
			final File pRacineOrigine) {
		
		/* retourne null si pRacineOrigine == null. */
		if (pRacineOrigine == null) {
			return null;
		}
				
		/* retourne null si pRacineOrigine n'existe pas. */
		if (!pRacineOrigine.exists()) {
			return null;
		}
		
		/* retourne null si pRacineOrigine n'est pas un répertoire. */
		if (!pRacineOrigine.isDirectory()) {
			return null;
		}

		final Path originePath 
			= pRacineOrigine.toPath()
				.toAbsolutePath().normalize();

		/* retourne la liste des Paths RELATIFS 
		 * des Files sous originePath. */
		/* utilise un try-with-resources. */
		try (Stream<Path> stream 
				= Files.walk(originePath)) {

			/* calcule chaque path RELATIF par 
			 * rapport à la racine origine. */
			/* détermine si le path designait 
			 * un Directory à l'origine. */
			final Map<Path, Boolean> map = stream
					.filter(path -> !path.equals(originePath))
					.collect(Collectors.toMap(
							path -> originePath.relativize(path)
							, path -> path.toFile().isDirectory()));
			
			/* trie la map sur les keys. */
			final SortedMap<Path, Boolean> mapTriee 
				= new TreeMap<Path, Boolean>(map);
			
			return mapTriee;
			
		} catch (IOException e) {
			return null;
		}
		
	} // Fin de recupererContenuSous(...)._________________________________
	

	
	/**
	 * <b>Copie une Map&lt;Path, Boolean&gt; de Paths RELATIFS 
	 * sous un répertoire destination</b>.<br/>
	 * <b>Ecrit sur disque</b>.<br/>
	 * <ul>
	 * <li>calcule le path ABSOLU de chaque File à copier dans 
	 * le répertoire destination 
	 * (<code>pathFileACopier 
	 * = pRepDestinationPath.resolve(pathRelatif);</code>).</li>
	 * <li>ne copie le File à copier que si il n'est pas déjà 
	 * existant dans le répertoire destination.</li>
	 * <li>crée un répertoire si le Boolean 
	 * dans la Map est à true.</li>
	 * <li>crée un fichier simple si le Boolean 
	 * dans la Map est à false.</li>
	 * </ul>
	 * - ne fait rien si pContenuMap == null.<br/>
	 * - ne fait rien si pRepDestinationPath == null.<br/>
	 * - crée pRepDestinationPath et son ascendance 
	 * si il n'existe pas sur le disque.<br/>
	 * <br/>
	 *
	 * @param pContenuMap : Map&lt;Path, Boolean&gt;.<br/>
	 * @param pRepDestinationPath : Path.<br/>
	 * @param pRacineOrigine : File : racine origine.<br/>
	 * 
	 * @throws Exception
	 */
	private void copierContenuVersDestination(
			final Map<Path, Boolean> pContenuMap
				, final Path pRepDestinationPath
					, final File pRacineOrigine) 
							throws Exception {
		
		/* ne fait rien si pContenuMap == null. */
		if (pContenuMap == null) {
			return;
		}
		
		/* ne fait rien si pRepDestinationPath == null. */
		if (pRepDestinationPath == null) {
			return;
		}
				
		// Boucle sur tous les Paths RELATIFS de la Map.
		final Set<Entry<Path, Boolean>> entrySet 
			= pContenuMap.entrySet();
		
		final Iterator<Entry<Path, Boolean>> ite 
			= entrySet.iterator();
				
		while (ite.hasNext()) {
			
			final Entry<Path, Boolean> entry = ite.next();
			final Path pathRelatif = entry.getKey();
			final Boolean isDirectory = entry.getValue();
			
			if (pathRelatif != null) {
							
				/* calcule le path ABSOLU de chaque File à copier 
				 * dans le répertoire destination. */
				final Path pathFileACopier 
					= pRepDestinationPath.resolve(pathRelatif)
						.toAbsolutePath().normalize();
				
				final File fileACopier = pathFileACopier.toFile();
				
				/* ne copie le File à copier que si il n'est 
				 * pas déjà existant dans le répertoire destination. */
				if (!fileACopier.exists()) {
					
					/* crée un répertoire si le path relatif 
					 * pointait sur un répertoire à l'origine. */
					/* crée pRepDestinationPath et son ascendance 
					 * si il n'existe pas sur le disque. */
					if (isDirectory) {
						
						Files.createDirectories(pathFileACopier);
					
					/* crée un fichier simple si le path relatif 
					 * pointait sur un fichier simple à l'origine. */
					} else {
						
						final Path fileACopierParentPath 
							= pathFileACopier.getParent();
				
						if (fileACopierParentPath != null) {
							if (!fileACopierParentPath.toFile().exists()) {
								Files.createDirectories(fileACopierParentPath);
							}
						}

						final Path pathOrigine 
							= pRacineOrigine.toPath()
								.toAbsolutePath().normalize();
						
						final Path fichierOriginePath 
							= pathOrigine.resolve(pathRelatif)
								.toAbsolutePath().normalize();
						
						Files.copy(fichierOriginePath, pathFileACopier);
						
					}	
					
				} // Fin de File existant._______
				
			} // Fin de if (path != null).___________
			
		} // Fin de la boucle._________________________
		
	} // Fin de copierContenuVersDestination(...)._________________________
	
	
	
	/**
	 * <b>fournit le PATH RELATIF de pPathEnfant 
	 * par rapport à pPathParent</b>.<br/>
	 * pPathEnfant est donc "plus long" que pPathParent 
	 * et descend de lui.<br/>
	 * <ul>
	 * par exemple :<br/>
	 * <li>si pPathParent = "D:/eclipse/toto"</li>
	 * <li>si pPathEnfant = "D:/eclipse/toto/<b>tata/titi</b>"</li>
	 * <li><code>fournirPathRelatif(pPathParent, pPathEnfant)</code> 
	 * retourne <b>"tata/titi"</b>.</li>
	 * <li>utilise 
	 * <code>pPathParent.relativize(pPathEnfant);</code></li>
	 * </ul>
	 * - retourne null si pPathParent == null.<br/>
	 * - retourne null si pPathEnfant == null.<br/>
	 * <br/>
	 *
	 * @param pPathParent : Path.<br/>
	 * @param pPathEnfant : Path.<br/>
	 * 
	 * @return : Path : Path relatif.<br/>
	 */
	private Path fournirPathRelatif(
			final Path pPathParent
				, final Path pPathEnfant) {
		
		/* retourne null si pPathParent == null. */
		if (pPathParent == null) {
			return null;
		}
		
		/* retourne null si pPathEnfant == null. */
		if (pPathEnfant == null) {
			return null;
		}
		
		return pPathParent.relativize(pPathEnfant);
		
	} // Fin de fournirPathRelatif(...).___________________________________
	
	
	
	/**
	 * <b>retourne le Path absolu du projet Eclipse courant</b>.<br/>
	 * <ul>
	 * <li>utilise <code>Paths.get(".").toAbsolutePath();</code>.</li>
	 * </ul>
	 *
	 * @return : Path : 
	 * Path absolu du projet Eclipse courant.<br/>
	 */
	private Path fournirPathAbsoluPresentProjet() {
		
		final Path pathAbsoluPresentProjet 
			= Paths.get(".").toAbsolutePath();
		
		return pathAbsoluPresentProjet;
		
	} // Fin de fournirPathAbsoluPresentProjet().__________________________
	
	
	
	/**
	 * <b>fournit le PATH ABSOLU 
	 * pathPresentProjet/ + pPathRelatif</b>.<br/>
	 * <ul>
	 * <li>ajoute le path relatif au path du présent projet.</li>
	 * <li>utilise 
	 * <code>pathProjetCourant.resolve(pPathRelatif);</code>.</li>
	 * </ul>
	 * - retourne null si pPathRelatif == null.<br/>
	 * <br/>
	 *
	 * @param pPathRelatif : Path.<br/>
	 * 
	 * @return : Path : 
	 * Path absolu dans le présent projet Eclipse.<br/>
	 */
	private Path fournirPathAbsoluDansPresentProjet(
							final Path pPathRelatif) {
		
		/* retourne null si pPathRelatif == null. */
		if (pPathRelatif == null) {
			return null;
		}
		
		/* ajoute le path relatif au path du présent projet. */
		final Path pathAbsoluDansPresentProjet 
			= this.fournirPathAbsoluPresentProjet()
				.resolve(pPathRelatif);
		
		return pathAbsoluDansPresentProjet;
		
	} // Fin de fournirPathAbsoluDansPresentProjet(...).___________________
	
	
	
	/**
	 * <b>fournit une String pour l'affichage 
	 * d'une List&lt;Path&gt;</b>.<br/>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;Path&gt;.<br/>
	 * 
	 * @return : String : pour affichage.<br/>
	 */
	public String afficherListPaths(
			final List<Path> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final Path path : pList) {
			
			if (path != null) {
				stb.append(path.toString());
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
			
		}
		
		return stb.toString();
		
	} // Fin de afficherListPaths(...).____________________________________
	

	
	/**
	 * <b>fournit une String pour l'affichage 
	 * d'une Map&lt;Path, Boolean&gt;</b>.<br/>
	 * - retourne null si pMap == null.<br/>
	 * <br/>
	 *
	 * @param pMap : Map&lt;Path, Boolean&gt;.<br/>
	 * 
	 * @return : String : pour affichage.<br/>
	 */
	public String afficherMapPathsBoolean(
			final Map<Path, Boolean> pMap) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		/* trie la map sur les keys. */
		final SortedMap<Path, Boolean> mapTriee 
			= new TreeMap<Path, Boolean>(pMap);
		
		final StringBuilder stb = new StringBuilder();
		
		final Set<Entry<Path, Boolean>> entrySet = mapTriee.entrySet();
		
		final Iterator<Entry<Path, Boolean>> ite = entrySet.iterator();
		
		while (ite.hasNext()) {

			final Entry<Path, Boolean> entry = ite.next();
			final Path path = entry.getKey();
			final Boolean isDirectory = entry.getValue();

			if (path != null) {

				final String string 
				= String.format(
						Locale.getDefault()
						, "PATH : %1$-100s      REPERTOIRE ? : %2$-10s",
						path, isDirectory);

				stb.append(string);
				stb.append(System.getProperty(LINE_SEPARATOR));

			}

		}
		
		return stb.toString();
		
	} // Fin de afficherMapPathsBoolean(...).______________________________
	
	
	
} // FIN DE LA CLASSE CopieurContenuRepertoireService.-----------------------
