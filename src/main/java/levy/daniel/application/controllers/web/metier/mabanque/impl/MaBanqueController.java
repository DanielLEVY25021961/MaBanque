package levy.daniel.application.controllers.web.metier.mabanque.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import levy.daniel.application.apptechnic.exceptions.technical.impl.MauvaisParametreRunTimeException;
import levy.daniel.application.controllers.web.metier.mabanque.IMabanqueController;
import levy.daniel.application.model.persistence.metier.compte.entities.jpa.AbstractCompteEntityJPA;
import levy.daniel.application.model.persistence.metier.operation.entities.jpa.AbstractOperationEntityJPA;
import levy.daniel.application.model.services.metier.mabanque.IMaBanqueService;

/**
 * CLASSE MaBanqueController :<br/>
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
@Controller(value="MaBanqueController")
@RequestMapping(value="/MaBanqueController")
public class MaBanqueController implements IMabanqueController {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * IMaBanqueService.
	 */
	@Autowired
	@Qualifier("MaBanqueService")
	private IMaBanqueService maBanqueService;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG 
		= LogFactory.getLog(MaBanqueController.class);

	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public MaBanqueController() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@GetMapping("/mabanque")
	public String versMaBanque() {
		return "/metier/mabanque/mabanque";
	} // Fin de versMaBanque().____________________________________________
	

	
	/**
	 * {@inheritDoc}
	 */
//	@PreAuthorize(value="hasAnyRole({'ROLE_LECTEUR', 'ROLE_GESTIONNAIRE', 'ROLE_ADMIN'})")
	@Override
	@GetMapping("/consulterCompte")
	public String consulterCompte(
			@RequestParam(name="codeCompte") final String pCodeCompte
				, @RequestParam(name="page", defaultValue="0") final int pPage
					, @RequestParam(name="size", defaultValue="4") final int pSize
							, final Model pModel) {
		
		/* passe l'identifiant de l'objet métier au 
		 * org.springframework.ui.Model 
		 * pour pouvoir l'utiliser dans toute la page. */
		pModel.addAttribute("codeCompte", pCodeCompte);
		
		try {
			
			/* réclame l'objet métier (sans le Hibernate Proxy) au SERVICE. */
			final AbstractCompteEntityJPA compte 
				= this.maBanqueService.consulterCompte(pCodeCompte);
			
			/* passe l'objet métier (sans le Hibernate Proxy) 
			 * au org.springframework.ui.Model. */
			pModel.addAttribute("compte", compte);
			
			/* passe le nom simple (Type) de la classe réelle 
			 * de l'objet métier (sans le Hibernate Proxy) 
			 * au org.springframework.ui.Model. */
			pModel.addAttribute("typeCompte"
					, compte.getClass().getSimpleName());
			
			/* réclame la liste paginée des objets dépendants 
			 * de l'objet métier au SERVICE. */
			final Page<AbstractOperationEntityJPA> pageOperations 
				= this.maBanqueService.listerOperationsParPage(
						pCodeCompte, pPage, pSize);
						
			List<AbstractOperationEntityJPA> listeOperations = null;
			int[] pages = null;
			
			if (pageOperations != null) {
				
				listeOperations = pageOperations.getContent();
				
				// PAGINATION.
				pages = new int[pageOperations.getTotalPages()];
								
			}
			
			/* passe le tableau des numéros (0-based) des pages d'opérations 
			 * au Model. */
			pModel.addAttribute("pages", pages);
			
			/* passe la liste paginée des objets dépendants 
			 * de l'objet métier au org.springframework.ui.Model. */
			pModel.addAttribute("listeOperations", listeOperations);
			
		} catch (Exception e) {

			final String message = e.getMessage();
			
			/* passe le message de l'exception 
			 * - si le SERVICE a retourné une Exception - au Model. */
			pModel.addAttribute("message", message);
		}
		
		/* aiguille vers la page de la banque avec le Model alimenté 
		 * et toutes les valeurs rafraîchies. */
		return "/metier/mabanque/mabanque";
		
	} // Fin de consulterCompte(...).______________________________________


	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@PostMapping("/enregistrerOperation")
	public String enregistrerOperation(
			@RequestParam("codeCompte") final String pCodeCompte
				, @RequestParam("typeOperation") final String pTypeOperation
					, @RequestParam("montant") final double pMontant
						, @RequestParam("codeCompteDestinataire") final String pCodeCompteDestinataire
								, final Model pModel
									, final RedirectAttributes pRedirectAttributes) {
		
		try {
			
			/* réclame l'objet métier (sans le Hibernate Proxy) au SERVICE. */
			final AbstractCompteEntityJPA compte 
				= this.maBanqueService.consulterCompte(pCodeCompte);
			
			/* passe l'objet métier (sans le Hibernate Proxy) 
			 * au org.springframework.ui.Model. */
			pModel.addAttribute("compte", compte);
						
			/* passe le nom simple (Type) de la classe réelle 
			 * de l'objet métier (sans le Hibernate Proxy) 
			 * au org.springframework.ui.Model. */
			pModel.addAttribute("typeCompte"
					, compte.getClass().getSimpleName());
			
			// TRAITEMENT DE L'OPERATION.
			if (StringUtils.equals(pTypeOperation, "VERSEMENT")) {
				
				this.maBanqueService
					.verser(pCodeCompte, pMontant);
				
			} else if (StringUtils.equals(pTypeOperation, "RETRAIT")) {
				
				this.maBanqueService
					.retirer(pCodeCompte, pMontant);
				
			} else if (StringUtils.equals(pTypeOperation, "VIREMENT")) {
				
				this.maBanqueService.virer(
						pCodeCompte, pCodeCompteDestinataire, pMontant);
				
			} else {
				
				final String message 
					= "Le type d'OPERATION passé en paramètre : " 
							+ pTypeOperation 
							+ " n'est pas pris en charge";
				
				/* throw une MauvaisParametreRunTimeException si le type d'opération n'est pas reconnu. */
				throw new MauvaisParametreRunTimeException(message);
				
			}
			
			/* réclame la liste paginée des objets dépendants 
			 * de l'objet métier au SERVICE. */
			final Page<AbstractOperationEntityJPA> pageOperations 
				= this.maBanqueService.listerOperationsParPage(
						pCodeCompte, 0, 4);
			
			List<AbstractOperationEntityJPA> listeOperations = null;
			
			if (pageOperations != null) {			
				listeOperations = pageOperations.getContent();
			}
			
			/* passe la liste paginée des objets dépendants 
			 * de l'objet métier au org.springframework.ui.Model. */
			pModel.addAttribute("listeOperations", listeOperations);
			
		} catch (Exception e) {

			final String messageOperation = e.getMessage();
			
			/* passe le message de l'exception 
			 * - si le SERVICE a retourné une Exception - au Model. */
			pRedirectAttributes.addFlashAttribute("messageOperation", messageOperation);

		}
		
		/* redirige vers l'action MaBanqueController/consulterCompte (avec le Model alimenté) qui mène vers la page de la banque en rafraichissant toutes les valeurs. */
		return "redirect:/MaBanqueController/consulterCompte?codeCompte=" 
		+ pCodeCompte;
		
	} // Fin de enregistrerOperation(...)._________________________________

	
	
} // FIN DE LA CLASSE MaBanqueController.------------------------------------
