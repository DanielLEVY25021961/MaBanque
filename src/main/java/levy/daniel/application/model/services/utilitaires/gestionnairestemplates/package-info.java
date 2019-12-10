/**
 * CLASSE package-info :<br/>
 * Ce package contient tous les SERVICES chargés 
 * de la <b>gestion des templates</b>.<br/>
 * <br/>
 * Les <b>templates</b> sont des <b>bouts de code "tout faits"</b> 
 * comportant éventuellement des <b>variables</b>.<br/>
 * L'objectif est d'injecter ces bouts de code "tout faits" 
 * avec leurs éventuelles variables <i>substituées</i> 
 * dans le code que l'on développe.<br/>
 * <br/>
 * Les templates sont stockés sous forme de <b>fichiers .txt</b> 
 * dans les <i>ressources internes (sous le classpath)</i> de l'application.<br/>
 * 
 * <p>
 * <span style="text-decoration: underline;"><b>
 * CREATION et UTILISATION des TEMPLATES par le développeur</b></span>
 * </p>
 * <img src="../../../../../../../../../../javadoc/images/gestionnairestemplates/gestion_templates.png" 
 * alt="Gestion des templates" border="1" align="center" />
 * <br/><br/>
 * <p><span style="text-decoration: underline;"><b>ROLE des GestionnaireTemplatesService</b></span></p>
 * Un gestionnaireTemplateService est responsable de :
 * <ul>
 * <li><b>lire un template de code</b> stocké sous forme de fichier .txt sous le classpath.</li>
 * <li><b>substituer d'éventuelles variables</b> par des valeurs dans le template lu.</li>
 * <li><b>retourner le résultat du template lu et substitué</b> sous forme de <b>List&lt;String&gt;</b> ou de <b>String</b> unique.</li>
 * </ul>
 * Le développeur peut alors injecter le code résultant de la lecture et de la substitution du template où il le souhaite dans son code.<br/>
 * <br/><br/>
 * <img src="../../../../../../../../../../javadoc/images/gestionnairestemplates/gestionnaire_templates_role.png" 
 * alt="Roles des Gestionnaire de templates" border="1" align="center" />
 * <br/><br/>
 * <p><span style="text-decoration: underline;"><b>DIAGRAMME DES CLASSES : </b></span></p>
 * <br/>
 * <img src="../../../../../../../../../../javadoc/images/gestionnairestemplates/gestionnaire_templates_SERVICE.png" 
 * alt="Diagramme de classes des Gestionnaires de templates SERVICE" border="1" align="center" />
 * <br/><br/>
 * <br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code><i>// chemin relatif du template par rapport aux ressources 
 * internes src/main/resources.</i></code><br/>
 * <b><code>final String cheminRelatifTemplate 
 * = "commentaires_properties/commentaires_labels_properties.txt"
 * ;</code></b><br/>
 * <code><i>// variable à substituer dans le template.</i></code><br/>
 * <b><code>final String variable = "{$Locale}";</code></b><br/>
 * <code><i>// valeur de substitution.</i></code><br/>
 * <b><code>final String substituant = localeCouranteFR.toString();
 * </code></b><br/>
 * <br/>	
 * <code><i>// Obtention du template substitué sous forme de String 
 * dans le code java auprès du Gestionaire de Templates.</i></code><br/>
 * <b><code>final String commentaire = 
 * gestionnaireTemplate.fournirTemplateSubstitueSousFormeString(
 * cheminRelatifTemplate, variable, substituant);</code></b><br/>
 * <br/>		
 * <code><i>// injection du template substitué dans le 
 * code java.</i></code><br/>
 * <b><code>gestionnairePropertiesCompletFR.
 * remplirEnDurFichierProperties(commentaire);</code></b><br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * template, Template, <br/>
 * lire fichier, lireFichier, fichier en liste, <br/>
 * transformer liste en String, Liste en String, <br/>
 * template, Template, liste de lignes à partir d'un fichier txt, <br/>
 * lire un fichier .txt, lire un fichier txt,<br/>
 * lire fichier .txt, lire fichier txt,<br/>
 * liste lignes à partir d'un fichier .txt, ListeLignes txt, <br/>
 * lire un template txt, lire un template .txt, obtenir liste lignes,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 7 déc. 2018
 *
 */
package levy.daniel.application.model.services.utilitaires.gestionnairestemplates;