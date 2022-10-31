package br.inatel.labs.labjpa.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.inatel.labs.labjpa.entity.NotaCompra;
import br.inatel.labs.labjpa.entity.NotaCompraItem;

@Service
@Transactional
public class NotaCompraService {
	@PersistenceContext
	private EntityManager em;

	public NotaCompra salvarNotaCompra(NotaCompra nota) {
		return em.merge(nota);
	}

	public NotaCompra buscarNotaCompraPeloId(Long id) {
		return em.find(NotaCompra.class, id);
	}

	public List<NotaCompra> listarNotaCompra() {
		return em.createQuery("select n from NotaCompra n", NotaCompra.class).getResultList();
	}

	// nota compra item

	public NotaCompraItem salvarNotaCompraItem(NotaCompraItem item) {
		return em.merge(item);
	}

	public NotaCompraItem buscarNotaCompraItemPeloId(Long id) {
		return em.find(NotaCompraItem.class, id);
	}
	
	public NotaCompra buscarNotaCompraPeloIdComListaItem(Long id) {
		NotaCompra nota = em.find(NotaCompra.class, id);
		nota.getListaNotaCompraItem().size(); // Provocando Proxy para buscar a lista de itens
		return nota;
	}
	

	public List<NotaCompraItem> listarNotaCompraItem() {
		return em.createQuery("select i from NotaCompraItem i", NotaCompraItem.class).getResultList();
	}
}
