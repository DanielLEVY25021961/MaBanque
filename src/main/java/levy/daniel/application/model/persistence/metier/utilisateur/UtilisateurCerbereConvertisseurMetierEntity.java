package levy.daniel.application.model.persistence.metier.utilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.metier.utilisateur.impl.UtilisateurCerbere;
import levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa.UtilisateurCerbereEntityJPA;

/**
 * CLASSE UtilisateurCerbereConvertisseurMetierEntity :<br/>
 * classe <b>utilitaire</b> chargée de <b>convertir 
 * une ENTITY en OBJET METIER</b> et de <b>convertir un
 * OBJET METIER en ENTITY</b>.<br/>
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
 * @since 21 févr. 2019
 *
 */
public final class UtilisateurCerbereConvertisseurMetierEntity {

	// ************************ATTRIBUTS************************************/

	/**
	 * FORMAT pour affichage formaté à la console 
	 * des IUtilisateurCerbere.<br/>
	 * "id=%1$-5d civilité = %2$-5s  
	 * prénom = %3$-15s nom = %4$-20s 
	 * tel = %5$-18s eMail = %6$-25s  Service = %7$-15s  
	 * Unité = %8$-35s  Profil = %9$-25s  Portée = %10$-15s  
	 * Restriction = %11$-35s".
	 */
	public static final String FORMAT_UTILISATEURCERBERE 
		= "id=%1$-5d civilité = %2$-5s  prénom = %3$-15s "
				+ "nom = %4$-20s tel = %5$-18s eMail = %6$-25s  "
				+ "Service = %7$-15s  Unité = %8$-35s  Profil = %9$-25s  "
				+ "Portée = %10$-15s  Restriction = %11$-35s";

