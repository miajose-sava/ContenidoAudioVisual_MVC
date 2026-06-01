package audiovisual.view;

import java.util.Scanner;

import audiovisual.controller.ContenidoController;
import audiovisual.model.ContenidoAudiovisual;
import audiovisual.model.Pelicula;

public class ConsolaView {
    
    // La Vista se comunica con el Controlador, NO directamente con los archivos
    private ContenidoController controlador;
    private Scanner scanner;

    public ConsolaView() {
        this.controlador = new ContenidoController();
        this.scanner = new Scanner(System.in);
    }

    // Este es el motor visual de la aplicación
    public void iniciarMenu() {
        int opcion = 0;
        
        do {
            System.out.println("\n=== GESTIÓN DE CONTENIDOS AUDIOVISUALES ===");
            System.out.println("1. Ver todos los contenidos");
            System.out.println("2. Agregar una nueva Película");
            System.out.println("3. Salir y Guardar");
            System.out.print("Elige una opción: ");
            
            // Leemos lo que el usuario digita
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcion = 0; // Si escribe letras en vez de números, forzamos un error controlado
            }

            switch (opcion) {
                case 1:
                    mostrarContenidos();
                    break;
                case 2:
                    agregarNuevaPelicula();
                    break;
                case 3:
                    System.out.println("¡Gracias por usar el sistema! Hasta pronto.");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida. Intente de nuevo.");
            }
            
        } while (opcion != 3);
    }
    
    private void mostrarContenidos() {
        System.out.println("\n--- LISTA DE CONTENIDOS ---");
        
        // Le pedimos la lista de datos al Controlador
        var lista = controlador.getListaContenidos();
        
        if (lista.isEmpty()) {
            System.out.println("No hay contenidos registrados todavía.");
        } else {
            // Recorremos la lista y mostramos un resumen de cada objeto
            for (ContenidoAudiovisual c : lista) {
                System.out.println("ID: " + c.getId() + " | " + c.getTitulo() + " (" + c.getClass().getSimpleName() + ")");
            }
        }
    }

    private void agregarNuevaPelicula() {
        System.out.println("\n--- AGREGAR NUEVA PELÍCULA ---");
        try {
            System.out.print("Ingrese el título: ");
            String titulo = scanner.nextLine();
            
            System.out.print("Ingrese la duración en minutos: ");
            int duracion = Integer.parseInt(scanner.nextLine());
            
            System.out.print("Ingrese el género: ");
            String genero = scanner.nextLine();
            
            System.out.print("Ingrese el estudio cinematográfico: ");
            String estudio = scanner.nextLine();
            
            // 1. Creamos el objeto en el Modelo
            Pelicula nuevaPelicula = new Pelicula(titulo, duracion, genero, estudio);
            
            // 2. Le asignamos un ID provisional (tamaño de la lista + 1)
            nuevaPelicula.setId(controlador.getListaContenidos().size() + 1);
            
            // 3. Le pasamos el objeto al Controlador para que lo guarde en memoria y en el archivo
            controlador.agregarContenido(nuevaPelicula);
            
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Error: La duración debe ser un número entero. Creación cancelada.");
        }
    }
}
