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
        ФИО: <input type="text" name="fullName" /><br>
        Телеграмм user_id: <input type="number" name="tgUserID" required/><br>
        Телефон: <input type="tel" name="phoneNumber" pattern="^\+7\d{10}$" required placeholder="+7XXXXXXXXXX"/><br>
        Должность: <input type="text" name="post" required/><br>
        email: <input type="email" name="email" required/><br>
        Фотография: <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <input type="submit" value="Добавить специалиста"/>
    </form>
    <#if message?has_content>
            ${message}
    </#if>
    Сотрудники
    <input id='specialistSearch' type="search" placeholder="Введите для поиска" autocomplete="off">
    <div id="specialistsContainer" class="container">
        <#if activeSpecialists?has_content>
            Список сотрудников
            <#list activeSpecialists as specialist>
                    <div class="specialist" data-name="${specialist.fullName}">
                    ФИО:  <a href="/specialist/${specialist.id}">${specialist.fullName}</a><br>
                    </div>
            </#list>
            <#else>
                Необходимо добавить сотрудников
        </#if>
    </div>
    <div id="nonActiveSpecialistsContainer" class="container">
        <#if nonActiveSpecialists?has_content>
            Архив сотрудников
            <#list nonActiveSpecialists as specialist>
                    <div class="specialist" data-name="${specialist.fullName}">
                    ФИО:  <a href="/specialist/${specialist.id}">${specialist.fullName}</a><br>
                    </div>
            </#list>
        </#if>
    </div>

</body>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const searchInput = document.getElementById('specialistSearch');
        const specialistsContainer = document.getElementById('specialistsContainer');
        const specialists = specialistsContainer.querySelectorAll('.specialist');

        searchInput.addEventListener('input', function () {
            const query = this.value.toLowerCase().trim();

            specialists.forEach(specialist => {
                const name = specialist.dataset.name.toLowerCase();
                if (name.includes(query)) {
                    specialist.style.display = 'block'; // Показать сотрудника
                } else {
                    specialist.style.display = 'none'; // Скрыть сотрудника
                }
            });
        });
    });
</script>
</html>