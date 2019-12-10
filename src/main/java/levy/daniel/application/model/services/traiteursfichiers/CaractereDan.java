package levy.daniel.application.model.services.traiteursfichiers;

import java.io.Serializable;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



/**
 * CLASSE CaractereDan :<br/>
 * <p>Encapsulation permettant de stocker 
 * toutes les grandeurs intéressantes d'un caractère.</p>
 * 
 * <p>"id;Position;Caractère;Unicode;numericValue;Type de Caractère;
 * Valeur Entière;Point de Code Décimal;
 * Point de Code HexaDécimal;Nom Unicode;".</p>
 * 
 * <p>Possède un constructeur CaractereDan(Character pChar) 
 * à remplissage automatique qui prend en paramètre 
 * un Character et déduit toutes ses valeurs (Unicode, Point de code, ...).</p>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>
 * // Instanciation d'un CaractereDan pour le Character 'é'.<br/>
 * // Le Constructeur à remplissage automatique calcule les valeurs : <br/>
 * final CaractereDan caracEAigu = new CaractereDan('é');<br/>
 * caracEAiguAuto1.toString() retourne "id : 454    Position : 454    Caractère : é     Unicode : \u00e9  NumericValue : -1    TypeCaractere : 2   valeurEntiere : 233   Point de Code décimal : 233   Point de Code Hexa : e9      Nom : LATIN SMALL LETTER E WITH ACUTE         ".<br/>
 * </code>
 *<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * char, Character, <br/>
 * Unicode, codePoint, point de code,<br/>
 * String.format, <br/>
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
 * @since 6 févr. 2016
 *
 */
