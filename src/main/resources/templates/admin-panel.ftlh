<head>
    <meta name="_csrf" content="${_csrf.token}" charset="UTF-8"> <!-- CSRF токен -->
    <title>ADMIN PANEL</title>
</head>
<script>
    function getCSRFToken() {
        const csrfTokenMeta = document.querySelector('meta[name="_csrf"]');
        return csrfTokenMeta ? csrfTokenMeta.content : '';
    }


    function deleteUser(id){
        const csrfToken = getCSRFToken();
        fetch('/admin/delete/' + id, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': csrfToken
            }
        })
        .then(response => {
            if (response.ok) {
                window.location.href = '/admin/';
            } else {
                console.log('Ошибка при удалении пользователя');
            }
        })
        .catch(error => {
            console.log('Ошибка сети:', error);
        });
    }

    function saveUser(id){
        const csrfToken = getCSRFToken();
        const rowBLock = document.querySelector('tr[id="' + id + '"]');
        const selectedRole = rowBLock.querySelector('select[name="roleList"]').value;
        requestData = {
            id: id,
            role: selectedRole
        }
        fetch('/admin/update/' + id, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-XSRF-TOKEN': csrfToken
            },
            body: JSON.stringify(requestData)
        })
        .then(response => {
            if (response.ok) {
                window.location.href = '/admin/';
            } else {
                console.log('Ошибка при удалении пользователя');
            }
        })
        .catch(error => {
            console.log('Ошибка сети:', error);
        });


        console.log(requestData);
    }

    function changeRole(id, initRole, role){
        const label = document.querySelector('label[id="' + id + '"]');
        if (initRole != role){
            label.innerText = "Не актуален";
        }
        if (initRole == role){
            label.innerText = "Aктуален";
        }
    }
</script>
<body>
    Ваш id: ${currentUser.id} - username: ${currentUser.username} - role: ${currentUser.role}<br>
    Пользователи:<br>
    <#if users?has_content>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Password</th>
                    <th>Role</th>
                    <th>Actions</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <#list users as user>
                <tr id="${user.id}">
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.originalPassword}</td>
                    <td>
                        <select name="roleList" onchange="changeRole(${user.id}, '${user.role}', this.value)">
                            <option value="${user.role}" selected>${user.role}</option>
                            <#if roles?has_content>
                                <#list roles as role>
                                    <#if role != user.role>
                                        <option value="${role}">${role}</option>
                                    </#if>
                                </#list>
                            </#if>
                        </select>
                    </td>
                    <td>
                        <input type="button" value="удалить" onclick="deleteUser(${user.id})">
                        <input type="button" value="сохранить" onclick="saveUser(${user.id})">
                    </td>
                    <td>
                        <label id="${user.id}">Актуален</label>
                    </td>
                </tr>
                </#list>
            </tbody>
        </table>
    </#if>
    Логи бота
    <div name="bot-logger" style="overflow: auto;">
        <#if botLogger?has_content>
            <#list botLogger as log>
                log <br>
            </#list>
        </#if>
    </div>
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
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
</body>
</html>
