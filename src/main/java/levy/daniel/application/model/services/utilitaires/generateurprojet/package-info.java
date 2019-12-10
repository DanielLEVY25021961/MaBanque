/**
 * CLASSE package-info :<br/>
 * Ce package contient toutes les classes nécessaires à la 
 * <b>génération sur disque 
 * de l'ARBORESCENCE d'un projet MAVEN simple 
 * (sans archétype)</b> dans un projet cible.<br/>
 * L'idée est de <b>générer automatiquement sur disque</b> 
 * tous les packages et classes 
 * d'<b>"infrastructure"</b> après que l'utilisateur ait créé un 
 * projet MAVEN simple (sans archétype) dans Eclipse.<br/>
 * Toutes les couches (GroupId, apptechnic, controllers, model, vues...)
 * ainsi que leurs sous-couches et les répertoires externes (data, logs, ...) 
 * sont générés dans le projet cible.<br/>
 * L'arborescence à copier dans le projet cible est fournie 
 * par un {@link ArboresceurProjetCible}.<br/>
 * <br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * Création des répértoires et packages sous un projet cible par GenerateurProjetService :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/generateurprojet/generateurprojet_resultat.png" 
 * alt="création des répertoires par GenerateurProjetService" border="1" align="center" />
 * </div>
 * <br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE CLASSES de GenerateurProjetService :
 * </span>
 * </p>
 * <br/>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/generateurprojet/generateurprojet_service.png" 
 * alt="generateurprojet SERVICE" border="1" align="center" />
 * </div>
 * <br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>
 *  // Path du projet cible.<br/>
 * projetCiblePath = Paths.get("D:/Donnees/toto");<br/>
 *  // INSTANCIATION D'UN GenerateurProjetService.<br/>
 * <b>IGenerateurProjetService generateur = new GenerateurProjetService();</b><br/>
 *  // GENERATION DE L'ARBORESCENCE DANS LE PROJET CIBLE.<br/>
 * <b>generateur.generer(projetCiblePath);</b><br/>
 * </code>
 * <br/>
 * 
 * - Mots-clé :<br/>
 * écriture fichier sur disque, ecrire sur disque, ecriture fichier, <br/>
 * ecriture répertoire sur disque, ecrire repertoire sur disque, <br/>
 * Files.createDirectories(path);<br/>
 * Création arborescence sur disque, ecriture arborescence sur disque,<br/>
 * écriture arborescence sur disque,
 * création récursive de répertoires, recursif, récursif,<br/>
 * obtenir Path à partir de String, Paths.get(String), <br/>
 * génération arborescence dans projet cible,<br/>
 * generation arborescence dans projet cible,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 26 nov. 2018
 *
 */
package levy.daniel.application.model.services.utilitaires.generateurprojet;
