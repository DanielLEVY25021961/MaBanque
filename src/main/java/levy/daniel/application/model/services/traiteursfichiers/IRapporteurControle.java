package levy.daniel.application.model.services.traiteursfichiers;

import java.util.List;

import levy.daniel.application.model.services.traiteursfichiers.rapportscontroles.LigneRapport;




/**
 * INTERFACE IRapporteurControle :<br/>
 * <p>Interface servant à isoler les méthodes 
 * liées aux <b>rapports</b> émis par les <b>Contrôles</b>.</p>
 * <p>Un rapport de Contrôle de fichier
 * est une List&lt;LigneRapport&gt;.</p>
 * <p>Un rapport de contrôle doit pouvoir 
 * être affiché sous forme csv ou dans une JTable.</p>
 * <ul>
 * comporte essentiellement : <br/>
 * <li>une méthode <code>afficherRapportTextuel(
 * List&lt;LigneRapport&gt; pList)</code> 
 * qui permet d'afficher un rapport de contrôle 
 * sous forme de String.</li>
 * <li>une méthode <code>fournirEnTeteCsv()</code> qui fournit l'en-tête  
 * d'un rapport de contrôle au format csv.</li>
 * <li>une méthode <code>afficherRapportCsv(
 * List&lt;LigneRapport&gt; pList
 * , boolean pAjouterEntete)</code> qui fournit 
 * un rapport de contrôle au format csv.</li>
 * <li>une méthode <code>fournirEnTeteRapportJTable(int pI)</code> 
 * qui fournit l'en-tête de la pI-ème colonne (0 - based) 
 * du rapport de contrôle sous forme de String 
 * pour affichage dans une JTable.</li>
 * <li>une méthode <code>fournirValeurRapportJTable(int pLigne, int pColonne)</code> 
 * qui fournit la valeur d'un rapport de contrôle sous forme d'Object 
 * pour affichage dans une JTable.</li>
 * <li>une méthode <code>getRapport()</code> qui fournit 
 * un rapport de contrôle sous forme de 
 * List&lt;LigneRapport&gt;.</li>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * csv, JTable,<br/> 
 * rapport sur l'exécution d'un contrôle de fichier,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * levy.daniel.application.IExportateurCsv 
 * (sous-entendu, pas d'implémentation directe).<br/>
 * levy.daniel.application.IExportateurJTable 
 * (sous-entendu, pas d'implémentation directe).<br/>
 * levy.daniel.application.IResetable 
 * (sous-entendu, pas d'implémentation directe).<br/>
 * levy.daniel.application.metier.controles.rapportscontroles.LigneRapport.<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 29 févr. 2016
 *
 */
public interface IRapporteurControle {
	

	
	/**
	 * fournit une String pour l'Affichage au format textuel 
	 * de <code>this.rapport</code>.<br/>
	 * <br/>
	 * - retourne null si <code>this.rapport</code> == null.<br/>
	 * <br/>
	 *
	 * @return : String : String pour affichage de <code>this.rapport</code> 
	 * au format textuel.<br/>
	 */
	String afficherRapportTextuel();
	

	
	/**
	 * method afficherRapportTextuel(
	 * List&lt;LigneRapport&gt; pList) :<br/>
	 * Affichage dans la console d'une List&lt;LigneRapport&gt; 
	 * au format textuel.<br/>
	 * <br/>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;LigneRapport&gt;.<br/>
	 * 
	 * @return : String : String pour affichage au format textuel.<br/>
	 */
	String afficherRapportTextuel(List<LigneRapport> pList);
	
	

	/**
	 * retourne : <br/>
	 * "id;ordre d'execution du contrôle;date du contrôle;utilisateur;Fichier;
	 * type de contrôle;Contrôle;Critère;Gravité;
	 * Numéro de Ligne;Message du Contrôle;Ordre du Champ;Position du Champ;
	 * Valeur du Champ;Action;"<br/>
	 * <br/>
	 * @return String : en-tête du rapport csv.<br/>
	 */
	String fournirEnTeteCsv();
	
	

	/**
	 * Affichage au format csv de <code>this.rapport</code>.<br/>
	 * <br/>
	 * - n'ajoute pas l'en-tête du rapport csv.<br/>
	 * - retourne null si <code>this.rapport</code> == null.<br/>
	 * <br/>
	 *
	 * @return : String : String pour affichage de <code>this.rapport</code> 
	 * au format csv.<br/>
	 */
	String afficherRapportCsv();
	
	

	/**
	 * method afficherRapportCsvAvecEntete() :<br/>
	 * Affichage au format csv de <code>this.rapport</code>.<br/>
	 * <br/>
	 * - Ajoute l'en-tête du rapport csv.<br/>
	 * - retourne null si <code>this.rapport</code> == null.<br/>
	 * <br/>
	 *
	 * @return : String : String pour affichage de <code>this.rapport</code> 
	 * au format csv.<br/>
	 */
	String afficherRapportCsvAvecEntete();
	
	

	/**
	 * method afficherRapportCsv(
	 * List&lt;LigneRapport&gt; pList
	 * , boolean pAjouterEntete) :<br/>
	 * Affichage dans la console d'une List&lt;LigneRapport&gt; 
	 * au format csv.<br/>
	 * <br/>
	 * - ajoute l'en-tête du rapport csv si pAjouterEntete vaut true.<br/>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;LigneRapport&gt;.<br/>
	 * @param pAjouterEntete 
	 * 
	 * @return : String : String pour affichage au format csv.<br/>
	 */
	String afficherRapportCsv(
			List<LigneRapport> pList
				, boolean pAjouterEntete);
	
	
	
	/**
	 * method getEnTeteRapportJTable() :<br/>
	 * Fournit l'en-tête de la pI-ème colonne 0-based 
	 * pour l'affichage d'un rapport dans une JTable.<br/>
	 * <br/>
	 *
	 * @param pI : int : colonne 0-based.<br/>
	 * 
	 * @return : String : En-tête de la pI-ème 
	 * colonne 0-based d'une JTable.<br/>
	 */
	String fournirEnTeteRapportJTable(int pI);
	

	
	/**
	 * method getValeurRapportJTable(
	 * int pLigne
	 * , int pColonne) :<br/>
	 * Fournit la valeur de la cellule située 
	 * à la pLigne-ème ligne 0-based 
	 * et à la pColonne-ème colonne 0-based 
	 * pour l'affichage d'un rapport dans une JTable.<br/>
	 * <br/>
	 * - retourne null si <code>this.rapport</code> == null.<br/>
	 * <br/>
	 *
	 * @param pLigne : int : ligne 0-based.<br/>
	 * @param pColonne : int : colonne 0-based.<br/>
	 * 
	 * @return : Object : Valeur du rapport dans la cellule.<br/>
	 */
	Object fournirValeurRapportJTable(int pLigne, int pColonne);
	


	/**
	 * method getRapport() :<br/>
	 * Getter du rapport fourni par le contrôle sous forme 
	 * de List&lt;LigneRapport&gt;.<br/>
	 * <br/>
	 *
	 * @return rapport : List&lt;LigneRapport&gt;.<br/>
	 */
	List<LigneRapport> getRapport();
	

	

} // FIN DE INTERFACE IRapporteurControle.-----------------------------------
