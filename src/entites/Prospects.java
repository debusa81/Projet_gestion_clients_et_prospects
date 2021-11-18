package entites;

import Exceptions.Exception_entites;
import utilitaires.Utilitaires;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


/**Classe prospects qui hérite de Société  et qui a pour attribut supplémentaires la date de prospection et
 * si le prospect est intéressé
 * @author Alexandre Debus
 *
 * @version 1
 */

public class Prospects  extends Societe
{
    //attribut de classe  de la classe Prospects
    private static int id_prospects=0;

    //attributs d' instance
    private LocalDate prospect_datee;
    private  String prospect_interesse;


    //constructeur implicit de la classe Prospects  heritant du constructeur de Societe
    public Prospects( String raison_sociale, String Numero_rue, String nom_marue, String code, String ville,
                      String email, String telephone,String commentaires, LocalDate prospect_date,
                      String prospect_interesse)
            throws ParseException, Exception_entites
    {
        super( raison_sociale, Numero_rue, nom_marue, code, ville, email,telephone ,commentaires);


       setProspect_date(prospect_date);
       setProspect_interesse(prospect_interesse);
       //incrementation de id prospects
       id_prospects=id_prospects+1;
       setId(id_prospects);
    }



    public Prospects(){
        id_prospects=id_prospects+1;
        setId(id_prospects);
    }


        //getter et setter
    public static int getId_prospects()
    {
        return id_prospects;
    }

    public static void setId_prospects(int id_prospects)
    {
        Prospects.id_prospects = id_prospects;
    }

    public LocalDate getProspect_date()
    {
        return prospect_datee;
    }

    /**setter de date qui permet de verifier la date
     *
     * @param prospect_date
     */
    public void setProspect_date(LocalDate prospect_date)
    {
        if (prospect_date==null){
            throw new NullPointerException("la date ne peut pas etre null");
        }


        this.prospect_datee= prospect_date;

    }

    public String getProspect_interesse()
    {
        return prospect_interesse;
    }

    /**setter de prospect interessé qui n' accepte que oui ou non comme valeur
     *
     * @param prospect_interesse
     * @throws Exception_entites
     */
    public void setProspect_interesse(String prospect_interesse)throws Exception_entites
    {
        //on initialise la variable val_prospect


        this.prospect_interesse = prospect_interesse;
    }

    /**
     * methode to_string
     * @return une chaine de caractères avec toutes les données  de Société + celle de Prospects
     */
    @Override
    public String toString()
    {
        return super.toString() +
                "" + prospect_datee +
                ", " + prospect_interesse;
    }
}
