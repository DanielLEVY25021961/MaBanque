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
 * CLASSE FileSystemUploadService :<br/>
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
@Service(value="FileSystemUploadService")
public class FileSystemUploadService implements IUploadService {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * "Classe FileSystemUploadService".
	 */
	public static final String CLASSE_FILE_SYSTEM_UPLOAD_SERVICE 
		= "Classe FileSystemUploadService";
	
	/**
	 * "Méthode upload(MultipartFile pFileSource, File pTarget)".
	 */
	public static final String METHODE_UPLOAD 
		= "Méthode upload(MultipartFile pFileSource, File pTarget)";
	
	/**
	 * "unused".<br/>
	 */
	public static final String UNUSED = "unused";

    /**
     * Path du répertoire racine des fichiers uploadés.<br/>
     */
    private final transient Path rootLocationPath;


	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings(UNUSED)
	private static final Log LOG 
		= LogFactory.getLog(FileSystemUploadService.class);
	
   
	// *************************METHODES************************************/
	   
   
     /**
     * CONSTRUCTEUR D'ARITE NULLE.<br/>
     * <ul>
     * <li>récupère le Path de la racine des téléversements 
     * auprès du <code>ConfigurationApplicationManager</code> 
     * et alimente automatiquement <code>this.rootLocationPath</code> 
     * avec le path indiqué dans le fichier properties 
     * <code>configuration_ressources_externes_fr_FR.properties</code>.</li>
     * <li>Crée <i>si nécessaire</i> l'arborescence des répertoires jusqu'au 
     * <strong>répertoire racine du dépôt des fichiers uploadés 
     * (inclus)</strong> <code>this.rootLocationPath</code>.</li>
     * <li>ne fait rien et ne lève pas d'exception 
     * si le répertoire racine est déjà existant.</li>
     * </ul>
     * 
     * @throws Exception 
     */
    public FileSystemUploadService() throws Exception {
    	
    	/* récupère le path de la racine des téléversements 
    	 * auprès du ConfigurationApplicationManager. */
    	this(null);
    	
     } // Fin de CONSTRUCTEUR D'ARITE NULLE._______________________________

    
    
     /**
     * CONSTRUCTEUR D'ARITE 1.<br/>
     * <ul>
     * <li>prend en paramètre le java.nio.file.Path 
     * de la racine des téléversements pRootLocationPath et le passe à
     * <code>this.rootLocationPath</code>.</li>
     * <li>récupère si pRootLocationPath == null 
     * le Path de la racine des téléversements 
     * auprès du <code>ConfigurationApplicationManager</code> 
     * et alimente automatiquement <code>this.rootLocationPath</code> 
     * avec le path indiqué dans le fichier properties 
     * <code>configuration_ressources_externes_fr_FR.properties</code>.</li>
     * <li>Crée <i>si nécessaire</i> l'arborescence des répertoires jusqu'au 
     * <strong>répertoire racine du dépôt des fichiers uploadés 
     * (inclus)</strong> <code>this.rootLocationPath</code>.</li>
     * <li>ne fait rien et ne lève pas d'exception 
     * si le répertoire racine est déjà existant.</li>
     * </ul>
     * 
     * @param pRootLocationPath : java.nio.file.Path : 
     * Path vers la racine des téléversements.
     * 
     * @throws Exception
     */
    public FileSystemUploadService(final Path pRootLocationPath) 
    		throws Exception {
    	
    	super();
    	
    	if (pRootLocationPath == null) {
    		
    		this.rootLocationPath = Paths.get(
        			ConfigurationApplicationManager.getPathTeleversements())
        			.normalize().toAbsolutePath();
    		
    	} else {
    		this.rootLocationPath = pRootLocationPath;
    	}
    	  	
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
        	
            Files.createDirectories(this.rootLocationPath);
            
        } catch (IOException e) {
        	
        	final String message 
        		= CLASSE_FILE_SYSTEM_UPLOAD_SERVICE 
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
    	
        return this.rootLocationPath.resolve(pFilename);
        
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
    	
    	if (pFileSource.isEmpty()) {
    		
    		final boolean status = false;
    		
    		final String messageUtilisateur 
    			= "vous devez sélectionner un fichier à uploader";
    		
    		final String messageDeveloppeur 
    			= CLASSE_FILE_SYSTEM_UPLOAD_SERVICE
    			+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
    			+ METHODE_UPLOAD
    			+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
    			+ messageUtilisateur;
    		
    		final File fichierUploade = null;
    		
    		final UploadServiceResponse uploadServiceResponse 
    			= new UploadServiceResponse(status, fichierUploade);
    		
    		uploadServiceResponse.ajouterMessageUtilisateur(messageUtilisateur);
    		uploadServiceResponse.ajouterMessageDeveloppeur(messageDeveloppeur);
    		
    		if (LOG.isFatalEnabled()) {
    			LOG.fatal(messageDeveloppeur);
    		}
    		
    		return uploadServiceResponse;
    	}
    	
    	
		return this.uploadOneFile(
				pFileSource
					, this.nommerFichierCible(
							pFileSource.getOriginalFilename()).toFile());
		
    } // Fin de upload(...)._______________________________________________

 
    
