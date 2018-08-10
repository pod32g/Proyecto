package com.pod32g;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class CuestionarioAprendizaje implements Cuestionarios {


    public String generarCuestionario() throws FileNotFoundException {
        Gson gson = new Gson();
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        BufferedReader reader = new BufferedReader(new FileReader(classLoader.getResource("static/preguntas.json").getFile()));
        Object json = gson.fromJson(reader, JsonObject.class);
        Map<String, List> jsonMap  = new HashMap<String, List>();

        JsonArray visual = ((JsonObject) json).get("visual").getAsJsonArray();
        JsonArray auditivo = ((JsonObject) json).getAsJsonArray("auditivo");
        JsonArray kinestesico = ((JsonObject) json).getAsJsonArray("kinestesico");

        List visualLista = gson.fromJson(visual, ArrayList.class);
        List auditivoLista = gson.fromJson(auditivo, ArrayList.class);
        List kinestesicoLista = gson.fromJson(kinestesico, ArrayList.class);

        Collections.shuffle(visualLista);
        Collections.shuffle(auditivoLista);
        Collections.shuffle(kinestesicoLista);

        jsonMap.put("visual", visualLista);
        jsonMap.put("auditivo", auditivoLista);
        jsonMap.put("kinestesico", kinestesicoLista);

        Gson gsonBuilder = new GsonBuilder().create();

        return gsonBuilder.toJson(jsonMap);
    }

    public static void main(String[] args) throws FileNotFoundException {
        //CuestionarioAprendizaje cuestionarioAprendizaje = new CuestionarioAprendizaje();
        //System.out.println(cuestionarioAprendizaje.toJson(cuestionarioAprendizaje.generarCuestionario()));
    }
}
