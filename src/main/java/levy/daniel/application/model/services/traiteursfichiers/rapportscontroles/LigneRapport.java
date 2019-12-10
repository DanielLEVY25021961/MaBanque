package levy.daniel.application.model.services.traiteursfichiers.rapportscontroles;

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
 * CLASSE LigneRapport :<br/>
 * <p>Encapsulation dans laquelle tous les contrôles 
 * (contrôle du fait qu'un fichier est bien un fichier texte
 * , contrôle du fait qu'un fichier est bien en UTF-8, ...) 
 * vont écrire leur résultat de contrôle.</p>
 * 
 * <p>Cette encapsulation comprend les attributs : <br/>
 * [id;dateControle;userName;nomFichier;typeControle;
 * nomControle;critere;gravite;
 * numeroLigne;messageControle;ordreChamp;
 * positionChamp;valeurChamp;action;]</p>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 26 févr. 2016
 *
 */
public class LigneRapport implements Serializable, Comparable<LigneRapport>
		, Cloneable, IExportateurCsv, IExportateurJTable, IResetable {

	// ************************ATTRIBUTS************************************/

	/**
	 * serialVersionUID : long :<br/>
	 * 1L.<br/>
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * "Classe LigneRapport".<br/>
	 */
	public static final String CLASSE_LIGNE_RAPPORT 
		= "Classe LigneRapport";
	
	/**
	 * "Méthode Compare(Object pObj)".<br/>
	 */
	public static final String METHODE_COMPARE 
		= "Méthode Compare(Object pObj)";
		
	/**
	 * Identifiant en base de l'objet métier.<br/>
	 */
	private Long id;

	/**
	 * ordre d'exécution du contrôle dans un enchaînement de contrôles.<br/>
	 */
	private Integer ordreControle;
	
	/**
	 * date d'exécution du contrôle.<br/>
	 * doit être impérativement fournie au format 
	 * des dates-heures françaises avec millisecondes 
	 * (dfDatetimemilliFrancaiseLexico) comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 */
	private String dateControle;
	
	/**
	 * nom de l'utilisateur qui a déclenché le contrôle.<br/>
	 */
	private String userName;
	
	/**
	 * nom du fichier objet du contrôle.<br/>
	 */
	private String nomFichier;
		
	/**
	 * type du contrôle ('contrôle de surface' par exemple).<br/>
	 */
	private String typeControle;
	
	/**
	 * nom du contrôle ('contrôle fichier texte' par exemple).<br/>
	 */
	private String nomControle;
		
	/**
	 * désignation du critère vérifié par le contrôle 
	 * comme "une ligne ne doit pas être vide" ou 
	 * "une ligne doit contenir obligatoirement 520 caractères".<br/>
	 */
	private String critere;
	
	/**
	 * désignation de la gravité de ce contrôle 
	 * (par exemple '1 - bloquant').<br/>
	 */
	private String gravite;
		
	/**
	 * numéro de la ligne dans le fichier qui déclenche le contrôle.<br/>
	 */
	private Integer numeroLigne;
		
	/**
	 * message émis par le contrôle.<br/>
	 */
	private String messageControle;
	
	/**
	 * ordre du champ contrôlé
	 * (dans un fichier 
	 * comportant une liste de champs comme un fichier ASCII HIT).<br/>
	 */
	private Integer ordreChamp;
		
	/**
	 * position du champ contrôlé dans une ligne du fichier 
	 * comme 7 ou [7-12].<br/>
	 */
	private String positionChamp;
		
	/**
	 * valeur prise par le champ contrôlé exprimée sous forme de String.<br/>
	 */
	private String valeurChamp;
		
	/**
	 * Boolean qui vaut : <br/>
	 * - true si le contrôle est favorable après un contrôle.<br/>
	 * - false si le contrôle est défavorable après un contrôle.<br/>
	 * Un contrôle doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.<br/>
	 */
	private Boolean statut;
		
	/**
	 * action menée après le contrôle comme "ligne éliminée" 
	 * ou "ligne conservée".<br/>
	 */
	private String action;
		
	/**
	 * new Locale("fr", "FR").<br/>
	 */
	public static final Locale LOCALE_FR_FR = new Locale("fr", "FR");

	/**
	 * dfDatetimemilliFrancaiseLexico : DateFormat :<br/>
	 * Format des dates-heures françaises lexicographique 
	 * avec millisecondes comme
	 * '1961-01-25_12-27-07-251'.<br/>
	 * "yyyy-MM-dd_HH-mm-ss-SSS".<br/>
	 */
	public final transient DateFormat dfDatetimemilliFrancaiseLexico 
	= new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS", LOCALE_FR_FR);
		
	/**
	 * " - ".<br/>
	 */
	public static final String SEP_MOINS = " - ";

	/**
	 * ";".<br/>
	 */
	public static final String SEP_POINTVIRGULE = ";";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(LigneRapport.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public LigneRapport() {
		
		this(null, null
				, null, null, null, null, null, null, null
				, null, null, null, null, null, true, null);
		
	} // FIn de CONSTRUCTEUR D'ARITE NULLE.________________________________


		
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * Sans id en base.<br/>
	 * AVEC ordreControle = 1.<br/>
	 * - Met automatiquement le satut du contrôle à true.<br/>
	 * <br/>
	 *
	 * @param pDateControle : String : date d'exécution du contrôle.
	 * doit être impérativement fournie au Format des 
	 * dates-heures françaises lexicographique 
	 * avec millisecondes comme
	 * '1961-01-25_12-27-07-251'.<br/>
	 * "yyyy-MM-dd_HH-mm-ss-SSS".<br/>
	 * @param pUserName : String : nom de l'utilisateur 
	 * qui a déclenché le contrôle.<br/> 
	 * @param pNomFichier : String : nom du fichier 
	 * objet du contrôle.<br/>
	 * @param pTypeControle : String : type du contrôle 
	 * (contrôle de surface par exemple).<br/> 
	 * @param pNomControle : String : nom du contrôle 
	 * ('contrôle fichier texte' par exemple).<br/>
	 * @param pCritere : String : désignation du critère vérifié par le contrôle 
	 * comme "une ligne ne doit pas être vide" ou 
	 * "une ligne doit contenir obligatoirement 520 caractères".<br/>
	 * @param pGravite : String : désignation de la gravité de ce contrôle 
	 * (par exemple 'bloquant').<br/>
	 * @param pNumeroLigne : Integer : numéro de la ligne dans le fichier 
	 * qui déclenche le contrôle.<br/>
	 * @param pMessageControle : String : message émis par le contrôle.<br/>
	 * @param pOrdreChamp : Integer : ordre du champ contrôlé
	 * (dans un fichier 
	 * comportant une liste de champs comme un fichier ASCII HIT).<br/>
	 * @param pPositionChamp : String : position du champ contrôlé 
	 * dans une ligne du fichier 
	 * comme 7 ou [7-12].<br/>
	 * @param pValeurChamp : String : valeur prise par le champ contrôlé.<br/> 
	 * @param pAction : String : action menée après le contrôle 
	 * comme "ligne éliminée" ou "ligne conservée".<br/>
	 */
	public LigneRapport(
			final String pDateControle
			, final String pUserName
			, final String pNomFichier
			, final String pTypeControle
			, final String pNomControle
			, final String pCritere
			, final String pGravite
			, final Integer pNumeroLigne
			, final String pMessageControle
			, final Integer pOrdreChamp
			, final String pPositionChamp
			, final String pValeurChamp
			, final String pAction) {
		
		this(null, 1, pDateControle, pUserName, pNomFichier, pTypeControle
				, pNomControle, pCritere, pGravite, pNumeroLigne
				, pMessageControle, pOrdreChamp, pPositionChamp
				, pValeurChamp, true, pAction);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * CONSTRUCTEUR COMPLET NON PERSISTANT.<br/>
	 * <br/>
	 * - Sans id en base.<br/>
	 * - Met automatiquement le satut du contrôle à true.<br/>
	 * <br/>
	 * 
	 * @param pOrdreControle : Integer : ordre d'exécution du contrôle 
	 * dans un enchaînement de contrôles.<br/>
	 * @param pDateControle : String : date d'exécution du contrôle.
	 * doit être impérativement fournie au Format 
	 * des dates-heures françaises lexicographique 
	 * avec millisecondes comme
	 * '1961-01-25_12-27-07-251'.<br/>
	 * "yyyy-MM-dd_HH-mm-ss-SSS".<br/>
	 * @param pUserName : String : nom de l'utilisateur 
	 * qui a déclenché le contrôle.<br/> 
	 * @param pNomFichier : String : nom du fichier 
	 * objet du contrôle.<br/>
	 * @param pTypeControle : String : type du contrôle 
	 * (contrôle de surface par exemple).<br/> 
	 * @param pNomControle : String : nom du contrôle 
	 * ('contrôle fichier texte' par exemple).<br/>
	 * @param pCritere : String : désignation du critère vérifié par le contrôle 
	 * comme "une ligne ne doit pas être vide" ou 
	 * "une ligne doit contenir obligatoirement 520 caractères".<br/>
	 * @param pGravite : String : désignation de la gravité de ce contrôle 
	 * (par exemple 'bloquant').<br/>
	 * @param pNumeroLigne : Integer : numéro de la ligne dans le fichier 
	 * qui déclenche le contrôle.<br/>
	 * @param pMessageControle : String : message émis par le contrôle.<br/>
	 * @param pOrdreChamp : Integer : ordre du champ contrôlé
	 * (dans un fichier 
	 * comportant une liste de champs comme un fichier ASCII HIT).<br/>
	 * @param pPositionChamp : String : position du champ contrôlé 
	 * dans une ligne du fichier 
	 * comme 7 ou [7-12].<br/>
	 * @param pValeurChamp : String : valeur prise par le champ contrôlé.<br/>
	 * @param pStatut : Boolean : Boolean qui vaut : <br/>
	 * - true si le contrôle est favorable après un contrôle.<br/>
	 * - false si le contrôle est défavorable après un contrôle.<br/>
	 * Un contrôle doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.<br/> 
	 * @param pAction : String : action menée après le contrôle 
	 * comme "ligne éliminée" ou "ligne conservée".<br/>
	 */
	public LigneRapport(
			 final Integer pOrdreControle
			, final String pDateControle
			, final String pUserName
			, final String pNomFichier
			, final String pTypeControle
			, final String pNomControle
			, final String pCritere
			, final String pGravite
			, final Integer pNumeroLigne
			, final String pMessageControle
			, final Integer pOrdreChamp
			, final String pPositionChamp
			, final String pValeurChamp
			, final Boolean pStatut
			, final String pAction) {
		
		this(null, pOrdreControle
				, pDateControle, pUserName, pNomFichier, pTypeControle
				, pNomControle, pCritere, pGravite, pNumeroLigne
				, pMessageControle, pOrdreChamp, pPositionChamp
				, pValeurChamp, pStatut, pAction);
		
	} // Fin du CONSTRUCTEUR COMPLET NON PERSISTANT._______________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET PERSISTANT.<br/>
	 * 
	 * @param pId : Long : Identifiant en base de l'objet métier.<br/>
	 * @param pOrdreControle : Integer : ordre d'exécution du contrôle 
	 * dans un enchaînement de contrôles.<br/>
	 * @param pDateControle : String : date d'exécution du contrôle.
	 * doit être impérativement fournie au Format 
	 * des dates-heures françaises lexicographique 
	 * avec millisecondes comme
	 * '1961-01-25_12-27-07-251'.<br/>
	 * "yyyy-MM-dd_HH-mm-ss-SSS".<br/>
	 * @param pUserName : String : nom de l'utilisateur 
	 * qui a déclenché le contrôle.<br/> 
	 * @param pNomFichier : String : nom du fichier 
	 * objet du contrôle.<br/>
	 * @param pTypeControle : String : type du contrôle 
	 * (contrôle de surface par exemple).<br/> 
	 * @param pNomControle : String : nom du contrôle 
	 * ('contrôle fichier texte' par exemple).<br/>
	 * @param pCritere : String : désignation du critère vérifié par le contrôle 
	 * comme "une ligne ne doit pas être vide" ou 
	 * "une ligne doit contenir obligatoirement 520 caractères".<br/>
	 * @param pGravite : String : désignation de la gravité de ce contrôle 
	 * (par exemple 'bloquant').<br/>
	 * @param pNumeroLigne : Integer : numéro de la ligne dans le fichier 
	 * qui déclenche le contrôle.<br/>
	 * @param pMessageControle : String : message émis par le contrôle.<br/>
	 * @param pOrdreChamp : Integer : ordre du champ contrôlé
	 * (dans un fichier 
	 * comportant une liste de champs comme un fichier ASCII HIT).<br/>
	 * @param pPositionChamp : String : position du champ contrôlé 
	 * dans une ligne du fichier 
	 * comme 7 ou [7-12].<br/>
	 * @param pValeurChamp : String : valeur prise par le champ contrôlé.<br/> 
	 * @param pStatut : Boolean : Boolean qui vaut : <br/>
	 * - true si le contrôle est favorable après un contrôle.<br/>
	 * - false si le contrôle est défavorable après un contrôle.<br/>
	 * Un contrôle doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.<br/>
	 * @param pAction : String : action menée après le contrôle 
	 * comme "ligne éliminée" ou "ligne conservée".<br/>
	 */
	public LigneRapport(
			final Long pId
			, final Integer pOrdreControle
			, final String pDateControle
			, final String pUserName
			, final String pNomFichier
			, final String pTypeControle
			, final String pNomControle
			, final String pCritere
			, final String pGravite
			, final Integer pNumeroLigne
			, final String pMessageControle
			, final Integer pOrdreChamp
			, final String pPositionChamp
			, final String pValeurChamp
			, final Boolean pStatut
			, final String pAction) {
		
		super();
		
		this.id = pId;
		this.ordreControle = pOrdreControle;
		this.dateControle = pDateControle;
		this.userName = pUserName;
		this.nomFichier = pNomFichier;
		this.typeControle = pTypeControle;
		this.nomControle = pNomControle;
		this.critere = pCritere;
		this.gravite = pGravite;
		this.numeroLigne = pNumeroLigne;
		this.messageControle = pMessageControle;
		this.ordreChamp = pOrdreChamp;
		this.positionChamp = pPositionChamp;
		this.valeurChamp = pValeurChamp;
		this.statut = pStatut;
		this.action = pAction;
		
	} // Fin de CONSTRUCTEUR COMPLET PERSISTANT.___________________________



	/**
	 * Champs (14 attributs) : <br/>
	 * [ordreControle;dateControle;userName;nomFichier;typeControle;
	 * nomControle;critere;gravite;
	 * numeroLigne;messageControle;ordreChamp;positionChamp;
	 * valeurChamp;action;]<br/>
	 * <br/>
	 * {@inheritDoc}<br/>
	 */
	@Override
	public final int hashCode() {
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result
				+ ((this.ordreControle == null) ? 0 : this.ordreControle.hashCode());
		result = prime * result
				+ ((this.action == null) ? 0 : this.action.hashCode());
		result = prime * result
				+ ((this.critere == null) ? 0 : this.critere.hashCode());
		result = prime
				* result
				+ ((this.dateControle == null) ? 0 : this.dateControle
						.hashCode());
		result = prime * result
				+ ((this.gravite == null) ? 0 : this.gravite.hashCode());
		result = prime
				* result
				+ ((this.messageControle == null) ? 0 : this.messageControle
						.hashCode());
		result = prime
				* result
				+ ((this.nomControle == null) ? 0 : this.nomControle.hashCode());
		result = prime * result
				+ ((this.nomFichier == null) ? 0 : this.nomFichier.hashCode());
		result = prime
				* result
				+ ((this.numeroLigne == null) ? 0 : this.numeroLigne.hashCode());
		result = prime * result
				+ ((this.ordreChamp == null) ? 0 : this.ordreChamp.hashCode());
		result = prime
				* result
				+ ((this.positionChamp == null) ? 0 : this.positionChamp
						.hashCode());
		result = prime
				* result
				+ ((this.typeControle == null) ? 0 : this.typeControle
						.hashCode());
		result = prime * result
				+ ((this.userName == null) ? 0 : this.userName.hashCode());
		result = prime
				* result
				+ ((this.valeurChamp == null) ? 0 : this.valeurChamp.hashCode());
		
		return result;
		
	} // Fin de hashCode().________________________________________________




	/**
	 * Champs (14 attributs) : <br/>
	 * [ordreControle;dateControle;userName;nomFichier;typeControle;
	 * nomControle;critere;gravite;
	 * numeroLigne;messageControle;ordreChamp;positionChamp;
	 * valeurChamp;action;]<br/>
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
		
		if (!(pObj instanceof LigneRapport)) {
			return false;
		}
		
		final LigneRapport other = (LigneRapport) pObj;
		
		if (this.ordreControle == null) {
			if (other.ordreControle != null) {
				return false;
			}
		} else if (!this.ordreControle.equals(other.ordreControle)) {
			return false;
		}
		
		if (this.action == null) {
			if (other.action != null) {
				return false;
			}
		} else if (!this.action.equals(other.action)) {
			return false;
		}
		
		if (this.critere == null) {
			if (other.critere != null) {
				return false;
			}
		} else if (!this.critere.equals(other.critere)) {
			return false;
		}
		
		if (this.dateControle == null) {
			if (other.dateControle != null) {
				return false;
			}
		} else if (!this.dateControle.equals(other.dateControle)) {
			return false;
		}
		
		if (this.gravite == null) {
			if (other.gravite != null) {
				return false;
			}
		} else if (!this.gravite.equals(other.gravite)) {
			return false;
		}
		
		if (this.messageControle == null) {
			if (other.messageControle != null) {
				return false;
			}
		} else if (!this.messageControle.equals(other.messageControle)) {
			return false;
		}
		
		if (this.nomControle == null) {
			if (other.nomControle != null) {
				return false;
			}
		} else if (!this.nomControle.equals(other.nomControle)) {
			return false;
		}
		
		if (this.nomFichier == null) {
			if (other.nomFichier != null) {
				return false;
			}
		} else if (!this.nomFichier.equals(other.nomFichier)) {
			return false;
		}
		
		if (this.numeroLigne == null) {
			if (other.numeroLigne != null) {
				return false;
			}
		} else if (!this.numeroLigne.equals(other.numeroLigne)) {
			return false;
		}
		
		if (this.ordreChamp == null) {
			if (other.ordreChamp != null) {
				return false;
			}
		} else if (!this.ordreChamp.equals(other.ordreChamp)) {
			return false;
		}
		
		if (this.positionChamp == null) {
			if (other.positionChamp != null) {
				return false;
			}
		} else if (!this.positionChamp.equals(other.positionChamp)) {
			return false;
		}
		
		if (this.typeControle == null) {
			if (other.typeControle != null) {
				return false;
			}
		} else if (!this.typeControle.equals(other.typeControle)) {
			return false;
		}
		
		if (this.userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!this.userName.equals(other.userName)) {
			return false;
		}
		
		if (this.valeurChamp == null) {
			if (other.valeurChamp != null) {
				return false;
			}
		} else if (!this.valeurChamp.equals(other.valeurChamp)) {
			return false;
		}
		
		return true;
		
	} // Fin de equals(...)._______________________________________________



	/**
	 *  champs (14 attributs dans un ordre différent du equals) :<br/>
	 * [ordreControle;dateControle;userName;nomFichier;typeControle;
	 * nomControle;critere;gravite;
	 * numeroLigne;ordreChamp;positionChamp;
	 * valeurChamp;action;messageControle;]<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final LigneRapport pObject) {
		
		/* Même instance : 0. */
		if (this == pObject) {
			return 0;
		}
		
		/* Paramètre null : -1. */
		if (pObject == null) {
			return -1;
		}
				
		/* Cast. */
		final LigneRapport other = pObject;
		
		/* Parse les String en dates pour la comparaison. */
		Date maDate = null;
		Date otherDate = null;
		
		final String maDateString = this.getDateControle();
		final String otherDateString = other.getDateControle();
		
		try {
			
			if (maDateString != null) {
				maDate 
					= this.dfDatetimemilliFrancaiseLexico.parse(maDateString);
			}
		} catch (ParseException parseExc1) {
			
			final String message 
			= "la date " 
			+ maDateString 
			+ " n'est pas conforme au pattern 'yyyy-MM-dd_HH-mm-ss-SSS' : ";
			
			/* LOG de niveau INFO. */
			loggerInfo(
					CLASSE_LIGNE_RAPPORT
					, METHODE_COMPARE, message, parseExc1.getMessage());
		}
		
		try {
			
			if (otherDateString != null) {
				otherDate 
					= this.dfDatetimemilliFrancaiseLexico.parse(otherDateString);
			}
			
		} catch (ParseException parseExc2) {
			
			final String message 
			= "la date " 
			+ otherDateString 
			+ " n'est pas conforme au pattern 'yyyy-MM-dd_HH-mm-ss-SSS' : ";
			
			/* LOG de niveau INFO. */
			loggerInfo(
					CLASSE_LIGNE_RAPPORT
					, METHODE_COMPARE, message, parseExc2.getMessage());
		}
		
		int compareOrdreControle = 0;
		int compareDateControle = 0;
		int compareUserName = 0;
		int compareNomFichier = 0;
		int compareTypeControle = 0;
		int compareNomControle = 0;
		int compareCritere = 0;
		int compareGravite = 0;
		int compareNumeroLigne = 0;
		int compareOrdreChamp = 0;
		int comparePositionChamp = 0;
		int compareValeurChamp = 0;
		int compareAction = 0;
		int compareMessageControle = 0;
		
		/* 1 - ordreControle. */
		if (this.ordreControle == null) {
			
			if (other.ordreControle != null) {
				return +1;
			}
		}
		else {
			
			if (other.ordreControle == null) {
				return -1;
			}
			
			compareOrdreControle 
				= this.ordreControle.compareTo(other.ordreControle);
			
			if (compareOrdreControle != 0) {
				return compareOrdreControle;
			}
			
		} // Fin de ordreControle.________________
		
		/* 2 - dateControle. */
		if (maDate == null) {
			
			if (otherDate != null) {
				return +1;
			}
		}
		else {
			
			if (otherDate == null) {
				return -1;
			}
			
			compareDateControle = maDate.compareTo(otherDate);
			
			if (compareDateControle != 0) {
				return compareDateControle;
			}
			
		} // Fin de dateControle.______
		
		/* 3 - userName. */
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
		
		/* 5 - typeControle. */
		if (this.getTypeControle() == null) {
			
			if (other.getTypeControle() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getTypeControle() == null) {
				return -1;
			}
			
			compareTypeControle = this.getTypeControle()
					.compareTo(other.getTypeControle());
			
			if (compareTypeControle != 0) {
				return compareTypeControle;
			}
		} // Fin de typeControle.___________________
		
		/* 6 - nomControle. */
		if (this.getNomControle() == null) {
			
			if (other.getNomControle() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getNomControle() == null) {
				return -1;
			}
			
			compareNomControle 
				= this.getNomControle().compareTo(other.getNomControle());
			
			if (compareNomControle != 0) {
				return compareNomControle;
			}
		} // Fin de nomControle.___________________
		
		/* 7 - critere. */
		if (this.getCritere() == null) {
			
			if (other.getCritere() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getCritere() == null) {
				return -1;
			}
			
			compareCritere = this.getCritere().compareTo(other.getCritere());
			
			if (compareCritere != 0) {
				return compareCritere;
			}
		} // Fin de critere._____________________
		
		
		/* 8 - gravite. */
		if (this.getGravite() == null) {
			
			if (other.getGravite() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getGravite() == null) {
				return -1;
			}
			
			compareGravite 
				= this.getGravite().compareTo(other.getGravite());
			
			if (compareGravite != 0) {
				return compareGravite;
			}
		} // Fin de gravite.____________________
		
		/* 9 - numeroLigne. */
		if (this.getNumeroLigne() == null) {
			
			if (other.getNumeroLigne() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getNumeroLigne() == null) {
				return -1;
			}
			
			compareNumeroLigne 
				= this.getNumeroLigne().compareTo(other.getNumeroLigne());
			
			if (compareNumeroLigne != 0) {
				return compareNumeroLigne;
			}
		} // Fin de numeroLigne.__________________
		
		/* 10 - ordreChamp. */
		if (this.getOrdreChamp() == null) {
			
			if (other.getOrdreChamp() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getOrdreChamp() == null) {
				return -1;
			}
			
			compareOrdreChamp 
				= this.getOrdreChamp().compareTo(other.getOrdreChamp());
			
			if (compareOrdreChamp != 0) {
				return compareOrdreChamp;
			}
		} // Fin de ordreChamp._______________________
		
		/* 11 - positionChamp. */
		if (this.getPositionChamp() == null) {
			
			if (other.getPositionChamp() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getPositionChamp() == null) {
				return -1;
			}
			
			comparePositionChamp 
				= this.getPositionChamp().compareTo(other.getPositionChamp());
			
			if (comparePositionChamp != 0) {
				return comparePositionChamp;
			}
		} // Fin de positionChamp._____________________
		
		/* 12 - valeurChamp. */
		if (this.getValeurChamp() == null) {
			
			if (other.getValeurChamp() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getValeurChamp() == null) {
				return -1;
			}
			
			compareValeurChamp 
				= this.getValeurChamp().compareTo(other.getValeurChamp());
			
			if (compareValeurChamp != 0) {
				return compareValeurChamp;
			}
		} // Fin de valeurChamp.__________________________
		
		/* 13 - action. */
		if (this.getAction() == null) {
			
			if (other.getAction() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getAction() == null) {
				return -1;
			}
			
			compareAction 
				= this.getAction().compareTo(other.getAction());
			
			if (compareAction != 0) {
				return compareAction;
			}
		} //Fin de action.__________________________________
		
		/* 14 - messageControle. */
		if (this.getMessageControle() == null) {
			
			if (other.getMessageControle() != null) {
				return +1;
			}
			
			/* retourne 0 si le dernier attribut 
			 * est null également dans other. */
			return 0;
		}
		
		compareMessageControle 
			= this.getMessageControle().compareTo(other.getMessageControle());
		
		/* retourne la comparaison entre les 
		 * derniers attributs quoi qu'il arrive. */
		return compareMessageControle;
		
	} // fin de compareTo()._______________________________________________

	
	
	/**
	 * Champs (13 attributs + id) : <br/>
	 * [id;dateControle;userName;nomFichier;typeControle;
	 * nomControle;critere;gravite;
	 * numeroLigne;messageControle;ordreChamp;positionChamp;
	 * valeurChamp;action;]<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final Object clone() throws CloneNotSupportedException {
		
		final LigneRapport clone = (LigneRapport) super.clone();
		
		clone.setId(this.id);
		clone.setOrdreControle(this.ordreControle);
		clone.setDateControle(this.dateControle);
		clone.setUserName(this.userName);
		clone.setNomFichier(this.nomFichier);
		clone.setTypeControle(this.typeControle);
		clone.setNomControle(this.nomControle);
		clone.setCritere(this.critere);
		clone.setGravite(this.gravite);
		clone.setNumeroLigne(this.numeroLigne);
		clone.setMessageControle(this.messageControle);
		clone.setOrdreChamp(this.ordreChamp);
		clone.setPositionChamp(this.positionChamp);
		clone.setValeurChamp(this.valeurChamp);
		clone.setStatut(this.statut);
		clone.setAction(this.action);
		
		return clone;
		
	} // Fin de clone().___________________________________________________
	
	
		
	/**
	 * Sert à afficher à la console un LigneRapport.<br/>
	 * <br/>
	 * Champs (15 attributs + id) : <br/>
	 * [id;ordreControle;dateControle;userName;nomFichier;typeControle;
	 * nomControle;critere;gravite;
	 * numeroLigne;messageControle;ordreChamp;positionChamp;
	 * valeurChamp;statut;action;].<br/>
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
		
		/* 1 - ordreControle. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Ordre du contrôle : %-5d", this.ordreControle));
		
		/* 2 - dateControle. */		
		builder.append(
					String.format(LOCALE_FR_FR
							, "Date du contrôle : %-27s", this.dateControle));
				
		/* 3 - userName */
		builder.append(
				String.format(LOCALE_FR_FR
						, "User : %-30s", this.userName));
		
		/* 4 - nomFichier. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Fichier contrôlé : %-70s", this.nomFichier));
		
		/* 5 - typeControle. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Type de Contrôle : %-40s", this.typeControle));
		
		/* 6 - nomControle. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Nom du Contrôle : %-70s", this.nomControle));
		
		/* 7 - critere. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Critère de Contrôle : %-110s", this.critere));
		
		/* 8 - gravite. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Gravité du Contrôle : %-30s", this.gravite));
		
		/* 9 - numeroLigne. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Numéro de ligne : %-10d", this.numeroLigne));
		
		/* 10 - messageControle. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Message du Contrôle : %-350s"
							, this.messageControle));
		
		/* 11 - ordreChamp. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Ordre du champ contrôlé dans une ligne  : %-10d"
							, this.ordreChamp));
		
		/* 12 - positionChamp. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Position du champ dans la ligne : %-20s"
							, this.positionChamp));
		
		/* 13 - valeurChamp. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Valeur du champ : %-20s"
							, this.valeurChamp));
		
		/* 14 - statut. */
		if (this.statut) {
			builder.append(
					String.format(LOCALE_FR_FR
							, "Statut du contrôle : %-5s"
								, "OK"));			
		}
		else {
			builder.append(
					String.format(LOCALE_FR_FR
							, "Statut du contrôle : %-5s"
								, "KO"));
		}
		
		/* 15 - action. */
		builder.append(
				String.format(LOCALE_FR_FR
						, "Action réalisée : %-20s"
							, this.action));
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * retourne : <br/>
	 * "id;ordre d'execution du contrôle;date du contrôle;utilisateur;
	 * Fichier;type de contrôle;Contrôle;Critère;Gravité;
	 * Numéro de Ligne;Message du Contrôle;Ordre du Champ;Position du Champ;
	 * Valeur du Champ;Statut du Contrôle;Action;"<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteCsv() {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append("id;");
		stb.append("ordre d'execution du contrôle;");
		stb.append("date du contrôle;");
		stb.append("utilisateur;");
		stb.append("Fichier;");
		stb.append("type de contrôle;");
		stb.append("Contrôle;");
		stb.append("Critère du Contrôle;");
		stb.append("Gravité du Contrôle;");
		stb.append("Numéro de Ligne;");
		stb.append("Message du Contrôle;");
		stb.append("Ordre du Champ;");
		stb.append("Position du Champ;");
		stb.append("Valeur du Champ;");
		stb.append("Statut du Contrôle;");
		stb.append("Action;");
		
		return stb.toString();
		
	} // Fin de getEnTeteCsv().____________________________________________



	/**
	 * Fournit le ValueObject sous forme de ligne Csv.<br/>
	 * <br/>
	 * "id;ordreControle;dateControle;userName;nomFichier;typeControle;
	 * nomControle;critere;gravite;
	 * numeroLigne;messageControle;ordreChamp;
	 * positionChamp;valeurChamp;statut;action;".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirStringCsv() {
		
		final StringBuilder stb = new StringBuilder();
		
		/* id. */
		stb.append(this.id);
		stb.append(SEP_POINTVIRGULE);
		
		/* ordreControle. */
		stb.append(this.ordreControle);
		stb.append(SEP_POINTVIRGULE);
		
		/* dateControle. */
		stb.append(this.dateControle);
		stb.append(SEP_POINTVIRGULE);
		
		/* userName. */
		stb.append(this.userName);
		stb.append(SEP_POINTVIRGULE);
		
		/* nomFichier. */
		stb.append(this.nomFichier);
		stb.append(SEP_POINTVIRGULE);
		
		/* typeControle. */
		stb.append(this.typeControle);
		stb.append(SEP_POINTVIRGULE);
		
		/* nomControle. */
		stb.append(this.nomControle);
		stb.append(SEP_POINTVIRGULE);
		
		/* critere. */
		stb.append(this.critere);
		stb.append(SEP_POINTVIRGULE);
		
		/* gravite. */
		stb.append(this.gravite);
		stb.append(SEP_POINTVIRGULE);
		
		/* numeroLigne. */
		if (this.numeroLigne == null) {
			stb.append("sans objet");
		}
		else {
			stb.append(this.numeroLigne);
		}		
		stb.append(SEP_POINTVIRGULE);
		
		/* messageControle. */
		stb.append(this.messageControle);
		stb.append(SEP_POINTVIRGULE);
		
		/* ordreChamp. */
		if (this.ordreChamp == null) {
			stb.append("sans objet");
		}
		else {
			stb.append(this.ordreChamp);
		}		
		stb.append(SEP_POINTVIRGULE);
		
		/* positionChamp. */
		stb.append(this.positionChamp);
		stb.append(SEP_POINTVIRGULE);
		
		/* valeurChamp. */
		stb.append(this.valeurChamp);
		stb.append(SEP_POINTVIRGULE);
		
		/* statut. */
		if (this.statut) {
			stb.append("OK");
		}
		else {
			stb.append("KO");
		}		
		stb.append(SEP_POINTVIRGULE);
		
		/* action. */
		stb.append(this.action);
		stb.append(SEP_POINTVIRGULE);
		
		return stb.toString();
		
	} // Fin de toCsv().___________________________________________________
	
	

	/**
	 * "id;ordre d'execution du contrôle;date du contrôle;utilisateur;
	 * Fichier;type de contrôle;Contrôle;Critère;Gravité;
	 * Numéro de Ligne;Message du Contrôle;Ordre du Champ;Position du Champ;
	 * Valeur du Champ;Statut du Contrôle;Action;"<br/>
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
			entete = "ordre d'execution du contrôle";
			break;
			
		case 2:
			entete = "date du contrôle";
			break;

		case 3:
			entete = "utilisateur";
			break;

		case 4:
			entete = "Fichier";
			break;
			
		case 5:
			entete = "type de contrôle";
			break;
			
		case 6:
			entete = "Contrôle";
			break;
			
		case 7:
			entete = "Critère du Contrôle";
			break;
			
		case 8:
			entete = "Gravité du Contrôle";
			break;
			
		case 9:
			entete = "Numéro de Ligne";
			break;
			
		case 10:
			entete = "Message du Contrôle";
			break;
			
		case 11:
			entete = "Ordre du Champ";
			break;
			
		case 12:
			entete = "Position du Champ";
			break;
			
		case 13:
			entete = "Valeur du Champ";
			break;
			
		case 14:
			entete = "Statut du Contrôle";
			break;
			
		case 15:
			entete = "Action";
			break;
			
		default:
			entete = "invalide";
			break;
			
		} // Fin du Switch._________________________________
		
		return entete;
		
	} // Fin de getEnTeteColonne(...)._____________________________________



	/**
	 * "id;ordreControle;dateControle;userName;nomFichier;typeControle;
	 * nomControle;critere;gravite;
	 * numeroLigne;messageControle;ordreChamp;
	 * positionChamp;valeurChamp;statut;action;".<br/>
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
			valeur = this.ordreControle;
			break;
			
		case 2:
			valeur = this.dateControle;
			break;

		case 3:
			valeur = this.userName;
			break;

		case 4:
			valeur = this.nomFichier;
			break;
			
		case 5:
			valeur = this.typeControle;
			break;
			
		case 6:
			valeur = this.nomControle;
			break;
			
		case 7:
			valeur = this.critere;
			break;
			
		case 8:
			valeur = this.gravite;
			break;
			
		case 9:
			if (this.numeroLigne == null) {
				valeur = "sans objet";
			}
			else {
				valeur = this.numeroLigne;
			}			
			break;
			
		case 10:
			valeur = this.messageControle;
			break;
			
		case 11:
			if (this.ordreChamp == null) {
				valeur = "sans objet";
			}
			else {
				valeur = this.ordreChamp;
			}			
			break;
			
		case 12:
			valeur = this.positionChamp;
			break;
			
		case 13:
			valeur = this.valeurChamp;
			break;
		
		case 14:
			valeur = this.statut;
			break;
			
		case 15:
			valeur = this.action;
			break;
			
		default:
			valeur = "invalide";
			break;
			
		} // Fin du Switch._________________________________
		
		return valeur;
		
	} // Fin de getValeurColonne(...)._____________________________________


	
	/**
	 * "id;ordreControle;dateControle;userName;nomFichier;typeControle;
	 * nomControle;critere;gravite;
	 * numeroLigne;messageControle;ordreChamp;
	 * positionChamp;valeurChamp;statut;action;".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final void reset() {
		
		this.id = null;
		this.ordreControle = null;
		this.dateControle = null;
		this.userName = null;
		this.nomFichier = null;
		this.typeControle = null;
		this.nomControle = null;
		this.critere = null;
		this.gravite = null;
		this.numeroLigne = null;
		this.messageControle = null;
		this.ordreChamp = null;
		this.positionChamp = null;
		this.valeurChamp = null;
		this.statut = null;
		this.action = null;
		
	} // Fin de reset().___________________________________________________


	
	/**
	 * method remplir(....) :<br/>
	 * Méthode à utiliser avec reset() pour éviter d'instancier 
	 * un nouvel objet à chaque itération d'une boucle.<br/>
	 * <br/>
	 *
	 * @param pId : Long : Identifiant en base de l'objet métier.<br/>
	 * @param pOrdreControle : Integer : ordre d'exécution 
	 * du contrôle dans un enchaînement de contrôles.<br/>
	 * @param pDateControle : String : date d'exécution du contrôle.
	 * doit être impérativement fournie au 
	 * Format des dates-heures françaises lexicographique 
	 * avec millisecondes comme
	 * '1961-01-25_12-27-07-251'.<br/>
	 * "yyyy-MM-dd_HH-mm-ss-SSS".<br/>
	 * @param pUserName : String : nom de l'utilisateur 
	 * qui a déclenché le contrôle.<br/> 
	 * @param pNomFichier : String : nom du fichier 
	 * objet du contrôle.<br/>
	 * @param pTypeControle : String : type du contrôle 
	 * (contrôle de surface par exemple).<br/> 
	 * @param pNomControle : String : nom du contrôle 
	 * ('contrôle fichier texte' par exemple).<br/>
	 * @param pCritere : String : désignation du critère vérifié par le contrôle 
	 * comme "une ligne ne doit pas être vide" ou 
	 * "une ligne doit contenir obligatoirement 520 caractères".<br/>
	 * @param pGravite : String : désignation de la gravité de ce contrôle 
	 * (par exemple 'bloquant').<br/>
	 * @param pNumeroLigne : Integer : numéro de la ligne dans le fichier 
	 * qui déclenche le contrôle.<br/>
	 * @param pMessageControle : String : message émis par le contrôle.<br/>
	 * @param pOrdreChamp : Integer : ordre du champ contrôlé
	 * (dans un fichier 
	 * comportant une liste de champs comme un fichier ASCII HIT).<br/>
	 * @param pPositionChamp : String : position du champ contrôlé 
	 * dans une ligne du fichier 
	 * comme 7 ou [7-12].<br/>
	 * @param pValeurChamp : String : valeur prise par le champ contrôlé.<br/> 
	 * @param pStatut : Boolean : Boolean qui vaut : <br/>
	 * - true si le contrôle est favorable après un contrôle.<br/>
	 * - false si le contrôle est défavorable après un contrôle.<br/>
	 * Un contrôle doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.<br/>
	 * @param pAction : String : action menée après le contrôle 
	 * comme "ligne éliminée" ou "ligne conservée".<br/>
	 */
	public final void remplir(
			final Long pId
			, final Integer pOrdreControle
			, final String pDateControle
			, final String pUserName
			, final String pNomFichier
			, final String pTypeControle
			, final String pNomControle
			, final String pCritere
			, final String pGravite
			, final Integer pNumeroLigne
			, final String pMessageControle
			, final Integer pOrdreChamp
			, final String pPositionChamp
			, final String pValeurChamp
			, final Boolean pStatut
			, final String pAction) {
		
		this.id = pId;
		this.ordreControle = pOrdreControle;
		this.dateControle = pDateControle;
		this.userName = pUserName;
		this.nomFichier = pNomFichier;
		this.typeControle = pTypeControle;
		this.nomControle = pNomControle;
		this.critere = pCritere;
		this.gravite = pGravite;
		this.numeroLigne = pNumeroLigne;
		this.messageControle = pMessageControle;
		this.ordreChamp = pOrdreChamp;
		this.positionChamp = pPositionChamp;
		this.valeurChamp = pValeurChamp;
		this.statut = pStatut;
		this.action = pAction;
		
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
			
			final String message 
			= pClasse 
			+ SEP_MOINS
			+ pMethode
			+ SEP_MOINS
			+ pMessage
			+ pComplement;
			
			LOG.info(message);
		}
		
	} // Fin de loggerInfo(
	 // String pClasse
	 // , String pMethode
	 // , String pMessage
	 // , String pComplement)._____________________________________________

	

	/**
	 * method getId() :<br/>
	 * Getter de l'identifiant en base.<br/>
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
	 * @param pId : Long.<br/>
	 */
	public final void setId(
			final Long pId) {
		this.id = pId;
	} // Fin de setId(
	 // Long pId)._________________________________________________________



	/**
	 * method getOrdreControle() :<br/>
	 * Getter de l' ordre d'exécution du contrôle 
	 * dans un enchaînement de contrôles.<br/>
	 * <br/>
	 *
	 * @return ordreControle : Integer.<br/>
	 */
	public final Integer getOrdreControle() {
		return this.ordreControle;
	} // Fin de getOrdreControle().________________________________________



	/**
	 * method setOrdreControle(
	 * Integer pOrdreControle) :<br/>
	 * Setter de l' ordre d'exécution du contrôle 
	 * dans un enchaînement de contrôles.<br/>
	 * <br/>
	 *
	 * @param pOrdreControle : Integer : 
	 * valeur à passer à ordreControle.<br/>
	 */
	public final void setOrdreControle(
			final Integer pOrdreControle) {
		this.ordreControle = pOrdreControle;
	} // Fin de setOrdreControle(
	 // Integer pOrdreControle).___________________________________________


	
	/**
	 * method getDateControle() :<br/>
	 * Getter de la date d'exécution du contrôle.<br/>
	 * doit être impérativement fournie au 
	 * Format des dates-heures françaises lexicographique 
	 * avec millisecondes comme
	 * '1961-01-25_12-27-07-251'.<br/>
	 * "yyyy-MM-dd_HH-mm-ss-SSS".<br/>
	 * <br/>
	 *
	 * @return dateControle : String.<br/>
	 */
	public final String getDateControle() {
		return this.dateControle;
	} // Fin de getDateControle()._________________________________________
	
	

	/**
	 * method setDateControle(
	 * String pDateControle) :<br/>
	 * Setter de la date d'exécution du contrôle.<br/>
	 * doit être impérativement fournie au 
	 * Format des dates-heures françaises lexicographique 
	 * avec millisecondes comme
	 * '1961-01-25_12-27-07-251'.<br/>
	 * "yyyy-MM-dd_HH-mm-ss-SSS".<br/>
	 * <br/>
	 *
	 * @param pDateControle : String : 
	 * valeur à passer à dateControle.<br/>
	 */
	public final void setDateControle(
			final String pDateControle) {
		this.dateControle = pDateControle;
	} // Fin de setDateControle(
	 // String pDateControle)._____________________________________________



	/**
	 * method getUserName() :<br/>
	 * Getter du nom de l'utilisateur qui a déclenché le contrôle.<br/>
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
	 * Setter du nom de l'utilisateur qui a déclenché le contrôle.<br/>
	 * <br/>
	 *
	 * @param pUserName : String : 
	 * valeur à passer à userName.<br/>
	 */
	public final void setUserName(
			final String pUserName) {
		this.userName = pUserName;
	} // Fin de setUserName(
	 // String pUserName)._________________________________________________



	/**
	 * method getNomFichier() :<br/>
	 * Getter du nom du fichier objet du contrôle.<br/>
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
	 * Setter du nom du fichier objet du contrôle.<br/>
	 * <br/>
	 *
	 * @param pNomFichier : String : 
	 * valeur à passer à nomFichier.<br/>
	 */
	public final void setNomFichier(
			final String pNomFichier) {
		this.nomFichier = pNomFichier;
	} // Fin de setNomFichier(
	 // String pNomFichier)._______________________________________________



	/**
	 * method getTypeControle() :<br/>
	 * Getter du type du contrôle (contrôle de surface par exemple).<br/>
	 * <br/>
	 *
	 * @return typeControle : String.<br/>
	 */
	public final String getTypeControle() {
		return this.typeControle;
	} // Fin de getTypeControle()._________________________________________



	/**
	 * method setTypeControle(
	 * String pTypeControle) :<br/>
	 * Setter du type du contrôle (contrôle de surface par exemple).<br/>
	 * <br/>
	 *
	 * @param pTypeControle : String : 
	 * valeur à passer à typeControle.<br/>
	 */
	public final void setTypeControle(
			final String pTypeControle) {
		this.typeControle = pTypeControle;
	} // Fin de setTypeControle(
	 // String pTypeControle)._____________________________________________



	/**
	 * method getNomControle() :<br/>
	 * Getter du nom du contrôle (contrôle fichier texte par exemple).<br/>
	 * <br/>
	 *
	 * @return nomControle : String.<br/>
	 */
	public final String getNomControle() {
		return this.nomControle;
	} // Fin de getNomControle().__________________________________________



	/**
	 * method setNomControle(
	 * String pNomControle) :<br/>
	 * Setter du nom du contrôle (contrôle fichier texte par exemple).<br/>
	 * <br/>
	 *
	 * @param pNomControle : String : 
	 * valeur à passer à nomControle.<br/>
	 */
	public final void setNomControle(
			final String pNomControle) {
		this.nomControle = pNomControle;
	} // Fin de setNomControle(
	 // String pNomControle).______________________________________________



	/**
	 * method getCritere() :<br/>
	 * Getter de la désignation du critère vérifié par le contrôle 
	 * comme "une ligne ne doit pas être vide" ou 
	 * "une ligne doit contenir obligatoirement 520 caractères".<br/>
	 * <br/>
	 *
	 * @return critere : String.<br/>
	 */
	public final String getCritere() {
		return this.critere;
	} // Fin de getCritere().______________________________________________



	/**
	 * method setCritere(
	 * String pCritere) :<br/>
	 * Setter de la désignation du critère vérifié par le contrôle 
	 * comme "une ligne ne doit pas être vide" ou 
	 * "une ligne doit contenir obligatoirement 520 caractères".<br/>
	 * <br/>
	 *
	 * @param pCritere : String : valeur à passer à critere.<br/>
	 */
	public final void setCritere(
			final String pCritere) {
		this.critere = pCritere;
	} // Fin de setCritere(
	 // String pCritere).__________________________________________________



	/**
	 * method getGravite() :<br/>
	 * Getter de la désignation de la gravité de ce contrôle 
	 * (par exemple bloquant).<br/>
	 * <br/>
	 *
	 * @return gravite : String.<br/>
	 */
	public final String getGravite() {
		return this.gravite;
	} // Fin de getGravite().______________________________________________



	/**
	 * method setGravite(
	 * String pGravite) :<br/>
	 * Setter de la désignation de la gravité de ce contrôle 
	 * (par exemple bloquant).<br/>
	 * <br/>
	 *
	 * @param pGravite : String : valeur à passer à gravite.<br/>
	 */
	public final void setGravite(
			final String pGravite) {
		this.gravite = pGravite;
	} // Fin de setGravite(
	 // String pGravite).__________________________________________________



	/**
	 * method getNumeroLigne() :<br/>
	 * Getter du numéro de la ligne dans le fichier 
	 * qui déclenche le contrôle.<br/>
	 * <br/>
	 *
	 * @return numeroLigne : Integer.<br/>
	 */
	public final Integer getNumeroLigne() {
		return this.numeroLigne;
	} // Fin de getNumeroLigne().__________________________________________



	/**
	 * method setNumeroLigne(
	 * Integer pNumeroLigne) :<br/>
	 * Setter du numéro de la ligne dans le fichier 
	 * qui déclenche le contrôle.<br/>
	 * <br/>
	 *
	 * @param pNumeroLigne : Integer : 
	 * valeur à passer à numeroLigne.<br/>
	 */
	public final void setNumeroLigne(
			final Integer pNumeroLigne) {
		this.numeroLigne = pNumeroLigne;
	} // Fin de setNumeroLigne(
	 // Integer pNumeroLigne)._____________________________________________



	/**
	 * method getMessageControle() :<br/>
	 * Getter du message émis par le contrôle.<br/>
	 * <br/>
	 *
	 * @return messageControle : String.<br/>
	 */
	public final String getMessageControle() {
		return this.messageControle;
	} // Fin de getMessageControle().______________________________________



	/**
	 * method setMessageControle(
	 * String pMessageControle) :<br/>
	 * Setter du message émis par le contrôle.<br/>
	 * <br/>
	 *
	 * @param pMessageControle : String : 
	 * valeur à passer à messageControle.<br/>
	 */
	public final void setMessageControle(
			final String pMessageControle) {
		this.messageControle = pMessageControle;
	} // Fin de setMessageControle(
	 // String pMessageControle).__________________________________________



	/**
	 * method getOrdreChamp() :<br/>
	 * Getter de l'ordre du champ contrôlé (
	 * dans un fichier comportant une liste de champs 
	 * comme un fichier ASCII HIT).<br/>
	 * <br/>
	 *
	 * @return ordreChamp : Integer.<br/>
	 */
	public final Integer getOrdreChamp() {
		return this.ordreChamp;
	} // Fin de getOrdreChamp().___________________________________________



	/**
	 * method setOrdreChamp(
	 * Integer pOrdreChamp) :<br/>
	 * Setter de l'ordre du champ contrôlé (
	 * dans un fichier comportant une liste de champs 
	 * comme un fichier ASCII HIT).<br/>
	 * <br/>
	 *
	 * @param pOrdreChamp : Integer : valeur à passer à ordreChamp.<br/>
	 */
	public final void setOrdreChamp(
			final Integer pOrdreChamp) {
		this.ordreChamp = pOrdreChamp;
	} // Fin de setOrdreChamp(
	 // Integer pOrdreChamp).______________________________________________



	/**
	 * method getPositionChamp() :<br/>
	 * Getter de la position du champ contrôlé dans une ligne du fichier 
	 * comme 7 ou [7-12].<br/>
	 * <br/>
	 *
	 * @return positionChamp : String.<br/>
	 */
	public final String getPositionChamp() {
		return this.positionChamp;
	} // Fin de getPositionChamp().________________________________________



	/**
	 * method setPositionChamp(
	 * String pPositionChamp) :<br/>
	 * Setter de la position du champ contrôlé dans une ligne du fichier 
	 * comme 7 ou [7-12].<br/>
	 * <br/>
	 *
	 * @param pPositionChamp : String : valeur à passer à positionChamp.<br/>
	 */
	public final void setPositionChamp(
			final String pPositionChamp) {
		this.positionChamp = pPositionChamp;
	} // Fin de setPositionChamp(
	 // String pPositionChamp).____________________________________________



	/**
	 * method getValeurChamp() :<br/>
	 * Getter de la valeur prise par le champ contrôlé.<br/>
	 * <br/>
	 *
	 * @return valeurChamp : String.<br/>
	 */
	public final String getValeurChamp() {
		return this.valeurChamp;
	} // Fin de getValeurChamp().__________________________________________



	/**
	 * method setValeurChamp(
	 * String pValeurChamp) :<br/>
	 * Setter de la valeur prise par le champ contrôlé.<br/>
	 * <br/>
	 *
	 * @param pValeurChamp : String : valeur à passer à valeurChamp.<br/>
	 */
	public final void setValeurChamp(
			final String pValeurChamp) {
		this.valeurChamp = pValeurChamp;
	} // Fin de setValeurChamp(
	 // String pValeurChamp).______________________________________________



	/**
	 * method getStatut() :<br/>
	 * Getter du Boolean qui vaut : <br/>
	 * - true si le contrôle est favorable après un contrôle.<br/>
	 * - false si le contrôle est défavorable après un contrôle.<br/>
	 * Un contrôle doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.<br/>
	 * <br/>
	 *
	 * @return statut : Boolean.<br/>
	 */
	public final Boolean getStatut() {
		return this.statut;
	} // Fin de getStatut().________________________________________________



	/**
	 * method setStatut(
	 * Boolean pStatut) :<br/>
	 * Setter du Boolean qui vaut : <br/>
	 * - true si le contrôle est favorable après un contrôle.<br/>
	 * - false si le contrôle est défavorable après un contrôle.<br/>
	 * Un contrôle doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.<br/>
	 * <br/>
	 *
	 * @param pStatut : Boolean : valeur à passer à statut.<br/>
	 */
	public final void setStatut(
			final Boolean pStatut) {
		this.statut = pStatut;
	} // Fin de setStatut(
	 // Boolean pStatut).__________________________________________________



	/**
	 * method getAction() :<br/>
	 * Getter de l'action menée après le contrôle 
	 * comme "ligne éliminée" ou "ligne conservée".<br/>
	 * <br/>
	 *
	 * @return action : String.<br/>
	 */
	public final String getAction() {
		return this.action;
	} // Fin de getAction()._______________________________________________



	/**
	 * method setAction(
	 * String pAction) :<br/>
	 * Setter de l'action menée après le contrôle 
	 * comme "ligne éliminée" ou "ligne conservée".<br/>
	 * <br/>
	 *
	 * @param pAction : String : valeur à passer à action.<br/>
	 */
	public final void setAction(
			final String pAction) {
		this.action = pAction;
	} // Fin de setAction(
	 // String pAction).___________________________________________________


	
} // FIN DE LA CLASSE LigneRapport.------------------------------------------
