package levy.daniel.application.model.services.utilitaires.generateurpomxml.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import levy.daniel.application.model.services.utilitaires.generateurpomxml.IParseurXMLService;

/**
 * CLASSE ParseurXMLService :<br/>
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
public class ParseurXMLService implements IParseurXMLService {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(ParseurXMLService.class);

	// *************************METHODES************************************/*

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public ParseurXMLService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Document parserXML(
			final File pFile) {
		
		/* retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		/* retourne null si pFile n'existe pas. */
		if (!pFile.exists()) {
			return null;
		}
		
		/* retourne null si pFile est un répertoire. */
		if (pFile.isDirectory()) {
			return null;
		}
		
		Document documentXML = null;
		final SAXBuilder saxBuilder = new SAXBuilder();
		
		try {
			
			documentXML = saxBuilder.build(pFile);
			
		} catch (JDOMException e) {
			
			final String message 
				= "Erreur XML dans le fichier : " 
						+ pFile.getAbsolutePath();
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message, e);
			}
			
			/* retourne null si JDOMException. */
			return null;
			
		} catch (IOException e) {
			
			final String message 
				= "Erreur IO dans le fichier : " 
						+ pFile.getAbsolutePath();
			
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message, e);
			}
			
			/* retourne null si IOException. */
			return null;
			
		}
		
		return documentXML;
		
	} // Fin de parserXML(...).____________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String afficherDocumentXML(
			final Document pDocument) {
		
		/* retourne null si pDocument == null. */
		if (pDocument == null) {
			return null;
		}
		
		final Format fomatPrettyFormat = Format.getPrettyFormat();  
		fomatPrettyFormat.setEncoding("UTF-8");
		
		final XMLOutputter xmlOutputter 
			= new XMLOutputter(fomatPrettyFormat);
				
		final String resultat = xmlOutputter.outputString(pDocument);
		
		return resultat;
		
	} // Fin de afficherDocumentXML(...).__________________________________
	
	
	
} // FIN DE LA CLASSE ParseurXMLService.-------------------------------------
