package entites;

import java.util.ArrayList;
import java.util.List;

/**Classe liste_prospects qui permet la création une liste Prospects
 * @author Alexandre Debus
 * Classe contenant la liste de prospects
 */

public class List_prospects
{
    private static ArrayList<Prospects> maliste_prospects = new ArrayList<Prospects>();

    public static ArrayList<Prospects> getMaliste_prospects()
    {
        return maliste_prospects;
    }
}
