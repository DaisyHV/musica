package quipux.musica.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quipux.musica.model.ListaReproduccion;
import quipux.musica.service.ListaReproduccionService;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class ListaReproduccionController {

    private final ListaReproduccionService service;

    @PostMapping()
    public ResponseEntity<ListaReproduccion> createLista(@Valid @RequestBody ListaReproduccion lista){
        try {
            ListaReproduccion listaGuardada = service.save(lista);
            String nombre = URLEncoder.encode(listaGuardada.getNombre(), StandardCharsets.UTF_8);
            return ResponseEntity
                    .created(URI.create("/lists/" + nombre))
                    .body(listaGuardada);


        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }
    @GetMapping()
    public ResponseEntity<List<ListaReproduccion>> getListas (){
        return new ResponseEntity<List<ListaReproduccion>>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{listName}")
    public ResponseEntity<ListaReproduccion> getLista (@PathVariable String listName){

        Optional<ListaReproduccion> lista= service.getAllByName(listName);
        if (lista.isPresent()){
            return new ResponseEntity<ListaReproduccion>(lista.get(), HttpStatus.OK);
        }
        return new ResponseEntity<ListaReproduccion>( HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{listName}")
    public ResponseEntity<List<ListaReproduccion>> deleteLista (@PathVariable String listName){
        if (service.deleteLista(listName)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<ListaReproduccion>>(HttpStatus.NOT_FOUND);
    }



}
