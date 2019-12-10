package levy.daniel.application.model.metier.utilisateur.impl;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.metier.utilisateur.IUtilisateurCerbere;

/**
 * CLASSE UtilisateurCerbere :<br/>
 * CLASSE CONCRETE <b>UtilisateurCerbere</b> sous MODEL/METIER :<br/>
 * 
 * <p>
 * <b>UtilisateurCerbere</b> modélise un <i>concept</i> 
 * d'<b>Utilisateur</b> d'une application 
 * - (Personne avec des droits sur une application) -
 * avec un nom, un prénom, des coordonnées et un
 * <i>PROFIL UNIQUE</i> à un instant t.<br/>
 * Un UtilisateurCerbere est conforme à l'API 
 * (Application Programming Interface) 
 * d'<strong>authentification CERBERE</strong> du Ministère (MTES).<br/>
 * Un UtilisateurCerbere ne possède 
 * qu'une seule <strong>HABILITATION</strong> 
 * (<code>[Profil, Portee, Restriction]</code>)
 * <i>à la fois</i> 
 * (même si il a plusieurs habilitations sur l'application dans Cerbere).
 * </p>
 * 
 * <p>
 * <b><span style="text-decoration: underline;">
 * Représentation schématique d'un UtilisateurCerbere :
 * </span></b>
 * </p>
 * <p>
 * <img src="../../../../../../../../../../javadoc/images/vues/desktop/metier/utilisateur/UtilisateurCerbereAffichageVue.png" 
 * alt="vue simplifiée d'un UtilisateurCerbere" border="1" align="center" />
 * </p>
 * 
 * 
 * <ul>
 * <p>
 * <span style="text-decoration: underline;">
 * HERITE de :
 * </span>
 * </p>
 * <li><b>IUtilisateurCerbere</b></li>
 * <li><b>IExportateurCsv</b> pour l'export d'un Objet 
 * métier en csv.</li>
 * <li><b>IExportateurJTable</b> pour l'affichage dans 
 * une JTable (Swing).</li>
 * <li><b>Comparable</b> pour l'affichage des Collections 
 * sous forme triée.</li>
 * <li><b>Cloneable</b> pour garantir que tout objet métier 
 * implémentant cette interface saura se cloner.</li>
 * <li><b>Serializable</b> pour garantir que tout objet métier 
 * implémentant cette interface pourra être serialisé.</li>
 * </ul>
 * 
 * 
 * <ul>
 * <p>
 * <span style="text-decoration: underline;">
 * Garantit que tout IUtilisateurCerbere sait :
 * </span>
 * </p>
 * <li>se <b>comparer</b> à un autre IUtilisateurCerbere.</li>
 * <li>se <b>cloner</b>.</li>
 * <li>s'exporter sous forme <b>csv</b>.</li>
 * <li>s'exporter sous forme <b>JTable</b>.</li>
 * </ul>
 * 
 *  
 * <ol>
 * <p>
 * <span style="text-decoration: underline;">
 * Garantit que tout IUtilisateurCerbere possède à minima :
 * </span>
 * </p>
 * <li><b>id</b> pour la mise en base.</li>
 * <li><b>civilite</b></li>
 * <li><b>prenom</b>.</li>
 * <li><b>nom</b>.</li>
 * <li><b>tel</b>.</li>
 * <li><b>email</b>.</li>
 * <li><b>service</b>.</li>
 * <li><b>unite</b>.</li>
 * <li><b>profil</b>.</li>
 * <li><b>portee</b>.</li>
 * <li><b>restriction</b>.</li>
 * </ol>
 * 
 * <p>
 * <span style="text-decoration: underline;">EGALITE METIER</span>
 * </p>
 * <ul>
 * <li>L'<b>égalité metier</b> d'un IUtilisateurCerbere est vérifiée sur :</li>
  * <ul>
 * <li><b>nom</b> (sensible à la casse).</li>
 * <li><b>prenom</b> (sensible à la casse).</li>
 * <li><b>email</b> (sensible à la casse).</li>
 * <li><b>unite</b> (sensible à la casse).</li>
 * </ul>
 * </ul>
 *  
 * <p>
 * <span style="text-decoration: underline;">COMPARAISON</span>
 * </p>
 * <ul>
 * <li>La <b>comparaison</b> d'un IUtilisateurCerbere est réalisée sur :</li>
  * <ol>
 * <li><b>nom</b> (insensible à la casse).</li>
 * <li><b>prenom</b> (insensible à la casse).</li>
 * <li><b>email</b> (insensible à la casse).</li>
 * <li><b>unite</b> (insensible à la casse).</li>
 * </ol>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">DIAGRAMME DE CLASSES D'IMPLEMENTATION</span>
 * </p>
 * <ul>
 * <li>
 * <img src="../../../../../../../../../../javadoc/images/model/metier/utilisateur/UtilisateurCerbere.png" 
 * alt="classes d'implémentation des IUtilisateurCerbere" border="1" align="center" />
 * </li>
 * </ul>
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">REGLES DE GESTION</span>
 * </p>
 * <ul>
 * <li>
 * Les <b>Règles de Gestion (RG)</b> applicables aux attributs 
 * d'un IUtilisateurCerbere sont les suivantes :
 * </li>
 * <br/>
 * <table border="1">
 * <tr>
 * <th>Attribut</th><th>Règle de Gestion</th>
 * </tr>
 * 
 *  
 * <tr>
 * <td rowspan="3">
 * prenom
 * </td>
 * <td>
 * "RG_NOMMAGE_PRENOM_RENSEIGNE_01
 *  : le prénom du IUtilisateurCerbere 
 *  doit être renseigné (obligatoire)"
 * </td>
 * </tr>
 * <tr>
 * <td>
 * "RG_NOMMAGE_PRENOM_LITTERAL_02
 *  : le prénom du IUtilisateurCerbere 
 *  ne doit contenir que des lettres ou des caractères spéciaux 
 *  '-', '_', ... (aucun chiffre)"
 * </td>
 * </tr>
 * <tr>
 * <td>
 * "RG_NOMMAGE_PRENOM_LONGUEUR_03
 *  : le prénom du IUtilisateurCerbere 
 *  doit contenir entre [1] et [50] lettres"
 * </td>
 * </tr>

 * <tr>
 * <td rowspan="3">
 * nom
 * </td>
 * <td>
 * "RG_NOMMAGE_NOM_RENSEIGNE_04 : 
 * le nom du IUtilisateurCerbere 
 * doit être renseigné (obligatoire)"
 * </td>
 * </tr>
 * <tr>
 * <td>
 * "RG_NOMMAGE_NOM_LITTERAL_05 : 
 * le nom du IUtilisateurCerbere 
 * ne doit contenir que des lettres ou des 
 * caractères spéciaux '-', '_', ... (aucun chiffre)"
 * </td>
 * </tr>
 * <tr>
 * <td>
 * "RG_NOMMAGE_NOM_LONGUEUR_06 : 
 * le nom du IUtilisateurCerbere 
 * doit contenir entre [1] et [50] lettres"
 * </td>
 * </tr>
 * 
 * </table>
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
 * @since 18 févr. 2019
 *
 */
public class UtilisateurCerbere implements IUtilisateurCerbere {

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
	 * civilité de l'utilisateur (M, F, ...).<br/>
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
		= LogFactory.getLog(UtilisateurCerbere.class);

	// *************************METHODES************************************/
	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UtilisateurCerbere() {
		
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
	public UtilisateurCerbere(
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
	public UtilisateurCerbere(
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
	public final UtilisateurCerbere clone() 
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
		
		return (UtilisateurCerbere) clone;
		
	} // Fin de clone().___________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("UtilisateurCerbere [");
		
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
	@Override
	public final Long getId() {	
		return this.id;
	} // Fin de getId().___________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setId(
			final Long pId) {	
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
	
			
	
} // FIN DE LA CLASSE UtilisateurCerbere.------------------------------------
