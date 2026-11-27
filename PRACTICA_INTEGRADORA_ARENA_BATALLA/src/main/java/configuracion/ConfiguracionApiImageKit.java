package configuracion;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfiguracionApiImageKit {

    private static final Properties propiedades = new Properties();

    static {
        try {
            FileInputStream archivo = new FileInputStream("api.properties");
            propiedades.load(archivo);
            archivo.close();
        } catch (IOException ex) {
            throw new RuntimeException("Error al cargar api.properties", ex);
        }
    }

    public static String obtener(String clave) {
        return propiedades.getProperty(clave);
    }
}