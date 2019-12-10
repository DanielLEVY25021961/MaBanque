package levy.daniel.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import levy.daniel.application.model.persistence.metier.client.dao.jpaspring.ClientDao;
import levy.daniel.application.model.persistence.metier.compte.dao.jpaspring.CompteDao;
import levy.daniel.application.model.persistence.metier.operation.dao.jpaspring.OperationDao;
import levy.daniel.application.model.services.metier.mabanque.IMaBanqueService;

/**
 * CLASSE MaBanqueApplication :<br/>
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
@SpringBootApplication
public class MaBanqueApplication implements CommandLineRunner {

	// ************************ATTRIBUTS************************************/
	/**
	 * ClientDao.
	 */
	@Autowired
	private ClientDao clientDao;
	
	/**
	 * CompteDao.
	 */
	@Autowired
	private CompteDao compteDao;
	
	/**
	 * OperationDao.
	 */
	@Autowired
	private OperationDao operationDao;
	
	/**
	 * IMaBanqueService.
	 */
	@Autowired
	private IMaBanqueService maBanqueService;

	// *************************METHODES************************************/
		
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public MaBanqueApplication() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	/**
	 * Point d'entrée de l'application.<br/>
	 *
	 * @param pArgs : String[] 
	 */
	public static void main(
			final String[] pArgs) {
		SpringApplication.run(MaBanqueApplication.class, pArgs);
	} // Fin de  main(...).________________________________________________



	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run(
			final String... pArgs) throws Exception {
		
//		final ClientEntityJPA client1 = this.clientDao.save(new ClientEntityJPA("client1", "client1@google.fr"));
//		final ClientEntityJPA client2 = this.clientDao.save(new ClientEntityJPA("client2", "client2@google.fr"));
//		final ClientEntityJPA client3 = this.clientDao.save(new ClientEntityJPA("client3", "client3@google.fr"));
//		final ClientEntityJPA client4 = this.clientDao.save(new ClientEntityJPA("client4", "client4@google.fr"));
//		
//		final LocalDateTime dateCreationNow = LocalDateTime.now();
//		
//		final AbstractCompteEntityJPA compte1 
//		= this.compteDao.save(new CompteCourantEntityJPA("compte1", dateCreationNow, 10000d, client1, 1000d));
//		final AbstractCompteEntityJPA compte2 
//		= this.compteDao.save(new CompteCourantEntityJPA("compte2", dateCreationNow, 20000d, client1, 2000d));
//		final AbstractCompteEntityJPA compte3 
//		= this.compteDao.save(new CompteEpargneEntityJPA("compte3", dateCreationNow, 200000d, client1, 0.05d));
//		
//		final AbstractCompteEntityJPA compte4 
//		= this.compteDao.save(new CompteCourantEntityJPA("compte4", dateCreationNow, 3000d, client2, 1000d));
//		final AbstractCompteEntityJPA compte5 
//		= this.compteDao.save(new CompteCourantEntityJPA("compte5", dateCreationNow, 40000d, client2, 2000d));
//		final AbstractCompteEntityJPA compte6 
//		= this.compteDao.save(new CompteEpargneEntityJPA("compte6", dateCreationNow, 600000d, client2, 0.05d));
//		
//		final LocalDateTime dateOperationNow = LocalDateTime.now();
//		
//		final AbstractOperationEntityJPA operation1 = this.operationDao.save(new RetraitEntityJPA(dateOperationNow, 5000d, compte1));
//		final AbstractOperationEntityJPA operation2 = this.operationDao.save(new RetraitEntityJPA(dateOperationNow, 2000d, compte1));
//		final AbstractOperationEntityJPA operation3 = this.operationDao.save(new VersementEntityJPA(dateOperationNow, 4000d, compte1));
//		
//		try {
//			
//			this.maBanqueService.virer("compte1", "compte2", 100d);
//			
//		} catch (Exception e) {
//			
//			System.out.println(e.getMessage());
//		}
		
	} // Fin de run(...).__________________________________________________
	
	

} // FIN DE LA CLASSE MaBanqueApplication.-----------------------------------
