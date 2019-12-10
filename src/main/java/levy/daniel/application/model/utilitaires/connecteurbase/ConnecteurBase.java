package levy.daniel.application.model.utilitaires.connecteurbase;

import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zaxxer.hikari.HikariDataSource;

/**
 * CLASSE ConnecteurBase :<br/>
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
 * @since 11 juil. 2019
 *
 */
public class ConnecteurBase {

	// ************************ATTRIBUTS************************************/

	/**
	 * URL du serveur de BASE DE DONNEES.<br/>
	 * Par exemple : "jdbc:postgresql://localhost:5432/base-traficweb_v1"<br/>
	 */
	private String jdbcUrl;
	
	/**
	 * LOGIN de la BASE DE DONNEES.<br/>
	 * Par exemple : "postgres".
	 */
	private String username;
	
	/**
	 * MOT DE PASSE de la BASE DE DONNEES.<br/>
	 * Par exemple : "postgres".
	 */
	private String password;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(ConnecteurBase.class);

	// *************************METHODES************************************/
	
	
	 
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * 
	 * @param pJdbcUrl : String : URL du serveur de BASE DE DONNEES.<br/>
	 * Par exemple : "jdbc:postgresql://localhost:5432/base-traficweb_v1"<br/>
	 * @param pUsername : String : LOGIN de la BASE DE DONNEES.<br/>
	 * Par exemple : "postgres".
	 * @param pPassword : String : MOT DE PASSE de la BASE DE DONNEES.<br/>
	 * Par exemple : "postgres".
	 */
	public ConnecteurBase(
			final String pJdbcUrl
				, final String pUsername
					, final String pPassword) {
		
		super();
		
		this.jdbcUrl = pJdbcUrl;
		this.username = pUsername;
		this.password = pPassword;
		
	} // Fin du CONSTRUCTEUR COMPLET.______________________________________
	

	

	/**
	 * teste la connexion à une HikariDataSource.
	 * <ul>
	 * <li>retourne true si le serveur de BD répond.</li>
	 * </ul>
	 *
	 * @return : Boolean : true si le serveur de BD répond.<br/>
	 */
	public Boolean connecterABaseHikariDataSource() {
		
		final HikariDataSource dataSource = new HikariDataSource();  
		
		dataSource.setJdbcUrl(this.jdbcUrl);
		dataSource.setUsername(this.username);
		dataSource.setPassword(this.password);
		
		try {
			
			dataSource.getConnection();
			
			return true;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
			return false;
			
		} finally {

			dataSource.close();
			
		}
					
	} // Fin de connecterABaseHikariDataSource().__________________________


	
	/**
	 * Getter de l'URL du serveur de BASE DE DONNEES.<br/>
	 * Par exemple : "jdbc:postgresql://localhost:5432/base-traficweb_v1"<br/>
	 *
	 * @return this.jdbcUrl : String.<br/>
	 */
	public final String getJdbcUrl() {
		return this.jdbcUrl;
	} // Fin de getJdbcUrl()?______________________________________________



	/**
	* Setter de l'URL du serveur de BASE DE DONNEES.<br/>
	* Par exemple : "jdbc:postgresql://localhost:5432/base-traficweb_v1"<br/>
	*
	* @param pJdbcUrl : String : 
	* valeur à passer à this.jdbcUrl.<br/>
	*/
	public final void setJdbcUrl(
			final String pJdbcUrl) {
		this.jdbcUrl = pJdbcUrl;
	} // Fin de setJdbcUrl(...).___________________________________________


	
	/**
	 * Getter du LOGIN de la BASE DE DONNEES.<br/>
	 * Par exemple : "postgres".
	 *
	 * @return this.username : String.<br/>
	 */
	public final String getUsername() {
		return this.username;
	} // Fin de getUsername()._____________________________________________


	
	/**
	* Setter du LOGIN de la BASE DE DONNEES.<br/>
	* Par exemple : "postgres".
	*
	* @param pUsername : String : 
	* valeur à passer à this.username.<br/>
	*/
	public final void setUsername(
			final String pUsername) {
		this.username = pUsername;
	} // Fin de setUsername(...).__________________________________________


	
	/**
	 * Getter du MOT DE PASSE de la BASE DE DONNEES.<br/>
	 * Par exemple : "postgres".
	 *
	 * @return this.password : String.<br/>
	 */
	public final String getPassword() {
		return this.password;
	} // Fin de getPassword()._____________________________________________


	
	/**
	* Setter du MOT DE PASSE de la BASE DE DONNEES.<br/>
	* Par exemple : "postgres".
	*
	* @param pPassword : String : 
	* valeur à passer à this.password.<br/>
	*/
	public final void setPassword(
			final String pPassword) {
		this.password = pPassword;
	} // Fin de setPassword(...).__________________________________________
	
	
	
} // FIN DE LA CLASSE ConnecteurBase.----------------------------------------
