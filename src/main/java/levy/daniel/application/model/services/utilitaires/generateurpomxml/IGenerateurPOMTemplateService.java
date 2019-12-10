package levy.daniel.application.model.services.utilitaires.generateurpomxml;

import java.io.File;
import java.nio.file.Path;

import levy.daniel.application.IConstantesSautsLigne;

/**
 * INTERFACE IGenerateurPOMTemplateService :<br/>
 * .<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 7 déc. 2018
 *
 */
public interface IGenerateurPOMTemplateService extends IConstantesSautsLigne {


	
	/**
	 * <b>génère un pom.xml <i>sous forme de String</i> 
	 * à partir d'un template de POM 
	 * et de valeurs à substituer</b>.<br/>
	 * <ul>
	 * <li>le Path pPathAbsoluTemplate doit
	 * viser un fichier TEMPLATE DE POM <b>situé sous 
	 * src/main/resources</b> du <i>présent projet</i>.<br/>
	 * Par exemple : 
	 * <code>src/main/resources/templates/pom/pom-template.txt</code></li>
	 * <li><b>substitue</b> les valeurs contenues dans le tableau 
	 * pSubstituants aux variables du template de POM.</li>
	 * <li>String[] pSubstituants doit respecter 
	 * <code>new String[] {"${groupId}", "${nomProjet}"
	 * , "${version}", "${packaging}"};</code> comme défini 
	 * dans VARIABLES_A_SUBSTITUER.</li>
	 * </ul>
	 * - retourne null si pPathAbsoluTemplate == null.<br/>
	 * - retourne null si pPathAbsoluTemplate n'est pas 
	 * sous src/main/resources du présent projet.<br/>
	 * - retourne null si le template situé à 
	 * pPathAbsoluTemplate n'existe pas.<br/>
	 * - retourne null si le File situé à pPathAbsoluTemplate 
	 * n'est pas un fichier simple.<br/>
	 * - retourne null si pSubstituants == null.<br/>
	 * <br/>
	 *
	 * @param pPathAbsoluTemplate : Path : 
	 * Path ABSOLU du template de POM à interpréter.<br/>
	 * @param pSubstituants : String[] : 
	 * valeurs à substituer aux variables du template de POM.<br/>
	 * Doit respecter <code>{"${groupId}", "${nomProjet}"
	 * , "${version}", "${packaging}"};</code>.<br/>
	 * Par exemple : <code>new String[] {"levy.daniel.application"
	 * , "test_generation", "0.0.1-SNAPSOT", "jar"}</code><br/>
	 * <br/>
	 * 
	 * @return : String : pom.xml sous forme de String.<br/>
	 * 
	 * @throws Exception
	 */
	String genererPOMStringAPartirTemplate(
			Path pPathAbsoluTemplate
				, String... pSubstituants) throws Exception;
	
	
	
	/**
	 * <b>génère un FICHIER pom.xml sous la racine d'un projet cible 
	 * situé à pProjetCiblePath à partir d'un template de POM 
	 * et de valeurs à substituer</b>.<br/>
	 * <ul>
	 * <li>le Path pPathAbsoluTemplate doit
	 * viser un fichier TEMPLATE DE POM <b>situé sous 
	 * src/main/resources</b> du <i>présent projet</i>.</li>
	 * <li><b>substitue</b> les valeurs contenues dans le tableau 
	 * pSubstituants aux variables du template de POM.</li>
	 * <li>String[] pSubstituants doit respecter 
	 * <code>new String[] {"${groupId}", "${nomProjet}"
	 * , "${version}", "${packaging}"};</code></li>
	 * <li>crée le fichier pom.xml dansle projet cible 
	 * si il n'existe pas.</li>
	 * </ul>
	 * - retourne null si pPathAbsoluTemplate == null.<br/>
	 * - retourne null si pPathAbsoluTemplate n'est pas 
	 * sous src/main/resources du présent projet.<br/>
	 * - retourne null si le template situé à 
	 * pPathAbsoluTemplate n'existe pas.<br/>
	 * - retourne null si le File situé à pPathAbsoluTemplate 
	 * n'est pas un fichier simple.<br/>
	 * - retourne null si pProjetCiblePath == null.<br/>
	 * - retourne null si le projet cible situé 
	 * à pProjetCiblePath n'existe pas.<br/>
	 * - retourne null si le projet cible situé 
	 * à pProjetCiblePath n'est pas un répertoire.<br/>
	 * - retourne null si pSubstituants == null.<br/>
	 * <br/>
	 *
	 * @param pPathAbsoluTemplate : Path : 
	 * Path ABSOLU du template de POM à interpréter.<br/>
	 * @param pProjetCiblePath : Path : 
	 * Path ABSOLU du projet cible dans lequel créer un pom.xml.<br/>
	 * @param pSubstituants : String[] : 
	 * valeurs à substituer aux variables du template de POM.<br/>
	 * Doit respecter <code>{"${groupId}", "${nomProjet}"
	 * , "${version}", "${packaging}"};</code>.<br/>
	 * Par exemple : <code>new String[] {"levy.daniel.application"
	 * , "test_generation", "0.0.1-SNAPSOT", "jar"}</code><br/>
	 * <br/>
	 * 
	 * @return File : 
	 * pom.xml dans le projet cible (sous sa racine).<br/>
	 *  
	 * @throws Exception
	 */
	File genererPOMAPartirTemplate(
			Path pPathAbsoluTemplate
				, Path pProjetCiblePath
					, String... pSubstituants) 
									throws Exception;
	

	
} // FIN DE L'INTERFACE IGenerateurPOMTemplateService.-----------------------