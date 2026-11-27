package com.upiicsa.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "historial_batallas")
public class HistorialBatalla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_ganador", nullable = false, length = 10)
    private String idGanador;

    @Column(name = "nombre_ganador", nullable = false, length = 30)
    private String nombreGanador;

    @Column(name = "nombre_perdedor", nullable = false, length = 30)
    private String nombrePerdedor;

    @Column(name = "turnos_totales", nullable = false)
    private int turnosTotales;

    @Column(name = "resumen_combate", length = 500)
    private String resumenCombate;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    // Constructor vacío obligatorio para Hibernate (instanciación por reflexión)
    public HistorialBatalla() {}

    public HistorialBatalla(Long id, String idGanador, String nombreGanador, String nombrePerdedor, int turnosTotales, String resumenCombate) {
        // TODO: Inicializar atributos y asignar LocalDateTime.now() a fechaRegistro
        this.id = id;
        this.idGanador = idGanador;
        this.nombreGanador = nombreGanador;
        this.nombrePerdedor = nombrePerdedor;
        this.turnosTotales = turnosTotales;
        this.resumenCombate = resumenCombate;
        this.fechaRegistro = LocalDateTime.now();
    }
    
    // TODO: Completar Getters y Setters encapsulados

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdGanador() {
        return idGanador;
    }

    public void setIdGanador(String idGanador) {
        this.idGanador = idGanador;
    }

    public String getNombreGanador() {
        return nombreGanador;
    }

    public void setNombreGanador(String nombreGanador) {
        this.nombreGanador = nombreGanador;
    }

    public String getNombrePerdedor() {
        return nombrePerdedor;
    }

    public void setNombrePerdedor(String nombrePerdedor) {
        this.nombrePerdedor = nombrePerdedor;
    }

    public int getTurnosTotales() {
        return turnosTotales;
    }

    public void setTurnosTotales(int turnosTotales) {
        this.turnosTotales = turnosTotales;
    }

    public String getResumenCombate() {
        return resumenCombate;
    }

    public void setResumenCombate(String resumenCombate) {
        this.resumenCombate = resumenCombate;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
