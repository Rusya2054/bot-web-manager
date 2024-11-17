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
        background-image: url('/specialists.svg');
        background-repeat: no-repeat;
        background-position: center;
        background-size: contain;
        width: 150px;
        height: 150px;
        border: 1px solid #ccc;
        cursor: pointer;
        vertical-align: bottom;
    }
</style>
<body>
    ${currentUser}<br>
    Main
    <div name="tools">
        <input id="specialists" name="specialists" value="специалисты" type="button" onclick="window.location.href='/specialist'">
        <input name="statistics" type="button" onclick="logout()">
        <input name="clients" type="button" onclick="logout()">
         <input name="logout" value="Выход" type="button" onclick="logout()">
    </div>
    <#if filials?has_content>
        <ul class="menu">
        <#list filials as filial>
            <li class="menu-item"> ${filial.name}
                <ul class="submenu">
                    <li>Подэлемент 1</li>
                    <li>Подэлемент 2</li>
                    <li>Подэлемент 3</li>
                </ul>
            </li>
        </#list>
        </ul>
    </#if>

</body>
</html>