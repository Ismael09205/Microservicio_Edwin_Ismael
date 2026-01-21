package com.Producto.API_Producto.util;
import java.text.Normalizer;
public class NormalizerText {
    public static String normalizar(String texto){
        return Normalizer.normalize(texto,Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .toLowerCase()
                .trim();
    }
}
