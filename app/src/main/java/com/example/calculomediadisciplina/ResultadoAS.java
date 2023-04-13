package com.example.calculomediadisciplina;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

public class ResultadoAS extends AppCompatActivity {

    TextView textViewAvaliacaoSubstitutiva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_as);

        inicializarComponentes();
    }

    @SuppressLint("SetTextI18n")
    private void inicializarComponentes(){
        Bundle bundle = getIntent().getExtras();
        float a1 = bundle.getFloat("notaA1");
        float a2 = bundle.getFloat("notaA2");
        float as = bundle.getFloat("notaAS");
        float media = (a1 + Math.max(a2, as)) / 2;

        textViewAvaliacaoSubstitutiva = findViewById(R.id.textViewAvaliacaoSubstitutiva);
        textViewAvaliacaoSubstitutiva.setText("Média: " + media);

    }
}