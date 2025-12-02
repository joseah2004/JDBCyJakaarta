package es.daw.jakarta.cabecerasapp.service;

import es.daw.jakarta.cabecerasapp.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    public List<Producto> findAll();

    public Optional<Producto> findById(Long id);

    //public Producto save(Producto producto);

    public Producto findByNombre(String nombre);

}
