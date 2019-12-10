package levy.daniel.application.model.metier.utilisateur;

import java.util.List;

import i2.application.cerbere.commun.Application;
import i2.application.cerbere.commun.Cerbere;
import i2.application.cerbere.commun.Entreprise;
import i2.application.cerbere.commun.Habilitation;
import i2.application.cerbere.commun.Profil;
import i2.application.cerbere.commun.Utilisateur;

/**
 * INTERFACE ILecteurCerbere :<br/>
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
public interface ILecteurCerbere {

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();

	
	
	/**
	 * Getter de l'ID en base.<br/>
	 *
	 * @return this.id : Long.<br/>
	 */
	Long getId();

	
	
	/**
	* Setter de l'ID en base.<br/>
	*
	* @param pId : Long : 
	* valeur à passer à this.id.<br/>
	*/
	void setId(Long pId);

	
	
	/**
	 * Getter de l' objet Cerbere récupéré en session
	 * en retour du filtre Cerbere.<br/>
	 *
	 * @return this.cerbere : i2.application.cerbere.commun.Cerbere.<br/>
	 */
	Cerbere getCerbere();

	
	
	/**
	* Setter de l' objet Cerbere récupéré en session
	* en retour du filtre Cerbere.<br/>
	* <ul>
	* <li>passe le paramètre pCerbere 
	* à l'attribut <code>this.cerbere</code>.</li>
	* <li><b>alimente tous les attributs de la classe</b>.</li>
	* </ul>
	*
	* @param pCerbere : i2.application.cerbere.commun.Cerbere : 
	* valeur à passer à this.cerbere.<br/>
	*/
	void setCerbere(Cerbere pCerbere);

	
	
	/**
	 * Getter de l'objet fourni par Cerbere 
	 * modélisant la présente application.<br/>
	 *
	 * @return this.application : 
	 * i2.application.cerbere.commun.Application.<br/>
	 */
	Application getApplication();

	
	
	/**
	* Setter de l'objet fourni par Cerbere 
	* modélisant la présente application.<br/>
	*
	* @param pApplication : i2.application.cerbere.commun.Application : 
	* valeur à passer à this.application.<br/>
	*/
	void setApplication(Application pApplication);

	
	
	/**
	 * Getter de l'objet fourni par Cerbere modélisant l'éventuelle entreprise 
	 * (SIRENXXX) pour les professionnels (hors Ministère).<br/>
	 *
	 * @return this.entreprise : 
	 * i2.application.cerbere.commun.Entreprise.<br/>
	 */
	Entreprise getEntreprise();

	
	
	/**
	* Setter de l'objet fourni par Cerbere modélisant l'éventuelle entreprise 
	* (SIRENXXX) pour les professionnels (hors Ministère).<br/>
	*
	* @param pEntreprise : i2.application.cerbere.commun.Entreprise : 
	* valeur à passer à this.entreprise.<br/>
	*/
	void setEntreprise(Entreprise pEntreprise);

	
	
	/**
	 * Getter de l'objet fourni par Cerbere modélisant 
	 * les divers profils de l'utilisateur.<br/>
	 *
	 * @return this.habilitation : Habilitation.<br/>
	 */
	Habilitation getHabilitation();

	
	
	/**
	* Setter de l'objet fourni par Cerbere modélisant 
	* les divers profils de l'utilisateur.<br/>
	*
	* @param pHabilitation : Habilitation : 
	* valeur à passer à this.habilitation.<br/>
	*/
	void setHabilitation(Habilitation pHabilitation);

	
	
	/**
	 * Getter de la liste des profils de l'utilisateur contenue 
	 * dans this.habilitation.<br/>
	 *
	 * @return this.listeProfils : List&lt;Profil&gt;.<br/>
	 */
	List<Profil> getListeProfils();

	
	
	/**
	* Getter de la liste des profils de l'utilisateur contenue 
	* dans this.habilitation.<br/>
	*
	* @param pListeProfils : List&lt;Profil&gt; : 
	* valeur à passer à this.listeProfils.<br/>
	*/
	void setListeProfils(List<Profil> pListeProfils);

	
	
	/**
	 * Getter de l'objet fourni par Cerbere modélisant l'utilisateur.<br/>
	 *
	 * @return this.utilisateur : Utilisateur.<br/>
	 */
	Utilisateur getUtilisateur();

	
	
	/**
	* Setter de l'objet fourni par Cerbere modélisant l'utilisateur.<br/>
	*
	* @param pUtilisateur : Utilisateur : 
	* valeur à passer à this.utilisateur.<br/>
	*/
	void setUtilisateur(Utilisateur pUtilisateur);
	
	

} // FIN DE L'INTERFACE ILecteurCerbere.-------------------------------------