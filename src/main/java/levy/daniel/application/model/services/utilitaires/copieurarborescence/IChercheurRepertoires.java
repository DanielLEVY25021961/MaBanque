package levy.daniel.application.model.services.utilitaires.copieurarborescence;

import java.io.File;
import java.util.List;

/**
 * INTERFACE IChercheurRepertoires :<br/>
 * Interface factorisant les comportements 
 * de tous les objets capables de lister les sous-répertoires 
 * d'un répertoire pFile.<br/>
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
 * DIAGRAMME DE SEQUENCE de la méthode <b>copierArborescence(File racineOrigine, String cheminDestination)</b> :
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
 *<br/>
 * <code>// Instanciation d'un ChercheurRepertoires</code><br/>
 * final IChercheurRepertoires chercheur = new ChercheurRepertoires();<br/>
 *<code>// Retourne la liste des sous-répertoires de 'repertoire'</code><br/>
 * final List<File> listeSousRepertoires = chercheur.chercherArborescenceSous(repertoire);<br/>
 * </code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * lister sous-repertoires, lister sous-répertoires,<br/> 
 * lister arborescence,<br/>
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
public interface IChercheurRepertoires extends IAfficheurCollections {

	
	
	/**
	 * method chercherArborescenceSous(
	 * File pFile) :<br/>
	 * Retourne la liste des sous-répertoires de pFile.<br/>
	 * Méthode récursive.<br/>
	 * <br/>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas.<br/>
	 * - Retourne null si pFile n'est pas un répertoire.<br/>
	 * - Retourne null si le pFile ne contient pas de sous-répertoires.<br/>
	 * <br/>
	 *
	 * @param pFile : File : le répertoire sous lequel 
	 * on veut connaître l'arboresence.<br/>
	 * @return : List&lt;File&gt; : liste des sous-répertoires.<br/>
	 */
	List<File> chercherArborescenceSous(File pFile);


	
	/**
	 * method chercherCheminsRepertoiresSous() :<br/>
	 * Retourne la liste des chemins des sous-répertoires de pFile.<br/>
	 * <br/>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas.<br/>
	 * - Retourne null si pFile n'est pas un répertoire.<br/>
	 * - Retourne null si le pFile ne contient pas de sous-répertoires.<br/>
	 * <br/>
	 *
	 * @param pFile : File : le répertoire sous lequel 
	 * on veut connaître l'arboresence.<br/>
	 * @param pList : List&lt;String&gt;
	 * 
	 * @return List&lt;String&gt; : liste des chemins des sous-répertoires.<br/>
	 */
	List<String> chercherCheminsRepertoiresSous(File pFile);

	
	
	/**
	 * method fournirListeCheminsSousRep(
	 * List&lt;File&gt; pListeSousRep) :<br/>
	 * Fournit la liste des chemins complets des sous-répertoires 
	 * à partir de la liste des sous-répertoires.<br/>
	 * <br/>
	 * - Retourne null si pListeSousRep est null.<br/>
	 * <br/>
	 *
	 * @param pListeSousRep : List&lt;File&gt;.<br/>
	 * @return : List&gt;String&gt; : 
	 * Liste des chemins complets des sous-répertoires.<br/>
	 */
	List<String> fournirListeCheminsSousRep(List<File> pListeSousRep);
	
	
	
} // FIN DE L'INTERFACE IChercheurRepertoires.-------------------------------