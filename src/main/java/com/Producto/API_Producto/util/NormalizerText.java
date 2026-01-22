package com.Producto.API_Producto.util;
import java.text.Normalizer;

public class NormalizerText {

    public static String normalizar(String texto){

        if (texto == null || texto.isBlank()){
            return null;
        }
        return Normalizer.normalize(texto,Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .toLowerCase()
                .trim();
    }
}
