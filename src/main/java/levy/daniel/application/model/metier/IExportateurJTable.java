package levy.daniel.application.model.metier;

/**
 * INTERFACE <b>IExportateurJTable</b> :<br/>
 * Interface qui garantit que tous les objets qui 
 * l'implémentent pourront être exportés dans des <b>JTable 
 * (Swing)</b>.<br/>
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
 * @since 17 juin 2014
 *
 */
public interface IExportateurJTable {


	
	/**
	* Fournit l'<b>en-tête de la pIème colonne (0-based) 
	* pour affichage dans une JTable</b> par exemple.<br/>
	* <ul>
	* <li>Suit l'ordre défini dans le csv.</li>
	* <li>retourne "invalide" si pI est hors indexes.</li>
	* </ul>
	*
	* @param pI : int : pIème colonne (0 - based).<br/>
	* 
	* @return : String : En-tête de la pIème colonne (0 - based).<br/>
	*/
	String fournirEnTeteColonne(int pI);



	/**
	* Fournit la <b>valeur de la pIème colonne (0-based) 
	* pour affichage dans une JTable</b> par exemple.<br/>
	* <ul>
	* <li>Suit l'ordre défini dans le csv.</li>
	* <li>Retourne toujours une <b>String</b> si l'objet 
	* n'est pas null.</li>
	* <li>Retourne null si la valeur dans l'objet est null.</li>
	* <li>retourne "invalide" si pI est hors indexes.</li>
	* </ul>
	*
	* @param pI : int : pIème colonne (0 - based).<br/>
	* 
	* @return : Object : valeur de la pIème colonne (0 - based).<br/>
	*/
	Object fournirValeurColonne(int pI);



} // FIN DE L'INTERFACE IExportateurJTable.----------------------------------
