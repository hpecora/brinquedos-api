package br.com.fiap.brinquedosapi.repository;

import br.com.fiap.brinquedosapi.model.Brinquedo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrinquedoRepository extends JpaRepository<Brinquedo, Long> {
}
