package levy.daniel.application.model.persistence.metier.userspring.dao.jpaspring;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import levy.daniel.application.model.persistence.metier.userspring.entities.jpa.impl.UserSpringEntityJPA;

/**
 * INTERFACE UserSpringDao :<br/>
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
@Repository(value="UserSpringDao")
public interface UserSpringDao extends JpaRepository<UserSpringEntityJPA, Long> {

	
	
	/**
	 * recherche en base un UserSpringEntityJPA via son username.
	 *
	 * @param pUsername : String : Login du User.
	 * 
	 * @return : UserSpringEntityJPA
	 */
	UserSpringEntityJPA findByUsername(String pUsername);
	
	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pUsername
	 * @param pPageable
	 * @return : Page<UserSpringEntityJPA> :  .<br/>
	 */
	@Query("select user from UserSpringEntityJPA user where user.username LIKE :x ")
	Page<UserSpringEntityJPA> rechercherRapideParPage(
			@Param("x") String pUsername, Pageable pPageable);

	
	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pUsername
	 * @return : List<UserSpringEntityJPA> :  .<br/>
	 */
	@Query("select user from UserSpringEntityJPA user where user.username LIKE :x ")
	List<UserSpringEntityJPA> rechercherRapide(
			@Param("x") String pUsername);
	

} // FIN DE L'INTERFACE UserSpringDao.---------------------------------------
