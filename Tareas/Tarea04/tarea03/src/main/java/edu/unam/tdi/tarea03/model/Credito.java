package edu.unam.tdi.tarea03.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "creditos")
public class Credito {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;
    private String moneda;              // "Soles (S/.)" o "Dólares (US$)"
    private BigDecimal monto;
    private Integer periodoMeses;
    private BigDecimal cuota;           // monto de cada cuota
    private BigDecimal tea;             // % anual (ej. 30.0)
    private LocalDate fechaVenc;

    // Getters/Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getMoneda() { return moneda; }
    public void setMoneda(String moneda) { this.moneda = moneda; }

    public BigDecimal getMonto() { return monto; }
    public void setMonto(BigDecimal monto) { this.monto = monto; }

    public Integer getPeriodoMeses() { return periodoMeses; }
    public void setPeriodoMeses(Integer periodoMeses) { this.periodoMeses = periodoMeses; }

    public BigDecimal getCuota() { return cuota; }
    public void setCuota(BigDecimal cuota) { this.cuota = cuota; }

    public BigDecimal getTea() { return tea; }
    public void setTea(BigDecimal tea) { this.tea = tea; }

    public LocalDate getFechaVenc() { return fechaVenc; }
    public void setFechaVenc(LocalDate fechaVenc) { this.fechaVenc = fechaVenc; }
}
