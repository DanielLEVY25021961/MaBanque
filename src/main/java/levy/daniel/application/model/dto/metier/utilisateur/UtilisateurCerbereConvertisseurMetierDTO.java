package levy.daniel.application.model.dto.metier.utilisateur;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.utilisateur.impl.UtilisateurCerbereDTO;
import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;
import levy.daniel.application.model.metier.utilisateur.impl.UtilisateurCerbere;

/**
 * CLASSE UtilisateurCerbereConvertisseurMetierDTO :<br/>
 * classe <b>utilitaire</b> chargée de <b>convertir 
 * un DTO en OBJET METIER</b> et de <b>convertir un
 * OBJET METIER en DTO</b>.<br/>
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
 * @since 19 févr. 2019
 *
 */
public final class UtilisateurCerbereConvertisseurMetierDTO {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereConvertisseurMetierDTO.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private UtilisateurCerbereConvertisseurMetierDTO() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * <b>convertit un DTO en OBJET METIER</b>.<br/>
	 * <ul>
	 * <li>retourne null si pDTO == null.</li>
	 * <li>récupère les valeurs String dans le DTO.</li>
	 * <li>convertit les String du DTO en types de l'Objet métier.</li>
	 * <li>injecte les valeurs typées dans un OBJET METIER 
	 * et le retourne.</li>
	 * </ul>
	 *
	 * @param pDTO : IUtilisateurCerbereDTO.<br/>
	 * 
	 * @return : IUtilisateurCerbere : Objet métier.<br/>
	 */
	public static IUtilisateurCerbere convertirDTOEnObjetMetier(
			final IUtilisateurCerbereDTO pDTO) {
		
		synchronized (UtilisateurCerbereConvertisseurMetierDTO.class) {
			
			IUtilisateurCerbere resultat = null;
			
			if (pDTO != null) {
				
				/* récupère les valeurs String dans le DTO. */
				final String idString = pDTO.getId();
				final String civiliteString = pDTO.getCivilite();
				final String prenomString = pDTO.getPrenom();
				final String nomString = pDTO.getNom();
				final String telString = pDTO.getTel();
				final String emailString = pDTO.getEmail();
				final String serviceString = pDTO.getService();
				final String uniteString = pDTO.getUnite();
				final String profilString = pDTO.getProfil();
				final String porteeString = pDTO.getPortee();
				final String restrictionString = pDTO.getRestriction();
				
				
				/* convertit les String du DTO en types de l'Objet métier. */
				Long id = null;
				
				if (!StringUtils.isBlank(idString)) {
					try {
						id = Long.valueOf(idString);
					} catch (NumberFormatException e) {
						id = null;
					}
				}
				
				final String civilite = civiliteString;
				final String prenom = prenomString;
				final String nom = nomString;
				final String tel = telString;
				final String email = emailString;
				final String service = serviceString;
				final String unite = uniteString;
				final String profil = profilString;
				final String portee = porteeString;
				final String restriction = restrictionString;
				
				/* injecte les valeurs typées dans un OBJET METIER. */
				resultat 
					= new UtilisateurCerbere(
							id
							, civilite
							, prenom, nom
							, tel, email
							, service, unite
							, profil, portee, restriction);
			}
			
			return resultat;
			
		} // Fin de synchronized._______________________
		
	} // Fin de convertirDTOEnObjetMetier(...).____________________________
	

	
	/**
	 * <b>convertit un OBJET METIER en DTO</b>.<br/>
	 * <ul>
	 * <li>retourne null si pObject == null.</li>
	 * <li>récupère les valeurs typées dans l'objet métier.</li>
	 * <li>convertit les types de l'Objet métier en String du DTO.</li>
	 * <li>injecte les valeurs String dans un DTO 
	 * et le retourne.</li>
	 * </ul>
	 *
	 * @param pObject : IUtilisateurCerbere : 
	 * Objet métier.<br/>
	 * 
	 * @return : IUtilisateurCerbereDTO : DTO.<br/>
	 */
	public static IUtilisateurCerbereDTO convertirObjetMetierEnDTO(
			final IUtilisateurCerbere pObject) {
		
		synchronized (UtilisateurCerbereConvertisseurMetierDTO.class) {
			
			IUtilisateurCerbereDTO resultat = null;
			
			if (pObject != null) {
				
				/* récupère les valeurs typées dans l'objet métier. */
				final Long id = pObject.getId();
				final String civilite = pObject.getCivilite();
				final String prenom = pObject.getPrenom();
				final String nom = pObject.getNom();				
				final String tel = pObject.getTel();
				final String email = pObject.getEmail();
				final String service = pObject.getService();
				final String unite = pObject.getUnite();
				final String profil = pObject.getProfil();
				final String portee = pObject.getPortee();
				final String restriction = pObject.getRestriction();
				
				/* convertit les types de l'Objet métier en String du DTO. */
				final String idString = String.valueOf(id);
				final String civiliteString = civilite;
				final String prenomString = prenom;
				final String nomString = nom;				
				final String telString = tel;
				final String emailString = email;
				final String serviceString = service;
				final String uniteString = unite;
				final String profilString = profil;
				final String porteeString = portee;
				final String restrictionString = restriction;
				
				/* injecte les valeurs String dans un DTO. */
				resultat 
					= new UtilisateurCerbereDTO(
							idString
							, civiliteString
							, prenomString, nomString
							, telString, emailString
							, serviceString, uniteString
							, profilString, porteeString, restrictionString);
				
			}
						
			return resultat;
			
		} // Fin de synchronized._______________________
		
	} // Fin de convertirObjetMetierEnDTO(...).____________________________
	
	
	
	/**
	 * convertit une liste d'OBJETS METIER en liste 
	 * de DTOs.<br/>
	 * <br/>
	 * - retourne null si pListeObjets == null.<br/>
	 * <br/>
	 *
	 * @param pListeObjets : List&lt;IUtilisateurCerbere&gt; : 
	 * Liste d'OBJETS METIER.<br/>
	 * @return : 
	 * List&lt;IUtilisateurCerbereDTO&gt; : 
	 * Liste de DTOs.<br/>
	 */
	public static List<IUtilisateurCerbereDTO> convertirListeObjetEnListeDTO(
			final List<IUtilisateurCerbere> pListeObjets) {
		
		synchronized (UtilisateurCerbereConvertisseurMetierDTO.class) {
			
			/* retourne null si pListeObjets == null. */
			if (pListeObjets == null) {
				return null;
			}
			
			final List<IUtilisateurCerbereDTO> resultat 
				= new ArrayList<IUtilisateurCerbereDTO>();
			
			for (final IUtilisateurCerbere objet : pListeObjets) {
				
				final IUtilisateurCerbereDTO dto 
					= convertirObjetMetierEnDTO(objet);
				
				resultat.add(dto);
				
			}
			
			return resultat;
			
		} // Fin de synchronized._______________________________________
		
	} // Fin de convertirListeObjetEnListeDTO(...).________________________
	
	
	
} // FIN DE LA CLASSE UtilisateurCerbereConvertisseurMetierDTO.--------------
