package entites;

import java.util.ArrayList;
import java.util.List;

/**Classe qui permet de générer la liste de clients
 * @author Alexandre Debus
 */
public class List_clients
{
    private static ArrayList<Clients> ma_liste_clients= new ArrayList<>();

    public static ArrayList<Clients> getMa_liste_clients()
    {
        return ma_liste_clients;
    }
}
