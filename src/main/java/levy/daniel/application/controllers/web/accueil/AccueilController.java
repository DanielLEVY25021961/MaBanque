package levy.daniel.application.controllers.web.accueil;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import i2.application.cerbere.commun.CerbereConnexionException;

/**
 * CLASSE AccueilController :<br/>
 * Controller Web SPRING BOOT chargé d'aiguiller 
 * vers la <strong>page d'accueil "index.html"</strong> 
 * à chaque demande du path context "/".<br/>
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
 * @since 8 nov. 2019
 *
 */
@Controller(value="AccueilController")
@RequestMapping("/")
public class AccueilController {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(AccueilController.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public AccueilController() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


    
	/**
	 * aiguille vers la page d'accueil <code>"/index.html"</code> 
	 * à chaque lancement de l'action <code>"/"</code><br/>
	 * La path context "/" est déjà indiqué dans l'annotation 
	 * RequestMapping au dessus de la classe.
	 * 
	 * @param pHttpServletRequest : javax.servlet.http.HttpServletRequest.
	 *
	 * @return : org.springframework.web.servlet.ModelAndView : 
	 * Vue "index.html" et Model (sans données).<br/>
	 * 
	 * @throws CerbereConnexionException 
	 */
	@GetMapping()
	public ModelAndView versAccueil(
			final HttpServletRequest pHttpServletRequest) throws CerbereConnexionException {
		
		final ModelAndView modelAndVew = new ModelAndView("accueil");
		
		/* aiguille vers la page index. */
		modelAndVew.setViewName("index");
		
		return modelAndVew;
		
	} // Fin de versAccueil()._____________________________________________
    
    
	
} // FIN DE LA CLASSE AccueilController.-------------------------------------
