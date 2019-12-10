package levy.daniel.application.model.utilitaires.jpa.datasource.impl;

import java.util.Properties;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import levy.daniel.application.model.utilitaires.jpa.datasource.IMyDataSource;
import levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.lecteur.LecteurConfigurationBaseSpring;

/**
 * CLASSE MyDataSourceHikariCP :
 * <p>
 * <b>WRAPPER de javax.sql.DataSource</b> permettant de : <br/>
 * <ul>
 * <li><b>instancier une Datasource HIKARI_DATASOURCE avec POOL DE CONNEXION</b> 
 * (<code>com.zaxxer.hikari.HikariDataSource</code> 
 * pour le POOL HikariCP)
 * héritant de javax.sql.DataSource en lui passant un  
 * <b>fichier de configuration de base SPRING</b> 
 * encapsulé dans un {@link LecteurConfigurationBaseSpring}.</li>
 * <li><b>instancier une Datasource HIKARI_DATASOURCE avec POOL DE CONNEXION</b> 
 * (<code>com.zaxxer.hikari.HikariDataSource</code> 
 * pour le POOL HikariCP) en lui <b>passant tous ses attributs</b>.</li>
 * <li><b>WRAPPER une <code>javax.sql.DataSource</code></b> 
 * dans <code>this.dataSource</code> de la présente classe 
 * en la <b>typant Datasource avec POOL DE CONNEXION</b> 
 * (par exemple <code>com.zaxxer.hikari.HikariDataSource</code> 
 * pour le POOL HikariCP)</li>
 * <li><b>afficher tous les attributs de la Datasource typée</b> 
 * avec POOL DE CONNEXION. Ces attributs diffèrent en effet 
 * en fonction du POOL DE CONNEXION utilisé (HikariCP, DBCP2, BoneCP
 * , Tomcat JDBC, HikariCP, ...).</li>
 * <li><b>retourner la Datasource typée avec POOL DE CONNEXION</b> 
 * (<code>com.zaxxer.hikari.HikariDataSource</code> 
 * pour le POOL HikariCP) encapsulée dans la présente classe</li>
 * </ul>
 * </p>
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
 * @since 1 févr. 2019
 *
 */
public class MyDataSourceHikari implements IMyDataSource {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * URL de la BASE (JPA).
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
	 * DRIVER JDBC de la BASE (sous forme de String - JPA).
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
	 * LOGIN de la BASE (JPA).
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
	 * MOT DE PASSE de la BASE (JPA).
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
	 * Taille minimale du pool de connexion HikariCP pour Hibernate.
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
	private transient String poolMinimumIdle;
	
