package levy.daniel.application.model.utilitaires.differentiateur;

import java.util.Objects;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE DifferenceChamp :<br/>
 * Encapsulation (classe générique) paramétrée chargée de stocker 
 * les différences entre 2 valeurs d'un méme champ de <i>Type paramétré</i> T 
 * et de nom <code><b>this.nomChamp</b></code> dans deux objets .<br/>
 * Par exemple : <br/>
 * nomChamp (Type T = String) <i>prenom</i> = "Sylvie" 
 * dans l'objet <code>Personne</code> 1 
 * <br/>et <br/>
 * nomChamp (Type T = String) <i>prenom</i> = "Rufus" dans l'objet <code>Personne</code> 2.<br/>
 * <ul>
 * <li>le paramétrage (Généricité) permet de n'écrire que cette classe 
 * pour gérer tous les Types de values (Integer, String, Bateau, ...).</li>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * Généricité, généricité, genericite, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * 
 * @param <T> : Type des values du champ comparé (Integer, String, Bateau, ...).
 * 
 * @since 8 août 2019
 *
 */
public class DifferenceChamp<T> {

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
	 * " - ".
	 */
	public static final String MOINS_ESPACE = " - ";
	
	/**
	 * "null".<br/>
	 */
	public static final String NULL = "null";
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";

	/**
	 * nom du champ sur lequel portent les différences.
	 */
	private String nomChamp;
	
	/**
	 * valeur (paramétrée) du champ dans le premier objet.
	 */
	private T value1;
	
	/**
	 * valeur (paramétrée) du champ dans le deuxième objet.
	 */
	private T value2;

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(DifferenceChamp.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public DifferenceChamp() {
		this(null, null, null);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 * 
	 * @param pNomChamp : String : nom du champ comparé entre deux objets.
	 * @param pValue1 : T : valeur (paramétrée) du champ dans l'objet1.
	 * @param pValue2 : T : valeur (paramétrée) du champ dans l'objet2.
	 */
	public DifferenceChamp(
			final String pNomChamp
				, final T pValue1
					, final T pValue2) {
		
		super();
		
		this.nomChamp = pNomChamp;
		this.value1 = pValue1;
		this.value2 = pValue2;
		
	} // FIN DE CONSTRUCTEUR COMPLET.______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int hashCode() {
		
		return Objects.hash(
				this.getNomChamp()
					, this.getValue1(), this.getValue2());
		
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
		
		if (!(pObject instanceof DifferenceChamp)) {
			return false;
		}
		
		final DifferenceChamp<?> other = (DifferenceChamp<?>) pObject;
		
		return Objects.equals(this.getNomChamp(), other.getNomChamp()) 
				&& Objects.equals(this.getValue1(), other.getValue1())
				&& Objects.equals(this.getValue2(), other.getValue2());
		
	} // Fin de equals(...)._______________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String toString() {
		
		final StringBuilder builder = new StringBuilder();
		
		builder.append("DifferenceChamp [");
		
		builder.append("nomChamp=");
		if (this.nomChamp != null) {			
			builder.append(this.nomChamp);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("value1=");
		if (this.value1 != null) {
			builder.append(this.value1);
		} else {
			builder.append(NULL);
		}
		
		builder.append(VIRGULE_ESPACE);
		
		builder.append("value2=");
		if (this.value2 != null) {
			builder.append(this.value2);
		} else {
			builder.append(NULL);
		}
		
		builder.append(']');
		
		return builder.toString();
		
	} // Fin de toString().________________________________________________



	/**
	 * Getter du nom du champ sur lequel portent les différences.
	 *
	 * @return this.nomChamp : String.<br/>
	 */
	public final String getNomChamp() {
		return this.nomChamp;
	} // Fin de getNomChamp()._____________________________________________
	

	
	/**
	* Setter du nom du champ sur lequel portent les différences.
	*
	* @param pNomChamp : String : 
	* valeur à passer à this.nomChamp.<br/>
	*/
	public final void setNomChamp(
			final String pNomChamp) {
		this.nomChamp = pNomChamp;
	} // Fin de setNomChamp(...).__________________________________________
	

	
	/**
	 * Getter du valeur (paramétrée) du champ dans le premier objet.
	 *
	 * @return this.value1 : T.<br/>
	 */
	public final T getValue1() {
		return this.value1;
	} // Fin de getValue1()._______________________________________________


	
	/**
	* Setter du valeur (paramétrée) du champ dans le premier objet.
	*
	* @param pValue1 : T : 
	* valeur à passer à this.value1.<br/>
	*/
	public final void setValue1(
			final T pValue1) {
		this.value1 = pValue1;
	} // Fin de setValue1(...).____________________________________________
	

	
	/**
	 * Getter du valeur (paramétrée) du champ dans le deuxième objet.
	 *
	 * @return this.value2 : T.<br/>
	 */
	public final T getValue2() {
		return this.value2;
	} // Fin de getValue2()._______________________________________________

	
	
	/**
	* Setter du valeur (paramétrée) du champ dans le deuxième objet.
	*
	* @param pValue2 : T : 
	* valeur à passer à this.value2.<br/>
	*/
	public final void setValue2(
			final T pValue2) {
		this.value2 = pValue2;
	} // Fin de setValue2(...).____________________________________________


		
} // FIn DE LA CLASSE DifferenceChamp.---------------------------------------
