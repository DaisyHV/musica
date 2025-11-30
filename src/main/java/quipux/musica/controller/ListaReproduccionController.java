package quipux.musica.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import quipux.musica.model.ListaReproduccion;
import quipux.musica.service.ListaReproduccionService;

import java.util.List;

@RestController
@RequestMapping("/lists")
@RequiredArgsConstructor
public class ListaReproduccionController {

    private final ListaReproduccionService service;

    @PostMapping()
    public ResponseEntity<ListaReproduccion> createLista(@RequestBody ListaReproduccion lista){
    return new ResponseEntity<>(service.save(lista), HttpStatus.CREATED);

    }
    @GetMapping()
    public ResponseEntity<List<ListaReproduccion>> getListas (){
        return new ResponseEntity<List<ListaReproduccion>>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("{listName}")
    public ResponseEntity<List<ListaReproduccion>> getListas (@PathVariable String listName){
        return new ResponseEntity<List<ListaReproduccion>>(service.getAllByName(listName), HttpStatus.OK);
    }


}
