package Clases;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a16alfonsofa
 */
public class Cuenta {

    private String Numero;
    private String Sucursal;
    private Set<Cliente> cliente;
    private Double SaldoActual;

    public Cuenta() {
    }

    public Cuenta(String Numero, String Sucursal, Double SaldoActual) {
        this.Numero = Numero;
        this.Sucursal = Sucursal;
        this.SaldoActual = SaldoActual;
        this.cliente = new HashSet<>(0);
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public String getSucursal() {
        return Sucursal;
    }

    public void setSucursal(String Sucursal) {
        this.Sucursal = Sucursal;
    }

    public Set<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(Set<Cliente> cliente) {
        this.cliente = cliente;
    }

    public Double getSaldoActual() {
        return SaldoActual;
    }

    public void setSaldoActual(Double SaldoActual) {
        this.SaldoActual = SaldoActual;
    }

}
