package levy.daniel.application.model.services.utilitaires.copieurarborescence;

import java.io.File;
import java.util.List;

/**
 * INTERFACE IAfficheurCollections :<br/>
 * Interface factorisant les méthodes des objets susceptibles 
 * d'afficher des collections de File ou de String.<br/>
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
 * @since 10 oct. 2014
 *
 */
public interface IAfficheurCollections {

	
	
	/**
	 * method afficherTableauStrings(
	 * String[] pTableau) :<br/>
	 * Affiche un tableau de String.<br/>
	 * <br/>
	 * - Retourne null si pTableau est null.<br/>
	 * <br/>
	 *
	 * @param pTableau : String[].<br/>
	 * @return : String : String pour affichage à la console.<br/>
	 */
	String afficherTableauStrings(String... pTableau);
	
	

	/**
	 * method afficherTableauFiles(
	 * File[] pTableau) :<br/>
	 * Affiche un tableau de File (en fait les chemins absolus des files).<br/>
	 * <br/>
	 * - Retourne null si pTableau est null.<br/>
	 * <br/>
	 *
	 * @param pTableau : File[].<br/> 
	 * @return : String : String pour affichage à la console.<br/>
	 */
	String afficherTableauFiles(File... pTableau);

	
		
	/**
	 * method afficherListeFiles(
	 * List&lt;File&gt; pList) :<br/>
	 * Affiche une liste de File (en fait les chemins absolus des files).<br/>
	 * <br/>
	 * - Retourne null si pList est null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;File&gt;.<br/>
	 * @return : String : String pour affichage à la console.<br/>
	 */
	String afficherListeFiles(List<File> pList);
	

	
	/**
	 * method afficherListeChemins(
	 * List&lt;String&gt; pList) :<br/>
	 * Affiche une liste de chemins absolus.<br/>
	 * <br/>
	 * - Retourne null si pList est null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;String&gt;
	 * @return : String : String pour affichage à la console.<br/>
	 */
	String afficherListeChemins(List<String> pList);
	
	
	
} // FIN DE L'INTERFACE IAfficheurCollections.-------------------------------