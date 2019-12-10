package levy.daniel.application.model.services.transformeurs.metier.utilisateurs.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.services.transformeurs.metier.utilisateurs.IUtilisateurCerbereTransformeurService;

/**
 * CLASSE UtilisateurCerbereTransformeurService :<br/>
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
@Service(value="UtilisateurCerbereTransformeurService")
public class UtilisateurCerbereTransformeurService 
			implements IUtilisateurCerbereTransformeurService {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereTransformeurService.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereTransformeurService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbereDTO transformer(
			final IUtilisateurCerbereDTO pDto) {
		return pDto;
	} // Fin de transformer(...).__________________________________________
	


} // FIN DE LA CLASSE UtilisateurCerbereTransformeurService.-----------------
