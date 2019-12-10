/**
 * CLASSE package-info :<br/>
 * Ce package contient toutes les classes nécessaires 
 * à la <b>gestion des préférences des OBJETS METIER UTILISATEURS</b> et des 
 * <b>fichiers properties externes des OBJETS METIER UTILISATEURS
 * paramétrables par la MOA</b> 
 * et gérés comme des préférences.<br/>
 * <br/>
 * Les préférences de l'application sont des données très générales 
 * qu'un <b>administrateur doit pouvoir paramétrer</b>. Par exemple :
 * <ul>
 * <li>La langue par défaut de l'application (Locale par défaut)</li>
 * <li>L'encodage par défaut de l'application (Charset par défaut)</li>
 * <li>...</li>
 * </ul>
 * <p>Plus généralement, les préférences de l'application sont tous 
 * les <b>paramètres</b> que l'administrateur doit ne 
 * <b>saisir qu'une seule fois</b> 
 * et qui doivent rester <b>mémorisés dans l'application</b> tant que 
 * l'Administrateur n'a pas décidé d'en changer.</p>
 * <p>Les préférences doivent être automatiquement 
 * initialisées avec des valeurs d'usine "en dur" au cas où 
 * se produirait un défaut de livraison de leur stockage.</p>
 * 
 * <p><span style="text-decoration: underline;"><b>Rôle du Gestionnaire des préférences</b></span></p>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/gestionnairespreferences/gestionnaire_preferences_uc.png" 
 * style= "float : left; width : 45%; margin-right : 2%; margin-top : 1%; margin-bottom : 1%;" 
 * alt="CU des préférences" border="1" align="center" />
 * <img src="../../../../../../../../../../../javadoc/images/gestionnairespreferences/gestionnaire_preferences_exigences.png"
 * style= "float : left; width : 50%; margin-right : 1%; margin-top : 1%; margin-bottom : 1%;" 
 * alt="exigences des préférences" border="1" align="center" />
 * </div>
 * <div>
 * <p style="clear : both";></p>
 * <img src="../../../../../../../../../../../javadoc/images/gestionnairespreferences/gestionnaire_preferences_role.png" 
 * alt="rôle des préférences" border="1" align="center" />
 * </div>
 * <br/><br/>
 * 
 * <p><span style="text-decoration: underline;"><b>Initialisation des préférences</b></span></p>
 * <div>
 * <p>L'application doit pouvoir pallier à un défaut de livraison ou 
 * d'installation du stockage des préférences (preferences.properties par exemple).</p>
 * <p>L'application doit donc créer son stockage 
 * (preferences.properties par exemple) 
 * avec des valeurs stockées "en dur" si il est manquant. ====> <b>le gestionnaire 
 * de préférences doit comporter une méthode créant un stockage 
 * avec des valeurs stockées "en dur" dans la classe</b>.
 * <br/>
 * L'application doit pouvoir servir des valeurs de préférences 
 * "en dur" si la création du stockage échoue pour 
 * des motifs de protection de disque, ... ====> 
 * <b>chaque méthode retournant une préférence doit fournir une préférence 
 * en dur au cas où la lecture du stockage échouerait.</b>
 * </p>
 * <br/>
 * <div>
 * <img src="../../../../../../../../../../../javadoc/images/gestionnairespreferences/gestionnaire_preferences_activites.png" 
 * alt="cinématique de l'initialisation des préférences" border="1" align="center" />
 * </div>
 * </div>
 * <br/><br/>
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
 * @since 13 mars 2019
 *
 */
package levy.daniel.application.apptechnic.configurationmanagers.gestionnairespreferences.metier.utilisateurs;