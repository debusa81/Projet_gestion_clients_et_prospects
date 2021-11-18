package utilitaires;

import java.time.format.DateTimeFormatter;

/**Classe Utilitaires qui permet la création d'énumération de typesociete et d'action
 * @author Debus Alexandre
 * @version 1
 */
public class Utilitaires {
  public static  enum ACTION{CREATION,MODIFICATION,SUPRESSION}
  public static  enum TYPESOCIETE{CLIENTS,PROSPECTS}

  public static DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");


}
