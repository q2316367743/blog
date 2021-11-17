<!-- SCRIPTS -->
<script src="https://cdn.jsdelivr.net/npm/nprogress@0/nprogress.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/nprogress@0/nprogress.min.css"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4/dist/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcdn.net/ajax/libs/typeit/7.0.4/typeit.min.js"></script>
<script  src="https://cdn.jsdelivr.net/npm/@fancyapps/fancybox@3/dist/jquery.fancybox.min.js" ></script>
<script>
    NProgress.configure({"showSpinner": false, "trickleSpeed": 100})
    NProgress.start()
    window.addEventListener('load', function () {
        NProgress.done();
    });
</script>
<script src="/js/events.js" ></script>
<script src="/js/plugins.js" ></script>
<script src="/js/boot.js"></script>
<script>
    function search_tag(tag) {
        $('#search-btn > a').click();
        $('#local-search-input').val(tag)
    }
</script>