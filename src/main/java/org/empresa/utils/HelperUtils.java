package org.empresa.utils;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Clase de utilidades estáticas para formateo y validación de datos del proyecto.
 * <p>
 * Contiene métodos para formatear salarios, fechas y validar DNIs de empleados.
 * Todos los métodos son estáticos y no requieren instanciar la clase.
 * </p>
 */
public class HelperUtils {

    /** Formateador de decimales para salarios (2 decimales). */
    private static final DecimalFormat df = new DecimalFormat("#.00");

    /** Formateador de fechas en el patrón dd/MM/yyyy. */
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Formatea un salario con 2 decimales y añade el símbolo €.
     *
     * @param salario Salario a formatear
     * @return Salario formateado como String (ejemplo: "1234.56 €")
     */
    public static String formatearSalario(double salario) {
        return df.format(salario) + " €";
    }
}


