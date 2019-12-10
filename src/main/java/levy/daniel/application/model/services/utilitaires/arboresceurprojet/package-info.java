/**
 * CLASSE package-info :<br/>
 * Ce package contient toutes les classes nécessaires à la <b>génération 
 * de l'ARBORESCENCE d'un projet MAVEN simple (sans archétype)</b>.<br/>
 * L'idée est de <b>générer automatiquement</b> tous les packages et classes 
 * d'<b>"infrastructure"</b> après que l'utilisateur ait créé un 
 * projet MAVEN simple (sans archétype) dans Eclipse.<br/>
 * 
 * <ul>
 * <li>ArboresceurProjetCible est une 
 * Classe <b>utilitaire (méthodes static)</b> chargée de fournir 
 * l'arborescence d'<b>"infrastructure"</b> d'un projet 
 * ECLIPSE MAVEN SIMPLE (pas d'artéfact) à générer 
 * (path des sources java, ...).</li>
 * <li>
 * ArboresceurProjetCible est une classe chargée 
 * de fournir des <b>SINGLETONS</b> pour :
 * <ol>
 * <li>l'emplacement du <b>projet CIBLE Eclipse</b> 
 * dans lequel générer le code : <b>projetCiblePath</b>.</li>
 * <li>l'emplacement de la <b>racine des sources</b> 
 * <code>(src/main/java)</code> dans lequel générer les .java 
 * du main : <b>srcMainJavaPath</b>.</li>
 * <li>l'emplacement de la <b>racine des ressources</b> 
 * <code>(src/main/resources)</code> dans lequel stocker les ressources 
 * du main : <b>srcMainResourcesPath</b>.</li>
 * <li>l'emplacement de la <b>racine des sources de test</b> 
 * <code>(src/test/java)</code> dans lequel générer les .java 
 * de test : <b>srcTestJavaPath</b>.</li>
 * <li>l'emplacement de la <b>racine des ressources des tests</b> 
 * <code>(src/test/resources)</code> dans lequel stocker les ressources 
 * des tests : <b>srcTestResourcesPath</b>.</li>
 * <li>Le <b>GroupId MAVEN</b> <code>(levy/daniel/application)</code> : 
 * <b>groupIdPathRelatif</b>.</li>
 * <li>l'emplacement de la <b>racine qualifiée des sources</b> 
 * <code>(src/main/java/levy/daniel/application)</code> dans lequel générer les .java 
 * du main : <b>racineSourcesJavaPath</b>.</li>
 * <li>l'emplacement de la <b>racine qualifiée des sources de test</b> 
 * <code>(src/test/java/levy/daniel/application)</code> dans lequel générer les .java 
 * de test JUnit : <b>racineTestsJavaPath</b>.</li>
 * <li>l'emplacement de la couche <b>apptechnic</b> dans main : 
 * <b>coucheAppTechnicMainPath</b>.</li>
 * <li>l'emplacement de la couche <b>apptechnic</b> dans test :  
 * <b>coucheAppTechnicTestPath</b>.</li>
 * <li>l'emplacement de la couche <b>controllers</b> dans main :  
 * <b>coucheControllersMainPath</b>.</li>
 * <li>l'emplacement de la couche <b>controllers</b> dans test :  
 * <b>coucheControllersTestPath</b>.</li>
 * <li>l'emplacement de la couche <b>vues</b> dans main :  
 * <b>coucheVuesMainPath</b>.</li>
 * <li>l'emplacement de la couche <b>vues</b> dans test :  
 * <b>coucheVuesTestPath</b>.</li>
 * <li>l'emplacement de la couche <b>model</b> dans main :  
 * <b>coucheModelMainPath</b>.</li>
 * <li>l'emplacement de la couche <b>model</b> dans test :  
 * <b>coucheModelTestPath</b>.</li>
 * <li>l'emplacement des sous-couches de chaque couche.</li>
 * <li>l'emplacement des répertoires annexes 
 * (conception_appli, javadoc, logs, rapports_controle
 * , ressources_externes, ...)</li>
 * <li>une liste de Path <b>ARBORESCENCE_PROJET_CIBLE</b> contenant 
 * l'ensemble des répertoires à créer dans le projet cible.</li>
 * </ol>
 * </li>
 * </ul>
 * 
 * Les <b>4 package source</b> situés directement sous le projet Eclipse 
 * exigés PAR CONVENTION par MAVEN (sans artefact) sont :<br/>
 * <ol>
 * <li><b>src/main/java</b> qui doit contenir toutes les 
 * <b>sources</b> (.java).</li>
 * <li><b>src/main/resources</b> qui doit contenir toutes 
 * les <b>ressource</b>s (.properties, Log4j2.xml, ...).</li>
 * <li><b>src/test/java</b> qui doit contenir toutes les <b>sources des tests JUnit</b> (xxxTest.java).</li>
 * <li><b>src/test/resources</b> qui doit contenir toutes les <b>ressources des tests JUnit</b> (jeux d'essai).</li>
 * </ol>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/arboresceurprojet/repertoires_source_MAVEN.png" 
 * alt="repertoires_source_maven" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * le <b>GroupId</b> MAVEN définit les paths de la racine des 
 * sources Java <b>racineSourcesJavaPath</b> 
 * (srcMainJavaPath + groupIdPathRelatif) et 
 * de la racine des tests JUnit <b>racineTestsJavaPath</b> 
 * (srcTestJavaPath + groupIdPathRelatif).<br/>
 * <br/>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/arboresceurprojet/groupId_sous_src_main_java.png" 
 * alt="racine des sources et des tests" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * Les 4 <b>couches applicatives</b> d'une application 
 * respectant Model-Vues-Controllers (MVC) sont :
 * <ul>
 * <li><b>model</b> qui contient toute la logique métier 
 * ne devant dépendre d'aucune technologie.</li>
 * <li><b>vues</b> qui contient toutes les vues 
 * (Swing, Javafx, JSP, Thymeleaf, ...).</li>
 * <li><b>controllers</b> qui contient tous 
 * les controllers (Observer, Servlet, ...) interceptant les vues.</li>
 * <li><b>apptechnic</b> qui contient tout le code d'infrastructure 
 * indépendant de la logique métier (Exceptions, managers, ...).</li>
 * </ul>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/arboresceurprojet/couches_applicatives.png" 
 * alt="couches applicatives" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <b><span style="text-decoration: underline;">
 * COUCHE MODEL :
 * </span></b>
 * </p>
 * la couche model contient toute la <b>logique métier</b> de l'application.<br/>
 * la couche model est découpée en 5 sous-couches pour se conformer à 
 * une <b>architecture n-tiers</b> : <br/>
 * <ul>
 * <li><b>services</b> qui contient les <i>points d'entrée</i> (façades)
 *  vers la logique métier.<br/> 
 *  Les SERVICES sont appelés par les CONTROLLERS.<br/>
 *  les SERVICES dépendent éventuellement de l'utilisation de frameworks 
 *  (annotations pour SPRING ou JPA par exemple).</li>
 * <li><b>dto</b> qui contient les Data Transfer Object (DTO). 
 * Les DTO permettent de capturer le contenu d'une VUE.<br/> 
 * Ils sont <i>transverses</i> car autorisés à voyager 
 * dans toutes les couches.<br/>
 * Les DTO sont de purs objets Java qui ne doivent pas 
 * dépendre de la technologie utilisée (Web, standalone, ..).</li>
 * <li><b>metier</b> qui contient les OBJETS METIER 
 * aussi appelés OBJETS DU DOMAINE (Compte, User, Eleve, ...).<br/> 
 * Ils sont <i>transverses</i> car autorisés à voyager 
 * dans toutes les couches.<br/>
 * Les OBJETS METIER sont de purs objets Java qui ne doivent pas 
 * dépendre de la technologie utilisée (Web, standalone, ..).
 * </li>
 * <li><b>persistence</b> qui contient les <b>entities</b> 
 * et les Data Access Objects (<b>DAO</b>).<br/>
 * les ENTITIES dépendent des frameworks utilisés (JPA, JAXB, ...).<br/>
 * les DAO dépendent des frameworks utilisés 
 * (SPRING et JPA, JPA seul, JAXB, ...).<br/>
 * La couche PERSISTENCE est chargée de converser avec le 
 * <b>stockage des données</b>.
 * </li>
 * <li><b>utilitaires</b> qui contient les utilitaires utilisables 
 * par toute l'application.</li>
 * </ul>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/arboresceurprojet/couche_model.png" 
 * alt="couche MODEL" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">
 * sous-couche <b>dto</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/arboresceurprojet/sous-couche_DTO.png" 
 * alt="sous-couche DTO" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">
 * sous-couche <b>metier</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/arboresceurprojet/sous-couche_metier.png" 
 * alt="sous-couche METIER" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">
 * sous-couche <b>persistence</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/arboresceurprojet/sous-couche_persistence.png" 
 * alt="sous-couche PERSISTENCE" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">
 * sous-couche <b>services</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/arboresceurprojet/sous-couche_services.png" 
 * alt="sous-couche SERVICES" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">
 * sous-couche <b>utilitaires</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/arboresceurprojet/sous-couche_utilitaires.png" 
 * alt="sous-couche UTILITAIRES" border="1" align="center" />
 * </div>
 * 
 * 
 * <br/>
 * <p>
 * <b><span style="text-decoration: underline;">
 * COUCHE VUES :
 * </span></b>
 * </p>
 * <div>
 * <p>
 * La couche vue est <b>sensible à la technologie (desktop ou web)</b>
 * </p>
 * <p>On peut avoir des JSP, des Thymeleaf, ... en mode web MVC, 
 * et des Vues Swing ou Javafx en mode standalone (desktop).</p>
 * <p>On a donc deux sous-couches : 
 * <ul>
 * <li><b>desktop</b></li>
 * <li><b>web</b></li>
 * </ul>
 * </p>
 * </div>
 * 
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/arboresceurprojet/couche_vues.png" 
 * alt="couche VUES" border="1" align="center" />
 * </div>

 * 
 * <br/>
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
 * <img src="../../../../../../../../../../javadoc/images/arboresceurprojet/couche_controllers.png" 
 * alt="couche CONTROLLERS" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <b><span style="text-decoration: underline;">
 * COUCHE APPTECHNIC :
 * </span></b>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/arboresceurprojet/couche_apptechnic.png" 
 * alt="couche APPTECHNIC" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * <p>
 * <b><span style="text-decoration: underline;">
 * REPERTOIRES EXTERNES :
 * </span></b>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/arboresceurprojet/repertoires_externes.png" 
 * alt="REPERTOIRES EXTERNES" border="1" align="center" />
 * </div>
 * 
 * <br/>
 * 
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>
 * // Path du projet cible<br/>
 * <b>Path projetCiblePath = Paths.get("D:/Donnees/toto");</b><br/>
 * // SELECTION DU PROJET CIBLE<br/>
 * <b>ArboresceurProjetCible.selectionnerProjetCible(projetCiblePath);</b><br/>
 *  // (Optionnel) sélection du GroupId<br/>
 * ArboresceurProjetCible.setGroupIdPathRelatif("newGroupId") <br/>
 *  // RECUPERATION DE L'ARBORESCENCE A CREER DANS LE PROJET CIBLE<br/>
 * <b>List&lt;Path&gt; arborescence = ArboresceurProjetCible.getArborescenceProjetCible();</b><br/>
 * </code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * path.resolve(path), path.resolve, resolve, resolve(),<br/>
 * ajouter un path à un autre, <br/>
 * fournir arborescence projet cible, <br/>
 * génération de projet, génération d'arborescence, <br/>
 * generation de projet, generation d'arborescence, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 22 nov. 2018
 *
 */
package levy.daniel.application.model.services.utilitaires.arboresceurprojet;