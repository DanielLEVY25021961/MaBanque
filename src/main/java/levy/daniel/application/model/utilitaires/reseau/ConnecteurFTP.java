package levy.daniel.application.model.utilitaires.reseau;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CLASSE ConnecteurFTP :<br/>
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
 * @since 16 juil. 2019
 *
 */
public class ConnecteurFTP {

	// ************************ATTRIBUTS************************************/


	/**
	 * "\r\n".
	 */
	public static final String CARRIAGE_RETURN = "\r\n";
	
	/**
	 * "."
	 */
	public static final String POINT = ".";
	
	/**
	 * Login du serveur FTP.
	 */
	private String user;
	
	/**
	 * Mot de passe du serveur FTP.
	 */
	private String password;
	
	/**
	 * nom d'hôte du serveur FTP.<br/>
	 * Par exemple "ftp.clemessy.fr" ou "127.0.0.1".
	 */
	private String hostFTP;
	
	/**
	 * port du serveur FTP (21 par défaut).
	 */
	private int port;
	
	/**
	 * adresse IP du serveur FTP pour l'échange des données 
	 * (CANAL DES DONNEES).
	 */
	private String dataIP;

	/**
	 * port utilisé par le serveur FTP pour échanger les données 
	 * (CANAL des DONNEES).
	 */
	private int dataPort;
	
	/**
	 * Proxy éventuellement présent sur le trajet du présent client FTP.
	 */
	private Proxy proxy;
	
	/**
	 * Socket de connexion au PROXY.
	 */
	private Socket socketProxy;
	
	/**
	 * Socket de connexion au serveur FTP 
	 * (mode BASE pour transmettre des ordres au serveur FTP).
	 */
	private Socket socketCanalServeur;
	
	/**
	 * InputStream pour la lecture dee réponses SERVEUR du Serveur FTP.<br/>
	 * Ne doit pas être fermé lors de la lecture d'une réponse du Serveur FTP 
	 * dans le CANAL SERVEUR car cela fermerait également la socketCanalServeur.
	 */
	private transient InputStream inputStreamCanalServeur;
	
	/**
	 * BufferedInputStream pour la lecture dee réponses SERVEUR du Serveur FTP.<br/>
	 * Ne doit pas être fermé lors de la lecture d'une réponse du Serveur FTP 
	 * dans le CANAL SERVEUR car cela fermerait également la socketCanalServeur.
	 */
	private transient BufferedInputStream bufferedInputStreamCanalServeur;

	/**
	 * OutputStream pour l'envoi des commandes SERVEUR au Serveur FTP.<br/>
	 * Ne doit pas être fermé lors de l'envoi d'une commande au Serveur FTP 
	 * dans le CANAL SERVEUR car cela fermerait également la socketCanalServeur.
	 */
	private transient OutputStream outputStreamCanalServeur;
		
	/**
	 * OutputStreamWriter pour l'envoi des commandes SERVEUR au Serveur FTP.<br/>
	 * Ne doit pas être fermé lors de l'envoi d'une commande au Serveur FTP 
	 * dans le CANAL SERVEUR car cela fermerait également la socketCanalServeur.
	 */
	private transient OutputStreamWriter outputStreamWriterCanalServeur;
	
	/**
	 * BufferedWriter pour l'envoi des commandes SERVEUR au Serveur FTP.<br/>
	 * Ne doit pas être fermé lors de l'envoi d'une commande au Serveur FTP 
	 * dans le CANAL SERVEUR car cela fermerait également la socketCanalServeur.
	 */
	private transient BufferedWriter bufferedWriterCanalServeur;
	
	/**
	 * Socket de connexion au serveur FTP 
	 * (CANAL DE DONNEES our échanger des données avec le serveur FTP).
	 */
	private Socket socketCanalDonnees;
	
	/**
	 * InputStream pour la lecture dee réponses SERVEUR du Serveur FTP.<br/>
	 * Ne doit pas être fermé lors de la lecture d'une réponse du Serveur FTP 
	 * dans le CANAL SERVEUR car cela fermerait également la socketCanalDonnees.
	 */
	private transient InputStream inputStreamCanalDonnees;
	
