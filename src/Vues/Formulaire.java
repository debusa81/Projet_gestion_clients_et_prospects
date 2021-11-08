package Vues;

import Exceptions.MonException;
import entites.Clients;
import entites.List_clients;
import entites.Societe;
import utilitaires.Utilitaires;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.Callable;

public class Formulaire extends JFrame {
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
    private JButton buttonOK;

    public Formulaire(Utilitaires.ACTION action, Societe societe,List liste) throws MonException {
        setContentPane(contentPane);

        getRootPane().setDefaultButton(buttonOK);
        TFD_id.setEnabled(false);
        List ma_liste = null;

        switch (action){
            case CREATION:
                if (societe instanceof Clients){
                    TFD_id.setText(String.valueOf(((Clients) societe).getId_clients()));

                }




                break;
            case SUPRESSION:
                LAB_formulaire.setText("Suppression d'un Client");
                remplir_champs(societe);
                BTN_action.setText("suprimmer client");
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


                break;
            case MODIFICATION:
                LAB_formulaire.setText("Modification d' un client");
                remplir_champs(societe);
                BTN_action.setText("modification  d' un client");


                break;
        }


        quitterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
        accueilButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                Accueil ac= new Accueil(ma_liste);
                ac.setVisible(true);
                ac.pack();
            }

        });


        BTN_action.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (action){
                    case SUPRESSION:
                        int reponse =JOptionPane.showConfirmDialog(null,"etes vous sur de vouloir" +
                                " supprimer ce client ");
                        if (reponse==JOptionPane.YES_OPTION){
                        Societe st = null;
                           // System.out.println(liste.get(1));
                            //System.out.println(liste.get(0));
                            //System.out.println(liste.get(2));
                            //System.out.println(liste.get(2)==societe);
                            String result = societe.toString().replaceAll("\\s+","");
                            //System.out.println(result);
                            for (Object clients :liste){
                                String resultat= clients.toString().replaceAll("\\s+","");
                               // System.out.println(result.equals(resultat));
                                if (resultat.equals(result)){
                                   // int index = liste.indexOf(resultat);
                                    //System.out.println(index);
                                    //System.out.println(clients);
                                     st = (Societe) clients;
                                    //liste.remove(clients);
                                    //System.out.println(liste);
                                }



                            }
                            System.out.println(st);
                            liste.remove(st);
                            JOptionPane.showMessageDialog(null,"Client bien suprimmé");


                            dispose();
                            Accueil accueil = new Accueil(liste);
                            accueil.setVisible(true);
                            accueil.pack();

                        }



                        break;
                    case CREATION:
                        try {

                            verification_champs(societe,liste);
                            JOptionPane.showMessageDialog(null,"Client bien ajouté");
                            dispose();
                            Accueil accueil = new Accueil(liste);
                            accueil.setVisible(true);
                            accueil.pack();

                        } catch (MonException ex) {
                            ex.printStackTrace();
                        }


                     break;
                }
            }
        });



    }

    public void remplir_champs(Societe societe){

        TFD_raison_sociale.setText(societe.getRaison_sociale());
        TFD_numrue.setText(societe.getNumero_rue());
        TFD_nomrue.setText(societe.getNom_rue());
        TFD_codepostal.setText(societe.getCode_postal());
        TFD_ville.setText(societe.getVille());
        TFD_telephone.setText(societe.getNum_tel());
        TFD_email.setText(societe.getEmail());
        //TFD_chiffreaffaire.setText(String.valueOf(societe.ge));

        if (societe instanceof Clients){
            TFD_id.setText(String.valueOf(((Clients) societe).getId_clients()));
            TFD_chiffreaffaire.setText(String.valueOf(((Clients) societe).getChiffre_affaire()));
            TFD_nbremployes.setText(String.valueOf(((Clients) societe).getNbr_employes()));
        }else {
            System.out.println("je suis une instance de prospect");
        }
       // TFD_nbremployes.setText(String.valueOf(societe.getNbr_employes()));
        TAE_Comment.setText(societe.getComments());
    }

    public void verification_champs(Societe societe,List ma_liste) throws MonException {
       String raison= TFD_raison_sociale.getText();
       String num_rue=TFD_numrue.getText();
       String nom_rue=TFD_nomrue.getText();
       String ville = TFD_ville.getText();
       String code_p= TFD_codepostal.getText();
       String tel = TFD_telephone.getText();
       String email = TFD_email.getText();
       String comments= TAE_Comment.getText();
       double Chiffre_affaire=0;
       int nbr_employe=0;
       try {
            Chiffre_affaire= Double.parseDouble(TFD_chiffreaffaire.getText());
            nbr_employe = Integer.parseInt(TFD_nbremployes.getText());

       }catch (NumberFormatException nfe){
           System.out.println("le nombre n'est pas au bon format");
       }

        try {
            societe.setRaison_sociale(raison);societe.setNumero_rue(num_rue);societe.setNom_rue(nom_rue);
            societe.setVille(ville);societe.setCode_postal(code_p);societe.setNum_tel(tel);societe.setEmail(email);
            societe.setComments(comments);
        }catch (MonException me){}

       if (societe instanceof Clients){
           ((Clients) societe).setChiffre_affaire(Chiffre_affaire);((Clients) societe).setNbr_employes(nbr_employe);
       }


      Clients mon_client = new Clients(raison,num_rue,nom_rue,code_p,ville,email,tel,comments,
              Chiffre_affaire,nbr_employe);
       ma_liste.add(mon_client);
        System.out.println(ma_liste);





    }
}
