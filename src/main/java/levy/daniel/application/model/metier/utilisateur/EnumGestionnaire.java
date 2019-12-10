package levy.daniel.application.model.metier.utilisateur;

/**
 * ENUMERATION EnumGestionnaire :<br/>
 * Enumération chargée de gérer tous les gestionnaires possibles.<br/>
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
 * @since 13 juin 2019
 *
 */
public enum EnumGestionnaire {
	
	/**
	 * DIRA.<br/>
	 */
	DIRA("DIRA", "DIR ATLANTIQUE")
	
	, /**
	 * DIRCE.<br/>
	 */
	DIRCE("DIRCE", "DIR CENTRE-EST")
	
	, /**
	 * DIRCO.<br/>
	 */
	DIRCO("DIRCO", "DIR CENTRE-OUEST")
	
	, /**
	 * DIRE.<br/>
	 */
	DIRE("DIRE", "DIR EST")
	
	, /**
	 * DIRIF.<br/>
	 */
	DIRIF("DIRIF", "DIR ILE-DE-FRANCE")
	
	, /**
	 * DIRMC.<br/>
	 */
	DIRMC("DIRMC", "DIR MASSIF-CENTRAL")
	
	, /**
	 * DIRMED.<br/>
	 */
	DIRMED("DIRMED", "DIR MEDITERRANEE")
	
	, /**
	 * DIRN.<br/>
	 */
	DIRN("DIRN", "DIR NORD")
	
	, /**
	 * DIRNO.<br/>
	 */
	DIRNO("DIRNO", "DIR NORD-OUEST")
	
	, /**
	 * DIRO.<br/>
	 */
	DIRO("DIRO", "DIR OUEST")
	
	, /**
	 * DIRSO.<br/>
	 */
	DIRSO("DIRSO", "DIR SUD-OUEST")
	
	, /**
	 * DARWIN.<br/>
	 */
	DARWIN("DARWIN", "RESEAU ROUTIER NATIONAL CONCEDE");
	
	
	
	/**
	 * nom court du gestionnaire.
	 */
	private final String nomCourt;
	
	/**
	 * nom complet du gestionnaire.
	 */
	private final String nomComplet;
	

	
    /**
    * CONSTRUCTEUR COMPLET.<br/>
    *
    * @param pNomCourt : String : nom court du gestionnaire.
    * @param pNomComplet : String : nom complet du gestionnaire.
    */
	EnumGestionnaire(
   		final String pNomCourt, final String pNomComplet) {
       this.nomCourt = pNomCourt;
       this.nomComplet = pNomComplet;
   } // Fin de CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * Getter du nom court du gestionnaire.
	 *
	 * @return this.nomCourt : String.<br/>
	 */
	public final String getNomCourt() {
		return this.nomCourt;
	} // Fin de getNomCourt()._____________________________________________


	
	/**
	 * Getter du nom complet du gestionnaire.
	 *
	 * @return this.nomComplet : String.<br/>
	 */
	public final String getNomComplet() {
		return this.nomComplet;
	} // Fin de getNomComplet().___________________________________________

	
	
} // FIN DE L'ENUMERATION EnumGestionnaire.----------------------------------
