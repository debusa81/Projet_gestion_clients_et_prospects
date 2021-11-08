package Vues;

import Exceptions.MonException;
import com.company.Main;
import entites.Clients;
import entites.List_clients;
import entites.Societe;
import utilitaires.Utilitaires;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Accueil extends JFrame  {
    private JPanel contentPane;
    private JButton BTN_clients;
    private JButton BTN_prospects;
    private JButton BTN_quitter;
    private JLabel LAB_bienvenue;
    private JLabel LAB_choix;
    private JButton BTN_ajouter;
    private JButton BTN_modifier;
    private JButton BTN_suprimmer;
    private JButton BTN_afficher;
    private JComboBox CBX_societe;
    private JButton BTN_accueil;
    private JButton BTN_confirm;


    public Accueil(List ma_liste) {
        setContentPane(contentPane);


        BTN_ajouter.setVisible(false);
        BTN_afficher.setVisible(false);
        BTN_modifier.setVisible(false);
        BTN_suprimmer.setVisible(false);
        CBX_societe.setVisible(false);
        BTN_accueil.setVisible(false);
        BTN_confirm.setVisible(false);
        final Societe[] soc = new Societe[1];
        final Societe[] val = new Societe[1];


        this.setPreferredSize(new Dimension(400,400));

        // call onCancel() when cross is clicked


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // call onCancel() on ESCAPE

        BTN_quitter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
            }
        });
        BTN_clients.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                BTN_clients.setVisible(false);
                BTN_prospects.setVisible(false);
                LAB_choix.setVisible(false);
                LAB_bienvenue.setText("Bienvenue sur la liste des clients ");
                BTN_ajouter.setVisible(true);
                BTN_afficher.setVisible(true);
                BTN_modifier.setVisible(true);
                BTN_suprimmer.setVisible(true);
                BTN_accueil.setVisible(true);


            }
        });
        BTN_prospects.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                BTN_prospects.setVisible(false);
                BTN_clients.setVisible(false);
                LAB_choix.setVisible(false);
                LAB_bienvenue.setText("Bienvenue sur la liste des prospects");

                BTN_ajouter.setVisible(true);
                BTN_afficher.setVisible(true);
                BTN_modifier.setVisible(true);
                BTN_suprimmer.setVisible(true);
                BTN_accueil.setVisible(true);
            }
        });
        BTN_modifier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                CBX_societe.setVisible(true);

                List<Clients> man= null;

                    man = ma_liste;

                System.out.println(man.get(0));
                System.out.println(man);
                BTN_confirm.setVisible(true);
                for (Clients s: man) {
                    CBX_societe.addItem(s.getRaison_sociale());
                    List<Clients> finalMan = man;
                    BTN_confirm.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            super.mouseClicked(e);
                            if (s.getRaison_sociale()==CBX_societe.getSelectedItem().toString()){
                                System.out.println(s);
                                val[0]=s;
                                Formulaire fr = null;
                                try {
                                    fr = new Formulaire(Utilitaires.ACTION.MODIFICATION,s, ma_liste);
                                } catch (MonException ex) {
                                    ex.printStackTrace();
                                }
                                fr.setVisible(true);
                                fr.pack();

                            }


                        }
                    });



                }


















            }
        });
        BTN_afficher.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(ma_liste);

                Affichage aff = null;
                try {

                    aff = new Affichage(ma_liste);
                } catch (MonException ex) {
                    ex.printStackTrace();
                }
                aff.setVisible(true);
                aff.pack();



            }
        });
        BTN_ajouter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
               Utilitaires.ACTION action= Utilitaires.ACTION.CREATION;

                dispose();
                Formulaire form = null;
                try {
                    form = new Formulaire(action, new Clients(),ma_liste);
                } catch (MonException ex) {
                    ex.printStackTrace();
                }
                form.setVisible(true);
                form.pack();

            }
        });
        BTN_suprimmer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                CBX_societe.setVisible(true);
                List<Clients> man= null;
                try {
                    man = Main.remplir_liste_clients(List_clients.getMa_liste());
                } catch (MonException ex) {
                    ex.printStackTrace();
                }
                System.out.println(man.get(0).getRaison_sociale());
                for (Clients s: man){
                    CBX_societe.addItem(s.getRaison_sociale());

                }
                BTN_confirm.setVisible(true);

                List<Clients> finalMan = man;
                List<Clients> finalMan1 = man;
                BTN_confirm.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        for (Clients clients: finalMan){
                            if (clients.getRaison_sociale()==CBX_societe.getSelectedItem().toString())
                            {

                                Formulaire f = null;
                                try {
                                    f = new Formulaire(Utilitaires.ACTION.SUPRESSION,clients,ma_liste);
                                } catch (MonException ex) {
                                    ex.printStackTrace();
                                }
                                f.setVisible(true);
                                f.pack();

                            }

                        }


                        dispose();

                    }
                });

            }

        });


        BTN_accueil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                dispose();
                Accueil ac = new Accueil(ma_liste);
                ac.setVisible(true);
                ac.pack();
            }
        });

    }


}
