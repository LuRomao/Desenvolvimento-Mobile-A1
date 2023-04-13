package com.example.calculomediadisciplina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.calculomediadisciplina.util.AfterTextChangeWatcher;
import com.example.calculomediadisciplina.util.MaxNumberInputFilter;

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

        textViewAluno = findViewById(R.id.textViewAlunoMedia);
        textViewAluno.setText(valores.getString("nomeAluno"));

        textViewMedia = findViewById(R.id.textViewMediaMedia);
        textViewMedia.setText(String.valueOf(media));

        textViewSituacao = findViewById(R.id.textViewSituacaoMedia);
        textViewSituacao.setText(stringSituacao);

        editTextAvalicaoSubstitutiva = findViewById(R.id.editTextAvalicaoSubstitutiva);
        editTextAvalicaoSubstitutiva.addTextChangedListener(new AfterTextChangeWatcher(this::atualizarBotao));
        editTextAvalicaoSubstitutiva.setFilters(new InputFilter[] { new MaxNumberInputFilter(10)});

        buttonCalcularAS = findViewById(R.id.buttonCalcularAS);
        buttonCalcularAS.setOnClickListener(this::onClickButton);

        if(media < 4){
            editTextAvalicaoSubstitutiva.setEnabled(false);
            buttonCalcularAS.setEnabled(false);
        }
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