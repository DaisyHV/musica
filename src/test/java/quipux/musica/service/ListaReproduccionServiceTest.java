package quipux.musica.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import quipux.musica.model.Cancion;
import quipux.musica.model.ListaReproduccion;
import quipux.musica.repository.CancionRepository;
import quipux.musica.repository.ListaReproduccionRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListaReproduccionServiceTest {
    @Mock
    private ListaReproduccionRepository listaRepository;

    @Mock
    private CancionRepository cancionRepository;

    @InjectMocks
    private ListaReproduccionService service;

    @Test
    void testGetAll() {
        List<ListaReproduccion> listasMock = Arrays.asList(
                new ListaReproduccion(1L, "Lista1", "Lista", null),
                new ListaReproduccion(2L, "Lista2", "Listab", null)
        );
        when(listaRepository.findAll()).thenReturn(listasMock);

        List<ListaReproduccion> result = service.getAll();

        assertEquals(2, result.size());
        assertEquals("Lista1", result.get(0).getNombre());
    }

    @Test
    void testGetAllByName_Found() {
        ListaReproduccion lista = new ListaReproduccion(1L, "MiLista", "Lista Daisy", null);
        when(listaRepository.findByNombre("MiLista")).thenReturn(lista);

        Optional<ListaReproduccion> result = service.getAllByName("MiLista");

        assertTrue(result.isPresent());
        assertEquals("MiLista", result.get().getNombre());
    }

    @Test
    void testGetAllByName_NotFound() {
        when(listaRepository.findByNombre("NoExiste")).thenReturn(null);

        Optional<ListaReproduccion> result = service.getAllByName("NoExiste");

        assertTrue(result.isEmpty());
    }

    @Test
    void testSave_WithExistingCancion() {
        Cancion cancionInLista = new Cancion(null, "Titulo1", null, null, null, null);
        Cancion cancionExistente = new Cancion(10L, "Titulo1", null, null, null, null);
        ListaReproduccion lista = new ListaReproduccion(null, "Lista","Lista para prueba", Arrays.asList(cancionInLista));

        when(cancionRepository.findByTitulo("Titulo1")).thenReturn(cancionExistente);
        when(listaRepository.save(any())).thenAnswer(i -> i.getArgument(0));  // Devuelve el objeto guardado

        ListaReproduccion result = service.save(lista);

        assertEquals(10L, result.getCanciones().get(0).getId());
        verify(cancionRepository, never()).save(any()); // No debería llamar a save para canción
        verify(listaRepository).save(lista);
    }

    @Test
    void testSave_WithNewCancion() {
        Cancion cancionInLista = new Cancion(null, "NuevoTitulo", null, null, null, null);
        Cancion cancionGuardada = new Cancion(20L, "NuevoTitulo", null, null, null, null);
        ListaReproduccion lista = new ListaReproduccion(1l,"lista1", "Lista", Arrays.asList(cancionInLista));

        when(cancionRepository.findByTitulo("NuevoTitulo")).thenThrow(new RuntimeException("No existe"));
        when(cancionRepository.save(cancionInLista)).thenReturn(cancionGuardada);
        when(listaRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        ListaReproduccion result = service.save(lista);

        assertEquals(20L, result.getCanciones().get(0).getId());
        verify(cancionRepository).save(cancionInLista);
        verify(listaRepository).save(lista);
    }

    @Test
    void testDeleteLista_Found() {
        ListaReproduccion lista = new ListaReproduccion(1L, "ListaAEliminar","lista", null);
        when(listaRepository.findByNombre("ListaAEliminar")).thenReturn(lista);
        doNothing().when(listaRepository).deleteById(1L);

        boolean deleted = service.deleteLista("ListaAEliminar");

        assertTrue(deleted);
        verify(listaRepository).deleteById(1L);
    }

    @Test
    void testDeleteLista_NotFound() {
        when(listaRepository.findByNombre("NoExiste")).thenReturn(null);

        boolean deleted = service.deleteLista("NoExiste");

        assertFalse(deleted);
        verify(listaRepository, never()).deleteById(anyLong());
    }

}
