package Vues;
import Exceptions.Exception_entites;
import com.company.Main;
import entites.*;
import utilitaires.Utilitaires;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
/**Classe affichage qui permet d'afficher toutes les informations dans un tableau
 * @author Debus Alexandre
 * @version 4.1.0
 */
public class Affichage extends JFrame {
    private JPanel contentPane;
    private JTable table1;
    private JScrollPane Jpanel_scroll;
    private JButton accueilButton;
    private JButton quitterButton;
    private JButton modifierButton;
    private JButton supprimerButton;
    private JButton BTN_ecrire;
    public Affichage(Utilitaires.TYPESOCIETE typesociete)
    {
        setContentPane(contentPane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(800,500));
        remplir_tableau(table1,typesociete);
        modifierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table1.getSelectedRow();
                try {
                    Formulaire formulaire = null;
                    if (typesociete== Utilitaires.TYPESOCIETE.CLIENTS){
                        formulaire = new Formulaire(Utilitaires.ACTION.MODIFICATION,
                                List_clients.getMa_liste_clients().get(row),typesociete);
                    }else {
                        formulaire = new Formulaire(Utilitaires.ACTION.MODIFICATION,
                                List_prospects.getMaliste_prospects().get(row),typesociete);
                    }
                    formulaire.setVisible(true);
                    formulaire.pack();
                    dispose();
                }catch (IndexOutOfBoundsException indexOutOfBoundsException){
                    JOptionPane.showMessageDialog(null,"vous devez selectionner une ligne ");
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"une erreur est survenue");
                    exception.printStackTrace();
                    System.exit(1);
                }
            }
        });
        supprimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row =table1.getSelectedRow();
                    int reponse=JOptionPane.showConfirmDialog(null,"etes vous sur de" +
                            " vouloir supprimer cet élément ?");
                    if (reponse==0){
                        if (typesociete == Utilitaires.TYPESOCIETE.CLIENTS){
                            List_clients.getMa_liste_clients().remove(List_clients.getMa_liste_clients().get(row));
                        }else{
                            List_prospects.getMaliste_prospects().
                                    remove(List_prospects.getMaliste_prospects().get(row));
                        }

                    }
                }catch (IndexOutOfBoundsException indexOutOfBoundsException){
                    JOptionPane.showMessageDialog(null,"vous devez selectionner un ligne ");
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"une erreur est survenue");
                    exception.printStackTrace();
                    System.exit(1);
                }
                dispose();
                Accueil accueil = new Accueil();
                accueil.setVisible(true);
                accueil.pack();
            }
        });
        quitterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        accueilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Accueil accueil = new Accueil();
                accueil.setVisible(true);
                accueil.pack();
            }
        });
        //creation  d'un fichier texte pour écrire les données à l'interieur
        BTN_ecrire.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList liste;
                    FileWriter out = null;
                    File file;
                    if (typesociete== Utilitaires.TYPESOCIETE.CLIENTS){
                        liste=List_clients.getMa_liste_clients();
                        out = new FileWriter("clients.txt");
                        file = new File("clients.txt");
                    }else{
                        liste=List_prospects.getMaliste_prospects();
                        out= new FileWriter("prospects.txt");
                        file= new File("prospects.txt");
                    }
                    for (int i = 0; i <liste.size() ; i++) {
                        out.write(String.valueOf(liste.get(i)));
                        out.write("\n");
                    }
                    out.close();
                    JOptionPane.showMessageDialog(null,
                            "la liste a bien été écrite dans le fichier");
                    Desktop d = Desktop.getDesktop();
                    d.open(file);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                catch (Exception exception){
                    JOptionPane.showMessageDialog(null,"une erreur est survenue");
                    exception.printStackTrace();
                    System.exit(1);
                }
            }
        });
    }
    public void remplir_tableau(JTable table,Utilitaires.TYPESOCIETE typesociete){
        DefaultTableModel defaultTableModel= new DefaultTableModel();
        if (typesociete== Utilitaires.TYPESOCIETE.CLIENTS)
        {
            //on crée le model de notre jtable
            String []  test={"id","raison","Adresse","Contact","chiffre d'affaire","nombre d'employe"};
            defaultTableModel.setColumnIdentifiers(test);
            table1.setModel(defaultTableModel);
            Object [] data=new Object[6];
            //pour chaque ligne du tableau on rentre chaque données dans la colonne correspondante
            for (Clients clients : List_clients.getMa_liste_clients())
            {
                data[0]=clients.getId();
                data[1]=clients.getRaison_sociale();
                data[2]=clients.getNumero_rue()+" "+clients.getNom_rue()+" "+clients.getCode_postal()+" "+
                        clients.getVille();
                data[3]=clients.getEmail()+ " "+clients.getNum_tel();
                //si il s'agit d'un entier  format #.##
                if ((clients.getChiffre_affaire())== (int)clients.getChiffre_affaire()){
                    data[4]=clients.getChiffre_affaire()+"0";
                }else {
                    data[4]=Utilitaires.DF.format(clients.getChiffre_affaire());
                }
                data[5]=clients.getNbr_employes();
                defaultTableModel.addRow(data);
            }
            //si il s'agit d' un prospect
        }else if (typesociete== Utilitaires.TYPESOCIETE.PROSPECTS) {
            //on crée le model
            String[] test = {"id", "raison", "Adresse", "Contact", "date de prospection", "interessé"};
            defaultTableModel.setColumnIdentifiers(test);
            table1.setModel(defaultTableModel);
            Object[] data = new Object[6];
            //pour chaque donnée on remplit le tableau
            for (Prospects prospects : List_prospects.getMaliste_prospects()) {
                data[0] = prospects.getId();
                data[1] = prospects.getRaison_sociale();
                data[2] = prospects.getNumero_rue() + " " + prospects.getNom_rue() + " " + prospects.getCode_postal() +
                        " " + prospects.getVille();
                data[3] = prospects.getEmail() + " " + prospects.getNum_tel();
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                data[4] = prospects.getProspect_date().format(dateTimeFormatter);
                //si la valeur de prospect interessé  est ègal à 1 ou 0
                data[5] = prospects.getProspect_interesse();
                defaultTableModel.addRow(data);
            }
        }
    }
}
