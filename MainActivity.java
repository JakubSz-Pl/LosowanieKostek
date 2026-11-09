
package com.example.losowaniekostek;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editTextLiczbaKostek;
    Button buttonLosuj, buttonRosnaco, buttonMalejaco;
    TextView textViewWyniki;
    GridLayout gridKostki;

    ArrayList<Integer> wyniki = new ArrayList<>();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLiczbaKostek =
                findViewById(R.id.editTextLiczbaKostek);

        buttonLosuj = findViewById(R.id.buttonLosuj);
        buttonRosnaco = findViewById(R.id.buttonRosnaco);
        buttonMalejaco = findViewById(R.id.buttonMalejaco);

        textViewWyniki = findViewById(R.id.textViewWyniki);
        gridKostki = findViewById(R.id.gridKostki);

        buttonLosuj.setOnClickListener(v -> losujKostki());

        buttonRosnaco.setOnClickListener(v -> sortujRosnaco());

        buttonMalejaco.setOnClickListener(v -> sortujMalejaco());
    }

    private void losujKostki() {

        String tekst = editTextLiczbaKostek.getText().toString();

        if (tekst.isEmpty()) {
            Toast.makeText(this,
                    "Podaj liczbę kostek!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        int liczba;

        try {
            liczba = Integer.parseInt(tekst);
        } catch (NumberFormatException e) {
            Toast.makeText(this,
                    "Nieprawidłowa liczba!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        if (liczba < 1 || liczba > 100) {
            Toast.makeText(this,
                    "Podaj liczbę od 1 do 100!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        wyniki.clear();
        gridKostki.removeAllViews();

        for (int i = 0; i < liczba; i++) {

            int wynik = random.nextInt(6) + 1;
            wyniki.add(wynik);

            TextView kostka = new TextView(this);

            kostka.setText("⚄");
            kostka.setTextSize(30);
            kostka.setGravity(android.view.Gravity.CENTER);

            GridLayout.LayoutParams params =
                    new GridLayout.LayoutParams();

            params.width = GridLayout.LayoutParams.MATCH_PARENT;
            params.height = GridLayout.LayoutParams.WRAP_CONTENT;

            kostka.setLayoutParams(params);

            gridKostki.addView(kostka);
        }

        wyswietlWyniki();
    }

    private void wyswietlWyniki() {

        StringBuilder tekst = new StringBuilder();

        for (int wynik : wyniki) {
            tekst.append(wynik).append(" ");
        }

        textViewWyniki.setText(tekst.toString());
    }

    private void sortujRosnaco() {

        if (wyniki.isEmpty()) {
            Toast.makeText(this,
                    "Najpierw wylosuj kostki!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Collections.sort(wyniki);
        wyswietlWyniki();
    }

    private void sortujMalejaco() {

        if (wyniki.isEmpty()) {
            Toast.makeText(this,
                    "Najpierw wylosuj kostki!",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Collections.sort(wyniki, Collections.reverseOrder());
        wyswietlWyniki();
    }
}
