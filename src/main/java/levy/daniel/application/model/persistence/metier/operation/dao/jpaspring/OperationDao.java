package levy.daniel.application.model.persistence.metier.operation.dao.jpaspring;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import levy.daniel.application.model.persistence.metier.operation.entities.jpa.AbstractOperationEntityJPA;

/**
 * INTERFACE OperationDao :<br/>
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
public interface OperationDao extends JpaRepository<AbstractOperationEntityJPA, Long> {

	
	
	/**
	 * liste les opérations relatives à un compte donné en les paginant.
	 * <ul>
	 * <li>Cette méthode doit être rajoutée manuellement à l'interface 
	 * JpaRepository qui ne connait pas cette méthode.</li>
	 * </ul>
	 *
	 * @param pCodeCompte : String : ID du compte.
	 * @param pPageable : org.springframework.data.domain.Pageable
	 * 
	 * @return : Page&lt;AbstractOperationEntityJPA&gt;
	 */
	@Query("select operation from AbstractOperationEntityJPA operation where operation.compte.codeCompte = :x order by operation.dateOperation desc")
	Page<AbstractOperationEntityJPA> listerOperationsParPage(
			@Param("x") String pCodeCompte, Pageable pPageable);
	
	
	
} // FIN DE L'INTERFACE OperationDao.----------------------------------------
