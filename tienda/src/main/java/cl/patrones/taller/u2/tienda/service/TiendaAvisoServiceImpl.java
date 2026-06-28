package cl.patrones.taller.u2.tienda.service;

import cl.patrones.taller.u2.bodegaje.service.BodegajeService;
import cl.patrones.taller.u2.catalogo.domain.Aviso;
import cl.patrones.taller.u2.catalogo.service.CategoriaService;
import cl.patrones.taller.u2.tienda.adapter.ProductoAvisoAdapter;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TiendaAvisoServiceImpl implements TiendaAvisoService{
    private BodegajeService bodegajeService;
    private CategoriaService categoriaService;
    private ProductoAvisoAdapter productoAvisoAdapter;

    public TiendaAvisoServiceImpl(
            BodegajeService bodegajeService,
            CategoriaService categoriaService,
            ProductoAvisoAdapter productoAvisoAdapter
    ) {
        this.bodegajeService = bodegajeService;
        this.categoriaService = categoriaService;
        this.productoAvisoAdapter = productoAvisoAdapter;
    }
    @Override
    public List<Aviso> getAvisos() {
        return productoAvisoAdapter.adaptar(bodegajeService.getProductos());
    }

    @Override
    public List<Aviso> getAvisosPorCategoriaId(Long categoriaId) {
        var clasificaciones = categoriaService.getClasificacionesPorCategoriaId(categoriaId);

        var skus = clasificaciones.stream()
                .map(clasificacion -> clasificacion.getSku())
                .toArray(String[]::new);

        var productos = bodegajeService.getProductosBySku(skus);

        return productoAvisoAdapter.adaptar(productos);
    }
}
