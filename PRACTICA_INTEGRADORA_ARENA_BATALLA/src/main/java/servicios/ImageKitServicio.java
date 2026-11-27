package servicios;

import configuracion.ConfiguracionApiImageKit;
import io.imagekit.client.ImageKitClient;
import io.imagekit.client.okhttp.ImageKitOkHttpClient;
import io.imagekit.models.files.FileUploadParams;
import io.imagekit.models.files.FileUploadResponse;
import java.nio.file.Path;
import java.util.Optional;

public class ImageKitServicio {

    private final ImageKitClient cliente;

    public ImageKitServicio() {
        cliente = ImageKitOkHttpClient.builder()
                .privateKey(ConfiguracionApiImageKit.obtener("IK_PRIVATE"))
                .build();
    }

    public Optional<String> subirImagen(Path archivo) {

        FileUploadParams parametros = FileUploadParams.builder()
                .file(archivo)
                .fileName(archivo.getFileName().toString())
                .folder("/personajes")
                .build();

        FileUploadResponse respuesta = cliente.files().upload(parametros);

        return respuesta.url();
    }
}