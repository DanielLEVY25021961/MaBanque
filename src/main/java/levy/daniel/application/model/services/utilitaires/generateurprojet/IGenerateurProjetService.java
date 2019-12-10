package levy.daniel.application.model.services.utilitaires.generateurprojet;

import java.nio.file.Path;
import java.util.List;

import levy.daniel.application.model.services.utilitaires.arboresceurprojet.ArboresceurProjetCible;
import levy.daniel.application.model.services.utilitaires.ecriveurpackageinfo.IEcriveurPackageInfoService;

/**
 * INTERFACE IGenerateurProjetService :<br/>
 * Abstraction factorisant les comportements des 
 * GenerateurProjetService concrets.<br/>
 * GenerateurProjetService est chargé <b>d'écrire 
 * une arborescence de projet MAVEN SIMPLE (sans artefact) 
 * sur disque</b> dans un projet cible.<br/>
 * Toutes les couches (GroupId, apptechnic, controllers, model, vues...)
 * ainsi que leurs sous-couches et les répertoires externes (data, logs, ...) 
 * sont générés dans le projet cible.<br/>
 * L'arborescence à copier dans le projet cible est fournie 
 * par un {@link ArboresceurProjetCible}
 * <br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * Création des répértoires et packages sous un projet cible par GenerateurProjetService :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/generateurprojet/generateurprojet_resultat.png" 
 * alt="création des répertoires par GenerateurProjetService" border="1" align="center" />
 * </div>
 * <br/>
 * <br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE CLASSES de GenerateurProjetService :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/generateurprojet/generateurprojet_service.png" 
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
 * <br/>
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
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 26 nov. 2018
 *
 */
public interface IGenerateurProjetService {

	/**
	 * <b>Ecrit sur disque une arborescence de projet 
	 * MAVEN SIMPLE (sans artefact)</b>.<br/>
	 * <ul>
	 * <li>Ne permet PAS de spécifier un GroupId MAVEN 
	 * pour le projet cible à générer.<br/> 
	 * Utilise le GroupId MAVEN par défaut GROUPID_PAR_DEFAUT 
	 * dans ArboresceurProjetCible (levy.daniel.application).</li>
	 * <li>utilise un ArboresceurProjetCible pour obtenir 
	 * l'arborescence de projet à créer.</li>
	 * <li>écrit l'arborescence sur disque.</li>
	 * <li>écrit tous les package-info sur disque 
	 * (délègue à un {@link IEcriveurPackageInfoService}).</li>
	 * </ul>
	 * - ne fait rien si pProjetCiblePath == null.<br/>
	 * - ne fait rien si le projet cible n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pProjetCiblePath : Path : 
	 * path du projet cible dans lequel générer 
	 * une infrastructure de projet.<br/>
	 * 
	 * @throws Exception
	 */
	void generer(Path pProjetCiblePath) throws Exception;

	
	
	/**
	 * <b>Ecrit sur disque une arborescence de projet 
	 * MAVEN SIMPLE (sans artefact)</b>.<br/>
	 * <ul>
	 * <li>Permet de spécifier un GroupId MAVEN 
	 * pour le projet cible à générer.</li>
	 * <li>utilise un ArboresceurProjetCible pour obtenir 
	 * l'arborescence de projet à créer.</li>
	 * <li>écrit l'arborescence sur disque.</li>
	 * <li>écrit tous les package-info sur disque 
	 * (délègue à un {@link IEcriveurPackageInfoService}).</li>
	 * </ul>
	 * - ne fait rien si pProjetCiblePath == null.<br/>
	 * - ne fait rien si le projet cible n'existe pas.<br/>
	 * <br/>
	 *
	 * @param pProjetCiblePath : Path : 
	 * path du projet cible dans lequel générer 
	 * une infrastructure de projet.<br/>
	 * @param pGroupId : String : 
	 * GroupId à appliquer dans le projet cible.<br/> 
	 * GroupId par défaut GROUPID_PAR_DEFAUT dans 
	 * ArboresceurProjetCible si pGroupId est blank.<br/>
	 * 
	 * @throws Exception 
	 */
	void generer(Path pProjetCiblePath, String pGroupId) 
											throws Exception;

	
	
	/**
	 * Fournit une String pour l'affichage d'une List&lt;Path&gt;.<br/>
	 * - retourne null si pArborescence == null.<br/>
	 * <br/>
	 *
	 * @param pArborescence : List&lt;Path&gt;.<br/> 
	 * 
	 * @return : String.<br/>
	 */
	String afficherArborescence(List<Path> pArborescence);

	
	
} // FIN DE L'INTERFACE IGenerateurProjetService.----------------------------