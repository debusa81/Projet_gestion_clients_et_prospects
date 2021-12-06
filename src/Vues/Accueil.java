package Vues;
import entites.*;
import utilitaires.Utilitaires;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
/**Classe Accueil qui crée l' accueil de notre applicatif
 * @author Alexandre Debus
 * @version 4.1.0
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
    public Accueil()
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
                remplir_combo();
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
                aff = new Affichage(typesociete);
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
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                action = Utilitaires.ACTION.CREATION;
                dispose();
                Formulaire formulaire;
                if (typesociete== Utilitaires.TYPESOCIETE.CLIENTS){
                    formulaire= new Formulaire(action,new Clients(),typesociete);
                }else { formulaire = new Formulaire(action,new Prospects(),typesociete);}
                formulaire.setVisible(true);
                formulaire.pack();
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
                remplir_combo();
                action= Utilitaires.ACTION.SUPRESSION;
            }
        });
        BTN_confirm.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                if (CBX_societe.getItemCount()==0){
                    JOptionPane.showMessageDialog(null,"vous" +
                            " ne pouvez plus modifier ou supprimer car il n' y a plus d'objet dans la liste");
                    Accueil accueil = new Accueil();
                    accueil.setVisible(true);
                    accueil.pack();
                }
                creer_formulaire();
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
                Accueil  monaccueil = new Accueil();
                monaccueil.setVisible(true);
                monaccueil.pack();
            }
        });
        //au click du bouton clients
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

    /**methode remplir combo qui permet de remplir ma combobox
     *
     */
    public void remplir_combo(){
        if (typesociete== Utilitaires.TYPESOCIETE.CLIENTS){
            for (Clients clients:List_clients.getMa_liste_clients()) {
                CBX_societe.addItem(clients.getRaison_sociale());
            }
        }else {
            for (Prospects prospects:List_prospects.getMaliste_prospects()){
                CBX_societe.addItem(prospects.getRaison_sociale());
            }
        }
    }

    /**méthode creer formulaire  qui permet de générer un formulaire 
     *
     */
    public void creer_formulaire(){
        if (typesociete== Utilitaires.TYPESOCIETE.CLIENTS){
            for (Clients clients:List_clients.getMa_liste_clients()){
                if (CBX_societe.getSelectedItem().toString()==clients.getRaison_sociale()){
                    Formulaire formulaire= new Formulaire(action,clients,typesociete);
                    formulaire.setVisible(true);
                    formulaire.pack();
                }
            }
        }else {
            for (Prospects prospects:List_prospects.getMaliste_prospects()){
                if (CBX_societe.getSelectedItem().toString()==prospects.getRaison_sociale()){
                    Formulaire formulaire = new Formulaire(action,prospects,typesociete);
                    formulaire.setVisible(true);
                    formulaire.pack();
                }
            }
        }
    }
}
