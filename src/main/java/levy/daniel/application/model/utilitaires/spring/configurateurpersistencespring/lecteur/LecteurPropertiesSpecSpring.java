package levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.lecteur;

import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.Environment;

/**
 * CLASSE LecteurPropertiesSpecSpring :<br/>
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
 * @since 31 janv. 2019
 *
 */
public class LecteurPropertiesSpecSpring {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe LecteurPropertiesSpecSpring".
	 */
	public static final String CLASSE_LECTEUR_PROPERTIES_SPEC_SPRING 
		= "Classe LecteurPropertiesSpecSpring";
	
	
	/**
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';
	
	/**
	 * ", ".<br/>
	 */
	public static final String VIRGULE_ESPACE = ", ";
	
	/**
	 * "null".<br/>
	 */
	public static final String NULL = "null";

	/**
	 * " - ".
	 */
	public static final String TIRET_ESPACE = " - ";
	
	/**
	 * saut de ligne de la plateforme.<br/>
	 * System.getProperty("line.separator")
	 */
	public static final String SAUT_LIGNE_PLATEFORME 
		= System.getProperty("line.separator");
	
	/**
	 * Locale.getDefault().
	 */
	public static final Locale LOCALE_PLATEFORME 
		= Locale.getDefault();
	
	/**
	 * "%1$-40s : %2$-45s".
	 */
	public static final String FORMAT_TOSTRING 
		= "%1$70s : %2$-72s";
	
	/**
	 * "%1$-5d  clé : %2$-35s - valeur : %3$s".
	 */
	public static final String FORMAT_PROPERTIES 
		= "%1$-5d  clé : %2$-35s - valeur : %3$s";
	
	/**
	 * "environmentSpring NON INJECTE !!!".
	 */
	public static final String ENVT_SPRING_NON_INJECTE 
		= "environmentSpring NON INJECTE !!!";
	
	/**
	 * <b>lecteur SPRING du fichier properties 
	 * de configuration de la base</b>.
	 * <ul>
	 * <li>org.springframework.core.env.Environment</li>
	 * </ul>
	 */
	private Environment environmentSpring;
	
	/**
	 * java.util.Properties contenant toutes les propriétés 
	 * spécifiques à SPRING.<br/>
	 */
	private final transient Properties propertiesSpecSpring = new Properties();
		
	/**
	 * boolean (sous forme String) qui stipule si SPRING 
	 * doit générer le schéma de création de tables.<br/>
	 * Interrupteur général exclusif à SPRING.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.generate-ddl</code> 
	 * dans le fichier properties SPRING</li>
	 * </ul>
	 */
	private transient String generateDdl;
	
	/**
	 * boolean (sous forme String) qui stipule si SPRING 
	 * doit autoriser la console pour une base H2.<br/>
	 * Interrupteur général exclusif à SPRING.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.h2.console.enabled</code> 
	 * dans le fichier properties SPRING</li>
	 * </ul>
	 */
	private transient String springH2ConsoleEnabled;
	
	/**
	 * valeur qui stipule pour SPRING 
	 * le chemin de la console pour une base H2.<br/>
	 * exclusif à SPRING.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.h2.console.path</code> 
	 * dans le fichier properties SPRING</li>
	 * </ul>
	 */
	private transient String springH2ConsolePath;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(LecteurPropertiesSpecSpring.class);

