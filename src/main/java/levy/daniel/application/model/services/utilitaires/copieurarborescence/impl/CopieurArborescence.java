package levy.daniel.application.model.services.utilitaires.copieurarborescence.impl;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.utilitaires.copieurarborescence.AbstractCopieurArborescence;

/**
 * class CopieurArborescence :<br/>
 * Copieur d'arborescence concret.<br/>
 * Possède une méthode copierArborescence(
 * File pRepertoire, String pDestination) 
 * qui copie l'arborescence sous pRepertoire dans pDestination.<br/>
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
 * <code>//  Instanciation d'un CopieurArborescence.</code><br/>
 * <code>final ICopieurArborescence copieur = new CopieurArborescence();</code><br/>
 * <br/>
 * <code>// Copie de l'arborescence sous 'repertoire' 
 * sous la nouvelle racine destination 
 * "D:\\Donnees\\racine-copie_arborescence"</code><br/>
 * <code>copieur.copierArborescence(
 * repertoire, "D:\\Donnees\\racine-copie_arborescence");</code><br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Création de répertoire, écriture sur disque, <br/>
 * copie d'arborescence, racine, root, <br/>
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
public class CopieurArborescence extends AbstractCopieurArborescence {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(CopieurArborescence.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR CopieurArborescence() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public CopieurArborescence() {
		
		super();
		
		/* Instancie le ChercheurRepertoires. */
		this.chercheur = new ChercheurRepertoires();
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
} // FIN DE LA CLASSE CopieurArborescence.-----------------------------------