	/**
	 * "line.separator".<br/>
	 */
	public static final String LINE_SEPARATOR = "line.separator";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
	= LogFactory.getLog(UtilisateurCerbereConvertisseurMetierEntity.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private UtilisateurCerbereConvertisseurMetierEntity() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * <b>Crée un OBJET METIER à partir d'une EntityJPA</b>.<br/>
	 * <ul>
	 * <li>retourne un OBJET METIER avec toutes les valeurs 
	 * à null si pEntityJPA == null.</li>
	 * </ul>
	 *
	 * @param pEntityJPA : UtilisateurCerbereEntityJPA.<br/>
	 * 
	 * @return : IUtilisateurCerbere.<br/>
	 */
	public static IUtilisateurCerbere creerObjetMetierAPartirEntityJPA(
			final UtilisateurCerbereEntityJPA pEntityJPA) {

		synchronized (UtilisateurCerbereConvertisseurMetierEntity.class) {
			
			final IUtilisateurCerbere objet 
				= new UtilisateurCerbere();
			
			if (pEntityJPA != null) {
				
				objet.setId(pEntityJPA.getId());
				objet.setCivilite(pEntityJPA.getCivilite());
				objet.setPrenom(pEntityJPA.getPrenom());
				objet.setNom(pEntityJPA.getNom());
				objet.setTel(pEntityJPA.getTel());
				objet.setEmail(pEntityJPA.getEmail());
				objet.setService(pEntityJPA.getService());
				objet.setUnite(pEntityJPA.getUnite());
				objet.setProfil(pEntityJPA.getProfil());
				objet.setPortee(pEntityJPA.getPortee());
				objet.setRestriction(pEntityJPA.getRestriction());
				
			}
							
			return objet;
		
		} // Fin de synchronized._______________________
		
	} // Fin de creerObjetMetierAPartirEntityJPA(...)._____________________
	
	
	
	
	/**
	 * <b>convertit une ENTITY JPA en OBJET METIER</b>.<br/>
	 * <ul>
	 * <li>retourne null si pEntity == null.</li>
	 * <li>récupère les valeurs dans le pEntity.</li>
	 * <li>injecte les valeurs de l'ENTITY dans un OBJET METIER.</li>
	 * </ul>
	 *
	 * @param pEntity : UtilisateurCerbereEntityJPA.<br/>
	 * 
	 * @return : IUtilisateurCerbere : Objet métier.<br/>
	 */
	public static IUtilisateurCerbere convertirEntityJPAEnObjetMetier(
			final UtilisateurCerbereEntityJPA pEntity) {
		
		synchronized (UtilisateurCerbereConvertisseurMetierEntity.class) {
			
			IUtilisateurCerbere objet = null;
			
			if (pEntity != null) {
				
				/* récupère les valeurs dans l'Entity. */
				/* injecte les valeurs typées dans un OBJET METIER. */
				objet 
					= new UtilisateurCerbere(
							pEntity.getId()
							, pEntity.getCivilite()
							, pEntity.getPrenom(), pEntity.getNom()
							, pEntity.getTel(), pEntity.getEmail()
							, pEntity.getService(), pEntity.getUnite()
							, pEntity.getProfil()
							, pEntity.getPortee()
							, pEntity.getRestriction());
			}
			
			return objet;
			
		} // Fin de synchronized._______________________
		
	} // Fin de convertirEntityJPAEnObjetMetier(...).______________________
	

	
	/**
	 * convertit une Liste d'ENTITIES JPA 
	 * en liste d'OBJETS METIER et la retourne.<br/>
	 * <br/>
	 * - retourne null si pList == null.<br/>
	 * - n'insère dans la liste résultat que les Entities non null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;UtilisateurCerbereEntityJPA&gt;.<br/>
	 * 
	 * @return : List&lt;IUtilisateurCerbere&gt;.<br/>
	 */
	public static List<IUtilisateurCerbere> convertirListEntitiesJPAEnModel(
			final List<UtilisateurCerbereEntityJPA> pList) {
		
		synchronized (UtilisateurCerbereConvertisseurMetierEntity.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final List<IUtilisateurCerbere> resultat 
				= new ArrayList<IUtilisateurCerbere>();
			
			for (final UtilisateurCerbereEntityJPA entity : pList) {
				
				/* n'insère dans la liste résultat 
				 * que les Entities non null. */
				if (entity != null) {
					
					final IUtilisateurCerbere objet 													
						= convertirEntityJPAEnObjetMetier(entity);
					
					resultat.add(objet);
					
				}
			}
			
			return resultat;
			
		} // Fin de synchronized.______________________
		
	} // Fin de convertirListEntitiesJPAEnModel(...).______________________
	
	
		
	/**
	 * <b>crée une ENTITY JPA à partir d'un OBJET METIER</b>.<br/>
	 * <ul>
	 * <li>retourne une Entity JPA avec toutes les valeurs 
	 * à null si pObject == null.</li>
	 * </ul>
	 *
	 * @param pObject : IUtilisateurCerbere.<br/>
	 *  
	 * @return : UtilisateurCerbereEntityJPA.<br/>
	 */
	public static UtilisateurCerbereEntityJPA creerEntityJPA(
			final IUtilisateurCerbere pObject) {
		
		synchronized (UtilisateurCerbereConvertisseurMetierEntity.class) {
			
			final UtilisateurCerbereEntityJPA entity 
				= new UtilisateurCerbereEntityJPA();
			
			if (pObject != null) {
				
				entity.setId(pObject.getId());
				entity.setCivilite(pObject.getCivilite());
				entity.setPrenom(pObject.getPrenom());
				entity.setNom(pObject.getNom());
				entity.setTel(pObject.getTel());
				entity.setEmail(pObject.getEmail());
				entity.setService(pObject.getService());
				entity.setUnite(pObject.getUnite());
				entity.setProfil(pObject.getProfil());
				entity.setPortee(pObject.getPortee());
				entity.setRestriction(pObject.getRestriction());
				
			}
			
			return entity;
					
		} // Fin de synchronized.______________________
		
	} // Fin de creerEntityJPA(...)._______________________________________

	
	
	/**
	 * <b>convertit un OBJET METIER en ENTITY JPA</b>.<br/>
	 * <ul>
	 * <li>retourne null si pObject == null.</li>
	 * <li>récupère les valeurs typées dans l'objet métier.</li>
	 * <li>injecte les valeurs de l'objet métier dans une ENTITY.</li>
	 * </ul>
	 *
	 * @param pObject : IUtilisateurCerbere : Objet métier.<br/>
	 * 
	 * @return : UtilisateurCerbereEntityJPA : ENTITY JPA.<br/>
	 */
	public static UtilisateurCerbereEntityJPA convertirObjetMetierEnEntityJPA(
			final IUtilisateurCerbere pObject) {
		
		synchronized (UtilisateurCerbereConvertisseurMetierEntity.class) {
			
			UtilisateurCerbereEntityJPA resultat = null;
			
			if (pObject != null) {
								
				/* injecte les valeurs String dans un DTO. */
				resultat 
					= new UtilisateurCerbereEntityJPA(
							pObject.getId()
							, pObject.getCivilite()
							, pObject.getPrenom(), pObject.getNom()
							, pObject.getTel(), pObject.getEmail()
							, pObject.getService(), pObject.getUnite()
							, pObject.getProfil()
							, pObject.getPortee()
							, pObject.getRestriction());
				
			}
						
			return resultat;
			
		} // Fin de synchronized._______________________
		
	} // Fin de convertirObjetMetierEnEntityJPA(...).______________________
	

	
	/**
	 * convertit une Liste d'OBJETS METIER en liste 
	 * d'ENTITIES JPA.<br/>
	 * <br/>
	 * - retourne null si pList == null.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;IUtilisateurCerbere&gt;
	 * 
	 * @return : List&lt;UtilisateurCerbereEntityJPA&gt;.<br/>
	 */
	public static List<UtilisateurCerbereEntityJPA> convertirListModelEnEntitiesJPA(
			final Iterable<IUtilisateurCerbere> pList) {
		
		synchronized (UtilisateurCerbereConvertisseurMetierEntity.class) {
			
			/* retourne null si pList == null. */
			if (pList == null) {
				return null;
			}
			
			final List<UtilisateurCerbereEntityJPA> resultat 
				= new ArrayList<UtilisateurCerbereEntityJPA>();
			
			for (final IUtilisateurCerbere objet : pList) {
				
				if (objet != null) {
					
					final UtilisateurCerbereEntityJPA entity 
						= convertirObjetMetierEnEntityJPA(objet);
					
					resultat.add(entity);
					
				}
			}
			
			return resultat;

		} // Fin de synchronized._______________________
		
	} // Fin de convertirListModelEnEntitiesJPA(...).______________________
	

	
	/**
	 * <b>retourne une String pour affichage formaté 
	 * (FORMAT_UTILISATEURCERBERE) 
	 * d'une liste d'entities</b>.<br/>
	 * <ul>
	 * <li>chaque item de la liste est retournée 
	 * sur une ligne formatée.</li>
	 * <li>utilise le saut de la plateforme comme saut à la ligne 
	 * (<code>System.getProperty("line.separator")</code>).</li>
	 * </ul>
	 * - retourne null si pList == null.<br/>
	 * - n'affiche pas un item null dans la liste 
	 * passée en paramètre.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;UtilisateurCerbereEntityJPA&gt; : 
	 * liste d'Entities.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	public static String afficherFormateListEntities(
			final List<UtilisateurCerbereEntityJPA> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final IUtilisateurCerbere entity : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (entity != null) {
				
				final String stringformatee 
					= String.format(
							Locale.getDefault()
								, FORMAT_UTILISATEURCERBERE
								, entity.getId()
								, entity.getCivilite()
								, entity.getPrenom(), entity.getNom()
								, entity.getTel(), entity.getEmail()
								, entity.getService(), entity.getUnite()
								, entity.getProfil()
								, entity.getPortee()
								, entity.getRestriction());
				
				stb.append(stringformatee);
				
				/* utilise le saut de la plateforme. */
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
		}
		
