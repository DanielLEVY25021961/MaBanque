package levy.daniel.application.model.services.utilitaires.ecriveurpackageinfo.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import levy.daniel.application.model.services.utilitaires.arboresceurprojet.ArboresceurProjetCible;
import levy.daniel.application.model.services.utilitaires.ecriveurpackageinfo.IEcriveurPackageInfoService;
import levy.daniel.application.model.services.utilitaires.generateurprojet.impl.GenerateurProjetService;

/**
 * CLASSE EcriveurPackageInfoService :<br/>
 * Classe concrète SERVICE chargée d'<b>écrire les package-info sur disque 
 * dans un projet cible</b>.<br/>
 * L'arborescence dans le projet cible doit avoir été générée 
 * par un {@link GenerateurProjetService}.<br/>
 * L'arborescence à copier dans le projet cible est fournie 
 * par un {@link ArboresceurProjetCible}.<br/>
 * <br/>
 * <p>
 * <b><span style="text-decoration: underline;">
 * DIAGRAMME DE CLASSES de EcriveurPackageInfo :
 * </span></b>
 * </p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/ecriveurpackageinfo/ecriveur_packageinfo.png" 
 * alt="diagramme de classes de ecriveurpackageinfo" border="1" align="center" />
 * </div>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * path absolu du projet eclipse courant, path courant, <br/>
 * Path courant, <br/>
 * pathRelatif = pProjetCiblePath.relativize(pPathDansProjetCible),<br/>
 * path relatif, relativize, soustraire path, <br/>
 * path relatif, <br/>
 * path relatif de path enfant par rapport à path parent,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 1 déc. 2018
 *
 */
public class EcriveurPackageInfoService implements IEcriveurPackageInfoService {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(EcriveurPackageInfoService.class);

