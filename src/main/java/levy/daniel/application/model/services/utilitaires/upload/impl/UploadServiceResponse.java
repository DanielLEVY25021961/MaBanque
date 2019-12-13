package levy.daniel.application.model.services.utilitaires.upload.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.IConstantesApplicatives;
import levy.daniel.application.IConstantesSeparateurs;

/**
 * CLASSE UploadServiceResponse :<br/>
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
 * @since 8 nov. 2019
 *
 */
public class UploadServiceResponse {

	// ************************ATTRIBUTS************************************/

	/**
	 * résultat de l'exécution du service.
	 */
	private boolean status;
	
	/**
	 * liste des messages du service à l'attention de l'utilisateur.
	 */
	private List<String> listMessagesUtilisateur = new ArrayList<String>();
	
	/**
	 * liste des messages du service à l'attention du développeur.
	 */
	private List<String> listMessagesDeveloppeur = new ArrayList<String>();
	
	/**
	 * Fichier uploadé par le service.
	 */
	private File fichierUploade;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UploadServiceResponse.class);


	// *************************METHODES************************************/


	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public UploadServiceResponse() {
		this(false, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * 
	 * @param pStatus : boolean : résultat de l'exécution du service.
	 * @param pFichierUploade : java.io.File : 
	 * Fichier uploadé par le service.
	 */
	public UploadServiceResponse(
			final boolean pStatus
				, final File pFichierUploade) {
		
		super();
		
		this.status = pStatus;
		this.fichierUploade = pFichierUploade;
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________

	
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("UploadServiceResponse [status=");
		
		builder.append(this.status);
		builder.append(IConstantesSeparateurs.SEPARATEUR_VIRGULE_AERE);
		
		builder.append("listMessagesUtilisateur=");
		if (this.listMessagesUtilisateur != null) {
			builder.append(this.listMessagesUtilisateur);			
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesSeparateurs.SEPARATEUR_VIRGULE_AERE);
		
		builder.append("listMessagesDeveloppeur=");
		if (this.listMessagesDeveloppeur != null) {			
			builder.append(this.listMessagesDeveloppeur);
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(IConstantesSeparateurs.SEPARATEUR_VIRGULE_AERE);
		
		builder.append("fichierUploade=");
		if (this.fichierUploade != null) {			
			builder.append(this.fichierUploade.getAbsolutePath());
		} else {
			builder.append(IConstantesApplicatives.NULL);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * ajoute un message dans la liste 
	 * <code>this.listMessagesUtilisateur</code>
	 *
	 * @param pString : String : message à l'attention de l'Utilisateur.
	 * 
	 * @return : boolean : true si le message a été ajouté.<br/>
	 */
	public final boolean ajouterMessageUtilisateur(final String pString) {
		
		if (!StringUtils.isBlank(pString)) {
			return this.listMessagesUtilisateur.add(pString);
		}
		
		return false;
		
	} // Fin de ajouterMessageUtilisateur(...).____________________________


	
	/**
	 * retire un message dans la liste 
	 * <code>this.listMessagesUtilisateur</code>
	 *
	 * @param pString : String : message à l'attention de l'Utilisateur 
	 * à retirer de <code>this.listMessagesUtilisateur</code>.
	 * 
	 * @return : boolean : true si le message a été retiré.<br/>
	 */
	public final boolean retirerMessageUtilisateur(final String pString) {
		
		if (!StringUtils.isBlank(pString)) {
			return this.listMessagesUtilisateur.remove(pString);
		}
		
		return false;
		
	} // Fin de retirerMessageUtilisateur(...).____________________________
	
	
	
	/**
	 * ajoute un message dans la liste 
	 * <code>this.listMessagesDeveloppeur</code>
	 *
	 * @param pString : String : message à l'attention du Developpeur.
	 * 
	 * @return : boolean : true si le message a été ajouté.<br/>
	 */
	public final boolean ajouterMessageDeveloppeur(final String pString) {
		
		if (!StringUtils.isBlank(pString)) {
			return this.listMessagesDeveloppeur.add(pString);
		}
		
		return false;
		
	} // Fin de ajouterMessageDeveloppeur(...).____________________________


	
	/**
	 * retire un message dans la liste 
	 * <code>this.listMessagesDeveloppeur</code>
	 *
	 * @param pString : String : message à l'attention du Developpeur 
	 * à retirer de <code>this.listMessagesDeveloppeur</code>.
	 * 
	 * @return : boolean : true si le message a été retiré.<br/>
	 */
	public final boolean retirerMessageDeveloppeur(final String pString) {
		
		if (!StringUtils.isBlank(pString)) {
			return this.listMessagesDeveloppeur.remove(pString);
		}
		
		return false;
		
	} // Fin de retirerMessageDeveloppeur(...).____________________________
	
	
		
	/**
	 * Getter du résultat de l'exécution du service.
	 *
	 * @return this.status : boolean.<br/>
	 */
	public final boolean isStatus() {
		return this.status;
	} // Fin de isStatus().________________________________________________



	/**
	* Setter du résultat de l'exécution du service.
	*
	* @param pStatus : boolean : 
	* valeur à passer à this.status.<br/>
	*/
	public final void setStatus(final boolean pStatus) {
		this.status = pStatus;
	} // Fin de setStatus(...).____________________________________________


	
	/**
	 * Getter de la liste des messages du service 
	 * à l'attention de l'utilisateur.
	 *
	 * @return this.listMessagesUtilisateur : List&lt;String&gt;.<br/>
	 */
	public final List<String> getListMessagesUtilisateur() {
		return this.listMessagesUtilisateur;
	} // Fin de getListMessagesUtilisateur().______________________________


	
	/**
	* Setter de la liste des messages du service 
	* à l'attention de l'utilisateur.
	*
	* @param pListMessagesUtilisateur : List&lt;String&gt; : 
	* valeur à passer à this.listMessagesUtilisateur.<br/>
	*/
	public final void setListMessagesUtilisateur(
			final List<String> pListMessagesUtilisateur) {
		this.listMessagesUtilisateur = pListMessagesUtilisateur;
	} // Fin de setListMessagesUtilisateur(...).___________________________


	
	/**
	 * Getter de la liste des messages du service 
	 * à l'attention du développeur.
	 *
	 * @return this.listMessagesDeveloppeur : List&lt;String&gt;.<br/>
	 */
	public final List<String> getListMessagesDeveloppeur() {
		return this.listMessagesDeveloppeur;
	} // Fin de getListMessagesDeveloppeur().______________________________


	
	/**
	* Setter de la liste des messages du service 
	* à l'attention du développeur.
	*
	* @param pListMessagesDeveloppeur : List&lt;String&gt; : 
	* valeur à passer à this.listMessagesDeveloppeur.<br/>
	*/
	public final void setListMessagesDeveloppeur(
			final List<String> pListMessagesDeveloppeur) {
		this.listMessagesDeveloppeur = pListMessagesDeveloppeur;
	} // Fin de setListMessagesDeveloppeur(...).___________________________


	
	/**
	 * Getter du Fichier uploadé par le service.
	 *
	 * @return this.fichierUploade : File.<br/>
	 */
	public final File getFichierUploade() {
		return this.fichierUploade;
	} // Fin de getFichierUploade()._______________________________________


	
	/**
	* Setter du Fichier uploadé par le service.
	*
	* @param pFichierUploade : File : 
	* valeur à passer à this.fichierUploade.<br/>
	*/
	public final void setFichierUploade(
			final File pFichierUploade) {
		this.fichierUploade = pFichierUploade;
	} // Fin de setFichierUploade(...).____________________________________

	
	
} // FIN DE LA CLASSE UploadServiceResponse.---------------------------------
