<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="_csrf" content="${_csrf.token}" charset="UTF-8">
    <title>Специалисты</title>
</head>
<style>
    body{
        background-color: #e6e6fa;
    }
</style>
<body>
    <input id="back-to-main" value="Вернуться" type="button" onclick="window.location.href='/'">
    <form id="add-specialist" action="/specialist/add" method="post" enctype="multipart/form-data">
        ФИО: <input type="text" name="fullName"/><br>
        Телеграмм user_id: <input type="number" name="tgUserID"/><br>
<!--        Телефон: <input type="tel" name="phoneNumber" pattern="^\+7\d{10}$" required placeholder="+7XXXXXXXXXX"/><br>-->
        Телефон: <input type="tel" name="phoneNumber"/><br>
        Должность: <input type="text" name="post"/><br>
<!--        email: <input type="email" name="email"/><br>-->
        email: <input type="text" name="email"/><br>
        Фотография: <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Добавить специалиста"/>
    </form>
    <#if specialists?has_content>
        <#list specialists as specialist>
            ФИО:  <a href="/specialist/${specialist.id}">${specialist.fullName}</a><br>
        </#list>
    </#if>
</body>
</html>