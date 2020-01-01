package levy.daniel.application.model.metier.utilisateur;

import java.io.Serializable;

import levy.daniel.application.model.metier.IExportateurCsv;
import levy.daniel.application.model.metier.IExportateurJTable;

/**
 * INTERFACE IUtilisateurCerbere :<br/>
 * <p>
 * <b>IUtilisateurCerbere</b> modélise un <i>concept</i> d'<b>Utilisateur</b>
 * d'une application - (Personne avec des droits sur une application) - avec un
 * nom, un prénom, des coordonnées et un <i>PROFIL UNIQUE</i> à un instant
 * t.<br/>
 * Un UtilisateurCerbere est conforme à l'API (Application Programming
 * Interface) d'<strong>authentification CERBERE</strong> du Ministère
 * (MTES).<br/>
 * Un UtilisateurCerbere ne possède qu'une seule <strong>HABILITATION</strong>
 * (<code>[Profil, Portee, Restriction]</code>) <i>à la fois</i> (même si il a
 * plusieurs habilitations sur l'application dans Cerbere).
 * </p>
 * 
 * <p>
 * <b><span style="text-decoration: underline;"> Représentation schématique d'un
 * UtilisateurCerbere : </span></b>
 * </p>
 * <p>
 * <img src=
 * "../../../../../../../../../javadoc/images/vues/desktop/metier/utilisateur/UtilisateurCerbereAffichageVue.png"
 * alt="vue simplifiée d'un UtilisateurCerbere" border="1" align="center" />
 * </p>
 * 
 * 
 * <ul>
 * <p>
 * <span style="text-decoration: underline;"> HERITE de : </span>
 * </p>
 * <li><b>IExportateurCsv</b> pour l'export d'un Objet métier en csv.</li>
 * <li><b>IExportateurJTable</b> pour l'affichage dans une JTable (Swing).</li>
 * <li><b>Comparable</b> pour l'affichage des Collections sous forme triée.</li>
 * <li><b>Cloneable</b> pour garantir que tout objet métier implémentant cette
 * interface saura se cloner.</li>
 * <li><b>Serializable</b> pour garantir que tout objet métier implémentant
 * cette interface pourra être serialisé.</li>
 * </ul>
 * 
 * 
 * <ul>
 * <p>
 * <span style="text-decoration: underline;"> Garantit que tout
 * IUtilisateurCerbere sait : </span>
 * </p>
 * <li>se <b>comparer</b> à un autre IUtilisateurCerbere.</li>
 * <li>se <b>cloner</b>.</li>
 * <li>s'exporter sous forme <b>csv</b>.</li>
 * <li>s'exporter sous forme <b>JTable</b>.</li>
 * </ul>
 * 
 * 
 * <ol>
 * <p>
 * <span style="text-decoration: underline;"> Garantit que tout
 * IUtilisateurCerbere possède à minima : </span>
 * </p>
 * <li><b>id</b> pour la mise en base.</li>
 * <li><b>civilite</b></li>
 * <li><b>prenom</b>.</li>
 * <li><b>nom</b>.</li>
 * <li><b>tel</b>.</li>
 * <li><b>email</b>.</li>
 * <li><b>service</b>.</li>
 * <li><b>unite</b>.</li>
 * <li><b>profil</b>.</li>
 * <li><b>portee</b>.</li>
 * <li><b>restriction</b>.</li>
 * </ol>
 * 
 * <p>
 * <span style="text-decoration: underline;">EGALITE METIER</span>
 * </p>
 * <ul>
 * <li>L'<b>égalité metier</b> d'un IUtilisateurCerbere est vérifiée sur :</li>
 * <ul>
 * <li><b>nom</b> (sensible à la casse).</li>
 * <li><b>prenom</b> (sensible à la casse).</li>
 * <li><b>email</b> (sensible à la casse).</li>
 * <li><b>unite</b> (sensible à la casse).</li>
 * </ul>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">COMPARAISON</span>
 * </p>
 * <ul>
 * <li>La <b>comparaison</b> d'un IUtilisateurCerbere est réalisée sur :</li>
 * <ol>
 * <li><b>nom</b> (insensible à la casse).</li>
 * <li><b>prenom</b> (insensible à la casse).</li>
 * <li><b>email</b> (insensible à la casse).</li>
 * <li><b>unite</b> (insensible à la casse).</li>
 * </ol>
 * </ul>
 * 
 * <p>
 * <span style="text-decoration: underline;">DIAGRAMME DE CLASSES
 * D'IMPLEMENTATION</span>
 * </p>
 * <ul>
 * <li><img src=
 * "../../../../../../../../../javadoc/images/model/metier/utilisateur/UtilisateurCerbere.png"
 * alt="classes d'implémentation des IUtilisateurCerbere" border="1" align=
 * "center" /></li>
 * </ul>
 * 
 * <br/>
 * <p>
 * <span style="text-decoration: underline;">REGLES DE GESTION</span>
 * </p>
 * <ul>
 * <li>Les <b>Règles de Gestion (RG)</b> applicables aux attributs d'un
 * IUtilisateurCerbere sont les suivantes :</li> <br/>
 * <table border="1">
 * <tr>
 * <th>Attribut</th>
 * <th>Règle de Gestion</th>
 * </tr>
 * 
 * 
 * <tr>
 * <td rowspan="3">prenom</td>
 * <td>"RG_NOMMAGE_PRENOM_RENSEIGNE_01 : le prénom du IUtilisateurCerbere doit
 * être renseigné (obligatoire)"</td>
 * </tr>
 * <tr>
 * <td>"RG_NOMMAGE_PRENOM_LITTERAL_02 : le prénom du IUtilisateurCerbere ne doit
 * contenir que des lettres ou des caractères spéciaux '-', '_', ... (aucun
 * chiffre)"</td>
 * </tr>
 * <tr>
 * <td>"RG_NOMMAGE_PRENOM_LONGUEUR_03 : le prénom du IUtilisateurCerbere doit
 * contenir entre [1] et [50] lettres"</td>
 * </tr>
 * 
 * <tr>
 * <td rowspan="3">nom</td>
 * <td>"RG_NOMMAGE_NOM_RENSEIGNE_04 : le nom du IUtilisateurCerbere doit être
 * renseigné (obligatoire)"</td>
 * </tr>
 * <tr>
 * <td>"RG_NOMMAGE_NOM_LITTERAL_05 : le nom du IUtilisateurCerbere ne doit
 * contenir que des lettres ou des caractères spéciaux '-', '_', ... (aucun
 * chiffre)"</td>
 * </tr>
 * <tr>
 * <td>"RG_NOMMAGE_NOM_LONGUEUR_06 : le nom du IUtilisateurCerbere doit contenir
 * entre [1] et [50] lettres"</td>
 * </tr>
 * 
 * </table>
 * </ul>
 * 
 * 
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <br/>
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
public interface IUtilisateurCerbere
		extends Comparable<IUtilisateurCerbere>, Serializable, Cloneable
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
	int compareTo(IUtilisateurCerbere pObjet);

	/**
	 * clone.<br/>
	 *
	 * @return IUtilisateurCerbere
	 * 
	 * @throws CloneNotSupportedException
	 */
	IUtilisateurCerbere clone() throws CloneNotSupportedException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	String toString();

	/**
	 * {@inheritDoc} <b>enTete CSV pour un UtilisateurCerbere</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteCsv();

	/**
	 * {@inheritDoc} <b>enTete CSV pour un UtilisateurCerbere</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirStringCsv();

	/**
	 * {@inheritDoc} <b>enTete CSV pour un UtilisateurCerbere</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	String fournirEnTeteColonne(int pI);

	/**
	 * {@inheritDoc} <b>enTete CSV pour un UtilisateurCerbere</b> :<br/>
	 * "id;civilite;prenom;nom;tel;email;service;unite;profil;portee;restriction;".<br/>
	 * <br/>
	 */
	@Override
	Object fournirValeurColonne(int pI);

	/**
	 * Getter de l'ID en base.<br/>
	 *
	 * @return this.id : Long.<br/>
	 */
	Long getId();

	/**
	 * Setter de l'ID en base.<br/>
	 *
	 * @param pId : Long : valeur à passer à this.id.<br/>
	 */
	void setId(Long pId);

	/**
	 * Getter de la civilité de l'utilisateur (M, F, ...).<br/>
	 *
	 * @return this.civilite : String.<br/>
	 */
	String getCivilite();

	/**
	 * Setter de la civilité de l'utilisateur (M, F, ...).<br/>
	 *
	 * @param pCivilite : String : valeur à passer à this.civilite.<br/>
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
	 * @param pPrenom : String : valeur à passer à this.prenom.<br/>
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
	 * @param pNom : String : valeur à passer à this.nom.<br/>
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
	 * @param pTel : String : valeur à passer à this.tel.<br/>
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
	 * @param pEmail : String : valeur à passer à this.email.<br/>
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
	 * @param pService : String : valeur à passer à this.service.<br/>
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
	 * @param pUnite : String : valeur à passer à this.unite.<br/>
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
	 * @param pProfil : String : valeur à passer à this.profil.<br/>
	 */
	void setProfil(String pProfil);

	/**
	 * Getter de la portée du profil de l'utilisateur dans la session courante.<br/>
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
	 * Setter de la portée du profil de l'utilisateur dans la session courante.<br/>
	 * <ul>
	 * Par exemple :
	 * <li>DIRA</li>
	 * <li>FRANCE ENTIERE</li>
	 * </ul>
	 *
	 * @param pPortee : String : valeur à passer à this.portee.<br/>
	 */
	void setPortee(String pPortee);

	/**
	 * Getter de la restriction (facultative) sur la portée du profil de
	 * l'utilisateur dans la session courante.<br/>
	 *
	 * @return this.restriction : String.<br/>
	 */
	String getRestriction();

	/**
	 * Setter de la restriction (facultative) sur la portée du profil de
	 * l'utilisateur dans la session courante.<br/>
	 *
	 * @param pRestriction : String : valeur à passer à this.restriction.<br/>
	 */
	void setRestriction(String pRestriction);

} // FIN DE L'INTERFACE IUtilisateurCerbere.-------------------------------