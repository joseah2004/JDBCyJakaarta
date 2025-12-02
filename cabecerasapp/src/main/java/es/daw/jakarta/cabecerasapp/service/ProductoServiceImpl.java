package es.daw.jakarta.cabecerasapp.service;

import es.daw.jakarta.cabecerasapp.model.Producto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProductoServiceImpl implements ProductoService{

    // por qué static???? una vez creada no la voy a modificar.
    // Sí modifico los productos, añadiendo, quitando... pero el objeto list es el mismo.

    private static List<Producto> productos = new ArrayList<Producto>();

    @Override
    public List<Producto> findAll() {
        //return List.of();
        // Simular que buscamos productos de una base de datos...
        return Arrays.asList(new Producto(1L, "notebook", "informática", 175000),
        new Producto(2L, "mesa escritorio", "oficina", 10000000),
        new Producto(3L, "teclado", "informatica", 400000));

    }

    @Override
    public Optional<Producto> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Producto findByNombre(String nombre) {
        return null;
    }
}
