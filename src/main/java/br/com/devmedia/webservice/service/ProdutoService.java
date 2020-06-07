package br.com.devmedia.webservice.service;

import java.util.List;

import br.com.devmedia.webservice.model.dao.ProdutoDAO;
import br.com.devmedia.webservice.model.domain.Produto;

public class ProdutoService {

	private ProdutoDAO dao = new ProdutoDAO();

	public List<Produto> getProdutos(Long marcaId) {
		return dao.getAll(marcaId);
	}

	public Produto getProduto(Long id) {
		return dao.getById(id);
	}

	public Produto saveProduto(Long marcaId, Produto produto) {
		return dao.save(marcaId, produto);
	}

	public Produto updateProduto(Long marcaId, Produto produto) {
		return dao.update(marcaId, produto);
	}

	public Produto deleteProduto(Long id) {
		return dao.delete(id);
	}

	public List<Produto> getProdutosByPagination(Long marcaId, int firstResult, int maxResults) {
		return dao.getByPagination(marcaId, firstResult, maxResults);
	}

	public List<Produto> getProdutoByName(Long marcaId, String name) {
		return dao.getByName(marcaId, name);
	}
}