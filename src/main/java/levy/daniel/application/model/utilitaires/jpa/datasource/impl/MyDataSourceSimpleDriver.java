package levy.daniel.application.model.utilitaires.jpa.datasource.impl;

import java.sql.Driver;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import levy.daniel.application.model.utilitaires.jpa.datasource.IMyDataSource;
import levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.lecteur.LecteurConfigurationBaseSpring;

/**
 * CLASSE MyDataSourceSimpleDriver :
 * <p>
 * <b>WRAPPER de javax.sql.DataSource</b> permettant de : <br/>
 * <ul>
 * 
 * <li><b>instancier une Datasource <b>SANS</b> POOL DE CONNEXION</b> 
 * <code>org.springframework.jdbc.datasource.SimpleDriverDataSource</code> 
 * <b>héritant de javax.sql.DataSource</b> en lui passant un  
 * <b>fichier de configuration de base SPRING</b> 
 * encapsulé dans un {@link LecteurConfigurationBaseSpring}.</li>
 * 
 * <li><b>instancier une Datasource <b>SANS</b> POOL DE CONNEXION</b> 
 * <code>org.springframework.jdbc.datasource.SimpleDriverDataSource</code> 
 * en lui <b>passant tous ses attributs</b>.</li>
 * <li><b>WRAPPER une <code>javax.sql.DataSource</code></b> 
 * dans <code>this.dataSource</code> de la présente classe 
 * en la <b>typant Datasource <b>sans</b> POOL DE CONNEXION</b> 
 * <code>org.springframework.jdbc.datasource.SimpleDriverDataSource</code>.</li>
 * <li><b>afficher tous les attributs de la Datasource typée</b> 
 * sans POOL DE CONNEXION. 
 * Ces attributs diffèrent en effet 
 * en fonction de la Datasource utilisée (SimpleDriverDataSource, ...).</li>
 * <li><b>retourner la Datasource sans POOL DE CONNEXION 
 * typée 
 * <code>org.springframework.jdbc.datasource.SimpleDriverDataSource</code> 
 * encapsulée dans la présente classe.</li>
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
public class MyDataSourceSimpleDriver implements IMyDataSource {

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
	 * DataSource sans POOL DE CONNEXIONS pour SPRING et HIBERNATE.<br/>
	 * <code>org.springframework.jdbc.datasource.SimpleDriverDataSource</code>.<br/>
	 * <ul>
	 * <li>clé : <code>hibernate.connection.datasource</code> 
	 * dans un EntityManagerFactory créé par le PROVIDER HIBERNATE</li>
	 * </ul>
	 */
	private SimpleDriverDataSource dataSource;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(MyDataSourceSimpleDriver.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public MyDataSourceSimpleDriver() {
		
		this(null
				, null
				, null, null);
		
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
	public MyDataSourceSimpleDriver(
			final LecteurConfigurationBaseSpring 
					pLecteurConfigurationBaseSpring) {
		
		this(pLecteurConfigurationBaseSpring.getUrl()
				, pLecteurConfigurationBaseSpring.getDriver()
				, pLecteurConfigurationBaseSpring.getUserName()
				, pLecteurConfigurationBaseSpring.getPassword());
		
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
	 */
	public MyDataSourceSimpleDriver(
			final String pUrl
				, final String pDriver
				, final String pUserName
				, final String pPassword) {
		
		super();
		
		this.url = pUrl;
		this.driver = pDriver;
		this.userName = pUserName;
		this.password = pPassword;
				
		/* instancie this.dataSource. */
		this.dataSource = new SimpleDriverDataSource(); 
		
		/* alimente this.dataSource. */
		this.alimenterDataSource();
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________

	
	
	 /**
	 * CONSTRUCTEUR WRAPPER.<br/>
	 * <ul>
	 * <li>cast la javax.sql.DataSource en type de DataSource 
	 * réellement utilisé par la classe 
	 * (SimpleDriverDataSource, ComboPooledDataSource, ...).</li>
	 * <li>alimente tous les attributs de la classe 
	 * à partir de pDataSource.</li>
	 * </ul>
	 * 
	 * @param pDataSource : javax.sql.DataSource
	 */
	public MyDataSourceSimpleDriver(
			final DataSource pDataSource) {
		
		super();
		
		this.dataSource = (SimpleDriverDataSource) pDataSource;
		
		this.alimenterAttributs();
		
	} // Fin de CONSTRUCTEUR WRAPPER.______________________________________
	
	
	
	/**
	 * alimente la SimpleDriverDataSource this.dataSource.<br/>
	 * <br/>
	 * - ne fait rien si this.dataSource est null.br/>
	 * <br/>
	 */
	private void alimenterDataSource() {
		
		/* ne fait rien si this.dataSource est null. */
		if (this.dataSource != null) {
			
			/* url. */
			this.dataSource.setUrl(this.url);
			
			/* driver. */
			Driver driverLocal = null;
			try {
				driverLocal = (Driver) Class.forName(this.driver).newInstance();
			} catch (Exception e) {
				if (LOG.isFatalEnabled()) {
					final String message = "Impossible de charger le DRIVER : " 
							+ this.driver;
					LOG.fatal(message, e);
				}
			}
			
			this.dataSource.setDriver(driverLocal);
			
			/* userName. */
			this.dataSource.setUsername(this.userName);
			
			/* password. */
			this.dataSource.setPassword(this.password);
			
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
			
			this.url = this.dataSource.getUrl();
			
			final Driver driverLocal = this.dataSource.getDriver();
			
			if (driverLocal != null) {
				this.driver = driverLocal.getClass().getName();
			}
			
			this.userName = this.dataSource.getUsername();
			this.password = this.dataSource.getPassword();
			
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
		 * réellement utilisé par la classe 
		 * (SimpleDriverDataSource,ComboPooledDataSource, ...).*/
		this.dataSource = (SimpleDriverDataSource) pDataSource;
		
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
	 * les propriétés d'une SimpleDriverDataSource.<br/>
	 * <br/>
	 * - retourne null si pDataSource == null.<br/>
	 * <br/>
	 *
	 * @param pDataSource : 
	 * org.springframework.jdbc.datasource.SimpleDriverDataSource.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	private String toStringDataSource(
			final SimpleDriverDataSource pDataSource) {
		
		/* retourne null si pDataSource == null. */
		if (pDataSource == null) {
			return null;
		}
		
		final StringBuilder builder = new StringBuilder();

		final String urlLocal = pDataSource.getUrl();
		
		String driverLocalString = null;
		final Driver driverLocal = pDataSource.getDriver();
		
		if (driverLocal != null) {
			driverLocalString = driverLocal.toString();
		}
		
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
		if (driverLocalString != null) {
			builder.append(driverLocalString);
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
		stb.append("CONTENU DE LA DATASOURCE DANS MyDataSourceSimpleDriver : ");
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
				
		return stb.toString();

	} // Fin de afficherDataSource().______________________________________

	
	
} // FIN DE LA CLASSE MyDataSourceC3P0.--------------------------------------
