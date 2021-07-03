// 基础服务，获取一些网站基本信息

import axios from '@/utils/axios'

/**
 * 获取作者信息
 * 
 */
export function getAuthor(success) {
    axios({
        url: '/global/author',
        method: 'GET'
    }).then(res => {
        success(res)
    }).catch(function() {
        success({
            success: true,
            code: 200,
            message: "成功",
            data: {
                item: {
                    other: "[{\"name\": \"CSDN\",\"icon\": \"https://g.csdnimg.cn/static/logo/favicon32.ico\",\"href\": \"https://blog.csdn.net/q2316367743\"}]",
                    name: "云落天都",
                    description: "主要是Java技术栈，略带部分源码，随笔",
                    avatar: "https://portrait.gitee.com/uploads/avatars/user/1786/5358547_qiaoshengda_1578985319.png!avatar200",
                    gitee: "https://gitee.com/qiaoshengda"
                }
            }
        })
    });
}

/**
 * 获取网站数据
 * 
 */
export function getWebInfo(success) {
    axios({
        url: '/global/web_info',
        method: 'GET'
    }).then(res => {
        success(res)
    })
}

export function getAbout(success) {
    axios({
        url: '/global/about',
        method: 'GET'
    }).then(res => {
        success(res)
    })
}

export function getConfig(success) {
    axios({
        url: '/global/config',
        method: 'GET'
    }).then(res => {
        success(res)
    }).catch(function() {
        success({
            success: true,
            code: 200,
            message: "成功",
            data: {
                item: {
                    "id": "1",
                    "createTime": "2020-02-23T20:41:00",
                    "background": "http://esion.xyz/assets/image/background.jpg",
                    "music": [{
                        "url": "http://esion.xyz/music/刘瑞琦 - 房间 (新版).flac",
                        "cover": "http://esion.xyz/image/default.jpg",
                        "name": "房间",
                        "artist": "刘瑞琪"
                    }, {
                        "url": "http://esion.xyz/music/陈粒 - 走马.flac",
                        "cover": "http://esion.xyz/image/default.jpg",
                        "name": "走马",
                        "artist": "陈粒"
                    }, {
                        "url": "http://esion.xyz/music/水木年华 - 一生有你.flac",
                        "cover": "http://esion.xyz/image/default.jpg",
                        "name": "一生有你",
                        "artist": "水木年华"
                    }, {
                        "url": "http://esion.xyz/music/程jiajia - 山楂树の恋.flac",
                        "cover": "http://esion.xyz/image/default.jpg",
                        "name": "山楂树之恋",
                        "artist": "程佳佳"
                    }, {
                        "url": "http://esion.xyz/music/蒋雪儿 - 燕无歇.flac",
                        "cover": "http://esion.xyz/image/default.jpg",
                        "name": "燕无歌",
                        "artist": "蒋雪儿"
                    }, {
                        "url": "http://esion.xyz/music/火箭少女101段奥娟 - 陪我长大.flac",
                        "cover": "http://esion.xyz/image/default.jpg",
                        "name": "陪我长大",
                        "artist": "段奥娟"
                    }, {
                        "url": "http://esion.xyz/music/陈一发儿 - 童话镇.flac",
                        "cover": "http://esion.xyz/image/default.jpg",
                        "name": "童话镇",
                        "artist": "陈一发"
                    }, {
                        "url": "http://esion.xyz/music/汪苏泷、By2 - 有点甜.flac",
                        "cover": "http://esion.xyz/image/default.jpg",
                        "name": "有点甜",
                        "artist": "汪苏泷、By2"
                    }, {
                        "url": "http://esion.xyz/music/王力宏 - 需要人陪.flac",
                        "cover": "http://esion.xyz/image/default.jpg",
                        "name": "需要人陪",
                        "artist": "王力宏"
                    }]
                }
            }
        })
    });
}

/**
 * 获取前5分类
 *
 * @param {Function} success 成功回调
 */
export function get_category_top(success) {
    axios({
        url: '/global/category/top',
        method: 'GET'
    }).then(res => {
        success(res)
    })
}

/**
 * 获取全部分类
 *
 * @param {Function} success 成功回调
 */
export function get_category_list(success) {
    axios({
        url: '/global/category/list',
        method: 'GET'
    }).then(res => {
        success(res)
    })
}