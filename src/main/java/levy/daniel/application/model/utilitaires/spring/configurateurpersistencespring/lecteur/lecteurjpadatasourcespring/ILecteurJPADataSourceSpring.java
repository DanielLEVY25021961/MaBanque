package levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.lecteur.lecteurjpadatasourcespring;

import org.springframework.core.env.Environment;

/**
 * INTERFACE ILecteurJPADataSourceSpring :<br/>
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
 * @since 11 juil. 2019
 *
 */
public interface ILecteurJPADataSourceSpring {



	@Override
	String toString();
	
	

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
	String getUrl();
	
	

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
	String getDriver();
	
	

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
	String getUserName();
	
	

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
	String getPassword();
	
	

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
	Environment getEnvironmentSpring();
	
	

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
	void setEnvironmentSpring(Environment pEnvironmentSpring);

	

} // FIN DE L'INTERFACE ILecteurJPADataSourceSpring.-------------------------