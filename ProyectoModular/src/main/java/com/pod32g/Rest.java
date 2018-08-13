package com.pod32g;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Rest {

    public void setEndpoints() {
        CuestionarioAprendizaje cuestionarioAprendizaje = new CuestionarioAprendizaje();
        Aprendizaje aprendizaje = new Aprendizaje();

        get("/Cuestionario", (request, response) -> {
            response.type("application/json");
            return cuestionarioAprendizaje.generarCuestionario();
        });
        post("/Cuestionario/Procesar","application/json", (request, response) -> {
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(request.body(), JsonObject.class);
            String codigo = jsonObject.get("codigo").getAsString();
            String tipo = aprendizaje.obtenerTipoAprendizaje(aprendizaje.analizarRespuestas(request.body()));
            aprendizaje.guardarResultados(codigo, tipo);
            return tipo;
        });
    }



    public static void main(String[] args) {
        Rest rest = new Rest();
        rest.setEndpoints();
    }
}
