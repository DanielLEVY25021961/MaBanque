package levy.daniel.application.model.utilitaires.verificateursregex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class VerificateurLettresLongueur :<br/>
 * <ul>
 * <li>Vérifie si une chaine de caractères 
 * ne comporte <b>que des lettres</b> et possède une certaine 
 * <b>longueur encadrée entre un min et un max</b>.
 * </li>
 * <li>
 * Les caractères utilisés ne peuvent être que des lettres
 * , <i>pas</i> des espaces, tirets, underscore, chiffres, ...
 * </li>
 * <li>
 * La chaine de caractères doit avoir au minimum 
 * la longueur min et au maximum la longueur max.
 * </li>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>// Passage des longueurs min et max.</code><br/>
 * <code>VerificateurLettresLongueur.fixerMinMax(3, 5);</code><br/>
 * <code>// Vérification d'une chaine de caractères.</code><br/>
 * <code>boolean resultatInvalide = VerificateurLettresLongueur.verifier("Dire4");</code><br/>
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
public final class VerificateurLettresLongueur {

	// ************************ATTRIBUTS************************************/

	/**
	 * min : int :<br/>
	 * Longueur minimale de la chaine.<br/>
	 */
	public static int min;

	
	/**
	 * max : int :<br/>
	 * Longueur maximale de la chaine.<br/>
	 */
	public static int max;
	
	
	/**
	 * pattern : String :<br/>
	 * "^[a-z[A-Z]]{min, max}".<br/>
	 */
	public static String pattern 
		= "^[a-z[A-Z]]{" + min + "," + max + "}";
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(VerificateurLettresLongueur.class);

	// *************************METHODES************************************/

	
	 /**
	 * method CONSTRUCTEUR VerificateurLettresLongueur() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * private pour bloquer l'instanciation.<br/>
	 * <br/>
	 */
	private VerificateurLettresLongueur() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * method fixerMinMax(
	 * int pMin
	 * , int pMax) :<br/>
	 * <ul>
	 * <li>Permet de passer la longueur minimum et maximum.</li>
	 * <li>Met à jour le Pattern.</li>
	 * </ul>
	 *
	 * @param pMin : int : longueur minimale.<br/>
	 * @param pMax : int : longueur maximale.<br/>
	 */
	public static void fixerMinMax(
			final int pMin, final int pMax) {
		
		min = pMin;
		max = pMax;
		
		pattern = "^[a-z[A-Z]]{" + min + "," + max + "}";
		
	} // Fin de fixerMinMax(...).__________________________________________
	
	
	
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
			= Pattern.compile(pattern);
		
		final Matcher matcher = patternEmail.matcher(pString);
		
		if (matcher.matches()) {
			resultat = true;
		}
		
		return resultat;
		
	} // Fin de verifier(...)._____________________________________________
	

	
} // FIN DE LA CLASSE VerificateurLettresLongueur.---------------------------
