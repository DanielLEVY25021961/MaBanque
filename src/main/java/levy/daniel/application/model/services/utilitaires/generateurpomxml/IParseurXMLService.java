package levy.daniel.application.model.services.utilitaires.generateurpomxml;

import java.io.File;

import org.jdom2.Document;

/**
 * INTERFACE IParseurXMLService :<br/>
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
 * @since 5 déc. 2018
 *
 */
public interface IParseurXMLService {
	
	

	/**
	 * <b>Parse un fichier XML</b> et le retourne 
	 * sous forme de org.jdom2.Document.<br/>
	 * <ul>
	 * <li>utilise <code>documentXML 
	 * = saxBuilder.build(pFile);</code>.</li>
	 * </ul>
	 * - retourne null si pFile == null.<br/>
	 * - retourne null si pFile n'existe pas.<br/>
	 * - retourne null si pFile est un répertoire.<br/>
	 * - retourne null si JDOMException.<br/>
	 * - retourne null si IOException.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier XML à parser.<br/>
	 * 
	 * @return : Document : org.jdom2.Document.<br/>
	 */
	Document parserXML(File pFile);
	
	
	
	/**
	 * <b>retourne une String encodée en UTF-8 pour l'affichage 
	 * formaté d'un Document XML</b>.<br/>
	 * <ul>
	 * <li>utilise 
	 * <code>xmlOutputter.outputString(pDocument)</code>.</li>
	 * <li>encode la String en UTF-8.</li>
	 * <li>utilise 
	 * <code>fomatPrettyFormat.setEncoding("UTF-8");</code>.</li>
	 * </ul>
	 * - retourne null si pDocument == null.<br/>
	 * <br/>
	 *
	 * @param pDocument : 
	 * org.jdom2.Document.<br/>
	 * 
	 * @return : String : 
	 * Document XML sous forme de String.<br/>
	 */
	String afficherDocumentXML(Document pDocument);
	
	

} // FIN DE L'INTERFACE IParseurXMLService.----------------------------------