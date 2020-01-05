package levy.daniel.application.model.services.metier.mabanque.impl;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import levy.daniel.application.apptechnic.exceptions.technical.impl.IdentifiantManquantRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.MauvaisParametreRunTimeException;
import levy.daniel.application.apptechnic.exceptions.technical.impl.ObjetManquantRunTimeException;
import levy.daniel.application.model.persistence.metier.client.dao.jpaspring.ClientDao;
import levy.daniel.application.model.persistence.metier.compte.dao.jpaspring.CompteDao;
import levy.daniel.application.model.persistence.metier.compte.entities.jpa.AbstractCompteEntityJPA;
import levy.daniel.application.model.persistence.metier.compte.entities.jpa.impl.CompteCourantEntityJPA;
import levy.daniel.application.model.persistence.metier.compte.entities.jpa.impl.CompteEpargneEntityJPA;
import levy.daniel.application.model.persistence.metier.operation.dao.jpaspring.OperationDao;
import levy.daniel.application.model.persistence.metier.operation.entities.jpa.AbstractOperationEntityJPA;
import levy.daniel.application.model.persistence.metier.operation.entities.jpa.impl.RetraitEntityJPA;
import levy.daniel.application.model.persistence.metier.operation.entities.jpa.impl.VersementEntityJPA;
import levy.daniel.application.model.services.metier.mabanque.IMaBanqueService;

