package levy.daniel.application.model.services.utilitaires.copieurconcept.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.utilitaires.arboresceurprojet.ArboresceurProjetCible;
import levy.daniel.application.model.services.utilitaires.arboresceurprojet.ArboresceurProjetSource;
import levy.daniel.application.model.services.utilitaires.copieurconcept.ICopieurConceptService;

/**
 * CLASSE CopieurConceptService :<br/>
 * SERVICE CopieurConcept concret.<br/>
 * 
 * Possède une méthode copierConcept(
 * Path pProjetSourcePath, Path pProjetCiblePath, String pNomConcept) 
 * qui <b>copie tous les packages et classes liées au concept pNomConcept</b> 
 * depuis pProjetSourcePath vers pProjetCiblePath.<br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * CLASSES ET PACKAGES liés à un CONCEPT :
 * </span>
 * </p>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * 1 - package <b>objet metier</b>
 * </span>  : le CONCEPT lui-même sous ${racineSources}/model/metier/concept
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/copieurconcept/concept_metier.png" 
 * alt="concept métier" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * 2 - classe <b>test JUnit de l'objet metier</b>
 * </span>  : le TEST JUnit du CONCEPT sous ${racineTests}/model/metier/concept/impl
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/copieurconcept/test_concept_metier.png" 
 * alt="test JUnit concept métier" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * 3 - package <b>DTO l'objet metier</b>
 * </span>  : DTO du CONCEPT sous ${racineSources}/model/dto/metier/concept
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/copieurconcept/dto_concept_metier.png" 
 * alt="DTO du concept métier" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * 4 - classe du <b>test JUnit du DTO l'objet metier</b>
 * </span>  : le TEST JUnit du DTO du CONCEPT sous ${racineTests}/model/dto/metier/concept/impl
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/copieurconcept/test_dto_concept_metier.png" 
 * alt="test JUnit du DTO du concept métier" border="1" align="center" />
 * </div>
 * 
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">
 * PRINCIPE DE FONCTIONNEMENT de copieurconcept :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/copieurconcept/fonctionnement_copieurconcept.png" 
 * alt="principe de fonctionnement de copieurconcept" border="1" align="center" />
 * </div>
 * 
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * valider répertoire, valider repertoire,<br/>
 * valider path répertoire, valider path repertoire,<br/>
 * valider Path répertoire, valider Path repertoire,<br/>
 * répertoire non null, repertoire non null, <br/>
 * répertoire existant, repertoire existant, <br/>
 * répertoire Directory, repertoire isDirectory(), <br/>
 * mettre première lettre en majuscule, capitalize, <br/>
 * mettre en majuscules, mettre en majuscule, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 11 déc. 2018
 *
 */
public class CopieurConceptService implements ICopieurConceptService {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe CopieurConceptService".<br/>
	 */
	public static final String CLASSE_COPIEURCONCEPTSERVICE 
		= "Classe CopieurConceptService";
	
