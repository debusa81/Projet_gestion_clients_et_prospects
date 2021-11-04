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
                    email,String telephone,String commentaires)
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

    public void setRaison_sociale(String raison_sociale)  {
        //filtre pour savoir si la raison sociale est nulle
        if (raison_sociale.trim()=="")
        {
            //on renvoie une erreur avec le message approprié
            try {
                throw new  MonException("");
                } catch (MonException e)
                    {
                        System.out.println("la raison sociale ne peut pas etre null");
                    }


        }
        Raisonsociale = raison_sociale;
    }

    public String getNumero_rue() {
        return numerorue;
    }

    public void setNumero_rue(String numero_rue)
    {
        //on vérifie si le numéro de rue n'a pas été saisie

        if (numero_rue.trim()=="")
        {
            try {
                throw new MonException("");
                } catch (MonException e)
                    {
                        System.out.println("il manque un élément à votre adresse, il faut remplir numéro rue ");
                    }
        }
        this.numerorue = numero_rue;
    }

    public String getNom_rue()
    {
        return nom_rue;
    }

    public void setNom_rue(String nom_rue) {
        //on vérifie si le nom de rue n'a pas été saisi
        if (nom_rue.trim()==""){
            try {
                throw new  MonException("");
            } catch (MonException e) {
                System.out.println(" Il manque un éléement à votre adresse , il faut remplir nom rue");
            }
        }
        this.nom_rue = nom_rue;
    }

    public String getCode_postal()
    {
        return Code_postal;
    }

    public void setCode_postal(String code_postal)
    {
        //on verifie si le code postal est vide
        if (code_postal.trim()=="")
        {
            try {
                throw new MonException("");
                } catch (MonException e)
                    {
                        System.out.println("il manque un élément à votre adresse , il faut remplir le code postal ");
                    }
        }
        Code_postal = code_postal;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String ville)
    {
        //on vérifie si la ville a bien été saisi
        if (ville=="")
        {
            try {
                throw new MonException("");
                } catch (MonException e)
                    {
                        System.out.println(" il manque un élément à votre adresse, il faut renseigner une ville");
                    }
        }
        Ville = ville;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        //on vérifie si l'email ne contient pas  d' @
        if (!email.contains("@"))
        {
            try {
                throw  new MonException("");
                } catch (MonException e)
                    {
                        System.out.println("ceci n'est pas une adresse mail, il ne contient pas d' @");
                    }
        }
        this.email = email;
    }

    public String getNum_tel()
    {
        return num_tel;
    }

    public void setNum_tel(String num_tel)
    {
        //on vérifie si le numéro de telephone a bien été saisie
        if (num_tel=="")
        {
            //on renvoie l' erreur
            try {
                throw new MonException("");
            } catch (MonException e) {
                System.out.println(" le numéro de teléphone ne peut pas etre vide veuillez le renseigner");
            }
        }
        //on vérifie  que le numéro de telephone ne soit pas inférieur à 10 caractères
         if (num_tel.length()<10)
         {
             //on renvoie une erreur avec le bon message
             try {
                 throw  new MonException("");
                  } catch (MonException e)
                    {
                        System.out.println("le numéro de téléphone doit avoir au moins 10 caractères");
                    }
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
