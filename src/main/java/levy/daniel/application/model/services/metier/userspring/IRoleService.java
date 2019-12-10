package levy.daniel.application.model.services.metier.userspring;

import java.util.List;

import org.springframework.data.domain.Page;

import levy.daniel.application.apptechnic.exceptions.technical.impl.ObjetManquantRunTimeException;
import levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl.RoleEntityJPA;

/**
 * INTERFACE IRoleService :<br/>
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
 * @author dan Lévy
 * @version 1.0
 * @since 28 nov. 2019
 *
 */
public interface IRoleService {

	
	
	/**
	 * recherche un objet  métier par son IDENTIFIANT METIER 
	 * dans le stockage et le retourne si il est trouvé.
	 * <ul>
	 * <li>délègue au DAO la tâche de trouver l'objet métier 
	 * dans le stockage.</li>
	 * <li>retourne l'objet métier trouvé dans le stockage.</li>
	 * </ul>
	 * - jette une ObjetManquantRunTimeException si pRolename est blank.<br/>
	 * - jette une ObjetManquantRunTimeException si l'objet métier 
	 * ne peut être trouvé dans le stockage.<br/>
	 * <br/>
	 *
	 * @param pRolename : String : IDENTIFIANT METIER de l'objet métier.
	 * 
	 * @return RoleEntityJPA : l'objet métier trouvé dans le stockage.
	 * 
	 * @throws ObjetManquantRunTimeException
	 */
	RoleEntityJPA loadRoleByRolename(String pRolename) 
			throws ObjetManquantRunTimeException;
	
	
	
	/**
	 * retourne la page de RoleEntityJPA de numéro pNumeroPage (0-based) 
	 * contenant pTaillePage enregistrements.
	 *
	 * @param pNumeroPage : int : numéro de la page (0-based).
	 * @param pTaillePage : int nombre d'enregistrements dans la page.
	 * 
	 * @return : Page&lt;RoleEntityJPA&gt;
	 */
	Page<RoleEntityJPA> listerUsersParPage(int pNumeroPage, int pTaillePage);

	
	
	/**
	 * retourne la liste de tous les RoleEntityJPA en base.<br/>
	 *
	 * @return : List&lt;RoleEntityJPA&gt; : 
	 * liste de tous les RoleEntityJPA en base.<br/>
	 */
	List<RoleEntityJPA> listAll();
	
	

} // FIN DE L'INTERFACE IRoleService.----------------------------------------