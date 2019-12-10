package levy.daniel.application.apptechnic.exceptions.technical.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.apptechnic.exceptions.technical.AbstractRunTimeTechnicalException;

/**
 * class ObjetManquantRunTimeException :<br/>
 * HERITE DE AbstractRunTimeTechnicalException ce qui lui
 * permet de s'inscrire dans une liste static partagée
 * par toutes les AbstractRunTimeTechnicalException.<br/>
 * <br/>
 * Exception concrète de type RunTime lancée lorsque
 * un fichier passé en paramètre est un vide.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <br/>
 *
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author Levy Lévy
 * @version 1.0
 * @since 24 juin 2011
 *
 */
public class ObjetManquantRunTimeException extends
		AbstractRunTimeTechnicalException {

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
	private static final Log LOG = LogFactory
			.getLog(ObjetManquantRunTimeException.class);

	// *************************METHODES************************************/
	
	

	/**
	 * method CONSTRUCTEUR ObjetManquantRunTimeException() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * Permet de construire une ObjetManquantRunTimeException
	 * sans message ni propagation d'exception cause.<br/>
	 *
	 */
	public ObjetManquantRunTimeException() {
		super();
	} // Fin de ObjetManquantRunTimeException()._____________________________
	


	/**
	 * method CONSTRUCTEUR ObjetManquantRunTimeException(
	 * String pMessage) :<br/>
	 * Constructeur d'arité 1.<br/>
	 * Permet de construire une ObjetManquantRunTimeException
	 * qui propage un message.<br/>
	 *
	 * @param pMessage : String : le message à propager.<br/>
	 */
	public ObjetManquantRunTimeException(final String pMessage) {
		super(pMessage);
	} // Fin de CONSTRUCTEUR ObjetManquantRunTimeException(
	// String pMessage).___________________________________________________
	
	
	
	/**
	 * method CONSTRUCTEUR ObjetManquantRunTimeException(
	 * Throwable pCause) :<br/>
	 * Constructeur d'arité 1.<br/>
	 * Permet de construire une ObjetManquantRunTimeException
	 * qui propage un Throwable qui a causé la présente
	 * exception.<br/>
	 *
	 * @param pCause : Throwable : l'exception qui a
	 * causé la présente et que l'on veut propager.<br/>
	 */
	public ObjetManquantRunTimeException(final Throwable pCause) {
		super(pCause);
	} // Fin de CONSTRUCTEUR ObjetManquantRunTimeException(
	// Throwable pCause).__________________________________________________
	
	
	
	/**
	 * method CONSTRUCTEUR ObjetManquantRunTimeException(
	 * String pMessage
	 * , Throwable pCause) :<br/>
	 * Constructeur d'arité 2.<br/>
	 * Permet de construire une ObjetManquantRunTimeException
	 * qui propage :<br/>
	 * - un message,<br/>
	 * - un Throwable qui a causé la présente
	 * exception.<br/>
	 *
	 * @param pMessage : String : le message à propager.<br/>
	 * @param pCause : Throwable : l'exception qui a
	 * causé la présente et que l'on veut propager.<br/>
	 */
	public ObjetManquantRunTimeException(
			final String pMessage, final Throwable pCause) {
		super(pMessage, pCause);
	} // Fin de CONSTRUCTEUR ObjetManquantRunTimeException(
	 // String pMessage, Throwable pCause).________________________________

	
	
	/**
	 * method CONSTRUCTEUR ObjetManquantRunTimeException(
	 * String pMessage
	 * , Throwable pCause
	 * , List<String> pListeExceptions) :<br/>
	 * Constructeur d'arité 3.<br/>
	 * Permet de construire une ObjetManquantRunTimeException
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
	public ObjetManquantRunTimeException(
			final String pMessage
				, final Throwable pCause
					, final List<String> pListeExceptions) {
		
		super(pMessage, pCause, pListeExceptions);
		
	} // Fin de CONSTRUCTEUR ObjetManquantRunTimeException(
	 // String pMessage
	 // , Throwable pCause
	 // , List<String> pListeExceptions).__________________________________
	

	
} // FIN DE LA CLASSE ObjetManquantRunTimeException.-------------------------
