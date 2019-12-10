package levy.daniel.application.model.services.utilitaires.upload.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import levy.daniel.application.ConfigurationApplicationManager;
import levy.daniel.application.IConstantesSeparateurs;
import levy.daniel.application.apptechnic.exceptions.technical.impl.StorageFileNotFoundRunTimeException;
import levy.daniel.application.model.services.utilitaires.upload.IUploadService;

/**
 * CLASSE FileSystemUploadTempService :<br/>
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
 * @since 10 nov. 2018
 *
 */
@Service(value="FileSystemUploadTempService")
public class FileSystemUploadTempService implements IUploadService {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe FileSystemUploadTempService".
	 */
	public static final String CLASSE_FILE_SYSTEM_UPLOAD_TEMP_SERVICE 
		= "Classe FileSystemUploadTempService";
	
	/**
	 * "Méthode upload(MultipartFile pFileSource, File pTarget)".
	 */
	public static final String METHODE_UPLOAD 
		= "Méthode upload(MultipartFile pFileSource, File pTarget)";
	
	/**
	 * "Méthode loadAll()".
	 */
	public static final String METHODE_LOAD_ALL 
		= "Méthode loadAll()";
	
	/**
	 * "méthode loadAsResource(String pFileName)".
	 */
	public static final String METHODE_LOAD_AS_RESOURCE 
		= "méthode loadAsResource(String pFileName)";
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";

    /**
     * Path ABSOLU du répertoire temporaire racine des fichiers uploadés.<br/>
     */
    private final transient Path rootTempLocationPath;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings(UNUSED)
	private static final Log LOG 
		= LogFactory.getLog(FileSystemUploadTempService.class);
	
   
	// *************************METHODES************************************/
	   
   
     /**
     * CONSTRUCTEUR D'ARITE NULLE.<br/>
     * <ul>
     * <li>récupère le Path de la racine des téléversements temporaires
     * auprès du <code>ConfigurationApplicationManager</code> 
     * et alimente automatiquement <code>this.rootTempLocationPath</code> 
     * avec le path indiqué dans le fichier properties 
     * <code>configuration_ressources_externes_fr_FR.properties</code>.</li>
     * <li>Crée <i>si nécessaire</i> l'arborescence des répertoires jusqu'au 
     * <strong>répertoire racine du dépôt des fichiers uploadés 
     * (inclus)</strong> <code>this.rootTempLocationPath</code>.</li>
     * <li>ne fait rien et ne lève pas d'exception 
     * si le répertoire racine est déjà existant.</li>
     * </ul>
     * 
     * @throws Exception 
     */
    public FileSystemUploadTempService() throws Exception {
    	
    	/* récupère le path de la racine des téléversements 
    	 * auprès du ConfigurationApplicationManager. */
    	this(null);
    	
     } // Fin de CONSTRUCTEUR D'ARITE NULLE._______________________________

    
    
     /**
     * CONSTRUCTEUR D'ARITE 1.<br/>
     * <ul>
     * <li>prend en paramètre le java.nio.file.Path 
     * de la racine des téléversements temporaires 
     * pRootTempLocationPath et le passe à
     * <code>this.rootTempLocationPath</code>.</li>
     * <li>récupère si pRootLocationPath == null 
     * le Path de la racine des téléversements temporaires 
     * auprès du <code>ConfigurationApplicationManager</code> 
     * et alimente automatiquement <code>this.rootTempLocationPath</code> 
     * avec le path indiqué dans le fichier properties 
     * <code>configuration_ressources_externes_fr_FR.properties</code>.</li>
     * <li>Crée <i>si nécessaire</i> l'arborescence des répertoires jusqu'au 
     * <strong>répertoire racine du dépôt temporaire des fichiers uploadés 
     * (inclus)</strong> <code>this.rootTempLocationPath</code>.</li>
     * <li>ne fait rien et ne lève pas d'exception 
     * si le répertoire temporaire racine est déjà existant.</li>
     * </ul>
     * 
     * @param pRootTempLocationPath : java.nio.file.Path : 
     * Path vers la racine du répertoire temporaire des téléversements.
     * 
     * @throws Exception
     */
    public FileSystemUploadTempService(final Path pRootTempLocationPath) 
    		throws Exception {
    	
    	super();
    	
    	if (pRootTempLocationPath == null) {
    		
     		this.rootTempLocationPath = Paths.get(
        			ConfigurationApplicationManager.getPathTemp())
        			.normalize().toAbsolutePath();
    		
    	} else {
    		this.rootTempLocationPath = pRootTempLocationPath;
    	}
    	
    	/* Crée si nécessaire l'arborescence des répertoires jusqu'au 
    	 * répertoire racine du dépôt temporaire des fichiers uploadés 
    	 * (inclus) this.rootTempLocationPath.*/
    	this.init();
    	
    } // Fin de CONSTRUCTEUR D'ARITE 1.____________________________________

    
    
