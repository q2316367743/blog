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