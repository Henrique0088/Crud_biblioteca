package br.com.senai.teste.service;

import java.util.List;
import java.util.Optional;
import br.com.senai.teste.model.Livro;
import br.com.senai.teste.repository.LivroRepository;


import org.springframework.stereotype.Service;


@Service 
public class LivroService {
 private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Livro cadastrarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

   public Livro buscarLivroPorId(int id) {
    return livroRepository.findById(id).orElse(null);
   }

  
    public Optional<Livro> atualizarLivro(int id, Livro novosDados) {
        Optional<Livro> livroExistente = livroRepository.findById(id);
        if (livroExistente.isPresent()) {
            Livro livro = livroExistente.get();
            livro.setTitulo(novosDados.getTitulo());
            livro.setAutor(novosDados.getAutor());
            livro.setAnoPublicacao(novosDados.getAnoPublicacao());
            Livro livroAtualizado = livroRepository.save(livro);

          return Optional.of(livroAtualizado);
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteLivro(int id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return true;
        } else {
            throw new RuntimeException("Livro não encontrado com o ID: " + id);
        }
    }
}
