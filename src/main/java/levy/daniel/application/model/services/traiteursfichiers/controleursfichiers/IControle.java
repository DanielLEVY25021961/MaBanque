package levy.daniel.application.model.services.traiteursfichiers.controleursfichiers;

import java.io.File;
import java.util.Date;
import java.util.Locale;

import levy.daniel.application.IConstantesSautsLigne;
import levy.daniel.application.model.services.traiteursfichiers.IEnregistreurRapport;
import levy.daniel.application.model.services.traiteursfichiers.ILecteurDecodeurFile;
import levy.daniel.application.model.services.traiteursfichiers.IListeurDeCaracteresUnicode;
import levy.daniel.application.model.services.traiteursfichiers.IRapporteurControle;

/**
 * Interface IControle :<br/>
 * INTERFACE (Abstraction) qui factorise le concept de Contrôle de Fichier, 
 * publie ses méthodes et garantit que :<br/>
 * <ul>
 * <li>Tout contrôle peut connaitre son ordre 
 * d'exécution dans un enchaînement de contrôles.</li><br/>
 * <li>Tout contrôle est effectué à une 'dateControle'.<br/> 
 * En principe, dateControle vaut la date système 
 * au moment de l'exécution du contrôle.<br/> 
 * La classe calcule automatiquement 'dateControleStringFormatee' 
 * (c'est à dire l'objet Java Date exprimé sous forme de String formatée)
 * connaissant dateControle.</li><br/>
 * <li>Tout contrôle est effectué par un utilisateur (user) 
 * dont on connait le nom 'userName'. La classe remplit automatiquement 
 * userName avec 'Administrateur' si on ne lui fournit pas de userName.</li><br/>
 * <li>Tout contrôle s'applique sur un File 'fichier'. 
 * La classe calcule automatiquement 'nomFichier' connaissant fichier.</li><br/>
 * <li>Tout contrôle appartient à un type de contrôle 'typeControle' 
 * comme "contrôle de surface". 
 * 'typeControle' est fourni par chaque classe concrète.</li><br/>
 * <li>Tout contrôle a un nom 'nomControle' comme 'contrôle fichier texte'. 
 * 'nomControle' est fourni par chaque classe concrète.</li><br/>
 * <li>Tout contrôle vérifie un critère 'nomCritere' comme 
 * 'le fichier ne doit pas comporter de caractères indésirables'. 
 * 'nomCritere' est fourni par chaque classe concrète.</li><br/>
 * <li>Tout contrôle a une gravité 'gravite' comme '1 - bloquant'. 
 * Cette gravité est directement liée au niveau d'anomalie du contrôle 
 * 'niveauAnomalie' comme "1" pour bloquant. 
 * Chaque classe concrète fournit le 'niveauAnomalie' du contrôle 
 * via sa méthode fournirCleNiveauAnomalie() qui permet d'aller 
 * chercher la valeur dans messagescontroles_fr_FR.properties 
 * ou via fournirNiveauAnomalieEnDur().</li><br/>
 * <li>Tout contrôle sait si il est bloquant via 'estBloquant'. 
 * La classe remplit automatiquement 'estBloquant' 
 * connaissant niveauAnomalie.</li><br/>
 * <li>Tout contrôle fournit un rapport de contrôle 
 * sous forme de List&lt;LigneRapport&gt; 'rapport'.</li><br/>
 *</ul>
 * <br/>
 * <br/>
 * Attributs : <br/>
 * [nomClasseConcrete;dateControle;dateControleStringFormatee;userName;
 * fichier;nomFichier;typeControle;nomControle;nomCritere;gravite;
 * niveauAnomalie;estBloquant;rapport].<br/>
 * <br/>
 * <ul>
 * comporte essentiellement : <br/>
 * <li>une méthode controler(File pFile, boolean pEnregistrerRapport) 
 * qui permet de contrôler un fichier pFile 
 * et de retourner true si le fichier passe favorablement le contrôle.<br/>
 * Cette méthode peut écrire le rapport de contrôle 
 * sous forme de List&lt;LigneRapport&gt; sur disque 
 * et génère 
 * également un compte rendu d'enregistrement 
 * sous forme de List&lt;LigneRapportEnregistrement&gt;.</li><br/>
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
 * Charset de décodage pCharset, lireFichier(File pFile)<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * levy.daniel.application.ILecteurDecodeurFile.<br/>
 * levy.daniel.application.IListeurDeCaracteresUnicode.<br/>
 * levy.daniel.application.IExportateurCsv.<br/>
 * levy.daniel.application.IExportateurJTable.<br/>
 * levy.daniel.application.IResetable.<br/>
 * levy.daniel.application.metier.controles.rapportscontroles.LigneRapport.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.rapportsenregistrements.LigneRapportEnregistrement.<br/>
 * levy.daniel.application.metier.controles.IEnregistreurRapport.<br/>
 * levy.daniel.application.metier.controles.IRapporteurControle.<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 29 févr. 2016
 *
 */
