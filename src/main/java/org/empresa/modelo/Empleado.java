package org.empresa.modelo;

/**
 * Representa un empleado de la empresa con sus datos básicos.
 * <p>
 * Cada empleado tiene un identificador único (id), un nombre y un DNI.
 * Esta clase se utiliza principalmente como modelo de datos para DAO y JSPs.
 * </p>
 */
public class Empleado {

    /**
     * Identificador único del empleado en la base de datos.
     */
    private int id;

    /**
     * Nombre completo del empleado.
     */
    private String nombre;

    /**
     * DNI del empleado, único en la base de datos.
     */
    private String dni;

    /**
     * Constructor vacío por defecto.
     * Permite crear un objeto Empleado sin inicializar sus atributos.
     */
    public Empleado() {
    }

    /**
     * Constructor con todos los atributos.
     *
     * @param id     Identificador único del empleado.
     * @param nombre Nombre completo del empleado.
     * @param dni    DNI del empleado.
     */
    public Empleado(int id, String nombre, String dni) {
        this.id = id;
        this.nombre = nombre;
        this.dni = dni;
    }

    /**
     * Obtiene el nombre del empleado.
     *
     * @return Nombre completo del empleado.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del empleado.
     *
     * @param nombre Nombre completo a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el identificador del empleado.
     *
     * @return ID del empleado.
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el identificador del empleado.
     *
     * @param id ID a asignar.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el DNI del empleado.
     *
     * @return DNI del empleado.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el DNI del empleado.
     *
     * @param dni DNI a asignar.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }
}