/**
 * CLASSE MaBanqueService :<br/>
 * .<br/>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 * <br/>
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
@Service(value = "MaBanqueService")
@Transactional
public class MaBanqueService implements IMaBanqueService {

	// ************************ATTRIBUTS************************************/

	/**
	 * "Impossible de trouver dans le stockage l'objet d'identifiant "
	 */
	public static final String IMPOSSIBLE_TROUVER_IDENTIFIANT 
		= "Impossible de trouver dans le stockage l'objet d'identifiant ";
	
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
	 * LOG : Log : Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(MaBanqueService.class);

	// *************************METHODES************************************/

	
	
	/**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public MaBanqueService() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public AbstractCompteEntityJPA consulterCompte(
			final String pCodeCompte) throws Exception {

		/*
		 * throw une IdentifiantManquantRunTimeException si pCodeCompte n'est pas
		 * renseigné (Blank).
		 */
		if (StringUtils.isBlank(pCodeCompte)) {

			final String message = "Vous devez renseigner le code du compte";

			throw new IdentifiantManquantRunTimeException(message);
		}

		AbstractCompteEntityJPA compte = null;

		try {

			/* récupère l'objet métier via son ID (sans Hibernate Proxy) auprès du DAO. */
			compte = (AbstractCompteEntityJPA) Hibernate.unproxy(this.compteDao.getOne(pCodeCompte));

			if (compte == null) {

				final String message = IMPOSSIBLE_TROUVER_IDENTIFIANT + pCodeCompte;

				/*
				 * throw une ObjetManquantRunTimeException si l'objet n'est pas trouvé ans le
				 * stockage.
				 */
				throw new ObjetManquantRunTimeException(message);

			}

		} catch (final Exception e) {

			final String message = IMPOSSIBLE_TROUVER_IDENTIFIANT + pCodeCompte;
			/*
			 * throw une ObjetManquantRunTimeException si l'objet n'est pas trouvé ans le
			 * stockage.
			 */
			throw new ObjetManquantRunTimeException(message, e);

		}

		return compte;

	} // Fin de consulterCompte(...).______________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void verser(
			final String pCodeCompte
				, final double pMontant) throws Exception {

		final AbstractCompteEntityJPA compte 
			= this.consulterCompte(pCodeCompte);

		/*
		 * throw une ObjetManquantRunTimeException si le compte est introuvable.
		 */
		if (compte == null) {

			final String message = IMPOSSIBLE_TROUVER_IDENTIFIANT + pCodeCompte;

			throw new ObjetManquantRunTimeException(message);
		}

		/*
		 * throw une MauvaisParametreRunTimeException si le montant est négatif.
		 */
		if (pMontant < 0) {

			final String message = "le montant passé en paramètre doit être positif : " + pMontant;

			throw new MauvaisParametreRunTimeException(message);
		}

		final LocalDateTime localDateTimeNow = LocalDateTime.now();

		/* crée un versement. */
		final VersementEntityJPA versement 
			= new VersementEntityJPA(localDateTimeNow, pMontant, compte);

		/* stocke en base le versement. */
		this.operationDao.save(versement);

		/* met à jour le solde du compte. */
		final double solde = compte.getSolde();
		final double nouveauSolde = solde + pMontant;

		compte.setSolde(nouveauSolde);

		/* sauvegarde le compte modifié. */
		this.compteDao.save(compte);

	} // Fin de verser(...)._______________________________________________

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void retirer(final String pCodeCompte, final double pMontant) throws Exception {

		final AbstractCompteEntityJPA compte = this.consulterCompte(pCodeCompte);

		/*
		 * throw une ObjetManquantRunTimeException si le compte est introuvable.
		 */
		if (compte == null) {

			final String message = IMPOSSIBLE_TROUVER_IDENTIFIANT + pCodeCompte;

			throw new ObjetManquantRunTimeException(message);
		}

		/*
		 * throw une MauvaisParametreRunTimeException si le montant est négatif.
		 */
		if (pMontant < 0) {

			final String message = "le montant passé en paramètre doit être positif : " + pMontant;

			throw new MauvaisParametreRunTimeException(message);
		}

		final LocalDateTime localDateTimeNow = LocalDateTime.now();

		final double solde = compte.getSolde();
		final double nouveauSolde = solde - pMontant;

		/*
		 * nécessite de faire Hibernate.unproxy(compte) pour connaitre l'instance de
		 * compte.
		 */
		final Object unproxiedEntity = Hibernate.unproxy(compte);

		if (unproxiedEntity instanceof CompteCourantEntityJPA) {

			final CompteCourantEntityJPA compteCourantCaste = (CompteCourantEntityJPA) unproxiedEntity;

			if (nouveauSolde >= -compteCourantCaste.getDecouvertAutorise()) {

				/* Crée et stocke un retrait. */
				final RetraitEntityJPA retrait = new RetraitEntityJPA(localDateTimeNow, pMontant, compteCourantCaste);

				this.operationDao.save(retrait);

				/* met à jour le solde du compte. */
				compteCourantCaste.setSolde(nouveauSolde);

				/* sauvegarde le compte modifié. */
				this.compteDao.save(compteCourantCaste);

			} else {

				final String message = "le solde de votre compte courant et votre facilité de caisse "
						+ "ne permettent pas ce retrait (votre solde atteindrait " + nouveauSolde + ")";

				/*
				 * throw une MauvaisParametreRunTimeException si le retrait excéderait les
				 * facilités de caisse.
				 */
				throw new MauvaisParametreRunTimeException(message);
			}

		} else if (unproxiedEntity instanceof CompteEpargneEntityJPA) {

			final CompteEpargneEntityJPA compteEpargneCaste = (CompteEpargneEntityJPA) unproxiedEntity;

			if (nouveauSolde >= 0) {

				/* Crée et stocke un retrait. */
				final RetraitEntityJPA retrait = new RetraitEntityJPA(localDateTimeNow, pMontant, compteEpargneCaste);

				this.operationDao.save(retrait);

				/* met à jour le solde du compte. */
				compteEpargneCaste.setSolde(nouveauSolde);

				/* sauvegarde le compte modifié. */
				this.compteDao.save(compteEpargneCaste);

			} else {

				final String message = "le solde de votre compte d'épargne "
						+ "ne permet pas ce retrait (votre solde atteindrait " + nouveauSolde + ")";

				/*
				 * throw une MauvaisParametreRunTimeException si le retrait excéderait les
				 * facilités de caisse.
				 */
				throw new MauvaisParametreRunTimeException(message);
			}

		}

	} // Fin de retirer(...).______________________________________________

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void virer(final String pCodeCompteSource, final String pCodeCompteDestinataire, final double pMontant)
			throws Exception {

		final AbstractCompteEntityJPA compteSource = this.consulterCompte(pCodeCompteSource);

		final AbstractCompteEntityJPA compteDestinataire = this.consulterCompte(pCodeCompteDestinataire);

		/*
		 * throw une ObjetManquantRunTimeException si le compte Source est introuvable.
		 */
		if (compteSource == null) {

			final String message = IMPOSSIBLE_TROUVER_IDENTIFIANT + pCodeCompteSource;

			throw new ObjetManquantRunTimeException(message);
		}

		/*
		 * throw une ObjetManquantRunTimeException si le compte Destinataire est
		 * introuvable.
		 */
		if (compteDestinataire == null) {

			final String message = IMPOSSIBLE_TROUVER_IDENTIFIANT
					+ pCodeCompteDestinataire;

			throw new ObjetManquantRunTimeException(message);
		}

		/*
		 * throw une MauvaisParametreRunTimeException si le montant est négatif.
		 */
		if (pMontant < 0) {

			final String message = "le montant passé en paramètre doit être positif : " + pMontant;

			throw new MauvaisParametreRunTimeException(message);
		}

		/*
		 * throw une MauvaisParametreRunTimeException si les 2 comptes sont identiques.
		 */
		if (StringUtils.equals(pCodeCompteSource, pCodeCompteDestinataire)) {

			final String message = "les comptes source et destination doivent être différents";

			throw new MauvaisParametreRunTimeException(message);

		}

		/*
		 * Retire pMontant sur le compte Source et throw une
		 * MauvaisParametreRunTimeException si le retrait excéderait les facilités de
		 * caisse.
		 */
		this.retirer(pCodeCompteSource, pMontant);

		/* verse pMontant sur le compte destinataire. */
		this.verser(pCodeCompteDestinataire, pMontant);

	} // Fin de virer(...).________________________________________________

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<AbstractOperationEntityJPA> listerOperationsParPage(final String pCodeCompte, final int pNumeroPage,
			final int pTaillePage) {

		final Page<AbstractOperationEntityJPA> page = this.operationDao.listerOperationsParPage(pCodeCompte,
				PageRequest.of(pNumeroPage, pTaillePage));

		return page;

	} // Fin de listerOperationsParPage(...).______________________________

} // FIN DE LA CLASSE MaBanqueService.---------------------------------------
