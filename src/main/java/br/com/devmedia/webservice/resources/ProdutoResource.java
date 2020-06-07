package br.com.devmedia.webservice.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.devmedia.webservice.model.domain.Produto;
import br.com.devmedia.webservice.resources.beans.FilterBean;
import br.com.devmedia.webservice.service.ProdutoService;

@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class ProdutoResource {

	private ProdutoService service = new ProdutoService();

	@GET
	public List<Produto> getProdutos(@PathParam("marcaId") long marcaId, @BeanParam FilterBean produtoFilter) {
		if ((produtoFilter.getOffset() >= 0) && (produtoFilter.getLimit() > 0)) {
			return service.getProdutosByPagination(marcaId, produtoFilter.getOffset(), produtoFilter.getLimit());
		}
		if (produtoFilter.getName() != null) {
			return service.getProdutoByName(marcaId, produtoFilter.getName());
		}

		return service.getProdutos(marcaId);
	}

	@GET
	@Path("{produtoId}")
	public Response getProduto(@PathParam("produtoId") long produtoId) {
		Produto produto = service.getProduto(produtoId);
		return Response.ok(produto).build();
	}

	@DELETE
	@Path("{produtoId}")
	public Response delete(@PathParam("produtoId") long id) {
		service.deleteProduto(id);
		return Response.noContent().build();
	}

	@POST
	public Response save(@PathParam("marcaId") Long marcaId, Produto produto) {
		produto = service.saveProduto(marcaId, produto);
		return Response.status(Status.CREATED)
				.entity(produto)
				.build();
	}

	@PUT
	@Path("{produtoId}")
	public Response update(@PathParam("marcaId") Long marcaId, @PathParam("produtoId") long id, Produto produto) {
		produto.setId(id);
		service.updateProduto(marcaId, produto);
		return Response.noContent().build();
	}

}