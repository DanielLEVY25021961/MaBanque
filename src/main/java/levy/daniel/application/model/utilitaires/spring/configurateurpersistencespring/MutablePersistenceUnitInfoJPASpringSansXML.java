package levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring;

import java.net.URL;
import java.sql.Driver;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.ClassTransformer;
import javax.persistence.spi.PersistenceUnitTransactionType;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.lang.Nullable;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo;
import org.springframework.orm.jpa.persistenceunit.SmartPersistenceUnitInfo;
import org.springframework.util.ClassUtils;

/**
 * CLASSE MutablePersistenceUnitInfoJPASpringSansXML :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * afficher java.util.Properties, afficher Properties,<br/> 
 * afficherProperties, <br/>
 * afficher DataSource, afficherDataSource(), <br/>
 * tri Set de String, trier Set String, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 19 janv. 2019
 *
 */
public class MutablePersistenceUnitInfoJPASpringSansXML 
							extends MutablePersistenceUnitInfo {

	// ************************ATTRIBUTS************************************/

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
	 * saut de ligne de la plateforme.<br/>
	 * System.getProperty("line.separator")
	 */
	public static final String SAUT_LIGNE_PLATEFORME 
		= System.getProperty("line.separator");
	
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
	 * Locale.getDefault().
	 */
	public static final Locale LOCALE_PLATEFORME 
		= Locale.getDefault();
	
	/**
	 * nom de l'unité de persistance.
	 * <ul>
	 * <li>correspond à l'élément 
	 * <code>persistence-unit.name</code> dans un persistence.xml 
	 * préconisé par JPA.</li>
	 * </ul>
	 */
	@Nullable
	private String persistenceUnitName;
	
	/**
	 * nom qualifié de l'ORM (HIBERNATE).<br/>
	 * Par exemple : 
	 * org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
	 * .class.getName().
	 * <ul>
	 * <li>correspond à l'élément <code>provider</code> 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 */
	@Nullable
	private String persistenceProviderClassName;

	/**
	 * Type de transaction.
	 * <ul>
	 * <li>RESOURCE_LOCAL pour une application STANDALONE 
	 * qui gère les transactions.</li>
	 * <li>JTA pour une application WEB dans laquelle 
	 * le conteneur de Servlet (TOMCAT) gère les transactions.</li>
	 * <li>Ne pas utiliser ce tag pour une application gérée par SPRING 
	 * (qui gère lui même les transactions)</li>
	 * <li>correspond à l'élément 
	 * <code>persistence-unit.transaction-type</code> 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 */
	@Nullable
	private PersistenceUnitTransactionType transactionType;
	
	/**
	 * SimpleDriverDataSource utilisé dans la présente classe.
	 * <ul>
	 * <li>déduite de transactionType 
	 * (jtaDataSource existe si transactionType = JTA).</li>
	 * </ul>
	 */
	private transient SimpleDriverDataSource dataSource;
	
	/**
	 * DataSource fournie par le conteneur de Servlet (TOMCAT) 
	 * via JNDI en cas de transactions JTA.
	 */
	@Nullable
	private DataSource jtaDataSource;
	
	/**
	 * DataSource de l'application Standalone 
	 * en cas de transactions RESOURCE_LOCAL (pas de TOMCAT).
	 */
	@Nullable
	private DataSource nonJtaDataSource;

	/**
	 * liste des <b>noms qualifiés des 
	 * fichiers de Mapping (orm.xml)</b> des Entities JPA 
	 * mappées pour management 
	 * par JPA dans un persistence.xml.<br/>
	 * <ul>
	 * <li>optionnel si on utilise les annotations 
	 * sur les classes Entities.</li>
	 * <li>Correspond au <code>mapping-file</code> element 
	 * dans un persistence.xml.</li>
	 * <li>un orm.xml prévaut toujours sur les annotations 
	 * lorsque l'on utilise des Entities JPA 
	 * <b>annotées</b>.</li>
	 * </ul>
	 */
	private List<String> mappingFileNames;
	
	/**
	 * liste des URL des jar que l'ORM doit inspecter.<br/>
	 * chaque URL Correspond au <code>jar-file</code> element 
	 * dans un persistence.xml.
	 */
	private List<URL> jarFileUrls;
	
	/**
	 * URL référençant un jar ou un répertoire <b>racine</b> 
	 * de l'unité de persistence.
	 */
	@Nullable
	private URL persistenceUnitRootUrl;
	
	/**
	 * liste des <b>noms qualifiés</b> des 
	 * classes Entities JPA mappées pour management 
	 * par JPA dans un persistence.xml.<br/>
	 * <ul>
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
	 * .
	 */
	private List<String> managedPackages = new LinkedList<String>();
	
	/**
	 * boolean qui stipule que l'ORM ne doit manager 
	 * que les classes Entities JPA listées dans le persistence.xml 
	 * si il est à true.<br/>
	 * <ul>
	 * <li>correspond au <code>exclude-unlisted-classes</code> 
	 * element dans un persistence.xml</li>
	 * <li>toujours false si on utilise les classes annotées. </li>
	 * </ul>
	 */
	private boolean excludeUnlistedClasses;  
		
	/**
	 * mode d'utilisation du cache de 2nd niveau par l'ORM.
	 * <ul>
	 * <li>correspond au <code>shared-cache-mode</code> 
	 * element dans un persistence.xml</li>
	 * </ul>
	 */
	private SharedCacheMode sharedCacheMode;
	
	/**
	 * mode de validation utilisé par l'ORM.
	 * <ul>
	 * <li>correspond au <code>validation-mode</code> element 
	 * dans un persistence.xml</li>
	 * </ul>
	 */
	private ValidationMode validationMode;
	
	/**
	 * <b>propriétés de <b>connexion à la base</b> 
	 * éventuellement spécifiques 
	 * à l'ORM, à SPRING, à H2, ...</b> 
	 * comme par exemple le dialecte HIBERNATE.<br/>
	 * <ul>
	 * <li>correspond au <code>properties</code> element 
	 * dans un persistence.xml</li>
	 * <li>chaque Property de properties correspond au 
	 * <code>property</code> element dans un persistence.xml</li>
	 * <li><code>hibernate.connection.url</code> 
	 * (URL de connexion à la base), 
	 * <code>javax.persistence.jdbc.driver</code> 
	 * (driver de connexion à la base) sont des Property...</li>
	 * </ul>
	 */
	private Properties properties = new Properties();

	/**
	 * version de JPA utilisée dans le persistence.xml (2.1, 3.0, ...).
	 */
	private String persistenceXMLSchemaVersion;
	
	/**
	 * .
	 */
	@Nullable
	private String persistenceProviderPackageName;
	
	/**
	 * ClassLoader utilisé par l'ORM pour charger les Entities JPA, ....
	 */
	private ClassLoader classLoader;
	
	/**
	 * ClassLoader utilisé temporairement 
	 * par l'ORM pour charger les Entities JPA, ....
	 */
	private ClassLoader newTempClassLoader;
	
	/**
	 * "2.1"
	 */
	public static final String JPA_VERSION = "2.1";

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(MutablePersistenceUnitInfoJPASpringSansXML.class);

	// *************************METHODES************************************/
	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public MutablePersistenceUnitInfoJPASpringSansXML() {
		
		this(null
				, null
				, PersistenceUnitTransactionType.RESOURCE_LOCAL
				, null, null
				, null, null, null, null, null, false
				, null, null, null, null, null, null, null);
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR MALIN.
	 * 
	 * @param pPersistenceUnitName : String : 
	 * nom de l'unité de persistance.
	 * @param pPersistenceProviderClassName : String : 
	 * nom qualifié de l'ORM (HIBERNATE).
	 * @param pTransactionType : PersistenceUnitTransactionType : 
	 * Type de transaction.
	 * @param pJtaDataSource : DataSource :
	 * DataSource fournie par le conteneur de Servlet (TOMCAT) 
	 * via JNDI en cas de transactions JTA.
	 * @param pNonJtaDataSource : DataSource : 
	 * DataSource de l'application Standalone 
	 * en cas de transactions RESOURCE_LOCAL (pas de TOMCAT).
	 * @param pManagedClassNames : List&lt;String&gt; : 
	 * liste des <b>noms</b> des 
	 * classes Entities JPA mappées pour management 
	 * par JPA dans un persistence.xml. 
	 * @param pProperties : Properties : 
	 * <b>propriétés de <b>connexion à la base</b> 
	 * éventuellement spécifiques 
	 * à l'ORM, à SPRING, à H2, ...</b> 
	 * comme par exemple le dialecte HIBERNATE.
	 */
	public MutablePersistenceUnitInfoJPASpringSansXML(
			final String pPersistenceUnitName
			, final String pPersistenceProviderClassName
			, final PersistenceUnitTransactionType pTransactionType
			, final DataSource pJtaDataSource
			, final DataSource pNonJtaDataSource
			, final List<String> pManagedClassNames
			, final Properties pProperties) {
		
       this(
       		pPersistenceUnitName
       		, pPersistenceProviderClassName
       		, pTransactionType
       		, pJtaDataSource
       		, pNonJtaDataSource
       		, null
       		, null
       		, null
       		, pManagedClassNames
       		, null
       		, false
       		, null
       		, null
       		, pProperties
       		, null
       		, null
       		, null
       		, null);
       
   } // Fin de CONSTRUCTEUR MALIN.________________________________________

	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * <ul>
	 * <li>alimente this.dataSource.</li>
	 * <li>this.jarFileUrls == liste vide si pJarFileUrls == null.<br/></li>
	 * <li>this.sharedCacheMode = UNSPECIFIED 
	* si pSharedCacheMode == null.</li>
	* <li>this.validationMode == AUTO si pValidationMode == null.</li>
	* <li>this.persistenceXMLSchemaVersion == JPA_VERSION si 
	* pPersistenceXMLSchemaVersion == null.</li>
	* <li>this.classLoader = 
	* Thread.currentThread().getContextClassLoader() 
	* si pClassLoader == null.</li>
	 * </ul>
	 * 
	 * @param pPersistenceUnitName : String : 
	 * nom de l'unité de persistance.
	 * @param pPersistenceProviderClassName : String : 
	 * nom qualifié de l'ORM (HIBERNATE).
	 * @param pTransactionType : PersistenceUnitTransactionType : 
	 * Type de transaction.
	 * @param pJtaDataSource : DataSource :
	 * DataSource fournie par le conteneur de Servlet (TOMCAT) 
	 * via JNDI en cas de transactions JTA.
	 * @param pNonJtaDataSource : DataSource : 
	 * DataSource de l'application Standalone 
	 * en cas de transactions RESOURCE_LOCAL (pas de TOMCAT).
	 * @param pMappingFileNames : List&lt;String&gt; : 
	 * liste des <b>noms qualifiés</b> des 
	 * classes Entities JPA mappées pour management 
	 * par JPA dans un persistence.xml.<br/>
	 * @param pJarFileUrls : List&lt;URL&gt; : 
	 * liste des URL des jar que l'ORM doit inspecter.
	 * @param pPersistenceUnitRootUrl : URL : 
	 * URL référençant un jar ou un répertoire <b>racine</b> 
	 * de l'unité de persistence.
	 * @param pManagedClassNames : List&lt;String&gt; : 
	 * liste des <b>noms</b> des 
	 * classes Entities JPA mappées pour management 
	 * par JPA dans un persistence.xml.
	 * @param pManagedPackages :  List&lt;String&gt; :
	 * @param pExcludeUnlistedClasses : boolean : 
	 * boolean qui stipule que l'ORM ne doit manager 
	 * que les classes Entities JPA listées dans le persistence.xml 
	 * si il est à true.
	 * @param pSharedCacheMode : SharedCacheMode : 
	 * mode d'utilisation du cache de 2nd niveau par l'ORM.
	 * @param pValidationMode : ValidationMode : 
	 * mode de validation utilisé par l'ORM.
	 * @param pProperties : Properties : 
	 * <b>propriétés de <b>connexion à la base</b> 
	 * éventuellement spécifiques 
	 * à l'ORM, à SPRING, à H2, ...</b> 
	 * comme par exemple le dialecte HIBERNATE.
	 * @param pPersistenceXMLSchemaVersion : String : 
	 * version de JPA utilisée dans le persistence.xml (2.1, 3.0, ...).
	 * @param pPersistenceProviderPackageName : String : 
	 * @param pClassLoader : ClassLoader : 
	 * ClassLoader utilisé par l'ORM pour charger les Entities JPA, ....
	 * @param pNewTempClassLoader : ClassLoader : 
	 * ClassLoader utilisé temporairement 
	 * par l'ORM pour charger les Entities JPA, ....
	 */
	public MutablePersistenceUnitInfoJPASpringSansXML(
			final String pPersistenceUnitName
			, final String pPersistenceProviderClassName
			, final PersistenceUnitTransactionType pTransactionType
			, final DataSource pJtaDataSource
			, final DataSource pNonJtaDataSource
			, final List<String> pMappingFileNames
			, final List<URL> pJarFileUrls
			, final URL pPersistenceUnitRootUrl
			, final List<String> pManagedClassNames
			, final List<String> pManagedPackages
			, final boolean pExcludeUnlistedClasses
			, final SharedCacheMode pSharedCacheMode
			, final ValidationMode pValidationMode
			, final Properties pProperties
			, final String pPersistenceXMLSchemaVersion
			, final String pPersistenceProviderPackageName
			, final ClassLoader pClassLoader
			, final ClassLoader pNewTempClassLoader) {
		
		super();
		
		this.persistenceUnitName = pPersistenceUnitName;
		super.setPersistenceUnitName(this.persistenceUnitName);
		
		this.persistenceProviderClassName = pPersistenceProviderClassName;
		super.setPersistenceProviderClassName(this.persistenceProviderClassName);
		
		this.transactionType = pTransactionType;
		super.setTransactionType(this.transactionType);
				
		this.jtaDataSource = pJtaDataSource;
		super.setJtaDataSource(this.jtaDataSource);
		
		this.nonJtaDataSource = pNonJtaDataSource;
		super.setNonJtaDataSource(this.nonJtaDataSource);
		
		/* alimente this.dataSource. */
		if (this.nonJtaDataSource != null) {
			this.dataSource = (SimpleDriverDataSource) this.nonJtaDataSource;
		} else if (this.jtaDataSource != null) {
			this.dataSource = (SimpleDriverDataSource) this.jtaDataSource;
		} else {
			this.dataSource = null;
		}
		
		if (pMappingFileNames == null) {
			this.mappingFileNames = new LinkedList<>();
		} else {
			this.mappingFileNames = pMappingFileNames;
//			for (final String mappingFileName : this.mappingFileNames) {
//				super.addMappingFileName(mappingFileName); 
//			}
		}
		
				
		if (pJarFileUrls == null) {
			this.jarFileUrls = Collections.emptyList(); 
		} else {
			this.jarFileUrls = pJarFileUrls;
//			for (final URL jarFileUrl : this.jarFileUrls) {
//				super.addJarFileUrl(jarFileUrl); 
//			}
		}
				
		this.persistenceUnitRootUrl = pPersistenceUnitRootUrl;
		super.setPersistenceUnitRootUrl(this.persistenceUnitRootUrl);
		
		if (pManagedClassNames == null) {
			this.managedClassNames = new LinkedList<String>();
		} else {
			this.managedClassNames = pManagedClassNames;
//			for (final String managedClassName : this.managedClassNames) {
//				super.addManagedClassName(managedClassName); 
//			}
		}
		
		if (pManagedPackages == null) {
			this.managedPackages = new LinkedList<String>();
		} else {
			this.managedPackages = pManagedPackages;
//			for (final String managedPackage : this.managedPackages) {
//				super.addManagedPackage(managedPackage); 
//			}
		}
		
		this.excludeUnlistedClasses = pExcludeUnlistedClasses;
		super.setExcludeUnlistedClasses(this.excludeUnlistedClasses);
		
		if (pSharedCacheMode == null) {
			this.sharedCacheMode = SharedCacheMode.UNSPECIFIED;
		} else {
			this.sharedCacheMode = pSharedCacheMode;
		}
		super.setSharedCacheMode(this.sharedCacheMode);
		
		if (pValidationMode == null) {
			this.validationMode = ValidationMode.AUTO;
		} else {
			this.validationMode = pValidationMode;
		}
		super.setValidationMode(this.validationMode);

		if (pProperties == null) {
			this.properties = new Properties();
		} else {
			this.properties = pProperties;
		}		
		super.setProperties(this.properties);
		
		if (pPersistenceXMLSchemaVersion == null) {
			this.persistenceXMLSchemaVersion = JPA_VERSION;
		} else {
			this.persistenceXMLSchemaVersion 
				= pPersistenceXMLSchemaVersion;
		}
		super.setPersistenceXMLSchemaVersion(
				this.persistenceXMLSchemaVersion);

		this.persistenceProviderPackageName 
			= pPersistenceProviderPackageName;
		super.setPersistenceProviderPackageName(
				this.persistenceProviderPackageName);
		
		
		if (pClassLoader == null) {
			
			this.classLoader 
				= ClassUtils.getDefaultClassLoader();
			
		} else {
			this.classLoader = pClassLoader;
		}

		this.newTempClassLoader = pNewTempClassLoader;
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("PersistenceUnitInfoJPASansXML [");
		
		builder.append("persistenceUnitName=");
		if (this.persistenceUnitName != null) {		
			builder.append(this.persistenceUnitName);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("persistenceProviderClassName=");
		if (this.persistenceProviderClassName != null) {
			builder.append(this.persistenceProviderClassName);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("transactionType=");
		if (this.transactionType != null) {			
			builder.append(this.transactionType);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("jtaDataSource=");
		if (this.jtaDataSource != null) {			
			builder.append(this.jtaDataSource);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("nonJtaDataSource=");
		if (this.nonJtaDataSource != null) {			
			builder.append(this.nonJtaDataSource);
			builder.append(this.afficherDataSource((SimpleDriverDataSource) this.nonJtaDataSource));
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
				
		builder.append("mappingFileNames=");
		if (this.mappingFileNames != null) {			
			builder.append(this.mappingFileNames);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("jarFileUrls=");
		if (this.jarFileUrls != null) {			
			builder.append(this.jarFileUrls);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("persistenceUnitRootUrl=");
		if (this.persistenceUnitRootUrl != null) {			
			builder.append(this.persistenceUnitRootUrl);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);

		builder.append("managedClassNames=");
		if (this.managedClassNames != null) {			
			builder.append(this.managedClassNames);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);

		
		builder.append("excludeUnlistedClasses=");
		builder.append(this.excludeUnlistedClasses);
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("sharedCacheMode=");
		if (this.sharedCacheMode != null) {			
			builder.append(this.sharedCacheMode);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("validationMode=");
		if (this.validationMode != null) {			
			builder.append(this.validationMode);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("properties=");
		if (this.properties != null) {			
			builder.append(this.properties);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);

		builder.append("persistenceXMLSchemaVersion=");
		if (this.persistenceXMLSchemaVersion != null) {			
			builder.append(this.persistenceXMLSchemaVersion);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);

		builder.append("classLoader=");
		if (this.classLoader != null) {			
			builder.append(this.classLoader);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);

		builder.append("newTempClassLoader=");
		if (this.newTempClassLoader != null) {			
			builder.append(this.newTempClassLoader);
		} else {
			builder.append(NULL);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________


		
	/**
	 * <b>fournit une String pour l'affichage 
	 * du contenu du present LecteurPropertiesSpring</b>.<br/>
	 *
	 * @return : String : affichage.<br/>
	 */
	public final String toStringAmeliore() {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append("VALEURS dans LE CONTENEUR persistenceUnitInfoJPASansXML : ");
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
				, "NOM DE L'UNITE DE PERSISTENCE (hibernate.ejb.persistenceUnitName)", this.getPersistenceUnitName()));
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
				, "TYPE DE TRANSACTION (hibernate.transaction.coordinator_class)", this.getTransactionType()));
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
				, "PROVIDER DE PERSISTENCE", this.getPersistenceProviderClassName()));
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		/* DataSource. */
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append("********* DATASOURCE *************");
		stb.append(SAUT_LIGNE_PLATEFORME);
		if (this.dataSource != null) {
			
			stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
					, " *** URL", this.dataSource.getUrl()));
			stb.append(SAUT_LIGNE_PLATEFORME);
			
			stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
					, "DRIVER", this.dataSource.getDriver()));
			stb.append(SAUT_LIGNE_PLATEFORME);
			
			stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
					, "LOGIN", this.dataSource.getUsername()));
			stb.append(SAUT_LIGNE_PLATEFORME);

			stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
					, "PASSWORD", this.dataSource.getPassword()));
			stb.append(SAUT_LIGNE_PLATEFORME);
		}
		
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
				, "mappingFileNames", this.getMappingFileNames()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
				, "jarFileUrls", this.getJarFileUrls()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
				, "persistenceUnitRootUrl", this.getPersistenceUnitRootUrl()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
				, "managedClassNames", this.getManagedClassNames()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
				, "managedPackages", this.getManagedPackages()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
				, "excludeUnlistedClasses", this.isExcludeUnlistedClasses()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
				, "sharedCacheMode (javax.persistence.sharedCache.mode)", this.getSharedCacheMode()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
				, "validationMode (javax.persistence.validation.mode)", this.getValidationMode()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
				, "persistenceXMLSchemaVersion", this.getPersistenceXMLSchemaVersion()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(String.format(LOCALE_PLATEFORME, FORMAT_TOSTRING
				, "persistenceProviderPackageName", this.getPersistenceProviderPackageName()));
		stb.append(SAUT_LIGNE_PLATEFORME);

		/* Properties. */
		stb.append(SAUT_LIGNE_PLATEFORME);

		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append("LISTE DES PROPRIETES DANS LE java.util.Properties : ");
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append(this.afficherPropertiesConteneur());
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		return stb.toString();
		
	} // Fin de toString().________________________________________________

	
	
	/**
	 * affiche les propriétés d'une SimpleDriverDataSource.<br/>
	 *
	 * @param pDataSource : SimpleDriverDataSource.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	public String afficherDataSource(
			final SimpleDriverDataSource pDataSource) {
		
		final StringBuilder builder = new StringBuilder();

		final String url = pDataSource.getUrl();
		String driverString = null;
		final Driver driverLocal = pDataSource.getDriver();
		
		if (driverLocal != null) {
			driverString = driverLocal.toString();
		}
		
		final String login = pDataSource.getUsername();
		final String password = pDataSource.getPassword();

		builder.append(" - DataSource [");
		
		builder.append("URL = ");
		if (url != null) {
			builder.append(url);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("DRIVER = ");
		if (driverString != null) {
			builder.append(driverString);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("LOGIN = ");
		if (login != null) {
			builder.append(login);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("MDP = ");
		if (password != null) {
			builder.append(password);
		} else {
			builder.append(NULL);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de afficherDataSource(...).___________________________________
	

	
	/**
	 * Fabrique une String à partir du java.util.Properties 
	 * <code>this.properties</code>.<br/>
	 * <br/>
	 * - retourne null si properties est null.<br/>
	 * <br/>
	 *
	 * @return : String : Pour affichage à la console.<br/>
	 */
	public final String afficherPropertiesConteneur() {
		return this.afficherJavaUtilProperties(this.properties);
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
		
		/* tri du Set de String. */
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
	 * {@inheritDoc}
	 * <ul>
	 * <li>correspond à l'élément 
	 * <code>persistence-unit.name</code> dans un persistence.xml 
	 * préconisé par JPA.</li>
	 * </ul>
	 */
	@Nullable
	@Override
	public String getPersistenceUnitName() {
		return this.persistenceUnitName;
	} // Fin de getPersistenceUnitName().__________________________________

	

	/**
	* Setter du nom de l'unité de persistance.
	* <ul>
	* <li>correspond à l'élément 
	* <code>persistence-unit.name</code> dans un persistence.xml 
	* préconisé par JPA.</li>
	* </ul>
	*
	* @param pPersistenceUnitName : String : 
	* valeur à passer à this.persistenceUnitName.<br/>
	*/
	@Override
	public void setPersistenceUnitName(
			@Nullable final String pPersistenceUnitName) {
		super.setPersistenceUnitName(pPersistenceUnitName);
		this.persistenceUnitName = pPersistenceUnitName;
	} // Fin de setPersistenceUnitName(...)._______________________________


	/**
	 * {@inheritDoc}
	 * <br/>
	 * - nom qualifié de l'ORM (HIBERNATE).<br/>
	 * Par exemple : 
	 * org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
	 * .class.getName().
	 * <ul>
	 * <li>correspond à l'élément <code>provider</code> 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	 */
	@Nullable
	@Override
	public String getPersistenceProviderClassName() {
		return this.persistenceProviderClassName;
	} // Fin de getPersistenceProviderClassName()._________________________
	
	
		
	/**
	* setter du nom qualifié de l'ORM (HIBERNATE).<br/>
	 * Par exemple : 
	 * org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
	 * .class.getName().
	 * <ul>
	 * <li>correspond à l'élément <code>provider</code> 
	 * dans un persistence.xml préconisé par JPA.</li>
	 * </ul>
	*
	* @param pPersistenceProviderClassName : String : 
	* valeur à passer à this.persistenceProviderClassName.<br/>
	*/
	@Override
	public void setPersistenceProviderClassName(
			@Nullable final String pPersistenceProviderClassName) {
		super.setPersistenceProviderClassName(pPersistenceProviderClassName);
		this.persistenceProviderClassName = pPersistenceProviderClassName;
	} // Fin de setPersistenceProviderClassName(...).______________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public PersistenceUnitTransactionType getTransactionType() {
		return this.transactionType;		
	} // Fin de getTransactionType().______________________________________

	
		
	/**
	* Setter du Type de transaction.
	* <ul>
	* <li>RESOURCE_LOCAL pour une application STANDALONE 
	* qui gère les transactions.</li>
	* <li>JTA pour une application WEB dans laquelle 
	* le conteneur de Servlet (TOMCAT) gère les transactions.</li>
	* <li>Ne pas utiliser ce tag pour une application gérée par SPRING 
	* (qui gère lui même les transactions)</li>
	* <li>correspond à l'élément 
	* <code>persistence-unit.transaction-type</code> 
	* dans un persistence.xml préconisé par JPA.</li>
	* </ul>
	*
	* @param pTransactionType : PersistenceUnitTransactionType : 
	* valeur à passer à this.transactionType.<br/>
	*/
	@Override
	public void setTransactionType(
			final PersistenceUnitTransactionType pTransactionType) {
		super.setTransactionType(pTransactionType);
		this.transactionType = pTransactionType;
	} // Fin de setTransactionType(...).___________________________________
	

	
	/**
	 * Getter du SimpleDriverDataSource utilisé dans la présente classe.
	 * <ul>
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
	 * {@inheritDoc}
	 */
	@Nullable
	@Override
	public DataSource getJtaDataSource() {
		return this.jtaDataSource;
	} // Fin de getJtaDataSource().________________________________________

	
	
	/**
	* setter de la DataSource fournie par le conteneur de Servlet (TOMCAT) 
	 * via JNDI en cas de transactions JTA.
	 * <ul>
	 * <li>passe this.jtaDataSource à this.dataSource.</li>
	 * </ul>
	*
	* @param pJtaDataSource : DataSource : 
	* valeur à passer à this.jtaDataSource.<br/>
	*/
	@Override
	public void setJtaDataSource(
			@Nullable final DataSource pJtaDataSource) {
		
		this.jtaDataSource = pJtaDataSource;
		super.setJtaDataSource(this.jtaDataSource);
		
		if (this.jtaDataSource != null) {
			this.dataSource = (SimpleDriverDataSource) this.jtaDataSource;
		}
		
	} // Fin de setJtaDataSource(...)._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Nullable
	@Override
	public DataSource getNonJtaDataSource() {
		return this.nonJtaDataSource;
	} // Fin de getNonJtaDataSource()._____________________________________
	

	
	/**
	* Setter de la DataSource de l'application Standalone 
	* en cas de transactions RESOURCE_LOCAL (pas de TOMCAT).
	* <ul>
	* <li>passe this.nonJtaDataSource à this.dataSource.</li>
	* </ul>
	*
	* @param pNonJtaDataSource : DataSource : 
	* valeur à passer à this.nonJtaDataSource.<br/>
	*/
	@Override
	public void setNonJtaDataSource(
			@Nullable final DataSource pNonJtaDataSource) {
		
		this.nonJtaDataSource = pNonJtaDataSource;
		super.setNonJtaDataSource(this.nonJtaDataSource);
		
		if (this.nonJtaDataSource != null) {
			this.dataSource = (SimpleDriverDataSource) this.nonJtaDataSource;
		}
		
	} // Fin de setNonJtaDataSource(...).__________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addMappingFileName(
			final String pMappingFileName) {
		this.mappingFileNames.add(pMappingFileName);
		super.addMappingFileName(pMappingFileName);
	} // Fin de addMappingFileName(...).___________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getMappingFileNames() {
		return this.mappingFileNames;
	} // Fin de getMappingFileNames()._____________________________________
	
	
	
	/**
	* setter de la liste des <b>noms qualifiés des 
	* fichiers de Mapping (orm.xml)</b> des Entities JPA 
	* mappées pour management 
	* par JPA dans un persistence.xml.<br/>
	* <ul>
	* <li>optionnel si on utilise les annotations 
	* sur les classes Entities.</li>
	* <li>Correspond au <code>mapping-file</code> element 
	* dans un persistence.xml.</li>
	* <li>un orm.xml prévaut toujours sur les annotations 
	* lorsque l'on utilise des Entities JPA 
	* <b>annotées</b>.</li>
	* </ul>
	*
	* @param pMappingFileNames : List&lt;String&gt; : 
	* valeur à passer à this.mappingFileNames.<br/>
	*/
	public void setMappingFileNames(
			final List<String> pMappingFileNames) {
		this.mappingFileNames = pMappingFileNames;
		for (final String mappingFileName : this.mappingFileNames) {
			super.addMappingFileName(mappingFileName);
		}
	} // Fin de setMappingFileNames(...).__________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addJarFileUrl(
			final URL pJarFileUrl) {
		this.jarFileUrls.add(pJarFileUrl);
		super.addJarFileUrl(pJarFileUrl);
	} // Fin de addJarFileUrl(...).________________________________________
	


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<URL> getJarFileUrls() {		
		return this.jarFileUrls;		
	} // Fin de getJarFileUrls().__________________________________________

	
		
	/**
	* setter de la liste des URL des jar que l'ORM doit inspecter.<br/>
	* chaque URL Correspond au <code>jar-file</code> element 
	* dans un persistence.xml.
	* <ul>
	* <li>this.jarFileUrls == liste vide si pJarFileUrls == null.<br/></li>
	* </ul>
	*
	* @param pJarFileUrls : List&lt;URL&gt; : 
	* valeur à passer à this.jarFileUrls.<br/>
	*/
	public void setJarFileUrls(
			final List<URL> pJarFileUrls) {
		
		if (pJarFileUrls == null) {
			this.jarFileUrls = Collections.emptyList(); 
		} else {
			
			this.jarFileUrls = pJarFileUrls;
			
			for (final URL jarFileUrl : this.jarFileUrls) {
				super.addJarFileUrl(jarFileUrl);
			}
			
		}
				
	} // Fin de setJarFileUrls(...)._______________________________________



	/**
	 * {@inheritDoc}
	 */
	@Nullable
	@Override
	public URL getPersistenceUnitRootUrl() {
		return this.persistenceUnitRootUrl;
	} // Fin de getPersistenceUnitRootUrl()._______________________________

	
	
	/**
	* setter de l'URL référençant un jar ou un répertoire <b>racine</b> 
	* de l'unité de persistence.
	*
	* @param pPersistenceUnitRootUrl : URL : 
	* valeur à passer à this.persistenceUnitRootUrl.<br/>
	*/
	@Override
	public void setPersistenceUnitRootUrl(
			@Nullable final URL pPersistenceUnitRootUrl) {
		this.persistenceUnitRootUrl = pPersistenceUnitRootUrl;
		super.setPersistenceUnitRootUrl(this.persistenceUnitRootUrl);
	} // Fin de setPersistenceUnitRootUrl(...).____________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addManagedClassName(
			final String pManagedClassName) {
		this.managedClassNames.add(pManagedClassName);
		super.addManagedClassName(pManagedClassName);
	} // Fin de addManagedClassName(...).__________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getManagedClassNames() {
		return this.managedClassNames;
	} // Fin de getManagedClassNames().____________________________________

	
	
	/**
	* setter de la liste des <b>noms qualifiés</b> des 
	* classes Entities JPA mappées pour management 
	* par JPA dans un persistence.xml.<br/>
	* <ul>
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
	public void setManagedClassNames(
			final List<String> pManagedClassNames) {
		
		if (pManagedClassNames == null) {
			this.managedClassNames = new LinkedList<String>();
		} else {
			this.managedClassNames = pManagedClassNames;
			for (final String managedClassName : this.managedClassNames) {
				super.addManagedClassName(managedClassName);
			}
		}
		
	} // Fin de setManagedClassNames(...)._________________________________

		
	
	/**
	 * Add a managed package to the persistence provider's metadata.
	 * <p>Note: This refers to annotated {@code package-info.java} files. It does
	 * <i>not</i> trigger entity scanning in the specified package; this is
	 * rather the job of {@link DefaultPersistenceUnitManager#setPackagesToScan}.
	 * @since 4.1
	 * @see SmartPersistenceUnitInfo#getManagedPackages()
	 * @see #addManagedClassName
	 */
	@Override
	public void addManagedPackage(
			final String pPackageName) {
		this.managedPackages.add(pPackageName);
		super.addManagedPackage(pPackageName);
	} // Fin de addManagedPackage(...).____________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getManagedPackages() {
		return this.managedPackages;
	} // Fin de getManagedPackages().______________________________________


	
	/**
	* .
	*
	* @param pManagedPackages : List&lt;String&gt; : 
	* valeur à passer à this.managedPackages.<br/>
	*/
	public final void setManagedPackages(
			final List<String> pManagedPackages) {
		
		if (pManagedPackages == null) {
			this.managedPackages = new LinkedList<String>();
		} else {
			this.managedPackages = pManagedPackages;
			for (final String managedPackage : this.managedPackages) {
				super.addManagedPackage(managedPackage);
			}
		}
		
	} // Fin de setManagedPackages(...).___________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean excludeUnlistedClasses() {		
		return this.excludeUnlistedClasses;
	} // Fin de excludeUnlistedClasses().__________________________________

	
	
	/**
	 * Getter du boolean qui stipule que l'ORM ne doit manager 
	 * que les classes Entities JPA listées dans le persistence.xml 
	 * si il est à true.<br/>
	 * <ul>
	 * <li>correspond au <code>exclude-unlisted-classes</code> 
	 * element dans un persistence.xml</li>
	 * <li>toujours false si on utilise les classes annotées. </li>
	 * </ul>
	 *
	 * @return this.excludeUnlistedClasses : boolean.<br/>
	 */
	public boolean isExcludeUnlistedClasses() {
		return this.excludeUnlistedClasses;
	} // Fin de isExcludeUnlistedClasses().________________________________


	
	/**
	* Setter du boolean qui stipule que l'ORM ne doit manager 
	* que les classes Entities JPA listées dans le persistence.xml 
	* si il est à true.<br/>
	* <ul>
	* <li>correspond au <code>exclude-unlisted-classes</code> 
	* element dans un persistence.xml</li>
	* <li>toujours false si on utilise les classes annotées. </li>
	* </ul>
	* 
	* @param pExcludeUnlistedClasses : boolean : 
	* valeur à passer à this.excludeUnlistedClasses.<br/>
	*/
	@Override
	public void setExcludeUnlistedClasses(
			final boolean pExcludeUnlistedClasses) {
		this.excludeUnlistedClasses = pExcludeUnlistedClasses;
		super.setExcludeUnlistedClasses(this.excludeUnlistedClasses);
	} // Fin de setExcludeUnlistedClasses(...).____________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public SharedCacheMode getSharedCacheMode() {		
		return this.sharedCacheMode;		
	} // Fin de getSharedCacheMode().______________________________________

	
		
	/**
	* setter du mode d'utilisation du cache de 2nd niveau par l'ORM.
	* <ul>
	* <li>correspond au <code>shared-cache-mode</code> 
	* element dans un persistence.xml</li>
	* <li>this.sharedCacheMode = UNSPECIFIED 
	* si pSharedCacheMode == null.</li>
	* </ul>
	*
	* @param pSharedCacheMode : SharedCacheMode : 
	* valeur à passer à this.sharedCacheMode.<br/>
	*/
	@Override
	public void setSharedCacheMode(
			final SharedCacheMode pSharedCacheMode) {
		
		if (pSharedCacheMode == null) {
			this.sharedCacheMode = SharedCacheMode.UNSPECIFIED;
		} else {
			this.sharedCacheMode = pSharedCacheMode;
		}
		super.setSharedCacheMode(this.sharedCacheMode);
				
	} // Fin de setSharedCacheMode(...).___________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public ValidationMode getValidationMode() {		
		return this.validationMode;		
	} // Fin de getValidationMode()._______________________________________


		
	/**
	* Setter du mode de validation utilisé par l'ORM.
	* <ul>
	* <li>correspond au <code>validation-mode</code> element 
	* dans un persistence.xml</li>
	* <li>this.validationMode == AUTO si pValidationMode == null.</li>
	* </ul>
	*
	* @param pValidationMode : ValidationMode : 
	* valeur à passer à this.validationMode.<br/>
	*/
	@Override
	public void setValidationMode(
			final ValidationMode pValidationMode) {
		
		if (pValidationMode == null) {
			this.validationMode = ValidationMode.AUTO;
		} else {
			this.validationMode = pValidationMode;
		}
		super.setValidationMode(this.validationMode);
				
	} // Fin de setValidationMode(...).____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addProperty(
			final String pName, final String pValue) {
		
		this.properties.setProperty(pName, pValue);
		super.addProperty(pName, pValue);
		
	} // Fin de addProperty(...).__________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Properties getProperties() {
		return this.properties;
	} // Fin de getProperties().___________________________________________
	
	
	
	/**
	* setter des <b>propriétés de connexion à la base 
	* éventuellement spécifiques 
	* à l'ORM, à SPRING, à H2, ...</b> 
	* comme par exemple le dialecte HIBERNATE.<br/>
	* <ul>
	* <li>correspond au <code>properties</code> element 
	* dans un persistence.xml</li>
	* <li>chaque Property de properties correspond au 
	* <code>property</code> element dans un persistence.xml</li>
	* <li><code>hibernate.connection.url</code> 
	* (URL de connexion à la base), 
	* <code>javax.persistence.jdbc.driver</code> 
	* (driver de connexion à la base) sont des Property...</li>
	* </ul>
	*
	* @param pProperties : Properties : 
	* valeur à passer à this.properties.<br/>
	*/
	@Override
	public void setProperties(
			final Properties pProperties) {
		
		if (pProperties == null) {
			this.properties = new Properties();
		} else {
			this.properties = pProperties;
		}		
		super.setProperties(this.properties);
		
	} // Fin de setProperties(...).________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPersistenceXMLSchemaVersion() {
		return this.persistenceXMLSchemaVersion;		
	} // Fin de getPersistenceXMLSchemaVersion().__________________________
	
	

	/**
	* setter de la version de JPA utilisée dans le persistence.xml.
	* <ul>
	* <li>this.persistenceXMLSchemaVersion == JPA_VERSION si 
	* pPersistenceXMLSchemaVersion == null.</li>
	* </ul>
	*
	* @param pPersistenceXMLSchemaVersion : String : 
	* valeur à passer à this.persistenceXMLSchemaVersion.<br/>
	*/
	@Override
	public void setPersistenceXMLSchemaVersion(
			final String pPersistenceXMLSchemaVersion) {
		
		if (pPersistenceXMLSchemaVersion == null) {
			this.persistenceXMLSchemaVersion = JPA_VERSION;
		} else {
			this.persistenceXMLSchemaVersion 
				= pPersistenceXMLSchemaVersion;
		}
		super.setPersistenceXMLSchemaVersion(
				this.persistenceXMLSchemaVersion);
		
	} // Fin de setPersistenceXMLSchemaVersion(...)._______________________



	/**
	 * {@inheritDoc}
	 */
	@Nullable
	@Override
	public String getPersistenceProviderPackageName() {
		return this.persistenceProviderPackageName;
	} // Fin de getPersistenceProviderPackageName()._______________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPersistenceProviderPackageName(
			@Nullable final String pPersistenceProviderPackageName) {
		
		this.persistenceProviderPackageName 
				= pPersistenceProviderPackageName;
		
		super.setPersistenceProviderPackageName(
				this.persistenceProviderPackageName);
		
	} // Fin de setPersistenceProviderPackageName(...).____________________



	/**
	 * {@inheritDoc}
	 */
	@Nullable
	@Override
	public ClassLoader getClassLoader() {		
		return this.classLoader;		
	} // Fin de getClassLoader().__________________________________________

		
	
	/**
	* Setter du ClassLoader utilisé par l'ORM 
	* pour charger les Entities JPA, ....
	* <ul>
	* <li>this.classLoader = 
	* Thread.currentThread().getContextClassLoader() 
	* si pClassLoader == null.</li>
	* </ul>
	*
	* @param pClassLoader : ClassLoader : 
	* valeur à passer à this.classLoader.<br/>
	*/
	public final void setClassLoader(
			final ClassLoader pClassLoader) {
		
		if (pClassLoader == null) {
			
			/* Thread.currentThread().getContextClassLoader();*/
			this.classLoader 
				= ClassUtils.getDefaultClassLoader();
			
		} else {
			this.classLoader = pClassLoader;
		}
		
	} // Fin de setClassLoader(...)._______________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addTransformer(
			final ClassTransformer pTransformer) {
		return;
	} // Fin de addTransformer(...)._______________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ClassLoader getNewTempClassLoader() {
		return this.newTempClassLoader;
	} // Fin de getNewTempClassLoader().___________________________________


	
	/**
	* Setter du ClassLoader utilisé temporairement 
	* par l'ORM pour charger les Entities JPA, ....
	*
	* @param pNewTempClassLoader : ClassLoader : 
	* valeur à passer à this.newTempClassLoader.<br/>
	*/
	public final void setNewTempClassLoader(
			final ClassLoader pNewTempClassLoader) {
		this.newTempClassLoader = pNewTempClassLoader;
	} // Fin de setNewTempClassLoader(...).________________________________
	
		
	
} // FIN DE LA CLASSE MutablePersistenceUnitInfoJPASpringSansXML.------------
