package quipux.musica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import quipux.musica.model.Cancion;
import quipux.musica.model.ListaReproduccion;

import java.util.List;

@Repository
public interface ListaReproduccionRepository extends JpaRepository<ListaReproduccion, Long> {
    List<ListaReproduccion> findByNombre(String nombre);
}
