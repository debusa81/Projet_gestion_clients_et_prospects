package entites;

import Exceptions.MonException;

/**
 * @author Alexandre Debus
 * classe Clients héritant de la classe Societe
 */

public class Clients extends Societe{
    //attribut de classe de la classe client
    private static int id_clients=0;
    //attributs d' instances
    private double chiffre_affaire;
    private int  nbr_employes;

    //constructeur avec deux caractères


    public Clients(double chiffre_affaire, int nbr_employes) {
        this.chiffre_affaire = chiffre_affaire;
        this.nbr_employes = nbr_employes;
    }

    //constructeur implicit héritant de celui de Société
    public Clients(String Raison_sociale, String numero_rue, String Nom_rue, String Code_postal, String ville, String email, String telephone, String commentaires, double chiffre_affaire, int nbr_employes) throws MonException {
        super(Raison_sociale,numero_rue,Nom_rue,Code_postal,ville,email,telephone,commentaires);
        setChiffre_affaire(chiffre_affaire);

        setNbr_employes(nbr_employes);
        id_clients++;
    }
    //getters et setters

    public static int getId_clients() {
        return id_clients;
    }

    public static void setId_clients(int id_clients) {
        Clients.id_clients = id_clients;
    }

    public double getChiffre_affaire() {
        return chiffre_affaire;
    }

    public void setChiffre_affaire(double chiffre_affaire) {
        if (chiffre_affaire<200){
            try {
                throw new MonException("");
            } catch (MonException e) {
                System.out.println("le chiffre d' affaire ne doit pas etre inférieur à 200");
            }

        }
        this.chiffre_affaire = chiffre_affaire;
    }

    public int getNbr_employes() {
        return nbr_employes;
    }

    public void setNbr_employes(int nbr_employes) {
        if (nbr_employes==0){
            try {
                throw  new MonException("");
            } catch (MonException e) {
                System.out.println(" le nombre d' employé doit etre strictement  supérieur à 0");
            }
        }
        this.nbr_employes = nbr_employes;
    }

    /**
     * methode to_string
     * @return chaine de caractère des données de sociéte + celle de Clients
     */
    @Override
    public String toString() {
        return super.toString() +
                "  " + chiffre_affaire +
                " " + nbr_employes
                ;
    }
}
