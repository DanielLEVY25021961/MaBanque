package levy.daniel.application.controllers.web.metier.userspring.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl.RoleEntityJPA;
import levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl.UserSpringEntityJPA;
import levy.daniel.application.model.services.metier.userspring.IRoleService;
import levy.daniel.application.model.services.metier.userspring.IUserSpringService;

/**
 * CLASSE UserSpringController :<br/>
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
@Controller(value="UserSpringController")
@RequestMapping(value="/UserSpringController")
public class UserSpringController {

	// ************************ATTRIBUTS************************************/

	/**
	 * "3".
	 */
	public static final String DEFAULT_SIZE_PAGE = "3";
	
	/**
	 * 3.
	 */
	public static final int DEFAULT_SIZE_PAGE_INT = 3;
	
	/**
	 * IUserSpringService.
	 */
	@Autowired
	@Qualifier("UserSpringService")
	private IUserSpringService userSpringService;
	
	@Autowired
	@Qualifier("RoleService")
	private IRoleService roleService;
		
	/**
	 * PasswordEncoder pour les mots de passe (SPRING SECURITY 5.2.1).
	 */
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UserSpringController.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public UserSpringController() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
		
	/**
	 * Execute une recherche rapide de la liste paginée des objets métier 
	 * à chaque lancement de l'action 
	 * <code>"/UserSpringController/rechercherRapidePagineUserSpring"</code>.<br/>
	 * aiguille vers la VUE APPELANTE passée en paramètre.
	 * <br/>
	 * <ul>
	 * <li>rafraîchit la liste paginée des objets métier 
	 * recherchés à chaque appel.</li>
	 * <li>passe au Model les attributs <strong>'username'
	 * , 'listeObjetsDansPage' et 'pages' 
	 * <i>si il y a plus d'une page</i></strong></li>
	 * <li>passe au Model le chemin de la VUE APPELANTE 
	 * dans l'attribut <strong>'vueAppelante'</strong>.</li>
	 * <li></li>
	 * </ul>
	 *
	 * @param pUsername : String : 
	 * identifiant des objets métier inséré dans la zone de recherche rapide. 
	 * @param pNumeroPage : int : numéro (0-based) de la page
	 * @param pTaillePage : int : nombre d'enregistrements dans la page.
	 * @param pVueAppelante 
	 * @param pModel : org.springframework.ui.Model
	 * 
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	@GetMapping(value="/rechercherRapidePagineUserSpring")
	public String rechercherRapidePagineUserSpring(
			@RequestParam(name="username", required=false, defaultValue="") final String pUsername
			, @RequestParam(name="page", defaultValue="0") final int pNumeroPage
			, @RequestParam(name="size", defaultValue=DEFAULT_SIZE_PAGE) final int pTaillePage
			, @RequestParam(name="vueAppelante", required=false, defaultValue="/metier/userspring/deleteUserspring") final String pVueAppelante
			, final Model pModel) throws Exception {
		
		/* rafraîchit la liste paginée des objets métier recherchés à chaque appel. */
		this.rafraichirListeRechercheePaginee(
				pUsername, pNumeroPage, pTaillePage, pModel);
		
		/* passe au Model le chemin de la VUE APPELANTE dans l'attribut 'vueAppelante'. */
		pModel.addAttribute("vueAppelante", pVueAppelante);
		
		/* aiguille vers la VUE APPELANTE. */
		return pVueAppelante;		
		
	} // Fin de rechercherRapidePagineUserSpring(...)._____________________
	
	/**
	 * aiguille vers la page d'administration des Utilisateurs SPRING 
	 * <code>"/metier/userspring/userspring.html"</code> 
	 * à chaque lancement de l'action 
	 * <code>"/UserSpringController/versPageUserSpring"</code>.<br/>
	 * La path "/UserSpringController" est déjà indiqué dans l'annotation 
	 * RequestMapping au dessus de la classe.<br/>
	 * Fournit à la VUE la liste des objets métier déjà stockés 
	 * rafraîchie à chaque appel.
	 * <ul>
	 * <li>rafraîchit la liste paginée des objets métier à chaque appel.</li>
	 * <li>récupère la liste d'objets metier dans la 
	 * <code>org.springframework.data.domain.Page</code> via  
	 * (<code>getContent()</code>)</li>
	 * <li>récupère le nombre total de pages d'objets métier 
	 * dans le stockage auprès de la 
	 * <code>org.springframework.data.domain.Page</code>.</li>
	 * <li>passe le tableau des numéros (0-based) des pages de Users 
	 * au Model dans l'attribut <strong>'pages'</strong> 
	 * <i>si il y a plus d'une page</i>.</li>
	 * <li>passe la liste des objets métier dans la Page au Model 
	 * dans l'attribut <strong>'listeObjetsDansPage'</strong>.</li>
	 * <li>aiguille vers la VUE d'ADMINISTRATION des Objets métier.</li>
	 * </ul>
	 * 
	 * @param pNumeroPage : int : numéro (0-based) de la page
	 * @param pTaillePage : int : nombre d'enregistrements dans la page.
	 * @param pModel : org.springframework.ui.Model
	 *
	 * @return : String : "/metier/userspring/userspring".<br/>
	 * 
	 * @throws Exception 
	 */
	@GetMapping(value="/versPageUserSpring")
	public String versPageUserSpring(
			@RequestParam(name="page", defaultValue="0") final int pNumeroPage
			, @RequestParam(name="size", defaultValue=DEFAULT_SIZE_PAGE) final int pTaillePage
				, final Model pModel) throws Exception {
		
		/* rafraîchit la liste paginée des objets métier à chaque appel. */
		this.rafraichirListeUsersPaginee("", pNumeroPage, pTaillePage, pModel);
		
		/* aiguille vers la VUE des Users. */
		return "/metier/userspring/userspring";
		
	} // Fin de versPageUserSpring().______________________________________
	
		
	
	/**
	 * aiguille vers la page de création des Utilisateurs SPRING 
	 * <code>"/metier/userspring/createUserspring.html"</code> 
	 * à chaque lancement de l'action 
	 * <code>"/UserSpringController/versPageCreateUserSpring"</code>.<br/>
	 * La path "/UserSpringController" est déjà indiqué dans l'annotation 
	 * RequestMapping au dessus de la classe.<br/>
	 * Fournit à la VUE la liste des objets métier déjà stockés 
	 * rafraîchie à chaque appel.
	 * <ul>
	 * <li>rafraîchit la liste paginée des objets métier à chaque appel.</li>
	 * <li>récupère la liste d'objets metier dans la 
	 * <code>org.springframework.data.domain.Page</code> via  
	 * (<code>getContent()</code>)</li>
	 * <li>récupère le nombre total de pages d'objets métier 
	 * dans le stockage auprès de la 
	 * <code>org.springframework.data.domain.Page</code>.</li>
	 * <li>passe le tableau des numéros (0-based) des pages de Users 
	 * au Model dans l'attribut <strong>'pages'</strong> 
	 * <i>si il y a plus d'une page</i>.</li>
	 * <li>passe la liste des objets métier dans la Page au Model 
	 * dans l'attribut <strong>'listeObjetsDansPage'</strong>.</li>
	 * <li>délègue au SERVICE la tâche de rapatrier 
	 * tous les rôles dans le stockage.</li>
	 * <li>passe au Model la liste des roles 
	 * dans l'attribut <strong>'roles'</strong>.</li>
	 * <li>aiguille vers la VUE de CREATION des Objets métier.</li>
	 * </ul>
	 * 
	 * @param pUsername : String : identifiant de l'objet métier à créer 
	 * (null lors du premier appel).
	 * @param pNumeroPage : int : numéro (0-based) de la page.
	 * @param pTaillePage : int : nombre d'enregistrements dans la page.
	 * @param pModel : org.springframework.ui.Model
	 *
	 * @return : String : "/metier/userspring/createUserspring".<br/>
	 * 
	 * @throws Exception 
	 */
	@GetMapping(value="/versPageCreateUserSpring")
	public String versPageCreateUserSpring(
			@RequestParam(name="username", required=false) final String pUsername
			, @RequestParam(name="page", defaultValue="0") final int pNumeroPage
			, @RequestParam(name="size", defaultValue=DEFAULT_SIZE_PAGE) final int pTaillePage
				, final Model pModel) throws Exception {
		
		/* rafraîchit la liste paginée des objets métier à chaque appel. */
		this.rafraichirListeUsersPaginee(
				pUsername, pNumeroPage, pTaillePage, pModel);
		
		/* délègue au SERVICE la tâche de rapatrier 
		 * tous les rôles dans le stockage. */
		final List<RoleEntityJPA> roles = this.roleService.listAll();
		
		/* passe au Model la liste des roles dans l'attribut 'roles'. */
		pModel.addAttribute("roles", roles);
		
		/* aiguille vers la VUE de CREATION des Objets métier. */
		return "/metier/userspring/createUserspring";
		
	} // Fin de versPageCreateUserSpring().________________________________
	
	
	
	/**
	 * Sauvegarde un Objet Métier dans le stockage 
	 * et aiguille vers la page de création des Users SPRING 
	 * <code>"/metier/userspring/createUserspring.html"</code> à chaque appel 
	 * de l'action <code>/UserSpringController/creerUserSpring</code>.
	 * <ul>
	 * <li>Encode le password avec le passwordEncoder.</li>
	 * <li>délègue au SERVICE la tâche de stockage de l'objet métier.</li>
	 * <li>le SERVICE encode le mot de passe avec un 
	 * BCryptPasswordEncoder().</li>
	 * <li>constitue dans tous les cas la réponse (boolean) du service.</li>
	 * <li>constitue dans tous les cas le message utilisateur.</li>
	 * <li>passe l'éventuelle Exception au Model dans l'attribut 
	 * <strong>'exceptionService'</strong>.</li>
	 * <li>passe l'objet métier stocké au Model dans l'attribut 
	 * <strong>'username'</strong> 
	 * (peut être null en cas d'Exception).</li>
	 * <li>passe le résultat de la création au Model 
	 * dans l'attribut <strong>'reponseServiceCreation'</strong> (boolean).</li>
	 * <li>passe le message utilisateur au Model 
	 * dans l'attribut <strong>'messageCreation'</strong>.</li>
	 * <li>aiguille vers la VUE de création des Users SPRING
	 * <code>"/metier/userspring/createUserspring.html"</code>.</li>
	 * </ul>
	 * - retourne une MauvaisParametreRunTimeException 
	 * si pUsername est blank.<br/>
	 * - retourne une MauvaisParametreRunTimeException 
	 * si pPassword est blank.<br/>
	 * <br/>
	 *
	 * @param pUsername : String : username.
	 * @param pPassword : String : password.
	 * @param pNumeroPage 
	 * @param pTaillePage 
	 * @param pModel : org.springframework.ui.Model 
	 * 
	 * @return : String : "/metier/userspring/createUserspring".
	 * 
	 * @throws Exception 
	 */
	@PostMapping(value="/creerUserSpring")
	public String creerUserSpring(
			@RequestParam(name="username", required=true) final String pUsername
				, @RequestParam(name="password", required=true) final String pPassword
				, @RequestParam(name="page", defaultValue="0") final int pNumeroPage
				, @RequestParam(name="size", defaultValue=DEFAULT_SIZE_PAGE) final int pTaillePage
					, final Model pModel) 
						throws Exception {
		
		UserSpringEntityJPA userSpringStocke = null;
		boolean reponseServiceCreation = false;
		String messageCreation = null;
		
		try {
			
			/* Encode le password avec le passwordEncoder. */
			String passwordEncode = this.passwordEncoder.encode(pPassword);
			
			/* délègue au SERVICE la tâche de stockage de l'objet métier. */
			userSpringStocke 
				= this.userSpringService.creerUserSpring(
						pUsername, passwordEncode);
			
			/* constitue dans tous les cas la réponse (boolean) du service. */
			reponseServiceCreation = true;
			
			/* constitue dans tous les cas le message utilisateur. */
			messageCreation = "le USER a bien été créé dans le stockage : " 
					+ pUsername;
						
		} catch (Exception e) {
			
			/* constitue dans tous les cas la réponse (boolean) du service. */
			reponseServiceCreation = false;
			
			/* constitue dans tous les cas le message utilisateur. */
			messageCreation = "impossible de créer le USER dans le stockage - " 
					+ e.getMessage();
			
			/* passe l'éventuelle Exception au Model dans l'attribut 
			 * 'exceptionService'. */
			pModel.addAttribute("exceptionService", e);
			
		}
		
		/* passe l'objet métier stocké au Model dans l'attribut 
		 * 'username' (peut être null en cas d'Exception). */
		pModel.addAttribute("username", pUsername);
		
		/* passe le résultat de la création au Model 
		 * dans l'attribut 'reponseService' (boolean). */
		pModel.addAttribute("reponseServiceCreation", reponseServiceCreation);
		
		/* passe le message utilisateur au Model 
		 * dans l'attribut 'message'. */
		pModel.addAttribute("messageCreation", messageCreation);
					
		/* aiguille vers la VUE de création des Users. */
		return this.versPageCreateUserSpring(
				pUsername, pNumeroPage, pTaillePage, pModel);
		
	} // Fin de creerUserSpring(...).______________________________________


	
	
	/**
	 * .<br/>
	 * "/UserSpringController/versPageDeleteUserSpring"<br/>
	 *
	 * @param pUsername
	 * @param pNumeroPage
	 * @param pTaillePage
	 * @param pModel
	 * @return : String :  .<br/>
	 * 
	 * @throws Exception 
	 */
	@GetMapping(value="/versPageDeleteUserSpring")
	public String versPageDeleteUserSpring(
			@RequestParam(name="username", required=false, defaultValue="") final String pUsername
			, @RequestParam(name="page", defaultValue="0") final int pNumeroPage
			, @RequestParam(name="size", defaultValue=DEFAULT_SIZE_PAGE) final int pTaillePage
				, final Model pModel) throws Exception {
		
		/* rafraîchit la liste paginée des objets métier recherchés à chaque appel. */
		this.rafraichirListeRechercheePaginee(
				pUsername, pNumeroPage, pTaillePage, pModel);
		
		/* aiguille vers la VUE de DESTRUCTION des Users SPRING. */
		return "/metier/userspring/deleteUserspring";		
				
	} // Fin de versPageDeleteUserSpring(...)._____________________________

	
	
	/**
	 * détruit le User d'ID pUsername dans le stockage.<br/>
	 * aiguille vers la page de destruction des objets métier 
	 * <code>"/metier/userspring/deleteUserspring.html"</code> 
	 * à chaque lancement de l'action 
	 * <code>"/UserSpringController/deleteUserSpring"</code>.<br/>
	 * La path "/UserSpringController" est déjà indiqué dans l'annotation 
	 * RequestMapping au dessus de la classe.<br/>
	 * <ul>
	 * <li>délègue au SERVICE la tâche de destruction de l'objet métier 
	 * dans le stockage.</li>
	 * <li>constitue dans tous les cas le message utilisateur.</li>
	 * <li>constitue dans tous les cas la réponse (boolean) du service.</li>
	 * <li>rafraîchit la liste paginée des objets métier recherchés 
	 * et alimente le Model avec <strong>'pages', 'listeObjetsDansPage' 
	 * et 'username' à "" pour que la recherche rapide retourne 
	 * tous les utilisateurs en base</strong>.</li>
	 * <li>passe au Model la réponse du service dans l'attribut 
	 * <strong>'reponseServiceDestruction'</strong>.</li>
	 * <li>passe au Model le message du service dans l'attribut 
	 * <strong>'messageDestruction'</strong>.</li>
	 * <li>aiguille vers la VUE de DESTRUCTION des Objets Metier.</li>
	 * </ul>
	 *
	 * @param pUsername : String : ID de l'objet métier.
	 * @param pModel : org.springframework.ui.Model
	 * 
	 * @return String : "/metier/userspring/deleteUserspring" 
	 * 
	 * @throws Exception
	 */
	@GetMapping(value="/deleteUserSpring")
	public String delete(
			@RequestParam(name="username", required=true) final String pUsername
			, final Model pModel) 
					throws Exception {
				
		String messageDestruction = "";
		boolean reponseServiceDestruction = false;
		
		try {
			
			/* délègue au SERVICE la tâche de destruction 
			 * de l'objet métier dans le stockage. */
			this.userSpringService.delete(pUsername);
			
			/* constitue dans tous les cas le message utilisateur. */
			messageDestruction 
			= messageDestruction 
			+ "L'objet métier a bien été détruit dans le stockage : " 
					+ pUsername;
			
			/* constitue dans tous les cas la réponse (boolean) du service. */
			reponseServiceDestruction = true;
			
		} catch (Exception e) {
			
			/* constitue dans tous les cas le message utilisateur. */
			messageDestruction 
			= messageDestruction 
			+ "Impossible de détruire l'objet métier dans le stockage : " 
					+ pUsername + " " + e.getMessage();
			
			/* constitue dans tous les cas la réponse (boolean) du service. */
			reponseServiceDestruction = false;
			
		}
		
		/* rafraîchit la liste paginée des objets métier recherchés 
		 * et alimente le Model avec 'pages', 'listeObjetsDansPage' 
		 * et 'username' à "" pour que la recherche rapide 
		 * retourne tous les utilisateurs en base. */
		this.rafraichirListeRechercheePaginee(
				"", 0, DEFAULT_SIZE_PAGE_INT, pModel);
		
		/* passe au Model la réponse du service dans l'attribut 
		 * 'reponseServiceDestruction'. */
		pModel.addAttribute(
				"reponseServiceDestruction", reponseServiceDestruction);
		
		/* passe au Model le message du service 
		 * dans l'attribut 'messageDestruction'. */
		pModel.addAttribute("messageDestruction", messageDestruction);
				
		/* aiguille vers la VUE de DESTRUCTION des Objets Metier. */
		return "/metier/userspring/deleteUserspring";
		
	} // Fin de delete(...)._______________________________________________
	
	
	
	/**
	 * affecte un (des) ROLE(s) de pRoles <i>présents dans le stockage</i> 
	 * à un OBJET METIER pUser à chaque lancement de l'action 
	 * <code>"/UserSpringController/affecterRolesAUser"</code>.<br/>
	 * aiguille vers la page 
	 * <code>"/metier/userspring/createUserspring.html"</code>.<br/>
	 * crée en même temps l'association entre l'OBJET METIER et ses Roles.
	 * <ul>
	 * <li>récupère le User dans le stockage si il existe.</li>
	 * <li>recherche le RoleEntityJPA dans le stockage 
	 * pour chaque élément de la liste pRoles (délègue au DAO).</li>
	 * <li>affecte chaque role stocké de pRolesChoisis à la Collection de roles 
	 * du User stocké.</li>
	 * <li>Stocke l'OBJET METIER avec sa nouvelle collection de roles.
	 *  <strong>Ceci permet de mettre à jour (update) 
	 *  la table de jointure</strong>.</li>
	 *  <li>délègue au SERVICE la tâche d'affecter les rôles au USER.</li> 
	 * </ul>
	 * - retourne une MauvaisParametreRunTimeException si pUser est null.<br/>
	 * - retourne une MauvaisParametreRunTimeException si pRole est blank.<br/>
	 * - retourne une SauvegardeImpossibleRunTimeException 
	 * si le role ne peut être stocké.<br/>
	 * <br/>
	 *
	 * @param pUsername : String : 
	 * ID METIER de l'objet métier auquel affecter les ROLES dans pRoles.
	 * @param pRolesChoisis : List&lt;String&gt; : 
	 * liste des RoleEntityJPA à affecter au UserSpringEntityJPA.
	 * @param pNumeroPage : int : numéro (0-based) de la page.
	 * @param pTaillePage : int : nombre d'enregistrements dans la page.
	 * @param pModel : org.springframework.ui.Model
	 * 
	 * @return String : org.springframework.ui.Model
	 * 
	 * @throws Exception
	 */
	@PostMapping(value="/affecterRolesAUser")	
	public String affecterRolesAUser(
			@RequestParam(name="username", required=true) final String pUsername
				, @RequestParam(name="rolesChoisis", required=true) List<String> pRolesChoisis
				, @RequestParam(name="page", defaultValue="0") final int pNumeroPage
				, @RequestParam(name="size", defaultValue=DEFAULT_SIZE_PAGE) final int pTaillePage
				, final Model pModel) 
					throws Exception {
		
		String messageRole = null;
		boolean reponseServiceRole = false;
		UserSpringEntityJPA userSpringEntityJPA = null;
		List<RoleEntityJPA> rolesAffectes = new ArrayList<RoleEntityJPA>();
		
		userSpringEntityJPA 
			= this.userSpringService.loadUserByUsername(pUsername);
		
		if (pRolesChoisis == null || pRolesChoisis.isEmpty()) {
			
			messageRole = "vous devez sélectionner au moins un Rôle";
			
			reponseServiceRole = false;
			
			pModel.addAttribute("messageRole", messageRole);
			pModel.addAttribute("reponseServiceRole", reponseServiceRole);
			
			/* aiguille vers la VUE de CREATION des Objets métier. */
			return "/metier/userspring/createUserspring";
			
		}
			
		for (final String role : pRolesChoisis) {

			/* délègue au RoleSERVICE la tâche de trouver les roles 
			 * dans le stockage. */
			final RoleEntityJPA roleEntityJPA 
				= this.roleService.loadRoleByRolename(role);

			rolesAffectes.add(roleEntityJPA);
		}
		
		
		try {
			
			/* délègue au SERVICE la tâche d'affecter les rôles au USER. */
			this.userSpringService.affecterRolesAUser(
					userSpringEntityJPA, rolesAffectes);
			
			messageRole = "Les rôles ont bien été affectés";
			
			reponseServiceRole = true;
			
		} catch (Exception e) {
			
			messageRole = "Impossible d'affecter les rôles au User " 
			+ pUsername;
			
			reponseServiceRole = false;
		}
		
		pModel.addAttribute("messageRole", messageRole);
		pModel.addAttribute("reponseServiceRole", reponseServiceRole);
		
		/* rafraîchit la liste paginée des objets métier à chaque appel. */
		this.rafraichirListeUsersPaginee(
				pUsername, pNumeroPage, pTaillePage, pModel);
		
		/* délègue au SERVICE la tâche de rapatrier 
		 * tous les rôles dans le stockage. */
		final List<RoleEntityJPA> roles = this.roleService.listAll();
		
		/* passe au Model la liste des roles dans l'attribut 'roles'. */
		pModel.addAttribute("roles", roles);

		
		/* aiguille vers la VUE de CREATION des Objets métier. */
		return "/metier/userspring/createUserspring";
		
	} // Fin de affecterRolesAUser(...).___________________________________
	
	
	
	/**
	 * retourne la page de UserSpringEntityJPA de numéro pNumeroPage (0-based) 
	 * contenant pTaillePage enregistrements.
	 *
	 * @param pNumeroPage : int : numéro de la page (0-based).
	 * @param pTaillePage : int nombre d'enregistrements dans la page.
	 * 
	 * @return : Page&lt;UserSpringEntityJPA&gt;
	 * 
	 * @throws Exception 
	 */
	public Page<UserSpringEntityJPA> listerUsersParPage(
			int pNumeroPage, int pTaillePage) throws Exception {
		return null;
	}
	
	

	/**
	 * retourne la liste de tous les User en base.<br/>
	 *
	 * @return : List&lt;UserSpringEntityJPA&gt; : 
	 * liste de tous les User en base.<br/>
	 * 
	 * @throws Exception 
	 */
	public List<UserSpringEntityJPA> listAll() throws Exception {
		return null;
	}
	

	
	/**
	 * rafraichit une <code>org.springframework.data.domain.Page</code> 
	 * de numéro pNumeroPage (0-based) et comportant pTaillePage 
	 * enregistrements à chaque appel.
	 * <ul>
	 * <li>délègue au SERVICE la tâche de rafraîchir 
	 * la Page des objets métier stockés.</li>
	 * <li>récupère la liste d'objets metier dans la 
	 * <code>org.springframework.data.domain.Page</code>.</li>
	 * <li>récupère le nombre total de pages d'objets métier 
	 * dans le stockage auprès de la 
	 * <code>org.springframework.data.domain.Page</code></li>
	 * <li>passe le tableau des numéros (0-based) des pages d'objets métier 
	 * au Model dans l'attribut <strong>'pages'</strong> 
	 * <i>si il y a plus d'une page</i>.</li>
	 * <li>passe la liste des objets métier dans la Page au Model 
	 * dans l'attribut <strong>'listeObjetsDansPage'</strong>.</li>
	 * <li>passe le username au Model dans 
	 * l'attribut <strong>'username'</strong>.</li>
	 * </ul>
	 *
	 * @param pUsername : String : 
	 * identifiant de l'objet métier saisi dans la zone de création.
	 * @param pNumeroPage : int : numéro (0-based) de la page.
	 * @param pTaillePage : int : nombre d'enregistrements dans une page.
	 * @param pModel : org.springframework.ui.Model
	 * 
	 * @throws Exception
	 */
	private void rafraichirListeUsersPaginee(
			final String pUsername
			, final int pNumeroPage
			, final int pTaillePage
			, final Model pModel) throws Exception {

		/*
		 * délègue au SERVICE la tâche de rafraîchir 
		 * la Page des objets métier stockés.
		 */
		final Page<UserSpringEntityJPA> page 
			= this.userSpringService.listerUsersParPage(
					pNumeroPage, pTaillePage);

		List<UserSpringEntityJPA> listeObjetsDansPage = null;
		int[] pages = null;

		if (page != null) {

			/*
			 * récupère la liste d'objets metier dans la
			 * org.springframework.data.domain.Page.
			 */
			listeObjetsDansPage = page.getContent();

			/*
			 * récupère le nombre total de pages d'objets métier 
			 * dans le stockage auprès de la 
			 * org.springframework.data.domain.Page.
			 */
			final int nombrePages = page.getTotalPages();

			/* si il y a plus d'une page. */
			if (nombrePages > 1) {

				// PAGINATION.
				pages = new int[nombrePages];

				/*
				 * passe le tableau des numéros (0-based) des pages 
				 * d'objets métiers au Model dans l'attribut 
				 * 'pages' si il y a plus d'une page.
				 */
				pModel.addAttribute("pages", pages);

			}

		}

		/*
		 * passe la liste des objets métier dans la Page au Model 
		 * dans l'attribut 'listeObjetsDansPage'.
		 */
		pModel.addAttribute("listeObjetsDansPage", listeObjetsDansPage);
		
		/*
		 * passe le username au Model dans l'attribut 'username'.
		 */
		pModel.addAttribute("username", pUsername);

	} // Fin de rafraichirListeUsersPaginee(...).__________________________
	
	
		
	/**
	 * rafraîchit la liste résultant de la recherche rapide 
	 * sur pUsername à chaque appel.<br/>
	 * <ul>
	 * <li>délègue au SERVICE la tâche de rechercher la liste paginée 
	 * des objets métier à partir de pUserName%.</li>
	 * <li>récupère la liste d'objets metier dans la 
	 * <code>org.springframework.data.domain.Page</code> 
	 * d'index pNumeroPage.</li>
	 * <li>récupère le nombre total de pages d'objets métier 
	 * dans le stockage auprès de la 
	 * <code>org.springframework.data.domain.Page</code>.</li>
	 * <li>passe le tableau des numéros (0-based) 
	 * des pages d'objets metier au Model dans l'attribut 
	 * <strong>'pages'</strong> 
	 * <i>si il y a plus d'une page</i>.</li>
	 * <li>passe la liste des objets métier dans la Page au Model 
	 * dans l'attribut <strong>'listeObjetsDansPage'</strong></li>
	 * <li>passe le username au Model dans l'attribut 
	 * <strong>'username'</strong>.</li>
	 * </ul>
	 *
	 * @param pUsername : String : 
	 * identifiant des objets métier inséré dans la zone de recherche rapide.
	 * @param pNumeroPage : int : numéro (0-based) de la page.
	 * @param pTaillePage : int : nombre d'enregistrements dans une page.
	 * @param pModel : org.springframework.ui.Model
	 * 
	 * @throws Exception
	 */
	private void rafraichirListeRechercheePaginee(
			final String pUsername
			, final int pNumeroPage
			, final int pTaillePage
			, final Model pModel) throws Exception {

		/* délègue au SERVICE la tâche de rechercher la liste paginée 
		 * des objets métier à partir de pUserName%. */
		final Page<UserSpringEntityJPA> page 
			= this.userSpringService.rechercherRapideParPage(
					pUsername, pNumeroPage, pTaillePage);

		List<UserSpringEntityJPA> listeObjetsDansPage = null;
		int[] pages = null;

		if (page != null) {

			/*
			 * récupère la liste d'objets metier dans la 
			 * org.springframework.data.domain.Page d'index pNumeroPage.
			 */
			listeObjetsDansPage = page.getContent();

			/*
			 * récupère le nombre total de pages d'objets métier 
			 * dans le stockage auprès de la 
			 * org.springframework.data.domain.Page.
			 */
			final int nombrePages = page.getTotalPages();

			/* si il y a plus d'une page. */
			if (nombrePages > 1) {

				// PAGINATION.
				pages = new int[nombrePages];

				/*
				 * passe le tableau des numéros (0-based) des pages 
				 * d'objets metier au Model dans l'attribut 'pages' 
				 * si il y a plus d'une page.
				 */
				pModel.addAttribute("pages", pages);

			}

		}

		/*
		 * passe la liste des objets métier dans la Page au Model 
		 * dans l'attribut 'listeObjetsDansPage'.
		 */
		pModel.addAttribute("listeObjetsDansPage", listeObjetsDansPage);

		/*
		 * passe le username au Model dans l'attribut 'username'.
		 */
		pModel.addAttribute("username", pUsername);
		
	} // Fin de rafraichirListeRechercheePaginee(...)._____________________

	
	
} // FIN DE LA CLASSE UserSpringController.----------------------------------
