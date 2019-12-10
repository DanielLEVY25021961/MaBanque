/**
 * class package-info :<br/>
 * Ce package contient toutes les classes utiles pour la lecture 
 * des <b>descriptions de fichier</b> de l'application.<br/>
 * Les descriptions des fichiers utilisées dans la présente application 
 * sont toutes au <b>format CSV</b> et <b>encodées en UTF-8 avec BOM</b> 
 * pour être facilement lisibles dans Microsoft Excel.
 * <p>
 * Les descriptions de fichiers sont des <b>ressources internes au classpath
 * (fichiers csv le plus souvent) ne devant pas être modifiées</b>.<br/> 
 * Elles permettent à l'application de lire les fichiers de données 
 * téléversés par les gestionnaires de données.
 * </p>
 * <p>
 * Par exemple, la description du fichier HIT stipule que :
 * <ul>
 * <li>les 3 premiers caractères d'une ligne d'un HIT coorespondent 
 * au département cadré à gauche (ardèche = 070, Rhône = 690).</li>
 * <li>les 6 caractères suivants correspondent au numéro de section.</li>
 * <li>...</li>
 * </ul>
 * </p>
 * <p>
 * <b>Les descriptions de fichiers doivent être intégrées 
 * au livrable (jar ou war)</b>.<br/>
 * Elles doivent donc impérativement se trouver sous le répertoire resources
 * dans le classpath.
 * </p>
 * <p>
 * Les descriptions des fichiers étant des <b>ressources internes
 * intégrées dans le classpath</b>, elle doivent être accédées 
 * en <i>mode RESSOURCES</i> 
 * (et pas en mode FILE) puisque leur localisation finale (contexte) dépendra 
 * de l'installation du livrable (jar ou war) par le centre-serveur.<br/>
 * <br/>
 * <p>
 * <img src="../../../../../../../../../javadoc/images/apptechnic/configurationmanagers/gestionnairesdescriptions/positionnement_ressources_descriptions_fichiers.png" 
 * alt="Positionnement des descriptions de fichiers" />
 * </p>
 * <br/>
 *
 *<p>
 * - Exemple d'utilisation :<br/>
 * </p>
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
 * @since 26 août 2017
 *
 */
package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesdescriptions;