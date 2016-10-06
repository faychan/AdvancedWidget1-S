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
    Spinner spNummer;
    LinearLayout llKinder;
    TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spNummer = (Spinner) findViewById(R.id.spinnerNummerKinder);
        Integer[] arNummer = new Integer[10];
        for (int i = 0; i < 10; i++) {
            arNummer[i] = i + 1;
        }
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arNummer);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNummer.setAdapter(adapter);

        llKinder = (LinearLayout) findViewById(R.id.linearLayoutKinder);
        tvHasil = (TextView) findViewById(R.id.textViewHasil);

        findViewById(R.id.buttonProses).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doProses();
            }
        });

        spNummer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                addEditText((int) spNummer.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addEditText(int jumlah) {
        llKinder.removeAllViews();
        for (int i = 1; i <= jumlah; i++) {
            View v = LayoutInflater.from(this).inflate(R.layout.layout_anak, llKinder, false);
            v.setTag("Kinder" + i);
            llKinder.addView(v);
        }
    }

    private void doProses() {
        int jumlah = (int) spNummer.getSelectedItem();
        String hasil = "";
        for (int i = 1; i <= jumlah; i++) {
            LinearLayout llNow = (LinearLayout) llKinder.findViewWithTag("Kinder" + i);

            EditText etNama = (EditText) llNow.findViewById(R.id.editTextNama);
            EditText etUmur = (EditText) llNow.findViewById(R.id.editTextUmur);

            String nama = etNama.getText().toString().trim();
            String umur = etUmur.getText().toString();

            if (umur.isEmpty())
                umur = "0";
            if (!nama.isEmpty())
                hasil += "Kinder ke-" + i + ": " + nama + " umur " + umur + " tahun\n";
        }
        tvHasil.setText(hasil);
    }

}
