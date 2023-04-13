package com.example.calculomediadisciplina;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Corresponde à tela de apresentação da média utilizando a avaliação substitutiva.
 */
public class ResultadoAS extends AppCompatActivity {

    TextView textViewAluno, textViewMedia, textViewSituacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_as);

        inicializarComponentes();
    }

    /**
     * Inicializa e obtem os componentes da tela.
     */
    private void inicializarComponentes(){
        Bundle valores = getIntent().getExtras();
        float a1 = valores.getFloat("notaA1");
        float a2 = valores.getFloat("notaA2");
        float as = valores.getFloat("notaAS");
        float media = (a1 + Math.max(a2, as)) / 2;

        String stringSituacao = media >= 6 ? "Aprovado" : "Reprovado";

        textViewAluno = findViewById(R.id.textViewAlunoAS);
        textViewAluno.setText(valores.getString("nomeAluno"));

        textViewMedia = findViewById(R.id.textViewMediaAS);
        textViewMedia.setText(String.valueOf(media));

        textViewSituacao = findViewById(R.id.textViewSituacaoAS);
        textViewSituacao.setText(stringSituacao);
    }
}