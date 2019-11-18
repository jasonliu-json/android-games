package uoft.csc207.gameapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;


public class CustomizeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

//    private String[] topTenScores = new String[10];
//    private String[] topTenLinesCleared = new String[10];
//    private String[] toptenTimes = new String[10];

    private static final String FILE = "Customize.json";
    private JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);
        load();
        System.out.println(jsonObject.toString());
//        loadDatabase();
//        String[][] topPlays = getTopPlayers();
//        topTenPlayers = topPlays[1];
//        topTenScores = topPlays[0];


        int[] shapeSpinnersId = {R.id.shape1_spinner, R.id.shape2_spinner, R.id.shape3_spinner,
                R.id.shape4_spinner};
        int[] colourSpinnersId = {R.id.colour1_spinner, R.id.colour2_spinner, R.id.colour3_spinner,
                R.id.colour4_spinner};

        for (int i=0; i<4; i++) {
            Spinner shapeSpinner = (Spinner) findViewById(shapeSpinnersId[i]);
            ArrayAdapter<CharSequence> shapeAdapter = ArrayAdapter.createFromResource(this,
                    R.array.Shapes, android.R.layout.simple_spinner_item);
            shapeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            shapeSpinner.setAdapter(shapeAdapter);
            shapeSpinner.setOnItemSelectedListener(this);

            Spinner colourSpinner = (Spinner) findViewById(colourSpinnersId[i]);
            ArrayAdapter<CharSequence> colourAdapter = ArrayAdapter.createFromResource(this,
                    R.array.Colours, android.R.layout.simple_spinner_item);
            colourAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            colourSpinner.setAdapter(colourAdapter);
            colourSpinner.setOnItemSelectedListener(this);
        }

    }

    private void save() {
        String jsonText = jsonObject.toString();
        try {
            FileOutputStream fileOutputStream = openFileOutput(FILE, MODE_PRIVATE);
            fileOutputStream.write(jsonText.getBytes());
            fileOutputStream.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void load() {
        try {
            int i = 0;
            String jsonString = "";
            try {
                FileInputStream fileInputStream = openFileInput(FILE);
                while ((i = fileInputStream.read()) != -1) {
                    jsonString += (char) i;
                }
            }
            catch (FileNotFoundException e) {
                BufferedReader bufferReader = new BufferedReader(
                        new InputStreamReader(getAssets().open(FILE)));
                while ((i = bufferReader.read()) != -1) {
                    jsonString += (char) i;
                }
            }
            jsonObject = new JSONObject(jsonString);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();;
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        try{
            JSONObject shapes = jsonObject.getJSONObject("shapes");
            JSONObject colours = jsonObject.getJSONObject("colours");
            switch (adapterView.getId()) {
                case R.id.shape1_spinner:
                    shapes.put("1", adapterView.getSelectedItem());
                    break;
                case R.id.shape2_spinner:
                    shapes.put("2", adapterView.getSelectedItem());
                    break;
                case R.id.shape3_spinner:
                    shapes.put("3", adapterView.getSelectedItem());
                    break;
                case R.id.shape4_spinner:
                    shapes.put("4", adapterView.getSelectedItem());
                    break;
                case R.id.colour1_spinner:
                    colours.put("1", adapterView.getSelectedItem());
                    break;
                case R.id.colour2_spinner:
                    colours.put("2", adapterView.getSelectedItem());
                    break;
                case R.id.colour3_spinner:
                    colours.put("3", adapterView.getSelectedItem());
                    break;
                case  R.id.colour4_spinner:
                    colours.put("4", adapterView.getSelectedItem());
                    break;
                case R.id.song_spinner:
                    jsonObject.put("Song", adapterView.getSelectedItem());
                    break;
                default:
                    break;
            }

            System.out.println(jsonObject.toString());
//            jsonObject.put("shapes", shapes);
//            jsonObject.put("colours", colours);
            save();
        } catch (JSONException e) {
            e.printStackTrace();
        }


//        String text = adapterView.getItemAtPosition(i).toString();
//        System.out.println(adapterView.getId() == R.id.shape1_spinner);
//        System.out.println(adapterView.getId() == R.id.colour1_spinner);
//        System.out.println(adapterView.getSelectedItem());
//        System.out.println(view.getId());
//        System.out.println(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

