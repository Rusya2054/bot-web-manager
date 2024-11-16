<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    ${currentUser}<br>
    <#if users?has_content>
        Пользователи:<br>
         <#list users as user>
            ${user}<br>
         </#list>
    </#if>
    <br>
    <div name="add-user">
        Добавить нового пользователя
        <form action="/admin/add-user" method="post">
            username: <input type="text" name="username"><br>
            password: <input type="text" name="password"><br>
            role:
            <select name="role">
                <#if roles?has_content>
                    <#list roles as role>
                        <option value="${role}">${role}</option>
                    </#list>
                </#if>
            </select>
            <input type="submit" value="Добавить"/>
        </form>
    </div>
</body>
</html>