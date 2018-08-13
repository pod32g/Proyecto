package com.pod32g;

import java.util.List;

public interface Resultado {

    public int analizarRespuestas(String respuestas);
    public List<Integer> sumarRespuestas(List<Integer[]> respuestas);
    public List<Integer[]> procesarRespuestas(String respuestas);
    public void guardarResultados(String codigo, String resultado);
}
