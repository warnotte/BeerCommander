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

import android.os.Handler;

import java.util.ArrayList;

public class Commandes extends AppCompatActivity {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<ItemCommande> listItems = new ArrayList<ItemCommande>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    LstViewAdapter adapter;

    ListView list;

    Context context;

    int NotificationLen = 750;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandes);

        listItems.add(new ItemCommande("Bière(s)", 0));
        listItems.add(new ItemCommande("Panaché(s)", 0));
        listItems.add(new ItemCommande("Mazout(s)", 0));
        listItems.add(new ItemCommande("Tango(s)", 0));
        listItems.add(new ItemCommande("Blanc-Coca(s)", 0));
        listItems.add(new ItemCommande("Blanc-Jus(s)", 0));


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

    public void clickRemoveItem(View view) {

        Button bt = (Button) view;

        final int position = list.getPositionForView((LinearLayout) view.getParent());
        if (position >= 0) {
            decrease_item_amount(position);
        } else
            Log.d("Tag", "Errer dd4e64e6");

    }

    private void decrease_item_amount(int position) {
        ItemCommande map = (ItemCommande) list.getItemAtPosition(position);
        if (map.count > 0)
            map.count--;
        Log.d("Tab", "DO IT " + map);
        adapter.notifyDataSetChanged();
        showToastMessage("-1 " + map.label, NotificationLen);
    }

    private void increase_item_amount(int position) {
        ItemCommande map = (ItemCommande) list.getItemAtPosition(position);
        map.count++;
        Log.d("Tab", "DO IT " + map);
        adapter.notifyDataSetChanged();
        showToastMessage("+1 " + map.label, NotificationLen);

    }

    public void addItem(String label)
    {
        listItems.add(new ItemCommande("label", 1));
        adapter.notifyDataSetChanged();
        showToastMessage("+1 " + label, NotificationLen);
    }



    public void showToastMessage(String text, int duration) {
        final Toast toast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
        toast.show();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, duration);
    }
}
