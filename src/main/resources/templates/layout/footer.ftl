<footer class="text-center mt-5 py-3">
    <#if config.keepOnRecord?length &gt; 0 >
        <div class="footer-content">
            <a href="http://beian.miit.gov.cn/" target="_blank" rel="nofollow noopener">
                <span>${config.keepOnRecord}</span>
            </a>
        </div>
    </#if>
    <div class="statistics">
        <!-- LeanCloud 统计PV -->
        <span id="leancloud-site-pv-container">
            <span>总访问量</span>
            <span id="leancloud-site-pv">${website.accessCount}</span>
             <span>次</span>
          </span>
        <!-- LeanCloud 统计UV -->
        <span id="leancloud-site-uv-container">
            <span>共运行</span>
            <span id="leancloud-site-uv">${website.runTime}</span>
             <span>天</span>
          </span>
    </div>
</footer>