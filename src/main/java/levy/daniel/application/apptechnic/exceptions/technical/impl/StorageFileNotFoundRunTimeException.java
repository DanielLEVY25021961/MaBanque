package levy.daniel.application.apptechnic.exceptions.technical.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.exceptions.technical.AbstractTechnicalException;

/**
 * CLASSE StorageFileNotFoundRunTimeException :<br/>
 * HERITE DE AbstractRunTimeTechnicalException ce qui lui
 * permet de s'inscrire dans une liste static partagée
 * par toutes les AbstractRunTimeTechnicalException.<br/>
 * <br/>
 * Exception concrète de type RunTime lancée lorsque
 * un fichier passé en paramètre est inexistant.<br/>
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
 * @since 25 oct. 2019
 *
 */
public class StorageFileNotFoundRunTimeException 
	extends AbstractTechnicalException {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID : long :<br/>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(StorageFileNotFoundRunTimeException.class);

	// *************************METHODES************************************/
	
	

	/**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * Permet de construire une StorageFileNotFoundRunTimeException
	 * sans message ni propagation d'exception cause.<br/>
	 *
	 */
	public StorageFileNotFoundRunTimeException() {
		super();
	} // Fin de StorageFileNotFoundRunTimeException().____________________________
	


	/**
	 * Constructeur d'arité 1.<br/>
	 * Permet de construire une StorageFileNotFoundRunTimeException
	 * qui propage un message.<br/>
	 *
	 * @param pMessage : String : le message à propager.<br/>
	 */
	public StorageFileNotFoundRunTimeException(final String pMessage) {
		super(pMessage);
	} // Fin de CONSTRUCTEUR StorageFileNotFoundRunTimeException(
	// String pMessage).___________________________________________________
	
	
	
	/**
	 * Constructeur d'arité 1.<br/>
	 * Permet de construire une StorageFileNotFoundRunTimeException
	 * qui propage un Throwable qui a causé la présente
	 * exception.<br/>
	 *
	 * @param pCause : Throwable : l'exception qui a
	 * causé la présente et que l'on veut propager.<br/>
	 */
	public StorageFileNotFoundRunTimeException(final Throwable pCause) {
		super(pCause);
	} // Fin de CONSTRUCTEUR StorageFileNotFoundRunTimeException(
	// Throwable pCause).__________________________________________________
	
	
	
	/**
	 * Constructeur d'arité 2.<br/>
	 * Permet de construire une StorageFileNotFoundRunTimeException
	 * qui propage :<br/>
	 * - un message,<br/>
	 * - un Throwable qui a causé la présente
	 * exception.<br/>
	 *
	 * @param pMessage : String : le message à propager.<br/>
	 * @param pCause : Throwable : l'exception qui a
	 * causé la présente et que l'on veut propager.<br/>
	 */
	public StorageFileNotFoundRunTimeException(
			final String pMessage, final Throwable pCause) {
		super(pMessage, pCause);
	} // Fin de CONSTRUCTEUR StorageFileNotFoundRunTimeException(
	 // String pMessage, Throwable pCause).________________________________

	
	
	/**
	 * Constructeur d'arité 3.<br/>
	 * Permet de construire une StorageFileNotFoundRunTimeException
	 * qui propage :<br/>
	 * - un message,<br/>
	 * - un Throwable qui a causé la présente
	 * exception.<br/>
	 * - Une liste de String susceptible d'encapsuler
	 * le message de la présente Exception.<br/>
	 *
	 * @param pMessage : String : le message à propager.<br/>
	 * @param pCause : Throwable : l'exception qui a
	 * causé la présente et que l'on veut propager.<br/>
	 * @param pListeExceptions : Liste dans laquelle on veut
	 * insérer le message de la présente Exception.<br/>
	 */
	public StorageFileNotFoundRunTimeException(
			final String pMessage
				, final Throwable pCause
					, final List<String> pListeExceptions) {
		
		super(pMessage, pCause, pListeExceptions);
		
	} // Fin de CONSTRUCTEUR StorageFileNotFoundRunTimeException(
	 // String pMessage
	 // , Throwable pCause
	 // , List<String> pListeExceptions).__________________________________

	
	
} // FIN DE LA CLASSE StorageFileNotFoundRunTimeException.-------------------
