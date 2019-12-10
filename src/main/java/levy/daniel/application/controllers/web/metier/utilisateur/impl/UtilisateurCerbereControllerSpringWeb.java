package levy.daniel.application.controllers.web.metier.utilisateur.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;
import levy.daniel.application.model.dto.metier.utilisateur.UtilisateurCerbereConvertisseurMetierDTO;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.persistence.metier.utilisateur.UtilisateurCerbereConvertisseurMetierEntity;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.UtilisateurCerbereEntityJPA;
import levy.daniel.application.model.services.metier.utilisateurs.UtilisateurCerbereResponse;
import levy.daniel.application.model.services.metier.utilisateurs.impl.UtilisateurCerbereSpringDataService;

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
@RequestMapping("UtilisateurCerbereControllerSpringWeb")
@Controller(value="UtilisateurCerbereControllerSpringWeb")
public class UtilisateurCerbereControllerSpringWeb {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * SERVICE pour l'objet métier.<br/>
	 * injecté par SPRING.<br/>
	 */
	@Autowired(required = true)
	@Qualifier(value="UtilisateurCerbereSpringDataService")
	private transient UtilisateurCerbereSpringDataService utilisateurCerbereService;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereControllerSpringWeb.class);
	

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereControllerSpringWeb() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/* CREATE ************/

	/**
	 * <strong>aiguille vers la page de création</strong> d'un utilisateur 
	 * "<code>/metier/utilisateurs/creerUtilisateur.html</code>" 
	 * après chaque action "/UtilisateurCerbereControllerSpringWeb/versCreerUtilisateur".<br/>
	 * Le <i>préfixe</i> du chemin : "/UtilisateurCerbereControllerSpringWeb" 
	 * à concaténer 
	 * avec le path de l'action de la présente méthode 
	 * <i>figure au dessus de la classe</i> et s'applique
	 *  à toutes les méthodes de la présente classe.
	 * <ul>
	 * <li><strong>rafraîchit la liste des utilisateurs</strong> déjà créés 
	 * à chaque appel et l'insère dans pModel 
	 * dans l'attribut <strong>"listUtilisateurs"</strong>.</li>
	 * <li>aiguille vers la page située sous le répertoire 
	 * <code>src/main/resources/templates</code> : 
	 * "/metier/utilisateurs/creerUtilisateur.html"</li>
	 * </ul>
	 * 
	 * @param pModel : org.springframework.ui.Model
	 *
	 * @return : String : "/metier/utilisateurs/creerUtilisateur".<br/>
	 * 
	 * @throws Exception 
	 */
	@GetMapping(value="/versCreerUtilisateur")
	public String versCreerUtilisateur(final Model pModel) throws Exception {
		
		/* retourne la page de création d'un objet métier. */
		return "/metier/utilisateurs/creerUtilisateur";
		
	} // Fin de versCreerUtilisateur().____________________________________
	
	
	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pObjectDTO : IUtilisateurCerbereDTO
	 * @param pModel : org.springframework.ui.Model
	 *  
	 * @return String
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
//	@Secured(value= {"ROLE_ADMIN"})
	public String create(
			final IUtilisateurCerbereDTO pObjectDTO
				, final Model pModel) throws Exception {
		
		/* délègue le stockage d'un OBJET METIER au SERVICE. */
		final UtilisateurCerbereResponse reponseService 
			= this.utilisateurCerbereService.create(pObjectDTO);
		
		/* ajoute la reponse du SERVICE ou MODEL. */
		pModel.addAttribute("reponseService", reponseService);
		
		/* retourne la page de création d'un objet métier. */
		return "/metier/utilisateurs/creerUtilisateur";
					
	} // Fin de create(...)._______________________________________________



//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public void persist(
//			final IUtilisateurCerbereDTO pObject) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Long createReturnId(
//			final IUtilisateurCerbereDTO pObject) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Iterable<IUtilisateurCerbereDTO> saveIterable(
//			final Iterable<IUtilisateurCerbereDTO> pList) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public IUtilisateurCerbereDTO retrieve(
//			final IUtilisateurCerbereDTO pObject) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public IUtilisateurCerbereDTO findById(
//			final Long pId) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Long retrieveId(
//			final IUtilisateurCerbereDTO pObject) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public List<IUtilisateurCerbereDTO> rechercherRapide(
//			final String pString) throws Exception {
//		
//		return null;
//		if (this.utilisateurCerbereService != null) {
//			return this.utilisateurCerbereService.rechercherRapide(pString);
//		} 
//		
//		System.out.println(" ***** utilisateurCerbereService est NULL ****");
//		return null;
//		
//	} // Fin de rechercherRapide(...)._____________________________________
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public List<IUtilisateurCerbereDTO> findAll() throws Exception {
//		
//		if (this.utilisateurCerbereService != null) {
//			return this.utilisateurCerbereService.findAll();
//		} 
//		
//		return null;
//				
//	} // Fin de findAll()._________________________________________________




	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pPage
	 * @param pSize
	 * @return List<IUtilisateurCerbereDTO>
	 * 
	 * @throws Exception :  :  .<br/>
	 */
	@GetMapping("/listEtudiantsParPage")
	public List<IUtilisateurCerbereDTO> findAllMax(
			final int pPage
				, final int pSize) throws Exception {

		final Page<UtilisateurCerbereEntityJPA> page 
			= this.utilisateurCerbereService.findAllMax(pPage, pSize);
		
		final List<UtilisateurCerbereEntityJPA> listeEntities 
			= page.getContent();
		
		final List<IUtilisateurCerbere> listeObjetsMetier 
			=  UtilisateurCerbereConvertisseurMetierEntity
				.convertirListEntitiesJPAEnModel(listeEntities);
		
		final List<IUtilisateurCerbereDTO> listeDTO 
			= UtilisateurCerbereConvertisseurMetierDTO
				.convertirListeObjetEnListeDTO(listeObjetsMetier);
		
		return listeDTO;
		
	} // Fin de findAllMax(...).___________________________________________



//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Iterable<IUtilisateurCerbereDTO> findAllIterable(
//			final Iterable<Long> pIds) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public IUtilisateurCerbereDTO update(
//			final IUtilisateurCerbereDTO pObject) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public IUtilisateurCerbereDTO updateById(
//			final Long pId
//				, final IUtilisateurCerbereDTO pObjectModifie) 
//												throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public boolean delete(
//			final IUtilisateurCerbereDTO pObject) throws Exception {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public void deleteById(
//			final Long pId) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public boolean deleteByIdBoolean(
//			final Long pId) throws Exception {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public void deleteAll() throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public boolean deleteAllBoolean() throws Exception {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public void deleteIterable(
//			final Iterable<IUtilisateurCerbereDTO> pList) throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public boolean deleteIterableBoolean(
//			final Iterable<IUtilisateurCerbereDTO> pList) throws Exception {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public boolean exists(
//			final IUtilisateurCerbereDTO pObject) throws Exception {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public boolean existsId(
//			final Long pId) throws Exception {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public Long count() throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public void ecrireStockageDansConsole() throws Exception {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public String afficherListeObjetsMetier(
//			final List<IUtilisateurCerbereDTO> pList) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereController.--------------------------
