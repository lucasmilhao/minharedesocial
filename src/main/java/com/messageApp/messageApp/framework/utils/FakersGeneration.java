package com.messageApp.messageApp.framework.utils;

import java.util.Locale;

import com.github.javafaker.Faker;

public class FakersGeneration {
    private final Faker faker;
    private String email;
    private String nome;
    private String senha;
    private String imagem;
    private String descricao;


    public FakersGeneration() {
        faker = new Faker(new Locale("pt-BR"));
    }

    public String getEmail() {
        return faker.internet().emailAddress();
    }

    public String getNome() {
        return faker.name().firstName();
    }

    public String getSenha() {
        return faker.internet().password();
    }

    public String getImagem() {
        int id = faker.number().numberBetween(1, 1000);
        return "https://picsum.photos/id/" + id + "/200/300";
    }

    public String getDescricao() {
        return faker.shakespeare().asYouLikeItQuote();
    }
}
