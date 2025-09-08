package br.com.fiap.brinquedosapi.service;

import br.com.fiap.brinquedosapi.model.Brinquedo;
import br.com.fiap.brinquedosapi.repository.BrinquedoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrinquedoService {

    private final BrinquedoRepository repository;

    public BrinquedoService(BrinquedoRepository repository) {
        this.repository = repository;
    }

    // GET /brinquedos
    public List<Brinquedo> listarTodos() {
        return repository.findAll();
    }

    // GET /brinquedos/{id}
    public Brinquedo buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // POST /brinquedos
    @Transactional
    public Brinquedo criar(Brinquedo brinquedo) {
        return repository.save(brinquedo);
    }

    // PUT /brinquedos/{id} (atualização completa)
    @Transactional
    public Brinquedo atualizar(Long id, Brinquedo brinquedoAtualizado) {
        return repository.findById(id).map(b -> {
            b.setNome(brinquedoAtualizado.getNome());
            b.setTipo(brinquedoAtualizado.getTipo());
            b.setClassificacao(brinquedoAtualizado.getClassificacao());
            b.setTamanho(brinquedoAtualizado.getTamanho());
            b.setPreco(brinquedoAtualizado.getPreco());
            return repository.save(b);
        }).orElse(null);
    }

    // PATCH /brinquedos/{id} (atualização parcial)
    @Transactional
    public Brinquedo atualizarParcial(Long id, Brinquedo parcial) {
        return repository.findById(id).map(b -> {
            if (parcial.getNome() != null) b.setNome(parcial.getNome());
            if (parcial.getTipo() != null) b.setTipo(parcial.getTipo());
            if (parcial.getClassificacao() != null) b.setClassificacao(parcial.getClassificacao());
            if (parcial.getTamanho() != null) b.setTamanho(parcial.getTamanho());
            if (parcial.getPreco() != null) b.setPreco(parcial.getPreco());
            return repository.save(b);
        }).orElse(null);
    }

    // DELETE /brinquedos/{id}
    @Transactional
    public boolean excluir(Long id) {
        if (!repository.existsById(id)) return false;
        repository.deleteById(id);
        return true;
    }
}
