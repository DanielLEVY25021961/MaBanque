package levy.daniel.application.model.services.utilitaires.generateurprojet.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.utilitaires.arboresceurprojet.ArboresceurProjetCible;
import levy.daniel.application.model.services.utilitaires.copieurcontenurepertoire.ICopieurContenuRepertoireService;
import levy.daniel.application.model.services.utilitaires.copieurcontenurepertoire.impl.CopieurContenuRepertoireService;
import levy.daniel.application.model.services.utilitaires.ecriveurpackageinfo.IEcriveurPackageInfoService;
import levy.daniel.application.model.services.utilitaires.ecriveurpackageinfo.impl.EcriveurPackageInfoService;
import levy.daniel.application.model.services.utilitaires.generateurpomxml.IGenerateurPOMTemplateService;
import levy.daniel.application.model.services.utilitaires.generateurpomxml.impl.GenerateurPOMTemplateService;
import levy.daniel.application.model.services.utilitaires.generateurprojet.IGenerateurProjetService;
import levy.daniel.application.model.services.utilitaires.managerpaths.ManagerPaths;

/**
 * CLASSE GenerateurProjetService :<br/>
 * Classe concrète SERVICE chargée d'<b>écrire une arborescence de REPERTOIRES
 * de projet MAVEN SIMPLE (sans artéfact) sur disque 
 * dans un projet cible</b>.<br/>
 * Toutes les couches (GroupId, apptechnic, controllers, model, vues...)
 * ainsi que leurs sous-couches et les répertoires externes (data, logs, ...) 
 * sont générés dans le projet cible.<br/>
 * L'arborescence à copier dans le projet cible est fournie 
 * par un {@link ArboresceurProjetCible}.<br/>
 * <br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * Création des répértoires et packages sous un projet cible par GenerateurProjetService :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/generateurprojet/generateurprojet_resultat.png" 
 * alt="création des répertoires par GenerateurProjetService" border="1" align="center" />
 * </div>
 * <br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE CLASSES de GenerateurProjetService :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/generateurprojet/generateurprojet_service.png" 
 * alt="generateurprojet SERVICE" border="1" align="center" />
 * </div>
 * <br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>
 *  // Path du projet cible.<br/>
 * projetCiblePath = Paths.get("D:/Donnees/toto");<br/>
 *  // INSTANCIATION D'UN GenerateurProjetService.<br/>
 * <b>IGenerateurProjetService generateur = new GenerateurProjetService();</b><br/>
 *  // GENERATION DE L'ARBORESCENCE DANS LE PROJET CIBLE.<br/>
 * <b>generateur.generer(projetCiblePath);</b><br/>
 * </code>
 *<br/>
 * 
 * 
 * - Mots-clé :<br/>
 * écriture fichier sur disque, ecrire sur disque, ecriture fichier, <br/>
 * ecriture répertoire sur disque, ecrire repertoire sur disque, <br/>
 * Files.createDirectories(path);<br/>
 * Création arborescence sur disque, ecriture arborescence sur disque,<br/>
 * écriture arborescence sur disque,
 * création récursive de répertoires, recursif, récursif,<br/>
 * obtenir Path à partir de String, Paths.get(String), <br/>
 * génération arborescence dans projet cible,<br/>
 * generation arborescence dans projet cible,<br/>
 * path ABSOLU du présent projet Eclipse, path absolu présent projet, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel Lévy
 * @version 1.0
 * @since 26 nov. 2018
 *
 */
public class GenerateurProjetService implements IGenerateurProjetService {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * Service chargé d'écrire les package-info.
	 */
	private final transient IEcriveurPackageInfoService ecriveurPackageInfoService 
		= new EcriveurPackageInfoService();

	/**
	 * Service chargé de recopier tout le contenu 
	 * sous un répertoire ORIGINE.
	 */
	private final transient ICopieurContenuRepertoireService copieurService 
		= new CopieurContenuRepertoireService();
	
	/**
	 * Service chargé de générer un POM 
	 * sous le projet cible.
	 */
	private final transient  IGenerateurPOMTemplateService pomService 
		= new GenerateurPOMTemplateService();
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
	= LogFactory.getLog(GenerateurProjetService.class);

