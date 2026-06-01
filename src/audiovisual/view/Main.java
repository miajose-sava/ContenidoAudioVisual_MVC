package audiovisual.view;

public class Main {

    public static void main(String[] args) {
        // 1. Instanciamos la Vista (Esto automáticamente despierta al Controlador y carga el archivo)
        ConsolaView vista = new ConsolaView();
        
        // 2. Arrancamos el menú interactivo para el usuario
        vista.iniciarMenu();
    }

}