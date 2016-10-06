package id.sch.smktelkom_mlg.learn.advancedwidget1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Spinner spAnzahl;
    LinearLayout llKinder;
    TextView tvErgebnis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spAnzahl = (Spinner) findViewById(R.id.spinnerAnzahlKinder);
        Integer[] arAnzahl = new Integer[10];
        for (int i = 0; i < 10; i++) {
            arAnzahl[i] = i + 1;
        }
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arAnzahl);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAnzahl.setAdapter(adapter);

        llKinder = (LinearLayout) findViewById(R.id.linearLayoutKinder);
        tvErgebnis = (TextView) findViewById(R.id.textViewErgebnis);

        findViewById(R.id.buttonProses).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProses();
            }
        });

        spAnzahl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                addEditText((int) spAnzahl.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addEditText(int anzahl) {
        llKinder.removeAllViews();
        for (int i = 1; i <= anzahl; i++) {
            View v = LayoutInflater.from(this).inflate(R.layout.layout_anak, llKinder, false);
            v.setTag("Kinder" + i);
            llKinder.addView(v);
        }
    }

    private void doProses() {
        int anzahl = (int) spAnzahl.getSelectedItem();
        String hasil = "";
        for (int i = 1; i <= anzahl; i++) {
            LinearLayout llNow = (LinearLayout) llKinder.findViewWithTag("Kinder" + i);

            EditText etName = (EditText) llNow.findViewById(R.id.editTextName);
            EditText etAlter = (EditText) llNow.findViewById(R.id.editTextAlter);

            String name = etName.getText().toString().trim();
            String alter = etAlter.getText().toString();

            if (alter.isEmpty())
                alter = "0";
            if (!name.isEmpty())
                hasil += "Kinder-" + i + ": " + name + alter + " Jahre alt\n";
        }
        tvErgebnis.setText(hasil);
    }

}