	// *************************METHODES************************************/
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public GenerateurProjetService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void generer(
			final Path pProjetCiblePath) throws Exception {
		
		this.generer(pProjetCiblePath, null);
		
	} // Fin de generer(...).______________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void generer(
			final Path pProjetCiblePath
				, final String pGroupId) throws Exception {
		
		/* ne fait rien si pProjetCiblePath == null. */
		if (pProjetCiblePath == null) {
			return;
		}
		
		final File projetCibleFile = pProjetCiblePath.toFile();
		
		/* ne fait rien si le projet cible n'existe pas. */
		if (!projetCibleFile.exists()) {
			return;
		}
		
		
		List<Path> arborescence = null;
		Path projetCiblePath = null;
		Map<String, Path> arborescenceMainProjetCibleMap = null;
//		Map<String, Path> arborescenceTestProjetCibleMap = null;
//		Map<String, Path> arborescenceRepExtProjetCibleMap = null;
		
		/* GroupId par défaut GROUPID_PAR_DEFAUT dans 
		 * ArboresceurProjetCible si pGroupId est blank. */
		if (StringUtils.isBlank(pGroupId)) {
			
			ArboresceurProjetCible.selectionnerProjetCible(pProjetCiblePath);
			arborescence = ArboresceurProjetCible.getArborescenceProjetCible();
			projetCiblePath = ArboresceurProjetCible.getProjetCiblePath();
			arborescenceMainProjetCibleMap 
				= ArboresceurProjetCible.getArborescenceMainProjetCibleMap();
//			arborescenceTestProjetCibleMap 
//				= ArboresceurProjetCible.getArborescenceTestProjetCibleMap();
//			arborescenceRepExtProjetCibleMap 
//				= ArboresceurProjetCible.getArborescenceRepertoiresExternesProjetCibleMap();
			
		} else {
			
			final Path groupIdPath = Paths.get(pGroupId);
			ArboresceurProjetCible.setGroupIdPathRelatif(groupIdPath);
			ArboresceurProjetCible.selectionnerProjetCible(pProjetCiblePath);			
			arborescence = ArboresceurProjetCible.getArborescenceProjetCible();
			arborescenceMainProjetCibleMap 
				= ArboresceurProjetCible.getArborescenceMainProjetCibleMap();
//			arborescenceTestProjetCibleMap 
//				= ArboresceurProjetCible.getArborescenceTestProjetCibleMap();
//			arborescenceRepExtProjetCibleMap 
//				= ArboresceurProjetCible.getArborescenceRepertoiresExternesProjetCibleMap();
			
		}
		
		// ARBORESCENCE D'INFRASTRUCTURE
		/* génère l'arborescence d'INFRASTRUCTURE 
		 * dans le projet cible sur disque. */
		this.genererArborescenceDansProjetCible(arborescence);
		
		// PACKAGE INFO
		/* génère tous les package-info dans le projet cible sur disque. */
		this.genererPackagesInfoDansProjetCible(
				arborescenceMainProjetCibleMap, projetCiblePath);
		
		// JAVADOC
		/* écrit tout le contenu du REPERTOIRE ORIGINE 
		 * javadoc du présent projet 
		 * sur disque sous le même répertoire SOUS LE PROJET CIBLE. */
		this.recopierContenuOrigineDansCibleIdentique(
				"javadoc", pProjetCiblePath);
		
		// APPTECHNIC
		/* écrit tout le contenu du PACKAGE ORIGINE 
		 * apptechnic du présent projet 
		 * sur disque sous le même PACKAGE SOUS LE PROJET CIBLE. */
		this.recopierContenuPackageOrigineDansCibleIdentique(
				"apptechnic", pProjetCiblePath);
		
		// ${racineSourcesJava}/CONFIGURATIONAPPLICATIONMANAGER.java
		/* écrit tout le FICHIER SOURCE 
		 * ConfigurationApplicationManager.java du présent projet 
		 * sur disque sous le même PACKAGE SOUS LE PROJET CIBLE. */
		this.recopierFichierSourceOrigineDansCibleIdentique(
				"ConfigurationApplicationManager.java"
					, pProjetCiblePath);

		// ${racineSourcesJava}/ICONSTANTESAPPLICATIVES.java
		/* écrit tout le FICHIER SOURCE 
		 * IConstantesApplicatives.java du présent projet 
		 * sur disque sous le même PACKAGE SOUS LE PROJET CIBLE. */
		this.recopierFichierSourceOrigineDansCibleIdentique(
				"IConstantesApplicatives.java"
					, pProjetCiblePath);
		
		// ${racineSourcesJava}/model/metier/IExportateurCsv.java
		/* écrit tout le FICHIER SOURCE 
		 * IExportateurCsv.java du présent projet 
		 * sur disque sous le même PACKAGE SOUS LE PROJET CIBLE. */
		this.recopierFichierSourceOrigineDansCibleIdentique(
				"model/metier/IExportateurCsv.java"
					, pProjetCiblePath);
		
		// ${racineSourcesJava}/model/metier/IExportateurJTable.java
		/* écrit tout le FICHIER SOURCE 
		 * IExportateurJTable.java du présent projet 
		 * sur disque sous le même PACKAGE SOUS LE PROJET CIBLE. */
		this.recopierFichierSourceOrigineDansCibleIdentique(
				"model/metier/IExportateurJTable.java"
					, pProjetCiblePath);

		// GENERE LE POM.
		final Path pathAbsoluTemplate 
			= ManagerPaths.getPathAbsoluSrcMainResourcesPresentProjet()
				.resolve("templates/pom/pom-template.txt");
		
		final String groupIdJava 
			= ArboresceurProjetCible.getGroupId();
		
		final String nomProjet 
			= ArboresceurProjetCible.getProjetCibleNom();
		
		final String version = "0.0.1-SNAPSOT";
		
		final String typeProjet = "jar";
		
		final String[] substituants 
			= new String[] {groupIdJava, nomProjet, version, typeProjet};
		
		this.pomService.genererPOMAPartirTemplate(
				pathAbsoluTemplate, pProjetCiblePath, substituants);
		
	} // Fin de generer(...).______________________________________________
	

		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherArborescence(
			final List<Path> pArborescence) {

		/* retourne null si pArborescence == null. */
		if (pArborescence == null) {
			return null;
		}
		final StringBuilder stb = new StringBuilder();

		for (final Path path : pArborescence) {
			if (path != null) {
				stb.append(path.toString());
				stb.append(System.getProperty("line.separator"));
			}
		}

		return stb.toString();

	} // Fin de afficherArborescence(...)._________________________________
	
	
	
	/**
	 * <b>écrit sur disque dans le projet cible l'ensemble de 
	 * l'arborescence d'infrastructure pArborescence</b>.<br/>
	 * <ul>
	 * <li><b>ne crée jamais de doublons.</b></li>
	 * <li>crée chaque répertoire et son ascendance si nécessaire.</li>
	 * </ul>
	 * - ne fait rien si pArborescence == null.<br/>
	 * - ne crée pas un élément de l'arborescence 
	 * si il est déjà existant sur disque.<br/>
	 * <br/>
	 *
	 * @param pArborescence : List&lt;Path&gt;.<br/>
	 * 
	 * @throws Exception
	 */
	private void genererArborescenceDansProjetCible(
							final List<Path> pArborescence) 
										throws Exception {
		
		/* ne fait rien si pArborescence == null. */
		if (pArborescence == null) {
			return;
		}
		
		this.ecrireSurDisque(pArborescence);
		
	} // Fin de genererArborescenceDansProjetCible(...).___________________
	

	
	/**
	 * <b>génère tous les package-info</b> dans la branche <b>main</b> 
	 * (pas de package-info dans les tests) 
	 * d'une arborescence dans un projet cible 
	 * fournie par un GenerateurProjetService.<br/>
	 * <br/>
	 * - ne fait rien si pArborescenceMainProjetCibleMap == null.<br/>
	 * <br/>
	 *
	 * @param pArborescenceMainProjetCibleMap : Map&lt;String, Path&gt; : 
	 * arborescence de la branche main du projet cible.<br/>
	 * @param pProjetCiblePath : Path : Path du projet cible.<br/>
	 * 
	 * @throws Exception
	 */
	private void genererPackagesInfoDansProjetCible(
			final Map<String, Path> pArborescenceMainProjetCibleMap
				, final Path pProjetCiblePath) 
										throws Exception {
		this.ecriveurPackageInfoService
		.genererPackageInfo(
				pArborescenceMainProjetCibleMap, pProjetCiblePath);
		
	} // Fin de genererPackagesInfoDansProjetCible(...).___________________
	
	
	
	/**
	 * <b>recopie tout le contenu de <code>projetCourant/pString</code> 
	 * sous <code>projetCible/pString</code></b>.<br/>
	 * <ul>
	 * <li>Par exemple : <br/>
	 * <code>recopierContenuOrigineDansCibleIdentique(
	 * "javadoc/images", Paths.get("D:/Donnees/eclipse/
	 * eclipseworkspace/test_generation"))</code> 
	 * recopie tout le contenu de D:/Donnees/eclipse/
	 * eclipseworkspace_neon/generation_code/
	 * javadoc/images dans 
	 * D:/Donnees/eclipse/
	 * eclipseworkspace/test_generation/
	 * javadoc/images
	 * </li>
	 * <li>le répertoire résultat dans le projet cible 
	 * <b>a le même nom</b> et la <b>même position relative</b> 
	 * que le répertoire ORIGINE dans le présent projet.</li>
	 * </ul>
	 * - ne fait rien si pString est blank.<br/>
	 * - ne fait rien si pProjetCiblePath == null.<br/>
	 * - ne fait rien si pProjetCiblePath/+pString n'existe pas 
	 * ou n'est pas le bon type de File.<br/>
	 * <br/>
	 *
	 * @param pString : String : 
	 * chemin relatif (par rapport au projet courant) 
	 * de la racine ORIGINE à recopier en respectant 
	 * la même arborescence dans le projet cible.<br/> 
	 * <b>Ne pas commencer pString par un slash</b>. 
	 * Par exemple : "javadoc/images".<br/>
	 * @param pProjetCiblePath : Path : chemin du projet cible.<br/>
	 * 
	 * @throws Exception
	 */
	private void recopierContenuOrigineDansCibleIdentique(
			final String pString
				, final Path pProjetCiblePath) throws Exception {
		
		/* ne fait rien si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return;
		}
		
		/* ne fait rien si pProjetCiblePath == null. */
		if (pProjetCiblePath == null) {
			return;
		}
		
		/* Récupère le path absolu du présent projet. */
		final Path pathAbsoluPresentProjet 
			= this.founirPathAbsoluPresentProjet();
		
		/* Calcule le path ABSOLU du répertoire 
		 * dans le présent projet à recopier. */
		// A METTRE DANS LES RESSOURCES. 
		final Path pathAbsoluARecopier 
			= pathAbsoluPresentProjet.resolve(pString);
		
		final File repOrigineARecopier = pathAbsoluARecopier.toFile();
		
		/* traite la non-existence du répertoire à copier. */
		if (!repOrigineARecopier.exists() 
				|| !repOrigineARecopier.isDirectory()) {
			
			final String message 
				= "LE REPERTOIRE A COPIER N'EXISTE PAS DANS lE PROJET ORIGINE : " 
						+ repOrigineARecopier.getAbsolutePath();
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			return;
		}
		
		/* calcule le path ABSOLU du répertoire cible.*/
		final Path repDestinationPath 
			= this.founirPathAbsoluRepertoireCible(
					pProjetCiblePath, pString);
		
		// RECOPIE SUR DISQUE.
		this.copieurService.copierContenu(
				repOrigineARecopier, repDestinationPath);
		
	} // Fin de recopierContenuOrigineDansCibleIdentique(...)._____________
	

	
	/**
	 * <b>recopie tout le contenu d'un 
	 * <code>PACKAGE projetCourant/racineSources/pString</code> 
	 * sous <code>projetCible/racineSources/pString</code></b>.<br/>
	 * <ul>
	 * <li>évite d'avoir à saisir src/main/java/${groupId} 
	 * dans pString.</li>
	 * <li>Par exemple : <br/>
	 * <code>recopierContenuPackageOrigineDansCibleIdentique(
	 * "apptechnic", Paths.get("D:/Donnees/eclipse/
	 * eclipseworkspace/test_generation"))</code> 
	 * recopie tout le contenu de D:/Donnees/eclipse/
	 * eclipseworkspace_neon/generation_code/
	 * src/main/java/${groupId}/apptechnic dans 
	 * D:/Donnees/eclipse/
	 * eclipseworkspace/test_generation/
	 * src/main/java/${groupId}/apptechnic
	 * </li>
	 * <li>le PACKAGE résultat dans le projet cible 
	 * <b>a le même nom</b> et la <b>même position relative 
	 * (même groupId dans le projet cible)</b> 
	 * que le répertoire ORIGINE dans le présent projet.</li>
	 * </ul>
	 * - ne fait rien si pString est blank.<br/>
	 * - ne fait rien si pProjetCiblePath == null.<br/>
	 * - ne fait rien si pProjetCiblePath/+pString n'existe pas 
	 * ou n'est pas le bon type de File.<br/>
	 * <br/>
	 *
	 * @param pString : String : 
	 * chemin relatif 
	 * (par rapport à la racine des sources du projet courant) 
	 * de la racine ORIGINE à recopier en respectant 
	 * la même arborescence dans le projet cible.<br/> 
	 * <b>Ne pas commencer pString par un slash</b>. 
	 * Par exemple : "apptechnic".<br/>
	 * @param pProjetCiblePath : Path : chemin du projet cible.<br/>
	 * 
	 * @throws Exception
	 */
	private void recopierContenuPackageOrigineDansCibleIdentique(
			final String pString
				, final Path pProjetCiblePath) throws Exception {
		
		/* ne fait rien si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return;
		}
		
		/* ne fait rien si pProjetCiblePath == null. */
		if (pProjetCiblePath == null) {
			return;
		}
		
		/* Récupère le path absolu du présent projet. */
		final Path pathAbsoluPresentProjet 
			= this.founirPathAbsoluPresentProjet();
		
		/* ajoute src/main/java/${groupId} au path du présent projet. */
		final Path pathAbsoluRacineSourcesPresentProjet 
			= pathAbsoluPresentProjet.resolve(
					ArboresceurProjetCible.SRC_MAIN_JAVA_PATH_RELATIF)
				.resolve(ArboresceurProjetCible.getGroupIdPathRelatif());
		
		/* Calcule le path ABSOLU du PACKAGE 
		 * dans le présent projet à recopier. */
		// A METTRE DANS LES RESSOURCES. 
		final Path pathAbsoluARecopier 
			= pathAbsoluRacineSourcesPresentProjet.resolve(pString);
		
		final File repOrigineARecopier = pathAbsoluARecopier.toFile();
		
		/* traite la non-existence du PACKAGE à copier. */
		if (!repOrigineARecopier.exists() 
				|| !repOrigineARecopier.isDirectory()) {
			
			final String message 
				= "LE PACKAGE A COPIER N'EXISTE PAS DANS lE PROJET ORIGINE : " 
						+ repOrigineARecopier.getAbsolutePath();
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			return;
		}

		/* récupère la racine des sources dans le projet cible. */
		final Path pathAbsoluRacineDestination 
			= ArboresceurProjetCible.getRacineSourcesJavaPath();
		
		/* calcule le path ABSOLU du Package cible.*/
		final Path repDestinationPath 
			= pathAbsoluRacineDestination.resolve(pString)
				.toAbsolutePath().normalize();
		
		// RECOPIE SUR DISQUE.
		this.copieurService.copierContenu(
				repOrigineARecopier, repDestinationPath);
		
	} // Fin de recopierContenuPackageOrigineDansCibleIdentique(...).______
	

	
	/**
	 * <b>recopie un <code>FICHIER projetCourant/pString</code> 
	 * sous <code>projetCible/pString</code></b>.<br/>
	 * <ul>
	 * <li><b>ne recopie le fichier origine que 
	 * si il n'existe pas déjà dans le projet cible</b>.</li>
	 * <li>utilise <code>Files.copy(...)</code></li>
	 * <li>Par exemple : <br/>
	 * <code>recopierFichierOrigineDansCibleIdentique(
	 * "javadoc/images/abstract_ids_insee.png"
	 * , Paths.get("D:/Donnees/eclipse/
	 * eclipseworkspace/test_generation"))</code> 
	 * recopie le FICHIER D:/Donnees/eclipse/
	 * eclipseworkspace_neon/generation_code/
	 * javadoc/images/abstract_ids_insee.png dans 
	 * D:/Donnees/eclipse/
	 * eclipseworkspace/test_generation/
	 * javadoc/images/abstract_ids_insee.png
	 * </li>
	 * <li>le FICHIER résultat dans le projet cible 
	 * <b>a le même nom</b> et la <b>même position relative</b> 
	 * que le FICHIER ORIGINE dans le présent projet.</li>
	 * </ul>
	 * - ne fait rien si pString est blank.<br/>
	 * - ne fait rien si pProjetCiblePath == null.<br/>
	 * - ne fait rien si pProjetCiblePath/+pString n'existe pas 
	 * ou n'est pas le bon type de File.<br/>
	 * <br/>
	 *
	 * @param pString : String : 
	 * chemin relatif (par rapport au projet courant) 
	 * du FICHIER ORIGINE à recopier en respectant 
	 * la même arborescence dans le projet cible.<br/> 
	 * <b>Ne pas commencer pString par un slash</b>. 
	 * Par exemple : "javadoc/images/abstract_ids_insee.png".<br/>
	 * @param pProjetCiblePath : Path : chemin du projet cible.<br/>
	 * 
	 * @throws Exception
	 */
	private void recopierFichierOrigineDansCibleIdentique(
			final String pString
				, final Path pProjetCiblePath) throws Exception {
		
		/* ne fait rien si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return;
		}
		
		/* ne fait rien si pProjetCiblePath == null. */
		if (pProjetCiblePath == null) {
			return;
		}
		
		/* Récupère le path absolu du présent projet. */
		final Path pathAbsoluPresentProjet 
			= this.founirPathAbsoluPresentProjet();
		
		/* Calcule le path ABSOLU du FICHIER 
		 * dans le présent projet à recopier. */
		// A METTRE DANS LES RESSOURCES. 
		final Path pathAbsoluARecopier 
			= pathAbsoluPresentProjet.resolve(pString);
		
		final File fileOrigineARecopier = pathAbsoluARecopier.toFile();
		
		/* traite la non-existence du FICHIER à copier. */
		if (!fileOrigineARecopier.exists() || !fileOrigineARecopier.isFile()) {
			
			final String message 
				= "LE FICHIER A COPIER N'EXISTE PAS DANS LE PROJET ORIGINE : " 
						+ fileOrigineARecopier.getAbsolutePath();
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			return;
		}
		
		/* calcule le path ABSOLU du fichier cible.*/
		final Path fileDestinationPath 
			= this.founirPathAbsoluRepertoireCible(
					pProjetCiblePath, pString);
		
		// RECOPIE DU FICHIER SUR DISQUE.
		if (!fileDestinationPath.toFile().exists()) {
			this.copierFichierEtAscendanceSurDisque(
					pathAbsoluARecopier, fileDestinationPath);
		}
		
	} // Fin de recopierFichierOrigineDansCibleIdentique(...)._____________
	
	
	
	/**
	 * <b>recopie un 
	 * <code>FICHIER SOURCE projetCourant/racineSources/pString</code> 
	 * sous <code>projetCible/racineSources/pString</code></b>.<br/>
	 * <ul>
	 * <li>évite d'avoir à saisir src/main/java/${groupId} 
	 * dans pString.</li>
	 * <li><b>ne recopie le FICHIER SOURCE origine que 
	 * si il n'existe pas déjà dans le projet cible</b>.</li>
	 * <li>utilise <code>Files.copy(...)</code></li>
	 * <li>Par exemple : <br/>
	 * <code>recopierFichierSourceOrigineDansCibleIdentique(
	 * "ConfigurationApplicationManager.java", Paths.get("D:/Donnees/eclipse/
	 * eclipseworkspace/test_generation"))</code> 
	 * recopie le FICHIER SOURCE D:/Donnees/eclipse/
	 * eclipseworkspace_neon/generation_code/
	 * src/main/java/${groupId}/ConfigurationApplicationManager.java dans 
	 * D:/Donnees/eclipse/
	 * eclipseworkspace/test_generation/
	 * src/main/java/${groupId}/ConfigurationApplicationManager.java
	 * </li>
	 * <li>le FICHIER SOURCE résultat dans le projet cible 
	 * <b>a le même nom</b> et la <b>même position relative 
	 * (même groupId dans le projet cible)</b> 
	 * que le FICHIER SOURCE ORIGINE dans le présent projet.</li>
	 * </ul>
	 * - ne fait rien si pString est blank.<br/>
	 * - ne fait rien si pProjetCiblePath == null.<br/>
	 * - ne fait rien si pProjetCiblePath/+pString n'existe pas 
	 * ou n'est pas le bon type de File.<br/>
	 * <br/>
	 *
	 * @param pString : String : 
	 * chemin relatif 
	 * (par rapport à la racine des sources du projet courant) 
	 * du FICHIER SOURCE ORIGINE à recopier en respectant 
	 * la même arborescence dans le projet cible.<br/> 
	 * <b>Ne pas commencer pString par un slash</b>. 
	 * Par exemple : "ConfigurationApplicationManager.java".<br/>
	 * @param pProjetCiblePath : Path : chemin du projet cible.<br/>
	 * 
	 * @throws Exception
	 */
	private void recopierFichierSourceOrigineDansCibleIdentique(
			final String pString
				, final Path pProjetCiblePath) throws Exception {
		
		/* ne fait rien si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return;
		}
		
		/* ne fait rien si pProjetCiblePath == null. */
		if (pProjetCiblePath == null) {
			return;
		}
		
		/* Récupère le path absolu du présent projet. */
		final Path pathAbsoluPresentProjet 
			= this.founirPathAbsoluPresentProjet();
		
		/* ajoute src/main/java/${groupId} au path du présent projet. */
		final Path pathAbsoluRacineSourcesPresentProjet 
			= pathAbsoluPresentProjet.resolve(
					ArboresceurProjetCible.SRC_MAIN_JAVA_PATH_RELATIF)
				.resolve(ArboresceurProjetCible.getGroupIdPathRelatif());
		
		/* Calcule le path ABSOLU du FICHIER SOURCE
		 * dans le présent projet à recopier. */
		// A METTRE DANS LES RESSOURCES. 
		final Path pathAbsoluARecopier 
			= pathAbsoluRacineSourcesPresentProjet.resolve(pString);
		
		final File fileOrigineARecopier = pathAbsoluARecopier.toFile();
		
		/* traite la non-existence du FICHIER à copier. */
		if (!fileOrigineARecopier.exists() || !fileOrigineARecopier.isFile()) {
			
			final String message 
				= "LE FICHIER A COPIER N'EXISTE PAS DANS LE PROJET ORIGINE : " 
						+ fileOrigineARecopier.getAbsolutePath();
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			return;
		}
		
		/* récupère la racine des sources dans le projet cible. */
		final Path pathAbsoluRacineDestination 
			= ArboresceurProjetCible.getRacineSourcesJavaPath();
		
		/* calcule le path ABSOLU du FICHIER SOURCE cible.*/
		final Path fileDestinationPath 
			= pathAbsoluRacineDestination.resolve(pString)
				.toAbsolutePath().normalize();
		
		// RECOPIE DU FICHIER SUR DISQUE.
		if (!fileDestinationPath.toFile().exists()) {
			this.copierFichierEtAscendanceSurDisque(
					pathAbsoluARecopier, fileDestinationPath);
		}
			
	}
	

	
	/**
	 * <b>recopie sur disque le fichier situé à 
	 * pPathAbsoluAFichierARecopier à la destination
	 *  pPathAbsoluFichierDestination</b>.<br/>
	 * <ul>
	 * <li><b>crée l'ascendance de pPathAbsoluFichierDestination</b> 
	 * si elle n'existe pas déjà.</li>
	 * <li><b>Remplace le fichier destination si il est déjà existant</b>.</li>
	 * </ul>
	 * - ne fait rien si pPathAbsoluAFichierARecopier == null.<br/>
	 * - ne fait rien si pPathAbsoluAFichierARecopier 
	 * n'existe pas.<br/>
	 * - ne fait rien si pPathAbsoluAFichierARecopier 
	 * n'est pas un fichier simple.<br/>
	 * - ne fait rien si pPathAbsoluFichierDestination == null.<br/>
	 * <br/>
	 *
	 * @param pPathAbsoluAFichierARecopier : Path : 
	 * Path absolu du fichier à recopier (ORIGINE).<br/>
	 * @param pPathAbsoluFichierDestination : Path : 
	 * Path absolu de la DESTINATION du fichier à recopier.<br/>
	 * @throws Exception 
	 */
	private void copierFichierEtAscendanceSurDisque(
			final Path pPathAbsoluAFichierARecopier
				, final Path pPathAbsoluFichierDestination) throws Exception {
		
		/* ne fait rien si pPathAbsoluAFichierARecopier == null. */
		if (pPathAbsoluAFichierARecopier == null) {
			return;
		}
		
		final File fichierARecopier 
			= pPathAbsoluAFichierARecopier.toFile();
		
		/* ne fait rien si pPathAbsoluAFichierARecopier 
		 * n'existe pas. */
		if (!fichierARecopier.exists()) {
			return;
		}
		
		/* ne fait rien si pPathAbsoluAFichierARecopier 
		 * n'est pas un fichier simple. */
		if (!fichierARecopier.isFile()) {
			return;
		}
		
		/* ne fait rien si pPathAbsoluFichierDestination == null. */
		if (pPathAbsoluFichierDestination == null) {
			return;
		}
		
		/* récupération du parent de la destination. */
		final Path pathAbsoluFichierDestinationParent 
			= pPathAbsoluFichierDestination.getParent();
		
		if (pathAbsoluFichierDestinationParent != null) {
			
			/* crée l'ascendance de pPathAbsoluFichierDestination 
			 * si elle n'existe pas déjà. */
			if (!pathAbsoluFichierDestinationParent.toFile().exists()) {
				Files.createDirectories(pathAbsoluFichierDestinationParent);
			}
		}
		
		/* recopie le fichier */
		Files.copy(
				pPathAbsoluAFichierARecopier
					, pPathAbsoluFichierDestination
						, StandardCopyOption.REPLACE_EXISTING);
		
	} // Fin de copierFichierEtAscendanceSurDisque(...).___________________
	

	
	/**
	 * <b>écrit sur disque l'ensemble de 
	 * l'arborescence pArborescence</b>.<br/>
	 * <ul>
	 * <li><b>ne crée jamais de doublons.</b></li>
	 * <li>crée chaque répertoire et son ascendance si nécessaire.</li>
	 * </ul>
	 * - ne fait rien si pArborescence == null.<br/>
	 * - ne crée pas un élément de l'arborescence 
	 * si il est déjà existant sur disque.<br/>
	 * <br/>
	 *
	 * @param pArborescence : List&lt;Path&gt;.<br/>
	 * @throws IOException
	 */
	private void ecrireSurDisque(
			final List<Path> pArborescence) 
										throws IOException {
		
		/* ne fait rien si pArborescence == null. */
		if (pArborescence == null) {
			return;
		}
		
		for (final Path path : pArborescence) {
			
			if (path == null) {
				continue;
			}
			
			/* ne crée pas un élément de l'arborescence 
			 * si il est déjà existant sur disque. */
			final File file = path.toFile();
			if (file.exists()) {
				continue;
			}
			
			/* crée chaque répertoire et son ascendance si nécessaire. */
			Files.createDirectories(path);
			
		}
		
	} // Fin de ecrireSurDisque(...).______________________________________


	
	/**
	 * <b>fournit le path ABSOLU du présent projet Eclipse</b>.<br/>
	 *
	 * @return : Path : 
	 * path ABSOLU du présent projet Eclipse.<br/>
	 */
	private Path founirPathAbsoluPresentProjet() {
		
		final Path pathAbsoluPresentProjet 
		= Paths.get(".").toAbsolutePath().normalize();
		
		return pathAbsoluPresentProjet;
		
	} // Fin de founirPathAbsoluPresentProjet().___________________________
	

	
	/**
	 * <b>fournit le path ABSOLU du répertoire cible</b> 
	 * situé à pProjetCiblePath/ + pString.<br/>
	 * <ul>
	 * <li>utilise <code>pProjetCiblePath.resolve(pString)</code>.</li>
	 * </ul>
	 *
	 * @param pProjetCiblePath : Path : 
	 * Path absolu du projet cible.<br/>
	 * @param pString : String : 
	 * chemin relatif du répertoire cible 
	 * par rapport à pProjetCiblePath.<br/>
	 * 
	 * @return : Path : 
	 * Path absolu du répertoire cible.<br/>
	 */
	private Path founirPathAbsoluRepertoireCible(
				final Path pProjetCiblePath, final String pString) {
		
		final Path repDestinationPath 
			= pProjetCiblePath.resolve(pString)
				.toAbsolutePath().normalize();
		
		return repDestinationPath;
		
	} // Fin de founirPathAbsoluRepertoireCible(...).______________________
	

	
} // FIN DE LA CLASSE GenerateurProjetService.-------------------------------
