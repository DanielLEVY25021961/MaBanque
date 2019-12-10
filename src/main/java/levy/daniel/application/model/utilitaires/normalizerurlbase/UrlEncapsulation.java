package levy.daniel.application.model.utilitaires.normalizerurlbase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE UrlEncapsulation :<br/>
 * PURE FABRICATION chargée d'encapsuler toutes les données contenues 
 * dans une URL de connexion à une Base de Données.<br/>
 * 
 * <p>
 * <span style="text-decoration: underline;">DIAGRAMME DE CLASSES D'IMPLEMENTATION</span>
 * </p>
 * <ul>
 * <li>
 * <img src="../../../../../../../../../javadoc/images/model/utilitaires/normalizerurlbase/diagramme_classes_NormalizerUrlBase.png" 
 * alt="classes d'implémentation NormalizerUrlBase" border="1" align="center" />
 * </li>
 * </ul>
 * 
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
 * @author dan Lévy
 * @version 1.0
 * @since 23 janv. 2019
 *
 */
public class UrlEncapsulation {

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
	 * <b>URL totale de BD fournie pour créer 
	 * la présente encapsulation</b>.<br/>
	 * Par exemple : <br/>
	 * <ul>
	 * <li><code>jdbc:hsqldb:file:
	 * ./data/base-adresses_javafx-h2/base-adresses_javafx;
	 * DB_CLOSE_ON_EXIT=FALSE;IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;</code> 
	 * en mode FILE.</li>
	 * <li><code>jdbc:postgresql://
	 * localhost:5432/base-adresses_javafx</code> en mode SERVEUR.</li>
	 * <li><code>jdbc:h2:mem:base-adresses_javafx</code> 
	 * en mode MEMOIRE.</li>
	 * </ul>
	 */
	private String urlFournie;
	
	/**
	 * <b>préfixe de l'urlFournie</b> indiquant la 
	 * BD utilisée et une utilisation de la BD 
	 * en mode FILE, SERVEUR ou MEMOIRE.<br/>
	 * Par exemple : 
	 * <ul>
	 * <li><code>jdbc:hsqldb:file:</code> en mode FILE</li> 
	 * <li><code>jdbc:mysql://</code> en mode SERVEUR</li>
	 * <li><code>jdbc:h2:mem:</code> en mode MEMOIRE</li>
	 * </ul>
	 */
	private String prefixeUrl;
	
	/**
	 * <b>"marque" de BD utilisée dans l'URL fournie</b>.<br/>
	 * Par exemple : <code>hsqldb</code> ou <code>postgresql</code>.<br/>
	 */
	private String marqueBd;
	
	/**
	 * <b>mode d'utilisation de la BD</b> dans 
	 * l'URL fournie (FILE, SERVEUR ou MEMOIRE).
	 * <ul>
	 * <li>FILE</li>
	 * <li>SERVEUR</li>
	 * <li>MEMOIRE</li>
	 * </ul>
	 */
	private EnumModesBase mode;
	
	/**
	 * <b>URL totale de BD fournie pour créer la présente
	 *  encapsulation <i>moins son préfixe</i></b>.<br/>
	 * Par exemple : 
	 * <code>./data/base-adresses_javafx-h2/base-adresses_javafx;
	 * DB_CLOSE_ON_EXIT=FALSE;IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;</code><br/>
	 * finUrlFournie contient 
	 * [cheminURL (absolu ou relatif) + commandesSupplementaires].<br/> 
	 */
	private String finUrlFournie;
	
	/**
	 * <b>stipule si le chemin fourni dans l'URL 
	 * est relatif</b> ou pas (pour une connexion 
	 * à la BD en mode FILE).<br/>
	 * true si le chemin est relatif.<br/>
	 */
	private boolean cheminRelatif;
	
	/**
	 * <b>chemin relatif de la BD éventuellement 
	 * fourni dans l'URL</b> pour une utilisation 
	 * de la BD en mode FILE.<br/>
	 * Par exemple : 
	 * <code>./data/base-adresses_javafx-h2/base-adresses_javafx</code><br/>
	 * <ul>
	 * <li>Peut être null si le chemin fourni pour une 
	 * utilisation de la BD en mode FILE est <i>absolu</i><br/> 
	 * (par exemple 
	 * <code>D:/Donnees/eclipse/eclipseworkspace/adresses_javafx/
	 * data/base-adresses_javafx-hsqldb/base-adresses_javafx</code>).</li>
	 * </ul>
	 */
	private String cheminRelatifUrl;
	
