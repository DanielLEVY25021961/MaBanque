/**
 * CLASSE package-info :<br/>
 * Ce package contient toutes les classes utiles pour la  
 * des <b>gestion des ResourceBundles</b> (fichiers properties) 
 * de l'application.<br/>
 * <p>
 * Les <b>ResourceBundles sont utiles car ils sont taillés sur mesure 
 * pour retourner la valeur d'une clé dans un fichier properties 
 * et ils gèrent automatiquement l'internationalisation</b>.<br/>
 * L'internationalisation consiste à choisir le bon fichier properties 
 * (application_fr_FR.properties, application_en_US.properties, ...) 
 * en fonction d'une Locale passée en paramètres.
 * </p>
 * <p>
 * Les ResourceBundles sont la modélisation Java des <b>fichiers properties</b>
 * situés <i>sous le classpath</i>.<br/>
 * Il est néanmoins possible d'adresser des fichiers properties 
 * <i>externes au classpath</i> comme des ResourceBundle 
 * en utilisant le ClassLoader.
 * </p>
 * <p>
 * La bonne pratique est de situer sous le classpath 
 * tous les fichiers properties nécessaires à la <b>configuration 
 * de l'application</b> qui ne doivent être modifiés que 
 * par un développeur (application.properties, ...).<br/>
 * Ces <b>fichiers de configuration seront ainsi intégrés 
 * dans les livrables</b> (jar ou war).
 * </p>
 * <p>
 * <img src="../../../../../../../../../javadoc/images/apptechnic/configurationmanagers/gestionnairesbundles/positionnement_resourcebundles_internes.png" 
 * alt="Positionnement ResourceBundles internes" />
 * </p>
 * <p>
 * On peut souhaiter utiliser comme des ResourceBundles 
 * des fichiers properties "externes" 
 * placés dans des répertoires <i>hors classpath</i> 
 * pour qu'ils puissent par exemple être modifiés 
 * par une Maîtrise d'OuvrAge (MOA).<br/>
 * Il s'agit souvent de ressources <b>paramétrables</b> par la MOA.<br/>
 * ATTENTION : Ces <b>fichiers properties externes ne seront pas intégrés 
 * aux livrables</b> (jar ou war) et devront être <b>livrés à part 
 * et positionnés par le Centre-Serveur</b>.
 * </p>
 * <p>
 * On transforme ce répertoire externe (au sens de système de fichiers) 
 * en ressource (accédée via la technologie ResourceBundle) en utilisant :<br/>
 * <code> // Accès au répertoire externe en mode FILE</code><br/>
 * <code>File repertoireRessourcesParametrables = new File(pPathRepRessourcesExternes);</code><br/>
 * <code>// Récupére les URL de tous les properties contenus dans le répertoire externe</code><br/>
 * <code>URL[] urlsRessourcesParametrables = {repertoireRessourcesParametrables.toURI().toURL()};</code><br/>
 * <code> // Instancie un ClassLoader pointant sur le répertoire externe.</code><br/>
 * <code>ClassLoader loaderRessourcesParametrables = new URLClassLoader(urlsRessourcesParametrables);</code><br/>
 * <code> // Récupère le properties (ResourceBundle) externe voulu.</code><br/>
 * <code>ResourceBundle resultat = ResourceBundle.getBundle(pNomBaseProperties, locale, loaderRessourcesParametrables);</code><br/>
 * </p>
 * <p>
 * <img src="../../../../../../../../../javadoc/images/apptechnic/configurationmanagers/gestionnairesbundles/positionnement_resourcebundles_externes.png" 
 * alt="Positionnement ResourceBundles externes" />
 * </p>
 * <p>
 * <br/>
 * <img src="../../../../../../../../../javadoc/images/apptechnic/configurationmanagers/gestionnairesbundles/properties_internes_externes.png" 
 * alt="properties internes et externes" border="1" align="center" />
 * <br/>
 * </p>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 5 déc. 2018
 *
 */
package levy.daniel.application.apptechnic.configurationmanagers.gestionnairesbundles;
