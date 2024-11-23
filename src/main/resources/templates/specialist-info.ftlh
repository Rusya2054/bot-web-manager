<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="_csrf" content="${_csrf.token}" charset="UTF-8">
    <title>specialist.fullName</title>
</head>
<script>
    function getCSRFToken() {
        const csrfTokenMeta = document.querySelector('meta[name="_csrf"]');
        return csrfTokenMeta ? csrfTokenMeta.content : '';
    }

    function updateSpecialist(id, action){
        const csrfToken = getCSRFToken();
        requestData = {
            id: id,
            action: action
        }
        fetch('/specialist/update/archive', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': csrfToken
            },
            body: JSON.stringify(requestData)
        })
        .then(response => {
            if (response.ok) {
                window.location.href = response.url;
            } else {
                console.log('Ошибка при удалении пользователя');
            }
        })
        .catch(error => {
            console.log('Ошибка сети:', error);
        });
    }

</script>
<style>
    body{
        background-color: #e6e6fa;
    }
</style>
<body>
    <input id="back-to-specialist" value="Вернуться" type="button" onclick="window.location.href='/specialist'">
    <#if specialistImage?has_content>
            <img id="specialistImage" height="400px" src="data:image/png;base64,${specialistImage}" /><br><br>
        <#else>
            Изображение отсутствует
    </#if>
    <form id="update-specialist" action="/specialist/update" method="post" enctype="multipart/form-data">
        ФИО: <input type="text" name="fullName" value="${specialist.fullName}"/><br>
        Телеграмм user_id:
            <#if specialist.tgUserID?has_content>
                <input type="text" value="${specialist.tgUserID?c}" name="tgUserID" /><br>
            <#else>
                <input type="number" name="tgUserID" /><br>
            </#if>
        Телефон:
            <#if specialist.phoneNumber?has_content>
                <input type="tel" name="phoneNumber" value="${specialist.phoneNumber}" pattern="^\+7\d{10}$"  placeholder="+7XXXXXXXXXX"/><br>
            <#else>
                <input type="tel" name="phoneNumber" pattern="^\+7\d{10}$"  placeholder="+7XXXXXXXXXX"/><br>
            </#if>
        Должность:
            <#if specialist.post?has_content>
                <input type="text" name="post" value="${specialist.post}" /><br>
            <#else>
                <input type="text" name="post"/><br>
            </#if>
        email:
            <#if specialist.email?has_content>
                <input type="email" name="email" value="${specialist.email}"/><br>
            <#else>
                <input type="email" name="email"/><br>
            </#if>
        Фотография: <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Сохранить изменения сотрудника"/>
    </form>
    <#if specialist.isActive?string == "true">
        Специалист активен
        <input type="button" value="Перенести в архив" onclick="updateSpecialist(${specialist.id}, 'setNonActive')">
    <#else>
        Специалист в архиве
        <input type="button" value="Извлечь из архива" onclick="updateSpecialist(${specialist.id}, 'setActive')">
    </#if>

</body>
</html>