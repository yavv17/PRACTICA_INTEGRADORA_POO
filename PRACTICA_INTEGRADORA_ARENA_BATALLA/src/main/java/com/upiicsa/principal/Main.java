package com.upiicsa.principal;

import com.upiicsa.controlador.ArenaControlador;
import com.upiicsa.modelo.GestorArenaModelo;
import com.upiicsa.vista.ArenaBatallaVista;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {

            GestorArenaModelo modelo = new GestorArenaModelo();

            ArenaBatallaVista vista = new ArenaBatallaVista();

            ArenaControlador controlador = new ArenaControlador(modelo, vista);

            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
        });
    }
}