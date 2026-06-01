package audiovisual.model;

import java.util.ArrayList;

public class Podcast extends ContenidoAudiovisual {
    private String plataforma; // Corregido a minúscula
    private ArrayList<Temporada> temporadas;

    public Podcast(String titulo, int duracionEnMinutos, String genero, String plataforma) {
        super(titulo, duracionEnMinutos, genero);
        this.plataforma = plataforma;
        this.temporadas = new ArrayList<>();
    }

    public void agregarTemporada(Temporada temporada) {
        this.temporadas.add(temporada);
    }

    // --- Getters y Setters necesarios para el ServicioArchivo ---

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public ArrayList<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(ArrayList<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    // --- Fin de Getters y Setters ---

    @Override
    public void mostrarDetalles() {
        System.out.println("Detalles del Podcast:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Plataforma: " + plataforma);
        System.out.println("Género: " + getGenero());
        System.out.println("Total de temporadas: " + temporadas.size());
        
        for (Temporada t : temporadas) {
            System.out.println("- Temp " + t.getNumTemporada() + ": " + t.getNumEpisodios() + " episodios.");
        }
        System.out.println();
    }

	public void setId(int id) {
		
	}
}