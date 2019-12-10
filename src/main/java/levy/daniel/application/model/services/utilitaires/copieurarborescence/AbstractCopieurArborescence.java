package levy.daniel.application.model.services.utilitaires.copieurarborescence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE AbstractCopieurArborescence :<br/>
 * Classe abstraite factorisant les attributs 
 * et les méthodes des CopieurArborescence concrets.<br/>
 * Possède une méthode copierArborescence(pRepertoire, pDestination) 
 * qui copie l'arborescence sous pRepertoire dans pDestination.<br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * PRINCIPE DE FONCTIONNEMENT de copieur_arborescence_maven :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/copieur_arborescence_maven/principe_fonctionnement_copieur_arborescence_maven.png" 
 * alt="principe de fonctionnement de copieur_arborescence_maven" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE CLASSES de copieur_arborescence_maven :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/copieur_arborescence_maven/diagramme_classes_copieur_arborescence_maven.png" 
 * alt="diagramme de classes de copieur_arborescence_maven" border="1" align="center" />
 * </div>
 * 
 * <p>
 * <span style="text-decoration: underline;">
 * DIAGRAMME DE SEQUENCE de la méthode <b>copierArborescence(File racineOrigine, String cheminDestination)</b> :
 * </span>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../javadoc/images/copieur_arborescence_maven/methode_copierArborescence-copieur_arborescence_maven.png" 
 * alt="diagramme de séquence de copierArborescence(...)" border="1" align="center" />
 * </div>

 * 
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <code>//  Instanciation d'un CopieurArborescence.</code><br/>
 * <code>final ICopieurArborescence copieur = new CopieurArborescence();</code><br/>
 * <br/>
 * <code>// Copie de l'arborescence sous 'repertoire' sous la nouvelle racine "D:\\Donnees\\toto"</code><br/>
 * <code>copieur.copierArborescence(repertoire, "D:\\Donnees\\toto");</code><br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Création de répertoire, écriture sur disque, <br/>
 * copie d'arborescence, racine, mkdir(), <br/>
 * StringUtils.difference(racine, path)<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 10 oct. 2014
 *
 */
