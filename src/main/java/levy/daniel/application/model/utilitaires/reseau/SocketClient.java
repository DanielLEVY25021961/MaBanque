package levy.daniel.application.model.utilitaires.reseau;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE SocketClient :<br/>
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
 * @author daniel.levy Lévy
 * @version 1.0
 * @since 15 juil. 2019
 *
 */
public final class SocketClient {

	// ************************ATTRIBUTS************************************/

	/**
	 * "\r\n".
	 */
	public static final String CARRIAGE_RETURN = "\r\n";
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(SocketClient.class);

	// *************************METHODES************************************/

	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	private SocketClient() {
		super();
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________

	
	
	/**
	 * retourne une adresse IP à partir d'un nom de domaine 
	 * ou d'une adresse IP d'un serveur à contacter.<br/>
	 * Par exemple : retourne "151.101.122.217" 
	 * que l'on saisisse "www.lemonde.fr" ou "151.101.122.217" en paramètre.
	 *
	 * @param pAdresseIpSite : String : 
	 * adresse IP ou nom de domaine d'un serveur
	 * 
	 * @return : String : adresse IP du serveur.
	 * 
	 * @throws UnknownHostException
	 */
	private static String fournirIpAPartirHote(
			final String pAdresseIpSite) 
								throws UnknownHostException {
				
		String adresseIpDistante = null;
		
		// Il s'agit d'un nom de domaine et non d'une adresse IPV4
		if (pAdresseIpSite.matches("[a-zA-Z\\.]+")) {
			
			adresseIpDistante 
				= InetAddress.getByName(pAdresseIpSite).getHostAddress();
			
		}		
		// IP V4
		else {
			adresseIpDistante = pAdresseIpSite;
		}
		
		return adresseIpDistante;

	} // Fin de fournirIpAPartirHote(...)._________________________________

	
	
	/**
	 * crée une abstraction d'un PROXY présent sur le réseau.
	 *
	 * @param pAdresseIpProxy : String : 
	 * adresse IP du PROXY (par exemple : "10.77.32.65" pour le PROXY du CEREMA)
	 * @param pPortProxy : int : 
	 * numéro de Port du PROXY (par exemple : 8080 pour le PROXY du CEREMA)
	 * @param pTypeProxy : java.net.Proxy.Type : 
	 * <ul>
	 * <li>Proxy.Type.SOCKS : 
	 * acronyme de Secured Over Credential-based Kerberos</li>
	 * <li>Proxy.Type.HTTP : proxy HTTP</li>
	 * <li>Proxy.Type.DIRECT : pas de Proxy</li>
	 * </ul>
	 * 
	 * @return : java.net.Proxy : .<br/>
	 * 
	 * @throws Exception 
	 */
	public static Proxy creerProxy(
			final String pAdresseIpProxy
				, final int pPortProxy
					, final Proxy.Type pTypeProxy) throws Exception {
		
		final SocketAddress proxyAdress 
			= new InetSocketAddress(pAdresseIpProxy, pPortProxy);
		
		final Proxy proxy = new Proxy(pTypeProxy, proxyAdress);
		
		return proxy;
		
	} // Fin de creerProxy(...).___________________________________________

	
	
	/**
	 * crée un SOCKET vers un site situé à pAdresseIpSite sur le port pPort.<br/>
	 * <ul>
	 * <li>pAdresseIpSite peut être soit une adresse IP
	 * , soit un nom de domaine</li>
	 * </ul>
	 *
	 * @param pAdresseIpSite : String : 
	 * adresse IP ou nom de domaine d'un site distant.
	 * @param pPortProtocole : int : 
	 * port du protocole utilisé pour contacter le serveur distant 
	 * (80 pour HTTP).
	 * 
	 * @throws Exception
	 */
	public static void creerConnexionDirecte(
			final String pAdresseIpSite
				, final int pPortProtocole) throws Exception {
		
		final String adresseIpDistante = fournirIpAPartirHote(pAdresseIpSite);
		
		Socket socketClient = null;
		
		try {
			
			socketClient = new Socket(adresseIpDistante, pPortProtocole);
			
			afficherSocketClientEtDistant(pAdresseIpSite, adresseIpDistante, socketClient);
			
		} catch (Exception e){ 
			
			throw new Exception(e);
			
		} finally {
			
			if (socketClient != null) {
				socketClient.close();
			}
			
		}
						
	} // Fin de creerConnexionDirecte(...).________________________________


	
	/**
	 * .<br/>
	 * <br/>
	 *
	 * @param pAdresseIpSite
	 * @param pPortProtocole
	 * @param pRequeteGet
	 * 
	 * @throws Exception
	 */
	public static void creerSocketDirectEtLireRequete(
			final String pAdresseIpSite
				, final int pPortProtocole
					, final String pRequeteGet) throws Exception {
		
		String adresseIpDistante = null;
		
		// Il s'agit d'un nom de domaine et non d'une adresse IPV4
		if (pAdresseIpSite.matches("[a-zA-Z\\.]+")) {
			adresseIpDistante = InetAddress.getByName(pAdresseIpSite).getHostAddress();
		}		
		// IP V4
		else {
			adresseIpDistante = pAdresseIpSite;
		}
		
		Socket socketClient = null;
		
		OutputStream outputStream = null;
		BufferedOutputStream bufferedOutputStream = null;
		
		InputStream inputSream = null;
		BufferedInputStream bufferedInputStream = null;
		
		try {

			socketClient = new Socket(adresseIpDistante, pPortProtocole);

			afficherSocketClientEtDistant(pAdresseIpSite, adresseIpDistante, socketClient);

			outputStream = socketClient.getOutputStream();
			bufferedOutputStream = new BufferedOutputStream(outputStream);

			/* ENVOIE LA REQUETE AU SERVEUR DISTANT. */
			bufferedOutputStream.write(pRequeteGet.getBytes(StandardCharsets.UTF_8));
			bufferedOutputStream.flush();

			/* RECUPERE LA REPONSE DU SERVEUR DISTANT. */
			inputSream = socketClient.getInputStream();
			bufferedInputStream = new BufferedInputStream(inputSream);

			/* LIT LA REPONSE DU SERVEUR DISTANT. */
			String content = "";
			int stream = 0;
			final byte[] bytes = new byte[1024];
			
			while ((stream = bufferedInputStream.read(bytes)) != -1) {
				content += new String(bytes, 0, stream);
			}
			
			/* AFFICHE LA REPONSE DU SERVEUR DISTANT DANS UN BROWSER. */
			new Browser(
						socketClient.getInetAddress().getHostName(), content); 

		} catch (Exception e){ 
			
			throw new Exception(e);
			
		} finally {
			
			if (socketClient != null) {
				socketClient.close();
			}
			
			if (outputStream != null) {
				outputStream.close();
			}
			
			if (bufferedOutputStream != null) {
				bufferedOutputStream.close();
			}

			if (inputSream != null) {
				inputSream.close();
			}

			if (bufferedInputStream != null) {
				bufferedInputStream.close();
			}
			
		}
								
	} // Fin de creerSocketDirectEtLireRequete(...)._______________________
	
	
	
	/**
	 * affiche à la console les informations d'un SOCKET CLIENT.
	 *
	 * @param pAdresseIpSite : String : adresse littérale (nom de domaine) ou IP du site distant
	 * @param pResult : adresse IP du site distant
	 * @param pSocketClient : Socket : Socket CLIENT.<br/>
	 */
	public static void afficherSocketClientEtDistant(
			final String pAdresseIpSite
				, final String pResult
					, final Socket pSocketClient) {
		
		System.out.println();		
		System.out.println("HOTE DISTANT SAISI EN PARAMETRE : " + pAdresseIpSite);
		System.out.println("HOTE DISTANT PASSE DANS LE SOCKET : " + pResult);
		
		System.out.println();
		System.out.println("Adresse IP INTERNET de l'hôte distant - socket.getInetAddress().getHostAddress() : " + pSocketClient.getInetAddress().getHostAddress());
		System.out.println("Adresse IP INTERNET de l'hôte distant SITE LOCAL - socket.getInetAddress().getHostAddress().isSiteLocalAddress() : " + pSocketClient.getInetAddress().isSiteLocalAddress());
		System.out.println("Nom canonique de l'hôte distant - socketClient.getInetAddress().getCanonicalHostName() : " + pSocketClient.getInetAddress().getCanonicalHostName());
		System.out.println("Nom de l'hôte distant (NOM DE DOMAINE) - socket.getInetAddress().getHostName() : " + pSocketClient.getInetAddress().getHostName());
		System.out.println("Adresse SOCKET de l'hôte distant - socket.getRemoteSocketAddress() : " + pSocketClient.getRemoteSocketAddress());
		System.out.println("Port de communication côté serveur distant - socket.getPort() : " + pSocketClient.getPort());
		
		System.out.println();
		System.out.println("Adresse IP du client - socketClient.getLocalAddress() : " + pSocketClient.getLocalAddress());
		System.out.println("Adresse IP du client BOUCLE LOCALE ? - socketClient.getLocalAddress().isLoopbackAddress() : " + pSocketClient.getLocalAddress().isLoopbackAddress());
		System.out.println("Nom canonique du client - socketClient.getLocalAddress().getCanonicalHostName() : " + pSocketClient.getLocalAddress().getCanonicalHostName());
		System.out.println("Nom du client (NOM DE DOMAINE) - socketClient.getLocalAddress().getHostName() : " + pSocketClient.getLocalAddress().getHostName());
		System.out.println("Adresse SOCKET du client - socketClient.getLocalSocketAddress() : " + pSocketClient.getLocalSocketAddress());
		System.out.println("Port de communication côté client - socketClient.getLocalPort() : " + pSocketClient.getLocalPort());

	} // Fin de afficherSocketClientEtDistant(...).________________________
	
	
	
	/**
	 * .<br/>
	 *
	 * @param pAdresseIpSite
	 * @param pPortProtocole : int :
	 * @param pProxy : java.net.Proxy :  .<br/>
	 * 
	 * @throws IOException 
	 */
	public static void creerConnexionViaProxy(
			final String pAdresseIpSite
				, final int pPortProtocole
					, final Proxy pProxy) throws IOException {
		
		final Socket socketProxy = new Socket(pProxy);
		
		final SocketAddress socketAddressRemote 
			= new InetSocketAddress(pAdresseIpSite, pPortProtocole);
		
		try {

			socketProxy.connect(socketAddressRemote);

			System.out.println("Port de communication côté serveur : " + socketProxy.getPort());
			System.out.println("Port de communication côté client : " + socketProxy.getLocalPort());
			System.out.println("Nom de l'hôte distant : " + socketProxy.getInetAddress().getHostName());
			System.out.println("Adresse de l'hôte distant : " + socketProxy.getInetAddress().getHostAddress());
			System.out.println("Adresse socket de l'hôte distant : " + socketProxy.getRemoteSocketAddress());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {			
			socketProxy.close();
		}
		
	} // Fin de creerConnexionViaProxy(...)._______________________________
	
	
	
	/**
	 * .<br/>
	 *
	 * @param pArgs : String[] :  .<br/>
	 * 
	 * @throws Exception 
	 */
	public static void main(
			final String... pArgs) throws Exception {
		
//		final String adresseIpProxy = "10.77.32.65";
//		final int portProxy = 8080;
//		final Proxy.Type typeProxy = Proxy.Type.SOCKS;
//		
//		final Proxy proxy = creerProxy(adresseIpProxy, portProxy, typeProxy);
//		
//		/* www.lemonde.fr */
//		final String adresseSiteRemote = "151.101.122.217";
//		final int portProtocoleHTTP = 80;
//		
//		creerConnexionViaProxy(adresseSiteRemote, portProtocoleHTTP, proxy);

		/* www.lemonde.fr */
//		final String adresseSiteRemote = "151.101.122.217";
//		final String adresseSiteRemote = "www.lequipe.fr";
//		final String adresseSiteRemote = "zetcode.com";
//		final int portProtocoleHTTP = 80;
//		
//		creerConnexionDirecte(adresseSiteRemote, portProtocoleHTTP);

		
		final String adresseSiteRemoteRequest = "fr.wikipedia.org";
		final int portProtocoleHTTPRequest = 80;
		
		String request = "GET wiki/Digital_learning HTTP/1.1" + CARRIAGE_RETURN;
        request += "Host: fr.wikipedia.org" + CARRIAGE_RETURN;
        request += CARRIAGE_RETURN;
        
        creerSocketDirectEtLireRequete(
        		adresseSiteRemoteRequest
        			, portProtocoleHTTPRequest
        				, request);
		
	} // Fin de main(...)._________________________________________________
	


} // FIN DE LA CLASSE SocketClient.------------------------------------------
