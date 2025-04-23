package br.edu.ifpb.daw.diario.repository;

import br.edu.ifpb.daw.diario.entity.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {

}
