package levy.daniel.application.model.services.metier.utilisateurs.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.dto.metier.utilisateur.UtilisateurCerbereConvertisseurMetierDTO;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.persistence.metier.utilisateur.IUtilisateurCerbereDAO;
import levy.daniel.application.model.services.metier.utilisateurs.IUtilisateurCerbereService;
import levy.daniel.application.model.services.metier.utilisateurs.UtilisateurCerbereResponse;
import levy.daniel.application.model.services.transformeurs.metier.utilisateurs.IUtilisateurCerbereTransformeurService;
import levy.daniel.application.model.services.valideurs.ErreursMaps;
import levy.daniel.application.model.services.valideurs.metier.utilisateurs.IUtilisateurCerbereValideurService;

/**
 * CLASSE UtilisateurCerbereService :<br/>
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
 * @since 28 févr. 2019
 *
 */
@Service(value="UtilisateurCerbereService")
@Transactional
public class UtilisateurCerbereService implements IUtilisateurCerbereService {

	// ************************ATTRIBUTS************************************/

	
	/**
	 * DAO pour l'objet métier.<br/>
	 * injecté par SPRING.<br/>
	 */
	@Autowired(required = true)
	@Qualifier(value="UtilisateurCerbereDAOJPASpring")
	private transient IUtilisateurCerbereDAO utilisateurCerbereDAO;
	
	/**
	 * SERVICE pour l'application des REGLES METIER (RM).
	 */
	@Autowired(required = true)
	@Qualifier(value="UtilisateurCerbereTransformeurService")
	private transient IUtilisateurCerbereTransformeurService transformeurService;
	
	/**
	 * SERVICE pour l'application des REGLES DE GESTION.
	 */
	@Autowired(required = true)
	@Qualifier(value="UtilisateurCerbereValideurService")
	private transient IUtilisateurCerbereValideurService valideurService;

	/**
	 * "STOCKAGE IMPOSSIBLE - il y a des erreurs dans le formulaire".
	 */
	public static final String MESSAGE_ERREUR_FORMULAIRE 
		= "STOCKAGE IMPOSSIBLE - il y a des erreurs dans le formulaire";
	
