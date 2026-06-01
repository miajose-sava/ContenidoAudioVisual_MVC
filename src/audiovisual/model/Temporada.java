package audiovisual.model;

public class Temporada {
	private int numTemporada;
	private int numEpisodios;
	
	public Temporada(int numTemporada, int numEpisodios) {
        this.numTemporada = numTemporada;
        this.numEpisodios = numEpisodios;
    }
	
	public int getNumTemporada() {
		return numTemporada;
	}
	public void setNumTemporada(int numTemporada) {
		this.numTemporada = numTemporada;
	}
	public int getNumEpisodios() {
		return numEpisodios;
	}
	public void setNumEpisodios(int numEpisodios) {
		this.numEpisodios = numEpisodios;
	}

}