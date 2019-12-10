package levy.daniel.application.model.services.metier.userspring.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import levy.daniel.application.apptechnic.exceptions.technical.impl.ObjetManquantRunTimeException;
import levy.daniel.application.model.persistence.metier.userspring.dao.jpaspring.RoleDao;
import levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl.RoleEntityJPA;
import levy.daniel.application.model.services.metier.userspring.IRoleService;

/**
 * CLASSE RoleService :<br/>
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
 * @since 28 nov. 2019
 *
 */
@Service(value="RoleService")
@Transactional
public class RoleService implements IRoleService {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * RoleDao injecté par SPRING.
	 */
	@Autowired
	@Qualifier(value="RoleDao")
	private RoleDao roleDao;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(RoleService.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public RoleService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public RoleEntityJPA loadRoleByRolename(final String pRolename) 
			throws ObjetManquantRunTimeException {
		
		/* jette une ObjetManquantRunTimeException si pRolename est blank. */
		if (StringUtils.isBlank(pRolename)) {
			
			final String messageBadRolename = "pRolename ne peut être blank";
			throw new ObjetManquantRunTimeException(messageBadRolename);
		}
		
		final String messageKO 
			= "Objet métier introuvable dans le stockage : " + pRolename;
		
		RoleEntityJPA roleEntityJPA = null;
		
		try {
			
			/* délègue au DAO la tâche de trouver l'objet métier
			 *  dans le stockage. */
			roleEntityJPA 
				= this.roleDao.findByRole(pRolename);
			
		} catch (Exception e) {
			
			/* jette une ObjetManquantRunTimeException si l'objet métier 
			 * ne peut être trouvé dans le stockage. */
			throw new ObjetManquantRunTimeException(messageKO);
		}
		
		if (roleEntityJPA == null) {
			
			/* jette une ObjetManquantRunTimeException si l'objet métier 
			 * ne peut être trouvé dans le stockage. */
			throw new ObjetManquantRunTimeException(messageKO);
		}
		
		/* retourne l'objet métier trouvé dans le stockage. */
		return roleEntityJPA;
		
	} //Fin de loadRoleByRolename(...).____________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<RoleEntityJPA> listerUsersParPage(
			int pNumeroPage, int pTaillePage) {
		
		final Page<RoleEntityJPA> page 
			= this.roleDao.findAll(PageRequest.of(pNumeroPage, pTaillePage));
		
		return page;
			
	} // Fin de listerUsersParPage(...)._____________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<RoleEntityJPA> listAll() {
		
		final List<RoleEntityJPA> resultat = this.roleDao.findAll();
		
		return resultat;
				
	} // Fin de listAll()._________________________________________________
	
	
	
} // FIN DE LA CLASSE RoleService.-------------------------------------------
