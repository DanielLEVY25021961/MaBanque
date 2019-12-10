package levy.daniel.application.model.dto.metier.utilisateur.impl;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.dto.metier.utilisateur.IUtilisateurCerbereDTO;


/**
 * CLASSE UtilisateurCerbereDTO :<br/>
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
 * @since 18 févr. 2019
 *
 */
public class UtilisateurCerbereDTO implements IUtilisateurCerbereDTO {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID.<br/>
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';
	
	/**
	 * ", ".<br/>
	 */
	public static final String VIRGULE_ESPACE = ", ";
	
	/**
	 * "null".<br/>
	 */
	public static final String NULL = "null";
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * id en base sous forme de String.<br/>
	 */
	private String id;
	
	/**
	 * civilité de l'utilisateur (M., Mme, Mlle, ...).<br/>
	 */
	private String civilite;
	
	/**
	 * prénom de l'utilisateur.<br/>
	 */
	private String prenom;
	
	/**
	 * nom de l'utilisateur.<br/>
	 */
	private String nom;
	
	/**
	 * numéro de téléphone de l'utilisateur.<br/>
	 */
	private String tel;
	
	/**
	 * adresse mel de l'utilisateur.<br/>
	 */
	private String email;
	
	/**
	 * service de l'utilisateur.<br/>
	 * <ul>
	 * Par exemple :
	 * <li><b>SG</b> pour SG/DAEI/CCDD1</li>
	 * <li><b>CGDD</b> pour CGDD/SDES/SDST/BSRV</li>
	 * <li><b>DIRA</b> pour DIRA/SIEER/CIGT/PC Bordeaux</li>
	 * <li><b>CEREMA</b> pour CEREMA/DTerMed/DCEDI/GTIE</li>
	 * <li><b>DGITM</b> pour DGITM/DIT/GRN/GCABron/GCA2</li>
	 * </ul>
	 */
	private String service;
	
	/**
	 * unité (dans le service) de l'utilisateur.<br/>
	 * <ul>
	 * Par exemple :
	 * <li>SG/DAEI/CCDD1</li>
	 * <li>CGDD/SDES/SDST/BSRV</li>
	 * <li>DIRA/SIEER/CIGT/PC Bordeaux</li>
	 * <li>CEREMA/DTerMed/DCEDI/GTIE</li>
	 * <li>DGITM/DIT/GRN/GCABron/GCA2</li>
	 * </ul>
	 */
	private String unite;
	
	/**
	 * profil de l'utilisateur dans la session courante.<br/>
	 * <ul>
	 * Par exemple :
	 * <li>ADMINISTRATEUR</li>
	 * <li>GESTIONNAIRE</li>
	 * </ul>
	 */
	private String profil;
	
	/**
	 * portée du profil de l'utilisateur dans la session courante.<br/>
	 * <ul>
	 * Par exemple :
	 * <li>DIRA</li>
	 * <li>FRANCE ENTIERE</li>
	 * </ul>
	 */
	private String portee;
	
