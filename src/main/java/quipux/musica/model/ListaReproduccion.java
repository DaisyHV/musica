package quipux.musica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "listas")
public class ListaReproduccion {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre debe ser obligatorio")
    private String nombre;
    private String descripcion;
    @ManyToMany(fetch =  FetchType.LAZY)
    @JoinTable(
            name="listas_has_canciones",
            joinColumns = @JoinColumn(name="lista_id"),
            inverseJoinColumns = @JoinColumn(name = "cancion_id")
    )
    private List<Cancion> canciones;

    public ListaReproduccion(Long id, String nombre, String descripcion, List<Cancion> canciones) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.canciones = canciones;
    }

    public ListaReproduccion() {
    }
}
