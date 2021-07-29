<#import "parts/common.ftlh" as c>

<@c.page>
    <form method="get" action="/main" class="row row-cols-lg-auto g-3 align-items-center">
        <div class="col-12">
            <input type="text" name="filter" class="form-control" value="${filter!}" placeholder="Search by tag">
        </div>
        <div class="col-12">
            <button type="submit" class="btn btn-warning">Search</button>
        </div>
    </form>
    <hr>
    <form class="mt-5" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <input type="text" name="text" class="form-control" placeholder="Type your note">
        </div>
        <div class="mb-3">
            <input type="text" name="tag" class="form-control" placeholder="Tag">
        </div>
        <div class="mb-3">
            <input class="form-control" name="file" type="file">
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <div class="mb-5"></div>
        <button type="submit" class="btn btn-warning">Add new note</button>
    </form>
    <hr>
    <div class="mt-5">
        <#list notes as note>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Text</th>
                    <th scope="col">Tag</th>
                    <th scope="col">Author</th>
                    <th scope="col">Image</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${note.text}</td>
                    <td>${note.tag}</td>
                    <td>${note.authorName}</td>
                    <#if note.filename??>
                        <td><img src="/img/${note.filename}"></td>
                    </#if>
                </tr>
                </tbody>
            </table>
        <#else>
            No note
        </#list>
    </div>
</@c.page>