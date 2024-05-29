package br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura;

import br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.principal.Principal;
import br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.repository.IAuthorAndBooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeLiterAluraApplication implements CommandLineRunner {

	@Autowired
	private IAuthorAndBooksRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.run();
	}
}