	/**
	 * "STOCKAGE IMPOSSIBLE - la saisie viole des contraintes 
	 * dans le stockage (doublons, champs, obligatoires, ...)".
	 */
	public static final String MESSAGE_ERREUR_STOCKAGE 
		= "STOCKAGE IMPOSSIBLE - la saisie viole des contraintes "
				+ "dans le stockage (doublons, champs, obligatoires, ...)";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereService.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/* CREATE ************/

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UtilisateurCerbereResponse create(
			final IUtilisateurCerbereDTO pObject) throws Exception {
		
		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		/* instancie une réponse à la requête. */
		final UtilisateurCerbereResponse reponse 
			= new UtilisateurCerbereResponse();
		
		/* délègue à un SERVICE Transformeur l'application 
		 * des  REGLES METIER sur le DTO passé en paramètre 
		 * (le transforme). */
		final IUtilisateurCerbereDTO dtoTransforme 
			= this.transformeurService.transformer(pObject);
		
		/* délègue à un SERVICE Valideur l'application des 
		 * REGLES DE GESTION sur le DTO transformé 
		 * par l'application des REGLES METIER 
		 * (récupère la Map des erreurs pour chaque attribut). */
		final ErreursMaps erreursMaps = 
				this.valideurService.valider(dtoTransforme);
		
		final Map<String, String> errorsMap 
			= erreursMaps.getErrorsMap();
		
		/* retourne une Reponse d'erreur si le SERVICE 
		 * Valideur signale des erreurs. */
		if (!errorsMap.isEmpty()) {
			reponse.setValide(false);
			reponse.setDto(dtoTransforme);
			reponse.ajouterMapAErrorsMap(errorsMap);
			reponse.ajouterMessageAMessagesErrorUtilisateur(
					MESSAGE_ERREUR_FORMULAIRE);
			reponse.setMessageGlobal("ERREURS DE VALIDATION");
			
			return reponse;
		}
		
		/* convertit le DTO transformé en OBJET METIER sinon. */
		final IUtilisateurCerbere objetMetier 
			= UtilisateurCerbereConvertisseurMetierDTO
				.convertirDTOEnObjetMetier(dtoTransforme);
		
		/* délègue le stockage d'un OBJET METIER au DAO. */
		final IUtilisateurCerbere objetStocke 
			= this.utilisateurCerbereDAO.create(objetMetier);
		
		/* récupère la liste des messages d'ERROR UTILISATEUR 
		 * auprès du DAO (doublons, ...). */
		final List<String> messagesErrorUtilisateurDAO
			= this.utilisateurCerbereDAO.getMessagesErrorUtilisateurList();
		
		/* retourne une Reponse d'erreur si le DAO 
		 * signale des erreurs globales (doublons, ...). */
		if (!messagesErrorUtilisateurDAO.isEmpty()) {
			reponse.setValide(false);
			reponse.setDto(dtoTransforme);
			reponse.ajouterMapAErrorsMap(errorsMap);
			reponse.ajouterMessageAMessagesErrorUtilisateur(
					MESSAGE_ERREUR_STOCKAGE);
			reponse.ajouterListMessageAMessagesErrorUtilisateur(
					messagesErrorUtilisateurDAO);
			reponse.setMessageGlobal("ERREURS DE STOCKAGE (DAO)");
			
			return reponse;
		}
		
		/* convertit l'OBJET METIER stocké retourné par le DAO en DTO 
		 * si il n'y a aucune erreur. */
		final IUtilisateurCerbereDTO dtoStocke 
			= UtilisateurCerbereConvertisseurMetierDTO
				.convertirObjetMetierEnDTO(objetStocke);
		
		/* retourne une Reponse positive encapsulant 
		 * le DTO de l'OBJET METIER stocké si il n'y a aucune erreur. */
		reponse.setValide(true);
		reponse.setDto(dtoStocke);
		reponse.setMessageGlobal("OK");
		
		return reponse;
		
	} // Fin de create(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void persist(
			final IUtilisateurCerbereDTO pObject) throws Exception {
		// TODO Auto-generated method stub
		
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long createReturnId(
			final IUtilisateurCerbereDTO pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<IUtilisateurCerbereDTO> saveIterable(
			final Iterable<IUtilisateurCerbereDTO> pList) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbereDTO retrieve(
			final IUtilisateurCerbereDTO pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbereDTO findById(
			final Long pId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long retrieveId(
			final IUtilisateurCerbereDTO pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IUtilisateurCerbereDTO> rechercherRapide(
			final String pString) throws Exception {
		
		final List<IUtilisateurCerbere> resultat 
			= this.utilisateurCerbereDAO.rechercherRapide(pString);
		
		return UtilisateurCerbereConvertisseurMetierDTO
				.convertirListeObjetEnListeDTO(resultat);
		
	} // Fin de rechercherRapide(...)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IUtilisateurCerbereDTO> findAll() throws Exception {	
		
		final List<IUtilisateurCerbere> resultat 
			= this.utilisateurCerbereDAO.findAll();
		
		return UtilisateurCerbereConvertisseurMetierDTO
				.convertirListeObjetEnListeDTO(resultat);
 
	} // Fin de findAll()._________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IUtilisateurCerbereDTO> findAllMax(
			final int pStartPosition
				, final int pMaxResult) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<IUtilisateurCerbereDTO> findAllIterable(
			final Iterable<Long> pIds) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbereDTO update(
			final IUtilisateurCerbereDTO pObject) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbereDTO updateById(
			final Long pId
				, final IUtilisateurCerbereDTO pObjectModifie) 
							throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(
			final IUtilisateurCerbereDTO pObject) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(
			final Long pId) throws Exception {
		// TODO Auto-generated method stub
		
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteByIdBoolean(
			final Long pId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() throws Exception {
		// TODO Auto-generated method stub
		
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteAllBoolean() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteIterable(
			final Iterable<IUtilisateurCerbereDTO> pList) throws Exception {
		// TODO Auto-generated method stub
		
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteIterableBoolean(
			final Iterable<IUtilisateurCerbereDTO> pList) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(
			final IUtilisateurCerbereDTO pObject) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsId(
			final Long pId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long count() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ecrireStockageDansConsole() throws Exception {
		// TODO Auto-generated method stub
		
	}



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String afficherListeObjetsMetier(
			final List<IUtilisateurCerbereDTO> pList) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereService.-----------------------------