    /**
     * <p>Crée <i>si nécessaire</i> l'arborescence des répertoires jusqu'au 
     * <strong>répertoire racine du dépôt des fichiers uploadés 
     * (inclus)</strong> <code>this.rootLocationPath</code>.</p>
     * <p>- ne fait rien et ne lève pas d'exception 
     * si le répertoire racine des télversements 
     * <code>this.rootLocationPath</code> 
     * est déjà existant.</p>
     * <p>- utilise 
     * <code>Files.createDirectories(this.rootLocationPath);</code></p>
     * 
     * @throws Exception 
     */
    private void init() throws StorageFileNotFoundRunTimeException {
    	
        try {
        	
            Files.createDirectories(this.rootTempLocationPath);
            
        } catch (IOException e) {
        	
        	final String message 
        		= CLASSE_FILE_SYSTEM_UPLOAD_TEMP_SERVICE 
        		+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
        		+ "Methode init()" 
        		+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
        		+ "Impossible de créer le répertoire racine des téléversements (uploads).";
        	
            throw new StorageFileNotFoundRunTimeException(message, e);
        }
        
    } // Fin de init().____________________________________________________
    
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final Path nommerFichierCible(
    		final String pFilename) {
    	
    	/* retourne null si pFilename est blank. */
    	if (StringUtils.isBlank(pFilename)) {
    		return null;
    	}
    	
        return this.rootTempLocationPath.resolve(pFilename);
        
    } // Fin de nommerFichierCible(...).___________________________________

    
    
    /**
     * {@inheritDoc}
     * 
     * @throws Exception 
     */
    @Override
    public final UploadServiceResponse uploadOneFile(
    		final MultipartFile pFileSource) 
    					throws Exception {
    	
    	/* retourne un uploadServiceResponse KO si pFileSource 
    	 * est null ou vide 
    	 * (pas de sélection de fichier à uploader côté client).*/
    	if (pFileSource.isEmpty()) {
    		return this.traiterFileSourceEmpty(pFileSource);
    	}
    	
		return this.uploadOneFile(
				pFileSource
					, this.nommerFichierCible(
							pFileSource.getOriginalFilename()).toFile());
		
    } // Fin de upload(...)._______________________________________________

 
    
