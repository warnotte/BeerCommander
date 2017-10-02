package wax.application.org.beercommander;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Commandes extends AppCompatActivity {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<ItemCommande> listItems=new ArrayList<ItemCommande>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<ItemCommande> adapter;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandes);


        adapter=new ArrayAdapter<ItemCommande>(this, android.R.layout.simple_list_item_1,  listItems);

        list = (ListView) findViewById(R.id.listView1);
      //  list.setListAdapter(adapter);
        list.setAdapter(adapter);
        adapter.add(new ItemCommande("New Item 1", 0));
        adapter.add(new ItemCommande("New Item 2", 0));
        adapter.add(new ItemCommande("New Item 3", 0));


        //Enfin on met un écouteur d'évènement sur notre listView
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                ItemCommande map = (ItemCommande) list.getItemAtPosition(position);
                map.count++;
                Log.d("Tab", "DO IT "+map);



                adapter.notifyDataSetChanged();

                /*//on créé une boite de dialogue
                AlertDialog.Builder adb = new AlertDialog.Builder(Tutoriel5_Android.this);
                //on attribue un titre à notre boite de dialogue
                adb.setTitle("Sélection Item");
                //on insère un message à notre boite de dialogue, et ici on affiche le titre de l'item cliqué
                adb.setMessage("Votre choix : "+map.get("titre"));
                //on indique que l'on veut le bouton ok à notre boite de dialogue
                adb.setPositiveButton("Ok", null);
                //on affiche la boite de dialogue
                adb.show();*/
            }
        });

    }


}
