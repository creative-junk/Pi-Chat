package crysoftdynamics.timeline;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar mToolBar;
    EditText codeEditView;
    EditText mobile;

    //String Country = Locale.getCountry();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       String locale = this.getResources().getConfiguration().locale.getDisplayCountry();
        //Setup the Country Code
        codeEditView = (EditText) findViewById(R.id.codeEditView);
        mobile = (EditText) findViewById(R.id.mobile);
        //Setup the spinner
        Spinner spinner = (Spinner) findViewById(R.id.country_spinner);
        spinner.requestFocus();
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.country_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        //Set the default Country
        spinner.setSelection(adapter.getPosition(locale));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Selected Country : " + parent.getItemAtPosition(position).toString() + position, Toast.LENGTH_SHORT).show();

                String[] mTestArray = getResources().getStringArray(R.array.country_code);
                String code = getCountryCode(position);
                codeEditView.setText(code);
                mobile.requestFocus();
            }

            @Override
            public void onNothingSelected(AdapterView parent) {

            }
        });
        /*
        Locale[] locales = Locale.getAvailableLocales();

        ArrayList<String> countries = new ArrayList<String>();
        String country;
        for( Locale loc : locales ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

        Spinner citizenship = (Spinner)findViewById(R.id.country_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, countries);
        citizenship.setAdapter(adapter);
        //Set the default Country
        spinner.setSelection(adapter.getPosition(locale));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "Selected Country : " + parent.getItemAtPosition(position).toString() + position, Toast.LENGTH_SHORT).show();

                String[] mTestArray = getResources().getStringArray(R.array.country_code);
                String code = getCountryCode(position);
                codeEditView.setText(code);

            }

            @Override
            public void onNothingSelected(AdapterView parent) {

            }
        });
        */

    }
    public String getCountryCode(int position){
        String[] myResArray = getResources().getStringArray(R.array.country_code);
        List<String> myResArrayList = Arrays.asList(myResArray);
        String countryCode = myResArrayList.get(position);
        return countryCode;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
