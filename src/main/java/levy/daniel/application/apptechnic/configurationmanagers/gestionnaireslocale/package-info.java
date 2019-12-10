/**
 * CLASSE package-info :<br/>
 * Ce package contient toutes les classes nécessaires à la gestion 
 * de l'<b>internationalisation de d'application</b>.<br/>
 * <ul>
 * <li>En principe, une application internationalisée met à disposition 
 * de l'utilisateur une liste des Locales disponibles.<br/>
 * Lorsque l'utilisateur a choisi une Locale dans la liste, 
 * toute l'application doit s'afficher dans la langue voule.</li>
 * </ul>
 * <br/>
 * <img src="../../../../../../../../../javadoc/images/apptechnic/LocaleManager.png" 
 * alt="LocaleManager" border="1" align="center" />
 * <br/
 * <br/><br/>
 * <img src="../../../../../../../../../javadoc/images/apptechnic/LocaleManager_Utilisation.png" 
 * alt="Utilisation du LocaleManager" border="1" align="center" />
 * <br/><br/>
 * <br/>
 *
 * <p>
 * - Exemples d'utilisation :
 * </p>
 * <code><i>// Récupération de la Locale américaine depuis l'IHM.</i></code><br/>
 * <b><code>final Locale localeEu = 
 * LocaleManager.recupererLocaleIHM("anglais (Etats-Unis)");</code></b><br/>
 * <br/>
 * <code><i>// SELECTION de la Locale américaine depuis l'IHM.</i></code><br/>
 * <b><code>LocaleManager.selectionnerLocaleApplication("anglais (Etats-Unis)");
 * </code></b><br/>
 * <code><i>// Récupération du SINGLETON de Locale en cours 
 * depuis n'importe où dans l'application.</i></code><br/>
 * <code>final Locale localeApplication 
 * = <b>LocaleManager.getLocaleApplication()</b>;</code><br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Locale, Locale("fr", "FR") en France, <br/>
 * instancier une Locale, SINGLETON, singleton,<br/>
 * sélectionner une Locale, selectionner une locale,<br/>
 * afficherListString, afficherList<String>, afficher Liste String, <br/>
 * afficherListeString, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 23 juil. 2018
 *
 */
package levy.daniel.application.apptechnic.configurationmanagers.gestionnaireslocale;