package nguyenthanhloi.hotelscanner;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class SearchActivity extends Activity {

    private ArrayList<hotel> hotels;
    static Context context;
    String location;
    ListView listview;
    int days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Result of Searching");
        setContentView(R.layout.activity_search);
        Bundle bundle = getIntent().getExtras();
        context = getApplicationContext();
        location =  bundle.getString("location");
        days = bundle.getInt("days");
        listview = (ListView) findViewById(R.id.listHotels);
        hotels = new ArrayList<hotel>();
        readData();

        //Sort array:

        CustomAdapterListView adapter = new CustomAdapterListView(this, hotels);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> arg0, View v, int pos, long id) {
                        Intent it = new Intent(getApplicationContext(), DetailActivity.class);
                        it.putExtra("location",location);
                        it.putExtra("myHotel",hotels.get(pos));
                        it.putExtra("days", days);
                        startActivity(it);
                    }
                });
    }

    public void readData() {
        String data;
        InputStream in = getResources().openRawResource(R.raw.save);
        InputStreamReader inreader = new InputStreamReader(in);
        BufferedReader bufreader = new BufferedReader(inreader);
        if(in!=null)
        {
            try
            {
                while((data=bufreader.readLine())!=null)
                {
                    String[] para = new String[6];
                    para[0] = data;
                    for (int i = 1; i < 6; i++)
                        para[i] = bufreader.readLine();
                    hotel h = new hotel(para[0], para[1], para[2], para[3], para[4], para[5]);
                    hotels.add(h);
                    bufreader.readLine();
                }
                in.close();
            }
            catch(IOException ex){
                Log.e("ERROR", ex.getMessage());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(false); // disable the button
            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
            BitmapDrawable background = new BitmapDrawable (BitmapFactory.decodeResource(getResources(), R.drawable.actionbar));
            actionBar.setBackgroundDrawable(background);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
