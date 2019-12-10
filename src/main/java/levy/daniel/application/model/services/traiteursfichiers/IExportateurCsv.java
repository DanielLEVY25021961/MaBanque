package levy.daniel.application.model.services.traiteursfichiers;

/**
 * INTERFACE IExportateurCsv :<br/>
 * <p>Interface qui garantit que tous les objets qui 
 * l'implémentent pourront être exportés au format csv 
 * afin d'être affichés dans n'importe quel tableur (Excel, Calc, ...).</p>
 * <ul>
 * comporte : <br/>
 * <li>une méthode fournirEnTeteCsv() qui fournit 
 * la ligne d'en-tête csv de l'objet.</li>
 * <li>une méthode fournirStringCsv() qui fournit la ligne des valeurs  
 * de l'objet au format csv.</li><br/>
 * </ul>
 * <br/>
 * 
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * csv,<br/>
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
public interface IExportateurCsv {
	

	
	/**
	 * Fournit l'en-tête des lignes csv.<br/>
	 * <br/>
	 * Pourrait être static dans l'absolu, 
	 * mais ne pourrait alors plus figurer dans l'interface. 
	 * L'idée est de pouvoir demander à n'importe quel objet métier 
	 * de fournir son en-tête de fichier csv.<br/>
	 *
	 * @return : String : en-tête de l'objet en csv.<br/>
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
