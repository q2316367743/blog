/**
 * 主页数据模块
 * 
 * @param {String} baseUrl 基础url
 * @param {Object} app 模块入口
 */

module.exports = function(baseUrl, app) {

    app.get(baseUrl + '/home/base', function(req, res) {
        res.json({
            success: true,
            code: 200,
            message: 'success',
            data: {
                item: {
                    visit: {
                        count: 9,
                        sum: 22
                    },
                    user: {
                        count: 99,
                        sum: 188
                    },
                    project: {
                        count: 1,
                        sum: 2
                    },
                    service: {
                        count: 4,
                        sum: 12
                    }
                }
            }
        });
    });

    app.get(baseUrl + '/home/visit', function(req, res) {
        res.json({
            success: true,
            code: 200,
            message: 'success',
            data: {
                item: {
                    visit: [
                        120,
                        132,
                        101,
                        134,
                        90,
                        230,
                        210,
                        101,
                        134,
                        90,
                        230,
                        210,
                    ],
                    comment: [
                        220,
                        182,
                        191,
                        234,
                        290,
                        330,
                        310,
                        191,
                        234,
                        290,
                        330,
                        310,
                    ],
                }
            }
        });
    });

    app.get(baseUrl + '/home/comment', function(req, res) {
        res.json({
            success: true,
            code: 200,
            message: 'success',
            data: {
                items: [{
                    id: '1',
                    name: '诸葛亮',
                    content: '皓首匹夫！苍髯老贼！你枉活九十有六，一生未立寸功，只会摇唇鼓舌！助曹为虐！一条断脊之犬，还敢在我军阵前狺狺狂吠，我从未见过有如此厚颜无耻之人！',
                    createTime: '5月30日 00:00'
                }, {
                    id: '2',
                    name: '胡歌',
                    content: '你以为只要长得漂亮就有男生喜欢？你以为只要有了钱漂亮妹子就自己贴上来了？你以为学霸就能找到好工作？我告诉你吧，这些都是真的！',
                    createTime: '5月30日 00:00'
                }, {
                    id: '3',
                    name: '杜甫',
                    content: '人才虽高，不务学问，不能致圣。刘向十日画一水，五日画一石。',
                    createTime: '5月30日 00:00'
                }],
                total: 3
            }
        });
    });

    app.get(baseUrl + '/home/user', function(req, res) {
        res.json({
            success: true,
            code: 200,
            message: 'success',
            data: {
                items: [{
                    name: '北京市',
                    value: 12
                }, {
                    name: '黑龙江省',
                    value: 22
                }, {
                    name: '吉林省',
                    value: 32
                }, {
                    name: '辽宁省',
                    value: 42
                }, {
                    name: '湖北省',
                    value: 52
                }, {
                    name: '四川省',
                    value: 62
                }],
            }
        });
    });

}