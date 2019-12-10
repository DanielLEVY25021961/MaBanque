/**
 * CLASSE package-info :<br/>
 * Ce package contient toutes les classes <b>CONTROLLERS</b> 
 * de l'application respectant le modèle MVC.<br/>
 * La couche CONTROLLERS intercepte les appels provenant des VUES 
 * et dialogue avec les SERVICES qui sont le point d'entrée 
 * de la logique métier.<br/>
 * Le rôle d'un Controller est d'<b>aiguiller</b> les appels 
 * provenant des vues vers les bonnes parties de la logique métier.<br/>
 * <br/>
 * 
 * <div>
 * <img src="../../../../../../../javadoc/images/architecture/architecture_n_tiers.png" 
 * alt="architecure n-tiers" border="1" align="center" />
 * </div>
 * <br/>
 * 
 * <p>
 * <b><span style="text-decoration: underline;">
 * COUCHE CONTROLLERS :
 * </span></b>
 * </p>
 * <div>
 * <p>
 * La couche controllers est <b>sensible à la technologie (desktop ou web)</b>
 * </p>
 * <p>On peut avoir des Servlets, Controllers annotés ... en mode web MVC, 
 * et des Observable en mode standalone (desktop).</p>
 * <p>On a donc deux sous-couches : 
 * <ul>
 * <li><b>desktop</b></li>
 * <li><b>web</b></li>
 * </ul>
 * </p>
 * </div>
 * 
 * <div>
 * <img src="../../../../../../../javadoc/images/arboresceurprojet/couche_controllers.png" 
 * alt="couche CONTROLLERS" border="1" align="center" />
 * </div>
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
 * @since 25 oct. 2019
 *
 */
package levy.daniel.application.controllers;