public abstract class AbstractCopieurArborescence 
						implements ICopieurArborescence {

	// ************************ATTRIBUTS************************************/

	/**
	 * chercheur : IChercheurRepertoires :<br/>
	 * chercheur de sous-répertoires.<br/>
	 */
	protected transient IChercheurRepertoires chercheur;

	
	/**
	 * listeSousRepertoires : List&lt;File&gt; :<br/>
	 * Liste des sous-répertoires sous le File pRepertoire 
	 * de la méthode 
	 * copierArborescence(File pRepertoire, String pRacineDestination).<br/>
	 */
	protected transient List<File> listeSousRepertoires;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory
			.getLog(AbstractCopieurArborescence.class);
	

	// *************************METHODES************************************/
	
	
	 /**
	 * method CONSTRUCTEUR AbstractCopieurArborescence() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractCopieurArborescence() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
			
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void copierArborescence(
			final File pRepertoire
				, final String pRacineDestination) {
		
		/* Ne fait rien si pRepertoire est null. */
		if (pRepertoire == null) {
			return;
		}
		
		/* Ne fait rien si pRepertoire n'existe pas. */
		if (!pRepertoire.exists()) {
			return;
		}
		
		/* Ne fait rien si pRepertoire n'est pas un répertoire. */
		if (!pRepertoire.isDirectory()) {
			return;
		}
		
		/* Ne fait rien si pRacineDestination est null.  */
		if (pRacineDestination == null) {
			return;
		}
		
		/* Crée la nouvelle racine destination 
		 * si elle n'existe pas déjà. */
		this.creerRepertoire(pRacineDestination);
		
		/* récupère la racine créée. */
		final File racineDestinationFile = new File(pRacineDestination);
		
		/* Ne fait rien si la racine destination ne peut être créée. */
		if (!racineDestinationFile.exists()) {
			return;
		}
		
		/* Récupération de l'arborescence sous-pRepertoire. */
		this.listeSousRepertoires 
			= this.chercheur.chercherArborescenceSous(pRepertoire);
		
		/* Ne fait rien si la liste des sous-répertoire 
		 * de pRepertoire est null. */
		if (this.listeSousRepertoires == null) {
			return;
		}
				
		/* Ne fait rien si la liste des sous-répertoire 
		 * de pRepertoire est vide. */
		if (this.listeSousRepertoires.isEmpty()) {
			return;
		}
		
		/* Extraction du nom complet de pRepertoire. */
		final String racine = pRepertoire.getAbsolutePath();
		
		
		final List<String> listeCheminsACreer = new ArrayList<String>();
		
		/* BOUCLE sur les sous-répertoires. ***************/
		for (final File sousRep : this.listeSousRepertoires) {
			
			/* Si sousRep est bien un répertoire existant. */
			if (sousRep != null 
					&& sousRep.exists() 
						&& sousRep.isDirectory()) {
				
				/* Extraction du nom complet de sousRep. */
				final String path = sousRep.getAbsolutePath();
				
				/* Extraction de la partie du nom complet 
				 * de sousRep sous la racine. */
				final String cheminSousRacine 
					= StringUtils.difference(racine, path);
				
				if (cheminSousRacine != null) {
					
					/* Création du chemin du sous-répertoire 
					 * à créer lors de la recopie. */
					final String cheminACreer 
						= pRacineDestination + cheminSousRacine;
					
					/* Ajout du chemin à créer dans la liste. */
					listeCheminsACreer.add(cheminACreer);
					
				}
								
			} // Fin de Si sousRep est bien un répertoire existant._____
			
		} // BOUCLE sur les sous-répertoires. ***************
		
		/* CREATION DE L'ARBORESCENCE. */		
		this.creerRepertoires(listeCheminsACreer);
		
	} // Fin de copierArborescence(
	 // File pRepertoire
	 // , String pRacineDestination).______________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void creerRepertoire(
			final String pChemin) {
		
		/* Ne fait rien si pChemin est null. */
		if (pChemin == null) {
			return;
		}
		
		/* Création du répertoire en mémoire. */
		final File repertoireACreer = new File(pChemin);
					
		/* Création du nouveau répertoire si il n'existe pas déjà. */
		/* mkdir() retourne false et ne crée 
		 * rien si le répertoire est déjà existant*/
		repertoireACreer.mkdir();
					
	} // Fin de creerRepertoire(
	 // String pChemin
	// , boolean pEcraser).________________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void creerRepertoires(
			final List<String> pChemins) {
		
		/* Ne fait rien si pChemins est null. */
		if (pChemins == null) {
			return;
		}
		
		/* Ne fait rien si pChemins est vide. */
		if (pChemins.isEmpty()) {
			return;
		}
		
		/* BOUCLE sur tous les chemins de la liste pChemins. ****/
		for (final String chemin : pChemins) {
			
			/* Création du répertoire en mémoire. */
			final File repertoireACreer = new File(chemin); 
						
			/* Création du nouveau répertoire 
			 * si il n'existait pas déjà. */
			/* mkdir() retourne false et ne crée 
			 * rien si le répertoire est déjà existant*/
			repertoireACreer.mkdir();
							
		} // Fin de BOUCLE sur tous les chemins de la liste pChemins. ****
		
	} // Fin de creerRepertoires(
	 // List<String> pChemins).____________________________________________



	/**
	 * method getListeSousRepertoires() :<br/>
	 * Getter de la Liste des sous-répertoires sous le File pRepertoire 
	 * de la méthode 
	 * copierArborescence(File pRepertoire, String pRacineDestination).<br/>
	 * <br/>
	 *
	 * @return listeSousRepertoires : List&lt;File&gt;.<br/>
	 */
	public final List<File> getListeSousRepertoires() {
		return this.listeSousRepertoires;
	} // Fin de getListeSousRepertoires()._________________________________

	
	
} // FIN DE LA CLASSE AbstractCopieurArborescence.---------------------------
