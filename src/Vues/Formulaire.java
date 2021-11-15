package Vues;

import Exceptions.Exception_entites;
import com.company.Main;
import entites.*;
import utilitaires.Utilitaires;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alexandre Debus
 * @version 1
 */

public class Formulaire extends JFrame
{
    private JPanel contentPane;
    private JTextField TFD_id;
    private JTextField TFD_raison_sociale;
    private JTextField TFD_numrue;
    private JTextField TFD_nomrue;
    private JTextField TFD_ville;
    private JTextField TFD_codepostal;
    private JTextField TFD_telephone;
    private JTextField TFD_email;
    private JTextArea TAE_Comment;
    private JTextField TFD_chiffreaffaire;
    private JTextField TFD_nbremployes;
    private JButton BTN_action;
    private JButton accueilButton;
    private JButton quitterButton;
    private JLabel LAB_raison_sociale;
    private JLabel LAB_numrue;
    private JLabel LAB_ville;
    private JLabel LAB_telephone;
    private JLabel LAB_nomrue;
    private JLabel LAB_codepostal;
    private JLabel LAB_email;
    private JLabel LAB_comment;
    private JLabel LAB_chiffreaffaire;
    private JLabel LAB_formulaire;
    private JPanel PAN_chiffreaffaire;
    private JPanel PAN_nbremploye;
    private JTextField TFD_date;
    private JPanel PAN_date;
    private JRadioButton RBT_oui;
    private JRadioButton RBT_non;
    private JPanel PAN_radiobouton;
    private JLabel LAB_date;
    private JLabel LAB_interesse;
    private JButton buttonOK;
        //constructeur de mon formulaire
    public Formulaire(Utilitaires.ACTION action, Societe societe,Utilitaires.TYPESOCIETE typesociete) throws Exception_entites
    {
        setContentPane(contentPane);


        getRootPane().setDefaultButton(buttonOK);
        //on rend l' id impossible à modifier
        TFD_id.setEnabled(false);
        if (typesociete== Utilitaires.TYPESOCIETE.PROSPECTS){
            LAB_formulaire.setText("Creation d'un  prospect");
            BTN_action.setText("ajout d'un prospect");
            PAN_chiffreaffaire.setVisible(false);
            PAN_nbremploye.setVisible(false);
        }else {
            PAN_date.setVisible(false);
            PAN_radiobouton.setVisible(false);
        }


        //si il s' agit d'une création,modification ou une suppresion
        switch (action){
            //on recupere l' id
            case CREATION:
                if (societe instanceof Clients){
                    TFD_id.setText(String.valueOf(((Clients) societe).getId_clients()));

                }else if (societe instanceof Prospects){

                    TFD_id.setText(String.valueOf(((Prospects) societe).getId_prospects()));


                }


                break;

                //si il s' agit d'une supression on modifie le titre de la fenetre et le texte sur le bouton d'action
            case SUPRESSION:
                LAB_formulaire.setText("Suppression d'un Client");
                //on remplis les champs avec les données existante

                remplir_champs(societe);
                BTN_action.setText("suprimmer client");
                //on rend la modification impossible
                TFD_raison_sociale.setEnabled(false);
                TFD_nbremployes.setEnabled(false);
                TFD_email.setEnabled(false);
                TFD_telephone.setEnabled(false);
                TAE_Comment.setEnabled(false);
                TFD_ville.setEnabled(false);
                TFD_codepostal.setEnabled(false);
                TFD_numrue.setEnabled(false);
                TFD_nomrue.setEnabled(false);
                TFD_chiffreaffaire.setEnabled(false);
                TFD_date.setEnabled(false);
                if (societe instanceof Prospects){
                    BTN_action.setText("supression d'un prospect");
                }


                break;

             //dans le cas d' une modificatin  on change le titre de la fenetre  et le texte du bouton d' action
            case MODIFICATION:
                LAB_formulaire.setText("Modification d' un client");
                remplir_champs(societe);
                BTN_action.setText("modification  d' un client");
                break;
        }

        //au click du bouton quitter
        quitterButton.addMouseListener(new MouseAdapter()
        {
            //on ferme la fenetre
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                dispose();
            }
        });
        //au click du bouton accueil

