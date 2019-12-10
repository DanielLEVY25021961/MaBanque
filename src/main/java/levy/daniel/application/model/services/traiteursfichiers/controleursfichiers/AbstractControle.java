package levy.daniel.application.model.services.traiteursfichiers.controleursfichiers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.traiteursfichiers.CaractereDan;
import levy.daniel.application.model.services.traiteursfichiers.enregistreursfichiers.IEnregistreurFichiers;
import levy.daniel.application.model.services.traiteursfichiers.enregistreursfichiers.impl.EnregistreurFichiers;
import levy.daniel.application.model.services.traiteursfichiers.enregistreursfichiers.rapportsenregistrements.LigneRapportEnregistrement;
import levy.daniel.application.model.services.traiteursfichiers.rapportscontroles.LigneRapport;

/**
 * class AbstractControle :<br/>
 * Abstraction qui garantit que :<br/>
 * <ul>
 * <li>Tout contrôle connait le nom de la classe concrète 
 * qui l'exécute 'nomClasseConcrete'.</li>
 * <li>Tout contrôle connait son ordre d'exécution 'ordreControle' dans un enchaînement de contrôles.</li>
 * <li>Tout contrôle est effectué à une 'dateContrôle'.</li> 
 * <li>La classe calcule automatiquement 'dateControleStringFormatee' 
 * connaissant dateControle.</li>
 * <li>Tout contrôle est effectué par un utilisateur (user) 
 * dont on connait le nom 'userName'. La classe remplit automatiquement 
 * userName avec 'Administrateur' si on ne lui fournit pas de userName.</li>
 * <li>Tout contrôle s'applique sur un File 'fichier'.</li> 
 * <li>La classe calcule automatiquement 'nomFichier' connaissant fichier.</li>
 * <li>Tout contrôle appartient à un type de contrôle 'typeControle' 
 * comme "contrôle de surface". 
 * 'typeControle' est fourni par chaque classe concrète.</li>
 * <li>Tout contrôle a un nom 'nomControle' comme 'contrôle fichier texte'. 
 * 'nomControle' est fourni par chaque classe concrète.</li>
 * <li>Tout contrôle vérifie un critère 'nomCritere' comme 
 * 'le fichier ne doit pas comporter de caractères indésirables'. 
 * 'nomCritere' est fourni par chaque classe concrète.</li>
 * <li>Tout contrôle a une gravité 'gravite' comme '1 - bloquant'.
 * Cette gravité est directement liée au niveau d'anomalie du contrôle 
 * 'niveauAnomalie' comme "1" pour bloquant.</li>
 * <li>Chaque classe concrète fournit le 'niveauAnomalie' du contrôle 
 * via sa méthode fournirCleNiveauAnomalie() qui permet d'aller 
 * chercher la valeur dans messagescontroles_fr_FR.properties 
 * ou via fournirNiveauAnomalieEnDur().</li>
 * <li>Tout contrôle sait si il est bloquant via 'estBloquant'. 
 * La classe remplit automatiquement 'estBloquant' 
 * connaissant niveauAnomalie.</li>
 * <li>Tout contrôle sait si il doit être effectué via 'aEffectuer' 
 * (contrôle paramétrable). 
 * Chaque classe concrète fournit le 'aEffectuer' du contrôle 
 * via sa méthode fournirCleAEffectuer() qui permet d'aller 
 * chercher la valeur dans messagescontroles_fr_FR.properties 
 * ou via fournirAEffectuerEnDur().</li>
 * <li>Tout contrôle fournit un rapport de contrôle 'rapport' 
 * sous forme de List&lt;LigneRapport&gt;.</li>
 * <li>Tout contrôle peut fournir un compte-rendu 
 * d'enregistrement sur disque 'rapportEnregistrement' 
 * sous forme de List&lt;LigneRapportEnregistrement&gt; 
 * si on décide d'enregistrer le rapport de contrôle 
 * ou n'importe quel artefact sur disque.</li>
 * <li>Tout contrôle peut fournir le fichier à contrôler 
 * sous forme de SortedMap&lt;Integer,String&gt; 'fichierEnMap' avec :<br/>
 * <ul>
 * <li>Integer : le numéro de la ligne.</li>
 * <li>String : la ligne.</li>
 * Il suffit d'utiliser la méthode lireFichierLigneParLigne(File pFile) 
 * pour remplir la Map.
 * </ul>
 * </li>
 * <li>Tout contrôle ou traitement sait fournir le fichier résultant 
 * de son traitement via sa méthode getFichierTraite().</li>
 * </ul>
 * 
 * <br/>
 * Attributs : <br/>
 * [nomClasseConcrete;ordreControle;dateControle
 * ;dateControleStringFormatee;userName;
 * fichier;nomFichier;typeControle;nomControle;nomCritere;gravite;
 * niveauAnomalie;estBloquant;aEffectuer;rapport;rapportEnregistrement;].<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<code>
 *  // Instanciation d'un ControleurTypeTexte.<br/>
 *  final ControleurTypeTexte control = new ControleurTypeTexte();<br/>
 *  // Invocation de la méthode controler(...) en demandant 
 *  l'écriture des rapports textuels et csv sur disque.<br/>
 *  final boolean resultat = control.controler(FILE_CHARETTE_ANSI, true);<br/>
 *  // resultat = true FILE_CHARETTE_ANSI est un fichier textuel.<br/>
 *  control.afficherRapportTextuel() // Pour voir le 
 *  rapport de contrôle sous forme textuelles.<br/>
 *  control.afficherRapportCsvAvecEntete() // Pour voir le 
 *  rapport de contrôle sous forme csv.<br/>
 *  // id;date du contrôle;utilisateur;Fichier;type de contrôle;Contrôle;
 *  Critère du Contrôle;Gravité du Contrôle;Numéro de Ligne;
 *  Message du Contrôle;Ordre du Champ;Position du Champ;
 *  Valeur du Champ;Action;<br/>
 *  // null;2016-03-06_19-08-55-259;Administrateur;
 *  chaàâreéèêëtte_ANSI.txt;Contrôle de surface;Contrôle fichier texte;
 *  Le fichier ne doit pas comporter de caractères indésirables 
 *  (impossibles à écrire au clavier);1 - anomalie bloquante;
 *  null;Le fichier 'chaàâreéèêëtte_ANSI.txt' est bien un fichier texte;
 *  null;sans objet;sans objet;OK - Fichier accepté;<br/> 
 *  control.afficherRapportEnregistrementTextuel() // Pour voir le compte-rendu 
 *  de l'enregistrement du rapport de contrôle sous forme textuelle.<br/>
 *  control.afficherRapportEnregistrementCsv() // Pour voir le compte-rendu 
 *  de l'enregistrement du rapport de contrôle sous forme csv.<br/>
 * </code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Nettoyer chaine de caractères, nettoyer String, <br/>
 * StringUtils.trim(resultat), trim(), nettoyer valeurs dans properties,<br/>
 * Hook,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * levy.daniel.application.ILecteurDecodeurFile.<br/>
 * levy.daniel.application.IListeurDeCaracteresUnicode.<br/>
 * levy.daniel.application.IExportateurCsv.<br/>
 * levy.daniel.application.IExportateurJTable.<br/>
 * levy.daniel.application.IResetable.<br/>
 * levy.daniel.application.metier.controles.rapportscontroles.LigneRapport.<br/>
 * levy.daniel.application.metier.controles.IRapporteurControle.<br/>
 * levy.daniel.application.metier.controles.IControle.<br/>
 * levy.daniel.application.metier.controles.CaractereDan.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.impl.EnregistreurFichiers.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.rapportsenregistrements.LigneRapportEnregistrement.<br/>
 * levy.daniel.application.metier.controles.IEnregistreurRapport.<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 27 févr. 2016
 *
 */
public abstract class AbstractControle implements IControle {

	// ************************ATTRIBUTS************************************/

	/**
	 * nom de la classe de contrôle concrète.<br/>
	 */
	protected transient String nomClasseConcrete;
	
	/**
	 * ordre d'exécution du contrôle dans un enchaînement de contrôles.<br/>
	 */
	protected Integer ordreControle;
		
	/**
	 * java.util.Date du contrôle.<br/>
	 */
	protected Date dateControle;
	
	/**
	 * date du contrôle formattée au format dfDatetimemilliFrancaiseLexico.<br/>
	 * Format des dates-heures françaises lexicographique 
	 * avec millisecondes comme
	 * '1961-01-25_12-27-07-251'.<br/>
	 * "yyyy-MM-dd_HH-mm-ss-SSS".<br/>
	 */
	protected transient String dateControleStringFormatee;
		
	/**
	 * nom de l'utilisateur qui a déclenché le contrôle.<br/>
	 */
	protected String userName;
	
	/**
	 * fichier sur lequel s'applique le contrôle.<br/>
	 */
	protected File fichier;
	
	/**
	 * nom du fichier objet du contrôle.<br/>
	 */
	protected transient String nomFichier;
		
	/**
	 * type du contrôle ('contrôle de surface' par exemple).<br/>
	 */
	protected transient String typeControle;
	
	/**
	 * nom du contrôle ('contrôle fichier texte' par exemple).<br/>
	 */
	protected transient String nomControle;
	
	/**
	 * nom du critère appliqué dans le contrôle 
	 * ('le fichier ne doit pas comporter de 
	 * caractères indésirables' par exemple).<br/>
	 */
	protected transient String nomCritere;
		
	/**
	 * désignation de la gravité de ce contrôle 
	 * (par exemple '1 - bloquant').<br/>
	 */
	protected transient String gravite;
	
	/**
	 * Niveau de l'anomalie correspondant au Contrôle
	 * dans le messagescontroles_fr_FR.properties.<br/>
	 * Par exemple : "1" pour le ControleurTypeTexte.<br/>
	 */
	protected transient String niveauAnomalie;
	
	/**
	 * boolean qui stipule si le contrôle doit pouvoir 
	 * bloquer le programme.<br/>
	 * true si le contrôle doit pouvoir bloquer le programme.<br/>
	 */
	protected transient boolean estBloquant;
		
	/**
	 * boolean qui stipule si le contrôle doit être effectué 
	 * dans un enchaînement de contrôles.<br/>
	 * Cette valeur doit figurer dans le messagescontroles_fr_FR.properties 
	 * ou être fournie en dur par les classes concrètes.<br/>
	 * true si le contrôle doit être effectué.<br/>
	 */
	protected transient boolean aEffectuer;
		
	/**
	 * rapport fourni par le contrôle sous forme 
	 * de List&lt;LigneRapport&gt;.<br/>
	 */
	protected transient List<LigneRapport> rapport 
		= new ArrayList<LigneRapport>();
		
	/**
	 * rapport fourni par l'enregistreur sous forme 
	 * de List&lt;LigneRapportEnregistrement&gt;.<br/>
	 */
	protected transient List<LigneRapportEnregistrement> rapportEnregistrement 
		= new ArrayList<LigneRapportEnregistrement>();
	
	/**
	 * SortedMap&lt;Integer,String&gt; encapsulant un Fichier texte avec :<br/>
	 * <ul>
	 * <li>Integer : le numéro de la ligne.</li><br/>
	 * <li>String : la ligne.</li><br/>
	 * </ul>
	 */
	protected transient SortedMap<Integer, String> fichierEnMap 
		= new TreeMap<Integer, String>();
		
	/**
	 * "Classe AbstractControle".<br/>
	 */
	public static final String CLASSE_ABSTRACTCONTROLE 
		= "Classe AbstractControle";
	
	/**
	 * "méthode initialiserBundleControles()".<br/>
	 */
	public static final String METHODE_INITIALISER_BUNDLE_CONTROLES 
		= "méthode initialiserBundleControles()";
		
	/**
	 * "Méthode recupererNiveauAnomalieDansProperties()".<br/>
	 */
	public static final String METHODE_RECUPERERNIVEAUANOMALIEDANSPROPERTIES 
		= "Méthode recupererNiveauAnomalieDansProperties()";
	
	/**
	 * "Méthode fournirDateAPartirDeStringFormattee(String pStringFormattee)".<br/>
	 */
	public static final String METHODE_FOURNIRDATEAPARTIRDESTRING 
		= "Méthode fournirDateAPartirDeStringFormattee(String pStringFormattee)";
	
	/**
	 * "Méthode fournirCheminRapportsDansProperties()".<br/>
	 */
	public static final String METHODE_FOURNIRCHEMINRAPPORTSDANSPROPERTIES 
		= "Méthode fournirCheminRapportsDansProperties()";
		
	/**
	 * "méthode detruireArborescence(String pChemin)".<br/>
	 */
	public static final String METHODE_DETRUIRE_ARBORESCENCE 
		= "méthode detruireArborescence(String pChemin)";
	
	/**
	 * "méthode viderRepertoireADetruire(File pFile)".<br/>
	 */
	public static final String METHODE_VIDER_REPERTOIRE 
		= "méthode viderRepertoireADetruire(File pFile)";
	
	/**
	 * "méthode fournirFile(String pChemin, Date pDate, String pNomFichier)".<br/>
	 */
	public static final String METHODE_FOURNIRFILE 
		= "méthode fournirFile(String pChemin, Date pDate, String pNomFichier)";

	/**
	 * "Méthode controler(File pFile)".<br/>
	 */
	public static final String METHODE_CONTROLER 
		= "Méthode controler(File pFile)";
	
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
	 * Encapsulation de messagescontroles_fr_FR.properties.<br/>
	 */
	public static ResourceBundle bundleControles;
			
