@import org.example.hexlet.dto.courses.CoursePage
@param CoursePage page

<!-- Изменения HTML в шаблоне не требуют перезапуска сервера -->
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>Hello Hexlet!</title>
</head>
<body>
<main>
    <h1>${page.getCourse().getName()}</h1>
    <p>${page.getCourse().getDescription()}</p>
</main>
</body>
</html>
