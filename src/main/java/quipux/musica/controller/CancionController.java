package quipux.musica.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import quipux.musica.model.Cancion;
import quipux.musica.model.ListaReproduccion;
import quipux.musica.service.CancionService;
import quipux.musica.service.ListaReproduccionService;

import java.util.List;

@RestController
@RequestMapping("/canciones")
@RequiredArgsConstructor
public class CancionController {
    private final CancionService service;

    @GetMapping()
    public ResponseEntity<List<Cancion>> getListas (){
        return new ResponseEntity<List<Cancion>>(service.getAllCanciones(), HttpStatus.OK);
    }
}
