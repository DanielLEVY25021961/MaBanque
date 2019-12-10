package levy.daniel.application.model.services.traiteursfichiers.enregistreursfichiers.rapportsenregistrements;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.traiteursfichiers.IExportateurCsv;
import levy.daniel.application.model.services.traiteursfichiers.IExportateurJTable;
import levy.daniel.application.model.services.traiteursfichiers.IResetable;



/**
 * class LigneRapportEnregistrement :<br/>
 * Encapsulation dans laquelle tous les Enregistreurs de fichiers 
 * vont écrire leur résultat d'enregistrement.<br/>
 * <br/>
 * Cette encapsulation comprend les attributs : <br/>
 * [id;dateEnregistrement;userName;objet;nomFichier;message;chemin;statut;].<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * levy.daniel.application.IExportateurCsv.<br/>
 * levy.daniel.application.IExportateurJTable.<br/>
 * levy.daniel.application.IResetable.<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 3 mars 2016
 *
 */
public class LigneRapportEnregistrement  implements 
				Serializable, Comparable<Object>
					, Cloneable, IExportateurCsv, IExportateurJTable
						, IResetable{

	// ************************ATTRIBUTS************************************/
	
	/**
	 * serialVersionUID : long :<br/>
	 * 1L.<br/>
	 */
	private static final long serialVersionUID = 1L;

	
	/**
	 * CLASSE_LIGNERAPPORTENREGISTREMENT : String :<br/>
	 * "Classe LigneRapportEnregistrement".<br/>
	 */
	public static final String CLASSE_LIGNERAPPORTENREGISTREMENT 
		= "Classe LigneRapportEnregistrement";

	
	/**
	 * METHODE_COMPARE : String :<br/>
	 * "Méthode Compare(Object pObj)".<br/>
	 */
	public static final String METHODE_COMPARE 
		= "Méthode Compare(Object pObj)";
	
	
	/**
	 * id : Long :<br/>
	 * Identifiant en base de l'objet métier.<br/>
	 */
	private Long id;


	/**
	 * dateEnregistrement : String :<br/>
	 * date d'exécution de l'enregistrement.<br/>
	 * doit être impérativement fournie au format 
	 * des dates-heures françaises avec millisecondes 
	 * (dfDatetimemilliFrancaiseLexico) comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 */
	private String dateEnregistrement;

	
	/**
	 * userName : String :<br/>
	 * nom de l'utilisateur qui a déclenché l'enregistrement.<br/>
	 */
	private String userName;

	
	/**
	 * objet : String :<br/>
	 * objet (ou motif) ayant demandé la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/>
	 */
	private String objet;
	

	/**
	 * nomFichier : String :<br/>
	 * nom du fichier objet de l'enregistrement.<br/>
	 */
	private String nomFichier;

	
	/**
	 * message : String :<br/>
	 * message à l'attention de l'utilisateur 
	 * indiquant si le fichier a bien été enregistré.<br/>
	 */
	private String message;
	
	
	/**
	 * chemin : String :<br/>
	 * chemin de création du fichier enregistré sur le disque.<br/>
	 */
	private String chemin;
	
	
	/**
	 * statut : String :<br/>
	 * statut de la création du fichier (OK si créé, KO sinon).<br/>
	 */
	private String statut;
	

	
	/**
	 * LOCALE_FR_FR : Locale :<br/>
	 * new Locale("fr", "FR").<br/>
	 */
	public static final Locale LOCALE_FR_FR = new Locale("fr", "FR");


	/**
	 * dfDatetimemilliFrancaiseLexico : DateFormat :<br/>
	 * Format des dates-heures françaises avec millisecondes comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 */
	public final transient DateFormat dfDatetimemilliFrancaise 
	= new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss.SSS", LOCALE_FR_FR);
	
	
	/**
	 * SEP_MOINS : String :<br/>
	 * " - ".<br/>
	 */
	public static final String SEP_MOINS = " - ";


	/**
	 * SEP_POINTVIRGULE : String :<br/>
	 * ";".<br/>
	 */
	public static final String SEP_POINTVIRGULE = ";";


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(LigneRapportEnregistrement.class);

	
	// *************************METHODES************************************/
	
	
	
	 /**
	 * method CONSTRUCTEUR LigneRapportEnregistrement() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public LigneRapportEnregistrement() {
		this(null, null, null, null, null, null, null, null);
	} // FIn de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	 /**
	 * method CONSTRUCTEUR LigneRapportEnregistrement(COMPLET) :<br/>
	 * CONSTRUCTEUR COMPLET.<br/>
	 * Sans id en base.<br/>
	 * <br/>
	 *
	 * @param pDateEnregistrement : String : 
	 * date d'exécution de l'enregistrement.
	 * doit être impérativement fournie au format 
	 * des dates-heures françaises avec millisecondes 
	 * (dfDatetimemilliFrancaiseLexico) comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 * @param pUserName : String : nom de l'utilisateur 
	 * qui a déclenché l'enregistrement.<br/>
	 * @param pObjet : String : objet (ou motif) ayant 
	 * demandé la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/> 
	 * @param pNomFichier : String : nom du fichier 
	 * objet de l'enregistrement sur disque.<br/>
	 * @param pMessage : String : message à l'attention de l'utilisateur 
	 * indiquant si le fichier a bien été enregistré.<br/>
	 * @param pChemin : String : chemin de création du fichier 
	 * enregistré sur le disque.<br/>
	 * @param pStatut : String : statut de la création du fichier 
	 * (OK si créé, KO sinon).<br/>
	 */
	public LigneRapportEnregistrement(
			final String pDateEnregistrement
			, final String pUserName
			, final String pObjet
			, final String pNomFichier			
			, final String pMessage
			, final String pChemin
			, final String pStatut) {
		
		this(null
				, pDateEnregistrement
				, pUserName
				, pObjet
				, pNomFichier
				, pMessage
				, pChemin
				, pStatut);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
	
	
	 /**
	 * method CONSTRUCTEUR LigneRapportEnregistrement(COMPLET PERSISTANT) :<br/>
	 * CONSTRUCTEUR COMPLET PERSISTANT.<br/>
	 * <br/>
	 *
	 * @param pId : Long : Identifiant en base de l'objet métier.<br/>
	 * @param pDateEnregistrement : String : 
	 * date d'exécution de l'enregistrement.
	 * doit être impérativement fournie au format 
	 * des dates-heures françaises avec millisecondes 
	 * (dfDatetimemilliFrancaiseLexico) comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 * @param pUserName : String : nom de l'utilisateur 
	 * qui a déclenché l'enregistrement.<br/>
	 * @param pObjet : String : objet (ou motif) ayant 
	 * demandé la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/> 
	 * @param pNomFichier : String : nom du fichier 
	 * objet de l'enregistrement sur disque.<br/>
	 * @param pMessage : String : message à l'attention de l'utilisateur 
	 * indiquant si le fichier a bien été enregistré.<br/>
	 * @param pChemin : String : chemin de création du fichier 
	 * enregistré sur le disque.<br/>
	 * @param pStatut : String : statut de la création du fichier 
	 * (OK si créé, KO sinon).<br/>
	 */
	public LigneRapportEnregistrement(
			final Long pId
			, final String pDateEnregistrement
			, final String pUserName
			, final String pObjet
			, final String pNomFichier			
			, final String pMessage
			, final String pChemin
			, final String pStatut) {
		
		super();
		
		this.id = pId;
		this.dateEnregistrement = pDateEnregistrement;
		this.userName = pUserName;
		this.objet = pObjet;
		this.nomFichier = pNomFichier;		
		this.message = pMessage;
		this.chemin = pChemin;
		this.statut = pStatut;
		
	} // Fin de CONSTRUCTEUR COMPLET PERSISTANT.___________________________


	
	
	/**
	 * champs (7 attributs) :<br/>
	 * [dateEnregistrement;userName;objet;nomFichier;message;chemin;statut;].<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		
		final int prime = 31;
		int result = 1;
		
		result = prime * result
				+ ((this.chemin == null) ? 0 : this.chemin.hashCode());
		result = prime
				* result
				+ ((this.dateEnregistrement == null) ? 0
						: this.dateEnregistrement.hashCode());
		result = prime * result
				+ ((this.message == null) ? 0 : this.message.hashCode());
		result = prime * result
				+ ((this.nomFichier == null) ? 0 : this.nomFichier.hashCode());
		result = prime * result
				+ ((this.objet == null) ? 0 : this.objet.hashCode());
		result = prime * result
				+ ((this.statut == null) ? 0 : this.statut.hashCode());
		result = prime * result
				+ ((this.userName == null) ? 0 : this.userName.hashCode());
		
		return result;
		
	} // Fin de hashCode().________________________________________________



	/**
	 * champs (7 attributs) :<br/>
	 * [dateEnregistrement;userName;objet;nomFichier;message;chemin;statut;].<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(
			final Object pObj) {
		
		if (this == pObj) {
			return true;
		}
		if (pObj == null) {
			return false;
		}
		if (!(pObj instanceof LigneRapportEnregistrement)) {
			return false;
		}
		
		final LigneRapportEnregistrement other 
			= (LigneRapportEnregistrement) pObj;
		
		if (this.chemin == null) {
			if (other.chemin != null) {
				return false;
			}
		} else if (!this.chemin.equals(other.chemin)) {
			return false;
		}
		
		if (this.dateEnregistrement == null) {
			if (other.dateEnregistrement != null) {
				return false;
			}
		} else if (!this.dateEnregistrement.equals(other.dateEnregistrement)) {
			return false;
		}
		
		if (this.message == null) {
			if (other.message != null) {
				return false;
			}
		} else if (!this.message.equals(other.message)) {
			return false;
		}
		
		if (this.nomFichier == null) {
			if (other.nomFichier != null) {
				return false;
			}
		} else if (!this.nomFichier.equals(other.nomFichier)) {
			return false;
		}
		
		if (this.objet == null) {
			if (other.objet != null) {
				return false;
			}
		} else if (!this.objet.equals(other.objet)) {
			return false;
		}
		
		if (this.statut == null) {
			if (other.statut != null) {
				return false;
			}
		} else if (!this.statut.equals(other.statut)) {
			return false;
		}
		
		if (this.userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!this.userName.equals(other.userName)) {
			return false;
		}
		
		return true;
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * champs (7 attributs) :<br/>
	 * [dateEnregistrement;userName;objet;nomFichier;message;chemin;statut;].<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final Object pObject) {
		
		/* Même instance : 0. */
		if (this == pObject) {
			return 0;
		}
		
		/* Paramètre null : -1. */
		if (pObject == null) {
			return -1;
		}
		
		/* Mauvaise instance : -1. */
		if (!(pObject instanceof LigneRapportEnregistrement)) {
			return -1;
		}
		
		/* Cast. */
		final LigneRapportEnregistrement other 
			= (LigneRapportEnregistrement) pObject;
		
		/* Parse les String en dates pour la comparaison. */
		Date maDate = null;
		Date otherDate = null;
		
		final String maDateString = this.getDateEnregistrement();
		final String otherDateString = other.getDateEnregistrement();
		
		try {
			
			if (maDateString != null) {
				maDate 
					= this.dfDatetimemilliFrancaise.parse(maDateString);
			}
		} catch (ParseException parseExc1) {
			
			final String messageInterne 
			= "la date " 
			+ maDateString 
			+ " n'est pas conforme au pattern 'dd/MM/yyyy-HH:mm:ss.SSS' : ";
			
			/* LOG de niveau INFO. */
			loggerInfo(
					CLASSE_LIGNERAPPORTENREGISTREMENT
					, METHODE_COMPARE, messageInterne, parseExc1.getMessage());
		}
		
		try {
			
			if (otherDateString != null) {
				otherDate 
					= this.dfDatetimemilliFrancaise.parse(otherDateString);
			}
			
		} catch (ParseException parseExc2) {
			
			final String messageInterne 
			= "la date " 
			+ otherDateString 
			+ " n'est pas conforme au pattern 'dd/MM/yyyy-HH:mm:ss.SSS' : ";
			
			/* LOG de niveau INFO. */
			loggerInfo(
					CLASSE_LIGNERAPPORTENREGISTREMENT
					, METHODE_COMPARE, messageInterne, parseExc2.getMessage());
		}
		
		int compareDateEnregistrement = 0;
		int compareUserName = 0;
		int compareObjet = 0;
		int compareNomFichier = 0;
		int compareMessage = 0;
		int compareChemin = 0;
		int compareStatut = 0;
		
		/* 1 - dateEnregistrement. */
		if (maDate == null) {
			
			if (otherDate != null) {
				return +1;
			}
		}
		else {
			
			if (otherDate == null) {
				return -1;
			}
			
			compareDateEnregistrement = maDate.compareTo(otherDate);
			
			if (compareDateEnregistrement != 0) {
				return compareDateEnregistrement;
			}
			
		} // Fin de dateControle.______
		
		/* 2 - userName. */
		if (this.getUserName() == null) {
			
			if (other.getUserName() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getUserName() == null) {
				return -1;
			}
			
			compareUserName 
				= this.getUserName().compareTo(other.getUserName());
			
			if (compareUserName != 0) {
				return compareUserName;
			}
		} // Fin de userName._______________________
		
		/* 3 - objet. */
		if (this.getObjet() == null) {
			
			if (other.getObjet() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getObjet() == null) {
				return -1;
			}
			
			compareObjet 
				= this.getObjet().compareTo(other.getObjet());
			
			if (compareObjet != 0) {
				return compareObjet;
			}
		} // Fin de objet.__________________________________
		
		/* 4 - nomFichier. */
		if (this.getNomFichier() == null) {
			
			if (other.getNomFichier() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getNomFichier() == null) {
				return -1;
			}
			
			compareNomFichier = this.getNomFichier()
						.compareTo(other.getNomFichier());
			
			if (compareNomFichier != 0) {
				return compareNomFichier;
			}
		} // Fin de nomFichier.______________________
		
		/* 5 - message. */
		if (this.getMessage() == null) {
			
			if (other.getMessage() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getMessage() == null) {
				return -1;
			}
			
			compareMessage = this.getMessage()
						.compareTo(other.getMessage());
			
			if (compareMessage != 0) {
				return compareMessage;
			}
		} // Fin de message.___________________
		
		/* 6 - chemin. */
		if (this.getChemin() == null) {
			
			if (other.getChemin() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getChemin() == null) {
				return -1;
			}
			
			compareChemin = this.getChemin()
						.compareTo(other.getChemin());
			
			if (compareChemin != 0) {
				return compareChemin;
			}
		} // Fin de chemin.__________________________
		
		/* 7 - statut. */
		if (this.getStatut() == null) {
			
			if (other.getStatut() != null) {
				return +1;
			}
			
			/* retourne 0 si le dernier attribut 
			 * est null également dans other. */
			return 0;
			
		}
		
		compareStatut = this.getStatut().compareTo(other.getStatut());
		
		/* retourne la comparaison entre les 
		 * derniers attributs quoi qu'il arrive. */
		return compareStatut;
		
	} // fin de compareTo()._______________________________________________


	
	/**
	 * Champs (7 attributs + id) :<br/>
	 * [id;dateEnregistrement;userName;objet;nomFichier;message;chemin;statut;].<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final Object clone() throws CloneNotSupportedException {
		
		final LigneRapportEnregistrement clone 
			= (LigneRapportEnregistrement) super.clone();
		
		clone.setId(this.id);
		clone.setDateEnregistrement(this.dateEnregistrement);
		clone.setUserName(this.userName);
		clone.setObjet(this.objet);
		clone.setNomFichier(this.nomFichier);
		clone.setMessage(this.message);
		clone.setChemin(this.chemin);
		clone.setStatut(this.statut);
		
		return clone;
		
	} // Fin de clone().___________________________________________________
	

	
	/**
	 * Sert à afficher à la console un LigneRapportEnregistrement.<br/>
	 * <br/>
	 * Champs (7 attributs + id) :<br/>
	 * [id;dateEnregistrement;userName;objet;nomFichier;message;chemin;statut;].<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		/* 0 - id. */
		if (this.id != null) {
			builder.append(
					String.format(LOCALE_FR_FR
							, "id : %-7d", this.id));
		}
		
		/* 1 - dateEnregistrement. */		
		builder.append(
					String.format(LOCALE_FR_FR
							, "Date de l'enregistrement : %-25s"
								, this.dateEnregistrement));
				
		/* 2 - userName */
		builder.append(
				String.format(LOCALE_FR_FR
						, "User : %-30s", this.userName));
		
		/* 3 - objet. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Objet : %-30s", this.objet));
		
		/* 4 - nomFichier. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Fichier enregistré : %-100s", this.nomFichier));
		
		/* 5 - message. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Message d'enregistrement : %-200s"
							, this.message));
		
		/* 6 - chemin. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Chemin d'enregistrement : %-200s"
							, this.chemin));
		
		/* 7 - statut. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Statut de l'enregistrement : %-7s"
							, this.statut));
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________
	
	

	/**
	 * retourne : <br/>
	 * "id;date de l'enregistrement;utilisateur;Objet;Fichier;
	 * Message de l'enregistrement;Chemin du fichier enregistré;
	 * Statut de l'enregistrement;".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteCsv() {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append("id;");
		stb.append("date de l'enregistrement;");
		stb.append("utilisateur;");
		stb.append("Objet;");
		stb.append("Fichier;");
		stb.append("Message de l'enregistrement;");
		stb.append("Chemin du fichier enregistré;");
		stb.append("Statut de l'enregistrement;");
		
		return stb.toString();
		
	} // Fin de getEnTeteCsv().____________________________________________


	
	/**
	 * Fournit le ValueObject sous forme de ligne Csv.<br/>
	 * <br/>
	 * "id;dateEnregistrement;userName;objet;nomFichier;message;chemin;statut;".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringCsv() {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append(this.id);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.dateEnregistrement);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.userName);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.objet);
		stb.append(SEP_POINTVIRGULE);		
		stb.append(this.nomFichier);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.message);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.chemin);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.statut);
		stb.append(SEP_POINTVIRGULE);
		
		return stb.toString();

	} // Fin de toCsv().___________________________________________________


	
	/**
	 * "id;date de l'enregistrement;utilisateur;Objet;Fichier;
	 * Message de l'enregistrement;Chemin du fichier enregistré;
	 * Statut de l'enregistrement;".<br/>
	 * <br/>
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
			entete = "date de l'enregistrement";
			break;

		case 2:
			entete = "utilisateur";
			break;

		case 3:
			entete = "Objet";
			break;
			
		case 4:
			entete = "Fichier";
			break;
			
		case 5:
			entete = "Message de l'enregistrement";
			break;
			
		case 6:
			entete = "Chemin du fichier enregistré";
			break;
			
		case 7:
			entete = "Statut de l'enregistrement";
			break;
			
		default:
			entete = "invalide";
			break;
			
		} // Fin du Switch._________________________________
		
		return entete;
		
	} // Fin de getEnTeteColonne(...)._____________________________________


	
	/**
	 * "id;dateEnregistrement;userName;objet;nomFichier;message;chemin;statut;".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final Object fournirValeurColonne(
			final int pI) {
		
		Object valeur = null;
		
		switch (pI) {
		
		case 0:
			valeur = this.id;
			break;
		
		case 1:
			valeur = this.dateEnregistrement;
			break;
		
		case 2:
			valeur = this.userName;
			break;
		
		case 3:
			valeur = this.objet;
			break;
			
		case 4:
			valeur = this.nomFichier;
			break;
			
		case 5:
			valeur = this.message;
			break;
			
		case 6:
			valeur = this.chemin;
			break;
			
		case 7:
			valeur = this.statut;
			break;
						
		default:
			valeur = "invalide";
			break;
			
		} // Fin du Switch._________________________________

		return valeur;

	} // Fin de getValeurColonne(...)._____________________________________
	

	
	/**
	 * "id;dateEnregistrement;userName;objet;nomFichier;message;chemin;statut;".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final void reset() {
		
		this.id = null;
		this.dateEnregistrement = null;
		this.userName = null;
		this.objet = null;
		this.nomFichier = null;
		this.message = null;
		this.chemin = null;
		this.statut = null;
		
	} // Fin de reset().___________________________________________________


	
	/**
	 * method remplir(....) :<br/>
	 * Méthode à utiliser avec reset() pour éviter d'instancier 
	 * un nouvel objet à chaque itération d'une boucle.<br/>
	 * <br/>
	 *
	 * @param pId : Long : Identifiant en base de l'objet métier.<br/>
	 * @param pDateEnregistrement : String : 
	 * date d'exécution de l'enregistrement.
	 * doit être impérativement fournie au format 
	 * des dates-heures françaises avec millisecondes 
	 * (dfDatetimemilliFrancaiseLexico) comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 * @param pUserName : String : nom de l'utilisateur 
	 * qui a déclenché l'enregistrement.<br/>
	 * @param pObjet : String : objet (ou motif) ayant 
	 * demandé la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/> 
	 * @param pNomFichier : String : nom du fichier 
	 * objet de l'enregistrement sur disque.<br/>
	 * @param pMessage : String : message à l'attention de l'utilisateur 
	 * indiquant si le fichier a bien été enregistré.<br/>
	 * @param pChemin : String : chemin de création du fichier 
	 * enregistré sur le disque.<br/>
	 * @param pStatut : String : statut de la création du fichier 
	 * (OK si créé, KO sinon).<br/>
	 */
	public final void remplir(
			final Long pId
			, final String pDateEnregistrement
			, final String pUserName
			, final String pObjet
			, final String pNomFichier			
			, final String pMessage
			, final String pChemin
			, final String pStatut) {
		
		this.id = pId;
		this.dateEnregistrement = pDateEnregistrement;
		this.userName = pUserName;
		this.objet = pObjet;
		this.nomFichier = pNomFichier;
		this.message = pMessage;
		this.chemin = pChemin;
		this.statut = pStatut;
		
	} // Fin de remplir(...).______________________________________________
	
	
	
	/**
	 * method loggerInfo(
	 * String pClasse
	 * , String pMethode
	 * , String pMessage
	 * , String pComplement) :<br/>
	 * Créée un message de niveau INFO dans le LOG.<br/>
	 * <br/>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pMessage : String : Message particulier de la méthode.<br/>
	 * @param pComplement : String : Complément au message de la méthode.<br/>
	 */
	private void loggerInfo(
			final String pClasse
				, final String pMethode
					, final String pMessage
						, final String pComplement) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null 
				|| pMessage == null || pComplement == null) {
			return;
		}
		
		/* LOG de niveau INFO. */			
		if (LOG.isInfoEnabled()) {
			
			final String messageLog 
			= pClasse 
			+ SEP_MOINS
			+ pMethode
			+ SEP_MOINS
			+ pMessage
			+ pComplement;
			
			LOG.info(messageLog);
		}
		
	} // Fin de loggerInfo(
	 // String pClasse
	 // , String pMethode
	 // , String pMessage
	 // , String pComplement)._____________________________________________
	


	/**
	 * method getId() :<br/>
	 *  Getter de l'identifiant en base.<br/>
	 * <br/>
	 * 
	 * @return this.id : Long.<br/>
	 */
	public final Long getId() {
		return this.id;
	} // Fin de getId().___________________________________________________


	
	/**
	 * method setId(
	 * Long pId) :<br/>
	 * Setter de l'identifiant en base.<br/>
	 * <br/>
	 *
	 * @param pId : Long : valeur à passer à id.<br/>
	 */
	public final void setId(
			final Long pId) {
		this.id = pId;
	} // Fin de setId(
	 // Long pId)._________________________________________________________
	


	/**
	 * method getDateEnregistrement() :<br/>
	 * Getter de la date d'exécution de l'enregistrement.<br/>
	 * doit être impérativement fournie au format 
	 * des dates-heures françaises avec millisecondes 
	 * (dfDatetimemilliFrancaiseLexico) comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 * <br/>
	 *
	 * @return dateEnregistrement : String.<br/>
	 */
	public final String getDateEnregistrement() {
		return this.dateEnregistrement;
	} // Fin de getDateEnregistrement().___________________________________
	


	/**
	 * method setDateEnregistrement(
	 * String pDateEnregistrement) :<br/>
	 * Setter de la date d'exécution de l'enregistrement.<br/>
	 * doit être impérativement fournie au format 
	 * des dates-heures françaises avec millisecondes 
	 * (dfDatetimemilliFrancaiseLexico) comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 * <br/>
	 * <br/>
	 *
	 * @param pDateEnregistrement : String : 
	 * valeur à passer à dateEnregistrement.<br/>
	 */
	public final void setDateEnregistrement(
			final String pDateEnregistrement) {
		this.dateEnregistrement = pDateEnregistrement;
	} // Fin de setDateEnregistrement(
	 // String pDateEnregistrement)._______________________________________
	


	/**
	 * method getUserName() :<br/>
	 * Getter du nom de l'utilisateur qui a déclenché l'enregistrement.<br/>
	 * <br/>
	 *
	 * @return userName : String.<br/>
	 */
	public final String getUserName() {
		return this.userName;
	} // Fin de getUserName()._____________________________________________


	/**
	 * method setUserName(
	 * String pUserName) :<br/>
	 * Setter du nom de l'utilisateur qui a déclenché l'enregistrement.<br/>
	 * <br/>
	 *
	 * @param pUserName : String : valeur à passer à userName.<br/>
	 */
	public final void setUserName(
			final String pUserName) {
		this.userName = pUserName;
	} // Fin de setUserName(
	 // String pUserName)._________________________________________________



	/**
	 * method getObjet() :<br/>
	 * Getter de l'objet (ou motif) ayant demandé la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/>
	 * <br/>
	 *
	 * @return objet : String.<br/>
	 */
	public final String getObjet() {
		return this.objet;
	} // Fin de getObjet().________________________________________________


	
	/**
	 * method setObjet(
	 * String pObjet) :<br/>
	 * Setter de l'objet (ou motif) ayant demandé la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/>
	 * <br/>
	 *
	 * @param pObjet : String : valeur à passer à objet.<br/>
	 */
	public final void setObjet(
			final String pObjet) {
		this.objet = pObjet;
	} // Fin de setObjet(
	 // String pObjet).____________________________________________________



	/**
	 * method getNomFichier() :<br/>
	 * Getter du nom du fichier objet de l'enregistrement.<br/>
	 * <br/>
	 *
	 * @return nomFichier : String.<br/>
	 */
	public final String getNomFichier() {
		return this.nomFichier;
	} // Fin de getNomFichier().___________________________________________


	
	/**
	 * method setNomFichier(
	 * String pNomFichier) :<br/>
	 * Setter du nom du fichier objet de l'enregistrement.<br/>
	 * <br/>
	 *
	 * @param pNomFichier : String : valeur à passer à nomFichier.<br/>
	 */
	public final void setNomFichier(
			final String pNomFichier) {
		this.nomFichier = pNomFichier;
	} // Fin de setNomFichier(
	 // String pNomFichier)._______________________________________________

	
	
	/**
	 * method getMessage() :<br/>
	 * Getter du message à l'attention de l'utilisateur 
	 * indiquant si le fichier a bien été enregistré.<br/>
	 * <br/>
	 *
	 * @return message : String.<br/>
	 */
	public final String getMessage() {
		return this.message;
	} // Fin de getMessage().______________________________________________


	
	/**
	 * method setMessage(
	 * String pMessage) :<br/>
	 * Setter du message à l'attention de l'utilisateur 
	 * indiquant si le fichier a bien été enregistré.<br/>
	 * <br/>
	 *
	 * @param pMessage : String : valeur à passer à message.<br/>
	 */
	public final void setMessage(
			final String pMessage) {
		this.message = pMessage;
	} // Fin de setMessage(
	 // String pMessage).__________________________________________________
	


	/**
	 * method getChemin() :<br/>
	 * Getter du chemin de création du fichier enregistré sur le disque.<br/>
	 * <br/>
	 *
	 * @return chemin : String.<br/>
	 */
	public final String getChemin() {
		return this.chemin;
	} // Fin de getChemin()._______________________________________________


	
	/**
	 * method setChemin(
	 * String pChemin) :<br/>
	 * Setter du chemin de création du fichier enregistré sur le disque.<br/>
	 * <br/>
	 *
	 * @param pChemin : String : valeur à passer à chemin.<br/>
	 */
	public final void setChemin(
			final String pChemin) {
		this.chemin = pChemin;
	} // Fin de setChemin(
	 // String pChemin).___________________________________________________


	
	/**
	 * method getStatut() :<br/>
	 * Getter du statut de la création du fichier (OK si créé, KO sinon).<br/>
	 * <br/>
	 *
	 * @return statut : String.<br/>
	 */
	public final String getStatut() {
		return this.statut;
	} // Fin de getStatut()._______________________________________________


	
	/**
	 * method setStatut(
	 * String pStatut) :<br/>
	 * Setter du statut de la création du fichier (OK si créé, KO sinon).<br/>
	 * <br/>
	 *
	 * @param pStatut : String : valeur à passer à statut.<br/>
	 */
	public final void setStatut(
			final String pStatut) {
		this.statut = pStatut;
	} // Fin de setStatut(
	 // String pStatut).___________________________________________________

	
	
} // FIN DE LA CLASSE LigneRapportEnregistrement.----------------------------
