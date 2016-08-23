package nguyenthanhloi.hotelscanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.text.DecimalFormat;
import java.util.List;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by ThanhLoi on 4/26/2016.
 */
public class CustomAdapterListView extends BaseAdapter {
    List<hotel> listData;
    LayoutInflater layoutInflater;
    Context context;

    public CustomAdapterListView(Context context, List<hotel> listData)
    {
        this.context = context;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public  int getCount()
    {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.textdesciption = (TextView) convertView.findViewById(R.id.description);
            holder.textName = (TextView) convertView.findViewById(R.id.textName);
            holder.textprice = (TextView) convertView.findViewById(R.id.textprice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        hotel h = this.listData.get(position);
        holder.textName.setText(h.get_name());
        holder.textdesciption.setText("Address: " + h.get_address() + "\n" + "Left: " + h.get_remainRoom());
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        holder.textprice.setText((formatter.format(Integer.parseInt(h.get_price())) + " VND per night"));
        int imageId  = context.getResources().getIdentifier(h.get_photo() , "drawable", context.getPackageName());
        holder.imageView.setImageResource(imageId);
        return convertView;
    }


    static class ViewHolder {
        ImageView imageView;
        TextView textName;
        TextView textdesciption;
        TextView textprice;
    }
}
