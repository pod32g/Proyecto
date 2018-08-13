package com.pod32g;

import static spark.Spark.*;

public class Rest {

    public void setEndpoints() {
        CuestionarioAprendizaje cuestionarioAprendizaje = new CuestionarioAprendizaje();
        Aprendizaje aprendizaje = new Aprendizaje();

        get("/Cuestionario", (request, response) -> {
            response.type("application/json");
            return cuestionarioAprendizaje.generarCuestionario();
        });
        post("/Cuestionario/Procesar","application/json", (request, response) -> {
            return aprendizaje.obtenerTipoAprendizaje(aprendizaje.analizarRespuestas(request.body()));
        });
    }

    public static void main(String[] args) {
        Rest rest = new Rest();
        rest.setEndpoints();
    }
}
