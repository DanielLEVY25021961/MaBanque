package levy.daniel.application.model.services.utilitaires.copieurarborescence;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE AbstractChercheurRepertoires :<br/>
 * Classe abstraite factorisant les attributs 
 * et les méthodes des ChercheurRepertoires concrets.<br/>
 * Possède une méthode chercherArborescenceSous(final File pFile) 
 * qui retourne la liste des sous-répertoires 
 * (pas fichiers simples) de pFile.<br/>
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
 * <br/>
 * <code>// Instanciation d'un ChercheurRepertoires</code><br/>
 * final IChercheurRepertoires chercheur = new ChercheurRepertoires();<br/>
 *<code>// Retourne la liste des sous-répertoires de 'repertoire'</code><br/>
 * final List<File> listeSousRepertoires = chercheur.chercherArborescenceSous(repertoire);<br/>
 * </code>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * lister sous-repertoires, lister sous-répertoires,<br/> 
 * lister arborescence,<br/>
 * Méthode récursive, récursif, file, répertoire, sous-répertoire<br/>
 * recursive, methode recursive,<br/>
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
public class AbstractChercheurRepertoires 
		implements IChercheurRepertoires {

	// ************************ATTRIBUTS************************************/

		/**
		 * LOG : Log : 
		 * Logger pour Log4j (utilisant commons-logging).
		 */
		@SuppressWarnings("unused")
		private static final Log LOG = LogFactory
				.getLog(AbstractChercheurRepertoires.class);

		// *************************METHODES************************************/
		
		
	/**
	 * method CONSTRUCTEUR AbstractChercheurRepertoires() :<br/>
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * <br/>
	 */
	public AbstractChercheurRepertoires() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<File> chercherArborescenceSous(
			final File pFile) {
		
		return this.chercherArborescenceSous(pFile, null);
		
	} // Fin de chercherArborescenceSous(
	 // File pFile)._______________________________________________________

	
	
	/**
	 * method chercherArborescenceSous(
	 * File pFile, List&lt;File&gt; pList) :<br/>
	 * Retourne la liste des sous-répertoires de pFile.<br/>
	 * Méthode récursive.<br/>
	 * APPELER chercherArborescenceSous(pFile, null). 
	 * pList ne sert qu'à la méthode récursive 
	 * pour conserver la liste des sous-répertoires.<br/>
	 * <br/>
	 * - Retourne null si pFile est null.<br/>
	 * - Retourne null si pFile n'existe pas.<br/>
	 * - Retourne null si pFile n'est pas un répertoire.<br/>
	 * - Retourne null si le pFile ne contient pas de sous-répertoires.<br/>
	 * <br/>
	 *
	 * @param pFile : File : le répertoire sous lequel 
	 * on veut connaître l'arboresence.<br/>
	 * @param pList : List&lt;File&gt;
	 * 
	 * @return List&lt;File&gt; : liste des sous-répertoires.<br/>
	 */
	private List<File> chercherArborescenceSous(
			final File pFile, final List<File> pList) {
		
		/* Retourne null si pFile est null. */
		if (pFile == null) {
			return null;
		}
		
		/* Retourne null si pFile n'existe pas. */
		if (!pFile.exists()) {
			return null;
		}
		
		/* Retourne null si pFile n'est pas un répertoire. */
		if (!pFile.isDirectory()) {
			return null;
		}
		
		final File[] contenuNiveauN = pFile.listFiles();
		
		/* Retourne null si le pFile ne contient pas de sous-répertoires. */
		if (contenuNiveauN == null) {
			return null;
		}
		
		List<File> listeRepertoires = null;
		
		if (pList == null) {
			listeRepertoires = new ArrayList<File>();
		}
		else {
			listeRepertoires = pList;
		}
		
		
		File[] contenuNiveauNplus1 = null;
		
		
		
		/* Boucle sur les sous-répertoires de niveau N. **********/
		for (int i = 0; i < contenuNiveauN.length; i++) {
			
			final File contenuNiveauSup = contenuNiveauN[i];
			
			/* Si le sous-répertoire de niveau N est un répertoire. */
			if (contenuNiveauSup != null 
					&& contenuNiveauSup.exists() 
						&& contenuNiveauSup.isDirectory()) {
				
				/* Ajout du sous-répertoire de niveau N à la liste. */
				listeRepertoires.add(contenuNiveauSup);
				
				/* Obtention de la liste des sous-fichiers de rang N+1. */
				contenuNiveauNplus1 = contenuNiveauSup.listFiles();
				
				if (contenuNiveauNplus1 != null) {
					
					/* Boucle sur les sous-répertoires de niveau N+1. ******/
					for (int j = 0; j < contenuNiveauNplus1.length; j++) {
						
						final File contenuNiveauInf = contenuNiveauNplus1[j];
						
						/* Si le sous-répertoire de niveau N+1 est un répertoire. */
						if (contenuNiveauInf != null 
								&& contenuNiveauInf.exists() 
									&& contenuNiveauInf.isDirectory()) {
							
							/* Ajout du sous-répertoire de niveau N+1 à la liste. */
							listeRepertoires.add(contenuNiveauInf);
							
							/* APPEL RECURSIF. */
							this.chercherArborescenceSous(
									contenuNiveauInf, listeRepertoires);
							
						} // Si le sous-répertoire de niveau N+1 est un répertoire.
						
					} // Boucle sur les sous-répertoires de niveau N+1.****
					
				}
				
			} // Si le sous-répertoire de niveau N est un répertoire.
			
		} // Boucle sur les sous-répertoires de niveau N.********
		
		return listeRepertoires;
		
	} // Fin de chercherArborescenceSous(
	 // File pFile, List<File> pList)._____________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<String> chercherCheminsRepertoiresSous(
			final File pFile) {
		
		final List<File> listeSousRepertoires 
			= this.chercherArborescenceSous(pFile);
		
		if (listeSousRepertoires == null) {
			return null;
		}
		
		final List<String> listeCheminsSousRepertoires 
			= this.fournirListeCheminsSousRep(listeSousRepertoires);
					
		return listeCheminsSousRepertoires;
		
	} // Fin de chercherCheminsRepertoiresSous(
	// File pFile).________________________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<String> fournirListeCheminsSousRep(
			final List<File> pListeSousRep) {
		
		/* Retourne null si pListeSousRep est null. */
		if (pListeSousRep == null) {
			return null;
		}
		
		final List<String> resultat = new ArrayList<String>();
		
		for (final File sousRep : pListeSousRep) {
			
			final String cheminSousRep = sousRep.getAbsolutePath();
			resultat.add(cheminSousRep);
		}
		
		return resultat;
		
	} // Fin de fournirListeCheminsSousRep(
	 // List<File> pListeSousRep)._________________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherTableauStrings(
			final String... pTableau) {
		
		/* Retourne null si pTableau est null. */
		if (pTableau == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		if (pTableau.length != 0) {
			
			for (int i = 0; i < pTableau.length; i++) {
				stb.append(pTableau[i]);
				stb.append('\n');
			}
		}
		
		return stb.toString();
		
	} // Fin de afficherTableauStrings(
	 // String[] pTableau).________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherTableauFiles(
			final File... pTableau) {
		
		/* Retourne null si pTableau est null. */
		if (pTableau == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		if (pTableau.length != 0) {
			
			for (int i = 0; i < pTableau.length; i++) {
				stb.append(pTableau[i].getName());
				stb.append('\n');
			}
		}
		
		return stb.toString();
		
	} // Fin de afficherTableauFiles(
	 // File[] pTableau).__________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherListeFiles(
			final List<File> pList) {
		
		/* Retourne null si pList est null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		for (final File file : pList) {
			
			stb.append(file.getAbsolutePath());
			stb.append('\n');
		}
		
		return stb.toString();
		
	} // Fin de afficherListeFiles(
	 // List<File> pList)._________________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String afficherListeChemins(
			final List<String> pList) {
		
		/* Retourne null si pList est null. */
		if (pList == null) {
			return null;
		}
		
		final StringBuffer stb = new StringBuffer();
		
		for (final String chemin : pList) {
			
			stb.append(chemin);
			stb.append('\n');
		}
		
		return stb.toString();
		
	} // Fin de afficherListeChemins(
	 // List<String> pList)._______________________________________________
	
	
	
} // FIN DE LA CLASSE AbstractChercheurRepertoires.--------------------------