<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Imanotes</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/main">Notes</a>
                </li>
            </ul>
            <div class="navbar-text pb-3 pe-3">${name}</div>
            <@l.logout />
        </div>
    </div>
</nav>