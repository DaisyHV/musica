package quipux.musica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import quipux.musica.model.Cancion;

import java.util.List;
import java.util.Optional;
@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {
    Cancion findByTitulo(String titulo);
}
