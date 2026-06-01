package audiovisual.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ServicioArchivoTest {

    @Test
    public void testConvertirYConstruirPeliculaConActores() {
        // 1. Preparamos una película con una lista de actores
        Pelicula peliOriginal = new Pelicula("Inception", 148, "Ciencia Ficción", "Warner Bros");
        ArrayList<Actor> actores = new ArrayList<>();
        actores.add(new Actor("Leonardo DiCaprio"));
        actores.add(new Actor("Joseph Gordon-Levitt"));
        peliOriginal.setActores(actores);
        peliOriginal.setId(1);
        
        // 2. Simulamos el proceso de persistencia
        String lineaCSV = ServicioArchivo.convertirAFormatocsv(peliOriginal);
        
        // 3. Verificamos que el formato CSV incluya a los actores correctamente
        assertTrue(lineaCSV.contains("Leonardo DiCaprio"), "El CSV debe contener el nombre del primer actor.");
        assertTrue(lineaCSV.contains("Joseph Gordon-Levitt"), "El CSV debe contener el nombre del segundo actor.");
        assertTrue(lineaCSV.contains(";"), "El CSV debe usar ';' como delimitador para la lista de actores.");
    }
    
    @Test
    public void testConvertirYConstruirSerieConTemporadas() {
        SerieDeTV serie = new SerieDeTV("Breaking Bad", 50, "Drama");
        serie.agregarTemporada(new Temporada(1, 7));
        serie.agregarTemporada(new Temporada(2, 13));
        serie.setId(2);

        String lineaCSV = ServicioArchivo.convertirAFormatocsv(serie);
        
        // Verificamos el formato específico de temporadas (número-episodios)
        assertTrue(lineaCSV.contains("1-7"), "El CSV debe contener el formato 'temporada-episodios' para la primera temporada.");
        assertTrue(lineaCSV.contains("2-13"), "El CSV debe contener el formato 'temporada-episodios' para la segunda temporada.");
    }
    
    @Test
    public void testConvertirYConstruirDocumentalConInvestigador() {
        Documental doc = new Documental("Cosmos", 60, "Ciencia", "Espacio");
        doc.setInvestigador(new Investigador("Carl Sagan"));
        doc.setId(3);

        String lineaCSV = ServicioArchivo.convertirAFormatocsv(doc);
        
        assertTrue(lineaCSV.contains("Carl Sagan"), "El CSV debe incluir el nombre del investigador.");
    }
    
    @Test
    public void testReconstruirPeliculaDesdeCSV() {
        // 1. Preparamos una línea CSV
        String linea = "Pelicula,1,Inception,148,Ciencia Ficción,Warner Bros,Leonardo DiCaprio;Joseph Gordon-Levitt";
        
        // 2. Llamamos al método para convertir CSV a Objeto
        ContenidoAudiovisual contenido = ServicioArchivo.construirDesdeCSV(linea);
        
        // 3. Verificaciones (Assertions)
        assertNotNull(contenido, "El objeto reconstruido no debería ser nulo.");
        assertTrue(contenido instanceof Pelicula, "El objeto reconstruido debería ser una Película.");
        
        Pelicula peli = (Pelicula) contenido;
        assertEquals("Inception", peli.getTitulo(), "El título no coincide.");
        assertEquals(2, peli.getActores().size(), "La lista de actores debería tener 2 elementos.");
        assertEquals("Leonardo DiCaprio", peli.getActores().get(0).getNombre(), "El nombre del primer actor no coincide.");
    }
}