		return stb.toString();
		
	} //Fin de afficherFormateListEntities(...).___________________________
	

	
	/**
	 * <b>retourne une String pour affichage formaté 
	 * (FORMAT_UTILISATEURCERBERE) 
	 * d'une liste d'objets métier</b>.<br/>
	 * <ul>
	 * <li>chaque item de la liste est retournée 
	 * sur une ligne formatée.</li>
	 * <li>utilise le saut de la plateforme comme saut à la ligne 
	 * (<code>System.getProperty("line.separator")</code>).</li>
	 * </ul>
	 * - retourne null si pList == null.<br/>
	 * - n'affiche pas un item null dans la liste 
	 * passée en paramètre.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;IUtilisateurCerbere&gt; : 
	 * liste d'Entities.<br/>
	 * 
	 * @return : String : affichage.<br/>
	 */
	public static String afficherFormateListObjets(
			final List<IUtilisateurCerbere> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final IUtilisateurCerbere entity : pList) {
			
			/* n'affiche pas une Entity null 
			 * dans la liste passée en paramètre. */
			if (entity != null) {
				
				final String stringformatee 
					= String.format(
							Locale.getDefault()
								, FORMAT_UTILISATEURCERBERE
								, entity.getId()
								, entity.getCivilite()
								, entity.getPrenom(), entity.getNom()
								, entity.getTel(), entity.getEmail()
								, entity.getService(), entity.getUnite()
								, entity.getProfil()
								, entity.getPortee()
								, entity.getRestriction());
				
				stb.append(stringformatee);
				
				/* utilise le saut de la plateforme. */
				stb.append(System.getProperty(LINE_SEPARATOR));
			}
		}
		
		return stb.toString();
		
	} //Fin de afficherFormateListObjets(...)._____________________________
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereConvertisseurMetierEntity.---------
