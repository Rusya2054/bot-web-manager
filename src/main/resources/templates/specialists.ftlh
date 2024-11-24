<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="_csrf" content="${_csrf.token}" charset="UTF-8">
    <title>Специалисты</title>
</head>
<style>
body {
    background-color: #e6e6fa;
    font-family: Arial, sans-serif;
}

#back-to-main {
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

#back-to-main:hover {
    background-color: #376ac5;
    color: white;
}

#add-specialist {
    background-color: white;
    padding: 20px;
    border-radius: 10px;
    border: 1px solid #ddd;
    width: 300px;
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
}

#add-specialist input[type="text"],
#add-specialist input[type="number"],
#add-specialist input[type="tel"],
#add-specialist input[type="email"],
#add-specialist input[type="file"] {
    width: 100%;
    padding: 8px;
    margin: 5px 0;
    border-radius: 5px;
    border: 1px solid #ddd;
}

#add-specialist input[type="submit"] {
    background-color: #376ac5;
    color: white;
    border: none;
    border-radius: 10px;
    cursor: pointer;
    padding: 10px;
    width: 100%;
}

#add-specialist input[type="submit"]:hover {
    background-color: #1e4c9b;
}

#specialistsContainer, #nonActiveSpecialistsContainer {
    display: flex;
    flex-direction: column;
    margin-top: 20px;
}

#specialistSearch {
    width: 100%;
    padding: 8px;
    border-radius: 5px;
    border: 1px solid #ddd;
    margin-bottom: 10px;
}

.specialist {
    background-color: white;
    padding: 10px;
    margin-bottom: 10px;
    border-radius: 10px;
    border: 1px solid #ddd;
}

.specialist a {
    color: #376ac5;
    text-decoration: none;
}

.specialist a:hover {
    text-decoration: underline;
}

.container {
    display: flex;
    justify-content: space-between;
    gap: 20px; /* Пространство между списками и формой */
    padding: 0 10%; /* Отступы слева и справа */
}

.container > div {
    width: 45%; /* Списки занимают 45% ширины контейнера */
}

#add-specialist {
    width: 30%; /* Форма занимает 30% ширины контейнера */
}
</style>

<body>
    <input id="back-to-main" value="Вернуться" type="button" onclick="window.location.href='/'">

    <div class="container">
        <!-- Список сотрудников слева -->
        <div>
            <div id="specialistsContainer">
                <input id='specialistSearch' type="search" placeholder="Введите для поиска" autocomplete="off">

                <#if activeSpecialists?has_content>
                    Список сотрудников
                    <#list activeSpecialists as specialist>
                        <div class="specialist" data-name="${specialist.fullName}">
                            <a href="/specialist/${specialist.id}">${specialist.fullName}</a><br>
                        </div>
                    </#list>
                <#else>
                    Необходимо добавить сотрудников
                </#if>
            </div>

            <!-- Архив сотрудников -->
            <div id="nonActiveSpecialistsContainer">
                <#if nonActiveSpecialists?has_content>
                    Архив сотрудников
                    <#list nonActiveSpecialists as specialist>
                        <div class="specialist" data-name="${specialist.fullName}">
                            <a href="/specialist/${specialist.id}">${specialist.fullName}</a><br>
                        </div>
                    </#list>
                </#if>
            </div>
        </div>

        <!-- Форма добавления специалиста справа -->
        <div id="add-specialist">
            <form action="/specialist/add" method="post" enctype="multipart/form-data">
                ФИО: <input type="text" name="fullName" /><br>
                Телеграмм user_id: <input type="number" name="tgUserID" required/><br>
                Телефон: <input type="tel" name="phoneNumber" pattern="^\+7\d{10}$" required placeholder="+7XXXXXXXXXX"/><br>
                Должность: <input type="text" name="post" required/><br>
                email: <input type="email" name="email" required/><br>
                Фотография: <input type="file" name="file" accept=".jpg, .png, .jpeg">
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <input type="submit" value="Добавить специалиста"/>
            </form>
        </div>
    </div>

    <#if message?has_content>
        ${message}
    </#if>
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