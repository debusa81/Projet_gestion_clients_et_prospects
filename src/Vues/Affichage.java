package Vues;

import Exceptions.Exception_entites;
import com.company.Main;
import entites.Clients;
import entites.List_clients;
import entites.List_prospects;
import entites.Prospects;
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

/**
 * @author Debus Alexandre
 * @version 1
 */
public class Affichage extends JFrame {
    private JPanel contentPane;
    private JTable table1;
    private JScrollPane Jpanel_scroll;
    private JButton accueilButton;
    private JButton quitterButton;


    public Affichage(Utilitaires.TYPESOCIETE typesociete) throws Exception_entites
    {

        setContentPane(contentPane);
        ;

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800,500));
        DefaultTableModel defaultTableModel= new DefaultTableModel();
        //si il s'agit d'un client
        if (typesociete== Utilitaires.TYPESOCIETE.CLIENTS)
        {
            //on crée le model de notre jtable
            String []  test={"raison","Adresse","Contact","chiffre d'affaire","nombre d'employe"};
            defaultTableModel.setColumnIdentifiers(test);
            table1.setModel(defaultTableModel);
            Object [] data=new Object[5];
            //pour chaque ligne du tableau on rentre chaque données dans la colonne correspondante
            for (Clients cl : List_clients.getMa_liste())
            {
                data[0]=cl.getRaison_sociale();
                data[1]=cl.getNum_tel()+" "+cl.getNom_rue()+" "+cl.getCode_postal()+" "+cl.getVille();
                data[2]=cl.getEmail()+ " "+cl.getNum_tel();
                data[3]=cl.getChiffre_affaire();
                data[4]=cl.getNbr_employes();
                defaultTableModel.addRow(data);
                System.out.println(cl.getId());
            }
            //si il s'agit d' un prospect
        }else if (typesociete== Utilitaires.TYPESOCIETE.PROSPECTS)
        {
            //on crée le model
            String []  test={"raison","Adresse","Contact","date de prospection","interessé"};
            defaultTableModel.setColumnIdentifiers(test);
            table1.setModel(defaultTableModel);
            Object [] data=new Object[5];
            //pour chaque donnée on remplit le tableau
            for (Prospects prospects:List_prospects.getMaliste_prospects())
            {
                data[0]=prospects.getRaison_sociale();
                data[1]=prospects.getNumero_rue()+" "+prospects.getNom_rue()+" "+prospects.getCode_postal()+
                        " "+prospects.getVille();
                data[2]=prospects.getEmail()+" "+prospects.getNum_tel();
               DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
                System.out.println(prospects.getProspect_date().format(dateTimeFormatter));
                data[3]= prospects.getProspect_date().format(dateTimeFormatter);
               //si la valeur de prospect interessé  est ègal à 1 ou 0
                if (prospects.getProspect_interesse()==1)
                {
                    data[4]="oui";
                }else
                {
                    data[4]="non";
                }

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
    }
}
