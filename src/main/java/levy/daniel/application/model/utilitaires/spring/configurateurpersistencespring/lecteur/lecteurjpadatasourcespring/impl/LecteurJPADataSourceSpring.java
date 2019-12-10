package levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.lecteur.lecteurjpadatasourcespring.impl;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.Environment;

import levy.daniel.application.IConstantesApplicatives;
import levy.daniel.application.model.utilitaires.normalizerurlbase.NormalizerUrlBase;
import levy.daniel.application.model.utilitaires.normalizerurlbase.UrlEncapsulation;
import levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.lecteur.lecteurjpadatasourcespring.ILecteurJPADataSourceSpring;

/**
 * CLASSE LecteurJPADataSourceSpring :<br/>
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
 * @since 30 janv. 2019
 *
 */
public class LecteurJPADataSourceSpring implements ILecteurJPADataSourceSpring {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe LecteurJPADataSourceSpring".
	 */
	public static final String CLASSE_LECTEUR_JPA_DATASOURCE_SPRING 
		= "Classe LecteurJPADataSourceSpring";
	
	
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
	 * "Méthode lireUrl()".
	 */
	public static final String METHODE_LIRE_URL 
		= "Méthode lireUrl()";
	
	/**
	 * "Méthode lireDriver()".
	 */
	public static final String METHODE_LIRE_DRIVER 
		= "Méthode lireDriver()";
	
	/**
	 * "Méthode lireUserName()".
	 */
	public static final String METHODE_LIRE_USERNAME 
		= "Méthode lireUserName()";
	
	/**
	 * "Méthode lirePassword()".
	 */
	public static final String METHODE_LIRE_PASSWORD 
		= "Méthode lirePassword()";
	
	/**
	 * "environmentSpring NON INJECTE !!!".
	 */
	public static final String ENVT_SPRING_NON_INJECTE 
		= "environmentSpring NON INJECTE !!!";
		
	/**
	 * "javax.persistence.jdbc.connexion.url".
	 */
	public static final String URL_KEY 
		= "javax.persistence.jdbc.connexion.url";
	
	/**
	 * "javax.persistence.jdbc.driver".
	 */
	public static final String DRIVER_KEY 
		= "javax.persistence.jdbc.driver";
	
	/**
	 * "javax.persistence.jdbc.connection.username".
	 */
	public static final String USERNAME_KEY 
		= "javax.persistence.jdbc.connection.username";
	
	/**
	 * "javax.persistence.jdbc.connection.password".
	 */
	public static final String PASSWORD_KEY 
		= "javax.persistence.jdbc.connection.password";
		
	/**
	 * <b>lecteur SPRING du fichier properties 
	 * de configuration de la base</b>.
	 * <ul>
	 * <li>org.springframework.core.env.Environment</li>
	 * </ul>
	 */
	private Environment environmentSpring;

