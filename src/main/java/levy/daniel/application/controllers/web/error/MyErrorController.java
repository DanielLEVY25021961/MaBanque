package levy.daniel.application.controllers.web.error;

import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

/**
 * CLASSE MyErrorController :<br/>
 * Controller Web SPRING BOOT chargé d'aiguiller 
 * vers la bonne 
 * <strong>page d'erreur erreurs.html, erreur-500.html...</strong> 
 * à chaque envoi du path error "/error" par le conteneur de Servlets.<br/>
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
 * @since 7 nov. 2019
 *
 */
@Controller(value="MyErrorController")
//@PreAuthorize("permitAll()") 
public class MyErrorController implements ErrorController {

	// ************************ATTRIBUTS************************************/

	/**
	 * "/error".
	 */
	public static final String PATH_ERROR = "/error";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(MyErrorController.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public MyErrorController() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * aiguille l'action "/error" provenant du conteneur de Servlet 
	 * vers la bonne page d'erreur (erreur-404.html, erreur-500.html, ...).
	 * <ul>
	 * <li>utilise un 
	 * <code>org.springframework.boot.web.servlet.error.DefaultErrorAttributes</code> 
	 * et une <code>org.springframework.web.context.request.WebRequest</code> 
	 * <strong>injectés par SPRING</strong> pour récupérer la Map des erreurs.</li>
	 * <li>récupère l'exception auprès du 
	 * org.springframework.boot.web.servlet.error.DefaultErrorAttributes</li>
	 * <li>ajoute l'Exception au <code>org.springframework.ui.Model</code></li>
	 * <li>ajoute la Map des attributs d'erreurs provenant 
	 * du DefaultErrorAttributes au org.springframework.ui.Model</li>
	 * <li><strong>retourne la page d'erreur en fonction du status 
	 * de l'erreur.</strong></li>
	 * </ul>
	 *
	 * @param pRequest : HttpServletRequest.
	 * @param pErrorAttributes : 
	 * org.springframework.boot.web.servlet.error.DefaultErrorAttributes
	 * @param pModel : org.springframework.ui.Model 
	 * @param pWebRequest : org.springframework.web.context.request.WebRequest
	 * 
	 * @return : String : Page HTML pour afficher l'erreur.<br/>
	 */
	@RequestMapping(PATH_ERROR)
	public final String handleError(
			final HttpServletRequest pRequest
				, final DefaultErrorAttributes pErrorAttributes
					, final Model pModel
						, final WebRequest pWebRequest) {
				
		/* utilise un 
		 * org.springframework.boot.web.servlet.error.DefaultErrorAttributes 
		 * pour récupérer la Map des erreurs. */
		final Map<String, Object> errorAttributesMap 
		= pErrorAttributes.getErrorAttributes(pWebRequest, true);
		
		/* récupère l'exception auprès du 
		 * org.springframework.boot.web.servlet.error.DefaultErrorAttributes */		
		final Throwable throwable = pErrorAttributes.getError(pWebRequest);
		
		/* ajoute l'Exception au org.springframework.ui.Model */
		if (throwable != null) {
			final String exception = throwable.getClass().getSimpleName();
			pModel.addAttribute("exception", exception);
		}
		
		/* ajoute la Map des attributs d'erreurs provenant
		 *  du DefaultErrorAttributes au org.springframework.ui.Model */
		pModel.addAllAttributes(errorAttributesMap);
		
		/* URL de la requête ayant causé l'Exception. */
		final String requestUrl = pRequest.getRequestURL().toString();
		pModel.addAttribute("requestUrl", requestUrl);
		
	    /* status de l'erreur. */
	    final Object status 
	    	= pRequest.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
	    
	    /* retourne la page d'erreur en fonction du status de l'erreur. */
	    if (status != null) {
	    	
	        Integer statusCode = Integer.valueOf(status.toString());
	     
	        if (statusCode == HttpStatus.NOT_FOUND.value()) {
	            return "error/erreur-404";
	        }
	        else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
	            return "error/erreur-500";
	        } 
	        else if (statusCode == HttpStatus.FORBIDDEN.value()) {
	        	return "error/erreur-403";
	        }
	        else if (statusCode == HttpStatus.METHOD_NOT_ALLOWED.value()) {
	        	return "error/erreur-405";
	        }
	    }
	    
	    return "error/erreurs";
	    
	} // Fin de handleError(...).__________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getErrorPath() {
		return PATH_ERROR;
	} // Fin de getErrorPath().____________________________________________

	

} // FIN DE LA CLASSE MyErrorController.-------------------------------------
