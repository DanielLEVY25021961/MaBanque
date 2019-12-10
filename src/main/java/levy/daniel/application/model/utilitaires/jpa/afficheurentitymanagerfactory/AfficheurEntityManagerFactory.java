package levy.daniel.application.model.utilitaires.jpa.afficheurentitymanagerfactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import levy.daniel.application.model.utilitaires.jpa.datasource.IMyDataSource;
import levy.daniel.application.model.utilitaires.jpa.datasource.MyDataSourceFactory;

/**
 * CLASSE AfficheurEntityManagerFactory :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Tri Set d'Entry, trier Set, trier Entry String Object,<br/>
 * afficher java.util.Properties, afficher Properties,<br/> 
 * afficherProperties, <br/>
 * trier Properties, trier properties,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 27 janv. 2019
 *
 */
public final class AfficheurEntityManagerFactory {

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
		= "%1$-5d  clé : %2$-45s - valeur : %3$s";

	/**
	 * "hibernate.connection.datasource".
	 */
	public static final String PROPERTY_DATASOURCE_KEY 
		= "hibernate.connection.datasource";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(AfficheurEntityManagerFactory.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private AfficheurEntityManagerFactory() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * <b>retourne une String pour l'affichage de toutes les 
	 * Properties lues par l'EntityManagerFactory</b>.<br/>
	 * <ul>
	 * <li>affiche toutes les propriétés contenues 
	 * dans le java.util.Properties de l'EntityManagerFactory.</li>
	 * <li>affiche le contenu de la DataSource contenue 
	 * dans l'EntityManagerFactory.</li>
	 * </ul>
	 * - retourne null si pEntityManagerFactory == null.<br/>
	 * <br/>
	 *
	 * @param pEntityManagerFactory : 
	 * javax.persistence.EntityManagerFactory.<br/> 
	 * 
	 * @return : String : affichage.<br/>
	 */
	public static String afficherEntityManagerFactory(
			final EntityManagerFactory pEntityManagerFactory) {
		
		synchronized(AfficheurEntityManagerFactory.class) {
			
			/* retourne null si pEntityManagerFactory == null. */
			if (pEntityManagerFactory == null) {
				return null;
			}
			
			final StringBuilder stb = new StringBuilder();
			
			/* properties. */
			stb.append(afficherProperties(pEntityManagerFactory));
			
			stb.append(SAUT_LIGNE_PLATEFORME);
			
			/* dataSource. */
			stb.append("DATASOURCE DE ENTITYMANAGERFACTORY (hibernate.connection.datasource) : ");
			stb.append(SAUT_LIGNE_PLATEFORME);
//			stb.append(afficherDataSource(pEntityManagerFactory));
			final DataSource dataSource 
			= (DataSource) pEntityManagerFactory.getProperties()
					.get(PROPERTY_DATASOURCE_KEY);
						
			IMyDataSource myDataSource = null;
			
			try {
				myDataSource = MyDataSourceFactory.getMyDataSource(dataSource);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (myDataSource != null) {
				stb.append(myDataSource.afficherDataSource());
			}
					
			return stb.toString();
			
		} // Fin du bloc synchronized._____________________
		
	} // Fin de afficherEntityManagerFactory(...)._________________________
	
	
	
	/**
	 * <b>retourne une String pour l'affichage de toutes les 
	 * Properties lues par l'EntityManagerFactory</b>.<br/>
	 * <br/>
	 * - retourne null si pEntityManagerFactory == null.<br/>
	 * <br/>
	 * 
	 * @param pEntityManagerFactory : 
	 * javax.persistence.EntityManagerFactory.<br/>  
	 *
	 * @return : String.<br/>
	 */
	public static String afficherProperties(
			final EntityManagerFactory pEntityManagerFactory) {
		
		synchronized(AfficheurEntityManagerFactory.class) {
			
			/* retourne null si pEntityManagerFactory == null. */
			if (pEntityManagerFactory == null) {
				return null;
			}
			
			final Map<String, Object> properties 
				= pEntityManagerFactory.getProperties();
			
			return afficherMapStringObject(properties);
			
		} // Fin du bloc synchronized._____________________
				
	} // Fin de afficherProperties().______________________________________
	
	
	
	
	/**
	 * <b>Fournit une String pour l'affichage 
	 * d'une Map&lt;String, Object&gt;</b>.<br/>
	 * <ul>
	 * <li>retourne null si pMap == null.</li>
	 * </ul>
	 *
	 * @param pMap : Map&lt;String, Object&gt;.<br/>
	 * 
	 * @return : String.<br/>
	 */
	public static String afficherMapStringObject(
			final Map<String, Object> pMap) {
		
		synchronized(AfficheurEntityManagerFactory.class) {
			
			/* retourne null si pMap == null. */
			if (pMap == null) {
				return null;
			}
			
			final StringBuffer stb = new StringBuffer();
			
			final Set<Entry<String, Object>> entrySet = pMap.entrySet();
			
			/* Tri du Set d'Entry. */
			final SortedSet<Entry<String, Object>> entrySetTrie 
				= new TreeSet<Entry<String, Object>>(
						new ComparatorProperties());
			
			for (final Entry<String, Object> entry : entrySet) {
				entrySetTrie.add(entry);
			}
			
			final Iterator<Entry<String, Object>> ite = entrySetTrie.iterator();
			
			int i = 0;
			
			while (ite.hasNext()) {
				
				i++;
				
				final Entry<String, Object> entry = ite.next();
				final String key = entry.getKey();
				final String value = entry.getValue().toString();
				
				final String ligne 
					= String.format(LOCALE_PLATEFORME
							, FORMAT_PROPERTIES
								, i, key, value);
				
				stb.append(ligne);
				stb.append(SAUT_LIGNE_PLATEFORME);
				
			}
			
			return stb.toString();
			
		} // Fin du bloc synchronized._____________________
		
	} // Fin de afficherMapStringObject(...).______________________________


	
	/**
	 * <b>fournit une String pour l'affichage 
	 * du contenu d'un SimpleDriverDataSource pDataSource</b>.<br/>
	 * <br/>
	 * - retourne null si pEntityManagerFactory == null.<br/>
	 * <br/>
	 *
	 * @param pEntityManagerFactory : 
	 * javax.persistence.EntityManagerFactory.<br/> 
	 * 
	 * @return : String : affichage.<br/>
	 */
	public static String afficherDataSource(
			final EntityManagerFactory pEntityManagerFactory) {
		
		synchronized(AfficheurEntityManagerFactory.class) {
			
			/* retourne null si pEntityManagerFactory == null. */
			if (pEntityManagerFactory == null) {
				return null;
			}
			
			final SimpleDriverDataSource dataSource 
				= (SimpleDriverDataSource) 
					pEntityManagerFactory.getProperties()
						.get(PROPERTY_DATASOURCE_KEY);
			
			if (dataSource != null) {
				return afficherDataSource(dataSource);
			}
			
			return null;
		}
		
	} // Fin de afficherDataSource(...).___________________________________
	
	
	
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
	public static String afficherDataSource(
			final SimpleDriverDataSource pDataSource) {
		
		synchronized(AfficheurEntityManagerFactory.class) {
			
			/* retourne null si pDataSource == null. */
			if (pDataSource == null) {
				return null;
			}
			
			final StringBuilder stb = new StringBuilder();
			
			String driverLocal = null;
			
			final Driver driver = pDataSource.getDriver();
			
			if (driver != null) {
				driverLocal = driver.toString();
			}
			
			final String urlLocal = pDataSource.getUrl();
			final String userNameLocal = pDataSource.getUsername();
			final String passwordLocal = pDataSource.getPassword();		
			final String dataSourceClass = pDataSource.getClass().getName();
			
			stb.append("- DRIVER : ");
			stb.append(driverLocal);
			
			stb.append(SAUT_LIGNE_PLATEFORME);
			
			stb.append("- URL : ");
			stb.append(urlLocal);
			
			stb.append(SAUT_LIGNE_PLATEFORME);
			
			stb.append("- USERNAME (LOGIN) : ");
			stb.append(userNameLocal);
			
			stb.append(SAUT_LIGNE_PLATEFORME);
			
			stb.append("- PASSWORD : ");
			stb.append(passwordLocal);
			stb.append(SAUT_LIGNE_PLATEFORME);
			
			stb.append("- CLASSE DE LA DATASOURCE : ");
			stb.append(dataSourceClass);
			
			return stb.toString();

		} // Fin du bloc synchronized._____________________
		
	} // Fin de afficherDataSource(...).___________________________________
	

		
} // FIN DE LA CLASSE AfficheurEntityManagerFactory.-------------------------
