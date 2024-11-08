package org.example.model;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.List;

public class Pregunta {
    private Integer idPregunta;
    private String pregunta;
    private Boolean esBoolean;
    private Respuesta respuestaCorrecta;
    private List<Respuesta> respuestas;

    public Pregunta(Integer idpregunta, String pregunta) {
        this.respuestaCorrecta = null;
        this.respuestas = new ArrayList<>();
        this.idPregunta = idpregunta;
        this.pregunta = pregunta;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public Boolean getEsBoolean() {
        return esBoolean;
    }

    public void setEsBoolean(Boolean esBoolean) {
        this.esBoolean = esBoolean;

        if (esBoolean) {
            Respuesta respuestaDefault = new Respuesta(0,"Verdadero");
            respuestas.add(respuestaDefault);
            Respuesta anotherRespuesta = new Respuesta(1,"Falso");
            respuestas.add(anotherRespuesta);
        }
    }

    public Respuesta getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void agregarRespuestaCorrecta(Integer idRespuestaCorrecta) {
        respuestas.forEach(respuesta -> {
            if (idRespuestaCorrecta.equals(respuesta.getIdRespuesta())) {
                Respuesta respuestaCorrecta = new Respuesta();
                respuestaCorrecta.setIdRespuesta(respuesta.getIdRespuesta());
                respuestaCorrecta.setRespuesta(respuesta.getRespuesta());
                this.respuestaCorrecta = respuestaCorrecta;

            }
        });
    }

    public List<Respuesta> obtenerRespuestas() {
        return respuestas;
    }

    public void agregarRespuestas(Respuesta respuestas) {
        try {
            if(this.respuestas.size() >= 5) {
                throw new RuntimeException("Capacidad maxima de respuestas alcanzada");
            }
            this.respuestas.add(respuestas);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarRespuestas() {
        if(this.respuestas.size() == 0) {
            System.out.println("No hay respuestas agregadas");
            return;
        }

        System.out.println("********RESPUESTAS********");
        respuestas.forEach(respuesta -> System.out.println(respuesta.toString()));
    }

    public void listarRespuestaCorrecta() {
        if(this.respuestaCorrecta == null) {
            System.out.println("No hay respuesta agregada");
            return;
        }

        System.out.println("********RESPUESTA CORRECTA********");
        System.out.println(respuestaCorrecta.toString());
    }

    public void  listarPregunta() {
        System.out.println("id: " + idPregunta + " - " + pregunta);
        listarRespuestas();
    }
}
