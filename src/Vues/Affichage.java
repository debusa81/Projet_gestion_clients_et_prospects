package Vues;

import Exceptions.Exception_entites;
import com.company.Main;
import entites.*;
import utilitaires.Utilitaires;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**Classe affichage qui permet d' afficher toutes les informations dans un tableau
 * @author Debus Alexandre
 * @version 1
 */
public class Affichage extends JFrame {
    private JPanel contentPane;
    private JTable table1;
    private JScrollPane Jpanel_scroll;
    private JButton accueilButton;
    private JButton quitterButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    public Affichage(Utilitaires.TYPESOCIETE typesociete) throws Exception_entites
    {
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800,500));
        DefaultTableModel defaultTableModel= new DefaultTableModel();
        //si il s'agit d'un client
        if (typesociete== Utilitaires.TYPESOCIETE.CLIENTS)
        {
            //on crée le model de notre jtable
            String []  test={"id","raison","Adresse","Contact","chiffre d'affaire","nombre d'employe"};
            defaultTableModel.setColumnIdentifiers(test);
            table1.setModel(defaultTableModel);
            Object [] data=new Object[6];
            //pour chaque ligne du tableau on rentre chaque données dans la colonne correspondante
            for (Clients cl : List_clients.getMa_liste_clients())
            {
                data[0]=cl.getId();
                data[1]=cl.getRaison_sociale();
                data[2]=cl.getNumero_rue()+" "+cl.getNom_rue()+" "+cl.getCode_postal()+" "+cl.getVille();
                data[3]=cl.getEmail()+ " "+cl.getNum_tel();
                data[4]=cl.getChiffre_affaire();
                data[5]=cl.getNbr_employes();
                defaultTableModel.addRow(data);
                System.out.println(cl.getId());
            }
            //si il s'agit d' un prospect
        }else if (typesociete== Utilitaires.TYPESOCIETE.PROSPECTS)
        {
            //on crée le model
            String []  test={"id","raison","Adresse","Contact","date de prospection","interessé"};
            defaultTableModel.setColumnIdentifiers(test);
            table1.setModel(defaultTableModel);
            Object [] data=new Object[6];
            //pour chaque donnée on remplit le tableau
            for (Prospects prospects:List_prospects.getMaliste_prospects())
            {
                data[0]=prospects.getId();
                data[1]=prospects.getRaison_sociale();
                data[2]=prospects.getNumero_rue()+" "+prospects.getNom_rue()+" "+prospects.getCode_postal()+
                        " "+prospects.getVille();
                data[3]=prospects.getEmail()+" "+prospects.getNum_tel();
               DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                System.out.println(prospects.getProspect_date().format(dateTimeFormatter));
                data[4]= prospects.getProspect_date().format(dateTimeFormatter);
               //si la valeur de prospect interessé  est ègal à 1 ou 0
                System.out.println(prospects.getProspect_interesse());
               data[5]=prospects.getProspect_interesse();
                defaultTableModel.addRow(data);
            }

        }

        //click sur le bouton quitter
        quitterButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                dispose();
            }
        });
        //click sur le bouton accueil
        accueilButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                try
                {
                    //on retourne sur la page accueil
                    Accueil accueil = new Accueil();
                    accueil.setVisible(true);
                    accueil.pack();
                    dispose();
                } catch (Exception_entites ex)
                {
                    ex.printStackTrace();
                }
            }
        });
        modifierButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int column=0;
                String value="";
                int row = table1.getSelectedRow();
                for (column=0;column<5;column++){
                     value=value +" " +table1.getModel().getValueAt(row,column).toString();
                }
                System.out.println(value);

                final String separateur=" ";

                String donnees[]=value.split(separateur);
                System.out.println(Arrays.toString(donnees));
                String raison=donnees[1];
                String num_rue=donnees[2];
                String nom_rue=donnees[3]+" "+donnees[4];
                String Code_postal=donnees[5];
                String ville=donnees[6];
                String email = donnees[7];
                String telephone=donnees[8];
                try {
                    Double chiffre_affaire= Double.valueOf(donnees[9]);
                    int nbr_employe= Integer.parseInt(donnees[10]);
                    Clients clients= new Clients(raison,num_rue,nom_rue,Code_postal,ville,email,telephone,
                            null,chiffre_affaire,nbr_employe);
                    Formulaire formulaire = new Formulaire(Utilitaires.ACTION.MODIFICATION,clients,
                            Utilitaires.TYPESOCIETE.CLIENTS);
                    formulaire.setVisible(true);
                    formulaire.pack();

                } catch (Exception_entites ex) {
                    JOptionPane.showMessageDialog(null,"les données ne conviennents pas ");
                }
                catch (NumberFormatException numberFormatException){
                  JOptionPane.showMessageDialog(null,"Les nombres de chiffre d' affaire et" +
                          " de nombre d'mployé ne sont pas au bon format ");
                }
                dispose();
            }
        });
    }
}
