let express = require('express'); //引入express模块
let Mock = require('mockjs'); //引入mock模块
let bodyParser = require('body-parser');

let app = express(); //实例化express

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

const baseUrl = '/api/manage'

/**
 * 配置跨域
 * @param  {[type]} req  [客户端发过来的请求所带数据]
 * @param  {[type]} res  [服务端的相应对象，可使用res.send返回数据，res.json返回json数据，res.down返回下载文件]
 */
//设置跨域访问
app.all('*', (req, res, next) => {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "*");
    res.header("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
    next();
});

// 加载各个模块
let user = require('./user');
user(baseUrl, app);
let home = require('./home');
home(baseUrl, app);
let article = require('./article');
article(baseUrl, app);

/**
 * 监听8090端口
 */
app.listen('9000');