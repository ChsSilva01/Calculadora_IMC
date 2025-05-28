package com.carlos.caculadora_imc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText editTextWeight = findViewById(R.id.edit_text_value);
        SeekBar seekBarHeight = findViewById(R.id.seek_info);
        Button buttonClear = findViewById(R.id.button_clear);
        Button buttonCalculate = findViewById(R.id.button_calculate);
        TextView textViewProgress = findViewById(R.id.textview_progress);
        TextView textViewResult = findViewById(R.id.textview_result);



        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextWeight.setText("");
                seekBarHeight.setProgress(0);
                textViewResult.setText("");

                textViewResult.setVisibility(View.GONE);
                textViewProgress.setVisibility(View.GONE);
            }
        });

        seekBarHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textViewProgress.setText(String.format(Locale.getDefault(), "%d cm", progress));
                textViewProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String weightText = editTextWeight.getText().toString();
                    double height = (double) seekBarHeight.getProgress() / 100;
                    if (height == 0){
                        Toast.makeText(getApplicationContext(), "Informe uma altura válida", Toast.LENGTH_SHORT).show();
                    } else {
                        double weight = Double.parseDouble(weightText);
                        double result = (double) weight / (height * height);

                        textViewResult.setText(String.format(Locale.getDefault(), "IMC: %.2f", result));
                        textViewResult.setVisibility(View.VISIBLE);
                    }
                } catch (Exception e){
                    // Exibe uma mensagem temporária na tela solicitando que o usuário informe um peso válido
                    Toast.makeText(getApplicationContext(), "Informe um peso válido", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}