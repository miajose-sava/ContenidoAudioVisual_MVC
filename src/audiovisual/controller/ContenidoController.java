package audiovisual.controller;

import java.util.ArrayList;
import audiovisual.model.ContenidoAudiovisual;
import audiovisual.model.ServicioArchivo;

public class ContenidoController {
    
    private ArrayList<ContenidoAudiovisual> listaContenidos;
    private final String RUTA_ARCHIVO = "contenidos.csv";

    // Constructor: Aquí cargamos automáticamente los datos guardados en el archivo
    public ContenidoController() {
        this.listaContenidos = ServicioArchivo.cargarContenidos(RUTA_ARCHIVO);
        if (this.listaContenidos == null) {
            this.listaContenidos = new ArrayList<>();
        }
    }

    // Método para obtener toda la lista (Para que la Vista pueda mostrarlos)
    public ArrayList<ContenidoAudiovisual> getListaContenidos() {
        return listaContenidos;
    }
    
 // Método para agregar un contenido y guardarlo en el archivo CSV de inmediato
    public void agregarContenido(ContenidoAudiovisual contenido) {
        if (contenido != null) {
            this.listaContenidos.add(contenido);
            
            // Sincronizamos la memoria con el disco duro de inmediato
            ServicioArchivo.guardarContenidos(this.listaContenidos, RUTA_ARCHIVO);
            System.out.println("Contenido sincronizado con el archivo CSV correctamente.");
        }
    }
}