    /**
     * gère le cas où l'utilisateur n'a pas sélectionné de 
     * fichier Multipart source à uploader (<code>pFileSource.isEmpty()</code>)
     * <ul>
     * <li>retourne un UploadServiceResponse KO 
     * avec un message à l'attention de l'utilisateur 
     * si <code>pFileSource.isEmpty()</code>.</li>
     * <li>ne logge pas et ne jette pas d'Exception</li>
     * </ul>
     * 
     *
     * @param pFileSource : org.springframework.web.multipart.MultipartFile : 
     * le fichier multipart source à uploader null ou vide.
     * 
     * @return UploadServiceResponse : UploadServiceResponse KO.
     * 
     * @throws Exception
     */
    private UploadServiceResponse traiterFileSourceEmpty(
    		final MultipartFile pFileSource) 
    					throws Exception {
    			
		final boolean statusService = false;
		
		final String messageUtilisateur 
			= "vous devez sélectionner un fichier à téléverser "
					+ "avant d'appuyer sur le bouton 'Téléverser'";
		
		final String messageDeveloppeur 
			= CLASSE_FILE_SYSTEM_UPLOAD_TEMP_SERVICE 
			+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
			+ METHODE_UPLOAD
			+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
			+ messageUtilisateur;
		
		final File fichierUploade = null;
		
		final UploadServiceResponse uploadServiceResponse 
			= new UploadServiceResponse(statusService, fichierUploade);
		
		uploadServiceResponse.ajouterMessageUtilisateur(messageUtilisateur);
		uploadServiceResponse.ajouterMessageDeveloppeur(messageDeveloppeur);
				
		return uploadServiceResponse;
    	
    } // Fin de traiterFileSourceEmpty(...)._______________________________
    
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final UploadServiceResponse uploadOneFile(
    		final MultipartFile pFileSource, final File pFileTarget) 
    								throws Exception {
    	
    	/* retourne un uploadServiceResponse KO si pFileSource 
    	 * est null ou vide 
    	 * (pas de sélection de fichier à uploader côté client).*/
    	if (pFileSource.isEmpty()) {
    		return this.traiterFileSourceEmpty(pFileSource);
    	}

    	/* n'uploade rien, LOG.fatal et jette une 
    	 * StorageFileNotFoundRunTimeException si pFileTarget 
    	 * est null ou répertoire. */
    	this.traiterMauvaisFileTarget(pFileTarget, METHODE_UPLOAD);
    	
    	/* récupère le nom du fichier multipart source uploadé. */
    	String filename = pFileSource.getName();
    	
    	Path pathTarget = null;
    	
		try (InputStream inputStream = pFileSource.getInputStream()) {

			pathTarget = pFileTarget.toPath();
			
			/* CREATION DE L'ARBORESCENCE DE pFileTarget 
			 * SOUS LA RACINE DES DEPOTS. */
			this.creerArboresencePathTarget(pathTarget);
			
			// CREATION ET COPIE DU CONTENU DE pFileSource DANS pFileTarget.***
			/* crée le fichier pFileTarget vide si il n'existait pas 
			 * et recopie dedans le contenu de pFileSource. */
			/* écrase et remplace pFileTarget si il existait déjà. */
			Files.copy(inputStream
					, pathTarget
						, StandardCopyOption.REPLACE_EXISTING);
			
			// PREPARATION DE LA REPONSE.
			/* status de la réponse du service. */
    		final boolean status = true;
    		
    		/* message utilisateur. */
    		final String messageUtilisateur 
    			= "le fichier " + pFileSource.getOriginalFilename() 
                + " a bien été uploadé côté serveur dans le répertoire "
                + "des fichiers temporaires.";
    		
    		/* message développeur. */
     		final String messageDeveloppeur 
    			= CLASSE_FILE_SYSTEM_UPLOAD_TEMP_SERVICE 
    			+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
    			+ METHODE_UPLOAD
    			+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
    			+ messageUtilisateur;
    		
     		/* fichier résultat de l'upload par le service. */
    		final File fichierUploade = pFileTarget;
    		
    		// INSTANCIATION DE LA REPONSE DU SERVICE.
    		final UploadServiceResponse uploadServiceResponse 
    			= new UploadServiceResponse(status, fichierUploade);
    		
    		uploadServiceResponse.ajouterMessageUtilisateur(messageUtilisateur);
    		uploadServiceResponse.ajouterMessageDeveloppeur(messageDeveloppeur);
			
			return uploadServiceResponse;

		} catch (IOException e) {
			
			final String message = CLASSE_FILE_SYSTEM_UPLOAD_TEMP_SERVICE 
    				+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
    				+ METHODE_UPLOAD 
    				+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
    				+ "impossible d'uploader le fichier multipart source : " 
    				+ filename;
			
    		if (LOG.isFatalEnabled()) {
    			LOG.fatal(message, e);
    		}
			
			throw new StorageFileNotFoundRunTimeException(message, e);
		}
    	
    } // Fin de upload(...)._______________________________________________
    

    
    /**
     * crée l'arborescence côté serveur du fichier à uploader 
     * pointant sur pPathTarget sous la racine des dépôts 
     * (temp) si elle n'existait pas.<br/>
     * <br/>
     * - ne fait rien, LOG.fatal et jette une 
     * StorageFileNotFoundRunTimeException si il est impossible 
     * de créer l'arborescence côté serveur.<br/>
     * <br/>
     *
     * @param pPathTarget : java.nio.file.Path : 
     * Path du fichier à uploader côté serveur dont il faut créer 
     * l'arborescence sous le répertoire racine des dépôts (temp).
     * 
     * @throws Exception
     */
    private void creerArboresencePathTarget(
    		final Path pPathTarget) throws Exception {
    	
		final int nombreNiveauxArborescence = pPathTarget.getNameCount();
		
		final Path arborescenceTarget
			= pPathTarget.subpath(0, nombreNiveauxArborescence - 1);
				
		if (!Files.exists(arborescenceTarget)) {
			
			try {
	        	
				// CREATION DE L'ARBORESCENCE. 
	            Files.createDirectories(arborescenceTarget);
	        
	        /*  ne fait rien, LOG.fatal et jette une 
	         * StorageFileNotFoundRunTimeException si il est 
	         * impossible de créer l'arborescence côté serveur. */
	        } catch (IOException e) {
	        	
	        	final String message 
	        		= CLASSE_FILE_SYSTEM_UPLOAD_TEMP_SERVICE 
	        		+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
	        		+ METHODE_UPLOAD
	        		+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
	        		+ "Impossible de créer l'arborescence sous "
	        		+ "le répertoire racine des téléversements (uploads).";
	        	
	        	if (LOG.isFatalEnabled()) {
	        		LOG.fatal(message, e);
	        	}
	        	
	            throw new StorageFileNotFoundRunTimeException(message, e);
	        }
		}

    } // Fin de creerArboresencePathTarget(...).___________________________
    

   
    /**
     * {@inheritDoc}
     * @throws StorageFileNotFoundRunTimeException 
     */
    @Override
    public final Stream<Path> loadAll() 
    			throws StorageFileNotFoundRunTimeException {
    	
         try {
        	
          	final Stream<Path> resultat 
         		= Files.walk(this.rootTempLocationPath)
                    .filter(path -> !path.equals(this.rootTempLocationPath))
                    .filter(Files::isRegularFile)
                    .map(this.rootTempLocationPath::relativize);
        	     	
            return resultat;
        }
		catch (IOException e) {

			final String message 
				= CLASSE_FILE_SYSTEM_UPLOAD_TEMP_SERVICE 
					+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
					+ METHODE_LOAD_ALL 
					+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
					+ "impossible de parcourir le répertoire "
					+ this.rootTempLocationPath.toAbsolutePath()
						.normalize().toString();

			if (LOG.isFatalEnabled()) {
				LOG.fatal(message, e);
			}
			
			throw new StorageFileNotFoundRunTimeException(message, e);
		}

    } // Fin de loadAll()._________________________________________________

    
        
