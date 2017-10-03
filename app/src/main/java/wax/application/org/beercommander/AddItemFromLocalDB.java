package wax.application.org.beercommander;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class AddItemFromLocalDB extends Activity {


    Commandes parent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item_from_local_db);

        parent = (Commandes) getParent();

        String[] countries = new String[] {
                "India",
                "Pakistan",
                "Sri Lanka",
                "China",
                "Bangladesh",
                "Nepal",
                "Afghanistan",
                "North Korea",
                "South Korea",
                "Japan"
        };

        // Array of integers points to images stored in /res/drawable-ldpi/
        int[] flags = new int[]{
                R.drawable.india,
                R.drawable.pakistan,
                R.drawable.srilanka,
                R.drawable.china,
                R.drawable.bangladesh,
                R.drawable.nepal,
                R.drawable.afghanistan,
                R.drawable.nkorea,
                R.drawable.skorea,
                R.drawable.japan
        };

        // Each row in the list stores country name, currency and flag
        List<HashMap<String,String>> aList = new ArrayList<HashMap<String,String>>();

        for(int i=0;i<10;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put("txt", countries[i]);
            hm.put("flag", Integer.toString(flags[i]) );
            aList.add(hm);
        }


        // Keys used in Hashmap
        String[] from = { "flag","txt"};

        // Ids of views in listview_layout
        int[] to = { R.id.flag,R.id.txt};

        // Instantiating an adapter to store each items
        // R.layout.listview_layout defines the layout of each item
        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList, R.layout.grid_items, from, to);

        // Getting a reference to gridview of MainActivity
        GridView gridView = (GridView) findViewById(R.id.gridview);

        // Setting an adapter containing images to the gridview
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(AddItemFromLocalDB.this, "" + position,
                        Toast.LENGTH_SHORT).show();

                AddItemFromLocalDB.this.parent.addItem("Turlututu");
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_produits_dans_db, menu);
        return true;
    }
}
