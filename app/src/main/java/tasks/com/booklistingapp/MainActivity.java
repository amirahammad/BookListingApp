package tasks.com.booklistingapp;

import android.app.ProgressDialog;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button search_Button;
    RecyclerView recyclerView;
    List<Object> listItems = new ArrayList();
    String var;
    Adapter adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new Adapter(this, listItems);
        recyclerView.setAdapter(adapter);

    }

    public void initialize() {
        editText = (EditText) findViewById(R.id.editText);
        search_Button = (Button) findViewById(R.id.search_button);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    public void displayData(View v) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void afterTextChanged (Editable s){

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String searchString = editText.getText().toString();
                for (int i = 0; i < listItems.size(); i++) {
                    String currentString = listItems.get(i).toString();
                    if (searchString.equalsIgnoreCase(currentString)) {
                        listItems.add(listItems.get(i));
                    }

                }
                adapter = new Adapter(MainActivity.this, listItems);
                recyclerView.setAdapter(adapter);

            }
        });
        try {
            addMenuItemsFromJson();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

  /*  public void filterData(String text) {

        ArrayList<String> filterdNames = new ArrayList<>();
        for (String s : listItems) {
            if (s.contains(text.toLowerCase())) {
                filterdNames.add(s);
            }
            adapter.filterList(filterdNames);

        }
    }*/


    private void addMenuItemsFromJson() throws IOException, JSONException {
        try {

            String jsonDataString = readJsonDataFromFile();
            JSONArray menuItemsJsonArray = new JSONArray(jsonDataString);

            for (int i = 0; i < menuItemsJsonArray.length(); ++i) {

                JSONObject object  = menuItemsJsonArray.getJSONObject(i);

                String bookName = object.getString("book_name");
                String authorName = object.getString("author_name");
                String decription = object.getString("description");

                Pojo pojo = new Pojo(bookName, authorName, decription);
                listItems.add(pojo);
            }
        }
        catch (IOException | JSONException exception) {
            Log.e(MainActivity.class.getName(), "Unable to parse JSON file.", exception);
        }

    }

    private String readJsonDataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {
            String jsonDataString = null;
            inputStream = getResources().openRawResource(R.raw.json_file);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonDataString = bufferedReader.readLine()) != null) {
                builder.append(jsonDataString);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return new String(builder);
    }

}
