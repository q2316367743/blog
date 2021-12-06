<nav id="navbar" class="navbar fixed-top  navbar-expand-lg navbar-dark scrolling-navbar">
    <div class="container">
        <a class="navbar-brand" href="/">
            <strong>${config.name}</strong>
        </a>
        <button id="navbar-toggler-btn" class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <div class="animated-icon"><span></span><span></span><span></span></div>
        </button>
        <!-- Collapsible content -->
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ml-auto text-center">
                <#list menus as menu>
                    <#if !menu.children?? || menu.children?size == 0 >
                        <li class="nav-item">
                            <a class="nav-link" href="${menu.data.href}"
                               target="<#if menu.data.target == 0>_self<#else>_blank</#if>">
                                <i class="${menu.data.icon}"></i>
                                ${menu.data.name}
                            </a>
                        </li>
                    <#else >
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" target="_self" href="javascript:;" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="${menu.data.icon}"></i>
                                ${menu.data.name}
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <#list menu.children as child>
                                    <a class="dropdown-item" target="<#if child.data.target == 0>_self<#else>_blank</#if>"
                                       rel="noopener external nofollow noreferrer"
                                       href="${child.data.href}">
                                        ${child.data.name}
                                    </a>
                                </#list>
                            </div>
                        </li>
                    </#if>
                </#list>
                <li class="nav-item" id="search-btn">
                    <a class="nav-link" target="_self" href="javascript:;" rel="external nofollow noreferrer"
                       data-toggle="modal" data-target="#modalSearch" aria-label="Search">
                        &nbsp;<i class="iconfont icon-search"></i>&nbsp;
                    </a>
                </li>
                <li class="nav-item" id="color-toggle-btn">
                    <a class="nav-link" target="_self" href="javascript:;" rel="external nofollow noreferrer"
                       aria-label="Color Toggle">&nbsp;<i
                                class="iconfont icon-dark" id="color-toggle-icon"></i>&nbsp;</a>
                </li>
            </ul>
        </div>
    </div>
</nav>