package audiovisual.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import audiovisual.model.Pelicula;

public class ContenidoControllerTest {

    private ContenidoController controlador;

    @BeforeEach
    public void setUp() {
        controlador = new ContenidoController();
    }

    // 1. Prueba de Caso Normal (Happy Path)
    @Test
    public void testAgregarContenidoNormal() {
        int tamañoInicial = controlador.getListaContenidos().size();
        Pelicula peliPrueba = new Pelicula("Peli Normal", 120, "Acción", "Estudio");
        
        controlador.agregarContenido(peliPrueba);
        int tamañoFinal = controlador.getListaContenidos().size();
        
        assertEquals(tamañoInicial + 1, tamañoFinal, "El tamaño de la lista debió aumentar en 1.");
    }

    // 2. Prueba de Caso Límite (Edge Case - Objeto Nulo)
    @Test
    public void testAgregarContenidoNulo() {
        int tamañoInicial = controlador.getListaContenidos().size();
        
        controlador.agregarContenido(null);
        int tamañoFinal = controlador.getListaContenidos().size();
        
        assertEquals(tamañoInicial, tamañoFinal, "El tamaño de la lista NO debió cambiar al enviar un null.");
    }

    // 3. Prueba de Caso Excepcional (Garantizar inicialización segura)
    @Test
    public void testListaNuncaEsNula() {
        assertNotNull(controlador.getListaContenidos(), "La lista de contenidos nunca debe ser null, debe ser al menos una lista vacía.");
    }
}