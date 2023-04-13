package com.example.calculomediadisciplina;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculomediadisciplina.util.AfterTextChangeWatcher;

public class ResultadoMedia extends AppCompatActivity {

    TextView textViewAluno, textViewMedia, textViewSituacao;
    EditText editTextAvalicaoSubstitutiva;
    Button buttonCalcularAS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_media);
        inicializarComponentes();
    }

    /**
     *
     */
    private void inicializarComponentes(){
        Bundle valores = getIntent().getExtras();
        float media = (valores.getFloat("notaA1") + valores.getFloat("notaA2")) / 2.0f;

        String stringAluno = "Aluno: " + valores.getString("nomeAluno");
        String stringMedia = "Média: " + media;
        String stringSituacao = media >= 6 ? "Aprovado" : (media >= 4 ? "Avaliação Substituitiva" : "Reprovado");

        textViewAluno = findViewById(R.id.textViewAluno);
        textViewAluno.setText(stringAluno);

        textViewMedia = findViewById(R.id.textViewMedia);
        textViewMedia.setText(stringMedia);

        textViewSituacao = findViewById(R.id.textViewSituacao);
        textViewSituacao.setText(stringSituacao);

        editTextAvalicaoSubstitutiva = findViewById(R.id.editTextAvalicaoSubstitutiva);
        editTextAvalicaoSubstitutiva.addTextChangedListener(new AfterTextChangeWatcher(this::atualizarBotao));

        buttonCalcularAS = findViewById(R.id.buttonCalcularAS);
        buttonCalcularAS.setOnClickListener(this::onClickButton);
    }

    /**
     *
     * @param view
     */
    private void onClickButton(View view){
        Intent intent = new Intent(getApplicationContext(), ResultadoAS.class);
        intent.putExtras(getIntent().getExtras());
        intent.putExtra("notaAS", Float.parseFloat(editTextAvalicaoSubstitutiva.getText().toString()));
        startActivity(intent);
    }

    /**
     * Atualiza o estado do botão caso necessário.
     */
    private void atualizarBotao(Editable editable){
        buttonCalcularAS.setEnabled(!editTextAvalicaoSubstitutiva.getText().toString().equals(""));
    }
}