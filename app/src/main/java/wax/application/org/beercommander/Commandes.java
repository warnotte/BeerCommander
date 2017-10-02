package wax.application.org.beercommander;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Commandes extends AppCompatActivity {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<ItemCommande> listItems=new ArrayList<ItemCommande>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    LstViewAdapter adapter;

    ListView list;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandes);


        listItems.add(new ItemCommande("New Item 1", 0));
        listItems.add(new ItemCommande("New Item 2", 0));
        listItems.add(new ItemCommande("New Item 3", 0));


        //   adapter=new ArrayAdapter<ItemCommande>(this, android.R.layout.simple_list_item_1,  listItems);

        list = (ListView) findViewById(R.id.listView1);



        adapter = new LstViewAdapter(this, R.layout.list_item, R.id.txt, listItems);
        // Bind data to the ListView
        list.setAdapter(adapter);


        //Enfin on met un écouteur d'évènement sur notre listView
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                increase_item_amount(position);

            }
        });

    }

    public void clickMe(View view){

        Button bt=(Button)view;

        final int position = list.getPositionForView((LinearLayout)view.getParent());
        if (position >= 0) {
            decrease_item_amount(position);
        }
        else
            Log.d("Tag", "Errer dd4e64e6");

    }

    private void decrease_item_amount(int position) {
        ItemCommande map = (ItemCommande) list.getItemAtPosition(position);
        if (map.count>0)
            map.count--;
        Log.d("Tab", "DO IT " + map);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Un-Schack!",Toast.LENGTH_SHORT).show();
    }

    private void increase_item_amount(int position) {
        ItemCommande map = (ItemCommande) list.getItemAtPosition(position);
        map.count++;
        Log.d("Tab", "DO IT " + map);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Schack!",Toast.LENGTH_SHORT).show();
    }


}
