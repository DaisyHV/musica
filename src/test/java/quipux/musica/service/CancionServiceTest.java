package quipux.musica.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import quipux.musica.model.Cancion;
import quipux.musica.repository.CancionRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class CancionServiceTest {
    @Mock
    private CancionRepository repository;

    @InjectMocks
    private CancionService service;

    @Test
    void testGetAllCanciones() {
        List<Cancion> cancionesMock = Arrays.asList(
                new Cancion(1L, "Song A", "Artist A", "A", "210", "Pop"),
                new Cancion(2L, "Song B", "Artist B", "B", "210", "Pop")
        );

        when(repository.findAll()).thenReturn(cancionesMock);

        List<Cancion> result = service.getAllCanciones();

        assertEquals(2, result.size());
        assertEquals("Song A", result.get(0).getTitulo());
        assertEquals("Song B", result.get(1).getTitulo());
    }

    @Test
    void testGetByTitulo() {
        Cancion cancionMock = new Cancion(1L, "Song A", "Artist A", "A", "210", "Pop");

        when(repository.findByTitulo("Song A")).thenReturn(cancionMock);

        Cancion result = service.getByTitulo("Song A");

        assertEquals("Song A", result.getTitulo());
        assertEquals("Artist A", result.getArtista());
    }

}
