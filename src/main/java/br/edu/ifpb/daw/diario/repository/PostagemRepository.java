package br.edu.ifpb.daw.diario.repository;

import br.edu.ifpb.daw.diario.entity.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    //Esse metodo é uma consulta direta no banco de dados, por isso está no repository, o service ficará responsável por usá-la.
    List<Postagem> findByTituloContainingIgnoreCase(String titulo);
}
