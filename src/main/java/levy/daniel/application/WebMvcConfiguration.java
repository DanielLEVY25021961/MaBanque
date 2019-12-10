package levy.daniel.application;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CLASSE WebMvcConfiguration :<br/>
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
 * @since 30 nov. 2019
 *
 */
@EnableWebMvc
@ComponentScan("levy.daniel.application")
public class WebMvcConfiguration implements WebMvcConfigurer {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(WebMvcConfiguration.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public WebMvcConfiguration() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addResourceHandlers(
			final ResourceHandlerRegistry pRegistry) {
		
//		pRegistry.addResourceHandler("/resources/**");
//		pRegistry.addResourceHandler("/static/**");
//		pRegistry.addResourceHandler("/css/**");
		
	} // Fin de addResourceHandlers(...).__________________________________

	
	
	/**
	 * {@inheritDoc}<br/>
	 * <br/>
	 * associe à l'ACTION <code>'/login'</code> 
	 * (définie dans le Controller d'authentification de SPRING) 
	 * la VUE <code>/accueil/login.html</code> sous resources/templates.<br/> 
	 * Le suffixe '.html' est automatiquement fourni par Thymeleaf.
	 */
	@Override
    public void addViewControllers(
    		final ViewControllerRegistry pRegistry) {
		
		/* associe à l'ACTION '/login' (définie dans le Controller 
		 * d'authentification de SPRING) la VUE /accueil/login.html. 
		 * le suffixe '.html' est automatiquement fourni par Thymeleaf. */
//        pRegistry.addViewController("/login").setViewName("/accueil/login");
//        pRegistry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        
    } // Fin de addViewControllers(...).___________________________________
	
	
	
} // FIN DE LA CLASSE WebMvcConfiguration.-----------------------------------
