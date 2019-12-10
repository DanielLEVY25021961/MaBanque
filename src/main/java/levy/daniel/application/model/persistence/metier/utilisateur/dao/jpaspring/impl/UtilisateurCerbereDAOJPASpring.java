package levy.daniel.application.model.persistence.metier.utilisateur.dao.jpaspring.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.metier.utilisateur.impl.UtilisateurCerbere;
import levy.daniel.application.model.persistence.daoexceptions.GestionnaireDaoException;
import levy.daniel.application.model.persistence.metier.utilisateur.IUtilisateurCerbereDAO;
import levy.daniel.application.model.persistence.metier.utilisateur.UtilisateurCerbereConvertisseurMetierEntity;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.UtilisateurCerbereEntityJPA;


/**
 * CLASSE UtilisateurCerbereDAOJPASpring :<br/>
 * DAO (Data Access Object) JPA avec SPRING <i>CONCRET</i> 
 * pour les {@link UtilisateurCerbere}.<br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">CONCEPT 
 * CONCERNE PAR CE DAO</span>
 * </p>
 * 
 * <p>
 * <b>{@link IUtilisateurCerbere}</b> modélise un <i>concept</i> 
 * de <b>Utilisateur</b> 
 * (internaute) avec un nom, un prénom, un Service, une Unité 
 * et un <i>rôle</i> dans l'application (ADMINISTRATEUR, GESTIONNAIRE, ...).<br/>
 * Un IUtilisateurCerbere est conforme au User retourné par l'API 
 * d'authentification CERBERE du Ministère (MTES).
 * </p>
 * 
 * <p>
 * <span style="text-decoration: underline;">DESCRIPTION DE 
 * DAO</span>
 * </p>
 * <ul>
 * <li>DAO <b>CONCRET</b> pour les <b>{@link IUtilisateurCerbere}</b>.</li>
 * <li>
 * Implémente l'interface <b>IUtilisateurCerbereDAO</b>.
 * </li>
 * <li>
 * DAO pour serializer des ENTITIES JPA {@link UtilisateurCerbereEntityJPA} 
 * lors de l'utilisation de Java Persistence API (JPA)
 * pour la persistence dans un contexte SPRING.
 * </li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">IMPLEMENTATION DES DAO</span>
 * </p>
 * <ul>
 * <li>
 * <img src="../../../../../../../../../../../../../../../javadoc/images/implementation_UtilisateurCerbere_DAO_JpaSpring.png" 
 * alt="implémentation des DAOs UtilisateurCerbere JPA SPRING" border="1" align="center" />
 * </li>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">UTILISATION DES DAO</span>
 * </p>
 * <ul>
 * <li>
 * <img src="../../../../../../../../../../../../javadoc/images/utilisation_UtilisateurCerbere_DAO_JpaSpring.png" 
 * alt="utilisation des DAOs UtilisateurCerbere JPA SPRING" border="1" align="center" />
 * </li>
 * </ul>
 * 
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
@Repository(value="UtilisateurCerbereDAOJPASpring")
public class UtilisateurCerbereDAOJPASpring implements IUtilisateurCerbereDAO {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe UtilisateurCerbereDAOJPASpring".<br/>
	 */
	public static final String CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING 
		= "Classe UtilisateurCerbereDAOJPASpring";
	
	/**
	 * " - ".
	 */
	public static final String SEPARATEUR_MOINS_AERE 
		= " - ";
	
	/**
	 * saut de ligne de la plateforme.<br/>
	 */
	public static final String SAUT_LIGNE_PLATEFORME 
		= System.getProperty("line.separator");
	
	/**
	 * "this.entityManager est NULL dans le présent DAO".<br/>
	 */
	public static final String MESSAGE_ENTITYMANAGER_NULL 
	= "this.entityManager est NULL dans le présent DAO";

	
	/**
	 * "select utilisateurCerbere from 
	 * UtilisateurCerbereEntityJPA as utilisateurCerbere ".<br/>
	 */
	public static final String SELECT_OBJET 
		= "select utilisateurCerbere from "
				+ "UtilisateurCerbereEntityJPA as utilisateurCerbere ";
	
	/**
	 * "STOCKAGE IMPOSSIBLE : l'Objet métier à créer est null".
	 */
	public static final String OBJET_NULL 
		= "STOCKAGE IMPOSSIBLE : l'Objet métier à créer est null";
	
	/**
	 * "STOCKAGE IMPOSSIBLE - DOUBLON : 
	 * l'objet existe déjà dans le stockage : ".
	 */
	public static final String DOUBLON 
		= "STOCKAGE IMPOSSIBLE - DOUBLON : l'objet existe déjà dans le stockage : ";
	
	/**
	 * "STOCKAGE IMPOSSIBLE - les champs OBLIGATOIRES (prénom, nom, email, unité) ne sont pas remplis : ".
	 */
	public static final String CHAMPS_OBLIGATOIRES 
		= "STOCKAGE IMPOSSIBLE - les champs OBLIGATOIRES (prénom, nom, email, unité) ne sont pas remplis : ";
	
	/**
	 * JPA EntityManager <b>injecté par SPRING</b>.<br/>
	 */
	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private transient EntityManager entityManager;
	
	/**
	 * Gestionnaire pour les Exceptions de DAO.<br/>
	 */
	private final transient GestionnaireDaoException gestionnaireException 
		= new GestionnaireDaoException();

	/**
	 * Liste des messages d'erreur à l'intention de l'utilisateur.<br/>
	 * Ne peut jamis être null. <b>tester avec isEmpty()</b>.<br/>
	 */
	private transient List<String> messagesErrorUtilisateurList 
		= new ArrayList<String>(); 
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereDAOJPASpring.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereDAOJPASpring() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * retourne un boolean qui stipule si les champs obligatoires 
	 * de pObject sont bien remplis.<br/>
	 * <br/>
	 * - retourne true si les champs obligatoires sont tous remplis.<br/>
	 * - retourne false si pObject == null.<br/>
	 * <br/>
	 *
	 * @param pObject : IUtilisateurCerbere : Objet Métier.
	 * 
	 * @return : boolean : 
	 * true si les champs obligatoires sont tous remplis.<br/>
	 */
	private boolean champsObligatoiresRemplis(
				final IUtilisateurCerbere pObject) {
		
		/* retourne false si pObject == null. */
		if (pObject == null) {
			return false;
		}
		
		if (StringUtils.isBlank(pObject.getPrenom())) {
			return false;
		}
		
		if (StringUtils.isBlank(pObject.getNom())) {
			return false;
		}
		
		if (StringUtils.isBlank(pObject.getEmail())) {
			return false;
		}
		
		if (StringUtils.isBlank(pObject.getUnite())) {
			return false;
		}
		
		return true;
		
	} // Fin de champsObligatoiresRemplis(...).____________________________


	
	/**
	 * retourne la requete JPQL paramétrée pour la méthode 
	 * <code><b>retrieve(OBJET METIER pObject)</b></code> 
	 * en tenant compte de la getion des paramètres null.<br/>
	 * <br/>
	 *
	 * @param pObject : ITeleversement : OBJET METIER
	 * 
	 * @return javax.persistence.Query
	 * 
	 * @throws Exception
	 */
	private Query fournirRequeteEgaliteMetier(
			final IUtilisateurCerbere pObject) throws Exception {
		
		/* REQUETE HQL PARMETREE. */
		String requeteString = null;
		
		Query requete = null;
				
		/* REQUETE HQL PARMETREE. */
		requeteString 
			= SELECT_OBJET
				+ "where ((utilisateurCerbere.prenom IS NULL and :pPrenom IS NULL) OR (utilisateurCerbere.prenom = :pPrenom)) "
				+ "and ((utilisateurCerbere.nom IS NULL and :pNom IS NULL) OR (utilisateurCerbere.nom = :pNom)) "
				+ "and ((utilisateurCerbere.email IS NULL and :pEmail IS NULL) OR (utilisateurCerbere.email = :pEmail)) "
				+ "and ((utilisateurCerbere.unite IS NULL and :pUnite IS NULL) OR (utilisateurCerbere.unite = :pUnite))";
		
		/* Construction de la requête HQL. */
		requete 
			= this.entityManager.createQuery(requeteString);
		
		/* Passage des paramètres de la requête HQL. */
		requete.setParameter("pPrenom", pObject.getPrenom());
		requete.setParameter("pNom", pObject.getNom());
		requete.setParameter("pEmail", pObject.getEmail());
		requete.setParameter("pUnite", pObject.getUnite());
		
		return requete;
				
	} // Fin de fournirRequeteEgaliteMetier(...).__________________________
	
	

	/* CREATE ************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere create(
			final IUtilisateurCerbere pObject) throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* instancie une nouvelle liste à chaque appel de la méthode. */
		this.messagesErrorUtilisateurList = new ArrayList<String>();

		
		/* NULL : retourne null si pObject == null. */
		if (pObject == null) {
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(OBJET_NULL);
			
			return null;
			
		} // Fin de NULL._____________________________________
		
		
		/* CHAMPS OBLIGATOIRES : retourne null si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pObject)) {
			
			final String message = CHAMPS_OBLIGATOIRES + pObject.toString();
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(message);
			
			return null;
			
		} // Fin de CHAMPS OBLIGATOIRES.______________________________
		
		
		/* DOUBLON : retourne null si pObject est un doublon. */
		if (this.exists(pObject)) {
			
			final String message = DOUBLON + pObject.toString();
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(message);
			
			return null;
			
		} // Fin de DOUBLON.___________________________________
		

		IUtilisateurCerbere persistentObject = null;

		try {
			
			/* conversion de l'OBJET METIER en ENTITY. */
			final UtilisateurCerbereEntityJPA entity 
				= UtilisateurCerbereConvertisseurMetierEntity
						.convertirObjetMetierEnEntityJPA(pObject);

			/* ***************** */
			/* PERSISTE en base. */
			this.entityManager.persist(entity);

			/* conversion de l'ENTITY en OBJET METIER. */
			persistentObject 
				= UtilisateurCerbereConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
							, "méthode create(object)", e);

		}

		/* retourne l'Objet persistant. */
		return persistentObject;

	} // Fin de create(...)._______________________________________________


	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere createOrRetrieve(
			final IUtilisateurCerbere pEntity) throws Exception {
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		/* instancie une nouvelle liste à chaque appel de la méthode. */
		this.messagesErrorUtilisateurList = new ArrayList<String>();

		
		/* NULL : retourne null si pObject == null. */
		if (pEntity == null) {
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(OBJET_NULL);
			
			return null;
			
		} // Fin de NULL._____________________________________
		
		
		/* CHAMPS OBLIGATOIRES : retourne null si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pEntity)) {
			
			final String message = CHAMPS_OBLIGATOIRES + pEntity.toString();
			
			/* ajout d'une explication dans le rapport utilisateur. */
			this.messagesErrorUtilisateurList.add(message);
			
			return null;
			
		} // Fin de CHAMPS OBLIGATOIRES.______________________________
		
		
		/* DOUBLON : retourne l'objet déjà persisté si pObject est un doublon. */
		if (this.exists(pEntity)) {
			
			return UtilisateurCerbereConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(
							this.retrieve(pEntity));
			
		} // Fin de DOUBLON.___________________________________
		

		IUtilisateurCerbere persistentObject = null;

		try {
			
			UtilisateurCerbereEntityJPA entity = null;
			
			/* conversion de l'OBJET METIER en ENTITY. */
			if (!(pEntity instanceof UtilisateurCerbereEntityJPA)) {
				entity 
				= UtilisateurCerbereConvertisseurMetierEntity
						.convertirObjetMetierEnEntityJPA(pEntity);
			} else {
				entity = (UtilisateurCerbereEntityJPA) pEntity;
			}
			

			/* ***************** */
			/* PERSISTE en base. */
			this.entityManager.persist(entity);

			/* RETOURNE L'ENTITY. */
			persistentObject = entity;

		}
		catch (Exception e) {

			final String message 
				= CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING 
				+ SEPARATEUR_MOINS_AERE
				+ "méthode createOrRetrieve(object)"
				+ SEPARATEUR_MOINS_AERE 
				+ "Impossible de persister l'Objet : " 
				+ pEntity.toString()
				+ SEPARATEUR_MOINS_AERE
				+ e.getMessage();
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(message, e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
							, "méthode createOrRetrieve(object)", e);

		}

		/* retourne l'Objet persistant. */
		return persistentObject;

	} // Fin de createOrRetrieve(...)._____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void persist(
			final IUtilisateurCerbere pObject) throws Exception {
		
		/* ne fait rien si pObject == null. */
		if (pObject == null) {
			return;
		}
		
		/* ne fait rien si pObject est un doublon. */
		if (this.exists(pObject)) {
			return;
		}
		
		if (this.existsId(pObject.getId())) {
			return;
		}
		
		/* ne fait rien si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pObject)) {
			return;
		}

		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}

		/* conversion de l'OBJET METIER en ENTITY. */
		final UtilisateurCerbereEntityJPA entity = 
				UtilisateurCerbereConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(pObject);

		try {
			
			/* ***************** */
			/* PERSISTE en base. */
			this.entityManager.persist(entity);

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
							, "méthode persist(object)", e);

		}

	} // Fin de persist(...).______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long createReturnId(
			final IUtilisateurCerbere pObject) throws Exception {
		
		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		/* retourne null si pObject est un doublon. */
		if (this.exists(pObject)) {
			return null;
		}
		
		/* retourne null si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pObject)) {
			return null;
		}
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		
		/* conversion de l'OBJET METIER en ENTITY. */
		final UtilisateurCerbereEntityJPA entity = 
				UtilisateurCerbereConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(pObject);
		
		IUtilisateurCerbere persistentObject = null;
		
		try {
			
			/* ***************** */
			/* PERSISTE en base. */
			this.entityManager.persist(entity);
			
			/* conversion de l'ENTITY en OBJET METIER. */
			persistentObject 
				= UtilisateurCerbereConvertisseurMetierEntity
				.convertirEntityJPAEnObjetMetier(entity);

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
							, "méthode createReturnId(object)", e);

		}
		
		/* retourne l'ID de l'Objet persistant. */
		if (persistentObject != null) {
			return persistentObject.getId();
		}
		
		return null;		

	} // Fin de createReturnId(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<IUtilisateurCerbere> saveIterable(
			final Iterable<IUtilisateurCerbere> pList) 
					throws Exception {

		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		/* conversion de le liste de MODEL en liste d'ENTITIES. */
		final List<UtilisateurCerbereEntityJPA> listeEntities 
			= UtilisateurCerbereConvertisseurMetierEntity
				.convertirListModelEnEntitiesJPA(pList);
		
		final List<IUtilisateurCerbere> resultat 
			= new ArrayList<IUtilisateurCerbere>();

		final Iterator<UtilisateurCerbereEntityJPA> iteS 
			= listeEntities.iterator();

		try {

			while (iteS.hasNext()) {

				final UtilisateurCerbereEntityJPA entity = iteS.next();

				/* passe un null dans le lot. */
				if (entity == null) {
					continue;
				}
					
					
				/* Passe les doublons existants en base. */
				if (!this.exists(entity)) {
					
					/* passe si les attributs obligatoires 
					 * de l'objet ne sont pas remplis.*/
					if (this.champsObligatoiresRemplis(entity)) {
						
						IUtilisateurCerbere objectPersistant = null;

						try {

							/* ***************** */
							/* PERSISTE en base. */
							this.entityManager.persist(entity);

							/* conversion de l'ENTITY en OBJET METIER. */
							objectPersistant 
								= UtilisateurCerbereConvertisseurMetierEntity
								.convertirEntityJPAEnObjetMetier(entity);

						} catch (Exception e) {

							/* LOG. */
							if (LOG.isFatalEnabled()) {
								LOG.fatal(e.getMessage(), e);
							}

							/* Gestion de la DAO Exception. */
							this.gestionnaireException
								.gererException(
										CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
											, "Méthode saveIterable(lot)", e);
						}
						
						/* ne sauvegarde pas un doublon 
						 * présent dans le lot. */
						if (objectPersistant != null) {

							/* Ajoute à l'iterable resultat. */
							resultat.add(objectPersistant);								
						}
						
					} // Entity avec attributs obligatoires remplis.
					
				} // Entity persistante._________________
				
			} // Next._____________________________________

		}
		catch (Exception e) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
							, "Méthode saveIterable(lot)", e);

		}

		/* retourne l'iterable resultat. */
		return resultat;

	} // Fin de saveIterable(...)._________________________________________
	
	

	/* READ *************/


	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere retrieve(
			final IUtilisateurCerbere pObject) throws Exception {

		/* return null si pObject == null. */
		if (pObject == null) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {

			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		IUtilisateurCerbere objetResultat = null;		
		UtilisateurCerbereEntityJPA entity = null;

		/* récupération de la requête paramétrée. */
		final Query requete = this.fournirRequeteEgaliteMetier(pObject);
		
		try {
			
			/* Execution de la requete HQL. */
			entity 
				= (UtilisateurCerbereEntityJPA) requete.getSingleResult();
			
			/* conversion de l'ENTITY en OBJET METIER. */
			objetResultat 
				= UtilisateurCerbereConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);
			
		}
		catch (NoResultException noResultExc) {
			
			/* retourne null si l'Objet métier n'existe pas 
			 * dans le stockage. */
			return null;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
						, "Méthode retrieve(objet)", e);
		}
		
		/* retourne l'objet metier trouvé. */
		return objetResultat;

	} // Fin de retrieve(...)._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere findById(
			final Long pId) throws Exception {
		
		/* retourne null si pId == null. */
		if (pId == null) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}

		IUtilisateurCerbere objetTrouve = null;
		UtilisateurCerbereEntityJPA entity = null;

		try {
			
			/* ************************* */
			/* récupération de l'ENTITY. */
			entity 
				= this.entityManager.find(
						UtilisateurCerbereEntityJPA.class, pId);
			
			/* conversion de l'ENTITY en OBJET METIER. */
			objetTrouve 
				= UtilisateurCerbereConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);
			
		}
		catch (Exception e) {
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
						, "Méthode findById(ID)", e);
			
		}
		
		/* retourne l'objet metier trouvé. */
		return objetTrouve;
				
	} // Fin de findById(...)._____________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long retrieveId(
			final IUtilisateurCerbere pObject) throws Exception {
		
		/* return null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		IUtilisateurCerbere objetResultat = null;		
		UtilisateurCerbereEntityJPA entity = null;
		
		/* récupération de la requête paramétrée. */
		final Query requete = this.fournirRequeteEgaliteMetier(pObject);
		
		try {
			
			/* Execution de la requete HQL. */
			entity 
				= (UtilisateurCerbereEntityJPA) requete.getSingleResult();
			
			/* conversion de l'ENTITY en OBJET METIER. */
			objetResultat 
				= UtilisateurCerbereConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);
			
		}
		catch (NoResultException noResultExc) {
			
			/* retourne null si l'Objet métier n'existe pas 
			 * dans le stockage. */
			return null;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
						, "Méthode retrieveId(objet)", e);
		}
		
		/* retourne l'ID de l'objet metier trouvé. */
		if (objetResultat != null) {
			return objetResultat.getId();
		}
		
		return null;
		
	} // Fin de retrieveId(...).___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IUtilisateurCerbere> rechercherRapide(
			final String pString) throws Exception {
				
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		/* Création de la requête JPQL sous forme de String. */
		final String requeteString 
			= SELECT_OBJET 
			+ "where utilisateurCerbere.prenom LIKE :pattern "
					+ "OR utilisateurCerbere.nom LIKE :pattern "
					+ "OR utilisateurCerbere.email LIKE :pattern "
					+ "OR utilisateurCerbere.unite LIKE :pattern";
		
		/* Construction de la requête HQL. */
		final Query requete 
			= this.entityManager.createQuery(requeteString);
		
		/* Passage des paramètres de la requête HQL. */
		final String patternRecherche = "%" + pString + "%";
		
		requete.setParameter("pattern", patternRecherche);
		
		List<UtilisateurCerbereEntityJPA> resultatEntity = null;
				
		List<IUtilisateurCerbere> resultat = null;
		
		try {
			
			/* Execution de la requete HQL. */
			resultatEntity 
				= requete.getResultList();
			
			/* conversion de la liste ENTITY en liste OBJET METIER. */
			resultat 
				= UtilisateurCerbereConvertisseurMetierEntity
					.convertirListEntitiesJPAEnModel(resultatEntity);
			
		}
		catch (NoResultException noResultExc) {
			
			/* retourne null si l'Objet métier n'existe pas 
			 * dans le stockage. */
			return null;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
						, "Méthode rechercherRapide(String %recherche%)", e);
		}
		
		/* retourne la liste d'objets métier. */
		if (resultat != null) {
			return resultat;
		}
		
		return null;
		
	} // Fin de rechercherRapide(...)._____________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IUtilisateurCerbere> findAll() throws Exception {
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "from UtilisateurCerbereEntityJPA";
		
		List<UtilisateurCerbereEntityJPA> resultatEntity = null;
		
		List<IUtilisateurCerbere> resultat = null;
		
		try {
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);
			
			/* Exécute la javax.persistence.Query. */
			resultatEntity = query.getResultList();
			
			/* convertit la liste d'Entities en OBJETS METIER. */
			resultat = UtilisateurCerbereConvertisseurMetierEntity
						.convertirListEntitiesJPAEnModel(
								resultatEntity);

		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
						, "Méthode findAll()", e);
			
		}
		
		/* Retourne la liste résultat. */
		return resultat;
		
	} // Fin de findAll()._________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IUtilisateurCerbere> findAllMax(
			final int pStartPosition
				, final int pMaxResult) throws Exception {

		/* retourne null si pId est en dehors des index de stockage. */
		if (pStartPosition > this.count() - 1) {
			return null;
		}
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
		
		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "from UtilisateurCerbereEntityJPA";
		
		List<UtilisateurCerbereEntityJPA> resultatEntity = null;
		
		List<IUtilisateurCerbere> resultat = null;
		
		try {
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString)
					.setFirstResult(pStartPosition)
						.setMaxResults(pMaxResult);
			
			/* Exécute la javax.persistence.Query. */
			resultatEntity = query.getResultList();
						
			/* convertit la liste d'Entities en OBJETS METIER. */
			resultat 
			= UtilisateurCerbereConvertisseurMetierEntity
				.convertirListEntitiesJPAEnModel(resultatEntity);

		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
						, "Méthode findAllMax(int pStartPosition"
								+ ", int pMaxResult)", e);
			
		}
		
		/* Retourne la liste résultat. */
		return resultat;
		
	} // Fin de findAllMax(...).___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<IUtilisateurCerbere> findAllIterable(
			final Iterable<Long> pIds) throws Exception {
		
		/* retourne null si pIds == null. */
		if (pIds == null) {
			return null;
		}

		final List<IUtilisateurCerbere> resultat 
			= new ArrayList<IUtilisateurCerbere>();

		for (final Long id : pIds) {
			
			final IUtilisateurCerbere objet = this.findById(id);
			
			if (objet != null) {
				resultat.add(objet);
			}
		}
		
		/* Retourne la liste résultat. */
		return resultat;
		
	} // Fin de findAllIterable(...).______________________________________



	/* UPDATE *************/

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere update(
			final IUtilisateurCerbere pObject) throws Exception {
		
		/* retourne null si pObject == null. */
		if (pObject == null) {
			return null;
		}
		
		/* retourne pObject si l'objet n'est pas 
		 * déjà persistant en base. */
		if (!this.existsId(pObject.getId())) {
			return pObject;
		}
		
		/* retourne null si pObject créerait un doublon. */
		if (this.exists(pObject)) {
			return null;
		}
		
		/* retourne null si les attributs obligatoires 
		 * de pObject ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pObject)) {
			return null;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
				
		/* conversion de l'OBJET METIER en ENTITY. */
		final UtilisateurCerbereEntityJPA entity = 
				UtilisateurCerbereConvertisseurMetierEntity
					.convertirObjetMetierEnEntityJPA(pObject);

		IUtilisateurCerbere persistentObject = null;
		
		try {
			
			/* MODIFIE en base. */
			this.entityManager.merge(entity);

			/* conversion de l'ENTITY en OBJET METIER. */
			persistentObject 
				= UtilisateurCerbereConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);
			
		}
		catch (Exception e) {
		
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
						, "Méthode update(objet)", e);
						
		}
				
		/* retourne l'Objet persistant modifié. */
		return persistentObject;
		
	} // Fin de update(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public IUtilisateurCerbere updateById(
			final Long pId, final IUtilisateurCerbere pObjectModifie) 
												throws Exception {
		
		/* retourne null si pId == null. */
		if (pId == null) {
			return null;
		}

		/* retourne null si pId est hors indexes. */
		if (this.findById(pId) == null) {
			return null;
		}
		
		/* retourne null si pObjectModifie == null. */
		if (pObjectModifie == null) {
			return null;
		}
		
		/* retourne null si l'objet modifie pObjectModifie 
		 * créerait un doublon dans le stockage. */
		if (this.exists(pObjectModifie)) {
			return null;
		}
		
		/* retourne null si les attributs obligatoires 
		 * de pObjectModifie ne sont pas remplis.*/
		if (!this.champsObligatoiresRemplis(pObjectModifie)) {
			return null;
		}
		
		/* récupère l'objet à modifier par sons index. */
		final IUtilisateurCerbere objetAModifier = this.findById(pId);
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return null;
		}
				
		IUtilisateurCerbere persistentObject = null;
		
		try {
			
			/* applique les modifications. */
			objetAModifier.setCivilite(pObjectModifie.getCivilite());
			objetAModifier.setPrenom(pObjectModifie.getPrenom());
			objetAModifier.setNom(pObjectModifie.getNom());
			objetAModifier.setTel(pObjectModifie.getTel());
			objetAModifier.setEmail(pObjectModifie.getEmail());
			objetAModifier.setService(pObjectModifie.getService());
			objetAModifier.setUnite(pObjectModifie.getUnite());
			objetAModifier.setProfil(pObjectModifie.getProfil());
			objetAModifier.setPortee(pObjectModifie.getPortee());
			objetAModifier.setRestriction(pObjectModifie.getRestriction());
			
			/* conversion de l'OBJET METIER en ENTITY. */
			final UtilisateurCerbereEntityJPA entity = 
					UtilisateurCerbereConvertisseurMetierEntity
						.convertirObjetMetierEnEntityJPA(objetAModifier);

			/* MODIFIE en base. */
			this.entityManager.merge(entity);
			
			/* applique les modifications dans le stockage (si nécessaire). */
			this.entityManager.flush();
			
			/* conversion de l'ENTITY en OBJET METIER. */
			persistentObject 
				= UtilisateurCerbereConvertisseurMetierEntity
					.convertirEntityJPAEnObjetMetier(entity);
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
						, "méthode updateById(Id, Object)", e);
						
		}
				
		/* retourne l'Objet persistant modifié. */
		return persistentObject;
		
	} // Fin de update(...)._______________________________________________



	/* DELETE *************/


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean delete(
			final IUtilisateurCerbere pObject) throws Exception {
		
		/* retourne false si pObject == null. */
		if (pObject == null) {
			return false;
		}
		
		/* récupère l'instance persistante. */
		final IUtilisateurCerbere persistanceInstance 
			= this.retrieve(pObject);
		
		/* retourne false si pObject n'est pas persisté. */
		if (persistanceInstance == null) {
			return false;
		}
				
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}
				
		boolean resultat = false;
			
		try {

			/* récupération de l'ENTITY a détruire. */
			final UtilisateurCerbereEntityJPA entity 
				= this.entityManager.find(
						UtilisateurCerbereEntityJPA.class
							, persistanceInstance.getId());
						
			/* ************ */
			/* DESTRUCTION. */
			this.entityManager.remove(entity);
			
			resultat = true;
							
		} catch (Exception e) {
		
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
						, "Méthode delete(objet)", e);
									
		}
		
		/* retourne le boolean resultat. */
		return resultat;
										
	} // Fin de delete(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteById(
			final Long pId) throws Exception {
		
		/* ne fait rien si pId == null. */
		if (pId == null) {
			return;
		}
		
		/* ne fait rien si pId est hors indexes. */
		if (this.findById(pId) == null) {
			return;
		}
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}
						
		try {
		
			/* récupération de l'ENTITY a détruire. */
			final UtilisateurCerbereEntityJPA entity 
				= this.entityManager.find(
						UtilisateurCerbereEntityJPA.class
							, pId);
						
			/* ************ */
			/* DESTRUCTION. */
			this.entityManager.remove(entity);
			
		}
		catch (Exception e) {
		
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
						, "Méthode deleteById(ID)", e);
		}
		
	} // Fin de deleteById(...).___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteByIdBoolean(
			final Long pId) throws Exception {
		
		/* retourne false si pId == null. */
		if (pId == null) {
			return false;
		}
		
		/* retourne false si pId est hors indexes. */
		if (this.findById(pId) == null) {
			return false;
		}
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}
				
		try {
						
			/* récupération de l'ENTITY a détruire. */
			final UtilisateurCerbereEntityJPA entity 
				= this.entityManager.find(
						UtilisateurCerbereEntityJPA.class
							, pId);
			
			/* ************ */
			/* DESTRUCTION. */
			this.entityManager.remove(entity);
						
			return true;
				
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}

			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
						, "Méthode deleteByIdBoolean(ID)", e);
		}
		
		return false;
		
	} // Fin de deleteByIdBoolean(...).____________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAll() throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}

		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "delete from UtilisateurCerbereEntityJPA";

		try {
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);
			
			/* EXECUTION DE LA REQUETE. */
			query.executeUpdate();
			
		}
		catch (Exception e) {
		
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
						, "Méthode deleteAll()", e);
			
		}
		
	} // Fin de deleteAll()._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteAllBoolean() throws Exception {

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}

		boolean resultat = false;
		
		/* Création de la requête HQL sous forme de String. */
		final String requeteString 
			= "delete from UtilisateurCerbereEntityJPA";
		
		try {
			
			/* Crée la requête javax.persistence.Query. */
			final Query query 
				= this.entityManager.createQuery(requeteString);
			
			/* EXECUTION DE LA REQUETE. */
			query.executeUpdate();
			
			resultat = true;
			
		}
		catch (Exception e) {
					
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(
						CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
						, "Méthode deleteAllBoolean()", e);
			
		}
		
		/* retourne le boolean resultat. */
		return resultat;
		
	} // Fin de deleteAll()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteIterable(
			final Iterable<IUtilisateurCerbere> pList) throws Exception {
		
		/* ne fait rien si pList == null. */
		if (pList == null) {
			return;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return;
		}
		
		final Iterator<IUtilisateurCerbere> ite = pList.iterator();
		
		try {
			
			while (ite.hasNext()) {
				
				final IUtilisateurCerbere objet = ite.next();
				final IUtilisateurCerbere objetPersistant = this.retrieve(objet);
				
				if (objetPersistant == null) {
					continue;
				}
								
				/* récupération de l'ENTITY a détruire. */
				final UtilisateurCerbereEntityJPA entity 
					= this.entityManager.find(
							UtilisateurCerbereEntityJPA.class
								, objetPersistant.getId());
				
				/* ************ */
				/* DESTRUCTION. */
				this.entityManager.remove(entity);
				
			}
			
		}
		catch (Exception e) {
					
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(
					CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
					, "Méthode deleteIterable(Iterable)", e);
			
		}
				
	} // Fin de deleteIterable(...)._______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteIterableBoolean(
			final Iterable<IUtilisateurCerbere> pList) throws Exception {
		
		/* retourne false si pList == null. */
		if (pList == null) {
			return false;
		}

		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}

		boolean resultat = false;
		
		final Iterator<IUtilisateurCerbere> ite = pList.iterator();
		
		try {
			
			while (ite.hasNext()) {
				
				final IUtilisateurCerbere objet = ite.next();
				final IUtilisateurCerbere objetPersistant = this.retrieve(objet);
				
				if (objetPersistant == null) {
					continue;
				}
				
				/* récupération de l'ENTITY a détruire. */
				final UtilisateurCerbereEntityJPA entity 
					= this.entityManager.find(
							UtilisateurCerbereEntityJPA.class
								, objetPersistant.getId());
				
				/* ************ */
				/* DESTRUCTION. */
				this.entityManager.remove(entity);
				
			}
			
			resultat = true;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException.gererException(
					CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
					, "Méthode deleteIterableBoolean(Iterable)", e);
			
		}
		
		/* retourne le boolean resultat. */
		return resultat;
				
	} // Fin de deleteIterableBoolean(...).________________________________



	/* TOOLS *************/


	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists(
			final IUtilisateurCerbere pObject) throws Exception {
		
		/* retourne false si pObject == null. */
		if (pObject == null) {
			return false;
		}
		
		/* Cas où this.entityManager == null. */
		if (this.entityManager == null) {
						
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(MESSAGE_ENTITYMANAGER_NULL);
			}
			return false;
		}

		boolean resultat = false;		
		IUtilisateurCerbere objetResultat = null;
		
		/* récupération de la requête paramétrée. */
		final Query requete = this.fournirRequeteEgaliteMetier(pObject);
		
		try {
			
			/* Execution de la requete HQL. */
			objetResultat 
			= (IUtilisateurCerbere) requete.getSingleResult();
			
			/* retourne true si l'objet existe en base. */
			if (objetResultat != null) {
				resultat = true;
			}
			
		}
		catch (NoResultException noResultExc) {
			
			/* retourne false si l'Objet métier n'existe pas en base. */
			return false;
			
		}
		catch (Exception e) {
			
			/* LOG. */
			if (LOG.isFatalEnabled()) {
				LOG.fatal(e.getMessage(), e);
			}
			
			/* Gestion de la DAO Exception. */
			this.gestionnaireException
				.gererException(CLASSE_UTILISATEURCERBEREDAO_JPA_SPRING
						, "Méthode exists(objet)", e);
		}
		
		/* retourne le boolean resultat. */
		return resultat;
		
	} // Fin de exists(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean existsId(
			final Long pId) throws Exception {
		
		/* retourne false si pId == null . */
		if (pId == null) {
			return false;
		}
		
		/* retourne true si l'objet métier existe en base. */
		if (this.findById(pId) != null) {
			return true;
		}
		
		return false;
		
	} // Fin de existsId(...)._____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long count() throws Exception {
		
		/* Récupère la liste d'Objets métier de Type paramétré T. */
		final List<IUtilisateurCerbere> listObjects = this.findAll();
		
		if (listObjects != null) {
			
			/* Retourne la taille de la liste. */
			return Long.valueOf(listObjects.size()) ;
		}
		
		return null;
		
	} // Fin de count().___________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ecrireStockageDansConsole() throws Exception {
		
		final List<IUtilisateurCerbere> stockageList = this.findAll();
		
		/* ne fait rien si this.findAll() retourne null. */
		if (stockageList == null) {
			return;
		}
		
		System.out.println(this.afficherListeObjetsMetier(stockageList));
		
	} // Fin de ecrireStockageDansConsole()._______________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String afficherListeObjetsMetier(
			final List<IUtilisateurCerbere> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		for (final IUtilisateurCerbere utilisateurCerbere : pList) {
			
			stb.append(utilisateurCerbere.toString());
			stb.append(SAUT_LIGNE_PLATEFORME);
			
		}
		
		return stb.toString();
		
	} // Fin de afficherListeObjetsMetier(...).____________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getMessagesErrorUtilisateurList() {
		return this.messagesErrorUtilisateurList;
	} // Fin de getMessagesErrorUtilisateurList()._________________________


	
} // FIN DE LA CLASSE UtilisateurCerbereDAOJPASpring.------------------------