	/**
	 * "le File désigné par ".<br/>
	 */
	public static final String LE_FILE_DESIGNE_PAR 
		= "le File désigné par ";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(CopieurConceptService.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public CopieurConceptService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void copierConcept(
			final Path pProjetSourcePath
				, final Path pProjetCiblePath
					, final String pNomConcept) 
									throws Exception {
		
		// OBJET METIER. ****************
		/* copie l'objet métier. */
		this.copierConceptCoucheMetier(
				pProjetSourcePath, pProjetCiblePath, pNomConcept);
		
		/* copie du test. */
		this.copierTestConceptCoucheMetier(
				pProjetSourcePath, pProjetCiblePath, pNomConcept);
		
		// DTO. **************************
		/* copie le DTO. */
		this.copierConceptCoucheDTOMetier(
				pProjetSourcePath, pProjetCiblePath, pNomConcept);
		
		/* copie du test DTO. */
		this.copierTestConceptCoucheDTOMetier(
				pProjetSourcePath, pProjetCiblePath, pNomConcept);
		
	} // Fin de copierConcept(...).________________________________________
	

	
	/**
	 * <b>copie tout le package (interface, classe concrète, ...) 
	 * de l'objet métier pNomConcept</b> 
	 * depuis le projet source dans le projet cible.<br/>
	 * Par exemple :<br/>
	 * copie tout le package model/metier/user depuis 
	 * le projet source vers le projet cible.<br/>
	 * <br/>
	 * - ne fait rien si pProjetSourcePath n'est pas valide.<br/>
	 * - ne fait rien si pProjetCiblePath n'est pas valide.<br/>
	 * - ne fait rien si pNomConcept n'est pas valide.<br/>
	 * <br/>
	 *
	 * @param pProjetSourcePath : Path : 
	 * Path absolu du projet source.
	 * @param pProjetCiblePath : Path : 
	 * Path absolu du projet cible.
	 * @param pNomConcept : String : 
	 * nom de l'objet métier et de son package dans le projet source.
	 * 
	 * @throws Exception
	 */
	private void copierConceptCoucheMetier(
			final Path pProjetSourcePath
				, final Path pProjetCiblePath
					, final String pNomConcept) 
									throws Exception {
		
		/* ne fait rien si pProjetSourcePath n'est pas valide. */
		if (!this.validerPathRepertoire(pProjetSourcePath)) {
			return;
		}
		
		/* ne fait rien si pProjetCiblePath n'est pas valide. */
		if (!this.validerPathRepertoire(pProjetCiblePath)) {
			return;
		}
		
		/* ne fait rien si pNomConcept n'est pas valide. */
		if (!validerNomConcept(pNomConcept, pProjetSourcePath)) {
			return;
		}
		
		/* récupère le path du package de l'objet métier 
		 * dans le projet source. */
		final Path pathObjetMetierSource 
			= this.fournirConceptMetierSource(
				pProjetSourcePath, pNomConcept);
		
		/* récupère le path du package de l'objet métier 
		 * dans le projet cible. */
		final Path pathObjetMetierCible 
			= this.fournirConceptMetierCible(
					pProjetCiblePath, pNomConcept);
		
		// COPIE DU PACKAGE ET DE SON CONTENU.
		this.recopierPackageEtContenu(
				pathObjetMetierSource, pathObjetMetierCible);
		
	} // Fin de copierConceptCoucheMetier(...).____________________________


	
	/**
	 * <b>copie le test unitaire JUnit 
	 * de l'objet métier pNomConcept</b> 
	 * depuis le projet source dans le projet cible.<br/>
	 * Par exemple :<br/>
	 * copie <code>src/test/java/levy/daniel/application/
	 * model/metier/developpeur/impl/DeveloppeurTest.java</code> depuis 
	 * le projet source vers le projet cible.<br/>
	 * <br/>
	 * - ne fait rien si pProjetSourcePath n'est pas valide.<br/>
	 * - ne fait rien si pProjetCiblePath n'est pas valide.<br/>
	 * - ne fait rien si pNomConcept n'est pas valide.<br/>
	 * <br/>
	 *
	 * @param pProjetSourcePath : Path : 
	 * Path absolu du projet source.
	 * @param pProjetCiblePath : Path : 
	 * Path absolu du projet cible.
	 * @param pNomConcept : String : 
	 * nom de l'objet métier et de son package dans le projet source.
	 * 
	 * @throws Exception
	 */
	private void copierTestConceptCoucheMetier(
			final Path pProjetSourcePath
				, final Path pProjetCiblePath
					, final String pNomConcept) 
									throws Exception {
				
		/* ne fait rien si pProjetSourcePath n'est pas valide. */
		if (!this.validerPathRepertoire(pProjetSourcePath)) {
			return;
		}
		
		/* ne fait rien si pProjetCiblePath n'est pas valide. */
		if (!this.validerPathRepertoire(pProjetCiblePath)) {
			return;
		}
		
		/* ne fait rien si pNomConcept n'est pas valide. */
		if (!validerNomConcept(pNomConcept, pProjetSourcePath)) {
			return;
		}
		
		/* récupération du test à recopier. */
		final Path pathTestObjetMetierSource 
			= this.fournirTestConceptMetierSource(
					pProjetSourcePath, pNomConcept);
		
		/* récupération du test cible. */
		final Path pathTestObjetMetierCible 
			= this.fournirTestConceptMetierCible(
					pProjetCiblePath, pNomConcept);
		
		// COPIE LE TEST.
		this.copierFichier(
				pathTestObjetMetierSource, pathTestObjetMetierCible);
				
	} // Fin de copierTestConceptCoucheMetier(...).________________________
	

	
	/**
	 * <b>copie tout le package (interface, classe concrète, ...) 
	 * du DTO de l'objet métier pNomConcept</b> 
	 * depuis le projet source dans le projet cible.<br/>
	 * Par exemple :<br/>
	 * copie tout le package model/dto/metier/user depuis 
	 * le projet source vers le projet cible.<br/>
	 * <br/>
	 * - ne fait rien si pProjetSourcePath n'est pas valide.<br/>
	 * - ne fait rien si pProjetCiblePath n'est pas valide.<br/>
	 * - ne fait rien si pNomConcept n'est pas valide.<br/>
	 * <br/>
	 *
	 * @param pProjetSourcePath : Path : 
	 * Path absolu du projet source.
	 * @param pProjetCiblePath : Path : 
	 * Path absolu du projet cible.
	 * @param pNomConcept : String : 
	 * nom de l'objet métier et de son package dans le projet source.
	 * 
	 * @throws Exception
	 */
	private void copierConceptCoucheDTOMetier(
			final Path pProjetSourcePath
				, final Path pProjetCiblePath
					, final String pNomConcept) 
									throws Exception {
		
		/* ne fait rien si pProjetSourcePath n'est pas valide. */
		if (!this.validerPathRepertoire(pProjetSourcePath)) {
			return;
		}
		
		/* ne fait rien si pProjetCiblePath n'est pas valide. */
		if (!this.validerPathRepertoire(pProjetCiblePath)) {
			return;
		}
		
		/* ne fait rien si pNomConcept n'est pas valide. */
		if (!validerNomConcept(pNomConcept, pProjetSourcePath)) {
			return;
		}
		
		/* récupère le path du package 
		 * du DTO de l'objet métier 
		 * dans le projet source. */
		final Path pathDTOObjetMetierSource 
			= this.fournirDTOConceptMetierSource(
				pProjetSourcePath, pNomConcept);
		
		/* récupère le path du package 
		 * du DTO de l'objet métier 
		 * dans le projet cible. */
		final Path pathDTOObjetMetierCible 
			= this.fournirDTOConceptMetierCible(
					pProjetCiblePath, pNomConcept);
		
		// COPIE DU PACKAGE ET DE SON CONTENU.
		this.recopierPackageEtContenu(
				pathDTOObjetMetierSource, pathDTOObjetMetierCible);
		
	} // Fin de copierConceptCoucheMetier(...).____________________________


	
	/**
	 * <b>copie le test unitaire JUnit 
	 * du DTO de l'objet métier pNomConcept</b> 
	 * depuis le projet source dans le projet cible.<br/>
	 * Par exemple :<br/>
	 * copie <code>src/test/java/levy/daniel/application/
	 * model/dto/metier/developpeur/impl/DeveloppeurDTOTest.java</code> 
	 * depuis 
	 * le projet source vers le projet cible.<br/>
	 * <br/>
	 * - ne fait rien si pProjetSourcePath n'est pas valide.<br/>
	 * - ne fait rien si pProjetCiblePath n'est pas valide.<br/>
	 * - ne fait rien si pNomConcept n'est pas valide.<br/>
	 * <br/>
	 *
	 * @param pProjetSourcePath : Path : 
	 * Path absolu du projet source.
	 * @param pProjetCiblePath : Path : 
	 * Path absolu du projet cible.
	 * @param pNomConcept : String : 
	 * nom de l'objet métier et de son package dans le projet source.
	 * 
	 * @throws Exception
	 */
	private void copierTestConceptCoucheDTOMetier(
			final Path pProjetSourcePath
				, final Path pProjetCiblePath
					, final String pNomConcept) 
									throws Exception {
		
		/* ne fait rien si pProjetSourcePath n'est pas valide. */
		if (!this.validerPathRepertoire(pProjetSourcePath)) {
			return;
		}
		
		/* ne fait rien si pProjetCiblePath n'est pas valide. */
		if (!this.validerPathRepertoire(pProjetCiblePath)) {
			return;
		}
		
		/* ne fait rien si pNomConcept n'est pas valide. */
		if (!validerNomConcept(pNomConcept, pProjetSourcePath)) {
			return;
		}
		
		/* récupération du test à recopier. */
		final Path pathTestSource 
			= this.fournirTestDTOConceptMetierSource(
					pProjetSourcePath, pNomConcept);
		
		/* récupération du test cible. */
		final Path pathTestCible 
			= this.fournirTestDTOConceptMetierCible(
					pProjetCiblePath, pNomConcept);
		
		// COPIE LE TEST.
		this.copierFichier(
				pathTestSource, pathTestCible);

	} // Fin de copierTestConceptCoucheDTOMetier(...)._____________________
	
	
	
	/**
	 * <b>copie un fichier simple situé à pPathOrigine 
	 * à pPathCible</b>.<br/>
	 * <ul>
	 * <li>crée l'ascendance de pPathCible si elle n'existe pas.</li>
	 * <li>ne copie pPathOrigine à pPathCible que si pPathCible 
	 * n'existe pas déjà.</li>
	 * </ul>
	 * - ne fait rien si pPathOrigine ne désigne pas 
	 * un fichier simple existant.<br/>
	 * - ne fait rien si pPathCible == null.<br/>
	 * <br/>
	 *
	 * @param pPathOrigine : Path : 
	 * Path désignant un fichier simple à copier.
	 * @param pPathCible : Path : 
	 * Path désignant le fichier copié dans la cible.<br/>
	 * 
	 * @throws Exception
	 */
	private void copierFichier(
			final Path pPathOrigine
				, final Path pPathCible) throws Exception {
		
		/* ne fait rien si pPathOrigine ne désigne 
		 * pas un fichier simple existant. */
		if (!validerPathFichier(pPathOrigine)) {
			return;
		}
		
		/* ne fait rien si pPathCible == null. */
		if (pPathCible == null) {
			return;
		}
		
		final Path pPathCibleParent = pPathCible.getParent();
		
		if (pPathCibleParent != null) {
			
			/* crée l'ascendance de pPathCible si elle n'existe pas. */
			if (!pPathCibleParent.toFile().exists()) {
				Files.createDirectories(pPathCibleParent);
			}
		}
				
		/* ne copie pPathOrigine à pPathCible 
		 * que si pPathCible n'existe pas déjà. */
		if (!pPathCible.toFile().exists()) {
			Files.copy(pPathOrigine, pPathCible
					, StandardCopyOption.REPLACE_EXISTING);
		}
		
	} // Fin de copierFichier(...)._____________________________________________
	
	
	
	/**
	 * <b>recopie intégralement un répertoire et son contenu</b> 
	 * depuis pPathPackageSource dans pPathPackageCible.<br/>
	 * Par exemple : <br/>
	 * recopie intégralement le package 
	 * <code>${projetSource}/sourcesJava/model/metier/user</code> 
	 * dans <code>${projetcIBLE}/sourcesJava/model/metier/user</code>
	 * <ul>
	 * <li>crée le repertoire cible si il n'existe pas déjà.</li>
	 * <li>copie le contenu du répertoire source 
	 * sous le répertoire cible.</li>
	 * </ul>
	 * - ne fait rien si pPathPackageSource n'est pas valide.<br/>
	 * - ne fait rien si pPathPackageCible == null.<br/>
	 * <br/>
	 *
	 * @param pPathPackageSource : Path : 
	 * Path du répertoire source à recopier intégralement.<br/>
	 * @param pPathPackageCible : Path : 
	 * Path du répertoire cible dans lequel on recopie 
	 * intégralement le contenu du répertoire source.<br/>
	 * 
	 * @throws Exception
	 */
	private void recopierPackageEtContenu(
			final Path pPathPackageSource
				, final Path pPathPackageCible) throws Exception {
		
		/* ne fait rien si pPathPackageSource n'est pas valide. */
		if (!this.validerPathRepertoire(pPathPackageSource)) {
			return;
		}
		
		/* ne fait rien si pPathPackageCible == null. */
		if (pPathPackageCible == null) {
			return;
		}
		
		/* crée le repertoire cible si il n'existe pas déjà. */
		final File filePackageCible = pPathPackageCible.toFile();
		
		if (!filePackageCible.exists()) {
			Files.createDirectories(pPathPackageCible);
		}
		
		final File filePackageSource = pPathPackageSource.toFile();
		
		/* récupère le contenu sous le répertoire packageSource. */
		final Map<Path, Boolean> contenuMap 
			= this.recupererContenuSous(filePackageSource);

		/* copie le contenu sous le répertoire filePackageSource 
		 * sous le répertoire situé à pPathPackageCible. */
		this.copierContenuVersDestination(
				contenuMap, pPathPackageCible, filePackageSource);
		
	} // Fin de recopierPackageEtContenu(...)._____________________________
	
	
		
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
	 * <b>passe la première lettre d'une pString en majuscule</b>.<br/>
	 * <ul>
	 * <li>utilise <code>StringUtils.capitalize(pString);</code></li>
	 * </ul>
	 * - return null si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String : 
	 * chaine de caractères dont on veut passer 
	 * la première lettre en majuscule.<br/>
	 * 
	 * @return : String : 
	 * String avec la première lettre en majuscule.<br/>
	 */
	private String mettrePremiereLettreEnMajuscule(
			final String pString) {
		
		/* return null si pString est blank. */
		if (StringUtils.isBlank(pString)) {
			return null;
		}
		
		final String resultat = StringUtils.capitalize(pString);
		
		return resultat;
		
	} // Fin de mettrePremiereLettreEnMajuscule(...).______________________
	

	
	/**
	 * <b>Fournit le Path du package d'un OBJET METIER</b> 
	 * pNomConcept 
	 * dans le projet <b>source</b> 
	 * situé à pProjetSourcePath.<br/>
	 * <ul>
	 * <li>l'OBJET METIER doit s'appeler 
	 * <code>this.mettrePremiereLettreEnMajuscule(pNomConcept).java 
	 * </code> dans le projet source.</li>
	 * <li>Par exemple, pour un pNomConcept "developpeur", 
	 * l'objet métier doit s'appeler <code>Developpeur.java</code>.</li>
	 * <li>l'objet métier doit être situé dans un package pNomConcept 
	 * sous 
	 * <code>model/metier/pNomConcept/impl/</code> comme par exemple :
	 * <br/> 
	 * <code>${projetSource}/src/main/java/
	 * levy/daniel/application/
	 * model/metier/developpeur/impl/Developpeur.java</code></li>
	 * </ul>
	 * - retourne null si pProjetSourcePath n'est pas valide.<br/>
	 * <br/>
	 *
	 * @param pProjetSourcePath : Path : 
	 * Path absolu du projet source.
	 * @param pNomConcept : String : 
	 * nom du package de l'objet métier dans le projet source.
	 * 
	 * @return : Path : 
	 * Path du package de l'objet métier pNomConcept 
	 * dans le projet source.<br/>
	 */
	private Path fournirConceptMetierSource(
			final Path pProjetSourcePath, final String pNomConcept) {
		
		/* retourne null si pProjetSourcePath n'est pas valide. */
		if (!validerPathRepertoire(pProjetSourcePath)) {
			return null;
		}
		
		/* positionne ArboresceurProjetSource sur le projet source. */
		ArboresceurProjetSource
			.selectionnerProjetSource(pProjetSourcePath);
		
		final Path pathObjetMetierSource 
			= ArboresceurProjetSource
			.getCoucheModelMetierMainPath()
			.resolve(pNomConcept)
			.toAbsolutePath().normalize();
		
		final File fileTestObjetMetierSource 
			= pathObjetMetierSource.toFile();
	
		if (!fileTestObjetMetierSource.exists()) {
			
			final String message 
				= CLASSE_COPIEURCONCEPTSERVICE 
				+ " - Méthode fournirConceptMetierSource(...) - " 
				+ "le package " 
				+ pathObjetMetierSource 
				+ " n'existe pas dans le stockage.";
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			return null;
		}
		
		return pathObjetMetierSource;
		
	} // Fin de fournirConceptMetierSource(...).___________________________
	

	
	/**
	 * <b>Fournit le Path du package d'un OBJET METIER</b> 
	 * pNomConcept 
	 * dans le projet <b>cible</b> 
	 * situé à pProjetCiblePath.<br/>
	 * <ul>
	 * <li>l'OBJET METIER doit s'appeler 
	 * <code>this.mettrePremiereLettreEnMajuscule(pNomConcept).java 
	 * </code> dans le projet cible.</li>
	 * <li>Par exemple, pour un pNomConcept "developpeur", 
	 * l'objet métier doit s'appeler <code>Developpeur.java</code>.</li>
	 * <li>l'objet métier doit être situé dans un package pNomConcept 
	 * sous 
	 * <code>model/metier/pNomConcept/impl/</code> comme par exemple :
	 * <br/> 
	 * <code>${projetCible}/src/main/java/
	 * levy/daniel/application/
	 * model/metier/developpeur/impl/Developpeur.java</code></li>
	 * </ul>
	 * - retourne null si pProjetCiblePath n'est pas valide.<br/>
	 * <br/>
	 *
	 * @param pProjetCiblePath : Path : 
	 * Path absolu du projet cible.
	 * @param pNomConcept : String : 
	 * nom du package de l'objet métier dans le projet cible.
	 * 
	 * @return : Path : 
	 * Path du package de l'objet métier pNomConcept 
	 * dans le projet cible.<br/>
	 */
	private Path fournirConceptMetierCible(
			final Path pProjetCiblePath, final String pNomConcept) {
		
		/* retourne null si pProjetCiblePath n'est pas valide. */
		if (!validerPathRepertoire(pProjetCiblePath)) {
			return null;
		}
		
		/* positionne ArboresceurProjetCible sur le projet cible. */
		ArboresceurProjetCible
			.selectionnerProjetCible(pProjetCiblePath);
		
		final Path pathObjetMetierCible 
			= ArboresceurProjetCible
			.getCoucheModelMetierMainPath()
			.resolve(pNomConcept)
			.toAbsolutePath().normalize();
		
		return pathObjetMetierCible;
		
	} // Fin de fournirConceptMetierCible(...).___________________________

	
	
	/**
	 * <b>Fournit le Path du test unitaire JUnit</b> relatif 
	 * à un OBJET METIER dans le projet <b>source</b> 
	 * situé à pProjetSourcePath.<br/>
	 * <ul>
	 * <li>le test doit s'appeler 
	 * <code>this.mettrePremiereLettreEnMajuscule(pNomConcept) 
	 * + "Test.java"</code> dans le projet source.</li>
	 * <li>Par exemple, pour un pNomConcept "developpeur", 
	 * le test doit s'appeler <code>DeveloppeurTest.java</code>.</li>
	 * <li>Le test doit être situé sous 
	 * <code>model/metier/pNomConcept/impl/</code> comme par exemple :
	 * <br/> 
	 * <code>${projetSource}/src/test/java/
	 * levy/daniel/application/
	 * model/metier/developpeur/impl/DeveloppeurTest.java</code></li>
	 * </ul>
	 * - retourne null si pProjetSourcePath n'est pas valide.<br/>
	 * <br/>
	 *
	 * @param pProjetSourcePath : Path : 
	 * Path absolu du projet source.
	 * @param pNomConcept : String : 
	 * nom du package de l'objet métier dans le projet source.
	 * 
	 * @return : Path : Path du test unitaire dans le projet source.<br/>
	 */
	private Path fournirTestConceptMetierSource(
			final Path pProjetSourcePath, final String pNomConcept) {
		
		/* retourne null si pProjetSourcePath n'est pas valide. */
		if (!validerPathRepertoire(pProjetSourcePath)) {
			return null;
		}
		
		/* positionne ArboresceurProjetSource sur le projet source. */
		ArboresceurProjetSource
			.selectionnerProjetSource(pProjetSourcePath);
		
		final Path pathTestObjetMetierSource 
		= ArboresceurProjetSource
			.getCoucheModelMetierTestPath()
			.resolve(pNomConcept)
			.resolve("impl")
			.resolve(this.mettrePremiereLettreEnMajuscule(pNomConcept) + "Test.java")
			.toAbsolutePath().normalize();
		
		final File fileTestObjetMetierSource 
		= pathTestObjetMetierSource.toFile();
	
		if (!fileTestObjetMetierSource.exists()) {
			
			final String message 
				= CLASSE_COPIEURCONCEPTSERVICE 
				+ " - Méthode fournirTestConceptMetierSource(...) - " 
				+ "le fichier " 
				+ pathTestObjetMetierSource 
				+ " n'existe pas dans le stockage.";
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			return null;
		}
		
		return pathTestObjetMetierSource;
		
	} // Fin de fournirTestConceptMetierSource(...)._______________________
	

	
	/**
	 * <b>Fournit le Path du test unitaire JUnit</b> relatif 
	 * à un OBJET METIER dans le projet <b>cible</b> 
	 * situé à pProjetCiblePath.<br/>
	 * <ul>
	 * <li>le test doit s'appeler 
	 * <code>this.mettrePremiereLettreEnMajuscule(pNomConcept) 
	 * + "Test.java"</code> dans le projet cible.</li>
	 * <li>Par exemple, pour un pNomConcept "developpeur", 
	 * le test doit s'appeler <code>DeveloppeurTest.java</code>.</li>
	 * <li>Le test doit être situé sous 
	 * <code>model/metier/pNomConcept/impl/</code> comme par exemple :
	 * <br/> 
	 * <code>${projetCible}/src/test/java/
	 * levy/daniel/application/
	 * model/metier/developpeur/impl/DeveloppeurTest.java</code></li>
	 * </ul>
	 * - retourne null si pProjetCiblePath n'est pas valide.<br/>
	 * <br/>
	 *
	 * @param pProjetCiblePath : Path : 
	 * Path absolu du projet cible.
	 * @param pNomConcept : String : 
	 * nom du package de l'objet métier dans le projet cible.
	 * 
	 * @return : Path : Path du test unitaire dans le projet cible.<br/>
	 */
	private Path fournirTestConceptMetierCible(
			final Path pProjetCiblePath, final String pNomConcept) {
		
		/* retourne null si pProjetCiblePath n'est pas valide. */
		if (!validerPathRepertoire(pProjetCiblePath)) {
			return null;
		}
		
		/* positionne ArboresceurProjetCible sur le projet cible. */
		ArboresceurProjetCible
			.selectionnerProjetCible(pProjetCiblePath);
		
		final Path pathTestObjetMetierCible 
		= ArboresceurProjetCible
			.getCoucheModelMetierTestPath()
			.resolve(pNomConcept)
			.resolve("impl")
			.resolve(this.mettrePremiereLettreEnMajuscule(pNomConcept) + "Test.java")
			.toAbsolutePath().normalize();
				
		return pathTestObjetMetierCible;
		
	} // Fin de fournirTestConceptMetierCible(...).________________________
	

	
	/**
	 * <b>Fournit le Path du package du DTO d'un OBJET METIER</b> 
	 * pNomConcept 
	 * dans le projet <b>source</b> 
	 * situé à pProjetSourcePath.<br/>
	 * <ul>
	 * <li>le DTO de l'OBJET METIER doit s'appeler 
	 * <code>this.mettrePremiereLettreEnMajuscule(pNomConcept)+ "DTO.java" 
	 * </code> dans le projet source.</li>
	 * <li>Par exemple, pour un pNomConcept "developpeur", 
	 * le DTO de l'objet métier doit s'appeler <code>DeveloppeurDTO.java</code>.</li>
	 * <li>l'objet métier doit être situé dans un package pNomConcept 
	 * sous 
	 * <code>model/dto/metier/pNomConcept/impl/</code> comme par exemple :
	 * <br/> 
	 * <code>${projetSource}/src/main/java/
	 * levy/daniel/application/
	 * model/dto/metier/developpeur/impl/DeveloppeurDTO.java</code></li>
	 * </ul>
	 * - retourne null si pProjetSourcePath n'est pas valide.<br/>
	 * <br/>
	 *
	 * @param pProjetSourcePath : Path : 
	 * Path absolu du projet source.
	 * @param pNomConcept : String : 
	 * nom du package de l'objet métier dans le projet source.
	 * 
	 * @return : Path : 
	 * Path du package de l'objet métier pNomConcept 
	 * dans le projet source.<br/>
	 */
	private Path fournirDTOConceptMetierSource(
			final Path pProjetSourcePath, final String pNomConcept) {
		
		/* retourne null si pProjetSourcePath n'est pas valide. */
		if (!validerPathRepertoire(pProjetSourcePath)) {
			return null;
		}
		
		/* positionne ArboresceurProjetSource sur le projet source. */
		ArboresceurProjetSource
			.selectionnerProjetSource(pProjetSourcePath);
		
		final Path pathDTOObjetMetierSource 
			= ArboresceurProjetSource
			.getCoucheModelDTOMetierMainPath()
			.resolve(pNomConcept)
			.toAbsolutePath().normalize();
		
		final File fileDTOObjetMetierSource 
			= pathDTOObjetMetierSource.toFile();
	
		if (!fileDTOObjetMetierSource.exists()) {
			
			final String message 
				= CLASSE_COPIEURCONCEPTSERVICE 
				+ " - Méthode fournirDTOConceptMetierSource(...) - " 
				+ "le package " 
				+ pathDTOObjetMetierSource 
				+ " n'existe pas dans le stockage.";
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			return null;
		}
		
		return pathDTOObjetMetierSource;
		
	} // Fin de fournirDTOConceptMetierSource(...).________________________
	

	
	/**
	 * <b>Fournit le Path du package du DTO d'un OBJET METIER</b> 
	 * pNomConcept 
	 * dans le projet <b>cible</b> 
	 * situé à pProjetCiblePath.<br/>
	 * <ul>
	 * <li>le DTO de l'OBJET METIER doit s'appeler 
	 * <code>this.mettrePremiereLettreEnMajuscule(pNomConcept)+ "DTO.java" 
	 * </code> dans le projet cible.</li>
	 * <li>Par exemple, pour un pNomConcept "developpeur", 
	 * le DTO de l'objet métier doit s'appeler <code>DeveloppeurDTO.java</code>.</li>
	 * <li>l'objet métier doit être situé dans un package pNomConcept 
	 * sous 
	 * <code>model/dto/metier/pNomConcept/impl/</code> comme par exemple :
	 * <br/> 
	 * <code>${projetCible}/src/main/java/
	 * levy/daniel/application/
	 * model/dto/metier/developpeur/impl/DeveloppeurDTO.java</code></li>
	 * </ul>
	 * - retourne null si pProjetCiblePath n'est pas valide.<br/>
	 * <br/>
	 *
	 * @param pProjetCiblePath : Path : 
	 * Path absolu du projet cible.
	 * @param pNomConcept : String : 
	 * nom du package de l'objet métier dans le projet cible.
	 * 
	 * @return : Path : 
	 * Path du package de l'objet métier pNomConcept 
	 * dans le projet cible.<br/>
	 */
	private Path fournirDTOConceptMetierCible(
			final Path pProjetCiblePath, final String pNomConcept) {
		
		/* retourne null si pProjetCiblePath n'est pas valide. */
		if (!validerPathRepertoire(pProjetCiblePath)) {
			return null;
		}
		
		/* positionne ArboresceurProjetCible sur le projet cible. */
		ArboresceurProjetCible
			.selectionnerProjetCible(pProjetCiblePath);
		
		final Path pathDTOObjetMetierCible 
			= ArboresceurProjetCible
			.getCoucheModelDTOMetierMainPath()
			.resolve(pNomConcept)
			.toAbsolutePath().normalize();
				
		return pathDTOObjetMetierCible;
		
	} // Fin de fournirDTOConceptMetierCible(...)._________________________

	
	
	/**
	 * <b>Fournit le Path du test unitaire JUnit</b> relatif 
	 * à un DTO d'un OBJET METIER dans le projet <b>source</b> 
	 * situé à pProjetSourcePath.<br/>
	 * <ul>
	 * <li>le test doit s'appeler 
	 * <code>this.mettrePremiereLettreEnMajuscule(pNomConcept) 
	 * + "DTOTest.java"</code> dans le projet source.</li>
	 * <li>Par exemple, pour un pNomConcept "developpeur", 
	 * le test doit s'appeler <code>DeveloppeurDTOTest.java</code>.</li>
	 * <li>Le test doit être situé sous 
	 * <code>model/dto/metier/pNomConcept/impl/</code> comme par exemple :
	 * <br/> 
	 * <code>${projetSource}/src/test/java/
	 * levy/daniel/application/
	 * model/dto/metier/developpeur/impl/DeveloppeurDTOTest.java</code></li>
	 * </ul>
	 * - retourne null si pProjetSourcePath n'est pas valide.<br/>
	 * <br/>
	 *
	 * @param pProjetSourcePath : Path : 
	 * Path absolu du projet source.
	 * @param pNomConcept : String : 
	 * nom du package de l'objet métier dans le projet source.
	 * 
	 * @return : Path : Path du test unitaire dans le projet source.<br/>
	 */
	private Path fournirTestDTOConceptMetierSource(
			final Path pProjetSourcePath, final String pNomConcept) {
		
		/* retourne null si pProjetSourcePath n'est pas valide. */
		if (!validerPathRepertoire(pProjetSourcePath)) {
			return null;
		}
		
		/* positionne ArboresceurProjetSource sur le projet source. */
		ArboresceurProjetSource
			.selectionnerProjetSource(pProjetSourcePath);
		
		final Path pathSource 
		= ArboresceurProjetSource
			.getCoucheModelDTOMetierTestPath()
			.resolve(pNomConcept)
			.resolve("impl")
			.resolve(this.mettrePremiereLettreEnMajuscule(pNomConcept) + "DTOTest.java")
			.toAbsolutePath().normalize();
		
		final File fileSource
			= pathSource.toFile();
	
		if (!fileSource.exists()) {
			
			final String message 
				= CLASSE_COPIEURCONCEPTSERVICE 
				+ " - Méthode fournirTestDTOConceptMetierSource(...) - " 
				+ "le fichier " 
				+ pathSource 
				+ " n'existe pas dans le stockage.";
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
			
			return null;
		}
		
		return pathSource;
		
	} // Fin de fournirTestDTOConceptMetierSource(...).____________________

	
	
	/**
	 * <b>Fournit le Path du test unitaire JUnit</b> relatif 
	 * à un DTO d'un OBJET METIER dans le projet <b>cible</b> 
	 * situé à pProjetCiblePath.<br/>
	 * <ul>
	 * <li>le test doit s'appeler 
	 * <code>this.mettrePremiereLettreEnMajuscule(pNomConcept) 
	 * + "DTOTest.java"</code> dans le projet cible.</li>
	 * <li>Par exemple, pour un pNomConcept "developpeur", 
	 * le test doit s'appeler <code>DeveloppeurDTOTest.java</code>.</li>
	 * <li>Le test doit être situé sous 
	 * <code>model/dto/metier/pNomConcept/impl/</code> comme par exemple :
	 * <br/> 
	 * <code>${projetCible}/src/test/java/
	 * levy/daniel/application/
	 * model/dto/metier/developpeur/impl/DeveloppeurDTOTest.java</code></li>
	 * </ul>
	 * - retourne null si pProjetCiblePath n'est pas valide.<br/>
	 * <br/>
	 *
	 * @param pProjetCiblePath : Path : 
	 * Path absolu du projet cible.
	 * @param pNomConcept : String : 
	 * nom du package de l'objet métier dans le projet cible.
	 * 
	 * @return : Path : Path du test unitaire dans le projet cible.<br/>
	 */
	private Path fournirTestDTOConceptMetierCible(
			final Path pProjetCiblePath, final String pNomConcept) {
		
		/* retourne null si pProjetCiblePath n'est pas valide. */
		if (!validerPathRepertoire(pProjetCiblePath)) {
			return null;
		}
		
		/* positionne ArboresceurProjetCible sur le projet cible. */
		ArboresceurProjetCible
			.selectionnerProjetCible(pProjetCiblePath);
		
		final Path pathCible 
		= ArboresceurProjetCible
			.getCoucheModelDTOMetierTestPath()
			.resolve(pNomConcept)
			.resolve("impl")
			.resolve(this.mettrePremiereLettreEnMajuscule(pNomConcept) + "DTOTest.java")
			.toAbsolutePath().normalize();
		
		return pathCible;
		
	} // Fin de fournirTestDTOConceptMetierCible(...)._____________________


	
	/**
	 * <b>valide pPath</b> 
	 * en retournant <b>true si le Path est valide 
	 * pour un REPERTOIRE (Directory)</b> 
	 * parce qu'il est <i>non null</i> et représente 
	 * un <i>répertoire</i> déjà 
	 * <i>existant</i> dans le stockage.<br/>
	 * <ul>
	 * <li>pPath est <b>invalide</b> si il est <b>null</b>.
	 * <br/>LOG.fatal et retourne false 
	 * si pPath == null.</li>
	 * <li>pPath est <b>invalide</b> si il 
	 * désigne un File <b>inexistant</b> dans le stockage.<br/>
	 * LOG.fatal et retourne false 
	 * si pPath désigne un File inexistant.</li>
	 * <li>pPath est <b>invalide</b> si il ne désigne 
	 * <b>pas un répertoire</b>.<br/>
	 * LOG.fatal et retourne false si pPath désigne un File 
	 * qui n'est pas un répertoire.</li>
	 * </ul>
	 *
	 * @param pPath : Path.<br/>
	 * 
	 * @return : boolean : 
	 * true si le Path est valide pour un répertoire (Directory).<br/>
	 */
	private boolean validerPathRepertoire(
			final Path pPath) {
		
		/* LOG.fatal et retourne false si pPath == null. */
		if (pPath == null) {
			
			if (LOG.isFatalEnabled()) {
				
				final String message 
					= "pPath est null";
				
				/* LOG fatal. */
				LOG.fatal(message);
				
			}
			
			return false;
			
		}
		
		final File file = pPath.toFile();
		
		/* LOG.fatal et retourne false si pPath désigne 
		 * un File inexistant. */
		if (!file.exists()) {
			
			if (LOG.isFatalEnabled()) {
				
				final String message 
					= LE_FILE_DESIGNE_PAR + pPath 
					+ " n'existe pas sur le stockage";
				
				/* LOG fatal. */
				LOG.fatal(message);
				
			}
			
			return false;

		}
		
		/* LOG.fatal et retourne false si pPath désigne 
		 * un File qui n'est pas un répertoire. */
		if (!file.isDirectory()) {
			
			if (LOG.isFatalEnabled()) {
				
				final String message 
					= LE_FILE_DESIGNE_PAR + pPath 
					+ " n'est pas un répertoire (Directory)";
				
				/* LOG fatal. */
				LOG.fatal(message);
				
			}
			
			return false;
			
		}
		
		/* retourne true si pPath designe 
		 * un répertoire (Directory) valide. */
		return true;
		
	} // Fin de validerPathRepertoire(...).________________________________
	

	
	/**
	 * <b>valide pPath</b> 
	 * en retournant <b>true si le Path est valide 
	 * pour un FICHIER (File Simple)</b> 
	 * parce qu'il est <i>non null</i> et représente 
	 * un <i>fichier</i> déjà 
	 * <i>existant</i> dans le stockage.<br/>
	 * <ul>
	 * <li>pPath est <b>invalide</b> si il est <b>null</b>.
	 * <br/>LOG.fatal et retourne false 
	 * si pPath == null.</li>
	 * <li>pPath est <b>invalide</b> si il 
	 * désigne un File <b>inexistant</b> dans le stockage.<br/>
	 * LOG.fatal et retourne false 
	 * si pPath désigne un File inexistant.</li>
	 * <li>pPath est <b>invalide</b> si il ne désigne 
	 * <b>pas un fichier simple</b>.<br/>
	 * LOG.fatal et retourne false si pPath désigne un File 
	 * qui n'est pas un fichier simple.</li>
	 * </ul>
	 *
	 * @param pPath : Path.<br/>
	 * 
	 * @return : boolean : 
	 * true si le Path est valide pour un FICHIER (File Simple).<br/>
	 */
	private boolean validerPathFichier(
			final Path pPath) {
		
		/* LOG.fatal et retourne false si pPath == null. */
		if (pPath == null) {
			
			if (LOG.isFatalEnabled()) {
				
				final String message 
					= "pPath est null";
				
				/* LOG fatal. */
				LOG.fatal(message);
				
			}
			
			return false;
			
		}
		
		final File file = pPath.toFile();
		
		/* LOG.fatal et retourne false si pPath désigne 
		 * un File inexistant. */
		if (!file.exists()) {
			
			if (LOG.isFatalEnabled()) {
				
				final String message 
					= LE_FILE_DESIGNE_PAR + pPath 
					+ " n'existe pas sur le stockage";
				
				/* LOG fatal. */
				LOG.fatal(message);
				
			}
			
			return false;

		}
		
		/* LOG.fatal et retourne false si pPath désigne 
		 * un File qui n'est pas un fichier simple. */
		if (!file.isFile()) {
			
			if (LOG.isFatalEnabled()) {
				
				final String message 
					= LE_FILE_DESIGNE_PAR + pPath 
					+ " n'est pas un fichier simple";
				
				/* LOG fatal. */
				LOG.fatal(message);
				
			}
			
			return false;
			
		}
		
		/* retourne true si pPath designe 
		 * un fichier simple valide. */
		return true;
				
	} // Fin de validerPathFichier(...).___________________________________
	
	
	
	/**
	 * <b>valide pNomConcept</b> 
	 * en retournant <b>true si pNomConcept
	 * représente un objet métier existant</b> 
	 * dans le projet source.<br/>
	 * Un package pour le concept doit exister à 
	 * <code>"model/metier/" + pNomConcept</code>.<br/>
	 * <ul>
	 * <li>retourne false si pProjetSourcePath n'est pas valide.</li>
	 * <li>LOG.fatal et retourne false si pNomConcept est blank.</li>
	 * <li>LOG.fatal et retourne false si il n'y a 
	 * pas de package metier pour le concept.</li>
	 * </ul>
	 *
	 * @param pNomConcept : String : 
	 * nom de l'objet métier et de son package dans le projet source.
	 * @param pProjetSourcePath : Path : 
	 * Path absolu du projet source.
	 * 
	 * @return : boolean : 
	 * true si pNomConcept est valide et désigne 
	 * un objet métier existant dans le projet source.<br/>
	 */
	private boolean validerNomConcept(
			final String pNomConcept
				, final Path pProjetSourcePath) {
		
		/* retourne false si pProjetSourcePath n'est pas valide. */
		if (!this.validerPathRepertoire(pProjetSourcePath)) {
			return false;
		}
		
		/* LOG.fatal et retourne false si pNomConcept est blank. */
		if (StringUtils.isBlank(pNomConcept)) {
			
			if (LOG.isFatalEnabled()) {
				
				final String message 
					= "pNomConcept est null ou vide";
				
				/* LOG fatal. */
				LOG.fatal(message);
				
			}
			
			return false;
			
		}

		ArboresceurProjetSource
			.selectionnerProjetSource(pProjetSourcePath);
	
		final Path pathObjetMetier 
			= ArboresceurProjetSource
					.getCoucheModelMetierMainPath()
					.resolve(pNomConcept)
					.toAbsolutePath().normalize();
		
		final File packageObjetMetier = pathObjetMetier.toFile();
		
		/* LOG.fatal et retourne false si il n'y a 
		 * pas de package metier pour le concept. */
		if (!packageObjetMetier.exists()) {
			
			if (LOG.isFatalEnabled()) {
				
				final String message 
					= "Il n'existe pas de package pour l'objet métier à : " 
							+ pathObjetMetier;
				
				/* LOG fatal. */
				LOG.fatal(message);
				
			}
			
			return false;
			
		}
		
		/* true si pNomConcept est valide et désigne 
		 * un objet métier existant dans le projet source. */
		return true;
		
	} // Fin de validerNomConcept(...).____________________________________
	
	
	
} // FIN DE LA CLASSE CopieurConceptService.---------------------------------