	/**
	 * <b>chemin absolu adapté à la plateforme 
	 * (Windows, Linux, ...) de la BD 
	 * indiqué dans l'URL</b> pour une utilisation 
	 * de la BD en mode FILE.
	 * <ul>
	 * <li>chemin absolu fabriqué à partir d'un 
	 * chemin relatif si l'URL fournie comportait un chemin relatif.</li>
	 * <li>chemin absolu fourni directement dans l'URL sinon.</li>
	 * </ul>
	 */
	private String cheminNormaliseUrl;
	
	/**
	 * <b>nom d'hôte du serveur de BD en mode SERVEUR</b>.<br/>
	 * null en modes FILE ou MEMOIRE.<br/>
	 * par exemple : <code>localhost</code><br/>
	 */
	private String cheminServeur;
	
	/**
	 * <b>port d'écoute du serveur de BD en mode SERVEUR</b>.<br/>
	 * null en modes FILE ou MEMOIRE.<br/>
	 * par exemple : <code>3306</code> pour une base MySQL.<br/>
	 */
	private String portServeur;
	
	/**
	 * <b>nom de la Base de Données</b>.<br/>
	 * par exemple : <code>base-adresses_javafx</code><br/>
	 */
	private String baseDonnees;

	/**
	 * <b>commandes supplémentaires dans l'URL fournie</b>.<br/>
	 * Par exemple : 
	 * <code>DB_CLOSE_ON_EXIT=FALSE;
	 * IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;</code><br/>
	 */
	private String commandesSupplementaires;

