package levy.daniel.application.model.services.traiteursfichiers.controleursfichiers;

import java.io.File;
import java.util.Date;
import java.util.Locale;
import java.util.SortedMap;

import levy.daniel.application.model.services.traiteursfichiers.IEnregistreurRapport;
import levy.daniel.application.model.services.traiteursfichiers.IRapporteurControle;


/**
 * INTERFACE IEnchaineurControles :<br/>
 * Abstraction qui garantit que :<br/>
 * - Tout enchaînement de contrôles est effectué à une 'dateContrôle'. 
 * La classe calcule automatiquement 'dateControleStringFormatee' 
 * connaissant dateControle.<br/>
 * - Tout enchaînement de contrôles est effectué par un utilisateur (user) 
 * dont on connait le nom 'userName'. La classe remplit automatiquement 
 * userName avec 'Administrateur' si on ne lui fournit pas de userName.<br/>
 * - Tout enchaînement de contrôles s'applique sur un File 'fichier'. 
 * La classe calcule automatiquement 'nomFichier' connaissant fichier.<br/>
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
 * levy.daniel.application.metier.controles.rapportscontroles.LigneRapport.<br/>
 * levy.daniel.application.metier.controles.IRapporteurControle.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.rapportsenregistrements.LigneRapportEnregistrement.<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 6 mars 2016
 *
 */
