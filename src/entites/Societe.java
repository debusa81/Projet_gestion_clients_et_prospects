package entites;

import Exceptions.MonException;

/**
 * @author Alexandre Debus
 * classe Societe
 * @version 1
 */
public abstract class Societe {
    //attributs d'instance
    private  int id;
    private  String Raisonsociale;
    private  String numerorue;
    private  String nom_rue;
    private  String Code_postal;
    private  String Ville;
    private  String email;
    private String num_tel;
    private  String Comments;
    //constructeur implicit
    public  Societe(String raison_sociale,String Numero_rue,String nom_marue,String code,String ville,String
                    email,String telephone,String commentaires)throws MonException
    {
                setId(id);
                setRaison_sociale(raison_sociale);
                setNumero_rue(Numero_rue);
                setNom_rue(nom_marue);
                setCode_postal(code);
                setEmail(email);
                setNum_tel(telephone);
                setVille(ville);
                setComments(commentaires);
    }
    //constructeur vide
    public  Societe(){}

    //getters et seetter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRaison_sociale() {
        return Raisonsociale;
    }

    public void setRaison_sociale(String raison_sociale) throws MonException  {
        //filtre pour savoir si la raison sociale est nulle
        if (raison_sociale.trim()=="")
        {
            //on renvoie une erreur avec le message approprié

                throw new  MonException("");



        }
        Raisonsociale = raison_sociale;
    }

    public String getNumero_rue() {
        return numerorue;
    }

    public void setNumero_rue(String numero_rue) throws MonException
    {
        //on vérifie si le numéro de rue n'a pas été saisie

        if (numero_rue.trim()=="")
        {

                throw new MonException("");

        }
        this.numerorue = numero_rue;
    }

    public String getNom_rue()
    {
        return nom_rue;
    }

    public void setNom_rue(String nom_rue) throws MonException{
        //on vérifie si le nom de rue n'a pas été saisi
        if (nom_rue.trim()==""){

                throw new  MonException("");

        }
        this.nom_rue = nom_rue;
    }

    public String getCode_postal()
    {
        return Code_postal;
    }

    public void setCode_postal(String code_postal) throws MonException
    {
        //on verifie si le code postal est vide
        if (code_postal.trim()=="")
        {

                throw new MonException("");

        }
        Code_postal = code_postal;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille (String ville) throws MonException
    {
        //on vérifie si la ville a bien été saisi
        if (ville=="")
        {

                throw new MonException("");

        }
        Ville = ville;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email) throws MonException
    {
        //on vérifie si l'email ne contient pas  d' @
        if (!email.contains("@"))
        {

                throw  new MonException("");

        }
        this.email = email;
    }

    public String getNum_tel()
    {
        return num_tel;
    }

    public void setNum_tel(String num_tel) throws MonException
    {
        //on vérifie si le numéro de telephone a bien été saisie
        if (num_tel=="")
        {
            //on renvoie l' erreur

                throw new MonException("");

        }
        //on vérifie  que le numéro de telephone ne soit pas inférieur à 10 caractères
         if (num_tel.length()<10)
         {
             //on renvoie une erreur avec le bon message

                 throw  new MonException("");

         }

        this.num_tel = num_tel;
    }

    public String getComments()
    {
        return Comments;
    }

    public void setComments(String comments)
    {
        Comments = comments;
    }

    /**
     * methode tostring
     * @return  chaine de caractères  des données de société
     */
    @Override
    public String toString()
    {
        return Raisonsociale+" "+numerorue+" "+nom_rue+"\n"+Code_postal+
                " "+Ville+"  "+ email +" "+ Comments;
    }
}
