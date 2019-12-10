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
 * CLASSE LecteurPropertiesProviderHibernate :<br/>
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
public class LecteurPropertiesProviderHibernate {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe LecteurPropertiesProviderHibernate".
	 */
	public static final String CLASSE_LECTEUR_PROPERTIES_PROVIDER_HIBERNATE 
		= "Classe LecteurPropertiesProviderHibernate";
	
	
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
	 * "Méthode lireDialect()".
	 */
	public static final String METHODE_LIRE_DIALECT 
		= "Méthode lireDialect()";

	/**
	 * "Méthode lireShowSql()".
	 */
	public static final String METHODE_LIRE_SHOWSQL 
		= "Méthode lireShowSql()";
	
	/**
	 * "Méthode lireFormatSql()".
	 */
	public static final String METHODE_LIRE_FORMATSQL 
		= "Méthode lireFormatSql()";
	
	/**
	 * "Méthode lireUseSqlComments".
	 */
	public static final String METHODE_LIRE_USESQLCOMMENTS 
		= "Méthode lireUseSqlComments";
	
	/**
	 * "Méthode lireGenerateStatistics()".
	 */
	public static final String METHODE_LIRE_GENERATESTATISTICS 
		= "Méthode lireGenerateStatistics()";
	
	/**
	 * "Méthode lireNoCacheProviderClass()".
	 */
	public static final String METHODE_LIRE_NOCACHEPROVIDERCLASS 
		= "Méthode lireNoCacheProviderClass()";
	
	/**
	 * "Méthode lireCacheProviderClass()".
	 */
	public static final String METHODE_LIRE_CACHEPROVIDERCLASS 
		= "Méthode lireCacheProviderClass()";
	
	/**
	 * "Méthode lireCacheUseSecondLevelCache()".
	 */
	public static final String METHODE_LIRE_CACHEUSESECONDLEVELCACHE 
		= "Méthode lireCacheUseSecondLevelCache()";
	
	/**
	 * "Méthode lireCacheUseQueryCache()".
	 */
	public static final String METHODE_LIRE_CACHEUSEQUERYCACHE 
		= "Méthode lireCacheUseQueryCache()";
	
	/**
	 * "Méthode lireResourceCache()".
	 */
	public static final String METHODE_LIRE_RESOURCECACHE 
		= "Méthode lireResourceCache()";
	
	/**
	 * "Méthode lireDdlAuto()".
	 */
	public static final String METHODE_LIRE_DDL_AUTO 
		= "Méthode lireDdlAuto()";
	
	/**
	 * "environmentSpring NON INJECTE !!!".
	 */
	public static final String ENVT_SPRING_NON_INJECTE 
		= "environmentSpring NON INJECTE !!!";
	
	/**
	 * "spring.jpa.properties.hibernate.dialect".
	 */
	public static final String DIALECT_KEY 
		= "spring.jpa.properties.hibernate.dialect";

	/**
	 * "spring.jpa.properties.hibernate.show_sql".
	 */
	public static final String SHOWSQL_KEY 
		= "spring.jpa.properties.hibernate.show_sql";
	
	/**
	 * "spring.jpa.properties.hibernate.format_sql".
	 */
	public static final String FORMATSQL_KEY 
		= "spring.jpa.properties.hibernate.format_sql";

	/**
	 * "spring.jpa.properties.hibernate.use_sql_comments".
	 */
	public static final String USESQLCOMMENTS_KEY 
		= "spring.jpa.properties.hibernate.use_sql_comments";
	
	/**
	 * "spring.jpa.properties.hibernate.generate_statistics".
	 */
	public static final String GENERATESTATISTICS_KEY 
		= "spring.jpa.properties.hibernate.generate_statistics";
	
	/**
	 * "spring.jpa.properties.cache.NoCacheProvider".
	 */
	public static final String NOCACHEPROVIDERCLASS_KEY 
		= "spring.jpa.properties.cache.NoCacheProvider";
	
	/**
	 * "spring.jpa.properties.cache.provider_class".
	 */
	public static final String CACHEPROVIDERCLASS_KEY 
		= "spring.jpa.properties.cache.provider_class";
	
