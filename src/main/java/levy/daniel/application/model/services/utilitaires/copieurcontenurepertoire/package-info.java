/**
 * CLASSE package-info :<br/>
 * Package contenant toutes les classes nécessaires 
 * à l'<b>extraction et à la copie 
 * de l'arborescence et des fichiers simples contenus 
 * sous un répertoire (racine origine)</b>.<br/>
 * <ul>
 * <li>Le contenu recopié contient l'arborescence (<b>répertoires</b>) 
 * <i>sous</i> la racine origine <b>et les fichiers simples</b>.</li>
 * <li><b>L'ensemble du contenu (répertoires et fichiers simples) 
 * sous le répertoire origine 
 * est recopié sous le répertoire destination en respectant l'arborescence</b>.</li>
 * <li>La racine origine n'est pas recopiée. Seul son contenu l'est.</li>
 * <li>Ne recopie un fichier de l'arborescence que si il n'existe 
 * pas déjà sous la destination.</li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * PRINCIPE DE FONCTIONNEMENT de copieurContenuRepertoire :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/copieurcontenurepertoire/principe_fonctionnement_copieurContenuRepertoire.png" 
 * alt="principe de fonctionnement de copieurContenuRepertoire" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE CLASSES de copieurcontenurepertoire :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/copieurcontenurepertoire/diagramme_classes_copieurContenuRepertoire.png" 
 * alt="diagramme de classes de copieurContenuRepertoire" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE SEQUENCE de la méthode <b>copierContenu(File racineOrigine, Path cheminDestination)</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/copieurcontenurepertoire/methode_copieurContenuRepertoire.png" 
 * alt="diagramme de séquence de copierContenu(...)" border="1" align="center" />
 * </div>
 * 
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>
 *  // Détermination de la racine ORIGINE dont on veut recopier tout le contenu (javadoc).<br/>
 * <b>File repRacineACopier = new File("D:/Donnees/eclipse/eclipseworkspace_neon/generation_code/javadoc");</b><br/>
 *  // Détermination de la racine DESTINATION (javadoc dans un autre projet).<br/>
 * <b>Path pathRepDestinationPath =  Paths.get("D:/Donnees/eclipse/eclipseworkspace/test_generation/javadoc");</b><br/>
 *  // Instanciation d'un SERVICE CopieurContenuRepertoireService<br/>
 * <b>ICopieurContenuRepertoireService copieur = new CopieurContenuRepertoireService();</b><br/>
 *  // EXECUTION DE LA COPIE.<br/>
 * <b>copieur.copierContenu(repRacineACopier, pathRepDestinationPath);</b><br/>
 * </code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * afficherListPaths, afficher liste de Paths, afficher list<Path>, <br/>
 * afficher list paths, afficher liste paths, <br/>
 * afficherMapPathsBoolean, afficher map de Paths, Boolean
 * , afficher Map<Path, Boolean>, <br/>
 * afficher list paths, afficher liste paths, <br/>
 * afficher map paths, afficher Map paths, <br/>
 * trier map sur key, trier Map sur Keys, <br/>
 * retourner contenu sous répertoire, <br/> 
 * retourner contenu sous repertoire,<br/>
 * path relatif, <br/>
 * path relatif de path enfant par rapport à path parent,<br/>
 * fournir Path RELATIF, <br/>
 * Path absolu du projet Eclipse courant, path courant, <br/>
 * PATH COURANT, Path Courant, <br/>
 * resolve, pathProjetCourant.resolve(pathRelatif), <br/>
 * ajouter un path, <br/>
 * pathParent.relativize(pathEnfant), <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 1 déc. 2018
 *
 */
package levy.daniel.application.model.services.utilitaires.copieurcontenurepertoire;
