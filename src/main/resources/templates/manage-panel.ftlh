<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="_csrf" content="${_csrf.token}" charset="UTF-8">
    <title>Title</title>
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

    </div>
    <div id="services-tools">
        <input id="add-service" value="Добавить" type="button" onclick="">
<!--        <input id="add-service" value="Добавить" type="button" onclick="">-->
    </div>
<!--    TODO: добавить сортировку по филиалам и по специалистам -->
    <div id="services-container">
        <table>
            <thead>
                <tr>
                    <th>Название</th>
                    <th>Филиал</th>
                    <th>Специалист</th>
                    <th>Действия</th>
                </tr>
                <#if services?has_content>
                    <#list services as service>
                        <tr>
                            <td>
                                <#if service.name?has_content>
                                    <input type="text" value="${service.name}" name="name-${service.id}"/>
                                <#else>
                                    <input type="text" value="${service.name}" name="name-${service.id}"/>
                                </#if>
                            </td>
                            <td>
                                <select name="filials-${service.id}">
                                    <option value="" disabled selected>Выберите филиал</option>
                                    <#list filials as filial>
                                        <#if service.filial?has_content && service.filial == filial>
                                            <option value="${service.filial.id}" selected>${service.filial.name}</option>
                                        <#else>
                                             <option value="${filial.id}">${filial.name}</option>
                                        </#if>

                                    </#list>
                                </select>
                            </td>
                            <td>
                                <select name="specialists-${service.id}">
                                    <option value="" disabled selected>Выберите специалиста</option>
                                    <#list specialists as specialist>
                                        <#if service.specialist?has_content && service.specialist == specialist>
                                            <option value="${service.specialist.id}" selected>${service.specialist.fullName}</option>
                                        <#else>
                                            <option value="${specialist.id}">${specialist.fullName}</option>
                                        </#if>
                                    </#list>
                                </select>
                            </td>
                            <td>
                                <input type="button" value="Удалить" onclick="">
                                <input type="button" value="Сохранить" onclick="">
                            </td>
                        </tr>
                    </#list>
                </#if>
            </thead>
        </table>
    </div>
</body>
</html>