package com.example.calculomediadisciplina.util;

import android.text.Editable;
import android.text.TextWatcher;

import java.util.function.Consumer;

/**
 * Classe para facilitar a implementação do text watcher apenas após a mudança do texto.
 */
public class AfterTextChangeWatcher implements TextWatcher {

    private Consumer<Editable> function;

    /**
     * Construtor obrigatório que recebe uma função que é subsequentemente chamada após a mudança de texto.
     * @param function função executada após a mudança de texto.
     */
    public AfterTextChangeWatcher(Consumer<Editable> function) {
        this.function = function;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        function.accept(s);
    }
}
