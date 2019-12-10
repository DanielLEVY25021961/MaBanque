/**
 * CLASSE package-info :<br/>
 * ce package contient tous les utilitaires 
 * pour la <b>lecture des fichiers properties de configuration 
 * d'une base de données par SPRING</b> 
 * en Java.<br/>

 * 
 * <p>
 * <b><span style="text-decoration: underline;">
 * OBJECTIF du lecteur de configuration SPRING :
 * </span></b>
 * </p>
 * <p>
 * Préparer les propriétés stockées dans un fichier properties SPRING pour instancier un EntityManagerFactory.<br/>
 * </p>
 * 
 * <p>
 * <b><span style="text-decoration: underline;">
 * Principe de fonctionnement du lecteur de configuration SPRING :
 * </span></b>
 * </p>
 * <p>
 * un <code>org.springframework.core.env.Environment</code> 
 * est automatiquement instancié 
 * lors de la configuration du CONTEXTE SPRING au lancement de l'application.<br/>
 * Cet Environment lit automatiquement le fichier properties SPRING désigné dans 
 * l'annotation PropertySource au dessus de la classe de configuration SPRING et l'encapsule.<br/>
 * Les propriétés stockées dans le fichier properties SPRING sont des propriétés SPRING 
 * qui ne peuvent être utilisées telles quelles pour instancier 
 * un <code>EntityManagerFactory</code>.<br/>
 * par exemple :
 * <ul>
 * <li><code>spring.jpa.properties.hibernate.dialect</code> 
 * devient <code>hibernate.dialect</code> dans un EntityManagerFactory
 *  (pour un PersistenceProvider HIBERNATE).</li>
 * <li><code>spring.jpa.properties.hibernate.ddl-auto</code> 
 * devient <code>hibernate.hbm2ddl.auto</code> dans un EntityManagerFactory
 *  (pour un PersistenceProvider HIBERNATE).</li>
 * </ul>
 * <b>Le LecteurConfigurationBaseSpring prépare toutes les properties 
 * pour qu'elle soient directement injectables dans un 
 * <code>org.springframework.orm.jpa.persistenceunit.MutablePersistenceUnitInfo</code> 
 * (utilisé ensuite pour instancier un <code>EntityManagerFactory</code>)</b>.
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../javadoc/images/model/utilitaires/spring/configurateurpersistencespring/lecteur/fonctionnement_lecteurconfigurationbase.png" 
 * alt="fonctionnement des lecteurs de configuration de BD SPRING" border="1" align="center" />
 * </p>
 * 
 * <p>
 * <b><span style="text-decoration: underline;">
 * diagramme de classes du lecteur de configuration (SPRING) d'une Base de Données :
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../../javadoc/images/model/utilitaires/spring/configurateurpersistencespring/lecteur/diagramme_classes_lecteurconfigurationbase_haut.png" 
 * alt="diagramme de classes des lecteurs de configuration de BD SPRING (haut)" border="1" align="center" />
 * <img src="../../../../../../../../../../../javadoc/images/model/utilitaires/spring/configurateurpersistencespring/lecteur/diagramme_classes_lecteurconfigurationbase_bas.png" 
 * alt="diagramme de classes des lecteurs de configuration de BD SPRING (bas)" border="1" align="center" />
 * </p>
 * 
 * <br/><br/>
 * 
 * - Exemple d'utilisation :<br/>
 * <code>
 *  // INSTANCIATION DU LecteurConfigurationBaseSpring DEPUIS LE SETTER AUTOWIRED setEnvironmentSpring(...) DE LA CLASSE DE CONFIGURATION SPRING<br/>
 * <b>this.setLecteurConfigurationBaseSpring(new LecteurConfigurationBaseSpring(this.environmentSpring));</b><br/>
 * </code>
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
 * @since 30 janv. 2019
 *
 */
package levy.daniel.application.model.utilitaires.spring.configurateurpersistencespring.lecteur;
