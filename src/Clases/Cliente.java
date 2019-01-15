package Clases;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a16alfonsofa
 */
public class Cliente {

    private String dni;
    private String nombre;
    private String direccion;
    private Set<Cuenta> cuenta;

    public Cliente() {
    }

    public Cliente(String dni, String nombre, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuenta = new HashSet<>(0);
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Set<Cuenta> getCuenta() {
        return cuenta;
    }

    public void setCuenta(Set<Cuenta> cuenta) {
        this.cuenta = cuenta;
    }

}
