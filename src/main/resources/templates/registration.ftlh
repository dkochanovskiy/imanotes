<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <h2 class="mb-5">Add new user</h2>
    ${note!}
    <@l.login "/registration" true />
</@c.page>