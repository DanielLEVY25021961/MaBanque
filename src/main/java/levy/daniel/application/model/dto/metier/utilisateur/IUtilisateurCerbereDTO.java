package levy.daniel.application.model.dto.metier.utilisateur;

import java.io.Serializable;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE IUtilisateurCerbereDTO :<br/>
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
public interface IUtilisateurCerbereDTO 
	extends Comparable<IUtilisateurCerbereDTO>
							, Serializable, Cloneable
								, IExportateurCsv, IExportateurJTable {

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	int hashCode();

	

	/**
	 * {@inheritDoc}
	 */
	@Override
	boolean equals(Object pObjet);

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	int compareTo(IUtilisateurCerbereDTO pObjet);



	/**
	 * clone.<br/>
	 *
	 * @return IUtilisateurCerbereDTO
	 * 
	 * @throws CloneNotSupportedException
	 */
	IUtilisateurCerbereDTO clone() throws CloneNotSupportedException;



	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un UtilisateurCerbere</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un UtilisateurCerbere</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un UtilisateurCerbere</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);



	/**
	 * {@inheritDoc}
	 * <b>enTete CSV pour un UtilisateurCerbere</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	Object fournirValeurColonne(int pI);

	
	
	/**
	 * Getter de l'ID en base (sous forme de String).<br/>
	 *
	 * @return this.id : String.<br/>
	 */
	String getId();

	
	
	/**
	* Setter de l'ID en base (sous forme de String).<br/>
	*
	* @param pId : String : 
	* valeur à passer à this.id.<br/>
	*/
	void setId(String pId);

	
	
	/**
	 * Getter de la civilité de l'utilisateur (M., Mme, Mlle, ...).<br/>
	 *
	 * @return this.civilite : String.<br/>
	 */
	String getCivilite();

	
	
	/**
	* Setter de la civilité de l'utilisateur (M., Mme, Mlle, ...).<br/>
	*
	* @param pCivilite : String : 
	* valeur à passer à this.civilite.<br/>
	*/
	void setCivilite(String pCivilite);

	
	
	/**
	 * Getter du prénom de l'utilisateur.<br/>
	 *
	 * @return this.prenom : String.<br/>
	 */
	String getPrenom();

	
	
	/**
	* Setter du prénom de l'utilisateur.<br/>
	*
	* @param pPrenom : String : 
	* valeur à passer à this.prenom.<br/>
	*/
	void setPrenom(String pPrenom);

	
	
	/**
	 * Getter du nom de l'utilisateur.<br/>
	 *
	 * @return this.nom : String.<br/>
	 */
	String getNom();

	
	
	/**
	* Setter du nom de l'utilisateur.<br/>
	*
	* @param pNom : String : 
	* valeur à passer à this.nom.<br/>
	*/
	void setNom(String pNom);

	
	
	/**
	 * Getter du numéro de téléphone de l'utilisateur.<br/>
	 *
	 * @return this.tel : String.<br/>
	 */
	String getTel();

	
	
	/**
	* Setter du numéro de téléphone de l'utilisateur.<br/>
	*
	* @param pTel : String : 
	* valeur à passer à this.tel.<br/>
	*/
	void setTel(String pTel);

	
	
	/**
	 * Getter de l'adresse mel de l'utilisateur.
	 *
	 * @return this.email : String.<br/>
	 */
	String getEmail();

	
	
	/**
	* Setter de l'adresse mel de l'utilisateur.
	*
	* @param pEmail : String : 
	* valeur à passer à this.email.<br/>
	*/
	void setEmail(String pEmail);

	
	
	/**
	 * Getter du service de l'utilisateur.<br/>
	 * <ul>
	 * Par exemple :
	 * <li><b>SG</b> pour SG/DAEI/CCDD1</li>
	 * <li><b>CGDD</b> pour CGDD/SDES/SDST/BSRV</li>
	 * <li><b>DIRA</b> pour DIRA/SIEER/CIGT/PC Bordeaux</li>
	 * <li><b>CEREMA</b> pour CEREMA/DTerMed/DCEDI/GTIE</li>
	 * <li><b>DGITM</b> pour DGITM/DIT/GRN/GCABron/GCA2</li>
	 * </ul>
	 *
	 * @return this.service : String.<br/>
	 */
	String getService();

	
	
	/**
	* Setter du service de l'utilisateur.<br/>
	* <ul>
	* Par exemple :
	* <li><b>SG</b> pour SG/DAEI/CCDD1</li>
	* <li><b>CGDD</b> pour CGDD/SDES/SDST/BSRV</li>
	* <li><b>DIRA</b> pour DIRA/SIEER/CIGT/PC Bordeaux</li>
	* <li><b>CEREMA</b> pour CEREMA/DTerMed/DCEDI/GTIE</li>
	* <li><b>DGITM</b> pour DGITM/DIT/GRN/GCABron/GCA2</li>
	* </ul>
	*
	* @param pService : String : 
	* valeur à passer à this.service.<br/>
	*/
	void setService(String pService);

	
	
	/**
	 * Getter de l'unité (dans le service) de l'utilisateur.<br/>
	 * <ul>
	 * Par exemple :
	 * <li>SG/DAEI/CCDD1</li>
	 * <li>CGDD/SDES/SDST/BSRV</li>
	 * <li>DIRA/SIEER/CIGT/PC Bordeaux</li>
	 * <li>CEREMA/DTerMed/DCEDI/GTIE</li>
	 * <li>DGITM/DIT/GRN/GCABron/GCA2</li>
	 * </ul>
	 *
	 * @return this.unite : String.<br/>
	 */
	String getUnite();

	
	
	/**
	* Setter de l'unité (dans le service) de l'utilisateur.<br/>
	* <ul>
	* Par exemple :
	* <li>SG/DAEI/CCDD1</li>
	* <li>CGDD/SDES/SDST/BSRV</li>
	* <li>DIRA/SIEER/CIGT/PC Bordeaux</li>
	* <li>CEREMA/DTerMed/DCEDI/GTIE</li>
	* <li>DGITM/DIT/GRN/GCABron/GCA2</li>
	* </ul>
	*
	* @param pUnite : String : 
	* valeur à passer à this.unite.<br/>
	*/
	void setUnite(String pUnite);

	
	
	/**
	 * Getter du profil de l'utilisateur dans la session courante.<br/>
	 * <ul>
	 * Par exemple :
	 * <li>ADMINISTRATEUR</li>
	 * <li>GESTIONNAIRE</li>
	 * </ul>
	 *
	 * @return this.profil : String.<br/>
	 */
	String getProfil();

	
	
	/**
	* Setter du profil de l'utilisateur dans la session courante.<br/>
	* <ul>
	* Par exemple :
	* <li>ADMINISTRATEUR</li>
	* <li>GESTIONNAIRE</li>
	* </ul>
	*
	* @param pProfil : String : 
	* valeur à passer à this.profil.<br/>
	*/
	void setProfil(String pProfil);

	
	
	/**
	 * Getter de la portée du profil 
	 * de l'utilisateur dans la session courante.<br/>
	 * <ul>
	 * Par exemple :
	 * <li>DIRA</li>
	 * <li>FRANCE ENTIERE</li>
	 * </ul>
	 *
	 * @return this.portee : String.<br/>
	 */
	String getPortee();

	
	
	/**
	* Setter de la portée du profil 
	* de l'utilisateur dans la session courante.<br/>
	* <ul>
	* Par exemple :
	* <li>DIRA</li>
	* <li>FRANCE ENTIERE</li>
	* </ul>
	*
	* @param pPortee : String : 
	* valeur à passer à this.portee.<br/>
	*/
	void setPortee(String pPortee);

	
	
	/**
	 * Getter de la restriction (facultative) sur la portée 
	 * du profil de l'utilisateur dans la session courante.<br/>
	 *
	 * @return this.restriction : String.<br/>
	 */
	String getRestriction();

	
	
	/**
	* Setter de la restriction (facultative) sur la portée 
	* du profil de l'utilisateur dans la session courante.<br/>
	*
	* @param pRestriction : String : 
	* valeur à passer à this.restriction.<br/>
	*/
	void setRestriction(String pRestriction);
	
	

} // FIN DE L'INTERFACE IUtilisateurCerbereDTO.----------------------------