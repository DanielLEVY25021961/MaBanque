package levy.daniel.application.model.metier.utilisateur.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import i2.application.cerbere.commun.Application;
import i2.application.cerbere.commun.Cerbere;
import i2.application.cerbere.commun.CerbereConnexionException;
import i2.application.cerbere.commun.Entreprise;
import i2.application.cerbere.commun.Habilitation;
import i2.application.cerbere.commun.Profil;
import i2.application.cerbere.commun.Utilisateur;
import levy.daniel.application.model.metier.utilisateur.ILecteurCerbere;

/**
 * CLASSE LecteurCerbere :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 18 févr. 2019
 *
 */
public class LecteurCerbere implements ILecteurCerbere {

	// ************************ATTRIBUTS************************************/

	/**
	 * ';'.<br/>
	 */
	public static final char POINT_VIRGULE = ';';
	
	/**
	 * ", ".<br/>
	 */
	public static final String VIRGULE_ESPACE = ", ";
	
	/**
	 * "null".<br/>
	 */
	public static final String NULL = "null";
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";
	
	/**
	 * id en base.<br/>
	 */
	private Long id;

	/**
	 * objet Cerbere récupéré en session en retour du filtre Cerbere.<br/>
	 */
	private transient Cerbere cerbere;
	
	/**
	 * objet fourni par Cerbere modélisant la présente application.<br/>
	 */
	private transient Application application;
	
	/**
	 * objet fourni par Cerbere modélisant l'éventuelle entreprise 
	 * (SIRENXXX) pour les professionnels (hors Ministère).<br/>
	 */
	private transient Entreprise entreprise;
	
	/**
	 * objet fourni par Cerbere modélisant 
	 * les divers profils de l'utilisateur.<br/>
	 */
	private transient Habilitation habilitation;
	
	/**
	 * liste des profils de l'utilisateur contenue 
	 * dans this.habilitation.<br/>
	 */
	private transient List<Profil> listeProfils;
	
	/**
	 * objet fourni par Cerbere modélisant l'utilisateur.<br/>
	 */
	private transient Utilisateur utilisateur;
	
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings(UNUSED)
	private static final Log LOG 
		= LogFactory.getLog(LecteurCerbere.class);

	// *************************METHODES************************************/
	
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 * 
	 * @throws CerbereConnexionException 
	 */
	public LecteurCerbere()  throws CerbereConnexionException {
		this(null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * <ul>
	 * <li>passe le paramètre pCerbere 
	 * à l'attribut <code>this.cerbere</code>.</li>
	 * <li><b>alimente tous les attributs de la classe</b>.</li>
	 * </ul>
	 *
	 * @param pCerbere : i2.application.cerbere.commun.Cerbere :
	 * 
	 * @throws CerbereConnexionException
	 */
	public LecteurCerbere(
			final Cerbere pCerbere) throws CerbereConnexionException {
		
		super();
		
		this.cerbere = pCerbere;
		
		/* alimente tous les attributs de la classe. */
		this.alimenterAttributsCerbere();
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * alimente tous les attributs Cerbere de la présente classe.<br/>
	 * <ul>
	 * <li>méthode <b>appelée directement dans le constructeur</b>.</li>
	 * <li>méthode appelée par le SETTER setCerbere(...).</li>
	 * <li>alimente <code>this.application</code>.</li>
	 * <li>alimente <code>this.entreprise</code> 
	 * (null si le compte professionnel SIRENXXX n'existe pas).</li>
	 * <li>alimente <code>this.habilitation</code>.</li>
	 * <li>alimente <code>this.listeProfils</code>.</li>
	 * <li>alimente <code>this.utilisateur</code>.</li>
	 * </ul>
	 * - ne fait rien si this.cerbere == null.<br/>
	 * <br/>
	 */
	private void alimenterAttributsCerbere() {
		
		/* ne fait rien si this.cerbere == null. */
		if (this.cerbere == null) {
			return;
		}
		
		/* alimente this.application. */
		this.application = this.cerbere.getApplication();
		
		/* alimente this.entreprise. */
		this.entreprise = this.cerbere.getEntreprise();
		
		/* alimente this.habilitation. */
		this.habilitation = this.cerbere.getHabilitation();
		
		/* alimente this.listeProfils. */
		if (this.habilitation != null) {
			this.listeProfils = this.habilitation.getTousProfils();
		}
		
		/* alimente this.utilisateur. */
		this.utilisateur = this.cerbere.getUtilisateur();
		
	} // Fin de alimenterAttributsCerbere()._______________________________

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("LecteurCerbere [");
		
		builder.append("id=");
		if (this.id != null) {			
			builder.append(this.id);			
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("cerbere=");
		if (this.cerbere != null) {			
			builder.append(this.cerbere);			
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("application=");
		if (this.application != null) {			
			builder.append(this.application);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("entreprise=");
		if (this.entreprise != null) {			
			builder.append(this.entreprise);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("habilitation=");
		if (this.habilitation != null) {			
			builder.append(this.habilitation);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("listeProfils=");
		if (this.listeProfils != null) {			
			builder.append(this.listeProfils);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("utilisateur=");
		if (this.utilisateur != null) {			
			builder.append(this.utilisateur);
		} else {
			builder.append(NULL);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public Long getId() {	
		return this.id;
	} // Fin de getId().___________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setId(
			final Long pId) {	
		this.id = pId;
	} // Fin de setId(...).________________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Cerbere getCerbere() {
		return this.cerbere;
	} // Fin de getCerbere().______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setCerbere(
			final Cerbere pCerbere) {
		
		this.cerbere = pCerbere;
		
		/* alimente tous les attributs de la classe. */
		this.alimenterAttributsCerbere();
		
	} // Fin de setCerbere(...).___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Application getApplication() {
		return this.application;
	} // Fin de getApplication().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setApplication(
			final Application pApplication) {
		this.application = pApplication;
	} // Fin de setApplication(...)._______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Entreprise getEntreprise() {
		return this.entreprise;
	} // Fin de getEntreprise().___________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setEntreprise(
			final Entreprise pEntreprise) {
		this.entreprise = pEntreprise;
	} // Fin de setEntreprise(...).________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Habilitation getHabilitation() {
		return this.habilitation;
	} // Fin de getHabilitation()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setHabilitation(
			final Habilitation pHabilitation) {
		this.habilitation = pHabilitation;
	} // Fin de setHabilitation(...).______________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<Profil> getListeProfils() {
		return this.listeProfils;
	} // Fin de getListeProfils()._________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setListeProfils(
			final List<Profil> pListeProfils) {
		this.listeProfils = pListeProfils;
	} // Fin de setListeProfils(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Utilisateur getUtilisateur() {
		return this.utilisateur;
	} // Fin de getUtilisateur().__________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setUtilisateur(
			final Utilisateur pUtilisateur) {
		this.utilisateur = pUtilisateur;
	} // Fin de setUtilisateur(...)._______________________________________


	
} // FIN DE LA CLASSE LecteurCerbere.----------------------------------------
