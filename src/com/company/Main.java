package com.company;

import Exceptions.Exception_entites;
import Vues.Accueil;
import entites.Clients;
import entites.List_clients;
import entites.List_prospects;
import entites.Prospects;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Alexandre Debus
 * @version 1
 */

public class Main {

    public static void main(String[] args) throws Exception_entites {


        //tri de la liste avant affichage
        remplir_liste_clients();
        remplir_liste_prospects();
        //System.out.println(List_prospects.getMaliste_prospects());

        Accueil accueil = new Accueil();
        accueil.setVisible(true);
        accueil.pack();




    }
        /**
         * méthode  qui permet de remplir ma liste de clients
         */
        public static void remplir_liste_clients() throws Exception_entites
        {

            Clients    cl = new Clients("SARL_alex",
                    "1", "rue Test", "54000", "NANCY",
                    "monemail@a.com", "0606060606", "commentaires",
                    150000, 150);

            Clients  cla = new Clients("Sarl_test",
                    "2", "rue Test", "54000", "NANCY",
                    "monemail@a.com", "0606060606", "commentaires",
                    150001, 151);

            Clients clb = new Clients("Sarl_alex",
                    "3", "rue Test", "54000", "NANCY",
                    "monemail@a.com", "06060606060", "commentaires",
                    152000, 130);

            List_clients.getMa_liste().add(cl);
            List_clients.getMa_liste().add(cla);
            List_clients.getMa_liste().add(clb);


            //on crée des objets clients et on les ajoute à la liste


                    Collections.sort(List_clients.getMa_liste(), new Comparator<Clients>()
                    {
                @Override
                public int compare(Clients o1, Clients o2)
                    {
                        return o1.getRaison_sociale().compareTo(o2.getRaison_sociale());
                    }
                    });

            //affichage de la liste
            //System.out.println(ma_liste);




        }

        /**
         * méthode qui permet de remplir la liste prospect
         */
        public static void remplir_liste_prospects() throws Exception_entites
        {



            //ajout et création d'objet Prospects à la liste
            try
            {
                //on vérifie que la date soit au bon format
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    Prospects pr = new Prospects("Mon_prospect", "1", "Rue ma rue",
                        "54000",
                        "Nancy"
                        , "email@email.com", "0606060606", "commentaire",
                        LocalDate.parse("19/02/2021", format), "oui");

                     Prospects pra = new Prospects("Mon_deuxieme_prospect", "2",
                             "Rue ma rue",
                        "54000",
                        "Nancy"
                        , "email@email.com", "0606060606",
                        "commentaire", LocalDate.parse("20/02/2021", format), "oui");

                    Prospects prb = new Prospects("Mon_troisieme",
                        "4", "Rue ma rue", "54000",
                        "Nancy"
                        , "email@email.com", "06060606006", "commentaire",
                        LocalDate.parse("18/02/2021", format), "oui");
                    //on ajoute  les prospects à la liste
                    List_prospects.getMaliste_prospects().add(pr);
                    List_prospects.getMaliste_prospects().add(pra);
                    List_prospects.getMaliste_prospects().add(prb);

            } catch (ParseException e)
                    {
                        e.printStackTrace();
                    }

            //affichage de la liste


        }



    }

