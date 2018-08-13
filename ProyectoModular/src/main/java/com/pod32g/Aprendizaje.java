package com.pod32g;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Aprendizaje implements Resultado {

    private List<Integer[]> respuestas = new LinkedList<Integer[]>();

    public String obtenerTipoAprendizaje(int tipo){
        switch(tipo){
            case 0:
                return "Visual";
            case 1:
                return "Auditivo";
            case 2:
                return "Kinestesico";
        }
        return null;
    }

    public int analizarRespuestas(String respuestasJSON) {
        List<Integer[]> respuestas = procesarRespuestas(respuestasJSON);
        List<Integer> tipos = sumarRespuestas(respuestas);
        return tipos.indexOf(Collections.max(tipos));
    }
      
    public void guardarResultados(String codigo, String resultado) {
        MySQLDB mySQLDB = new MySQLDB();
        mySQLDB.runInsertQuery(String.format("INSERT INTO Cuestionario(codigo, resultado) VALUES ('%s', '%s')",
                codigo, resultado));
        mySQLDB.close();
    }

    public List<Integer> sumarRespuestas(List<Integer[]> respuestas) {
        int visual = 0, auditivo = 0, kinestesico = 0;
        List<Integer> sumas = new LinkedList<Integer>();

        //Visual
        for(int i = 0; i < respuestas.get(0).length; i++) {
            visual += respuestas.get(0)[i];
        }

        //Auditivo
        for(int i = 0; i < respuestas.get(1).length; i++) {
            auditivo += respuestas.get(1)[i];
        }

        //Kinestesico
        for (int i = 0; i < respuestas.get(2).length; i++) {
            kinestesico += respuestas.get(2)[i];
        }

        //Agregar las sumas a la lista
        sumas.add(visual);
        sumas.add(auditivo);
        sumas.add(kinestesico);

        return sumas;
    }

    public List<Integer[]> procesarRespuestas(String respuestas) {
        Gson gson = new Gson();
        JsonObject json = gson.fromJson(respuestas, JsonObject.class);
        this.respuestas.clear();

        JsonArray visual = json.get("visual").getAsJsonArray();
        JsonArray auditivo = json.get("auditivo").getAsJsonArray();
        JsonArray kinestesico = json.get("kinestesico").getAsJsonArray();

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
                "    \"visual\" : [0, 0, 10],\n" +
                "    \"auditivo\" : [1, 2, 0],\n" +
                "    \"kinestesico\" : [1, 2, 3]\n" +
                "}";

        System.out.println(aprendizaje.analizarRespuestas(jsonTest));
    }

}
