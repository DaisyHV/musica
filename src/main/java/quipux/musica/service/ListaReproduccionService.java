package quipux.musica.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import quipux.musica.model.Cancion;
import quipux.musica.model.ListaReproduccion;
import quipux.musica.repository.CancionRepository;
import quipux.musica.repository.ListaReproduccionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ListaReproduccionService {

    private final ListaReproduccionRepository repository;
    private final CancionRepository repositoryCancion;

    public List<ListaReproduccion> getAll(){
        return repository.findAll();
    }

    public Optional<ListaReproduccion> getAllByName(String nombre){
        Optional<ListaReproduccion> listaExist =repository.findByNombre(nombre)!=null ?
                Optional.of(repository.findByNombre(nombre)) : Optional.empty();
        return listaExist;
    }

    public ListaReproduccion save (ListaReproduccion lista){
    for (int i=0;i<lista.getCanciones().size();i++){
            try{
                Cancion cancion= repositoryCancion.findByTitulo(lista.getCanciones().get(i).getTitulo());
                lista.getCanciones().get(i).setId(cancion.getId());

            } catch (Exception e) {

                Cancion cancion= repositoryCancion.save(lista.getCanciones().get(i));
                lista.getCanciones().get(i).setId(cancion.getId());
            }



            }
    return repository.save(lista);

    }

    public boolean deleteLista(String titulo) {
        Optional<ListaReproduccion> listaExist =repository.findByNombre(titulo)!=null ?
                Optional.of(repository.findByNombre(titulo)) : Optional.empty();
        if (listaExist.isPresent()) {
            repository.deleteById(listaExist.get().getId());
            return true;
        }
        return false;


    }

}
