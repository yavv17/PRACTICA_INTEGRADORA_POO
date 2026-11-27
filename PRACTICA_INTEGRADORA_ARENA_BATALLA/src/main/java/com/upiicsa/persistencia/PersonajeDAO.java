package com.upiicsa.persistencia;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.upiicsa.modelo.*;
import java.util.Map;

public class PersonajeDAO {

    public void insertar(PersonajeBase p) throws SQLException {
        String sql = "INSERT INTO personajes (id, nombre, tipo_clase, vida, vida_maxima, "
                   + "fuerza, poder, defensa, arma, ruta_imagen, atributos_extra) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            // TODO: Asignar los 11 parámetros con ps.setString(...) y ps.setInt(...)
            // Tip: Usa p.getTipoClase() y serializa los atributos propios en 'atributos_extra'.
            ps.setString(1, p.getId());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getTipoClase());
            ps.setInt(4, p.getVida());
            ps.setInt(5,p.getVidaMaxima());
            ps.setInt(6, p.getFuerza());
            ps.setInt(7,p.getPoder());
            ps.setInt(8, p.getDefensa());
            ps.setString(9, p.getArma());
            ps.setString(10, p.getRutaImagen());
            ps.setString(11, p.getAtributosClaseSerializados());
            
            ps.executeUpdate();
        }
    }

    public List<PersonajeBase> listarTodos() throws SQLException {
        List<PersonajeBase> lista = new ArrayList<>();
        String sql = "SELECT * FROM personajes ORDER BY id ASC";

        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                // TODO: Invocar reconstruirSubclase(rs) y agregar el objeto a 'lista'
                PersonajeBase nuevo = reconstruirSubclase(rs);
                lista.add(nuevo);
            }
        }
        return lista;
    }

    public boolean actualizar(PersonajeBase p) throws SQLException {
        String sql = "UPDATE personajes SET nombre=?, vida=?, vida_maxima=?, fuerza=?, poder=?, defensa=?, arma=?, ruta_imagen=? WHERE id=?";
        // TODO: Implementar PreparedStatement y retornar ps.executeUpdate() > 0
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipoClase());
            ps.setInt(3, p.getVida());
            ps.setInt(4, p.getVidaMaxima());
            ps.setInt(5, p.getFuerza());
            ps.setInt(6, p.getPoder());
            ps.setInt(7, p.getDefensa());
            ps.setString(8, p.getArma());
            ps.setString(9, p.getRutaImagen());
            ps.setString(10, p.getAtributosClaseSerializados());
            ps.setString(11, p.getId());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String id) throws SQLException {
        String sql = "DELETE FROM personajes WHERE id = ?";
        // TODO: Implementar PreparedStatement y retornar ps.executeUpdate() > 0
        try (Connection con = ConexionBD.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);

            return ps.executeUpdate() > 0;
        }
    }

    public void intercambiarArmas(String id1, String armaNueva1, String id2, String armaNueva2) throws SQLException {
        String sql = "UPDATE personajes SET arma = ? WHERE id = ?";
        try (Connection con = ConexionBD.obtenerConexion()) {
            con.setAutoCommit(false); // Inicia unidad atómica
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                // TODO: Ejecutar el UPDATE para id1 y luego para id2; confirmar con con.commit()
                ps.setString(1, armaNueva1);
                ps.setString(2,id1);
                
                int resultado1 = ps.executeUpdate();
                
                if(resultado1 == 0){
                    throw new SQLException("No se encontro al primer combatiente");
                }
                
                ps.setString(1, armaNueva2);
                ps.setString(2,id2);
                
                int resultado2 = ps.executeUpdate();
                
                if(resultado2 == 0){
                    throw new SQLException("No se encontro al segundo combatiente");
                }
                
                con.commit();
            } catch (SQLException ex) {
                con.rollback(); // Revierte ambos cambios ante cualquier error
                throw ex;
            }
        }
    }

    private PersonajeBase reconstruirSubclase(ResultSet rs) throws SQLException {
        String tipo = rs.getString("tipo_clase");
        // TODO: Implementar switch(tipo) para instanciar cualquiera de las 10 subclases
        // (Orco, Ninfa, Polinene, Arquero, Bardo, Golem, Saiyan, Caballero, Barbaro, Stand)
        // restaurando tanto atributos comunes como específicos y rutaImagen.
        String json = rs.getString("atributos_extra");
        ObjectMapper mapper = new ObjectMapper();

        try {
            Map<String, Object> atributos = mapper.readValue(
                json,
                new TypeReference<Map<String, Object>>() {}
            );

            PersonajeBase personaje;
            String id = rs.getString("id");
            String nombre = rs.getString("nombre");
            int vida = rs.getInt("vida");
            int fuerza = rs.getInt("fuerza");
            int poder = rs.getInt("poder");
            int defensa = rs.getInt("defensa");
            String arma = rs.getString("arma");
            String ruta_imagen = rs.getString("ruta_imagen");
            

            switch (tipo) {

                case "Arquero":
                    Arquero arquero = new Arquero(
                            id,nombre,vida,fuerza,poder,defensa,arma,ruta_imagen
                    );
                    arquero.setRango(numero(atributos, "rango"));
                    arquero.setMunicion(texto(atributos, "municion"));
                    personaje = arquero;
                    break;

                case "Barbaro":
                    Barbaro barbaro = new Barbaro(
                            id,nombre,vida,fuerza,poder,defensa,arma,ruta_imagen
                    );
                    barbaro.setEstamina(numero(atributos, "estamina"));
                    barbaro.setResistencia(numero(atributos, "resistencia"));
                    barbaro.setArmaAdicional(texto(atributos, "armaAdicional"));
                    personaje = barbaro;
                    break;

                case "Bardo":
                    Bardo bardo = new Bardo(
                            id,nombre,vida,fuerza,poder,defensa,arma,ruta_imagen
                    );
                    bardo.setInspiracion(numero(atributos, "inspiracion"));
                    personaje = bardo;
                    break;

                case "Caballero":
                    Caballero caballero = new Caballero(
                            id,nombre,vida,fuerza,poder,defensa,arma,ruta_imagen
                    );
                    caballero.setArmadura(numero(atributos, "armadura"));
                    caballero.setResistencia(numero(atributos, "resistencia"));
                    caballero.setEscudo(booleano(atributos, "escudo"));
                    caballero.setCriaturaApoyo(texto(atributos, "criaturaApoyo"));
                    personaje = caballero;
                    break;

                case "Golem":
                    Golem golem = new Golem(
                            id,nombre,vida,fuerza,poder,defensa,arma,ruta_imagen
                    );
                    golem.setResistencia(numero(atributos, "resistencia"));
                    golem.setElemento(texto(atributos, "elemento"));
                    personaje = golem;
                    break;

                case "Ninfa":
                    Ninfa ninfa = new Ninfa(
                            id,nombre,vida,fuerza,poder,defensa,arma,ruta_imagen
                    );
                    ninfa.setElemento(texto(atributos, "elemento"));
                    ninfa.setNivelSeduccion(numero(atributos, "nivelSeduccion"));
                    ninfa.setExperiencia(numero(atributos, "experiencia"));
                    personaje = ninfa;
                    break;

                case "Orco":
                    Orco orco = new Orco(
                            id,nombre,vida,fuerza,poder,defensa,arma,ruta_imagen
                    );
                    orco.setColmillos(numero(atributos, "colmillos"));
                    orco.setTamano(texto(atributos, "tamano"));
                    orco.setColor(texto(atributos, "color"));
                    orco.setAditamento(texto(atributos, "aditamento"));
                    personaje = orco;
                    break;

                case "Polinene":
                    Polinene polinene = new Polinene(
                            id,nombre,vida,fuerza,poder,defensa,arma,ruta_imagen
                    );
                    polinene.setCantidadPolimonedas(
                        numero(atributos, "cantidadPolimonedas")
                    );
                    polinene.setInteligencia(numero(atributos, "inteligencia"));
                    polinene.setTamear(booleano(atributos, "tamear"));
                    personaje = polinene;
                    break;

                case "Saiyan":
                    Saiyan saiyan = new Saiyan(
                            id,nombre,vida,fuerza,poder,defensa,arma,ruta_imagen
                    );
                    saiyan.setCola(booleano(atributos, "cola"));
                    saiyan.setCelulasZ(numero(atributos, "celulasZ"));
                    saiyan.setZenkai(numero(atributos, "zenkai"));
                    saiyan.setKi(numero(atributos, "ki"));
                    personaje = saiyan;
                    break;

                case "Stand":
                    Stand stand = new Stand(
                            id,nombre,vida,fuerza,poder,defensa,arma,ruta_imagen
                    );
                    stand.setRango(numero(atributos, "rango"));
                    stand.setTipo(texto(atributos, "tipo"));
                    stand.setFrase(texto(atributos, "frase"));
                    stand.setUsuario(texto(atributos, "usuario"));
                    personaje = stand;
                    break;

                default:
                    throw new SQLException(
                        "Tipo de personaje desconocido: " + tipo
                    );
            }

            return personaje;

        } catch (Exception ex) {

            throw new SQLException(
                "Error al reconstruir el personaje de tipo " + tipo,
                ex
            );
        }
    }
    
    private int numero(Map<String, Object> atributos, String clave) {
        return ((Number) atributos.get(clave)).intValue();
    }

    private String texto(Map<String, Object> atributos, String clave) {
        Object valor = atributos.get(clave);
        return valor == null ? null : valor.toString();
    }

    private boolean booleano(Map<String, Object> atributos, String clave) {
        return Boolean.parseBoolean(atributos.get(clave).toString());
    }
}
