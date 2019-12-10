package levy.daniel.application.model.metier;

/**
 * INTERFACE <b>IExportateurCsv</b> :<br/>
 * Interface qui garantit que tous les objets qui 
 * l'implémentent pourront être <b>exportés au format csv</b>.<br/>
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
public interface IExportateurCsv {



	/**
	 * Fournit l'<b>en-tête</b> des <b>lignes csv</b> de l'Objet métier 
	 * avec un séparateur POINT-VIRGULE ';'.<br/>
	 * <ul>
	 * <li>
	 * Pourrait être static dans l'absolu, 
	 * mais ne pourrait alors plus figurer dans l'interface.<br/> 
	 * L'idée est de pouvoir demander à n'importe quel objet métier 
	 * de fournir son en-tête de fichier csv.
	 * </li>
	 * </ul>
	 *
	 * @return : String : en-tête CSV de l'Objet métier.<br/>
	 */
	String fournirEnTeteCsv();



	/**
	 * Fournit l'Objet métier sous forme de <b>ligne Csv</b> 
	 * avec un séparateur POINT-VIRGULE ';'.<br/>
	 * <ul>
	 * <li>Java remplace automatiquement les valeurs null par 
	 * <b>"null"</b> comme dans "Robert;null" avec un nom null pour un 
	 * Objet métier [prenom;nom].</li>
	 * </ul>
	 *
	 * @return : String : l'Objet métier sous forme de ligne CSV.<br/>
	 */
	String fournirStringCsv();



} // FIN DE L'INTERFACE IExportateurCsv.-------------------------------------
