<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div>
        <@l.logout />
    </div>
    <div>
        <form method="post" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Введите сообщение" />
            <input type="text" name="tag" placeholder="Тэг">
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список сообщений</div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter!}">
        <button type="submit">Найти</button>
    </form>
    <#list notes as note>
        <div>
            <b>${note.id}</b>
            <span>${note.text}</span>
            <i>${note.tag}</i>
            <strong>${note.authorName}</strong>
            <div>
                <#if note.filename??>
                    <img src="/img/${note.filename}">
                </#if>
            </div>
        </div>
    <#else>
        No notes
    </#list>
</@c.page>