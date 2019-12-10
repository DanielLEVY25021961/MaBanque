/**
 * CLASSE package-info :<br/>
 * ce package contient tous les utilitaires 
 * pour la <b>gestion des URLS de Base de Données</b> en Java.<br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">DIAGRAMME DE CLASSES D'IMPLEMENTATION</span>
 * </p>
 * <ul>
 * <li>
 * <img src="../../../../../../../../../javadoc/images/model/utilitaires/normalizerurlbase/diagramme_classes_NormalizerUrlBase.png" 
 * alt="classes d'implémentation NormalizerUrlBase" border="1" align="center" />
 * </li>
 * </ul>
 * 
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>
 *  // NOM DE LA BD <br/>
 * <b>String BD = "base-adresses_javafx";</b><br/>
 *  // URL fournie avec <i>un chemin relatif</i> <br/>
 * <b>String URL_FILE1 = "jdbc:hsqldb:file:./data/base-adresses_javafx-h2/base-adresses_javafx;DB_CLOSE_ON_EXIT=FALSE;IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;";</b><br/>
 *  // RECUPERATION D'UNE UrlEncapsulation auprès du NormalizerUrlBase<br/>
 * <b>UrlEncapsulation encapsulation = NormalizerUrlBase.creerUrlEncapsulation(URL_FILE1, BD);</b><br/>
 *  // RECUPERATION DE L'URL NORMALISEE AUPRES DE L'ENCAPSULATION<br/>
 * <b>String urlNormalisee = encapsulation.getUrlNormalisee()</b> retourne : <br/>
 * <i>jdbc:hsqldb:file:D:\Donnees\eclipse\eclipseworkspace\adresses_javafx\data\base-adresses_javafx-h2\base-adresses_javafx;DB_CLOSE_ON_EXIT=FALSE;IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;</i><br/>
 * </code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Pattern, Matcher, Expressions régulières, Regex, regex,<br/>
 * expression reguliere, <br/>
 * encapsulation, pure fabrication, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 23 janv. 2019
 *
 */
package levy.daniel.application.model.utilitaires.normalizerurlbase;