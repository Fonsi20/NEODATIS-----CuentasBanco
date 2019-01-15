package Clases;

import java.util.Date;

/**
 *
 * @author a16alfonsofa
 */
public class CuentaPlazo extends Cuenta {

    private float intereses;
    private Date fechaVencimiento;

    public CuentaPlazo() {
    }

    public CuentaPlazo(float intereses, Date fechaVencimiento, String Numero, String Sucursal, Double SaldoActual) {
        super(Numero, Sucursal, SaldoActual);
        this.intereses = intereses;
        this.fechaVencimiento = fechaVencimiento;
    }

    public float getIntereses() {
        return intereses;
    }

    public void setIntereses(float intereses) {
        this.intereses = intereses;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

}
