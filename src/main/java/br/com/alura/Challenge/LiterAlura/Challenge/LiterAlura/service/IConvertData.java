package br.com.alura.Challenge.LiterAlura.Challenge.LiterAlura.service;

public interface IConvertData {
    <T> T obterDados(String json, Class<T> tClass);
}

