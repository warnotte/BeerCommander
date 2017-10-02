package wax.application.org.beercommander;

/**
 * Created by user on 2/10/2017.
 */

public class ItemCommande {
    String label;
    int count;

    public ItemCommande(String label, int cpt)
    {
        this.label = label;
        this.count = cpt;
    }


    public String toString()
    {
        return "["+count+"] - "+label;
    }
}
