package levy.daniel.application.model.services.utilitaires.ecriveurpackageinfo;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

import levy.daniel.application.model.services.utilitaires.arboresceurprojet.ArboresceurProjetCible;
import levy.daniel.application.model.services.utilitaires.generateurprojet.impl.GenerateurProjetService;

/**
 * INTERFACE IEcriveurPackageInfoService :<br/>
 * Abstraction des SERVICES chargés d'<b>écrire les package-info sur disque 
 * dans un projet cible</b>.<br/>
 * L'arborescence dans le projet cible doit avoir été générée 
 * par un {@link GenerateurProjetService}.<br/>
 * L'arborescence à copier dans le projet cible est fournie 
 * par un {@link ArboresceurProjetCible}.<br/>
 * <br/>
 * <p>
 * <b><span style="text-decoration: underline;">
 * DIAGRAMME DE CLASSES de EcriveurPackageInfo :
 * </span></b>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/ecriveurpackageinfo/ecriveur_packageinfo.png" 
 * alt="diagramme de classes de ecriveurpackageinfo" border="1" align="center" />
 * </div>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * path absolu du projet eclipse courant, path courant, <br/>
 * Path courant, <br/>
 * pathRelatif = pProjetCiblePath.relativize(pPathDansProjetCible),<br/>
 * path relatif, relativize, soustraire path, <br/>
 * path relatif, <br/>
 * path relatif de path enfant par rapport à path parent,<br/>
 * Path absolu du projet Eclipse courant, path courant, <br/>
 * PATH COURANT, Path Courant, <br/>
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
public interface IEcriveurPackageInfoService {

	
	
	/**
	 * <b>génère tous les package-info</b> dans la branche <b>main</b> 
	 * (pas de package-info dans les tests) 
	 * d'une arborescence dans un projet cible 
	 * fournie par un GenerateurProjetService.<br/>
	 * <br/>
	 * - ne fait rien si pArboMain == null.<br/>
	 * <br/>
	 *
	 * @param pArboMain : Map&lt;String, Path&gt; : 
	 * arborescence de la branche main du projet cible.<br/>
	 * @param projetCiblePath : Path : Path du projet cible.<br/>
	 * 
	 * @throws IOException 
	 */
	void genererPackageInfo(
			Map<String, Path> pArboMain, Path projetCiblePath) 
						throws IOException;
	
	
	
} // FIN DE L'INTERFACE IEcriveurPackageInfoService.-------------------------