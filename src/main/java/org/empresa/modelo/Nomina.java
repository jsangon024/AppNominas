package org.empresa.modelo;

import java.time.LocalDate;

public class Nomina {
    private int id;
    private int empleadoId;
    private double salario;
    private LocalDate fechaPago;
    private int horasTrabajadas;

    public Nomina() {

    }

    public Nomina(int id, int empleadoId, double salario, LocalDate fechaPago, int horasTrabajadas) {
        this.id = id;
        this.empleadoId = empleadoId;
        this.salario = salario;
        this.fechaPago = fechaPago;
        this.horasTrabajadas = horasTrabajadas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }
}