	/**
	 * BufferedInputStream pour la lecture dee réponses SERVEUR du Serveur FTP.<br/>
	 * Ne doit pas être fermé lors de la lecture d'une réponse du Serveur FTP 
	 * dans le CANAL SERVEUR car cela fermerait également la socketCanalDonnees.
	 */
	private transient BufferedInputStream bufferedInputStreamCanalDonnees;

	/**
	 * OutputStream pour l'envoi des commandes SERVEUR au Serveur FTP.<br/>
	 * Ne doit pas être fermé lors de l'envoi d'une commande au Serveur FTP 
	 * dans le CANAL SERVEUR car cela fermerait également la socketCanalDonnees.
	 */
	private transient OutputStream outputStreamCanalDonnees;
		
	/**
	 * OutputStreamWriter pour l'envoi des commandes SERVEUR au Serveur FTP.<br/>
	 * Ne doit pas être fermé lors de l'envoi d'une commande au Serveur FTP 
	 * dans le CANAL SERVEUR car cela fermerait également la socketCanalDonnees.
	 */
	private transient OutputStreamWriter outputStreamWriterCanalDonnees;
	
	/**
	 * BufferedWriter pour l'envoi des commandes SERVEUR au Serveur FTP.<br/>
	 * Ne doit pas être fermé lors de l'envoi d'une commande au Serveur FTP 
	 * dans le CANAL SERVEUR car cela fermerait également la socketCanalDonnees.
	 */
	private transient BufferedWriter bufferedWriterCanalDonnees;
	
	/**
	 * LOG : Log : 
	 * Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(ConnecteurFTP.class);



	// *************************METHODES************************************/
	
	
	 /**
	 * CONSTRUCTEUR D'ARITE NULLE.
	 */
	public ConnecteurFTP() {
		this(null, null, null, 21);
	} // Fin de CONSTRUCTEUR D'ARITE NULLE.________________________________
	
	
	
