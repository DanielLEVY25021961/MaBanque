package levy.daniel.application.model.utilitaires.verificateursregex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class VerificateurLettres :<br/>
 * <ul>
 * <li>Vérifie si une chaine de caractères 
 * ne comporte <b>que des lettres</b>.
 * </li>
 * <li>
 * Les caractères utilisés ne peuvent être que des lettres
 * , <i>pas</i> des espaces, tirets, underscore, chiffres, ...
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
public final class VerificateurLettres {

	// ************************ATTRIBUTS************************************/

	/**
	 * PATTERN_LETTRES_EXCLUSIVEMENT : String :<br/>
	 * "^[a-z[A-Z]]+".<br/>
	 */
	public static final String PATTERN_LETTRES_EXCLUSIVEMENT 
		= "^[a-z[A-Z]]+";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory.getLog(VerificateurLettres.class);

	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR VerificateurLettres() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation.<br/>
	 * <br/>
	 */
	private VerificateurLettres() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * method verifier(
	 * String pString) :<br/>
	 * <ul>
	 * <li>Vérifie que la String pString respecte 
	 * le motif PATTERN_LETTRES_EXCLUSIVEMENT.</li>
	 * </ul>
	 * retourne false si pString est blank.<br/>
	 * <br/>
	 *
	 * @param pString : String : String à vérifier.<br/>
	 * 
	 * @return : boolean : true si pEmail vérifie le motif.<br/>
	 */
	public static boolean verifier(
			final String pString) {
		
		/* retourne false si pEmail est blank. */
		if (StringUtils.isBlank(pString)) {
			return false;
		}
				
		boolean resultat = false;
		
		final Pattern patternEmail 
			= Pattern.compile(PATTERN_LETTRES_EXCLUSIVEMENT);
		
		final Matcher matcher = patternEmail.matcher(pString);
		
		if (matcher.matches()) {
			resultat = true;
		}
		
		return resultat;
		
	} // Fin de verifier(...)._____________________________________________
	

	
} // FIN DE LA CLASSE VerificateurLettres.-----------------------------------