public interface IEnchaineurControles 
			extends IRapporteurControle, IEnregistreurRapport {
	
	/**
	 * new Locale("fr", "FR").<br/>
	 */
	Locale LOCALE_FR_FR = new Locale("fr", "FR");
			
	/**
	 * Caractère de remplacement introduit lors de la lecture en UTF-8 
	 * d'un fichier texte encodé avec un autre Charset.<br/>
	 * REPLACEMENT CHARACTER."\\ufffd" '�'.<br/> 
	 */
	char CARACTERE_REMPLACEMENT = '\ufffd';

	/**
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 * '\uFEFF'
	 */
	char BOM_UTF_8 = '\uFEFF';
	
	/**
	 * " - ".<br/>
	 */
	String SEP_MOINS = " - ";
		
	/**
	 * '_'.<br/>
	 */
	char UNDERSCORE = '_';
		
	/**
	 * '.'.<br/>
	 */
	char POINT = '.';
		
	/**
	 * "\\".<br/>
	 */
	String SEPARATEUR_FILE = "\\";
		
	/**
	 * Séparateur Java pour les répertoires "\\".<br/>
	 */
	String SEP_REP = "\\";
	
	/**
	 * ";".<br/>
	 */
	String SEP_POINTVIRGULE = ";";


	
	/**
	 * SERVICE PRINCIPAL.<br/>
	 * Contrôle d'un fichier.<br/>
	 * Vérifie qu'un fichier passe un enchaînement de contrôles.<br/>
	 * Doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.<br/>
	 * <br/>
	 * - Met automatiquement le boolean pEnregistrerRapport à true.<br/>
	 * - Enregistre un rapport de contrôle sur le disque.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dont on veut savoir 
	 * si il passe le contrôle.<br/>
	 * 
	 * @return : boolean : true si pFile passe l'enchaînement de contrôles.<br/>
	 */
	boolean controler(File pFile);
	
	
	
	/**
	 * SERVICE PRINCIPAL.<br/>
	 * Contrôle d'un fichier.<br/>
	 * Vérifie qu'un fichier passe un enchaînement de contrôles.<br/>
	 * Doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.<br/>
	 * <ul>
	 * <li>N'exécute un contrôle que si son aEffectuer vaut true 
	 * (contrôles paramétrables).</li>
	 * <li>passe pFile à this.fichier et 
	 * rafraîchit automatiquement this.nomFichier.</li>
	 * <li>rafraîchit le rapport (en instancie un nouveau à chaque appel 
	 * de la méthode 
	 * <code>controler(File pFile, boolean pEnregistrerRapport)</code>).</li>
	 * <li>Ajoute chaque rapport d'un contrôle à <code>this.rapport</code>.</li>
	 * <li>passe le résultat de la méthode à false si un seul contrôle 
	 * n'est pas favorable.</li>
	 * <li>retourne false, met <code>this.estBloquant</code> 
	 * à true et sort de la méthode 
	 * si un contrôle défavorable est bloquant.</li>
	 * <li>Enregistre le rapport de contrôle sur disque 
	 * si pEnregistrerRapport vaut true.</li>
	 * </ul>
	 * <br/>
	 * - retourne false si this.mapControles == null.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dont on veut savoir 
	 * si il passe le contrôle.<br/>
	 * @param pEnregistrerRapport : boolean : 
	 * true si on veut enregistrer le rapport dans un fichier sur disque.<br/>
	 * 
	 * @return : boolean : true si pFile passe l'enchaînement de contrôles.<br/>
	 */
	boolean controler(File pFile, boolean pEnregistrerRapport);
	
	
	
	/**
	 * SERVICE PRINCIPAL.<br/>
	 * Contrôle d'une String.<br/>
	 * Vérifie qu'une String passe un enchaînement de contrôles.<br/>
	 * Doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.<br/>
	 *
	 * @param pString : String : chaîne de caractères dont on veut savoir 
	 * si elle passe le contrôle.<br/>
	 * @param pEnregistrerRapport : boolean : 
	 * true si on veut enregistrer le rapport dans un fichier sur disque.<br/>
	 * 
	 * @return : boolean : true si pString passe l'enchaînement de contrôles.<br/>
	 */
	boolean controler(String pString, boolean pEnregistrerRapport);
	


	/**
	 * Getter de la java.util.Date de l'enchaînement de contrôles.<br/>
	 *
	 * @return dateControle : Date.<br/>
	 */
	Date getDateControle();
	
	

	/**
	 * Setter de la java.util.Date de l'enchaînement de contrôles.<br/>
	 * <br/>
	 * - calcule automatiquement dateControleStringFormattee.<br/>
	 * <br/>
	 *
	 * @param pDateControle : Date : valeur à passer à dateControle.<br/>
	 */
	void setDateControle(Date pDateControle);
	
	

	/**
	 * Getter de la date de l'enchaînement de contrôles formattée 
	 * au format dfDatetimemilliFrancaiseLexico.<br/>
	 * Format des dates-heures françaises lexicographique 
	 * avec millisecondes comme
	 * '1961-01-25_12-27-07-251'.<br/>
	 * "yyyy-MM-dd_HH-mm-ss-SSS".<br/>
	 *
	 * @return dateControleStringFormatee : String.<br/>
	 */
	String getDateControleStringFormatee();
	
	

	/**
	 * Getter du nom de l'utilisateur qui a déclenché 
	 * l'enchaînement de contrôles.<br/>
	 *
	 * @return userName : String.<br/>
	 */
	String getUserName();
	
	

	/**
	 * Setter du nom de l'utilisateur qui a déclenché 
	 * l'enchaînement de contrôles.<br/>
	 * <br/>
	 * remplit userName avec pUserName si pUserName != null 
	 * ou 'Administrateur' sinon.<br/>
	 *
	 * @param pUserName : String : 
	 * valeur à passer à userName.<br/>
	 */
	void setUserName(String pUserName);
	
	

	/**
	 * Getter du fichier sur lequel s'applique 
	 * l'enchaînement de contrôles.<br/>
	 *
	 * @return fichier : File.<br/>
	 */
	File getFichier();
	
	

	/**
	 * Setter du fichier sur lequel s'applique 
	 * l'enchaînement de contrôles.<br/>
	 * <br/>
	 * - calcule automatiquement nomFichier.<br/>
	 *
	 * @param pFichier : File : valeur à passer à fichier.<br/>
	 */
	void setFichier(File pFichier);
	
	

	/**
	 * Getter du nom du fichier objet 
	 * de l'enchaînement de contrôles.<br/>
	 *
	 * @return nomFichier : String.<br/>
	 */
	String getNomFichier();


	
	/**
	 * indique si l'enchainement de contrôles va bloquer le programme.<br/>
	 *
	 * @return : boolean : true si l'enchainement de contrôles 
	 * va bloquer le programme.<br/>
	 */
	boolean isEstBloquant();
	
	
	
	/**
	 * Getter de la Collection contenant l'ensemble des contrôles 
	 * à enchaîner sur un fichier.<br/>
	 *
	 * @return mapControles : SortedMap&lt;Integer,IControle&gt;.<br/>
	 */
	SortedMap<Integer, IControle> getMapControles();
	
	
	
} // FIN DE L'INTERFACE IEnchaineurControles.--------------------------------
