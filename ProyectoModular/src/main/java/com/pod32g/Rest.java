package com.pod32g;

import static spark.Spark.*;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Rest {

    public void setEndpoints() {
        CuestionarioAprendizaje cuestionarioAprendizaje = new CuestionarioAprendizaje();
        Aprendizaje aprendizaje = new Aprendizaje();

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request
                    .headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers",
                        accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request
                    .headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods",
                        accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

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
