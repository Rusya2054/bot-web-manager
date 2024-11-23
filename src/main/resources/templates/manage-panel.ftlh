<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="_csrf" content="${_csrf.token}" charset="UTF-8">
    <title>Title</title>
</head>

<body>
<div id="add-filial">
            <form action="/filial/add" method="post" enctype="multipart/form-data">
                ФИО: <input type="text" name="fullName" /><br>
                Телеграмм user_id: <input type="number" name="tgUserID" required/><br>
                Телефон: <input type="tel" name="phoneNumber" pattern="^\+7\d{10}$" required placeholder="+7XXXXXXXXXX"/><br>
                Должность: <input type="text" name="post" required/><br>
                email: <input type="email" name="email" required/><br>
                Фотография: <input type="file" name="file">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="submit" value="Добавить специалиста"/>
            </form>
        </div>
</body>
</html>