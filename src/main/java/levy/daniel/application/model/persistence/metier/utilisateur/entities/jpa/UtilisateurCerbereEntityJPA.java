package levy.daniel.application.model.persistence.metier.utilisateur.entities.jpa;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;

/**
 * CLASSE UtilisateurCerbereEntityJPA :<br/>
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
 * @since 21 févr. 2019
 *
 */
@Entity(name="UtilisateurCerbereEntityJPA")
@Table(name="UTILISATEURS", schema="PUBLIC"
, uniqueConstraints=@UniqueConstraint(name="UNICITE_EMAIL"
, columnNames={"EMAIL"})
, indexes={@Index(name="INDEX_NOM_PRENOM", columnList="NOM, PRENOM")})
public class UtilisateurCerbereEntityJPA implements IUtilisateurCerbere {

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
	 * id en base.<br/>
	 */
	private Long id;
	
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
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(UtilisateurCerbereEntityJPA.class);

	// *************************METHODES************************************/	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbereEntityJPA() {
		
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
	public UtilisateurCerbereEntityJPA(
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
	 * @param pId : Long : 
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
	public UtilisateurCerbereEntityJPA(
			final Long pId
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
		
		if (!(pObjet instanceof IUtilisateurCerbere)) {
			return false;
		}
		
		final IUtilisateurCerbere other = (IUtilisateurCerbere) pObjet;
		
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
			final IUtilisateurCerbere pObjet) {
		
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
	public final UtilisateurCerbereEntityJPA clone() 
				throws CloneNotSupportedException {
		
		final IUtilisateurCerbere clone 
			= (IUtilisateurCerbere) super.clone();
				
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
		
		return (UtilisateurCerbereEntityJPA) clone;
		
	} // Fin de clone().___________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("UtilisateurCerbereEntityJPA [");
		
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
			if (this.getId() != null) {
				valeur = String.valueOf(this.getId());
			}
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
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	@Override
	public Long getId() {	
		return this.id;
	} // Fin de getId().___________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(
			final Long pId) {	
		this.id = pId;
	} // Fin de setId(...).________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="CIVILITE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getCivilite() {
		return this.civilite;
	} // Fin de getCivilite()._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setCivilite(
			final String pCivilite) {
		this.civilite = pCivilite;
	} // Fin de setCivilite(...).__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PRENOM"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public String getPrenom() {
		return this.prenom;
	} // Fin de getPrenom()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPrenom(
			final String pPrenom) {
		this.prenom = pPrenom;
	} // Fin de setPrenom(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="NOM"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public String getNom() {
		return this.nom;
	} // Fin de getNom().__________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setNom(
			final String pNom) {
		this.nom = pNom;
	} // Fin de setNom(...)._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="TEL"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getTel() {
		return this.tel;
	} // Fin de getTel().__________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setTel(
			final String pTel) {
		this.tel = pTel;
	} // Fin de setTel(...)._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="EMAIL"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public String getEmail() {
		return this.email;
	} // Fin de getEmail().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEmail(
			final String pEmail) {
		this.email = pEmail;
	} // Fin de setEmail(...)._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="SERVICE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getService() {
		return this.service;
	} // Fin de getService().______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setService(
			final String pService) {
		this.service = pService;
	} // Fin de setService(...).___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="UNITE"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public String getUnite() {
		return this.unite;
	} // Fin de getUnite().________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setUnite(
			final String pUnite) {
		this.unite = pUnite;
	} // Fin de setUnite(...)._____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PROFIL"
			, unique = false, updatable = true
			, insertable = true, nullable = false)
	@Override
	public String getProfil() {
		return this.profil;
	} // Fin de getProfil()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setProfil(
			final String pProfil) {
		this.profil = pProfil;
	} // Fin de setProfil(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="PORTEE"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getPortee() {
		return this.portee;
	} // Fin de getPortee()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPortee(
			final String pPortee) {
		this.portee = pPortee;
	} // Fin de setPortee(...).____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Column(name="RESTRICTION"
			, unique = false, updatable = true
			, insertable = true, nullable = true)
	@Override
	public String getRestriction() {
		return this.restriction;
	} // Fin de getRestriction().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setRestriction(
			final String pRestriction) {
		this.restriction = pRestriction;
	} // Fin de setRestriction(...)._______________________________________
	
			

} // FIN DE LA CLASSE UtilisateurCerbereEntityJPA.---------------------------
