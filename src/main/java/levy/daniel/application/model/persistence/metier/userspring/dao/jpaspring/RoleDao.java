package levy.daniel.application.model.persistence.metier.userspring.dao.jpaspring;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl.RoleEntityJPA;

/**
 * INTERFACE RoleDao :<br/>
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
 * @since 25 nov. 2019
 *
 */
@Repository(value="RoleDao")
public interface RoleDao extends JpaRepository<RoleEntityJPA, Long> {

	
	
	/**
	 * recherche dans le stockage un RoleEntityJPA via son role.
	 *
	 * @param pRole : String : rôle du RoleEntityJPA.
	 * 
	 * @return : RoleEntityJPA
	 */
	RoleEntityJPA findByRole(String pRole);
	
	
	
} // Fin de INTERFACE RoleDao.-----------------------------------------------
