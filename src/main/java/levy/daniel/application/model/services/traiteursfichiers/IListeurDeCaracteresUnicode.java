package levy.daniel.application.model.services.traiteursfichiers;


/**
 * INTERFACE IListeurDeCaracteresUnicode :<br/>
 * <p>Interface centralisant toutes les méthodes des objets capables 
 * de lister une String caractère par caractère en fournissant 
 * toutes les données Unicode 
 * (codePoint, code HexaDecimal Unicode, nom du caractère, ...).</p>
 * 
 * comporte essentiellement : 
 * </ul>
 * <li>une méthode listerChaineCarParCar(
 * String pString, Integer pNombreMaxiCaracteres) qui liste 
 * caractère par caractère les pNombreMaxiCaracteres premiers 
 * caractères de pString en détaillant les caractéristiques unicode 
 * de chaque caractère.</li>
 * </ul>
 * <br/>
 *
 * - Exemple d'utilisation :<br/>
 *<br/>
 * 
 * - Mots-clé :<br/>
 * unicode, Unicode, codepoint, nom unicode,<br/>
 * point de code décimal, point de code hexa,<br/>
 * <br/>
 *
 * - Dépendances :<br/>
 * <br/>
 *
 *
 * @author dan Lévy
 * @version 1.0
 * @since 2 mars 2016
 *
 */
public interface IListeurDeCaracteresUnicode {
	
	
	/**
	 * <p>Retourne une String permettant l'affichage 
	 * de min(longueurChaine, 1000) premiers caractères
	 * (caractère par caractère sur des lignes distinctes) 
	 * d'une chaine de caractères pString 
	 * avec les informations 
	 * Unicode concernant chaque caractère.</p>
	 * 
	 * Par exemple : <br/>
	 * <code>IControle.listerChaineCarParCar("à b")</code> 
	 * retourne : <br/>
	 * Position : 1      Caractère : à     Unicode : \u00e0  NumericValue : -1    TypeCaractere : 2   valeurEntiere : 224   Point de Code décimal : 224   Point de Code Hexa : e0      Nom : LATIN SMALL LETTER A WITH GRAVE         <br/>
	 * Position : 2      Caractère :       Unicode : \u0020  NumericValue : -1    TypeCaractere : 12  valeurEntiere : 32    Point de Code décimal : 32    Point de Code Hexa : 20      Nom : SPACE                                   <br/>
	 * Position : 3      Caractère : b     Unicode : \u0062  NumericValue : 11    TypeCaractere : 2   valeurEntiere : 98    Point de Code décimal : 98    Point de Code Hexa : 62      Nom : LATIN SMALL LETTER B                    <br/>
	 * <br/>
	 * - retourne null si pString est blank (null ou vide).<br/>
	 * <br/>
	 *
	 * @param pString : String : String à afficher 
	 * caractère par caractère.<br/>
	 * 
	 * @return : String : Affichage caractère 
	 * par caractère sur des lignes distinctes.<br/>
	 */
	String listerChaineCarParCar(String pString);
	

	
	/**
	 * <p>Retourne une String permettant l'affichage 
	 * des pNombreMaxiCaracteres premiers caractères
	 * (caractère par caractère sur des lignes distinctes) 
	 * d'une chaine de caractères pString 
	 * avec les informations 
	 * Unicode concernant chaque caractères.</p>
	 * - si pNombreMaxiCaracteres == null, 
	 * lit le min(longueurChaine, 1000) premiers caractères.<br/>
	 * - si pNombreMaxiCaracteres == 0, lit toute la chaîne. 
	 * ATTENTION à la mémoire et à l'affichage.<br/>
	 * - si la longueur de pString <= pNombreMaxiCaracteres
	 * , lit toute la chaîne.<br/>
	 * - si la longueur de pString > pNombreMaxiCaracteres
	 * , lit pNombreMaxiCaracteres.<br/>
	 * <br/>
	 * Par exemple : <br/>
	 * <code>IControle.listerChaineCarParCar("à b", 100)</code> 
	 * retourne : <br/>
	 * Position : 1      Caractère : à     Unicode : \u00e0  NumericValue : -1    TypeCaractere : 2   valeurEntiere : 224   Point de Code décimal : 224   Point de Code Hexa : e0      Nom : LATIN SMALL LETTER A WITH GRAVE         <br/>
	 * Position : 2      Caractère :       Unicode : \u0020  NumericValue : -1    TypeCaractere : 12  valeurEntiere : 32    Point de Code décimal : 32    Point de Code Hexa : 20      Nom : SPACE                                   <br/>
	 * Position : 3      Caractère : b     Unicode : \u0062  NumericValue : 11    TypeCaractere : 2   valeurEntiere : 98    Point de Code décimal : 98    Point de Code Hexa : 62      Nom : LATIN SMALL LETTER B                    <br/>
	 * <br/>
	 * - retourne null si pString est blank (null ou vide).<br/>
	 * <br/>
	 *
	 * @param pString : String : String à afficher 
	 * caractère par caractère.<br/>
	 * @param pNombreMaxiCaracteres : Integer : 
	 * Nombre maximal de caractères lus par la méthode. 
	 * Utile pour éviter les débordements de mémoire.<br/>
	 * 
	 * @return : String : Affichage caractère 
	 * par caractère sur des lignes distinctes.<br/>
	 */
	String listerChaineCarParCar(
			String pString
			, Integer pNombreMaxiCaracteres);
	


} // FIN DE L'INTERFACE IListeurDeCaracteresUnicode.-------------------------
