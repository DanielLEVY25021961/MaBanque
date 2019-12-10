package levy.daniel.application.model.services.traiteursfichiers;

/**
 * INTERFACE IExportateurJTable :<br/>
 * <p>Interface qui garantit que tous les objets qui 
 * l'implémentent pourront être exportés dans des JTable.</p>
 * <ul>
 * comporte : <br/>
 * <li>une méthode <code>fournirEnTeteColonne(int pI)</code> qui fournit 
 * l'en-tête de la pI-ème colonne (0-based) de l'objet.</li>
 * <li>une méthode <code>fournirValeurColonne(int pI)</code> 
 * qui fournit la valeur 
 * de la pI-ème colonne (0-based) de l'objet.</li>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * JTable, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 17 juin 2014
 *
 */
public interface IExportateurJTable {
	

	
	/**
	* Fournit l'en-tête de la pIème colonne (0-based) 
	* pour affichage dans une JTable par exemple.<br/>
	* <br/>
	* Suit l'ordre défini dans le csv.<br/>
	*
	* @param pI : int : pIème colonne (0 - based).<br/>
	* 
	* @return : String : En-tête de la pIème colonne (0 - based).<br/>
	*/
	String fournirEnTeteColonne(int pI);
	
	
	
	/**
	* Fournit la valeur de la pIème colonne (0-based) 
	* pour affichage dans une JTable par exemple.<br/>
	* <br/>
	* Suit l'ordre défini dans le csv.<br/>
	*
	* @param pI : int : pIème colonne (0 - based).<br/>
	* 
	* @return : Object : valeur de la pIème colonne (0 - based).<br/>
	*/
	Object fournirValeurColonne(int pI);
	
	
	
} // FIN DE L'INTERFACE IExportateurJTable.----------------------------------
