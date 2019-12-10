package levy.daniel.application.model.services.utilitaires.copieurconcept;

import java.nio.file.Path;

/**
 * INTERFACE ICopieurConceptService :<br/>
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
 * @since 13 déc. 2018
 *
 */
public interface ICopieurConceptService {

	
	
	/**
	 * .<br/>
	 * <ul>
	 * <li></li>
	 * </ul>
	 *
	 * @param pProjetSourcePath
	 * @param pProjetCiblePath
	 * @param pNomConcept
	 * @throws Exception : void :  .<br/>
	 */
	void copierConcept(Path pProjetSourcePath, Path pProjetCiblePath, String pNomConcept) throws Exception;
	

	
} // FIN DE L'INTERFACE ICopieurConceptService.------------------------------