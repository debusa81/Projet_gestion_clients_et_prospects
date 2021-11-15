package entites;

/**
 * @author Alexandre Debus
 * classe contenant la liste de clients
 * @version 1
 */

import java.util.ArrayList;
import java.util.List;

public class List_clients
{
    private static ArrayList<Clients> ma_liste_clients= new ArrayList<>();

    public static ArrayList<Clients> getMa_liste()
    {
        return ma_liste_clients;
    }
}
