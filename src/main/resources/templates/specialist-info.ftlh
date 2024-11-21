<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="_csrf" content="${_csrf.token}" charset="UTF-8">
    <title>specialist.fullName</title>
</head>
<style>
    body{
        background-color: #e6e6fa;
    }
</style>
<body>
    <input id="back-to-specialist" value="Вернуться" type="button" onclick="window.location.href='/specialist'">
    <#if specialist.image?has_content>
        <img src="/images/${img.id}" height="60px"/><br><br>
    </#if>
<!--    <#if specialists?has_content>-->
<!--        <#list specialists as specialist>-->
<!--            ФИО:  <a href="/specialist/${specialist.id}">${specialist.fullName}</a><br>-->
<!--        </#list>-->
<!--    </#if>-->
</body>
</html>