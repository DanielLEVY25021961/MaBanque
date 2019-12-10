package levy.daniel.application.model.services.metier.userspring;

import java.util.List;

import org.springframework.data.domain.Page;

import levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl.RoleEntityJPA;
import levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl.UserSpringEntityJPA;

/**
 * INTERFACE IUserSpringService :<br/>
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
 * @since 26 nov. 2019
 *
 */
public interface IUserSpringService {

	
	
	/**
	 * recherche dans le stockage un User par son username.<br/>
	 *
	 * @param pUsername : String : username du User.
	 * 
	 * @return UserDetails : 
	 * org.springframework.security.core.userdetails.UserDetails
	 * 
	 * @throws Exception
	 */
	UserSpringEntityJPA loadUserByUsername(String pUsername) 
			throws Exception;


		
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
	Page<UserSpringEntityJPA> rechercherRapideParPage(
			String pUsername, int pNumeroPage, int pTaillePage) 
													throws Exception;
	
	
	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pUsername
	 * @return : List<UserSpringEntityJPA> :  .<br/>
	 * 
	 * @throws Exception
	 */
	List<UserSpringEntityJPA> rechercherRapide(
									String pUsername) throws Exception;
	
	
	/**
	 * Sauvegarde un User dans le stockage.
	 * <ul>
	 * <li>encode le mot de passe avec un BCryptPasswordEncoder().</li>
	 * </ul>
	 * - retourne une MauvaisParametreRunTimeException si pUsername est blank.<br/>
	 * - retourne une MauvaisParametreRunTimeException si pPassword est blank.<br/>
	 * <br/>
	 *
	 * @param pUsername : String : username.
	 * @param pPassword : String : password.
	 * 
	 * @return : UserSpringEntityJPA : le User sauvé dans le stockage.
	 * 
	 * @throws Exception 
	 */
	UserSpringEntityJPA creerUserSpring(String pUsername, String pPassword) 
			throws Exception;


	
	/**
	 * détruit l'objet métier d'identifiant pUsername dans le stockage.
	 *
	 * @param pUsername : String : 
	 * identifiant du User SPRING à détruire dans le stockage.
	 * 
	 * @throws Exception
	 */
	void delete(String pUsername) throws Exception;
	
	
	
	/**
	 * affecte un (des) ROLE(s) dans le stockage pour un USER pUser.<br/>
	 * crée en même temps l'association entre le User et ses Roles.
	 * <ul>
	 * <li>récupère le User dans le stockage si il existe.</li>
	 * <li>crée le User dans le stockage si il n'existait pas.</li>
	 * <li>recherche le RoleEntityJPA dans le stockage 
	 * pour chaque élément de la liste pRoles (délègue au DAO).</li>
	 * <li>affecte chaque role stocké de pRoles à la Collection de roles 
	 * du User stocké.</li>
	 * <li>Stocke l'OBJET METIER avec sa nouvelle collection de roles.
	 *  <strong>Ceci permet de mettre à jour (update) 
	 *  la table de jointure</strong>. 
	 *  <br/>délègue au DAO la tâche de stocker l'OBJET METIER modifié.</li>
	 *  <li>retourne l' OBJET METIER avec sa nouvelle collection de roles.</li>
	 * </ul>
	 * - retourne une MauvaisParametreRunTimeException si pUser est null.<br/>
	 * - retourne une MauvaisParametreRunTimeException si pRoles est null.<br/>
	 * - retourne une MauvaisParametreRunTimeException si pRoles est vide.<br/>
	 * - retourne une SauvegardeImpossibleRunTimeException si un role de pRoles 
	 * ne peut être affecté au USER.<br/>
	 * <br/>
	 *
	 * @param pUser : UserSpringEntityJPA : user qui aura le rôle.
	 * @param pRoles : List&lt;RoleEntityJPA&gt; : 
	 * liste de roles (ADMIN, USER, ...).
	 * 
	 * @return UserSpringEntityJPA : 
	 * le UserSpringEntityJPA avec sa nouvelle collection de roles.
	 * 
	 * @throws Exception
	 */
	UserSpringEntityJPA affecterRolesAUser(
			UserSpringEntityJPA pUser, List<RoleEntityJPA> pRoles) 
					throws Exception;
	
	
	
	/**
	 * retourne la page de UserSpringEntityJPA de numéro pNumeroPage (0-based) 
	 * contenant pTaillePage enregistrements.
	 *
	 * @param pNumeroPage : int : numéro de la page (0-based).
	 * @param pTaillePage : int nombre d'enregistrements dans la page.
	 * 
	 * @return : Page&lt;UserSpringEntityJPA&gt;
	 */
	Page<UserSpringEntityJPA> listerUsersParPage(
			int pNumeroPage, int pTaillePage);
	
	

	/**
	 * retourne la liste de tous les UserSpringEntityJPA en base.<br/>
	 *
	 * @return : List&lt;UserSpringEntityJPA&gt; : 
	 * liste de tous les UserSpringEntityJPA en base.<br/>
	 */
	List<UserSpringEntityJPA> listAll();

	
	
} // FIN DE L'INTERFACE IUserSpringService.----------------------------------