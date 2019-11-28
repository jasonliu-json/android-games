package uoft.csc207.gameapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import uoft.csc207.gameapplication.Utility.JSONFileRW;


public class CustomizeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static final String FILE = "Customize.json";
    private JSONObject jsonObject;
    private JSONObject tetrisCust;
    private JSONObject rhythmCust;
    private JSONObject mazeCust;
    private JSONFileRW fileRW;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);
        fileRW = new JSONFileRW(FILE, this);
        jsonObject = fileRW.load();
        if (jsonObject != null) {
            try {
                tetrisCust = jsonObject.getJSONObject("tetris");
                rhythmCust = jsonObject.getJSONObject("rhythm");
                mazeCust = jsonObject.getJSONObject("maze");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // Tetris Game Spinners
        Spinner tetrisControlSpinner = (Spinner) findViewById(R.id.tetris_control_spinner);
        ArrayAdapter<CharSequence> tetrisControlAdapter = ArrayAdapter.createFromResource(this,
                R.array.tetrisControls, android.R.layout.simple_spinner_item);
        tetrisControlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tetrisControlSpinner.setAdapter(tetrisControlAdapter);
        tetrisControlSpinner.setOnItemSelectedListener(this);

        // Rhythm Game Spinners
        int[] shapeSpinnersId = {R.id.shape1_spinner, R.id.shape2_spinner, R.id.shape3_spinner,
                R.id.shape4_spinner};

        for (int i = 0; i < 4; i++) {
            Spinner shapeSpinner = (Spinner) findViewById(shapeSpinnersId[i]);
            ArrayAdapter<CharSequence> shapeAdapter = ArrayAdapter.createFromResource(this,
                    R.array.shapes, android.R.layout.simple_spinner_item);
            shapeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            shapeSpinner.setAdapter(shapeAdapter);
            shapeSpinner.setOnItemSelectedListener(this);
        }

        // Maze Game Spinner
        Spinner mazeControlSpinner = (Spinner) findViewById(R.id.maze_control_spinner);
        ArrayAdapter<CharSequence> mazeControlAdapter = ArrayAdapter.createFromResource(this,
                R.array.mazeControls, android.R.layout.simple_spinner_item);
        mazeControlAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mazeControlSpinner.setAdapter(mazeControlAdapter);
        mazeControlSpinner.setOnItemSelectedListener(this);

        // Apply Button
        Button applyButton = (Button) findViewById(R.id.apply_button);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
                Toast.makeText(CustomizeActivity.this, "Applied", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void save() {
        String jsonText = jsonObject.toString();
        fileRW.write(jsonText);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        try {
            switch (adapterView.getId()) {
                case R.id.tetris_control_spinner:
                    tetrisCust.put("controls", adapterView.getSelectedItem());
                    break;
                case R.id.shape1_spinner:
                    rhythmCust.put("shape1", adapterView.getSelectedItem());
                    break;
                case R.id.shape2_spinner:
                    rhythmCust.put("shape2", adapterView.getSelectedItem());
                    break;
                case R.id.shape3_spinner:
                    rhythmCust.put("shape3", adapterView.getSelectedItem());
                    break;
                case R.id.shape4_spinner:
                    rhythmCust.put("shape4", adapterView.getSelectedItem());
                    break;
                case R.id.maze_control_spinner:
                    mazeCust.put("controls", adapterView.getSelectedItem());
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
}

