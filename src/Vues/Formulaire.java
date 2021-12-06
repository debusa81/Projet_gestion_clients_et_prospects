
package Vues;
import Exceptions.Exception_entites;
import entites.*;
import utilitaires.Utilitaires;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
/**
 * Classe Formulaire  qui permet de créer tous les types de formulaires
 * @author Alexandre Debus
 * @version 4.1.0
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
    //constructeur de mon formulaire
    /**
     * @param action
     * @param societe
     * @param typesociete
     *
     */
    public Formulaire(Utilitaires.ACTION action, Societe societe,Utilitaires.TYPESOCIETE typesociete)  {
        setContentPane(contentPane);
        //on rend l' id impossible à modifier
        TFD_id.setEnabled(false);
        TFD_id.setText(String.valueOf(societe.getId()));
        //maj des éléments selon l' action
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
                if (societe instanceof Clients)
                {
                    TFD_id.setText(String.valueOf(societe.getId()));
                }else if (societe instanceof Prospects){
                    TFD_id.setText(String.valueOf(societe.getId()));
                }
                break;
            //si il s' agit d'une suppression on modifie le titre de la
            // fenetre et le texte sur le bouton d'action
            case SUPRESSION:
                LAB_formulaire.setText("Suppression d'un Client");
                //on remplis les champs avec les données existante
                remplir_champs(societe);
                BTN_action.setText("supprimer client");
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
                RBT_oui.setEnabled(false);
                RBT_non.setEnabled(false);
                if (societe instanceof Prospects)
                {
                    BTN_action.setText("suppression d'un prospect");
                    LAB_formulaire.setText("Suppression  de prospect");
                }
                break;
            //dans le cas d' une modificatin  on change le titre de la fenetre  et le texte du bouton d' action
            case MODIFICATION:
                TFD_id.setText(String.valueOf(societe.getId()));
                if (typesociete== Utilitaires.TYPESOCIETE.CLIENTS){
                    LAB_formulaire.setText("Modification d' un client");
                    BTN_action.setText("modification  d' un client");
                }else {
                    LAB_formulaire.setText("Modification d' un Prospect");
                    BTN_action.setText("modification  d' un Prospect");
                }
                remplir_champs(societe);
            default:
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
                Accueil  accueil = new Accueil();
                accueil.setVisible(true);
                accueil.pack();
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
                        ArrayList liste;
                        if (typesociete== Utilitaires.TYPESOCIETE.CLIENTS){
                            liste= List_clients.getMa_liste_clients();
                        }else {
                            liste=List_prospects.getMaliste_prospects();
                        }
                        int reponse= JOptionPane.showConfirmDialog(null,
                                "Etes vous sur de vouloir" +
                                        " supprimer un" +typesociete+"?");
                        if (reponse==0)
                        {
                            liste.remove(societe);
                        }
                        dispose();
                        Accueil accueil = new Accueil();
                        accueil.setVisible(true);
                        accueil.pack();
                        break;
                    case CREATION:
                            verification_champs(societe);
                        break;
                    //dans le cas d'une modification
                    case MODIFICATION :
                        try {
                            societe.setRaison_sociale(TFD_raison_sociale.getText());
                            societe.setNumero_rue(TFD_numrue.getText());
                            societe.setNom_rue(TFD_nomrue.getText());
                            societe.setVille(TFD_ville.getText());
                            societe.setCode_postal(TFD_codepostal.getText());
                            societe.setEmail(TFD_email.getText());
                            societe.setNum_tel(TFD_telephone.getText());
                            societe.setComments(TAE_Comment.getText());
                            if (societe instanceof Clients){
                                ((Clients) societe).setChiffre_affaire(Double
                                        .parseDouble(TFD_chiffreaffaire.getText()));
                                ((Clients) societe).
                                        setNbr_employes(Integer.
                                                parseInt(TFD_nbremployes.getText()));
                                List_clients.getMa_liste_clients().set(List_clients.
                                        getMa_liste_clients()
                                        .indexOf(societe), (Clients) societe);
                            }else if (societe instanceof Prospects){
                                ((Prospects) societe).setProspect_date(LocalDate.parse(
                                        TFD_date.getText(),Utilitaires.DATETIMEFORMATTER));
                                if (RBT_oui.isSelected()){
                                    ((Prospects) societe).setProspect_interesse("oui");
                                }else {((Prospects) societe).setProspect_interesse("non");}
                                List_prospects.getMaliste_prospects().
                                        set(List_prospects.getMaliste_prospects().indexOf(societe),
                                                (Prospects) societe);
                            }
                            dispose();
                            JOptionPane.showMessageDialog(null,
                                    "modification bien effectué");
                            Accueil accueil1 = new Accueil();
                            accueil1.setVisible(true);
                            accueil1.pack();
                        }catch (Exception_entites exception_entites){
                            JOptionPane.showMessageDialog
                                    (null,exception_entites.getMessage());
                        }catch (NumberFormatException numberFormatException){
                            JOptionPane.showMessageDialog(null,"le chiffre d'affaire et" +
                                    " le nombre d'employé doivent etre remplis");
                        }
                        catch (DateTimeParseException dateTimeParseException){
                            JOptionPane.showMessageDialog(null,
                                    " la date n'est pas au bon format");
                        }
                        catch (Exception exception){
                            JOptionPane.showMessageDialog(null,"une erreur est subvenue");
                            exception.printStackTrace();
                            System.exit(1);
                        }
                    default:
                }
            }
        });
    }
    /**
     * Méthode qui permet de remplir tous les champs  par défaut quand il
     * s' agit d'une modification ou d' une suppresion
     * @param societe notre societe
     */
    public void remplir_champs(Societe societe)
    {
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
            TFD_id.setText(String.valueOf((societe.getId())));
            TFD_chiffreaffaire.setText(String.valueOf(((Clients) societe).getChiffre_affaire()));
            TFD_nbremployes.setText(String.valueOf(((Clients) societe).getNbr_employes()));
            //ainsi que ceux de prospects
        }else if(societe instanceof Prospects)
        {
            TFD_id.setText(String.valueOf(societe.getId()));
            System.out.println(((Prospects) societe).getProspect_interesse());
            String date_prospect=((Prospects) societe).
                    getProspect_date().format(Utilitaires.DATETIMEFORMATTER);
            TFD_date.setText(date_prospect);
            if (((Prospects) societe).getProspect_interesse()=="oui")
            {
                RBT_oui.setSelected(true);
            }else
            {
                RBT_non.setSelected(true);
            }
        }
    }
    /**
     * Méthode qui permet la vérification de tous les champs rentrés  ainsi que la création d'une société
     * (Clients ou prospects)
     * @param societe  notre sociéte
     *
     */
    public void verification_champs(Societe societe)
    {
        String raison= TFD_raison_sociale.getText();
        String num_rue=TFD_numrue.getText();
        String nom_rue=TFD_nomrue.getText();
        String ville = TFD_ville.getText();
        String code_p= TFD_codepostal.getText();
        String tel = TFD_telephone.getText();
        String email = TFD_email.getText();
        String comments= TAE_Comment.getText();
        try {
            societe.setRaison_sociale(TFD_raison_sociale.getText());
            societe.setNumero_rue(TFD_numrue.getText());
            societe.setNom_rue(TFD_nomrue.getText());
            societe.setVille(TFD_ville.getText());
            societe.setCode_postal(TFD_codepostal.getText());
            societe.setNum_tel(TFD_telephone.getText());
            societe.setEmail(TFD_email.getText());
            societe.setComments(TAE_Comment.getText());
            if (societe instanceof Clients)
            {
                ((Clients) societe).setNbr_employes(Integer.parseInt(TFD_nbremployes.getText()));
                ((Clients) societe).setChiffre_affaire(Double.parseDouble(TFD_chiffreaffaire.getText()));
                Clients mon_client = new Clients(raison,num_rue,nom_rue,code_p,ville,email,tel,comments,
                        Double.parseDouble(TFD_chiffreaffaire.getText()),
                        Integer.parseInt(TFD_nbremployes.getText()));
                List_clients.getMa_liste_clients().add(mon_client);
            }
            else if (societe instanceof Prospects)
            {
                LocalDate localDate=LocalDate.
                        parse(TFD_date.getText(),Utilitaires.DATETIMEFORMATTER);
                ((Prospects) societe).setProspect_date(localDate);
                String interesse = null;
                if (RBT_oui.isSelected())
                {
                    interesse="oui";
                }else if (RBT_non.isSelected())
                {
                    interesse="non";
                }
                ((Prospects) societe).setProspect_interesse(interesse);
                Prospects mon_prospect = new Prospects(raison,num_rue,nom_rue,code_p,
                        ville,email,tel,comments,
                        LocalDate.parse(TFD_date.getText(),Utilitaires.DATETIMEFORMATTER),interesse);
                List_prospects.getMaliste_prospects().add(mon_prospect);
            }
            dispose();
            Accueil accueil1 = new Accueil();
            accueil1.setVisible(true);
            accueil1.pack();
            JOptionPane.showMessageDialog(null,"Ajout" +
                    " bien effectué");
        }catch(Exception_entites exception_entites) {
            JOptionPane.showMessageDialog(null, exception_entites.getMessage());
        }
        catch (DateTimeParseException|ParseException dateTimeParseException){
            JOptionPane.showMessageDialog(null,
                    "la date n'est pas au bon format dd/mm/yyyy");
        }catch (NumberFormatException numberFormatException){
            JOptionPane.showMessageDialog(null,"le chiffre d' affaire ou  le nombre d'employé" +
                    " doivent etre des nombres");
        }
        catch (Exception exception){
            JOptionPane.showMessageDialog(null,"une erreur est survenue");
            exception.printStackTrace();
            System.exit(1);
        }
    }
}
