package nguyenthanhloi.hotelscanner;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class MainActivity extends Activity {

    RelativeLayout welcomescreen;
    String begin, end;
    Date from, to;
    String location;
    EditText edit;
    //GPSService gpsService = new GPSService(getApplicationContext());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Welcome to Hotel Scanner");
        setContentView(R.layout.activity_main);
        welcomescreen = (RelativeLayout)findViewById(R.id.welcome);
        welcomescreen.setBackgroundResource(R.drawable.back);
        edit = (EditText) findViewById(R.id.editText);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        ActionBar actionBar = getActionBar();
        BitmapDrawable background = new BitmapDrawable (BitmapFactory.decodeResource(getResources(), R.drawable.actionbar));
        actionBar.setBackgroundDrawable(background);
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

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            Button bt = (Button) findViewById(R.id.btbegin);
            bt.setText(arg3+"/"+arg2+"/"+arg1);
        }
    };

    private DatePickerDialog.OnDateSetListener myDateListener2 = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            Button bt = (Button) findViewById(R.id.btend);
            bt.setText(arg3+"/"+arg2+"/"+arg1);
        }
    };
    public void bt1Click(View v)
    {

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dlg = new DatePickerDialog(this, myDateListener, year, month, day);
        dlg.show();
        Button bt = (Button) v;
        bt.setText(day + "/" + month + "/" + year);
    }

    public void bt2Click(View v)
    {

        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // Note: zero based!
        int day = now.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dlg = new DatePickerDialog(this, myDateListener2, year, month, day);
        dlg.show();
        Button bt = (Button) v;
        bt.setText(day+"/"+month+"/"+year);
    }

    static public int daysBetween(Date d1, Date d2){
        return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
    }


    public void onClickGPS(View v)
    {
       // String address = gpsService.getLocationAddress();
       // edit.setText(address);
    }

    public void btSearchClick(View v)
    {

        location = edit.getText().toString();
        Button b1 = (Button) findViewById(R.id.btbegin);
        Button b2 = (Button) findViewById(R.id.btend);

        if (b1.getText().toString() == "BEGIN DAY" || b2.getText().toString() == "END DAY")
        {
            Toast.makeText(this, "Date is not valid!", Toast.LENGTH_SHORT).show();
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try
        {
            from = dateFormat.parse(b1.getText().toString());
            to = dateFormat.parse(b2.getText().toString());
        }catch(ParseException ex){}
        int days =  daysBetween(from, to);
        if (location.isEmpty())
        {
            Toast.makeText(this, "Please type your location!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (days < 0)
            {
                Toast.makeText(this, "Date is not valid!", Toast.LENGTH_SHORT).show();
                return;
            }
        else
        {
            Toast.makeText(this, "Searching...", Toast.LENGTH_SHORT).show();
            Intent it = new Intent(getApplicationContext(), SearchActivity.class);
            begin = findViewById(R.id.btbegin).toString();
            end = findViewById(R.id.btend).toString();
            it.putExtra("location", location);
            it.putExtra("begin", begin);
            it.putExtra("end", end);
            it.putExtra("days", days);
            startActivity(it);
        }
    }
}
