package entites;

import Exceptions.MonException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Date;

/**
 * @author Alexandre Debus
 * classe Prospects héritant de la classe Societe
 * @version 1
 */

public class Prospects  extends Societe
{
    //attribut de classe  de la classe Prospects
    private static int id_prospects=0;

    //attributs d' instance
    private LocalDate prospect_date;
    private  int prospect_interesse;


    //constructeur implicit de la classe Prospects  heritant du constructeur de Societe
    public Prospects( String raison_sociale, String Numero_rue, String nom_marue, String code, String ville,
                      String email, String telephone,String commentaires, LocalDate prospect_date,
                      String prospect_interesse)
            throws ParseException,MonException
    {
        super( raison_sociale, Numero_rue, nom_marue, code, ville, email,telephone ,commentaires);

                id_prospects= this.getId();
       setProspect_date(String.valueOf(prospect_date));
       setProspect_interesse(prospect_interesse);
       //incrementation de id prospects
       id_prospects++;
    }
    //constructeur avec deux parametres
    public Prospects(LocalDate prospect_date, int prospect_interesse)
    {
        setProspect_date(String.valueOf(prospect_date));
        System.out.println(prospect_date);
        this.prospect_interesse = prospect_interesse;
    }
    //constructeur avec un parametre
    public Prospects(String prospect_date)
    {

            setProspect_date(prospect_date);

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
        return prospect_date;
    }

    public void setProspect_date(String prospect_date)
    {

        this.prospect_date= LocalDate.parse(prospect_date);




    }

    public int getProspect_interesse()
    {
        return prospect_interesse;
    }

    public void setProspect_interesse(String prospect_interesse)
    {
        //on initialise la variable val_prospect
        int val_prospect = 0;
        //si le parametre equivaut à oui ,non ou autre chose
        if (prospect_interesse.equals("oui"))
        {
            val_prospect=1;
        }else if(prospect_interesse.equals("non"))
            {
            val_prospect=0;
            }else
                {
                //on renvoie une erreur si  ce n'est pas oui ou non
                try
                    {
                        throw new MonException("");
                    } catch (MonException e)
                        {

                            System.out.println("la valeur n'est pas bonne elle doit etre soit oui  soit non");
                        }
        }

        this.prospect_interesse = val_prospect;
    }

    /**
     * methode to_string
     * @return une chaine de caractères avec toutes les données  de Société + celle de Prospects
     */
    @Override
    public String toString()
    {
        return super.toString() +
                "" + prospect_date +
                ", " + prospect_interesse;
    }
}
