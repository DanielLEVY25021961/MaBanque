package levy.daniel.application.controllers.web.accueil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * CLASSE LoginController :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <br/>
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
 * @since 30 nov. 2019
 *
 */
@Controller(value = "LoginController")
@RequestMapping("/LoginController")
public class LoginController {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(LoginController.class);

	// *************************METHODES************************************/

	/**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public LoginController() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	/**
	 * redirige vers la page de login <code>/accueil/login.html</code> à chaque
	 * lancement de l'action <code>"/LoginController/versLogin"</code>.<br/>
	 * Le path "/LoginController" est déjà indiqué dans l'annotation RequestMapping
	 * au dessus de la classe.
	 * <ul>
	 * <li>ajoute un message "authentification incorrecte" au Model dans l'attribut
	 * <strong>'message'</strong> si 'error' dans la requête.</li>
	 * <li>ajoute un message "Vous êtes bien déloggé" au Model dans l'attribut
	 * <strong>'message'</strong> si 'logout' dans la requête.</li>
	 * <li>récupère <code>org.springframework.security.core.Authentication</code>
	 * dans le contexte du
	 * <code>org.springframework.security.core.context.SecurityContextHolder</code>
	 * si 'logout' dans la requête.</li>
	 * <li><strong>logout l'authentication</strong> si elle est encore présente dans
	 * la requête.</li>
	 * <li>retourne la page "/accueil/login.html".</li>
	 * </ul>
	 * 
	 * @param pError    : String : paramètre 'error' passé dans la requête en cas de
	 *                  défaut d'authentification.
	 * @param pLogout   : String : paramètre 'logout' passé dans la requête en cas
	 *                  de demande de Logout.
	 * @param pModel    : org.springframework.ui.Model
	 * @param pRequest  : javax.servlet.http.HttpServletRequest
	 * @param pResponse : javax.servlet.http.HttpServletResponse
	 *
	 * @return : String : "/accueil/login".<br/>
	 */
	@GetMapping("/versLogin")
	public String versLogin(@RequestParam(value = "error", required = false) final String pError,
			@RequestParam(value = "logout", required = false) final String pLogout, final Model pModel,
			final HttpServletRequest pRequest, final HttpServletResponse pResponse) {

		pModel.addAttribute("error", pError);
		pModel.addAttribute("logout", pLogout);

		if (pError != null) {

			final String message = "authentification incorrecte";

			/*
			 * ajoute un message "authentification incorrecte" au Model dans l'attribut
			 * 'message' si 'error' dans la requête.
			 */
			pModel.addAttribute("message", message);

		}

		if (pLogout != null) {

			/*
			 * récupère org.springframework.security.core.Authentication dans le contexte du
			 * org.springframework.security.core.context.SecurityContextHolder si 'logout'
			 * dans la requête.
			 */
			final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			/* logout l'authentication si elle est encore présente dans la requête. */
			if (authentication != null) {
				new SecurityContextLogoutHandler().logout(pRequest, pResponse, authentication);
			}

			final String message = "Vous êtes bien déloggé";

			/*
			 * ajoute un message "Vous êtes bien déloggé" au Model dans l'attribut 'message'
			 * si 'error' dans la requête.
			 */
			pModel.addAttribute("message", message);

		}

		/* retourne la page "/accueil/login.html". */
		return "/accueil/login";

	} // Fin de versLogin()._______________________________________________

	/**
	 * <ul>
	 * <li><strong>récupère les informations du USER en SESSION</strong> et les
	 * encapsule sous forme de <code>java.util.Map&lt;String, Object&gt;</code> avec
	 * :
	 * <ul>
	 * <li>String : la Key ('username' ou 'roles).</li>
	 * <li>Object : les valeurs associées à la Key.</li>
	 * </ul>
	 * </li>
	 * <li>aiguille vers la page "/accueil/userLogge".</li>
	 * </ul>
	 * <ul>
	 * <li>retourne vers l'accueil "index" si
	 * <code>org.springframework.security.core.context.SecurityContext</code> de la
	 * <code>javax.servlet.http.HttpSession</code> injectée par SPRING est
	 * null.</li>
	 * <li>retourne vers l'accueil "index" si
	 * org.springframework.security.core.Authentication est null.</li>
	 * <li>récupère le
	 * <code>org.springframework.security.core.context.SecurityContext</code> auprès
	 * de la <code>javax.servlet.http.HttpSession</code> <strong>injectée par
	 * SPRING</strong>.</li>
	 * <li>récupère le <code>org.springframework.security.core.Authentication</code>
	 * du User en session.</li>
	 * <li>récupère le username (Login) du User en session auprès de
	 * Authentication.</li>
	 * <li>récupère la liste des ROLES du User en Session auprès de
	 * Authentication.</li>
	 * <li>ajoute le username (Login) à la Map resultat avec la Key 'username'.</li>
	 * <li>ajoute la liste des rôles du User en session avec la Key 'roles'.</li>
	 * <li>ajoute la Map resultat à l'attribut paramsUserSession du Model.</li>
	 * </ul>
	 * - retourne "index.html" si SecurityContext == null.<br/>
	 * <br/>
	 *
	 * @param pHttpSession : <code>javax.servlet.http.HttpSession</code> : la
	 *                     Session Web courante.
	 * @param pModel       : org.springframework.ui.Model
	 * 
	 * @return : String : "/accueil/userLogge"
	 */
	@GetMapping("/getLoggedUserInSession")
	public String getLoggedUserInSession(final HttpSession pHttpSession, final Model pModel) {

		/*
		 * récupère le org.springframework.security.core.context.SecurityContext auprès
		 * de la javax.servlet.http.HttpSession injectée par SPRING.
		 */
		final SecurityContext securityContext = (SecurityContext) pHttpSession
				.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);

		Authentication authentication = null;
		String username = null;
		List<String> rolesUserEnSessionList = null;

		/* ne fait rien si SecurityContext == null. */
		if (securityContext != null) {

			/*
			 * récupère l' org.springframework.security.core.Authentication du User en
			 * session.
			 */
			authentication = securityContext.getAuthentication();

			if (authentication != null) {

				/* récupère le username (Login) du User en session. */
				username = authentication.getName();

				rolesUserEnSessionList = new ArrayList<String>();

				/*
				 * récupère la liste des ROLES du User en Session auprès de Authentication.
				 */
				for (final GrantedAuthority grantedAuthority : authentication.getAuthorities()) {

					rolesUserEnSessionList.add(grantedAuthority.getAuthority());

				}

				final Map<String, Object> resultat = new ConcurrentHashMap<String, Object>();

				/* ajoute le username (Login) à la Map resultat. */
				resultat.put("username", username);

				/*
				 * ajoute la liste des rôles du User en session avec la Key 'roles'.
				 */
				resultat.put("roles", rolesUserEnSessionList);

				/* ajoute la Map resultat à l'attribut paramsUserSession du Model. */
				pModel.addAttribute("paramsUserSession", resultat);

				/* retourne la Map resultat alimentée. */
				return "/accueil/userLogge";

			}

			/*
			 * retourne vers l'accueil "index" si
			 * org.springframework.security.core.Authentication est null.
			 */
			return "index";

		}

		/*
		 * retourne vers l'accueil "index" si
		 * org.springframework.security.core.context.SecurityContext de la
		 * javax.servlet.http.HttpSession injectée par SPRING est null.
		 */
		return "index";

	} // Fin de getLoggedUserInSession(...)._______________________________

} // FIN DE LA CLASSE LoginController.---------------------------------------
