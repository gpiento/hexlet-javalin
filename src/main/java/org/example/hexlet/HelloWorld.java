package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.model.Course;

import java.util.List;
import java.util.Optional;

import static io.javalin.rendering.template.TemplateUtil.model;

public class HelloWorld {
    public static void main(String[] args) {
        // Создаем приложение
        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });
        app.get("/", ctx -> ctx.render("layout/page.jte", model("header", "Hello World!")));
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

/*
        app.get("/courses/{courseId}/lessons/{id}", ctx -> {
            ctx.result("Course ID: " + ctx.pathParam("courseId"));
            ctx.result("Lesson ID: " + ctx.pathParam("id"));
        });
*/
        List<Course> courseList = List.of(
                new Course("nameCourse1", "descriptionCourse1"),
                new Course("nameCourse2", "descriptionCourse2"),
                new Course("nameCourse3", "descriptionCourse3")
        );
        app.get("/courses/{id}", ctx -> {
            String id = ctx.pathParam("id");
            Course course = courseList.get(Integer.parseInt(id) - 1);
            CoursePage page = new CoursePage(course);
            ctx.render("courses/show.jte", model("page", page));
        });

        app.start(7070);
    }
}
