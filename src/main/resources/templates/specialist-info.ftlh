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
body {
    background-color: #e6e6fa;
    font-family: Arial, sans-serif;
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

#back-to-specialist {
    position: absolute;
    top: 20px; /* Отступ сверху */
    left: 20px; /* Отступ слева */
    background-color: white;
    height: 30px;
    width: 150px;
    font-size: 14px;
    color: black;
    border-radius: 10px;
    border: 1px solid #ddd;
    cursor: pointer;
    margin-bottom: 20px;
}

#back-to-specialist:hover {
    background-color: #376ac5;
    color: white;
}

.container {
    display: flex;
    align-items: flex-start;
    justify-content: space-between;
    width: 100%;
    max-width: 1000px;
}

#specialistImage {
    height: 400px;
    width: auto;
    margin-right: 20px;
    border-radius: 10px;
    border: 1px solid #ddd;
}

#update-specialist {
    background-color: white;
    padding: 20px;
    border-radius: 10px;
    border: 1px solid #ddd;
    width: 60%;
    display: flex;
    flex-direction: column;
}

#update-specialist input[type="text"],
#update-specialist input[type="number"],
#update-specialist input[type="tel"],
#update-specialist input[type="email"],
#update-specialist input[type="file"] {
    width: 100%;
    padding: 8px;
    margin: 10px 0;
    border-radius: 5px;
    border: 1px solid #ddd;
}

#update-specialist input[type="submit"] {
    background-color: #376ac5;
    color: white;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    padding: 10px;
    width: 100%;
}

#update-specialist input[type="submit"]:hover {
    background-color: #1e4c9b;
}

#update-specialist input[type="button"] {
    background-color: #f4f4f4;
    color: #376ac5;
    border: 1px solid #ddd;
    padding: 10px;
    width: 100%;
    border-radius: 10px;
    margin-top: 10px;
    cursor: pointer;
}

#update-specialist input[type="button"]:hover {
    background-color: #376ac5;
    color: white;
}

#specialistStatus {
    text-align: center;
    margin-top: 20px;
    font-size: 18px;
}

#activeButton, #nonActiveButton {
    background-color: #376ac5;
    color: white;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    padding: 10px 20px;
    font-size: 16px;
    margin-top: 10px;
    width: auto;
    transition: background-color 0.3s, transform 0.2s;
}

#activeButton:hover, #nonActiveButton:hover {
    background-color: #1e4c9b;
    transform: scale(1.05);
}

#activeButton:active, #nonActiveButton:active {
    background-color: #155a8a;
    transform: scale(1);
}

#nonActiveButton {
    background-color: #f4a261;
}

#nonActiveButton:hover {
    background-color: #e07b47;
}
</style>

<body>
    <input id="back-to-specialist" value="Вернуться" type="button" onclick="window.location.href='/specialist'">

    <div class="container">
        <#if specialistImage?has_content>
            <img id="specialistImage" src="data:image/png;base64,${specialistImage}" /><br><br>
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
                <input type="tel" name="phoneNumber" value="${specialist.phoneNumber}" pattern="^\+7\d{10}$" placeholder="+7XXXXXXXXXX"/><br>
            <#else>
                <input type="tel" name="phoneNumber" pattern="^\+7\d{10}$" placeholder="+7XXXXXXXXXX"/><br>
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

            Фотография: <input type="file" name="file" accept=".jpg, .png, .jpeg">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Сохранить изменения сотрудника"/>
        </form>
    </div>

    <div id="specialistStatus">
        <#if specialist.isActive?string == "true">
            Специалист активен
            <input id="activeButton" type="button" value="Перенести в архив" onclick="updateSpecialist(${specialist.id}, 'setNonActive')">
        <#else>
            Специалист в архиве
            <input id="nonActiveButton" type="button" value="Извлечь из архива" onclick="updateSpecialist(${specialist.id}, 'setActive')">
        </#if>
    </div>
</body>
</html>