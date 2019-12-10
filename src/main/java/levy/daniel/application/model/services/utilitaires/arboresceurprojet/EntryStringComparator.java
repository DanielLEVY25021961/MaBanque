package levy.daniel.application.model.services.utilitaires.arboresceurprojet;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE EntryStringComparator :<br/>
 * Comparator pour trier des Maps&lt;String, Path&gt; 
 * en triant les String de manière alphabétique.<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * map triée, Map triée, Map triee, SortedMap, TreeMap, <br/>
 * Comparator, comparator, <br/>
 * trier Entry, trier Entry<String, Path> sur String, <br/>
 * trier entry sur String, <br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 29 nov. 2018
 *
 */
public class EntryStringComparator 
		implements Comparator<Entry<String, Path>> {

	// ************************ATTRIBUTS************************************/

	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(EntryStringComparator.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public EntryStringComparator() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compare(
			final Entry<String, Path> pO1
				, final Entry<String, Path> pO2) {
		
		if (pO1 == null) {
			
			if (pO2 != null) {
				return +1;
			}
			
			return 0;
			
		} else if (pO2 == null) {
			return -1;
		}
		
		final String string1 = pO1.getKey();
		final String string2 = pO2.getKey();
		
		if (string1 == null) {
			
			if (string2 != null) {
				return +1;
			}
			
			return 0;
			
		} else if (string2 == null) {
			return -1;
		}
		
		return string1.compareToIgnoreCase(string2);
		
	} // Fin de compare(...).______________________________________________
	


} // FIN DE LA CLASSE EntryStringComparator.---------------------------------
