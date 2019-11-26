package com.allain.curso;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.allain.curso.domain.Categoria;
import com.allain.curso.domain.Cidade;
import com.allain.curso.domain.Estado;
import com.allain.curso.domain.Produto;
import com.allain.curso.repositories.CategoriaRepository;
import com.allain.curso.repositories.CidadeRepository;
import com.allain.curso.repositories.EstadoRepository;
import com.allain.curso.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria (null,"Informática");
		Categoria cat2 = new Categoria (null,"Escritorio");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		cat1.getProduto().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProduto().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		Estado estd1 = new Estado(null,"Minas Gerais");
		Estado estd2 = new Estado(null,"São Paulo");
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Cidade c1 = new Cidade(null, "Uberlâdia", estd1);
		Cidade c2 = new Cidade(null, "São Paulo", estd2);
		Cidade c3 = new Cidade(null, "Campinas", estd2);
		
		estd1.getCidades().addAll(Arrays.asList(c1));
		estd2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(estd1,estd2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
	}

}
