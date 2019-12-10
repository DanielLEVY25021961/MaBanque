package levy.daniel.application.model.utilitaires.utilitairesstrings;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * class FormateurChaine :<br/>
 * CLASSE UTILITAIRE permettant de "padder" (mettre à la bonne longueur)
 * des chaines de caractères ou des chaines assimilables à des 
 * nombres entiers.<br/>
 * - possède une méthode mettreALongueur(String pString, int pLongueur)
 * destinée aux String classiques
 * qui retourne une chaine concaténée avec des espaces à la fin
 * pour atteindre pLongueur 
 * (ou tronque à droite la chaine si longueur(pString) > pLongueur).<br/>
 * - possède une méthode completerAvecZerosAGauche(String pString
 * , int pLongueur) destinée aux String parsables en Integer 
 * qui retourne une chaine concaténée avec des zéros 
 * au début pour atteindre pLongueur 
 * (ou tronque à gauche la chaine si longueur(pString) > pLongueur).<br/>
 * Cette méthode retourne null si la chaine n'est pas parsable en Integer.<br/>
 * <br/>
 * 
 * Exemples d'utilisation :<br/>
 * <code>FormateurChaine.mettreALongueur("tatata", 4);//donnera "tata"</code><br/>
 * <br/>
 * 
 * Mots-clé :<br/>
 * padding, bonne longueur, afficher verticalement
 * à la console, aligner verticalementà la console,<br/>
 * <br/>
 * 
 * Dépendances :<br/>
 * <br/>
 * 
 * @author Dan Lévy
 * @version 1.0
 * @since 29 sept. 2009
 */
public final class FormateurChaine {

	// ***************************ATTRIBUTS*********************************/
	
	/* CONSTANTES. */	
	/**
	 * CLASSE_FORMATEURCHAINE : String :<br/>
	 * "Classe FormateurChaine - ".<br/>
	 */
	public static final String CLASSE_FORMATEURCHAINE 
		= "Classe FormateurChaine - ";

	
	/**
	 * METHODE_COMPLETER_AVEC_ZEROS : String :
	 * "Méthode completerAvecZerosAGauche(String pString, int pLongueur) - ".
	 */
	public static final String METHODE_COMPLETER_AVEC_ZEROS 
		= "Méthode completerAvecZerosAGauche(String pString, int pLongueur) - ";

	
	/**
	 * MESSAGE_PAS_ENTIERE : String :
	 * "La chaine passée en paramètre ne peut être 
	 * assimilée à une valeur entière : ".
	 */
	public static final String MESSAGE_PAS_ENTIERE 
		= "La chaine passée en paramètre ne peut être "
			+ "assimilée à une valeur entière : ";

	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG = LogFactory
		.getLog(FormateurChaine.class);

	// ***************************METHODES**********************************/