public class CaractereDan implements 
						Serializable, Comparable<CaractereDan>, Cloneable
							, IExportateurCsv, IExportateurJTable
														, IResetable {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * serialVersionUID : long :<br/>
	 * 1L.<br/>
	 */
	private static final long serialVersionUID = 1L;
		
	
	/**
	 * id : Long :<br/>
	 * Identifiant en base de l'objet métier
	 */
	private Long id;

	
	/**
	 * position : Integer :<br/>
	 * Position du caractère dans une String.<br/>
	 */
	private Integer position;
	
	
	/**
	 * caractere : Character :<br/>
	 * caractère.<br/>
	 */
	private Character caractere;
	
	
	/**
	 * unicode : String :<br/>
	 * Unicode du caractère (\u0020...).<br/>
	 */
	private transient String unicode;
	
	
	/**
	 * numericValue : int :<br/>
	 * valeur entière que le caractère Unicode représente 
	 * fournie par Character.getNumericValue(pChar).<br/>
	 */
	private transient int numericValue;

	
	/**
	 * typeCaractere : int :<br/>
	 * valeur entière indiquant 
	 * la catégorie générale d'un caractère.<br/>
	 */
	private transient int typeCaractere;

	
	/**
	 * valeurEntiere : int :<br/>
	 * valeur entière d'un caractère retournée 
	 * par la méthode read() d'un Reader.<br/>
	 * A priori similaire au point de code décimal.<br/>
	 */
	private transient int valeurEntiere;

	
	/**
	 * codePointDecimal : int :<br/>
	 * Point de code du caractère en décimal.<br/>
	 */
	private transient int codePointDecimal;
	

	/**
	 * codePointHexa : String :<br/>
	 * Point de code du caractère en hexadécimal.<br/>
	 */
	private transient String codePointHexa;
	
	
	/**
	 * nom : String :<br/>
	 * nom Unicode du caractère.<br/>
	 * (LATIN CAPITAL LETTER E, SPACE, ...).<br/>
	 */
	private transient String nom;
	
	
	/**
	 * LOCALE_FR_FR : Locale :<br/>
	 * new Locale("fr", "FR").<br/>
	 */
	public static final Locale LOCALE_FR_FR = new Locale("fr", "FR");
	

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
	private static final Log LOG = LogFactory.getLog(CaractereDan.class);
	

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public CaractereDan() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR MALIN A REMPLISSAGE AUTOMATIQUE.<br/>
	 * - SANS id en base.<br/>
	 * - SANS position.<br/>
	 * Remplit automatiquement les valeurs du caractère.<br/>
	 *
	 * @param pChar : Character : caractère.<br/>
	 */
	public CaractereDan(final Character pChar) {
		
		this(null, null, pChar);
		
	} // Fin de CONSTRUCTEUR CaractereDan(
	 // Character pChar).__________________________________________________
	
	
	
	 /**
	 * - SANS id en base.<br/>
	 * - AVEC position.<br/>
	 * Remplit automatiquement les valeurs du caractère.<br/>
	 *
	 * @param pPosition : Integer : Position du caractère dans une String.<br/>
	 * @param pChar : Character : caractère.<br/>
	 */
	public CaractereDan(final Integer pPosition
			, final Character pChar) {
		
		this(null, pPosition, pChar);
		
	} // Fin de CONSTRUCTEUR A REMPLISSAGE_NON_PERSISTANT._________________
	
	
	
	 /**
	 * CONSTRUCTEUR A REMPLISSAGE_PERSISTANT.<br/>
	 * - AVEC id en base.<br/>
	 * - AVEC position.<br/>
	 * Calcule automatiquement les valeurs unicode dépendantes de pChar.<br/>
	 *
	 * @param pId : Long : id en base.<br/>
	 * @param pPosition : Integer : Position du caractère dans une String.<br/>
	 * @param pChar : Character : caractère.<br/>
	 */
	public CaractereDan(final Long pId
			, final Integer pPosition
			, final Character pChar) {
				
		this(pId, pPosition, pChar, null, 0, 0, 0, 0, null, null);
		
		/* Calcule automatiquement les valeurs unicode dépendantes de pChar. */
		this.remplirAuto(pChar);
		
	} // Fin de CONSTRUCTEUR A REMPLISSAGE_PERSISTANT.______________________
	
	
		
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * A utiliser sans id (pas de persistance).<br/>
	 *
	 * @param pPosition : Integer : Position du caractère dans une String.<br/>
	 * @param pCaractere : Character : caractère.<br/>
	 * @param pUnicode : String : Unicode du caractère (\u0020...).<br/>
	 * @param pNumericValue : int : valeur entière 
	 * que le caractère Unicode représente 
	 * fournie par Character.getNumericValue(pChar).<br/>
	 * @param pTypeCaractere : int : valeur entière indiquant 
	 * la catégorie générale d'un caractère.<br/>
	 * @param pValeurEntiere : int : valeur entière d'un caractère retournée 
	 * par la méthode read() d'un Reader.<br/>
	 * @param pCodePointDecimal : int : Point de code 
	 * du caractère en décimal.<br/>
	 * @param pCodePointHexa : String : Point de code du caractère 
	 * en hexadécimal.<br/> 
	 * @param pNom : String : nom Unicode du caractère.<br/>
	 */
	public CaractereDan(final Integer pPosition
			, final Character pCaractere
			, final String pUnicode
			, final int pNumericValue
			, final int pTypeCaractere
			, final int pValeurEntiere
			, final int pCodePointDecimal
			, final String pCodePointHexa
			, final String pNom) {
		
		this(null
				, pPosition, pCaractere, pUnicode, pNumericValue
				, pTypeCaractere, pValeurEntiere, pCodePointDecimal
				, pCodePointHexa, pNom);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	 /**
	 * CONSTRUCTEUR COMPLET_PERSISTANT.<br/>
	 * 
	 * @param pId : Long : id en base.<br/>
	 * @param pPosition : Integer : Position du caractère dans une String.<br/>
	 * @param pCaractere : Character : caractère.<br/>
	 * @param pUnicode : String : Unicode du caractère (\u0020...).<br/>
	 * @param pNumericValue : int : valeur entière 
	 * que le caractère Unicode représente 
	 * fournie par Character.getNumericValue(pChar).<br/>
	 * @param pTypeCaractere : int : valeur entière indiquant 
	 * la catégorie générale d'un caractère.<br/>
	 * @param pValeurEntiere : int : valeur entière d'un caractère retournée 
	 * par la méthode read() d'un Reader.<br/>
	 * @param pCodePointDecimal : int : Point de code 
	 * du caractère en décimal.<br/>
	 * @param pCodePointHexa : String : Point de code du caractère 
	 * en hexadécimal.<br/> 
	 * @param pNom : String : nom Unicode du caractère.<br/>
	 */
	public CaractereDan(final Long pId
			, final Integer pPosition
			, final Character pCaractere
			, final String pUnicode
			, final int pNumericValue
			, final int pTypeCaractere
			, final int pValeurEntiere
			, final int pCodePointDecimal
			, final String pCodePointHexa
			, final String pNom) {
		
		super();
		
		this.id = pId;
		this.position = pPosition;
		this.caractere = pCaractere;
		this.unicode = pUnicode;
		this.numericValue = pNumericValue;
		this.typeCaractere = pTypeCaractere;
		this.valeurEntiere = pValeurEntiere;
		this.codePointDecimal = pCodePointDecimal;
		this.codePointHexa = pCodePointHexa;
		this.nom = pNom;
		
	} // Fin de CONSTRUCTEUR COMPLET_PERSISTANT.___________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.position == null) ? 0 : this.position.hashCode());
		result = prime * result
				+ ((this.caractere == null) ? 0 : this.caractere.hashCode());
		result = prime * result + this.codePointDecimal;
		result = prime
				* result
				+ ((this.codePointHexa == null) ? 0 : this.codePointHexa
						.hashCode());
		result = prime * result
				+ ((this.nom == null) ? 0 : this.nom.hashCode());
		result = prime * result + this.numericValue;
		result = prime * result + this.typeCaractere;
		result = prime * result
				+ ((this.unicode == null) ? 0 : this.unicode.hashCode());
		result = prime * result + this.valeurEntiere;
		
		return result;
		
	} // Fin de hashCode().________________________________________________



	/**
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
		
		if (!(pObj instanceof CaractereDan)) {
			return false;
		}
		
		final CaractereDan other = (CaractereDan) pObj;
		
		if (this.position == null) {
			if (other.position != null) {
				return false;
			}
		} else if (!this.position.equals(other.position)) {
			return false;
		}
		
		if (this.caractere == null) {
			if (other.caractere != null) {
				return false;
			}
		} else if (!this.caractere.equals(other.caractere)) {
			return false;
		}
		
		if (this.codePointDecimal != other.codePointDecimal) {
			return false;
		}
		
		if (this.codePointHexa == null) {
			if (other.codePointHexa != null) {
				return false;
			}
		} else if (!this.codePointHexa.equals(other.codePointHexa)) {
			return false;
		}
		
		if (this.nom == null) {
			if (other.nom != null) {
				return false;
			}
		} else if (!this.nom.equals(other.nom)) {
			return false;
		}
		
		if (this.numericValue != other.numericValue) {
			return false;
		}
				
		if (this.typeCaractere != other.typeCaractere) {
			return false;
		}
		
		if (this.unicode == null) {
			if (other.unicode != null) {
				return false;
			}
		} else if (!this.unicode.equals(other.unicode)) {
			return false;
		}
		
		if (this.valeurEntiere != other.valeurEntiere) {
			return false;
		}
		
		return true;
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int compareTo(
			final CaractereDan pObject) {
		
		/* Même instance : 0. */
		if (this == pObject) {
			return 0;
		}
		
		/* Paramètre null : -1. */
		if (pObject == null) {
			return -1;
		}
		
		
		/* Cast. */
		final CaractereDan other = pObject;
		
		int compPosition = 0;
		int compCaractere = 0;
		int compareUnicode = 0;
		int compareCodePointHexa = 0;
		int compNom = 0;
		
		/* 1 - position. */
		if (this.getPosition() == null) {
			if (other.getPosition() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getPosition() == null) {
				return -1;
			}
			
			compPosition = this.getPosition().compareTo(other.getPosition());
			
			if (compPosition != 0) {
				return compPosition;
			}
		} // Fin de position.__________________
		
		/* 2 - caractère. */
		if (this.getCaractere() == null) {
			if (other.getCaractere() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getCaractere() == null) {
				return -1;
			}
			
			compCaractere = this.getCaractere().compareTo(other.getCaractere());
			
			if (compCaractere != 0) {
				return compCaractere;
			}
		} // Fin de caractère._________________________
		
		
		/* 3 - unicode. */
		if (this.getUnicode() == null) {
			if (other.getUnicode() != null) {
				return +1;
			}
		}
		else {
			
			if (other.getUnicode() == null) {
				return -1;
			}
			
			compareUnicode = this.getUnicode().compareTo(other.getUnicode());
			
			if (compareUnicode != 0) {
				return compareUnicode;
			}
		} // Fin de unicode._________________________________
		
		/* 4 - numericValue. */
		if (this.getNumericValue() < other.getNumericValue()) {
			return -1;
		} else if (this.getNumericValue() > other.getNumericValue()) {
			return +1;
		}
		
		/* 5 - typeCaractere. */
		if (this.getTypeCaractere() < other.getTypeCaractere()) {
			return -1;
		} else if (this.getTypeCaractere() > other.getTypeCaractere()) {
			return +1;
		}
		
		/* 6 - valeurEntiere. */
		if (this.getValeurEntiere() < other.getValeurEntiere()) {
			return -1;
		} else if (this.getValeurEntiere() > other.getValeurEntiere()) {
			return +1;
		}
		
		/* 7 - codePointDecimal. */
		if (this.getCodePointDecimal() < other.getCodePointDecimal()) {
			return -1;
		} else if (this.getCodePointDecimal() > other.getCodePointDecimal()) {
			return +1;
		}
		
		/* 8 - codePointHexa. */
		if (this.getCodePointHexa() == null) {
			if (other.getCodePointHexa() != null) {
				return +1;
			}
		}
		else {
			if (other.getCodePointHexa() == null) {
				return -1;
			}
			
			compareCodePointHexa 
				= this.getCodePointHexa().compareTo(other.getCodePointHexa());
			
			if (compareCodePointHexa != 0) {
				return compareCodePointHexa;
			}
		} // Fin de codePointHexa._______________________________
		
		/* 9 - nom. */
		if (this.getNom() == null) {
			
			if (other.getNom() != null) {
				return +1;
			}
			
			return 0;
		}
					
		if (other.getNom() == null) {
			return -1;
		}
		
		compNom = this.getNom().compareTo(other.getNom());
		
		return compNom;
				
	} // fin de compareTo()._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Object clone() throws CloneNotSupportedException {
		
		final CaractereDan clone = (CaractereDan) super.clone();
		
		clone.setId(this.id);
		clone.setPosition(this.position);
		clone.setCaractere(this.caractere);
		
		return clone;
		
	} // Fin de clone().___________________________________________________
	
	
		
	/**
	 * String formattée pour l'affichage d'un CaractereDan.<br/>
	 * Par exemple :<br/>
	 * "Caractère : é     Unicode : \u00e9  NumericValue : -1    
	 * TypeCaractere : 2   valeurEntiere : 233   Point de Code décimal : 233  
	 *  Point de Code Hexa : e9     
	 *   Nom : LATIN SMALL LETTER E WITH ACUTE         ".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		if (this.id != null) {
			builder.append(
					String.format(LOCALE_FR_FR
							, "id : %-7d", this.id));
		}
		
		if (this.position != null) {
			builder.append(
					String.format(LOCALE_FR_FR
							, "Position : %-7d", this.position));
		}
			
		builder.append(
				String.format(LOCALE_FR_FR
						, "Caractère : %-6s", this.caractere));
		
		builder.append(
				String.format(LOCALE_FR_FR
						, "Unicode : %-8s", this.unicode));
		
		builder.append(
				String.format(LOCALE_FR_FR
						, "NumericValue : %-6d", this.numericValue));
		
		builder.append(
				String.format(LOCALE_FR_FR
						, "TypeCaractere : %-4d", this.typeCaractere));
		
		builder.append(
				String.format(LOCALE_FR_FR
						, "valeurEntiere : %-6d", this.valeurEntiere));
		
		builder.append(
				String.format(LOCALE_FR_FR
						, "Point de Code décimal : %-6d", this.codePointDecimal));
		
		builder.append(
				String.format(LOCALE_FR_FR
						, "Point de Code Hexa : %-8s", this.codePointHexa));
		
		builder.append(
				String.format(LOCALE_FR_FR
						, "Nom : %-40s", this.nom));
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * retourne "id;Position;Caractère;Unicode;numericValue;Type de Caractère;
	 * Valeur Entière;Point de Code Décimal;
	 * Point de Code HexaDécimal;Nom Unicode;".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteCsv() {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append("id;");
		stb.append("Position;");
		stb.append("Caractère;");
		stb.append("Unicode;");
		stb.append("numericValue;");
		stb.append("Type de Caractère;");
		stb.append("Valeur Entière;");
		stb.append("Point de Code Décimal;");
		stb.append("Point de Code HexaDécimal;");
		stb.append("Nom Unicode;");
		
		return stb.toString();
		
	} // Fin de getEnTeteCsv().____________________________________________

	
	
	/**
	 * Fournit le ValueObject sous forme de ligne Csv.<br/>
	 * <br/>
	 * "id;Position;Caractère;Unicode;numericValue;Type de Caractère;
	 * Valeur Entière;Point de Code Décimal;
	 * Point de Code HexaDécimal;Nom Unicode;".<br/>
	 * <br/>
	 * Par exemple pour é : <br/>
	 * null;null;é;\u00e9;-1;2;233;233;e9;LATIN SMALL LETTER E WITH ACUTE;<br/>
	 * <br/>
	 *
	 * @return : String : Le valueObject sous forme csv 
	 * avec le séparateur ';'.<br/>
	 */
	@Override
	public final String fournirStringCsv() {
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append(this.id);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.position);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.caractere);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.unicode);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.numericValue);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.typeCaractere);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.valeurEntiere);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.codePointDecimal);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.codePointHexa);
		stb.append(SEP_POINTVIRGULE);
		stb.append(this.nom);
		stb.append(SEP_POINTVIRGULE);
		
		return stb.toString();
		
	} // Fin de toCsv().___________________________________________________

	

	/**
	 * "id;Position;Caractère;Unicode;numericValue;Type de Caractère;
	 * Valeur Entière;Point de Code Décimal;
	 * Point de Code HexaDécimal;Nom Unicode;".<br/>
	 * <br/>
	 * 
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
			entete = "position";
			break;

		case 2:
			entete = "caractère";
			break;

		case 3:
			entete = "unicode";
			break;
			
		case 4:
			entete = "numericValue";
			break;
			
		case 5:
			entete = "typeCaractere";
			break;
			
		case 6:
			entete = "valeurEntiere";
			break;
			
		case 7:
			entete = "codePointDecimal";
			break;
			
		case 8:
			entete = "codePointHexa";
			break;
			
		case 9:
			entete = "nom";
			break;
			
		default:
			entete = "invalide";
			break;
			
		} // Fin du Switch._________________________________
		
		return entete;
		
	} // Fin de getEnTeteColonne(...)._____________________________________



	/**
	 * "id;Position;Caractère;Unicode;numericValue;Type de Caractère;
	 * Valeur Entière;Point de Code Décimal;
	 * Point de Code HexaDécimal;Nom Unicode;".<br/>
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
			valeur = this.position;
			break;

		case 2:
			valeur = this.caractere;
			break;

		case 3:
			valeur = this.unicode;
			break;
			
		case 4:
			valeur = this.numericValue;
			break;
			
		case 5:
			valeur = this.typeCaractere;
			break;
			
		case 6:
			valeur = this.valeurEntiere;
			break;
			
		case 7:
			valeur = this.codePointDecimal;
			break;
			
		case 8:
			valeur = this.codePointHexa;
			break;
			
		case 9:
			valeur = this.nom;
			break;
			
		default:
			valeur = "invalide";
			break;
			
		} // Fin du Switch._________________________________
		
		return valeur;
		
	} // Fin de getValeurColonne(...)._____________________________________


	
	/**
	 * "id;Position;Caractère;Unicode;numericValue;Type de Caractère;
	 * Valeur Entière;Point de Code Décimal;
	 * Point de Code HexaDécimal;Nom Unicode;".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final void reset() {
		
		this.id = null;
		this.position = null;
		this.caractere = null;
		this.unicode = null;
		this.numericValue = 0;
		this.typeCaractere = 0;
		this.valeurEntiere = 0;
		this.codePointDecimal = 0;
		this.codePointHexa = null;
		this.nom = null;
		
	} // Fin de reset().___________________________________________________

	
	
	/**
	 * Méthode à utiliser avec reset() pour éviter d'instancier 
	 * un nouvel objet à chaque itération d'une boucle.<br/>
	 * <br/>
	 * - Calcule automatiquement les valeurs unicode dépendantes de pChar.<br/>
	 * <br/>
	 *
	 * @param pId : Long : id en base.<br/>
	 * @param pPosition : Integer : Position du caractère dans une String.<br/>
	 * @param pChar : Character : caractère.<br/>
	 */
	public final void remplir(
			final Long pId
			, final Integer pPosition
			, final Character pChar) {
		
		this.id = pId;
		this.position = pPosition;
		this.caractere = pChar;
		
		/* Calcule automatiquement les valeurs unicode dépendantes de pChar. */
		this.remplirAuto(pChar);
		
	} // Fin de remplir(
	 // Long pId
	 // , Integer pPosition
	 // , Character pChar).________________________________________________
	

	
	/**
	 * Calcule automatiquement les valeurs unicode dépendantes de pChar.<br/>
	 *
	 * @param pChar : Character.<br/>
	 */
	private void remplirAuto(
			final Character pChar) {
		
		this.unicode = this.getCodeUnicodeHexaDecimal(pChar);
		this.numericValue = this.getNumericValue(pChar);
		this.typeCaractere = this.getTypeCharacter(pChar);
		this.valeurEntiere = this.getIntValue(pChar);
		this.codePointDecimal = this.getCodePointDecimal(pChar);
		this.codePointHexa = this.getCodePointHexaDecimal(pChar);
		this.nom = this.getNameUnicodeChar(pChar);
		
	} // Fin de remplirAuto(
	 // Character pChar).__________________________________________________
	
	
	
	/**
	 * Retourne le codePoint d'un caractère exprimé en décimal.<br/>
	 * par exemple : <br/>
	 * - 'à' retourne 224.<br/>
	 * - 'è' retourne 232.<br/>
	 * <br/>
	 * - retourne 0 si pChar == null.<br/>
	 * <br/>
	 * une caractère Unicode = un “codepoint”.<br/>
	 * un char Java = un “codeunit”.<br/>
	 * un “codepoint” est consitué de un “codeunit” 
	 * dans la zone Basic Multilingual Plane (BMP) 
	 * et de deux “codeunits” pour les caractères supplémentaires.<br/>
	 * Par exemple, un caractère gothique (ahsa) 
	 * a pour code Unicode U+10330 (plus grand que 65535). 
	 * Il sera donc représenté de façon interne 
	 * par une paire de char java (“codeunits”) : \uD800,\uDF30.<br/>
	 * <br/>
	 * A code point is a value that can be used in a coded character set.<br/> 
	 * A code point is a 32-bit int data type, 
	 * where the lower 21 bits represent a valid code point value 
	 * and the upper 11 bits are 0.<br/>
	 * <br/>
	 * A coded character set is a set of characters 
	 * for which a unique number has been assigned to each character.<br/> 
	 * Units of a coded character set are known as code points.<br/> 
	 * A code point value represents the position of a character 
	 * in the coded character set. <br/>
	 * For example, the code point for the letter à in the Unicode 
	 * coded character set is 225 in decimal, 
	 * or E1 in hexadecimal notation.<br/>
	 *
	 * @param pChar : Character : le caractère 
	 * dont on veut connaitre le point de code.<br/>
	 * 
	 * @return : int : le point de code en valeur décimale.<br/>
	 */
	private int getCodePointDecimal(
			final Character pChar) {
		
		/* retourne 0 si pChar == null. */
		if (pChar == null) {
			return 0;
		}
		
		final char[] tabcar = new char[1];
		tabcar[0] = pChar;
		
		final int codePoint = Character.codePointAt(tabcar, 0);
		
		return codePoint;
		
	} // Fin de getCodePointDecimal(
	 // Character pChar).__________________________________________________
	

	
	/**
	 * Retourne le codePoint d'un caractère exprimé en hexadécimal.<br/>
	 * par exemple : <br/>
	 * - 'à' retourne e0.<br/>
	 * - 'è' retourne e8.<br/>
	 * <br/>
	 * - retourne null si pChar == null.<br/>
	 * <br/>
	 * une caractère Unicode = un “codepoint”.<br/>
	 * un char Java = un “codeunit”.<br/>
	 * un “codepoint” est consitué de un “codeunit” 
	 * dans la zone Basic Multilingual Plane (BMP) 
	 * et de deux “codeunits” pour les caractères supplémentaires.<br/>
	 * Par exemple, un caractère gothique (ahsa) 
	 * a pour code Unicode U+10330 (plus grand que 65535). 
	 * Il sera donc représenté de façon interne 
	 * par une paire de char java (“codeunits”) : \uD800,\uDF30.<br/>
	 * <br/>
	 * A code point is a value that can be used in a coded character set.<br/> 
	 * A code point is a 32-bit int data type, 
	 * where the lower 21 bits represent a valid code point value 
	 * and the upper 11 bits are 0.<br/>
	 * <br/>
	 * A coded character set is a set of characters 
	 * for which a unique number has been assigned to each character.<br/> 
	 * Units of a coded character set are known as code points.<br/> 
	 * A code point value represents the position of a character 
	 * in the coded character set. <br/>
	 * For example, the code point for the letter à in the Unicode 
	 * coded character set is 225 in decimal, 
	 * or E1 in hexadecimal notation.<br/>
	 * <br/>
	 *
	 *  @param pChar : Character : le caractère 
	 * dont on veut connaitre le point de code.<br/>
	 * 
	 * @return : String : le point de code en valeur hexadécimale.<br/>
	 */
	private String getCodePointHexaDecimal(
			final Character pChar) {
		
		/* retourne null si pChar == null. */
		if (pChar == null) {
			return null;
		}
		
		return Integer.toHexString(this.getCodePointDecimal(pChar));
	} // Fin de getCodePointHexaDecimal(
	 // Character pChar).__________________________________________________


	
	/**
	 * Retourne la valeur entière d'un caractère retournée 
	 * par la méthode read() d'un Reader.<br/>
	 * <br/>
	 * Cette valeur est la même que celle que 
	 * l'on optient en castant le char en int : (int) pChar.<br/>
	 * Il semblerait que cette valeur soit égale au point de code décimal.<br/>
	 * <br/>
	 * Par exemple : <br/>
	 * - 'à' retourne 224.<br/>
	 * - 'è' retourne 232.<br/>
	 * <br/>
	 * - retourne 0 si pChar == null.<br/>
	 * <br/>
	 *
	 * @param pChar : Character : le caractère dont on veut 
	 * donner la valeur entière retournée par la méthode 
	 * read() d'un Reader.<br/>
	 * 
	 * @return : int : la valeur entière retournée 
	 * par la méthode read() d'un Reader.<br/>
	 */
	private int getIntValue(
			final Character pChar) {
		
		/* retourne 0 si pChar == null. */
		if (pChar == null) {
			return 0;
		}
		
		return pChar;
		
	} // Fin de getIntValue(
	 // Character pChar).__________________________________________________
	

	
	/**
	 * Retourne la valeur entière que le caractère Unicode représente.<br/>
	 * Utilise Character.getNumericValue(pChar).<br/>
	 * <br/>
	 * - retourne 0 si pChar == null.<br/>
	 * <br/>
	 *
	 * @param pChar : Character.<br/>
	 * 
	 * @return : int : la valeur du caractère Unicode.<br/>
	 */
	private int getNumericValue(
			final Character pChar) {
		
		/* retourne 0 si pChar == null. */
		if (pChar == null) {
			return 0;
		}
		
		return Character.getNumericValue(pChar);
		
	} // Fin de getNumericValue(
	 // Character pChar).__________________________________________________


	
	/**
	 * Retourne une valeur entière indiquant 
	 * la catégorie générale d'un caractère.<br/>
	 * <br/>
	 * Par exemple : <br/>
	 * - 'à' retourne 2.<br/>
	 * - 'è' retourne 2.<br/>
	 * - '.' retourne 24.<br/>
	 * - ' ' retourne 12.<br/>
	 * <br/>
	 * - retourne 0 si pChar == null.<br/>
	 * <br/>
	 *
	 * @param pChar : Character : le caractère 
	 * dont on veut connaitre la catégorie générale.<br/>
	 * 
	 * @return : int : la catégorie générale d'un caractère.<br/>
	 */
	private int getTypeCharacter(
			final Character pChar) {
		
		/* retourne 0 si pChar == null. */
		if (pChar == null) {
			return 0;
		}
		
		final int pointCode = this.getCodePointDecimal(pChar);
		
		return Character.getType(pointCode);
		
	} // Fin de getTypeCharacter(
	 // Character pChar).__________________________________________________
	

	
	/**
	 * Retourne le code Unicode (de la forme \\uxxxx) d'un caractère.<br/>
	 * <br/>
	 * - Par exemple : <br/> 
	 * '-' retourne \u002d.<br/>
	 * 'ï' retourne \u00ef.<br/>
	 * ' ' retourne \u0020.<br/>
	 * 'é' retourne \u00e9.<br/>
	 * 'è' retourne \u00e8.<br/>
	 * '\r' retourne \u000d.br/>
	 * '\n' retourne \u000a.<br/>
	 * '’' retourne \u2019.<br/>
	 * <br/>
	 * - retourne null si pCharacter == null.<br/>
	 * <br/>
	 *
	 * @param pCharacter : Character : le caractère 
	 * dont on veut connaitre le code Unicode.<br/>
	 * 
	 * @return : String : Le code Unicode.<br/>
	 */
	private String getCodeUnicodeHexaDecimal(
			final Character pCharacter) {
		
		String codeUnicodeHexaDecimal;
		
		/* retourne null si pCharacter == null. */
		if (pCharacter == null) {
			return null;
		}
		
		try {
			
			/* Récupère le code hexadecimal unicode du caractère. */
			final int nombreUnicodeHexaDecimal = pCharacter;
			
			/* Construit le code unicode hexa de la forme \u002d. */
			codeUnicodeHexaDecimal 
				= String.format(
						LOCALE_FR_FR, "\\u%04x", nombreUnicodeHexaDecimal);
			
		} catch (Exception e) {
			return e.getMessage();
		}
	    
	    return codeUnicodeHexaDecimal;
	    
	} // Fin de getCodeUnicodeHexaDecimal(
	 // Character pCharacter)._____________________________________________


	
	/**
	 * Retourne le nom Unicode d'un Character.<br/>
	 * <br/>
	 * Exemples :<br/>
	 * - LATIN CAPITAL LETTER E<br/>
	 * - LATIN SMALL LETTER O<br/>
	 * - SPACE<br/>
	 * - REPLACEMENT CHARACTER<br/>
	 * <br/>
	 * - retourne null si pChar == null.<br/>
	 * <br/>
	 *
	 * @param pChar : Character.<br/>
	 * 
	 * @return : String : Nom unicode.<br/>
	 */
	private String getNameUnicodeChar(
			final Character pChar) {
		
		/* retourne null si pChar == null. */
		if (pChar == null) {
			return null;
		}
		
		char[] tabcar = new char[1];
		tabcar[0] = pChar;
		
		return Character.getName(Character.codePointAt(tabcar, 0));
		
	} // Fin de getNameUnicodeChar(
	 // Character pChar).__________________________________________________



	/**
	 * Getter de l'identifiant en base.<br/>
	 * 
	 * @return this.id : Long.<br/>
	 */
	public final Long getId() {
		return this.id;
	} // Fin de getId().___________________________________________________



	/**
	 * Setter de l'identifiant en base.<br/>
	 * 
	 * @param pId : Long.<br/>
	 */
	public final void setId(
			final Long pId) {
		this.id = pId;
	} // Fin de setId(
	 // Long pId)._________________________________________________________


	/**
	 * Getter de la Position du caractère dans une String.<br/>
	 *
	 * @return position : Integer.<br/>
	 */
	public final Integer getPosition() {
		return this.position;
	} // Fin de getPosition()._____________________________________________



	/**
	 * Setter de la Position du caractère dans une String.<br/>
	 *
	 * @param pPosition : Integer : valeur à passer à position.<br/>
	 */
	public final void setPosition(
			final Integer pPosition) {
		this.position = pPosition;
	} // Fin de setPosition(
	 // Integer pPosition).________________________________________________



	/**
	 * Getter du caractère.<br/>
	 *
	 * @return caractere : Character.<br/>
	 */
	public final Character getCaractere() {
		return this.caractere;
	} // Fin de getCaractere().____________________________________________



	/**
	 * Setter du caractère.<br/>
	 * <br/>
	 * Calcule automatiquement les valeurs unicode dépendantes de pChar.<br/>
	 *
	 * @param pChar : Character : valeur à passer à caractere.<br/>
	 */
	public final void setCaractere(
			final Character pChar) {
		
		this.caractere = pChar;
		
		/* Calcule automatiquement les valeurs unicode dépendantes de pChar. */
		this.remplirAuto(pChar);
		
	} // Fin de setCaractere(
	 // Character pChar).__________________________________________________



	/**
	 * Getter du Unicode du caractère (\u0020...).<br/>
	 *
	 * @return unicode : String.<br/>
	 */
	public final String getUnicode() {
		return this.unicode;
	} // Fin de getUnicode().______________________________________________



	/**
	 * Getter de la valeur entière que le caractère Unicode représente 
	 * fournie par Character.getNumericValue(pChar).<br/>
	 *
	 * @return numericValue : int.<br/>
	 */
	public final int getNumericValue() {
		return this.numericValue;
	} // Fin de getNumericValue()._________________________________________



	/**
	 * Getter de la valeur entière indiquant 
	 * la catégorie générale d'un caractère.<br/>
	 *
	 * @return typeCaractere : int.<br/>
	 */
	public final int getTypeCaractere() {
		return this.typeCaractere;
	} // Fin de getTypeCaractere().________________________________________



	/**
	 * Getter de la valeur entière d'un caractère retournée 
	 * par la méthode read() d'un Reader.<br/>
	 *
	 * @return valeurEntiere : int.<br/>
	 */
	public final int getValeurEntiere() {
		return this.valeurEntiere;
	} // Fin de getValeurEntiere().________________________________________



	/**
	 * Getter du Point de code du caractère en décimal.<br/>
	 *
	 * @return codePointDecimal : int.<br/>
	 */
	public final int getCodePointDecimal() {
		return this.codePointDecimal;
	} // Fin de getCodePointDecimal()._____________________________________



	/**
	 * Getter du Point de code du caractère en hexadécimal.<br/>
	 *
	 * @return codePointHexa : String.<br/>
	 */
	public final String getCodePointHexa() {
		return this.codePointHexa;
	} // Fin de getCodePointHexa().________________________________________



	/**
	 * Getter du nom Unicode du caractère.<br/>
	 *
	 * @return nom : String.<br/>
	 */
	public final String getNom() {
		return this.nom;
	} // Fin de getNom().__________________________________________________


	
} // FIN DE LA CLASSE CaractereDan.------------------------------------------
