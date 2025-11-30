package quipux.musica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "canciones")
public class Cancion {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String artista;
    private String album;
    private String anno;
    private String genero;

    @JsonIgnore
    @ManyToMany(fetch =  FetchType.LAZY)
    @JoinTable(
            name="listas_has_canciones",
            joinColumns = @JoinColumn(name="cancion_id"),
            inverseJoinColumns = @JoinColumn(name = "lista_id")
    )
    private List<ListaReproduccion> listas;

    public Cancion(Long id, String titulo, String artista, String album, String anno, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.anno = anno;
        this.genero = genero;
    }

    public Cancion() {
    }
}
