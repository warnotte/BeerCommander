package wax.application.org.beercommander;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LstViewAdapter extends ArrayAdapter<ItemCommande> {

    int groupid;

	ArrayList<ItemCommande> item_list;

	//ArrayList<ItemCommande> desc;
	
	Context context;

	public LstViewAdapter(Context context, int vg, int id, ArrayList<ItemCommande> item_list){
		super(context,vg, id, item_list);
		this.context=context;
		groupid=vg;
		this.item_list=item_list;

	}
	// Hold views of the ListView to improve its scrolling performance
	static class ViewHolder {
		public Text counter;
		public TextView textview;
		public Button buttonAdd;
		public Button buttonRemove;



	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View rowView = convertView;
		// Inflate the list_item.xml file if convertView is null
		if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView= inflater.inflate(groupid, parent, false);
            ViewHolder viewHolder = new ViewHolder();
			viewHolder.textview= (TextView) rowView.findViewById(R.id.txt);
			viewHolder.buttonRemove= (Button) rowView.findViewById(R.id.btRemove);
			viewHolder.buttonAdd= (Button) rowView.findViewById(R.id.btAdd);
			viewHolder.counter= (Text) rowView.findViewById(R.id.editText);
			rowView.setTag(viewHolder);

		}
		// Set text to each TextView of ListView item
		ViewHolder holder = (ViewHolder) rowView.getTag();

        ItemCommande obj = item_list.get(position);
		holder.textview.setText(obj.label);
		holder.buttonRemove.setText("-");
		holder.buttonAdd.setText("+");
		holder.counter.setData(""+obj.count);
		return rowView;
	}




}