        accueilButton.addMouseListener(new MouseAdapter()
        {
            //on ferme la fenetre et on réinitialise la page d' accueil
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                Accueil ac= null;
                try {
                    ac = new Accueil();
                } catch (Exception_entites ex) {
                    ex.printStackTrace();
                }
                ac.setVisible(true);
                ac.pack();
            }

        });

        //au clique sur le bouton action
        BTN_action.addMouseListener (new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                //gestion des cas,création,modifier ,supprimer
                switch (action)
                {
                    //à la suppresion on renvoie un message de confirmation
                    case SUPRESSION:


                    suppression(societe,typesociete);
                        break;

                        //dans le cas d'une création
                    case CREATION:
                        try
                        {
                            if (societe instanceof Clients)
                            {
                                ArrayList<Clients> liste=List_clients.getMa_liste();
                                verification_champs(societe,liste);
                                JOptionPane.showMessageDialog(null,"Client bien ajouté");
                            }else if (societe instanceof Prospects){
                                System.out.println("je suis un prospects");
                                ArrayList<Prospects>liste=List_prospects.getMaliste_prospects();
                                verification_champs(societe,liste);
                                JOptionPane.showMessageDialog(null,"Prospects bien ajouté");
                            }
                            //on vérifie tous nos champs et on ajoute notre client

                            //on indique qu'il y a bien eu un ajout

                            //on ferme la fenetre et on reinitialise notre page accueil avec la liste

                            dispose();
                            Accueil accueil = new Accueil();
                            accueil.setVisible(true);
                            accueil.pack();

                        } catch (Exception_entites ex)
                                {
                                     ex.printStackTrace();
                                }


                     break;
                        //dans le cas d'une modification
                    case MODIFICATION:
                        //on va chercher l' indice  de notre objet dans la liste
                       List<Clients> liste=List_clients.getMa_liste();
                        int index = liste.indexOf(societe);

                        try
                        {
                            //on modifie tous les champs  avec ce que l' utilisateur a renseigné
                            societe.setRaison_sociale(TFD_raison_sociale.getText());
                            societe.setNumero_rue(TFD_numrue.getText());societe.setNom_rue(TFD_nomrue.getText());
                            societe.setVille(TFD_ville.getText());societe.setCode_postal(TFD_codepostal.getText());
                            societe.setEmail(TFD_email.getText());societe.setComments(TAE_Comment.getText());
                            //si il s' agit d'un client
                            if (societe instanceof Clients)
                            {
                                ((Clients) societe).setChiffre_affaire(Double.parseDouble(TFD_chiffreaffaire.getText()));
                                ((Clients) societe).setNbr_employes(Integer.parseInt(TFD_nbremployes.getText()));
                            }
                        }catch (Exception_entites Me)
                                {
                                    System.out.println("les éléments ne sont pas bon ");
                                }
                        //on indique que la modification a bien été effectué
                        JOptionPane.showMessageDialog(null,"modification bien effectué ");
                        //on ferme la fenetre, on update la liste, et on réinitialise une page accueil
                        dispose();

                        liste.set(index,(Clients) societe);
                        Accueil accueil = null;
                        try {
                            accueil = new Accueil();
                        } catch (Exception_entites ex) {
                            ex.printStackTrace();
                        }
                        accueil.setVisible(true);
                        accueil.pack();

                }
            }
        });



    }

    /**
     * Méthode qui permet de remplir tous les champs  par défaut quand il s' agit d'une modification ou d' une suppresion
     * @param societe notre societe
     */
    public void remplir_champs(Societe societe){
        //on remplit tous les champs commun de société
        TFD_raison_sociale.setText(societe.getRaison_sociale());
        TFD_numrue.setText(societe.getNumero_rue());
        TFD_nomrue.setText(societe.getNom_rue());
        TFD_codepostal.setText(societe.getCode_postal());
        TFD_ville.setText(societe.getVille());
        TFD_telephone.setText(societe.getNum_tel());
        TFD_email.setText(societe.getEmail());
        TAE_Comment.setText(societe.getComments());
            //ensuite on remplit les champs spécifiques de client
        if (societe instanceof Clients)
        {
            TFD_id.setText(String.valueOf(((Clients) societe).getId_clients()));
            TFD_chiffreaffaire.setText(String.valueOf(((Clients) societe).getChiffre_affaire()));
            TFD_nbremployes.setText(String.valueOf(((Clients) societe).getNbr_employes()));
            System.out.println("client");
        }else if(societe instanceof Prospects)
            {
                TFD_id.setText(String.valueOf(((Prospects) societe).getId_prospects()));
                System.out.println(((Prospects) societe).getProspect_interesse());
                DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String date_prospect=((Prospects) societe).getProspect_date().format(dateTimeFormatter);
                TFD_date.setText(date_prospect);

                if (((Prospects) societe).getProspect_interesse()==1){
                    RBT_oui.setSelected(true);

                }else {
                    RBT_non.setSelected(true);
                }


            }


    }

    /**
     * Méthode qui permet la vérification de tous les champs rentrés  ainsi que la création d'une société
     * (Clients ou prospects)
     * @param societe  notre sociéte
     * @param ma_liste la liste qu'on a récupére dans notre formulaire
     * @throws Exception_entites toutes les exceptions que l' on a créeé
     */
    public void verification_champs(Societe societe,ArrayList ma_liste) throws Exception_entites
    {
        //on récupère les champs remplis par l' utilisateur
       String raison= TFD_raison_sociale.getText();
       String num_rue=TFD_numrue.getText();
       String nom_rue=TFD_nomrue.getText();
       String ville = TFD_ville.getText();
       String code_p= TFD_codepostal.getText();
       String tel = TFD_telephone.getText();
       String email = TFD_email.getText();
       String comments= TAE_Comment.getText();



       //on prepare la création de notre nouvel objet avec ses chmps commun
        try {
            societe.setRaison_sociale(raison);societe.setNumero_rue(num_rue);societe.setNom_rue(nom_rue);
            societe.setVille(ville);societe.setCode_postal(code_p);societe.setNum_tel(tel);societe.setEmail(email);
            societe.setComments(comments);
        }catch (Exception_entites me){}
        //avec ses champs spécifiques
       if (societe instanceof Clients){

               double Chiffre_affaire=0;
               int nbr_employe=0;
               //on recupère les champs numérique au bon format
               try {
                   Chiffre_affaire= Double.parseDouble(TFD_chiffreaffaire.getText());
                   nbr_employe = Integer.parseInt(TFD_nbremployes.getText());
                   String date_prospects=TFD_date.getText();
                   RBT_non.setActionCommand("non");
                   RBT_oui.setActionCommand("oui");
                   ButtonGroup group = new ButtonGroup();
                   group.add(RBT_oui);
                   group.add(RBT_non);

                   System.out.println(group.getSelection().getActionCommand());





               }catch (NumberFormatException nfe){
                   System.out.println("le nombre n'est pas au bon format");
               }

           ((Clients) societe).setChiffre_affaire(Chiffre_affaire);((Clients) societe).setNbr_employes(nbr_employe);
           //Clients mon_client = new Clients(raison,num_rue,nom_rue,code_p,ville,email,tel,comments,
                  // Chiffre_affaire,nbr_employe);

           //on l' ajoute à la liste
          // ma_liste.add(mon_client);
       }else if(societe instanceof Prospects) {
           try {
               LocalDate localDate=LocalDate.parse(TFD_date.getText());
               DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
               String date_formate=dateTimeFormatter.format(localDate);


               ((Prospects) societe).setProspect_date(date_formate);
           }catch (DateTimeParseException dateTimeParseException){

           }
          //((Prospects) societe).setProspect_interesse();


          // ((Prospects) societe).setProspect_date();
       }
    //et on crée le nouvel objet








    }

    public void suppression(Societe societe, Utilitaires.TYPESOCIETE typesociete){
        int reponse = 0;
        String societe_nom;
        if (typesociete== Utilitaires.TYPESOCIETE.CLIENTS) {
            societe_nom="Client";
        }else {
            societe_nom="Prospect";
        }
        reponse = JOptionPane.showConfirmDialog(null, "etes vous sur de vouloir" +
                " supprimer ce "+ societe_nom);
        //si la réponse est oui
        if (reponse == JOptionPane.YES_OPTION) {
            Societe st = null;
            ArrayList liste = null;
            if (typesociete == Utilitaires.TYPESOCIETE.CLIENTS){
                liste= List_clients.getMa_liste();
            }else {
                liste= List_prospects.getMaliste_prospects();
            }


            String result = societe.toString().replaceAll("\\s+", "");
            //on va chercher notre liste

            for (Object clients : liste) {
                String resultat = clients.toString().replaceAll("\\s+", "");
                //on recherche une correspondance  avec notre societe
                if (resultat.equals(result)) {

                    st = (Societe) clients;

                }


            }

            //on enleve l'objet de notre liste
            liste.remove(st);
            //on indique l'objet a été supprimé de la liste
            JOptionPane.showMessageDialog(null, societe_nom+" bien suprimmé");

            //on ferme la fenetre  et on reinitialise la page d' accueil avec notre liste mise à jour
            dispose();
            Accueil accueil = null;
            try {
                accueil = new Accueil();
            } catch (Exception_entites ex) {
                ex.printStackTrace();
            }
            accueil.setVisible(true);
            accueil.pack();

        }
    }
}