	/**
	 * Taille maximale du pool de connexion HikariCP pour Hibernate.
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
	 * Timeout du pool de connexion HikariCP pour Hibernate.
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
	 * DataSource avec POOL DE CONNEXIONS HikariCP pour HIBERNATE.<br/>
	 * <code>com.zaxxer.hikari.HikariDataSource</code>.<br/>
	 * <ul>
	 * <li>clé : <code>hibernate.connection.datasource</code> 
	 * dans un EntityManagerFactory créé par le PROVIDER HIBERNATE</li>
	 * </ul>
	 */
	private HikariDataSource dataSource;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(MyDataSourceHikari.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public MyDataSourceHikari() {
		
		this(null
				, null
				, null
				, null, null, null, null, null);
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR MALIN.<br/>
	 * <ul>
	 * Construit automatiquement la classe à partir 
	 * d'un LecteurConfigurationBaseSpring 
	 * qui a lu un fichier properties SPRING.
	 * </ul>
	 * 
	 * @param pLecteurConfigurationBaseSpring : 
	 * LecteurConfigurationBaseSpring.<br/>
	 */
	public MyDataSourceHikari(
			final LecteurConfigurationBaseSpring 
					pLecteurConfigurationBaseSpring) {
		
		this(pLecteurConfigurationBaseSpring.getUrl()
				, pLecteurConfigurationBaseSpring.getDriver()
				, pLecteurConfigurationBaseSpring.getUserName()
				, pLecteurConfigurationBaseSpring.getPassword()
				, null
				, pLecteurConfigurationBaseSpring.getPoolMaxSize()
				, pLecteurConfigurationBaseSpring.getPoolTimeOut()
				, pLecteurConfigurationBaseSpring.getPoolMaxStatements());
		
	} // Fin de CONSTRUCTEUR MALIN.________________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * <ul>
	 * <li>instancie this.dataSource.</li>
	 * <li>alimente this.dataSource.</li>
	 * </ul>
	 * 
	 * @param pUrl : String : 
	 * URL de la BASE.
	 * @param pDriver : String : 
	 * DRIVER JDBC de la BASE (sous forme de String).
	 * @param pUserName : String : 
	 * LOGIN de la BASE.
	 * @param pPassword : String : 
	 * MOT DE PASSE de la BASE.
	 * @param pPoolMinSize : String : 
	 * Taille minimale du pool de connexion HikariCP pour Hibernate.
	 * @param pPoolMaxSize : String : 
	 * Taille maximale du pool de connexion HikariCP pour Hibernate.
	 * @param pPoolTimeOut : String : 
	 * Timeout du pool de connexion HikariCP pour Hibernate.
	 * @param pPoolMaxStatements : String : 
	 * taille du cache de PreparedStatements du pool de connexion 
	 * HikariCP pour Hibernate.
	 */
	public MyDataSourceHikari(
			final String pUrl
				, final String pDriver
				, final String pUserName, final String pPassword
				, final String pPoolMinSize, final String pPoolMaxSize
				, final String pPoolTimeOut
				, final String pPoolMaxStatements) {
		
		super();
		
		this.url = pUrl;
		this.driver = pDriver;
		this.userName = pUserName;
		this.password = pPassword;
				
		this.poolMinimumIdle = pPoolMinSize;
		this.poolMaxSize = pPoolMaxSize;
		this.poolTimeOut = pPoolTimeOut;
		this.poolMaxSize = pPoolMaxStatements;
		
		try {
	        Class.forName(this.driver);
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
		
		final HikariConfig config = new HikariConfig();
		
		config.setJdbcUrl(this.url);
		config.setDriverClassName(this.driver);
		config.setUsername(this.userName);
		config.setPassword(this.password);
		
		config.setAllowPoolSuspension(false);

		config.setAutoCommit(true);

		config.setConnectionTimeout(1000L);
		     
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
         
		/* instancie this.dataSource. */
		this.dataSource = new HikariDataSource(config); 
		
		/* alimente this.dataSource. */
		this.alimenterDataSource();
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________

	
	
	 /**
	 * CONSTRUCTEUR WRAPPER.<br/>
	 * <ul>
	 * <li>cast la javax.sql.DataSource en type de DataSource 
	 * réellement utilisé par la classe (HikariConfig, ...).</li>
	 * <li>alimente tous les attributs de la classe 
	 * à partir de pDataSource.</li>
	 * </ul>
	 * 
	 * @param pDataSource : javax.sql.DataSource
	 */
	public MyDataSourceHikari(final DataSource pDataSource) {
		
		super();
		
		this.dataSource = (HikariDataSource) pDataSource;
		
		this.alimenterAttributs();
		
	} // Fin de CONSTRUCTEUR WRAPPER.______________________________________
	
	
	
	/**
	 * alimente la HikariDataSource this.dataSource.<br/>
	 * <br/>
	 * - ne fait rien si this.dataSource est null.br/>
	 * <br/>
	 */
	private void alimenterDataSource() {
		
		/* ne fait rien si this.dataSource est null. */
		if (this.dataSource != null) {
						
			/* poolMinSize. */
			this.dataSource.setMinimumIdle(
					this.fournirIntPoolMinSize(
							this.poolMinimumIdle));
			
				
			/* poolMaxSize. */
			this.dataSource.setMaximumPoolSize(
					this.fournirIntPoolMaxSize(
							this.poolMaxSize));
			
			/* poolTimeOut. */
			this.dataSource.setIdleTimeout(
					this.fournirIntPoolTimeOut(
							this.poolTimeOut));
						
		}
		
	} // Fin de alimenterDataSource()._____________________________________
	

	
	/**
	 * alimente les attributs de la classe à partir de this.dataSource.<br/>
	 * <br/>
	 * - ne fait rien si this.dataSource est null.br/>
	 * <br/>
	 */
	private void alimenterAttributs() {
		
		/* ne fait rien si this.dataSource est null. */
		if (this.dataSource != null) {
			
			this.url = this.dataSource.getJdbcUrl();
			this.driver = this.dataSource.getDriverClassName();
			this.userName = this.dataSource.getUsername();
			this.password = this.dataSource.getPassword();
						
			this.poolMinimumIdle
				= String.valueOf(this.dataSource.getMinimumIdle());
			
			this.poolMaxSize 
				= String.valueOf(this.dataSource.getMaximumPoolSize());
			
			this.poolTimeOut 
			= String.valueOf(this.dataSource.getIdleTimeout());
						
		}
		
	} // Fin de alimenterAttributs().______________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final DataSource getDataSource() {
		return this.dataSource;		
	} // Fin de getDataSource().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setDataSource(
			final DataSource pDataSource) {
		
		/* cast la javax.sql.DataSource en type de DataSource 
		 * réellement utilisé par la classe (HikariConfig, ...).*/
		this.dataSource = (HikariDataSource) pDataSource;
		
		/* alimente les attributs de la classe. */
		this.alimenterAttributs();
		
	} // Fin de CONSTRUCTEUR MALIN.________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		return this.toStringDataSource(this.dataSource);
	} // Fin de toString().________________________________________________
	
	
	
	/**
	 * affiche sur une ligne unique 
	 * les propriétés d'une HikariDataSource.<br/>
	 * <br/>
	 * - retourne null si pDataSource == null.<br/>
	 * <br/>
	 *
	 * @param pDataSource : 
	 * com.zaxxer.hikari.HikariDataSource.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	private String toStringDataSource(
			final HikariDataSource pDataSource) {
		
		/* retourne null si pDataSource == null. */
		if (pDataSource == null) {
			return null;
		}
		
		final StringBuilder builder = new StringBuilder();

		final String urlLocal = pDataSource.getJdbcUrl();
		final String driverLocal = pDataSource.getDriverClassName();
		final String loginLocal = pDataSource.getUsername();
		final String passwordLocal = pDataSource.getPassword();

		builder.append(" - DataSource [");
		
		builder.append("URL = ");
		if (urlLocal != null) {
			builder.append(urlLocal);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("DRIVER = ");
		if (driverLocal != null) {
			builder.append(driverLocal);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("LOGIN = ");
		if (loginLocal != null) {
			builder.append(loginLocal);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("MDP = ");
		if (passwordLocal != null) {
			builder.append(passwordLocal);
		} else {
			builder.append(NULL);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de afficherDataSource(...).___________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherDataSource() {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append("CONTENU DE LA DATASOURCE DANS MyDataSourceHikariCP : ");
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		/* DRIVER. */
		stb.append(
				String.format(
						LOCALE_PLATEFORME
							, FORMAT_TOSTRING
								, "DRIVER", this.driver));		
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		/* URL. */
		stb.append(
				String.format(
						LOCALE_PLATEFORME
							, FORMAT_TOSTRING
								, "URL", this.url));
		stb.append(SAUT_LIGNE_PLATEFORME);

		/* LOGIN. */
		stb.append(
				String.format(
						LOCALE_PLATEFORME
							, FORMAT_TOSTRING
							, "USERNAME (LOGIN)", this.userName));
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		/* PASSWORD. */
		stb.append(
				String.format(
						LOCALE_PLATEFORME
							, FORMAT_TOSTRING
								, "PASSWORD", this.password));
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		/* CLASSE. */
		stb.append(
				String.format(
						LOCALE_PLATEFORME
							, FORMAT_TOSTRING
								, "CLASSE DE LA DATASOURCE"
								, this.dataSource.getClass().getName()));
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		/* poolMinSize. */
		stb.append(
				String.format(
						LOCALE_PLATEFORME
							, FORMAT_TOSTRING
								, "Taille minimale du Pool"
								, this.poolMinimumIdle));
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		/* poolMaxSize. */
		stb.append(
				String.format(
						LOCALE_PLATEFORME
							, FORMAT_TOSTRING
								, "Taille maximale du Pool"
								, this.poolMaxSize));
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		/* poolTimeOut. */
		stb.append(
				String.format(
						LOCALE_PLATEFORME
							, FORMAT_TOSTRING
								, "TimeOut en secondes"
								, this.poolTimeOut));
		stb.append(SAUT_LIGNE_PLATEFORME);
		
		stb.append(SAUT_LIGNE_PLATEFORME);
		stb.append("PROPERTIES DE LA DATASOURCE : ");
		stb.append(SAUT_LIGNE_PLATEFORME);
		final Properties properties 
			= this.dataSource.getDataSourceProperties();
		stb.append(this.afficherJavaUtilProperties(properties));
				
		return stb.toString();

	} // Fin de afficherDataSource().______________________________________

	
	
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
	 * <b>retourne une valeur entière pour this.poolMinSize 
	 * à partir d'une String pPoolMinSize</b>.<br/>
	 * <br/>
	 * - retourne 5 si pPoolMinSize est blank.<br/>
	 * - retourne 5 si pPoolMinSize n'est pas parsable en entier.<br/>
	 * <br/>
	 *
	 * @param pPoolMinSize : String.<br/>
	 * 
	 * @return : int.<br/>
	 */
	private int fournirIntPoolMinSize(
			final String pPoolMinSize) {
		
		/* retourne 5 si pPoolMinSize est blank. */
		if (StringUtils.isBlank(pPoolMinSize)) {
			return 5;
		}
		
		int resultat = 5;
		
		try {
			resultat = Integer.parseInt(pPoolMinSize);
		} catch (NumberFormatException e) {
			/* retourne 5 si pPoolMinSize n'est pas parsable en entier. */
			resultat = 5;
		}
		
		return resultat;
		
	} // Fin de fournirIntPoolMinSize(...).________________________________

	
	
	/**
	 * <b>retourne une valeur entière pour this.poolMaxSize 
	 * à partir d'une String pPoolMaxSize</b>.<br/>
	 * <br/>
	 * - retourne 20 si pPoolMaxSize est blank.<br/>
	 * - retourne 20 si pPoolMaxSize n'est pas parsable en entier.<br/>
	 * <br/>
	 *
	 * @param pPoolMaxSize : String.<br/>
	 * 
	 * @return : int.<br/>
	 */
	private int fournirIntPoolMaxSize(
			final String pPoolMaxSize) {
		
		/* retourne 20 si pPoolMaxSize est blank. */
		if (StringUtils.isBlank(pPoolMaxSize)) {
			return 20;
		}
		
		int resultat = 20;
		
		try {
			resultat = Integer.parseInt(pPoolMaxSize);
		} catch (NumberFormatException e) {
			/* retourne 20 si pPoolMaxSize n'est pas parsable en entier. */
			resultat = 20;
		}
		
		return resultat;
		
	} // Fin de fournirIntPoolMaxSize(...).________________________________

	
	
	/**
	 * <b>retourne une valeur entière pour this.poolTimeOut 
	 * à partir d'une String pPoolTimeOut</b>.<br/>
	 * <br/>
	 * - retourne 500 si pPoolTimeOut est blank.<br/>
	 * - retourne 500 si pPoolTimeOut n'est pas parsable en entier.<br/>
	 * <br/>
	 *
	 * @param pPoolTimeOut : String.<br/>
	 * 
	 * @return : int.<br/>
	 */
	private int fournirIntPoolTimeOut(
			final String pPoolTimeOut) {
		
		/* retourne 500 si pPoolTimeOut est blank. */
		if (StringUtils.isBlank(pPoolTimeOut)) {
			return 500;
		}
		
		int resultat = 500;
		
		try {
			resultat = Integer.parseInt(pPoolTimeOut);
		} catch (NumberFormatException e) {
			/* retourne 500 si pPoolTimeOut n'est pas parsable en entier. */
			resultat = 500;
		}
		
		return resultat;
		
	} // Fin de fournirIntPoolTimeOut(...).________________________________

	
	
	/**
	 * <b>retourne une valeur entière pour this.poolMaxStatements 
	 * à partir d'une String pPoolMaxStatements</b>.<br/>
	 * <br/>
	 * - retourne 50 si pPoolMaxStatements est blank.<br/>
	 * - retourne 50 si pPoolMaxStatements n'est pas parsable en entier.<br/>
	 * <br/>
	 *
	 * @param pPoolMaxStatements : String.<br/>
	 * 
	 * @return : int.<br/>
	 */
	private int fournirIntPoolMaxStatements(
			final String pPoolMaxStatements) {
		
		/* retourne 50 si pPoolMaxStatements est blank. */
		if (StringUtils.isBlank(pPoolMaxStatements)) {
			return 50;
		}
		
		int resultat = 50;
		
		try {
			resultat = Integer.parseInt(pPoolMaxStatements);
		} catch (NumberFormatException e) {
			/* retourne 50 si pPoolMaxStatements 
			 * n'est pas parsable en entier. */
			resultat = 50;
		}
		
		return resultat;
		
	} // Fin de fournirIntPoolMaxStatements(...).__________________________

	
	
	/**
	 * <b>retourne une valeur entière pour this.poolIdleTestPeriod 
	 * à partir d'une String pPoolIdleTestPeriod</b>.<br/>
	 * <br/>
	 * - retourne 2000 si pPoolIdleTestPeriod est blank.<br/>
	 * - retourne 2000 si pPoolIdleTestPeriod n'est pas parsable en entier.<br/>
	 * <br/>
	 *
	 * @param pPoolIdleTestPeriod : String.<br/>
	 * 
	 * @return : int.<br/>
	 */
	private int fournirIntPoolIdleTestPeriod(
			final String pPoolIdleTestPeriod) {
		
		/* retourne 2000 si pPoolIdleTestPeriod est blank. */
		if (StringUtils.isBlank(pPoolIdleTestPeriod)) {
			return 2000;
		}
		
		int resultat = 2000;
		
		try {
			resultat = Integer.parseInt(pPoolIdleTestPeriod);
		} catch (NumberFormatException e) {
			/* retourne 2000 si pPoolIdleTestPeriod 
			 * n'est pas parsable en entier. */
			resultat = 2000;
		}
		
		return resultat;
		
	} // Fin de fournirIntPoolIdleTestPeriod(...)._________________________

	
	
	/**
	 * <b>retourne une valeur entière pour this.poolAcquireIncrement 
	 * à partir d'une String pPoolAcquireIncrement</b>.<br/>
	 * <br/>
	 * - retourne 1 si pPoolAcquireIncrement est blank.<br/>
	 * - retourne 1 si pPoolAcquireIncrement n'est pas parsable en entier.<br/>
	 * <br/>
	 *
	 * @param pPoolAcquireIncrement : String.<br/>
	 * 
	 * @return : int.<br/>
	 */
	private int fournirIntPoolAcquireIncrement(
			final String pPoolAcquireIncrement) {
		
		/* retourne 1 si pPoolAcquireIncrement est blank. */
		if (StringUtils.isBlank(pPoolAcquireIncrement)) {
			return 1;
		}
		
		int resultat = 1;
		
		try {
			resultat = Integer.parseInt(pPoolAcquireIncrement);
		} catch (NumberFormatException e) {
			/* retourne 1 si pPoolAcquireIncrement 
			 * n'est pas parsable en entier. */
			resultat = 1;
		}
		
		return resultat;
		
	} // Fin de fournirIntPoolAcquireIncrement(...)._______________________

	
	
} // FIN DE LA CLASSE MyDataSourceHikariCP.--------------------------------------
