package ru.yandex.practicum.filmorate;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.controller.BaseController;
import ru.yandex.practicum.filmorate.model.Entity;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

public abstract class BaseFilmorateApplicationTest<T extends BaseController, E extends Entity> {
    public T controller;
    public E entity;
    HttpClient client = HttpClient.newHttpClient();
    public String validBody;
    public String emptyBody = "";
    public String invalidBody1;
    public String invalidBody2;
    public String invalidBody3;
    public String validUpdateBody;
    public String invalidUpdateBody;

    URI uri;
    String headerKey = "Content-Type";
    String headerValue = "application/json";
    HttpRequest request;
    HttpResponse<String> response;

    @Test
    void validAddNew() throws IOException, InterruptedException{
        request = HttpRequest.newBuilder().uri(uri).header(headerKey, headerValue)
                .POST(HttpRequest.BodyPublishers.ofString(validBody)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }
    @Test
    void failAddEmpty() throws IOException, InterruptedException{
        request = HttpRequest.newBuilder().uri(uri).header(headerKey, headerValue)
                .POST(HttpRequest.BodyPublishers.ofString(emptyBody)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertNotEquals(200, response.statusCode());
    }
    @Test
    void failAddIncorrectParam1() throws IOException, InterruptedException{
        request = HttpRequest.newBuilder().uri(uri).header(headerKey, headerValue)
                .POST(HttpRequest.BodyPublishers.ofString(invalidBody1)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertNotEquals(200, response.statusCode());
    }
    @Test
    void failAddIncorrectParam2() throws IOException, InterruptedException{
        request = HttpRequest.newBuilder().uri(uri).header(headerKey, headerValue)
                .POST(HttpRequest.BodyPublishers.ofString(invalidBody2)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertNotEquals(200, response.statusCode());
    }
    @Test
    void failAddIncorrectParam3() throws IOException, InterruptedException{
        request = HttpRequest.newBuilder().uri(uri).header(headerKey, headerValue)
                .POST(HttpRequest.BodyPublishers.ofString(invalidBody3)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertNotEquals(200, response.statusCode());
    }
    @Test
    void validUpdateExists() throws IOException, InterruptedException{
        request = HttpRequest.newBuilder().uri(uri).header(headerKey, headerValue)
                .POST(HttpRequest.BodyPublishers.ofString(validBody)).build();
        client.send(request, HttpResponse.BodyHandlers.ofString());

        request = HttpRequest.newBuilder().uri(uri).header(headerKey, headerValue)
                .PUT(HttpRequest.BodyPublishers.ofString(validUpdateBody)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());
    }
    @Test
    void invalidUpdateNotExists() throws IOException, InterruptedException{
        request = HttpRequest.newBuilder().uri(uri).header(headerKey, headerValue)
                .POST(HttpRequest.BodyPublishers.ofString(validBody)).build();
        client.send(request, HttpResponse.BodyHandlers.ofString());

        request = HttpRequest.newBuilder().uri(uri).header(headerKey, headerValue)
                .PUT(HttpRequest.BodyPublishers.ofString(invalidUpdateBody)).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertNotEquals(200, response.statusCode());
    }

}
