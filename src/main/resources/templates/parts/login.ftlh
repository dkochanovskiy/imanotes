<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="mb-3">
            <label for="username" class="form-label">User Name :</label>
            <input type="text" class="form-control" name="username" id="username">
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Password :</label>
            <input type="text" class="form-control" name="password" id="password">
        </div>
        <div><input type="hidden" name="_csrf" value="${_csrf.token}"></div>
        <div>
            <button type="submit" class="btn btn-warning mb-3">
                <#if isRegisterForm>
                    Create
                <#else>
                    Sign In
                </#if>
            </button>
        </div>
        <#if !isRegisterForm>
            <a href="/registration">Add new user</a>
        </#if>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <div><input type="hidden" name="_csrf" value="${_csrf.token}"></div>
        <button type="submit" class="btn btn-warning mb-3">
            Sign out
        </button>
    </form>
</#macro>