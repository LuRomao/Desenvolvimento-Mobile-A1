package com.example.calculomediadisciplina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculomediadisciplina.util.AfterTextChangeWatcher;

/**
 * Corresponde à tela de apresentação da média do aluno.
 */
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
     * Inicializa e obtem os componentes da tela.
     */
    private void inicializarComponentes(){
        Bundle valores = getIntent().getExtras();
        float media = (valores.getFloat("notaA1") + valores.getFloat("notaA2")) / 2.0f;

        String stringSituacao = media >= 6 ? "Aprovado" : (media >= 4 ? "Avaliação Substituitiva" : "Reprovado");

        textViewAluno = findViewById(R.id.textViewAluno);
        textViewAluno.setText(valores.getString("nomeAluno"));

        textViewMedia = findViewById(R.id.textViewMedia);
        textViewMedia.setText(String.valueOf(media));

        textViewSituacao = findViewById(R.id.textViewSituacao);
        textViewSituacao.setText(stringSituacao);

        editTextAvalicaoSubstitutiva = findViewById(R.id.editTextAvalicaoSubstitutiva);
        editTextAvalicaoSubstitutiva.addTextChangedListener(new AfterTextChangeWatcher(this::atualizarBotao));

        buttonCalcularAS = findViewById(R.id.buttonCalcularAS);
        buttonCalcularAS.setOnClickListener(this::onClickButton);
    }

    /**
     * Acessa a outra tela ao clicar no botão de calcular avaliação substitutiva.
     * @param view view do botão.
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