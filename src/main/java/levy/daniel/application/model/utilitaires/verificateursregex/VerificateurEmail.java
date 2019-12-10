package levy.daniel.application.model.utilitaires.verificateursregex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class VerificateurEmail :<br/>
 * <ul>
 * <li>Vérifie si une chaine de caractères 
 * est conforme à <b>un e-mail</b>.
 * </li>
 * </ul>
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
 * @since 5 déc. 2017
 *
 */
public final class VerificateurEmail {

	// ************************ATTRIBUTS************************************/

	/**
	 * PATTERN_EMAIL : String :<br/>
	 * "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$".<br/>
	 */
	public static final String PATTERN_EMAIL 
		= "^[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(VerificateurEmail.class);

	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR VerificateurEmail() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation.<br/>
	 * <br/>
	 */
	private VerificateurEmail() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method verifier(
	 * String pEmail) :<br/>
	 * <ul>
	 * <li>Vérifie que la String pEmail respecte 
	 * le motif PATTERN_EMAIL.</li>
	 * </ul>
	 * retourne false si pEmail est blank.<br/>
	 * <br/>
	 *
	 * @param pEmail : String : String à vérifier.<br/>
	 * 
	 * @return : boolean : true si pEmail vérifie le motif.<br/>
	 */
	public static boolean verifier(
			final String pEmail) {
		
		/* retourne false si pEmail est blank. */
		if (StringUtils.isBlank(pEmail)) {
			return false;
		}
				
		boolean resultat = false;
		
		final Pattern patternEmail = Pattern.compile(PATTERN_EMAIL);
		final Matcher matcher = patternEmail.matcher(pEmail);
		
		if (matcher.matches()) {
			resultat = true;
		}
		
		return resultat;
		
	} // Fin de verifier(...)._____________________________________________
	

	
} // FIN DE LA CLASSE VerificateurEmail.-------------------------------------
