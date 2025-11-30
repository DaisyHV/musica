package quipux.musica.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quipux.musica.model.Cancion;
import quipux.musica.model.ListaReproduccion;
import quipux.musica.repository.CancionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CancionService {

    private final CancionRepository repository;

    public List<Cancion> getAllCanciones(){
        return repository.findAll();
    }
    public Cancion getByTitulo(String titulo){
        return repository.findByTitulo(titulo);
    }


}
