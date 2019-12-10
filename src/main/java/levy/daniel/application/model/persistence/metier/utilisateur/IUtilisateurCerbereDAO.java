package levy.daniel.application.model.persistence.metier.utilisateur;

import java.util.List;

import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;



/**
 * INTERFACE IUtilisateurCerbereDAO :<br/>
 * .<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 22 févr. 2019
 *
 */
public interface IUtilisateurCerbereDAO {


	
	/* CREATE ************/

	
	/**
	 * <b>crée un objet métier pObject dans le stockage 
	 * et retourne l'objet METIER persisté</b>.<br/>
	 * <ul>
	 * <li>ne crée <b>pas de doublon</b>.</li>
	 * <li>retourne null si pObject existe déjà dans le stockage.</li>
	 * <li>retourne null si les attributs obligatoires 
	 * de pObject ne sont pas remplis.</li>
	 * </ul>
	 * - retourne null si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : IUtilisateurCerbere : 
	 * l'objet métier à persister dans le stockage.<br/>
	 * 
	 * @return IUtilisateurCerbere : 
	 * l'objet métier persisté dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	IUtilisateurCerbere create(IUtilisateurCerbere pObject) throws Exception;
	
	
	
	/**
	 * <b>crée une ENTITY pEntity dans le stockage 
	 * SI ELLE N'EXISTE PAS DEJA
	 * et retourne l'ENTITY persistée</b>.<br/>
	 * <b>retourne l'ENTITY pEntity DEJA PERSISTENTE SI ELLE EXISTE DEJA.</b>
	 * <ul>
	 * <li>ne crée <b>pas de doublon</b>.</li>
	 * <li><b>retourne l'ENTITY PERSISTEE</b> si pEntity 
	 * existe déjà dans le stockage.</li>
	 * <li>retourne null si les attributs obligatoires 
	 * de pEntity ne sont pas remplis.</li>
	 * </ul>
	 * - retourne null si pEntity == null.<br/>
	 * <br/>
	 *
	 * @param pEntity : IUtilisateurCerbere : 
	 * l'ENTITY à persister dans le stockage.<br/>
	 * 
	 * @return IUtilisateurCerbere : 
	 * l'ENTITY persistée dans le stockage (sous forme d'interface).<br/>
	 * 
	 * @throws Exception
	 */
	IUtilisateurCerbere createOrRetrieve(IUtilisateurCerbere pEntity) 
															throws Exception;
	
	

	/**
	 * <b>crée un objet métier pObject dans le stockage 
	 * mais ne retourne rien</b>.<br/>
	 * <ul>
	 * <li>ne crée <b>pas de doublon</b>.</li>
	 * <li>ne fait rien si pObject existe déjà dans le stockage.</li>
	 * <li>ne fait rien si les attributs obligatoires 
	 * de pObject ne sont pas remplis.</li>
	 * </ul>
	 * - ne fait rien si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : IUtilisateurCerbere : 
	 * l'objet métier à persister dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	void persist(IUtilisateurCerbere pObject) throws Exception;
	
	

	/**
	 * <b>crée un objet métier pObject dans le stockage 
	 * et retourne l'identifiant (ou index 0-based) 
	 * de l'objet persisté</b>.<br/>
	 * <ul>
	 * <li>ne crée <b>pas de doublon</b>.</li>
	 * <li>retourne null si pObject existe déjà dans le stockage.</li>
	 * <li>retourne null si les attributs obligatoires 
	 * de pObject ne sont pas remplis.</li>
	 * </ul>
	 * - retourne null si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : IUtilisateurCerbere : 
	 * l'objet métier à persister dans le stockage.<br/>
	 * 
	 * @return : Long : 
	 * identifiant (ou index 0-based) 
	 * de l'objet persisté dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	Long createReturnId(IUtilisateurCerbere pObject) throws Exception;
	
	

	/**
	 * <b>crée un iterable d'objets métier pList 
	 * dans le stockage</b> et retourne l'itérable persisté.<br/>
	 * <ul>
	 * <li>ne crée <b>pas de doublon</b>.</li>
	 * <li>ne fait rien et continue la sauvegarde si un objet 
	 * dans l'itérable existe déjà dans le stockage.</li>
	 * <li>ne fait rien et continue la sauvegarde si un objet 
	 * null est contenu dans l'itérable.</li>
	 * <li>ne fait rien et continue la sauvegarde 
	 * si les attributs obligatoires 
	 * d'un objet de l'itérable ne sont pas remplis.</li>
	 * <li>retourne une liste <b>vide</b> (pas null) 
	 * si aucun objet ne l'itérable n'a pu être enregistré.</li>
	 * </ul>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : Iterable&lt;IUtilisateurCerbere&gt; : 
	 * itérable d'objets métier à persister dans le stockage.<br/>
	 * 
	 * @return Iterable&lt;IUtilisateurCerbere&gt; : 
	 * itérable d'objets métier persistés dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	Iterable<IUtilisateurCerbere> saveIterable(Iterable<IUtilisateurCerbere> pList) 
				throws Exception;
	
	

	/* READ *************/


