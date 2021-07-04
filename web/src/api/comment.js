import axios from '@/utils/axios'

/**
 * 根据文章ID获取评论
 * 
 * @param {String} id 文章ID
 * @param {Function} success 成功回调
 */
export function getComment(id, success) {
    success({
        success: true,
        code: 200,
        message: 'success',
        data: {
            items: [{
                id: '1',
                email: '1583720084@qq.com',
                nickname: '浅梦凉城',
                website: '',
                type: 1,
                browser: 'Edge 86.0.622.51',
                system_version: 'Windows 10.0',
                create_time: '2021-02-03',
                content: '真强',
                reply: [
                    {
                        id: '3',
                        email: '2316367743@qq.com',
                        nickname: '落雨不悔',
                        website: 'javascript:;',
                        type: 3,
                        target_nickname: '落雨不悔',
                        target_website: '',
                        browser: 'Edge 86.0.622.51',
                        system_version: 'Windows 10.0',
                        create_time: '2021-02-03',
                        content: '真强',
                    }, {
                        id: '3',
                        email: '2316367743@qq.com',
                        nickname: '落雨不悔',
                        website: 'javascript:;',
                        type: 3,
                        target_nickname: '落雨不悔',
                        target_website: '',
                        browser: 'Edge 86.0.622.51',
                        system_version: 'Windows 10.0',
                        create_time: '2021-02-03',
                        content: '真强',
                    }
                ]
            }, {
                id: '1',
                email: '1583720084@qq.com',
                nickname: '剑锋寒',
                website: 'javascript:;',
                type: 2,
                browser: 'Edge 86.0.622.51',
                system: 'Windows 10.0',
                createTime: '2021-02-03',
                content: '真强'
    
            }],
            total: 2,
        }
    })
}

/**
 * 新增评论
 * 
 * @param {Object} data
 * 
 * @param {String} brower 浏览器版本
 * @param {String} content 评论内容
 * @param {String} email 邮箱
 * @param {String} nickname 昵称
 * @param {String} system 系统版本
 * @param {String} website 个人网站
 * 
 * @param {Function} success 成功回调
 */
export function addComment(data, success) {
    axios({
        url: '/blog/comment',
        method: 'POST',
        data: data
    }).then(res => {
        success(res.data)
    })
}

/**
 * 回复评论
 * 
 * @param {Object} data 参数
 * 
 * @param {String} brower 浏览器版本
 * @param {String} content 评论内容
 * @param {String} email 邮箱
 * @param {String} nickname 昵称
 * @param {String} system 系统版本
 * @param {String} website 个人网站
 * @param {String} commentId 评论ID
 */
export function addReply(data, success) {
    axios({
        url: '/blog/comment/reply',
        method: 'POST',
        data: data
    }).then(res => {
        success(res.data)
    })
}