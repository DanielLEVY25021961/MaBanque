package levy.daniel.application;


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
	 * ", ".
	 */
	String SEPARATEUR_VIRGULE_AERE = ", ";
		
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

	

} // FIN DE L'INTERFACE IConstantesSeparateurs.------------------------------