	/**
	 * <b>URL totale avec chemin absolu adapté 
	 * à la plateforme (Windows, Linux, ...) de BD 
	 * à fournir à un PersistenceUnitInfo</b>.<br/>
	 * Par exemple : <br/>
	 * <ul>
	 * <li><code>jdbc:hsqldb:file:
	 * D:/Donnees/eclipse/eclipseworkspace/adresses_javafx/
	 * data/base-adresses_javafx-hsqldb/base-adresses_javafx;
	 * DB_CLOSE_ON_EXIT=FALSE;IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;</code> 
	 * en mode FILE.
	 * <b>Les chemins relatifs doivent avoir été résolus et normalisés</b>.</li>
	 * <li><code>jdbc:postgresql://localhost:5432/base-adresses_javafx</code> 
	 * en mode SERVEUR.</li>
	 * <li><code>jdbc:h2:mem:base-adresses_javafx</code> 
	 * en mode MEMOIRE.</li>
	 * </ul>
	 */
	private String urlNormalisee;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(UrlEncapsulation.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public UrlEncapsulation() {
		
		this(null
				, null, null, null, null
				, false, null, null, null, null, null, null, null);
		
	} // Fin du CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * 
	 * @param pUrlFournie : String : 
	 * URL totale de BD fournie pour créer la présente encapsulation.
	 * @param pPrefixeUrl : String : 
	 * préfixe de l'urlFournie.
	 * @param pMarqueBd : String : 
	 * "marque" de BD utilisée dans l'URL fournie.
	 * @param pMode : EnumModesBase : 
	 * mode d'utilisation de la BD dans 
	 * l'URL fournie (FILE, SERVEUR ou MEMOIRE).
	 * @param pFinUrlFournie : String : 
	 * URL totale de BD fournie pour créer la présente 
	 * encapsulation <i>moins son préfixe</i>.
	 * @param pCheminRelatif : boolean : 
	 * stipule si le chemin fourni dans l'URL est relatif
	 * @param pCheminRelatifUrl : String : 
	 * chemin relatif de la BD éventuellement fourni dans l'URL 
	 * pour une utilisation de la BD en mode FILE.
	 * @param pCheminNormaliseUrl : String : 
	 * chemin absolu adapté à la plateforme (Windows, Linux, ...) de la BD
	 * @param pCheminServeur : String : 
	 * nom d'hôte du serveur de BD en mode SERVEUR
	 * @param pPortServeur : String : 
	 * port d'écoute du serveur de BD en mode SERVEUR.
	 * @param pBaseDonnees : String : 
	 * nom de la Base de Données.
	 * @param pCommandesSupplementaires : String : 
	 * commandes supplémentaires dans l'URL fournie.
	 * @param pUrlNormalisee : String : 
	 * URL totale avec chemin absolu adapté à la plateforme 
	 * (Windows, Linux, ...) de BD à fournir à un PersistenceUnitInfo.
	 */
	public UrlEncapsulation(
			final String pUrlFournie
			, final String pPrefixeUrl
			, final String pMarqueBd
			, final EnumModesBase pMode
			, final String pFinUrlFournie
			, final boolean pCheminRelatif
			, final String pCheminRelatifUrl
			, final String pCheminNormaliseUrl
			, final String pCheminServeur
			, final String pPortServeur
			, final String pBaseDonnees
			, final String pCommandesSupplementaires
			, final String pUrlNormalisee) {
		
		super();
		
		this.urlFournie = pUrlFournie;
		this.prefixeUrl = pPrefixeUrl;
		this.marqueBd = pMarqueBd;
		this.mode = pMode;
		this.finUrlFournie = pFinUrlFournie;
		this.cheminRelatif = pCheminRelatif;
		this.cheminRelatifUrl = pCheminRelatifUrl;
		this.cheminNormaliseUrl = pCheminNormaliseUrl;
		this.cheminServeur = pCheminServeur;
		this.portServeur = pPortServeur;
		this.baseDonnees = pBaseDonnees;
		this.commandesSupplementaires = pCommandesSupplementaires;
		this.urlNormalisee = pUrlNormalisee;
		
	} // Fin du CONSTRUCTEUR COMPLET.______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.baseDonnees == null) ? 0 : this.baseDonnees.hashCode());
		result = prime * result + ((this.cheminNormaliseUrl == null) ? 0 : this.cheminNormaliseUrl.hashCode());
		result = prime * result + (this.cheminRelatif ? 1231 : 1237);
		result = prime * result + ((this.cheminRelatifUrl == null) ? 0 : this.cheminRelatifUrl.hashCode());
		result = prime * result + ((this.cheminServeur == null) ? 0 : this.cheminServeur.hashCode());
		result = prime * result
				+ ((this.commandesSupplementaires == null) ? 0 : this.commandesSupplementaires.hashCode());
		result = prime * result + ((this.finUrlFournie == null) ? 0 : this.finUrlFournie.hashCode());
		result = prime * result + ((this.marqueBd == null) ? 0 : this.marqueBd.hashCode());
		result = prime * result + ((this.mode == null) ? 0 : this.mode.hashCode());
		result = prime * result + ((this.portServeur == null) ? 0 : this.portServeur.hashCode());
		result = prime * result + ((this.prefixeUrl == null) ? 0 : this.prefixeUrl.hashCode());
		result = prime * result + ((this.urlFournie == null) ? 0 : this.urlFournie.hashCode());
		result = prime * result + ((this.urlNormalisee == null) ? 0 : this.urlNormalisee.hashCode());
		return result;
		
	} // Fin de hashCode().________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean equals(
			final Object pObject) {
		
		if (this == pObject) {
			return true;
		}
		if (pObject == null) {
			return false;
		}
		if (!(pObject instanceof UrlEncapsulation)) {
			return false;
		}
		
		final UrlEncapsulation other = (UrlEncapsulation) pObject;
		
		if (this.baseDonnees == null) {
			if (other.baseDonnees != null) {
				return false;
			}
		} else if (!this.baseDonnees
				.equalsIgnoreCase(other.baseDonnees)) {
			return false;
		}
		if (this.cheminNormaliseUrl == null) {
			if (other.cheminNormaliseUrl != null) {
				return false;
			}
		} else if (!this.cheminNormaliseUrl
				.equalsIgnoreCase(other.cheminNormaliseUrl)) {
			return false;
		}
		if (this.cheminRelatif != other.cheminRelatif) {
			return false;
		}
		if (this.cheminRelatifUrl == null) {
			if (other.cheminRelatifUrl != null) {
				return false;
			}
		} else if (!this.cheminRelatifUrl
				.equalsIgnoreCase(other.cheminRelatifUrl)) {
			return false;
		}
		if (this.cheminServeur == null) {
			if (other.cheminServeur != null) {
				return false;
			}
		} else if (!this.cheminServeur
				.equalsIgnoreCase(other.cheminServeur)) {
			return false;
		}
		if (this.commandesSupplementaires == null) {
			if (other.commandesSupplementaires != null) {
				return false;
			}
		} else if (!this.commandesSupplementaires
				.equalsIgnoreCase(other.commandesSupplementaires)) {
			return false;
		}
		if (this.finUrlFournie == null) {
			if (other.finUrlFournie != null) {
				return false;
			}
		} else if (!this.finUrlFournie
				.equalsIgnoreCase(other.finUrlFournie)) {
			return false;
		}
		if (this.marqueBd == null) {
			if (other.marqueBd != null) {
				return false;
			}
		} else if (!this.marqueBd
				.equalsIgnoreCase(other.marqueBd)) {
			return false;
		}
		if (this.mode != other.mode) {
			return false;
		}
		if (this.portServeur == null) {
			if (other.portServeur != null) {
				return false;
			}
		} else if (!this.portServeur
				.equals(other.portServeur)) {
			return false;
		}
		if (this.prefixeUrl == null) {
			if (other.prefixeUrl != null) {
				return false;
			}
		} else if (!this.prefixeUrl
				.equalsIgnoreCase(other.prefixeUrl)) {
			return false;
		}
		if (this.urlFournie == null) {
			if (other.urlFournie != null) {
				return false;
			}
		} else if (!this.urlFournie
				.equalsIgnoreCase(other.urlFournie)) {
			return false;
		}
		if (this.urlNormalisee == null) {
			if (other.urlNormalisee != null) {
				return false;
			}
		} else if (!this.urlNormalisee
				.equalsIgnoreCase(other.urlNormalisee)) {
			return false;
		}
		
		return true;
		
	} // Fin de equals(...)._______________________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("UrlEncapsulation [");
		
		builder.append("urlFournie=");
		if (this.urlFournie != null) {			
			builder.append(this.urlFournie);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("prefixeUrl=");
		if (this.prefixeUrl != null) {			
			builder.append(this.prefixeUrl);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);

		builder.append("marqueBd=");
		if (this.marqueBd != null) {
			builder.append(this.marqueBd);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("mode=");
		if (this.mode != null) {			
			builder.append(this.mode);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);

		builder.append("finUrlFournie=");
		if (this.finUrlFournie != null) {			
			builder.append(this.finUrlFournie);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
				
		builder.append("cheminRelatif=");
		builder.append(this.cheminRelatif);
		
		builder.append(VIRGULE_ESPACE);

		builder.append("cheminRelatifUrl=");
		if (this.cheminRelatifUrl != null) {			
			builder.append(this.cheminRelatifUrl);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("cheminNormaliseUrl=");
		if (this.cheminNormaliseUrl != null) {			
			builder.append(this.cheminNormaliseUrl);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("cheminServeur=");
		if (this.cheminServeur != null) {			
			builder.append(this.cheminServeur);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("portServeur=");
		if (this.portServeur != null) {			
			builder.append(this.portServeur);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);

		builder.append("baseDonnees=");
		if (this.baseDonnees != null) {			
			builder.append(this.baseDonnees);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("commandesSupplementaires=");
		if (this.commandesSupplementaires != null) {
			builder.append(this.commandesSupplementaires);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("urlNormalisee=");
		if (this.urlNormalisee != null) {			
			builder.append(this.urlNormalisee);
		} else {
			builder.append(NULL);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * Getter de l'<b>URL totale de BD fournie pour créer 
	 * la présente encapsulation</b>.<br/>
	 * Par exemple : <br/>
	 * <ul>
	 * <li><code>jdbc:hsqldb:file:
	 * ./data/base-adresses_javafx-h2/base-adresses_javafx;
	 * DB_CLOSE_ON_EXIT=FALSE;IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;</code> 
	 * en mode FILE.</li>
	 * <li><code>jdbc:postgresql://
	 * localhost:5432/base-adresses_javafx</code> en mode SERVEUR.</li>
	 * <li><code>jdbc:h2:mem:base-adresses_javafx</code> 
	 * en mode MEMOIRE.</li>
	 * </ul>
	 *
	 * @return this.urlFournie : String.<br/>
	 */
	public final String getUrlFournie() {
		return this.urlFournie;
	} // Fin de getUrlFournie().___________________________________________



	/**
	* Setter de l'<b>URL totale de BD fournie pour créer 
	* la présente encapsulation</b>.<br/>
	* Par exemple : <br/>
	* <ul>
	* <li><code>jdbc:hsqldb:file:
	* ./data/base-adresses_javafx-h2/base-adresses_javafx;
	* DB_CLOSE_ON_EXIT=FALSE;IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;</code> 
	* en mode FILE.</li>
	* <li><code>jdbc:postgresql://
	* localhost:5432/base-adresses_javafx</code> en mode SERVEUR.</li>
	* <li><code>jdbc:h2:mem:base-adresses_javafx</code> 
	* en mode MEMOIRE.</li>
	* </ul>
	*
	* @param pUrlFournie : String : 
	* valeur à passer à this.urlFournie.<br/>
	*/
	public final void setUrlFournie(
			final String pUrlFournie) {
		this.urlFournie = pUrlFournie;
	} // Fin de setUrlFournie(...).________________________________________
	

	
	/**
	 * Getter du <b>préfixe de l'urlFournie</b> indiquant la 
	 * BD utilisée et une utilisation de la BD 
	 * en mode FILE, SERVEUR ou MEMOIRE.<br/>
	 * Par exemple : 
	 * <ul>
	 * <li><code>jdbc:hsqldb:file:</code> en mode FILE</li> 
	 * <li><code>jdbc:mysql://</code> en mode SERVEUR</li>
	 * <li><code>jdbc:h2:mem:</code> en mode MEMOIRE</li>
	 * </ul>
	 *
	 * @return this.prefixeUrl : String.<br/>
	 */
	public final String getPrefixeUrl() {
		return this.prefixeUrl;
	} // Fin de getPrefixeUrl().___________________________________________


	
   /**
	* Setter du <b>préfixe de l'urlFournie</b> indiquant la 
	* BD utilisée et une utilisation de la BD 
	* en mode FILE, SERVEUR ou MEMOIRE.<br/>
	* Par exemple : 
	* <ul>
	* <li><code>jdbc:hsqldb:file:</code> en mode FILE</li> 
	* <li><code>jdbc:mysql://</code> en mode SERVEUR</li>
	* <li><code>jdbc:h2:mem:</code> en mode MEMOIRE</li>
	* </ul>
	*
	* @param pPrefixeUrl : String : 
	* valeur à passer à this.prefixeUrl.<br/>
	*/
	public final void setPrefixeUrl(
			final String pPrefixeUrl) {
		this.prefixeUrl = pPrefixeUrl;
	} // Fin de setPrefixeUrl(...).________________________________________
	

	
	/**
	 * Getter de la <b>"marque" de BD utilisée dans l'URL fournie</b>.<br/>
	 * Par exemple : <code>hsqldb</code> ou <code>postgresql</code>.<br/>
	 *
	 * @return this.marqueBd : String.<br/>
	 */
	public final String getMarqueBd() {
		return this.marqueBd;
	} // Fin de getMarqueBd()._____________________________________________
	

	
	/**
	* Setter de la <b>"marque" de BD utilisée dans l'URL fournie</b>.<br/>
	* Par exemple : <code>hsqldb</code> ou <code>postgresql</code>.<br/>
	*
	* @param pMarqueBd : String : 
	* valeur à passer à this.marqueBd.<br/>
	*/
	public final void setMarqueBd(
			final String pMarqueBd) {
		this.marqueBd = pMarqueBd;
	} // Fin de setMarqueBd(...).__________________________________________
	

	
	/**
	 * Getter du <b>mode d'utilisation de la BD</b> dans 
	 * l'URL fournie (FILE, SERVEUR ou MEMOIRE).
	 * <ul>
	 * <li>FILE</li>
	 * <li>SERVEUR</li>
	 * <li>MEMOIRE</li>
	 * </ul>
	 *
	 * @return this.mode : EnumModesBase.<br/>
	 */
	public final EnumModesBase getMode() {
		return this.mode;
	} // Fin de getMode()._________________________________________________


	
	/**
	* Setter du <b>mode d'utilisation de la BD</b> dans 
	* l'URL fournie (FILE, SERVEUR ou MEMOIRE).
	* <ul>
	* <li>FILE</li>
	* <li>SERVEUR</li>
	* <li>MEMOIRE</li>
	* </ul>
	*
	* @param pMode : EnumModesBase : 
	* valeur à passer à this.mode.<br/>
	*/
	public final void setMode(
			final EnumModesBase pMode) {
		this.mode = pMode;
	} // Fin de setMode(...).______________________________________________
	

	
	/**
	 * Getter de l'<b>URL totale de BD fournie pour créer la présente
	 * encapsulation <i>moins son préfixe</i></b>.<br/>
	 * Par exemple : 
	 * <code>./data/base-adresses_javafx-h2/base-adresses_javafx;
	 * DB_CLOSE_ON_EXIT=FALSE;IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;</code><br/>
	 * finUrlFournie contient 
	 * [cheminURL (absolu ou relatif) + commandesSupplementaires].<br/> 
	 *
	 * @return this.finUrlFournie : String.<br/>
	 */
	public final String getFinUrlFournie() {
		return this.finUrlFournie;
	} // Fin de getFinUrlFournie().________________________________________


	
	/**
	* Setter de l'<b>URL totale de BD fournie pour créer la présente
	* encapsulation <i>moins son préfixe</i></b>.<br/>
	* Par exemple : 
	* <code>./data/base-adresses_javafx-h2/base-adresses_javafx;
	* DB_CLOSE_ON_EXIT=FALSE;IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;</code><br/>
	* finUrlFournie contient 
	* [cheminURL (absolu ou relatif) + commandesSupplementaires].<br/> 
	*
	* @param pFinUrlFournie : String : 
	* valeur à passer à this.finUrlFournie.<br/>
	*/
	public final void setFinUrlFournie(
			final String pFinUrlFournie) {
		this.finUrlFournie = pFinUrlFournie;
	} // Fin de setFinUrlFournie(...)._____________________________________

	
	
	/**
	 * Getter du boolean qui <b>stipule si le chemin fourni dans l'URL 
	 * est relatif</b> ou pas (pour une connexion 
	 * à la BD en mode FILE).<br/>
	 * true si le chemin est relatif.<br/>
	 *
	 * @return this.cheminRelatif : boolean.<br/>
	 */
	public final boolean isCheminRelatif() {
		return this.cheminRelatif;
	} // Fin de isCheminRelatif()._________________________________________


	
	/**
	* Setter du boolean qui <b>stipule si le chemin fourni dans l'URL 
	* est relatif</b> ou pas (pour une connexion 
	* à la BD en mode FILE).<br/>
	* true si le chemin est relatif.<br/>
	*
	* @param pCheminRelatif : boolean : 
	* valeur à passer à this.cheminRelatif.<br/>
	*/
	public final void setCheminRelatif(
			final boolean pCheminRelatif) {
		this.cheminRelatif = pCheminRelatif;
	} // Fin de setCheminRelatif(...)._____________________________________

	
	
	/**
	 * Getter du <b>chemin relatif de la BD éventuellement 
	 * fourni dans l'URL</b> pour une utilisation 
	 * de la BD en mode FILE.<br/>
	 * Par exemple : 
	 * <code>./data/base-adresses_javafx-h2/base-adresses_javafx</code><br/>
	 * <ul>
	 * <li>Peut être null si le chemin fourni pour une 
	 * utilisation de la BD en mode FILE est <i>absolu</i><br/> 
	 * (par exemple 
	 * <code>D:/Donnees/eclipse/eclipseworkspace/adresses_javafx/
	 * data/base-adresses_javafx-hsqldb/base-adresses_javafx</code>).</li>
	 * </ul>
	 *
	 * @return this.cheminRelatifUrl : String.<br/>
	 */
	public final String getCheminRelatifUrl() {
		return this.cheminRelatifUrl;
	} // Fin de getCheminRelatifUrl()._____________________________________


	
	/**
	* Setter du <b>chemin relatif de la BD éventuellement 
	* fourni dans l'URL</b> pour une utilisation 
	* de la BD en mode FILE.<br/>
	* Par exemple : 
	* <code>./data/base-adresses_javafx-h2/base-adresses_javafx</code><br/>
	* <ul>
	* <li>Peut être null si le chemin fourni pour une 
	* utilisation de la BD en mode FILE est <i>absolu</i><br/> 
	* (par exemple 
	* <code>D:/Donnees/eclipse/eclipseworkspace/adresses_javafx/
	* data/base-adresses_javafx-hsqldb/base-adresses_javafx</code>).</li>
	* </ul>
	*
	* @param pCheminRelatifUrl : String : 
	* valeur à passer à this.cheminRelatifUrl.<br/>
	*/
	public final void setCheminRelatifUrl(
			final String pCheminRelatifUrl) {
		this.cheminRelatifUrl = pCheminRelatifUrl;
	} // Fin de setCheminRelatifUrl(...).__________________________________

	
	
	/**
	 * Getter du <b>chemin absolu adapté à la plateforme 
	 * (Windows, Linux, ...) de la BD 
	 * indiqué dans l'URL</b> pour une utilisation 
	 * de la BD en mode FILE.
	 * <ul>
	 * <li>chemin absolu fabriqué à partir d'un 
	 * chemin relatif si l'URL fournie comportait un chemin relatif.</li>
	 * <li>chemin absolu fourni directement dans l'URL sinon.</li>
	 * </ul>
	 *
	 * @return this.cheminNormaliseUrl : String.<br/>
	 */
	public final String getCheminNormaliseUrl() {
		return this.cheminNormaliseUrl;
	} // Fin de getCheminNormaliseUrl().___________________________________

	
	
	/**
	* Setter du <b>chemin absolu adapté à la plateforme 
	* (Windows, Linux, ...) de la BD 
	* indiqué dans l'URL</b> pour une utilisation 
	* de la BD en mode FILE.
	* <ul>
	* <li>chemin absolu fabriqué à partir d'un 
	* chemin relatif si l'URL fournie comportait un chemin relatif.</li>
	* <li>chemin absolu fourni directement dans l'URL sinon.</li>
	* </ul>
	*
	* @param pCheminNormaliseUrl : String : 
	* valeur à passer à this.cheminNormaliseUrl.<br/>
	*/
	public final void setCheminNormaliseUrl(
			final String pCheminNormaliseUrl) {
		this.cheminNormaliseUrl = pCheminNormaliseUrl;
	} // Fin de setCheminNormaliseUrl(...).________________________________
	

	
	/**
	 * Getter du <b>nom d'hôte du serveur de BD en mode SERVEUR</b>.<br/>
	 * null en modes FILE ou MEMOIRE.<br/>
	 * par exemple : <code>localhost</code><br/>
	 *
	 * @return this.cheminServeur : String.<br/>
	 */
	public final String getCheminServeur() {
		return this.cheminServeur;
	} // Fin de getCheminServeur().________________________________________


	
	/**
	* Setter du <b>nom d'hôte du serveur de BD en mode SERVEUR</b>.<br/>
	* null en modes FILE ou MEMOIRE.<br/>
	* par exemple : <code>localhost</code><br/>
	*
	* @param pCheminServeur : String : 
	* valeur à passer à this.cheminServeur.<br/>
	*/
	public final void setCheminServeur(
			final String pCheminServeur) {
		this.cheminServeur = pCheminServeur;
	} // Fin de setCheminServeur(...)._____________________________________

	
	
	/**
	 * Getter du <b>port d'écoute du serveur de BD en mode SERVEUR</b>.<br/>
	 * null en modes FILE ou MEMOIRE.<br/>
	 * par exemple : <code>3306</code> pour une base MySQL.<br/>
	 *
	 * @return this.portServeur : String.<br/>
	 */
	public final String getPortServeur() {
		return this.portServeur;
	} // Fin de getPortServeur().__________________________________________


	
	/**
	* Setter du <b>port d'écoute du serveur de BD en mode SERVEUR</b>.<br/>
	* null en modes FILE ou MEMOIRE.<br/>
	* par exemple : <code>3306</code> pour une base MySQL.<br/>
	*
	* @param pPortServeur : String : 
	* valeur à passer à this.portServeur.<br/>
	*/
	public final void setPortServeur(
			final String pPortServeur) {
		this.portServeur = pPortServeur;
	} // Fin de setPortServeur(...)._______________________________________


	
	/**
	 * Getter du <b>nom de la Base de Données</b>.<br/>
	 * par exemple : <code>base-adresses_javafx</code><br/>
	 *
	 * @return this.baseDonnees : String.<br/>
	 */
	public final String getBaseDonnees() {
		return this.baseDonnees;
	} // Fin de getBaseDonnees().__________________________________________

	
	
	/**
	* Setter du <b>nom de la Base de Données</b>.<br/>
	* par exemple : <code>base-adresses_javafx</code><br/>
	*
	* @param pBaseDonnees : String : 
	* valeur à passer à this.baseDonnees.<br/>
	*/
	public final void setBaseDonnees(
			final String pBaseDonnees) {
		this.baseDonnees = pBaseDonnees;
	} // Fin de setBaseDonnees(...)._______________________________________


	
	/**
	 * Getter des <b>commandes supplémentaires dans l'URL fournie</b>.<br/>
	 * Par exemple : 
	 * <code>DB_CLOSE_ON_EXIT=FALSE;
	 * IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;</code><br/>
	 *
	 * @return this.commandesSupplementaires : String.<br/>
	 */
	public final String getCommandesSupplementaires() {
		return this.commandesSupplementaires;
	} // Fin de getCommandesSupplementaires()._____________________________


	
	/**
	* Setter des <b>commandes supplémentaires dans l'URL fournie</b>.<br/>
	* Par exemple : 
	* <code>DB_CLOSE_ON_EXIT=FALSE;
	* IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;</code><br/>
	*
	* @param pCommandesSupplementaires : String : 
	* valeur à passer à this.commandesSupplementaires.<br/>
	*/
	public final void setCommandesSupplementaires(
			final String pCommandesSupplementaires) {
		this.commandesSupplementaires = pCommandesSupplementaires;
	} // Fin de setCommandesSupplementaires(...).__________________________


	
	/**
	 * Getter de l'<b>URL totale avec chemin absolu adapté 
	 * à la plateforme (Windows, Linux, ...) de BD 
	 * à fournir à un PersistenceUnitInfo</b>.<br/>
	 * Par exemple : <br/>
	 * <ul>
	 * <li><code>jdbc:hsqldb:file:
	 * D:/Donnees/eclipse/eclipseworkspace/adresses_javafx/
	 * data/base-adresses_javafx-hsqldb/base-adresses_javafx;
	 * DB_CLOSE_ON_EXIT=FALSE;IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;</code> 
	 * en mode FILE.
	 * <b>Les chemins relatifs doivent avoir été résolus et normalisés</b>.</li>
	 * <li><code>jdbc:postgresql://localhost:5432/base-adresses_javafx</code> 
	 * en mode SERVEUR.</li>
	 * <li><code>jdbc:h2:mem:base-adresses_javafx</code> 
	 * en mode MEMOIRE.</li>
	 * </ul>
	 *
	 * @return this.urlNormalisee : String.<br/>
	 */
	public final String getUrlNormalisee() {
		return this.urlNormalisee;
	} // Fin de getUrlNormalisee().________________________________________

	
	
	/**
	* Setter de l'<b>URL totale avec chemin absolu adapté 
	* à la plateforme (Windows, Linux, ...) de BD 
	* à fournir à un PersistenceUnitInfo</b>.<br/>
	* Par exemple : <br/>
	* <ul>
	* <li><code>jdbc:hsqldb:file:
	* D:/Donnees/eclipse/eclipseworkspace/adresses_javafx/
	* data/base-adresses_javafx-hsqldb/base-adresses_javafx;
	* DB_CLOSE_ON_EXIT=FALSE;IFEXISTS=TRUE;DB_CLOSE_DELAY=-1;</code> 
	* en mode FILE.
	* <b>Les chemins relatifs doivent avoir été résolus et normalisés</b>.</li>
	* <li><code>jdbc:postgresql://localhost:5432/base-adresses_javafx</code> 
	* en mode SERVEUR.</li>
	* <li><code>jdbc:h2:mem:base-adresses_javafx</code> 
	* en mode MEMOIRE.</li>
	* </ul>
	*
	* @param pUrlNormalisee : String : 
	* valeur à passer à this.urlNormalisee.<br/>
	*/
	public final void setUrlNormalisee(
			final String pUrlNormalisee) {
		this.urlNormalisee = pUrlNormalisee;
	} // Fin de setUrlNormalisee(...)._____________________________________

	
	
} // FIN DE LA CLASSE UrlEncapsulation.--------------------------------------
