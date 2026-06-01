package audiovisual.model;

//Subclase Documental que extiende de ContenidoAudiovisual
public class Documental extends ContenidoAudiovisual {
 private String tema;
 private Investigador investigador;

 public Documental(String titulo, int duracionEnMinutos, String genero, String tema) {
     super(titulo, duracionEnMinutos, genero);
     this.tema = tema;
 }
 
 public Investigador getInvestigador() {
     return investigador;
 }

 public void setInvestigador(Investigador investigador) {
     this.investigador = investigador;
 }

 public String getTema() {
     return tema;
 }

 public void setTema(String tema) {
     this.tema = tema;
 }
 
 @Override
 public void mostrarDetalles() {
     System.out.println("Detalles de la película:");
     System.out.println("ID: " + getId());
     System.out.println("Título: " + getTitulo());
     System.out.println("Duración en minutos: " + getDuracionEnMinutos());
     System.out.println("Género: " + getGenero());
     System.out.println("Tema: " + this.tema);
     if (investigador != null) {
         System.out.println("Investigador a cargo: " + investigador.getNombre());
     } else {
         System.out.println("Investigador: Pendiente por asignar");
     }
     System.out.println();
 }

 public void setId(int id) {
	// TODO Auto-generated method stub
	
 }
}
