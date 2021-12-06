package com.company;
import Exceptions.Exception_entites;
import Vues.Accueil;
import entites.Clients;
import entites.List_clients;
import entites.List_prospects;
import entites.Prospects;
import utilitaires.Utilitaires;
import javax.swing.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
/**Classe main qui permet de lancer dans notre programme
 * @author Alexandre Debus
 * @version 4.1.0
 */
public class Main {
    /**
     * méthode principal du programme
     * @param args
     *
     */
    public static void main(String[] args)  {
        //tri de la liste avant affichage
        remplir_liste_clients();
        remplir_liste_prospects();
        Accueil accueil = new Accueil();
        accueil.setVisible(true);
        accueil.pack();
    }
    /**
     *
     * méthode remplir liste clients  qui permet de remplir ma liste de clients
     */
    public static void remplir_liste_clients() {
        //on crée des objets clients et on les ajoute à la liste
        Clients cl,cla,clb;
        try {
            cl = new Clients("SARL_alex",
                    "1", "rue Test", "54000", "NANCY",
                    "monemail@a.com", "0606060606", "commentaires",
                    15000, 150);

            cla = new Clients("Sarl_test",
                    "2", "rue Test", "54000", "NANCY",
                    "monemail@a.com", "0606060606", "commentaires",
                    150001.526, 151);

            clb = new Clients("Sarl_classe",
                    "3", "rue Test", "54000", "NANCY",
                    "monemail@a.com", "06060606060", "commentaires",
                    152000.369, 130);
            List_clients.getMa_liste_clients().add(cl);
            List_clients.getMa_liste_clients().add(cla);
            List_clients.getMa_liste_clients().add(clb);
        }catch (Exception_entites exception_entites){
            JOptionPane.showMessageDialog(null,exception_entites.getMessage());
        }
        catch (Exception exception){
            JOptionPane.showMessageDialog(null,"Une erreur est subvenue");
            exception.printStackTrace();
            System.exit(1);
        }
        Collections.sort(List_clients.getMa_liste_clients(), new Comparator<Clients>()
        {
            @Override
            public int compare(Clients o1, Clients o2)
            {
                return o1.getRaison_sociale().compareTo(o2.getRaison_sociale());
            }
        });
        //affichage de la liste
    }
    /**
     * méthode  remplir_liste_prospects qui permet de remplir la liste prospect
     */
    public static void remplir_liste_prospects()
    {
        //ajout et création d'objet Prospects à la liste
        try
        {
            //on vérifie que la date soit au bon format
            Prospects pr = new Prospects("Mon_prospect", "1", "Rue ma rue",
                    "54000",
                    "Nancy"
                    , "email@email.com", "0606060606", "commentaire",
                    LocalDate.parse("19/02/2020",Utilitaires.DATETIMEFORMATTER), "oui");
            Prospects pra = new Prospects("Mon_deuxieme_prospect", "2",
                    "Rue ma rue",
                    "54000",
                    "Nancy"
                    , "email@email.com", "0606060606",
                    "commentaire", LocalDate.parse("20/02/2021", Utilitaires.DATETIMEFORMATTER),
                    "oui");
            Prospects prb = new Prospects("Mon_troisieme",
                    "4", "Rue ma rue", "54000",
                    "Nancy"
                    , "email@email.com", "06060606006", "commentaire",
                    LocalDate.parse("18/02/2021", Utilitaires.DATETIMEFORMATTER), "oui");
            //on ajoute  les prospects à la liste
            List_prospects.getMaliste_prospects().add(pr);
            List_prospects.getMaliste_prospects().add(pra);
            List_prospects.getMaliste_prospects().add(prb);
        }catch (Exception_entites exception_entites){
            JOptionPane.showMessageDialog(null,exception_entites.getMessage());
        }
        catch (ParseException e)
        {
            JOptionPane.showMessageDialog(null,"La date n'a pu étre converti");
        }
        catch (DateTimeParseException dateTimeParseException){
            JOptionPane.showMessageDialog(null,"la date n'est pas au bon format ");
        }
        catch (Exception exception){
            JOptionPane.showMessageDialog(null,"une erreur est survenue");
            exception.printStackTrace();
            System.exit(1);
        }
        //affichage de la liste
        Collections.sort(List_prospects.getMaliste_prospects(), new Comparator<Prospects>() {
            @Override
            public int compare(Prospects o1, Prospects o2) {
                return o1.getRaison_sociale().compareTo(o2.getRaison_sociale());
            }
        });
    }
}

