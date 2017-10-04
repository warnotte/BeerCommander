package wax.application.org.beercommander;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddItemFromLocalDB extends Activity {

    static String[] countries = new String[] {
            "Bière",
            "Panaché",
            "Mazout",
            "Tango",
            "Schusst",
            "Eau",
            "Coca",
            "Limonade",
            "Orangeade",
            "Vielsalm",
            "Myrtille",
            "TchaTcha",
            "Vielsalm",
            "Blanc-Coca",
            "Blanc-Jus"
    };
    List<HashMap<String,String>> aList;
    SimpleAdapter adapter;

    EditText newElementTexTfield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_from_local_db);

        newElementTexTfield = (EditText) findViewById(R.id.text_nouvel_element);

        // Array of integers points to images stored in /res/drawable-ldpi/
        int[] flags = new int[]{
            R.drawable.Beer_Drink_5431,

        };

        // Each row in the list stores country name, currency and flag
        aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<10;i++){
            HashMap<String, String> hm = new HashMap<String,String>();

            if (Commandes.instance.checkIfLabelExistsInList(countries[i]))
                continue;

            hm.put("txt", countries[i]);
            hm.put("flag", Integer.toString(flags[i%flags.length]) );
            aList.add(hm);
        }


        // Keys used in Hashmap
        String[] from = { "flag","txt"};

        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.grid_items, from, to);

        // Getting a reference to gridview of MainActivity
        GridView gridView = (GridView) findViewById(R.id.gridview);

        // Setting an adapter containing images to the gridview
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                HashMap<String, String> map = aList.get(position);
                String label = map.get("txt");
                Commandes.instance.showToastMessage("Adding " + label,Commandes.instance.NotificationLen);
                Commandes.instance.addItem(label);
                removeItem(label);
            }
        });
    }

    public void removeItem(String label)
    {
        for(int i=0;i<aList.size();i++) {
            HashMap<String,String> o = aList.get(i);
            String label_item = o.get("txt");
            if (label_item.equalsIgnoreCase(label)) {
                aList.remove(i);
                adapter.notifyDataSetChanged();
                break;
            }
        }
    }

    public void addNewItem(View view)
    {
        String label = newElementTexTfield.getText().toString();

        if (checkIfItemExistInDBOrIncommande(label)==false) {
            HashMap<String, String> map = new HashMap<>();
            map.put("txt", label);
            map.put("flag", Integer.toString(R.drawable.india));
            aList.add(map);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_produits_dans_db, menu);
        return true;
    }

    public boolean checkIfItemExistInDBOrIncommande(String label)
    {
        // Check dans la DB ici ou dans la commande.
        if (Commandes.instance.checkIfLabelExistsInList(label)==true)
        {
            Commandes.instance.showToastMessage("Existe déjà dans la commande", Commandes.instance.NotificationLen);
            return true;
        }

        for(int i=0;i<aList.size();i++) {
            HashMap<String, String> o = aList.get(i);
            String label_item = o.get("txt");
            if (label_item.equalsIgnoreCase(label)) {
                Commandes.instance.showToastMessage("Existe déjà dans la base de données", Commandes.instance.NotificationLen);
                return true;
            }
        }
        return false;
    }
}
