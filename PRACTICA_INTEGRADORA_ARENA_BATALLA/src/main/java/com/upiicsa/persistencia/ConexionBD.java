package com.upiicsa.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class ConexionBD {

    private ConexionBD() {
    }

    public static Connection obtenerConexion() throws SQLException {

        Properties propiedades = new Properties();

        try (InputStream entrada =
                Files.newInputStream(Path.of("db.properties"))) {

            propiedades.load(entrada);

        } catch (IOException e) {
            throw new SQLException(
                "No se pudo cargar db.properties", e
            );
        }

        String url = propiedades.getProperty("db.url");
        String user = propiedades.getProperty("db.user");
        String password = propiedades.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }
}