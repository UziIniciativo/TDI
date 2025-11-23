package edu.unam.tdi.tarea03.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "movimientos_tarjeta")
public class MovTarjeta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String mes;
    private String tarjeta;
    private String transaccion;
    private BigDecimal gastos;

    private BigDecimal pagos;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }

    public String getTarjeta() { return tarjeta; }
    public void setTarjeta(String tarjeta) { this.tarjeta = tarjeta; }

    public String getTransaccion() { return transaccion; }
    public void setTransaccion(String transaccion) { this.transaccion = transaccion; }

    public BigDecimal getGastos() { return gastos; }
    public void setGastos(BigDecimal gastos) { this.gastos = gastos; }

    public BigDecimal getPagos() { return pagos; }
    public void setPagos(BigDecimal pagos) { this.pagos = pagos; }
}
