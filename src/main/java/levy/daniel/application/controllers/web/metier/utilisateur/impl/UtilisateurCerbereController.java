package levy.daniel.application.controllers.web.metier.utilisateur.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import levy.daniel.application.controllers.web.metier.utilisateur.IUtilisateurCerbereController;
import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.services.metier.utilisateurs.IUtilisateurCerbereService;
import levy.daniel.application.model.services.metier.utilisateurs.UtilisateurCerbereResponse;

/**
 * CLASSE UtilisateurCerbereController :<br/>
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
 * @since 2 mars 2019
 *
 */
@Controller(value="UtilisateurCerbereController")
public class UtilisateurCerbereController 
							implements IUtilisateurCerbereController {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * SERVICE pour l'objet métier.<br/>
	 * injecté par SPRING.<br/>
	 */
	@Autowired(required = true)
	@Qualifier(value="UtilisateurCerbereService")
	private transient IUtilisateurCerbereService utilisateurCerbereService;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereController.class);
	

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereController() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/* CREATE ************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UtilisateurCerbereResponse create(
			final IUtilisateurCerbereDTO pObjectDTO) throws Exception {
		
		/* délègue le stockage d'un OBJET METIER au SERVICE. */
		final UtilisateurCerbereResponse reponse 
			= this.utilisateurCerbereService.create(pObjectDTO);
		
		/* retourne la reponse du SERVICE. */
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
		
		if (this.utilisateurCerbereService != null) {
			return this.utilisateurCerbereService.rechercherRapide(pString);
		} 
		
		System.out.println(" ***** utilisateurCerbereService est NULL ****");
		return null;
		
	} // Fin de rechercherRapide(...)._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IUtilisateurCerbereDTO> findAll() throws Exception {
		
		if (this.utilisateurCerbereService != null) {
			return this.utilisateurCerbereService.findAll();
		} 
		
		return null;
				
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
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereController.--------------------------
