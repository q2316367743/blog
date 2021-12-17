/**
 * 美化数据大小
 * @param {number} data 最小大小，单位：B
 * @param {number} level 转换到指定单位
 * @returns 美化后的字符串
 */
export function format(data, level) {
    if (level) {
        if (level === 1) {
            return (data / 1024 / 1024 / 1024).toFixed(2) + 'GB';
        } else if (level === 2) {
            return (data / 1024 / 1024).toFixed(2) + 'MB';
        } else if (level === 3) {
            return (data / 1024).toFixed(2) + 'KB';
        } else if (level === 4) {
            return data.toFixed(2) + 'B'
        }
    } else {
        if (data > 1024 * 1024 * 1024) {
            return (data / 1024 / 1024 / 1024).toFixed(2) + 'GB';
        } else if (data > 1024 * 1024) {
            return (data / 1024 / 1024).toFixed(2) + 'MB';
        } else if (data > 1024) {
            return (data / 1024).toFixed(2) + 'KB';
        } else {
            return data.toFixed(2) + 'B'
        }
    }
}

/**
 * 根据地址获取连接的每一部分，方式//的出现
 * @param {string} path 地址
 * @returns 链接的每一部分
 */
export function split_path(path) {
    let parts = [];
    let items = path.split('/');
    for (let item of items) {
        if (item !== '') {
            parts.push(item);
        }
    }
    return parts;
}

/**
 * 获取链接的上一级地址
 * @param {string} path 地址
 * @returns 上一级地址
 */
export function get_upper_path(path) {
    if (path == '/') {
        return '/';
    }
    let parts = split_path(path);
    if (parts.length === 1) {
        return '/';
    }
    let result = '';
    for (let i = 0; i < parts.length - 1; i++) {
        result = result + '/' + parts[i];
    }
    return result;
}

const img = ['jpg', 'png', 'gif', 'webp', 'jpeg'];
const text = ["ftl", "html", "js", "css", 'txt'];

/**
 * 根据链接获取文件类型
 * @param {string} link 链接
 * @returns 类型。1：图片；2：文本；3：文件
 */
export function get_file_type(link) {
    let items = link.split('\.');
    let len = items.length;
    let type = items[len - 1];
    if (img.indexOf(type) > -1) {
        return 1;
    }
    if (text.indexOf(type) > -1) {
        return 2;
    }
    return 3;
}