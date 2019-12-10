package levy.daniel.application.model.utilitaires.jpa.afficheurentitymanagerfactory;

import java.util.Comparator;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE ComparatorProperties :<br/>
 * Comparator pour classer des java.util.Properties 
 * en fonction des clés (String).<br/>
 * les Properties sont des Entry&lt;String, Object&gt;.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * tri de Properties, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 28 janv. 2019
 *
 */
public class ComparatorProperties implements Comparator<Entry<String, Object>> {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(ComparatorProperties.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.<br/>
	 */
	public ComparatorProperties() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compare(
			final Entry<String, Object> pO1
				, final Entry<String, Object> pO2) {
		
		if (pO1 == pO2) { 
			return 0;
		}

		if (pO1 == null) {
			
			if (pO2 != null) {
				return +1;
			}
			
			return 0;
		}
		
		if (pO2 == null) {
			return -1;
		}
				
		final String key1 = pO1.getKey();
		final String key2 = pO2.getKey();
		
		if (key1 == null) {
							
			if (key2 == null) {
				return 0;
			}
			
			return +1;
							
		}
					
		if (key2 == null) {
			return -1;
		}
			
		return key1.compareToIgnoreCase(key2);	
	
	} // Fin de compare(...).______________________________________________


	
} // FIN DE LA CLASSE ComparatorProperties.----------------------------------
