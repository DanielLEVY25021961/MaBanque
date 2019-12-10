package levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.lecteur;

import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.Environment;

import levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.lecteur.lecteurjpadatasourcespring.ILecteurJPADataSourceSpring;
import levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.lecteur.lecteurjpadatasourcespring.impl.LecteurJPADataSourceSpring;

/**
 * CLASSE LecteurConfigurationBaseSpring :<br/>
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
public class LecteurConfigurationBaseSpring {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe LecteurConfigurationBaseSpring".
	 */
	public static final String CLASSE_LECTEUR_CONFIGURATION_BASE_SPRING 
		= "Classe LecteurConfigurationBaseSpring";
	
	
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
	 * "Méthode lirePersistenceUnitName()".
	 */
	public static final String METHODE_LIRE_PERSISTENCE_UNIT_NAME 
		= "Méthode lirePersistenceUnitName()";
	
	/**
	 * "Méthode lireTransactionType()".
	 */
	public static final String METHODE_LIRE_TRANSACTION_TYPE 
		= "Méthode lireTransactionType()";
	
	/**
	 * "environmentSpring NON INJECTE !!!".
	 */
	public static final String ENVT_SPRING_NON_INJECTE 
		= "environmentSpring NON INJECTE !!!";
	
	/**
	 * "javax.persistence.jdbc.persistence-unit.name".
	 */
	public static final String PERSISTENCE_UNIT_NAME_KEY 
		= "javax.persistence.jdbc.persistence-unit.name";
	
	/**
	 * javax.persistence.jdbc.persistence-unit.transaction-type.
	 */
	public static final String TRANSACTION_TYPE_KEY 
		= "javax.persistence.jdbc.persistence-unit.transaction-type";
		
	/**
	 * <b>lecteur SPRING du fichier properties 
	 * de configuration de la base</b>.
	 * <ul>
	 * <li>org.springframework.core.env.Environment</li>
	 * </ul>
	 */
	private Environment environmentSpring;
	
	/**
	 * <b>nom de l'unité de persistence</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.persistence-unit.name</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : 
	 * <code>persistence-unit.name</code> dans un persistence.xml 
	 * préconisé par JPA.</li>
	 * <li>clé : 
	 * <code>hibernate.ejb.persistenceUnitName</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String persistenceUnitName;
	
	/**
	 * <b>type de transaction (sous forme de String)</b> 
	 * <ul>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.persistence-unit.transaction-type</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : <code>persistence-unit.transaction-type</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.transaction.coordinator</code> 
	 * dans un EntityManagerFactory créé par le PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String typeTransaction;

	/**
	 * <b>Lecteur SPRING spécialisé dans la lecture des valeurs JPA 
	 * d'une DataSource [URL, Driver, Login, Password]</b>.<br/>
	 */
	private transient ILecteurJPADataSourceSpring lecteurJPADataSourceSpring;
	
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
	 * java.util.Properties contenant toutes les propriétés 
	 * utiles pour un EntityManagerFactory.
	 */
	private final transient Properties propertiesConfiguration = new Properties();
	
	/**
	 * <b>Lecteur SPRING spécialisé dans la lecture des valeurs  
	 * spécifiques à un PROVIDER [ddl-auto, Dialect, cache, ...]</b>.
	 */
	private transient LecteurPropertiesProviderHibernate lecteurPropertiesProviderHibernate;
	
	/**
	 * DIALECTE utilisé par le PROVIDER pour la BASE.<br/>
	 * par exemple : "org.hibernate.dialect.H2Dialect" 
	 * pour un PROVIDER HIBERNATE et une base H2.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.dialect</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.dialect</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.dialect</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String dialect;

	/**
	 * boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit afficher les requêtes SQL.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.show_sql</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.show_sql</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.show_sql</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String showSql;

	/**
	 * boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit formater les requêtes SQL.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.format_sql</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.format_sql</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.format_sql</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String formatSql;
	
	/**
	 * boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit commenter les requêtes SQL.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.use_sql_comments</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.use_sql_comments</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.use_sql_comments</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String useSqlComments;

	/**
	 * boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit générer des statistiques.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.generate_statistics</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.generate_statistics</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.generate_statistics</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String generateSatistics;
	
	/**
	 * <b>nom qualifié de la classe de non-cache de 2nd niveau.</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.cache.NoCacheProvider</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>cache.provider_class</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>cache.provider_class</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String noCacheProviderClass;
	
	/**
	 * <b>nom qualifié de la classe de cache de 2nd niveau.</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.cache.provider_class</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>cache.provider_class</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>cache.provider_class</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String cacheProviderClass;
	
	/**
	 * boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit utiliser le cache de second niveau.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.cache.use_second_level_cache</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>cache.use_second_level_cache</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>cache.use_second_level_cache</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String cacheUseSecondLevelCache;
	
	/**
	 * boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit utiliser le cache de requête de second niveau.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.cache.use_query_cache</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>cache.use_query_cache</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>cache.use_query_cache</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String cacheUseQueryCache;
	
	/**
	 * chemin qualifié du fichier déclaratif du cache 
	 * de 2nd niveau (ehcache.xml).
	 * <ul>
	 * <li>clé : 
	 * <code>net.sf.ehcache.configurationResourcename</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>net.sf.ehcache.configurationResourcename</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>net.sf.ehcache.configurationResourcename</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String resourceCache;
	
	/**
	 * valeur qui stipule si le PROVIDER 
	 * doit générer le schéma de création de tables.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.ddl-auto</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.hbm2ddl.auto</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.hbm2ddl.auto</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient String ddlAuto;

	/**
	 * <b>Lecteur SPRING spécialisé dans la lecture des valeurs  
	 * spécifiques à un POOL DE CONNEXION d'un PROVIDER 
	 * [poolMinSize, poolMaxSize, poolTimeOut, ...]</b>.
	 */
	private transient LecteurPropertiesPoolC3P0Hibernate lecteurPropertiesPoolC3P0Hibernate;
	
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
	 * <b>Lecteur SPRING spécialisé dans la lecture des valeurs  
	 * spécifiques à SPRING [generateDdl, springH2ConsolePath, ...]</b>.
	 */
	private transient LecteurPropertiesSpecSpring lecteurPropertiesSpecSpring;
	
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
		= LogFactory.getLog(LecteurConfigurationBaseSpring.class);

	// *************************METHODES************************************/
	
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public LecteurConfigurationBaseSpring() {		
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
	public LecteurConfigurationBaseSpring(
			final Environment pEnvironmentSpring) {
		
		super();
				
		this.environmentSpring = pEnvironmentSpring;
		
		/* alimente tous les attributs de la classe. */
		this.lireProperties();
		
	} // Fin de CONSTRUCTEUR D'ARITE 1.____________________________________
	

	
	/**
	 * <b>fournit une String pour l'affichage 
	 * du contenu (attributs et properties) encapsulé dans le present 
	 * LecteurConfigurationBaseSpring</b>.<br/>
	 *
	 * @return : String : affichage.<br/>
	 */
	@Override
	public final String toString() {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append("VALEURS provenant du fichier properties SPRING : ");
		stb.append(SAUT_LIGNE_PLATEFORME);

		
		// PERSISTENCE UNIT
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append(String.format(FORMAT_TOSTRING
				, "NOM DE L'UNITE DE PERSISTENCE (hibernate.ejb.persistenceUnitName)", this.getPersistenceUnitName()));
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		stb.append(String.format(FORMAT_TOSTRING
				, "TYPE DE TRANSACTION (hibernate.transaction.coordinator_class)", this.getTypeTransaction()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		
		// DATASOURCE
		stb.append(SAUT_LIGNE_PLATEFORME);
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

		
		// PROPERTIES PROVIDER
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "DIALECTE (hibernate.dialect)", this.getDialect()));
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		stb.append(String.format(FORMAT_TOSTRING
				, "DDL-AUTO (hibernate.hbm2ddl.auto)", this.getDdlAuto()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "SHOW_SQL (hibernate.show_sql)", this.getShowSql()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "FORMAT_SQL (hibernate.format_sql)", this.getFormatSql()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "USE_SQL_COMMENTS (hibernate.use_sql_comments)", this.getUseSqlComments()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "GENERATE_STATISTICS (hibernate.generate_statistics)", this.getGenerateSatistics()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		
		// PROPERTIES CACHE
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append(String.format(FORMAT_TOSTRING
				, "NO_CACHE_PROVIDER_CLASS (cache.provider_class)", this.getNoCacheProviderClass()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append(String.format(FORMAT_TOSTRING
				, "CACHE_PROVIDER_CLASS (cache.provider_class)", this.getCacheProviderClass()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "CACHE-USE_SECOND_LEVEL_CACHE (cache.use_second_level_cache)", this.getCacheUseSecondLevelCache()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "CACHE-USE_QUERY_CACHE (cache.use_query_cache)", this.getCacheUseQueryCache()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "RESOURCE_CACHE (net.sf.ehcache.configurationResourcename)", this.getResourceCache()));
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		
		// PROPERTIES POOL
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append(String.format(FORMAT_TOSTRING
				, "poolMinSize (hibernate.c3p0.min_size)", this.getPoolMinSize()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "poolMaxSize (hibernate.c3p0.max_size)", this.getPoolMaxSize()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "poolTimeOut (hibernate.c3p0.timeout)", this.getPoolTimeOut()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "poolMaxStatements (hibernate.c3p0.max_statements)", this.getPoolMaxStatements()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "poolIdleTestPeriod (hibernate.c3p0.idle_test_period)", this.getPoolIdleTestPeriod()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		
		// PROPERTIES SPRING
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append(String.format(FORMAT_TOSTRING
				, "generateDdl (spring.jpa.generate-ddl)", this.getGenerateDdl()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "springH2ConsoleEnabled (spring.h2.console.enabled)", this.getSpringH2ConsoleEnabled()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "springH2ConsolePath (spring.h2.console.path)", this.getSpringH2ConsolePath()));
		stb.append(SAUT_LIGNE_PLATEFORME);
	
		
		// AFFICHAGE DU PROPERTIES CONFIGURATION (SOMME DES PROPERTIES)
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append("PROPRIETES DANS this.propertiesConfiguration : ");
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append(this.afficherPropertiesConfiguration());


		return stb.toString();
		
	} // Fin de toString().________________________________________________
	

	
	/**
	 * affiche le contenu de <code>this.propertiesConfiguration</code>.<br/>
	 *
	 * @return : String : affichage.<br/>
	 */
	public final String afficherPropertiesConfiguration() {
		return this.afficherJavaUtilProperties(
				this.propertiesConfiguration);
	} // Fin de afficherPropertiesConfiguration()._________________________
	
	
	
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
	 * <li>automatiquement appelé par le constructeur d'arité 1</li>
	 * <ul>
	 * <li>lit le nom de l'unité de persistence (persistenceUnitName).</li>
	 * <li>lit le type de transaction (typeTransaction).</li>
	 * 
	 * <li>délègue à un <b><code>LecteurJPADataSourceSpring</code></b> 
	 * la lecture des valeurs de la DataSource.</li>
	 * <ul>
	 * <li>lit l'URL de la base (url).</li>
	 * <li>lit le DRIVER de la Base (driver).</li>
	 * <li>lit le LOGIN de la Base (userName).</li>
	 * <li>lit le PASSWORD de la Base (password).</li>
	 * </ul>
	 * 
	 * <li>délègue à un <b><code>LecteurPropertiesProviderHibernate</code></b> 
	 * la lecture des valeurs spécifiques au PROVIDER.</li>
	 * <li><b>alimente <code>this.propertiesConfiguration</code> 
	 * avec les propriétés spécifique au PROVIDER</b>.</li>
	 * <ul>
	 * <li>lit le DIALECTE de la base (dialect).</li>
	 * <li>lit le SHOW_SQL (showSql).</li>
	 * <li>lit le FORMAT_SQL (formatSql).</li>
	 * <li>lit le USE_SQL_COMMENTS (useSqlComments).</li>
	 * <li>lit le GENERATE_STATISTICS (generateStatistics).</li>
	 * <li>lit le NO_CACHE_PROVIDER_CLASS (noCacheProviderClass).</li>
	 * <li>lit le CACHE_PROVIDER_CLASS (cacheProviderClass).</li>
	 * <li>lit le CACHE-USE_SECOND_LEVEL_CACHE (cacheUseSecondLevelCache).</li>
	 * <li>lit le CACHE-USE_QUERY_CACHE (cacheUseQueryCache).</li>
	 * <li>lit le RESOURCE_CACHE (resourceCache).</li>
	 * <li>lit le DDL-AUTO (ddlAuto).</li>
	 * </ul>
	 * 
	 * <li>délègue à un <b><code>LecteurPropertiesPoolC3P0Hibernate</code></b> 
	 * la lecture des valeurs spécifiques au 
	 * POOL DE CONNEXION et au PROVIDER.</li>
	 * <li><b>alimente <code>this.propertiesConfiguration</code> 
	 * avec les propriétés spécifique au POOL et au PROVIDER</b>.</li>
	 * <ul>
	 * <li>lit les valeurs du Pool de connexion.</li>
	 * </ul>
	 * 
	 * <li>délègue à un <b><code>LecteurPropertiesSpecSpring</code></b>  
	 * la lecture des valeurs spécifiques à SPRING.</li>
	 * <li><b>alimente <code>this.propertiesConfiguration</code> 
	 * avec les propriétés spécifique à SPRING</b>.</li>
	 * <ul>
	 * <li>lit l'interrupteur generateDdl.</li>	  
	 * <li>lit le springH2ConsoleEnabled.</li>
	 * <li>lit le springH2ConsolePath.</li>
	 * </ul>
	 * </ul>
	 */
	private void lireProperties() {
		
		/* persistenceUnitName. */
		this.lirePersistenceUnitName();
		
		/* typeTransaction. */
		this.lireTypeTransaction();
		
		// DATASOURCE
		this.lecteurJPADataSourceSpring 
			= new LecteurJPADataSourceSpring(this.environmentSpring);
		
		/* URL. */
		this.url = this.lecteurJPADataSourceSpring.getUrl();
		
		/* DRIVER. */
		this.driver = this.lecteurJPADataSourceSpring.getDriver();
		
		/* userName. */
		this.userName = this.lecteurJPADataSourceSpring.getUserName();
		
		/* password. */
		this.password = this.lecteurJPADataSourceSpring.getPassword();
		
		// PROPERTIES spécifiques au Provider.
		this.lecteurPropertiesProviderHibernate 
			= new LecteurPropertiesProviderHibernate(this.environmentSpring);
		
		/* dialect. */
		this.dialect = this.lecteurPropertiesProviderHibernate.getDialect();
		
		/* showSql. */
		this.showSql = this.lecteurPropertiesProviderHibernate.getShowSql();
		
		/* formatSql. */
		this.formatSql = this.lecteurPropertiesProviderHibernate.getFormatSql();
		
		/* useSqlComments. */
		this.useSqlComments = this.lecteurPropertiesProviderHibernate.getUseSqlComments();
		
		/* generateStatistics. */
		this.generateSatistics = this.lecteurPropertiesProviderHibernate.getGenerateSatistics();
		
		/* noCacheProviderClass. */
		this.noCacheProviderClass = this.lecteurPropertiesProviderHibernate.getNoCacheProviderClass();
		
		/* cacheProviderClass. */
		this.cacheProviderClass = this.lecteurPropertiesProviderHibernate.getCacheProviderClass();
		
		/* cacheUseSecondLevelCache. */
		this.cacheUseSecondLevelCache = this.lecteurPropertiesProviderHibernate.getCacheUseSecondLevelCache();
		
		/* cacheUseQueryCache. */
		this.cacheUseQueryCache = this.lecteurPropertiesProviderHibernate.getCacheUseQueryCache();
		
		/* resourceCache. */
		this.resourceCache = this.lecteurPropertiesProviderHibernate.getResourceCache();
		
		/* ddlAuto. */
		this.ddlAuto = this.lecteurPropertiesProviderHibernate.getDdlAuto();
		
		/* alimente this.propertiesConfiguration. */
		this.alimenterPropertiesProvider();
		
		// PROPERTIES spécifiques au POOL DE CONNEXION et au Provider.
		this.lecteurPropertiesPoolC3P0Hibernate 
			= new LecteurPropertiesPoolC3P0Hibernate(this.environmentSpring);
		/* pool. */
		this.poolMinSize 
			= this.lecteurPropertiesPoolC3P0Hibernate.getPoolMinSize();
		this.poolMaxSize 
			= this.lecteurPropertiesPoolC3P0Hibernate.getPoolMaxSize();
		this.poolTimeOut 
			= this.lecteurPropertiesPoolC3P0Hibernate.getPoolTimeOut();
		this.poolMaxStatements 
			= this.lecteurPropertiesPoolC3P0Hibernate.getPoolMaxStatements();
		this.poolIdleTestPeriod 
			= this.lecteurPropertiesPoolC3P0Hibernate.getPoolIdleTestPeriod();
		this.poolAcquireIncrement 
			= this.lecteurPropertiesPoolC3P0Hibernate.getPoolAcquireIncrement();
		
		/* alimente this.propertiesConfiguration. */
		this.alimenterPropertiesPool();
		
		// PROPERTIES spécifiques à Spring.
		this.lecteurPropertiesSpecSpring 
			= new LecteurPropertiesSpecSpring(this.environmentSpring);
		/* generateDdl. */
		this.generateDdl = this.lecteurPropertiesSpecSpring.getGenerateDdl();
		
		/* springH2ConsoleEnabled. */
		this.springH2ConsoleEnabled 
			= this.lecteurPropertiesSpecSpring.getSpringH2ConsoleEnabled();
		
		/* springH2ConsolePath. */
		this.springH2ConsolePath 
			= this.lecteurPropertiesSpecSpring.getSpringH2ConsolePath();
		
		/* alimente this.propertiesConfiguration. */
		this.alimenterPropertiesSpecSpring();
		
	} // Fin de lireProperties().__________________________________________
	

	
	/**
	 * <b>alimente <code>this.propertiesConfiguration</code> 
	 * avec les propriétés spécifique au PROVIDER</b>.
	 */
	private void alimenterPropertiesProvider() {
		this.propertiesConfiguration.putAll(
				this.lecteurPropertiesProviderHibernate
					.getPropertiesProvider());
	} // Fin de alimenterPropertiesProvider()._____________________________

	
	
	/**
	 * <b>alimente <code>this.propertiesConfiguration</code> 
	 * avec les propriétés spécifique au POOL et au PROVIDER</b>.
	 */
	private void alimenterPropertiesPool() {
		this.propertiesConfiguration.putAll(
				this.lecteurPropertiesPoolC3P0Hibernate
					.getPropertiesPool());
	} // Fin de alimenterPropertiesPool()._________________________________

	
	
	/**
	 * <b>alimente <code>this.propertiesConfiguration</code> 
	 * avec les propriétés spécifique à SPRING</b>.
	 */
	private void alimenterPropertiesSpecSpring() {
		this.propertiesConfiguration.putAll(
				this.lecteurPropertiesSpecSpring
					.getPropertiesSpecSpring());
	} // Fin de alimenterPropertiesSpecSpring().___________________________

	
	
	/**
	 * <b>lit la valeur de persistenceUnitName 
	 * dans le properties SPRING fourni par l'Environment.</b>
	 * <ul>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.persistence-unit.name</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : 
	 * <code>persistence-unit.name</code> dans un persistence.xml 
	 * préconisé par JPA.</li>
	 * <li>clé : 
	 * <code>hibernate.ejb.persistenceUnitName</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.persistenceUnitName : String : 
	 * nom de l'unité de persistance.<br/>
	 */
	private String lirePersistenceUnitName() {
		
		if (this.environmentSpring != null) {
			
			this.persistenceUnitName 
				= this.environmentSpring.getProperty(
					PERSISTENCE_UNIT_NAME_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_CONFIGURATION_BASE_SPRING
				+ TIRET_ESPACE
				+ METHODE_LIRE_PERSISTENCE_UNIT_NAME
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.persistenceUnitName;
		
	} // Fin de lirePersistenceUnitName()._________________________________
	

	
	/**
	 * <b>lit la valeur de typeTransaction 
	 * dans le properties SPRING fourni par l'Environment.</b>
	 *
	 * @return this.typeTransaction : String : 
	 * type de transaction.<br/>
	 */
	private String lireTypeTransaction() {
		
		if (this.environmentSpring != null) {
			
			this.typeTransaction 
				= this.environmentSpring.getProperty(
						TRANSACTION_TYPE_KEY);
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_CONFIGURATION_BASE_SPRING
				+ TIRET_ESPACE
				+ METHODE_LIRE_TRANSACTION_TYPE
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.typeTransaction;
		
	} // Fin de lireTypeTransaction()._____________________________________


	
	/**
	 * Getter du <b>nom de l'unité de persistence</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.persistence-unit.name</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : 
	 * <code>persistence-unit.name</code> dans un persistence.xml 
	 * préconisé par JPA.</li>
	 * <li>clé : 
	 * <code>hibernate.ejb.persistenceUnitName</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.persistenceUnitName : String.<br/>
	 */
	public final String getPersistenceUnitName() {
		return this.persistenceUnitName;
	} // Fin de getPersistenceUnitName().__________________________________
	
	
	
	/**
	 * Getter du <b>type de transaction (sous forme de String)</b> 
	 * <ul>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.persistence-unit.transaction-type</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : <code>persistence-unit.transaction-type</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.transaction.coordinator</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.typeTransaction : String.<br/>
	 */
	public final String getTypeTransaction() {
		return this.typeTransaction;
	} // Fin de getTypeTransaction().______________________________________

	
		
	/**
	 * Getter du <b>Lecteur SPRING spécialisé dans la lecture 
	 * des valeurs JPA 
	 * d'une DataSource [URL, Driver, Login, Password]</b>.<br/>.
	 *
	 * @return this.lecteurJPADataSourceSpring : 
	 * LecteurJPADataSourceSpring.<br/>
	 */
	public final ILecteurJPADataSourceSpring getLecteurJPADataSourceSpring() {
		return this.lecteurJPADataSourceSpring;
	} // Fin de getLecteurJPADataSourceSpring().___________________________



	/**
	 * Getter de l'URL de la BASE.
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
	 * @return this.url : String.<br/>
	 */
	public final String getUrl() {
		return this.url;
	} // Fin de getUrl().__________________________________________________


	
	/**
	 * Getter du DRIVER JDBC de la BASE (sous forme de String).
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
	 *
	 * @return this.driver : String.<br/>
	 */
	public final String getDriver() {
		return this.driver;
	} // Fin de getDriver()._______________________________________________


	
	/**
	 * Getter du LOGIN de la BASE.
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
	 * @return this.userName : String.<br/>
	 */
	public final String getUserName() {
		return this.userName;
	} // Fin de getUserName()._____________________________________________


	
	/**
	 * Getter du MOT DE PASSE de la BASE.
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
	 * @return this.password : String.<br/>
	 */
	public final String getPassword() {
		return this.password;
	} // Fin de getPassword()._____________________________________________


		
	/**
	 * Getter du java.util.Properties contenant toutes les propriétés 
	 * utiles pour un EntityManagerFactory.
	 *
	 * @return this.propertiesConfiguration : Properties.<br/>
	 */
	public final Properties getPropertiesConfiguration() {
		return this.propertiesConfiguration;
	} // Fin de getPropertiesConfiguration().______________________________



	/**
	 * Getter du DIALECTE utilisé par le PROVIDER pour la BASE.<br/>
	 * par exemple : "org.hibernate.dialect.H2Dialect" 
	 * pour un PROVIDER HIBERNATE et une base H2.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.dialect</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.dialect</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.dialect</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.dialect : String.<br/>
	 */
	public final String getDialect() {
		return this.dialect;
	} // Fin de getDialect().______________________________________________


	
	/**
	 * Getter du boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit afficher les requêtes SQL.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.show_sql</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.show_sql</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.show_sql</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.showSql : String.<br/>
	 */
	public final String getShowSql() {
		return this.showSql;
	} // Fin de getShowSql().______________________________________________


	
	/**
	 * Getter du boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit formater les requêtes SQL.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.format_sql</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.format_sql</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.format_sql</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.formatSql : String.<br/>
	 */
	public final String getFormatSql() {
		return this.formatSql;
	} // Fin de getFormatSql().____________________________________________


	
	/**
	 * getter du boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit commenter les requêtes SQL.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.use_sql_comments</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.use_sql_comments</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.use_sql_comments</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 * 
	 * @return this.useSqlComments : String.<br/>
	 */
	public final String getUseSqlComments() {
		return this.useSqlComments;
	} // Fin de getUseSqlComments()._______________________________________


	
	/**
	 * getter du boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit générer des statistiques.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.generate_statistics</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.generate_statistics</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.generate_statistics</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return String : 
	 * generateSatistics dans le Properties du CONTENEUR.<br/>
	 */
	public final String getGenerateSatistics() {
		return this.generateSatistics;
	} // Fin de getGenerateSatistics().____________________________________


	
	/**
	 * getter du <b>nom qualifié de la classe de non-cache de 2nd niveau.</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.cache.NoCacheProvider</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>cache.provider_class</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>cache.provider_class</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.noCacheProviderClass : String : 
	 * noCacheProviderClass dans le Properties du CONTENEUR.<br/>
	 */
	public final String getNoCacheProviderClass() {		
		return this.noCacheProviderClass;		
	} // Fin de getNoCacheProviderClass()._________________________________


	
	/**
	 * getter du <b>nom qualifié de la classe de cache de 2nd niveau.</b>.
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.cache.provider_class</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>cache.provider_class</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>cache.provider_class</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.cacheProviderClass : String : 
	 * cacheProviderClass dans le Properties du CONTENEUR.<br/>
	 */
	public final String getCacheProviderClass() {
		return this.cacheProviderClass;
	} // Fin de getCacheProviderClass().___________________________________


	
	/**
	 * boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit utiliser le cache de second niveau.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.cache.use_second_level_cache</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>cache.use_second_level_cache</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>cache.use_second_level_cache</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.cacheUseSecondLevelCache : String : 
	 * cacheUseSecondLevelCache dans le Properties du CONTENEUR.<br/>
	 */
	public final String getCacheUseSecondLevelCache() {
		return this.cacheUseSecondLevelCache;
	} // Fin de getCacheUseSecondLevelCache()._____________________________


	
	/**
	 * boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit utiliser le cache de requête de second niveau.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.cache.use_query_cache</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>cache.use_query_cache</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>cache.use_query_cache</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.cacheUseQueryCache : String : 
	 * cacheUseSecondLevelCache dans le Properties du CONTENEUR.<br/>
	 */
	public final String getCacheUseQueryCache() {
		return this.cacheUseQueryCache;
	} // Fin de getCacheUseSecondLevelCache()._____________________________


	
	/**
	 * getter du chemin qualifié du fichier déclaratif du cache 
	 * de 2nd niveau (ehcache.xml).
	 * <ul>
	 * <li>clé : 
	 * <code>net.sf.ehcache.configurationResourcename</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>net.sf.ehcache.configurationResourcename</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>net.sf.ehcache.configurationResourcename</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.resourceCache : String : 
	 * resourceCache dans le Properties du CONTENEUR.<br/>
	 */
	public final String getResourceCache() {
		return this.resourceCache;
	} // Fin de getResourceCache().________________________________________
	

	
	/**
	 * getter de la valeur qui stipule si le PROVIDER 
	 * doit générer le schéma de création de tables.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.properties.hibernate.ddl-auto</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>hibernate.hbm2ddl.auto</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.hbm2ddl.auto</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return this.ddlAuto : String : 
	 * ddlAuto dans le Properties du CONTENEUR.<br/>
	 */
	public final String getDdlAuto() {
		return this.ddlAuto;
	} // Fin de getDdlAuto().______________________________________________
	

	
	/**
	 * Getter du <b>Lecteur SPRING spécialisé dans la lecture des valeurs  
	 * spécifiques à un PROVIDER [ddl-auto, Dialect, cache, ...]</b>.
	 *
	 * @return this.lecteurPropertiesProviderHibernate : 
	 * LecteurPropertiesProviderHibernate.<br/>
	 */
	public LecteurPropertiesProviderHibernate 
					getLecteurPropertiesProviderHibernate() {
		return this.lecteurPropertiesProviderHibernate;
	} // Fin de getLecteurPropertiesProviderHibernate().___________________



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
	 * Getter du <b>Lecteur SPRING spécialisé dans la lecture des valeurs  
	 * spécifiques à un POOL DE CONNEXION d'un PROVIDER 
	 * [poolMinSize, poolMaxSize, poolTimeOut, ...]</b>.
	 *
	 * @return this.lecteurPropertiesPoolC3P0Hibernate : 
	 * LecteurPropertiesPoolC3P0Hibernate.<br/>
	 */
	public LecteurPropertiesPoolC3P0Hibernate getLecteurPropertiesPoolC3P0Hibernate() {
		return this.lecteurPropertiesPoolC3P0Hibernate;
	} // Fin de getLecteurPropertiesPoolC3P0Hibernate().___________________


		
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
	 * getter du valeur qui stipule pour SPRING 
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
	 * Getter du <b>Lecteur SPRING spécialisé dans la lecture des valeurs  
	 * spécifiques à SPRING [generateDdl, springH2ConsolePath, ...]</b>.
	 *
	 * @return this.lecteurPropertiesSpecSpring : LecteurPropertiesSpecSpring.<br/>
	 */
	public final LecteurPropertiesSpecSpring getLecteurPropertiesSpecSpring() {
		return this.lecteurPropertiesSpecSpring;
	} // Fin de getLecteurPropertiesSpecSpring().__________________________



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

	
	
} // FIN DE LA CLASSE LecteurConfigurationBaseSpring.------------------------