	/**
	 * restriction (facultative) sur la portée 
	 * du profil de l'utilisateur dans la session courante.<br/>
	 */
	private String restriction;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings(UNUSED)
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereDTO.class);

	// *************************METHODES************************************/
	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereDTO() {
		
		this(null
				, null
				, null, null
				, null, null
				, null, null
				, null, null, null);
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * CONSTRUCTEUR COMPLET.<br/>
	 *
	 * @param pCivilite : String : 
	 * civilité de l'utilisateur (M., Mme, Mlle, ...).
	 * @param pPrenom : String : 
	 * prénom de l'utilisateur.<br/>
	 * @param pNom : String : 
	 * nom de l'utilisateur.<br/>
	 * @param pTel : String : 
	 * numéro de téléphone de l'utilisateur.<br/>
	 * @param pEmail : String : 
	 * adresse mel de l'utilisateur.<br/>
	 * @param pService : String : 
	 * service de l'utilisateur.<br/>
	 * @param pUnite : String : 
	 * unité (dans le service) de l'utilisateur.<br/>
	 * @param pProfil : String : 
	 * profil de l'utilisateur dans la session courante.<br/>
	 * @param pPortee : String : 
	 * portée du profil de l'utilisateur dans la session courante.<br/>
	 * @param pRestriction : String : 
	 * restriction (facultative) sur la portée
	 */
	public UtilisateurCerbereDTO(
			final String pCivilite
			, final String pPrenom
			, final String pNom
			, final String pTel
			, final String pEmail
			, final String pService
			, final String pUnite
			, final String pProfil
			, final String pPortee
			, final String pRestriction) {
		
		this(null
				, pCivilite
				, pPrenom, pNom
				, pTel, pEmail
				, pService, pUnite
				, pProfil, pPortee, pRestriction);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET BASE.<br/>
	 *
	 * @param pId : String : 
	 * ID en base.
	 * @param pCivilite : String : 
	 * civilité de l'utilisateur (M., Mme, Mlle, ...).
	 * @param pPrenom : String : 
	 * prénom de l'utilisateur.<br/>
	 * @param pNom : String : 
	 * nom de l'utilisateur.<br/>
	 * @param pTel : String : 
	 * numéro de téléphone de l'utilisateur.<br/>
	 * @param pEmail : String : 
	 * adresse mel de l'utilisateur.<br/>
	 * @param pService : String : 
	 * service de l'utilisateur.<br/>
	 * @param pUnite : String : 
	 * unité (dans le service) de l'utilisateur.<br/>
	 * @param pProfil : String : 
	 * profil de l'utilisateur dans la session courante.<br/>
	 * @param pPortee : String : 
	 * portée du profil de l'utilisateur dans la session courante.<br/>
	 * @param pRestriction : String : 
	 * restriction (facultative) sur la portée
	 */
	public UtilisateurCerbereDTO(
			final String pId
			, final String pCivilite
			, final String pPrenom
			, final String pNom
			, final String pTel
			, final String pEmail
			, final String pService
			, final String pUnite
			, final String pProfil
			, final String pPortee
			, final String pRestriction) {
		
		super();
		
		this.id = pId;
		this.civilite = pCivilite;
		this.prenom = pPrenom;
		this.nom = pNom;
		this.tel = pTel;
		this.email = pEmail;
		this.service = pService;
		this.unite = pUnite;
		this.profil = pProfil;
		this.portee = pPortee;
		this.restriction = pRestriction;
		
	} // Fin de CONSTRUCTEUR COMPLET BASE._________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		return Objects.hash(this.getNom(), this.getPrenom()
				, this.getEmail(), this.getUnite());
	} // Fin de hashCode().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(
			final Object pObjet) {
		
		if (this == pObjet) {
			return true;
		}
		
		if (pObjet == null) {
			return false;
		}
		
		if (!(pObjet instanceof IUtilisateurCerbereDTO)) {
			return false;
		}
		
		final IUtilisateurCerbereDTO other 
			= (IUtilisateurCerbereDTO) pObjet;
		
		return Objects.equals(this.getNom(), other.getNom()) 
				&& Objects.equals(this.getPrenom(), other.getPrenom())
				&& Objects.equals(this.getEmail(), other.getEmail()) 
				&& Objects.equals(this.getUnite(), other.getUnite());
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final IUtilisateurCerbereDTO pObjet) {
		
		if (this == pObjet) {
			return 0;
		}

		if (pObjet == null) {
			return -1;
		}

		int compareNom = 0;
		int comparePrenom = 0;
		int compareEmail = 0;
		int compareUnite = 0;
		
		/* nom. */
		if (this.getNom() == null) {
			if (pObjet.getNom() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getNom() == null) {
				return -1;
			}
			
			compareNom 
			= this.getNom().compareToIgnoreCase(pObjet.getNom());
		
			if (compareNom != 0) {
				return compareNom;
			}
		}
				
		/* prenom. */
		if (this.getPrenom() == null) {
			if (pObjet.getPrenom() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getPrenom() == null) {
				return -1;
			}
			
			comparePrenom 
			= this.getPrenom().compareToIgnoreCase(pObjet.getPrenom());
		
			if (comparePrenom != 0) {
				return comparePrenom;
			}
		}
		
		/* email. */
		if (this.getEmail() == null) {
			if (pObjet.getEmail() != null) {
				return +1;
			}
		} else {
			
			if (pObjet.getEmail() == null) {
				return -1;
			}
			
			compareEmail 
			= this.getEmail().compareToIgnoreCase(pObjet.getEmail());
		
			if (compareEmail != 0) {
				return compareEmail;
			}
		}
		
		/* unite. */
		if (this.getUnite() == null) {
			if (pObjet.getUnite() != null) {
				return +1;
			}
			
			return 0;
		}
		
		if (pObjet.getUnite() == null) {
			return -1;
		}
		
		compareUnite 
			= this.getUnite().compareToIgnoreCase(pObjet.getUnite());
		
		return compareUnite;

	} // Fin de compareTo(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final UtilisateurCerbereDTO clone() 
				throws CloneNotSupportedException {
		
		final IUtilisateurCerbereDTO clone 
			= (IUtilisateurCerbereDTO) super.clone();
				
		clone.setId(this.getId());
		clone.setCivilite(this.getCivilite());
		clone.setPrenom(this.getPrenom());
		clone.setNom(this.getNom());
		clone.setTel(this.getTel());
		clone.setEmail(this.getEmail());
		clone.setService(this.getService());
		clone.setUnite(this.getUnite());
		clone.setProfil(this.getProfil());
		clone.setPortee(this.getPortee());
		clone.setRestriction(this.getRestriction());
		
		return (UtilisateurCerbereDTO) clone;
		
	} // Fin de clone().___________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("UtilisateurCerbereDTO [");
		
		builder.append("id=");
		if (this.getId() != null) {			
			builder.append(this.getId());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("civilite=");
		if (this.getCivilite() != null) {			
			builder.append(this.getCivilite());			
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("prenom=");
		if (this.getPrenom() != null) {			
			builder.append(this.getPrenom());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("nom=");
		if (this.getNom() != null) {			
			builder.append(this.getNom());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("tel=");
		if (this.getTel() != null) {			
			builder.append(this.getTel());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("email=");
		if (this.getEmail() != null) {			
			builder.append(this.getEmail());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("service=");
		if (this.getService() != null) {			
			builder.append(this.getService());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("unite=");
		if (this.getUnite() != null) {			
			builder.append(this.getUnite());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("profil=");
		if (this.getProfil() != null) {			
			builder.append(this.getProfil());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("portee=");
		if (this.getPortee() != null) {			
			builder.append(this.getPortee());
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("restriction=");
		if (this.getRestriction() != null) {			
			builder.append(this.getRestriction());
		} else {
			builder.append(NULL);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteCsv() {
		return "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;";
	} // Fin de fournirEnTeteCsv().________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringCsv() {
		
		final StringBuilder stb = new StringBuilder();

		stb.append(this.getId());
		stb.append(POINT_VIRGULE);
		stb.append(this.getCivilite());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPrenom());
		stb.append(POINT_VIRGULE);
		stb.append(this.getNom());
		stb.append(POINT_VIRGULE);
		stb.append(this.getTel());
		stb.append(POINT_VIRGULE);
		stb.append(this.getEmail());
		stb.append(POINT_VIRGULE);
		stb.append(this.getService());
		stb.append(POINT_VIRGULE);
		stb.append(this.getUnite());
		stb.append(POINT_VIRGULE);
		stb.append(this.getProfil());
		stb.append(POINT_VIRGULE);
		stb.append(this.getPortee());
		stb.append(POINT_VIRGULE);
		stb.append(this.getRestriction());
		stb.append(POINT_VIRGULE);
		
		return stb.toString();
		
	} // Fin de fournirStringCsv().________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteColonne(
			final int pI) {
		
		String entete = null;

		switch (pI) {

		case 0:
			entete = "id";
			break;

		case 1:
			entete = "civilité";
			break;

		case 2:
			entete = "prénom";
			break;

		case 3:
			entete = "nom";
			break;
			
		case 4:
			entete = "tel";
			break;
			
		case 5:
			entete = "email";
			break;
			
		case 6:
			entete = "service";
			break;
			
		case 7:
			entete = "unite";
			break;
			
		case 8:
			entete = "profil";
			break;
			
		case 9:
			entete = "portée";
			break;
			
		case 10:
			entete = "restriction";
			break;
			
		default:
			entete = "invalide";
			break;

		} // Fin du Switch._________________________________

		return entete;

	} // Fin de fournirEnTeteColonne(...)._________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Object fournirValeurColonne(
			final int pI) {
		
		Object valeur = null;

		switch (pI) {

		case 0:
			valeur = this.getId();
			break;

		case 1:
			valeur = this.getCivilite();
			break;

		case 2:
			valeur = this.getPrenom();
			break;

		case 3:
			valeur = this.getNom();
			break;

		case 4:
			valeur = this.getTel();
			break;
			
		case 5:
			valeur = this.getEmail();
			break;
			
		case 6:
			valeur = this.getService();
			break;
			
		case 7:
			valeur = this.getUnite();
			break;
			
		case 8:
			valeur = this.getProfil();
			break;
			
		case 9:
			valeur = this.getPortee();
			break;
			
		case 10:
			valeur = this.getRestriction();		
			break;

		default:
			valeur = "invalide";
			break;

		} // Fin du Switch._________________________________

		return valeur;

	} // Fin de fournirValeurColonne(...)._________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getId() {	
		return this.id;
	} // Fin de getId().___________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setId(
			final String pId) {	
		this.id = pId;
	} // Fin de setId(...).________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getCivilite() {
		return this.civilite;
	} // Fin de getCivilite()._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setCivilite(
			final String pCivilite) {
		this.civilite = pCivilite;
	} // Fin de setCivilite(...).__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPrenom() {
		return this.prenom;
	} // Fin de getPrenom()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPrenom(
			final String pPrenom) {
		this.prenom = pPrenom;
	} // Fin de setPrenom(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNom() {
		return this.nom;
	} // Fin de getNom().__________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setNom(
			final String pNom) {
		this.nom = pNom;
	} // Fin de setNom(...)._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getTel() {
		return this.tel;
	} // Fin de getTel().__________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setTel(
			final String pTel) {
		this.tel = pTel;
	} // Fin de setTel(...)._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getEmail() {
		return this.email;
	} // Fin de getEmail().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setEmail(
			final String pEmail) {
		this.email = pEmail;
	} // Fin de setEmail(...)._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getService() {
		return this.service;
	} // Fin de getService().______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setService(
			final String pService) {
		this.service = pService;
	} // Fin de setService(...).___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getUnite() {
		return this.unite;
	} // Fin de getUnite().________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setUnite(
			final String pUnite) {
		this.unite = pUnite;
	} // Fin de setUnite(...)._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getProfil() {
		return this.profil;
	} // Fin de getProfil()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setProfil(
			final String pProfil) {
		this.profil = pProfil;
	} // Fin de setProfil(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getPortee() {
		return this.portee;
	} // Fin de getPortee()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setPortee(
			final String pPortee) {
		this.portee = pPortee;
	} // Fin de setPortee(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getRestriction() {
		return this.restriction;
	} // Fin de getRestriction().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setRestriction(
			final String pRestriction) {
		this.restriction = pRestriction;
	} // Fin de setRestriction(...)._______________________________________
	
			
	
} // FIN DE LA CLASSE UtilisateurCerbereDTO.----------------------------------
