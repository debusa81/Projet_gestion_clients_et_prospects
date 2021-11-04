package Vues;

import javax.swing.*;
import java.awt.*;

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
    private JTextField TFD_;
    private JTextField textField9;
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
    private JButton buttonOK;

    public Formulaire() {
        setContentPane(contentPane);

        getRootPane().setDefaultButton(buttonOK);
        TFD_id.setEnabled(false);
        TFD_id.setBackground(Color.gray);
    }
}
