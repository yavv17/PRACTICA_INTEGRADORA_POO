/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package principal;

import controlador.ArenaControlador;
import modelo.GestorArenaModelo;
import vista.ArenaBatallaVista;

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