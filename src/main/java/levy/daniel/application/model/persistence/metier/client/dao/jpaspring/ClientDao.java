package levy.daniel.application.model.persistence.metier.client.dao.jpaspring;

import org.springframework.data.jpa.repository.JpaRepository;

import levy.daniel.application.model.persistence.metier.client.entities.jpa.ClientEntityJPA;

/**
 * INTERFACE ClientDao :<br/>
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
public interface ClientDao extends JpaRepository<ClientEntityJPA, Long> {
	
	/**/

} // FIN DE L'INTERFACE ClientDao.-------------------------------------------
