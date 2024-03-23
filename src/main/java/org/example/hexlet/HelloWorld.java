package org.example.hexlet;

import io.javalin.Javalin;

import java.util.Optional;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });
        app.get("/", ctx -> ctx.result("Hello World"));
        app.get("/users", ctx -> ctx.result("GET /users"));
        app.post("/users", ctx -> ctx.result("POST /users"));
        app.get("/hello", ctx -> {
            Optional<String> name = Optional.ofNullable(ctx.queryParam("name"));
            if (name.isPresent()) {
                ctx.result(String.format("Hello, %s!\n", name.get()));
            } else {
                ctx.result("Hello, World!\n");
            }
        });

        app.get("/courses/{courseId}/lessons/{id}", ctx -> {
            ctx.result("Course ID: " + ctx.pathParam("courseId"));
            ctx.result("Lesson ID: " + ctx.pathParam("id"));
        });

        app.start(7070);
    }
}
