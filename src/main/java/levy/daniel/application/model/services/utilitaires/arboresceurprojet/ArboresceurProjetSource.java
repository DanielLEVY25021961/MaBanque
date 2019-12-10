package levy.daniel.application.model.services.utilitaires.arboresceurprojet;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE ArboresceurProjetSource :<br/>
 * <ul>
 * <li>Classe <b>utilitaire (méthodes static)</b> chargée de fournir 
 * l'arborescence d'<b>"infrastructure"</b> d'un projet 
 * ECLIPSE MAVEN SIMPLE (pas d'artéfact).</li>
 * <li>
 * classe chargée de fournir des <b>SINGLETONS</b> pour :
 * <ol>
 * <li>l'emplacement du <b>projet SOURCE Eclipse</b> 
 * dans lequel prendre le code à trouver : <b>projetSourcePath</b>.</li>
 * <li>l'emplacement de la <b>racine des sources</b> 
 * <code>(src/main/java)</code> dans lequel trouver les .java 
 * du main : <b>srcMainJavaPath</b>.</li>
 * <li>l'emplacement de la <b>racine des ressources</b> 
 * <code>(src/main/resources)</code> dans lequel trouver les ressources 
 * du main : <b>srcMainResourcesPath</b>.</li>
 * <li>l'emplacement de la <b>racine des sources de test</b> 
 * <code>(src/test/java)</code> dans lequel trouver les .java 
 * de test : <b>srcTestJavaPath</b>.</li>
 * <li>l'emplacement de la <b>racine des ressources des tests</b> 
 * <code>(src/test/resources)</code> dans lequel trouver les ressources 
 * des tests : <b>srcTestResourcesPath</b>.</li>
 * <li>Le <b>GroupId MAVEN</b> <code>(levy/daniel/application)</code> : 
 * <b>groupIdPathRelatif</b>.</li>
 * <li>l'emplacement de la <b>racine qualifiée des sources</b> 
 * <code>(src/main/java/levy/daniel/application)</code> dans lequel trouver les .java 
 * du main : <b>racineSourcesJavaPath</b>.</li>
 * <li>l'emplacement de la <b>racine qualifiée des sources de test</b> 
 * <code>(src/test/java/levy/daniel/application)</code> dans lequel trouver les .java 
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
 * <li>une liste de Path <b>ARBORESCENCE_PROJET_SOURCE</b> contenant 
 * l'ensemble des répertoires à trouver dans le projet source.</li>
 * <li>une map de [String,Path] <b>ARBORESCENCE_MAIN_PROJET_SOURCE_MAP</b> 
 * contenant l'ensemble des répertoires à créer dans le <b>main</b> 
 * du projet source.</li>
 * <li>une map de [String,Path] <b>ARBORESCENCE_TEST_PROJET_SOURCE_MAP</b> 
 * contenant l'ensemble des répertoires à trouver dans le <b>test</b> 
 * du projet source.</li>
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
 * // Path du projet source<br/>
 * <b>Path projetSourcePath = Paths.get("D:/Donnees/toto");</b><br/>
 *  // (Optionnel) sélection du GroupId<br/>
 * ArboresceurProjetSource.setGroupIdPathRelatif("newGroupId") <br/>
 * // SELECTION DU PROJET SOURCE<br/>
 * <b>ArboresceurProjetSource.selectionnerProjetSource(projetSourcePath);</b><br/>
 *  // RECUPERATION DE L'ARBORESCENCE A CREER DANS LE PROJET SOURCE<br/>
 * <b>List&lt;Path&gt; arborescence = ArboresceurProjetSource.getArborescenceProjetSource();</b><br/>
 *   // RECUPERATION DE L'ARBORESCENCE A CREER DANS LE MAIN du PROJET SOURCE<br/>
 * <b>Map&lt;String, Path&gt; arborescenceMainMap = ArboresceurProjetSource.getArborescenceMainProjetSourceMap();</b><br/>
 *   // RECUPERATION DE L'ARBORESCENCE A CREER DANS LE TEST du PROJET SOURCE<br/>
 * <b>Map&lt;String, Path&gt; arborescenceTestMap = ArboresceurProjetSource.getArborescenceTestProjetSourceMap();</b><br/>
 *   // RECUPERATION DE L'ARBORESCENCE DES REPERTOIRES EXTERNES à créer PROJET SOURCE<br/>
 * <b>Map&lt;String, Path&gt; arborescenceRepExtMap = ArboresceurProjetSource.getArborescenceRepertoiresExternesProjetSourceMap();</b><br/>
 * </code><br/>
 * <br/>
 * Exemple avec changement de groupId :<br/>
 * <code>
 *  // CHANGEMENT DU groupId<br/>
 * <b>ArboresceurProjetSource.setGroupId("fr.orsys.demo");</b><br/>
 * // SELECTION DU PROJET CIBLE<br/>
 * <b>ArboresceurProjetSource.selectionnerProjetSource(projetSourcePath);</b><br/> 
 * </code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * path.resolve(path), path.resolve, resolve, resolve(),<br/>
 * ajouter un path à un autre, <br/>
 * fournir arborescence projet source, <br/>
 * Collections.sort(list, new EntryStringComparator());<br/>
 * map triée, Map triée, Map triee, SortedMap, TreeMap, <br/>
 * Comparator, comparator, <br/>
 * trier Entry, trier Entry<String, Path> sur String, <br/>
 * trier entry sur String, trier Map sur clé,<br/>
 * trier Map sur key, trier Map sur Key, trier map sur key,<br/>
 * String.format(...), String.format,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 23 nov. 2018
 *
 */
public final class ArboresceurProjetSource {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "metier".
	 */
	public static final String METIER 
		= "metier";
	
	/**
	 * "accueil".
	 */
	public static final String ACCUEIL 
		= "accueil";
	
	/**
	 * "utilitaires".
	 */
	public static final String UTILITAIRES 
		= "utilitaires";
	
	/**
	 * "desktop".
	 */
	public static final String DESKTOP 
		= "desktop";
	
	/**
	 * "web".
	 */
	public static final String WEB 
		= "web";
	
	/**
	 * "apptechnic".<br/>
	 */
	public static final String APPTECHNIC 
		= "apptechnic";
	
	/**
	 * "controllers".<br/>
	 */
	public static final String CONTROLLERS 
		= "controllers";
	
	/**
	 * "vues".<br/>
	 */
	public static final String VUES 
		= "vues";
	
	/**
	 * "model".<br/>
	 */
	public static final String MODEL 
		= "model";
	
	/**
	 * "line.separator".
	 */
	public static final String LINE_SEPARATOR 
		= "line.separator";
	
	/**
	 * <ul>
	 * <li><b>path du projet SOURCE Eclipse</b> 
	 * dont on va trouver le code.</li>
	 * <li>path sous forme de <b>java.nio.file.Path</b>.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>D:/Donnees/eclipse/eclipseworkspace_neon/projet_users
	 * </code></li>
	 * </ul>
	 */
	private static Path projetSourcePath;
	
	/**
	 * <ul>
	 * <li><b>nom du projet SOURCE Eclipse</b> 
	 * dont on va trouver le code.</li>
	 * <li>calculé par la méthode selectionnerProjetSource(Path)</li>
	 * <li>nom sous forme de <b>String</b>.</li>
	 * <li>Singleton.</li>
	 * <li>Par exemple : <br/>
	 * <code>projet_users</code>
	 * </li>
	 * </ul>
	 */
	private static String projetSourceNom;
	
	/**
	 * path <b>relatif</b> (par rapport au projet source) 
	 * des sources Java dans le projet source.<br/>
	 * Paths.get("src/main/java")
	 */
	public static final Path SRC_MAIN_JAVA_PATH_RELATIF 
		= Paths.get("src/main/java");
	
	/**
	 * <b>path absolu des sources Java</b> dans le projet source.<br/>
	 * projetSourcePath + src/main/java<br/>
	 */
	private static Path srcMainJavaPath;

	/**
	 * path <b>relatif</b> (par rapport au projet source) 
	 * des ressources dans le projet source.<br/>
	 * Paths.get("src/main/resources")
	 */
	public static final Path SRC_MAIN_RESOURCES_PATH_RELATIF 
		= Paths.get("src/main/resources");
	
	/**
	 * <b>path absolu des ressources</b> dans le projet source.<br/>
	 * projetSourcePath + src/main/resources<br/>
	 */
	private static Path srcMainResourcesPath;

	/**
	 * <b>path absolu de ressources/META-INF</b> 
	 * dans le projet source.<br/>
	 * srcMainResourcesPath/ + META-INF<br/>
	 */
	private static Path srcMainResourcesMetaInfPath;

	/**
	 * path <b>relatif</b> (par rapport au projet source) 
	 * des sources des tests JUnit dans le projet source.<br/>
	 * Paths.get("src/test/java")
	 */
	public static final Path SRC_TEST_JAVA_PATH_RELATIF 
		= Paths.get("src/test/java");

	/**
	 * <b>path absolu des sources des tests JUnit</b> 
	 * dans le projet source.<br/>
	 * projetSourcePath + src/test/java<br/>
	 */
	private static Path srcTestJavaPath;

	/**
	 * path <b>relatif</b> (par rapport au projet source) 
	 * des ressources des tests JUnit dans le projet source.<br/>
	 * Paths.get("src/test/resources")
	 */
	public static final Path SRC_TEST_RESOURCES_PATH_RELATIF 
		= Paths.get("src/test/resources");

	/**
	 * <b>path absolu des ressources des tests Junit</b> 
	 * dans le projet source.<br/>
	 * projetSourcePath + src/test/resources<br/>
	 */
	private static Path srcTestResourcesPath;

	/**
	 * <b>path absolu des ressources/META-INF des tests Junit</b> 
	 * dans le projet source.<br/>
	 * srcTestResourcesPath/ + META-INF<br/>
	 */
	private static Path srcTestResourcesMetaInfPath;
	
	/**
	 * GroupId MAVEN à appliquer par défaut.<br/>
	 * Le groupId MAVEN (avec des .) 
	 * peut être modifié par le Setter.<br/>
	 * "levy.daniel.application".
	 */
	private static final String GROUPID_PAR_DEFAUT 
	= "levy.daniel.application";
	
	/**
	 * GroupId MAVEN dans le projet source.<br/>
	 * Le groupId MAVEN (avec des .) 
	 * peut être modifié par le Setter.<br/>
	 * "levy.daniel.application" par défaut.
	 */
	private static String groupId = GROUPID_PAR_DEFAUT;
	
	/**
	 * PATH du GroupId MAVEN à appliquer par défaut.<br/>
	 * Le groupIdPathRelatif (avec des /) 
	 * peut être modifié par le Setter.<br/>
	 * "levy/daniel/application".
	 */
	private static final String GROUPIDPATH_PAR_DEFAUT 
		= "levy/daniel/application";

	/**
	 * path <b>relatif</b> 
	 * (par rapport au srcMainJavaPath et au srcTestJavaPath)
	 * correspondant au GroupId MAVEN.<br/>
	 * - Paths.get("levy/daniel/application") par défaut.<br/>
	 * - Sinon, utiliser le Setter.<br/>
	 */
	private static Path groupIdPathRelatif 
		= Paths.get(GROUPIDPATH_PAR_DEFAUT);
	
	/**
	 * <b>path absolu des sources Java</b> 
	 * dans le projet source.<br/>
	 * srcMainJavaPath + groupIdPathRelatif<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application</code>
	 */
	private static Path racineSourcesJavaPath;
	
	/**
	 * <b>path absolu des tests JUnit</b> 
	 * dans le projet source.<br/>
	 * srcTestJavaPath + groupIdPathRelatif<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application</code>
	 */
	private static Path racineTestsJavaPath;
		
	/**
	 * <b>path absolu des sources de la couche apptechnic</b> 
	 * dans le projet source.<br/>
	 * racineSourcesJavaPath + apptechnic<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/apptechnic</code>
	 */
	private static Path coucheAppTechnicMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche apptechnic</b> 
	 * dans le projet source.<br/>
	 * racineTestsJavaPath + apptechnic<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/apptechnic</code>
	 */
	private static Path coucheAppTechnicTestPath;
		
	/**
	 * <b>path absolu des sources de la couche controllers</b> 
	 * dans le projet source.<br/>
	 * racineSourcesJavaPath + controllers<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/controllers</code>
	 */
	private static Path coucheControllersMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche controllers</b> 
	 * dans le projet source.<br/>
	 * racineTestsJavaPath + controllers<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/controllers</code>
	 */
	private static Path coucheControllersTestPath;
		
	/**
	 * <b>path absolu des sources de la couche 
	 * controllers/desktop</b> 
	 * dans le projet source.<br/>
	 * coucheControllersMainPath + desktop<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /controllers/desktop</code>
	 */
	private static Path coucheControllersDesktopMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * controllers/desktop</b> 
	 * dans le projet source.<br/>
	 * coucheControllersTestPath + desktop<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /controllers/desktop</code>
	 */
	private static Path coucheControllersDesktopTestPath;
	
	/**
	 * <b>path absolu des sources de la couche 
	 * controllers/desktop/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheControllersDesktopMainPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /controllers/desktop/accueil</code>
	 */
	private static Path coucheControllersDesktopAccueilMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * controllers/desktop/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheControllersDesktopTestPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /controllers/desktop/accueil</code>
	 */
	private static Path coucheControllersDesktopAccueilTestPath;
	
	/**
	 * <b>path absolu des sources de la couche 
	 * controllers/desktop/metier</b> 
	 * dans le projet source.<br/>
	 * coucheControllersDesktopMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /controllers/desktop/metier</code>
	 */
	private static Path coucheControllersDesktopMetierMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * controllers/desktop/metier</b> 
	 * dans le projet source.<br/>
	 * coucheControllersDesktopTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /controllers/desktop/metier</code>
	 */
	private static Path coucheControllersDesktopMetierTestPath;
	
	/**
	 * <b>path absolu des sources de la couche 
	 * controllers/desktop/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheControllersDesktopMainPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /controllers/desktop/utilitaires</code>
	 */
	private static Path coucheControllersDesktopUtilitairesMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * controllers/desktop/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheControllersDesktopTestPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /controllers/desktop/utilitaires</code>
	 */
	private static Path coucheControllersDesktopUtilitairesTestPath;
		
	/**
	 * <b>path absolu des sources de la couche 
	 * controllers/web</b> 
	 * dans le projet source.<br/>
	 * coucheControllersMainPath + web<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /controllers/web</code>
	 */
	private static Path coucheControllersWebMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * controllers/web</b> 
	 * dans le projet source.<br/>
	 * coucheControllersTestPath + web<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /controllers/web</code>
	 */
	private static Path coucheControllersWebTestPath;
		
	/**
	 * <b>path absolu des sources de la couche 
	 * controllers/web/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheControllersWebMainPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /controllers/web/accueil</code>
	 */
	private static Path coucheControllersWebAccueilMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * controllers/web/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheControllersWebTestPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /controllers/web/accueil</code>
	 */
	private static Path coucheControllersWebAccueilTestPath;
		
	/**
	 * <b>path absolu des sources de la couche 
	 * controllers/web/metier</b> 
	 * dans le projet source.<br/>
	 * coucheControllersWebMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /controllers/web/metier</code>
	 */
	private static Path coucheControllersWebMetierMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * controllers/web/metier</b> 
	 * dans le projet source.<br/>
	 * coucheControllersWebTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /controllers/web/metier</code>
	 */
	private static Path coucheControllersWebMetierTestPath;
		
	/**
	 * <b>path absolu des sources de la couche 
	 * controllers/web/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheControllersWebMainPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /controllers/web/utilitaires</code>
	 */
	private static Path coucheControllersWebUtilitairesMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * controllers/web/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheControllersWebTestPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /controllers/web/utilitaires</code>
	 */
	private static Path coucheControllersWebUtilitairesTestPath;
		
	/**
	 * <b>path absolu des sources de la couche vues</b> 
	 * dans le projet source.<br/>
	 * racineSourcesJavaPath + vues<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/vues</code>
	 */
	private static Path coucheVuesMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche vues</b> 
	 * dans le projet source.<br/>
	 * racineTestsJavaPath + vues<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/vues</code>
	 */
	private static Path coucheVuesTestPath;
		
	/**
	 * <b>path absolu des sources de la couche 
	 * vues/desktop</b> 
	 * dans le projet source.<br/>
	 * coucheVuesMainPath + desktop<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /vues/desktop</code>
	 */
	private static Path coucheVuesDesktopMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * vues/desktop</b> 
	 * dans le projet source.<br/>
	 * coucheVuesTestPath + desktop<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /vues/desktop</code>
	 */
	private static Path coucheVuesDesktopTestPath;
	
	/**
	 * <b>path absolu des sources de la couche 
	 * vues/desktop/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheVuesDesktopMainPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /vues/desktop/accueil</code>
	 */
	private static Path coucheVuesDesktopAccueilMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * vues/desktop/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheVuesDesktopTestPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /vues/desktop/accueil</code>
	 */
	private static Path coucheVuesDesktopAccueilTestPath;
	
	/**
	 * <b>path absolu des sources de la couche 
	 * vues/desktop/metier</b> 
	 * dans le projet source.<br/>
	 * coucheVuesDesktopMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /vues/desktop/metier</code>
	 */
	private static Path coucheVuesDesktopMetierMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * vues/desktop/metier</b> 
	 * dans le projet source.<br/>
	 * coucheVuesDesktopTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /vues/desktop/metier</code>
	 */
	private static Path coucheVuesDesktopMetierTestPath;
	
	/**
	 * <b>path absolu des sources de la couche 
	 * vues/desktop/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheVuesDesktopMainPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /vues/desktop/utilitaires</code>
	 */
	private static Path coucheVuesDesktopUtilitairesMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * vues/desktop/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheVuesDesktopTestPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /vues/desktop/utilitaires</code>
	 */
	private static Path coucheVuesDesktopUtilitairesTestPath;
		
	/**
	 * <b>path absolu des sources de la couche 
	 * vues/web</b> 
	 * dans le projet source.<br/>
	 * coucheVuesMainPath + web<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /vues/web</code>
	 */
	private static Path coucheVuesWebMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * vues/web</b> 
	 * dans le projet source.<br/>
	 * coucheVuesTestPath + web<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /vues/web</code>
	 */
	private static Path coucheVuesWebTestPath;
		
	/**
	 * <b>path absolu des sources de la couche 
	 * vues/web/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheVuesWebMainPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /vues/web/accueil</code>
	 */
	private static Path coucheVuesWebAccueilMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * vues/web/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheVuesWebTestPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /vues/web/accueil</code>
	 */
	private static Path coucheVuesWebAccueilTestPath;
		
	/**
	 * <b>path absolu des sources de la couche 
	 * vues/web/metier</b> 
	 * dans le projet source.<br/>
	 * coucheVuesWebMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /vues/web/metier</code>
	 */
	private static Path coucheVuesWebMetierMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * vues/web/metier</b> 
	 * dans le projet source.<br/>
	 * coucheVuesWebTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /vues/web/metier</code>
	 */
	private static Path coucheVuesWebMetierTestPath;
		
	/**
	 * <b>path absolu des sources de la couche 
	 * vues/web/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheVuesWebMainPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application
	 * /vues/web/utilitaires</code>
	 */
	private static Path coucheVuesWebUtilitairesMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * vues/web/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheVuesWebTestPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application
	 * /vues/web/utilitaires</code>
	 */
	private static Path coucheVuesWebUtilitairesTestPath;
			
	/**
	 * <b>path absolu des sources de la couche model</b> 
	 * dans le projet source.<br/>
	 * racineSourcesJavaPath + model<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/model</code>
	 */
	private static Path coucheModelMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche model</b> 
	 * dans le projet source.<br/>
	 * racineTestsJavaPath + model<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/model</code>
	 */
	private static Path coucheModelTestPath;
	
	/**
	 * <b>path absolu des sources de la couche model/dto</b> 
	 * dans le projet source.<br/>
	 * coucheModelMainPath + dto<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/dto</code>
	 */
	private static Path coucheModelDTOMainPath;

	/**
	 * <b>path absolu des tests JUnit de la couche model/dto</b> 
	 * dans le projet source.<br/>
	 * coucheModelTestPath + dto<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/dto</code>
	 */
	private static Path coucheModelDTOTestPath;
		
	/**
	 * <b>path absolu des sources de la couche model/dto/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelDTOMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/dto/metier</code>
	 */
	private static Path coucheModelDTOMetierMainPath;

	/**
	 * <b>path absolu des tests JUnit de la couche model/dto/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelDTOTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/dto/metier</code>
	 */
	private static Path coucheModelDTOMetierTestPath;
	
	/**
	 * <b>path absolu des sources de la couche model/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/metier</code>
	 */
	private static Path coucheModelMetierMainPath;

	/**
	 * <b>path absolu des tests JUnit de la couche model/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/metier</code>
	 */
	private static Path coucheModelMetierTestPath;
	
	/**
	 * <b>path absolu des sources de la couche model/persistence</b> 
	 * dans le projet source.<br/>
	 * coucheModelMainPath + persistence<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/persistence</code>
	 */
	private static Path coucheModelPersistenceMainPath;

	/**
	 * <b>path absolu des tests JUnit de la couche model/persistence</b> 
	 * dans le projet source.<br/>
	 * coucheModelTestPath + persistence<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/persistence</code>
	 */
	private static Path coucheModelPersistenceTestPath;
	
	/**
	 * <b>path absolu des sources de la couche 
	 * model/persistence/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheModelPersistenceMainPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/persistence/accueil</code>
	 */
	private static Path coucheModelPersistenceAccueilMainPath;

	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * model/persistence/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheModelPersistenceTestPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/persistence/accueil</code>
	 */
	private static Path coucheModelPersistenceAccueilTestPath;
	
	/**
	 * <b>path absolu des sources de la couche 
	 * model/persistence/daoexceptions</b> 
	 * dans le projet source.<br/>
	 * coucheModelPersistenceMainPath + daoexceptions<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/persistence/daoexceptions</code>
	 */
	private static Path coucheModelPersistenceDaoexceptionsMainPath;

	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * model/persistence/daoexceptions</b> 
	 * dans le projet source.<br/>
	 * coucheModelPersistenceTestPath + daoexceptions<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/persistence/daoexceptions</code>
	 */
	private static Path coucheModelPersistenceDaoexceptionsTestPath;
	
	/**
	 * <b>path absolu des sources de la couche 
	 * model/persistence/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelPersistenceMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/persistence/metier</code>
	 */
	private static Path coucheModelPersistenceMetierMainPath;

	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * model/persistence/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelPersistenceTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/persistence/metier</code>
	 */
	private static Path coucheModelPersistenceMetierTestPath;
	
	/**
	 * <b>path absolu des sources de la couche model/services</b> 
	 * dans le projet source.<br/>
	 * coucheModelMainPath + services<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services</code>
	 */
	private static Path coucheModelServicesMainPath;

	/**
	 * <b>path absolu des tests JUnit de la couche model/services</b> 
	 * dans le projet source.<br/>
	 * coucheModelTestPath + services<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services</code>
	 */
	private static Path coucheModelServicesTestPath;
	
	/**
	 * <b>path absolu des sources de la couche 
	 * model/services/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesMainPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/accueil</code>
	 */
	private static Path coucheModelServicesAccueilMainPath;

	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * model/services/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesTestPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/accueil</code>
	 */
	private static Path coucheModelServicesAccueilTestPath;
	
	/**
	 * <b>path absolu des sources de la couche 
	 * model/services/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/metier</code>
	 */
	private static Path coucheModelServicesMetierMainPath;

	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * model/services/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/metier</code>
	 */
	private static Path coucheModelServicesMetierTestPath;
	
	/**
	 * <b>path absolu des sources de la couche 
	 * model/services/transformeurs</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesMainPath + transformeurs<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/transformeurs</code>
	 */
	private static Path coucheModelServicesTransformeursMainPath;

	/**
	 * <b>path absolu des sources de la couche 
	 * model/services/transformeurs/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesTransformeursMainPath/ + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/transformeurs/metier</code>
	 */
	private static Path coucheModelServicesTransformeursMetierMainPath;
	
	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * model/services/transformeurs</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesTestPath + transformeurs<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/transformeurs</code>
	 */
	private static Path coucheModelServicesTransformeursTestPath;

	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * model/services/transformeurs/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesTransformeursTestPath/ + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/transformeurs/metier</code>
	 */
	private static Path coucheModelServicesTransformeursMetierTestPath;

	/**
	 * <b>path absolu des sources de la couche 
	 * model/services/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesMainPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/utilitaires</code>
	 */
	private static Path coucheModelServicesUtilitairesMainPath;

	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * model/services/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesTestPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/utilitaires</code>
	 */
	private static Path coucheModelServicesUtilitairesTestPath;
	
	/**
	 * <b>path absolu des sources de la couche 
	 * model/services/valideurs</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesMainPath + valideurs<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/valideurs</code>
	 */
	private static Path coucheModelServicesValideursMainPath;

	/**
	 * <b>path absolu des sources de la couche 
	 * model/services/valideurs/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesValideursMainPath/ + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/valideurs/metier</code>
	 */
	private static Path coucheModelServicesValideursMetierMainPath;

	/**
	 * <b>path absolu des tests JUnit de la couche 
	 * model/services/valideurs</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesTestPath + valideurs<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/valideurs</code>
	 */
	private static Path coucheModelServicesValideursTestPath;

	/**
	 * <b>path absolu des sources de la couche 
	 * model/services/valideurs/metier</b> 
	 * dans les tests du projet source.<br/>
	 * coucheModelServicesValideursTestPath/ + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/valideurs/metier</code>
	 */
	private static Path coucheModelServicesValideursMetierTestPath;
	
	/**
	 * <b>path absolu des sources de la couche model/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheModelMainPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/utilitaires</code>
	 */
	private static Path coucheModelUtilitairesMainPath;

	/**
	 * <b>path absolu des tests JUnit de la couche model/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheModelTestPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/utilitaires</code>
	 */
	private static Path coucheModelUtilitairesTestPath;
	
	/**
	 * repertoire externe <b>conception_appli</b>.<br/>
	 * projetSourcePath + conception_appli
	 */
	private static Path conceptionAppliPath;
	
	/**
	 * repertoire externe <b>data</b>.
	 * projetSourcePath + data
	 */
	private static Path dataPath;
	
	/**
	 * dataPath/ + "base-" + ${projetSourceNom} + "-h2"</b>.<br/>
	 */
	private static Path dataH2Path;
	
	/**
	 * dataPath/ + "base-" + ${projetSourceNom}-hsqldb.
	 */
	private static Path dataHSQLDBPath;
	
	/**
	 * dataPath/ + "base-" + ${projetSourceNom} + "-JAXB"</b>.<br/>
	 */
	private static Path dataJAXBPath;
	
	/**
	 * dataPath/ + scripts_sql.
	 */
	private static Path dataScriptsSqlPath;
	
	/**
	 * repertoire externe <b>javadoc</b>.
	 */
	private static Path javadocPath;
		
	/**
	 * repertoire externe <b>javadoc/images</b>.
	 */
	private static Path javadocImagesPath;
	
	/**
	 * repertoire externe <b>logs</b>.
	 */
	private static Path logsPath;
	
	/**
	 * repertoire externe <b>rapports_controle</b>.
	 */
	private static Path rapportsControlePath;
	
	/**
	 * repertoire externe <b>ressources_externes</b>.
	 */
	private static Path ressourcesExternesPath;
	
	/**
	 * Liste des répertoires (sous forme de Path) 
	 * constituant l'arborescence à créer dans le projet source.
	 */
	private static final List<Path> ARBORESCENCE_PROJET_SOURCE 
		= new LinkedList<Path>();
	
	/**
	 * <b>Map des répertoires sous src/main/java</b> 
	 * dans le projet source avec :
	 * <ul>
	 * <li>String : le chemin relatif du répertoire par rapport 
	 * au GroupId comme <code>"model/dto"</code></li>
	 * <li>Path : l'attribut contenant le Path absolu 
	 * du répertoire comme <code>coucheModelDTOMainPath</code></li>
	 * </ul>
	 */
	private static final Map<String, Path> ARBORESCENCE_MAIN_PROJET_SOURCE_MAP 
		= new ConcurrentHashMap<String, Path>();
	
	/**
	 * <b>Map des répertoires sous src/test/java</b> 
	 * dans le projet source avec :
	 * <ul>
	 * <li>String : le chemin relatif du répertoire par rapport 
	 * au GroupId comme <code>"model/dto"</code></li>
	 * <li>Path : l'attribut contenant le Path absolu 
	 * du répertoire comme <code>coucheModelDTOTestPath</code></li>
	 * </ul>
	 */
	private static final Map<String, Path> ARBORESCENCE_TEST_PROJET_SOURCE_MAP 
		= new ConcurrentHashMap<String, Path>();
	
	/**
	 * <b>Map des répertoires externes</b> 
	 * dans le projet source avec :
	 * <ul>
	 * <li>String : le nom du répertoire externe 
	 * comme <code>"data"</code></li>
	 * <li>Path : l'attribut contenant le Path absolu 
	 * du répertoire comme <code>dataPath</code></li>
	 * </ul>
	 */
	private static final Map<String, Path> ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP 
		= new ConcurrentHashMap<String, Path>();
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	private static final Log LOG 
		= LogFactory.getLog(ArboresceurProjetSource.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	private ArboresceurProjetSource() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * <b>sélectionne le projet source</b> MAVEN Eclipse 
	 * dans lequel on veut trouver une aborescence de projet MAVEN.<br/>
	 * <ul>
	 * <li>alimente <b>projetSourcePath</b>.</li>
	 * <li>alimente <b>projetSourceNom</b>.</li>
	 * <li><b>calcule tous les autres attributs</b> 
	 * (paths) de la classe.</li>
	 * <li><b>alimente la liste ARBORESCENCE_PROJET_SOURCE</b> 
	 * contenant tous les répertoires à créer dans le projet source.</li>
	 * <li><b>alimente la Map ARBORESCENCE_MAIN_PROJET_SOURCE_MAP</b>.</li>
	 * <li><b>alimente la Map ARBORESCENCE_TEST_PROJET_SOURCE_MAP</b>.</li>
	 * </ul>
	 * - ne fait rien si pPojetSourcePath == null.<br/>
	 * <br/>
	 *
	 * @param pProjetSourcePath : Path : Path du projet source dans lequel 
	 * on va trouver une arborescence d'application n-tiers 
	 * MAVEN SIMPLE.<br/>
	 */
	public static void selectionnerProjetSource(
			final Path pProjetSourcePath) {
		
		synchronized (ArboresceurProjetSource.class) {
			
			/* ne fait rien si pPojetSourcePath == null. */
			if (pProjetSourcePath == null) {
				return;
			}
			
			projetSourcePath = pProjetSourcePath;
			projetSourceNom = extraireNom(pProjetSourcePath);
			
			/* calcule tous les autres attributs. */
			calculerTousAttributs();
			
			/* alimente la liste ARBORESCENCE_PROJET_SOURCE 
			 * contenant tous les répertoires à créer 
			 * dans le projet source.*/
			alimenterArborescence();
			
			/* alimente la Map ARBORESCENCE_MAIN_PROJET_SOURCE_MAP. */
			alimenterArborescenceMainMap();
			
			/* alimente la Map ARBORESCENCE_TEST_PROJET_SOURCE_MAP. */
			alimenterArborescenceTestMap();
			
			/* alimente la Map ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP. */
			alimenterArborescenceRepExternesMap();
			
		} // Fin de synchronized._______________________
		
	} // Fin de selectionnerProjetSource(...).______________________________


	
	/**
	 * extrait sous forme de String le nom du dernier 
	 * niveau d'un Path.<br/>
	 * - Par exemple : <code>toto</code> 
	 * pour le path <code>C:/tata/toto</code>.<br/>
	 * - retourne null si pPath == null.<br/> 
	 *
	 * @param pPath : Path.<br/>
	 * 
	 * @return : String : nom du dernier niveau du Path.<br/>
	 */
	private static String extraireNom(
			final Path pPath) {
		
		synchronized (ArboresceurProjetSource.class) {
			
			/* retourne null si pPath == null. */
			if (pPath != null) {
				
				final int nombreNiveaux = pPath.getNameCount();
				final Path nomPath = pPath.getName(nombreNiveaux - 1);
				return nomPath.toString();
			}
			
			return null;
			
		} // Fin de synchronized._______________________
		
	} // Fin de extraireNom(...).__________________________________________
	
	
	
	/**
	 * calcule tous les attributs de la présente classe.<br/>
	 */
	private static void calculerTousAttributs() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			calculerSrcMainJavaPath();
			calculerSrcMainResourcesPath();
			calculerSrcMainResourcesMetaInfPath();
			calculerSrcTestJavaPath();
			calculerSrcTestResourcesPath();
			calculerSrcTestResourcesMetaInfPath();
			
			calculerRacineSourcesJavaPath();
			calculerRacineTestsJavaPath();
			
			// APPTECHNIC
			calculerCoucheAppTechnicMainPath();
			calculerCoucheAppTechnicTestPath();
			
			// CONTROLLERS
			calculerCoucheControllersMainPath();
			calculerCoucheControllersTestPath();
			calculerCoucheControllersDesktopMainPath();
			calculerCoucheControllersDesktopTestPath();
			calculerCoucheControllersDesktopAccueilMainPath();
			calculerCoucheControllersDesktopAccueilTestPath();
			calculerCoucheControllersDesktopMetierMainPath();
			calculerCoucheControllersDesktopMetierTestPath();
			calculerCoucheControllersDesktopUtilitairesMainPath();
			calculerCoucheControllersDesktopUtilitairesTestPath();
			calculerCoucheControllersWebMainPath();
			calculerCoucheControllersWebTestPath();
			calculerCoucheControllersWebAccueilMainPath();
			calculerCoucheControllersWebAccueilTestPath();
			calculerCoucheControllersWebMetierMainPath();
			calculerCoucheControllersWebMetierTestPath();
			calculerCoucheControllersWebUtilitairesMainPath();
			calculerCoucheControllersWebUtilitairesTestPath();
			
			// VUES
			calculerCoucheVuesMainPath();
			calculerCoucheVuesTestPath();
			calculerCoucheVuesDesktopMainPath();
			calculerCoucheVuesDesktopTestPath();
			calculerCoucheVuesDesktopAccueilMainPath();
			calculerCoucheVuesDesktopAccueilTestPath();
			calculerCoucheVuesDesktopMetierMainPath();
			calculerCoucheVuesDesktopMetierTestPath();
			calculerCoucheVuesDesktopUtilitairesMainPath();
			calculerCoucheVuesDesktopUtilitairesTestPath();
			calculerCoucheVuesWebMainPath();
			calculerCoucheVuesWebTestPath();
			calculerCoucheVuesWebAccueilMainPath();
			calculerCoucheVuesWebAccueilTestPath();
			calculerCoucheVuesWebMetierMainPath();
			calculerCoucheVuesWebMetierTestPath();
			calculerCoucheVuesWebUtilitairesMainPath();
			calculerCoucheVuesWebUtilitairesTestPath();
			
			// MODEL
			calculerCoucheModelMainPath();
			calculerCoucheModelTestPath();
			
			calculerCoucheModelDTOMainPath();
			calculerCoucheModelDTOTestPath();
			calculerCoucheModelDTOMetierMainPath();
			calculerCoucheModelDTOMetierTestPath();
			
			calculerCoucheModelMetierMainPath();
			calculerCoucheModelMetierTestPath();
			
			calculerCoucheModelPersistenceMainPath();
			calculerCoucheModelPersistenceTestPath();
			calculerCoucheModelPersistenceAccueilMainPath();
			calculerCoucheModelPersistenceAccueilTestPath();
			calculerCoucheModelPersistenceDaoexceptionsMainPath();
			calculerCoucheModelPersistenceDaoexceptionsTestPath();
			calculerCoucheModelPersistenceMetierMainPath();
			calculerCoucheModelPersistenceMetierTestPath();
			
			calculerCoucheModelServicesMainPath();
			calculerCoucheModelServicesTestPath();
			calculerCoucheModelServicesAccueilMainPath();
			calculerCoucheModelServicesAccueilTestPath();
			calculerCoucheModelServicesMetierMainPath();
			calculerCoucheModelServicesMetierTestPath();
			calculerCoucheModelServicesTransformeursMainPath();
			calculerCoucheModelServicesTransformeursMetierMainPath();
			calculerCoucheModelServicesTransformeursTestPath();
			calculerCoucheModelServicesTransformeursMetierTestPath();
			calculerCoucheModelServicesUtilitairesMainPath();
			calculerCoucheModelServicesUtilitairesTestPath();
			calculerCoucheModelServicesValideursMainPath();
			calculerCoucheModelServicesValideursMetierMainPath();
			calculerCoucheModelServicesValideursTestPath();
			calculerCoucheModelServicesValideursMetierTestPath();
			
			calculerCoucheModelUtilitairesMainPath();
			calculerCoucheModelUtilitairesTestPath();
			
			// REPERTOIRES EXTERNES
			calculerConceptionAppliPath();
			calculerDataPath();
			calculerDataH2Path();
			calculerDataHSQLDBPath();
			calculerDataJAXBPath();
			calculerDataScriptsSqlPath();
			calculerJavadocPath();
			calculerJavadocImagesPath();
			calculerLogsPath();
			calculerRapportsControlePath();
			calculerRessourcesExternesPath();
			
		} // Fin de synchronized._______________________
		
	} // Fin de calculerTousAttributs().___________________________________
	

	
	/**
	 * Alimente la liste ARBORESCENCE_PROJET_SOURCE.<br/>
	 * Liste des répertoires (sous forme de Path) 
	 * constituant l'arborescence à créer dans le projet source.<br/>
	 */
	private static void alimenterArborescence() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			ARBORESCENCE_PROJET_SOURCE.add(srcMainJavaPath);
			ARBORESCENCE_PROJET_SOURCE.add(srcMainResourcesPath);
			ARBORESCENCE_PROJET_SOURCE.add(srcMainResourcesMetaInfPath);
			ARBORESCENCE_PROJET_SOURCE.add(srcTestJavaPath);
			ARBORESCENCE_PROJET_SOURCE.add(srcTestResourcesPath);
			ARBORESCENCE_PROJET_SOURCE.add(srcTestResourcesMetaInfPath);
			
			ARBORESCENCE_PROJET_SOURCE.add(racineSourcesJavaPath);
			ARBORESCENCE_PROJET_SOURCE.add(racineTestsJavaPath);
			
			// APPTECHNIC
			ARBORESCENCE_PROJET_SOURCE.add(coucheAppTechnicMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheAppTechnicTestPath);
			
			// CONTROLLERS
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersTestPath);
			
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersDesktopMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersDesktopTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersDesktopAccueilMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersDesktopAccueilTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersDesktopMetierMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersDesktopMetierTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersDesktopUtilitairesMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersDesktopUtilitairesTestPath);
			
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersWebMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersWebTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersWebAccueilMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersWebAccueilTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersWebMetierMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersWebMetierTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersWebUtilitairesMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheControllersWebUtilitairesTestPath);
			
			// VUES
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesTestPath);
			
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesDesktopMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesDesktopTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesDesktopAccueilMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesDesktopAccueilTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesDesktopMetierMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesDesktopMetierTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesDesktopUtilitairesMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesDesktopUtilitairesTestPath);
			
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesWebMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesWebTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesWebAccueilMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesWebAccueilTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesWebMetierMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesWebMetierTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesWebUtilitairesMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheVuesWebUtilitairesTestPath);
			
			// MODEL
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelTestPath);
			
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelDTOMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelDTOTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelDTOMetierMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelDTOMetierTestPath);
			
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelMetierMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelMetierTestPath);
			
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelPersistenceMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelPersistenceTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelPersistenceAccueilMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelPersistenceAccueilTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelPersistenceDaoexceptionsMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelPersistenceDaoexceptionsTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelPersistenceMetierMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelPersistenceMetierTestPath);
			
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesAccueilMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesAccueilTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesMetierMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesMetierTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesTransformeursMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesTransformeursMetierMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesTransformeursTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesTransformeursMetierTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesUtilitairesMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesUtilitairesTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesValideursMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesValideursMetierMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesValideursTestPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelServicesValideursMetierTestPath);

			ARBORESCENCE_PROJET_SOURCE.add(coucheModelUtilitairesMainPath);
			ARBORESCENCE_PROJET_SOURCE.add(coucheModelUtilitairesTestPath);
			
			// REPERTOIRES EXTERNES
			ARBORESCENCE_PROJET_SOURCE.add(conceptionAppliPath);
			ARBORESCENCE_PROJET_SOURCE.add(dataPath);
			ARBORESCENCE_PROJET_SOURCE.add(dataH2Path);
			ARBORESCENCE_PROJET_SOURCE.add(dataHSQLDBPath);
			ARBORESCENCE_PROJET_SOURCE.add(dataJAXBPath);
			ARBORESCENCE_PROJET_SOURCE.add(dataScriptsSqlPath);
			ARBORESCENCE_PROJET_SOURCE.add(javadocPath);
			ARBORESCENCE_PROJET_SOURCE.add(javadocImagesPath);
			ARBORESCENCE_PROJET_SOURCE.add(logsPath);
			ARBORESCENCE_PROJET_SOURCE.add(rapportsControlePath);
			ARBORESCENCE_PROJET_SOURCE.add(ressourcesExternesPath);
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterArborescence().___________________________________
	

	
	/**
	 * alimente la <b>Map des répertoires sous src/main/java</b> 
	 * dans le projet source avec :
	 * <ul>
	 * <li>String : le chemin relatif du répertoire par rapport 
	 * au GroupId comme <code>"model/dto"</code></li>
	 * <li>Path : l'attribut contenant le Path absolu 
	 * du répertoire comme <code>coucheModelDTOMainPath</code></li>
	 * </ul>
	 */
	private static void alimenterArborescenceMainMap() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("src/main/java", srcMainJavaPath);
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("src/main/resources", srcMainResourcesPath);
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("src/main/resources/META-INF", srcMainResourcesMetaInfPath);
			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("src/main/java/groupid", racineSourcesJavaPath);
			
			// APPTECHNIC
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put(APPTECHNIC, coucheAppTechnicMainPath);
			
			// CONTROLLERS
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put(CONTROLLERS, coucheControllersMainPath);
			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("controllers/desktop", coucheControllersDesktopMainPath);
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("controllers/desktop/accueil", coucheControllersDesktopAccueilMainPath);			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("controllers/desktop/metier", coucheControllersDesktopMetierMainPath);				
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("controllers/desktop/utilitaires", coucheControllersDesktopUtilitairesMainPath);			
			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("controllers/web", coucheControllersWebMainPath);
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("controllers/web/accueil", coucheControllersWebAccueilMainPath);			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("controllers/web/metier", coucheControllersWebMetierMainPath);			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("controllers/web/utilitaires", coucheControllersWebUtilitairesMainPath);			

			// VUES
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put(VUES, coucheVuesMainPath);
			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("vues/desktop", coucheVuesDesktopMainPath);
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("vues/desktop/accueil", coucheVuesDesktopAccueilMainPath);			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("vues/desktop/metier", coucheVuesDesktopMetierMainPath);				
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("vues/desktop/utilitaires", coucheVuesDesktopUtilitairesMainPath);			
			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("vues/web", coucheVuesWebMainPath);
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("vues/web/accueil", coucheVuesWebAccueilMainPath);			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("vues/web/metier", coucheVuesWebMetierMainPath);			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("vues/web/utilitaires", coucheVuesWebUtilitairesMainPath);
			
			// MODEL
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put(MODEL, coucheModelMainPath);			

			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/dto", coucheModelDTOMainPath);
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/dto/metier", coucheModelDTOMetierMainPath);
			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/metier", coucheModelMetierMainPath);

			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/persistence", coucheModelPersistenceMainPath);
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/persistence/accueil", coucheModelPersistenceAccueilMainPath);
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/persistence/daoexceptions", coucheModelPersistenceDaoexceptionsMainPath);
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/persistence/metier", coucheModelPersistenceMetierMainPath);

			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/services", coucheModelServicesMainPath);
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/services/accueil", coucheModelServicesAccueilMainPath);
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/services/metier", coucheModelServicesMetierMainPath);
			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/services/transformeurs", coucheModelServicesTransformeursMainPath);
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/services/transformeurs/metier", coucheModelServicesTransformeursMetierMainPath);
			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/services/utilitaires", coucheModelServicesUtilitairesMainPath);
			
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/services/valideurs", coucheModelServicesValideursMainPath);
			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/services/valideurs/metier", coucheModelServicesValideursMetierMainPath);

			ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.put("model/utilitaires", coucheModelUtilitairesMainPath);
									
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterArborescenceMainMap().____________________________
	
	
	
	/**
	 * alimente la <b>Map des répertoires sous src/test/java</b> 
	 * dans le projet source avec :
	 * <ul>
	 * <li>String : le chemin relatif du répertoire par rapport 
	 * au GroupId comme <code>"model/dto"</code></li>
	 * <li>Path : l'attribut contenant le Path absolu 
	 * du répertoire comme <code>coucheModelDTOTestPath</code></li>
	 * </ul>
	 */
	private static void alimenterArborescenceTestMap() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("src/test/java", srcTestJavaPath);
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("src/test/resources", srcTestResourcesPath);
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("src/test/resources/META-INF", srcTestResourcesMetaInfPath);
			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("src/test/java/groupid", racineTestsJavaPath);
			
			// APPTECHNIC
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put(APPTECHNIC, coucheAppTechnicTestPath);
			
			// CONTROLLERS
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put(CONTROLLERS, coucheControllersTestPath);
			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("controllers/desktop", coucheControllersDesktopTestPath);
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("controllers/desktop/accueil", coucheControllersDesktopAccueilTestPath);			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("controllers/desktop/metier", coucheControllersDesktopMetierTestPath);				
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("controllers/desktop/utilitaires", coucheControllersDesktopUtilitairesTestPath);			
			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("controllers/web", coucheControllersWebTestPath);
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("controllers/web/accueil", coucheControllersWebAccueilTestPath);			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("controllers/web/metier", coucheControllersWebMetierTestPath);			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("controllers/web/utilitaires", coucheControllersWebUtilitairesTestPath);	
			
			// VUES
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put(VUES, coucheVuesTestPath);
			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("vues/desktop", coucheVuesDesktopTestPath);
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("vues/desktop/accueil", coucheVuesDesktopAccueilTestPath);			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("vues/desktop/metier", coucheVuesDesktopMetierTestPath);				
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("vues/desktop/utilitaires", coucheVuesDesktopUtilitairesTestPath);			
			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("vues/web", coucheVuesWebTestPath);
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("vues/web/accueil", coucheVuesWebAccueilTestPath);			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("vues/web/metier", coucheVuesWebMetierTestPath);			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("vues/web/utilitaires", coucheVuesWebUtilitairesTestPath);
			
			// MODEL
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put(MODEL, coucheModelTestPath);			

			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/dto", coucheModelDTOTestPath);
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/dto/metier", coucheModelDTOMetierTestPath);
			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/metier", coucheModelMetierTestPath);

			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/persistence", coucheModelPersistenceTestPath);
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/persistence/accueil", coucheModelPersistenceAccueilTestPath);
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/persistence/daoexceptions", coucheModelPersistenceDaoexceptionsTestPath);
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/persistence/metier", coucheModelPersistenceMetierTestPath);

			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/services", coucheModelServicesTestPath);
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/services/accueil", coucheModelServicesAccueilTestPath);
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/services/metier", coucheModelServicesMetierTestPath);
			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/services/transformeurs", coucheModelServicesTransformeursTestPath);
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/services/transformeurs/metier", coucheModelServicesTransformeursMetierTestPath);
			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/services/utilitaires", coucheModelServicesUtilitairesTestPath);
			
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/services/valideurs", coucheModelServicesValideursTestPath);
			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/services/valideurs/metier", coucheModelServicesValideursMetierTestPath);

			ARBORESCENCE_TEST_PROJET_SOURCE_MAP.put("model/utilitaires", coucheModelUtilitairesTestPath);
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterArborescenceTestMap().____________________________
	

	
	/**
	 * alimente la <b>Map des répertoires externes</b> 
	 * dans le projet source avec :
	 * <ul>
	 * <li>String : le nom du répertoire externe 
	 * comme <code>"data"</code></li>
	 * <li>Path : l'attribut contenant le Path absolu 
	 * du répertoire comme <code>dataPath</code></li>
	 * </ul>
	 */
	private static void alimenterArborescenceRepExternesMap() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			// REPERTOIRES EXTERNES
			ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP.put("conception_appli", conceptionAppliPath);
			ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP.put("data", dataPath);
			ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP.put("data/H2", dataH2Path);
			ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP.put("data/hsqldb", dataHSQLDBPath);
			ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP.put("data/JAXB", dataJAXBPath);
			ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP.put("data/scripts_sql", dataScriptsSqlPath);
			ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP.put("javadoc", javadocPath);
			ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP.put("javadoc/images", javadocImagesPath);
			ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP.put("logs", logsPath);
			ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP.put("rapports_controle", rapportsControlePath);
			ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP.put("ressources_externes", ressourcesExternesPath);
			
		} // Fin de synchronized._______________________
		
	} // Fin de alimenterArborescenceRepExternesMap()._____________________
	
	
	
	/**
	 * fournit une String pour l'affichage de 
	 * ARBORESCENCE_PROJET_SOURCE.<br/>
	 *
	 * @return : String.<br/>
	 */
	public static String afficherArborescence() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			for (final Path path : ARBORESCENCE_PROJET_SOURCE) {
				
				if (path != null) {
					stb.append(path.toString());
					stb.append(System.getProperty(LINE_SEPARATOR));
				}
				
			}
			
			return stb.toString();
			
		} // Fin de synchronized._______________________		
		
	} // Fin de afficherArborescence().____________________________________
	

	
	/**
	 * fournit une String pour l'affichage de 
	 * ARBORESCENCE_MAIN_PROJET_SOURCE_MAP.<br/>
	 *
	 * @return : String.<br/>
	 */
	public static String afficherArborescenceMainMap() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			/* trie la Map sur les Keys. */
			final Map<String, Path> mapTriee 
				= trierMap(ARBORESCENCE_MAIN_PROJET_SOURCE_MAP);
			
			final Set<Entry<String, Path>> entrySet 
				= mapTriee.entrySet();
						
			final Iterator<Entry<String, Path>> ite = entrySet.iterator();
			
			while (ite.hasNext()) {
				
				final Entry<String, Path> entry = ite.next();
				final String key = entry.getKey();
				final Path value = entry.getValue();
				
				final String ligne = 
						String.format(
								Locale.getDefault()
								, "chemin : %1$-40s      path : %2$-45s"
								, key
								, value.toString());
				
				stb.append(ligne);
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
			
			return stb.toString();
			
		} // Fin de synchronized._______________________
		
	} // Fin de afficherArborescenceMainMap()._____________________________

	
	
	/**
	 * fournit une String pour l'affichage de 
	 * ARBORESCENCE_TEST_PROJET_SOURCE_MAP.<br/>
	 *
	 * @return : String.<br/>
	 */
	public static String afficherArborescenceTestMap() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			/* trie la Map sur les Keys. */
			final Map<String, Path> mapTriee 
				= trierMap(ARBORESCENCE_TEST_PROJET_SOURCE_MAP);
			
			final Set<Entry<String, Path>> entrySet 
				= mapTriee.entrySet();
					
			final Iterator<Entry<String, Path>> ite = entrySet.iterator();
			
			while (ite.hasNext()) {
				
				final Entry<String, Path> entry = ite.next();
				final String key = entry.getKey();
				final Path value = entry.getValue();
				
				final String ligne = 
						String.format(
								Locale.getDefault()
								, "chemin : %1$-40s      path : %2$-45s"
								, key
								, value.toString());
				
				stb.append(ligne);
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
			
			return stb.toString();
			
		} // Fin de synchronized._______________________
		
	} // Fin de afficherArborescenceTestMap()._____________________________
	
	
	
	/**
	 * fournit une String pour l'affichage de 
	 * ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP.<br/>
	 *
	 * @return : String.<br/>
	 */
	public static String afficherArborescenceRepExtMap() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			final StringBuilder stb = new StringBuilder();
			
			/* trie la Map sur les Keys. */
			final Map<String, Path> mapTriee 
				= trierMap(ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP);
			
			final Set<Entry<String, Path>> entrySet 
				= mapTriee.entrySet();
					
			final Iterator<Entry<String, Path>> ite = entrySet.iterator();
			
			while (ite.hasNext()) {
				
				final Entry<String, Path> entry = ite.next();
				final String key = entry.getKey();
				final Path value = entry.getValue();
				
				final String ligne = 
						String.format(
								Locale.getDefault()
								, "chemin : %1$-40s      path : %2$-45s"
								, key
								, value.toString());
				
				stb.append(ligne);
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
			
			return stb.toString();
			
		} // Fin de synchronized._______________________
		
	} // Fin de afficherArborescenceRepExtMap().___________________________
	
	
		
	/**
	 * fournit le nombre de répertoires 
	 * à créer dans le projet source.<br/>
	 *
	 * @return : int : 
	 * nombre de répertoires à créer dans le projet source.<br/>
	 */
	public static int fournirNombreRepACreer() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			return ARBORESCENCE_PROJET_SOURCE.size();
					
		} // Fin de synchronized._______________________	
		
	} // Fin de fournirNombreRepACreer().__________________________________
	
	
	
	/**
	 * <b>Trie une Map sur sa Key (String) 
	 * en respectant l'ordre alphabétique</b>.<br/>
	 * <ul>
	 * <li>utilise un EntryStringComparator 
	 * pour comparer les Entry de la Map sur leur Key (String).</li>
	 * </ul>
	 * - retourne null si pMap == null.<br/>
	 * <br/>
	 * 
	 * @param pMap : Map&lt;String,Path&gt; : 
	 * map à trier sur la Key.<br/>
	 * 
	 * @return : Map&lt;String,Path&gt; : 
	 * map triée sur la Key.<br/>
	 */
	public static Map<String, Path> trierMap(
			final Map<String, Path> pMap){

		synchronized (ArboresceurProjetSource.class) {

			/* retourne null si pMap == null. */
			if (pMap == null) {
				return null;
			}
			
			/* extrait la liste des Keys des Entry de la Map. */
			final List<Entry<String, Path>> list 
				= new LinkedList<Map.Entry<String, Path>>(pMap.entrySet());

			/* Trie la liste des Keys des Entry de la Map. */
			Collections.sort(list, new EntryStringComparator());

			// créer une nouvelle Map à partir de LinkedList triée.
			final Map<String, Path> mapTriee 
				= new LinkedHashMap<String, Path>();

			for (final Entry<String, Path> entry : list) {
				mapTriee.put(entry.getKey(), entry.getValue());
			}

			return mapTriee;

		} // Fin de synchronized._______________________
         
    } // Fin de trierMap(...)._____________________________________________
	

	
	/**
	 * calcule le path absolu des src/main/java 
	 * <b>srcMainJavaPath</b> dans le projet source.<br/>
	 * - ne fait rien si projetSourcePath == null.<br/>
	 * - projetSourcePath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 */
	private static void calculerSrcMainJavaPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (projetSourcePath != null) {
				srcMainJavaPath 
					= projetSourcePath
						.resolve(SRC_MAIN_JAVA_PATH_RELATIF);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerSrcMainJavaPath()._________________________________


	
	/**
	 * calcule le path absolu des src/main/resources 
	 * <b>srcMainResourcesPath</b> dans le projet source.<br/>
	 * - ne fait rien si projetSourcePath == null.<br/>
	 * - projetSourcePath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 */
	private static void calculerSrcMainResourcesPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (projetSourcePath != null) {
				srcMainResourcesPath 
					= projetSourcePath
						.resolve(SRC_MAIN_RESOURCES_PATH_RELATIF);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerSrcMainResourcesPath().____________________________

	
	
	/**
	 * calcule le path absolu des src/main/resources/META-INF 
	 * <b>srcMainResourcesMetaInfPath</b> dans le projet source.<br/>
	 * srcMainResourcesPath/ + META-INF<br/>
	 * - ne fait rien si srcMainResourcesPath == null.<br/>
	 * - srcMainResourcesPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 */
	private static void calculerSrcMainResourcesMetaInfPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (srcMainResourcesPath != null) {
				srcMainResourcesMetaInfPath 
					= srcMainResourcesPath
						.resolve("META-INF");
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerSrcMainResourcesMetaInfPath()._____________________
	
	
	
	/**
	 * calcule le path absolu des src/test/java 
	 * <b>srcTestJavaPath</b> dans le projet source.<br/>
	 * - ne fait rien si projetSourcePath == null.<br/>
	 * - projetSourcePath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 */
	private static void calculerSrcTestJavaPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (projetSourcePath != null) {
				srcTestJavaPath 
					= projetSourcePath
						.resolve(SRC_TEST_JAVA_PATH_RELATIF);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerSrcTestJavaPath()._________________________________


	
	/**
	 * calcule le path absolu des src/test/resources 
	 * <b>srcTestResourcesPath</b> dans le projet source.<br/>
	 * - ne fait rien si projetSourcePath == null.<br/>
	 * - projetSourcePath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 */
	private static void calculerSrcTestResourcesPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (projetSourcePath != null) {
				srcTestResourcesPath 
					= projetSourcePath
						.resolve(SRC_TEST_RESOURCES_PATH_RELATIF);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerSrcTestResourcesPath().____________________________

	
	
	/**
	 * calcule le path absolu des src/test/resources/META-INF 
	 * <b>srcTestResourcesMetaInfPath</b> dans le projet source.<br/>
	 * srcTestResourcesPath/ + META-INF<br/>
	 * - ne fait rien si srcTestResourcesPath == null.<br/>
	 * - srcTestResourcesPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 */
	private static void calculerSrcTestResourcesMetaInfPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (srcTestResourcesPath != null) {
				srcTestResourcesMetaInfPath 
					= srcTestResourcesPath
						.resolve("META-INF");
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerSrcTestResourcesMetaInfPath()._____________________


	
	/**
	 * calcule le path absolu des src/main/java/${groupIdPathRelatif} 
	 * <b>racineSourcesJavaPath</b> dans le projet source.<br/>
	 * - ne fait rien si srcMainJavaPath == null.<br/>
	 * - srcMainJavaPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application</code>
	 */
	private static void calculerRacineSourcesJavaPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (srcMainJavaPath != null) {
				racineSourcesJavaPath 
					= srcMainJavaPath
						.resolve(groupIdPathRelatif);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerRacineSourcesJavaPath().___________________________


	
	/**
	 * calcule le path absolu des src/test/java/${groupIdPathRelatif} 
	 * <b>racineTestsJavaPath</b> dans le projet source.<br/>
	 * - ne fait rien si srcTestJavaPath == null.<br/>
	 * - srcTestJavaPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application</code>
	 */
	private static void calculerRacineTestsJavaPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (srcTestJavaPath != null) {
				racineTestsJavaPath 
					= srcTestJavaPath
						.resolve(groupIdPathRelatif);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerRacineTestsJavaPath()._____________________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche apptechnic</b>
	 * <b>coucheAppTechnicMainPath</b> dans le projet source.<br/>
	 * racineSourcesJavaPath + apptechnic<br/>
	 * - ne fait rien si racineSourcesJavaPath == null.<br/>
	 * - racineSourcesJavaPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/apptechnic</code>
	 */
	private static void calculerCoucheAppTechnicMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (racineSourcesJavaPath != null) {
				coucheAppTechnicMainPath 
					= racineSourcesJavaPath
						.resolve(APPTECHNIC);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheAppTechnicMainPath().________________________


	
	/**
	 * calcule le <b>path absolu des tests de la couche apptechnic</b>
	 * <b>coucheAppTechnicTestPath</b> dans le projet source.<br/>
	 * racineTestsJavaPath + apptechnic<br/>
	 * - ne fait rien si racineTestsJavaPath == null.<br/>
	 * - racineTestsJavaPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/apptechnic</code>
	 */
	private static void calculerCoucheAppTechnicTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (racineTestsJavaPath != null) {
				coucheAppTechnicTestPath 
					= racineTestsJavaPath
						.resolve(APPTECHNIC);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheAppTechnicTestPath().________________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche controllers</b>
	 * <b>coucheControllersMainPath</b> dans le projet source.<br/>
	 * racineSourcesJavaPath + controllers<br/>
	 * - ne fait rien si racineSourcesJavaPath == null.<br/>
	 * - racineSourcesJavaPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/controllers</code>
	 */
	private static void calculerCoucheControllersMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (racineSourcesJavaPath != null) {
				coucheControllersMainPath 
					= racineSourcesJavaPath
						.resolve(CONTROLLERS);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersMainPath()._______________________


	
	/**
	 * calcule le <b>path absolu des tests de la couche controllers</b>
	 * <b>coucheControllersTestPath</b> dans le projet source.<br/>
	 * racineTestsJavaPath + controllers<br/>
	 * - ne fait rien si racineTestsJavaPath == null.<br/>
	 * - racineTestsJavaPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/controllers</code>
	 */
	private static void calculerCoucheControllersTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (racineTestsJavaPath != null) {
				coucheControllersTestPath 
					= racineTestsJavaPath
						.resolve(CONTROLLERS);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersTestPath()._______________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * controllers/desktop</b>
	 * <b>coucheControllersDesktopMainPath</b> dans le projet source.<br/>
	 * coucheControllersMainPath + desktop<br/>
	 * - ne fait rien si coucheControllersMainPath == null.<br/>
	 * - coucheControllersMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/controllers/desktop</code>
	 */
	private static void calculerCoucheControllersDesktopMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersMainPath != null) {
				coucheControllersDesktopMainPath 
					= coucheControllersMainPath
						.resolve(DESKTOP);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersDesktopMainPath().________________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * controllers/desktop</b>
	 * <b>coucheControllersDesktopTestPath</b> dans le projet source.<br/>
	 * coucheControllersTestPath + desktop<br/>
	 * - ne fait rien si coucheControllersTestPath == null.<br/>
	 * - coucheControllersTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/controllers/desktop</code>
	 */
	private static void calculerCoucheControllersDesktopTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersTestPath != null) {
				coucheControllersDesktopTestPath 
					= coucheControllersTestPath
						.resolve(DESKTOP);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersDesktopTestPath().________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * controllers/desktop/accueil</b>
	 * <b>coucheControllersDesktopAccueilMainPath</b> dans le projet source.<br/>
	 * coucheControllersDesktopMainPath + accueil<br/>
	 * - ne fait rien si coucheControllersDesktopMainPath == null.<br/>
	 * - coucheControllersDesktopMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * controllers/desktop/accueil</code>
	 */
	private static void calculerCoucheControllersDesktopAccueilMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersDesktopMainPath != null) {
				coucheControllersDesktopAccueilMainPath 
					= coucheControllersDesktopMainPath
						.resolve(ACCUEIL);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersDesktopAccueilMainPath()._________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * controllers/desktop/accueil</b>
	 * <b>coucheControllersDesktopAccueilTestPath</b> dans le projet source.<br/>
	 * coucheControllersDesktopTestPath + accueil<br/>
	 * - ne fait rien si coucheControllersDesktopTestPath == null.<br/>
	 * - coucheControllersDesktopTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * controllers/desktop/accueil</code>
	 */
	private static void calculerCoucheControllersDesktopAccueilTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersDesktopTestPath != null) {
				coucheControllersDesktopAccueilTestPath 
					= coucheControllersDesktopTestPath
						.resolve(ACCUEIL);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersDesktopAccueilTestPath()._________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * controllers/desktop/metier</b>
	 * <b>coucheControllersDesktopMetierMainPath</b> dans le projet source.<br/>
	 * coucheControllersDesktopMainPath + metier<br/>
	 * - ne fait rien si coucheControllersDesktopMainPath == null.<br/>
	 * - coucheControllersDesktopMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * controllers/desktop/metier</code>
	 */
	private static void calculerCoucheControllersDesktopMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersDesktopMainPath != null) {
				coucheControllersDesktopMetierMainPath 
					= coucheControllersDesktopMainPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersDesktopMetierMainPath().__________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * controllers/desktop/metier</b>
	 * <b>coucheControllersDesktopMetierTestPath</b> dans le projet source.<br/>
	 * coucheControllersDesktopTestPath + metier<br/>
	 * - ne fait rien si coucheControllersDesktopTestPath == null.<br/>
	 * - coucheControllersDesktopTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * controllers/desktop/metier</code>
	 */
	private static void calculerCoucheControllersDesktopMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersDesktopTestPath != null) {
				coucheControllersDesktopMetierTestPath 
					= coucheControllersDesktopTestPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersDesktopMetierTestPath().__________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * controllers/desktop/utilitaires</b>
	 * <b>coucheControllersDesktopUtilitairesMainPath</b> dans le projet source.<br/>
	 * coucheControllersDesktopMainPath + utilitaires<br/>
	 * - ne fait rien si coucheControllersDesktopMainPath == null.<br/>
	 * - coucheControllersDesktopMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * controllers/desktop/utilitaires</code>
	 */
	private static void calculerCoucheControllersDesktopUtilitairesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersDesktopMainPath != null) {
				coucheControllersDesktopUtilitairesMainPath 
					= coucheControllersDesktopMainPath
						.resolve(UTILITAIRES);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersDesktopUtilitairesMainPath()._____


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * controllers/desktop/utilitaires</b>
	 * <b>coucheControllersDesktopUtilitairesTestPath</b> dans le projet source.<br/>
	 * coucheControllersDesktopTestPath + utilitaires<br/>
	 * - ne fait rien si coucheControllersDesktopTestPath == null.<br/>
	 * - coucheControllersDesktopTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * controllers/desktop/utilitaires</code>
	 */
	private static void calculerCoucheControllersDesktopUtilitairesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersDesktopTestPath != null) {
				coucheControllersDesktopUtilitairesTestPath 
					= coucheControllersDesktopTestPath
						.resolve(UTILITAIRES);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersDesktopUtilitairesTestPath()._____

	
	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * controllers/web</b>
	 * <b>coucheControllersWebMainPath</b> dans le projet source.<br/>
	 * coucheControllersMainPath + web<br/>
	 * - ne fait rien si coucheControllersMainPath == null.<br/>
	 * - coucheControllersMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/controllers/web</code>
	 */
	private static void calculerCoucheControllersWebMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersMainPath != null) {
				coucheControllersWebMainPath 
					= coucheControllersMainPath
						.resolve(WEB);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersWebMainPath().____________________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * controllers/web</b>
	 * <b>coucheControllersWebTestPath</b> dans le projet source.<br/>
	 * coucheControllersTestPath + web<br/>
	 * - ne fait rien si coucheControllersTestPath == null.<br/>
	 * - coucheControllersTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/controllers/web</code>
	 */
	private static void calculerCoucheControllersWebTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersTestPath != null) {
				coucheControllersWebTestPath 
					= coucheControllersTestPath
						.resolve(WEB);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersWebTestPath().____________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * controllers/web/accueil</b>
	 * <b>coucheControllersWebAccueilMainPath</b> dans le projet source.<br/>
	 * coucheControllersWebMainPath + accueil<br/>
	 * - ne fait rien si coucheControllersWebMainPath == null.<br/>
	 * - coucheControllersWebMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * controllers/web/accueil</code>
	 */
	private static void calculerCoucheControllersWebAccueilMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersWebMainPath != null) {
				coucheControllersWebAccueilMainPath 
					= coucheControllersWebMainPath
						.resolve(ACCUEIL);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersWebAccueilMainPath()._____________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * controllers/web/accueil</b>
	 * <b>coucheControllersWebAccueilTestPath</b> dans le projet source.<br/>
	 * coucheControllersWebTestPath + accueil<br/>
	 * - ne fait rien si coucheControllersWebTestPath == null.<br/>
	 * - coucheControllersWebTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * controllers/web/accueil</code>
	 */
	private static void calculerCoucheControllersWebAccueilTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersWebTestPath != null) {
				coucheControllersWebAccueilTestPath 
					= coucheControllersWebTestPath
						.resolve(ACCUEIL);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersWebAccueilTestPath()._____________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * controllers/web/metier</b>
	 * <b>coucheControllersWebMetierMainPath</b> dans le projet source.<br/>
	 * coucheControllersWebMainPath + metier<br/>
	 * - ne fait rien si coucheControllersWebMainPath == null.<br/>
	 * - coucheControllersWebMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * controllers/web/metier</code>
	 */
	private static void calculerCoucheControllersWebMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersWebMainPath != null) {
				coucheControllersWebMetierMainPath 
					= coucheControllersWebMainPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersWebMetierMainPath().______________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * controllers/web/metier</b>
	 * <b>coucheControllersWebMetierTestPath</b> dans le projet source.<br/>
	 * coucheControllersWebTestPath + metier<br/>
	 * - ne fait rien si coucheControllersWebTestPath == null.<br/>
	 * - coucheControllersWebTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * controllers/web/metier</code>
	 */
	private static void calculerCoucheControllersWebMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersWebTestPath != null) {
				coucheControllersWebMetierTestPath 
					= coucheControllersWebTestPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersWebMetierTestPath().______________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * controllers/web/utilitaires</b>
	 * <b>coucheControllersWebUtilitairesMainPath</b> dans le projet source.<br/>
	 * coucheControllersWebMainPath + utilitaires<br/>
	 * - ne fait rien si coucheControllersWebMainPath == null.<br/>
	 * - coucheControllersWebMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * controllers/web/utilitaires</code>
	 */
	private static void calculerCoucheControllersWebUtilitairesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersWebMainPath != null) {
				coucheControllersWebUtilitairesMainPath 
					= coucheControllersWebMainPath
						.resolve(UTILITAIRES);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersWebUtilitairesMainPath()._________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * controllers/web/utilitaires</b>
	 * <b>coucheControllersWebUtilitairesTestPath</b> dans le projet source.<br/>
	 * coucheControllersWebTestPath + utilitaires<br/>
	 * - ne fait rien si coucheControllersWebTestPath == null.<br/>
	 * - coucheControllersWebTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * controllers/web/utilitaires</code>
	 */
	private static void calculerCoucheControllersWebUtilitairesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersWebTestPath != null) {
				coucheControllersWebUtilitairesTestPath 
					= coucheControllersWebTestPath
						.resolve(UTILITAIRES);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheControllersWebUtilitairesTestPath()._________


	
	/**
	 * calcule le <b>path absolu des sources de la couche vues</b>
	 * <b>coucheVuesMainPath</b> dans le projet source.<br/>
	 * racineSourcesJavaPath + vues<br/>
	 * - ne fait rien si racineSourcesJavaPath == null.<br/>
	 * - racineSourcesJavaPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/vues</code>
	 */
	private static void calculerCoucheVuesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (racineSourcesJavaPath != null) {
				coucheVuesMainPath 
					= racineSourcesJavaPath
						.resolve(VUES);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesMainPath().______________________________


	
	/**
	 * calcule le <b>path absolu des tests de la couche vues</b>
	 * <b>coucheVuesTestPath</b> dans le projet source.<br/>
	 * racineTestsJavaPath + vues<br/>
	 * - ne fait rien si racineTestsJavaPath == null.<br/>
	 * - racineTestsJavaPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/vues</code>
	 */
	private static void calculerCoucheVuesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (racineTestsJavaPath != null) {
				coucheVuesTestPath 
					= racineTestsJavaPath
						.resolve(VUES);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesTestPath().______________________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * vues/desktop</b>
	 * <b>coucheVuesDesktopMainPath</b> dans le projet source.<br/>
	 * coucheVuesMainPath + desktop<br/>
	 * - ne fait rien si coucheVuesMainPath == null.<br/>
	 * - coucheVuesMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/vues/desktop</code>
	 */
	private static void calculerCoucheVuesDesktopMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesMainPath != null) {
				coucheVuesDesktopMainPath 
					= coucheVuesMainPath
						.resolve(DESKTOP);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesDesktopMainPath()._______________________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * vues/desktop</b>
	 * <b>coucheVuesDesktopTestPath</b> dans le projet source.<br/>
	 * coucheVuesTestPath + desktop<br/>
	 * - ne fait rien si coucheVuesTestPath == null.<br/>
	 * - coucheVuesTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/vues/desktop</code>
	 */
	private static void calculerCoucheVuesDesktopTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesTestPath != null) {
				coucheVuesDesktopTestPath 
					= coucheVuesTestPath
						.resolve(DESKTOP);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesDesktopTestPath()._______________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * vues/desktop/accueil</b>
	 * <b>coucheVuesDesktopAccueilMainPath</b> dans le projet source.<br/>
	 * coucheVuesDesktopMainPath + accueil<br/>
	 * - ne fait rien si coucheVuesDesktopMainPath == null.<br/>
	 * - coucheVuesDesktopMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * vues/desktop/accueil</code>
	 */
	private static void calculerCoucheVuesDesktopAccueilMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesDesktopMainPath != null) {
				coucheVuesDesktopAccueilMainPath 
					= coucheVuesDesktopMainPath
						.resolve(ACCUEIL);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesDesktopAccueilMainPath().________________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * vues/desktop/accueil</b>
	 * <b>coucheVuesDesktopAccueilTestPath</b> dans le projet source.<br/>
	 * coucheVuesDesktopTestPath + accueil<br/>
	 * - ne fait rien si coucheVuesDesktopTestPath == null.<br/>
	 * - coucheVuesDesktopTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * vues/desktop/accueil</code>
	 */
	private static void calculerCoucheVuesDesktopAccueilTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesDesktopTestPath != null) {
				coucheVuesDesktopAccueilTestPath 
					= coucheVuesDesktopTestPath
						.resolve(ACCUEIL);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesDesktopAccueilTestPath().________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * vues/desktop/metier</b>
	 * <b>coucheVuesDesktopMetierMainPath</b> dans le projet source.<br/>
	 * coucheVuesDesktopMainPath + metier<br/>
	 * - ne fait rien si coucheVuesDesktopMainPath == null.<br/>
	 * - coucheVuesDesktopMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * vues/desktop/metier</code>
	 */
	private static void calculerCoucheVuesDesktopMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesDesktopMainPath != null) {
				coucheVuesDesktopMetierMainPath 
					= coucheVuesDesktopMainPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesDesktopMetierMainPath()._________________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * vues/desktop/metier</b>
	 * <b>coucheVuesDesktopMetierTestPath</b> dans le projet source.<br/>
	 * coucheVuesDesktopTestPath + metier<br/>
	 * - ne fait rien si coucheVuesDesktopTestPath == null.<br/>
	 * - coucheVuesDesktopTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * vues/desktop/metier</code>
	 */
	private static void calculerCoucheVuesDesktopMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesDesktopTestPath != null) {
				coucheVuesDesktopMetierTestPath 
					= coucheVuesDesktopTestPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesDesktopMetierTestPath()._________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * vues/desktop/utilitaires</b>
	 * <b>coucheVuesDesktopUtilitairesMainPath</b> dans le projet source.<br/>
	 * coucheVuesDesktopMainPath + utilitaires<br/>
	 * - ne fait rien si coucheVuesDesktopMainPath == null.<br/>
	 * - coucheVuesDesktopMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * vues/desktop/utilitaires</code>
	 */
	private static void calculerCoucheVuesDesktopUtilitairesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesDesktopMainPath != null) {
				coucheVuesDesktopUtilitairesMainPath 
					= coucheVuesDesktopMainPath
						.resolve(UTILITAIRES);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesDesktopUtilitairesMainPath().____________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * vues/desktop/utilitaires</b>
	 * <b>coucheVuesDesktopUtilitairesTestPath</b> dans le projet source.<br/>
	 * coucheVuesDesktopTestPath + utilitaires<br/>
	 * - ne fait rien si coucheVuesDesktopTestPath == null.<br/>
	 * - coucheVuesDesktopTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * vues/desktop/utilitaires</code>
	 */
	private static void calculerCoucheVuesDesktopUtilitairesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesDesktopTestPath != null) {
				coucheVuesDesktopUtilitairesTestPath 
					= coucheVuesDesktopTestPath
						.resolve(UTILITAIRES);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesDesktopUtilitairesTestPath().____________

	
	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * vues/web</b>
	 * <b>coucheVuesWebMainPath</b> dans le projet source.<br/>
	 * coucheVuesMainPath + web<br/>
	 * - ne fait rien si coucheVuesMainPath == null.<br/>
	 * - coucheVuesMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/vues/web</code>
	 */
	private static void calculerCoucheVuesWebMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesMainPath != null) {
				coucheVuesWebMainPath 
					= coucheVuesMainPath
						.resolve(WEB);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesWebMainPath().___________________________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * vues/web</b>
	 * <b>coucheVuesWebTestPath</b> dans le projet source.<br/>
	 * coucheVuesTestPath + web<br/>
	 * - ne fait rien si coucheVuesTestPath == null.<br/>
	 * - coucheVuesTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/vues/web</code>
	 */
	private static void calculerCoucheVuesWebTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesTestPath != null) {
				coucheVuesWebTestPath 
					= coucheVuesTestPath
						.resolve(WEB);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesWebTestPath().___________________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * vues/web/accueil</b>
	 * <b>coucheVuesWebAccueilMainPath</b> dans le projet source.<br/>
	 * coucheVuesWebMainPath + accueil<br/>
	 * - ne fait rien si coucheVuesWebMainPath == null.<br/>
	 * - coucheVuesWebMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * vues/web/accueil</code>
	 */
	private static void calculerCoucheVuesWebAccueilMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesWebMainPath != null) {
				coucheVuesWebAccueilMainPath 
					= coucheVuesWebMainPath
						.resolve(ACCUEIL);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesWebAccueilMainPath().____________________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * vues/web/accueil</b>
	 * <b>coucheVuesWebAccueilTestPath</b> dans le projet source.<br/>
	 * coucheVuesWebTestPath + accueil<br/>
	 * - ne fait rien si coucheVuesWebTestPath == null.<br/>
	 * - coucheVuesWebTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * vues/web/accueil</code>
	 */
	private static void calculerCoucheVuesWebAccueilTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesWebTestPath != null) {
				coucheVuesWebAccueilTestPath 
					= coucheVuesWebTestPath
						.resolve(ACCUEIL);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesWebAccueilTestPath().____________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * vues/web/metier</b>
	 * <b>coucheVuesWebMetierMainPath</b> dans le projet source.<br/>
	 * coucheVuesWebMainPath + metier<br/>
	 * - ne fait rien si coucheVuesWebMainPath == null.<br/>
	 * - coucheVuesWebMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * vues/web/metier</code>
	 */
	private static void calculerCoucheVuesWebMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesWebMainPath != null) {
				coucheVuesWebMetierMainPath 
					= coucheVuesWebMainPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesWebMetierMainPath()._____________________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * vues/web/metier</b>
	 * <b>coucheVuesWebMetierTestPath</b> dans le projet source.<br/>
	 * coucheVuesWebTestPath + metier<br/>
	 * - ne fait rien si coucheVuesWebTestPath == null.<br/>
	 * - coucheVuesWebTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * vues/web/metier</code>
	 */
	private static void calculerCoucheVuesWebMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesWebTestPath != null) {
				coucheVuesWebMetierTestPath 
					= coucheVuesWebTestPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesWebMetierTestPath()._____________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * vues/web/utilitaires</b>
	 * <b>coucheVuesWebUtilitairesMainPath</b> dans le projet source.<br/>
	 * coucheVuesWebMainPath + utilitaires<br/>
	 * - ne fait rien si coucheVuesWebMainPath == null.<br/>
	 * - coucheVuesWebMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * vues/web/utilitaires</code>
	 */
	private static void calculerCoucheVuesWebUtilitairesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesWebMainPath != null) {
				coucheVuesWebUtilitairesMainPath 
					= coucheVuesWebMainPath
						.resolve(UTILITAIRES);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesWebUtilitairesMainPath().________________


	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * vues/web/utilitaires</b>
	 * <b>coucheVuesWebUtilitairesTestPath</b> dans le projet source.<br/>
	 * coucheVuesWebTestPath + utilitaires<br/>
	 * - ne fait rien si coucheVuesWebTestPath == null.<br/>
	 * - coucheVuesWebTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * vues/web/utilitaires</code>
	 */
	private static void calculerCoucheVuesWebUtilitairesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesWebTestPath != null) {
				coucheVuesWebUtilitairesTestPath 
					= coucheVuesWebTestPath
						.resolve(UTILITAIRES);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheVuesWebUtilitairesTestPath().________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche model</b>
	 * <b>coucheModelMainPath</b> dans le projet source.<br/>
	 * racineSourcesJavaPath + model<br/>
	 * - ne fait rien si racineSourcesJavaPath == null.<br/>
	 * - racineSourcesJavaPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/model</code>
	 */
	private static void calculerCoucheModelMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (racineSourcesJavaPath != null) {
				coucheModelMainPath 
					= racineSourcesJavaPath
						.resolve(MODEL);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelMainPath()._____________________________


	
	/**
	 * calcule le <b>path absolu des tests de la couche model</b>
	 * <b>coucheModelTestPath</b> dans le projet source.<br/>
	 * racineTestsJavaPath + model<br/>
	 * - ne fait rien si racineTestsJavaPath == null.<br/>
	 * - racineTestsJavaPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/model</code>
	 */
	private static void calculerCoucheModelTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (racineTestsJavaPath != null) {
				coucheModelTestPath 
					= racineTestsJavaPath
						.resolve(MODEL);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelTestPath()._____________________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/dto</b>
	 * <b>coucheModelDTOMainPath</b> dans le projet source.<br/>
	 * coucheModelMainPath + dto<br/>
	 * - ne fait rien si coucheModelMainPath == null.<br/>
	 * - coucheModelMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/dto</code>
	 */
	private static void calculerCoucheModelDTOMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelMainPath != null) {
				coucheModelDTOMainPath 
					= coucheModelMainPath
						.resolve("dto");
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelDTOMainPath().__________________________
	

	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * model/dto</b>
	 * <b>coucheModelDTOTestPath</b> dans le projet source.<br/>
	 * coucheModelTestPath + dto<br/>
	 * - ne fait rien si coucheModelTestPath == null.<br/>
	 * - coucheModelTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/dto</code>
	 */
	private static void calculerCoucheModelDTOTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelTestPath != null) {
				coucheModelDTOTestPath 
					= coucheModelTestPath
						.resolve("dto");
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelDTOTestPath().__________________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/dto/metier</b>
	 * <b>coucheModelDTOMetierMainPath</b> dans le projet source.<br/>
	 * coucheModelDTOMainPath + metier<br/>
	 * - ne fait rien si coucheModelDTOMainPath == null.<br/>
	 * - coucheModelDTOMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/dto/metier</code>
	 */
	private static void calculerCoucheModelDTOMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelDTOMainPath != null) {
				coucheModelDTOMetierMainPath 
					= coucheModelDTOMainPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelDTOMetierMainPath().____________________
	

	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * model/dto/metier</b>
	 * <b>coucheModelDTOMetierTestPath</b> dans le projet source.<br/>
	 * coucheModelDTOTestPath + metier<br/>
	 * - ne fait rien si coucheModelDTOTestPath == null.<br/>
	 * - coucheModelDTOTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/dto/metier</code>
	 */
	private static void calculerCoucheModelDTOMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelDTOTestPath != null) {
				coucheModelDTOMetierTestPath 
					= coucheModelDTOTestPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelDTOMetierTestPath().____________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/metier</b>
	 * <b>coucheModelMetierMainPath</b> dans le projet source.<br/>
	 * coucheModelMainPath + metier<br/>
	 * - ne fait rien si coucheModelMainPath == null.<br/>
	 * - coucheModelMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/metier</code>
	 */
	private static void calculerCoucheModelMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelMainPath != null) {
				coucheModelMetierMainPath 
					= coucheModelMainPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelMetierMainPath()._______________________
	

	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * model/metier</b>
	 * <b>coucheModelMetierTestPath</b> dans le projet source.<br/>
	 * coucheModelTestPath + metier<br/>
	 * - ne fait rien si coucheModelTestPath == null.<br/>
	 * - coucheModelTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/metier</code>
	 */
	private static void calculerCoucheModelMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelTestPath != null) {
				coucheModelMetierTestPath 
					= coucheModelTestPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelMetierTestPath()._______________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/persistence</b>
	 * <b>coucheModelPersistenceMainPath</b> dans le projet source.<br/>
	 * coucheModelMainPath + persistence<br/>
	 * - ne fait rien si coucheModelMainPath == null.<br/>
	 * - coucheModelMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/persistence</code>
	 */
	private static void calculerCoucheModelPersistenceMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelMainPath != null) {
				coucheModelPersistenceMainPath 
					= coucheModelMainPath
						.resolve("persistence");
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelPersistenceMainPath().__________________
	

	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * model/persistence</b>
	 * <b>coucheModelPersistenceTestPath</b> dans le projet source.<br/>
	 * coucheModelTestPath + persistence<br/>
	 * - ne fait rien si coucheModelTestPath == null.<br/>
	 * - coucheModelTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/persistence</code>
	 */
	private static void calculerCoucheModelPersistenceTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelTestPath != null) {
				coucheModelPersistenceTestPath 
					= coucheModelTestPath
						.resolve("persistence");
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelPersistenceTestPath().__________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/persistence/accueil</b>
	 * <b>coucheModelPersistenceAccueilMainPath</b> dans le projet source.<br/>
	 * coucheModelPersistenceMainPath + accueil<br/>
	 * - ne fait rien si coucheModelPersistenceMainPath == null.<br/>
	 * - coucheModelPersistenceMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/persistence/accueil</code>
	 */
	private static void calculerCoucheModelPersistenceAccueilMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelPersistenceMainPath != null) {
				coucheModelPersistenceAccueilMainPath 
					= coucheModelPersistenceMainPath
						.resolve(ACCUEIL);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelPersistenceAccueilMainPath().___________
	

	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * model/persistence/accueil</b>
	 * <b>coucheModelPersistenceAccueilTestPath</b> dans le projet source.<br/>
	 * coucheModelPersistenceTestPath + accueil<br/>
	 * - ne fait rien si coucheModelPersistenceTestPath == null.<br/>
	 * - coucheModelPersistenceTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/persistence/accueil</code>
	 */
	private static void calculerCoucheModelPersistenceAccueilTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelPersistenceTestPath != null) {
				coucheModelPersistenceAccueilTestPath 
					= coucheModelPersistenceTestPath
						.resolve(ACCUEIL);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelPersistenceAccueilTestPath().___________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/persistence/daoexceptions</b>
	 * <b>coucheModelPersistenceDaoexceptionsMainPath</b> dans le projet source.<br/>
	 * coucheModelPersistenceMainPath + daoexceptions<br/>
	 * - ne fait rien si coucheModelPersistenceMainPath == null.<br/>
	 * - coucheModelPersistenceMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/persistence/daoexceptions</code>
	 */
	private static void calculerCoucheModelPersistenceDaoexceptionsMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelPersistenceMainPath != null) {
				coucheModelPersistenceDaoexceptionsMainPath 
					= coucheModelPersistenceMainPath
						.resolve("daoexceptions");
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelPersistenceDaoexceptionsMainPath()._____
	

	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * model/persistence/daoexceptions</b>
	 * <b>coucheModelPersistenceDaoexceptionsTestPath</b> dans le projet source.<br/>
	 * coucheModelPersistenceTestPath + daoexceptions<br/>
	 * - ne fait rien si coucheModelPersistenceTestPath == null.<br/>
	 * - coucheModelPersistenceTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/persistence/daoexceptions</code>
	 */
	private static void calculerCoucheModelPersistenceDaoexceptionsTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelPersistenceTestPath != null) {
				coucheModelPersistenceDaoexceptionsTestPath 
					= coucheModelPersistenceTestPath
						.resolve("daoexceptions");
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelPersistenceDaoexceptionsTestPath()._____


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/persistence/metier</b>
	 * <b>coucheModelPersistenceMetierMainPath</b> dans le projet source.<br/>
	 * coucheModelPersistenceMainPath + metier<br/>
	 * - ne fait rien si coucheModelPersistenceMainPath == null.<br/>
	 * - coucheModelPersistenceMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/persistence/metier</code>
	 */
	private static void calculerCoucheModelPersistenceMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelPersistenceMainPath != null) {
				coucheModelPersistenceMetierMainPath 
					= coucheModelPersistenceMainPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelPersistenceMetierMainPath().___________
	

	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * model/persistence/metier</b>
	 * <b>coucheModelPersistenceMetierTestPath</b> dans le projet source.<br/>
	 * coucheModelPersistenceTestPath + metier<br/>
	 * - ne fait rien si coucheModelPersistenceTestPath == null.<br/>
	 * - coucheModelPersistenceTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/persistence/metier</code>
	 */
	private static void calculerCoucheModelPersistenceMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelPersistenceTestPath != null) {
				coucheModelPersistenceMetierTestPath 
					= coucheModelPersistenceTestPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelPersistenceMetierTestPath().___________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/services</b>
	 * <b>coucheModelServicesMainPath</b> dans le projet source.<br/>
	 * coucheModelMainPath + services<br/>
	 * - ne fait rien si coucheModelMainPath == null.<br/>
	 * - coucheModelMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services</code>
	 */
	private static void calculerCoucheModelServicesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelMainPath != null) {
				coucheModelServicesMainPath 
					= coucheModelMainPath
						.resolve("services");
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesMainPath()._____________________
	

	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * model/services</b>
	 * <b>coucheModelServicesTestPath</b> dans le projet source.<br/>
	 * coucheModelTestPath + services<br/>
	 * - ne fait rien si coucheModelTestPath == null.<br/>
	 * - coucheModelTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services</code>
	 */
	private static void calculerCoucheModelServicesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelTestPath != null) {
				coucheModelServicesTestPath 
					= coucheModelTestPath
						.resolve("services");
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesTestPath()._____________________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/services/accueil</b>
	 * <b>coucheModelServicesAccueilMainPath</b> dans le projet source.<br/>
	 * coucheModelServicesMainPath + accueil<br/>
	 * - ne fait rien si coucheModelServicesMainPath == null.<br/>
	 * - coucheModelServicesMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/accueil</code>
	 */
	private static void calculerCoucheModelServicesAccueilMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesMainPath != null) {
				coucheModelServicesAccueilMainPath 
					= coucheModelServicesMainPath
						.resolve(ACCUEIL);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesAccueilMainPath().______________
	

	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * model/services/accueil</b>
	 * <b>coucheModelServicesAccueilTestPath</b> dans le projet source.<br/>
	 * coucheModelServicesTestPath + accueil<br/>
	 * - ne fait rien si coucheModelServicesTestPath == null.<br/>
	 * - coucheModelServicesTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/accueil</code>
	 */
	private static void calculerCoucheModelServicesAccueilTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesTestPath != null) {
				coucheModelServicesAccueilTestPath 
					= coucheModelServicesTestPath
						.resolve(ACCUEIL);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesAccueilTestPath().______________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/services/metier</b>
	 * <b>coucheModelServicesMetierMainPath</b> dans le projet source.<br/>
	 * coucheModelServicesMainPath + metier<br/>
	 * - ne fait rien si coucheModelServicesMainPath == null.<br/>
	 * - coucheModelServicesMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/metier</code>
	 */
	private static void calculerCoucheModelServicesMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesMainPath != null) {
				coucheModelServicesMetierMainPath 
					= coucheModelServicesMainPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesMetierMainPath()._______________
	

	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * model/services/metier</b>
	 * <b>coucheModelServicesMetierTestPath</b> dans le projet source.<br/>
	 * coucheModelServicesTestPath + metier<br/>
	 * - ne fait rien si coucheModelServicesTestPath == null.<br/>
	 * - coucheModelServicesTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/metier</code>
	 */
	private static void calculerCoucheModelServicesMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesTestPath != null) {
				coucheModelServicesMetierTestPath 
					= coucheModelServicesTestPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesMetierTestPath()._______________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/services/transformeurs</b>
	 * <b>coucheModelServicesTransformeursMainPath</b> dans le projet source.<br/>
	 * coucheModelServicesMainPath + transformeurs<br/>
	 * - ne fait rien si coucheModelServicesMainPath == null.<br/>
	 * - coucheModelServicesMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/transformeurs</code>
	 */
	private static void calculerCoucheModelServicesTransformeursMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesMainPath != null) {
				coucheModelServicesTransformeursMainPath 
					= coucheModelServicesMainPath
						.resolve("transformeurs");
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesTransformeursMainPath().________
	

	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/services/transformeurs/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesTransformeursMainPath/ + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/transformeurs/metier</code>
	 */
	private static void calculerCoucheModelServicesTransformeursMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesTransformeursMainPath != null) {
				coucheModelServicesTransformeursMetierMainPath 
					= coucheModelServicesTransformeursMainPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesTransformeursMetierMainPath().__
	
	
	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * model/services/transformeurs</b>
	 * <b>coucheModelServicesTransformeursTestPath</b> dans le projet source.<br/>
	 * coucheModelServicesTestPath + transformeurs<br/>
	 * - ne fait rien si coucheModelServicesTestPath == null.<br/>
	 * - coucheModelServicesTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/transformeurs</code>
	 */
	private static void calculerCoucheModelServicesTransformeursTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesTestPath != null) {
				coucheModelServicesTransformeursTestPath 
					= coucheModelServicesTestPath
						.resolve("transformeurs");
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesTransformeursTestPath().________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/services/transformeurs/metier</b> 
	 * dans les tests du projet source.<br/>
	 * coucheModelServicesTransformeursTestPath/ + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/transformeurs/metier</code>
	 */
	private static void calculerCoucheModelServicesTransformeursMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesTransformeursTestPath != null) {
				coucheModelServicesTransformeursMetierTestPath 
					= coucheModelServicesTransformeursTestPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesTransformeursMetierTestPath().__
	

	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/services/utilitaires</b>
	 * <b>coucheModelServicesUtilitairesMainPath</b> dans le projet source.<br/>
	 * coucheModelServicesMainPath + utilitaires<br/>
	 * - ne fait rien si coucheModelServicesMainPath == null.<br/>
	 * - coucheModelServicesMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/utilitaires</code>
	 */
	private static void calculerCoucheModelServicesUtilitairesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesMainPath != null) {
				coucheModelServicesUtilitairesMainPath 
					= coucheModelServicesMainPath
						.resolve(UTILITAIRES);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesUtilitairesMainPath().__________
	

	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * model/services/utilitaires</b>
	 * <b>coucheModelServicesUtilitairesTestPath</b> dans le projet source.<br/>
	 * coucheModelServicesTestPath + utilitaires<br/>
	 * - ne fait rien si coucheModelServicesTestPath == null.<br/>
	 * - coucheModelServicesTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/utilitaires</code>
	 */
	private static void calculerCoucheModelServicesUtilitairesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesTestPath != null) {
				coucheModelServicesUtilitairesTestPath 
					= coucheModelServicesTestPath
						.resolve(UTILITAIRES);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesUtilitairesTestPath().__________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/services/valideurs</b>
	 * <b>coucheModelServicesValideursMainPath</b> dans le projet source.<br/>
	 * coucheModelServicesMainPath + valideurs<br/>
	 * - ne fait rien si coucheModelServicesMainPath == null.<br/>
	 * - coucheModelServicesMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/valideurs</code>
	 */
	private static void calculerCoucheModelServicesValideursMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesMainPath != null) {
				coucheModelServicesValideursMainPath 
					= coucheModelServicesMainPath
						.resolve("valideurs");
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesValideursMainPath().____________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/services/valideurs/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesValideursMainPath/ + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/valideurs/metier</code>
	 */
	private static void calculerCoucheModelServicesValideursMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesValideursMainPath != null) {
				coucheModelServicesValideursMetierMainPath 
					= coucheModelServicesValideursMainPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesValideursMetierMainPath().______
	

	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * model/services/valideurs</b>
	 * <b>coucheModelServicesValideursTestPath</b> dans le projet source.<br/>
	 * coucheModelServicesTestPath + valideurs<br/>
	 * - ne fait rien si coucheModelServicesTestPath == null.<br/>
	 * - coucheModelServicesTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/valideurs</code>
	 */
	private static void calculerCoucheModelServicesValideursTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesTestPath != null) {
				coucheModelServicesValideursTestPath 
					= coucheModelServicesTestPath
						.resolve("valideurs");
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesValideursTestPath().____________


	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/services/valideurs/metier</b> 
	 * dans les tests du projet source.<br/>
	 * coucheModelServicesValideursTestPath/ + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/valideurs/metier</code>
	 */
	private static void calculerCoucheModelServicesValideursMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesValideursTestPath != null) {
				coucheModelServicesValideursMetierTestPath 
					= coucheModelServicesValideursTestPath
						.resolve(METIER);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelServicesValideursMetierTestPath().______
	

	
	/**
	 * calcule le <b>path absolu des sources de la couche 
	 * model/utilitaires</b>
	 * <b>coucheModelUtilitairesMainPath</b> dans le projet source.<br/>
	 * coucheModelMainPath + utilitaires<br/>
	 * - ne fait rien si coucheModelMainPath == null.<br/>
	 * - coucheModelMainPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/utilitaires</code>
	 */
	private static void calculerCoucheModelUtilitairesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelMainPath != null) {
				coucheModelUtilitairesMainPath 
					= coucheModelMainPath
						.resolve(UTILITAIRES);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelUtilitairesMainPath().__________________
	

	
	/**
	 * calcule le <b>path absolu des tests de la couche 
	 * model/utilitaires</b>
	 * <b>coucheModelUtilitairesTestPath</b> dans le projet source.<br/>
	 * coucheModelTestPath + utilitaires<br/>
	 * - ne fait rien si coucheModelTestPath == null.<br/>
	 * - coucheModelTestPath n'a pas besoin d'exister physiquement 
	 * sur le disque.<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/utilitaires</code>
	 */
	private static void calculerCoucheModelUtilitairesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelTestPath != null) {
				coucheModelUtilitairesTestPath 
					= coucheModelTestPath
						.resolve(UTILITAIRES);
			}
			
		} // Fin de synchronized._______________________
				
	} // Fin de calculerCoucheModelUtilitairesTestPath().__________________


	
	/**
	 * calcule le <b>path absolu du repertoire externe 
	 * conception_appli</b>.<br/>
	 * projetSourcePath + conception_appli<br/>
	 */
	private static void calculerConceptionAppliPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (projetSourcePath != null) {
				conceptionAppliPath 
					= projetSourcePath
						.resolve("conception_appli");
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de calculerConceptionAppliPath()._____________________________


	
	/**
	 * calcule le <b>path absolu du repertoire externe 
	 * data</b>.<br/>
	 * projetSourcePath + data<br/>
	 */
	private static void calculerDataPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (projetSourcePath != null) {
				dataPath 
					= projetSourcePath
						.resolve("data");
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de calculerDataPath().________________________________________


	
	/**
	 * calcule le <b>path absolu du repertoire externe 
	 * dataH2Path = dataPath/ 
	 * + "base-" + ${projetSourceNom} + "-h2"</b>.<br/>
	 */
	private static void calculerDataH2Path() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (dataPath != null) {
				
				final String nomRep = "base-" + projetSourceNom + "-h2";
				
				dataH2Path 
					= dataPath
						.resolve(nomRep);
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de calculerDataH2Path().______________________________________


	
	/**
	 * calcule le <b>path absolu du repertoire externe 
	 * dataHSQLDBPath = dataPath/ 
	 * + "base-" + ${projetSourceNom} + "-hsqldb"</b>.<br/>
	 */
	private static void calculerDataHSQLDBPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (dataPath != null) {
				
				final String nomRep = "base-" + projetSourceNom + "-hsqldb";
				
				dataHSQLDBPath 
					= dataPath
						.resolve(nomRep);
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de calculerDataHSQLDBPath().__________________________________


	
	/**
	 * calcule le <b>path absolu du repertoire externe 
	 * dataJAXBPath = dataPath/ 
	 * + "base-" + ${projetSourceNom} + "-JAXB"</b>.<br/>
	 */
	private static void calculerDataJAXBPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (dataPath != null) {
				
				final String nomRep = "base-" + projetSourceNom + "-JAXB";
				
				dataJAXBPath 
					= dataPath
						.resolve(nomRep);
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de calculerDataJAXBPath().____________________________________


	
	/**
	 * calcule le <b>path absolu du repertoire externe 
	 * dataScriptsSqlPath = dataPath/ 
	 * + scripts_sql</b>.<br/>
	 */
	private static void calculerDataScriptsSqlPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (dataPath != null) {
				
				dataScriptsSqlPath 
					= dataPath
						.resolve("scripts_sql");
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de calculerDataScriptsSqlPath().______________________________

	
	
	/**
	 * calcule le <b>path absolu du repertoire externe 
	 * javadoc</b>.<br/>
	 * projetSourcePath + javadoc<br/>
	 */
	private static void calculerJavadocPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (projetSourcePath != null) {
				javadocPath 
					= projetSourcePath
						.resolve("javadoc");
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de calculerJavadocPath()._____________________________________

	
	
	/**
	 * javadocPath/ + images.<br/>
	 */
	private static void calculerJavadocImagesPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (javadocPath != null) {
				javadocImagesPath 
					= javadocPath
						.resolve("images");
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de calculerJavadocImagesPath()._______________________________


	
	/**
	 * calcule le <b>path absolu du repertoire externe 
	 * logs</b>.<br/>
	 * projetSourcePath/ + logs<br/>
	 */
	private static void calculerLogsPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (projetSourcePath != null) {
				logsPath 
					= projetSourcePath
						.resolve("logs");
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de calculerLogsPath().________________________________________


	
	/**
	 * calcule le <b>path absolu du repertoire externe 
	 * rapports_controle</b>.<br/>
	 * projetSourcePath/ + rapports_controle<br/>
	 */
	private static void calculerRapportsControlePath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (projetSourcePath != null) {
				rapportsControlePath 
					= projetSourcePath
						.resolve("rapports_controle");
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de calculerRapportsControlePath().____________________________


	
	/**
	 * calcule le <b>path absolu du repertoire externe 
	 * ressources_externes</b>.<br/>
	 * projetSourcePath/ + ressources_externes<br/>
	 */
	private static void calculerRessourcesExternesPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (projetSourcePath != null) {
				ressourcesExternesPath 
					= projetSourcePath
						.resolve("ressources_externes");
			}
			
		} // Fin de synchronized._______________________
		
	} // Fin de calculerRessourcesExternesPath().__________________________
	

		
	/**
	 * <b>calcule un Path (par exemple 
	 * <code>levy/daniel/application</code>) à partir 
	 * d'un groupId MAVEN 
	 * (<code>levy.daniel.application</code>)</b>.<br/>
	 * <ul>
	 * <li>remplace d'éventuels points "." par des slashes "/".<br/>
	 *  Sinon, laisse la chaine en entrée inchangée.</li>
	 *  <li>retourne le path associé à la String substituée.</li>
	 *  <li>utilise <code>StringUtils.replace(pGroupId, ".", "/");</code>.</li>
	 * </ul>
	 * - retourne null si pGroupId est blank.<br/>
	 * <br/>
	 *
	 * @param pGroupId : String : 
	 * groupId avec des points "."<br/>
	 * @return : Path : 
	 * Path du groupId avec des slashes "/".<br/>
	 */
	private static Path calculerPathRelatifGroupId(
			final String pGroupId) {
		
		synchronized (ArboresceurProjetSource.class) {
			
			/* retourne null si pGroupId est blank. */
			if (StringUtils.isBlank(pGroupId)) {
				return null;
			}
			
			String resultatString = null;
			
			/* remplace d'éventuels points "." par des slashes "/". 
			 * Sinon, laisse la chaine en entrée inchangée. */
			if (StringUtils.contains(pGroupId, ".")) {
				resultatString  
					= StringUtils.replace(pGroupId, ".", "/");
			} else {
				resultatString = pGroupId;
			}
			
			Path resultatPath = null;
			
			/* retourne le path associé à la String substituée. */
			if (resultatString != null) {
				resultatPath = Paths.get(resultatString);
			} else {
				return null;
			}
						
			if (resultatPath != null) {
				return resultatPath.normalize();
			}
			
			return null;
			
		} // Fin de synchronized._______________________
		
	} // Fin de calculerPathRelatifGroupId(...).___________________________
	

	
	/**
	 * <b>calcule un groupId (par exemple 
	 * <code>levy.daniel.application</code>) à partir 
	 * d'un path relatif associé à un groupId MAVEN 
	 * (<code>levy/daniel/application</code>)</b>.<br/>
	 * <ul>
	 * <li>remplace d'éventuels séparateurs de fichiers 
	 * comme des slashes "/" par des points ".".<br/>
	 *  Sinon, laisse la chaine en entrée inchangée.</li>
	 *  <li>utilise <code>System.getProperty("file.separator")</code> 
	 *  pour trouver le séparateur de fichiers de la plateforme.</li>
	 *  <li>utilise <code>StringUtils.replace(pGroupId, "/", ".");</code>.</li>
	 * </ul>
	 * - retourne null si pPathRelatifGroupId == null.<br/>
	 * <br/>
	 *
	 * @param pPathRelatifGroupId : Path.<br/>
	 * 
	 * @return : String : groupId avec des points "." comme "levy.daniel.application".<br/>
	 */
	private static String calculerGroupId(
			final Path pPathRelatifGroupId) {
		
		synchronized (ArboresceurProjetSource.class) {
			
			/* retourne null si pPathRelatifGroupId == null. */
			if (pPathRelatifGroupId == null) {
				return null;
			}
			
			final String pathRelatifGroupIdString 
				= pPathRelatifGroupId.toString();
			
			final String fileSeparator 
				= System.getProperty("file.separator");
			
			String resultat = null;
			
			if (StringUtils.contains(
					pathRelatifGroupIdString, fileSeparator)) {
				
				resultat 
					= StringUtils
						.replace(
								pathRelatifGroupIdString
									, fileSeparator, ".");
				
			} else {
				resultat = pathRelatifGroupIdString;
			}
			
			return resultat;
			
		} // Fin de synchronized._______________________
		
	} // Fin de calculerGroupId(...).______________________________________
	
	
	
	/**
	 * Getter de projetSourcePath.<br/>
	 *
	 * @return projetSourcePath : Path.<br/>
	 */
	public static Path getProjetSourcePath() {
		synchronized (ArboresceurProjetSource.class) {
			return projetSourcePath;
		} // Fin de synchronized._______________________		
	} // Fin de getProjetSourcePath().______________________________________

	
	
	/**
	 * Getter du nom du projet source.<br/>
	 *
	 * @return projetSourceNom : String.<br/>
	 */
	public static String getProjetSourceNom() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (projetSourceNom == null) {
				if (projetSourcePath != null) {
					projetSourceNom = extraireNom(projetSourcePath);
				}
			}
			
			return projetSourceNom;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getProjetSourceNom()._______________________________________
	
	

	/**
	 * Getter de srcMainJavaPath.<br/>
	 * <b>path absolu des sources Java</b> dans le projet source.
	 *
	 * @return srcMainJavaPath : Path.<br/>
	 */
	public static Path getSrcMainJavaPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (srcMainJavaPath == null) {
				calculerSrcMainJavaPath();
			}
			
			return srcMainJavaPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getSrcMainJavaPath().______________________________________
	
	

	/**
	 * Getter de srcMainResourcesPath.<br/>
	 * <b>path absolu des ressources</b> dans le projet source.
	 *
	 * @return srcMainResourcesPath : Path.<br/>
	 */
	public static Path getSrcMainResourcesPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (srcMainResourcesPath == null) {
				calculerSrcMainResourcesPath();
			}
			
			return srcMainResourcesPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getSrcMainResourcesPath()._________________________________

	
	
	/**
	 * Getter du <b>path absolu de ressources/META-INF</b> 
	 * dans le projet source.<br/>
	 * srcMainResourcesPath/ + META-INF<br/>
	 *
	 * @return srcMainResourcesMetaInfPath : Path.<br/>
	 */
	public static Path getSrcMainResourcesMetaInfPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (srcMainResourcesMetaInfPath == null) {
				calculerSrcMainResourcesMetaInfPath();
			}
			
			return srcMainResourcesMetaInfPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getSrcMainResourcesMetaInfPath().__________________________
	
	

	/**
	 * Getter de srcTestJavaPath.<br/>
	 * <b>path absolu des sources des tests JUnit</b> 
	 * dans le projet source.<br/>
	 *
	 * @return srcTestJavaPath : Path.<br/>
	 */
	public static Path getSrcTestJavaPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (srcTestJavaPath == null) {
				calculerSrcTestJavaPath();
			}
			
			return srcTestJavaPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getSrcTestJavaPath().______________________________________
	

	
	/**
	 * Getter de srcTestResourcesPath.<br/>
	 * <b>path absolu des ressources des tests Junit</b> 
	 * dans le projet source.<br/>
	 *
	 * @return srcTestResourcesPath : Path.<br/>
	 */
	public static Path getSrcTestResourcesPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (srcTestResourcesPath == null) {
				calculerSrcTestResourcesPath();
			}
			
			return srcTestResourcesPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getSrcTestResourcesPath()._________________________________

	
	
	/**
	 * Getter du <b>path absolu de ressources/META-INF</b> de test 
	 * dans le projet source.<br/>
	 * srcTestResourcesPath/ + META-INF<br/>
	 *
	 * @return srcTestResourcesMetaInfPath : Path.<br/>
	 */
	public static Path getSrcTestResourcesMetaInfPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (srcTestResourcesMetaInfPath == null) {
				calculerSrcTestResourcesMetaInfPath();
			}
			
			return srcTestResourcesMetaInfPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getSrcTestResourcesMetaInfPath().__________________________


	
	/**
	 * Getter du GroupId MAVEN dans le projet source.<br/>
	 * Le groupId MAVEN (avec des .) 
	 * peut être modifié par le Setter.<br/>
	 * "levy.daniel.application" par défaut.
	 *
	 * @return groupId : String.<br/>
	 */
	public static String getGroupId() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			return groupId;
		
		} // Fin de synchronized._______________________
		
	} // Fin de getGroupId().______________________________________________



	/**
	* Setter du GroupId MAVEN dans le projet source.<br/>
	* Le groupId MAVEN (avec des .) 
	* peut être modifié par le Setter.<br/>
	* "levy.daniel.application" par défaut.<br/>
	* <ul>
	* <li><b>Utiliser ce Setter AVANT l'appel à 
	* <code>selectionnerProjetSource(Path)</code> 
	* qui va lancer tous les calculs des chemins</b>.</li>
	* <li><b>recalcule automatiquement groupIdPathRelatif</b>.</li>
	* </ul>
	*
	* @param pGroupId : String : 
	* valeur à passer à groupId comme "levy.daniel.application".<br/>
	*/
	public static void setGroupId(
			final String pGroupId) {
		
		synchronized (ArboresceurProjetSource.class) {
			groupId = pGroupId;
			
			/* recalcule automatiquement groupIdPathRelatif. */
			groupIdPathRelatif = calculerPathRelatifGroupId(pGroupId);
		}
		
	} // Fin de setGroupId(...).___________________________________________



	/**
	 * Getter du path <b>relatif</b> 
	 * (par rapport au srcMainJavaPath et au srcTestJavaPath)
	 * correspondant au GroupId MAVEN.<br/>
	 * - Paths.get("levy/daniel/application") par défaut.<br/>
	 * - Sinon, utiliser le Setter.<br/>
	 *
	 * @return groupIdPathRelatif : Path.<br/>
	 */
	public static Path getGroupIdPathRelatif() {
				
		synchronized (ArboresceurProjetSource.class) {
			
			if (groupIdPathRelatif == null) {
				groupIdPathRelatif 
					= Paths.get(GROUPIDPATH_PAR_DEFAUT);
			}
			
			return groupIdPathRelatif;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getGroupIdPathRelatif().___________________________________



	/**
	* Setter du path <b>relatif</b> 
	* (par rapport au srcMainJavaPath et au srcTestJavaPath)
	* correspondant au GroupId MAVEN.<br/>
	* - Paths.get("levy/daniel/application") par défaut.<br/>
	* - Sinon, utiliser le Setter <b>et lui passer un path 
	* relatif avec des slashes /</b>.
	* <ul>
	* <li><b>Utiliser ce Setter AVANT l'appel à 
	* <code>selectionnerProjetSource(Path)</code> 
	* qui va lancer tous les calculs des chemins</b>.</li>
	* <li><b>recalcule automatiquement groupId</b>.</li>
	* </ul>
	*
	* @param pGroupIdPathRelatif : Path : 
	* valeur à passer à groupIdPathRelatif.<br/>
	*/
	public static void setGroupIdPathRelatif(
			final Path pGroupIdPathRelatif) {
		
		synchronized (ArboresceurProjetSource.class) {
						
			groupIdPathRelatif = pGroupIdPathRelatif;
			
			/* recalcule automatiquement groupId. */
			groupId = calculerGroupId(groupIdPathRelatif);
			
		} // Fin de synchronized._______________________
		
	} // Fin de setGroupIdPathRelatif(...).________________________________
	


	/**
	 * Getter de racineSourcesJavaPath.<br/>
	 * <b>path absolu des sources Java</b> 
	 * dans le projet source.<br/>
	 * srcMainJavaPath + groupIdPathRelatif<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application</code>
	 *
	 * @return racineSourcesJavaPath : Path.<br/>
	 */
	public static Path getRacineSourcesJavaPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (racineSourcesJavaPath == null) {
				calculerRacineSourcesJavaPath();
			}
			
			return racineSourcesJavaPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getRacineSourcesJavaPath().________________________________
	

	
	/**
	 * Getter de racineTestsJavaPath.<br/>
	 * <b>path absolu des sources des tests JUnit</b> 
	 * dans le projet source.<br/>
	 * srcTestJavaPath + groupIdPathRelatif<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application</code>
	 *
	 * @return racineTestsJavaPath : Path.<br/>
	 */
	public static Path getRacineTestsJavaPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (racineTestsJavaPath == null) {
				calculerRacineTestsJavaPath();
			}
			
			return racineTestsJavaPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getRacineTestsJavaPath().__________________________________


	
	/**
	 * Getter du <b>path absolu 
	 * de la couche apptechnic</b> 
	 * dans le projet source.<br/>
	 * racineSourcesJavaPath + apptechnic<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/apptechnic</code>
	 *
	 * @return coucheAppTechnicMainPath : Path.<br/>
	 */
	public static Path getCoucheAppTechnicMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheAppTechnicMainPath == null) {
				calculerCoucheAppTechnicMainPath();
			}
			
			return coucheAppTechnicMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheAppTechnicMainPath()._____________________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche apptechnic</b> 
	 * dans le projet source.<br/>
	 * racineTestsJavaPath + apptechnic<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/apptechnic</code>
	 *
	 * @return coucheAppTechnicTestPath : Path.<br/>
	 */
	public static Path getCoucheAppTechnicTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheAppTechnicTestPath == null) {
				calculerCoucheAppTechnicTestPath();
			}
			
			return coucheAppTechnicTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheAppTechnicTestPath()._____________________________
	


	/**
	 * Getter du <b>path absolu 
	 * de la couche controllers</b> 
	 * dans le projet source.<br/>
	 * racineSourcesJavaPath + controllers<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/controllers</code>
	 *
	 * @return coucheControllersMainPath : Path.<br/>
	 */
	public static Path getCoucheControllersMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersMainPath == null) {
				calculerCoucheControllersMainPath();
			}
			
			return coucheControllersMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersMainPath().____________________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche controllers</b> 
	 * dans le projet source.<br/>
	 * racineTestsJavaPath + controllers<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/controllers</code>
	 *
	 * @return coucheControllersTestPath : Path.<br/>
	 */
	public static Path getCoucheControllersTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersTestPath == null) {
				calculerCoucheControllersTestPath();
			}
			
			return coucheControllersTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersTestPath().____________________________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche controllers/desktop</b> 
	 * dans le projet source.<br/>
	 * coucheControllersMainPath + desktop<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/controllers/desktop</code>
	 *
	 * @return coucheControllersDesktopMainPath : Path.<br/>
	 */
	public static Path getCoucheControllersDesktopMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersDesktopMainPath == null) {
				calculerCoucheControllersDesktopMainPath();
			}
			
			return coucheControllersDesktopMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersDesktopMainPath()._____________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche controllers/desktop</b> 
	 * dans le projet source.<br/>
	 * coucheControllersTestPath + desktop<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/controllers/desktop</code>
	 *
	 * @return coucheControllersDesktopTestPath : Path.<br/>
	 */
	public static Path getCoucheControllersDesktopTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersDesktopTestPath == null) {
				calculerCoucheControllersDesktopTestPath();
			}
			
			return coucheControllersDesktopTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersDesktopTestPath()._____________________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche controllers/desktop/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheControllersDesktopMainPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/controllers/desktop/accueil</code>
	 *
	 * @return coucheControllersDesktopAccueilMainPath : Path.<br/>
	 */
	public static Path getCoucheControllersDesktopAccueilMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersDesktopAccueilMainPath == null) {
				calculerCoucheControllersDesktopAccueilMainPath();
			}
			
			return coucheControllersDesktopAccueilMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersDesktopAccueilMainPath().______________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche controllers/desktop/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheControllersDesktopTestPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/controllers/desktop/accueil</code>
	 *
	 * @return coucheControllersDesktopAccueilTestPath : Path.<br/>
	 */
	public static Path getCoucheControllersDesktopAccueilTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersDesktopAccueilTestPath == null) {
				calculerCoucheControllersDesktopAccueilTestPath();
			}
			
			return coucheControllersDesktopAccueilTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersDesktopAccueilTestPath().______________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche controllers/desktop/metier</b> 
	 * dans le projet source.<br/>
	 * coucheControllersDesktopMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/controllers/desktop/metier</code>
	 *
	 * @return coucheControllersDesktopMetierMainPath : Path.<br/>
	 */
	public static Path getCoucheControllersDesktopMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersDesktopMetierMainPath == null) {
				calculerCoucheControllersDesktopMetierMainPath();
			}
			
			return coucheControllersDesktopMetierMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersDesktopMetierMainPath()._______________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche controllers/desktop/metier</b> 
	 * dans le projet source.<br/>
	 * coucheControllersDesktopTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/controllers/desktop/metier</code>
	 *
	 * @return coucheControllersDesktopMetierTestPath : Path.<br/>
	 */
	public static Path getCoucheControllersDesktopMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersDesktopMetierTestPath == null) {
				calculerCoucheControllersDesktopMetierTestPath();
			}
			
			return coucheControllersDesktopMetierTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersDesktopMetierTestPath()._______________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche controllers/desktop/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheControllersDesktopMainPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/controllers/desktop/utilitaires</code>
	 *
	 * @return coucheControllersDesktopUtilitairesMainPath : Path.<br/>
	 */
	public static Path getCoucheControllersDesktopUtilitairesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersDesktopUtilitairesMainPath == null) {
				calculerCoucheControllersDesktopUtilitairesMainPath();
			}
			
			return coucheControllersDesktopUtilitairesMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersDesktopUtilitairesMainPath().__________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche controllers/desktop/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheControllersDesktopTestPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/controllers/desktop/utilitaires</code>
	 *
	 * @return coucheControllersDesktopUtilitairesTestPath : Path.<br/>
	 */
	public static Path getCoucheControllersDesktopUtilitairesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersDesktopUtilitairesTestPath == null) {
				calculerCoucheControllersDesktopUtilitairesTestPath();
			}
			
			return coucheControllersDesktopUtilitairesTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersDesktopUtilitairesTestPath().__________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche controllers/web</b> 
	 * dans le projet source.<br/>
	 * coucheControllersMainPath + web<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/controllers/web</code>
	 *
	 * @return coucheControllersWebMainPath : Path.<br/>
	 */
	public static Path getCoucheControllersWebMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersWebMainPath == null) {
				calculerCoucheControllersWebMainPath();
			}
			
			return coucheControllersWebMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersWebMainPath()._________________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche controllers/web</b> 
	 * dans le projet source.<br/>
	 * coucheControllersTestPath + web<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/controllers/web</code>
	 *
	 * @return coucheControllersWebTestPath : Path.<br/>
	 */
	public static Path getCoucheControllersWebTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersWebTestPath == null) {
				calculerCoucheControllersWebTestPath();
			}
			
			return coucheControllersWebTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersWebTestPath()._________________________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche controllers/web/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheControllersWebMainPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/controllers/web/accueil</code>
	 *
	 * @return coucheControllersWebAccueilMainPath : Path.<br/>
	 */
	public static Path getCoucheControllersWebAccueilMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersWebAccueilMainPath == null) {
				calculerCoucheControllersWebAccueilMainPath();
			}
			
			return coucheControllersWebAccueilMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersWebAccueilMainPath().__________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche controllers/web/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheControllersWebTestPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/controllers/web/accueil</code>
	 *
	 * @return coucheControllersWebAccueilTestPath : Path.<br/>
	 */
	public static Path getCoucheControllersWebAccueilTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersWebAccueilTestPath == null) {
				calculerCoucheControllersWebAccueilTestPath();
			}
			
			return coucheControllersWebAccueilTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersWebAccueilTestPath().__________________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche controllers/web/metier</b> 
	 * dans le projet source.<br/>
	 * coucheControllersWebMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/controllers/web/metier</code>
	 *
	 * @return coucheControllersWebMetierMainPath : Path.<br/>
	 */
	public static Path getCoucheControllersWebMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersWebMetierMainPath == null) {
				calculerCoucheControllersWebMetierMainPath();
			}
			
			return coucheControllersWebMetierMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersWebMetierMainPath().___________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche controllers/web/metier</b> 
	 * dans le projet source.<br/>
	 * coucheControllersWebTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/controllers/web/metier</code>
	 *
	 * @return coucheControllersWebMetierTestPath : Path.<br/>
	 */
	public static Path getCoucheControllersWebMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersWebMetierTestPath == null) {
				calculerCoucheControllersWebMetierTestPath();
			}
			
			return coucheControllersWebMetierTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersWebMetierTestPath().___________________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche controllers/web/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheControllersWebMainPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/controllers/web/utilitaires</code>
	 *
	 * @return coucheControllersWebUtilitairesMainPath : Path.<br/>
	 */
	public static Path getCoucheControllersWebUtilitairesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersWebUtilitairesMainPath == null) {
				calculerCoucheControllersWebUtilitairesMainPath();
			}
			
			return coucheControllersWebUtilitairesMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersWebUtilitairesMainPath().______________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche controllers/web/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheControllersWebTestPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/controllers/web/utilitaires</code>
	 *
	 * @return coucheControllersWebUtilitairesTestPath : Path.<br/>
	 */
	public static Path getCoucheControllersWebUtilitairesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheControllersWebUtilitairesTestPath == null) {
				calculerCoucheControllersWebUtilitairesTestPath();
			}
			
			return coucheControllersWebUtilitairesTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheControllersWebUtilitairesTestPath().______________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche vues</b> 
	 * dans le projet source.<br/>
	 * racineSourcesJavaPath + vues<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/vues</code>
	 *
	 * @return coucheVuesMainPath : Path.<br/>
	 */
	public static Path getCoucheVuesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesMainPath == null) {
				calculerCoucheVuesMainPath();
			}
			
			return coucheVuesMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesMainPath().___________________________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche vues</b> 
	 * dans le projet source.<br/>
	 * racineTestsJavaPath + vues<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/vues</code>
	 *
	 * @return coucheVuesTestPath : Path.<br/>
	 */
	public static Path getCoucheVuesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesTestPath == null) {
				calculerCoucheVuesTestPath();
			}
			
			return coucheVuesTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesTestPath().___________________________________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche vues/desktop</b> 
	 * dans le projet source.<br/>
	 * coucheVuesMainPath + desktop<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/vues/desktop</code>
	 *
	 * @return coucheVuesDesktopMainPath : Path.<br/>
	 */
	public static Path getCoucheVuesDesktopMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesDesktopMainPath == null) {
				calculerCoucheVuesDesktopMainPath();
			}
			
			return coucheVuesDesktopMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesDesktopMainPath().____________________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche vues/desktop</b> 
	 * dans le projet source.<br/>
	 * coucheVuesTestPath + desktop<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/vues/desktop</code>
	 *
	 * @return coucheVuesDesktopTestPath : Path.<br/>
	 */
	public static Path getCoucheVuesDesktopTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesDesktopTestPath == null) {
				calculerCoucheVuesDesktopTestPath();
			}
			
			return coucheVuesDesktopTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesDesktopTestPath().____________________________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche vues/desktop/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheVuesDesktopMainPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/vues/desktop/accueil</code>
	 *
	 * @return coucheVuesDesktopAccueilMainPath : Path.<br/>
	 */
	public static Path getCoucheVuesDesktopAccueilMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesDesktopAccueilMainPath == null) {
				calculerCoucheVuesDesktopAccueilMainPath();
			}
			
			return coucheVuesDesktopAccueilMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesDesktopAccueilMainPath()._____________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche vues/desktop/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheVuesDesktopTestPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/vues/desktop/accueil</code>
	 *
	 * @return coucheVuesDesktopAccueilTestPath : Path.<br/>
	 */
	public static Path getCoucheVuesDesktopAccueilTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesDesktopAccueilTestPath == null) {
				calculerCoucheVuesDesktopAccueilTestPath();
			}
			
			return coucheVuesDesktopAccueilTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesDesktopAccueilTestPath()._____________________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche vues/desktop/metier</b> 
	 * dans le projet source.<br/>
	 * coucheVuesDesktopMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/vues/desktop/metier</code>
	 *
	 * @return coucheVuesDesktopMetierMainPath : Path.<br/>
	 */
	public static Path getCoucheVuesDesktopMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesDesktopMetierMainPath == null) {
				calculerCoucheVuesDesktopMetierMainPath();
			}
			
			return coucheVuesDesktopMetierMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesDesktopMetierMainPath().______________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche vues/desktop/metier</b> 
	 * dans le projet source.<br/>
	 * coucheVuesDesktopTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/vues/desktop/metier</code>
	 *
	 * @return coucheVuesDesktopMetierTestPath : Path.<br/>
	 */
	public static Path getCoucheVuesDesktopMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesDesktopMetierTestPath == null) {
				calculerCoucheVuesDesktopMetierTestPath();
			}
			
			return coucheVuesDesktopMetierTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesDesktopMetierTestPath().______________________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche vues/desktop/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheVuesDesktopMainPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/vues/desktop/utilitaires</code>
	 *
	 * @return coucheVuesDesktopUtilitairesMainPath : Path.<br/>
	 */
	public static Path getCoucheVuesDesktopUtilitairesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesDesktopUtilitairesMainPath == null) {
				calculerCoucheVuesDesktopUtilitairesMainPath();
			}
			
			return coucheVuesDesktopUtilitairesMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesDesktopUtilitairesMainPath()._________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche vues/desktop/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheVuesDesktopTestPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/vues/desktop/utilitaires</code>
	 *
	 * @return coucheVuesDesktopUtilitairesTestPath : Path.<br/>
	 */
	public static Path getCoucheVuesDesktopUtilitairesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesDesktopUtilitairesTestPath == null) {
				calculerCoucheVuesDesktopUtilitairesTestPath();
			}
			
			return coucheVuesDesktopUtilitairesTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesDesktopUtilitairesTestPath()._________________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche vues/web</b> 
	 * dans le projet source.<br/>
	 * coucheVuesMainPath + web<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/vues/web</code>
	 *
	 * @return coucheVuesWebMainPath : Path.<br/>
	 */
	public static Path getCoucheVuesWebMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesWebMainPath == null) {
				calculerCoucheVuesWebMainPath();
			}
			
			return coucheVuesWebMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesWebMainPath().________________________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche vues/web</b> 
	 * dans le projet source.<br/>
	 * coucheVuesTestPath + web<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/vues/web</code>
	 *
	 * @return coucheVuesWebTestPath : Path.<br/>
	 */
	public static Path getCoucheVuesWebTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesWebTestPath == null) {
				calculerCoucheVuesWebTestPath();
			}
			
			return coucheVuesWebTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesWebTestPath().________________________________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche vues/web/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheVuesWebMainPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/vues/web/accueil</code>
	 *
	 * @return coucheVuesWebAccueilMainPath : Path.<br/>
	 */
	public static Path getCoucheVuesWebAccueilMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesWebAccueilMainPath == null) {
				calculerCoucheVuesWebAccueilMainPath();
			}
			
			return coucheVuesWebAccueilMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesWebAccueilMainPath()._________________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche vues/web/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheVuesWebTestPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/vues/web/accueil</code>
	 *
	 * @return coucheVuesWebAccueilTestPath : Path.<br/>
	 */
	public static Path getCoucheVuesWebAccueilTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesWebAccueilTestPath == null) {
				calculerCoucheVuesWebAccueilTestPath();
			}
			
			return coucheVuesWebAccueilTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesWebAccueilTestPath()._________________________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche vues/web/metier</b> 
	 * dans le projet source.<br/>
	 * coucheVuesWebMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/vues/web/metier</code>
	 *
	 * @return coucheVuesWebMetierMainPath : Path.<br/>
	 */
	public static Path getCoucheVuesWebMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesWebMetierMainPath == null) {
				calculerCoucheVuesWebMetierMainPath();
			}
			
			return coucheVuesWebMetierMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesWebMetierMainPath().__________________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche vues/web/metier</b> 
	 * dans le projet source.<br/>
	 * coucheVuesWebTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/vues/web/metier</code>
	 *
	 * @return coucheVuesWebMetierTestPath : Path.<br/>
	 */
	public static Path getCoucheVuesWebMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesWebMetierTestPath == null) {
				calculerCoucheVuesWebMetierTestPath();
			}
			
			return coucheVuesWebMetierTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesWebMetierTestPath().__________________________
	
	

	/**
	 * Getter du <b>path absolu 
	 * de la couche vues/web/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheVuesWebMainPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/vues/web/utilitaires</code>
	 *
	 * @return coucheVuesWebUtilitairesMainPath : Path.<br/>
	 */
	public static Path getCoucheVuesWebUtilitairesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesWebUtilitairesMainPath == null) {
				calculerCoucheVuesWebUtilitairesMainPath();
			}
			
			return coucheVuesWebUtilitairesMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesWebUtilitairesMainPath()._____________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche vues/web/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheVuesWebTestPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/vues/web/utilitaires</code>
	 *
	 * @return coucheVuesWebUtilitairesTestPath : Path.<br/>
	 */
	public static Path getCoucheVuesWebUtilitairesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheVuesWebUtilitairesTestPath == null) {
				calculerCoucheVuesWebUtilitairesTestPath();
			}
			
			return coucheVuesWebUtilitairesTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheVuesWebUtilitairesTestPath()._____________________

	
	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model</b> 
	 * dans le projet source.<br/>
	 * racineSourcesJavaPath + model<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/
	 * application/model</code>
	 *
	 * @return coucheModelMainPath : Path.<br/>
	 */
	public static Path getCoucheModelMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelMainPath == null) {
				calculerCoucheModelMainPath();
			}
			
			return coucheModelMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelMainPath().__________________________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model</b> 
	 * dans le projet source.<br/>
	 * racineTestsJavaPath + model<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/
	 * application/model</code>
	 *
	 * @return coucheModelTestPath : Path.<br/>
	 */
	public static Path getCoucheModelTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelTestPath == null) {
				calculerCoucheModelTestPath();
			}
			
			return coucheModelTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelTestPath().__________________________________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/dto</b> 
	 * dans le projet source.<br/>
	 * coucheModelMainPath + dto<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/dto</code>
	 *
	 * @return coucheModelDTOMainPath : Path.<br/>
	 */
	public static Path getCoucheModelDTOMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelDTOMainPath == null) {
				calculerCoucheModelDTOMainPath();
			}
			
			return coucheModelDTOMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelDTOMainPath()._______________________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model/dto</b> 
	 * dans le projet source.<br/>
	 * coucheModelTestPath + dto<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/dto</code>
	 *
	 * @return coucheModelDTOTestPath : Path.<br/>
	 */
	public static Path getCoucheModelDTOTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelDTOTestPath == null) {
				calculerCoucheModelDTOTestPath();
			}
			
			return coucheModelDTOTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelDTOTestPath()._______________________________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/dto/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelDTOMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/dto/metier</code>
	 *
	 * @return coucheModelDTOMetierMainPath : Path.<br/>
	 */
	public static Path getCoucheModelDTOMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelDTOMetierMainPath == null) {
				calculerCoucheModelDTOMetierMainPath();
			}
			
			return coucheModelDTOMetierMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelDTOMetierMainPath()._________________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model/dto/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelDTOTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/dto/metier</code>
	 *
	 * @return coucheModelDTOTestPath : Path.<br/>
	 */
	public static Path getCoucheModelDTOMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelDTOMetierTestPath == null) {
				calculerCoucheModelDTOMetierTestPath();
			}
			
			return coucheModelDTOMetierTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelDTOMetierTestPath()._________________________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/metier</code>
	 *
	 * @return coucheModelMetierMainPath : Path.<br/>
	 */
	public static Path getCoucheModelMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelMetierMainPath == null) {
				calculerCoucheModelMetierMainPath();
			}
			
			return coucheModelMetierMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelMetierMainPath().____________________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/metier</code>
	 *
	 * @return coucheModelMetierTestPath : Path.<br/>
	 */
	public static Path getCoucheModelMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelMetierTestPath == null) {
				calculerCoucheModelMetierTestPath();
			}
			
			return coucheModelMetierTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelMetierTestPath().____________________________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/persistence</b> 
	 * dans le projet source.<br/>
	 * coucheModelMainPath + persistence<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/persistence</code>
	 *
	 * @return coucheModelPersistenceMainPath : Path.<br/>
	 */
	public static Path getCoucheModelPersistenceMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelPersistenceMainPath == null) {
				calculerCoucheModelPersistenceMainPath();
			}
			
			return coucheModelPersistenceMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelPersistenceMainPath()._______________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model/persistence</b> 
	 * dans le projet source.<br/>
	 * coucheModelTestPath + persistence<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/persistence</code>
	 *
	 * @return coucheModelPersistenceTestPath : Path.<br/>
	 */
	public static Path getCoucheModelPersistenceTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelPersistenceTestPath == null) {
				calculerCoucheModelPersistenceTestPath();
			}
			
			return coucheModelPersistenceTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelPersistenceTestPath()._______________________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/persistence/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheModelPersistenceMainPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/persistence/accueil</code>
	 *
	 * @return coucheModelPersistenceAccueilMainPath : Path.<br/>
	 */
	public static Path getCoucheModelPersistenceAccueilMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelPersistenceAccueilMainPath == null) {
				calculerCoucheModelPersistenceAccueilMainPath();
			}
			
			return coucheModelPersistenceAccueilMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelPersistenceAccueilMainPath().________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model/persistence/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheModelPersistenceTestPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/persistence/accueil</code>
	 *
	 * @return coucheModelPersistenceAccueilTestPath : Path.<br/>
	 */
	public static Path getCoucheModelPersistenceAccueilTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelPersistenceAccueilTestPath == null) {
				calculerCoucheModelPersistenceAccueilTestPath();
			}
			
			return coucheModelPersistenceAccueilTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelPersistenceAccueilTestPath().________________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/persistence/daoexceptions</b> 
	 * dans le projet source.<br/>
	 * coucheModelPersistenceMainPath + daoexceptions<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/persistence/daoexceptions</code>
	 *
	 * @return coucheModelPersistenceDaoexceptionsMainPath : Path.<br/>
	 */
	public static Path getCoucheModelPersistenceDaoexceptionsMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelPersistenceDaoexceptionsMainPath == null) {
				calculerCoucheModelPersistenceDaoexceptionsMainPath();
			}
			
			return coucheModelPersistenceDaoexceptionsMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelPersistenceDaoexceptionsMainPath().__________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model/persistence/daoexceptions</b> 
	 * dans le projet source.<br/>
	 * coucheModelPersistenceTestPath + daoexceptions<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/persistence/daoexceptions</code>
	 *
	 * @return coucheModelPersistenceDaoexceptionsTestPath : Path.<br/>
	 */
	public static Path getCoucheModelPersistenceDaoexceptionsTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelPersistenceDaoexceptionsTestPath == null) {
				calculerCoucheModelPersistenceDaoexceptionsTestPath();
			}
			
			return coucheModelPersistenceDaoexceptionsTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelPersistenceDaoexceptionsTestPath().__________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/persistence/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelPersistenceMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/persistence/metier</code>
	 *
	 * @return coucheModelPersistenceMetierMainPath : Path.<br/>
	 */
	public static Path getCoucheModelPersistenceMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelPersistenceMetierMainPath == null) {
				calculerCoucheModelPersistenceMetierMainPath();
			}
			
			return coucheModelPersistenceMetierMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelPersistenceMetierMainPath()._________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model/persistence/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelPersistenceTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/persistence/metier</code>
	 *
	 * @return coucheModelPersistenceMetierTestPath : Path.<br/>
	 */
	public static Path getCoucheModelPersistenceMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelPersistenceMetierTestPath == null) {
				calculerCoucheModelPersistenceMetierTestPath();
			}
			
			return coucheModelPersistenceMetierTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelPersistenceMetierTestPath()._________________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/services</b> 
	 * dans le projet source.<br/>
	 * coucheModelMainPath + services<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services</code>
	 *
	 * @return coucheModelServicesMainPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesMainPath == null) {
				calculerCoucheModelServicesMainPath();
			}
			
			return coucheModelServicesMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesMainPath().__________________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model/services</b> 
	 * dans le projet source.<br/>
	 * coucheModelTestPath + services<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services</code>
	 *
	 * @return coucheModelServicesTestPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesTestPath == null) {
				calculerCoucheModelServicesTestPath();
			}
			
			return coucheModelServicesTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesTestPath().__________________________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/services/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesMainPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/accueil</code>
	 *
	 * @return coucheModelServicesAccueilMainPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesAccueilMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesAccueilMainPath == null) {
				calculerCoucheModelServicesAccueilMainPath();
			}
			
			return coucheModelServicesAccueilMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesAccueilMainPath().___________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model/services/accueil</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesTestPath + accueil<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/accueil</code>
	 *
	 * @return coucheModelServicesAccueilTestPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesAccueilTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesAccueilTestPath == null) {
				calculerCoucheModelServicesAccueilTestPath();
			}
			
			return coucheModelServicesAccueilTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesAccueilTestPath().___________________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/services/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesMainPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/metier</code>
	 *
	 * @return coucheModelServicesMetierMainPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesMetierMainPath == null) {
				calculerCoucheModelServicesMetierMainPath();
			}
			
			return coucheModelServicesMetierMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesMetierMainPath().____________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model/services/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesTestPath + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/metier</code>
	 *
	 * @return coucheModelServicesMetierTestPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesMetierTestPath == null) {
				calculerCoucheModelServicesMetierTestPath();
			}
			
			return coucheModelServicesMetierTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesMetierTestPath().____________________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/services/transformeurs</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesMainPath + transformeurs<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/transformeurs</code>
	 *
	 * @return coucheModelServicesTransformeursMainPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesTransformeursMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesTransformeursMainPath == null) {
				calculerCoucheModelServicesTransformeursMainPath();
			}
			
			return coucheModelServicesTransformeursMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesTransformeursMainPath()._____________


	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/services/transformeurs/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesTransformeursMainPath/ + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/transformeurs/metier</code>
	 *
	 * @return coucheModelServicesTransformeursMetierMainPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesTransformeursMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesTransformeursMetierMainPath == null) {
				calculerCoucheModelServicesTransformeursMetierMainPath();
			}
			
			return coucheModelServicesTransformeursMetierMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesTransformeursMetierMainPath()._______


	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model/services/transformeurs</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesTestPath + transformeurs<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/transformeurs</code>
	 *
	 * @return coucheModelServicesTransformeursTestPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesTransformeursTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesTransformeursTestPath == null) {
				calculerCoucheModelServicesTransformeursTestPath();
			}
			
			return coucheModelServicesTransformeursTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesTransformeursTestPath()._____________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/services/transformeurs/metier</b> 
	 * dans les tests du projet source.<br/>
	 * coucheModelServicesTransformeursTestPath/ + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/transformeurs/metier</code>
	 *
	 * @return coucheModelServicesTransformeursMetierTestPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesTransformeursMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesTransformeursMetierTestPath == null) {
				calculerCoucheModelServicesTransformeursMetierTestPath();
			}
			
			return coucheModelServicesTransformeursMetierTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesTransformeursMetierTestPath()._______


	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/services/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesMainPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/utilitaires</code>
	 *
	 * @return coucheModelServicesUtilitairesMainPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesUtilitairesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesUtilitairesMainPath == null) {
				calculerCoucheModelServicesUtilitairesMainPath();
			}
			
			return coucheModelServicesUtilitairesMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesUtilitairesMainPath()._______________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model/services/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesTestPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/utilitaires</code>
	 *
	 * @return coucheModelServicesUtilitairesTestPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesUtilitairesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesUtilitairesTestPath == null) {
				calculerCoucheModelServicesUtilitairesTestPath();
			}
			
			return coucheModelServicesUtilitairesTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesUtilitairesTestPath()._______________

	
	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/services/valideurs</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesMainPath + valideurs<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/valideurs</code>
	 *
	 * @return coucheModelServicesValideursMainPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesValideursMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesValideursMainPath == null) {
				calculerCoucheModelServicesValideursMainPath();
			}
			
			return coucheModelServicesValideursMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesValideursMainPath()._________________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/services/valideurs/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesValideursMainPath/ + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/services/valideurs/metier</code>
	 *
	 * @return coucheModelServicesValideursMetierMainPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesValideursMetierMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesValideursMetierMainPath == null) {
				calculerCoucheModelServicesValideursMetierMainPath();
			}
			
			return coucheModelServicesValideursMetierMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesValideursMetierMainPath().___________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model/services/valideurs</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesTestPath + valideurs<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/valideurs</code>
	 *
	 * @return coucheModelServicesValideursTestPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesValideursTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesValideursTestPath == null) {
				calculerCoucheModelServicesValideursTestPath();
			}
			
			return coucheModelServicesValideursTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesValideursTestPath()._________________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/services/valideurs/metier</b> 
	 * dans le projet source.<br/>
	 * coucheModelServicesValideursTestPath/ + metier<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/services/valideurs/metier</code>
	 *
	 * @return coucheModelServicesValideursMetierTestPath : Path.<br/>
	 */
	public static Path getCoucheModelServicesValideursMetierTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelServicesValideursMetierTestPath == null) {
				calculerCoucheModelServicesValideursMetierTestPath();
			}
			
			return coucheModelServicesValideursMetierTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelServicesValideursMetierTestPath().___________
	

	
	/**
	 * Getter du <b>path absolu 
	 * de la couche model/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheModelMainPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/main/java/levy/daniel/application/
	 * model/utilitaires</code>
	 *
	 * @return coucheModelUtilitairesMainPath : Path.<br/>
	 */
	public static Path getCoucheModelUtilitairesMainPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelUtilitairesMainPath == null) {
				calculerCoucheModelUtilitairesMainPath();
			}
			
			return coucheModelUtilitairesMainPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelUtilitairesMainPath()._______________________
	

	
	/**
	 * Getter du <b>path absolu des tests JUnit 
	 * de la couche model/utilitaires</b> 
	 * dans le projet source.<br/>
	 * coucheModelTestPath + utilitaires<br/>
	 * Par exemple : 
	 * <code>${projet}/src/test/java/levy/daniel/application/
	 * model/utilitaires</code>
	 *
	 * @return coucheModelUtilitairesTestPath : Path.<br/>
	 */
	public static Path getCoucheModelUtilitairesTestPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (coucheModelUtilitairesTestPath == null) {
				calculerCoucheModelUtilitairesTestPath();
			}
			
			return coucheModelUtilitairesTestPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getCoucheModelUtilitairesTestPath()._______________________


	
	/**
	 * Getter du repertoire externe <b>conception_appli</b>.<br/>
	 * projetSourcePath + conception_appli
	 *
	 * @return conceptionAppliPath : Path.<br/>
	 */
	public static Path getConceptionAppliPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (conceptionAppliPath == null) {
				calculerConceptionAppliPath();
			}
			
			return conceptionAppliPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getConceptionAppliPath().__________________________________


	
	/**
	 * Getter du repertoire externe <b>data</b>.<br/>
	 * projetSourcePath + data
	 *
	 * @return dataPath : Path.<br/>
	 */
	public static Path getDataPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (dataPath == null) {
				calculerDataPath();
			}
			
			return dataPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getDataPath()._____________________________________________

	
	
	/**
	 * Getter de dataH2Path.<br/>
	 * dataPath/ + "base-" + ${projetSourceNom} + "-h2"</b>.<br/><br/>
	 *
	 * @return dataH2Path : Path.<br/>
	 */
	public static Path getDataH2Path() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (dataH2Path == null) {
				calculerDataH2Path();
			}
			
			return dataH2Path;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getDataH2Path().___________________________________________

	
	
	/**
	 * Getter de dataHSQLDBPath.<br/>
	 * dataPath/ + "base-" + ${projetSourceNom} + "-hsqldb"</b>.<br/><br/>
	 *
	 * @return dataHSQLDBPath : Path.<br/>
	 */
	public static Path getDataHSQLDBPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (dataHSQLDBPath == null) {
				calculerDataHSQLDBPath();
			}
			
			return dataHSQLDBPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getDataHSQLDBPath()._______________________________________

	
	
	/**
	 * Getter de dataJAXBPath.<br/>
	 * dataPath/ + "base-" + ${projetSourceNom} + "-JAXB"</b>.<br/><br/>
	 *
	 * @return dataJAXBPath : Path.<br/>
	 */
	public static Path getDataJAXBPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (dataJAXBPath == null) {
				calculerDataJAXBPath();
			}
			
			return dataJAXBPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getDataJAXBPath()._______________________________________

	
	
	/**
	 * Getter de dataScriptsSqlPath.<br/>
	 * dataScriptsSqlPath = dataPath/ 
	 * + scripts_sql</b>.<br/>
	 *
	 * @return dataScriptsSqlPath : Path.<br/>
	 */
	public static Path getDataScriptsSqlPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (dataScriptsSqlPath == null) {
				calculerDataScriptsSqlPath();
			}
			
			return dataScriptsSqlPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getDataScriptsSqlPath().___________________________________


	
	/**
	 * Getter du repertoire externe <b>javadoc</b>.<br/>
	 * projetSourcePath + javadoc
	 *
	 * @return javadocPath : Path.<br/>
	 */
	public static Path getJavadocPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (javadocPath == null) {
				calculerJavadocPath();
			}
			
			return javadocPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getJavadocPath().__________________________________________


	
	/**
	 * Getter du repertoire externe <b>javadoc/images</b>.<br/>
	 * javadocPath/ + images
	 *
	 * @return javadocImagesPath : Path.<br/>
	 */
	public static Path getJavadocImagesPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (javadocImagesPath == null) {
				calculerJavadocImagesPath();
			}
			
			return javadocImagesPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getJavadocImagesPath().____________________________________


	
	/**
	 * Getter du repertoire externe <b>logs</b>.<br/>
	 * projetSourcePath/ + logs
	 *
	 * @return logsPath : Path.<br/>
	 */
	public static Path getLogsPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (logsPath == null) {
				calculerLogsPath();
			}
			
			return logsPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getLogsPath()._____________________________________________


	
	/**
	 * Getter du repertoire externe <b>rapports_controle</b>.<br/>
	 * projetSourcePath/ + rapports_controle
	 *
	 * @return rapportsControlePath : Path.<br/>
	 */
	public static Path getRapportsControlePath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (rapportsControlePath == null) {
				calculerRapportsControlePath();
			}
			
			return rapportsControlePath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getRapportsControlePath()._________________________________


	
	/**
	 * Getter du repertoire externe <b>ressources_externes</b>.<br/>
	 * projetSourcePath/ + ressources_externes
	 *
	 * @return ressourcesExternesPath : Path.<br/>
	 */
	public static Path getRessourcesExternesPath() {
		
		synchronized (ArboresceurProjetSource.class) {
			
			if (ressourcesExternesPath == null) {
				calculerRessourcesExternesPath();
			}
			
			return ressourcesExternesPath;
			
		} // Fin de synchronized._______________________
		
	} // Fin de getRessourcesExternesPath()._______________________________
	
	

	/**
	 * Getter de la Liste des répertoires (sous forme de Path) 
	 * constituant l'arborescence à créer dans le projet source.<br/>
	 *
	 * @return ARBORESCENCE_PROJET_SOURCE : List&lt;Path&gt;.<br/>
	 */
	public static List<Path> getArborescenceProjetSource() {
		return ARBORESCENCE_PROJET_SOURCE;
	} // Fin de getArborescenceProjetSource().______________________________



	/**
	 * Getter de la <b>Map des répertoires sous src/main/java</b> 
	 * dans le projet source avec :
	 * <ul>
	 * <li>String : le chemin relatif du répertoire par rapport 
	 * au GroupId comme <code>"model/dto"</code></li>
	 * <li>Path : l'attribut contenant le Path absolu 
	 * du répertoire comme <code>coucheModelDTOMainPath</code></li>
	 * </ul>
	 *
	 * @return ARBORESCENCE_MAIN_PROJET_SOURCE_MAP : 
	 * Map&lt;String,Path&gt;.<br/>
	 */
	public static Map<String, Path> getArborescenceMainProjetSourceMap() {
		return ARBORESCENCE_MAIN_PROJET_SOURCE_MAP;
	} // Fin de getArborescenceMainProjetSourceMap()._______________________



	/**
	 * Getter de la <b>Map des répertoires sous src/test/java</b> 
	 * dans le projet source avec :
	 * <ul>
	 * <li>String : le chemin relatif du répertoire par rapport 
	 * au GroupId comme <code>"model/dto"</code></li>
	 * <li>Path : l'attribut contenant le Path absolu 
	 * du répertoire comme <code>coucheModelDTOTestPath</code></li>
	 * </ul>
	 *
	 * @return ARBORESCENCE_TEST_PROJET_SOURCE_MAP :  
	 * Map&lt;String,Path&gt;.<br/>
	 */
	public static Map<String, Path> getArborescenceTestProjetSourceMap() {
		return ARBORESCENCE_TEST_PROJET_SOURCE_MAP;
	} // Fin de getArborescenceTestProjetSourceMap()._______________________


	
	/**
	 * Getter de la <b>Map des répertoires externes</b> 
	 * dans le projet source avec :
	 * <ul>
	 * <li>String : le nom du répertoire externe 
	 * comme <code>"data"</code></li>
	 * <li>Path : l'attribut contenant le Path absolu 
	 * du répertoire comme <code>dataPath</code></li>
	 * </ul>
	 *
	 * @return ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP : 
	 * Map&lt;String,Path&gt;.<br/>
	 */
	public static Map<String, Path> getArborescenceRepertoiresExternesProjetSourceMap() {
		return ARBORESCENCE_REPERTOIRES_EXTERNES_PROJET_SOURCE_MAP;
	} // Fin de getArborescenceRepertoiresExternesProjetSourceMap().________

		
	
} // FIN DE LA CLASSE ArboresceurProjetSource.-------------------------------
