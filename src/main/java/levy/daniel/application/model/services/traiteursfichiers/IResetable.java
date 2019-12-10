package levy.daniel.application.model.services.traiteursfichiers;


/**
 * INTERFACE IResetable :<br/>
 * <p>Garantit que tous les objets métier qui implémenteront 
 * cette interface auront une méthode reset().</p>
 * 
 * <p>L'idée est d'éviter d'instancier l'objet métier 
 * à chaque itération dans une boucle (ce qui est coûteux) 
 * mais d'utiliser à la place sa méthode reset() 
 * et une méthode remplir(...) pour éviter l'instanciation.</p>
 * <ul>
 * comporte : <br/>
 * <li>une méthode reset() chargée de remettre l'objet à zéro.</li>
 * <li>ATTENTION : la méthode remplir(...) avec des paramètres dépendant 
 * de l'objet doit être implémentée dans les objets concrets.</li>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * reset, utilisation d'une seule instance d'un objet dans une boucle,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 27 févr. 2016
 *
 */
public interface IResetable {
	

	
	/**
	 * Remise à zéro de l'objet métier.
	 */
	void reset();

	
	
} // FIN DE L'INTERFACE IResetable.------------------------------------------
