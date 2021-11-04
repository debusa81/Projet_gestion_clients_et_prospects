package com.company;

import Exceptions.MonException;
import Vues.Accueil;
import entites.Clients;
import entites.List_clients;
import entites.List_prospects;
import entites.Prospects;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Alexandre Debus
 * @version 1
 */

public class Main {

    public static void main(String[] args){

    remplir_liste_clients(List_clients.getMa_liste());
    remplir_liste_prospects();
    get_liste();
        Accueil accueil = new Accueil();
        accueil.setVisible(true);
        accueil.pack();




    }
        /**
         * méthode  qui permet de remplir ma liste de clients
         */
        public static List<Clients> remplir_liste_clients(List<Clients> ma_liste)
        {
            //recupération de ma liste de clients
              ma_liste = new ArrayList<Clients>();

            //on crée des objets clients et on les ajoute à la liste


                    Clients   cl = new Clients("SARL_alex",
                        "1", "rue Test", "54000", "NANCY",
                        "monemail@a.com", "0606060606", "commentaires",
                     150000, 150);
                    Clients cla = new Clients("Sarl_test",
                        "2", "rue Test", "54000", "NANCY",
                        "monemail@a.com", "0606060606", "commentaires",
                        150001, 151);

                    Clients clb = new Clients("Sarl_alex",
                        "3", "rue Test", "54000", "NANCY",
                        "monemail@a.com", "06060606060", "commentaires",
                        152000, 130);
                    ma_liste = new ArrayList<>();
                    ma_liste.add(cl);
                    ma_liste.add(cla);
                    ma_liste.add(clb);


            //affichage de la liste
            //System.out.println(ma_liste);


            return ma_liste;

        }

        /**
         * méthode qui permet de remplir la liste prospect
         */
        public static void remplir_liste_prospects()
        {
            //recupération de la liste  de prospects
            List<Prospects> ma_liste_prospects = List_prospects.getMaliste_prospects();

            ma_liste_prospects = new ArrayList<>();
            //ajout et création d'objet Prospects à la liste
            try {
                //on vérifie que la date soit au bon format
                DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                Prospects pr = new Prospects("Mon_prospect", "1", "Rue ma rue",
                        "54000",
                        "Nancy"
                        , "email@email.com", "0606060606", "commentaire",
                        LocalDate.parse("19/02/2021", format), "oui");

                Prospects pra = new Prospects("Mon_deuxieme_prospect", "2", "Rue ma rue",
                        "54000",
                        "Nancy"
                        , "email@email.com", "0606060606",
                        "commentaire", LocalDate.parse("20/02/2021", format), "oui");

                Prospects prb = new Prospects("Mon_troisieme",
                        "4", "Rue ma rue", "54000",
                        "Nancy"
                        , "email@email.com", "06060606006", "commentaire",
                        LocalDate.parse("18/02/2021", format), "oui");
                ma_liste_prospects.add(pr);
                ma_liste_prospects.add(pra);
                ma_liste_prospects.add(prb);

                } catch (ParseException e)
                    {
                        e.printStackTrace();
                    }
            //affichage de la liste
            System.out.println(ma_liste_prospects);

        }
        public static String get_liste(){
        String s= String.valueOf(remplir_liste_clients(List_clients.getMa_liste()).get(0));

            return s ;
        }
    public static String get_liste_un(){
        String s= String.valueOf(remplir_liste_clients(List_clients.getMa_liste()).get(1));

        return s ;
    }
    public static String get_liste_deux(){
        String s= String.valueOf(remplir_liste_clients(List_clients.getMa_liste()).get(2));

        return s ;
    }
    }

