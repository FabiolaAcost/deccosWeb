package cl.patrones.taller.u2.tienda.menu;

import cl.patrones.taller.u2.catalogo.domain.Categoria;
import cl.patrones.taller.u2.tienda.menu.util.Slugger;

import java.util.List;

public class CategoriaItemMenuAdapter implements ItemMenu{

    private Categoria categoria;
    private List<CategoriaItemMenuAdapter> hijos;
    public CategoriaItemMenuAdapter(Categoria categoria, List<CategoriaItemMenuAdapter> hijos) {
        this.categoria = categoria;
        this.hijos = hijos;
    }
    @Override
    public String getTexto() {
        return categoria.getNombre();
    }

    @Override
    public String getSlug() {
        return Slugger.toSlug(categoria.getNombre());
    }

    @Override
    public String getEnlace() {
        return "/categoria/" + categoria.getId() + "/" + getSlug();
    }

    @Override
    public boolean tieneHijos() {
        return hijos != null && !hijos.isEmpty();
    }

    @Override
    public List<? extends ItemMenu> getHijos() {
        return hijos;
    }
}