	// *************************METHODES************************************/
	
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public LecteurPropertiesSpecSpring() {		
		this(null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	 /**
	 * CONSTRUCTEUR D'ARITE 1.<br/>
	 * <ul>
	 * <li>passe le paramètre pEnvironmentSpring 
	 * à this.environmentSpring</li>
	 * <li>alimente tous les attributs de la classe 
	 * via this.lireProperties()</li>
	 * <li>alimente le java.util.Properties</li>
	 * </ul>
	 * 
	 * @param pEnvironmentSpring : 
	 * org.springframework.core.env.Environment
	 */
	public LecteurPropertiesSpecSpring(
			final Environment pEnvironmentSpring) {
		
		super();
				
		this.environmentSpring = pEnvironmentSpring;
		
		/* alimente tous les attributs de la classe. */
		this.lireProperties();
		
	} // Fin de CONSTRUCTEUR D'ARITE 1.____________________________________
	

	
	/**
	 * affiche le contenu de <code>this.propertiesSpecSpring</code>.<br/>
	 *
	 * @return : String : affichage.<br/>
	 */
	public final String afficherPropertiesSpecSpring() {
		return this.afficherJavaUtilProperties(
				this.propertiesSpecSpring);
	} // Fin de afficherPropertiesSpecSpring().____________________________
	
	
	
	/**
	 * Fabrique une String à partir d'un java.util.Properties.<br/>
	 * <br/>
	 * - retourne null si pProperties est null.<br/>
	 * <br/>
	 *
	 * @param pProperties : java.util.Properties.
	 * 
	 * @return : String : Pour affichage à la console.<br/>
	 */
	public final String afficherJavaUtilProperties(
			final Properties pProperties) {
		
		/* retourne null si pProperties est null. */
		if (pProperties == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		final Set<String> keys = pProperties.stringPropertyNames();
		
		/* Tri du Set de String. */
		final SortedSet<String> keysTrie = new TreeSet<String>(keys);
		
		int i = 0;
		
		for (final String key : keysTrie) {
			
			i++;
			
			final String valeur = pProperties.getProperty(key);
			
			final String ligne 
				= String.format(
						LOCALE_PLATEFORME
							, FORMAT_PROPERTIES
								, i, key, valeur);
			
			stb.append(ligne);
			stb.append(SAUT_LIGNE_PLATEFORME);
			
		}
		
		return stb.toString();
		
	} // Fin de afficherJavaUtilProperties(...).___________________________


	
	/**
	 * <b>Lit le fichier properties SPRING de configuration de la base 
	 * encapsulé dans <code>this.environmentSpring</code></b>.
	 * <ul>
	 * <li>automatiquement appelé par le constructeur d'arité 1</li><br/>
	 * <ul>
	 * <li>lit generateDdl</li>
	 * <li>lit springH2ConsoleEnabled</li>
	 * <li>lit springH2ConsolePath</li>
	 * <li><b>alimente <code>this.propertiesSpecSpring</code> 
	 * avec toutes les propriétés</b>.</li>
	 * </ul>
	 * </ul>
	 */
	private void lireProperties() {
		
		/* generateDdl. */
		this.lireGenerateDdl();
		
		/* springH2ConsoleEnabled. */
		this.lireSpringH2ConsoleEnabled();
		
		/* springH2ConsolePath. */
		this.lireSpringH2ConsolePath();
						
		/* alimentation de propertiesSpecSpring. */
		this.alimenterPropertiesSpecSpring();
				
	} // Fin de lireProperties().__________________________________________
	

	
	/**
	 * <b>alimente <code>this.propertiesSpecSpring</code> 
	 * avec toutes les propriétés</b>.
	 */
	private void alimenterPropertiesSpecSpring() {
		
		if (this.generateDdl != null) {
			this.propertiesSpecSpring
				.put("spring.jpa.generate-ddl", this.generateDdl);
		}
		
		if (this.springH2ConsoleEnabled != null) {
			this.propertiesSpecSpring
				.put("spring.h2.console.enabled", this.springH2ConsoleEnabled);
		}
		
		if (this.springH2ConsolePath != null) {
			this.propertiesSpecSpring
				.put("spring.h2.console.path", this.springH2ConsolePath);
		}
		
	} // Fin de alimenterPropertiesSpecSpring().___________________________
	
	
	
	/**
	 * <b>lit le boolean (sous forme String) qui stipule si SPRING 
	 * doit générer le schéma de création de tables</b>.<br/>
	 * Interrupteur général exclusif à SPRING.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.generate-ddl</code> 
	 * dans le fichier properties SPRING</li>
	 * </ul>
	 *
	 * @return : String : this.generateDdl.<br/>
	 */
	private String lireGenerateDdl() {
		
		if (this.environmentSpring != null) {
			
			this.generateDdl 
				= this.environmentSpring.getProperty(
						"spring.jpa.generate-ddl");
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPEC_SPRING 
				+ TIRET_ESPACE
				+ "Méthode lireGenerateDdl()"
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.generateDdl;
		
	} // Fin de lireGenerateDdl()._________________________________________
	
	
	
	/**
	 * <b>lit le boolean (sous forme String) qui stipule si SPRING 
	 * doit autoriser la console pour une base H2</b>.<br/>
	 * Interrupteur général exclusif à SPRING.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.h2.console.enabled</code> 
	 * dans le fichier properties SPRING</li>
	 * </ul>
	 *
	 * @return : String : this.springH2ConsoleEnabled.<br/>
	 */
	private String lireSpringH2ConsoleEnabled() {
		
		if (this.environmentSpring != null) {
			
			this.springH2ConsoleEnabled 
				= this.environmentSpring.getProperty(
						"spring.h2.console.enabled");
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPEC_SPRING
				+ TIRET_ESPACE
				+ "Méthode lireSpringH2ConsoleEnabled()"
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.springH2ConsoleEnabled;
		
	} // Fin de lireSpringH2ConsoleEnabled().______________________________
	
	
	
	/**
	 * <b>lit le valeur qui stipule pour SPRING 
	 * le chemin de la console pour une base H2</b>.<br/>
	 * exclusif à SPRING.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.h2.console.path</code> 
	 * dans le fichier properties SPRING</li>
	 * </ul>
	 *
	 * @return : String : this.springH2ConsolePath.<br/>
	 */
	private String lireSpringH2ConsolePath() {
		
		if (this.environmentSpring != null) {
			
			this.springH2ConsolePath 
				= this.environmentSpring.getProperty(
						"spring.h2.console.path");
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPEC_SPRING
				+ TIRET_ESPACE
				+ "Méthode lireSpringH2ConsolePath()"
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.springH2ConsolePath;
		
	} // Fin de lireSpringH2ConsolePath()._________________________________


		
	/**
	 * Getter du boolean (sous forme String) qui stipule si SPRING 
	 * doit générer le schéma de création de tables.<br/>
	 * Interrupteur général exclusif à SPRING.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.generate-ddl</code> 
	 * dans le fichier properties SPRING</li>
	 * </ul>
	 *
	 * @return this.generateDdl : String.<br/>
	 */
	public final String getGenerateDdl() {
		return this.generateDdl;
	} // Fin de getGenerateDdl().__________________________________________


	
	/**
	 * Getter du boolean (sous forme String) qui stipule si SPRING 
	 * doit autoriser la console pour une base H2.<br/>
	 * Interrupteur général exclusif à SPRING.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.h2.console.enabled</code> 
	 * dans le fichier properties SPRING</li>
	 * </ul>
	 *
	 * @return this.springH2ConsoleEnabled : String.<br/>
	 */
	public final String getSpringH2ConsoleEnabled() {
		return this.springH2ConsoleEnabled;
	} // Fin de getSpringH2ConsoleEnabled()._______________________________


	
	/**
	 * Getter du valeur qui stipule pour SPRING 
	 * le chemin de la console pour une base H2.<br/>
	 * exclusif à SPRING.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.h2.console.path</code> 
	 * dans le fichier properties SPRING</li>
	 * </ul>
	 *
	 * @return this.springH2ConsolePath : String.<br/>
	 */
	public final String getSpringH2ConsolePath() {
		return this.springH2ConsolePath;
	} // Fin de getSpringH2ConsolePath().__________________________________



	/**
	 * Getter du java.util.Properties contenant toutes les propriétés 
	 * spécifiques à SPRING.<br/>
	 *
	 * @return this.propertiesSpecSpring : Properties.<br/>
	 */
	public final Properties getPropertiesSpecSpring() {
		return this.propertiesSpecSpring;
	} // Fin de getPropertiesSpecSpring()._________________________________



	/**
	 * Getter du <b>lecteur SPRING du fichier properties 
	 * de configuration de la base</b>.
	 * <ul>
	 * <li>org.springframework.core.env.Environment</li>
	 * </ul>
	 *
	 * @return this.environmentSpring : 
	 * org.springframework.core.env.Environment.<br/>
	 */
	public final Environment getEnvironmentSpring() {
		return this.environmentSpring;
	} // Fin de getEnvironmentSpring().____________________________________


	
	/**
	* Setter du <b>lecteur SPRING du fichier properties 
	 * de configuration de la base</b>.
	 * <ul>
	 * <li>org.springframework.core.env.Environment</li>
	 * <li>alimente tous les attributs de la classe 
	 * via this.lireProperties()</li>
	 * </ul>
	*
	* @param pEnvironmentSpring : 
	* org.springframework.core.env.Environment. : 
	* valeur à passer à this.environmentSpring.<br/>
	*/
	public final void setEnvironmentSpring(
			final Environment pEnvironmentSpring) {
		
		this.environmentSpring = pEnvironmentSpring;
		
		/* alimente les attributs de la classe. */
		this.lireProperties();

	} // Fin de setEnvironmentSpring(...)._________________________________

	
	
} // FIN DE LA CLASSE LecteurPropertiesSpecSpring.---------------------------
