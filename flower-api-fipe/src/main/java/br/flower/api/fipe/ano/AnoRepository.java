package br.flower.api.fipe.ano;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnoRepository extends JpaRepository<Ano, String>{

}
