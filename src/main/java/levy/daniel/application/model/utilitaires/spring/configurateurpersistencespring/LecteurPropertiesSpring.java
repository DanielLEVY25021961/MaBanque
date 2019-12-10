package levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring;

import java.net.URL;
import java.sql.Driver;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.PersistenceUnitTransactionType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import levy.daniel.application.model.utilitaires.normalizerurlbase.NormalizerUrlBase;
import levy.daniel.application.model.utilitaires.normalizerurlbase.UrlEncapsulation;

/**
 * CLASSE LecteurPropertiesSpring :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * afficher java.util.Properties, afficher Properties,<br/> 
 * afficherProperties, <br/>
 * afficher Liste String, afficherListeString, <br/>
 * tri Set de String, trier Set String, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 20 janv. 2019
 *
 */
//@Configuration(value="LecteurPropertiesSpring")
//@PropertySources({@PropertySource("classpath:configurations_bases_jpa/configuration_H2_file.properties")})
public class LecteurPropertiesSpring {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Classe LecteurPropertiesSpring".
	 */
	public static final String CLASSE_LECTEUR_PROPERTIES_SPRING 
		= "Classe LecteurPropertiesSpring";
	
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
	 * "environmentSpring NON INJECTE !!!".
	 */
	public static final String ENVT_SPRING_NON_INJECTE 
		= "environmentSpring NON INJECTE !!!";
	
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
	 * <b>lecteur SPRING du fichier properties</b> proposé 
	 * dans l'annotation sur la présente classe 'PropertySource'.
	 * <ul>
	 * <li>injecté par SPRING via le Setter 
	 * <code>setEnvironmentSpring(Environment pEnvironmentSpring)</code>
	 * .</li>
	 * <li>org.springframework.core.env.Environment</li>
	 * </ul>
	 */
	private Environment environmentSpring;
	
	/**
	 * conteneur de type 
	 * <code>org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo</code> 
	 * pour les valeurs lues dans le properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.
	 */
	public MutablePersistenceUnitInfoJPASpringSansXML persistenceUnitInfoJPASansXML 
		= new MutablePersistenceUnitInfoJPASpringSansXML();

	/**
	 * URL de la BASE.
	 * <ul>
	 * <li>stocké dans l'objet <b>DataSource</b> 
	 * (this.jtaDataSource ou this.nonJtaDataSource) du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * DRIVER de la BASE.
	 * <ul>
	 * <li>stocké dans l'objet <b>DataSource</b> 
	 * (this.jtaDataSource ou this.nonJtaDataSource) du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	private transient Driver driver;
	
	/**
	 * LOGIN de la BASE.
	 * <ul>
	 * <li>stocké dans l'objet <b>DataSource</b> 
	 * (this.jtaDataSource ou this.nonJtaDataSource) du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * <li>stocké dans l'objet <b>DataSource</b> 
	 * (this.jtaDataSource ou this.nonJtaDataSource) du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * SimpleDriverDataSource utilisé dans la présente classe.
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>déduite de transactionType 
	 * (jtaDataSource existe si transactionType = JTA).</li>
	 * </ul>
	 */
	private transient SimpleDriverDataSource dataSource;
	
	/**
	 * DataSource fournie par le conteneur de Servlet (TOMCAT) 
	 * via JNDI en cas de transactions JTA.
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>déduite de transactionType 
	 * (jtaDataSource existe si transactionType = JTA).</li>
	 * <li>clé : element <code>jta-data-source</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.connection.datasource</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient SimpleDriverDataSource jtaDataSource;
	
	/**
	 * DataSource de l'application Standalone 
	 * en cas de transactions RESOURCE_LOCAL (pas de TOMCAT).
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>déduite de transactionType 
	 * (nonJtaDataSource existe si transactionType 
	 * = RESOURCE_LOCAL ou NULL).</li>
	 * <li>clé : element <code>non-jta-data-source</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.connection.datasource</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 */
	private transient SimpleDriverDataSource nonJtaDataSource;

	/**
	 * liste des <b>noms qualifiés des 
	 * fichiers de Mapping (orm.xml)</b> des Entities JPA 
	 * mappées pour management 
	 * par JPA dans un persistence.xml.<br/>
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>optionnel si on utilise les annotations 
	 * sur les classes Entities.</li>
	 * <li>Correspond au <code>mapping-file</code> element 
	 * dans un persistence.xml.</li>
	 * <li>un orm.xml prévaut toujours sur les annotations 
	 * lorsque l'on utilise des Entities JPA 
	 * <b>annotées</b>.</li>
	 * </ul>
	 */
	private List<String> mappingFileNames = new LinkedList<String>();
	
	/**
	 * liste des URL des jar que l'ORM doit inspecter.<br/>
	 * chaque URL Correspond au <code>jar-file</code> element 
	 * dans un persistence.xml.<br/>
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * </ul>
	 */
	private List<URL> jarFileUrls = new LinkedList<URL>();
	
	/**
	 * URL référençant un jar ou un répertoire <b>racine</b> 
	 * de l'unité de persistence.
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * </ul>
	 */
	private URL persistenceUnitRootUrl;
	
	/**
	 * liste des <b>noms qualifiés</b> des 
	 * classes Entities JPA mappées pour management 
	 * par JPA dans un persistence.xml.<br/>
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>Correspond au <code>class</code> element 
	 * dans un persistence.xml.</li>
	 * <li>par exemple : <br/>
	 * <i>"levy.daniel.application.model.persistence.metier
	 * .contactsimple.entities.jpa.ContactSimpleEntityJPA"</i></li>
	 * <li>Sans Objet lorsque l'on utilise des Entities JPA 
	 * <b>annotées</b> découvertes par JPA.</li>
	 * </ul>
	 */
	private List<String> managedClassNames = new LinkedList<String>();
	
	/**
	 * liste des <b>noms qualifiés</b> des 
	 * packages d'Entities JPA mappées pour management 
	 * par JPA dans un persistence.xml.<br/>
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>INUTILISE.</li>
	 * </ul>
	 */
	private List<String> managedPackages = new LinkedList<String>();
	
	/**
	 * boolean qui stipule que l'ORM ne doit manager 
	 * que les classes Entities JPA listées dans le persistence.xml 
	 * si il est à true.<br/>
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>correspond au <code>exclude-unlisted-classes</code> 
	 * element dans un persistence.xml</li>
	 * <li>toujours false si on utilise les classes annotées. </li>
	 * </ul>
	 */
	private boolean excludeUnlistedClasses;
	
	/**
	 * mode d'utilisation du cache de 2nd niveau par l'ORM.
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>correspond au <code>shared-cache-mode</code> 
	 * element dans un persistence.xml</li>
	 * <li><code>javax.persistence.sharedCache.mode</code> 
	* dans l'EntityManagerFactory.</li>
	 * </ul>
	 */
	private SharedCacheMode sharedCacheMode = SharedCacheMode.UNSPECIFIED;
	
	/**
	 * mode de validation utilisé par l'ORM.
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>correspond au <code>validation-mode</code> element 
	 * dans un persistence.xml</li>
	 * </ul>
	 */
	private ValidationMode validationMode = ValidationMode.AUTO;
	
	/**
	 * "2.1"
	 */
	public static final String JPA_VERSION = "2.1";

	/**
	 * version de JPA utilisée dans le persistence.xml (2.1, 3.0, ...).
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>correspond à l'attribut <code>version</code> de l'element
	 * racine <code>persistence</code> 
	 * dans un persistence.xml</li>
	 * </ul>
	 */
	private String persistenceXMLSchemaVersion = JPA_VERSION;

	/**
	 * nom qualifié du package du PROVIDER de persistence (Hibernate).
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * </ul>
	 */
	private String persistenceProviderPackageName;
	
	/**
	 * DIALECTE utilisé par le PROVIDER pour la BASE.<br/>
	 * par exemple : "org.hibernate.dialect.H2Dialect" 
	 * pour un PROVIDER HIBERNATE et une base H2.
	 * <ul>
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * chemin qualifié du fichier déclaratif du cache de 2nd niveau (ehcache.xml).
	 * <ul>
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * Taille minimale du pool de connexion.
	 * <ul>
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * Taille maximale du pool de connexion.
	 * <ul>
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * Timeout du pool de connexion.
	 * <ul>
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * taille du cache de PreparedStatements du pool de connexion.
	 * <ul>
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * période de recherche des connexions inactives du pool de connexion.
	 * <ul>
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * lorsque le pool de connexion est épuisé.
	 * <ul>
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * boolean (sous forme String) qui stipule si SPRING 
	 * doit générer le schéma de création de tables.<br/>
	 * Interrupteur général exclusif à SPRING.<br/>
	 * <ul>
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
	 * <li>clé : 
	 * <code>spring.jpa.generate-ddl</code> 
	 * dans le fichier properties SPRING</li>
	 * </ul>
	 */
	private transient String generateDdl;
	
	/**
	 * valeur qui stipule si le PROVIDER 
	 * doit générer le schéma de création de tables.<br/>
	 * <ul>
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * boolean (sous forme String) qui stipule si SPRING 
	 * doit autoriser la console pour une base H2.<br/>
	 * Interrupteur général exclusif à SPRING.<br/>
	 * <ul>
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
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
	 * <li>stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.</li>
	 * <li>clé : 
	 * <code>spring.h2.console.path</code> 
	 * dans le fichier properties SPRING</li>
	 * </ul>
	 */
	private transient String springH2ConsolePath;
	
