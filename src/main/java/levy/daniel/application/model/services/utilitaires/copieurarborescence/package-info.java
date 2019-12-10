/**
 * class package-info :<br/>
 * Package contenant toutes les classes nécessaires 
 * à l'<b>extraction et à la copie de l'arborescence contenue 
 * sous un répertoire (racine origine)</b>.<br/>
 * <ul>
 * <li>L'arborescence n'est constituée que des <b>répertoires</b> 
 * sous la racine origine, pas des fichiers simples.</li>
 * <li>La racine origine n'est pas recopiée. Seul son contenu l'est.</li>
 * <li>Ne recopie un fichier de l'arborescence que si il n'existe 
 * pas déjà sous la destination.</li>
 * </ul>
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
 * <br/>
 * <code>//  Instanciation d'un CopieurArborescence.</code><br/>
 * <b><code>final ICopieurArborescence copieur = new CopieurArborescence();</code></b><br/>
 * <br/>
 * <code>// Copie de l'arborescence sous 'repertoire' sous la nouvelle racine "D:\\Donnees\\toto"</code><br/>
 * <b><code>copieur.copierArborescence(repertoire, "D:\\Donnees\\toto");</code></b><br/>
 * <br/>
 * 
 * - Mots-clé :<br/>
 * Création de répertoire, écriture sur disque, <br/>
 * copie d'arborescence, racine, <br/>
 * copie ARBORESCENCE, recopie fichiers,<br/>
 * <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 18 nov. 2018
 *
 */
package levy.daniel.application.model.services.utilitaires.copieurarborescence;