	/**
	 * "spring.jpa.properties.cache.use_second_level_cache".
	 */
	public static final String CACHEUSESECONDLEVELCACHE_KEY 
		= "spring.jpa.properties.cache.use_second_level_cache";
	
	/**
	 * "spring.jpa.properties.cache.use_query_cache".
	 */
	public static final String CACHEUSEQUERYCACHE_KEY 
		= "spring.jpa.properties.cache.use_query_cache";
	
	/**
	 * "net.sf.ehcache.configurationResourcename".
	 */
	public static final String RESOURCECACHE_KEY 
		= "net.sf.ehcache.configurationResourcename";
	
	/**
	 * "cache.provider_class".<br/>
	 */
	public static final String CACHE_PROVIDER_CLASS_KEY 
		= "cache.provider_class";
	
	/**
	 * "spring.jpa.properties.hibernate.ddl-auto".
	 */
	public static final String DDLAUTO_KEY 
		= "spring.jpa.properties.hibernate.ddl-auto";
	
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
	 * dépendant du PROVIDER.<br/>.
	 */
	private final transient Properties propertiesProvider = new Properties();
	
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
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(LecteurPropertiesProviderHibernate.class);

	// *************************METHODES************************************/
	
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public LecteurPropertiesProviderHibernate() {		
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
	public LecteurPropertiesProviderHibernate(
			final Environment pEnvironmentSpring) {
		
		super();
				
		this.environmentSpring = pEnvironmentSpring;
		
		/* alimente tous les attributs de la classe. */
		this.lireProperties();
		
	} // Fin de CONSTRUCTEUR D'ARITE 1.____________________________________
	

	
	/**
	 * affiche le contenu de <code>this.propertiesProvider</code>.<br/>
	 *
	 * @return : String : affichage.<br/>
	 */
	public final String afficherPropertiesProvider() {
		return this.afficherJavaUtilProperties(
				this.propertiesProvider);
	} // Fin de afficherPropertiesProvider().______________________________
	
	
	
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
	 * <li><b>alimente <code>this.propertiesProvider</code> 
	 * avec toutes les propriétés</b>.</li>
	 * </ul>
	 * </ul>
	 */
	private void lireProperties() {
		
		/* dialect. */
		this.lireDialect();
		
		/* showSql. */
		this.lireShowSql();
		
		/* formatSql. */
		this.lireFormatSql();
		
		/* useSqlComments. */
		this.lireUseSqlComments();
		
		/* generateStatistics. */
		this.lireGenerateStatistics();
		
		/* noCacheProviderClass. */
		this.lireNoCacheProviderClass();
		
		/* cacheProviderClass. */
		this.lireCacheProviderClass();
		
		/* cacheUseSecondLevelCache. */
		this.lireCacheUseSecondLevelCache();
		
		/* cacheUseQueryCache. */
		this.lireCacheUseQueryCache();
		
		/* resourceCache. */
		this.lireResourceCache();
		
		/* ddlAuto. */
		this.lireDdlAuto();
		
		/* alimentation de propertiesProvider. */
		this.alimenterPropertiesProvider();
				
	} // Fin de lireProperties().__________________________________________
	

	
	/**
	 * <b>alimente <code>this.propertiesProvider</code> 
	 * avec toutes les propriétés</b>.
	 */
	private void alimenterPropertiesProvider() {
		
		if (this.dialect != null) {
			this.propertiesProvider
				.put("hibernate.dialect", this.dialect);
		}
		
		if (this.showSql != null) {
			this.propertiesProvider
				.put("hibernate.show_sql", this.showSql);
		}
		
		if (this.formatSql != null) {
			this.propertiesProvider
				.put("hibernate.format_sql", this.formatSql);
		}
		
		if (this.useSqlComments != null) {
			this.propertiesProvider
				.put("hibernate.use_sql_comments", this.useSqlComments);
		}
		
		if (this.generateSatistics != null) {
			this.propertiesProvider
				.put("hibernate.generate_statistics", this.generateSatistics);
		}
		
		if (this.noCacheProviderClass != null) {
			this.propertiesProvider
				.put(CACHE_PROVIDER_CLASS_KEY, this.noCacheProviderClass);
		}
		
		if (this.cacheProviderClass != null) {
			this.propertiesProvider
				.put(CACHE_PROVIDER_CLASS_KEY, this.cacheProviderClass);
		}
		
		if (this.cacheUseSecondLevelCache != null) {
			this.propertiesProvider
				.put("cache.use_second_level_cache"
						, this.cacheUseSecondLevelCache);
		}
		
		if (this.cacheUseQueryCache != null) {
			this.propertiesProvider
				.put("cache.use_query_cache"
						, this.cacheUseQueryCache);
		}
		
		if (this.resourceCache != null) {
			this.propertiesProvider
				.put("net.sf.ehcache.configurationResourcename"
						, this.resourceCache);
		}
		
		if (this.ddlAuto != null) {
			this.propertiesProvider
				.put("hibernate.hbm2ddl.auto"
						, this.ddlAuto);
		}
		
	} // Fin de alimenterPropertiesProvider()._____________________________


	
	/**
	 * <b>lit le DIALECTE utilisé par le PROVIDER pour la BASE</b>.<br/>
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
	 * @return : String : this.dialect.<br/>
	 */
	private String lireDialect() {
		
		if (this.environmentSpring != null) {
			
			this.dialect 
				= this.environmentSpring.getProperty(
						DIALECT_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_PROVIDER_HIBERNATE
				+ TIRET_ESPACE
				+ METHODE_LIRE_DIALECT
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.dialect;
		
	} // Fin de lireDialect().____________________________________________
	
	
	
	/**
	 * <b>lit boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit afficher les requêtes SQL</b>.<br/>
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
	 * @return : String : this.showSql.<br/>
	 */
	private String lireShowSql() {
		
		if (this.environmentSpring != null) {
			
			this.showSql 
				= this.environmentSpring.getProperty(
						SHOWSQL_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_PROVIDER_HIBERNATE 
				+ TIRET_ESPACE
				+ METHODE_LIRE_SHOWSQL
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.showSql;
		
	} // Fin de lireShowSql()._____________________________________________
	
	
	
	/**
	 * <b>lit le boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit formater les requêtes SQL</b>.<br/>
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
	 * @return : String : this.showSql.<br/>
	 */
	private String lireFormatSql() {
		
		if (this.environmentSpring != null) {
			
			this.formatSql 
				= this.environmentSpring.getProperty(
						FORMATSQL_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_PROVIDER_HIBERNATE 
				+ TIRET_ESPACE
				+ METHODE_LIRE_FORMATSQL
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.formatSql;
		
	} // Fin de lireFormatSql().___________________________________________
	

	
	/**
	 * <b>lit le boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit commenter les requêtes SQL</b>.<br/>
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
	 * @return : String : this.useSqlComments.<br/>
	 */
	private String lireUseSqlComments() {
		
		if (this.environmentSpring != null) {
			
			this.useSqlComments 
				= this.environmentSpring.getProperty(
						USESQLCOMMENTS_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_PROVIDER_HIBERNATE 
				+ TIRET_ESPACE
				+ METHODE_LIRE_USESQLCOMMENTS
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.useSqlComments;

	} // Fin de lireUseSqlComments().______________________________________
	

	
	/**
	 * <b>lit le boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit générer des statistiques</b>.<br/>
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
	 * @return : String : this.generateSatistics.<br/>
	 */
	private String lireGenerateStatistics() {
		
		if (this.environmentSpring != null) {
			
			this.generateSatistics 
				= this.environmentSpring.getProperty(
						GENERATESTATISTICS_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_PROVIDER_HIBERNATE 
				+ TIRET_ESPACE
				+ METHODE_LIRE_GENERATESTATISTICS
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.generateSatistics;

	} // Fin de lireGenerateStatistics().__________________________________
	

	
	/**
	 * <b>lit le nom qualifié de la classe de non-cache de 2nd niveau.</b>.
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
	 * @return : String : this.noCacheProviderClass.<br/>
	 */
	private String lireNoCacheProviderClass() {
		
		if (this.environmentSpring != null) {
			
			this.noCacheProviderClass 
				= this.environmentSpring.getProperty(
						NOCACHEPROVIDERCLASS_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_PROVIDER_HIBERNATE 
				+ TIRET_ESPACE
				+ METHODE_LIRE_NOCACHEPROVIDERCLASS
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.noCacheProviderClass;

	} // Fin de lireNoCacheProviderClass().________________________________
	

	
	/**
	 * <b>lit le nom qualifié de la classe de non-cache de 2nd niveau.</b>.
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
	 * @return : String : this.cacheProviderClass.<br/>
	 */
	private String lireCacheProviderClass() {
		
		if (this.environmentSpring != null) {
			
			this.cacheProviderClass 
				= this.environmentSpring.getProperty(
						CACHEPROVIDERCLASS_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_PROVIDER_HIBERNATE 
				+ TIRET_ESPACE
				+ METHODE_LIRE_CACHEPROVIDERCLASS
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.cacheProviderClass;

	} // Fin de lireCacheProviderClass().__________________________________

	
	
	/**
	 * <b>lit le boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit utiliser le cache de second niveau</b>.<br/>
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
	 * @return : String : this.cacheUseSecondLevelCache.<br/>
	 */
	private String lireCacheUseSecondLevelCache() {
		
		if (this.environmentSpring != null) {
			
			this.cacheUseSecondLevelCache
				= this.environmentSpring.getProperty(
						CACHEUSESECONDLEVELCACHE_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_PROVIDER_HIBERNATE 
				+ TIRET_ESPACE
				+ METHODE_LIRE_CACHEUSESECONDLEVELCACHE
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.cacheUseSecondLevelCache;

	} // Fin de lireCacheUseSecondLevelCache().____________________________

	
	
	/**
	 * <b>lit le boolean (sous forme String) qui stipule si le PROVIDER 
	 * doit utiliser le cache de requête de second niveau</b>.<br/>
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
	 * @return : String : this.cacheUseQueryCache.<br/>
	 */
	private String lireCacheUseQueryCache() {
		
		if (this.environmentSpring != null) {
			
			this.cacheUseQueryCache
				= this.environmentSpring.getProperty(
						CACHEUSEQUERYCACHE_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_PROVIDER_HIBERNATE 
				+ TIRET_ESPACE
				+ METHODE_LIRE_CACHEUSEQUERYCACHE
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.cacheUseQueryCache;

	} // Fin de lireCacheUseQueryCache().__________________________________

	
	
	/**
	 * <b>lit le chemin qualifié du fichier déclaratif du cache 
	 * de 2nd niveau (ehcache.xml)</b>.<br/>
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
	 * @return : String : this.resourceCache.<br/>
	 */
	private String lireResourceCache() {
		
		if (this.environmentSpring != null) {
			
			this.resourceCache
				= this.environmentSpring.getProperty(
						RESOURCECACHE_KEY);
									
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_PROVIDER_HIBERNATE 
				+ TIRET_ESPACE
				+ METHODE_LIRE_RESOURCECACHE
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.resourceCache;

	} // Fin de lireResourceCache()._______________________________________
	
	
	
	/**
	 * <b>lit la valeur ddl-auto dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.ddlAuto</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>hibernate.hbm2ddl.auto</code> (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.ddlAuto.<br/>
	 */
	private String lireDdlAuto() {
		
		if (this.environmentSpring != null) {
			
			this.ddlAuto 
				= this.environmentSpring.getProperty(
						DDLAUTO_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_PROVIDER_HIBERNATE 
				+ TIRET_ESPACE
				+ METHODE_LIRE_DDL_AUTO
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.ddlAuto;
		
	} // Fin de lireDdlAuto()._____________________________________________


	
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
	 * Getter du java.util.Properties contenant toutes les propriétés 
	 * dépendant du PROVIDER.<br/>
	 *
	 * @return this.propertiesProvider : Properties.<br/>
	 */
	public final Properties getPropertiesProvider() {
		return this.propertiesProvider;
	} // Fin de getPropertiesProvider().____________________________________



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

	

} // FIN DE LA CLASSE LecteurPropertiesProviderHibernate.--------------------
