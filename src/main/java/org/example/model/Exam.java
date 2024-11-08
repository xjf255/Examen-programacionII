package org.example.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Exam {
    private Integer id;
    private String curso;
    private String catedratico;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private List<Pregunta> preguntas = new ArrayList<>();

    public Exam(Integer id, String curso, String catedratico) {
        this.id = id;
        this.curso = curso;
        this.catedratico = catedratico;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        setFechaActualizacion();
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
        setFechaActualizacion();
    }

    public String getCatedratico() {
        return catedratico;
    }

    public void setCatedratico(String catedratico) {
        this.catedratico = catedratico;
        setFechaActualizacion();
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    private void setFechaActualizacion() {
        this.fechaActualizacion = LocalDateTime.now();
    }

    public List<Pregunta> obtenerPreguntas() {
        return preguntas;
    }

    public Integer obtenerIdPregunta() { return this.preguntas.size(); }

    public void agregarPregunta(Pregunta preguntas) {
        this.preguntas.add(preguntas);
        setFechaActualizacion();
    }

    public boolean eliminarPregunta(Pregunta preguntas) {
        try {
            this.preguntas.remove(preguntas);
            setFechaActualizacion();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Pregunta no encontrada");
        }
    }

    public void listarPreguntas() {
        if(preguntas != null && preguntas.size() > 0) {
            preguntas.forEach(pregunta -> pregunta.listarPregunta());
        }
    }
}
