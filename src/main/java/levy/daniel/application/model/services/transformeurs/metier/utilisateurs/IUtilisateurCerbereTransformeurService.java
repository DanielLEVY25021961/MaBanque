package levy.daniel.application.model.services.transformeurs.metier.utilisateurs;

import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;

/**
 * INTERFACE IUtilisateurCerbereTransformeurService :<br/>
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
 * @author dan Lévy
 * @version 1.0
 * @since 10 mars 2019
 *
 */
public interface IUtilisateurCerbereTransformeurService {

	
	
	/**
	 * transforme un DTO en lui appliquant des REGLES METIER (RM).<br/>
	 *
	 * @param pDto : IUtilisateurCerbereDTO : DTO.<br/>
	 * 
	 * @return : IUtilisateurCerbereDTO : 
	 * DTO transformé par l'application des REGLES METIER (RM).<br/>
	 */
	IUtilisateurCerbereDTO transformer(IUtilisateurCerbereDTO pDto);
	
	

} // FIN DE L'INTERFACE IUtilisateurCerbereTransformeurService.--------------
