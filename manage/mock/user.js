/**
 * 用户模块，用于登录，获取个人信息，登出服务
 * 
 * @param {String} baseUrl 基础url
 * @param {Object} app 模块入口
 */

module.exports = function(baseUrl, app) {

    app.get(baseUrl + '/user/login', function(req, res) {
        if (!req.query) {
            res.json({
                success: false,
                code: 400,
                message: '用户名或密码错误',
                data: {}
            });
            return;
        }
        if (req.query.username == 'esion' && req.query.password == '123456') {
            res.json({
                success: true,
                code: 200,
                message: 'success',
                data: {
                    item: '1234-5678-90'
                }
            });
        } else {
            res.json({
                success: false,
                code: 400,
                message: '用户名或密码错误',
                data: {}
            });
        }

    });

    app.get(baseUrl + '/user/info', function(req, res) {
        let token = '1234-5678-90' //req.get('token');
        if (token && token == '1234-5678-90') {
            res.json({
                success: true,
                code: 200,
                message: 'success',
                data: {
                    item: {
                        username: '2316367743',
                        nickname: '落雨不悔',
                    }
                }
            });
        } else {
            res.json({
                success: false,
                code: 401,
                message: '账户未登录，请重新登录',
                data: {}
            });
        }
    })

    app.get(baseUrl + '/user/logout', function(req, res) {
        res.json({
            success: true,
            code: 200,
            message: 'success',
            data: {}
        });
    })

}