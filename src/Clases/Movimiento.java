package Clases;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author a16alfonsofa
 */
public class Movimiento {

    private String numeroCta;
    private LocalDate fechaOperacion;
    private LocalTime  hora;
    private float cantidad;
    private Double SaldoAnterior;

    public Movimiento() {
    }

    public Movimiento(String numeroCta, LocalDate fechaOperacion, LocalTime hora, float cantidad, Double SaldoAnterior) {
        this.numeroCta = numeroCta;
        this.fechaOperacion = fechaOperacion;
        this.hora = hora;
        this.cantidad = cantidad;
        this.SaldoAnterior = SaldoAnterior;
    }

    public String getNumeroCta() {
        return numeroCta;
    }

    public void setNumeroCta(String numeroCta) {
        this.numeroCta = numeroCta;
    }

    public LocalDate getFechaOperacion() {
        return fechaOperacion;
    }

    public void setFechaOperacion(LocalDate fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public Double getSaldoAnterior() {
        return SaldoAnterior;
    }

    public void setSaldoAnterior(Double SaldoAnterior) {
        this.SaldoAnterior = SaldoAnterior;
    }

}