	/**
	 * <b>recherche un objet métier pObject 
	 * dans le stockage</b> et retourne l'objet métier persisté.<br/>
	 * recherche l'objet métier par son égalité métier 
	 * (equals()).<br/>
	 * <ul>
	 * <li>retourne null si l'objet métier 
	 * n'existe pas dans le stockage.</li>
	 * </ul>
	 * - retourne null si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : IUtilisateurCerbere : 
	 * objet métier à rechercher.<br/>
	 * 
	 * @return : IUtilisateurCerbere : 
	 * objet métier recherché.<br/>
	 * 
	 * @throws Exception
	 */
	IUtilisateurCerbere retrieve(IUtilisateurCerbere pObject) throws Exception;
	
	

	/**
	 * <b>recherche un objet métier pObject via son identifiant (index) 
	 * dans le stockage</b> et retourne l'objet métier persisté.<br/>
	 * L'identifiant peut être soit un identifiant en base, 
	 * soit un index 0-based indiquant la position 
	 * de l'objet dans le stockage (fichier XML par exemple).<br/>
	 * <ul>
	 * <li>retourne null si l'objet métier d'identifiant pId
	 * n'existe pas dans le stockage.</li>
	 * </ul>
	 * - retourne null si pId == null.<br/>
	 * <br/>
	 *
	 * @param pId : Long : 
	 * index (0-based) ou identifiant en base 
	 * de l'objet métier à rechercher.<br/>
	 * 
	 * @return : IUtilisateurCerbere : objet métier recherché.<br/>
	 * 
	 * @throws Exception
	 */
	IUtilisateurCerbere findById(Long pId) throws Exception;
	

	
	/**
	 * <b>retourne l'identifiant ou l'index (0-based) 
	 * de l'objet métier pObject dans le stockage</b>.<br/>
	 * L'identifiant peut être soit un identifiant en base, 
	 * soit un index 0-based indiquant la position 
	 * de l'objet dans le stockage (fichier XML par exemple).<br/>
	 * <ul>
	 * <li>retourne null si l'objet n' existe pas dans le stockage.</li>
	 * </ul>
	 *
	 * @param pObject : IUtilisateurCerbere : 
	 * objet métier dont on recherche l'identifiant.<br/>
	 *  
	 * @return Long : 
	 * identifiant ou index (0-based) dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	Long retrieveId(IUtilisateurCerbere pObject) throws Exception;
	
	
	
	/**
	 * recherche tous les objets métier <b>contenant pString 
	 * dans au moins un de leurs attributs</b> 
	 * utilisés dans <i>equals()</i>.<br/>
	 * <ul>
	 * <li>retourne null aucun objet remplissant 
	 * les conditions n'existe dans le stockage.</li>
	 * </ul>
	 *
	 * @param pString : String : chaîne de caractères à entourer 
	 * de JOKERS pour voir si elle est contenue dans 
	 * un des attributs du equals().<br/>
	 * 
	 * @return List&lt;IUtilisateurCerbere&gt; : 
	 * liste des objets métier dont au moins 1 des attributs 
	 * de equals contient pString.<br/>
	 * 
	 * @throws Exception
	 */
	List<IUtilisateurCerbere> rechercherRapide(
			String pString) throws Exception;
	
	

