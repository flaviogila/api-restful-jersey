package br.com.devmedia.webservice.resources;

import br.com.devmedia.webservice.model.domain.Marca;
import br.com.devmedia.webservice.resources.beans.FilterBean;
import br.com.devmedia.webservice.service.MarcaService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/marcas")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class MarcaResource {

    private MarcaService service = new MarcaService();

    @GET
    public List<Marca> getMarcas(@BeanParam FilterBean marcaFilter) {
        if ((marcaFilter.getOffset() >= 0) && (marcaFilter.getLimit() > 0)) {
            return service.getMarcasByPagination(marcaFilter.getOffset(), marcaFilter.getLimit());
        }
        if (marcaFilter.getName() != null) {
            return service.getMarcaByName(marcaFilter.getName());
        }

        return service.getMarcas();
    }

    @GET
    @Path("{marcaId}")
    public Response getMarca(@PathParam("marcaId") long id) {
        Marca marca = service.getMarca(id);
        return Response.ok(marca).build();
    }

    @DELETE
    @Path("{marcaId}")
    public Response delete(@PathParam("marcaId") long id) {
        service.deleteMarca(id);
        return Response.noContent().build();
    }

    @POST
    public Response save(Marca marca) {
        marca = service.saveMarca(marca);
        return Response.status(Response.Status.CREATED)
                .entity(marca)
                .build();
    }

    @PUT
    @Path("{marcaId}")
    public Response update(@PathParam("marcaId") long id, Marca marca) {
        marca.setId(id);
        service.updateMarca(marca);
        return Response.noContent().build();
    }

    @Path("{marcaId}/produtos")
    public ProdutoResource getProdutoResource() {
        return new ProdutoResource();
    }
}