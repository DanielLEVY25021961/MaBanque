package levy.daniel.application.model.services.utilitaires.upload;

import java.io.File;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import levy.daniel.application.model.services.utilitaires.upload.impl.UploadServiceResponse;

/**
 * INTERFACE IUploadService :<br/>
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
public interface IUploadService {

    
    
    /**
     * <p>nomme le fichier cible résultat d'un upload en <strong>concaténant 
     * la String pFilename à la racine des uploads</strong> 
     * et retourne son Path <i>ABSOLU</i> côté serveur.</p>
     * <ul>
     * <li>nommerFichierCible(inexistant) retourne un Path inexistant.</li>
     * <li>ne crée pas automatiquement l'arborescence et le fichier cible.</li>
     * </ul>
     * - retourne null si pFilename est blank.<br/>
     * <br/>
     *
     * @param pFilename : String : chemin <i>relatif</i> du fichier résultat 
     * de l'upload du fichier source <i>par rapport à la racine des fichiers 
     * uploadés</i> sur le serveur.<br/>
     * Par exemple <code>"2018/fichier2018.txt"</code> à placer
     * sous la racine des fichiers uplodés côté serveur.<br/>
     * <br/>
     * 
     * @return : Path : Path <i>ABSOLU</i> du fichier uploadé 
     * (cible) sur le serveur.<br/>
     * 
     * @throws Exception
     */
    Path nommerFichierCible(String pFilename) throws Exception;

    
    
    /**
     * UPLOAD un fichier Multipart  
     * <code>org.springframework.web.multipart.MultipartFile</code> 
     * <i>pFileSource</i> du <strong>client</strong>
     * vers un fichier <strong>destination</strong> 
     * <code>java.io.File </code> automatiquement crée dans le 
     * <strong>dépôt des fichiers temporaires (temp) côté serveur</strong>.<br/>
     * <ul>
     * <li>crée le fichier destination vide 
     * - (ainsi que son arborescence sous temp éventuellement) - 
     * si il n'existait pas 
     * et recopie dedans le contenu de pFileSource.</li>
     * <li>écrase et remplace le fichier destination si il existait déjà.</li>
     * </ul>
     * - retourne un {@link UploadServiceResponse} KO si pFileSource 
     * est null ou vide 
     * (pas de sélection de fichier à uploader côté client).<br/>
     * <br/>
     *
     * @param pFileSource : 
     * <code>org.springframework.web.multipart.MultipartFile</code> : 
     * le fichier multipart source à uploader.
     * 
     * @return {@link UploadServiceResponse} : 
     * encapsulation contenant un status de l'upload (OK/KO), 
     * des listes de messages à l'attention de l'utilisateur 
     * et du développeur, et le File uploadé. 
     * 
     * @throws Exception 
     */
    UploadServiceResponse uploadOneFile(
    		MultipartFile pFileSource) throws Exception;


    
    /**
     *  UPLOAD un fichier Multipart  
     * <code>org.springframework.web.multipart.MultipartFile</code> 
     * <i>pFileSource</i> du <strong>client</strong>
     * vers un fichier <strong>destination</strong> 
     * <code>java.io.File </code> <i>pFileTarget</i>.<br/>
     * <ul>
     * <li>crée l'arborescence de pFileTarget sous la racine des dépôts 
     * (temp) si elle n'existait pas.</li>
     * <li>crée le fichier pFileTarget vide si il n'existait pas 
     * et recopie dedans le contenu de pFileSource.</li>
     * <li>écrase et remplace pFileTarget si il existait déjà.</li>
     * </ul>
     * <br/>
     * - retourne un {@link UploadServiceResponse} KO si pFileSource 
     * est null ou vide 
     * (pas de sélection de fichier à uploader côté client).<br/>
     * - n'uploade rien, LOG.fatal et jette une 
     * StorageFileNotFoundRunTimeException si pFileTarget est null
     * ou répertoire.</br>
     * <br/>
     *
     * @param pFileSource : 
     * <code>org.springframework.web.multipart.MultipartFile</code> : 
     * le fichier multipart source à uploader.
     * @param pFileTarget : java.io.File : le fichier cible uploadé.
     * 
     * @return {@link UploadServiceResponse} : 
     * encapsulation contenant un status de l'upload (OK/KO), 
     * des listes de messages à l'attention de l'utilisateur 
     * et du développeur, et le File uploadé. 
     * 
     * @throws Exception
     */
    UploadServiceResponse uploadOneFile(
    		MultipartFile pFileSource, File pFileTarget) throws Exception;
 
    
    
    /**
     * recherche tous les fichiers <i>simples</i> déjà uploadés côté serveur 
     * sous la racine des dépôts (uploads) 
     * et retourne la collection sous forme de Stream&lt;Path&gt;.
     * <ul>
     * <li>les Path retournés dans le Stream sont les chemins <i>relatifs</i> 
     * des fichiers déjà uploadés par rapport à la racine 
     * des dépôts (uploads).</li>
     * <li>retourne tous les fichiers <i>simples</i> sous la racine des dépôts 
     * (uploads) <i>quel que soit leur niveau sous l'arborescence</i>.</li>
     * <li>ne trie pas les Paths du Stream par 
     * date de modification (création).</li>
     * </ul>
     *
     * @return : Stream&lt;Path&gt;.<br/>
     * 
     * @throws Exception 
     */
    Stream<Path> loadAll() throws Exception;

    
    
    /**
     * retourne un <strong>fichier uploadé côté serveur sous forme de 
     * <code>org.springframework.core.io.Resource</code></strong> 
     * afin de le rendre téléchargeable (download) côté client 
     * grâce à une URL.
     * <ul>
     * <li>récupère le path <i>absolu</i> du fichier uploadé
     *  pFileName côté serveur.</li>
     *  <li>Crée une Resource 
     *  <code>org.springframework.core.io.UrlResource.UrlResource</code> 
     *  à partir du path absolu du fichier uploadé côté serveur.</li>
     *  <li>retourne le fichier sous forme de 
     *  <code>org.springframework.core.io.Resource</code> 
     *  si il existe côté serveur et est lisible.</li>
     * </ul>
     * - ne fait rien, LOG.fatal et jette une 
     * StorageFileNotFoundRunTimeException si pFilename est blank.<br/>
     * - ne fait rien, LOG.FATAL et jette une 
     * StorageFileNotFoundRunTimeException si la ressource relative 
     * à pFilename côté serveur est illisible.<br/>
     * - ne fait rien, LOG.FATAL et jette une 
     * StorageFileNotFoundRunTimeException si la ressource relative 
     * à pFilename côté serveur est inaccessible.<br/>
     * <br/>
     *
     * @param pFilename : String : chemin <i>relatif</i> du fichier résultat 
     * de l'upload du fichier source <i>par rapport à la racine des fichiers 
     * uploadés</i> sur le serveur.<br/>
     * Par exemple <code>"2018/fichier2018.txt"</code> à placer
     * sous la racine des fichiers uplodés côté serveur.<br/>
     * <br/>
     * 
     * @return : <code>org.springframework.core.io.Resource</code>
     * 
     * @throws Exception
     */
    Resource loadAsResource(String pFilename) throws Exception;


    
    /**
     * .<br/>
     * <ul>
     * <li>.</li>
     * </ul>
     * :  :  .<br/>
     * 
     * @throws Exception
     */
    void deleteAll() throws Exception;

    
    
} // FIN DE L'INTERFACE IUploadService.-------------------------------------