public interface IControle extends IRapporteurControle
			, ILecteurDecodeurFile, IListeurDeCaracteresUnicode
				, IEnregistreurRapport, IConstantesSautsLigne {

	/**
	 * new Locale("fr", "FR").<br/>
	 */
	Locale LOCALE_FR_FR = new Locale("fr", "FR");
	
	//*****************************************************************/
	//**************************** SAUTS ******************************/
	//*****************************************************************/
	// définis dans IConstantesSautsLigne	
			
	/**
	 * Caractère de remplacement introduit lors de la lecture en UTF-8 
	 * d'un fichier texte encodé avec un autre Charset.<br/>
	 * REPLACEMENT CHARACTER."\\ufffd" '�'.<br/> 
	 */
	char CARACTERE_REMPLACEMENT = '\ufffd';
	
	/**
	 * BOM UTF-8 pour forcer Excel 2010 à lire en UTF-8.<br/>
	 */
	char BOM_UTF_8 = '\uFEFF';

	
	/**
	 * SEP_MOINS : String :<br/>
	 * " - ".<br/>
	 */
	String SEP_MOINS = " - ";
	
	
	/**
	 * UNDERSCORE : char :<br/>
	 * '_'.<br/>
	 */
	char UNDERSCORE = '_';
	
	
	/**
	 * POINT : char :<br/>
	 * '.'.<br/>
	 */
	char POINT = '.';
	
	
	/**
	 * "/".<br/>
	 */
	String SEPARATEUR_FILE = "/";
		
	/**
	 * Séparateur Java pour les répertoires "/".<br/>
	 */
	String SEP_REP = "/";
	
	/**
	 * ";".<br/>
	 */
	String SEP_POINTVIRGULE = ";";

	/**
	 * "null".<br/>
	 */
	String NULL = "null";
	
	/**
	 * "sans objet".<br/>
	 */
	String SANS_OBJET = "sans objet";
		
	/**
	 * "tous".<br/>
	 */
	String TOUS = "tous";
		
	/**
	 * "Exception GRAVE : ".<br/>
	 */
	String MESSAGE_EXCEPTION = "Exception GRAVE : ";
	
	/**
	 * "Fichier refusé".<br/>
	 */
	String ACTION_FICHIER_REFUSE = "KO - Fichier refusé";
	
	/**
	 * "OK - Fichier accepté".<br/>
	 */
	String ACTION_FICHIER_ACCEPTE = "OK - Fichier accepté";
	
	/**
	 * "Ligne ne comportant pas 520 caractères".<br/>
	 */
	String ACTION_LIGNE_NON_HIT 
		= "Ligne ne comportant pas 520 caractères";

	/**
	 * "Ligne ne comportant pas 57 champs".<br/>
	 */
	String ACTION_LIGNE_NON_DARWIN 
	= "Ligne ne comportant pas 57 champs";


	
	/**
	 * SERVICE PRINCIPAL - N'ENREGISTRE PAS DE RAPPORT SUR DISQUE.<br/>
	 * Contrôle d'un fichier.<br/>
	 * <ul>
	 * <li>Vérifie qu'un fichier passe un contrôle.</li>
	 * <li>Doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.</li>
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
	 * - Met automatiquement le boolean pEnregistrerRapport à false.<br/>
	 * - N'enregistre pas de rapport sur le disque.<br/>
	 * <br/>
	 * RG-01 : Contrôle de validité<br/>
	 * RG-01-Controler : true si favorable.<br/>
	 * <br/>
	 *
	 * @param pFile : File : fichier dont on veut savoir 
	 * si il passe le contrôle.<br/>
	 * 
	 * @return : boolean : true si pFile passe le contrôle.<br/>
	 */
	boolean controler(File pFile);
	
	
	
	/**
	 * SERVICE PRINCIPAL.<br/>
	 * Contrôle d'un fichier.<br/>
	 * - Enregistre le rapport de contrôle sur disque 
	 * si pEnregistrerRapport == true.<br/>
	 * <ul>
	 * <li>Vérifie qu'un fichier passe un contrôle.</li>
	 * <li>Doit retourner true si le contrôle s'effectue favorablement. 
	 * Par exemple, un contrôle vérifiant qu'un fichier est un texte 
	 * doit retourner true si c'est le cas.</li>
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
	boolean controler(File pFile, boolean pEnregistrerRapport);
	
	
	
	/**
	 * Getter de l' ordre d'exécution du contrôle 
	 * dans un enchaînement de contrôles.<br/>
	 *
	 * @return ordreControle : Integer.<br/>
	 */
	Integer getOrdreControle();
	
	

	/**
	 * Setter de l' ordre d'exécution du contrôle 
	 * dans un enchaînement de contrôles.<br/>
	 *
	 * @param pOrdreControle : Integer : 
	 * valeur à passer à ordreControle.<br/>
	 */
	void setOrdreControle(Integer pOrdreControle);
	
	
	
	/**
	 * Getter de la java.util.Date du contrôle.<br/>
	 *
	 * @return dateControle : Date.<br/>
	 */
	Date getDateControle();
	
	

	/**
	 * Setter de la java.util.Date du contrôle.<br/>
	 * <br/>
	 * - calcule automatiquement dateControleStringFormattee.<br/>
	 *
	 * @param pDateControle : Date : valeur à passer à dateControle.<br/>
	 */
	void setDateControle(Date pDateControle);
	
	

	/**
	 * Getter de la date du contrôle formattée 
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
	 * Getter du nom de l'utilisateur qui a déclenché le contrôle.<br/>
	 *
	 * @return userName : String.<br/>
	 */
	String getUserName();
	
	

	/**
	 * Setter du nom de l'utilisateur qui a déclenché le contrôle.<br/>
	 * <br/>
	 * remplit userName avec pUserName si pUserName != null 
	 * ou 'Administrateur' sinon.<br/>
	 *
	 * @param pUserName : String : 
	 * valeur à passer à userName.<br/>
	 */
	void setUserName(String pUserName);
	
	

	/**
	 * Getter du fichier sur lequel s'applique le contrôle.<br/>
	 *
	 * @return fichier : File.<br/>
	 */
	File getFichier();
	
	

	/**
	 * Setter du fichier sur lequel s'applique le contrôle.<br/>
	 * <br/>
	 * - calcule automatiquement nomFichier.<br/>
	 *
	 * @param pFichier : File : valeur à passer à fichier.<br/>
	 */
	void setFichier(File pFichier);
	
	

	/**
	 * Getter du nom du fichier objet du contrôle.<br/>
	 *
	 * @return nomFichier : String.<br/>
	 */
	String getNomFichier();

	
	
	/**
	 * Getter du type du contrôle ('contrôle de surface' par exemple).<br/>
	 *
	 * @return typeControle : String.<br/>
	 */
	String getTypeControle();

	
	
	/**
	 * Getter du nom du contrôle ('contrôle fichier texte' par exemple).<br/>
	 *
	 * @return nomControle : String.<br/>
	 */
	String getNomControle();

	
	
	/**
	 * Getter du nom du critère appliqué dans le contrôle 
	 * ('le fichier ne doit pas comporter de 
	 * caractères indésirables' par exemple).<br/>
	 *
	 * @return nomCritere : String.<br/>
	 */
	String getNomCritere();
	
	

	/**
	 * Getter de la désignation de la gravité de ce contrôle 
	 * (par exemple '1 - bloquant').<br/>
	 *
	 * @return gravite : String.<br/>
	 */
	String getGravite();
	
	

	/**
	 * Getter du Niveau de l'anomalie correspondant au Contrôle
	 * dans le messagescontroles_fr_FR.properties.<br/>
	 * Par exemple : "1" pour le ControleurTypeTexte.<br/>
	 *
	 * @return niveauAnomalie : String.<br/>
	 */
	String getNiveauAnomalie();

	
	
	/**
	 * Getter du boolean qui stipule si le contrôle doit pouvoir 
	 * bloquer le programme.<br/>
	 * true si le contrôle doit pouvoir bloquer le programme.<br/>
	 *
	 * @return estBloquant : boolean.<br/>
	 */
	boolean isEstBloquant();

	
	
	/**
	 * Getter du boolean qui stipule si le contrôle doit être effectué 
	 * dans un enchaînement de contrôles.<br/>
	 * Cette valeur doit figurer dans le messagescontroles_fr_FR.properties 
	 * ou être fournie en dur par les classes concrètes.<br/>
	 * true si le contrôle doit être effectué.<br/>
	 * Contrôle paramétrable.<br/>
	 *
	 * @return aEffectuer : boolean.<br/>
	 */
	boolean isaEffectuer();

	
	
	/**
	 * Retourne le fichier résultant du contrôle ou du traitement.<br/>
	 * Utile pour l'enchaînement des contrôles.<br/>
	 *
	 * @return : File : fichier résultant du contrôle ou du traitement.<br/>
	 */
	File getFichierTraite();
	
	

} // FIN DE L'INTERFACE IControle.-------------------------------------------