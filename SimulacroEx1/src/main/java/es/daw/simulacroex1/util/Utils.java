package es.daw.simulacroex1.util;


import es.daw.simulacroex1.model.Fabricante;

import java.util.List;
import java.util.stream.Collectors;

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

}
