package nguyenthanhloi.hotelscanner;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class DetailActivity extends Activity {

    Context context;
    String location;
    hotel myHotel;
    TextView name, price, description, callnumber;
    ImageView imageView;
    int days;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        context = getApplicationContext();
        Bundle bundle = getIntent().getExtras();
        location = bundle.getString("location");
        myHotel = (hotel) bundle.get("myHotel");
        days = bundle.getInt("days");
        setTitle("Detail of " + myHotel.get_name());

        DecimalFormat formatter = new DecimalFormat("#,###,###");
        name = (TextView) findViewById(R.id.textName);
        price = (TextView) findViewById(R.id.textprice);
        description = (TextView) findViewById(R.id.description);
        callnumber = (TextView) findViewById(R.id.textcall);
        imageView = (ImageView) findViewById(R.id.imageView);

        name.setText(myHotel.get_name());
        price.setText(formatter.format(Integer.parseInt(myHotel.get_price())) + " VND x " + days + " day(s) = " + formatter.format(Integer.parseInt(myHotel._price)*days) + " VND");
        description.setText("Address: " + myHotel.get_address() + "\n");
        callnumber.setText(myHotel.get_phonenum());

        int imageId  = context.getResources().getIdentifier(myHotel.get_photo() , "drawable", context.getPackageName());
        imageView.setImageResource(imageId);
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
    public void btMapClick(View v)
    {
        Intent it = new Intent(context, MapsActivity.class);
        it.putExtra("location",location);
        it.putExtra("destination", myHotel._address);
        startActivity(it);
    }

    public void onCallClick(View v)
    {
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + callnumber.getText().toString()));
        startActivity(i);
    }
}
