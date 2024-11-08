package org.example;

import org.example.model.Exam;
import org.example.model.Pregunta;
import org.example.model.Respuesta;
import org.fusesource.jansi.AnsiConsole;

import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Exam> exams = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Bienvenido!");
        System.out.println("Ingrese su nombre:");
        String catedratico = validarEntrada();
        while (catedratico != null) {
            System.out.println("************ OPCIONES ************");
            System.out.println("1. Crear examen");
            System.out.println("2. Eliminar examen");
            System.out.println("3. Modificar examen");
            System.out.println("4. Ver examenes");
            System.out.println("5. Salir");
            Integer opcion = validarEntero();

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nombre del curso:");
                    String curso = validarEntrada();
                    Exam examen = new Exam(exams.size(), curso, catedratico);
                    System.out.println("Ingrese la cantidad de preguntas");
                    Integer preguntas = validarEntero();
                    for (int i = 1; i <= preguntas; i++) {
                        System.out.println("pregunta no." + i);
                        String valorPregunta = validarEntrada();
                        Integer opcionRespuestas = obtenerCantidadRespuestas();
                        Pregunta pregunta = new Pregunta(examen.obtenerIdPregunta(), valorPregunta);
                        if (opcionRespuestas == 1) {
                            pregunta.setEsBoolean(true);
                            System.out.println("¿Verdadero es su respuesta correcta? s/n");
                            String respuesta = validarEntrada();
                            if (respuesta.equals("s")) {
                                pregunta.agregarRespuestaCorrecta(0);
                            } else {
                                pregunta.agregarRespuestaCorrecta(1);
                            }

                        } else {
                            pregunta.setEsBoolean(false);
                            System.out.println("*********INGRESE LAS POSIBLES RESPUESTAS*********");
                            for (int j = 0; j < opcionRespuestas; j++) {
                                System.out.println("Ingrese una posible respuesta:");
                                String respuestas = validarEntrada();
                                Respuesta respuesta = new Respuesta(j, respuestas);
                                pregunta.agregarRespuestas(respuesta);
                            }
                        }
                        examen.agregarPregunta(pregunta);
                    }
                    exams.add(examen);
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del curso:");
                    String nombreCurso = validarEntrada();
                    try {
                        if (exams.size() <= 0) {
                            throw new NoSuchElementException();
                        } else {
                            Iterator<Exam> iterator = exams.iterator();
                            boolean examFound = false;

                            while (iterator.hasNext()) {
                                Exam exam = iterator.next();
                                if (exam.getCurso().equals(nombreCurso)) {
                                    iterator.remove();
                                    examFound = true;
                                    System.out.println("Examen Eliminado Correctamente");
                                }
                            }

                            if (!examFound) {
                                System.out.println("No existe el examen");
                            }
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("No existe el examen");
                    }
                    break;
                case 3:
                    if (exams.size() == 0) {
                        System.out.println("No existe examen a modificar");
                        break;
                    }
                    System.out.println("Ingrese el nombre del curso:");
                    String cursoAModificar = validarEntrada();
                    Exam examenModificar = null;
                    try {
                        if (exams.size() <= 0) {
                            throw new NoSuchElementException();
                        } else {
                            Iterator<Exam> iterator = exams.iterator();

                            while (iterator.hasNext()) {
                                Exam exam = iterator.next();
                                if (exam.getCurso().equals(cursoAModificar)) {
                                    examenModificar = exam;
                                }
                            }
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("No existe el examen");
                    }

                    examenModificar.listarPreguntas();

                    System.out.println("1. Eliminar pregunta");
                    System.out.println("2. Agregar pregunta");

                    String opModificar = validarEntrada();
                    List<Pregunta> preguntasModificar = examenModificar.obtenerPreguntas();

                    if (opModificar.equals("1")) {
                        System.out.println("Ingrese el id de la pregunta");
                        Integer idPregunta = validarEntero();

                        try {
                            if (preguntasModificar.size() <= 0) {
                                throw new NoSuchElementException();
                            } else {
                                Iterator<Pregunta> iterator = preguntasModificar.iterator();

                                boolean preguntaFound = false;

                                while (iterator.hasNext()) {
                                    Pregunta pregunta = iterator.next();
                                    if (pregunta.getIdPregunta().equals(idPregunta)) {
                                        iterator.remove();
                                        preguntaFound = true;
                                        System.out.println("Pregunta Eliminada Correctamente");
                                    }
                                }
                            }
                        } catch (NoSuchElementException e) {
                            System.out.println("No existe el id de la pregunta");
                        }

                    }

                    if (opModificar.equals("2")) {
                        System.out.println("Ingrese su pregunta");
                        String pregunta = validarEntrada();
                        Pregunta nuevaPregunta = new Pregunta(preguntasModificar.size(), pregunta);
                        Integer opcionRespuestas = obtenerCantidadRespuestas();
                        if (opcionRespuestas == 1) {
                            nuevaPregunta.setEsBoolean(true);
                            System.out.println("¿Verdadero es su respuesta correcta? s/n");
                            String respuesta = validarEntrada();
                            if (respuesta.equals("s")) {
                                nuevaPregunta.agregarRespuestaCorrecta(0);
                            } else {
                                nuevaPregunta.agregarRespuestaCorrecta(1);
                            }

                        } else {
                            nuevaPregunta.setEsBoolean(false);
                            System.out.println("*********INGRESE LAS POSIBLES RESPUESTAS*********");
                            for (int j = 0; j < opcionRespuestas; j++) {
                                System.out.println("Ingrese una posible respuesta:");
                                String respuestas = validarEntrada();
                                Respuesta respuesta = new Respuesta(j, respuestas);
                                nuevaPregunta.agregarRespuestas(respuesta);
                            }
                        }

                        exams.remove(examenModificar);
                        examenModificar.agregarPregunta(nuevaPregunta);
                        exams.add(examenModificar);
                    }

                    break;
                case 4:
                    if (exams.size() == 0) {
                        System.out.println("NO EXISTEN EXAMENES");
                    } else {
                        for (Exam exam : exams) {
                            System.out.println(exam.getCurso());
                        }
                    }
                    break;
                case 5:
                    return;
                default:
                    System.out.println("opcion no valida");
            }
            AnsiConsole.systemInstall();
            System.out.print("\033[H\033[2J");
            System.out.flush();
            AnsiConsole.systemUninstall();
        }
    }

    private static Integer obtenerCantidadRespuestas() {
        while (true) {
            System.out.println("Ingrese la cantidad de respuestas de la pregunta(maximo 5 y 1 = verdadero/falso):");
            Integer opcionRespuestas = validarEntero();
            if (opcionRespuestas > 5) {
                System.out.println("No se aceptan más de 5 preguntas, Inténtelo de nuevo: ");
            } else if (opcionRespuestas <= 0) {
                System.out.println("Cantidad no valida, Inténtelo de nuevo: ");
            }
            return opcionRespuestas;
        }
    }

    private static String validarEntrada() {
        while (true) {
            try {
                String entrada = scanner.nextLine().trim();
                if (entrada.isEmpty()) {
                    System.out.println("La entrada no puede estar vacía. Inténtelo de nuevo:");
                    continue;
                } else {
                    return entrada;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error al leer la entrada. Inténtelo de nuevo:");
            } catch (RuntimeException e) {
                System.out.println("Ocurrió un error inesperado. Inténtelo de nuevo:");
            }
        }
    }

    public static Integer validarEntero() {
        while (true) {
            try {
                if (scanner.hasNextInt()) {
                    return scanner.nextInt();
                } else {
                    System.out.println("La entrada solo puede contener números enteros. Inténtelo de nuevo:");
                    scanner.next();
                }
            } catch (NoSuchElementException e) {
                System.out.println("Error al leer la entrada. Inténtelo de nuevo:");
            } catch (RuntimeException e) {
                System.out.println("Ocurrió un error inesperado. Inténtelo de nuevo:");
            } finally {
                scanner.nextLine();
            }
        }
    }
}