	/**
	 * <b>retourne la liste de tous les objets métier 
	 * persistés dans le stockage</b>.<br/>
	 * - peut retourner null si le stockage ne peut être lu.<br/>
	 *
	 * @return : List&lt;IUtilisateurCerbere&gt; : 
	 * liste de tous les objets métier persistés dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	List<IUtilisateurCerbere> findAll() throws Exception;
	
	

	/**
	 * <b>retourne la liste des pMaxResult objets métier 
	 * persistés dans le stockage</b> à partir de la 
	 * position pStartPosition (0-based).<br/>
	 * <ul>
	 * <li>retourne par exemple les 50 objets métier stockés 
	 * à partir du 100ème.</li>
	 * <li>retourne [count() - pStartPosition] objets 
	 * si [pStartPosition + pMaxResult] sort des indexes.</li>
	 * <li>retourne null si pStartPosition est hors indexes.</li>
	 * </ul>
	 *
	 * @param pStartPosition : int : index (0-based) de départ.<br/>
	 * @param pMaxResult : int : 
	 * nombre maximum d'objets métier à retourner.<br/>
	 * 
	 * @return : List&lt;IUtilisateurCerbere&gt; : 
	 * liste des pMax objets métier persistés dans le stockage 
	 * à partir de pStartPosition (0-based).<br/>
	 * 
	 * @throws Exception
	 */
	List<IUtilisateurCerbere> findAllMax(
			int pStartPosition, int pMaxResult) throws Exception;
	
	

	/**
	 * <b>retourne une Collection iterable d'Objets métier 
	 * (List&lt;IUtilisateurCerbere&gt;) dont les IDs appartiennent 
	 * à la Collection itérable d'IDs passée en paramètre.</b>
	 * <ul>
	 * <li>retourne une liste <b>vide</b> (pas null) 
	 * si aucun objet avec un ID contenu dans pIds 
	 * n'a été persisté dans le stockage.</li>
	 * </ul>
	 * - retourne null si pIds == null.<br/>
	 * - ne retourne que les objets de la collection 
	 * effectivement persistés en base.<br/>
	 * <br/>
	 *
	 * @param pIds : Iterable&lt;Long&gt;.<br/>
	 * 
	 * @return Iterable&lt;IUtilisateurCerbere&gt; : 
	 * List&lt;IUtilisateurCerbere&gt;.<br/>
	 * 
	 * @throws Exception 
	 */
	Iterable<IUtilisateurCerbere> findAllIterable(Iterable<Long> pIds) throws Exception;



	/* UPDATE *************/

	
	/**
	 * <ul>
	 * <li><b>Modifie</b> un objet métier <b>persistant</b> 
	 * <i>existant</i> dans le stockage.</li>
	 * <li><i>A utiliser pour les DAO utilisant une 
	 * technologie capable de tracker une ENTITE 
	 * (JPA + HIBERNATE par exemple) et de faire un merge()</i>.</li>
	 * <li>Retourne l'objet métier pObject 
	 * <b>modifié dans le stockage</b>.</li>
	 * <li>Les modifications ne doivent 
	 * pas altérer l'identifiant dans le stockage.</li>
	 * <li>Les modifications ne doivent pas créer 
	 * un doublon dans le stockage. retourne null sinon.</li>
	 * </ul>
	 * - retourne null si pObject == null.<br/>
	 * - ne fait rien et retourne l'instance détachée 
	 * si pObject n'est pas déjà persistant dans le stockage.<br/>
	 * <br/>
	 * <code>Exemple de code : </code><br/>
	 * <code>// Récupération de l'objet persistant à modifier.</code><br/>
	 * <code>objet1Persistant = this.dao.retrieve(objet1);</code><br/>
	 * <code>// Modifications.</code><br/>
	 * <code>objet1Persistant.setPrenom(
	 * "Jean-Frédéric modifié");</code><br/>
	 * <code>objet1Persistant.setNom("Bôrne modifié");</code><br/>
	 * <code>// Application des modifications en base.</code><br/>
	 * <code>objet1ModifiePersistant = 
	 * this.dao.<b>update(objet1Persistant)</b>;</code><br/>
	 * <br/>
	 *
	 * @param pObject : IUtilisateurCerbere : 
	 * objet métier comportant les modifications 
	 * à appliquer à l'objet persistant.<br/>
	 * 
	 * @return : IUtilisateurCerbere : 
	 * objet métier persistant modifié dans le stockage.<br/>
	 * 
	 * @throws Exception 
	 */
	IUtilisateurCerbere update(IUtilisateurCerbere pObject) throws Exception;
	
	

