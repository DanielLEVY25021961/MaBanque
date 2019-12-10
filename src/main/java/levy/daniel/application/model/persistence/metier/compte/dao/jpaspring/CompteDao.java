package levy.daniel.application.model.persistence.metier.compte.dao.jpaspring;

import org.springframework.data.jpa.repository.JpaRepository;

import levy.daniel.application.model.persistence.metier.compte.entities.jpa.AbstractCompteEntityJPA;

/**
 * INTERFACE CompteDao :<br/>
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
 * @since 19 nov. 2019
 *
 */
public interface CompteDao extends JpaRepository<AbstractCompteEntityJPA, String> {

	/**/
	
} // FIN DE L'INTERFACE CompteDao.-------------------------------------------
