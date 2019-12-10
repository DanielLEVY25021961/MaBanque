package levy.daniel.application;


/**
 * INTERFACE IConstantesSautsLigne :<br/>
 * Interface contenant tous les sauts de ligne nécessaires à l'application.<br/>
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
public interface IConstantesSautsLigne {

	
	
	/**
	 * Saut de ligne généré par les éditeurs UNIX et par JAVA.<br/>
	 * "\n" (Retour Ligne = LINE FEED (LF)).<br/>
	 * '\u000a'
	 */
	String SAUTDELIGNE_UNIX = "\n";
	
	/**
	 * Saut de ligne généré par les éditeurs MAC.<br/>
	 * "\r" (Retour Chariot RC = CARRIAGE RETURN (CR)).<br/>
	 * '\u000d'
	 */
	String SAUTDELIGNE_MAC = "\r";
	
	/**
	 * Saut de ligne généré par les éditeurs DOS/WINDOWS.<br/>
	 * "\r\n" (Retour Chariot RC + Retour Ligne Line Feed LF).<br/>
	 * '\u000a''\u000d'
	 */
	String SAUTDELIGNE_DOS_WINDOWS = "\r\n";
	
	/**
	 * Saut de ligne spécifique de la plateforme.<br/>
	 * System.getProperty("line.separator").<br/>
	 */
	String NEWLINE = System.getProperty("line.separator");


	
} // FIN DE L'INTERFACE IConstantesSautsLigne.-------------------------------
