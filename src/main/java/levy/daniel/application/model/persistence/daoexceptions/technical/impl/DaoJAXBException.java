package levy.daniel.application.model.persistence.daoexceptions.technical.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.persistence.daoexceptions.AbstractDaoException;


/**
 * CLASSE DaoJAXBException :<br/>
 * Exception typée pour les DAO JAXB 
 * pour les erreurs de Marshall, UnMarshall.<br/>
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
 * @since 18 sept. 2017
 *
 */
public class DaoJAXBException extends AbstractDaoException {
	
	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID : long :<br/>
	 * .<br/>
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(DaoJAXBException.class);

	// *************************METHODES************************************/
	

	/**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * Permet de construire une DaoJAXBException
	 * sans message ni propagation d'exception cause.<br/>
	 */
	public DaoJAXBException() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	


	/**
	 * CONSTRUCTEUR D'ARITE 1.<br/>
	 * Permet de construire une DaoJAXBException
	 * qui propage un message.<br/>
	 * <br/>
	 *
	 * @param pMessage : String : le message à propager.<br/>
	 */
	public DaoJAXBException(final String pMessage) {
		super(pMessage);
	} // Fin de CONSTRUCTEUR D'ARITE 1.____________________________________
	
	
	
	/**
	 * CONSTRUCTEUR D'ARITE 1.<br/>
	 * Permet de construire une DaoJAXBException
	 * qui propage un Throwable qui a causé la présente
	 * exception.<br/>
	 * <br/>
	 *
	 * @param pCause : Throwable : l'exception qui a
	 * causé la présente et que l'on veut propager.<br/>
	 */
	public DaoJAXBException(final Throwable pCause) {
		super(pCause);
	} // Fin de CONSTRUCTEUR D'ARITE 1.____________________________________
	
	
	
	/**
	 * CONSTRUCTEUR D'ARITE 2.<br/>
	 * Permet de construire une DaoJAXBException
	 * qui propage :<br/>
	 * - un message,<br/>
	 * - un Throwable qui a causé la présente
	 * exception.<br/>
	 * <br/>
	 *
	 * @param pMessage : String : le message à propager.<br/>
	 * @param pCause : Throwable : l'exception qui a
	 * causé la présente et que l'on veut propager.<br/>
	 */
	public DaoJAXBException(
			final String pMessage, final Throwable pCause) {
		super(pMessage, pCause);
	} // Fin de CONSTRUCTEUR D'ARITE 2.____________________________________

	
	
	/**
	 * CONSTRUCTEUR D'ARITE 3.<br/>
	 * Permet de construire une DaoJAXBException
	 * qui propage :<br/>
	 * - un message,<br/>
	 * - un Throwable qui a causé la présente
	 * exception.<br/>
	 * - Une liste de String susceptible d'encapsuler
	 * le message de la présente Exception.<br/>
	 * <br/>
	 *
	 * @param pMessage : String : le message à propager.<br/>
	 * @param pCause : Throwable : l'exception qui a
	 * causé la présente et que l'on veut propager.<br/>
	 * @param pListeExceptions : Liste dans laquelle on veut
	 * insérer le message de la présente Exception.<br/>
	 */
	public DaoJAXBException(
			final String pMessage
				, final Throwable pCause
					, final List<String> pListeExceptions) {
		
		super(pMessage, pCause, pListeExceptions);
		
	} // Fin de CONSTRUCTEUR D'ARITE 3.____________________________________
	


} // FIN DE LA CLASSE DaoJAXBException.--------------------------------------
