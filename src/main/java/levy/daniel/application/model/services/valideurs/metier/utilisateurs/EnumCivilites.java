package levy.daniel.application.model.services.valideurs.metier.utilisateurs;

import java.util.ArrayList;
import java.util.List;

/**
 * ENUMERATION EnumCivilites :<br/>
 * Enumération des civilités.<br/>
 * chaque Enumeration comprend 2 valeurs :
 * <ul>
 * <li>valeur abrégée (par exemple M.)</li>
 * <li>valeur complète (par exemple Monsieur)</li>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * énumération, enumération, enumeration, Enumeration,<br/>
 * Enumeration à 2 valeurs, à deux champs, <br/>
 * constructeur à 2 valeurs, constructeur à 2 paramètres, <br/>
 * constructeur à 2 arguments,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 28 mars 2019
 *
 */
public enum EnumCivilites {
	
	/**
	 * "M.", "Monsieur".<br/>
	 */
	MONSIEUR("M.", "Monsieur")
	
	, /**
	 * "Mme", "Madame".<br/>
	 */
	MADAME("Mme", "Madame")
	
	, /**
	 * "Mlle", "Mademoiselle".<br/>
	 */
	MADEMOISELLE("Mlle", "Mademoiselle")
	
	, /**
	 * "Dr", "Docteur".<br/>
	 */
	DOCTEUR("Dr", "Docteur")
	
	, /**
	 * "Maître", "Maître".<br/>
	 */
	MAITRE("Maître", "Maître")
	
	, /**
	 * "Mr", "Mister".<br/>
	 */
	MISTER("Mr", "Mister");

	
	/**
	 * abreviation de la civilité.<br/>
	 */
	private final String abreviation;
	
	/**
	 * civilité complète.<br/>
	 */
	private final String civilite;
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 *
	 * @param pAbreviation
	 * @param pCivilite
	 */
	EnumCivilites(
			final String pAbreviation, final String pCivilite) {
		this.abreviation = pAbreviation;
		this.civilite = pCivilite;		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * Getter de l'abreviation de la civilité.<br/>
	 * Par exemple, "Mlle" pour Mademoiselle.
	 *
	 * @return this.abreviation : String.<br/>
	 */
	public final String getAbreviation() {
		return this.abreviation;
	} // Fin de getAbreviation().__________________________________________


	
	/**
	 * Getter de la civilité complète.<br/>
	 * Par exemple, "Mademoiselle".
	 *
	 * @return this.civilite : String.<br/>
	 */
	public final String getCivilite() {
		return this.civilite;
	} // Fin de getCivilite()._____________________________________________
	

	
	/**
	 * retourne la liste des abréviations possibles 
	 * contenues dans la présente Enumeration.<br/>
	 * par exemple M., Mme, Mlle, ...<br/>
	 *
	 * @return : List&lt;String&gt; : 
	 * Liste des abréviations contenues dans l'Enumeration.<br/>
	 */
	public static List<String> getListeAbreviations() {
		
		final EnumCivilites[] enumerations = values();
		
		final List<String> resultat = new ArrayList<String>();
		
		for (int i = 0; i < enumerations.length; i++) {
			
			final String abrev = enumerations[i].getAbreviation();
			
			resultat.add(abrev);
		}
		
		return resultat;
		
	} // Fin de getListeAbreviations().____________________________________
	
	

	/**
	 * retourne la liste des noms complets possibles 
	 * contenus dans la présente Enumeration.<br/>
	 * par exemple Monsieur, Madamee, Mademoiselle, ...<br/>
	 *
	 * @return : List&lt;String&gt; : 
	 * Liste des noms complets contenus dans l'Enumeration.<br/>
	 */
	public static List<String> getListeComplets() {
		
		final EnumCivilites[] enumerations = values();
		
		final List<String> resultat = new ArrayList<String>();
		
		for (int i = 0; i < enumerations.length; i++) {
			
			final String complet = enumerations[i].getCivilite();
			
			resultat.add(complet);
		}
		
		return resultat;
		
	} // Fin de getListeComplets().________________________________________
	

	
	/**
	 * retourne la liste de toutes les valeurs (abrégées ou pas) 
	 * contenues dans la présente énumération.<br/>
	 * Par exemple M., Monsieur, Mme, ...<br/>
	 *
	 * @return : List&lt;String&gt; : 
	 * Liste de toutes les valeurs (abrégées ou pas)
	 *  contenues dans l'Enumeration.<br/>
	 */
	public static List<String> getListeValeursPossibles() {
		
		final List<String> resultat = new ArrayList<String>();
		
		resultat.addAll(getListeAbreviations());
		resultat.addAll(getListeComplets());
		
		return resultat;
		
	} // Fin de getListeValeursPossibles().________________________________
	
	
	
} // FIN DE L' ENUMERATION EnumCivilites.------------------------------------
