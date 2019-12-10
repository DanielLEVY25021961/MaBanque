package levy.daniel.application.model.services.utilitaires.copieurarborescence.impl;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.utilitaires.copieurarborescence.AbstractChercheurRepertoires;

/**
 * CLASSE ChercheurRepertoires :<br/>
 * ChercheurRepertoires concret.<br/>
 * Possède une méthode chercherArborescenceSous(final File pFile) 
 * qui retourne la liste des sous-répertoires 
 * <i>(pas fichiers simples)</i> de pFile.<br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * PRINCIPE DE FONCTIONNEMENT de copieur_arborescence_maven :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/copieur_arborescence_maven/principe_fonctionnement_copieur_arborescence_maven.png" 
 * alt="principe de fonctionnement de copieur_arborescence_maven" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE CLASSES de copieur_arborescence_maven :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/copieur_arborescence_maven/diagramme_classes_copieur_arborescence_maven.png" 
 * alt="diagramme de classes de copieur_arborescence_maven" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE SEQUENCE de la méthode <b>copierArborescence(File racineOrigine, String cheminDestination)</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/copieur_arborescence_maven/methode_copierArborescence-copieur_arborescence_maven.png" 
 * alt="diagramme de séquence de copierArborescence(...)" border="1" align="center" />
 * </div>

 * 
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <br/>
 * <code>// Instanciation d'un ChercheurRepertoires</code><br/>
 * final IChercheurRepertoires chercheur = new ChercheurRepertoires();<br/>
 *<code>// Retourne la liste des sous-répertoires de 'repertoire'</code><br/>
 * final List&lt;File&gt; listeSousRepertoires = chercheur.chercherArborescenceSous(repertoire);<br/>
 * </code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * lister sous-repertoires, lister sous-répertoires,<br/> 
 * lister arborescence,<br/>
 * Méthode récursive, récursif, file, répertoire, sous-répertoire<br/>
 * recherche arborescence, parcours fichiers,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 7 oct. 2014
 *
 */
public class ChercheurRepertoires extends AbstractChercheurRepertoires {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(ChercheurRepertoires.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR ChercheurRepertoires() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public ChercheurRepertoires() {
		super();		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
} // FIN DE LA CLASSE ChercheurRepertoires.----------------------------------