	/**
	 * method CONSTRUCTEUR FormateurChaine() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE privé pour interdire
	 * l'instanciation.<br/>
	 */
	private FormateurChaine() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/**
	 * method mettreALongueur(String pString, int pLongueur) :<br/>
	 * - Complète la chaine pString avec autant de blancs que nécessaire
	 * pour que la chaine résultante ait une longueur de pLongueur.<br/>
	 * - Tronque à droite la chaine pString passée en paramètre à pLongueur
	 * si pLongueur voulue est inférieure à la longueur
	 * de pString (par exemple "zorro" devient "zor" pour pLongueur == 3).<br/>
	 * <br/>
	 * - Retourne null si la chaine passée en paramètre est null
	 * ou si pLongueur <=0.<br/>
	 * <br/>
	 *
	 * @param pString : String : la chaine de caractères à mettre
	 * à la bonne longueur en la complétant avec des espaces.<br/>
	 * @param pLongueur : int : longueur à donner à la chaine
	 * résultante (en rajoutant éventuellement des espaces à la fin).<br/>
	 * 
	 * @return String : la chaine concaténée avec des espaces.<br/>
	 */
	public static String mettreALongueur(
			final String pString, final int pLongueur) {

		//************Traitement du vide et de la nullité*************/
		
		/* Si la chaine passée en paramètre est null, ou si
		 * la longueur de formatage est 0, quitte. */
		if (pString == null || pLongueur <= 0) {
			return null;
		} // Fin de if ((pString == null) || (pLongueur <= 0))._____

		
		//***************TRAITEMENT************************************/
		
		String chaineALongueur = null;
		
		StringBuffer stbChaineALongueur = null;
		StringBuffer stbChaineComplement = null;
		
		synchronized (FormateurChaine.class) {
			
			/* Longueur de la chaine passée en paramètre. */
			final int longueur = pString.length();
			
			/* Si la longueur "longueur" de la chaine passée en paramètre 
			 * excède la longueur de formatage "pLongueur",
			 *  on tronque à "pLongueur" à droite. ****/
			if (longueur > pLongueur) {
				
				chaineALongueur = pString.substring(0, pLongueur);
				
			} else {
			/* Sinon, on complète la chaine pString passée en paramètre 
			 * avec le nombre de blancs nécessaires.********************/
				
				stbChaineALongueur = new StringBuffer(pString);
				
				final int nombreEspacesAInserer = pLongueur - longueur;
				
				stbChaineComplement = new StringBuffer();
				
				for (int i = 0; i < nombreEspacesAInserer; i++) {
					stbChaineComplement.append(' ');
				}
				
				stbChaineALongueur.append(stbChaineComplement);

				chaineALongueur = stbChaineALongueur.toString();				
			}

			return chaineALongueur;
			
		} // Fin du bloc static synchronized.________________________
				
	} // Fin de mettreALongueur(
	// String pString
	// , int pLongueur).___________________________________________________
	

	
	/**
	 * method completerAvecZerosAGauche(String pString, int pLongueur) :<br/>
	 * - Prend en paramètre une chaine de caractères "numérique" et la met
	 * à une longueur fixe en la complétant avec des zéros à gauche.<br/>
	 * - Tronque à gauche la chaine pString passée en paramètre à pLongueur
	 * si pLongueur voulue est inférieure à la longueur
	 * de pString (par exemple 007 devient 07 avec pLongueur == 2).<br/>
	 * <br/>
	 * - Retourne null si la chaine passée en paramètre est null
	 * ou si pLongueur <=0.<br/>
	 * - Retourne null si la chaine passée en paramètre n'est pas
	 * parsable en Integer et Logge une NumberFormatException.<br/>
	 * <br/>
	 * 
	 * @param pString : String : la chaine de caractères "numérique".<br/>
	 * @param pLongueur : la longueur de la chaine après le complément
	 * avec des zéros.<br/>
	 * 
	 * @return String : la chaine complétée avec des zéros à gauche.<br/>
	 */
	public static String completerAvecZerosAGauche(
			final String pString
				, final int pLongueur) {
				
		//************Traitement du vide et de la nullité*************/
		
		/* Si la chaine passée en paramètre est null, ou si
		 * la longueur de formatage est 0, quitte. */
		if (pString == null || pLongueur <= 0) {
			return null;
		}
		//******Cas où la chaine n'est pas assimilable à un Nombre*****/
		
		try {
			
			Integer.parseInt(pString);
			
		} catch (NumberFormatException e) {
			
			final String message 
				= CLASSE_FORMATEURCHAINE 
				+ METHODE_COMPLETER_AVEC_ZEROS
				+ MESSAGE_PAS_ENTIERE
				+ pString;
			
			if (LOG.isDebugEnabled()) {
				LOG.debug(message, e);
			}
			
			return null;
			
		}

		//***Fin du Traitement du vide,de la nullité, du non numérique*/
		
		String chaineCompletee = null;
		final int longueurChaine = pString.length();
		
		//***************TRAITEMENT*NORMAL*****************************/
		
		synchronized (FormateurChaine.class) {
			
			final StringBuffer stb = new StringBuffer();

			/* Calcul du nombre de zéros à rajouter. */
			final int nbreZeros = pLongueur - longueurChaine;
			
			/* Si la longueur de la chaine passée en paramètre est inférieure
			 * à la longueur voulue en sortie. */
			if (longueurChaine < pLongueur) {
				
				for (int i = 0; i < nbreZeros; i++) {
					stb.append('0');
				}
				stb.append(pString);
				chaineCompletee = stb.toString();
						
			/* Sinon, il faut tronquer la chaine à gauche.*/
			} else {
			
				chaineCompletee = pString.substring(-nbreZeros, longueurChaine);
			}
					
			return chaineCompletee;
			
		} // Fin du bloc static synchronized.________________________
				
	} // Fin de completerAvecZerosAGauche(String pString,
	// int pLongueur)._____________________________________________________
	

	
} // FIN DE LA CLASSE FormateurChaine.---------------------------------------
