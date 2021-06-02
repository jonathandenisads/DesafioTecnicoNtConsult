package desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import desafio.model.Associado;

public interface AssociadoRepository extends JpaRepository<Associado, Integer> {

	Associado findByCpf(String cpf);
}
