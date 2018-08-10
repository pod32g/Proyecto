package com.pod32g;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Aprendizaje implements Resultado {

    private List<Integer[]> respuestas = new LinkedList<Integer[]>();

    private int visual = 0;
    private int auditivo = 0;
    private int kinestesico = 0;

    public int analizarRespuestas(List<Integer> respuestas) {
        return respuestas.indexOf(Collections.max(respuestas));
    }

    public List<Integer> sumarRespuestas(List<Integer[]> respuestas) {
        List<Integer> sumas = new LinkedList<Integer>();

        //Visual
        for(int i = 0; i < respuestas.get(0).length; i++) {
            this.visual += respuestas.get(0)[i];
        }

        //Auditivo
        for(int i = 0; i < respuestas.get(1).length; i++) {
            this.auditivo += respuestas.get(1)[i];
        }

        //Kinestesico
        for (int i = 0; i < respuestas.get(2).length; i++) {
            this.kinestesico += respuestas.get(2)[i];
        }

        //Agregar las sumas a la lista
        sumas.add(this.visual);
        sumas.add(this.auditivo);
        sumas.add(this.kinestesico);

        return sumas;
    }

    public List<Integer[]> procesarRespuestas(String respuestas) {
        Gson gson = new Gson();
        Object json = gson.fromJson(respuestas, JsonObject.class);

        JsonArray visual = ((JsonObject) json).get("visual").getAsJsonArray();
        JsonArray auditivo = ((JsonObject) json).get("auditivo").getAsJsonArray();
        JsonArray kinestesico = ((JsonObject) json).get("kinestesico").getAsJsonArray();

        Integer[] visualAns = new Integer[visual.size()];
        Integer[] auditivoAns = new Integer[auditivo.size()];
        Integer[] kinestesicoAns = new Integer[kinestesico.size()];

        for (int i = 0; i < visual.size(); i++) {
            visualAns[i] = visual.get(i).getAsInt();
        }

        for(int i = 0; i < auditivo.size(); i++) {
            auditivoAns[i] = auditivo.get(i).getAsInt();
        }

        for(int i = 0; i < kinestesico.size(); i++) {
            kinestesicoAns[i] = kinestesico.get(i).getAsInt();
    }

        this.respuestas.add(visualAns);
        this.respuestas.add(auditivoAns);
        this.respuestas.add(kinestesicoAns);

        return this.respuestas;
}

    public static void main(String[] args) {
        Aprendizaje aprendizaje = new Aprendizaje();
        String jsonTest = "{\n" +
                "    \"visual\" : [0, 0, 0],\n" +
                "    \"auditivo\" : [1, 2, 9],\n" +
                "    \"kinestesico\" : [1, 2, 3]\n" +
                "}";

        System.out.println(aprendizaje.analizarRespuestas(aprendizaje.sumarRespuestas(aprendizaje.procesarRespuestas(jsonTest))));
    }

}
