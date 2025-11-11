package org.empresa.modelo;

import java.time.LocalDate;

/**
 * Representa la nómina de un empleado en la empresa.
 * <p>
 * Contiene información sobre el salario, fecha de pago y horas trabajadas
 * de un empleado específico identificado por su ID.
 * </p>
 */
public class Nomina {

    /**
     * Identificador único de la nómina en la base de datos.
     */
    private int id;

    /**
     * Identificador del empleado al que pertenece esta nómina.
     */
    private int empleadoId;

    /**
     * Salario correspondiente a la nómina.
     */
    private double salario;

    /**
     * Fecha de pago de la nómina.
     */
    private LocalDate fechaPago;

    /**
     * Cantidad de horas trabajadas en el período correspondiente.
     */
    private int horasTrabajadas;

    /**
     * Constructor vacío por defecto.
     * Permite crear un objeto Nomina sin inicializar sus atributos.
     */
    public Nomina() {
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param id             Identificador único de la nómina.
     * @param empleadoId     ID del empleado asociado.
     * @param salario        Salario de la nómina.
     * @param fechaPago      Fecha de pago.
     * @param horasTrabajadas Horas trabajadas durante el período.
     */
    public Nomina(int id, int empleadoId, double salario, LocalDate fechaPago, int horasTrabajadas) {
        this.id = id;
        this.empleadoId = empleadoId;
        this.salario = salario;
        this.fechaPago = fechaPago;
        this.horasTrabajadas = horasTrabajadas;
    }

    /**
     * Obtiene el ID de la nómina.
     *
     * @return Identificador de la nómina.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID de la nómina.
     *
     * @param id ID a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el ID del empleado asociado a esta nómina.
     *
     * @return ID del empleado.
     */
    public int getEmpleadoId() {
        return empleadoId;
    }

    /**
     * Establece el ID del empleado asociado a esta nómina.
     *
     * @param empleadoId ID del empleado.
     */
    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    /**
     * Obtiene el salario de la nómina.
     *
     * @return Salario del empleado.
     */
    public double getSalario() {
        return salario;
    }

    /**
     * Establece el salario de la nómina.
     *
     * @param salario Salario a asignar.
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * Obtiene la fecha de pago de la nómina.
     *
     * @return Fecha de pago.
     */
    public LocalDate getFechaPago() {
        return fechaPago;
    }

    /**
     * Establece la fecha de pago de la nómina.
     *
     * @param fechaPago Fecha a asignar.
     */
    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    /**
     * Obtiene las horas trabajadas en el período correspondiente.
     *
     * @return Horas trabajadas.
     */
    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    /**
     * Establece las horas trabajadas en el período correspondiente.
     *
     * @param horasTrabajadas Horas a asignar.
     */
    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
}
