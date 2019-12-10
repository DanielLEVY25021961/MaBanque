package levy.daniel.application;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import nz.net.ultraq.thymeleaf.LayoutDialect;





/**
 * CLASSE AppConfig :<br/>
 * <ul>
 * <li>enregistre le filtre CERBERE auprès de SPRING BOOT.</li>
 * </ul>
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
 * @since 18 nov. 2019
 *
 */
@Configuration
public class AppConfig {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(AppConfig.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public AppConfig() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

		
	 /**
	 * Crée un FilterRegistrationBean&lt;FiltreCerbere&gt; 
	 * pour enregistrer le filtre CERBERE.<br/>
	 *
	 * @return : FilterRegistrationBean&lt;FiltreCerbere&gt;
	 */
//	@Bean
//	public FilterRegistrationBean<FiltreCerbere> filterRegistrationBean() {
//
//		final FilterRegistrationBean<FiltreCerbere> registrationBean 
//			= new FilterRegistrationBean<FiltreCerbere>();
//
//		final FiltreCerbere filtreCerbere = new FiltreCerbere();
//
//		registrationBean.setFilter(filtreCerbere);
//		registrationBean.addUrlPatterns("/*");
//		registrationBean.setOrder(1); // set precedence
//		registrationBean.setName("Filtre CERBERE");
//		
//		/* Determination de applicationId. */
//		registrationBean.addInitParameter(
//				"applicationId", "cerbere-springboot-security");
//
//		return registrationBean;
//
//	} // Fin de filterRegistrationBean().__________________________________
//	

	
	
    /**
     * installe le nz.net.ultraq.thymeleaf.LayoutDialect.<br/>
     *
     * @return : nz.net.ultraq.thymeleaf.LayoutDialect
     */
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    } // Fin de layoutDialect().___________________________________________
 
    
    
    /**
     * .<br/>
     * <br/>
     *
     * @return : org.thymeleaf.spring5.SpringTemplateEngine
     */
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//    	
//        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        
//        templateEngine.addDialect(new LayoutDialect());
//        
//        return templateEngine;
//        
//    } // Fin de templateEngine().__________________________________________
    
    
	
} // FIN DE LA CLASSE AppConfig.---------------------------------------------
