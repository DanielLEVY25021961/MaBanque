package levy.daniel.application.model.utilitaires.jpa.datasource;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.utilitaires.jpa.datasource.impl.MyDataSourceHikari;
import levy.daniel.application.model.utilitaires.jpa.datasource.impl.MyDataSourceSimpleDriver;

/**
 * CLASSE MyDataSourceFactory :<br/>
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
 * @since 7 févr. 2019
 *
 */
public final class MyDataSourceFactory {

	// ************************ATTRIBUTS************************************/

	/**
	 * DATASOURCE SPRING SANS POOL DE CONNEXION.<br/>
	 * "org.springframework.jdbc.datasource.SimpleDriverDataSource".
	 */
	public static final String SIMPLE_DRIVER_DATASOURCE 
		= "org.springframework.jdbc.datasource.SimpleDriverDataSource";
	
	/**
	 * DATASOURCE AVEC POOL DE CONNEXION C3P0.<br/>
	 * "com.mchange.v2.c3p0.ComboPooledDataSource".
	 */
	public static final String COMBO_POOLED_DATASOURCE 
		= "com.mchange.v2.c3p0.ComboPooledDataSource";
	
	/**
	 * "com.zaxxer.hikari.HikariDataSource".
	 */
	public static final String HIKARI_DATASOURCE 
		= "com.zaxxer.hikari.HikariDataSource";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(MyDataSourceFactory.class);

	// *************************METHODES************************************/

	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	private MyDataSourceFactory() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * <b>fournit une instance alimentée de IMyDataSource 
	 * en fonction de la classe réelle de la javax.sql.DataSource 
	 * passée en paramètre</b>.<br/>
	 * <ul>
	 * <li></li>
	 * <li>retourne une instance de MyDataSourceSimpleDriver 
	 * si la classe réelle de pDataSource est 
	 * <code>org.springframework.jdbc.datasource.SimpleDriverDataSource</code>
	 * </li>
	 * <li></li>
	 * <li></li>
	 * </ul>
	 *
	 * @param pDataSource : javax.sql.DataSource.<br/>
	 * 
	 * @return : IMyDataSource :  .<br/>
	 * 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static IMyDataSource getMyDataSource(
			final DataSource pDataSource) 
					throws NoSuchMethodException, SecurityException
						, InstantiationException, IllegalAccessException
							, IllegalArgumentException
								, InvocationTargetException {
		
		synchronized (MyDataSourceFactory.class) {
			
			/* retourne null si pDataSource == null. */
			if (pDataSource == null) {
				return null;
			}
			
			if (pDataSource.getClass().getName()
					.equals(SIMPLE_DRIVER_DATASOURCE)) {
				
				final Constructor<?> constructeurDataSource 
					= MyDataSourceSimpleDriver.class
						.getConstructor(new Class<?>[] {DataSource.class});
				
				final IMyDataSource resultat 
					= (MyDataSourceSimpleDriver) constructeurDataSource.newInstance(pDataSource);
				
				return resultat;
								
			} else if (pDataSource.getClass().getName()
					.equals(HIKARI_DATASOURCE)) {
				
				final Constructor<?> constructeurDataSource 
				= MyDataSourceHikari.class
					.getConstructor(new Class<?>[] {DataSource.class});
			
				final IMyDataSource resultat 
					= (MyDataSourceHikari) constructeurDataSource.newInstance(pDataSource);
				
				return resultat;
					
			}
			
			return null;

		} // Fin du bloc synchronized.__________________________________
		
	} // Fin de getMyDataSource(...).______________________________________
	
	
	
} // FIN DE LA CLASSE MyDataSourceFactory.-----------------------------------
