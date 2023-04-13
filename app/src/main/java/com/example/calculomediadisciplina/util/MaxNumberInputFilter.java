package com.example.calculomediadisciplina.util;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Filtro o campo TextEditNumber limitando os números em um número máximo.
 */
public class MaxNumberInputFilter implements InputFilter {

    private int max;

    /**
     * Construtor default recebendo o numero máximo do campo de numero.
     * @param max valor máximo.
     */
    public MaxNumberInputFilter(int max) {
        this.max = max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            // Obtém a string completa com os novos caracteres que estão no source e o que já havia sido digitado no dest
            String stringDigitada = source.subSequence(start, end).toString();
            StringBuilder stringCompleta = new StringBuilder(dest).replace(dstart, dend, stringDigitada);

            //Valida se o número é menor ou igual ao máximo
            int numero = Integer.parseInt(stringCompleta.toString());
            if (numero <= max) {
                return null;
            }
        } catch (NumberFormatException e) {
            // Ignora caso um erro ocorra
        }
        return "";
    }

}
