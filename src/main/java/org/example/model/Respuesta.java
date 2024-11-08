package org.example.model;

public class Respuesta {
    private Integer idRespuesta = null;
    private String respuesta;

    public Respuesta(Integer idRespuesta, String respuesta) {
        this.idRespuesta = idRespuesta;
        this.respuesta = respuesta;
    }

    public Respuesta() {}

    public Integer getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "idRespuesta=" + idRespuesta +
                ", respuesta='" + respuesta + '\'';
    }
}
