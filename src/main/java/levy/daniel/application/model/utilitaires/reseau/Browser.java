package levy.daniel.application.model.utilitaires.reseau;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * CLASSE Browser :<br/>
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
 * @since 14 juil. 2019
 *
 */
public class Browser extends JFrame {

	// ************************ATTRIBUTS************************************/
	
	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * JFXPanel.
	 */
	private final transient JFXPanel fxPanel = new JFXPanel();

	/**
	 * Group.
	 */
	private transient Group group;
	
	/**
	 * Scene.
	 */
	private transient Scene scene;
	
	/**
	 * WebView.
	 */
	private transient WebView webView;
	
	/**
	 * WebEngine.
	 */
	private transient WebEngine webEngine;
	
	/**
	 * FXRunnable.<br/>
	 * hérite de java.lang.Runnable.
	 */
	private transient FXRunnable fxRunnable;
	
	/**
	 * 900.
	 */
	public static final int WIDTH = 1200;
	
	/**
	 * 600.
	 */
	public static final int HEIGHT = 600;
	
	
	/**
	 * LOG : Log : Logger pour Log4j (utilisant commons-logging).
	 */
	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(Browser.class);

	// *************************METHODES************************************/
	
	   
	 /**
	 * CONSTRUCTEUR COMPLET.<br/>
	 * 
	 * @param pTitle : String :
	 * @param pContent : String :
	 */
	public Browser(
			final String pTitle
				, final String pContent) {
		
		super();

		// On initialise notre fenêtre JFrame
		this.setSize(new Dimension(WIDTH, HEIGHT));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle(pTitle);
		this.add(new JScrollPane(this.fxPanel));
		this.setVisible(true);

		// pour mélanger des composants JavaFX et Swing
		// les composants JavaFX doivent s'exécuter dans leur propre thread
		// Ils ne s'exécutent pas dans l'EDT swing !
		this.fxRunnable = new FXRunnable(pContent);
		
		// Lance le thread dans un thread dédié à JavaFX
		Platform.runLater(this.fxRunnable);

	} // Fin de CONSTRUCTEUR COMPLET.______________________________________
	

	
	/**
	 * mettre à jour le JFXPanel.<br/>
	 * <br/>
	 *
	 * @param pContent : String : contenu du JFXPanel.<br/>
	 */
	public void setContent(
			final String pContent) {
		// pour la mise à jour de ce composant,
		// vu qu'il cohabite avec Swing
		// celle-ci DOIT se faire dans un thread JavaFX
		this.fxRunnable = new FXRunnable(pContent);
		
		Platform.runLater(this.fxRunnable);
		
	} // Fin de setContent(...).___________________________________________
	
	
	
	/**
	 * Getter du JFXPanel.
	 *
	 * @return this.fxPanel : JFXPanel.<br/>
	 */
	public final JFXPanel getFxPanel() {
		return this.fxPanel;
	} // Fin de getFxPanel().______________________________________________


	
	/**
	 * Getter du Group.
	 *
	 * @return this.group : Group.<br/>
	 */
	public final Group getGroup() {
		return this.group;
	} // Fin de getGroup().________________________________________________


		
	/**
	* Setter du Group.
	*
	* @param pGroup : Group : 
	* valeur à passer à this.group.<br/>
	*/
	public final void setGroup(
			final Group pGroup) {
		this.group = pGroup;
	} // Fin de setGroup(...)._____________________________________________



	/**
	 * Getter de la Scene.
	 *
	 * @return this.scene : Scene.<br/>
	 */
	public final Scene getScene() {
		return this.scene;
	} // Fin de getScene().________________________________________________


	
	/**
	* Setter de la Scene.
	*
	* @param pScene : Scene : 
	* valeur à passer à this.scene.<br/>
	*/
	public final void setScene(
			final Scene pScene) {
		this.scene = pScene;
	} // Fin de setScene(...)._____________________________________________


	
	/**
	 * Getter du WebView.
	 *
	 * @return this.webView : WebView.<br/>
	 */
	public final WebView getWebView() {
		return this.webView;
	} // Fin de getWebView().______________________________________________


	
	/**
	* Setter du WebView..
	*
	* @param pWebView : WebView : 
	* valeur à passer à this.webView.<br/>
	*/
	public final void setWebView(
			final WebView pWebView) {
		this.webView = pWebView;
	} // Fin de setWebView(...).___________________________________________

	
	
	/**
	 * Getter du WebEngine.
	 *
	 * @return this.webEngine : WebEngine.<br/>
	 */
	public final WebEngine getWebEngine() {
		return this.webEngine;
	} // Fin de getWebEngine().____________________________________________


	
	/**
	* Setter du WebEngine.
	*
	* @param pWebEngine : WebEngine : 
	* valeur à passer à this.webEngine.<br/>
	*/
	public final void setWebEngine(
			final WebEngine pWebEngine) {
		this.webEngine = pWebEngine;
	} // Fin de setWebEngine(...)._________________________________________

	
	
	/**
	 * Getter du FXRunnable.
	 *
	 * @return this.fxRunnable : FXRunnable.<br/>
	 */
	public final FXRunnable getFxRunnable() {
		return this.fxRunnable;
	} // Fin de getFxRunnable().___________________________________________



	/**
	 * une classe interne qui permet de travailler 
	 * conjointement avec JavaFX et Swing.
	 * 
	 * @author CHerby
	 */
	private class FXRunnable implements Runnable {

		/**
		 * contenu du JFXPanel.
		 */
		private final String content;
		
		

		/**
		 * CONSTRUCTEUR COMPLET.<br/>
		 * 
		 * @param pContent : String : contenu du JFXPanel.
		 */
		FXRunnable(final String pContent) {
			this.content = pContent;
		} // Fin de CONSTRUCTEUR COMPLET._________________________________

		
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void run() {
			
			this.initFX(Browser.this.getFxPanel());
			this.setContent(this.content);
			
		} // Fin de run()._________________________________________________
		
		

		/**
		 * initialise les composants JavaFX
		 * 
		 * @param pFxPanel
		 */
		private void initFX(
				final JFXPanel pFxPanel) {
			
			Browser.this.setGroup(new Group());
			Browser.this.setScene(new Scene(Browser.this.getGroup()));
			
			final Scene sceneLocal = Browser.this.getScene();
			pFxPanel.setScene(sceneLocal);

			Browser.this.setWebView(new WebView());
			Browser.this.setWebEngine(Browser.this.getWebView().getEngine());

			Browser.this.getGroup().getChildren().add(Browser.this.getWebView());

			Browser.this.getWebView().setMinSize(WIDTH, HEIGHT);
			Browser.this.getWebView().setMaxSize(WIDTH, HEIGHT);
			
		} // Fin de initFX(...).___________________________________________
		
		
				
		/**
		 * Getter du contenu du JFXPanel.
		 *
		 * @return this.content : String.<br/>
		 */
		public final String getContent() {
			return this.content;
		} // Fin de getContent().__________________________________________



		/**
		 * passe le contenu pContent au WebEbgine du Browser.<br/>
		 *
		 * @param pContent : String : contenu à passer au WebEngine.<br/>
		 */
		public void setContent(
				final String pContent) {
			
			Browser.this.getWebEngine().loadContent(pContent);
			Browser.this.getWebEngine().reload();
			
		} // Fin de setContent(...)._______________________________________
		
		
	} // FIN DE LA CLASSE FXRunnable.----------------------------------------
	
	
		   
} // FIN DE LA CLASSE Browser.-----------------------------------------------
