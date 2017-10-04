package wax.application.org.beercommander;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Commandes extends Activity {


    public static Commandes instance;

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<ItemCommande> listItems = new ArrayList<ItemCommande>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    LstViewAdapter adapter;

    ListView list;

    Context context;
    EditText newElementTexTfield;
    ToggleButton button_mode_delete;

    public int NotificationLen = 750;

    Handler handler_inactive = new Handler();
    Runnable runnable_inactive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandes);

        instance = this;

        listItems.add(new ItemCommande(AddItemFromLocalDB.countries[0], 0));
        listItems.add(new ItemCommande(AddItemFromLocalDB.countries[1], 0));
        listItems.add(new ItemCommande(AddItemFromLocalDB.countries[2], 0));


        list = (ListView) findViewById(R.id.listView1);

        newElementTexTfield = (EditText) findViewById(R.id.editText2);
        button_mode_delete = (ToggleButton) findViewById(R.id.button_mode_delete);

        adapter = new LstViewAdapter(this, R.layout.list_item, R.id.txt, listItems);
        // Bind data to the ListView
        list.setAdapter(adapter);

        //Enfin on met un écouteur d'évènement sur notre listView
        list.setOnItemClickListener(new OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {

                if (button_mode_delete.isChecked() == false)
                    increase_item_amount(position);
                else {
                    removeItem(position);
                }
            }
        });



    }

    public void clickToggleButtonDeleteElements(View view) {
        if (button_mode_delete.isChecked() == true)
            showToastMessage("Now you can click to erase elements", NotificationLen * 4);
    }

    public void clickRemoveItem(View view) {
        Button bt = (Button) view;
        final int position = list.getPositionForView((LinearLayout) view.getParent());
        if (position >= 0) {
            decrease_item_amount(position);
        } else
            Log.d("Tag", "Errer dd4e64e6");
    }

    public void clickAddNewItem(View view) {

        button_mode_delete.setChecked(false);

        Intent tutorialPage = new Intent (this, AddItemFromLocalDB.class);
        startActivity(tutorialPage);



        /*
        String str = newElementTexTfield.getText().toString();
        addItem(str);
*/

    }

    private void decrease_item_amount(int position) {
        ItemCommande map = (ItemCommande) list.getItemAtPosition(position);
        if (map.count > 0) {
            map.count--;
            Log.d("Tab", "DO IT " + map);
            adapter.notifyDataSetChanged();
            showToastMessage("-1 " + map.label, NotificationLen);
            signalChangement();
        }
    }

    private void increase_item_amount(int position) {
        ItemCommande map = (ItemCommande) list.getItemAtPosition(position);
        map.count++;
        Log.d("Tab", "DO IT " + map);
        adapter.notifyDataSetChanged();
        showToastMessage("+1 " + map.label, NotificationLen);

        signalChangement();
    }

    public void addItem(String label) {
        if (checkIfLabelExistsInList(listItems, label) == false) {
            listItems.add(new ItemCommande(label, 1));
            adapter.notifyDataSetChanged();
            showToastMessage("Add " + label, NotificationLen);
            signalChangement();
        } else
            showToastMessage("Already exists " + label, NotificationLen);
    }

    public void removeItem(int position) {
        ItemCommande map = (ItemCommande) list.getItemAtPosition(position);
        listItems.remove(map);
        adapter.notifyDataSetChanged();
        showToastMessage("Efface " + map.label, 250);
    }

    public boolean checkIfLabelExistsInList(String label) {
        return checkIfLabelExistsInList(listItems, label);
    }

    private boolean checkIfLabelExistsInList(ArrayList<ItemCommande> listItems, String label) {
        for (int i = 0; i < listItems.size(); i++) {
            if (listItems.get(i).label.equalsIgnoreCase(label) == true)
                return true;
        }
        return false;
    }


    private void signalChangement() {
        if (runnable_inactive != null)
            handler_inactive.removeCallbacks(runnable_inactive);
        runnable_inactive = new Runnable() {
            @Override
            public void run() {
                reoderListByCount();
            }
        };
        handler_inactive.postDelayed(runnable_inactive, 1000);
    }

    /**
     * Reordonne la liste pour avoir le plus d'item count au dessus.
     */
    public void reoderListByCount() {
        Collections.sort(listItems, new Comparator<ItemCommande>() {
            @Override
            public int compare(ItemCommande lhs, ItemCommande rhs) {
                return Integer.compare(rhs.count, lhs.count);
            }
        });
        adapter.notifyDataSetChanged();
        //showToastMessage("INACTIF", 1000);
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
