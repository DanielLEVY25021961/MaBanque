package levy.daniel.application.model.services.utilitaires.copieurarborescence;

import java.io.File;
import java.util.List;

/**
 * class ICopieurArborescence :<br/>
 * Interface factorisant les comportements des CopieurArborescence.<br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * PRINCIPE DE FONCTIONNEMENT de copieur_arborescence_maven :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/copieur_arborescence_maven/principe_fonctionnement_copieur_arborescence_maven.png" 
 * alt="principe de fonctionnement de copieur_arborescence_maven" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE CLASSES de copieur_arborescence_maven :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/copieur_arborescence_maven/diagramme_classes_copieur_arborescence_maven.png" 
 * alt="diagramme de classes de copieur_arborescence_maven" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE SEQUENCE de la méthode <b>copierArborescence(File racineOrigine, String cheminDestination) :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/copieur_arborescence_maven/methode_copierArborescence-copieur_arborescence_maven.png" 
 * alt="diagramme de séquence de copierArborescence(...)" border="1" align="center" />
 * </div>
 * 
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>//  Instanciation d'un CopieurArborescence.</code><br/>
 * <code>final ICopieurArborescence copieur = new CopieurArborescence();</code><br/>
 * <br/>
 * <code>// Copie de l'arborescence sous 'repertoire' sous la nouvelle racine "D:\\Donnees\\toto"</code><br/>
 * <code>copieur.copierArborescence(repertoire, "D:\\Donnees\\toto");</code><br/>
 * <br/>
 * 
 * - Mots-clé :<br/>
 * Création de répertoire, écriture sur disque, <br/>
 * copie d'arborescence, racine, <br/>
 * copie ARBORESCENCE, recopie fichiers,<br/>
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
public interface ICopieurArborescence {
	
	
	
	/**
	 * method copierArborescence(
	 * File pRepertoire
	 * , String pRacineDestination) :<br/>
	 * <ul>
	 * <li><b>Copie l'arborescence située <i>sous le 
	 * répertoire pRepertoire</i> 
	 * sous la racine destination pRacineDestination</b>.</li>
	 * <li>ne copie pas pRepertoire lui même.</li>
	 * <li>La copie d'un sous-répertoire de pRepertoire n'a lieu 
	 * que si il n'existe pas déjà dans l'arborescence 
	 * sous pRacineDestination.</li>
	 * <br/>
	 * - Ne fait rien si pRepertoire est null.<br/>
	 * - Ne fait rien si pRepertoire n'existe pas.<br/>
	 * - Ne fait rien si pRepertoire n'est pas un répertoire.<br/>
	 * - Ne fait rien si la liste des sous-répertoire de pRepertoire est null.<br/>
	 * - Ne fait rien si la liste des sous-répertoire de pRepertoire est vide.<br/>
	 * - Ne fait rien si pRacineDestination est null.<br/>
	 * - Ne fait rien si la racine destination ne peut être créée.<br/>
	 * <br/>
	 *
	 * @param pRepertoire : File : le répertoire dont on veut extraire 
	 * l'arborescence en dessous pour la copier sous pRacineDestination.<br/>
	 * 
	 * @param pRacineDestination : String : Le chemin du répertoire 
	 * destination dans lequel on va recopier 
	 * l'arborescence sous pRepertoire.<br/>
	 */
	void copierArborescence(
			File pRepertoire
				, String pRacineDestination);
	
	
	
	/**
	 * method creerRepertoire(
	 * String pChemin) :<br/>
	 * Crée un répertoire à pChemin.<br/>
	 * - Ne crée un nouveau répertoire que si il n'est 
	 * pas déjà existant à pChemin.<br/>
	 * <br/>
	 * - Ne fait rien si pChemin est null.<br/>
	 * <br/>
	 *
	 * @param pChemin : String : Chemin absolu 
	 * du répertoire que l'on veut créer.<br/>
	 */
	void creerRepertoire(String pChemin);
	

	
	/**
	 * method creerRepertoires(
	 * List&lt;String&gt; pChemins) :<br/>
	 * Crée la liste des répertoires (arborescence) 
	 * indiquée par la liste des chemins pChemins.<br/>
	 * - Ne crée un nouveau répertoire que si il n'est pas déjà existant.<br/>
	 * <br/>
	 * - Ne fait rien si pChemins est null.<br/>
	 * - Ne fait rien si pChemins est vide.<br/>
	 * <br/>
	 *
	 * @param pChemins : List&lt;String&gt; : liste contenant les chemins 
	 * des sous-répertoires à créer.<br/>
	 */
	void creerRepertoires(List<String> pChemins);
	
	
	
} // FIN DE LA CLASSE ICopieurArborescence.----------------------------------
