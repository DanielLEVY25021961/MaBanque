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
	 * recherche dans le stockage un objet métier via son username (login).
	 *
	 * @param pUsername : String : Login du User.
	 * 
	 * @return : UserSpringEntityJPA : objet métier.
	 */
	UserSpringEntityJPA findByUsername(String pUsername);
	

	
	/**
	 * recherche dans le stockage une Page d'objets métier 
	 * dont le username (login) contient la String passée en paramètre.<br/>
	 *
	 * @param pUsername : String : paramètre de recherche.
	 * @param pPageable : 
	 * <code>org.springframework.data.domain.Pageable</code> : 
	 * Objet contenant le numéro (0-based) et la taille de la page à retourner.
	 * 
	 * @return : 
	 * <code>org.springframework.data.domain.Page&lt;UserSpringEntityJPA&gt;</code> : 
	 * Page des résultats.
	 */
	@Query("select user from UserSpringEntityJPA user where user.username LIKE :x ")
	Page<UserSpringEntityJPA> rechercherRapideParPage(
			@Param("x") String pUsername, Pageable pPageable);

	
	
	/**
	 * recherche dans le stockage une Liste d'objets métier 
	 * dont le username (login) contient la String passée en paramètre.<br/>
	 *
	 * @param pUsername : String : paramètre de recherche.
	 * 
	 * @return : java.util.List&lt;UserSpringEntityJPA&gt; : 
	 * liste des résultats.<br/>
	 */
	@Query("select user from UserSpringEntityJPA user where user.username LIKE :x ")
	List<UserSpringEntityJPA> rechercherRapide(
			@Param("x") String pUsername);

	

} // FIN DE L'INTERFACE UserSpringDao.---------------------------------------
