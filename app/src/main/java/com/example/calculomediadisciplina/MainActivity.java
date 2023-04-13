package com.example.calculomediadisciplina;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.calculomediadisciplina.util.AfterTextChangeWatcher;

public class MainActivity extends AppCompatActivity {

    EditText editTextNome, editTextA1, editTextA2;
    Button buttonCalcularMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializarComponentes();
    }

    /**
     * Inicializa e obtem os componentes da tela.
     */
    private void inicializarComponentes(){
        AfterTextChangeWatcher afterTextChanged = new AfterTextChangeWatcher(this::atualizarBotao);

        editTextNome = findViewById(R.id.editTextNome);
        editTextNome.addTextChangedListener(afterTextChanged);

        editTextA1 = findViewById(R.id.editTextA1);
        editTextA1.addTextChangedListener(afterTextChanged);

        editTextA2 = findViewById(R.id.editTextA2);
        editTextA2.addTextChangedListener(afterTextChanged);

        buttonCalcularMedia = findViewById(R.id.buttonCalcularMedia);
        buttonCalcularMedia.setOnClickListener(this::onClickButton);
    }

    /**
     *
     * @param view
     */
    private void onClickButton(View view){
        Intent intent = new Intent(getApplicationContext(), ResultadoMedia.class);
        intent.putExtra("nomeAluno", editTextNome.getText().toString());
        intent.putExtra("notaA1", Float.parseFloat(editTextA1.getText().toString()));
        intent.putExtra("notaA2", Float.parseFloat(editTextA2.getText().toString()));
        startActivity(intent);
    }

    /**
     * Atualiza o estado do botão caso necessário.
     */
    private void atualizarBotao(Editable editable){
        boolean deveEstarHabilitado = !editTextNome.getText().toString().equals("")
                && !editTextA1.getText().toString().equals("")
                && !editTextA2.getText().toString().equals("");

        buttonCalcularMedia.setEnabled(deveEstarHabilitado);
    }
}