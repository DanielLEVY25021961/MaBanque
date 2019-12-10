package levy.daniel.application.model.services.traiteursfichiers.enregistreursfichiers.impl;

import java.io.File;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.traiteursfichiers.enregistreursfichiers.AbstractEnregistreurFichiers;


/**
 * class EnregistreurFichiers :<br/>
 * Classe utilitaire concrète qui fournit un service d'enregistrement 
 * de fichiers sur disque.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * levy.daniel.application.IExportateurCsv 
 * (sous-entendu, pas d'implémentation directe).<br/>
 * levy.daniel.application.IExportateurJTable 
 * (sous-entendu, pas d'implémentation directe).<br/>
 * levy.daniel.application.IResetable 
 * (sous-entendu, pas d'implémentation directe).<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.rapportsenregistrements.LigneRapportEnregistrement.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.IRapporteurEnregistrement.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.IEnregistreurFichiers.<br/>
 * levy.daniel.application.metier.services.enregistreursfichiers.AbstractEnregistreurFichiers.<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 3 mars 2016
 *
 */
public class EnregistreurFichiers extends AbstractEnregistreurFichiers {

	// ************************ATTRIBUTS************************************/

	/**
	 * CLASSE_ENREGISTREUR_FICHIERS : String :<br/>
	 * "Classe EnregistreurFichiers".<br/>
	 */
	public static final String CLASSE_ENREGISTREUR_FICHIERS 
		= "Classe EnregistreurFichiers";

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(EnregistreurFichiers.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR EnregistreurFichiers() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 * - Remplit le nom de la classe concrète this.nomClasseConcrete 
	 * fourni par this.fournirNomClasseConcrete() 
	 * dans la classe concrète.<br/>
	 * - Met automatiquement dateEnregistrement à date système.<br/>
	 * - Met automatiquement userName à "Administrateur".<br/>
	 * <br/>
	 * - calcule automatiquement dateEnregistrementStringFormattee.<br/>
	 * - passe null à this.objet.<br/>
	 * - passe null à this.fichier.<br/>
	 * - calcule automatiquement nomFichier (null).<br/>
	 * <br/>
	 */
	public EnregistreurFichiers() {
		this(null, null, null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	 /**
	 * method CONSTRUCTEUR EnregistreurFichiers(
	 * String pObjet) :<br/>
	 * Constructeur avec objet.<br/>
	 * <br/>
	 * - Remplit le nom de la classe concrète this.nomClasseConcrete 
	 * fourni par this.fournirNomClasseConcrete() 
	 * dans la classe concrète.<br/>
	 * - Met automatiquement dateEnregistrement à date système.<br/>
	 * - Met automatiquement userName à "Administrateur".<br/>
	 * <br/>
	 * - calcule automatiquement dateEnregistrementStringFormattee.<br/>
	 * - passe pObjet à this.objet.<br/>
	 * - passe null à this.fichier.<br/>
	 * - calcule automatiquement nomFichier (null).<br/>
	 * <br/>
	 *
	 * @param pObjet : String : objet (ou motif) ayant demandé 
	 * la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/>
	 */
	public EnregistreurFichiers(
			final String pObjet) {
		this(null, null, pObjet, null);
	} // Fin de CONSTRUCTEUR EnregistreurFichiers(
	 // String pObjet).____________________________________________________
	

	
	/**
	 * method CONSTRUCTEUR EnregistreurFichiers(
	 * String pObjet
	 * , File pFichier) :<br/>
	 * Constructeur avec objet et fichier.<br/>
	 * <br/>
	 * - Remplit le nom de la classe concrète this.nomClasseConcrete 
	 * fourni par this.fournirNomClasseConcrete() 
	 * dans la classe concrète.<br/>
	 * - Met automatiquement dateEnregistrement à date système.<br/>
	 * - Met automatiquement userName à "Administrateur".<br/>
	 * <br/>
	 * - calcule automatiquement dateEnregistrementStringFormattee.<br/>
	 * - passe pObjet à this.objet.<br/>
	 * - passe pFichier à this.fichier.<br/>
	 * - calcule automatiquement nomFichier.<br/>
	 * <br/>
	 *
	 * @param pObjet : String : objet (ou motif) ayant demandé 
	 * la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/>
	 * @param pFichier : File : fichier enregistré.<br/>
	 */
	public EnregistreurFichiers(
				final String pObjet
						, final File pFichier) {
		
		this(null, null, pObjet, pFichier);
		
	} // Fin de CONSTRUCTEUR EnregistreurFichiers(
	 // String pObjet
	 // , File pFichier).__________________________________________________
	
	
	
	 /**
	 * method CONSTRUCTEUR EnregistreurFichiers(
	 * String pUserName
	 * , String pObjet
	 * , File pFichier) :<br/>
	 * Constructeur avec user, objet et fichier.<br/>
	 * <br/>
	 * - Remplit le nom de la classe concrète this.nomClasseConcrete 
	 * fourni par this.fournirNomClasseConcrete() 
	 * dans la classe concrète.<br/>
	 * - Met automatiquement dateEnregistrement à date système.<br/>
	 * <br/>
	 * - calcule automatiquement dateEnregistrementStringFormattee.<br/>
	 * - remplit userName avec pUserName si pUserName != null 
	 * ou 'Administrateur' sinon.<br/>
	 * - passe pObjet à this.objet.<br/>
	 * - passe pFichier à this.fichier.<br/>
	 * - calcule automatiquement nomFichier.<br/>
	 * <br/>
	 *
	 * @param pUserName : String : 
	 * nom de l'utilisateur qui a déclenché l'enregistrement du fichier.<br/>
	 * @param pObjet : String : objet (ou motif) ayant demandé 
	 * la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/>
	 * @param pFichier : File : fichier enregistré.<br/>
	 */
	public EnregistreurFichiers(
			final String pUserName
					, final String pObjet
						, final File pFichier) {
		
		this(null, pUserName, pObjet, pFichier);
		
	} // Fin de CONSTRUCTEUR EnregistreurFichiers(
	 // String pUserName
	 // , String pObjet
	 // , File pFichier).__________________________________________________
	
	
	
	 /**
	 * method CONSTRUCTEUR EnregistreurFichiers(COMPLET) :<br/>
	 * CONSTRUCTEUR COMPLET.<br/>
	 * <br/>
	 * - Remplit le nom de la classe concrète this.nomClasseConcrete 
	 * fourni par this.fournirNomClasseConcrete() 
	 * dans la classe concrète.<br/>
	 * - Remplit dateEnregistrement avec pDateEnregistrement 
	 * si pDateEnregistrement != null 
	 * ou la date système sinon.<br/>
	 * - calcule automatiquement dateEnregistrementStringFormattee.<br/>
	 * - remplit userName avec pUserName si pUserName != null 
	 * ou 'Administrateur' sinon.<br/>
	 * - passe pObjet à this.objet.<br/>
	 * - passe pFichier à this.fichier.<br/>
	 * - calcule automatiquement nomFichier.<br/>
	 * <br/>
	 * 
	 * @param pDateEnregistrement : Date : 
	 * java.util.Date de l'enregistrement du fichier.<br/>
	 * @param pUserName : String : 
	 * nom de l'utilisateur qui a déclenché l'enregistrement du fichier.<br/>
	 * @param pObjet : String : objet (ou motif) ayant demandé 
	 * la création du fichier 
	 * comme 'contrôle de lignes vide'.<br/>
	 * @param pFichier : File : fichier enregistré.<br/>
	 */
	public EnregistreurFichiers(
			final Date pDateEnregistrement
				, final String pUserName
					, final String pObjet
						, final File pFichier) {
		
		super(pDateEnregistrement, pUserName, pObjet, pFichier);
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
	

	/**
	 * ""Classe EnregistreurFichiers"".<br/>
	 * <br/>
	 * {@inheritDoc}
	 */
	@Override
	protected final String fournirNomClasseConcrete() {
		return CLASSE_ENREGISTREUR_FICHIERS;
	} // Fin de fournirNomClasseConcrete().________________________________
	

	
} // Fin DE LA CLASSE EnregistreurFichiers.----------------------------------
