package Vues;

import entites.Clients;
import utilitaires.Utilitaires;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private JButton ajouterClientButton;
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

    public Formulaire(Utilitaires.ACTION action, Clients societe) {
        setContentPane(contentPane);

        getRootPane().setDefaultButton(buttonOK);
        TFD_id.setEnabled(false);
        TFD_id.setBackground(Color.gray);

        switch (action){
            case CREATION:
                break;
            case SUPRESSION:
                LAB_formulaire.setText("Suppression d'un Client");
                break;
            case MODIFICATION:
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
                Accueil ac= new Accueil();
                ac.setVisible(true);
                ac.pack();
            }

        });
        System.out.println(societe.getId_clients());
        TFD_id.setText(String.valueOf(societe.getId_clients()));
        TFD_raison_sociale.setText(societe.getRaison_sociale());
        TFD_numrue.setText(societe.getNumero_rue());
        TFD_nomrue.setText(societe.getNom_rue());
        TFD_codepostal.setText(societe.getCode_postal());
        TFD_ville.setText(societe.getVille());
        TFD_telephone.setText(societe.getNum_tel());
        TFD_email.setText(societe.getEmail());
        TFD_chiffreaffaire.setText(String.valueOf(societe.getChiffre_affaire()));
        TFD_nbremployes.setText(String.valueOf(societe.getNbr_employes()));

    }
}
