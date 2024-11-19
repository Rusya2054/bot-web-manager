<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="_csrf" content="${_csrf.token}" charset="UTF-8">
    <title>MAIN</title>
</head>
<script>
    function getCSRFToken() {
        const csrfTokenMeta = document.querySelector('meta[name="_csrf"]');
        return csrfTokenMeta ? csrfTokenMeta.content : '';
    }
    function logout() {
        const csrfToken = getCSRFToken();
        fetch('/logout', {
            method: 'POST',
            headers: {
                'X-XSRF-TOKEN': csrfToken
            }
        }).then(response => {
            if (response.redirected) {
                window.location.href = response.url;
            }
        });
    }
</script>
<style>
    .menu {
        list-style: none;
        padding: 0;
        margin: 0;
    }

    .menu-item {
        position: relative;
        padding: 10px 15px;
        background-color: #f0f0f0;
        cursor: pointer;
        border: 1px solid #ddd;
        margin-bottom: 5px;
    }

    .menu-item:hover {
        background-color: #e0e0e0;
    }

    .submenu {
        display: none; /* Скрываем подсписок */
        list-style: none;
        padding: 0;
        margin: 0;
        position: absolute;
        top: 100%; /* Показываем подсписок ниже родительского элемента */
        left: 0;
        background-color: #ffffff;
        border: 1px solid #ddd;
        z-index: 10;
        width: 200px;
    }

    .submenu li {
        padding: 10px;
        border-bottom: 1px solid #ddd;
    }

    .submenu li:last-child {
        border-bottom: none;
    }

    .submenu li:hover {
        background-color: #f0f0f0;
    }

    .menu-item:hover .submenu {
        display: block;
    }
    #specialists{
        background-color: transparent;
        background-image: url('/specialists-button.svg');
        background-repeat: no-repeat;
        background-position: center;
        background-size: contain;
        width: 150px;
        height: 150px;
        border: 1px solid #ccc;
        cursor: pointer;
        vertical-align: bottom;
    }

    #clients{
        background-color: transparent;
        background-image: url('/clients-button.svg');
        background-repeat: no-repeat;
        background-position: center;
        background-size: contain;
        width: 150px;
        height: 150px;
        border: 1px solid #ccc;
        cursor: pointer;
        vertical-align: bottom;
    }

    #statistics{
        background-color: transparent;
        background-image: url('/statistics-button.svg');
        background-repeat: no-repeat;
        background-position: center;
        background-size: contain;
        width: 150px;
        height: 150px;
        border: 1px solid #ccc;
        cursor: pointer;
        vertical-align: bottom;
    }

    #manage{
        background-color: transparent;
        background-image: url('/management-button.svg');
        background-repeat: no-repeat;
        background-position: center;
        background-size: contain;
        width: 150px;
        height: 150px;
        border: 1px solid #ccc;
        cursor: pointer;
        vertical-align: bottom;
    }

    #bot{
        background-color: transparent;
        background-image: url('/bot-button.svg');
        background-repeat: no-repeat;
        background-position: center;
        background-size: contain;
        width: 150px;
        height: 150px;
        border: 1px solid #ccc;
        cursor: pointer;
        vertical-align: bottom;
    }

    #logout{
        background-repeat: no-repeat;
        background-position: center;
        background-size: contain;
        width: 150px;
        height: 150px;
        border: 1px solid #ccc;
        cursor: pointer;
        vertical-align: bottom;
    }

    .grid-container {
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        grid-template-rows: repeat(3, auto);
        gap: 10px;
        width: 100%;
        height: 100%;
    }
    #tools {
        grid-column: 1 / 4; /* Занимает весь верхний ряд */
        display: grid; /* Включаем CSS Grid */
        grid-template-columns: repeat(6, 1fr); /* Разбиваем на 6 равных колонок */
        gap: 10px;
    }
    #filial-list {
        grid-column: 1; /* Первый столбец */
        grid-row: 2 / 4; /* Занимает два ряда */
    }
    #filial-info-storage {
        grid-column: 2 / 4; /* Занимает два столбца справа */
        grid-row: 2 / 4; /* Занимает два ряда */
    }
    #tools input {
        grid-column: span 1; /* Каждый input занимает одну колонку */
        max-width: 100%; /* Предотвращаем перерастяжение */
    }
</style>
<body>
    <div class="grid-container">
        <div id="tools" style="background-color: red;">
            <input id="specialists" name="specialists" value="специалисты" type="button" onclick="window.location.href='/specialist'">
            <input id="statistics" name="statistics" value="статистика" type="button" onclick="window.location.href='/client'">
            <input id="clients" name="clients" value="Клиенты" type="button" onclick="window.location.href='/client'">
            <input id="manage" name="manage" value="Панель управления" type="button" onclick="window.location.href='/client'">
            <input id="bot" name="bot" value="Бот" type="button" onclick="window.location.href='/client'">
            <input id="logout" name="logout" value="Выход" type="button" onclick="logout()">
        </div>
        <div id="filial-list">
            <#if filials?has_content>
                <ul class="menu">
                    <#list filials as filial>
                        <li class="menu-item">${filial.name}
                            <ul class="submenu">
                                <li>Подэлемент 1</li>
                                <li>Подэлемент 2</li>
                                <li>Подэлемент 3</li>
                            </ul>
                        </li>
                    </#list>
                </ul>
            </#if>
        </div>
        <div id="filial-info-storage" style="background-color: green;">
            текст
        </div>
    </div>
</body>
</html>