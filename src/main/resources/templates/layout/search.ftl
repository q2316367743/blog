<!-- 搜索菜单 -->
<div class="modal fade" id="modalSearch" tabindex="-1" role="dialog" aria-labelledby="ModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                <h4 class="modal-title w-100 font-weight-bold">搜索</h4>
                <button type="button" id="local-search-close" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body mx-3">
                <div class="md-form mb-5">
                    <input type="text" id="local-search-input" class="form-control validate">
                    <label data-error="x" data-success="v"
                           for="local-search-input">关键词</label>
                </div>
                <div class="list-group" id="local-search-result">
                </div>
            </div>
        </div>
    </div>
    <script type="application/javascript">
        $('#local-search-input').on('keyup', function (e) {
            search(e.target.value)
        })

        function search(keyword) {
            // 1. 搜索
            $.getJSON('${config.href}/api/article', {
                title: keyword
            }, function (res) {
                let content = $('#local-search-result');
                // 2. 删除之前的
                content.html('');
                // 3. 遍历渲染
                let views = [];
                for (let article of res.data.records) {
                    views.push('<a href="${config.href}/article/' + article.id + '.html" ' +
                        'class="list-group-item list-group-item-action font-weight-bolder search-list-title">' + article.title + '</a>' +
                        '<p class="search-list-content" id="search-list-content">' + article.description + '</p>')
                }
                content.html(views.join('\n'));
            })
        }
    </script>
</div>