	/**
	 * CARACTERES_INDESIRABLES_SET : Set&lt;Character&gt; :<br/>
	 * Set contenant des caractères indésirables 
	 * (impossibles à écrire simplement au clavier).<br/>
	 */
	public static final Set<Character> CARACTERES_INDESIRABLES_SET 
		= new HashSet<Character>();
	
	
	static {
		
		/* ACUTE ACCENT '´' */
		CARACTERES_INDESIRABLES_SET.add('\u00b4');
		/* GRAVE ACCENT '`' */
		CARACTERES_INDESIRABLES_SET.add('\u0060');
		/* CIRCUMFLEX ACCENT '^' */
		CARACTERES_INDESIRABLES_SET.add('\u005e');
		/* BOX DRAWINGS DOUBLE DOWN AND LEFT '╗' */
		CARACTERES_INDESIRABLES_SET.add('\u2557');
		/* BOX DRAWINGS LIGHT DOWN AND LEFT '┐' */
		CARACTERES_INDESIRABLES_SET.add('\u2510');
		/* LATIN CAPITAL LETTER U WITH ACUTE 'Ú' */
		CARACTERES_INDESIRABLES_SET.add('\u00da');
		/* RIGHT-POINTING DOUBLE ANGLE QUOTATION MARK '»' */
		CARACTERES_INDESIRABLES_SET.add('\u00bb');
		/* INVERTED QUESTION MARK '¿' */
		CARACTERES_INDESIRABLES_SET.add('\u00bf');		
		/* LATIN SMALL LETTER D WITH CARON 'ď' */
		CARACTERES_INDESIRABLES_SET.add('\u010f');
		/* LATIN SMALL LETTER T WITH CARON 'ť' */
		CARACTERES_INDESIRABLES_SET.add('\u0165');
		/* LATIN SMALL LETTER Z WITH DOT ABOVE 'ż' */
		CARACTERES_INDESIRABLES_SET.add('\u017c');
		/* LATIN 1 SUPPLEMENT 80 ' ' */
		CARACTERES_INDESIRABLES_SET.add('\u0080');		
		/* LATIN SMALL LETTER R WITH ACUTE 'ŕ' */
		CARACTERES_INDESIRABLES_SET.add('\u0155');
		/* LATIN SMALL LETTER C WITH CARON 'č' */
		CARACTERES_INDESIRABLES_SET.add('\u010d');
		/* LATIN SMALL LETTER E WITH OGONEK 'ę' */
		CARACTERES_INDESIRABLES_SET.add('\u0119');		
		/* LATIN CAPITAL LETTER O WITH ACUTE 'Ó' */
		CARACTERES_INDESIRABLES_SET.add('\u00d3');
		/* LATIN CAPITAL LETTER O WITH CIRCUMFLEX 'Ô' */
		CARACTERES_INDESIRABLES_SET.add('\u00d4');
		/* LATIN CAPITAL LETTER THORN 'Þ' */
		CARACTERES_INDESIRABLES_SET.add('\u00de');
		/* LATIN CAPITAL LETTER U WITH CIRCUMFLEX 'Û' */
		CARACTERES_INDESIRABLES_SET.add('\u00db');
		/* LATIN CAPITAL LETTER U WITH GRAVE 'Ù' */
		CARACTERES_INDESIRABLES_SET.add('\u00d9');
		/* LATIN CAPITAL LETTER C WITH CEDILLA 'Ç' */
		CARACTERES_INDESIRABLES_SET.add('\u00c7');
		/* NKO LETTER HA 'ߤ' */
		CARACTERES_INDESIRABLES_SET.add('\u07e4');
		/* SYNCHRONOUS IDLE '' */
		CARACTERES_INDESIRABLES_SET.add('\u0016');
		/* INFORMATION SEPARATOR THREE ' ' */
		CARACTERES_INDESIRABLES_SET.add('\u001d');
		/* SYRIAC END OF PARAGRAPH '܀' */
		CARACTERES_INDESIRABLES_SET.add('\u0700');
		/* HEBREW LETTER HE 'ה' */
		CARACTERES_INDESIRABLES_SET.add('\u05d4');
		/* ARABIC LETTER REH WITH SMALL V BELOW 'ה' */
		CARACTERES_INDESIRABLES_SET.add('\u0695');
		/* ARABIC KASRATAN ' ٍ' */
		CARACTERES_INDESIRABLES_SET.add('\u064d');
		/* COPTIC SMALL LETTER GANGIA 'ϫ' */
		CARACTERES_INDESIRABLES_SET.add('\u03eb');
		
		/* NULL ' ' */
		CARACTERES_INDESIRABLES_SET.add('\u0000');
		/* null '޷' */
		CARACTERES_INDESIRABLES_SET.add('\u07b7');
		/* ACKNOWLEDGE '' */
		CARACTERES_INDESIRABLES_SET.add('\u0006');
		/* END OF TEXT '' */
		CARACTERES_INDESIRABLES_SET.add('\u0003');
		/* START OF HEADING '' */
		CARACTERES_INDESIRABLES_SET.add('\u0001');
		/* DEVICE CONTROL TWO '' */
		CARACTERES_INDESIRABLES_SET.add('\u0012');
		/* END OF TRANSMISSION '' */
		CARACTERES_INDESIRABLES_SET.add('\u0004');
		/* DEVICE CONTROL FOUR '' */
		CARACTERES_INDESIRABLES_SET.add('\u0014');
		/* BACKSPACE '' */
		CARACTERES_INDESIRABLES_SET.add('\u0008');
		/* ENQUIRY '' */
		CARACTERES_INDESIRABLES_SET.add('\u0005');
		/* BELL '' */
		CARACTERES_INDESIRABLES_SET.add('\u0007');
		/* CANCEL '' */
		CARACTERES_INDESIRABLES_SET.add('\u0018');
		
		/* REPLACEMENT CHARACTER '�' */
		CARACTERES_INDESIRABLES_SET.add('\ufffd');
		/* LATIN CAPITAL LETTER A WITH TILDE 'Ã' */
		CARACTERES_INDESIRABLES_SET.add('\u00c3');
		/* COPYRIGHT SIGN '©' */
		CARACTERES_INDESIRABLES_SET.add('\u00a9');
		/* DIAERESIS '¨' */
		CARACTERES_INDESIRABLES_SET.add('\u00a8');
		/* CHARACTER TABULATION WITH JUSTIFICATION ' ' */
		CARACTERES_INDESIRABLES_SET.add('\u0089');
		/* LINE TABULATION SET ' ' */
		CARACTERES_INDESIRABLES_SET.add('\u008a');
		/* PER MILLE SIGN '‰' */
		CARACTERES_INDESIRABLES_SET.add('\u2030');
		/* LATIN CAPITAL LETTER S WITH CARON 'Š' */
		CARACTERES_INDESIRABLES_SET.add('\u0160');
		/* SINGLE LOW-9 QUOTATION MARK '‚' */
		CARACTERES_INDESIRABLES_SET.add('\u201a');
		/* BREAK PERMITTED HERE ' ' */
		CARACTERES_INDESIRABLES_SET.add('\u0082');
	}
	


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(AbstractControle.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 * - Met automatiquement 1 dans this.ordreControle.<br/>
	 * - Met automatiquement dateControle à date système.<br/>
	 * - Met automatiquement userName à "Administrateur".<br/>
	 * - Met automatiquement fichier à null.<br/>
	 * <br/>
	 * - Remplit le nom de la classe concrète this.nomClasseConcrete fourni 
	 * par this.fournirNomClasseConcrete() dans la classe concrète.<br/>
	 * - calcule automatiquement dateControleStringFormattee.<br/>
	 * - calcule automatiquement nomFichier.<br/>
	 * - Remplit le type du contrôle typeControle fourni par 
	 * this.fournirTypeControle() dans la classe concrète.<br/>
	 * - Remplit le nom du contrôle nomControle fourni par 
	 * this.fournirNomControle() dans la classe concrète.<br/>
	 * - Remplit le nom du critère nomCritere fourni par 
	 * this.fournirNomCritere() dans la classe concrète.<br/>
	 * - Remplit gravite (ce qui remplit également niveauAnomalie 
	 * et estBloquant).<br/>
	 * - Va chercher dans messagescontroles_fr_FR.properties 
	 * si le contrôle doit être effectué et remplit this.aEffectuer.<br/>
	 * <br/>
	 */
	public AbstractControle() {
		
		this(1, null, null, null);
		
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	 /**
	 * Constructeur avec fichier.<br/>
	 * <br/>
	 * - Met automatiquement 1 dans this.ordreControle.<br/>
	 * - Met automatiquement dateControle à date système.<br/>
	 * - Met automatiquement userName à "Administrateur".<br/>
	 * <br/>
	 * - Remplit le nom de la classe concrète this.nomClasseConcrete 
	 * fourni par this.fournirNomClasseConcrete() dans la classe concrète.<br/>
	 * - calcule automatiquement dateControleStringFormattee.<br/>
	 * - calcule automatiquement nomFichier.<br/>
	 * - Remplit le type du contrôle typeControle fourni par 
	 * this.fournirTypeControle() dans la classe concrète.<br/>
	 * - Remplit le nom du contrôle nomControle fourni par 
	 * this.fournirNomControle() dans la classe concrète.<br/>
	 * - Remplit le nom du critère nomCritere fourni par 
	 * this.fournirNomCritere() dans la classe concrète.<br/>
	 * - Remplit gravite (ce qui remplit également niveauAnomalie 
	 * et estBloquant).<br/>
	 * - Va chercher dans messagescontroles_fr_FR.properties 
	 * si le contrôle doit être effectué et remplit this.aEffectuer.<br/>
	 * <br/>
	 *
	 * @param pFichier : File : fichier sur lequel s'applique le contrôle.<br/>
	 */
	public AbstractControle(
			final File pFichier) {
		
		this(1, null, null, pFichier);
		
	} // Fin de AbstractControle(
	 // File pFichier).____________________________________________________
	
	
	
	 /**
	 * Constructeur avec user et fichier.<br/>
	 * <br/>
	 * - Met automatiquement 1 dans this.ordreControle.<br/>
	 * - Met automatiquement dateControle à date système.<br/>
	 * <br/>
	 * - Remplit le nom de la classe concrète this.nomClasseConcrete 
	 * fourni par this.fournirNomClasseConcrete() dans la classe concrète.<br/>
	 * - calcule automatiquement dateControleStringFormattee.<br/>
	 * - remplit userName avec pUserName si pUserName != null 
	 * ou 'Administrateur' sinon.<br/>
	 * - calcule automatiquement nomFichier.<br/>
	 * - Remplit le type du contrôle typeControle fourni par 
	 * this.fournirTypeControle() dans la classe concrète.<br/>
	 * - Remplit le nom du contrôle nomControle fourni par 
	 * this.fournirNomControle() dans la classe concrète.<br/>
	 * - Remplit le nom du critère nomCritere fourni par 
	 * this.fournirNomCritere() dans la classe concrète.<br/>
	 * - Remplit gravite (ce qui remplit également niveauAnomalie 
	 * et estBloquant).<br/>
	 * - Va chercher dans messagescontroles_fr_FR.properties 
	 * si le contrôle doit être effectué et remplit this.aEffectuer.<br/>
	 * <br/>
	 *
	 * @param pUserName : String : nom de l'utilisateur 
	 * qui a déclenché le contrôle.<br/> 
	 * @param pFichier : File : fichier sur lequel s'applique le contrôle.<br/>
	 */
	public AbstractControle(
			final String pUserName
					, final File pFichier) {
		
		this(1, null, pUserName, pFichier);
		
	} // Fin de AbstractControle(
	 // String pUserName
	 // , File pFichier).__________________________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * <ul>
	 * <li>initialise éventuellement le bundleControles qui encapsule 
	 * messagescontroles_fr_FR.properties.</li>
	 * <li>Remplit le nom de la classe concrète this.nomClasseConcrete fourni 
	 * par this.fournirNomClasseConcrete() dans la classe concrète.</li>
	 * <li>passe pOrdreControle à this.ordreControle.</li>
	 * <li>Remplit dateControle avec pDateControle si pDateControle != null 
	 * ou la date système sinon.</li>
	 * <li>calcule automatiquement dateControleStringFormattee.</li>
	 * <li>remplit userName avec pUserName si pUserName != null 
	 * ou 'Administrateur' sinon.</li>
	 * <li>calcule automatiquement nomFichier.</li>
	 * <li>Remplit le type du contrôle typeControle fourni par 
	 * this.fournirTypeControle() dans la classe concrète.</li>
	 * <li>Remplit le nom du contrôle nomControle fourni par 
	 * this.fournirNomControle() dans la classe concrète.</li>
	 * <li>Remplit le nom du critère nomCritere fourni par 
	 * this.fournirNomCritere() dans la classe concrète.</li>
	 * <li>Remplit gravite (ce qui remplit également niveauAnomalie 
	 * et estBloquant).</li>
	 * <li>Va chercher dans messagescontroles_fr_FR.properties 
	 * si le contrôle doit être effectué et remplit this.aEffectuer.</li>
	 * <ul>
	 * <br/>
	 * 
	 * @param pOrdreControle : Integer : ordre d'exécution du contrôle 
	 * dans un enchaînement de contrôles.<br/>
	 * @param pDateControle : Date : java.util.Date du contrôle.<br/>
	 * @param pUserName : String : nom de l'utilisateur 
	 * qui a déclenché le contrôle.<br/> 
	 * @param pFichier : File : fichier sur lequel s'applique le contrôle.<br/>
	 */
	public AbstractControle(
			final Integer pOrdreControle
				, final Date pDateControle
					, final String pUserName
						, final File pFichier) {
		
		/* Instancie la super-classe*/
		super();
		
		/* initialise éventuellement le bundleControles qui encapsule 
		 * messagescontroles_fr_FR.properties. */
		initialiserBundleControles();
		
		/* Remplit le nom de la classe concrète this.nomClasseConcrete 
		 * fourni par this.fournirNomClasseConcrete() 
		 * dans la classe concrète. */
		this.nomClasseConcrete = this.fournirNomClasseConcrete();
		
		/* passe pOrdreControle à this.ordreControle. */
		this.ordreControle = pOrdreControle;
		
		/* Remplit dateControle avec pDateControle si pDateControle != null 
		 * ou la date système sinon. */
		this.dateControle = this.fournirDate(pDateControle);
		
		/* calcule automatiquement dateControleStringFormattee. */
		this.dateControleStringFormatee 
			= this.fournirDateFormatee(this.dateControle);
		
		/* remplit userName avec pUserName si pUserName != null 
		 * ou 'Administrateur' sinon. */
		this.userName = this.fournirUserName(pUserName);
		
		/* passe pFichier à this.fichier. */
		this.fichier = pFichier;
		
		/* calcule automatiquement nomFichier. */
		this.nomFichier = this.fournirNomFichier(this.fichier);
		
		/* Remplit le type du contrôle typeControle fourni 
		 * par this.fournirTypeControle() dans la classe concrète. */
		this.typeControle = this.fournirTypeControle();
		
		/* Remplit le nom du contrôle nomControle fourni 
		 * par this.fournirNomControle() dans la classe concrète. */
		this.nomControle = this.fournirNomControle();
		
		/* Remplit le nom du critère nomCritere fourni 
		 * par this.fournirNomCritere() dans la classe concrète. */
		this.nomCritere = this.fournirNomCritere();
		
		/* Remplit gravite (ce qui remplit également niveauAnomalie 
		 * et estBloquant). */
		this.gravite = this.fournirGravite();
		
		/* Va chercher dans messagescontroles_fr_FR.properties 
		 * si le contrôle doit être effectué. */
		this.aEffectuer = this.fournirAEffectuer();
				
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * - Centralise le traitement des mauvais fichiers 
	 * (null, inexistant, répertoire, vide).<br/>
	 * - Rafraîchit automatiquement this.fichier et this.rapport.<br/>
	 * - Appelle automatiquement this.controlerHook(pFile, pEnregistrerRapport) 
	 * qui permet l'exécution du contrôle spécifique 
	 * de chaque classe concrète.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final boolean controler(
			final File pFile) {
		
		return this.controler(pFile, false);
		
	} // Fin de controler(
	 // File pFile)._______________________________________________________
	

	
	/**
	 * - Centralise le traitement des mauvais fichiers 
	 * (null, inexistant, répertoire, vide).<br/>
	 * - Rafraîchit automatiquement this.fichier et this.rapport.<br/>
	 * - Appelle automatiquement 
	 * <code>this.controlerHook(pFile, pEnregistrerRapport)</code> 
	 * qui permet l'exécution du contrôle spécifique 
	 * de chaque classe concrète.<br/>
	 * <br/>
	 * {@inheritDoc}
	 * <br/>
	 */
	@Override
	public final boolean controler(
			final File pFile
				, final boolean pEnregistrerRapport) {
		
		// TRAITEMENT DES MAUVAIS FICHIERS 
		// (null, inexistant, répertoire, vide).*************************
		final boolean resultatTraitementMauvaisFichier 
			= this.traiterMauvaisFile(
					pFile, pEnregistrerRapport, METHODE_CONTROLER);
		
		/* Sort de la méthode et retourne false si le fichier est mauvais. */
		if (!resultatTraitementMauvaisFichier) {
			return false;
		}
		
		// RAFRAICHISSEMENT DE this.fichier et de this.rapport.**********
		/* passe pFile à this.fichier et 
		 * rafraîchit automatiquement this.nomFichier. */
		this.setFichier(pFile);
		
		/* rafraîchit le rapport de contrôle 
		 * (en instancie un nouveau à chaque appel 
		 * de la méthode controler(File pFile)). */
		this.rapport = new ArrayList<LigneRapport>();
		
		// APPLICATION DU CONTROLE SPECIFIQUE A LA CLASSE CONCRETE
		// (HOOK).*******************************************************
		final boolean resultat 
			= this.controlerHook(pFile, pEnregistrerRapport);
		
		// RAPPORTS DE NIVEAU FICHIER.***********************************
		// A appeler à la fin de la méthode 
		// controlerHook(File pFile, boolean pEnregistrerRapport) 
		// de chaque classe concrète pour passer un message spécifique 
		// dans le rapport de contrôle.
		
		// RETOUR DU BOOLEAN RESULTAT.***********************************
		return resultat;
		
	} // Fin de controlerHook(
	// File pFile
	// , boolean pEnregistrerRapport)._____________________________________


	
	/**
	 * method controlerHook(
	 * File pFile
	 * , boolean pEnregistrerRapport) :<br/>
	 * SERVICE PRINCIPAL - HOOK de controler(...).<br/>
	 * Contrôle d'un fichier.<br/>
	 * - Enregistre le rapport de contrôle sur disque 
	 * si pEnregistrerRapport == true.<br/>
	 * <ul>
	 * <li>Vérifie qu'un fichier passe un contrôle.</li><br/>
	 * <li>Doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.</li><br/>
	 * </ul>
	 * <br/>
	 * - retourne false, LOG de niveau INFO et rapport 
	 * si pFile == null.<br/>
	 * - retourne false, LOG de niveau INFO et rapport 
	 * si pFile est inexistant.<br/>
	 * - retourne false, LOG de niveau INFO et rapport 
	 * si pFile est un répertoire.<br/>
	 * - retourne false, LOG de niveau INFO et rapport 
	 * si pFile est vide.<br/>
	 * <br/>
	 * RG-01 : Contrôle de validité<br/>
	 * RG-01-Controler : true si favorable.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dont on veut savoir 
	 * si il passe le contrôle.<br/>
	 * @param pEnregistrerRapport : boolean : 
	 * true si on veut enregistrer le rapport dans un fichier sur disque.<br/>
	 * 
	 * @return : boolean : true si pFile passe le contrôle.<br/>
	 */
	protected abstract boolean controlerHook(
			File pFile, boolean pEnregistrerRapport);
	

	
	/**
	 * method ajouterLigneRapportFavorableNiveauFichier(
	 * String pMessage
	 * , boolean pEnregistrerRapport) :<br/>
	 * <ul>
	 * <li>Crée une ligne de rapport de contrôle 
	 * avec un message spécifique à chaque Contrôle concret pMessage  
	 * en cas de succès du contrôle pour l'ensemble du fichier et l'ajoute 
	 * au rapport de contrôle.</li><br/>
	 * <li>Ajoute cette ligne de rapport au 
	 * rapport de contrôle 'this.rapport'.</li><br/>
	 * <li>Enregistre éventuellement 
	 * le rapport sur disque.</li><br/>
	 * </ul>
	 * <br/>
	 *
	 * @param pMessage : String : message spécifique à chaque Contrôle concret 
	 * en cas de succès du contrôle.<br/>
	 * @param pEnregistrerRapport : boolean : 
	 * true si on veut enregistrer le rapport dans un fichier sur disque.<br/>
	 */
	protected final void ajouterLigneRapportFavorableNiveauFichier(
			final String pMessage,
				final boolean pEnregistrerRapport) {

		/* Création d'une ligne de rapport. */
		final LigneRapport ligneRapport 
			= creerLigneRapport(null
						, pMessage
						, null
						, SANS_OBJET
						, SANS_OBJET
						, true
						, ACTION_FICHIER_ACCEPTE);

		/* Ajoute la ligne de rapport à this.rapport. */
		this.ajouterLigneRapport(ligneRapport);

		/* Enregistrement du rapport sur disque. */
		if (pEnregistrerRapport) {

			this.enregistrerRapportTextuelUTF8(this.fournirFileTxtUTF8());
			this.enregistrerRapportCsvUTF8(this.fournirFileCsvUTF8());

		} // Fin de if (pEnregistrerRapport).________________________
		
	} // Fin de ajouterLigneRapportFavorableNiveauFichier(
	 // String pMessage
	 // , boolean pEnregistrerRapport).____________________________________
	

	
	/**
	 * method ajouterLigneRapportDefavorableNiveauFichier(
	 * String pMessage
	 * , boolean pEnregistrerRapport) :<br/>
	 * <ul>
	 * <li>Crée une ligne de rapport de contrôle 
	 * avec un message spécifique à chaque Contrôle concret pMessage  
	 * en cas d'insuccès du contrôle pour l'ensemble du fichier et l'ajoute 
	 * au rapport de contrôle.</li><br/>
	 * <li>Ajoute cette ligne de rapport au 
	 * rapport de contrôle 'this.rapport'.</li><br/>
	 * <li>Enregistre éventuellement 
	 * le rapport sur disque.</li><br/>
	 * </ul>
	 * <br/>
	 *
	 * @param pMessage : String : message spécifique à chaque Contrôle concret 
	 * en cas d'insuccès du contrôle.<br/>
	 * @param pEnregistrerRapport : boolean : 
	 * true si on veut enregistrer le rapport dans un fichier sur disque.<br/>
	 */
	 protected final void ajouterLigneRapportDefavorableNiveauFichier(
			final String pMessage,
				final boolean pEnregistrerRapport) {

		/* Création d'une ligne de rapport. */
		final LigneRapport ligneRapport 
			= creerLigneRapport(null
						, pMessage
						, null
						, SANS_OBJET
						, SANS_OBJET
						, false
						, ACTION_FICHIER_REFUSE);

		/* Ajoute la ligne de rapport à this.rapport. */
		this.ajouterLigneRapport(ligneRapport);

		/* Enregistrement du rapport sur disque. */
		if (pEnregistrerRapport) {

			this.enregistrerRapportTextuelUTF8(this.fournirFileTxtUTF8());
			this.enregistrerRapportCsvUTF8(this.fournirFileCsvUTF8());

		} // Fin de if (pEnregistrerRapport).________________________
		
	} // Fin de ajouterLigneRapportDefavorableNiveauFichier(
	 // String pMessage
	 // , boolean pEnregistrerRapport).____________________________________
	 
	 
	 
	/**
	 * method initialiserBundleControles() :<br/>
	 * Initialise bundleControles qui encapsule 
	 * messagescontroles_fr_FR.properties.<br/>
	 * <br/>
	 * - retourne sans rien faire et LOG de niveau INFO 
	 * si messagescontroles_fr_FR.properties est manquant.<br/>
	 * <br/>
	 */
	private static void initialiserBundleControles() {
		
		/* Bloc static synchronized. */
		synchronized (AbstractControle.class) {
			
			if (bundleControles == null) {
				
				try {
					
					/*
					 * Charge le ResourceBundle encapsulant
					 * messagescontroles_fr_FR.properties
					 */
					bundleControles = ResourceBundle.getBundle(
							"messagescontroles", LOCALE_FR_FR);
					
					
				} catch (MissingResourceException mre) {
					
					final String message 
						= "messagescontroles_fr_FR.properties manquant "
								+ "sous la racine du projet (bin) : " 
								+ mre.getMessage();
					
					/* LOG de niveau INFO. */
					if (LOG.isInfoEnabled()) {
						
						final String messageLog = CLASSE_ABSTRACTCONTROLE 
								+ SEP_MOINS 
								+ METHODE_INITIALISER_BUNDLE_CONTROLES 
								+ SEP_MOINS 
								+ message;
						
						LOG.info(messageLog, mre);
					}
									
					/* retourne sans rien faire et LOG de niveau INFO 
					 * si messagescontroles_fr_FR.properties est manquant. */
					return;
					
				} 
				
			} // Fin de if (bundleControles == null).____________________
						
		} // Fin du bloc static synchronized.___________
				
	} // Fin de initialiserBundleControles().______________________________


	
	/**
	 * method traiterMauvaisFile(
	 * File pFile
	 * , boolean pEnregistrerRapport
	 * , String pMethode) :<br/>
	 * <ul>
	 * <li>Centralise le traitement des fichiers incorrects.</li><br/>
	 * <li>Utilise le nom de la classe concrète appelante.</li><br/>
	 * <li>Utilise le nom de la méthode appelante.</li><br/>
	 * <li>Traite le cas des fichiers null, inexistants
	 * , répertoires ou vide.</li><br/>
	 * </ul>
	 * - retourne false si c'est le cas.<br/>
	 * - Génère un rapport en cas de mauvais fichier.<br/>
	 * - Ecrit le rapport (comme un rapport de contrôle ) 
	 * sur disque si pEnregistrerRapport vaut true.<br/>
	 * <br/>
	 * <ul>
	 * <li>retourne false, LOG de niveau INFO et rapport 
	 * si pFile == null.</li><br/>
	 * <li>retourne false, LOG de niveau INFO et rapport 
	 * si pFile est inexistant.</li><br/>
	 * <li>retourne false, LOG de niveau INFO et rapport 
	 * si pFile est un répertoire.</li><br/>
	 * <li>retourne false, LOG de niveau INFO et rapport 
	 * si pFile est vide.</li><br/>
	 * </ul>
	 * <br/>
	 *  Retourne true si le fichier n'est pas :<br/>
	 * - null.<br/>
	 * - inexistant.<br/>
	 * - répertoire.<br/>
	 * - vide.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pEnregistrerRapport : boolean : 
	 * true si on veut enregistrer le rapport dans un fichier sur disque.<br/>
	 * @param pMethode : String : Méthode qui appelle la présente.<br/>
	 * 
	 * @return : boolean : false si pFile est mauvais.<br/>
	 */
	protected final boolean traiterMauvaisFile(
			final File pFile
				, final boolean pEnregistrerRapport
					, final String pMethode) {
		
		
		/* retourne false, LOG de niveau INFO 
		 * et rapport si pFile == null. */
		if (pFile == null) {
			
			/* LOG de niveau INFO. */
			loggerInfo(
					this.fournirNomClasseConcrete()
						, pMethode
							, MESSAGE_FICHIER_NULL);
			
			/* rapport. */
			final LigneRapport ligneRapport 
				= creerLigneRapport(
						null
						, MESSAGE_FICHIER_NULL
						, null
						, SANS_OBJET
						, SANS_OBJET
						, false
						, ACTION_FICHIER_REFUSE);
			
			this.ajouterLigneRapport(ligneRapport);
			
			/* Enregistrement du rapport sur disque. */
			if (pEnregistrerRapport) {
				
				this.enregistrerRapportTextuelUTF8(this.fournirFileTxtUTF8());
				this.enregistrerRapportCsvUTF8(this.fournirFileCsvUTF8());
				
			}
			
			/* retourne false, LOG de niveau INFO 
			 * et rapport si pFile == null. */
			return false;
			
		} // Fin de if (pFile == null)._______________
		
		/* retourne false, LOG de niveau INFO 
		 * et rapport si pFile est inexistant. */
		if (!pFile.exists()) {
			
			/* LOG de niveau INFO. */
			loggerInfo(
					this.fournirNomClasseConcrete()
						, pMethode
							, MESSAGE_FICHIER_INEXISTANT
								, pFile.getAbsolutePath());
			
			/* rapport. */
			final LigneRapport ligneRapport 
				= creerLigneRapport(
						null
						, MESSAGE_FICHIER_INEXISTANT + pFile.getAbsolutePath()
						, null
						, SANS_OBJET
						, SANS_OBJET
						, false
						, ACTION_FICHIER_REFUSE);
						
			this.ajouterLigneRapport(ligneRapport);
			
			/* Enregistrement du rapport sur disque. */
			if (pEnregistrerRapport) {
				
				this.enregistrerRapportTextuelUTF8(this.fournirFileTxtUTF8());
				this.enregistrerRapportCsvUTF8(this.fournirFileCsvUTF8());
				
			}
			
			/* retourne false, LOG de niveau INFO 
			 * et rapport si pFile est inexistant. */
			return false;
			
		} // Fin de if (!pFile.exists())._________________________
		
		/* retourne false, LOG de niveau INFO 
		 * et rapport si pFile est un répertoire. */
		if (pFile.isDirectory()) {
			
			/* LOG de niveau INFO. */
			loggerInfo(
					this.fournirNomClasseConcrete()
						, pMethode
							, MESSAGE_FICHIER_REPERTOIRE
								, pFile.getAbsolutePath());
			
			/* rapport. */
			final LigneRapport ligneRapport 
				= creerLigneRapport(
						null
						, MESSAGE_FICHIER_REPERTOIRE + pFile.getAbsolutePath()
						, null
						, SANS_OBJET
						, SANS_OBJET
						, false
						, ACTION_FICHIER_REFUSE);
						
			this.ajouterLigneRapport(ligneRapport);
			
			/* Enregistrement du rapport sur disque. */
			if (pEnregistrerRapport) {
				
				this.enregistrerRapportTextuelUTF8(this.fournirFileTxtUTF8());
				this.enregistrerRapportCsvUTF8(this.fournirFileCsvUTF8());
				
			}
			
			/* retourne false, LOG de niveau INFO 
			 * et rapport si pFile est un répertoire. */
			return false;
			
		} // Fin de if (pFile.isDirectory())._______________________

		
		/* retourne false, LOG de niveau INFO 
		 * et rapport si pFile est vide. */
		if (pFile.length() == 0) {
			
			/* LOG de niveau INFO. */
			loggerInfo(
					this.fournirNomClasseConcrete()
						, pMethode
							, MESSAGE_FICHIER_VIDE
								, pFile.getAbsolutePath());
			
			/* rapport. */
			final LigneRapport ligneRapport 
				= creerLigneRapport(
						null
						, MESSAGE_FICHIER_VIDE + pFile.getAbsolutePath()
						, null
						, SANS_OBJET
						, SANS_OBJET
						, false
						, ACTION_FICHIER_REFUSE);
						
			this.ajouterLigneRapport(ligneRapport);
			
			/* Enregistrement du rapport sur disque. */
			if (pEnregistrerRapport) {
				
				this.enregistrerRapportTextuelUTF8(this.fournirFileTxtUTF8());
				this.enregistrerRapportCsvUTF8(this.fournirFileCsvUTF8());
				
			}
			
			/* retourne false, LOG de niveau INFO 
			 * et rapport si pFile est vide. */
			return false;
			
		} // Fin de if (pFile.length() == 0)._______________________
		
		return true;
		
	} // Fin de traiterMauvaisFile(
	 // File pFile
	// , boolean pEnregistrerRapport
	// , String pMethode)._________________________________________________
	

	
	/**
	 * method traiterMauvaisFile(
	 * File pFile
	 * , String pMethode) :<br/>
	 * Centralise le traitement des fichiers incorrects.<br/>
	 * <br/>
	 * <ul>
	 * <li>retourne MESSAGE_FICHIER_NULL, LOG de niveau INFO et rapport 
	 * si pFile == null.</li><br/>
	 * <li>retourne MESSAGE_FICHIER_INEXISTANT, LOG de niveau INFO et rapport 
	 * si pFile est inexistant.</li><br/>
	 * <li>retourne MESSAGE_FICHIER_REPERTOIRE, LOG de niveau INFO et rapport 
	 * si pFile est un répertoire.</li><br/>
	 * <li>retourne MESSAGE_FICHIER_VIDE, LOG de niveau INFO et rapport 
	 * si pFile est vide.</li><br/>
	 * </ul>
	 * <br/>
	 *  Retourne null si le fichier n'est pas :<br/>
	 * - null.<br/>
	 * - inexistant.<br/>
	 * - répertoire.<br/>
	 * - vide.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @param pMethode : String : Méthode qui appelle la présente.<br/>
	 * 
	 * @return : String : null si le fichier est bon 
	 * et un message d'erreur si le fichier est mauvais.<br/>
	 */
	protected final String traiterMauvaisFile(
			final File pFile
				, final String pMethode) {
		
		/* retourne MESSAGE_FICHIER_NULL 
		 * , LOG de niveau INFO et rapport 
		 * si pFile est null. */
		if (pFile == null) {
			
			/* LOG de niveau INFO. */
			loggerInfo(
					this.fournirNomClasseConcrete()
						, pMethode
							, MESSAGE_FICHIER_NULL);
			
			/* rapport. */
			final LigneRapport ligneRapport 
				= creerLigneRapport(
						null
						, MESSAGE_FICHIER_NULL
						, null
						, SANS_OBJET
						, SANS_OBJET
						, false
						, ACTION_FICHIER_REFUSE);
			
			this.ajouterLigneRapport(ligneRapport);
			
			/* retour de MESSAGE_FICHIER_NULL. */
			return MESSAGE_FICHIER_NULL;
			
		} // Fin de if (pFile == null).__________________________
		
		/* retourne MESSAGE_FICHIER_INEXISTANT
		 * , LOG de niveau INFO et rapport 
		 * si pFile est inexistant. */
		if (!pFile.exists()) {
							
			/* LOG de niveau INFO. */
			loggerInfo(
					this.fournirNomClasseConcrete()
						, pMethode
							, MESSAGE_FICHIER_INEXISTANT
								, pFile.getAbsolutePath());
			
			/* rapport. */
			final LigneRapport ligneRapport 
				= creerLigneRapport(
						null
						, MESSAGE_FICHIER_INEXISTANT + pFile.getAbsolutePath()
						, null
						, SANS_OBJET
						, SANS_OBJET
						, false
						, ACTION_FICHIER_REFUSE);
			
			this.ajouterLigneRapport(ligneRapport);
			
			/* retour de MESSAGE_FICHIER_INEXISTANT. */
			return MESSAGE_FICHIER_INEXISTANT;
			
		} // Fin de if (!pFile.exists()).___________________________
		
		
		/* retourne MESSAGE_FICHIER_REPERTOIRE
		 * , LOG de niveau INFO et rapport 
		 * si pFile est un répertoire. */
		if (pFile.isDirectory()) {
			
			/* LOG de niveau INFO. */
			loggerInfo(
					this.fournirNomClasseConcrete()
						, pMethode
							, MESSAGE_FICHIER_REPERTOIRE
								, pFile.getAbsolutePath());
			
			/* rapport. */
			final LigneRapport ligneRapport 
				= creerLigneRapport(
						null
						, MESSAGE_FICHIER_REPERTOIRE + pFile.getAbsolutePath()
						, null
						, SANS_OBJET
						, SANS_OBJET
						, false
						, ACTION_FICHIER_REFUSE);
			
			this.ajouterLigneRapport(ligneRapport);
			
			/* retour de MESSAGE_FICHIER_REPERTOIRE. */
			return MESSAGE_FICHIER_REPERTOIRE;
			
		} // Fin de if (pFile.isDirectory())._______________________

		/* retourne MESSAGE_FICHIER_VIDE
		 * , LOG de niveau INFO et rapport 
		 * si pFile est vide. */
		if (pFile.length() == 0) {
			
			/* LOG de niveau INFO. */
			loggerInfo(
					this.fournirNomClasseConcrete()
						, pMethode
							, MESSAGE_FICHIER_VIDE
								, pFile.getAbsolutePath());
			
			/* rapport. */
			final LigneRapport ligneRapport 
				= creerLigneRapport(
						null
						, MESSAGE_FICHIER_VIDE + pFile.getAbsolutePath()
						, null
						, SANS_OBJET
						, SANS_OBJET
						, false
						, ACTION_FICHIER_REFUSE);
			
			this.ajouterLigneRapport(ligneRapport);
			
			/* retour de MESSAGE_FICHIER_VIDE. */
			return MESSAGE_FICHIER_VIDE;
			
		} // Fin de if (pFile.length() == 0).____________________
		
		return null;
		
	} // Fin de traiterMauvaisFile(
	 // File pFile
	// , String pMethode)._________________________________________________
	
	
	
	/**
	 * method fournirDateSystemeFormatee() :<br/>
	 * SERVICE ANNEXE.<br/>
	 * Retourne la date système au format dfDateTimeMilliFrancaise
	 * des dates-heures françaises avec millisecondes comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>.<br/>
	 * <br/>
	 *
	 * @return : String : la date système au format 
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 */
	public static final String fournirDateSystemeFormatee() {
		
		return fournirDateStringFormatee(new Date());
		
	} // Fin de fournirDateSystemeFormatee().______________________________


	
	/**
	 * method fournirDateStringFormatee(
	 * Date pDate) :<br/>
	 * SERVICE ANNEXE.<br/>
	 * Retourne la date pDate au format dfDateTimeMilliFrancaise 
	 * des dates-heures françaises avec millisecondes comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 * <br/>
	 * - retourne null si pDate == null.<br/>
	 * <br/>
	 * 
	 * @param pDate : java.util.Date.<br/>
	 *
	 * @return : String : la date pDate au format 
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 */
	public static final String fournirDateStringFormatee(
			final Date pDate) {
				
		/* Bloc static synchronized. */
		synchronized (AbstractControle.class) {
			
			/* retourne null si pDate == null. */
			if (pDate == null) {
				return null;
			}
			
			final DateFormat dfDateTimeMilliFrancaise 
				= new SimpleDateFormat(
					"dd/MM/yyyy-HH:mm:ss.SSS", LOCALE_FR_FR);
			
			return dfDateTimeMilliFrancaise.format(pDate);
			
		} // Fin du bloc static synchronized.___________
		
	} // Fin de fournirDateStringFormatee(
	 // Date pDate)._______________________________________________________
	

	
	/**
	 * method fournirDateAPartirDeStringFormatee(
	 * String pDateFormatee) :<br/>
	 * SERVICE ANNEXE.<br/>
	 * Retourne une Java.util.Date à partir d'une String formatée 
	 * selon le format dfDateTimeMilliFrancaise
	 * des dates-heures françaises avec millisecondes comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * "dd/MM/yyyy-HH:mm:ss.SSS".<br/>
	 * <br/>
	 * - retourne null si pDateFormatee est blank.<br/>
	 * - retourne null et LOG de niveau INFO si
	 * la date passée en paramètre ne respecte pas le format 
	 * 'dd/MM/yyyy-HH:mm:ss.SSS'.<br/>
	 * <br/>
	 *
	 * @param pDateFormatee : String : String formatée 
	 * selon le format dfDateTimeMilliFrancaise
	 * des dates-heures françaises avec millisecondes comme
	 * '25/02/1961-12:27:07.251'.<br/>
	 * 
	 * @return : Date : java.util.Date.<br/>
	 */
	public static final Date fournirDateAPartirDeStringFormatee(
			final String pDateFormatee) {
		
		/* Bloc static synchronized. */
		synchronized (AbstractControle.class) {
			
			/* retourne null si pDateFormattee est blank. */
			if (StringUtils.isBlank(pDateFormatee)) {
				return null;
			}
			
			Date resultat = null;
			
			try {
				
				final DateFormat dfDateTimeMilliFrancaise 
					= new SimpleDateFormat(
							"dd/MM/yyyy-HH:mm:ss.SSS", LOCALE_FR_FR);
				
				resultat = dfDateTimeMilliFrancaise.parse(pDateFormatee);
				
			} catch (ParseException parseExc) {

				final String message 
				= "la date passée en paramètre ne respecte pas "
						+ "le format 'dd/MM/yyyy-HH:mm:ss.SSS' "
						+ pDateFormatee
						+ " - Exception : "
						+ parseExc.getMessage();

				/* LOG de niveau INFO. */
				if (LOG.isInfoEnabled()) {

					final String messageLog = CLASSE_ABSTRACTCONTROLE
							+ SEP_MOINS + METHODE_FOURNIRDATEAPARTIRDESTRING
							+ SEP_MOINS + message;

					LOG.info(messageLog, parseExc);
				}

				/*
				 * retourne null et LOG de niveau INFO si
				 * la date passée en paramètre ne respecte pas le format 
				 * 'dd/MM/yyyy-HH:mm:ss.SSS'.
				 */
				return null;
			}
			
			return resultat;
			
		} // Fin du bloc static synchronized.___________
		
	} // Fin de fournirDateAPartirDeStringFormatee(
	 // String pDateFormatee)._____________________________________________
	
	
	
	/**
	 * method fournirNomClasseConcrete() :<br/>
	 * Retourne le nom de la classe concrète de contrôle.<br/>
	 * Utile pour les messages dans les Logs et rapports.<br/>
	 * <br/>
	 *
	 * @return : String : nom de la classe concrète de contrôle.<br/>
	 */
	protected abstract String fournirNomClasseConcrete();
	

	
	/**
	 * method fournirDate(
	 * Date pDate) :<br/>
	 * - retourne la date système si pDate == null.<br/>
	 * - retourne pDate sinon.<br/>
	 * <br/>
	 *
	 * @param pDate : java.util.Date.<br/>
	 * 
	 * @return : Date : date système ou pDate.<br/>
	 */
	private Date fournirDate(
			final Date pDate) {
		
		/* retourne la date système si pDate == null. */
		if (pDate == null) {
			return new Date();
		}
		
		/* retourne pDate sinon. */
		return pDate;
		
	} // Fin de fournirDate(
	 // Date pDate)._______________________________________________________
	

	
	/**
	 * method fournirDateFormatee(
	 * Date pDate) :<br/>
	 * Fournit une date sous forme de String formattée 
	 * au format dfDatetimemilliFrancaiseLexico.<br/>
	 * Format des dates-heures françaises lexicographique 
	 * avec millisecondes comme
	 * '1961-01-25_12-27-07-251'.<br/>
	 * "yyyy-MM-dd_HH-mm-ss-SSS".<br/>
	 * <br/>
	 * - retourne null si pDate == null.<br/>
	 * <br/>
	 *
	 * @param pDate : java.util.Date.<br/>
	 * 
	 * @return : String : "yyyy-MM-dd_HH-mm-ss-SSS".<br/>
	 */
	private String fournirDateFormatee(
			final Date pDate) {
		
		/* retourne null si pDate == null. */
		if (pDate == null) {
			return null;
		}
		
		final String dateFormatee 
			= this.dfDatetimemilliFrancaiseLexico.format(pDate);
		
		return dateFormatee;
		
	} // Fin de fournirDateFormatee(
	 // Date pDate)._______________________________________________________
	
	
	
	/**
	 * method fournirUserName(
	 * String pUserName) :<br/>
	 * - retourne 'Administrateur' si pUsername == null.<br/>
	 * - retourne pUserName sinon.<br/>
	 * <br/>
	 *
	 * @param pUserName : String.<br/>
	 * 
	 * @return : String : pUserName si pas null ou 'Administrateur .<br/>
	 */
	private String fournirUserName(
			final String pUserName) {
		
		/* retourne 'Administrateur' si pUsername == null. */
		if (pUserName == null) {
			return "Administrateur";
		}
		
		/* retourne pUserName sinon. */
		return pUserName;
		
	} // Fin de fournirUserName(
	 // String pUserName)._________________________________________________
	

	
	/**
	 * method fournirNomFichier(
	 * File pFile) :<br/>
	 * retourne le nom court (sans tout le chemin) de pFile.<br/>
	 * <br/>
	 * - retourne null si pFile == null.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * 
	 * @return : String : nom du fichier.<br/>
	 */
	private String fournirNomFichier(
			final File pFile) {
		
		/* retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		/* retourne le nom court (sans tout le chemin) de pFile. */
		return pFile.getName();
		
	} // Fin de fournirNomFichier(
	 // File pFile)._______________________________________________________
	
	
	
	/**
	 * method fournirTypeControle() :<br/>
	 * Assure que toutes les classes concrètes fourniront un typeControle.<br/>
	 * <br/>
	 *
	 * @return : String : typeControle.<br/>
	 */
	protected abstract String fournirTypeControle();

	
	
	/**
	 * method fournirNomControle() :<br/>
	 * Assure que toutes les classes concrètes fourniront un nomControle.<br/>
	 * <br/>
	 *
	 * @return : String : nomControle.<br/>
	 */
	protected abstract String fournirNomControle();
	
	
	/**
	 * method fournirNomCritere() :<br/>
	 * Assure que toutes les classes concrètes fourniront un nomCritere.<br/>
	 * <br/>
	 *
	 * @return : String : nomCritere.<br/>
	 */
	protected abstract String fournirNomCritere();

	
	
	/**
	 * method fournirGravite() :<br/>
	 * - remplit this.niveauAnomalie.<br/>
	 * - remplit this.estBloquant.
	 * - Constitue la clé de la gravité (label) 
	 * de l'anomalie dans messagescontroles_fr_FR.properties 
	 * comme "label.niveau1".<br/>
	 * Retourne le label associé au niveau d'anomalie
	 * dans messagescontroles_fr_FR.properties.<br/>
	 * Par exemple :<br/>
	 * - label.niveau0 = 0 - indéfini,<br/>
	 * - label.niveau1 = 1 - anomalie bloquante<br/>
	 * <br/>
	 * - retourne null si la clé n'est pas trouvée.<br/>
	 * <br/>
	 *
	 * @return : String : la gravité (label du niveau de l'anomalie).<br/>
	 */
	private String fournirGravite() {
		
		/* remplit this.niveauAnomalie. */
		this.niveauAnomalie = this.fournirNiveauAnomalie(null);
		
		/* remplit this.estBloquant. */
		this.estBloquant = this.fournirEstBloquant(this.niveauAnomalie);
		
		/* Constitue la clé de la gravité (label) 
		 * de l'anomalie dans messagescontroles_fr_FR.properties. */
		final String cleLabelAnomalie = "label.niveau" + this.niveauAnomalie;
		
		/* Retourne le label associé au niveau d'anomalie
		 * dans messagescontroles_fr_FR.properties si il existe.*/
		if (bundleControles.containsKey(cleLabelAnomalie)) {
			return bundleControles.getString(cleLabelAnomalie);
		}
		
		/* retourne null si la clé n'est pas trouvée. */
		return null;		
		
	} // Fin de fournirGravite().__________________________________________

	
	
	/**
	 * method fournirNiveauAnomalie(
	 * String pNiveauAnomalie) :<br/>
	 * - retourne pNiveauAnomalie si pNiveauAnomalie != null.<br/>
	 * - Sinon, récupère niveauAnomalie dans 
	 * messagescontroles_fr_FR.properties et le retourne si il existe.<br/>
	 * - Sinon, retourne le niveau d'anomalie du contrôle 
	 * stocké en dur dans la classe concrète.<br/>
	 * <br/>
	 *
	 * @param pNiveauAnomalie : String : niveau d'anomalie 
	 * comme "1" pour bloquant.<br/>
	 * 
	 * @return : String : le niveau d'anomalie du contrôle.<br/>
	 */
	private String fournirNiveauAnomalie(
			final String pNiveauAnomalie) {
		
		/* retourne pNiveauAnomalie si pNiveauAnomalie != null. */
		if (pNiveauAnomalie != null) {
			return pNiveauAnomalie;
		}
		
		/* Sinon, récupère niveauAnomalie dans 
		 * messagescontroles_fr_FR.properties et le retourne si il existe. */
		final String niveauAnomalieDansProperties 
			= this.recupererNiveauAnomalieDansProperties();
		
		if (niveauAnomalieDansProperties != null) {
			return niveauAnomalieDansProperties;
		}
		
		/* Sinon, retourne le niveau d'anomalie du contrôle 
		 * stocké en dur dans la classe concrète. */
		return this.fournirNiveauAnomalieEnDur();
		
	} // Fin de fournirNiveauAnomalie(
	 // String pNiveauAnomalie).___________________________________________
	

	
	/**
	 * method fournirEstBloquant(
	 * String pNiveauAnomalie) :<br/>
	 * Décide si le contrôle est bloquant 
	 * en fonction de son niveauAnomalie.<br/>
	 * <br/>
	 * - retourne true si pNiveauAnomalie nettoyé vaut "1".<br/>
	 * - retourne false sinon.<br/>
	 * <br/>
	 * - retourne false si pNiveauAnomalie est blank.<br/>
	 * <br/>
	 *
	 * @param pNiveauAnomalie : String : "1" pour bloquant.<br/>
	 * 
	 * @return : boolean : true si le contrôle est bloquant.<br/>
	 */
	private boolean fournirEstBloquant(
			final String pNiveauAnomalie) {
		
		/* retourne false si pNiveauAnomalie est blank. */
		if (StringUtils.isBlank(pNiveauAnomalie)) {
			return false;
		}
		
		/* retourne true si pNiveauAnomalie nettoyé vaut "1". */
		if (StringUtils.equals("1", StringUtils.trim(pNiveauAnomalie))) {
			return true;
		}
		
		/* retourne false sinon. */
		return false;
		
	} // Fin de fournirEstBloquant(
	 // String pNiveauAnomalie).___________________________________________
	
	
	
	/**
	 * method recupererNiveauAnomalieDansProperties() :<br/>
	 * récupère le niveau d'anomalie du contrôle dans 
	 * messagescontroles_fr_FR.properties.<br/>
	 * <br/>
	 * - Nettoie le niveau d'anomalie lu dans 
	 * messagescontroles_fr_FR.properties en retirant les éventuels blancs.<br/>
	 * <br/>
	 * - retourne null, LOG de niveau INFO et rapport 
	 * si bundleControles est null.<br/>
	 * - retourne null, LOG de niveau INFO et rapport 
	 * si messagescontroles_fr_FR.properties est manquant.<br/>
	 * - retourne null, LOG de niveau INFO et rapport 
	 * si messagescontroles_fr_FR.properties ne contient pas la clé.<br/>
	 * - retourne null, LOG de niveau INFO et rapport si
	 * messagescontroles_fr_FR.properties ne contient pas la valeur 
	 * associée à la clé.<br/>
	 * <br/>
	 *
	 * @return : String : niveauAnomalie.<br/>
	 */
	private String recupererNiveauAnomalieDansProperties() {

		String resultat = null;
		String resultatNettoye = null;
		
		try {
						
			/* récupère le niveau d'anomalie du contrôle 
			 * dans messagescontroles_fr_FR.properties. */
			if (bundleControles != null) {
				
				resultat 
				= bundleControles.getString(
						this.fournirCleNiveauAnomalie());
				
				/* Nettoie le niveau d'anomalie lu dans 
				 * messagescontroles_fr_FR.properties en retirant 
				 * les éventuels blancs. */
				resultatNettoye = StringUtils.trim(resultat);
				
			}
			
			/* retourne null, LOG de niveau INFO et rapport 
			 * si bundleControles est null.*/
			else {
				
				final String message 
					= "bundleControles est null (non initialisé)";
			
			/* LOG de niveau INFO. */
			loggerInfo(
					this.fournirNomClasseConcrete()
					, METHODE_RECUPERERNIVEAUANOMALIEDANSPROPERTIES
					, message);
			
			/* rapport. */
			final LigneRapport ligneRapport 
				= new LigneRapport(
						this.dateControleStringFormatee
						, this.userName
						, NULL
						, TOUS
						, TOUS
						, "bundleControles non initialisé"
						, SANS_OBJET
						, null
						, message
						, null
						, SANS_OBJET
						, SANS_OBJET
						, SANS_OBJET);
			
			this.ajouterLigneRapport(ligneRapport);
			
			/* retourne null, LOG de niveau INFO et rapport 
			 * si bundleControles est null. */
			return null;
			
			}
			
			
		} catch (MissingResourceException mre) {

			final String message = "La clé '"
					+ this.fournirCleNiveauAnomalie()
					+ "' n'existe pas dans  messagescontroles_fr_FR.properties : "
					+ mre.getMessage();

			/* LOG de niveau INFO. */
			loggerInfo(this.fournirNomClasseConcrete(),
					METHODE_RECUPERERNIVEAUANOMALIEDANSPROPERTIES, message);

			/* rapport. */
			final LigneRapport ligneRapport = new LigneRapport(
					this.dateControleStringFormatee,
					this.userName,
					NULL,
					this.typeControle,
					this.nomControle,
					"pas de clé ou valeur dans messagescontroles_fr_FR.properties",
					SANS_OBJET, null, message, null, SANS_OBJET, SANS_OBJET,
					SANS_OBJET);

			this.ajouterLigneRapport(ligneRapport);

			/*
			 * retourne null, LOG de niveau INFO et rapport si
			 * messagescontroles_fr_FR.properties ne contient pas la clé.
			 */
			return null;

		} 
		
		if (StringUtils.isBlank(resultatNettoye)) {
			
			final String message = "La valeur associée à La clé '"
					+ this.fournirCleNiveauAnomalie()
					+ "' n'existe pas dans  messagescontroles_fr_FR.properties";

			/* LOG de niveau INFO. */
			loggerInfo(this.fournirNomClasseConcrete(),
					METHODE_RECUPERERNIVEAUANOMALIEDANSPROPERTIES, message);

			/* rapport. */
			final LigneRapport ligneRapport = new LigneRapport(
					this.dateControleStringFormatee,
					this.userName,
					NULL,
					this.typeControle,
					this.nomControle,
					"pas de clé ou valeur dans messagescontroles_fr_FR.properties",
					SANS_OBJET, null, message, null, SANS_OBJET, SANS_OBJET,
					SANS_OBJET);

			this.ajouterLigneRapport(ligneRapport);

			/*
			 * retourne null, LOG de niveau INFO et rapport si
			 * messagescontroles_fr_FR.properties ne contient 
			 * pas la valeur associée à la clé.
			 */
			return null;
		}
		
		/* retourne le niveau d'anomalie 
		 * dans messagescontroles_fr_FR.properties. */
		return resultatNettoye;
		
	} // Fin de recupererNiveauAnomalieDansProperties().___________________
	

	
	/**
	 * method fournirCleNiveauAnomalie() :<br/>
	 * Retourne la clé du niveau d'anomalie du contrôle 
	 * dans messagescontroles_fr_FR.properties.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * "ControleurTypeTexte.niveau.anomalie".<br/>
	 * <br/>
	 *
	 * @return : String : clé du niveau d'anomalie du contrôle 
	 * dans messagescontroles_fr_FR.properties.<br/>
	 */
	protected abstract String fournirCleNiveauAnomalie();
	

	
	/**
	 * method fournirNiveauAnomalieEnDur() :<br/>
	 * Retourne le niveau d'anomalie du contrôle 
	 * stocké en dur dans la classe concrète.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * "1" pour un contrôle bloquant.<br/>
	 * <br/>
	 *
	 * @return : String : niveau d'anomalie du contrôle stocké en dur 
	 * dans la classe concrète.<br/>
	 */
	protected abstract String fournirNiveauAnomalieEnDur();
	

	
	/**
	 * method fournirAEffectuer() :<br/>
	 * retourne true si un contrôle doit être effectué 
	 * dans un enchaînement de contrôles.<br/>
	 * <ul>
	 * <li>Va chercher dans messagescontroles_fr_FR.properties 
	 * si le contrôle doit être effectué dans un 
	 * enchaînement de contrôles.</li><br/>
	 * <li>Sinon, lit aEffectuer dans la méthode 
	 * fournirAEffectuerEnDur() de la classe concrète.</li><br/>
	 * </ul>
	 * <br/>
	 *
	 * @return : boolean : this.aEffectuer.<br/>
	 */
	private boolean fournirAEffectuer() {
		
		if (bundleControles != null) {
			
			if (!StringUtils.isBlank(this.fournirCleAEffectuer())) {
				
				/* Récupération de la clé dans
				 *  messagescontroles_fr_FR.properties. */
				final String valeurAEffectuerDansProperties 
					= bundleControles.getString(this.fournirCleAEffectuer());
				
				/* Si la clé existe. */
				if (!StringUtils.isBlank(valeurAEffectuerDansProperties)) {
					return fournirBooleanAPartirDeString(
							valeurAEffectuerDansProperties);
				}
			}
			
		}
		
		return this.fournirAEffectuerEnDur();
		
	} // Fin de fournirAEffectuer()._______________________________________
	

	
	/**
	 * method fournirBooleanAPartirDeString(
	 * String pString) :<br/>
	 * Fournit un boolean à partir d'une String.<br/>
	 * Retourne true si pString == "1" ou "true" quelle que soit a casse.<br/>
	 * <br/>
	 * <ul>
	 * <li>nettoie pString (trim).</li><br/>
	 * </ul>
	 * <br/>
	 *
	 * @param pString : String : la String dont on veut extraire un Boolean.<br/>
	 * 
	 * @return : Boolean : Résultat de l'extraction.<br/>
	 */
	private Boolean fournirBooleanAPartirDeString(
			final String pString) {
		
		/* nettoie pString (trim). */
		final String maString = StringUtils.trim(pString);
		
		if (StringUtils.equals("1", maString)) {
			return true;
		}
		else if (StringUtils.equalsIgnoreCase("true", maString)) {
			return true;
		}
		
		return false;
		
	} // Fin de fournirBooleanAPartirDeString(
	 // String pString).___________________________________________________
	
	
	
	/**
	 * method fournirCleAEffectuer() :<br/>
	 * Retourne la clé de aEffectuer dans 
	 * messagescontroles_fr_FR.properties.<br/>
	 * A implémenter dans chaque classe concrète.<br/>
	 * <br/>
	 * "ControleurTypeTexte.aEffectuer" par exemple.<br/>
	 *
	 * @return : String : clé de aEffectuer dans 
	 * messagescontroles_fr_FR.properties.<br/>
	 */
	protected abstract String fournirCleAEffectuer();
	

	
	/**
	 * method fournirAEffectuerEnDur() :<br/>
	 * Retourne une valeur en dur pour aEffectuer.<br/>
	 * A implémenter dans chaque classe concrète.<br/>
	 * <br/>
	 * <br/>
	 *
	 * @return : boolean : true si le contrôle doit être effectué 
	 * dans un enchaînement de contrôles.<br/>
	 */
	protected abstract boolean fournirAEffectuerEnDur();
	
	
	
	/**
	 * method loggerInfo(
	 * String pClasse
	 * , String pMethode
	 * , String pMessage) :<br/>
	 * Créée un message de niveau INFO dans le LOG.<br/>
	 * <br/>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pMessage : String : Message particulier de la méthode.<br/>
	 */
	protected final void loggerInfo(
			final String pClasse
				, final String pMethode
					, final String pMessage) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null || pMessage == null) {
			return;
		}
		
		/* LOG de niveau INFO. */			
		if (LOG.isInfoEnabled()) {
			
			final String message 
			= pClasse 
			+ SEP_MOINS
			+ pMethode
			+ SEP_MOINS
			+ pMessage;
			
			LOG.info(message);
		}
		
	} // Fin de la classe loggerInfo(
	 // String pClasse
	 // , String pMethode
	 // , String pMessage).________________________________________________
	

	
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
	protected final void loggerInfo(
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
	 * method loggerError(
	 * String pClasse
	 * , String pMethode
	 * , Exception pException) :<br/>
	 * Créée un message de niveau ERROR dans le LOG.<br/>
	 * <br/>
	 * - Ne fait rien si un des paramètres est null.<br/>
	 * <br/>
	 *
	 * @param pClasse : String : Classe appelante.<br/>
	 * @param pMethode : String : Méthode appelante.<br/>
	 * @param pException : Exception : Exception transmise 
	 * par la méthode appelante.<br/>
	 */
	protected final void loggerError(
			final String pClasse
				, final String pMethode
					, final Exception pException) {
		
		/* Ne fait rien si un des paramètres est null. */
		if (pClasse == null || pMethode == null || pException == null) {
			return;
		}
		
		/* LOG de niveau ERROR. */			
		if (LOG.isErrorEnabled()) {
			
			final String message 
			= pClasse 
			+ SEP_MOINS
			+ pMethode
			+ SEP_MOINS 
			+ pException.getMessage();
			
			LOG.error(message, pException);
			
		}
		
	} // Fin de loggerError(
	 // String pClasse
	 // , String pMethode
	 // , Exception pException).___________________________________________


	
	 /**
	  * SERVICE ANNEXE.<br/>
	  * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String lireFichierEnUTF8(
			final File pFile) {
		return this.lireFichier(pFile, CHARSET_UTF8);
	} // Fin de lireFichierEnUTF8(
	// File pFile).________________________________________________________

	
	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String lireFichierEnAscii(
			final File pFile) {
		return this.lireFichier(pFile, CHARSET_US_ASCII);
	} // Fin de lireFichierEnAscii(
	// File pFile).________________________________________________________
	

	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String lireFichierEnLatin1(
			final File pFile) {
		return this.lireFichier(pFile, CHARSET_ISO_8859_1);
	} // Fin de lireFichierEnLatin1(
	// File pFile).________________________________________________________
	

	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String lireFichierEnLatin2(
			final File pFile) {
		return this.lireFichier(pFile, CHARSET_ISO_8859_2);
	} // Fin de lireFichierEnLatin2(
	// File pFile).________________________________________________________
	

	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String lireFichierEnLatin9(
			final File pFile) {
		return this.lireFichier(pFile, CHARSET_ISO_8859_15);
	} // Fin de lireFichierEnLatin9(
	// File pFile).________________________________________________________
	

	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String lireFichierEnAnsi(
			final File pFile) {
		return this.lireFichier(pFile, CHARSET_WINDOWS_1252);
	} // Fin de lireFichierEnAnsi(
	// File pFile).________________________________________________________
	

	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String lireFichierEnIbm850(
			final File pFile) {
		return this.lireFichier(pFile, CHARSET_IBM850);
	} // Fin de lireFichierEnIbm850(
	// File pFile).________________________________________________________

	
	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String lireFichier(
			final File pFile
				, final Charset pCharset) {
		
		// TRAITEMENT DES MAUVAIS FICHIERS 
		// (null, inexistant, répertoire, vide).*************************
		final String resultatTraitementFichier 
			= this.traiterMauvaisFile(pFile, METHODE_LIREFICHIER);
		
		if (resultatTraitementFichier != null) {
			return resultatTraitementFichier;
		}
		
		// RAFRAICHISSEMENT DE this.fichier.*****************************
		/* passe pFile à this.fichier et 
		 * rafraîchit automatiquement this.nomFichier. */
		this.setFichier(pFile);
	
		
		// LECTURE ***************
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		int characterEntier = 0;
		Character character = null;
		
		final StringBuilder stb = new StringBuilder();

		Charset charset = null;

		/* Choisit automatiquement le CHARSET_UTF8 si pCharset == null. */
		if (pCharset == null) {
			charset = CHARSET_UTF8;
		} else {
			charset = pCharset;
		}

		try {

			/*
			 * Instancie un flux en lecture fileInputStream en lui passant
			 * pFile.
			 */
			fileInputStream 
				= new FileInputStream(pFile);

			/*
			 * Instancie un InputStreamReader 
			 * en lui passant le FileReader et le
			 * Charset.
			 */
			inputStreamReader 
				= new InputStreamReader(fileInputStream, charset);

			/*
			 * Instancie un tampon de flux de caractères en lecture en lui
			 * passant le flux inputStreamReader.
			 */
			bufferedReader 
				= new BufferedReader(inputStreamReader);
			
			/* Parcours du bufferedReader. */
			while (true) {
				
				/* Lecture de chaque caractère. */
				characterEntier = bufferedReader.read();
				
				/* Arrêt de la lecture si fin de fichier. */
				if (characterEntier < 0) {
					break;
				}
				
				/* Conversion de l'entier en caractère. */
				character = (char) characterEntier;
								
				/* Ajout du caractère au StringBuilder. */
				stb.append(character);
				
			} // Fin du parcours du bufferedReader._________

		} catch (FileNotFoundException fnfe) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasseConcrete()
						, METHODE_LIREFICHIER
							, fnfe);
			
			/* retourne le message de l'exception. */
			return fnfe.getMessage();
			
		} catch (IOException ioe) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasseConcrete()
						, METHODE_LIREFICHIER
							, ioe);
			
			/* retourne le message de l'exception. */
			return ioe.getMessage();
		}
		
		finally {
			
			/* fermeture du flux BufferedReader. */
			if (bufferedReader != null) {
				
				try {
					
					bufferedReader.close();
					
				} catch (IOException ioe2) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIREFICHIER
									, ioe2);
					
				}
				
			} // Fin de if (bufferedReader != null).____
			
			/* fermeture du flux inputStreamReader. */
			if (inputStreamReader != null) {
				
				try {
					
					inputStreamReader.close();
					
				} catch (IOException ioe4) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIREFICHIER
									, ioe4);
				}
				
			} // Fin de if (inputStreamReader != null).______
			
			/* fermeture du flux fileInputStream. */
			if (fileInputStream != null) {
				
				try {
					
					fileInputStream.close();
					
				} catch (IOException ioe3) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIREFICHIER
									, ioe3);
					
				}
				
			} // Fin de if (fileInputStream != null).________
			
		} // Fin du finally._____________________________
		
		return stb.toString();
		
	} // Fin de lireFichier(
	 // File pFile
	 // , Charset pCharset)._______________________________________________


	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String lireFichierLigneParLigne(
			final File pFile) {
		
		return this.lireFichierLigneParLigne(pFile, CHARSET_UTF8);
		
	} // Fin de lireFichierLigneParLigne(
	// File pFile).________________________________________________________
	
	
	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String lireFichierLigneParLigne(
			final File pFile
				, final Charset pCharset) {
		
		// TRAITEMENT DES MAUVAIS FICHIERS 
		// (null, inexistant, répertoire, vide).*************************
		final String resultatTraitementFichier 
			= this.traiterMauvaisFile(
					pFile, METHODE_LIREFICHIERLIGNE_PAR_LIGNE);
		
		if (resultatTraitementFichier != null) {
			return resultatTraitementFichier;
		}
				
		// RAFRAICHISSEMENT DE this.fichier.*****************************
		/* passe pFile à this.fichier et 
		* rafraîchit automatiquement this.nomFichier. */
		this.setFichier(pFile);
		
		/* rafraîchit this.fichierEnMap. */
		this.fichierEnMap.clear();
	
		
		// LECTURE LIGNE PAR LIGNE ***************
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		final StringBuilder stb = new StringBuilder();
		
		String ligneLue = null;
		int numerolLigneLue = 0;

		Charset charset = null;

		/* Choisit automatiquement le CHARSET_UTF8 si pCharset == null. */
		if (pCharset == null) {
			charset = CHARSET_UTF8;
		} else {
			charset = pCharset;
		}

		try {

			/*
			 * Instancie un flux en lecture fileInputStream en lui passant
			 * pFile.
			 */
			fileInputStream = new FileInputStream(pFile);

			/*
			 * Instancie un InputStreamReader en lui passant le FileReader et le
			 * Charset.
			 */
			inputStreamReader = new InputStreamReader(fileInputStream, charset);

			/*
			 * Instancie un tampon de flux de caractères en lecture en lui
			 * passant le flux inputStreamReader.
			 */
			bufferedReader = new BufferedReader(inputStreamReader);
			
			/* Parcours du bufferedReader. */
			while (true) {
				
				/* Incrémentation du numéro de la ligne lue. */
				numerolLigneLue++;
				
				/* Lecture de chaque ligne. */
				ligneLue = bufferedReader.readLine();
								
				/* Arrêt de la lecture si fin de fichier. */
				if (ligneLue == null) {
					break;
				}
				
				/* - remplit this.fichierEnMap. */
				this.fichierEnMap.put(numerolLigneLue, ligneLue);
				
				/* Ajout de la ligne au StringBuilder. */
				stb.append(
						String.format(LOCALE_FR_FR
								, "Ligne : %-5d", numerolLigneLue));
				
				stb.append(
						String.format(LOCALE_FR_FR
								, "%-520s", ligneLue));
								
				
				stb.append(NEWLINE);
				
			} // Fin du parcours du bufferedReader._________

		} catch (FileNotFoundException fnfe) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasseConcrete()
						, METHODE_LIREFICHIERLIGNE_PAR_LIGNE
							, fnfe);
			
			/* retourne le message de l'exception. */
			return fnfe.getMessage();
			
		} catch (IOException ioe) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasseConcrete()
						, METHODE_LIREFICHIERLIGNE_PAR_LIGNE
							, ioe);
			
			/* retourne le message de l'exception. */
			return ioe.getMessage();
		}
		
		finally {
			
			/* fermeture du flux BufferedReader. */
			if (bufferedReader != null) {
				
				try {
					
					bufferedReader.close();
					
				} catch (IOException ioe2) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIREFICHIERLIGNE_PAR_LIGNE
									, ioe2);
					
				}
				
			} // Fin de if (bufferedReader != null).____
			
			/* fermeture du flux inputStreamReader. */
			if (inputStreamReader != null) {
				
				try {
					
					inputStreamReader.close();
					
				} catch (IOException ioe4) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIREFICHIERLIGNE_PAR_LIGNE
									, ioe4);
				}
				
			} // Fin de if (inputStreamReader != null).______
			
			/* fermeture du flux fileInputStream. */
			if (fileInputStream != null) {
				
				try {
					
					fileInputStream.close();
					
				} catch (IOException ioe3) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIREFICHIERLIGNE_PAR_LIGNE
									, ioe3);
					
				}
				
			} // Fin de if (fileInputStream != null).________
			
		} // Fin du finally._____________________________
		
		return stb.toString();
		
	} // Fin de lireFichierLigneParLigne(
	 // File pFile
	 // , Charset pCharset)._______________________________________________


		
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String lireLigneFichier(
			final int pNumeroLigne
				, final File pFile
					, final Charset pCharset) {
		
		
		// TRAITEMENT DES MAUVAIS FICHIERS 
		// (null, inexistant, répertoire, vide).*************************
		final String resultatTraitementFichier 
			= this.traiterMauvaisFile(pFile, METHODE_LIRELIGNEFICHIER);
		
		if (resultatTraitementFichier != null) {
			return resultatTraitementFichier;
		}
		
		// RAFRAICHISSEMENT DE this.fichier.*****************************
		/* passe pFile à this.fichier et 
		* rafraîchit automatiquement this.nomFichier. */
		this.setFichier(pFile);
	
		
		// LECTURE LIGNE PAR LIGNE ***************
		FileInputStream fileInputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;

		String ligneLue = null;
		int numeroLigneLue = 0;
		
		Charset charset = null;

		/* Choisit automatiquement le CHARSET_UTF8 si pCharset == null. */
		if (pCharset == null) {
			charset = CHARSET_UTF8;
		} else {
			charset = pCharset;
		}

		try {

			/*
			 * Instancie un flux en lecture fileInputStream en lui passant
			 * pFile.
			 */
			fileInputStream 
				= new FileInputStream(pFile);

			/*
			 * Instancie un InputStreamReader en lui passant 
			 * le FileReader et le Charset.
			 */
			inputStreamReader 
				= new InputStreamReader(fileInputStream, charset);

			/*
			 * Instancie un tampon de flux de caractères en lecture en lui
			 * passant le flux inputStreamReader.
			 */
			bufferedReader 
				= new BufferedReader(inputStreamReader);
			
			/* Parcours du bufferedReader. **************/
			while (true) {
				
				/* Incrémentation du numéro de la ligne lue. */
				numeroLigneLue++;
				
				/* Lecture de chaque ligne. */
				ligneLue = bufferedReader.readLine();
				
				/* Arrêt de la lecture si fin de fichier. */
				if (ligneLue == null) {
					break;
				}
				
				/* Retourne la ligneLue si c'est la pNumeroLigne-ème ligne. */
				if (numeroLigneLue == pNumeroLigne) {
					return ligneLue;
				}
				
			} // Fin du parcours du bufferedReader._________

		} catch (FileNotFoundException fnfe) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasseConcrete()
						, METHODE_LIRELIGNEFICHIER
							, fnfe);
			
			/* retourne le message de l'exception. */
			return fnfe.getMessage();
			
		} catch (IOException ioe) {
			
			/* LOG de niveau ERROR. */
			loggerError(
					this.fournirNomClasseConcrete()
						, METHODE_LIRELIGNEFICHIER
							, ioe);
			
			/* retourne le message de l'exception. */
			return ioe.getMessage();
		}
		
		finally {
			
			/* fermeture du flux BufferedReader. */
			if (bufferedReader != null) {
				
				try {
					
					bufferedReader.close();
					
				} catch (IOException ioe2) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIRELIGNEFICHIER
									, ioe2);
					
				}
				
			} // Fin de if (bufferedReader != null).____
			
			/* fermeture du flux inputStreamReader. */
			if (inputStreamReader != null) {
				
				try {
					
					inputStreamReader.close();
					
				} catch (IOException ioe4) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIRELIGNEFICHIER
									, ioe4);
				}
				
			} // Fin de if (inputStreamReader != null).______
			
			/* fermeture du flux fileInputStream. */
			if (fileInputStream != null) {
				
				try {
					
					fileInputStream.close();
					
				} catch (IOException ioe3) {
					
					/* LOG de niveau ERROR. */
					loggerError(
							this.fournirNomClasseConcrete()
								, METHODE_LIRELIGNEFICHIER
									, ioe3);
					
				}
				
			} // Fin de if (fileInputStream != null).________
			
		} // Fin du finally._____________________________
		
		return null;
				
	} // Fin de lireLigneFichier(
	 // int pNumeroLigne
	 // , File pFile
	 // , Charset pCharset)._______________________________________________
	

	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherFichierEnMap() {
		return this.afficherMapIntegerString(this.fichierEnMap);
	} // Fin de afficherFichierEnMap().____________________________________

	
	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherLigneDeFichierEnMap(
			final int pNumeroLigne) {
		
		return this.afficherLignedeMapIntegerString(
				this.fichierEnMap, pNumeroLigne);
		
	} // Fin de afficherLigneDeFichierEnMap(
	 // int pNumeroLigne)._________________________________________________

	
	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherMapIntegerString(
			final SortedMap<Integer, String> pMap) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		final Set<Entry<Integer, String>> set = pMap.entrySet();
		
		if (set == null) {
			return null;
		}
		
		final Iterator<Entry<Integer, String>> ite = set.iterator();
		
		if (ite == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		/* Parcours de l'iterator. */
		while (ite.hasNext()) {
			
			final Entry<Integer, String> entry = ite.next();
			
			if (entry == null) {
				return null;
			}
			
			final int numerolLigneLue = entry.getKey();
			final String ligneLue = entry.getValue();
							
			/* Ajout de la ligne au StringBuilder. */
			stb.append(
					String.format(LOCALE_FR_FR
							, "Ligne : %-5d", numerolLigneLue));
			
			stb.append(
					String.format(LOCALE_FR_FR
							, "%-520s", ligneLue));
										
			stb.append(NEWLINE);
														
		} // Fin de Parcours de l'iterator.______________________
		
		/* Retour de la ligne. */
		return stb.toString();
		
	} // Fin de afficherMapIntegerString(
	 // SortedMap<Integer, String> pMap).__________________________________
	

	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherLignedeMapIntegerString(
			final SortedMap<Integer, String> pMap
				, final int pNumeroLigne) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		final Set<Entry<Integer, String>> set = pMap.entrySet();
		
		if (set == null) {
			return null;
		}
		
		final Iterator<Entry<Integer, String>> ite = set.iterator();
		
		if (ite == null) {
			return null;
		}
		
		/* Parcours de l'iterator. */
		while (ite.hasNext()) {
			
			final Entry<Integer, String> entry = ite.next();
			
			if (entry == null) {
				return null;
			}
			
			final int numerolLigneLue = entry.getKey();
			final String ligneLue = entry.getValue();
			
			/* Test du numéro de la ligne. */
			if (numerolLigneLue == pNumeroLigne) {
				
				final StringBuilder stb = new StringBuilder();
				
				/* Ajout de la ligne au StringBuilder. */
				stb.append(
						String.format(LOCALE_FR_FR
								, "Ligne : %-5d", numerolLigneLue));
				
				stb.append(
						String.format(LOCALE_FR_FR
								, "%-520s", ligneLue));
								
				
				stb.append(NEWLINE);
				
				/* Retour de la ligne. */
				return stb.toString();
				
			} // Fin de if (numerolLigneLue == pNumeroLigne).________
						
		} // Fin de Parcours de l'iterator.______________________
		
		/* retourne null si la ligne n'existe pas. */
		return null;
		
	} // Fin de afficherLignedeMapIntegerString(
	 // SortedMap<Integer, String> pMap
	 // , int pNumeroLigne)._______________________________________________
	
	
	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String listerFichierEnMap() {
		return this.listerMapIntegerString(this.fichierEnMap);
	} // Fin de listerFichierEnMap().______________________________________

	
	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String listerLigneDeFichierEnMap(
			final int pNumeroLigne) {
		
		return this.listerLignedeMapIntegerString(
				this.fichierEnMap, pNumeroLigne);
		
	} // Fin de listerLigneDeFichierEnMap(
	 // int pNumeroLigne)._________________________________________________

	
	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String listerMapIntegerString(
			final SortedMap<Integer, String> pMap) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		final Set<Entry<Integer, String>> set = pMap.entrySet();
		
		if (set == null) {
			return null;
		}
		
		final Iterator<Entry<Integer, String>> ite = set.iterator();
		
		if (ite == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		/* Parcours de l'iterator. */
		while (ite.hasNext()) {
			
			final Entry<Integer, String> entry = ite.next();
			
			if (entry == null) {
				return null;
			}
			
			final String ligneLue = entry.getValue();
									
			stb.append(ligneLue);										
			stb.append(NEWLINE);
														
		} // Fin de Parcours de l'iterator.______________________
		
		/* Retour de la ligne. */
		return stb.toString();
		
	} // Fin de listerMapIntegerString(
	 // SortedMap<Integer, String> pMap).__________________________________
	

	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String listerLignedeMapIntegerString(
			final SortedMap<Integer, String> pMap
				, final int pNumeroLigne) {
		
		/* retourne null si pMap == null. */
		if (pMap == null) {
			return null;
		}
		
		final Set<Entry<Integer, String>> set = pMap.entrySet();
		
		if (set == null) {
			return null;
		}
		
		final Iterator<Entry<Integer, String>> ite = set.iterator();
		
		if (ite == null) {
			return null;
		}
		
		/* Parcours de l'iterator. */
		while (ite.hasNext()) {
			
			final Entry<Integer, String> entry = ite.next();
			
			if (entry == null) {
				return null;
			}
			
			final int numerolLigneLue = entry.getKey();
			final String ligneLue = entry.getValue();
			
			/* Test du numéro de la ligne. */
			if (numerolLigneLue == pNumeroLigne) {
				
				final StringBuilder stb = new StringBuilder();
				
				/* Ajout de la ligne au StringBuilder. */
				stb.append(ligneLue);				
				stb.append(NEWLINE);
				
				/* Retour de la ligne. */
				return stb.toString();
				
			} // Fin de if (numerolLigneLue == pNumeroLigne).________
						
		} // Fin de Parcours de l'iterator.______________________
		
		/* retourne null si la ligne n'existe pas. */
		return null;
		
	} // Fin de listerLignedeMapIntegerString(
	 // SortedMap<Integer, String> pMap
	 // , int pNumeroLigne)._______________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String transcoder(
			final String pString
				, final Charset pCharsetEncodage
					, final Charset pCharsetDecodage
						, final boolean pRapporte
							, final Integer pNumeroLigne) {
		
		/* retourne null si pString == null. */
		if (pString == null) {
			return null;
		}
		
		Charset charsetEncodage = null;
		Charset charsetDecodage = null;
		
		/* passe automatiquement le charsetEncodage à CHARSET_ANSI 
		 * si pCharsetEncodage == null. */
		if (pCharsetEncodage == null) {
			charsetEncodage = CHARSET_ANSI;
		}
		else {
			charsetEncodage = pCharsetEncodage;
		}
		
		/* passe automatiquement le charsetDecodage à CHARSET_IBM850 
		 * si pCharsetDecodage == null. */
		if (pCharsetDecodage == null) {
			charsetDecodage = CHARSET_IBM850;
		}
		else {
			charsetDecodage = pCharsetDecodage;
		}
		
		String chaineATranscoder = null;
		
		/* Retire le BOM_UTF-8 en début de ligne 
		 * si l'encodage cible n'est pas CHARSET_UTF8. */
		if (pString.charAt(0) == BOM_UTF_8 
				&& charsetEncodage == CHARSET_UTF8 
					&& charsetDecodage != CHARSET_UTF8) {
			
			chaineATranscoder = pString.substring(1);
			
			// RAPPORT ********
			if (pRapporte) {
				
				/* message du rapport. */
				final String message 
				= "La ligne n° " 
				+ pNumeroLigne 
				+ " : '" 
				+ StringUtils.abbreviate(pString, 100)
				+ "' comportait un caractère invisible BOM_UTF-8 au début. Il a été retiré.";
				
				/* Création d'une ligne de rapport. */
				final LigneRapport ligneRapport 
				= this.creerLigneRapport(
						pNumeroLigne
						, message
						, null
						, SANS_OBJET
						, SANS_OBJET
						, true
						, "CARACTERE BOM_UTF-8 retiré");
				
				/* Ajout au rapport. */
				this.ajouterLigneRapport(ligneRapport);
				
			} // Fin de if (pRapporte).____________________
		}
		else {
			chaineATranscoder = pString;
		}
		
		// TRANSCODAGE ************************
		final String resultat 
		= new String(
				chaineATranscoder.getBytes(charsetEncodage), charsetDecodage);
		
		// RAPPORT ********
		if (pRapporte) {
			
			/* message du rapport. */
			final String message 
			= "La ligne n° " 
			+ pNumeroLigne 
			+ " : '" 
			+ StringUtils.abbreviate(pString, 100)
			+ "' a été transcodée en " 
			+ charsetDecodage.name() 
			+ " : '" 
			+ StringUtils.abbreviate(resultat, 100) + "'";
			
			/* Création d'une ligne de rapport. */
			final LigneRapport ligneRapport 
			= this.creerLigneRapport(
					pNumeroLigne
					, message
					, null
					, SANS_OBJET
					, SANS_OBJET
					, true
					, "LIGNE_TRANSCODEE en " 
					+ charsetDecodage.name());
			
			/* Ajout au rapport. */
			this.ajouterLigneRapport(ligneRapport);
			
		} // Fin de if (pRapporte).____________________
		
		return resultat;
		
	} // Fin de transcoder(
	 // String pString
	 // , Charset pCharsetEncodage
	 // , Charset pCharsetDecodage
	 // , boolean pRapporte
	// , Integer pNumeroLigne).____________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean determinerSiEncodagePossible(
			final String pString
				, final Charset pCharset
					, final int pNumeroLigne) {
		
		/* retourne false si pString == null. */
		if (pString == null) {
			return false;
		}
		
		/* retourne false si pCharset est null. */
		if (pCharset == null) {
			return false;
		}
		
		boolean resultat = true;
				
		/* calcule la longueur de la chaine. */
		final int longueurChaine = pString.length();
		int position = 0;
		
		// LECTURE CARACTERE PAR CARACTERE ***************
		for (int i = 0; i < longueurChaine; i++) {
			
			/* Incrémentation de la position. */
			position ++;
			
			/* Extraction du caractère. */
			final char caractere = pString.charAt(i);
			
			/* Teste si le caractère est indésirable. */
			if (CARACTERES_INDESIRABLES_SET.contains(caractere)) {
				
				/* Constitution du message pour le rapport. */
				final String message 
					= new CaractereDan(
							position, caractere).toString();
				
				/* Création de la ligne de rapport. */
				final LigneRapport ligneRapport 
					= this.creerLigneRapport(
							pNumeroLigne
							, message + " NON ENCODE AVEC " + pCharset
							, null
							, String.valueOf(position)
							, String.valueOf(caractere)
							, false
							, ACTION_LIGNE_A_TRANSCODER);
				
				/* Ajoute une ligne au rapport 
				 * pour le caractère déficient. */
				this.ajouterLigneRapport(ligneRapport);
				
				/* Passe le resultat à false. */				
				resultat = false;
				
			}  // Fin de if (CARACTERES_INDESIRABLES_SET
			// .contains(character)).______________________
			
		} // Fin du Parcours de la ligne.__________________________
			
		return resultat;
		
	} // Fin de determinerSiEncodagePossible(
	 // String pString
	 // , Charset pCharset
	// , int pNumeroLigne).________________________________________________
	

	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String listerChaineCarParCar(
			final String pString) {
		
		return this.listerChaineCarParCar(pString, null);
		
	} // Fin de listerChaineCarParCar(
	 // String pString).___________________________________________________
	
	
	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String listerChaineCarParCar(
			final String pString
			, final Integer pNombreMaxiCaracteres) {
		
			
			/* retourne null si pString est blank (null ou vide). */
			if (StringUtils.isBlank(pString)) {
				return null;
			}
			
			int longueurALire = 0;
			
			/* Détermine la longueur de la chaîne. */
			final int longueurChaine = pString.length();
			int position = 0;
			Character caractereChaine = null;
			final StringBuilder stb = new StringBuilder();
			
			/* Calcul de longueurALire. */
			/* si pNombreMaxiCaracteres == null, 
			 * lit le min(longueurChaine, 1000) premiers caractères. */
			if (pNombreMaxiCaracteres == null) {
				if (longueurChaine < 1000) {
					longueurALire = longueurChaine;
				}
				else {
					longueurALire = 1000;
				}				
			}
			/* si pNombreMaxiCaracteres == 0, lit toute la chaîne. */
			else if (pNombreMaxiCaracteres == 0) {
				longueurALire = longueurChaine;
			}
			/* si la longueur de pString <= pNombreMaxiCaracteres
			 * , lit toute la chaîne.  */
			else if (longueurChaine <= pNombreMaxiCaracteres) {
				longueurALire = longueurChaine;
			}
			/* si la longueur de pString > pNombreMaxiCaracteres
			 * , lit pNombreMaxiCaracteres.  */
			else {
				longueurALire = pNombreMaxiCaracteres;
			}
			
			/* Parcours de longueurALire de la chaîne 
			 * caractère par caractère. */
			for (int index = 0; index < longueurALire; index++) {
				
				/* L'index est 0-based. */
				position = index + 1;
				
				/* détermination du caractère dans la chaine. */
				try {
					caractereChaine = pString.charAt(index);
				} catch (IndexOutOfBoundsException e1) {
					caractereChaine = null;
				}
				
				/* Instanciation d'un CaractereDan. */
				final CaractereDan carDan 
					= new CaractereDan(position, caractereChaine);
				
				stb.append(carDan.toString());
				stb.append(NEWLINE);
				
			} // Fin du parcours de la chaîne._______________
			
			/* Retour du résultat. */
			return stb.toString();
					
	} // Fin de listerChaineCarParCar(
	 // String pString
	// , Integer pNombreMaxiCaracteres).___________________________________
	

	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportTextuel() {
		
		/* retourne null si this.rapport == null. */
		if (this.rapport == null) {
			return null;
		}
		
		return this.afficherRapportTextuel(this.rapport);
		
	} // Fin de afficherRapportTextuel().__________________________________
	

	
	/**
	 * SERVICE ANNEXE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportTextuel(
			final List<LigneRapport> pList) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final LigneRapport ligne : pList) {
			stb.append(ligne.toString());
			stb.append(NEWLINE);
		}
		
		return stb.toString();
		
	} // Fin de afficherRapportTextuel(
	 // List<LigneRapport> pList)._________________________________________



	/**
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
		stb.append("Action;");
		
		return stb.toString();
		
	} // Fin de getEnTeteCsv().____________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportCsv() {
		
		/* retourne null si this.rapport == null. */
		if (this.rapport == null) {
			return null;
		}
		
		return this.afficherRapportCsv(this.rapport, false);
		
	} // Fin de afficherRapportCsv().______________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportCsvAvecEntete() {
		
		/* retourne null si this.rapport == null. */
		if (this.rapport == null) {
			return null;
		}
		
		return this.afficherRapportCsv(this.rapport, true);
		
	} // Fin de afficherRapportCsvAvecEntete().____________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportCsv(
			final List<LigneRapport> pList
				, final boolean pAjouterEntete) {
		
		/* retourne null si pList == null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		int compteur = 0;
		
		for (final LigneRapport ligne : pList) {
			
			compteur++;
			
			/* Ajout du caractère BOM_UTF-8 pour 
			 * forcer Excel 2010 à détecter l'UTF-8. */
			stb.append(BOM_UTF_8);
			
			/* Ajout de l'en-tête. */
			if (compteur == 1 && pAjouterEntete) {
				stb.append(this.fournirEnTeteCsv());
				stb.append(NEWLINE);
			}
			
			stb.append(ligne.fournirStringCsv());
			stb.append(NEWLINE);
		}
		
		return stb.toString();
				
	} // Fin de afficherRapportCsv(
	 // List<LigneRapport> pList
	// , boolean pAjouterEntete).__________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String fournirEnTeteRapportJTable(final int pI) {
		
		final LigneRapport ligne 
			= new LigneRapport();
				
		return ligne.fournirEnTeteColonne(pI);
		
	} // Fin de getEnTeteRapportJTable(
	// int pI).____________________________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Object fournirValeurRapportJTable(
			final int pLigne
				, final int pColonne) {
		
		/* retourne null si this.rapport == null. */
		if (this.rapport == null) {
			return null;
		}
		
		return this.rapport.get(pLigne).fournirValeurColonne(pColonne);
		
	} // Fin de getValeurRapportJTable(
	// int pLigne
	// , int pColonne).____________________________________________________
	

	
	/**
	 * method ajouterLigneRapport(
	 * LigneRapport pLigneRapport) :<br/>
	 * Ajoute une LigneRapport au rapport du contrôle 'this.rapport'.<br/>
	 * <br/>
	 * - retourne false si pLigneRapport == null.<br/>
	 * - retourne false si rapport == null.<br/>
	 * <br/>
	 *
	 * @param pLigneRapport : LigneRapport.<br/>
	 * 
	 * @return : boolean : true si la ligne de rapport 
	 * a été ajoutée au rapport.<br/>
	 */
	protected final boolean ajouterLigneRapport(
			final LigneRapport pLigneRapport) {
		
		/* retourne false si pLigneRapport == null. */
		if (pLigneRapport == null) {
			return false;
		}
		
		/* retourne false si rapport == null. */
		if (this.rapport == null) {
			return false;
		}
		
		/* Ajout de la ligne de rapport. */
		return this.rapport.add(pLigneRapport);
		
	} // Fin de ajouterLigneRapport(
	 // LigneRapport pLigneRapport)._______________________________________
	

	
	/**
	 * method retirerLigneRapport(
	 * LigneRapport pLigneRapport) :<br/>
	 * Retire une LigneRapport au rapport du contrôle.<br/>
	 * <br/>
	 * - retourne false si pLigneRapport == null.<br/>
	 * - retourne false si rapport == null.<br/>
	 * <br/>
	 *
	 * @param pLigneRapport : LigneRapport.<br/>
	 * 
	 * @return : boolean : true si la ligne de rapport
	 *  a été retirée du rapport.<br/>
	 */
	protected final boolean retirerLigneRapport(
			final LigneRapport pLigneRapport) {
		
		/* retourne false si pLigneRapport == null. */
		if (pLigneRapport == null) {
			return false;
		}
		
		/* retourne false si rapport == null. */
		if (this.rapport == null) {
			return false;
		}
		
		/* retrait de la ligne de rapport. */
		return this.rapport.remove(pLigneRapport);
		
	} // Fin de retirerLigneRapport(
	 // LigneRapport pLigneRapport)._______________________________________
	

	
	/**
	 * method creerLigneRapport(
	 * Integer pNumeroLigne
	 * , String pMessageControle
	 * , Integer pOrdreChamp
	 * , String pPositionChamp
	 * , String pValeurChamp
	 * , Boolean pStatut
	 * , String pAction) :<br/>
	 * Crée et retourne une ligne de rapport LigneRapport 
	 * avec des attributs pré-remplis et les valeurs passées en paramètre.<br/>
	 * <br/>
	 * Liste des attributs pré-remplis : <br/>
	 * <ul>
	 * <li>Met automatiquement this.ordreControle dans 'ordreControle' 
	 * de la ligne de rapport.</li><br/>
	 * <li>Met automatiquement this.dateControleStringFormatee 
	 * dans la date d'exécution du contrôle 'dateControle'.</li><br/>
	 * <li>Met automatiquement this.userName dans le nom 
	 * de l'utilisateur qui a déclenché le contrôle 'userName'.</li><br/>
	 * <li>Met automatiquement this.nomFichier dans le nom du fichier 
	 * objet du contrôle 'nomFichier'.</li><br/>
	 * <li>Met automatiquement this.typeControle dans le type du contrôle 
	 * ('contrôle de surface' par exemple) 'typeControle'.</li><br/>
	 * <li>Met automatiquement this.nomControle dans le nom du contrôle 
	 * ('contrôle fichier texte' par exemple) 'nomControle'.</li><br/>
	 * <li>Met automatiquement this.nomCritere dans la désignation 
	 * du critère vérifié par le contrôle 
	 * ('une ligne ne doit pas être vide' par exemple) 'critere'.</li><br/>
	 * <li>Met automatiquement this.gravite dans la désignation 
	 * de la gravité de ce contrôle (par exemple '1 - bloquant') 'gravité'.</li><br/>
	 * </ul>
	 * <br/>
	 *
	 * @param pNumeroLigne : Integer : numéro de la ligne dans le fichier 
	 * qui déclenche le contrôle.<br/>
	 * @param pMessageControle : String : message émis par le contrôle.<br/>
	 * @param pOrdreChamp : Integer : ordre du champ contrôlé
	 * (dans un fichier comportant une liste de champs comme un fichier 
	 * ASCII HIT).<br/>
	 * @param pPositionChamp : String : position du champ contrôlé 
	 * dans une ligne du fichier comme 7 ou [7-12].<br/>
	 * @param pValeurChamp : String : valeur prise par le champ contrôlé 
	 * exprimée sous forme de String.<br/>
	 * @param pStatut : Boolean : true si le contrôle 
	 * est passé favorablement.<br/>
	 * @param pAction : String : action menée après le contrôle 
	 * comme "ligne éliminée" ou "ligne conservée".<br/>
	 * <br/>
	 * 
	 * @return : LigneRapport : Une ligne de rapport.<br/>
	 */
	protected final LigneRapport creerLigneRapport(
			final Integer pNumeroLigne
			, final String pMessageControle
			, final Integer pOrdreChamp
			, final String pPositionChamp
			, final String pValeurChamp
			, final Boolean pStatut
			, final String pAction) {
		
		return new LigneRapport(
				this.ordreControle
				, this.dateControleStringFormatee
				, this.userName
				, this.nomFichier
				, this.typeControle
				, this.nomControle
				, this.nomCritere
				, this.gravite
				, pNumeroLigne
				, pMessageControle
				, pOrdreChamp
				, pPositionChamp
				, pValeurChamp
				, pStatut
				, pAction);
		
	} // Fin de creerLigneRapport(
	 // Integer pNumeroLigne
	 // , String pMessageControle
	 // , Integer pOrdreChamp
	 // , String pPositionChamp
	 // , String pValeurChamp
	 // , Boolean pStatut
	 // , String pAction)._________________________________________________


	
	/**
	 * SERVICE ACCESSOIRE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final File enregistrerRapportTextuelANSI(
			final File pFichier) {
		
		return this.enregistrerRapportTextuel(
				this.rapport
				, this.dateControle
				, this.userName
				, this.nomControle, pFichier, CHARSET_ANSI, NEWLINE);
		
	} // Fin de enregistrerRapportTextuelANSI(
	 // File pFichier).____________________________________________________
	
	
	
	/**
	 * SERVICE ACCESSOIRE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final File enregistrerRapportTextuelLatin9(
			final File pFichier) {
		
		return this.enregistrerRapportTextuel(
				this.rapport
				, this.dateControle
				, this.userName
				, this.nomControle, pFichier, CHARSET_LATIN9, NEWLINE);
		
	} // Fin de enregistrerRapportTextuelLatin9(
	 // File pFichier).____________________________________________________
	
	
	
	/**
	 * SERVICE ACCESSOIRE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final File enregistrerRapportTextuelUTF8(
			final File pFichier) {
		
		return this.enregistrerRapportTextuel(
				this.rapport
				, this.dateControle
				, this.userName
				, this.nomControle, pFichier, CHARSET_UTF8, NEWLINE);
		
	} // Fin de enregistrerRapportTextuelUTF8(
	 // File pFichier).____________________________________________________
	
	
	
	/**
	 * SERVICE ACCESSOIRE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final File enregistrerRapportTextuel(
			final List<LigneRapport> pRapportList
				, final Date pDateEnregistrement
					, final String pUserName
						, final String pObjet
							, final File pFichier
								, final Charset pCharset
									, final String pSautLigne) {
		
		/* retourne null si pFichier == null. */
		if (pFichier == null) {
			return null;
		}
		
		/* retourne null si pFichier est inexistant. */
		if (!pFichier.exists()) {
			return null;
		}
		
		/* retourne null si pFichier est un répertoire. */
		if (pFichier.isDirectory()) {
			return null;
		}
		
		/* retourne null si pRapportList == null. */
		if (pRapportList == null) {
			return null;
		}
		
		final EnregistreurFichiers enregistreur 
			= new EnregistreurFichiers(
					pDateEnregistrement, pUserName, pObjet, pFichier);
		
		String aEcrire = null;
		
		final StringBuilder stb = new StringBuilder();
		
		/* Constitution de la String à ecrire dans le fichier. */
		for (final LigneRapport ligne : pRapportList) {
			stb.append(ligne.toString());
			stb.append(pSautLigne);
		}
		
		aEcrire = stb.toString();
		
		final File resultat = enregistreur.ecrireStringDansFile(
				pFichier, aEcrire, pCharset, pSautLigne);
		
		/* Ajout du rapport d'enregistrement 
		 * de l'enregistreur à this.rapportEnregistrement. */
		this.ajouterARapportEnregistrement(enregistreur.getRapport());
		
		return resultat;
		
	} // Fin de enregistrerRapportTextuel(....).___________________________
	

	
	/**
	 * SERVICE ACCESSOIRE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final File enregistrerRapportCsvANSI(
			final File pFichier) {
		
		return this.enregistrerRapportCsv(
				this.rapport
				, this.dateControle
				, this.userName
				, this.nomControle, pFichier, CHARSET_ANSI, NEWLINE
				, true);
		
	} // Fin de enregistrerRapportCsvANSI(
	// File pFichier)._____________________________________________________
	

	
	/**
	 * SERVICE ACCESSOIRE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final File enregistrerRapportCsvLatin9(
			final File pFichier) {
		
		return this.enregistrerRapportCsv(
				this.rapport
				, this.dateControle
				, this.userName
				, this.nomControle, pFichier, CHARSET_LATIN9, NEWLINE
				, true);
		
	} // Fin de enregistrerRapportCsvLatin9(
	// File pFichier)._____________________________________________________
	

	
	/**
	 * SERVICE ACCESSOIRE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final File enregistrerRapportCsvUTF8(
			final File pFichier) {
		
		return this.enregistrerRapportCsv(
				this.rapport
				, this.dateControle
				, this.userName
				, this.nomControle, pFichier, CHARSET_UTF8, NEWLINE
				, true);
		
	} // Fin de enregistrerRapportCsvUTF8(
	// File pFichier)._____________________________________________________
	
	
	
	/**
	 * SERVICE ACCESSOIRE.<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	public final File enregistrerRapportCsv(
			final List<LigneRapport> pRapportList
				, final Date pDateEnregistrement
					, final String pUserName
						, final String pObjet
							, final File pFichier
								, final Charset pCharset
									, final String pSautLigne
										, final boolean pEnteteCsv) {
		
		/* retourne null si pFichier == null. */
		if (pFichier == null) {
			return null;
		}
		
		/* retourne null si pFichier est inexistant. */
		if (!pFichier.exists()) {
			return null;
		}
		
		/* retourne null si pFichier est un répertoire. */
		if (pFichier.isDirectory()) {
			return null;
		}
		
		/* retourne null si pRapportList == null. */
		if (pRapportList == null) {
			return null;
		}
		
		/* Instanciation d'un service d'enregistrement 
		 * des fichiers sur disque. */
		final IEnregistreurFichiers enregistreur 
			= new EnregistreurFichiers(
					pDateEnregistrement, pUserName, pObjet, pFichier);
		
		String aEcrire = null;
		
		final StringBuilder stb = new StringBuilder();
		
		/* Constitution de la String à ecrire dans le fichier. */
		int compteur = 0;
		
		for (final LigneRapport ligne : pRapportList) {
			
			compteur++;
			
			/* Ajout d'un caractère BOM-UTF-8 si le Charset est UTF-8 
			 * pour forcer Excel 2010 à détecter l'UTF-8. */
			if (pCharset == CHARSET_UTF8) {
				stb.append(BOM_UTF_8);
			}
			
			/* Ajout de l'en-tête csv. */
			if (pEnteteCsv) {
				if (compteur == 1) {
					stb.append(ligne.fournirEnTeteCsv());
					stb.append(pSautLigne);
				}
			}
			
			
			stb.append(ligne.fournirStringCsv());
			stb.append(pSautLigne);
		}
		
		aEcrire = stb.toString();
		
		/* Ecriture du rapport de controle dans pFichier. */
		final File resultat = enregistreur.ecrireStringDansFile(
				pFichier, aEcrire, pCharset, pSautLigne);
		
		/* Ajout du rapport d'enregistrement 
		 * de l'enregistreur à this.rapportEnregistrement. */
		this.ajouterARapportEnregistrement(enregistreur.getRapport());
		
		return resultat;
		
	} // Fin de enregistrerRapportCsv(....)._______________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<LigneRapportEnregistrement> getRapportEnregistrement() {
		return this.rapportEnregistrement;
	} // Fin de getRapportEnregistrement().________________________________


		
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getMessagesEnregistrementsRapports() {
		
		/* retourne null si this.rapportEnregistrement == null. */
		if (this.rapportEnregistrement == null) {
			return null;
		}
		
		/* Instanciation d'un StringBuilder. */
		final StringBuilder stb = new StringBuilder();
		
		/* Parcours de this.rapportEnregistrement. */
		for (final LigneRapportEnregistrement ligne : this.rapportEnregistrement) {
			
			/* agrége les messages de création de rapport 
			 * de contrôle sur disque. */
			if (ligne != null) {
				
				stb.append(ligne.getMessage());
				stb.append(NEWLINE);	
				
			} // Fin de if (ligne != null).__________________
			
		} // Fin de Parcours de this.rapportEnregistrement.___________
		
		/* retour du résultat. */
		return stb.toString();
		
	} // Fin de getMessagesEnregistrementsRapports().______________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportEnregistrementTextuel() {
		return this.afficherRapportEnregistrementTextuel(
				this.rapportEnregistrement);
	} // Fin de afficherRapportEnregistrementTextuel().____________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportEnregistrementTextuel(
			final List<LigneRapportEnregistrement> pList) {
		
		/* retourne null si pList est null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		
		for (final LigneRapportEnregistrement ligne : pList) {
			stb.append(ligne.toString());
			stb.append(NEWLINE);
		}
		
		return stb.toString();
		
	} // Fin de afficherRapportEnregistrementTextuel(
	 // List<LigneRapportEnregistrement> pList).___________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportEnregistrementCsv() {
		return this.afficherRapportEnregistrementCsv(
				this.rapportEnregistrement, true);
	} // Fin de afficherRapportEnregistrementCsv().________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherRapportEnregistrementCsv(
			final List<LigneRapportEnregistrement> pList
				, final boolean pEnTete) {
		
		/* retourne null si pList est null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuilder stb = new StringBuilder();
		int compteur = 0;
		
		for (final LigneRapportEnregistrement ligne : pList) {
			
			compteur++;
			
			/* Ajout d'un caractère BOM-UTF-8
			 * pour forcer Excel 2010 à détecter l'UTF-8. */
			stb.append(BOM_UTF_8);
			
			if (pEnTete) {
				if (compteur == 1) {
					stb.append(ligne.fournirEnTeteCsv());
					stb.append(NEWLINE);
				}
			}
			
			stb.append(ligne.fournirStringCsv());
			stb.append(NEWLINE);
		}
		
		return stb.toString();
		
	} // Fin de afficherRapportEnregistrementCsv(
	 // List<LigneRapportEnregistrement> pList
	 // , boolean pEnTete).________________________________________________

	
	
	/**
	 * method ajouterARapportEnregistrement(
	 * List&lt;LigneRapportEnregistrement&gt; pList) :<br/>
	 * Ajoute une List&lt;LigneRapportEnregistrement&gt; 
	 * à this.rapportEnregistrement.<br/>
	 * <br/>
	 *
	 * @param pList : List&lt;LigneRapportEnregistrement&gt;.<br/>
	 * 
	 * @return : boolean : true si pList à été ajoutée.<br/>
	 */
	private boolean ajouterARapportEnregistrement(
			final List<LigneRapportEnregistrement> pList) {
		
		if (this.rapportEnregistrement == null) {
			return false;
		}
		
		return this.rapportEnregistrement.addAll(pList);
		
	} // Fin de ajouterARapportEnregistrement(
	 // List<LigneRapportEnregistrement> pList).___________________________
	

	
	/**
	 * method fournirFileTxtUTF8() :<br/>
	 * Fournit un fichier pour enregistrer le rapport au 
	 * format textuel en UTF-8.<br/>
	 * <br/>
	 * - Récupère le chemin des rapports stocké dans 
	 * configurationapplication_fr_FR.properties si il existe
	 * , sinon, récupère le chemin en dur fourni 
	 * par this.fournirCheminRapportsEnDur().<br/>
	 * - Fabrique éventuellement l'arborescence du chemin des rapports
	 * (".\\data\\temp\\rapports" par exemple)<br/>
	 * - Fabrique le nom du fichier sous la forme 
	 * [date_nom_encodage.extension] 
	 * comme "1961-02-25_14-27-07_RAPPORT_UTF8.txt" par exemple<br/>
	 * - Fabrique et retourne le fichier 
	 * (.\data2\temp\rapports\1961-02-25_14-27-07_RAPPORT_UTF8.txt 
	 * par exemple).<br/>
	 * <br/>
	 *
	 * @return : File : Contient this.rapport au format textuel en UTF-8.<br/>
	 */
	protected final File fournirFileTxtUTF8() {
		
		/* Récupère le chemin des rapports stocké dans 
		 * configurationapplication_fr_FR.properties si il existe
		 * , sinon, récupère le chemin en dur fourni par 
		 * this.fournirCheminRapportsEnDur(). */
		final String cheminRapports = this.fournirCheminFichiers();
		
		if (StringUtils.isBlank(cheminRapports)) {
			return null;
		}
		
		/* - Fabrique éventuellement l'arborescence du chemin des rapports
		 * (".\\data\\temp\\rapports" par exemple)<br/>
		 * - Fabrique le nom du fichier sous la forme 
		 * [date_nom_encodage.extension] 
		 * comme "1961-02-25_14-27-07_RAPPORT_UTF8.txt" par exemple<br/>
		 * - Fabrique et retourne le fichier 
		 * (.\data2\temp\rapports\1961-02-25_14-27-07_RAPPORT_UTF8.txt 
		 * par exemple).<br/>*/
		final File resultat 
			= this.fournirFile(
					cheminRapports
						, this.dateControle
							, this.fournirBaseNomRapport() + UNDERSCORE + this.nomFichier
								, "UTF8"
									, "txt");
		
		return resultat;
		
	} // Fin de fournirFileTxtUTF8().______________________________________
	
	
	
	/**
	 * method fournirFileCsvUTF8() :<br/>
	 * Fournit un fichier pour enregistrer le rapport au 
	 * format csv en UTF-8.<br/>
	 * <br/>
	 * - Récupère le chemin des rapports stocké dans 
	 * configurationapplication_fr_FR.properties si il existe
	 * , sinon, récupère le chemin en dur fourni 
	 * par this.fournirCheminRapportsEnDur().<br/>
	 * - Fabrique éventuellement l'arborescence du chemin des rapports
	 * (".\\data\\temp\\rapports" par exemple)<br/>
	 * - Fabrique le nom du fichier sous la forme 
	 * [date_nom_encodage.extension] 
	 * comme "1961-02-25_14-27-07_RAPPORT_UTF8.txt" par exemple<br/>
	 * - Fabrique et retourne le fichier 
	 * (.\data2\temp\rapports\1961-02-25_14-27-07_RAPPORT_UTF8.txt 
	 * par exemple).<br/>
	 * <br/>
	 *
	 * @return : File : Contient this.rapport au format textuel en UTF-8.<br/>
	 */
	protected final File fournirFileCsvUTF8() {
		
		/* Récupère le chemin des rapports stocké dans 
		 * configurationapplication_fr_FR.properties si il existe
		 * , sinon, récupère le chemin en dur fourni par 
		 * this.fournirCheminRapportsEnDur(). */
		final String cheminRapports = this.fournirCheminFichiers();
		
		if (StringUtils.isBlank(cheminRapports)) {
			return null;
		}
		
		/* - Fabrique éventuellement l'arborescence du chemin des rapports
		 * (".\\data\\temp\\rapports" par exemple)<br/>
		 * - Fabrique le nom du fichier sous la forme 
		 * [date_nom_encodage.extension] 
		 * comme "1961-02-25_14-27-07_RAPPORT_UTF8.txt" par exemple<br/>
		 * - Fabrique et retourne le fichier 
		 * (.\data2\temp\rapports\1961-02-25_14-27-07_RAPPORT_UTF8.txt 
		 * par exemple).<br/>*/
		final File resultat 
			= this.fournirFile(
					cheminRapports
						, this.dateControle
							, this.fournirBaseNomRapport()+ UNDERSCORE + this.nomFichier
								, "UTF8"
									, "csv");
		
		return resultat;
		
	} // Fin de fournirFileTxtUTF8().______________________________________
	
	
		
	/**
	 * method fournirBaseNomRapport() :<br/>
	 * Fournit la base du nom pour créer 
	 * les fichiers de stockage des rapports.<br/>
	 * Par exemple : <br/>
	 * "RAPPORT-CONTROLE-FICHIER-TEXTE"
	 * <br/>
	 *
	 * @return : String : Nom de base des fichiers de rapport.<br/>
	 */
	protected abstract String fournirBaseNomRapport();
	
	
	
	/**
	 * method fournirCheminFichiers() :<br/>
	 * Propose un chemin (arborescence de répertoires) pour stocker 
	 * les fichiers de rapport en utilisant :<br/>
	 * 1 - un chemin des rapports fixé dans 
	 * configurationapplication_fr_FR.properties si il existe.<br/>
	 * 2 - un chemin en dur stocké dans la présente classe 
	 * et fourni par fournirCheminRapportsEnDur().<br/>
	 * <br/>
	 * retourne le chemin des fichiers indiqué 
	 * dans configuration_fr_FR.properties si il existe
	 * , si la clef définie dans fournirCleCheminRapports() 
	 * existe et si cette clef est renseignée,<br/>
	 * - sinon retourne la valeur en dur écrite dans 
	 * fournirCheminRapportsEnDur().<br/>
	 * <br/>
	 *
	 * @return : String : Le chemin des rapports.<br/>
	 */
	private String fournirCheminFichiers() {
		return fournirCheminFichiers(null);
	} // Fin de fournirCheminFichiers().___________________________________
	
	
	
	/**
	 * method fournirCheminFichiers(
	 * String pCheminFichiers) :<br/>
	 * Propose un chemin (arborescence de répertoires) pour stocker 
	 * les fichiers de rapport en utilisant :<br/>
	 * 1 - pCheminFichiers si il n'est pas blank.<br/>
	 * 2 - un chemin des rapports fixé dans 
	 * configurationapplication_fr_FR.properties si il existe.<br/>
	 * 3 - un chemin en dur stocké dans la présente classe 
	 * et fourni par fournirCheminRapportsEnDur().<br/>
	 * <br/>
	 * - retourne pCheminFichiers si pCheminFichiers n'est pas blank,<br/>
	 * - sinon retourne le chemin des fichiers indiqué 
	 * dans configuration_fr_FR.properties si il existe
	 * , si la clef définie dans fournirCleCheminRapports() 
	 * existe et si cette clef est renseignée,<br/>
	 * - sinon retourne la valeur en dur écrite dans 
	 * fournirCheminRapportsEnDur().<br/>
	 * <br/>
	 *
	 * @param pCheminFichiers : String : 
	 * chemin des rapports proposé par le développeur.<br/>
	 * 
	 * @return : String : Le chemin des rapports.<br/>
	 */
	private String fournirCheminFichiers(
			final String pCheminFichiers) {
		
		/* retourne pCheminFichiers si pCheminFichiers n'est pas blank. */
		if (!StringUtils.isBlank(pCheminFichiers)) {
			return pCheminFichiers;
		}
		
		/* sinon, retourne le chemin des fichiers indiqué 
		 * dans configuration_fr_FR.properties si il existe
		 * , si la clef définie dans fournirCleCheminRapports() existe 
		 * et si cette clef est renseignée. */
		if (!StringUtils.isBlank(fournirCheminRapportsDansProperties())) {
			return fournirCheminRapportsDansProperties();
		}
		
		/* sinon, retourne la valeur en dur écrite 
		 * dans fournirCheminRapportsEnDur.*/
		return fournirCheminRapportsEnDur() ;
		
	} // Fin de fournirCheminFichiers(
	 // String pCheminFichiers).___________________________________________
	
	
	
	/**
	 * method fournirCleCheminRapports() :<br/>
	 * Fournit la clé dans configurationapplication_fr_FR.properties 
	 * associée au chemin des rapports.<br/>
	 * <br/>
	 * "AbstractControle.fournirCheminFichiers.cheminrapports".<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * "AbstractControle.fournirCheminFichiers.cheminrapports".<br/>
	 */
	private String fournirCleCheminRapports() {
		return "AbstractControle.fournirCheminFichiers.cheminrapports";
	} // Fin de fournirCleCheminRapports().________________________________
	
	
	
	/**
	 * method fournirValeurCheminRapports() :<br/>
	 * retourne la valeur du chemin des rapports associée 
	 * à la clé fournie par fournirCleCheminRapports() 
	 * contenue dans ./bin/configurationapplication_fr_FR.properties.<br/>
	 * retourne null ou "  " si le properties a été oublié
	 * , si la clé est absente dans le properties, 
	 * ou si la valeur associée à la clef est inexistante.<br/>
	 * <br/>
	 * - retourne null si ./bin/configurationapplication_fr_FR.properties 
	 * est manquant.<br/>
	 * - retourne null si ./bin/configurationapplication_fr_FR.properties 
	 * ne contient pas la clef fournie par fournirCleCheminRapports().<br/>
	 * - retourne " " si ./bin/configurationapplication_fr_FR.properties 
	 * contient la clef fournie par fournirCleCheminRapports() 
	 * mais qu'il n'y a pas de valeur associée à cette clé 
	 * dans le properties.<br/>
	 * <br/>
	 *
	 * @return : String :   le chemin des rapports dans 
	 * ./bin/configurationapplication_fr_FR.properties.<br/>
	 */
	private String fournirCheminRapportsDansProperties() {
	
		String chemin = null;
		
		try {
			
			/* Charge le ResourceBundle encapsulant 
			 * configurationapplication_fr_FR.properties*/
			final ResourceBundle bundle 
				= ResourceBundle.getBundle(
						"configurationapplication", LOCALE_FR_FR);
			
			chemin = bundle.getString(this.fournirCleCheminRapports());
			
		} catch (Exception e) {
			
			final String message 
			= "./bin/configurationapplication_fr_FR.properties "
					+ "est manquant ou la clé n'existe pas - Exception : ";
			
			/* LOG de niveau INFO. */
			loggerInfo(
					CLASSE_ABSTRACTCONTROLE
					, METHODE_FOURNIRCHEMINRAPPORTSDANSPROPERTIES
					, message
					, e.getMessage());
			
			/* retourne null si 
			 * ./bin/configurationapplication_fr_FR.properties 
			 * est manquant. */
			return null;
		}
		
		return chemin;
				
	} // Fin de fournirCheminRapportsDansProperties()._____________________


	
	/**
	 * method fournirCheminRapportsEnDur() :<br/>
	 * Fournit un chemin (arborescence) en dur au cas où :<br/>
	 * 1 - le developpeur ne propose pas de chemin en paramètre  
	 * dans fournirCheminFichiers(String pCheminFichiers),<br/>
	 * 2 - Il n'existe pas de ./bin/configurationapplication_fr_FR.properties 
	 * (ou pas la clef dans le properties fournie par 
	 * fournirCleCheminRapports(), 
	 * ou pas de valeur associée à cette clef).<br/>
	 * <br/>
	 *
	 * @return : String : 
	 * ".\\data\\temp\\rapports\\rapportscontroles".<br/>
	 */
	private String fournirCheminRapportsEnDur() {
		return ".\\data\\temp\\rapports\\rapportscontroles";
	} // Fin de fournirCheminRapportsEnDur().______________________________
	
	
	
	/**
	 * Fabrique éventuellement l'arborescence pChemin 
	 * (".\\data\\temp\\rapports" par exemple)<br/>
	 * , fabrique le nom du fichier sous la forme 
	 * [date_nom_encodage.extension] 
	 * comme "1961-02-25_14-27-07_RAPPORT_UTF8.txt" par exemple<br/>
	 * , fabrique et retourne le fichier 
	 * (.\data2\temp\rapports\1961-02-25_14-27-07_RAPPORT_UTF8.txt 
	 * par exemple).<br/>
	 * <br/>
	 * - crée un répertoire (ou toute l'arborescence) 
	 * pour le fichier si il n'existe pas.<br/>
	 * - Prend automatiquement la date système si pDate est null.<br/>
	 * <br/>
	 * Par exemple : <br/>
	 * <code>
	 * final String chemin1 = ".\\data2\\temp\\rapports";<br/>
	 * final Date date1 = GestionnaireDates.fournirDateAvecString(
	 * "25/02/1961-14:27:07.251", dfDatetimemilliFrancaiseLexico);<br/>
	 * // Crée le fichier 
	 * .\data2\temp\rapports\1961-02-25_14-27-07_RAPPORT_UTF8.txt<br/>
	 * final File resultat = controle.fournirFile(
	 * chemin1, date1, "RAPPORT", "UTF8", "txt");<br/>
	 * </code>
	 * <br/>
	 * - retourne null si pChemin est blank.<br/>
	 * - retourne null si pNomFichier est blank.<br/>
	 * - retourne null (et LOG ERROR) si il se produit une Exception 
	 * lors de la création du fichier.<br/>
	 * <br/>
	 * 
	 * @param pChemin : String : chemin (arborescence de répertoires) 
	 * pour le fichier.<br/>
	 * @param pDate : Date : Date pour préfixer le nom du fichier. 
	 * La Date sera formattée sous la forme "yyyy-MM-dd_HH-mm-ss" 
	 * de dfDatetimemilliFrancaiseLexico comme 2012-01-16_18-09-55 <br/>
	 * @param pNomFichier : String : nom de base du fichier.<br/>
	 * @param pEncodage : String : encodage pour suffixer 
	 * le nom du fichier.<br/>
	 * @param pExtension : String : extension du fichier.<br/>
	 * 
	 * @return : File : Le File créé.<br/>
	 */
	protected final File fournirFile(
			final String pChemin
				, final Date pDate
					, final String pNomFichier
						, final String pEncodage
							, final String pExtension) {
		
		/* retourne null si pChemin est blank. */
		if (StringUtils.isBlank(pChemin)) {
			return null;
		}
		
		/* retourne null si pNomFichier est blank. */
		if (StringUtils.isBlank(pNomFichier)) {
			return null;
		}
					
		/* crée un répertoire pour le fichier si il n'existe pas. */
		creerArborescence(pChemin);
		
		/* crée le chemin complet du fichier en nommant le fichier. */
		final String cheminFichier 
			= pChemin 
			+ SEPARATEUR_FILE 
			+ fournirNomFichier(pDate, pNomFichier, pEncodage, pExtension);
		
		final File resultatFile = new File(cheminFichier);
		
		/* Création du fichier si il n'existe pas. */
		if (!resultatFile.exists()) {
			try {
				
				resultatFile.createNewFile();
				
			} catch (IOException ioe) {
				
				/* LOG de niveau ERROR. */
				loggerError(
						CLASSE_ABSTRACTCONTROLE
							, METHODE_FOURNIRFILE
								, ioe);
				
				/* retourne null (et LOG ERROR) si il se produit 
				 * une Exception lors de la création du fichier. */
				return null;
				
			}
		}
		
		return resultatFile;
			
	} // Fin de fournirFile()._____________________________________________


	
	/**
	 * method fournirNomFichier(
	 * Date pDate
	 * , String pNom
	 * , String pEncodage
	 * , String pExtension) :<br/>
	 * Fournit un nom pour un fichier 
	 * de la forme [date_nom_encodage.extension].<br/>
	 * Par exemple : <br/>
	 * <code>final Date date1 = controle.fournirDateAvecString(
	 * "25/02/1961-14:27:07.251", dfDatetimemilliFrancaiseLexico);</code> 
	 * instancie une date calée le 25/02/1961 à 14h27'07" 
	 * et 251 millisecondes.<br/>
	 * <code>GestionnaireFichiers.fournirNomFichier(
	 * date1, "RAPPORT", "UTF8", "txt");</code> 
	 * retourne "1961-02-25_14-27-07-789_RAPPORT_UTF8.txt".<br/>
	 * <br/>
	 * - passe automatiquement la date à la date système si pDate == null.<br/>
	 * - retourne null si pNom est blank.<br/>
	 * <br/>
	 *
	 * @param pDate : Date : Date pour préfixer le chemin. 
	 * La Date sera formattée sous la forme "yyyy-MM-dd_HH-mm-ss-SSS" 
	 * de dfDatetimemilliFrancaiseLexico comme 2012-01-16_18-09-55-789 <br/>
	 * @param pNom : String : nom de base du fichier.<br/>
	 * @param pEncodage : String : encodage pour suffixer 
	 * le nom du fichier.<br/>
	 * @param pExtension : String : extension du fichier.<br/>
	 * 
	 * @return : String : Nom pour le fichier.<br/>
	 */
	private String fournirNomFichier(
			final Date pDate
				, final String pNom
					, final String pEncodage
						, final String pExtension) {
		
		Date date = null;
		
		/* passe automatiquement la date 
		 * à la date système si pDate == null. */
		if (pDate == null) {
			date = new Date();
		}
		else {
			date = pDate;
		}
		
		/* retourne null si pNom est blank. */
		if (StringUtils.isBlank(pNom)) {
			return null;
		}
		
		/* Récupère la date  
		 * formattée sous la forme 2012-01-16_18-09-55-759. */
		final String dateFormatteeString 
			= fournirDateFormatee(date, this.dfDatetimemilliFrancaiseLexico);
		
		final StringBuilder stb = new StringBuilder();
		
		stb.append(dateFormatteeString);
		stb.append(UNDERSCORE);
		stb.append(pNom);
		
		if (!StringUtils.isBlank(pEncodage)) {
			stb.append(UNDERSCORE);
			stb.append(pEncodage);
		}
		
		if (!StringUtils.isBlank(pExtension)) {
			stb.append(POINT);
			stb.append(pExtension);
		}
		
		return stb.toString();
		
	} // Fin de fournirNomFichier(...).____________________________________
	
	
	
	/**
	 * method fournirDateFormatee(
	 * Date pDate
	 * , DateFormat pDateFormat) :<br/>
	 * Retourne une String représentant la java.util.Date pDate 
	 * au format pDateFormat.<br/>
	 * Par exemple :<br/>
	 * - Retourne la String "25/02/1961" 
	 * avec une Date au 25/02/1961 et un DateFormat 
	 * DF_DATE_FRANCAISE (
	 * new SimpleDateFormat("dd/MM/yyyy", LOCALE_FR_FR)).<br/>
	 * <br/>
	 * - retourne null si pDate == null.<br/>
	 * - retourne null si pDateFormat == null.<br/>
	 * <br/>
	 *
	 * @param pDate : java.util.Date.<br/>
	 * @param pDateFormat : DateFormat.<br/>
	 * 
	 * @return : String : String pour affichage 
	 * formatté de pDate selon pDateFormat.<br/>
	 */
	private String fournirDateFormatee(
			final Date pDate
				, final DateFormat pDateFormat) {
					
		/* retourne null si pDate == null. */
		if (pDate == null) {
			return null;
		}
		
		/* retourne null si pDateFormat == null. */
		if (pDateFormat == null) {
			return null;
		}
		
		pDateFormat.setLenient(false);
		
		return pDateFormat.format(pDate);
			
	} // Fin de fournirDateFormatee(
	 // Date pDate
	 // DateFormat pDateFormat).___________________________________________
	


	/**
	 * Créée en une seule fois toute l'arborescence passée en paramètre.<br/>
	 * <br/>
	 * Par exemple :<br/>
	 * - creerArborescence("C:\\NewRep1\\NewRep2\\NewRep3") 
	 * va créer toute cette arborescence sur le disque d'un seul coup.<br/>
	 * - creerArborescence(".\\data2\\temp\\rapports") 
	 * va créer cette arborescence à partir 
	 * du répertoire courant d'un seul coup.<br/>
	 * <br/>
	 * - retourne false si pChemin est blank.<br/>
	 * - retourne false si l'arborescence existe déjà.<br/>
	 * - retourne false si pChemin ne contient pas '\\'.<br/>
	 * - retourne false si un des répertoires du chemin est blank.<br/>
	 * - retourne false si la racine du chemin n'existe pas.<br/>
	 * - retourne false si la racine du chemin n'est pas un répertoire.<br/>
	 * - retourne false si un répertoire a créer n'a pas été créé.<br/>
	 * <br/>
	 *
	 * @param pChemin : String : Chemin de l'arborescence à créer.<br/>
	 * 
	 * @return boolean : true si l'arborescence a été créée.<br/>
	 */
	private boolean creerArborescence(
			final String pChemin) {
		
		/* retourne false si pChemin est blank. */
		if (StringUtils.isBlank(pChemin)) {
			return false;
		}
		
		final File cheminFile = new File(pChemin);
		
		/* retourne false si l'arborescence existe déjà. */
		if (cheminFile.exists()) {
			return false;
		}
		
		/* retourne false si pChemin ne contient pas '/'. */
		if (!StringUtils.contains(pChemin, "/")) {
			return false;
		}
		
		/* Récupération des répertoires par découpage de la chaine. */
		final String[] repertoires = StringUtils.split(pChemin, "/");
		final int nombreRep = repertoires.length;
		
		/* retourne false si un des répertoires du chemin est blank. */
		for (final String rep : repertoires) {
			if (StringUtils.isBlank(rep)) {
				return false;
			}
		}
		
		/* Extraction de la racine. */
		final String repRacineString = repertoires[0];
		
		final File repRacine = new File(repRacineString);
		
		/* retourne false si la racine du chemin n'existe pas. */
		if (!repRacine.exists()) {
			return false;
		}
		
		/* retourne false si la racine du chemin n'est pas un répertoire. */
		if (!repRacine.isDirectory()) {
			return false;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		stb.append(repRacineString);
		
		/* Boucle sur les répertoires du chemin. */
		for (int i = 1; i < nombreRep; i++) {
			
			/* Création du chemin du répertoire à créer. */
			stb.append(SEP_REP);
			stb.append(repertoires[i]);
			
			final File repertoireFile = new File(stb.toString());
			
			/* Créée le répertoire au chemin de création 
			 * si il n'existait pas.*/
			if (!repertoireFile.exists()) {
				
				if (!repertoireFile.mkdir()) {
					/* retourne false si un répertoire 
					 * a créer n'a pas été créé. */
					return false;
				}
			}
			
		} // Fin de boucle.________________________
		
		/* retourne true si l'arborescence a été créée. */
		return true;
		
	} // Fin de creerArborescence(
	 // String pChemin).___________________________________________________
	
	
	
	/**
	 * method detruireArborescence(
	 * String pChemin) :<br/>
	 * SERVICE ACCESSOIRE.<br/>
	 * Détruit le répertoire situé au chemin pChemin.<br/>
	 * Vide le contenu du répertoire si nécessaire avant de le supprimer.<br/>
	 * <br/>
	 * - retourne false si pChemin est blank.<br/>
	 * - retourne false si le répertoire à détruire n'existe pas.<br/>
	 * - retourne false si le File à détruire n'est pas un répertoire.
	 * <br/>
	 *
	 * @param pChemin : String : Chemin du répertoire à détruire.<br/>
	 * 
	 * @return : boolean : true si le répertoire a été détruit.<br/>
	 */
	public final boolean detruireArborescence(
			final String pChemin) {
		
		/* retourne false si pChemin est blank. */
		if (StringUtils.isBlank(pChemin)) {
			return false;
		}
					
		final File repADetruire = new File(pChemin);
		
		/* retourne false si le répertoire à détruire n'existe pas. */
		if (!repADetruire.exists()) {
			return false;
		}
		
		/* retourne false si le File à détruire n'est pas un répertoire. */
		if (!repADetruire.isDirectory()) {
			return false;
		}
					
		/* Détruit le répertoire et retourne le boolean. */				
		try {
			
			/* Vide d'abord le contenu du répertoire. */
			viderRepertoireADetruire(repADetruire);
			
			/* Détruit le répertoire. */
			return repADetruire.delete();
			
		} catch (Exception e) {
			
			/* LOG de niveau INFO. */
			loggerInfo(
					CLASSE_ABSTRACTCONTROLE
						, METHODE_DETRUIRE_ARBORESCENCE
							, e.getMessage());
			
			return false;
			
		}
				
	} // Fin de detruireArborescence(
	 // String pChemin).___________________________________________________
	
	

	/**
	 * method viderRepertoireADetruire(
	 * File pRep) :<br/>
	 * SERVICE ACCESSOIRE.<br/>
	 * Vide tout le contenu du répertoire pRep sans écraser pRep.<br/>
	 * méthode récursive.<br/>
	 * Il est indispensable de vider tout le contenu d'un répertoire 
	 * avant de pouvoir supprimer celui-ci en Java.<br/>
	 * <br/>
	 * Retourne un boolean à true si le 
	 * contenu du répertoire a bien été effacé.<br/>
	 * <br/>
	 * - retourne false si pRep == null.<br/>
	 * - retourne false si pRep n'existe pas.<br/>
	 * - retourne false si pRep n'est pas un répertoire.<br/>
	 * <br/>
	 *
	 * @param pRep : File : Répertoire dont on veut vider 
	 * tout le contenu le contenu tout en le conservant.<br/>
	 * 
	 * @return : boolean : true si le contenu du répertoire a été vidé.<br/>
	 */
	public final boolean viderRepertoireADetruire(
			final File pRep) {
				
		/* retourne false si pRep == null. */
		if (pRep == null) {
			return false;
		}
		
		/* retourne false si pRep n'existe pas. */
		if (!pRep.exists()) {
			return false;
		}
		
		/* retourne false si pRep n'est pas un répertoire. */
		if (!pRep.isDirectory()) {
			return false;
		}
		
		/* Récupération des File dans pRep. */
		final File[] filesContenus = pRep.listFiles();
		
		if (filesContenus == null) {
			return true;
		}
		
		/* Sort Si pRep est vide. */
		if (filesContenus.length == 0) {
			return true;
		}
		
		/* Si pRep non vide. */
		/* ForEach (boucle) sur les File de pRep. ******/
		for (final File file : filesContenus) {
			
			/* Appel récursif si file est un répertoire. */
			if (file.isDirectory()) {
				
				/* APPEL RECURSIF. */
				viderRepertoireADetruire(file);
				
				
			} // Fin de if (!file.isDirectory()).___________
			
			/* Destruction du file dans tous les cas. */					
			try {
				
				file.delete();
				
			} catch (Exception e) {
				
				/* LOG de niveau INFO. */
				loggerInfo(
						CLASSE_ABSTRACTCONTROLE
							, METHODE_VIDER_REPERTOIRE
								, e.getMessage());
				return false;
				
			}
								
		} // Fin du ForEach (boucle) sur les File de pRep.___
		
		return true;
		
	} // Fin de viderRepertoireADetruire(
	 // File pRep).________________________________________________________
	

	
	/**
	 * method fournirRepertoireParent(
	 * File pFile) :<br/>
	 * Retourne le répertoire parent d'un File pFile.<br/>
	 * pFile n'a pas besoin d'être un file existant.<br/> 
	 * En revanche, le répertoire parent retourné doit exister.<br/> 
	 * La méthode retourne le parent dans le chemin abstrait de pFile.<br/>
	 * <br/>
	 * - retourne null si pFile == null.<br/>
	 * - retourne null si le parent de pFile 
	 * n'est pas un répertoire existant.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @return : File : Répertoire parent existant.<br/>
	 */
	protected final File fournirRepertoireParent(
			final File pFile) {
		
		/* retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		final File resultat = pFile.getParentFile();
		
		/* retourne null si le parent de pFile 
		 * n'est pas un répertoire existant. */
		if (!resultat.isDirectory()) {
			return null;
		}
		
		return resultat;
		
	} // Fin de fournirRepertoireParent(
	 // File pFile)._______________________________________________________
	

	
	/**
	 * method fournirRepertoireGrandParent(
	 * File pFile) :<br/>
	 * Retourne le répertoire grand-parent d'un File pFile.<br/>
	 * pFile n'a pas besoin d'être un file existant.<br/> 
	 * En revanche, le répertoire parent 
	 * et le répertoire grand-parent retourné doivent exister.<br/> 
	 * La méthode retourne le grand-parent 
	 * dans le chemin abstrait de pFile.<br/>
	 * <br/>
	 * - retourne null si pFile == null.<br/>
	 * - retourne null si le parent de pFile 
	 * n'est pas un répertoire existant.<br/>
	 * - retourne null si le grand-parent de pFile 
	 * n'est pas un répertoire existant.<br/>
	 * <br/>
	 *
	 * @param pFile : File.<br/>
	 * @return : File : Répertoire grand-parent existant.<br/>
	 */
	protected final File fournirRepertoireGrandParent(
			final File pFile) {
		
		/* retourne null si pFile == null. */
		if (pFile == null) {
			return null;
		}
		
		final File fileParent = this.fournirRepertoireParent(pFile);
		
		/* retourne null si le parent de pFile 
		 * n'est pas un répertoire existant.*/
		if (!fileParent.isDirectory()) {
			return null;
		}
		
		final File resultat = this.fournirRepertoireParent(fileParent);
		
		/* retourne null si le grand-parent de pFile 
		 * n'est pas un répertoire existant. */
		if (!resultat.isDirectory()) {
			return null;
		}
		
		return resultat;
		
	} // Fin de fournirRepertoireGrandParent(
	 // File pFile)._______________________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Integer getOrdreControle() {
		return this.ordreControle;
	} // Fin de getOrdreControle().________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setOrdreControle(
			final Integer pOrdreControle) {
		this.ordreControle = pOrdreControle;
	} // Fin de setOrdreControle(
	 // Integer pOrdreControle).___________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Date getDateControle() {
		return (Date) this.dateControle.clone();
	} // Fin de getDateControle()._________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setDateControle(
			final Date pDateControle) {
		
		if (pDateControle != null) {
			
			this.dateControle = (Date) pDateControle.clone();
			
			/* calcule automatiquement dateControleStringFormattee. */
			this.dateControleStringFormatee 
				= this.fournirDateFormatee(this.dateControle);

		}
		
	} // Fin de setDateControle(
	 // Date pDateControle)._______________________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getDateControleStringFormatee() {
		return this.dateControleStringFormatee;
	} // Fin de getDateControleStringFormatee().___________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getUserName() {
		return this.userName;
	} // Fin de getUserName()._____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setUserName(
			final String pUserName) {
		this.userName = this.fournirUserName(pUserName);
	} // Fin de setUserName(
	 // String pUserName)._________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getFichier() {
		return this.fichier;
	} // Fin de getFichier().______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setFichier(
			final File pFichier) {
		
		this.fichier = pFichier;
		
		/* calcule automatiquement nomFichier. */
		this.nomFichier = this.fournirNomFichier(this.fichier);
		
	} // Fin de setFichier(
	 // File pFichier).____________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomFichier() {
		return this.nomFichier;
	} // Fin de getNomFichier().___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getTypeControle() {
		return this.typeControle;
	} // Fin de getTypeControle()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomControle() {
		return this.nomControle;
	} // Fin de getNomControle().__________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNomCritere() {
		return this.nomCritere;
	} // Fin de getNomCritere().___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getGravite() {
		return this.gravite;
	} // Fin de getGravite().______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getNiveauAnomalie() {
		return this.niveauAnomalie;
	} // Fin de getNiveauAnomalie()._______________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isEstBloquant() {
		return this.estBloquant;
	} // Fin de isEstBloquant().___________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean isaEffectuer() {
		return this.aEffectuer;
	} // Fin de isaEffectuer().____________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<LigneRapport> getRapport() {
		return this.rapport;
	} // Fin de getRapport().______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final SortedMap<Integer, String> getFichierEnMap() {
		return this.fichierEnMap;
	} // Fin de getFichierEnMap()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract File getFichierTraite();
	
		
} // FIN DE LA CLASSE AbstractControle.--------------------------------------
