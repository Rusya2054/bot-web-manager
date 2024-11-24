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
    body{
        background-color: #e6e6fa;
    }
    .menu {
        list-style: none;
        padding: 0;
        margin: 0;
    }

    .menu-item {
        border-radius: 10px;
        position: relative;
        padding: 10px 15px;
        background-color: white;
        cursor: pointer;
        border: 1px solid #ddd;
        margin-bottom: 5px;
    }

    .menu-item:hover {
        background-color: #376ac5;
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
        background-color: #376ac5;
    }

    .menu-item:hover .submenu {
        display: block;
    }
    #specialists, #clients, #statistics, #manage, #bot, #logout {
        background-color: transparent;
        height: 25px;
        width: 150px;
        font-family: Tahoma, sans-serif;
        font-size: 14px;
        color: black;
        border-radius: 10px;
        border: 0px solid #ccc;
    }

    #specialists:hover, #clients:hover, #statistics:hover, #manage:hover, #bot:hover, #logout:hover {
        background-color: #376ac5;
        color: white;
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
        display: flex;
        justify-content: space-between; /* Располагает элементы по краям */
        align-items: center; /* Выравнивание по центру по вертикали */
        width: 92%;
        border-radius: 10px;
        background-color: white;
    }

    .left-buttons {
        display: flex;
        gap: 10px;
    }
    #filial-info-storage {
        background-color: white;
        border-radius: 10px;
    }

</style>
<body>
    <div class="grid-container">
        <div id="tools">
            <div class="left-buttons">
                <input id="specialists" name="specialists" value="Специалисты" type="button" onclick="window.location.href='/specialist'">
                <input id="statistics" name="statistics" value="Статистика" type="button" onclick="window.location.href='/client'">
                <input id="clients" name="clients" value="Клиенты" type="button" onclick="window.location.href='/client'">
                <input id="manage" name="manage" value="Панель управления" type="button" onclick="window.location.href='/manage'">
                <input id="bot" name="bot" value="Бот" type="button" onclick="window.location.href='/client'">
            </div>
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
        <div id="filial-info-storage">
            текст
        </div>
    </div>
</body>
</html>