	/**
	 * <b>modifie dans le stockage 
	 * l'objet d'index (0-based) ou d'identifiant pId 
	 * avec les valeurs 
	 * contenues dans pObjectModifie</b>.<br/>
	 * substitue pObjectModifie à l'objet persistant
	 *  d'ID pId dans le stockage.<br/>
	 * <ul>
	 * <li><b>pId doit correspondre à l'index (0-based) 
	 * de l'objet métier à modifier</b> pour un DAO non JPA
	 * , à l'ID en base sinon.</li>
	 * <li>pObjectModifie est un conteneur contenant les modifications 
	 * à apporter à un objet persistant dans le stockage. 
	 * Il n'a pas besoin d'avoir d'attribut id.</li>
	 * <li>retourne null si l'objet modifie pObjectModifie 
	 * créerait un doublon dans le stockage.</li>
	 * <li>retourne null s'il n'y a pas d'objet persistant à pId.</li>
	 * <li>retourne null si pId est en dehors des indexes.</li>
	 * </ul>
	 * - retourne null si pId == null.<br/>
	 * - retourne null si pObjectModifie == null.<br/>
	 * <br/>
	 *
	 * @param pId : Long : 
	 * index (0-based) de l'objet métier à modifier.<br/>
	 * @param pObjectModifie : IUtilisateurCerbere : 
	 * Objet métier contenant les modifications 
	 * à apporter à l'objet persistant dans le stockage.<br/>
	 * 
	 * @return IUtilisateurCerbere : objet métier persistant modifié.<br/>
	 * 
	 * @throws Exception
	 */
	IUtilisateurCerbere updateById(Long pId, IUtilisateurCerbere pObjectModifie) throws Exception;



	/* DELETE *************/


	/**
	 * <b>retire l'objet métier pObject dans le stockage</b>.<br/>
	 * retourne true si le retrait a bien été effectué.<br/>
	 * <ul>
	 * <li>retourne false si pObject n'est pas persisté.</li>
	 * </ul>
	 * - retourne false si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : IUtilisateurCerbere : objet métier à détruire.<br/>
	 * 
	 * @return : boolean : 
	 * true si l'objet métier a été détruit.<br/>
	 * 
	 * @throws Exception
	 */
	boolean delete(IUtilisateurCerbere pObject) throws Exception;
	
	

	/**
	 * <b>retire l'objet métier d'identifiant ou 
	 * d'index (0-based) pId dans le stockage</b>.<br/>
	 * <ul>
	 * <li>ne fait rien si pId est hors indexes.</li>
	 * </ul>
	 * - ne fait rien si pId == null.<br/>
	 * <br/>
	 *
	 * @param pId : Long : 
	 * index (0-based) de l'objet métier à modifier.<br/>
	 * 
	 * @throws Exception
	 */
	void deleteById(Long pId) throws Exception;
	
	

	/**
	 * <b>retire l'objet métier d'identifiant ou 
	 * d'index (0-based) pId dans le stockage</b>.<br/>
	 * retourne true si le retrait à bien été effectué.<br/>
	 * <ul>
	 * <li>retourne false si pId est hors indexes.</li>
	 * </ul>
	 * - retourne false si pId == null.<br/>
	 * <br/>
	 *
	 * @param pId : Long : 
	 * index (0-based) de l'objet métier à modifier.<br/>
	 * 
	 * @return boolean : true si le retrait à bien été effectué.<br/>
	 * 
	 * @throws Exception 
	 */
	boolean deleteByIdBoolean(Long pId) throws Exception;
	
	

	/**
	 * <b>retire tous les objets métier dans le stockage</b>.<br/>
	 *
	 * @throws Exception
	 */
	void deleteAll() throws Exception;
	
	

	/**
	 * <b>retire tous les objets métier dans le stockage</b>.<br/>
	 * retourne true si le retrait a bien été effectué.<br/>
	 *
	 * @return : boolean : 
	 * true si le retrait a bien été effectué.<br/>
	 * 
	 * @throws Exception
	 */
	boolean deleteAllBoolean() throws Exception;
	
	