	// *************************METHODES************************************/
	
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public EcriveurPackageInfoService() {
		super();
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void genererPackageInfo(
			final Map<String, Path> pArboMain
			, final Path projetCiblePath) throws IOException {

		/* ne fait rien si pArboMain == null. */
		if (pArboMain == null) {
			return;
		}

		final Set<Entry<String, Path>> entrySet = pArboMain.entrySet();

		final Iterator<Entry<String, Path>> ite = entrySet.iterator();

		while (ite.hasNext()) {

			final Entry<String, Path> entry = ite.next();
			final Path pathDansProjetCible = entry.getValue();

			if (pathDansProjetCible != null) {

				final Path pathFichierDestination 
					= pathDansProjetCible.resolve("package-info.java")
						.toAbsolutePath().normalize();

				final File packageInfoACopier 
					= fournirFichierACopier(
							pathDansProjetCible, projetCiblePath);

				if (packageInfoACopier != null) {

					try {

						Files.copy(
								packageInfoACopier.toPath()
								, pathFichierDestination
								, StandardCopyOption.REPLACE_EXISTING);

					} catch (Exception e) {
						continue;
					}
				}
			}
		}

	} // Fin de genererPackageInfo(...).___________________________________
	

	
	/**
	 * <b>fournit le fichier package-info</b> 
	 * correspondant à pPathDansProjetCible.<br/>
	 * Le présent projet suit l'arborescence du projet cible 
	 * et contient tous les bons package-info 
	 * dans les bons répertoires.<br/>
	 * <br/>
	 * - retourne null si il n'existe pas de package-info 
	 * pour pPathDansProjetCible.<br/>
	 * - retourne null si pPathDansProjetCible == null.<br/>
	 * - retourne null si pProjetCiblePath == null.<br/>
	 * <br/>
	 *
	 * @param pPathDansProjetCible : Path : 
	 * path dans le projet cible dans lequel écrire un package-info.<br/>
	 * @param pProjetCiblePath : Path : 
	 * Path du projet cible.<br/>
	 * 
	 * @return : File : 
	 * package-info dans la présent projet correspondant 
	 * à pPathDansProjetCible.<br/>
	 */
	private File fournirFichierACopier(
			final Path pPathDansProjetCible
				, final Path pProjetCiblePath) {
		
		/* retourne null si pPathDansProjetCible == null. */
		if (pPathDansProjetCible == null) {
			return null;
		}
		
		/* retourne null si pProjetCiblePath == null. */
		if (pProjetCiblePath == null) {
			return null;
		}
		
		/* extraction du path relatif de pPathDansProjetCible 
		 * par rapport à pProjetCiblePath. */
		final Path pathRelatif 
			= this.fournirPathRelatif(
					pProjetCiblePath, pPathDansProjetCible);
		
		/* path absolu du répertoire dans le présent projet contenant 
		 * le package-info correspondant à pPathDansProjetCible. */
		final Path pathAbsoluRepFichierACopier 
			= this.fournirPathAbsoluDansPresentProjet(pathRelatif)
				.toAbsolutePath().normalize();
		
		/* path absolu package-info dans le présent projet 
		 * correspondant à pPathDansProjetCible. */
		final Path pathAbsoluFichierACopier 
			= pathAbsoluRepFichierACopier.resolve("package-info.java")
				.toAbsolutePath().normalize();
		
		final File fichierACopier = pathAbsoluFichierACopier.toFile();
		
		if (fichierACopier.exists()) {
			return fichierACopier;
		}
		
		/* retourne null si il n'existe pas de package-info 
		 * pour pPathDansProjetCible. */
		return null;
		
	} // Fin de fournirFichierACopier(...).________________________________
	

	
	/**
	 * <b>fournit le PATH RELATIF de pPathEnfant 
	 * par rapport à pPathParent</b>.<br/>
	 * pPathEnfant est donc "plus long" que pPathParent 
	 * et descend de lui.<br/>
	 * <ul>
	 * par exemple :<br/>
	 * <li>si pPathParent = "D:/eclipse/toto"</li>
	 * <li>si pPathEnfant = "D:/eclipse/toto/tata/titi"</li>
	 * <li><code>fournirPathRelatif(pPathParent, pPathEnfant)</code> 
	 * retourne <b>"tata/titi"</b>.</li>
	 * <li>utilise 
	 * <code>pPathParent.relativize(pPathEnfant);</code></li>
	 * </ul>
	 * - retourne null si pPathParent == null.<br/>
	 * - retourne null si pPathEnfant == null.<br/>
	 * <br/>
	 *
	 * @param pPathParent : Path.<br/>
	 * @param pPathEnfant : Path.<br/>
	 * 
	 * @return : Path : Path relatif.<br/>
	 */
	private Path fournirPathRelatif(
			final Path pPathParent
				, final Path pPathEnfant) {
		
		/* retourne null si pPathParent == null. */
		if (pPathParent == null) {
			return null;
		}
		
		/* retourne null si pPathEnfant == null. */
		if (pPathEnfant == null) {
			return null;
		}
		
		return pPathParent.relativize(pPathEnfant);
		
	} // Fin de fournirPathRelatif(...).___________________________________
	
	
	
	/**
	 * <b>retourne le Path absolu du projet Eclipse courant</b>.<br/>
	 * <ul>
	 * <li>utilise <code>Paths.get(".").toAbsolutePath();</code>.</li>
	 * </ul>
	 *
	 * @return : Path : 
	 * Path absolu du projet Eclipse courant.<br/>
	 */
	private Path fournirPathAbsoluPresentProjet() {
		
		final Path pathAbsoluPresentProjet 
			= Paths.get(".")
				.toAbsolutePath().normalize();
		
		return pathAbsoluPresentProjet;
		
	} // Fin de fournirPathAbsoluPresentProjet().__________________________
	
	
	
	/**
	 * <b>fournit le PATH ABSOLU 
	 * pathPresentProjet/ + pPathRelatif</b>.<br/>
	 *
	 * @param pPathRelatif : Path.<br/>
	 * 
	 * @return : Path : 
	 * Path absolu dans le présent projet Eclipse.<br/>
	 */
	private Path fournirPathAbsoluDansPresentProjet(
							final Path pPathRelatif) {
		
		final Path pathAbsoluDansPresentProjet 
			= this.fournirPathAbsoluPresentProjet()
				.resolve(pPathRelatif)
					.toAbsolutePath().normalize();
		
		return pathAbsoluDansPresentProjet;
		
	} // Fin de fournirPathAbsoluDansPresentProjet(...).___________________
	
	
	
} // FIN DE LA CLASSE EcriveurPackageInfoService.----------------------------