	 /**
	 * CONSTRUCTEUR COMPLET.
	 *
	 * @param pUser : String : Login du serveur FTP
	 * @param pPassword : String : Mot de passe du serveur FTP
	 * @param pHostFTP : String : nom d'hôte du serveur FTP.<br/>
	 * Par exemple "ftp.clemessy.fr" ou "127.0.0.1".
	 * @param pPort : int : port du serveur FTP (21 par défaut).
	 */
	public ConnecteurFTP(
			final String pUser, final String pPassword
			, final String pHostFTP, final int pPort) {
		
		super();
		
		this.user = pUser;
		this.password = pPassword;
		this.hostFTP = pHostFTP;
		this.port = pPort;
		
	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	
	
	
	/**
	 * .<br/>
	 *
	 * @param pArgs : String[] :  .<br/>
	 * 
	 * @throws Exception 
	 */
	public static void main(
			final String... pArgs) throws Exception {
		
		final String adresseIpProxy = "10.77.32.65"; 
		final int portProxy = 8080;
		final Proxy.Type typeProxy = Proxy.Type.SOCKS;
		
		final String user = "admin";
		final String password = "admin";
		final String hostFTP = "127.0.0.1"; 
		final int portFTP = 21;
		
		final ConnecteurFTP connecteurFTP 
			= new ConnecteurFTP(user, password, hostFTP, portFTP);
		
		final Proxy proxy   
			= connecteurFTP.creerProxy(adresseIpProxy, portProxy, typeProxy);
		
//		connecteurFTP.connecterViaProxy(proxy);
		
		connecteurFTP.connecterDirectement();
		
		connecteurFTP.commandLIST();
		
	} // Fin de main(...)._________________________________________________

	
	
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
	private String fournirIpAPartirHote(
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
	 * lit la réponse du serveur FTP (CANAL SERVEUR) et 
	 * la retourne sous forme de String.<br/>
	 *
	 * @return String : réponse du serveur FTP (CANAL SERVEUR)
	 * 
	 * @throws Exception
	 */
	private String readReponseFTPCanalServeur() throws Exception {
		
		String response = "";
		int stream = 0;
		
		final byte[] bytes = new byte[4096];
					
		this.inputStreamCanalServeur = this.socketCanalServeur.getInputStream();
		this.bufferedInputStreamCanalServeur 
			= new BufferedInputStream(this.inputStreamCanalServeur);
		
		stream = this.bufferedInputStreamCanalServeur.read(bytes);
		
		response = new String(bytes, 0, stream);
			
		return response;
		
	} // Fin de readReponseFTPCanalServeur().______________________________
		

	
	/**
	 * envoie une commande (USER, LIST, PWD, ...) 
	 * au serveur FTP (via le CANAL SERVEUR).
	 * <ul>
	 * <li>encode la commande en pCharset.</li>
	 * <li>choisit automatiquement Charset-UTF-8 si pCharset == null.</li>
	 * </ul>
	 *
	 * @param pCommand : String : commande à transmettre au serveur FTP.
	 * @param pCharset : Charset : 
	 * Charset utilisé pour la rédaction de la commande.
	 * 
	 * @throws Exception
	 */
	private void sendCommandFTPCanalServeur(
			final String pCommand
			, final Charset pCharset) throws Exception {

		final String commande = pCommand + CARRIAGE_RETURN;

		Charset charset = null;

		if (pCharset == null) {
			charset = StandardCharsets.UTF_8;
		} else {
			charset = pCharset;
		}

		if (this.socketCanalServeur != null) {

			this.outputStreamCanalServeur = this.socketCanalServeur.getOutputStream();
			this.outputStreamWriterCanalServeur 
				= new OutputStreamWriter(this.outputStreamCanalServeur, charset);
			this.bufferedWriterCanalServeur = new BufferedWriter(this.outputStreamWriterCanalServeur);

			this.bufferedWriterCanalServeur.write(commande);
			this.bufferedWriterCanalServeur.flush();
			
		}

	} // Fin de sendCommandFTPCanalServeur(...).___________________________	 
	 

	
	/**
	 * .<br/>
	 * : void :  .<br/>
	 * 
	 * @throws Exception 
	 */
	public void connecterDirectement() throws Exception {

		final String adresseServeurFTP = this.fournirIpAPartirHote(this.hostFTP);

		// CONNEXION VIA LE CANAL SERVEUR
		this.socketCanalServeur = new Socket(adresseServeurFTP, this.port);

		final String responseServeurFTP = this.readReponseFTPCanalServeur();

		System.out.println(responseServeurFTP);

		if (!responseServeurFTP.startsWith("220")) {
			throw new IOException(
					"Erreur de connexion au FTP : \n" + responseServeurFTP);
		}

		// ENVOI DE LA COMMANDE USER
		final String commandUSER = "USER " + this.user;
		this.sendCommandFTPCanalServeur(commandUSER, null);

		final String responseUSER = this.readReponseFTPCanalServeur();
		
		System.out.println(responseUSER);
		
		if (!responseUSER.startsWith("331")) {
			throw new IOException(
					"Erreur de connexion avec le compte utilisateur : \n" + responseUSER);
		}

		// ENVOI DE LA COMMANDE PASS
		final String commandPASS = "PASS " + this.password;
		this.sendCommandFTPCanalServeur(commandPASS, null);

		final String responsePASS = this.readReponseFTPCanalServeur();
		
		System.out.println(responsePASS);
		
		if (!responsePASS.startsWith("230")) {
			throw new IOException(
					"Erreur de connexion avec le compte utilisateur : \n" + responsePASS);
		}

	} // Fin de connecterDirectement().____________________________________
	
		
	
	/**
	 * .<br/>
	 *
	 * @param pProxy
	 * @throws Exception : void :  .<br/>
	 */
	public void connecterViaProxy(final Proxy pProxy) throws Exception {
		
		final String adresseServeurFTP 
			= this.fournirIpAPartirHote(this.hostFTP);
		
		this.socketProxy = new Socket(pProxy);
		
		final SocketAddress socketAddressServeurFTP 
			= new InetSocketAddress(adresseServeurFTP, this.port);
		
		this.socketProxy.connect(socketAddressServeurFTP);
		
		this.socketCanalServeur = new Socket(adresseServeurFTP, this.port);

		final String responseServeurFTP = this.readReponseFTPCanalServeur();

		if (!responseServeurFTP.startsWith("220")) {
			throw new IOException(
					"Erreur de connexion au FTP : \n" + responseServeurFTP);
		}
		
	} // Fin de connecterViaProxy(...).____________________________________
	
	
	
	/**
	 * ferme la connexion au serveur FTP.
	 * <ul>
	 * <li>envoie la commande "QUIT" au serveur FTP (CANAL SERVEUR)</li>
	 * <li>ferme la SOCKET <code><b>this.socketCanalServeur</b></code></li>
	 * <li>ferme <code><b>this.inputStreamCanalServeur</b></code></li>
	 * <li>ferme <code><b>this.bufferedInputStreamCanalServeur</b></code></li>
	 * <li>ferme <code><b>this.outputStreamCanalServeur</b></code></li>
	 * <li>ferme <code><b>this.outputStreamWriterCanalServeur</b></code></li>
	 * <li>ferme <code><b>this.bufferedWriterCanalServeur</b></code></li>
	 * <li>ferme la SOCKET <code><b>this.socketCanalDonnees</b></code></li>
	 * <li>ferme <code><b>this.inputStreamCanalDonnees</b></code></li>
	 * <li>ferme <code><b>this.bufferedInputStreamCanalDonnees</b></code></li>
	 * <li>ferme <code><b>this.outputStreamCanalDonnees</b></code></li>
	 * <li>ferme <code><b>this.outputStreamWriterCanalDonnees</b></code></li>
	 * <li>ferme <code><b>this.bufferedWriterCanalDonnees</b></code></li>
	 * </ul>
	 * @throws Exception 
	 */
	public void quit() throws Exception {

		try {

			this.sendCommandFTPCanalServeur("QUIT", null);

		} catch (IOException e) {

			throw new Exception(e);

		} finally {

			if (this.socketCanalServeur != null) {
				try {
					this.socketCanalServeur.close();
				} catch (IOException e) {
					throw new Exception(e);
				} finally {
					this.socketCanalServeur = null;
				}
			}
			
			if (this.inputStreamCanalServeur != null) {
				try {
					this.inputStreamCanalServeur.close();
				} catch (IOException e) {
					throw new Exception(e);
				} finally {
					this.inputStreamCanalServeur = null;
				}
			}
			
			if (this.bufferedInputStreamCanalServeur != null) {
				try {
					this.bufferedInputStreamCanalServeur.close();
				} catch (IOException e) {
					throw new Exception(e);
				} finally {
					this.bufferedInputStreamCanalServeur = null;
				}
			}
			
			if (this.outputStreamCanalServeur != null) {
				try {
					this.outputStreamCanalServeur.close();
				} catch (IOException e) {
					throw new Exception(e);
				} finally {
					this.outputStreamCanalServeur = null;
				}
			}
			
			if (this.outputStreamWriterCanalServeur != null) {
				try {
					this.outputStreamWriterCanalServeur.close();
				} catch (IOException e) {
					throw new Exception(e);
				} finally {
					this.outputStreamWriterCanalServeur = null;
				}
			}
			
			if (this.bufferedWriterCanalServeur != null) {
				try {
					this.bufferedWriterCanalServeur.close();
				} catch (IOException e) {
					throw new Exception(e);
				} finally {
					this.bufferedWriterCanalServeur = null;
				}
			}

			if (this.socketCanalDonnees != null) {
				try {
					this.socketCanalDonnees.close();
				} catch (IOException e) {
					throw new Exception(e);
				} finally {
					this.socketCanalDonnees = null;
				}
			}
			
			if (this.inputStreamCanalDonnees != null) {
				try {
					this.inputStreamCanalDonnees.close();
				} catch (IOException e) {
					throw new Exception(e);
				} finally {
					this.inputStreamCanalDonnees = null;
				}
			}
			
			if (this.bufferedInputStreamCanalDonnees != null) {
				try {
					this.bufferedInputStreamCanalDonnees.close();
				} catch (IOException e) {
					throw new Exception(e);
				} finally {
					this.bufferedInputStreamCanalDonnees = null;
				}
			}
			
			if (this.outputStreamCanalDonnees != null) {
				try {
					this.outputStreamCanalDonnees.close();
				} catch (IOException e) {
					throw new Exception(e);
				} finally {
					this.outputStreamCanalDonnees = null;
				}
			}
			
			if (this.outputStreamWriterCanalDonnees != null) {
				try {
					this.outputStreamWriterCanalDonnees.close();
				} catch (IOException e) {
					throw new Exception(e);
				} finally {
					this.outputStreamWriterCanalDonnees = null;
				}
			}
			
			if (this.bufferedWriterCanalDonnees != null) {
				try {
					this.bufferedWriterCanalDonnees.close();
				} catch (IOException e) {
					throw new Exception(e);
				} finally {
					this.bufferedWriterCanalDonnees = null;
				}
			}
			
		}
		
	} // Fin de quit().____________________________________________________

	
	
	/**
	 * initialise le mode passif du Serveur FTP 
	 * ce qui permet de pouvoir communiquer
	 * avec le canal dédié aux données (CANAL des DONNEES).
	 * <ul>
	 * <li>envoie au Serveur FTP la commande "PASV".</li>
	 * <li>décortique la réponse du serveur FTP (CANAL SERVEUR)</li>
	 * <li>déduit l'IP pour les données <code><b>this.dataIP</b></code></li>
	 * <li>déduit le port pour les données <code><b>this.dataPort</b></code></li>
	 * </ul>
	 * 
	 * @throws Exception
	 */
	private void enterPassiveMode() throws Exception {

		this.sendCommandFTPCanalServeur("PASV", null);

		final String response = this.readReponseFTPCanalServeur();

		String ip = null;
		int portLocal = -1;

		// On décortique ici la réponse retournée par le serveur pour récupérer
		// l'adresse IP et le port à utiliser pour le canal data
		final int debut = response.indexOf('(');
		final int fin = response.indexOf(')', debut + 1);

		if (debut > 0) {

			final String dataLink = response.substring(debut + 1, fin);
			final StringTokenizer tokenizer 
				= new StringTokenizer(dataLink, ",");
			
			try {
				// L'adresse IP est séparée par des virgules
				// on les remplace donc par des points...
				ip = tokenizer.nextToken() 
						+ POINT 
						+ tokenizer.nextToken() 
						+ POINT 
						+ tokenizer.nextToken() 
						+ POINT
						+ tokenizer.nextToken();

				// Le port est un entier de type int
				// mais cet entier est découpé en deux
				// la première partie correspond aux 4 premiers bits de l'octet
				// la deuxième au 4 derniers
				// Il faut donc multiplier le premier nombre par 256 et l'additionner au second
				// pour avoir le numéro de ports défini par le serveur
				portLocal 
					= Integer.parseInt(
							tokenizer.nextToken()) * 256 
							+ Integer.parseInt(tokenizer.nextToken());

				this.dataIP = ip;
				this.dataPort = portLocal;

			} catch (Exception e) {
				throw new IOException("SimpleFTP received bad data link information: " + response);
			}
		}

	} // Fin de enterPassiveMode().


	
	/**
	 * Méthode initialisant les flux de communications
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private void createDataSocket() throws Exception {

		this.socketCanalDonnees = new Socket(this.dataIP, this.dataPort);

		this.inputStreamCanalDonnees 
			= this.socketCanalDonnees.getInputStream();
		this.bufferedInputStreamCanalDonnees 
			= new BufferedInputStream(this.inputStreamCanalDonnees);

		this.outputStreamCanalDonnees 
			= this.socketCanalDonnees.getOutputStream();
		this.outputStreamWriterCanalDonnees 
			= new OutputStreamWriter(this.outputStreamCanalDonnees);
		this.bufferedWriterCanalDonnees 
			= new BufferedWriter(this.outputStreamWriterCanalDonnees);
		
	} // Fin de createDataSocket().________________________________________

	
	
	/**
	 * Méthode permettant de lire les réponses du FTP 
	 * (VIA LE CANAL DE DONNEES)
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	private String readCanalDonnees() throws Exception {

		String response = "";
		
		final byte[] bytes = new byte[1024];
		int stream;

		while ((stream = this.bufferedInputStreamCanalDonnees.read(bytes)) != -1) {
			response += new String(bytes, 0, stream);
		}

		System.out.println(response);
		
		return response;
		
	} // Fiin de readCanalDonnees()._______________________________________

	
	
	/**
	 * Retourne l'endroit où nous nous trouvons dur le FTP
	 * 
	 * @return String
	 * 
	 * @throws Exception 
	 */
	public String commandPWD() throws Exception {
		
		// On envoie la commande
		final String commande = "PWD";
		
		this.sendCommandFTPCanalServeur(commande, null);
		
		// On lit la réponse
		final String reponse = this.readReponseFTPCanalServeur();
		
		System.out.println(reponse);
		
		return reponse;
		
	} // Fin de commandPWD().______________________________________________

	
	
	/**
	 * Permet de changer de répertoire (Change Working Directory)
	 *
	 * @param pDir : String : répertoire cible
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	public String commandCWD(
			final String pDir) throws Exception {
		
		final String commande = "CWD " + pDir;
		
		// On envoie la commande
		this.sendCommandFTPCanalServeur(commande, null);
		
		// On lit la réponse
		final String reponse = this.readReponseFTPCanalServeur();
		
		System.out.println(reponse);
		
		return reponse;
		
	} // Fin de commandCWD(...).___________________________________________

	
	
	/**
	 * liste les répertoires.<br/>
	 * <br/>
	 *
	 * @return String
	 * 
	 * @throws Exception
	 */
	public String commandLIST() throws Exception {

		final String commande = "TYPE ASCII";
		
		// On envoie la commande
		this.sendCommandFTPCanalServeur(commande, null);
		
		// On lit la réponse
		final String response = this.readReponseFTPCanalServeur();
		
		System.out.println(response);

		// Passe le serveur en mode passif pour faire transiter des données.
		this.enterPassiveMode();
		
		// instancie une communication via le CANAL de DONNEES
		this.createDataSocket();
		
		final String commandeDonnees = "LIST";
		
		this.sendCommandFTPCanalServeur(commandeDonnees, null);

		final String reponseCanalDonnees = this.readCanalDonnees();
		
		System.out.println(reponseCanalDonnees);
		
		return reponseCanalDonnees;
		
	} // Fin de commandLIST()._____________________________________________	

	
	
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
	public Proxy creerProxy(
			final String pAdresseIpProxy
				, final int pPortProxy
					, final Proxy.Type pTypeProxy) throws Exception {
		
		final SocketAddress proxyAdress 
			= new InetSocketAddress(pAdresseIpProxy, pPortProxy);
		
		this.proxy = new Proxy(pTypeProxy, proxyAdress);
		
		return this.proxy;
		
	} // Fin de creerProxy(...).___________________________________________

	
	
	/**
	 * Getter du Login du serveur FTP.
	 *
	 * @return this.user : String.<br/>
	 */
	public final String getUser() {
		return this.user;
	} // Fin de getUser()._________________________________________________
	
	
	
	/**
	* Setter du Login du serveur FTP.
	*
	* @param pUser : String : 
	* valeur à passer à this.user.<br/>
	*/
	public final void setUser(
			final String pUser) {
		this.user = pUser;
	} // Fin de setUser(...).______________________________________________
	
	
	
	/**
	 * Getter du Mot de passe du serveur FTP.
	 *
	 * @return this.password : String.<br/>
	 */
	public final String getPassword() {
		return this.password;
	} // Fin de getPassword()._____________________________________________
	
	
	
	/**
	* Setter du Mot de passe du serveur FTP.
	*
	* @param pPassword : String : 
	* valeur à passer à this.password.<br/>
	*/
	public final void setPassword(
			final String pPassword) {
		this.password = pPassword;
	} // Fin de setPassword(...).__________________________________________
	
	
	
	/**
	 * Getter du nom d'hôte du serveur FTP.<br/>
	 * Par exemple "ftp.clemessy.fr" ou "127.0.0.1".
	 *
	 * @return this.hostFTP : String.<br/>
	 */
	public final String getHostFTP() {
		return this.hostFTP;
	} // Fin de getHostFTP().______________________________________________
	
	
	
	/**
	* Setter du nom d'hôte du serveur FTP.<br/>
	* Par exemple "ftp.clemessy.fr" ou "127.0.0.1".
	*
	* @param pHostFTP : String : 
	* valeur à passer à this.hostFTP.<br/>
	*/
	public final void setHostFTP(
			final String pHostFTP) {
		this.hostFTP = pHostFTP;
	} // Fin de setHostFTP(...).___________________________________________
	
	
	
	/**
	 * Getter du port du serveur FTP (21 par défaut).
	 *
	 * @return this.port : int.<br/>
	 */
	public final int getPort() {
		return this.port;
	} // Fin de getPort()._________________________________________________
	
	
	
	/**
	* Setter du port du serveur FTP (21 par défaut).
	*
	* @param pPort : int : 
	* valeur à passer à this.port.<br/>
	*/
	public final void setPort(
			final int pPort) {
		this.port = pPort;
	} // Fin de setPort(...).______________________________________________


		
	/**
	 * Getter de l'adresse IP du serveur FTP pour l'échange des données 
	 * (CANAL DES DONNEES).
	 *
	 * @return this.dataIP : String.<br/>
	 */
	public final String getDataIP() {
		return this.dataIP;
	} // Fin de getDataIP()._______________________________________________


	
	/**
	* Setter de l'adresse IP du serveur FTP pour l'échange des données 
	* (CANAL DES DONNEES).
	*
	* @param pDataIP : String : 
	* valeur à passer à this.dataIP.<br/>
	*/
	public final void setDataIP(
			final String pDataIP) {
		this.dataIP = pDataIP;
	} // Fin de setDataIP(...).____________________________________________


	
	/**
	 * Getter du port utilisé par le serveur FTP pour échanger les données 
	 * (CANAL des DONNEES).
	 *
	 * @return this.dataPort : int.<br/>
	 */
	public final int getDataPort() {
		return this.dataPort;
	} // Fin de getDataPort()._____________________________________________


	
	/**
	* Setter du port utilisé par le serveur FTP pour échanger les données 
	* (CANAL des DONNEES).
	*
	* @param pDataPort : int : 
	* valeur à passer à this.dataPort.<br/>
	*/
	public final void setDataPort(
			final int pDataPort) {
		this.dataPort = pDataPort;
	} // Fin de setDataPort(...).__________________________________________



	/**
	 * Getter du Proxy éventuellement présent 
	 * sur le trajet du présent client FTP.
	 *
	 * @return this.proxy : Proxy.<br/>
	 */
	public final Proxy getProxy() {
		return this.proxy;
	} // Fin de getProxy().________________________________________________


	
	/**
	* Setter du Proxy éventuellement présent 
	* sur le trajet du présent client FTP.
	*
	* @param pProxy : Proxy : 
	* valeur à passer à this.proxy.<br/>
	*/
	public final void setProxy(
			final Proxy pProxy) {
		this.proxy = pProxy;
	} // Fin de setProxy(...)._____________________________________________


	
	/**
	 * Getter du Socket de connexion au PROXY.
	 *
	 * @return this.socketProxy : Socket.<br/>
	 */
	public final Socket getSocketProxy() {
		return this.socketProxy;
	} // Fin de getSocketProxy().__________________________________________


	
	/**
	* Setter du Socket de connexion au PROXY.
	*
	* @param pSocketProxy : Socket : 
	* valeur à passer à this.socketProxy.<br/>
	*/
	public final void setSocketProxy(
			final Socket pSocketProxy) {
		this.socketProxy = pSocketProxy;
	} // Fin de setSocketProxy(...)._______________________________________


	
	/**
	 * Getter du Socket de connexion au serveur FTP 
	 * (mode BASE pour transmettre des ordres au serveur FTP).
	 *
	 * @return this.socketCanalServeur : Socket.<br/>
	 */
	public final Socket getSocketCanalServeur() {
		return this.socketCanalServeur;
	} // Fin de getSocketCanalServeur().___________________________________


	
	/**
	* Setter du Socket de connexion au serveur FTP 
	* (mode BASE pour transmettre des ordres au serveur FTP).
	*
	* @param pSocketCanalServeur : Socket : 
	* valeur à passer à this.socketCanalServeur.<br/>
	*/
	public final void setSocketCanalServeur(
			final Socket pSocketCanalServeur) {
		this.socketCanalServeur = pSocketCanalServeur;
	} // Fin de setSocketCanalServeur(...).________________________________


	
	/**
	 * Getter du Socket de connexion au serveur FTP 
	 * (CANAL DE DONNEES our échanger des données avec le serveur FTP).
	 *
	 * @return this.socketCanalDonnees : Socket.<br/>
	 */
	public final Socket getSocketCanalDonnees() {
		return this.socketCanalDonnees;
	} // Fin de getSocketCanalDonnees().___________________________________


	
	/**
	* Setter du Socket de connexion au serveur FTP 
	* (CANAL DE DONNEES our échanger des données avec le serveur FTP).
	*
	* @param pSocketCanalDonnees : Socket : 
	* valeur à passer à this.socketCanalDonnees.<br/>
	*/
	public final void setSocketCanalDonnees(
			final Socket pSocketCanalDonnees) {
		this.socketCanalDonnees = pSocketCanalDonnees;
	} // Fin de setSocketCanalDonnees(...).________________________________

	
		
} // FIN DE LA CLASSE ConnecteurFTP.-----------------------------------------
