package utilitaires;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
/**Classe Utilitaires qui permet la création d'énumération de typesociete et d'action
 * @author Debus Alexandre
 * @version 4.1.0
 */
public class Utilitaires {
  //énumeration et datetimeformatter
  public static  enum ACTION{CREATION,MODIFICATION,SUPRESSION}
  public static  enum TYPESOCIETE{CLIENTS,PROSPECTS}
  public static DateTimeFormatter DATETIMEFORMATTER= DateTimeFormatter.ofPattern("dd/MM/yyyy");
  public static DecimalFormat DF = new DecimalFormat("#.##");;
}
