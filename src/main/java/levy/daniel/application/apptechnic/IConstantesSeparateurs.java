package levy.daniel.application.apptechnic;

import java.util.Locale;

/**
 * INTERFACE IConstantesSeparateurs :<br/>
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
 * @since 3 oct. 2019
 *
 */
public interface IConstantesSeparateurs {
	
	
	
	/**
	 * Séparateur point virgule pour les CSV.<br/>
	 * ";"
	 */
	String SEP_PV = ";";
    
	/**
	 * " - ".<br/>
	 */
	String SEPARATEUR_MOINS_AERE = " - ";
		
	/**
	 * "_".<br/>
	 */
	String UNDERSCORE = "_";
	
	/**
	 * '/'.<br/>
	 */
	char SLASH = '/';
		
	/**
	 * '\'.<br/>
	 * ATTENTION : antislash est un caractère spécial 
	 * qui doit être échappé en Java ('\\')<br/>
	 */
	char ANTISLASH = '\\';
	
	/**
	 * '.'.<br/>
	 */
	char POINT = '.';
	
	/**
	 * '\uFEFF'<br/>
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	char BOM_UTF_8 = '\uFEFF';
	
	/**
	 * Locale France.<br/>
	 */
	Locale LOCALE_FR = new Locale("fr", "FR");

	

} // FIN DE L'INTERFACE IConstantesSeparateurs.------------------------------