    /**
     * {@inheritDoc}
     */
    @Override
    public final UploadServiceResponse uploadOneFile(
    		final MultipartFile pFileSource, final File pFileTarget) 
    								throws Exception {
    	
    	/* n'uploade rien, LOG.fatal et jette une 
    	 * StorageFileNotFoundRunTimeException si pFileSource 
    	 * est null ou vide. */
    	this.traiterMauvaisFileMultipartSource(pFileSource, METHODE_UPLOAD);

    	/* n'uploade rien, LOG.fatal et jette une 
    	 * StorageFileNotFoundRunTimeException si pFileTarget 
    	 * est null ou répertoire. */
    	this.traiterMauvaisFileTarget(pFileTarget, METHODE_UPLOAD);
    	
       	
    	/* récupère le nom du fichier multipart uploadé. */
    	String filename = pFileSource.getName();
    	
//        filename 
//    	= org.springframework.util.StringUtils
//    		.cleanPath(pFileSource.getOriginalFilename());

		try (InputStream inputStream = pFileSource.getInputStream()) {

			final Path pathTarget = pFileTarget.toPath();
			
			final int nombreNiveauxArborescence = pathTarget.getNameCount();
			final Path arborescenceTarget
				= pathTarget.subpath(0, nombreNiveauxArborescence - 1);
			
			if (!Files.exists(arborescenceTarget)) {
				
				try {
		        	
		            Files.createDirectories(arborescenceTarget);
		            
		        } catch (IOException e) {
		        	
		        	final String message 
		        		= CLASSE_FILE_SYSTEM_UPLOAD_SERVICE 
		        		+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
		        		+ METHODE_UPLOAD
		        		+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
		        		+ "Impossible de créer l'arborescence sous le répertoire racine des téléversements (uploads).";
		        	
		            throw new StorageFileNotFoundRunTimeException(message, e);
		        }
			}
			
			/* crée le fichier pFileTarget vide si il n'existait pas 
			 * et recopie dedans le contenu de pFileSource. */
			/* écrase et remplace pFileTarget si il existait déjà. */
			Files.copy(inputStream
					, pathTarget
						, StandardCopyOption.REPLACE_EXISTING);
			  		
			final boolean status = true;
			
			final String messageUtilisateur 
				= "le fichier " + pFileSource.getOriginalFilename() 
		        + " a bien été uploadé côté serveur à l'emplacement : " 
				+ pFileTarget.getAbsolutePath();
			
			final String messageDeveloppeur 
				= CLASSE_FILE_SYSTEM_UPLOAD_SERVICE
				+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
				+ METHODE_UPLOAD
				+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE
				+ messageUtilisateur;
			
			final File fichierUploade = null;
			
			final UploadServiceResponse uploadServiceResponse 
				= new UploadServiceResponse(status, fichierUploade);
			
			uploadServiceResponse.ajouterMessageUtilisateur(messageUtilisateur);
			uploadServiceResponse.ajouterMessageDeveloppeur(messageDeveloppeur);
			
			return uploadServiceResponse;

		} catch (IOException e) {
			
			final String message = CLASSE_FILE_SYSTEM_UPLOAD_SERVICE 
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
     * {@inheritDoc}
     * @throws StorageFileNotFoundRunTimeException 
     */
    @Override
    public Stream<Path> loadAll() throws StorageFileNotFoundRunTimeException {
        try {
        	
         	final Stream<Path> resultat = Files.walk(this.rootLocationPath, 1)
                    .filter(path -> !path.equals(this.rootLocationPath))
                    .map(this.rootLocationPath::relativize);
        	      	
            return resultat;
        }
        catch (IOException e) {
            throw new StorageFileNotFoundRunTimeException("Failed to read stored files", e);
        }

    } // Fin de loadAll()._________________________________________________

    
        
    /**
     * {@inheritDoc}
     * 
     * @throws StorageFileNotFoundRunTimeException 
     */
    @Override
    public Resource loadAsResource(
    		final String pFilename) throws StorageFileNotFoundRunTimeException {
    	
        try {
        	
            final Path file = nommerFichierCible(pFilename);
            final Resource resource = new UrlResource(file.toUri());
            
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
              
            throw new StorageFileNotFoundRunTimeException(
                    "Could not read file: " + pFilename);

            
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundRunTimeException(
            		"Could not read file: " + pFilename, e);
        }
    }
    
    

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(this.rootLocationPath.toFile());
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
    		
    		final String message = CLASSE_FILE_SYSTEM_UPLOAD_SERVICE 
    				+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
    				+ pMethode 
    				+ IConstantesSeparateurs.SEPARATEUR_MOINS_AERE 
    				+ "Le fichier source (multipart) passé en paramètre est null";
    		
    		if (LOG.isFatalEnabled()) {
    			LOG.fatal(message);
    		}
    		
    		throw new StorageFileNotFoundRunTimeException(message);
    	}
    	
       	
    	/* récupère le nom du fichier multipart uploadé. */
    	String filename = pFileSource.getName();
    	
//        filename 
//    	= org.springframework.util.StringUtils
//    		.cleanPath(pFileSource.getOriginalFilename());
   	
        /* LOG.fatal et jette une 
         * StorageFileNotFoundRunTimeException si pFileSource est vide. */
        if (pFileSource.isEmpty()) {
        	
        	final String message = CLASSE_FILE_SYSTEM_UPLOAD_SERVICE 
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
    		
    		final String message = CLASSE_FILE_SYSTEM_UPLOAD_SERVICE 
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
	   		
    		final String message = CLASSE_FILE_SYSTEM_UPLOAD_SERVICE 
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
   
    
    
} // FIN DE LA CLASSE FileSystemUploadService.-------------------------------
