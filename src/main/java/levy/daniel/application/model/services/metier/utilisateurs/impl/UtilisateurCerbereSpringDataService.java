package levy.daniel.application.model.services.metier.utilisateurs.impl;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.dto.metier.utilisateur.UtilisateurCerbereConvertisseurMetierDTO;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.persistence.metier.utilisateur.IUtilisateurCerbereDAOJPASpringRepository;
import levy.daniel.application.model.persistence.metier.utilisateur.UtilisateurCerbereConvertisseurMetierEntity;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.UtilisateurCerbereEntityJPA;
import levy.daniel.application.model.services.metier.utilisateurs.UtilisateurCerbereResponse;

/**
 * CLASSE UtilisateurCerbereSpringDataService :<br/>
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
 * @since 18 nov. 2019
 *
 */
@Service(value="UtilisateurCerbereSpringDataService")
@Transactional
public class UtilisateurCerbereSpringDataService {

	// ************************ATTRIBUTS************************************/

	/**
	 * DAO pour les UtilisateurCerbere.
	 */
	@Autowired
	private IUtilisateurCerbereDAOJPASpringRepository utilisateurCerbereDAO;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereSpringDataService.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public UtilisateurCerbereSpringDataService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * Sauvegarde un IUtilisateurCerbereDTO dans le stockage.
	 *
	 * @param pUtilisateurCerbereDTO : IUtilisateurCerbereDTO
	 * 
	 * @return UtilisateurCerbereResponse
	 * 
	 * @throws Exception
	 */
	public UtilisateurCerbereResponse create(
			final IUtilisateurCerbereDTO pUtilisateurCerbereDTO) 
										throws Exception {
		
		if (pUtilisateurCerbereDTO == null) {
			
			final String messageGlobalKO 
			= "Impossible d'enregistrer un utilisateur NULL";
			
			final UtilisateurCerbereResponse utilisateurCerbereResponseKO 
			= new UtilisateurCerbereResponse(
					false, null, messageGlobalKO);
			
			utilisateurCerbereResponseKO
				.ajouterMessageAMessagesErrorUtilisateur(messageGlobalKO);
						
			return utilisateurCerbereResponseKO;
		}
		
		if (pUtilisateurCerbereDTO.getEmail() == null) {
			
			final String messageGlobalKO 
			= "Impossible d'enregistrer un utilisateur avec un email NULL";
			
			final UtilisateurCerbereResponse utilisateurCerbereResponseKO 
			= new UtilisateurCerbereResponse(
					false, null, messageGlobalKO);
			
			utilisateurCerbereResponseKO
				.ajouterMessageAMessagesErrorUtilisateur(messageGlobalKO);
						
			return utilisateurCerbereResponseKO;
		}
		
		final IUtilisateurCerbere utilisateurCerbere 
			= UtilisateurCerbereConvertisseurMetierDTO
			.convertirDTOEnObjetMetier(pUtilisateurCerbereDTO);
		
		final UtilisateurCerbereEntityJPA utilisateurCerbereEntityJPA 
			= UtilisateurCerbereConvertisseurMetierEntity
				.convertirObjetMetierEnEntityJPA(utilisateurCerbere);
		
		try {
			
			final UtilisateurCerbereEntityJPA utilisateurCerbereEntityJPASauvergarde 
				= this.utilisateurCerbereDAO.save(utilisateurCerbereEntityJPA);
			
			final IUtilisateurCerbere utilisateurSauvegarde 
				= UtilisateurCerbereConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(
							utilisateurCerbereEntityJPASauvergarde);
			
			final IUtilisateurCerbereDTO utilisateurDTOSauvegarde 
				= UtilisateurCerbereConvertisseurMetierDTO
					.convertirObjetMetierEnDTO(utilisateurSauvegarde);
			
			final String messageGlobalOK 
				= "l'utilisateur a bien été enregistré";
			
			final UtilisateurCerbereResponse utilisateurCerbereResponseOK 
			= new UtilisateurCerbereResponse(
					true, utilisateurDTOSauvegarde, messageGlobalOK);
			
			return utilisateurCerbereResponseOK;
			
		} catch (Exception e) {
			
			final String messageGlobalKO 
			= "Impossible d'enregistrer l'utilisateur " 
					+ pUtilisateurCerbereDTO.toString();
			
			final UtilisateurCerbereResponse utilisateurCerbereResponseKO 
			= new UtilisateurCerbereResponse(
					true, null, messageGlobalKO);
			
			utilisateurCerbereResponseKO
				.ajouterMessageAMessagesErrorUtilisateur(e.getMessage());
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(messageGlobalKO, e);
			}
			
			return utilisateurCerbereResponseKO;
			
		}
		
	} // Fin de create(...)._______________________________________________
	

	
	/**
	 * retourne la 
	 * org.springframework.data.domain.Page&lt;UtilisateurCerbereEntityJPA&gt;
	 * pPage (0-based) comportant pSize enregistrements.
	 * <ul>
	 * <li>Les objets sont triés par nom et prénom.</li>
	 * </ul>
	 *
	 * @param pPage : numéro zero-based de la page
	 * @param pSize : nombre d'enrergistrements dans une page.
	 * 
	 * @return : 
	 * org.springframework.data.domain.Page&lt;UtilisateurCerbereEntityJPA&gt;
	 */
	public Page<UtilisateurCerbereEntityJPA> findAllMax(
			final int pPage, final int pSize) {
				
		final PageRequest pageRequest 
			= PageRequest.of(pPage, pPage, Sort.by("nom", "prenom"));
		
		final Page<UtilisateurCerbereEntityJPA> page = 
				this.utilisateurCerbereDAO.findAll(
						pageRequest);
		
		return page;
		
	} // Fin de findAllMax(...).___________________________________________
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereSpringDataService.-------------------
