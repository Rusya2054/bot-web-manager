<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="_csrf" content="${_csrf.token}" charset="UTF-8">
    <title>Панель управления</title>
</head>
<body>
<input id="back-to-main" value="Вернуться" type="button" onclick="window.location.href='/'">
    <div id="add-filial">
        <form action="/filial/add" method="post" enctype="multipart/form-data">
            ФИО: <input type="text" name="name" /><br>
            ФИО: <input type="text" name="address" /><br>
            Телефон: <input type="tel" name="phoneNumber" pattern="^\+7\d{10}$" required placeholder="+7XXXXXXXXXX"/><br>
            Фед. телефон: <input type="tel" name="federalNumber" pattern="^\+7\d{10}$" required placeholder="+7XXXXXXXXXX"/><br>
            Фотография: <input type="file" name="file" accept=".jpg, .png, .jpeg">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="submit" value="Добавить филиал"/>
        </form>
    </div>
    <div id="filials">
        <#if filials?has_content>
            <#list filials as filial>
                <div class="filial" data-name="${filial.id}">
                    <a href="/filial/${filial.id}">${filial.name}</a><br>
                </div>
            </#list>
        </#if>
    </div>
<!--    TODO: добавить сортировку по филиалам и по специалистам -->
    <div id="services-tools">
        <input id="add-service" value="Добавить" type="button" onclick="addRow()">
    </div>
    <div id="services-container">
        <table id="service-table">
            <thead>
                <tr>
                    <th>Название</th>
                    <th>Филиал</th>
                    <th>Специалист</th>
                    <th>Действия</th>
                </tr>
                <#if services?has_content>
                    <#list services as service>
                        <div id="${service.id?c}">
                            <tr id="row-${service.id?c}">
                                <td>
                                    <#if service.name?has_content>
                                        <input type="text" value="${service.name}" name="name-${service.id?c}"/>
                                    <#else>
                                        <input type="text" value="${service.name}" name="name-${service.id?c}"/>
                                    </#if>
                                </td>
                                <td>
                                    <select name="filials-${service.id?c}">
                                        <option value="" disabled selected>Выберите филиал</option>
                                        <#list filials as filial>
                                            <#if service.filial?has_content && service.filial == filial>
                                                <option value="${service.filial.id?c}" selected>${service.filial.name}</option>
                                            <#else>
                                                 <option value="${filial.id?c}">${filial.name}</option>
                                            </#if>

                                        </#list>
                                    </select>
                                </td>
                                <td>
                                    <select name="specialists-${service.id?c}">
                                        <option value="" disabled selected>Выберите специалиста</option>
                                        <#list specialists as specialist>
                                            <#if service.specialist?has_content && service.specialist == specialist>
                                                <option value="${service.specialist.id?c}" selected>${service.specialist.fullName}</option>
                                            <#else>
                                                <option value="${specialist.id?c}">${specialist.fullName}</option>
                                            </#if>
                                        </#list>
                                    </select>
                                </td>
                                <td>
                                    <input type="button" value="Удалить" onclick="deleteRow('row-${service.id?c}')">
                                    <input type="button" value="Сохранить" onclick="saveRow('row-${service.id?c}')">
                                </td>
                            </tr>
                        </div>
                    </#list>
                </#if>
            </thead>
        </table>
    </div>
</body>
<script>
    let rowCounter = ${services?size!0};

    function addRow() {
        rowCounter++;
        const tableBody = document.querySelector('table[id="service-table"]');

        const newRow = document.createElement('tr');

        newRow.id = 'new-row-' + rowCounter;

        newRow.innerHTML =
            '<td>' +
                '<input type="text" name="name-new" value="" required/>' +
            '</td>' +
            '<td>' +
                '<select name="filials-new">' +
                    '<option value="" disabled selected>Выберите филиал</option>' +
                    '<#list filials as filial>' +
                        '<option value="${filial.id?c}">${filial.name}</option>' +
                    '</#list>' +
                '</select>' +
            '</td>' +
            '<td>' +
                '<select name="specialists-new">' +
                    '<option value="" disabled selected>Выберите специалиста</option>' +
                    '<#list specialists as specialist>' +
                        '<option value="${specialist.id?c}">${specialist.fullName}</option>' +
                    '</#list>' +
                '</select>' +
            '</td>' +
            '<td>' +
                '<input type="button" value="Удалить" onclick="deleteRow(\'new-row-' + rowCounter + '\')">' +
                '<input type="button" value="Сохранить" onclick="saveRow(\'new-row-' + rowCounter + '\')">' +
            '</td>';
        tableBody.appendChild(newRow);
    }
    function deleteRow(inputRow){
        if (inputRow.startsWith("new")){
            const row = document.getElementById(inputRow);
            row.remove();
        } else {
            const elementId = Number(inputRow.split("-")[1]);
            console.log(elementId);
        }
        console.log("deleteRow");
        console.log(inputRow);
    }
    function saveRow(inputRow){
        if (inputRow.startsWith("new")){
            const row = document.getElementById(inputRow);
            console.log(row);
        } else {
            const elementId = Number(inputRow.split("-")[1]);
            console.log(elementId);
        }
    }
</script>
</html>