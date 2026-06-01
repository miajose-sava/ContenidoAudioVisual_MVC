package audiovisual.model;

import java.util.ArrayList;

public class VideoJuego extends ContenidoAudiovisual {
    private String consola;
    // Asociación: El videojuego se relaciona con actores de voz
    private ArrayList<Actor> actoresVoz;

    public VideoJuego(String titulo, int duracionEnMinutos, String genero, String consola) {
        super(titulo, duracionEnMinutos, genero);
        this.consola = consola;
        this.actoresVoz = new ArrayList<>();
    }

    public void agregarActorVoz(Actor actor) {
        this.actoresVoz.add(actor);
    }

    public String getConsola() {
		return consola;
	}

	public void setConsola(String consola) {
		this.consola = consola;
	}

	public ArrayList<Actor> getActoresVoz() {
		return actoresVoz;
	}

	public void setActoresVoz(ArrayList<Actor> actoresVoz) {
		this.actoresVoz = actoresVoz;
	}

	@Override
    public void mostrarDetalles() {
        System.out.println("Detalles del Videojuego:");
        System.out.println("ID: " + getId());
        System.out.println("Título: " + getTitulo());
        System.out.println("Consola/Plataforma: " + consola);
        
        if (!actoresVoz.isEmpty()) {
            System.out.print("Elenco de voz: ");
            for (Actor a : actoresVoz) {
                System.out.print(a.getNombre() + " | ");
            }
            System.out.println();
        } else {
            System.out.println("Elenco de voz: Por definir");
        }
        System.out.println();
    }

	public void setId(int id) {
		
	}
}
