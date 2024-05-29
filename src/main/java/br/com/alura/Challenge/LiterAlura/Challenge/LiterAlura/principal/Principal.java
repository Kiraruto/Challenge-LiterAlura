package br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.principal;

import br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.Model.DadosAtor;
import br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.Model.DadosLivros;
import br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.records.Result;
import br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.records.Results;
import br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.repository.IAuthorAndBooksRepository;
import br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.service.ApiToJson;
import br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.service.ConvertData;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {

    private final String URL1 = "https://gutendex.com/books/?search=";
    private Scanner scanner = new Scanner(System.in);
    private final ApiToJson converterDados = new ApiToJson();
    private final ConvertData convertData = new ConvertData();
    private List<DadosAtor> listDadosAtor = new ArrayList<>();

    private IAuthorAndBooksRepository repositorio;

    public Principal(IAuthorAndBooksRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void run() {

        String choose = "";

        while (!choose.equalsIgnoreCase("0")) {

            System.out.println("""
                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Listar autores registardos
                    4- Listar autores vivos em um determinado ano
                    5- Listar livros em um determinado idioma
                    
                    0- Sair
                    """);

            choose = scanner.next();
            scanner.nextLine();

            switch (choose) {
                case "1":
                    buscarLivros();
                    break;
                case "2":
                    listarLivros();
                    break;
                case "3":
                    listarAutores();
                    break;
                case "4":
                    autoresVivos();
                    break;
                case "5":
                    linguaLivro();
                    break;
                case "0":
                    System.out.println("Saindo...");
                    break;
            }
        }
    }

    private void buscarLivros() {
        System.out.println("Qual nome do livro deseja buscar?");
        String chooseBook = scanner.nextLine();
        System.out.println(chooseBook);

        String url = URL1 + chooseBook.replace(" ", "%20").toLowerCase();
        var json = converterDados.http(url);

        if (json == null || json.trim().isEmpty() || !json.contains("results")) {
            System.out.println("Nenhum dado encontrado para o título especificado.");
            return;
        }

        Results livros = convertData.obterDados(json, Results.class);

        if (livros.results().isEmpty()) {
            System.out.println("Nenhum dado encontrado para o Livro ou Autor especificado.");
            return;
        }

        DadosAtor dadosAtor = new DadosAtor(livros);

        for (Result result : livros.results()) {
            DadosLivros dadosLivros = new DadosLivros(result);
            dadosLivros.setDadosAtor(dadosAtor);
            dadosAtor.getDadosLivrosList().add(dadosLivros);

            try {
                repositorio.save(dadosAtor);
                System.out.println("************************");
                System.out.println("Livro salvo com sucesso!");
                System.out.println("************************");
            } catch (DataIntegrityViolationException e) {
                System.out.println("***************************************************");
                System.out.println("Erro: O livro com o mesmo título e autor já existe.");
                System.out.println("***************************************************");
                break;
            } catch (Exception e) {
                System.out.println("****************************************");
                System.out.println("Erro: Ocorreu um erro ao salvar o livro.");
                System.out.println("****************************************");
                break;
            }
        }
    }

    private void listarLivros() {
        listDadosAtor = repositorio.findAll();
        methodLivros();
    }

    private void listarAutores() {
        listDadosAtor = repositorio.findAll();
        methodAutor();
    }

    private void autoresVivos() {
        System.out.println("Insira o ano que deseja pesquisar");
        Long years = scanner.nextLong();

        listDadosAtor = repositorio.atoresVivos(years);
        methodAutor();
    }

    private void linguaLivro(){
        System.out.println("""
                Insira o idioma para realizar a busca:
                es- espanhol
                en- inglês
                fr- francês
                pt- português
                """);
        String language = scanner.next();

        listDadosAtor = repositorio.linguaLivros(language);
        methodLivros();
    }

    private void methodAutor() {
        listDadosAtor.stream()
                .map(a -> " Autor: " + a.getNameAuthor() + "\n" + " Ano de nascimento: " + a.getBirthYear() + "\n" + " Ano de falecimento: " + a.getDeathYear() + "\n" + " Livros: " + a.getDadosLivrosList().stream()
                        .map(l -> "[" + l.getTitleBook()).collect(Collectors.joining(", ")) + "]" + "\n")
                .forEach(System.out::println);
    }

    private void methodLivros() {
        listDadosAtor.stream()
                .flatMap(a -> a.getDadosLivrosList().stream()
                        .map(l -> " ----- LIVROS ----- " + "\n" + "  Título: " + l.getTitleBook() + "\n" + "  Autor: " + l.getDadosAtor().getNameAuthor() + "\n" + "  Idioma : " + l.getLanguages() + "\n" + "  Número de downloads : " + l.getDownloadCout() + "\n" + " -------------------" + "\n"))
                .forEach(System.out::println);
    }
}