	/**
	 * java.util.Properties contenu dans le CONTENEUR 
	 * <code>this.persistenceUnitInfoJPASansXML</code> 
	 * de la présente classe.
	 */
	private transient Properties propertiesConteneur;
	
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
	public static final String CACHE_PROVIDER_CLASS 
		= "cache.provider_class";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(LecteurPropertiesSpring.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public LecteurPropertiesSpring() {		
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
	public LecteurPropertiesSpring(
			final Environment pEnvironmentSpring) {
		
		super();
				
		this.environmentSpring = pEnvironmentSpring;
		
		/* alimente tous les attributs de la classe. */
		this.lireProperties();
		
	} // Fin de CONSTRUCTEUR D'ARITE 1.____________________________________
	
	
	
	/**
	 * <b>Crée un bean nommé "lecteurPropertiesSpring" 
	 * qui retourne la présente instance et la place 
	 * dans le conteneur SPRING</b>.<br/>
	 * <ul>
	 * <li><b>this.environmentSpring</b> 
	 * (org.springframework.core.env.Environment) est 
	 * <i>déjà injecté</i> par SPRING via son setter 
	 * lors de la création de ce présent BEAN.</li>
	 * <li><b>lit le properties</b> proposé dans l'annotation 
	 * sur la présente classe 'PropertySource' 
	 * grace à this.environmentSpring</li>
	 * </ul>
	 *
	 * @return : LecteurPropertiesSpring :  .<br/>
	 */
	@Bean(value = "lecteurPropertiesSpring")
	public LecteurPropertiesSpring lecteurPropertiesSpring() {
		
		/* alimente tous les attributs de la classe. */
		this.lireProperties();
		
		System.out.println("DANS LE BEAN LecteurPropertiesSpring");
		return this;
	}


	
	/**
	 * <b>Lit le fichier properties proposé 
	 * dans l'annotation sur la présente classe 'PropertySource' 
	 * et alimente le CONTENEUR 
	 * <code>this.persistenceUnitInfoJPASansXML</code> 
	 * avec toutes les valeurs lues</b>.
	 * <ul>
	 * <li>automatiquement appelé par la méthode de création 
	 * de BEAN SPRING <code>this.lecteurPropertiesSpring()</code></li>
	 * <li>appelée <i>après</i> l'injection de 
	 * <code>this.environmentSpring</code> par SPRING 
	 * dans la présente classe.</li>
	 * <ul>
	 * <li>lit le nom de l'unité de persistence (persistenceUnitName).</li>
	 * <li>lit le type de transaction (transactionType).</li>
	 * <li>passe HIBERNATE pour SPRING comme PersistenceProvider.</li>
	 * 
	 * <li>lit l'URL de la base (url).</li>
	 * <li>lit le DRIVER de la Base (driver).</li>
	 * <li>lit le LOGIN de la Base (userName).</li>
	 * <li>lit le PASSWORD de la Base (password).</li>
	 * <li>ALIMENTE jtaDataSource et nonJtaDataSource.</li>
	 * 
	 * <li>passe une liste vide au CONTENEUR 
	 * <code>this.persistenceUnitInfoJPASansXML</code> 
	 * pour mapping-files.</li>
	 * <li>passe une liste vide au CONTENEUR 
	 * <code>this.persistenceUnitInfoJPASansXML</code> 
	 * pour jarFileUrls.</li>
	 * <li>passe null au CONTENEUR 
	 * <code>this.persistenceUnitInfoJPASansXML</code> 
	 * pour persistenceUnitRootUrl.</li>
	 * <li>passe une liste vide au CONTENEUR 
	 * <code>this.persistenceUnitInfoJPASansXML</code> 
	 * pour managedClassNames.</li>
	 * <li>passe une liste vide au CONTENEUR 
	 * <code>this.persistenceUnitInfoJPASansXML</code> 
	 * pour managedPackages.</li>
	 * <li>passe false au CONTENEUR 
	 * <code>this.persistenceUnitInfoJPASansXML</code> 
	 * pour excludeUnlistedClasses.</li>
	 * <li>passe UNSPECIFIED au CONTENEUR 
	 * <code>this.persistenceUnitInfoJPASansXML</code> 
	 * pour sharedCacheMode.</li>
	 * <li>passe AUTO au CONTENEUR 
	 * <code>this.persistenceUnitInfoJPASansXML</code> 
	 * pour validationMode.</li>
	 *<li>passe 2.1 au CONTENEUR 
	 * <code>this.persistenceUnitInfoJPASansXML</code> 
	 * pour persistenceXMLSchemaVersion.</li>
	 * <li>passe null au CONTENEUR 
	 * <code>this.persistenceUnitInfoJPASansXML</code> 
	 * pour persistenceProviderPackageName.</li>
	 * 
	 * 
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
	 * <li>lit les valeurs du Pool de connexion.</li>
	 * <li>lit l'interrupteur generateDdl.</li>
	 * <li>lit le DDL-AUTO (ddlAuto).</li>
	 * <li>lit le springH2ConsoleEnabled.</li>
	 * <li>lit le springH2ConsolePath.</li>
	 * <li>alimente <code>this.propertiesConteneur</code> 
	 * avec le java.util.Properties contenu dans le 
	 * CONTENEUR <code>this.persistenceUnitInfoJPASansXML</code>.</li>
	 * </ul>
	 * </ul>
	 */
	private void lireProperties() {
		
		/* persistenceUnitName. */
		this.persistenceUnitInfoJPASansXML
			.setPersistenceUnitName(this.lirePersistenceUnitName());
		
		/* transactionType. */
		this.persistenceUnitInfoJPASansXML
			.setTransactionType(this.lireTransactionType());
		
		/* persistenceProvider. */
		this.persistenceUnitInfoJPASansXML
			.setPersistenceProviderClassName(this.lirePersistenceProvider());
		
		/* URL. */
		this.lireUrl();
		
		/* DRIVER. */
		this.lireDriver();
		
		/* userName. */
		this.lireUserName();
		
		/* password. */
		this.lirePassword();
		
		/* jtaDataSource et nonJtaDataSource. */
		this.determinerTypeDataSource();
		
		/* mapping-files. */
		this.persistenceUnitInfoJPASansXML
			.setMappingFileNames(this.mappingFileNames);
		
		/* jarFileUrls. */
		this.persistenceUnitInfoJPASansXML
			.setJarFileUrls(this.jarFileUrls);
		
		/* persistenceUnitRootUrl. */
		this.persistenceUnitInfoJPASansXML
			.setPersistenceUnitRootUrl(this.persistenceUnitRootUrl);
		
		/* managedClassNames. */
		this.persistenceUnitInfoJPASansXML
			.setManagedClassNames(this.managedClassNames);
		
		/* managedPackages. */
		this.persistenceUnitInfoJPASansXML
			.setManagedPackages(this.managedPackages);
		
		/* excludeUnlistedClasses. */
		this.persistenceUnitInfoJPASansXML
			.setExcludeUnlistedClasses(this.excludeUnlistedClasses);
		
		/* sharedCacheMode. */
		this.persistenceUnitInfoJPASansXML
			.setSharedCacheMode(this.sharedCacheMode);
		
		/* validationMode. */
		this.persistenceUnitInfoJPASansXML
			.setValidationMode(this.validationMode);
		
		/* persistenceXMLSchemaVersion. */
		this.persistenceUnitInfoJPASansXML
			.setPersistenceXMLSchemaVersion(
					this.persistenceXMLSchemaVersion);

		/* persistenceProviderPackageName. */
		this.persistenceUnitInfoJPASansXML
			.setPersistenceProviderPackageName(
					this.persistenceProviderPackageName);
		
		
		
		this.persistenceUnitInfoJPASansXML
			.setProperties(new Properties());
		
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
		
		/* pool. */
		this.lirePoolMinSize();
		this.lirePoolMaxSize();
		this.lirePoolTimeOut();
		this.lirePoolMaxStatements();
		this.lirePoolIdleTestPeriod();
		this.lirePoolAcquireIncrement();
		
		/* generateDdl. */
		this.lireGenerateDdl();
		
		/* ddlAuto. */
		this.lireDdlAuto();
		
		/* springH2ConsoleEnabled. */
		this.lireSpringH2ConsoleEnabled();
		
		/* springH2ConsolePath. */
		this.lireSpringH2ConsolePath();
		
		/* alimente this.propertiesConteneur 
		 * avec le java.util.Properties contenu 
		 * dans this.persistenceUnitInfoJPASansXML. */
		this.propertiesConteneur 
			= this.persistenceUnitInfoJPASansXML.getProperties();
		
	} // Fin de lireProperties().__________________________________________
	

	
	/**
	 * <b>lit la valeur de persistenceUnitName dans le properties</b>
	 * <ul>
	 * <li>correspond à l'élément 
	* <code>persistence-unit.name</code> dans un persistence.xml 
	* préconisé par JPA.</li>
	* </ul>
	 *
	 * @return : String : nom de l'unité de persistance.<br/>
	 */
	private String lirePersistenceUnitName() {
		
		String persistenceUnitName = null;
		
		if (this.environmentSpring != null) {
			
			persistenceUnitName 
				= this.environmentSpring.getProperty(
					PERSISTENCE_UNIT_NAME_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
				+ TIRET_ESPACE
				+ METHODE_LIRE_PERSISTENCE_UNIT_NAME
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return persistenceUnitName;
		
	} // Fin de lirePersistenceUnitName()._________________________________


	
	/**
	 * <b>lit la valeur de transactionType dans le properties</b>
	 * <ul>
	 * <li>correspond à l'élément 
	* <code>persistence-unit.transaction-type</code> 
	* dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : PersistenceUnitTransactionType : 
	 * type de transaction.<br/>
	 */
	private PersistenceUnitTransactionType lireTransactionType() {
		
		String transactionTypeString = null;
		PersistenceUnitTransactionType transactionType = null; 
		
		if (this.environmentSpring != null) {
			
			transactionTypeString 
				= this.environmentSpring.getProperty(
						TRANSACTION_TYPE_KEY);
			
			if ("JTA".equalsIgnoreCase(transactionTypeString)) {
				transactionType 
					= PersistenceUnitTransactionType.JTA;
			} else if ("RESOURCE_LOCAL"
					.equalsIgnoreCase(transactionTypeString)) {
				transactionType 
					= PersistenceUnitTransactionType.RESOURCE_LOCAL;
			}
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
				+ TIRET_ESPACE
				+ METHODE_LIRE_TRANSACTION_TYPE
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return transactionType;
		
	} // Fin de lireTransactionType()._____________________________________
	

	
	/**
	 * <b>retourne toujours le JpaVendorAdapter HIBERNATE pour SPRING : 
	 * org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter</b>.<br/>
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>un persistence.xml ne comporte pas obligatoirement 
	 * l'élément <code>provider</code> si on 
	 * veut le rendre indépendant de l'ORM.</li>
	 * </ul>
	 *
	 * @return : String : 
	 * org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter.<br/>
	 */
	private String lirePersistenceProvider() {
		return HibernateJpaVendorAdapter.class.getName();
	} // Fin de lirePersistenceProvider()._________________________________
	
	
	
	/**
	 * <b>lit la valeur de URL dans le properties et la normalise</b>.<br/>
	 * injecte la valeur lue dans <code>this.url</code>.<br/>
	 * <ul>
	 * <li>correspond à l'élément property nommé
	 * <code>javax.persistence.jdbc.url</code> dans un persistence.xml 
	 * préconisé par JPA.</li>
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
				
				final String bd = "base-adresses_javafx";
				
				String urlNormalisee = null;
				
				final UrlEncapsulation encapsulation 
				 	= NormalizerUrlBase.creerUrlEncapsulation(urlFournie, bd);
				 
				urlNormalisee = encapsulation.getUrlNormalisee();
				
				this.url = urlNormalisee;
				
			}			
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <ul>
	 * <li>correspond à l'élément property nommé
	 * <code>javax.persistence.jdbc.driver</code> dans un persistence.xml 
	 * préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : this.driver : java.sql.Driver.<br/>
	 */
	private Driver lireDriver() {
				
		if (this.environmentSpring != null) {
			
			final String driverString 
				= this.environmentSpring.getProperty(
					DRIVER_KEY);
			
			if (driverString != null) {
							
				try {
					
					this.driver 
					= (Driver) Class.forName(driverString).newInstance();
					
				} catch (Exception e) {
					
					final String message 
						= CLASSE_LECTEUR_PROPERTIES_SPRING 
						+ TIRET_ESPACE
						+ METHODE_LIRE_DRIVER
						+ TIRET_ESPACE
						+ "impossible de charger le DRIVER : " 
						+ driverString;
					
					if (LOG.isFatalEnabled()) {
						LOG.fatal(message, e);
					}
				}
								
			}
					
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <li>correspond à l'élément property nommé
	 * <code>javax.persistence.jdbc.user</code> dans un persistence.xml 
	 * préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.userName.<br/>
	 */
	private String lireUserName() {
		
		if (this.environmentSpring != null) {
			
			this.userName 
				= this.environmentSpring.getProperty(
						USERNAME_KEY);
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <li>correspond à l'élément property nommé
	 * <code>javax.persistence.jdbc.password</code> dans un persistence.xml 
	 * préconisé par JPA.</li>
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
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>détermine le type de DataSource 
	 * (jtaDataSource ou nonJtaDataSource) 
	 * en fonction du type de transaction transactionType.</b>.<br/>
	 * <ul>
	 * <li>alimente <code>this.dataSource</code> en fonction 
	 * de la détermination.</li>
	 * <li>alimente <code>this.jtaDataSource</code> 
	 * et <code>this.nonJtaDataSource</code>.</li>
	 * <li>injecte les jtaDataSource et nonJtaDataSource 
	 * dans le conteneur <code>this.persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>retourne une nonJtaDataSource alimentée en 
	 * [driver, URL, userName, password] 
	 * si transactionType == null.</li>
	 * <li>retourne une jtaDataSource alimentée en 
	 * [driver, URL, userName, password] 
	 * si transactionType == JTA.</li>
	 * <li>retourne une nonJtaDataSource alimentée en 
	 * [driver, URL, userName, password] 
	 * si transactionType == RESOURCE_LOCAL.</li>
	 * </ul>
	 *
	 * @return : org.springframework.jdbc.datasource.SimpleDriverDataSource : 
	 * this.jtaDataSource ou  this.nonJtaDataSource.<br/>
	 */
	private SimpleDriverDataSource determinerTypeDataSource() {
		
		if (this.getTransactionType() == null) {
			
			this.jtaDataSource = null;
			
			this.nonJtaDataSource 
				= new SimpleDriverDataSource(
						this.driver
						, this.url
						, this.userName, this.password);
			
			/* alimente this.dataSource en fonction 
			 * de la détermination. */
			this.dataSource = this.nonJtaDataSource;
			
			/* injecte les jtaDataSource et nonJtaDataSource 
			 * dans le conteneur this.persistenceUnitInfoJPASansXML. */
			this.persistenceUnitInfoJPASansXML
				.setJtaDataSource(this.jtaDataSource);
			
			this.persistenceUnitInfoJPASansXML
				.setNonJtaDataSource(this.nonJtaDataSource);
			
			return this.nonJtaDataSource;
			
		} else if (this.getTransactionType()
				.equals(PersistenceUnitTransactionType.JTA)) {
			
			this.jtaDataSource 
				= new SimpleDriverDataSource(
					this.driver
					, this.url
					, this.userName, this.password);
			
			/* alimente this.dataSource en fonction 
			 * de la détermination. */
			this.dataSource = this.jtaDataSource;
			
			this.nonJtaDataSource = null;
			
			/* injecte les jtaDataSource et nonJtaDataSource 
			 * dans le conteneur this.persistenceUnitInfoJPASansXML. */
			this.persistenceUnitInfoJPASansXML
				.setJtaDataSource(this.jtaDataSource);
			
			this.persistenceUnitInfoJPASansXML
				.setNonJtaDataSource(this.nonJtaDataSource);
			
			return this.jtaDataSource;
			
		} else if (this.getTransactionType()
				.equals(PersistenceUnitTransactionType.RESOURCE_LOCAL)) {
			
			this.jtaDataSource = null;
			
			this.nonJtaDataSource 
				= new SimpleDriverDataSource(
						this.driver
						, this.url
						, this.userName, this.password);
						
			/* alimente this.dataSource en fonction 
			 * de la détermination. */
			this.dataSource = this.nonJtaDataSource;
			
			/* injecte les jtaDataSource et nonJtaDataSource 
			 * dans le conteneur this.persistenceUnitInfoJPASansXML. */
			this.persistenceUnitInfoJPASansXML
				.setJtaDataSource(this.jtaDataSource);
			
			this.persistenceUnitInfoJPASansXML
				.setNonJtaDataSource(this.nonJtaDataSource);
			
			return this.nonJtaDataSource;
		}
		
		this.jtaDataSource = null;
		this.nonJtaDataSource = null;
		
		/* alimente this.dataSource en fonction 
		 * de la détermination. */
		this.dataSource = null;
		
		/* injecte les jtaDataSource et nonJtaDataSource 
		 * dans le conteneur this.persistenceUnitInfoJPASansXML. */
		this.persistenceUnitInfoJPASansXML
			.setJtaDataSource(this.jtaDataSource);
		
		this.persistenceUnitInfoJPASansXML
			.setNonJtaDataSource(this.nonJtaDataSource);
		
		return null;
		
	} // Fin de determinerTypeDataSource().________________________________
	

	
	/**
	 * <b>lit la valeur du DIALECTE de BD dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.dialect</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>hibernate.dialect</code> (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.dialect.<br/>
	 */
	private String lireDialect() {
		
		if (this.environmentSpring != null) {
			
			this.dialect 
				= this.environmentSpring.getProperty(
						DIALECT_KEY);
			
			/* ajout de la valeur dans le Property. */
			if (this.persistenceUnitInfoJPASansXML != null) {
				this.persistenceUnitInfoJPASansXML
				.addProperty("hibernate.dialect", this.dialect);
			}
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur SHOW_SQL dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.showSql</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>hibernate.show_sql</code> (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.showSql.<br/>
	 */
	private String lireShowSql() {
		
		if (this.environmentSpring != null) {
			
			this.showSql 
				= this.environmentSpring.getProperty(
						SHOWSQL_KEY);
			
			/* ajout de la valeur dans le Property. */
			if (this.persistenceUnitInfoJPASansXML != null) {
				this.persistenceUnitInfoJPASansXML
				.addProperty("hibernate.show_sql", this.showSql);
			}
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur FORMAT_SQL dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.formatSql</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>hibernate.format_sql</code> (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.showSql.<br/>
	 */
	private String lireFormatSql() {
		
		if (this.environmentSpring != null) {
			
			this.formatSql 
				= this.environmentSpring.getProperty(
						FORMATSQL_KEY);
			
			/* ajout de la valeur dans le Property. */
			if (this.persistenceUnitInfoJPASansXML != null) {
				this.persistenceUnitInfoJPASansXML
				.addProperty("hibernate.format_sql", this.formatSql);
			}
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur USE_SQL_COMMENTS dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.useSqlComments</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>hibernate.use_sql_comments</code> (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.useSqlComments.<br/>
	 */
	private String lireUseSqlComments() {
		
		if (this.environmentSpring != null) {
			
			this.useSqlComments 
				= this.environmentSpring.getProperty(
						USESQLCOMMENTS_KEY);
			
			/* ajout de la valeur dans le Property. */
			if (this.persistenceUnitInfoJPASansXML != null) {
				this.persistenceUnitInfoJPASansXML
				.addProperty("hibernate.use_sql_comments"
							, this.useSqlComments);
			}
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur GENERATE_STATISTICS dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.generateSatistics</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>hibernate.generate_statistics</code> (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.generateSatistics.<br/>
	 */
	private String lireGenerateStatistics() {
		
		if (this.environmentSpring != null) {
			
			this.generateSatistics 
				= this.environmentSpring.getProperty(
						GENERATESTATISTICS_KEY);
			
			/* ajout de la valeur dans le Property. */
			if (this.persistenceUnitInfoJPASansXML != null) {
				this.persistenceUnitInfoJPASansXML
				.addProperty("hibernate.generate_statistics"
							, this.generateSatistics);
			}
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur CACHE-PROVIDER_CLASS 
	 * en cas d'<b>absence</b> de cache de 2nd niveau 
	 * dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.noCacheProviderClass</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>cache.provider_class</code> (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.noCacheProviderClass.<br/>
	 */
	private String lireNoCacheProviderClass() {
		
		if (this.environmentSpring != null) {
			
			this.noCacheProviderClass 
				= this.environmentSpring.getProperty(
						NOCACHEPROVIDERCLASS_KEY);
			
			/* ajout de la valeur dans le Property. */
			if (this.noCacheProviderClass != null) {
				
				this.persistenceUnitInfoJPASansXML
				.addProperty(CACHE_PROVIDER_CLASS
							, this.noCacheProviderClass);
				
			}
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur CACHE-PROVIDER_CLASS 
	 * en cas de <b>présence</b> de cache de 2nd niveau 
	 * dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.cacheProviderClass</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>cache.provider_class</code> (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.cacheProviderClass.<br/>
	 */
	private String lireCacheProviderClass() {
		
		if (this.environmentSpring != null) {
			
			this.cacheProviderClass 
				= this.environmentSpring.getProperty(
						CACHEPROVIDERCLASS_KEY);
			
			/* ajout de la valeur dans le Property. */
			if (this.cacheProviderClass != null) {
				
				this.persistenceUnitInfoJPASansXML
					.addProperty(CACHE_PROVIDER_CLASS
							, this.cacheProviderClass);
				
			}
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur CACHE-USE_SECOND_LEVEL_CACHE 
	 * en cas de <b>présence</b> de cache de 2nd niveau 
	 * dans le properties</b>.<br/>
	 * injecte la valeur lue dans 
	 * <code>this.cacheUseSecondLevelCache</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>cache.use_second_level_cache</code> (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.cacheUseSecondLevelCache.<br/>
	 */
	private String lireCacheUseSecondLevelCache() {
		
		if (this.environmentSpring != null) {
			
			this.cacheUseSecondLevelCache
				= this.environmentSpring.getProperty(
						CACHEUSESECONDLEVELCACHE_KEY);
			
			/* ajout de la valeur dans le Property. */
			if (this.cacheProviderClass != null) {
				
				this.persistenceUnitInfoJPASansXML
					.addProperty("cache.use_second_level_cache"
							, this.cacheUseSecondLevelCache);
				
			}
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur CACHE-USE_QUERY_CACHE 
	 * en cas de <b>présence</b> de cache de 2nd niveau 
	 * dans le properties</b>.<br/>
	 * injecte la valeur lue dans 
	 * <code>this.cacheUseQueryCache</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>cache.use_query_cache</code> (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.cacheUseQueryCache.<br/>
	 */
	private String lireCacheUseQueryCache() {
		
		if (this.environmentSpring != null) {
			
			this.cacheUseQueryCache
				= this.environmentSpring.getProperty(
						CACHEUSEQUERYCACHE_KEY);
			
			/* ajout de la valeur dans le Property. */
			if (this.cacheProviderClass != null) {
				
				this.persistenceUnitInfoJPASansXML
					.addProperty("cache.use_query_cache"
							, this.cacheUseQueryCache);
				
			}
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur RESOURCE_CACHE 
	 * en cas de <b>présence</b> de cache de 2nd niveau 
	 * dans le properties</b>.<br/>
	 * injecte la valeur lue dans 
	 * <code>this.resourceCache</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>net.sf.ehcache.configurationResourcename</code> 
	 * (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.resourceCache.<br/>
	 */
	private String lireResourceCache() {
		
		if (this.environmentSpring != null) {
			
			this.resourceCache
				= this.environmentSpring.getProperty(
						RESOURCECACHE_KEY);
			
			/* ajout de la valeur dans le Property. */
			if (this.cacheProviderClass != null) {
				
				this.persistenceUnitInfoJPASansXML
					.addProperty("net.sf.ehcache.configurationResourcename"
							, this.resourceCache);
				
			}
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur du poolMinSize dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.poolMinSize</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>hibernate.c3p0.min_size</code> 
	 * (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.poolMinSize.<br/>
	 */
	private String lirePoolMinSize() {
		
		if (this.environmentSpring != null) {
			
			this.poolMinSize 
				= this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.min_size");
			
			/* ajout de la valeur dans le Property. */
			if (this.poolMinSize != null) {
				
				this.persistenceUnitInfoJPASansXML
					.addProperty("hibernate.c3p0.min_size"
							, this.poolMinSize);
				
			}
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur du poolMaxSize dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.poolMaxSize</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>hibernate.c3p0.max_size</code> 
	 * (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.poolMaxSize.<br/>
	 */
	private String lirePoolMaxSize() {
		
		if (this.environmentSpring != null) {
			
			this.poolMaxSize 
				= this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.max_size");
			
			/* ajout de la valeur dans le Property. */
			if (this.poolMaxSize != null) {
				
				this.persistenceUnitInfoJPASansXML
					.addProperty("hibernate.c3p0.max_size"
							, this.poolMaxSize);
				
			}
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur du poolTimeOut dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.poolTimeOut</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>hibernate.c3p0.timeout</code> 
	 * (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.poolTimeOut.<br/>
	 */
	private String lirePoolTimeOut() {
		
		if (this.environmentSpring != null) {
			
			this.poolTimeOut 
				= this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.timeout");
			
			/* ajout de la valeur dans le Property. */
			if (this.poolTimeOut != null) {
				
				this.persistenceUnitInfoJPASansXML
					.addProperty("hibernate.c3p0.timeout"
							, this.poolTimeOut);
				
			}
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur du poolMaxStatements dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.poolMaxStatements</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>hibernate.c3p0.max_statements</code> 
	 * (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.poolMaxStatements.<br/>
	 */
	private String lirePoolMaxStatements() {
		
		if (this.environmentSpring != null) {
			
			this.poolMaxStatements 
				= this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.max_statements");
			
			/* ajout de la valeur dans le Property. */
			if (this.poolMaxStatements != null) {
				
				this.persistenceUnitInfoJPASansXML
					.addProperty("hibernate.c3p0.max_statements"
							, this.poolMaxStatements);
				
			}
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur du poolIdleTestPeriod dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.poolIdleTestPeriod</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>hibernate.c3p0.idle_test_period</code> 
	 * (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.poolIdleTestPeriod.<br/>
	 */
	private String lirePoolIdleTestPeriod() {
		
		if (this.environmentSpring != null) {
			
			this.poolIdleTestPeriod 
				= this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.idle_test_period");
			
			/* ajout de la valeur dans le Property. */
			if (this.poolIdleTestPeriod != null) {
				
				this.persistenceUnitInfoJPASansXML
					.addProperty("hibernate.c3p0.idle_test_period"
							, this.poolIdleTestPeriod);
				
			}
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur du poolAcquireIncrement dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.poolAcquireIncrement</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>hibernate.c3p0.acquire_increment</code> 
	 * (pour un provider HIBERNATE) 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 *
	 * @return : String : this.poolAcquireIncrement.<br/>
	 */
	private String lirePoolAcquireIncrement() {
		
		if (this.environmentSpring != null) {
			
			this.poolAcquireIncrement 
				= this.environmentSpring.getProperty(
						"spring.jpa.properties.hibernate.c3p0.acquire_increment");
			
			/* ajout de la valeur dans le Property. */
			if (this.poolAcquireIncrement != null) {
				
				this.persistenceUnitInfoJPASansXML
					.addProperty("hibernate.c3p0.acquire_increment"
							, this.poolAcquireIncrement);
				
			}
			
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur SPRING generate-ddl dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.generateDdl</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>spring.jpa.generate-ddl</code> (pour un provider HIBERNATE) 
	 * dans un fichier de configuration SPRING.</li>
	 * </ul>
	 *
	 * @return : String : this.generateDdl.<br/>
	 */
	private String lireGenerateDdl() {
		
		if (this.environmentSpring != null) {
			
			this.generateDdl 
				= this.environmentSpring.getProperty(
						"spring.jpa.generate-ddl");
			
			/* ajout de la valeur dans le Property. */
			if (this.generateDdl != null) {
				
				this.persistenceUnitInfoJPASansXML
					.addProperty("spring.jpa.generate-ddl"
						, this.generateDdl);
				
			}
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
						"spring.jpa.properties.hibernate.ddl-auto");
			
			/* ajout de la valeur dans le Property. */
			if (this.ddlAuto != null) {
				
				this.persistenceUnitInfoJPASansXML
					.addProperty("hibernate.hbm2ddl.auto"
						, this.ddlAuto);
				
			}
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
				+ TIRET_ESPACE
				+ "Méthode lireDdlAuto()"
				+ TIRET_ESPACE
				+ ENVT_SPRING_NON_INJECTE;
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message);
			}
		}
		
		return this.ddlAuto;
		
	} // Fin de lireDdlAuto()._____________________________________________
	
	
	
	/**
	 * <b>lit la valeur SPRING springH2ConsoleEnabled 
	 * dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.springH2ConsoleEnabled</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>spring.h2.console.enabled</code> 
	 * dans un fichier de configuration SPRING.</li>
	 * </ul>
	 *
	 * @return : String : this.springH2ConsoleEnabled.<br/>
	 */
	private String lireSpringH2ConsoleEnabled() {
		
		if (this.environmentSpring != null) {
			
			this.springH2ConsoleEnabled 
				= this.environmentSpring.getProperty(
						"spring.h2.console.enabled");
			
			/* ajout de la valeur dans le Property. */
			if (this.springH2ConsoleEnabled != null) {
				
				this.persistenceUnitInfoJPASansXML
					.addProperty("spring.h2.console.enabled"
						, this.springH2ConsoleEnabled);
				
			}
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>lit la valeur SPRING springH2ConsolePath 
	 * dans le properties</b>.<br/>
	 * injecte la valeur lue dans <code>this.springH2ConsolePath</code>.<br/>
	 * <ul>
	 * <li>ajoute la valeur lue dans la propriété correspondante 
	 * du Property du CONTENEUR 
	 * <code>persistenceUnitInfoJPASansXML</code>.</li>
	 * <li>correspond à l'élément property nommé
	 * <code>spring.h2.console.path</code> 
	 * dans un fichier de configuration SPRING.</li>
	 * </ul>
	 *
	 * @return : String : this.springH2ConsolePath.<br/>
	 */
	private String lireSpringH2ConsolePath() {
		
		if (this.environmentSpring != null) {
			
			this.springH2ConsolePath 
				= this.environmentSpring.getProperty(
						"spring.h2.console.path");
			
			/* ajout de la valeur dans le Property. */
			if (this.springH2ConsolePath != null) {
				
				this.persistenceUnitInfoJPASansXML
					.addProperty("spring.h2.console.path"
						, this.springH2ConsolePath);
				
			}
						
		} else {
			
			final String message 
				= CLASSE_LECTEUR_PROPERTIES_SPRING 
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
	 * <b>fournit une String pour l'affichage 
	 * du contenu du CONTENEUR <code>this.persistenceUnitInfoJPASansXML</code> 
	 * encapsulé dans le present LecteurPropertiesSpring</b>.<br/>
	 *
	 * @return : String : affichage.<br/>
	 */
	@Override
	public final String toString() {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append("VALEURS provenant du fichier properties SPRING dans LE CONTENEUR persistenceUnitInfoJPASansXML du LecteurPropertiesSpring : ");
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		stb.append(String.format(FORMAT_TOSTRING
				, "NOM DE L'UNITE DE PERSISTENCE (hibernate.ejb.persistenceUnitName)", this.getPersistenceUnitName()));
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		stb.append(String.format(FORMAT_TOSTRING
				, "TYPE DE TRANSACTION (hibernate.transaction.coordinator_class)", this.getTransactionType()));
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		stb.append(String.format(FORMAT_TOSTRING
				, "PROVIDER DE PERSISTENCE", this.getPersistenceProviderClassName()));
		stb.append(SAUT_LIGNE_PLATEFORME);
		
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

		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append(String.format(FORMAT_TOSTRING
				, "*** JTADATASOURCE (hibernate.connection.datasource)", this.getJtaDataSource()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append("CONTENU DE LA JTADATASOURCE : ");
		stb.append(this.afficherDataSource(this.jtaDataSource));
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		stb.append(String.format(FORMAT_TOSTRING
				, "*** NON-JTADATASOURCE (hibernate.connection.datasource)", this.getNonJtaDataSource()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append("CONTENU DE LA NON-JTADATASOURCE : ");
		stb.append(this.afficherDataSource(this.nonJtaDataSource));
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		stb.append(String.format(FORMAT_TOSTRING
				, "mappingFileNames", this.getMappingFileNames()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "jarFileUrls", this.getJarFileUrls()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "persistenceUnitRootUrl", this.getPersistenceUnitRootUrl()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "managedClassNames", this.getManagedClassNames()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "managedPackages", this.getManagedPackages()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "excludeUnlistedClasses", this.isExcludeUnlistedClasses()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "sharedCacheMode (javax.persistence.sharedCache.mode)", this.getSharedCacheMode()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "validationMode (javax.persistence.validation.mode)", this.getValidationMode()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "persistenceXMLSchemaVersion", this.getPersistenceXMLSchemaVersion()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "persistenceProviderPackageName", this.getPersistenceProviderPackageName()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		/* Properties. */
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "DIALECTE (hibernate.dialect)", this.getDialect()));
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

		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append(String.format(FORMAT_TOSTRING
				, "generateDdl (spring.jpa.generate-ddl)", this.getGenerateDdl()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "DDL-AUTO (hibernate.hbm2ddl.auto)", this.getDdlAuto()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "springH2ConsoleEnabled (spring.h2.console.enabled)", this.getSpringH2ConsoleEnabled()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(FORMAT_TOSTRING
				, "springH2ConsolePath (spring.h2.console.path)", this.getSpringH2ConsolePath()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append("LISTE DES PROPRIETES DANS LE Properties du CONTENEUR : ");
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append(this.afficherPropertiesConteneur());
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		return stb.toString();
		
	} // Fin de toString().________________________________________________
	

	
	/**
	 * <b>fournit une String pour l'affichage 
	 * du contenu du SimpleDriverDataSource 
	 * <code>this.dataSource</code></b>.<br/>
	 * <br/>
	 * - retourne null si <code>this.dataSource</code> == null.
	 * <br/>
	 *
	 * @return : String : affichage.<br/>
	 */
	public final String afficherDataSource() {
		return this.afficherDataSource(this.dataSource);
	} // Fin de afficherDataSource().______________________________________
	
	
	
	/**
	 * <b>fournit une String pour l'affichage 
	 * du contenu d'un SimpleDriverDataSource pDataSource</b>.<br/>
	 * <br/>
	 * - retourne null si pDataSource == null.
	 * <br/>
	 *
	 * @param pDataSource : 
	 * org.springframework.jdbc.datasource.SimpleDriverDataSource.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	public final String afficherDataSource(
			final SimpleDriverDataSource pDataSource) {
		
		/* retourne null si pDataSource == null. */
		if (pDataSource == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		String driverLocalString = null;
		final Driver driverLocal = pDataSource.getDriver();
		
		if (driverLocal != null) {
			driverLocalString = driverLocal.toString();
		}
		
		final String urlLocal = pDataSource.getUrl();
		final String userNameLocal = pDataSource.getUsername();
		final String passwordLocal = pDataSource.getPassword();
		
		stb.append("- DRIVER : ");
		stb.append(driverLocalString);
		
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		stb.append("- URL : ");
		stb.append(urlLocal);
		
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		stb.append("- USERNAME (LOGIN) : ");
		stb.append(userNameLocal);
		
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		stb.append("- PASSWORD : ");
		stb.append(passwordLocal);
				
		return stb.toString();
		
	} // Fin de afficherDataSource(...).___________________________________
	

	
	/**
	 * Fabrique une String à partir du java.util.Properties 
	 * <code>this.propertiesConteneur</code> contenu 
	 * dans le CONTENEUR 
	 * <code>this.persistenceUnitInfoJPASansXML</code>.<br/>
	 * <br/>
	 * - retourne null si this.propertiesConteneur est null.<br/>
	 * <br/>
	 *
	 * @return : String : Pour affichage à la console.<br/>
	 */
	public final String afficherPropertiesConteneur() {
		return this.afficherJavaUtilProperties(this.propertiesConteneur);
	} // Fin de afficherPropertiesConteneur()._____________________________
	
	
	
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
	 * Fabrique une String à partir d'une List&lt;String&gt;.<br/>
	 * <br/>
	 * - Retourne null si pListe est null.<br/>
	 * <br/>
	 *
	 * @param pListe : List&lt;String&gt; : liste de lignes.
	 * 
	 * @return : String : Pour affichage à la console.<br/>
	 */
	public final String afficherListeString(
			final List<String> pListe) {
		
		/* Retourne null si pListe est null. */
		if (pListe == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final String ligne : pListe) {
			
			stb.append(ligne);
			stb.append(SAUT_LIGNE_PLATEFORME);
			
		}
		
		return stb.toString();
			
	} // Fin de afficherListeString(...).__________________________________
	
	
	
	/**
	 * Getter du <b>lecteur SPRING du fichier properties</b> proposé 
	 * dans l'annotation sur la présente classe 'PropertySource'.
	 * <ul>
	 * <li>injecté par SPRING via le Setter 
	 * <code>setEnvironmentSpring(Environment pEnvironmentSpring)</code>
	 * .</li>
	 * </ul>
	 *
	 * @return this.environmentSpring : 
	 * org.springframework.core.env.Environment.<br/>
	 */
	public final Environment getEnvironmentSpring() {
		return this.environmentSpring;
	} // Fin de getEnvironmentSpring().____________________________________


	
	/**
	* Setter du <b>lecteur SPRING du fichier properties</b> proposé 
	* dans l'annotation sur la présente classe 'PropertySource'.
	* <ul>
	* <li>injecté par SPRING via le Setter 
	* <code>setEnvironmentSpring(Environment pEnvironmentSpring)</code>
	* .</li>
	* </ul>
	*
	* @param pEnvironmentSpring : 
	* org.springframework.core.env.Environment. : 
	* valeur à passer à this.environmentSpring.<br/>
	*/
	@Autowired(required=true)
	public final void setEnvironmentSpring(
			final Environment pEnvironmentSpring) {
		this.environmentSpring = pEnvironmentSpring;
	} // Fin de setEnvironmentSpring(...)._________________________________



	/**
	 * Getter du conteneur de type 
	 * <code>org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo</code> 
	 * <b><code>this.persistenceUnitInfoJPASansXML</code></b>
	 * pour les valeurs lues dans le properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.
	 *
	 * @return this.persistenceUnitInfoJPASansXML : 
	 * MutablePersistenceUnitInfoJPASpringSansXML.<br/>
	 */
	public final MutablePersistenceUnitInfoJPASpringSansXML 
								getPersistenceUnitInfoJPASansXML() {
		return this.persistenceUnitInfoJPASansXML;
	} // Fin de getPersistenceUnitInfoJPASansXML().________________________


	
	/**
	* Setter du conteneur de type 
	* <code>org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo</code> 
	* <b><code>this.persistenceUnitInfoJPASansXML</code></b>
	* pour les valeurs lues dans le properties 
	* de configuration indiqué dans l'annotation 
	* PropertySource au dessus de la présente classe.
	*
	* @param pPersistenceUnitInfoJPASansXML : 
	* MutablePersistenceUnitInfoJPASpringSansXML : 
	* valeur à passer à this.persistenceUnitInfoJPASansXML.<br/>
	*/
	public final void setPersistenceUnitInfoJPASansXML(
			final MutablePersistenceUnitInfoJPASpringSansXML 
								pPersistenceUnitInfoJPASansXML) {
		this.persistenceUnitInfoJPASansXML = pPersistenceUnitInfoJPASansXML;
	} // Fin de setPersistenceUnitInfoJPASansXML(...)._____________________
	
	
	
	/**
	 * <b>retourne le nom de l'unité de persistence (persistenceUnitName) 
	 * stocké dans le conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe<br/>
	 * <ul>
	 * <li>clé : <code>javax.persistence.jdbc.persistence-unit.name</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : <code>persistence-unit.name</code> dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.ejb.persistenceUnitName</code> 
	 * dans un EntityManagerFactory créé par le PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return : String : nom de l'unité de persistence.<br/>
	 */
	public final String getPersistenceUnitName() {
		return this.persistenceUnitInfoJPASansXML.getPersistenceUnitName();
	} // Fin de getPersistenceUnitName().__________________________________


	
	/**
	 * <b>retourne le type de transaction (transactionType) 
	 * stocké dans le conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.persistence-unit.transaction-type</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : <code>persistence-unit.transaction-type</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.transaction.coordinator</code> 
	 * dans un EntityManagerFactory créé par le PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return : PersistenceUnitTransactionType : 
	 * type de transaction.<br/>
	 */
	public final PersistenceUnitTransactionType getTransactionType() {
		return this.persistenceUnitInfoJPASansXML.getTransactionType();
	} // Fin de getTransactionType().______________________________________
	

	
	/**
	 * retourne le nom qualifié de l'ORM (HIBERNATE) pour SPRING : 
	 * <i>org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter</i>.<br/>
	 * <ul>
	 * <li>non lu dans le properties SPRING.</li>
	 * <li>clé : <code>provider</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>????</code> 
	 * dans un EntityManagerFactory créé par 
	 * le PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return : String : 
	 * org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter.<br/>
	 */
	public final String getPersistenceProviderClassName() {
		return this.persistenceUnitInfoJPASansXML
				.getPersistenceProviderClassName();
	} // Fin de getPersistenceProviderClassName()._________________________

	
	
	/**
	 * <b>retourne l'URL de la Base 
	 * stocké dans l'objet <b>DataSource</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>javax.persistence.jdbc.connexion.url</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée <code>javax.persistence.jdbc.url</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>javax.persistence.jdbc.url</code> 
	 * dans un EntityManagerFactory créé par le PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return : String : url dans DataSource.<br/>
	 */
	public final String getUrl() {
		
		SimpleDriverDataSource jtaDataSourceLocal = null;
		SimpleDriverDataSource nonJtaDataSourceLocal = null;
		
		if (this.getTransactionType() == null) {
			
			String resultat = null;
			
			nonJtaDataSourceLocal 
				= (SimpleDriverDataSource) 
				this.persistenceUnitInfoJPASansXML.getNonJtaDataSource();
			
			if (nonJtaDataSourceLocal != null) {
				resultat = nonJtaDataSourceLocal.getUrl();
			}
						
			return resultat;
			
		} else if (this.getTransactionType()
				.equals(PersistenceUnitTransactionType.JTA)) {
			
			String resultat = null;
			
			jtaDataSourceLocal 
				= (SimpleDriverDataSource) 
				this.persistenceUnitInfoJPASansXML.getJtaDataSource();
			
			if (jtaDataSourceLocal != null) {
				resultat = jtaDataSourceLocal.getUrl();
			}
			
			return resultat;
			
		} else if (this.getTransactionType()
				.equals(PersistenceUnitTransactionType.RESOURCE_LOCAL)) {
			
			String resultat = null;
			
			nonJtaDataSourceLocal 
				= (SimpleDriverDataSource) 
					this.persistenceUnitInfoJPASansXML.getNonJtaDataSource();
			
			if (nonJtaDataSourceLocal != null) {
				resultat = nonJtaDataSourceLocal.getUrl();
			}
			
			return resultat;

		}
		
		return null;
		
	} // Fin de getUrl().__________________________________________________

	
	
	/**
	 * <b>retourne le DRIVER de la Base 
	 * stocké dans l'objet DataSource du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe<br/>
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
	 * @return : this.driver : java.sql.Driver dans DataSource.<br/>
	 */
	public final Driver getDriver() {
		
		SimpleDriverDataSource jtaDataSourceLocal = null;
		SimpleDriverDataSource nonJtaDataSourceLocal = null;
		
		if (this.getTransactionType() == null) {
			
			Driver resultat = null;
			
			nonJtaDataSourceLocal 
				= (SimpleDriverDataSource) 
				this.persistenceUnitInfoJPASansXML.getNonJtaDataSource();
			
			if (nonJtaDataSourceLocal != null) {
				resultat = nonJtaDataSourceLocal.getDriver();
			}
						
			return resultat;
			
		} else if (this.getTransactionType()
				.equals(PersistenceUnitTransactionType.JTA)) {
			
			Driver resultat = null;
			
			jtaDataSourceLocal 
				= (SimpleDriverDataSource) 
				this.persistenceUnitInfoJPASansXML.getJtaDataSource();
			
			if (jtaDataSourceLocal != null) {
				resultat = jtaDataSourceLocal.getDriver();
			}
						
			return resultat;			
			
		} else if (this.getTransactionType()
				.equals(PersistenceUnitTransactionType.RESOURCE_LOCAL)) {
			
			Driver resultat = null;
			
			nonJtaDataSourceLocal 
			= (SimpleDriverDataSource) 
			this.persistenceUnitInfoJPASansXML.getNonJtaDataSource();
			
			jtaDataSourceLocal 
				= (SimpleDriverDataSource) 
				this.persistenceUnitInfoJPASansXML.getJtaDataSource();
			
			if (nonJtaDataSourceLocal != null) {
				resultat = nonJtaDataSourceLocal.getDriver();
			}
						
			return resultat;			
					
		}
		
		return null;
		
	} // Fin de getDriver()._______________________________________________
	
	
	
	/**
	 * <b>retourne le LOGIN de la Base 
	 * stocké dans l'objet DataSource du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe<br/>
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
	 * @return : String : userName  dans DataSource.<br/>
	 */
	public final String getUserName() {
		
		SimpleDriverDataSource jtaDataSourceLocal = null;
		SimpleDriverDataSource nonJtaDataSourceLocal = null;
		
		if (this.getTransactionType() == null) {
			
			String resultat = null;
			
			nonJtaDataSourceLocal 
				= (SimpleDriverDataSource) 
				this.persistenceUnitInfoJPASansXML.getNonJtaDataSource();
			
			if (nonJtaDataSourceLocal != null) {
				resultat = nonJtaDataSourceLocal.getUsername();
			}
			
			return resultat;
			
		} else if (this.getTransactionType()
				.equals(PersistenceUnitTransactionType.JTA)) {
			
			String resultat = null;
						
			jtaDataSourceLocal 
				= (SimpleDriverDataSource) 
				this.persistenceUnitInfoJPASansXML.getJtaDataSource();
			
			if (jtaDataSourceLocal != null) {
				resultat = jtaDataSourceLocal.getUsername();
			}
			
			return resultat;
			
		} else if (this.getTransactionType()
				.equals(PersistenceUnitTransactionType.RESOURCE_LOCAL)) {
			
			String resultat = null;
			
			nonJtaDataSourceLocal 
				= (SimpleDriverDataSource) 
					this.persistenceUnitInfoJPASansXML.getNonJtaDataSource();
			
			if (nonJtaDataSourceLocal != null) {
				resultat = nonJtaDataSourceLocal.getUsername();
			}
			
			return resultat;
			
		}
		
		return null;
		
	} // Fin de getUserName()._____________________________________________
	
	
	
	/**
	 * <b>retourne le PASSWORD de la Base 
	 * stocké dans l'objet DataSource du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe<br/>
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
	 * @return : String : password dans DataSource.<br/>
	 */
	public final String getPassword() {
		
		SimpleDriverDataSource jtaDataSourceLocal = null;
		SimpleDriverDataSource nonJtaDataSourceLocal = null;
		
		if (this.getTransactionType() == null) {
			
			String resultat = null;
			
			nonJtaDataSourceLocal 
				= (SimpleDriverDataSource) 
					this.persistenceUnitInfoJPASansXML.getNonJtaDataSource();
			
			if (nonJtaDataSourceLocal != null) {
				resultat = nonJtaDataSourceLocal.getPassword();
			}
			
			return resultat;
			
		} else if (this.getTransactionType()
				.equals(PersistenceUnitTransactionType.JTA)) {
			
			String resultat = null;
			
			jtaDataSourceLocal 
				= (SimpleDriverDataSource) 
					this.persistenceUnitInfoJPASansXML.getJtaDataSource();
			
			if (jtaDataSourceLocal != null) {
				resultat = jtaDataSourceLocal.getPassword();
			}
			
			return resultat;
			
		} else if (this.getTransactionType()
				.equals(PersistenceUnitTransactionType.RESOURCE_LOCAL)) {
			
			String resultat = null;
			
			nonJtaDataSourceLocal 
				= (SimpleDriverDataSource) 
					this.persistenceUnitInfoJPASansXML.getNonJtaDataSource();
			
			if (nonJtaDataSourceLocal != null) {
				resultat = nonJtaDataSourceLocal.getPassword();
			}
			
			return resultat;
			
		}
		
		return null;
		
	} // Fin de getPassword()._____________________________________________
	

		
	/**
	 * Getter du SimpleDriverDataSource utilisé dans la présente classe.
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>déduite de transactionType 
	 * (jtaDataSource existe si transactionType = JTA).</li>
	 * </ul>
	 *
	 * @return this.dataSource : SimpleDriverDataSource.<br/>
	 */
	public final SimpleDriverDataSource getDataSource() {
		return this.dataSource;
	} // Fin de getDataSource().___________________________________________



	/**
	 * <b>retourne l'objet DataSource jtaDataSource du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe<br/>
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>déduite de transactionType 
	 * (jtaDataSource existe si transactionType = JTA).</li>
	 * <li>clé : element <code>jta-data-source</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.connection.datasource</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return : SimpleDriverDataSource : jtaDataSource.<br/>
	 */
	public final SimpleDriverDataSource getJtaDataSource() {
		
		return (SimpleDriverDataSource) 
				this.persistenceUnitInfoJPASansXML.getJtaDataSource();	

	} // Fin de getJtaDataSource().________________________________________
	

	
	/**
	 * <b>retourne l'objet DataSource nonJtaDataSource du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe<br/>
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>déduite de transactionType 
	 * (nonJtaDataSource existe si transactionType 
	 * = RESOURCE_LOCAL ou NULL).</li>
	 * <li>clé : element <code>non-jta-data-source</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>hibernate.connection.datasource</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return : SimpleDriverDataSource : nonJtaDataSource.<br/>
	 */
	public final SimpleDriverDataSource getNonJtaDataSource() {
		
		return (SimpleDriverDataSource) 
			this.persistenceUnitInfoJPASansXML.getNonJtaDataSource();	

	} // Fin de getNonJtaDataSource()._____________________________________


	
	/**
	 * Getter de la liste des <b>noms qualifiés des 
	 * fichiers de Mapping (orm.xml)</b> des Entities JPA 
	 * mappées pour management 
	 * par JPA dans un persistence.xml.<br/>
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>optionnel si on utilise les annotations 
	 * sur les classes Entities.</li>
	 * <li>Correspond au <code>mapping-file</code> element 
	 * dans un persistence.xml.</li>
	 * <li>un orm.xml prévaut toujours sur les annotations 
	 * lorsque l'on utilise des Entities JPA 
	 * <b>annotées</b>.</li>
	 * </ul>
	 *
	 * @return mappingFileNames : List&lt;String&gt;.<br/>
	 */
	public final List<String> getMappingFileNames() {
		return this.persistenceUnitInfoJPASansXML.getMappingFileNames();
	} // Fin de getMappingFileNames()._____________________________________


	
	/**
	* Setter de la liste des <b>noms qualifiés des 
	* fichiers de Mapping (orm.xml)</b> des Entities JPA 
	* mappées pour management 
	* par JPA dans un persistence.xml.<br/>
	* <ul>
	* <li>valeur non lue dans le properties de configuration SPRING</li>
	* <li>injecte la valeur dans le CONTENEUR 
	* <code>this.persistenceUnitInfoJPASansXML</code>.</li>
	* <li>optionnel si on utilise les annotations 
	* sur les classes Entities.</li>
	* <li>Correspond au <code>mapping-file</code> element 
	* dans un persistence.xml.</li>
	* <li>un orm.xml prévaut toujours sur les annotations 
	* lorsque l'on utilise des Entities JPA 
	* <b>annotées</b>.</li>
	* </ul>
	*
	* @param pMappingFileNames : List<String> : 
	* valeur à passer à this.mappingFileNames.<br/>
	*/
	public final void setMappingFileNames(
			final List<String> pMappingFileNames) {
		
		this.mappingFileNames = pMappingFileNames;
		
		this.persistenceUnitInfoJPASansXML
			.setMappingFileNames(this.mappingFileNames);
		
	} // Fin de setMappingFileNames(...).__________________________________


	
	/**
	 * Getter de la liste des URL des jar que l'ORM doit inspecter.<br/>
	 * chaque URL Correspond au <code>jar-file</code> element 
	 * dans un persistence.xml.<br/>
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * </ul>
	 *
	 * @return jarFileUrls : List&lt;URL&gt;.<br/>
	 */
	public final List<URL> getJarFileUrls() {
		return this.persistenceUnitInfoJPASansXML.getJarFileUrls();
	} // Fin de getJarFileUrls().__________________________________________


	
	/**
	* Setter de la liste des URL des jar que l'ORM doit inspecter.<br/>
	* chaque URL Correspond au <code>jar-file</code> element 
	* dans un persistence.xml.<br/>
	* <ul>
	* <li>injecte la valeur dans le CONTENEUR 
	* <code>this.persistenceUnitInfoJPASansXML</code>.</li>
	* <li>valeur non lue dans le properties de configuration SPRING</li>
	* </ul>
	*
	* @param pJarFileUrls : List&lt;URL&gt; : 
	* valeur à passer à this.jarFileUrls.<br/>
	*/
	public final void setJarFileUrls(
			final List<URL> pJarFileUrls) {
		
		this.jarFileUrls = pJarFileUrls;
		
		this.persistenceUnitInfoJPASansXML
			.setJarFileUrls(this.jarFileUrls);
		
	} // Fin de setJarFileUrls(...)._______________________________________


	
	/**
	 * Getter de l'URL référençant un jar ou un répertoire <b>racine</b> 
	 * de l'unité de persistence.
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * </ul>
	 *
	 * @return persistenceUnitRootUrl : URL.<br/>
	 */
	public final URL getPersistenceUnitRootUrl() {
		return this.persistenceUnitInfoJPASansXML
					.getPersistenceUnitRootUrl();
	} // Fin de getPersistenceUnitRootUrl()._______________________________


	
	/**
	* Setter de l'URL référençant un jar ou un répertoire <b>racine</b> 
	* de l'unité de persistence.
	* <ul>
	* <li>injecte la valeur dans le CONTENEUR 
	* <code>this.persistenceUnitInfoJPASansXML</code>.</li>
	* <li>valeur non lue dans le properties de configuration SPRING</li>
	* </ul>
	*
	* @param pPersistenceUnitRootUrl : URL : 
	* valeur à passer à this.persistenceUnitRootUrl.<br/>
	*/
	public final void setPersistenceUnitRootUrl(
			final URL pPersistenceUnitRootUrl) {
		
		this.persistenceUnitRootUrl = pPersistenceUnitRootUrl;
		
		this.persistenceUnitInfoJPASansXML
			.setPersistenceUnitRootUrl(this.persistenceUnitRootUrl);
		
	} // Fin de setPersistenceUnitRootUrl(...).____________________________


	
	/**
	 * Getter de la liste des <b>noms qualifiés</b> des 
	 * classes Entities JPA mappées pour management 
	 * par JPA dans un persistence.xml.<br/>
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>Correspond au <code>class</code> element 
	 * dans un persistence.xml.</li>
	 * <li>par exemple : <br/>
	 * <i>"levy.daniel.application.model.persistence.metier
	 * .contactsimple.entities.jpa.ContactSimpleEntityJPA"</i></li>
	 * <li>Sans Objet lorsque l'on utilise des Entities JPA 
	 * <b>annotées</b> découvertes par JPA.</li>
	 * </ul>
	 *
	 * @return managedClassNames : List&lt;String&gt;.<br/>
	 */
	public final List<String> getManagedClassNames() {
		return this.persistenceUnitInfoJPASansXML.getManagedClassNames();
	} // Fin de getManagedClassNames().____________________________________


	
	/**
	* Setter de la liste des <b>noms qualifiés</b> des 
	* classes Entities JPA mappées pour management 
	* par JPA dans un persistence.xml.<br/>
	* <ul>
	* <li>injecte la valeur dans le CONTENEUR 
	* <code>this.persistenceUnitInfoJPASansXML</code>.</li>
	* <li>valeur non lue dans le properties de configuration SPRING</li>
	* <li>Correspond au <code>class</code> element 
	* dans un persistence.xml.</li>
	* <li>par exemple : <br/>
	* <i>"levy.daniel.application.model.persistence.metier
	* .contactsimple.entities.jpa.ContactSimpleEntityJPA"</i></li>
	* <li>Sans Objet lorsque l'on utilise des Entities JPA 
	* <b>annotées</b> découvertes par JPA.</li>
	* </ul>
	*
	* @param pManagedClassNames : List&lt;String&gt; : 
	* valeur à passer à this.managedClassNames.<br/>
	*/
	public final void setManagedClassNames(
			final List<String> pManagedClassNames) {
		
		this.managedClassNames = pManagedClassNames;
		
		this.persistenceUnitInfoJPASansXML
			.setManagedClassNames(this.managedClassNames);
		
	} // Fin de setManagedClassNames(...)._________________________________


	
	/**
	 * Getter de la liste des <b>noms qualifiés</b> des 
	 * packages d'Entities JPA mappées pour management 
	 * par JPA dans un persistence.xml.<br/>
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>INUTILISE.</li>
	 * </ul>
	 *
	 * @return managedPackages : List&lt;String&gt;.<br/>
	 */
	public final List<String> getManagedPackages() {
		return this.persistenceUnitInfoJPASansXML.getManagedPackages();
	} // Fin de getManagedPackages().______________________________________


	
	/**
	* Setter de la liste des <b>noms qualifiés</b> des 
	* packages d'Entities JPA mappées pour management 
	* par JPA dans un persistence.xml.<br/>
	* <ul>
	* <li>injecte la valeur dans le CONTENEUR 
	* <code>this.persistenceUnitInfoJPASansXML</code>.</li>
	* <li>valeur non lue dans le properties de configuration SPRING</li>
	* <li>INUTILISE.</li>
	* </ul>
	*
	* @param pManagedPackages : List&lt;String&gt; : 
	* valeur à passer à this.managedPackages.<br/>
	*/
	public final void setManagedPackages(
			final List<String> pManagedPackages) {
		
		this.managedPackages = pManagedPackages;
		
		this.persistenceUnitInfoJPASansXML
			.setManagedPackages(this.managedPackages);
		
	} // Fin de setManagedPackages(...).___________________________________


	
	/**
	 * Getter du boolean qui stipule que l'ORM ne doit manager 
	 * que les classes Entities JPA listées dans le persistence.xml 
	 * si il est à true.<br/>
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>correspond au <code>exclude-unlisted-classes</code> 
	 * element dans un persistence.xml</li>
	 * <li>toujours false si on utilise les classes annotées. </li>
	 * </ul>
	 *
	 * @return excludeUnlistedClasses : boolean.<br/>
	 */
	public final boolean isExcludeUnlistedClasses() {
		return this.persistenceUnitInfoJPASansXML
							.isExcludeUnlistedClasses();
	} // Fin de isExcludeUnlistedClasses().________________________________


	
	/**
	* Setter du boolean qui stipule que l'ORM ne doit manager 
	* que les classes Entities JPA listées dans le persistence.xml 
	* si il est à true.<br/>
	* <ul>
	* <li>injecte la valeur dans le CONTENEUR 
	* <code>this.persistenceUnitInfoJPASansXML</code>.</li>
	* <li>valeur non lue dans le properties de configuration SPRING</li>
	* <li>correspond au <code>exclude-unlisted-classes</code> 
	* element dans un persistence.xml</li>
	* <li>toujours false si on utilise les classes annotées. </li>
	* </ul>
	*
	* @param pExcludeUnlistedClasses : boolean : 
	* valeur à passer à this.excludeUnlistedClasses.<br/>
	*/
	public final void setExcludeUnlistedClasses(
			final boolean pExcludeUnlistedClasses) {
		
		this.excludeUnlistedClasses = pExcludeUnlistedClasses;
		
		this.persistenceUnitInfoJPASansXML
			.setExcludeUnlistedClasses(this.excludeUnlistedClasses);
		
	} // Fin de setExcludeUnlistedClasses(...).____________________________


	
	/**
	 * Getter du mode d'utilisation du cache de 2nd niveau par l'ORM.
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>correspond au <code>shared-cache-mode</code> 
	 * element dans un persistence.xml</li>
	 * <li><code>javax.persistence.sharedCache.mode</code> 
	* dans l'EntityManagerFactory.</li>
	 * </ul>
	 *
	 * @return sharedCacheMode : SharedCacheMode.<br/>
	 */
	public final SharedCacheMode getSharedCacheMode() {
		return this.persistenceUnitInfoJPASansXML.getSharedCacheMode();
	} // Fin de getSharedCacheMode().______________________________________


	
	/**
	* Setter du mode d'utilisation du cache de 2nd niveau par l'ORM.
	* <ul>
	* <li>injecte la valeur dans le CONTENEUR 
	* <code>this.persistenceUnitInfoJPASansXML</code>.</li>
	* <li>valeur non lue dans le properties de configuration SPRING</li>
	* <li>correspond au <code>shared-cache-mode</code> 
	* element dans un persistence.xml</li>
	* <li><code>javax.persistence.sharedCache.mode</code> 
	* dans l'EntityManagerFactory.</li>
	* </ul>
	*
	* @param pSharedCacheMode : SharedCacheMode : 
	* valeur à passer à this.sharedCacheMode.<br/>
	*/
	public final void setSharedCacheMode(
			final SharedCacheMode pSharedCacheMode) {
		
		this.sharedCacheMode = pSharedCacheMode;
		
		this.persistenceUnitInfoJPASansXML
			.setSharedCacheMode(this.sharedCacheMode);
		
	} // Fin de setSharedCacheMode(...).___________________________________


	
	/**
	 * Getter du mode de validation utilisé par l'ORM.
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>correspond au <code>validation-mode</code> element 
	 * dans un persistence.xml</li>
	 * </ul>
	 *
	 * @return validationMode : ValidationMode.<br/>
	 */
	public final ValidationMode getValidationMode() {
		return this.persistenceUnitInfoJPASansXML.getValidationMode();
	} // Fin de getValidationMode()._______________________________________


	
	/**
	* Setter du mode de validation utilisé par l'ORM.
	* <ul>
	* <li>injecte la valeur dans le CONTENEUR 
	* <code>this.persistenceUnitInfoJPASansXML</code>.</li>
	* <li>valeur non lue dans le properties de configuration SPRING</li>
	* <li>correspond au <code>validation-mode</code> element 
	* dans un persistence.xml</li>
	* </ul>
	*
	* @param pValidationMode : ValidationMode : 
	* valeur à passer à this.validationMode.<br/>
	*/
	public final void setValidationMode(
			final ValidationMode pValidationMode) {
		
		this.validationMode = pValidationMode;
		
		this.persistenceUnitInfoJPASansXML
			.setValidationMode(this.validationMode);
		
	} // Fin de setValidationMode(...).____________________________________


	
	/**
	 * Getter de la version de JPA.
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * <li>correspond à l'attribut <code>version</code> de l'element
	 * racine <code>persistence</code> 
	 * dans un persistence.xml</li>
	 * </ul>
	 *
	 * @return persistenceXMLSchemaVersion : String.<br/>
	 */
	public final String getPersistenceXMLSchemaVersion() {
		return this.persistenceUnitInfoJPASansXML
				.getPersistenceXMLSchemaVersion();
	} // Fin de getPersistenceXMLSchemaVersion().__________________________


	
	/**
	* Setter de la version de JPA.
	* <ul>
	* <li>injecte la valeur dans le CONTENEUR 
	* <code>this.persistenceUnitInfoJPASansXML</code>.</li>
	* <li>valeur non lue dans le properties de configuration SPRING</li>
	* <li>correspond à l'attribut <code>version</code> de l'element
	 * racine <code>persistence</code> 
	 * dans un persistence.xml</li>
	* </ul>
	*
	* @param pPersistenceXMLSchemaVersion : String : 
	* valeur à passer à this.persistenceXMLSchemaVersion.<br/>
	*/
	public final void setPersistenceXMLSchemaVersion(
			final String pPersistenceXMLSchemaVersion) {
		
		this.persistenceXMLSchemaVersion = pPersistenceXMLSchemaVersion;
		
		this.persistenceUnitInfoJPASansXML
			.setPersistenceXMLSchemaVersion(
					this.persistenceXMLSchemaVersion);
		
	} // Fin de setPersistenceXMLSchemaVersion(...)._______________________


	
	/**
	 * Getter du nom qualifié du package du PROVIDER 
	 * de persistence (Hibernate).
	 * <ul>
	 * <li>valeur non lue dans le properties de configuration SPRING</li>
	 * </ul>
	 *
	 * @return persistenceProviderPackageName : String.<br/>
	 */
	public final String getPersistenceProviderPackageName() {
		return this.persistenceUnitInfoJPASansXML
				.getPersistenceProviderPackageName();
	} // Fin de getPersistenceProviderPackageName()._______________________


	
	/**
	* Setter du nom qualifié du package du PROVIDER 
	* de persistence (Hibernate).
	* <ul>
	* <li>injecte la valeur dans le CONTENEUR 
	* <code>this.persistenceUnitInfoJPASansXML</code>.</li>
	* <li>valeur non lue dans le properties de configuration SPRING</li>
	* </ul>
	*
	* @param pPersistenceProviderPackageName : String : 
	* valeur à passer à this.persistenceProviderPackageName.<br/>
	*/
	public final void setPersistenceProviderPackageName(
			final String pPersistenceProviderPackageName) {
		
		this.persistenceProviderPackageName 
			= pPersistenceProviderPackageName;
		
		this.persistenceUnitInfoJPASansXML
			.setPersistenceProviderPackageName(
					this.persistenceProviderPackageName);
		
	} // Fin de setPersistenceProviderPackageName(...).____________________


	
	/**
	 * <b>retourne le DIALECTE de la Base 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return String : dialect dans le Properties du CONTENEUR.<br/>
	 */
	public final String getDialect() {
		return this.persistenceUnitInfoJPASansXML
					.getProperties().getProperty("hibernate.dialect");
	} // Fin de getDialect().______________________________________________


	
	/**
	 * <b>retourne le SHOW_SQL 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return String : showSql dans le Properties du CONTENEUR.<br/>
	 */
	public final String getShowSql() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties().getProperty("hibernate.show_sql");
	} // Fin de getShowSql().______________________________________________


	
	/**
	 * <b>retourne le FORMAT_SQL 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return String : formatSql dans le Properties du CONTENEUR.<br/>
	 */
	public final String getFormatSql() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties().getProperty("hibernate.format_sql");
	} // Fin de getFormatSql().____________________________________________


	
	/**
	 * <b>retourne le USE_SQL_COMMENTS 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return String : useSqlComments dans le Properties du CONTENEUR.<br/>
	 */
	public final String getUseSqlComments() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty("hibernate.use_sql_comments");
	} // Fin de getUseSqlComments()._______________________________________


	
	/**
	 * <b>retourne le GENERATE_STATISTICS 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty("hibernate.generate_statistics");
	} // Fin de getGenerateSatistics().____________________________________


	
	/**
	 * <b>retourne le NO_CACHE_PROVIDER_CLASS 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return String : 
	 * noCacheProviderClass dans le Properties du CONTENEUR.<br/>
	 */
	public final String getNoCacheProviderClass() {
		
		if (this.noCacheProviderClass != null) {
			return this.persistenceUnitInfoJPASansXML
					.getProperties()
						.getProperty(CACHE_PROVIDER_CLASS);
		}
		
		return null;
		
	} // Fin de getNoCacheProviderClass()._________________________________


	
	/**
	 * <b>retourne le CACHE_PROVIDER_CLASS 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return String : 
	 * cacheProviderClass dans le Properties du CONTENEUR.<br/>
	 */
	public final String getCacheProviderClass() {
		
		if (this.cacheProviderClass != null) {
			return this.persistenceUnitInfoJPASansXML
					.getProperties()
						.getProperty(CACHE_PROVIDER_CLASS);
		}
		
		return null;
		
	} // Fin de getCacheProviderClass().___________________________________


	
	/**
	 * <b>retourne le CACHE-USE_SECOND_LEVEL_CACHE 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return String : 
	 * cacheUseSecondLevelCache dans le Properties du CONTENEUR.<br/>
	 */
	public final String getCacheUseSecondLevelCache() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty("cache.use_second_level_cache");
	} // Fin de getCacheUseSecondLevelCache()._____________________________


	
	/**
	 * <b>retourne le CACHE-USE_QUERY_CACHE 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return String : 
	 * cacheUseSecondLevelCache dans le Properties du CONTENEUR.<br/>
	 */
	public final String getCacheUseQueryCache() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty("cache.use_query_cache");
	} // Fin de getCacheUseSecondLevelCache()._____________________________


	
	/**
	 * <b>retourne le RESOURCE_CACHE 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>net.sf.ehcache.configurationResourcename</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : property nommée 
	 * <code>net.sf.ehcache.configurationResourcename</code> 
	 * dans un persistence.xml préconisé par JPA</li>
	 * <li>clé : <code>net.sf.ehcache.configurationResourcename</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return String : 
	 * resourceCache dans le Properties du CONTENEUR.<br/>
	 */
	public final String getResourceCache() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty(
							"net.sf.ehcache.configurationResourcename");
	} // Fin de getResourceCache().________________________________________
	

	
	/**
	 * <b>retourne le poolMinSize 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return : String : 
	 * poolMinSize dans le Properties du CONTENEUR.<br/>
	 */
	public final String getPoolMinSize() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty("hibernate.c3p0.min_size");
	} // Fin de getPoolMinSize().__________________________________________
	

	
	/**
	 * <b>retourne le poolMaxSize 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return : String : 
	 * poolMaxSize dans le Properties du CONTENEUR.<br/>
	 */
	public final String getPoolMaxSize() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty("hibernate.c3p0.max_size");
	} // Fin de getPoolMaxSize().__________________________________________
	

	
	/**
	 * <b>retourne le poolTimeOut 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return : String : 
	 * poolTimeOut dans le Properties du CONTENEUR.<br/>
	 */
	public final String getPoolTimeOut() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty("hibernate.c3p0.timeout");
	} // Fin de getPoolTimeOut().__________________________________________
	

	
	/**
	 * <b>retourne le poolMaxStatements 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return : String : 
	 * poolMaxStatements dans le Properties du CONTENEUR.<br/>
	 */
	public final String getPoolMaxStatements() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty("hibernate.c3p0.max_statements");
	} // Fin de getPoolMaxStatements().____________________________________
	

	
	/**
	 * <b>retourne le poolIdleTestPeriod 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return : String : 
	 * poolIdleTestPeriod dans le Properties du CONTENEUR.<br/>
	 */
	public final String getPoolIdleTestPeriod() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty("hibernate.c3p0.idle_test_period");
	} // Fin de getPoolIdleTestPeriod().___________________________________
	

	
	/**
	 * <b>retourne le poolAcquireIncrement 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return : String : 
	 * poolAcquireIncrement dans le Properties du CONTENEUR.<br/>
	 */
	public final String getPoolAcquireIncrement() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty("hibernate.c3p0.acquire_increment");
	} // Fin de getPoolAcquireIncrement()._________________________________
	

	
	/**
	 * <b>retourne le generateDdl 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
	 * <ul>
	 * <li>clé : 
	 * <code>spring.jpa.generate-ddl</code> 
	 * dans le fichier properties SPRING</li>
	 * <li>clé : <code>spring.jpa.generate-ddl</code> 
	 * dans un EntityManagerFactory créé par le 
	 * PersistenceProvider HIBERNATE</li>
	 * </ul>
	 *
	 * @return : String : 
	 * generateDdl dans le Properties du CONTENEUR.<br/>
	 */
	public final String getGenerateDdl() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty("spring.jpa.generate-ddl");
	} // Fin de getGenerateDdl().__________________________________________
	

	
	/**
	 * <b>retourne le ddl-auto 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
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
	 * @return : String : 
	 * ddlAuto dans le Properties du CONTENEUR.<br/>
	 */
	public final String getDdlAuto() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty("hibernate.hbm2ddl.auto");
	} // Fin de getDdlAuto().______________________________________________
	

	
	/**
	 * <b>retourne le springH2ConsoleEnabled 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
	 *  <ul>
	 * <li>clé : 
	 * <code>spring.h2.console.enabled</code> 
	 * dans le fichier properties SPRING</li>
	 * </ul>

	 *
	 * @return : String : 
	 * springH2ConsoleEnabled dans le Properties du CONTENEUR.<br/>
	 */
	public final String getSpringH2ConsoleEnabled() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty("spring.h2.console.enabled");
	} // Fin de getSpringH2ConsoleEnabled()._______________________________
	

	
	/**
	 * <b>retourne le springH2ConsolePath 
	 * stocké dans l'objet <b>properties</b> du conteneur 
	 * <code>this.persistenceUnitInfoJPASansXML</code></b> 
	 * après lecture du properties 
	 * de configuration indiqué dans l'annotation 
	 * PropertySource au dessus de la présente classe.<br/>
	 *  <ul>
	 * <li>clé : 
	 * <code>spring.h2.console.path</code> 
	 * dans le fichier properties SPRING</li>
	 * </ul>

	 *
	 * @return : String : 
	 * springH2ConsolePath dans le Properties du CONTENEUR.<br/>
	 */
	public final String getSpringH2ConsolePath() {
		return this.persistenceUnitInfoJPASansXML
				.getProperties()
					.getProperty("spring.h2.console.path");
	} // Fin de getSpringH2ConsolePath().__________________________________


	
	/**
	 * Getter du java.util.Properties contenu dans le CONTENEUR 
	 * <code>this.persistenceUnitInfoJPASansXML</code> 
	 * de la présente classe.
	 *
	 * @return this.propertiesConteneur : java.util.Properties.<br/>
	 */
	public final Properties getPropertiesConteneur() {
		return this.propertiesConteneur;
	} // Fin de getPropertiesConteneur().__________________________________
	
	
	
} // FIN DE LA CLASSE LecteurPropertiesSpring.-------------------------------
