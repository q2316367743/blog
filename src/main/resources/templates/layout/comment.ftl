<div class="comment">
    <div class="comment-title">评论</div>
    <div class="comment-main">
        <div class="comment-base">
            <input id="nickname" type="text" class="form-control" placeholder="*昵称"
                   style="width: 32%"/>
            <div style="width: 1%"></div>
            <input id="email" type="text" class="form-control" placeholder="*电子邮箱（不会展示）"
                   style="width: 33%"/>
            <div style="width: 1%"></div>
            <input id="website" type="text" class="form-control" placeholder="博客地址（获取头像）"
                   value="https://" style="width: 33%"/>
        </div>
        <div class="comment-content">
            <textarea id="content" rows="5" class="form-control" placeholder="*评论内容" style="width: 100%"></textarea>
        </div>
        <div class="comment-option">
            <button type="button" class="btn btn-outline-danger" onclick="comment_clear()">清空</button>
            <button type="button" class="btn btn-primary" onclick="reply(${id}, 0, 0)">发表</button>
        </div>
    </div>
    <div class="comment-domain">
        <div class="comment-domain-title">
            ${comment_total} 条评论
        </div>
        <#list comments as comment>
            <div class="comment-item">
                <div class="comment-item-base">
                    <div class="comment-item-icon">
                        <img src="${comment.website}/favicon.ico">
                    </div>
                    <div class="comment-item-info">
                        <div class="comment-item-name">
                            <a href="${comment.website}" target="_blank">
                                ${comment.nickname}
                            </a>
                        </div>
                        <div class="comment-item-time">${comment.createTime?string('yyyy-MM-dd HH:mm:ss')}</div>
                    </div>
                </div>
                <div class="comment-item-content">${comment.content}</div>
                <#list comment.children as child>
                    <div class="comment-item-extra">
                        <div class="comment-item-base">
                            <div class="comment-item-icon">
                                <img src="${child.website}/favicon.ico">
                            </div>
                            <div class="comment-item-info">
                                <div class="comment-item-name">
                                    <a href="${child.website}" target="_blank">
                                        ${child.nickname}
                                    </a>
                                </div>
                                <div class="comment-item-time">${child.createTime?string('yyyy-MM-dd HH:mm:ss')}</div>
                            </div>
                        </div>
                        <div class="comment-item-content">
                            <#if child.targetNickname?length &gt; 0>
                                <a href="${child.targetWebsite}"
                                   target="_blank">@ ${child.targetNickname}</a>
                            </#if>
                            <span>${child.content}</span>
                        </div>
                    </div>
                </#list>
            </div>
        </#list>
    </div>
</div>

<script>
    /**
     * 评论
     * @param article_id 文章ID
     * @param root_id 根评论
     * @param target_id 回复
     */
    function reply(article_id, root_id, target_id) {
        let nickname = $('#nickname').val();
        if (nickname === '') {
            alert('昵称不能为空');
            return;
        }
        let email = $('#email').val();
        if (email === '') {
            alert('邮箱不能为空');
            return;
        }
        let website = $('#website').val();
        let content = $('#content').val();
        if (content === '') {
            alert('内容不能为空');
            return;
        }
        $.ajax({
            url: '${config.href}/api/comment',
            type: 'POST',
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify({
                email: email,
                website: website,
                nickname: nickname,
                article_id: article_id,
                root_id: root_id,
                target_id: target_id,
                content: content
            }),
            dataType: "json",
            success: (res) => {
                if (res.code === 200) {
                    alert('新增评论成功，待审核');
                    comment_clear();
                }
            }
        })
    }

    function comment_clear() {
        $('#nickname').val('');
        $('#email').val('');
        $('#website').val('https://');
        $('#content').val('');
    }
</script>