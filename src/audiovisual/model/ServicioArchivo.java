package audiovisual.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ServicioArchivo {
	
	public static void guardarContenidos(ArrayList<ContenidoAudiovisual> lista, String rutaArchivo) {
	    // try-with-resources: Abre el archivo y lo cierra automáticamente al terminar
	    try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
	        
	        for (ContenidoAudiovisual contenido : lista) {
	            // Aquí llamaremos a un método ayudante para convertir el objeto a texto
	            String linea = convertirAFormatocsv(contenido);
	            bw.write(linea);
	            bw.newLine(); // Salto de línea para el siguiente contenido
	        }
	        System.out.println("Datos guardados exitosamente en " + rutaArchivo);
	        
	    } catch (IOException e) {
	        System.err.println("Error al guardar los datos: " + e.getMessage());
	    }
	}
	
	public static String convertirAFormatocsv(ContenidoAudiovisual c) {
        String base = c.getClass().getSimpleName() + "," + c.getId() + "," + c.getTitulo() + "," + c.getDuracionEnMinutos() + "," + c.getGenero();
        
       if (c instanceof Pelicula) {
            Pelicula p = (Pelicula) c;
            String actoresStr = "";
            
            if (p.getActores() != null && !p.getActores().isEmpty()) {
                for (int i = 0; i < p.getActores().size(); i++) {
                    actoresStr += p.getActores().get(i).getNombre();
                    if (i < p.getActores().size() - 1) {
                        actoresStr += ";";
                    }
                }
            } else {
                actoresStr = "Sin actores";
            }
            return base + "," + p.getEstudio() + "," + actoresStr;
            
        } else if (c instanceof SerieDeTV) {
            SerieDeTV s = (SerieDeTV) c;
            String tempStr = "";
            if (s.getTemporadas() != null) {
                for (int i = 0; i < s.getTemporadas().size(); i++) {
                    Temporada t = s.getTemporadas().get(i);
                    tempStr += t.getNumTemporada() + "-" + t.getNumEpisodios();
                    if (i < s.getTemporadas().size() - 1) tempStr += ";";
                }
            }
            return base + "," + tempStr;
            
        } else if (c instanceof Documental) {
            Documental d = (Documental) c;
            String inv = (d.getInvestigador() != null) ? d.getInvestigador().getNombre() : "";
            return base + "," + d.getTema() + "," + inv;
            
        } else if (c instanceof Podcast) {
            Podcast pod = (Podcast) c;
            String tempStr = "";
            if (pod.getTemporadas() != null) {
                for (int i = 0; i < pod.getTemporadas().size(); i++) {
                    Temporada t = pod.getTemporadas().get(i);
                    tempStr += t.getNumTemporada() + "-" + t.getNumEpisodios();
                    if (i < pod.getTemporadas().size() - 1) tempStr += ";";
                }
            }
            return base + "," + pod.getPlataforma() + "," + tempStr;
            
        } else if (c instanceof VideoJuego) {
            VideoJuego vj = (VideoJuego) c;
            String actoresStr = "";
            if (vj.getActoresVoz() != null) {
                for (int i = 0; i < vj.getActoresVoz().size(); i++) {
                    actoresStr += vj.getActoresVoz().get(i).getNombre();
                    if (i < vj.getActoresVoz().size() - 1) actoresStr += ";";
                }
            }
            return base + "," + vj.getConsola() + "," + actoresStr;
        }
        
        return base;
    }
	
	public static ArrayList<ContenidoAudiovisual> cargarContenidos(String rutaArchivo) {
	    ArrayList<ContenidoAudiovisual> listaRecuperada = new ArrayList<>();
	    
	    // try-with-resources: Abre el lector de archivos de forma segura
	    try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
	        String linea;
	        
	        // Lee el archivo línea por línea hasta que no haya más texto (null)
	        while ((linea = br.readLine()) != null) {
	            // Pasamos la línea a un método ayudante que creará el objeto específico
	            ContenidoAudiovisual contenido = construirDesdeCSV(linea);
	            if (contenido != null) {
	                listaRecuperada.add(contenido);
	            }
	        }
	        System.out.println("Datos cargados exitosamente desde " + rutaArchivo);
	        
	    } catch (IOException e) {
	        System.out.println("No se encontró un archivo previo. Se iniciará un sistema vacío.");
	    }
	    
	    return listaRecuperada;
	}
	
	public static ContenidoAudiovisual construirDesdeCSV(String linea) {
        String[] partes = linea.split(",");
        if (partes.length < 5) return null;
        
        String tipo = partes[0];
        int id = Integer.parseInt(partes[1]);
        String titulo = partes[2];
        int duracion = Integer.parseInt(partes[3]);
        String genero = partes[4];
        
        if (tipo.equals("Pelicula")) {
            String estudio = partes.length > 5 ? partes[5] : "";
            Pelicula p = new Pelicula(titulo, duracion, genero, estudio);
            p.setId(id);
            if (partes.length > 6 && !partes[6].isEmpty()) {
                String[] actores = partes[6].split(";");
                ArrayList<Actor> listaActores = new ArrayList<>();
                for (String nom : actores) listaActores.add(new Actor(nom));
                p.setActores(listaActores);
            }
            return p;
            
        } else if (tipo.equals("SerieDeTV")) {
            SerieDeTV s = new SerieDeTV(titulo, duracion, genero);
            s.setId(id);
            if (partes.length > 5 && !partes[5].isEmpty()) {
                String[] temporadas = partes[5].split(";");
                ArrayList<Temporada> listaTemporadas = new ArrayList<>();
                for (String t : temporadas) {
                    String[] datosT = t.split("-");
                    if (datosT.length == 2) {
                        listaTemporadas.add(new Temporada(Integer.parseInt(datosT[0]), Integer.parseInt(datosT[1])));
                    }
                }
                s.setTemporadas(listaTemporadas);
            }
            return s;
            
        } else if (tipo.equals("Documental")) {
            String tema = partes.length > 5 ? partes[5] : "";
            Documental d = new Documental(titulo, duracion, genero, tema);
            d.setId(id);
            if (partes.length > 6 && !partes[6].isEmpty()) {
                d.setInvestigador(new Investigador(partes[6]));
            }
            return d;
            
        } else if (tipo.equals("Podcast")) {
            String plataforma = partes.length > 5 ? partes[5] : "";
            Podcast pod = new Podcast(titulo, duracion, genero, plataforma);
            pod.setId(id);
            if (partes.length > 6 && !partes[6].isEmpty()) {
                String[] temporadas = partes[6].split(";");
                ArrayList<Temporada> listaTemporadas = new ArrayList<>();
                for (String t : temporadas) {
                    String[] datosT = t.split("-");
                    if (datosT.length == 2) {
                        listaTemporadas.add(new Temporada(Integer.parseInt(datosT[0]), Integer.parseInt(datosT[1])));
                    }
                }
                pod.setTemporadas(listaTemporadas);
            }
            return pod;
            
        } else if (tipo.equals("VideoJuego")) {
            String consola = partes.length > 5 ? partes[5] : "";
            VideoJuego vj = new VideoJuego(titulo, duracion, genero, consola);
            vj.setId(id);
            if (partes.length > 6 && !partes[6].isEmpty()) {
                String[] actores = partes[6].split(";");
                ArrayList<Actor> listaActores = new ArrayList<>();
                for (String nom : actores) listaActores.add(new Actor(nom));
                vj.setActoresVoz(listaActores);
            }
            return vj;
        }
        
        return null;
    }

}