	/**
	 * <b>retire tous les objets de l'itérable pList déjà persistés 
	 * dans le stockage</b>.<br/>
	 * ne retourne rien.<br/>
	 * <ul>
	 * <li>ne fait rien et continue le processus de retrait 
	 * si un objet de l'itérable n'est pas persisté.</li>
	 * </ul>
	 * - ne fait rien si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : Iterable&lt;IUtilisateurCerbere&gt; : 
	 * itérable d'objets à retirer du stockage.<br/>
	 *  
	 * @throws Exception
	 */
	void deleteIterable(Iterable<IUtilisateurCerbere> pList) throws Exception;
	
	

	/**
	 * <b>retire tous les objets de l'itérable pList déjà persistés 
	 * dans le stockage</b>.<br/>
	 * retourne true si le retrait a bien été effectué.<br/>
	 * <ul>
	 * <li>ne fait rien et continue le processus de retrait 
	 * si un objet de l'itérable n'est pas persisté.</li>
	 * </ul>
	 * - retourne false si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : Iterable&lt;IUtilisateurCerbere&gt; : 
	 * itérable d'objets àretirer du stockage?<br/>
	 * 
	 * @return : boolean : true si le retrait a bien été effectué.<br/>
	 * 
	 * @throws Exception
	 */
	boolean deleteIterableBoolean(Iterable<IUtilisateurCerbere> pList) throws Exception;



	/* TOOLS *************/


	/**
	 * <b>retourne true si l'objet métier pObject 
	 * existe dans le stockage</b>.<br/>
	 * <br/>
	 * - retourne false si pObject == null.<br/>
	 * - retourne false si l'Objet métier pObject n'existe pas en base.<br/>
	 * <br/>
	 *
	 * @param pObject : IUtilisateurCerbere : objet métier à rechercher.<br/>
	 * 
	 * @return boolean : 
	 * true si l'objet métier pObject existe dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	boolean exists(IUtilisateurCerbere pObject) throws Exception;
	
	

	/**
	 * <b>retourne true si l'objet métier pObject 
	 * d'identifiant ou d'index (0-based) pId 
	 * existe dans le stockage</b>.<br/>
	 * <br/>
	 * - retourne false si pId == null.<br/>
	 * - retourne false si pId est hors indexes.<br/>
	 * - retourne false si il n'existe pas d'objet métier 
	 * d'index pId (0-based) dans le stockage.<br/>
	 * <br/>
	 *
	 * @param pId : Long : 
	 * identifiant ou index (0-based) de l'objet métier à 
	 * trouver dans le stockage.<br/>
	 * 
	 * @return boolean : 
	 * true si l'objet métier existe dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	boolean existsId(Long pId) throws Exception;
	
	

	/**
	 * <b>retourne le nombre total d'objets métier</b> 
	 * stockés dans le stockage.<br/>
	 * <br/>
	 * - retourne 0L si le stockage est vide.<br/>
	 * - retourne null si this.findAll() retourne null.<br/>
	 * <br/>
	 *
	 * @return : Long : 
	 * nombre d'enregistrements dans le stockage.<br/>
	 * 
	 * @throws Exception
	 */
	Long count() throws Exception;
	
	

	/**
	 * <b>écrit le contenu du stockage dans la console</b>.<br/>
	 * <br/>
	 * - ne fait rien si findAll() retourne null.<br/>
	 * <br/>
	 *
	 * @throws Exception
	 */
	void ecrireStockageDansConsole() throws Exception;
	
	

	/**
	 * <b>fournit une String pour l'affichage à la console 
	 * d'une Liste d'OBJETS METIER</b>.<br/>
	 * <br/>
	 * retourne null si pList == null.<br/>
	 *
	 * @param pList : List&lt;IUtilisateurCerbere&gt;.<br/>
	 * 
	 * @return : String.<br/>
	 */
	String afficherListeObjetsMetier(List<IUtilisateurCerbere> pList);
	
	
	
	/**
	 * Getter de la Liste des messages d'erreur 
	 * à l'intention de l'utilisateur.<br/>
	 * Ne peut jamis être null. <b>tester avec isEmpty()</b>.<br/>
	 *
	 * @return this.messagesErrorUtilisateurList : 
	 * List&lt;String&gt;.<br/>
	 */
	List<String> getMessagesErrorUtilisateurList();	
	
	
	
} // FIN DE L'INTERFACE IUtilisateurCerbereDAO.------------------------------
