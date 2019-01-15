/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a16alfonsofa
 */
public class CuentaCorriente extends Cuenta {

    private Set<Movimiento> movimientos;

    public CuentaCorriente() {
    }

    public CuentaCorriente(String Numero, String Sucursal, Double SaldoActual) {
        super(Numero, Sucursal, SaldoActual);
        this.movimientos = new HashSet<>(0);
    }

    public Set<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Set<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

}