    /**
     * {@inheritDoc}
     * 
     * @throws StorageFileNotFoundRunTimeException 
     */
    @Override
    public final Resource loadAsResource(
    		final String pFilename) 
    				throws StorageFileNotFoundRunTimeException {
    	
    	/* ne fait rien, LOG.fatal et jette une 
    	 * StorageFileNotFoundRunTimeException si pFilename est blank. */
    	if (StringUtils.isBlank(pFilename)) {
    		
    		final String message 
    			= CLASSE_FILE_SYSTEM_UPLOAD_TEMP_SERVICE 
    			+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
    			+ METHODE_LOAD_AS_RESOURCE
    			+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
    			+ "le nom du fichier uploadé passé en paramètre et blank";
    		
    		final StorageFileNotFoundRunTimeException exc 
    			= new StorageFileNotFoundRunTimeException(message);
    		
    		if (LOG.isFatalEnabled()) {
    			LOG.fatal(message, exc);
    		}
    		
    		throw exc;
    	}
    	
    	Path filePath = null;
    	   	
        try {
        	
        	/* récupère le path absolu du fichier uploadé 
        	 * pFileName côté serveur. */
            filePath = nommerFichierCible(pFilename);
            
            /* Crée une Resource 
             * org.springframework.core.io.UrlResource.UrlResource 
             * à partir du path absolu du fichier uploadé côté serveur. */
            final Resource resource = new UrlResource(filePath.toUri());
            
            /* retourne le fichier sous forme de Resource 
             * si il existe et est lisible. */
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            
            /* ne fait rien, LOG.FATAL et jette une 
             * StorageFileNotFoundRunTimeException si la ressource 
             * relative à pFilename côté serveur est illisible. */
            final String messageRessourceIllisible 
            	= CLASSE_FILE_SYSTEM_UPLOAD_TEMP_SERVICE 
        			+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
        			+ METHODE_LOAD_AS_RESOURCE
        			+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
        			+ "Le fichier ressource uploadé côté serveur : " 
        			+ pFilename + " est illisible";
            
            final StorageFileNotFoundRunTimeException excRessourceIllisible 
            	=  new StorageFileNotFoundRunTimeException(
            			messageRessourceIllisible);
            
            if (LOG.isFatalEnabled()) {
    			LOG.fatal(messageRessourceIllisible, excRessourceIllisible);
    		}
    		
    		throw excRessourceIllisible;
           
        }
        /* ne fait rien, LOG.FATAL et jette une 
         * StorageFileNotFoundRunTimeException si la ressource 
         * relative à pFilename côté serveur est inaccessible. */
        catch (MalformedURLException e) {
        	
        	final String messageMalformedUrl 
        		= CLASSE_FILE_SYSTEM_UPLOAD_TEMP_SERVICE 
        			+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
        			+ METHODE_LOAD_AS_RESOURCE
        			+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
        			+ "Le fichier ressource uploadé côté serveur : " 
        			+ filePath.toAbsolutePath().normalize() 
        			+ " est inaccessible";
        	
            if (LOG.isFatalEnabled()) {
    			LOG.fatal(messageMalformedUrl, e);
    		}
       	
            throw new StorageFileNotFoundRunTimeException(
            		messageMalformedUrl, e);
            
        }
        
    } // Fin de loadAsResource(...)._______________________________________
    
    

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(this.rootTempLocationPath.toFile());
    }
    

    
    /**
     * traite les mauvais fichiers source 
     * org.springframework.web.multipart.MultipartFile 
     * en entrée d'un upload.<br/>
     * <ul>
     * - LOG.fatal et jette une 
     * StorageFileNotFoundRunTimeException si pFileSource est null.<br/>
     * - LOG.fatal et jette une 
     * StorageFileNotFoundRunTimeException si pFileSource est vide.<br/>
     * </ul>
     *
     * @param pFileSource : org.springframework.web.multipart.MultipartFile : 
     * le fichier source en entrée à uploader.
     * @param pMethode : String : méthode appelante.
     * 
     * @throws StorageFileNotFoundRunTimeException
     */
    private void traiterMauvaisFileMultipartSource(
    		final MultipartFile pFileSource, final String pMethode) 
    					throws StorageFileNotFoundRunTimeException {
    	
    	/* LOG.fatal et jette une 
    	 * StorageFileNotFoundRunTimeException si pFileSource est null. */
    	if (pFileSource == null) {
    		
    		final String message = CLASSE_FILE_SYSTEM_UPLOAD_TEMP_SERVICE 
    				+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
    				+ pMethode 
    				+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
    				+ "Le fichier source (multipart) passé en paramètre est null";
    		
    		if (LOG.isFatalEnabled()) {
    			LOG.fatal(message);
    		}
    		
    		throw new StorageFileNotFoundRunTimeException(message);
    	}
    	
        /* récupération du nom du fichier source. */
    	String filename = pFileSource.getName();
    	
//      filename 
//  	= org.springframework.util.StringUtils
//  		.cleanPath(pFileSource.getOriginalFilename());
     		  	
        /* LOG.fatal et jette une 
         * StorageFileNotFoundRunTimeException si pFileSource est vide. */
        if (pFileSource.isEmpty()) {
        	
        	final String message = CLASSE_FILE_SYSTEM_UPLOAD_TEMP_SERVICE 
    				+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
    				+ pMethode 
    				+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
    				+ "Le fichier source (multipart) passé en paramètre est vide : " 
    				+ filename;
        	
        	if (LOG.isFatalEnabled()) {
    			LOG.fatal(message);
    		}
        	
            throw new StorageFileNotFoundRunTimeException(message);
        }

    } // Fin de traiterMauvaisFileMultipartSource(...).____________________
 
    
        
    /**
     * traite les mauvais fichiers target 
     * java.io.File 
     * en sortie d'un upload.<br/>
     * <ul>
     * <li>LOG.fatal et jette une StorageFileNotFoundRunTimeException 
     * si pFileTarget est null.</li>
     * <li>LOG.fatal et jette une StorageFileNotFoundRunTimeException 
     * si pFileTarget est un répertoire.</li>
     * </ul>
     *
     * @param pFileTarget : java.io.File : fichier résultant de l'upload.
     * @param pMethode : String : méthode appelante.
     * 
     * @throws StorageFileNotFoundRunTimeException : void :  .<br/>
     */
    private void traiterMauvaisFileTarget(
    		final File pFileTarget, final String pMethode) 
    					throws StorageFileNotFoundRunTimeException {  
    	
    	/* LOG.fatal et jette une StorageFileNotFoundRunTimeException 
    	 * si pFileTarget est null. */
    	if (pFileTarget == null) {
    		
    		final String message = CLASSE_FILE_SYSTEM_UPLOAD_TEMP_SERVICE 
    				+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
    				+ pMethode 
    				+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
    				+ "Le fichier cible passé en paramètre est null";
    		
    		if (LOG.isFatalEnabled()) {
    			LOG.fatal(message);
    		}
    		
    		throw new StorageFileNotFoundRunTimeException(message);
    		
    	}
		
		/* LOG.fatal et jette une StorageFileNotFoundRunTimeException 
    	 * si pFileTarget est un répertoire. */
		if (pFileTarget.isDirectory()) {
	   		
    		final String message = CLASSE_FILE_SYSTEM_UPLOAD_TEMP_SERVICE 
    				+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
    				+ pMethode 
    				+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
    				+ "Le fichier cible passé en paramètre est un répertoire : " 
    				+ pFileTarget.getAbsolutePath();
    		
    		if (LOG.isFatalEnabled()) {
    			LOG.fatal(message);
    		}
    		
    		throw new StorageFileNotFoundRunTimeException(message);
		    		  		
    	}
		
    } // Fin de traiterMauvaisFileTarget(...)._____________________________
   
    
    
} // FIN DE LA CLASSE FileSystemUploadTempService.---------------------------
