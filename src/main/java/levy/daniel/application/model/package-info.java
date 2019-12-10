/**
 * CLASSE package-info :<br/>
 * Ce package contient toutes les classes du <b>DOMAINE</b> 
 * (LOGIQUE METIER) de l'application.<br/>
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
 * <img src="../../../../../../../javadoc/images/arboresceurprojet/couche_model.png" 
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
 * <img src="../../../../../../../javadoc/images/arboresceurprojet/sous-couche_DTO.png" 
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
 * <img src="../../../../../../../javadoc/images/arboresceurprojet/sous-couche_metier.png" 
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
 * <img src="../../../../../../../javadoc/images/arboresceurprojet/sous-couche_persistence.png" 
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
 * <img src="../../../../../../../javadoc/images/arboresceurprojet/sous-couche_services.png" 
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
 * <img src="../../../../../../../javadoc/images/arboresceurprojet/sous-couche_utilitaires.png" 
 * alt="sous-couche UTILITAIRES" border="1" align="center" />
 * </div>
 * <br/>
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
 * @since 3 nov. 2018
 *
 */
package levy.daniel.application.model;