	/**
	 * URL de la BASE.
	 * <ul>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.connexion.url</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>javax.persistence.jdbc.url</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>javax.persistence.jdbc.url</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String url;
	
	/**
	 * DRIVER JDBC de la BASE (sous forme de String).
	 * <ul>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.driver</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>javax.persistence.jdbc.driver</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>javax.persistence.jdbc.driver</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String driver;
	
	/**
	 * LOGIN de la BASE.
	 * <ul>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.connection.username</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>javax.persistence.jdbc.user</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>javax.persistence.jdbc.user</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String userName;
	
	/**
	 * MOT DE PASSE de la BASE.
	 * <ul>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.connection.password</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>javax.persistence.jdbc.password</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>javax.persistence.jdbc.password</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String password;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(LecteurJPADataSourceSpring.class);

	// *************************METHODES************************************/
	
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public LecteurJPADataSourceSpring() {		
		this(null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	 /**
	 * CONSTRUCTEUR D'ARITE 1.<br/>
	 * <ul>
	 * <li>passe le paramètre pEnvironmentSpring 
	 * à this.environmentSpring</li>
	 * <li>alimente tous les attributs de la classe 
	 * via this.lireProperties()</li>
	 * </ul>
	 * 
	 * @param pEnvironmentSpring : 
	 * org.springframework.core.env.Environment
	 */
	public LecteurJPADataSourceSpring(
			final Environment pEnvironmentSpring) {
		
		super();
				
		this.environmentSpring = pEnvironmentSpring;
		
		/* alimente tous les attributs de la classe. */
		this.lireProperties();
		
	} // Fin de CONSTRUCTEUR D'ARITE 1.____________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder stb = new StringBuilder();
			
		/* DataSource. */
		stb.append(String.format(FORMAT_TOSTRING
				, " *** URL", this.getUrl()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "DRIVER", this.getDriver()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "LOGIN", this.getUserName()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "PASSWORD", this.getPassword()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		return stb.toString();
		
	} // Fin de toString().________________________________________________


	
	/**
	 * <b>Lit le fichier properties SPRING de configuration de la base 
	 * encapsulé dans <code>this.environmentSpring</code></b>.
	 * <ul>
	 * <li>automatiquement appelé par le constructeur d'arité 1</li><br/>
	 * <ul>
	 * <li>lit l'URL de la base (url).</li>
	 * <li>lit le DRIVER de la Base (driver).</li>
	 * <li>lit le LOGIN de la Base (userName).</li>
	 * <li>lit le PASSWORD de la Base (password).</li>
	 * </ul>
	 * </ul>
	 */
	private void lireProperties() {
		
		/* URL. */
		this.lireUrl();
		
		/* DRIVER. */
		this.lireDriver();
		
		/* userName. */
		this.lireUserName();
		
		/* password. */
		this.lirePassword();
				
	} // Fin de lireProperties().__________________________________________
	
	
		
	/**
	 * <b>lit la valeur de URL dans le properties 
	 * et la <i>normalise</i></b>.<br/>
	 * injecte la valeur lue dans <code>this.url</code>.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.connexion.url</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>javax.persistence.jdbc.url</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>javax.persistence.jdbc.url</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.url : String : URL de la base.<br/>
	 */
	private String lireUrl() {
				
		if (this.environmentSpring != null) {
			
			final String urlFournie 
				= this.environmentSpring.getProperty(
					URL_KEY);
			
			if (urlFournie != null) {
				
				final String bd = IConstantesApplicatives.NOM_BASE;
				
				String urlNormalisee = null;
				
				final UrlEncapsulation encapsulation 
				 	= NormalizerUrlBase.creerUrlEncapsulation(urlFournie, bd);
				
				if (encapsulation != null) {
					
					urlNormalisee = encapsulation.getUrlNormalisee();
					
					this.url = urlNormalisee;
					
				} else {
					
					final String message 
					= CLASSE_LECTEUR_JPA_DATASOURCE_SPRING
					+ TIRET_ESPACE
					+ METHODE_LIRE_URL
					+ TIRET_ESPACE
					+ "Impossible de normaliser l'URL dans le properties : '" + urlFournie + "' pour le nom de BD : " + bd;
					
					if (LOG.isFatalEnabled()) {
						LOG.fatal(message);
					}
				}
				
				
			}			
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_JPA_DATASOURCE_SPRING
				+ TIRET_ESPACE
				+ METHODE_LIRE_URL
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.url;
		
	} // Fin de lireUrl()._________________________________________________	

		
	
	/**
	 * <b>lit la valeur du Driver de BD dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.driver</code>.<br/>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.driver</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>javax.persistence.jdbc.driver</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>javax.persistence.jdbc.driver</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.driver : String : 
	 * nom du Driver JDBC de la base.<br/>
	 */
	private String lireDriver() {
				
		if (this.environmentSpring != null) {
			
			this.driver
				= this.environmentSpring.getProperty(
					DRIVER_KEY);
								
		} else {
			
			final String message 
				= CLASSE_LECTEUR_JPA_DATASOURCE_SPRING
				+ TIRET_ESPACE
				+ METHODE_LIRE_DRIVER
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.driver;

	} // Fin de lireDriver().______________________________________________
	

	
	/**
	 * <b>lit la valeur du LOGIN de BD dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.userName</code>.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.connection.username</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>javax.persistence.jdbc.user</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>javax.persistence.jdbc.user</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.userName : String : Login.<br/>
	 */
	private String lireUserName() {
		
		if (this.environmentSpring != null) {
			
			this.userName 
				= this.environmentSpring.getProperty(
						USERNAME_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_JPA_DATASOURCE_SPRING
				+ TIRET_ESPACE
				+ METHODE_LIRE_USERNAME
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.userName;
		
	} // Fin de lireUserName().____________________________________________
	

	
	/**
	 * <b>lit la valeur du PASSWORD de BD dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.password</code>.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.connection.password</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>javax.persistence.jdbc.password</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>javax.persistence.jdbc.password</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return : String : this.password.<br/>
	 */
	private String lirePassword() {
		
		if (this.environmentSpring != null) {
			
			this.password 
				= this.environmentSpring.getProperty(
						PASSWORD_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_JPA_DATASOURCE_SPRING
				+ TIRET_ESPACE
				+ METHODE_LIRE_PASSWORD
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.password;
		
	} // Fin de lirePassword().____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getUrl() {
		return this.url;
	} // Fin de getUrl().__________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getDriver() {
		return this.driver;
	} // Fin de getDriver()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getUserName() {
		return this.userName;
	} // Fin de getUserName()._____________________________________________



	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPassword() {
		return this.password;
	} // Fin de getPassword()._____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Environment getEnvironmentSpring() {
		return this.environmentSpring;
	} // Fin de getEnvironmentSpring().____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setEnvironmentSpring(
			final Environment pEnvironmentSpring) {
		
		this.environmentSpring = pEnvironmentSpring;
		
		/* alimente les attributs de la classe. */
		this.lireProperties();
		
	} // Fin de setEnvironmentSpring(...)._________________________________

	
	
} // FIN DE LA CLASSE LecteurJPADataSourceSpring.----------------------------
