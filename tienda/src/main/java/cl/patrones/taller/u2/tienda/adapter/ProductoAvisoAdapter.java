package cl.patrones.taller.u2.tienda.adapter;
import cl.patrones.taller.u2.bodegaje.domain.Producto;
import cl.patrones.taller.u2.catalogo.domain.Aviso;
import cl.patrones.taller.u2.catalogo.service.CategoriaService;
import org.springframework.stereotype.Component;
import cl.patrones.taller.u2.bodegaje.domain.Stock;

import java.util.List;
import java.util.Optional;

@Component
public class ProductoAvisoAdapter {
    private CategoriaService categoriaService;

    public ProductoAvisoAdapter(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    public Optional<Aviso> adaptar(Producto producto) {
        return categoriaService.getClasificacionPorSku(producto.getSku())
                .map(clasificacion -> new Aviso(
                        producto.getId(),
                        producto.getSku(),
                        producto.getNombre(),
                        calcularPrecio(producto.getCosto()),
                        producto.getImagen(),
                        calcularStock(producto),
                        clasificacion.getCategoria()
                ));
    }

    public List<Aviso> adaptar(List<Producto> productos) {
        return productos.stream()
                .map(this::adaptar)
                .flatMap(Optional::stream)
                .toList();
    }

    private Long calcularPrecio(Long costo) {
        return Math.round(costo * 1.3);
    }

    private int calcularStock(Producto producto) {
        return producto.getStocks()
                .stream()
                .mapToInt(Stock::getCantidad)
                .sum();
    }
}
