/**
 * 文章模块
 * 
 * @param {String} baseUrl 基础url
 * @param {Object} app 模块入口
 */

module.exports = function(baseUrl, app) {

    /**
     * 获取文章分类
     * 
     * {
     *      id: '',
     *      name: '',
     * }
     */
    app.get(baseUrl + '/article/category', function(req, res) {
        res.json({
            success: true,
            code: 200,
            message: 'success',
            data: {
                items: [{
                    id: '1',
                    name: '技术分享'
                }, {
                    id: '2',
                    name: '学习心得'
                }, {
                    id: '3',
                    name: 'Java'
                }, {
                    id: '4',
                    name: 'Python'
                }, ]
            }
        })
    })

    /**
     * 新增一个标签
     * 
     */
    app.post(baseUrl + '/article/tag', function(req, res) {
        res.json({
            success: true,
            code: 200,
            message: 'success',
            data: {
                item: {
                    id: (new Date()).valueOf() + '',
                    name: req.body.name
                }
            }
        })
    })

    /**
     * 新增一篇文章
     * 
     */
    app.post(baseUrl + '/article', function(req, res) {
        console.log(req.body)
        res.json({
            success: true,
            code: 200,
            message: 'success',
            data: {}
        })
    })

}