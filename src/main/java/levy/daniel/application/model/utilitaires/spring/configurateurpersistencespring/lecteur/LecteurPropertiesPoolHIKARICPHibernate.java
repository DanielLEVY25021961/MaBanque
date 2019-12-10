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
 * CLASSE LecteurPropertiesPoolHIKARICPHibernate :<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 31 janv. 2019
 *
 */
public class LecteurPropertiesPoolHIKARICPHibernate {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe LecteurPropertiesPoolHIKARICPHibernate".
	 */
	public static final String CLASSE_LECTEUR_PROPERTIES_POOLC3P0_HIBERNATE 
		= "Classe LecteurPropertiesPoolHIKARICPHibernate";
	
	
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
	 * dépendant du POOL DE CONNEXION et du PROVIDER.<br/>
	 */
	private final transient Properties propertiesPool = new Properties();

	/**
	 * Taille minimale du pool de connexion C3P0 pour Hibernate.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.min_size</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.min_size</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.min_size</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String poolMinSize;
	
	/**
	 * Taille maximale du pool de connexion C3P0 pour Hibernate.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.max_size</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.max_size</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.max_size</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String poolMaxSize;
	
	/**
	 * Timeout du pool de connexion C3P0 pour Hibernate.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.timeout</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.timeout</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.timeout</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String poolTimeOut;
	
	/**
	 * taille du cache de PreparedStatements du pool de connexion 
	 * C3P0 pour Hibernate.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.max_statements</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.max_statements</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.max_statements</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String poolMaxStatements;
	
	/**
	 * période de recherche des connexions inactives 
	 * du pool de connexion C3P0 pour Hibernate.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.idle_test_period</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.idle_test_period</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.idle_test_period</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String poolIdleTestPeriod;

	/**
	 * nombre de connexions acquises en une seule fois 
	 * lorsque le pool de connexion C3P0 pour Hibernate est épuisé.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.acquire_increment</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.acquire_increment</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.acquire_increment</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String poolAcquireIncrement;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
	= LogFactory.getLog(LecteurPropertiesPoolHIKARICPHibernate.class);

	// *************************METHODES************************************/
	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public LecteurPropertiesPoolHIKARICPHibernate() {		
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
	public LecteurPropertiesPoolHIKARICPHibernate(
			final Environment pEnvironmentSpring) {
		
		super();
				
		this.environmentSpring = pEnvironmentSpring;
		
		/* alimente tous les attributs de la classe. */
		this.lireProperties();
		
	} // Fin de CONSTRUCTEUR D'ARITE 1.____________________________________
	

	
	/**
	 * affiche le contenu de <code>this.propertiesPool</code>.<br/>
	 *
	 * @return : String : affichage.<br/>
	 */
	public final String afficherPropertiesPool() {
		return this.afficherJavaUtilProperties(
				this.propertiesPool);
	} // Fin de afficherPropertiesPool().__________________________________
	
	
	
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
	 * <li>lit poolMinSize</li>
	 * <li>lit poolMaxSize</li>
	 * <li>lit poolTimeOut</li>
	 * <li>lit poolIdleTestPeriod</li>
	 * <li>lit poolAcquireIncrement</li>
	 * <li><b>alimente <code>this.propertiesPool</code> 
	 * avec toutes les propriétés</b>.</li>
	 * </ul>
	 * </ul>
	 */
	private void lireProperties() {
		
		/* poolMinSize. */
		this.lirePoolMinSize();
		
		/* poolMaxSize. */
		this.lirePoolMaxSize();
		
		/* poolTimeOut. */
		this.lirePoolTimeOut();
		
		/* poolMaxStatements. */
		this.lirePoolMaxStatements();
		
		/* poolIdleTestPeriod. */
		this.lirePoolIdleTestPeriod();
		
		/* poolAcquireIncrement. */
		this.lirePoolAcquireIncrement();
				
		/* alimentation de propertiesPool. */
		this.alimenterPropertiesPool();
				
	} // Fin de lireProperties().__________________________________________
	

	
	/**
	 * <b>alimente <code>this.propertiesPool</code> 
	 * avec toutes les propriétés</b>.
	 */
	private void alimenterPropertiesPool() {
		
		if (this.poolMinSize != null) {
			this.propertiesPool
				.put("hibernate.c3p0.min_size", this.poolMinSize);
		}
		
		if (this.poolMaxSize != null) {
			this.propertiesPool
				.put("hibernate.c3p0.max_size", this.poolMaxSize);
		}
		
		if (this.poolTimeOut != null) {
			this.propertiesPool
				.put("hibernate.c3p0.timeout", this.poolTimeOut);
		}
		
		if (this.poolMaxStatements != null) {
			this.propertiesPool
				.put("hibernate.c3p0.max_statements", this.poolMaxStatements);
		}
		
		if (this.poolIdleTestPeriod != null) {
			this.propertiesPool
				.put("hibernate.c3p0.idle_test_period", this.poolIdleTestPeriod);
		}
		
		if (this.poolAcquireIncrement != null) {
			this.propertiesPool
				.put("hibernate.c3p0.acquire_increment", this.poolAcquireIncrement);
		}
		
	} // Fin de alimenterPropertiesPool()._________________________________
	

	
	/**
	 * <b>lit la Taille minimale du pool de connexion 
	 * C3P0 pour Hibernate</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.min_size</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.min_size</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.min_size</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return : String : this.poolMinSize.<br/>
	 */
	private String lirePoolMinSize() {
		
		if (this.environmentSpring != null) {
			
			this.poolMinSize 
				= this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.min_size");
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_POOLC3P0_HIBERNATE
				+ TIRET_ESPACE
				+ "Méthode lirePoolMinSize()"
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.poolMinSize;
		
	} // Fin de lirePoolMinSize()._________________________________________
	

	
	/**
	 * <b>lit la Taille maximale du pool de connexion 
	 * C3P0 pour Hibernate</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.max_size</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.max_size</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.max_size</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return : String : this.poolMaxSize.<br/>
	 */
	private String lirePoolMaxSize() {
		
		if (this.environmentSpring != null) {
			
			this.poolMaxSize 
				= this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.max_size");
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_POOLC3P0_HIBERNATE
				+ TIRET_ESPACE
				+ "Méthode lirePoolMaxSize()"
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.poolMaxSize;
		
	} // Fin de lirePoolMaxSize()._________________________________________
	

	
	/**
	 * <b>lit le Timeout du pool de connexion C3P0 pour Hibernate.</b>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.timeout</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.timeout</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.timeout</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return : String : this.poolTimeOut.<br/>
	 */
	private String lirePoolTimeOut() {
		
		if (this.environmentSpring != null) {
			
			this.poolTimeOut 
				= this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.timeout");
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_POOLC3P0_HIBERNATE
				+ TIRET_ESPACE
				+ "Méthode lirePoolTimeOut()"
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.poolTimeOut;
		
	} // Fin de lirePoolTimeOut()._________________________________________
	

	
	/**
	 * <b>lit le taille du cache de PreparedStatements du pool de connexion 
	 * C3P0 pour Hibernate</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.max_statements</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.max_statements</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.max_statements</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return : String : this.poolMaxStatements.<br/>
	 */
	private String lirePoolMaxStatements() {
		
		if (this.environmentSpring != null) {
			
			this.poolMaxStatements 
				= this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.max_statements");
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_POOLC3P0_HIBERNATE
				+ TIRET_ESPACE
				+ "Méthode lirePoolMaxStatements()"
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.poolMaxStatements;
		
	} // Fin de lirePoolMaxStatements().___________________________________
	

	
	/**
	 * <b>lit la période de recherche des connexions inactives 
	 * du pool de connexion C3P0 pour Hibernate</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.idle_test_period</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.idle_test_period</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.idle_test_period</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return : String : this.poolIdleTestPeriod.<br/>
	 */
	private String lirePoolIdleTestPeriod() {
		
		if (this.environmentSpring != null) {
			
			this.poolIdleTestPeriod 
				= this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.idle_test_period");
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_POOLC3P0_HIBERNATE
				+ TIRET_ESPACE
				+ "Méthode lirePoolIdleTestPeriod()"
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.poolIdleTestPeriod;
		
	} // Fin de lirePoolIdleTestPeriod().__________________________________
	

	
	/**
	 * <b>lit le nombre de connexions acquises en une seule fois 
	 * lorsque le pool de connexion C3P0 pour Hibernate est épuisé</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.acquire_increment</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.acquire_increment</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.acquire_increment</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return : String : this.poolAcquireIncrement.<br/>
	 */
	private String lirePoolAcquireIncrement() {
		
		if (this.environmentSpring != null) {
			
			this.poolAcquireIncrement 
				= this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.acquire_increment");
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_POOLC3P0_HIBERNATE
				+ TIRET_ESPACE
				+ "Méthode lirePoolAcquireIncrement()"
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.poolAcquireIncrement;
		
	} // Fin de lirePoolAcquireIncrement().________________________________
	

	
	/**
	 * <b>Getter de la Taille minimale du pool de connexion 
	 * C3P0 pour Hibernate</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.min_size</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.min_size</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.min_size</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.poolMinSize : String.<br/>
	 */
	public final String getPoolMinSize() {
		return this.poolMinSize;
	} // Fin de getPoolMinSize().__________________________________________
	

	
	/**
	 * <b>getter de la Taille maximale du pool de connexion 
	 * C3P0 pour Hibernate</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.max_size</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.max_size</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.max_size</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.poolMaxSize : String.<br/>
	 */
	public final String getPoolMaxSize() {
		return this.poolMaxSize;
	} // Fin de getPoolMaxSize().__________________________________________
	

	
	/**
	 * <b>getter du Timeout du pool de connexion C3P0 pour Hibernate</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.timeout</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.timeout</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.timeout</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.poolTimeOut : String.<br/>
	 */
	public final String getPoolTimeOut() {
		return this.poolTimeOut;
	} // Fin de getPoolTimeOut().__________________________________________
	

	
	/**
	 * <b>getter de la taille du cache de PreparedStatements 
	 * du pool de connexion 
	 * C3P0 pour Hibernate</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.max_statements</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.max_statements</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.max_statements</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.poolMaxStatements : String.<br/>
	 */
	public final String getPoolMaxStatements() {
		return this.poolMaxStatements;
	} // Fin de getPoolMaxStatements().____________________________________
	

	
	/**
	 * <b>getter de la période de recherche des connexions inactives 
	 * du pool de connexion C3P0 pour Hibernate</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.idle_test_period</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.idle_test_period</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.idle_test_period</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.poolIdleTestPeriod : String.<br/>
	 */
	public final String getPoolIdleTestPeriod() {
		return this.poolIdleTestPeriod;
	} // Fin de getPoolIdleTestPeriod().___________________________________
	

	
	/**
	 * <b>getter du nombre de connexions acquises en une seule fois 
	 * lorsque le pool de connexion C3P0 pour Hibernate est épuisé</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.c3p0.acquire_increment</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.c3p0.acquire_increment</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.c3p0.acquire_increment</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.poolAcquireIncrement : String.<br/>
	 */
	public final String getPoolAcquireIncrement() {
		return this.poolAcquireIncrement;
	} // Fin de getPoolAcquireIncrement()._________________________________


	
	/**
	 * Getter du java.util.Properties contenant toutes les propriétés 
	 * dépendant du POOL DE CONNEXION et du PROVIDER.<br/>
	 *
	 * @return this.propertiesPool : Properties.<br/>
	 */
	public final Properties getPropertiesPool() {
		return this.propertiesPool;
	} // Fin de getPropertiesPool()._______________________________________



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
	


} // FIN DE LA CLASSE LecteurPropertiesPoolHIKARICPHibernate.----------------
