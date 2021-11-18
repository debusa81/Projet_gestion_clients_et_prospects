package entites;

import Exceptions.Exception_entites;

/**Classe Clients qui hérite de la classe Société ,elle possede pour attribut date de prospection, et interessé
 * @author Alexandre Debus
 *
 */

public class Clients extends Societe
{
    //attribut de classe de la classe client
    private static int id_clients=0;
    //attributs d' instances
    private double chiffre_affaire;
    private int  nbr_employes;

    public Clients(String Raison_sociale, String numero_rue, String Nom_rue, String Code_postal, String ville,
                   String email, String telephone, String commentaires, double chiffre_affaire,
                   int nbr_employes) throws Exception_entites
    {
        super(Raison_sociale,numero_rue,Nom_rue,Code_postal,ville,email,telephone,commentaires);
        setChiffre_affaire(chiffre_affaire);

        setNbr_employes(nbr_employes);
        id_clients=id_clients+1;
        setId(id_clients);


    }
    public Clients(){
        id_clients=id_clients+1;
        setId(id_clients);
    }
    //getters et setters

    public static int getId_clients() {
        return id_clients;
    }

    public double getChiffre_affaire() {
        return chiffre_affaire;
    }

    /**setter qui permet de filtrer si le chiffre d' affaire est au dessus de 200
     *
     * @param chiffre_affaire
     * @throws Exception_entites
     */
    public void setChiffre_affaire(double chiffre_affaire) throws Exception_entites
    {
        //on vérifie que le chiffre d' affaire soit bien supérieur à 200
        if (chiffre_affaire<200)
        {
            //sinon on renvoie une erreur
                throw new Exception_entites("le chiffre d' affaire doit etre supérieur à 200");

        }
        this.chiffre_affaire = chiffre_affaire;
    }

    public int getNbr_employes() {
        return nbr_employes;
    }

    /**
     * setter qui permet de vérifier que le nombre d'employé n'est pas 0
     * @param nbr_employes
     * @throws Exception_entites
     */
    public void setNbr_employes(int nbr_employes) throws Exception_entites
    {
        //on vérifie que le nombre d'employé soit différent de 0
        if (nbr_employes==0)
        {
            //sinon on renvoit une erreur
                throw  new Exception_entites(" le nombre d' employé  doit etre supérieur à 0");
        }
        this.nbr_employes = nbr_employes;
    }

    /**
     * methode to_string
     * @return chaine de caractère des données de sociéte + celle de Clients
     */
    @Override
    public String toString()
    {
        return super.toString() +
                "  " + chiffre_affaire +
                " " + nbr_employes
                ;
    }
}
