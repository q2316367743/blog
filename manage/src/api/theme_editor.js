import request from '@/utils/request'

export default {
    ls(success, error) {
        request({
            url: 'theme/editor/ls',
            method: 'GET'
        }).then((res) => {
            success(res);
        }).catch((e) => {
            if (error) {
                error(e);
            } else {
                console.error(e);
            }
        })
    },
    cat(path, charset, success, error) {
        request({
            url: 'theme/editor/cat',
            method: 'GET',
            params: {
                path,
                charset
            }
        }).then((res) => {
            success(res);
        }).catch((e) => {
            if (error) {
                error(e);
            } else {
                console.error(e);
            }
        })
    },
    write(path, content, success, error) {
        request({
            url: 'theme/editor/write',
            method: 'POST',
            data: {
                path,
                content
            }
        }).then((res) => {
            success(res);
        }).catch((e) => {
            if (error) {
                error(e);
            } else {
                console.error(e);
            }
        })
    }
}