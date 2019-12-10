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

import levy.daniel.application.apptechnic.exceptions.technical.impl.MauvaisParametreRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.SauvegardeImpossibleRunTimeException;
import levy.daniel.application.model.persistence.metier.userspring.dao.jpaspring.RoleDao;
import levy.daniel.application.model.persistence.metier.userspring.dao.jpaspring.UserSpringDao;
import levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl.RoleEntityJPA;
import levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl.UserSpringEntityJPA;
import levy.daniel.application.model.services.metier.userspring.IUserSpringService;

/**
 * CLASSE UserSpringService :<br/>
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
 * @since 25 nov. 2019
 *
 */
@Service(value="UserSpringService")
@Transactional
//public class UserSpringService 
//						implements UserDetailsService, IUserSpringService {
public class UserSpringService 
		implements IUserSpringService {
	
	// ************************ATTRIBUTS************************************/
	
	/**
	 * UserSpringDao injecté par SPRING.
	 */
	@Autowired
	@Qualifier(value="UserSpringDao")
    private UserSpringDao userDao;
	
	/**
	 * RoleDao injecté par SPRING.
	 */
	@Autowired
	@Qualifier(value="RoleDao")
	private RoleDao roleDao;
	
	/**
	 * PasswordEncoder pour les mots de passe (SPRING SECURITY 5.2.1).
	 */
//	@Autowired
//    private PasswordEncoder passwordEncoder;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(UserSpringService.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public UserSpringService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public UserDetails loadUserByUsername(
//			final String pUsername) throws Exception {
//		
//		
//		/* retourne une MauvaisParametreRunTimeException si pUsername est blank. */
//		if (StringUtils.isBlank(pUsername)) {
//			
//			final String message = "Vous devez fournir un username NON NULL";
//			
//			throw new MauvaisParametreRunTimeException(message);
//		}
//
//		UserSpringEntityJPA user = null;
//		
//		try {
//			
//			user = this.userDao.findByUsername(pUsername);
//			
//		} catch (Exception e) {
//			
//			final String message 
//    		= "Impossible de trouver dans le stockage un User avec pour username : " 
//    				+ pUsername;
//    	
//			throw new UsernameNotFoundException(message, e);
//        
//		}
//		
//        if (user == null) {
//        	
//        	final String message 
//        		= "Impossible de trouver dans le stockage un User avec pour username : " 
//        				+ pUsername;
//        	
//            throw new UsernameNotFoundException(message);
//        }
//        
//        return user;
//        
//	} // Fin de loadUserByUsername(...).___________________________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserSpringEntityJPA loadUserByUsername(
			final String pUsername) throws Exception {
		
		
		/* retourne une MauvaisParametreRunTimeException si pUsername est blank. */
		if (StringUtils.isBlank(pUsername)) {
			
			final String message = "Vous devez fournir un username NON NULL";
			
			throw new MauvaisParametreRunTimeException(message);
		}

		UserSpringEntityJPA user = null;
		
		try {
			
			user = this.userDao.findByUsername(pUsername);
			
		} catch (Exception e) {
			
			final String message 
    		= "Impossible de trouver dans le stockage un User avec pour username : " 
    				+ pUsername;
    	
			throw new SauvegardeImpossibleRunTimeException(message, e);
        
		}
		
        if (user == null) {
        	
        	final String message 
        		= "Impossible de trouver dans le stockage un User avec pour username : " 
        				+ pUsername;
        	
            throw new SauvegardeImpossibleRunTimeException(message);
        }
        
        return user;
        
	} // Fin de loadUserByUsername(...).___________________________________
	

	
	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pUsername
	 * @param pNumeroPage
	 * @param pTaillePage
	 * 
	 * @return Page<UserSpringEntityJPA>
	 * 
	 * @throws Exception
	 */
	@Override
	public Page<UserSpringEntityJPA> rechercherRapideParPage(
			final String pUsername 
				, final int pNumeroPage
					, final int pTaillePage) throws Exception {
				
		return this.userDao.rechercherRapideParPage(
				pUsername + "%", PageRequest.of(pNumeroPage, pTaillePage));
		
	}

	
	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pUsername
	 * @return : List<UserSpringEntityJPA> :  .<br/>
	 */
	@Override
	public List<UserSpringEntityJPA> rechercherRapide(
			final String pUsername) throws Exception {
				
		return this.userDao.rechercherRapide(pUsername + "%");
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserSpringEntityJPA creerUserSpring(
			final String pUsername, final String pPassword) throws Exception {

		
		/* retourne une MauvaisParametreRunTimeException si pUsername est blank. */
		if (StringUtils.isBlank(pUsername)) {
			
			final String message 
			= "Vous devez fournir un username NON BLANK (NULL ou VIDE)";
			
			throw new MauvaisParametreRunTimeException(message);
			
		}

		
		/* retourne une MauvaisParametreRunTimeException si pPassword est blank. */
		if (StringUtils.isBlank(pPassword)) {
			
			final String message 
				= "Vous devez fournir un pPassword NON BLANK (NULL ou VIDE)";
			
			throw new MauvaisParametreRunTimeException(message);
			
		}

		UserSpringEntityJPA userSauve = null;
		
		/* encode le mot de passe avec un BCryptPasswordEncoder(). */
//		final String passwordEncode = this.passwordEncoder.encode(pPassword);
		
//		final UserSpringEntityJPA user 
//			= new UserSpringEntityJPA(pUsername, passwordEncode, true);
		
		final UserSpringEntityJPA user 
		= new UserSpringEntityJPA(pUsername, pPassword, true);
		
		try {
			
			userSauve = this.userDao.save(user);
			
		} catch (Exception e) {
			
			final String message 
				= "impossible de créer le User " 
					+ user.toString() 
					+ " dans le stockage";
			
			throw new SauvegardeImpossibleRunTimeException(message, e);
		}
		
		return userSauve;
		
	} // Fin de creerUserSpring(...).______________________________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(
			final String pUsername) 
					throws Exception {
		
		/* jette une MauvaisParametreRunTimeException si pUsername est blank. */
		if (StringUtils.isBlank(pUsername)) {
			
			final String message 
				= "vous devez renseigner le username avant de détruire un USER";
			
			throw new MauvaisParametreRunTimeException(message);
		}
		
		final UserSpringEntityJPA user = this.loadUserByUsername(pUsername);
			
		this.userDao.delete(user);
		
	} // Fin de delete(...)._______________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserSpringEntityJPA affecterRolesAUser(
			final UserSpringEntityJPA pUser
			, final List<RoleEntityJPA> pRoles) throws Exception {

		/* retourne une MauvaisParametreRunTimeException si pUser est null. */
		if (pUser == null) {

			final String message 
				= "Vous devez renseigner un USER NON NULL";

			throw new MauvaisParametreRunTimeException(message);
		}

		/* retourne une MauvaisParametreRunTimeException si pRoles est null. */
		if (pRoles == null) {

			final String message 
				= "Vous devez fournir une liste de ROLES NON NULL";

			throw new MauvaisParametreRunTimeException(message);
		}

		/* retourne une MauvaisParametreRunTimeException si pRoles est vide. */
		if (pRoles.isEmpty()) {

			final String message 
				= "Vous devez fournir une liste de ROLES NON VIDE";

			throw new MauvaisParametreRunTimeException(message);
		}

		UserSpringEntityJPA userStocke = null;

		try {

			/* récupère le User dans le stockage si il existe. */
			userStocke = this.loadUserByUsername(pUser.getUsername());

		} catch (Exception e) {

			/* crée le User dans le stockage si il n'existait pas. */
			userStocke 
				= this.creerUserSpring(
						pUser.getUsername(), pUser.getPassword());

		}

		RoleEntityJPA roleStocke = null;

		// BOUCLE SUR LES ROLES.*****************
		for (final RoleEntityJPA role : pRoles) {

			try {

				/*
				 * recherche le RoleEntityJPA dans le stockage 
				 * pour chaque élément de la liste pRoles.
				 * Délègue au DAO.
				 */
				roleStocke = this.roleDao.findByRole(role.getRole());

				if (roleStocke == null) {

					final String message 
						= "le role " 
								+ role.getRole() 
								+ " n'est pas stocké. Enregistrez le d'abord.";

					/*
					 * retourne une SauvegardeImpossibleRunTimeException 
					 * si un role de pRoles n'est pas déjà stocké.
					 */
					throw new SauvegardeImpossibleRunTimeException(message);
				}

				/*
				 * affecte chaque role stocké de pRoles 
				 * à la Collection de roles du User stocké.
				 */
				userStocke.ajouterRole(roleStocke);

			} catch (Exception e) {

				final String message 
					= "impossible d'affecter le role : " 
							+ role.getRole() 
							+ " au USER : " 
							+ userStocke.getUsername();

				/*
				 * retourne une SauvegardeImpossibleRunTimeException si un role de pRoles ne peut être affecté au USER.
				 */
				throw new SauvegardeImpossibleRunTimeException(message, e);
			}

		}

		/*
		 * Stocke l'OBJET METIER avec sa nouvelle collection de roles. 
		 * Ceci permet de mettre à jour (update) la table de jointure.
		 * délègue au DAO la tâche de stocker l'OBJET METIER modifié.
		 */
		this.userDao.save(userStocke);

		/* retourne l' OBJET METIER avec sa nouvelle collection de roles. */
		return userStocke;

	} // Fin de affecterRolesAUser(...).___________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<UserSpringEntityJPA> listerUsersParPage(
			final int pNumeroPage
				, final int pTaillePage) {
		
		final Page<UserSpringEntityJPA> page 
			= this.userDao.findAll(PageRequest.of(pNumeroPage, pTaillePage));
		
		return page;
		
	} // Fin de listerUsersParPage(...).___________________________________
	
	
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<UserSpringEntityJPA> listAll() {
		
		final List<UserSpringEntityJPA> resultat = this.userDao.findAll();
		
		return resultat;
		
	} // Fin de listAll()._________________________________________________
	
	
	
} // FIN DE LA CLASSE UserSpringService.-------------------------------------
