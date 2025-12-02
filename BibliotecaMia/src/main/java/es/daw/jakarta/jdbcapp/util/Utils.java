package es.daw.jakarta.jdbcapp.util;

import es.daw.jakarta.jdbcapp.model.Fabricante;
import es.daw.jakarta.jdbcapp.model.Producto;

import java.util.List;

public class Utils {
    public static String obtenerNombreFabricante(List<Fabricante> fabricantes, Integer codigo) {

//        // FORMA 1: IMPERATIVA
//
//        for (Fabricante f: fabricantes) {
//            if (f.getCodigo().equals(codigo))
//                return f.getNombre();
//        }
//        return "desconocido";


        // FORMA 2: STREAM()
         return fabricantes.stream()
                 .filter(f -> f.getCodigo().equals(codigo))
                 .findFirst()
                 //.get().getNombre();
                 .map(Fabricante::getNombre)
                 .orElse("desconocido");
    }

    // NUEVO MÉTODO: obtiene los productos de un fabricante concreto
    public static List<Producto> obtenerProductosPorFabricante(List<Producto> productos, Integer codigoFabricante) {
        return productos.stream()
                .filter(p -> p.getCodigo_fabricante().equals(codigoFabricante))
                .sorted((p1, p2) -> p2.getNombre().compareToIgnoreCase(p1.getNombre())) // DESC
                //.sorted((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()))
                //.collect(Collectors.toList());
                .toList();
    }
}
