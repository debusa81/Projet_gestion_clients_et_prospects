package Vues;

import Exceptions.MonException;
import com.company.Main;
import entites.Clients;
import entites.List_clients;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Affichage extends JFrame {
    private JPanel contentPane;
    private JList<String> LST_obj ;
    private JButton buttonOK;
    private JButton buttonCancel;

    public Affichage( List<Clients> ma_liste) throws MonException {
        setContentPane(contentPane);
       ;





        // call onCancel() when cross is clicked
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        System.out.println(ma_liste);









       DefaultListModel model = new DefaultListModel();

        for (Clients c:ma_liste){
            model.addElement(c);
        }
       LST_obj.setModel(model);








    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
