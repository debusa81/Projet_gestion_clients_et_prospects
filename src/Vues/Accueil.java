package Vues;

import Exceptions.Exception_entites;
import com.company.Main;
import entites.*;
import utilitaires.Utilitaires;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**Classe Accueil qui crée l' accueil de notre applicatif
 * @author Alexandre Debus
 * @version 1
 */

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
    private JPanel PAN_ajout_mod;
    private JPanel PAN_confirm_combo;
    Utilitaires.TYPESOCIETE typesociete;
    Utilitaires.ACTION  action;


    public Accueil()throws Exception_entites
    {
        setContentPane(contentPane);
        //on cache les éléments
        PAN_ajout_mod.setVisible(false);
        PAN_confirm_combo.setVisible(false);
        BTN_accueil.setVisible(false);


        //on définit les préférence de dimensions
        this.setPreferredSize(new Dimension(400,400));

        //on quitte l' application quand on clique sur la croix
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //au click sur le bouton cliquer
        BTN_quitter.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                dispose();
            }
        });
        //au clique sur le bouton clients


        //au click du bouton prospects
        BTN_prospects.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                super.mouseClicked(e);
                BTN_prospects.setVisible(false);
                BTN_clients.setVisible(false);
                LAB_choix.setVisible(false);
                LAB_bienvenue.setText("Bienvenue sur la liste des prospects");
                PAN_ajout_mod.setVisible(true);
                BTN_accueil.setVisible(true);
                typesociete= Utilitaires.TYPESOCIETE.PROSPECTS;
            }
        });


        //click au bouton modifier
        BTN_modifier.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                CBX_societe.removeAllItems();
                PAN_confirm_combo.setVisible(true);

                ArrayList test = null;
                    //on vérifie bien qu'il s'agit un client

                    if (typesociete== Utilitaires.TYPESOCIETE.CLIENTS)
                    {
                        ArrayList<Clients> Liste_clients= null;
                        Liste_clients = List_clients.getMa_liste_clients();
                        for (Clients s: Liste_clients)
                        {
                            //on ajoute toutes les raisons sociales au combobox
                            CBX_societe.addItem(s.getRaison_sociale());

                        }

                    }else
                    {
                        ArrayList<Prospects> man;
                        man=List_prospects.getMaliste_prospects();
                        for (Prospects prospects:man)
                        {
                            CBX_societe.addItem(prospects.getRaison_sociale());
                        }

                    }
                //on rend visible le bouton de confirmation
                     BTN_confirm.setVisible(true);
                    //on indique qu'il s'agit d' une modification
                    action= Utilitaires.ACTION.MODIFICATION;
            }
        });
        //au click du bouton afficher
        BTN_afficher.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);

                //on crée  un nouvel affichage
                Affichage aff = null;
                try
                {

                    aff = new Affichage(typesociete);
                } catch (Exception_entites ex)
                        {
                            JOptionPane.showMessageDialog(null,"il y a une erreur de saisie");

                        }
                aff.setVisible(true);
                aff.pack();
                //on ferme la fenetre
                dispose();



            }
        });
        //au click du bouton ajouter
        BTN_ajouter.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                //on affecte qu'il s'agit d'une création
                action= Utilitaires.ACTION.CREATION;
                //on ferme la fenetre
                dispose();
                //et on crée un nouveau formulaire
                Formulaire form = null;
                //si il s'agit d'un client
                if (typesociete == Utilitaires.TYPESOCIETE.CLIENTS)
                {
                    try
                    {
                        form = new Formulaire(action, new Clients(),typesociete);
                    } catch (Exception_entites ex)
                    {
                        JOptionPane.showMessageDialog(null,"il y a eu un soucis au niveau " +
                                "de la création du formulaire ");

                    }
                }else {
                    try {
                        form = new Formulaire(action,new Prospects(),typesociete);
                        } catch (Exception_entites ex)
                            {
                           JOptionPane.showMessageDialog(null,"il y a eu un soucis au" +
                                   " niveau de la création du formulaire ");
                            }
                      }


                        form.setVisible(true);
                        form.pack();
                        dispose();

            }
        });
        //au click sur le bouton supprimer
        BTN_suprimmer.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                //on rend la combobox visible
                PAN_confirm_combo.setVisible(true);
                //on verifie qu' au démarrage notre combobox est vide
                CBX_societe.removeAllItems();

                CBX_societe.setVisible(true);
                BTN_confirm.setVisible(true);
                //on indique qu'il s'agit d' une supression
                action= Utilitaires.ACTION.SUPRESSION;
                //on affiche nos listes pour clients et prospects
                if (typesociete== Utilitaires.TYPESOCIETE.CLIENTS)
                {

                    for (int i = 0; i < List_clients.getMa_liste_clients().size(); i++)
                    {
                        CBX_societe.addItem(List_clients.getMa_liste_clients().get(i).getRaison_sociale());

                    }
                }else
                {
                    for (int i = 0; i < List_prospects.getMaliste_prospects().size(); i++)
                    {
                        CBX_societe.addItem(List_prospects.getMaliste_prospects().get(i).getRaison_sociale());
                    }
                }
            }


        });
        BTN_confirm.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);

                if (typesociete== Utilitaires.TYPESOCIETE.CLIENTS)
                {
                    //on compare si la raison sociale choisie est présente dans la liste

                    for (int i = 0; i <List_clients.getMa_liste_clients().size() ; i++)
                    {

                        if (CBX_societe.getSelectedItem().toString()==List_clients.getMa_liste_clients().get(i).
                                getRaison_sociale())
                        {
                            Societe societe= List_clients.getMa_liste_clients().get(i);
                            //on crée alors le formulaire
                            try {
                                Formulaire formulaire= new Formulaire(action,societe,typesociete);
                                formulaire.setVisible(true);
                                formulaire.pack();
                            } catch (Exception_entites ex) {
                                JOptionPane.showMessageDialog(null,"Il y a eu un" +
                                        " soucis au niveau " +
                                        "de la création du formulaire");
                            }
                        }
                    }
                }else
                {
                    //on compare pour la liste de prospects
                    for (int i = 0; i <List_prospects.getMaliste_prospects().size() ; i++)
                    {

                    if (Objects.equals(CBX_societe.getSelectedItem().toString(), List_prospects.getMaliste_prospects().
                            get(i).
                            getRaison_sociale()))
                        {

                             Societe societe = List_prospects.getMaliste_prospects().get(i);
                            //création du formulaire
                            try
                            {
                                Formulaire  formulaire = new Formulaire(action,societe,typesociete);
                                formulaire.setVisible(true);
                                formulaire.pack();
                            } catch (Exception_entites ex)
                            {
                                ex.printStackTrace();
                            }
                        }

                    }
                }
                //on ferme la fenetre
                dispose();
            }
        });



            //au click du bouton accueil
        BTN_accueil.addMouseListener(new MouseAdapter()
        {
            //on ferme la fenetre et on réinitialise au départ la page d' accueil
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                dispose();
                Accueil ac = null;
                try
                {
                    ac = new Accueil();
                } catch (Exception_entites ex) {
                   JOptionPane.showMessageDialog(null,"impossible de retourner à l' accueil");
                }
                ac.setVisible(true);
                ac.pack();
            }
        });


        BTN_clients.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);

                //on cache et on montre les élements dont on a besoin
                BTN_clients.setVisible(false);
                BTN_prospects.setVisible(false);
                LAB_choix.setVisible(false);
                LAB_bienvenue.setText("Bienvenue sur la liste des clients ");
                PAN_ajout_mod.setVisible(true);
                BTN_accueil.setVisible(true);
                 typesociete= Utilitaires.TYPESOCIETE.CLIENTS;

            }
        });

    }

}
