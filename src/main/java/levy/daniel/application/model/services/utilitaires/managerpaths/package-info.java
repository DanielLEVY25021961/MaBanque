/**
 * CLASSE package-info :<br/>
 * <ul>
 * <li>ManagerPaths est une Classe <b>utilitaire (méthodes static)</b> 
 * chargée de fournir
 * les caractéristiques du <b>projet eclipse courant</b> 
 * (chemin du Workspace courant, nom du projet courant
 * , path des sources java dans le projet courant, ...).</li>
 * <li>
 * classe chargée de fournir des SINGLETONS pour :
 * <ol>
 * <li><b>l'unité de disque dur</b> sur laquelle est située 
 * le projet courant <b>pathUniteCourante</b>.</li>
 * <li>l'emplacement du <b>Workspace Eclipse</b> courant 
 * <b>pathPresentWorkspace</b>.</li>
 * <li>l'emplacement du <b>projet Eclipse</b> courant 
 * <b>pathPresentProjet</b>.</li>
 * </ol>
 * </li>
 * </ul>
 *  
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE CLASSES de managerpaths :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/managerpaths/diagramme_de_classes-managerpaths-depot_concepts.png" 
 * alt="diagramme de classes de managerpaths" border="1" align="center" />
 * </div>
 * 
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>// affiche l'unité courante du présent projet Eclipse (D:/).</code><br/>
 * <b><code>String uniteCourante = ManagerPaths.getPathUniteCouranteString();</code></b><br/>
 * <code>// afiche le présent projet Eclipse (D:/Donnees/eclipse/eclipseworkspace_oxygen/depot_concepts).</code><br/>
 * <b><code>String projetCourant = ManagerPaths.getPathPresentProjetString();</code></b><br/>
 * <code>// afiche le workspace du présent projet Eclipse (D:/Donnees/eclipse/eclipseworkspace_oxygen).</code><br/>
 * <b><code>String workspaceCourant = ManagerPaths.getPathPresentWorkspaceString();</code></b><br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * séparateur de fichiers, separateur, antislash, <br/>
 * Singleton, singleton, <br/>
 * transformer String en Path, Paths.get(String), <br/>
 * connaitre unité de disque courante, <br/>
 * chemin workspace courant, chemin projet courant, <br/>
 * fournir chemin disque courant,<br/>
 * fournir chemin workspace courant, <br/>
 * fournir chemin projet courant, <br/>
 * <br/>
 * 
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 21 nov. 2018
 *
 */
package levy.daniel.application.model.services.utilitaires.managerpaths;