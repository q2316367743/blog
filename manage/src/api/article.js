import request from '@/utils/request'

export default {
    list(condition, success, error) {
        request({
            url: 'article',
            method: 'GET',
            params: condition
        }).then((res) => {
            success(res);
        }).catch((e) => {
            if (error) {
                error(e);
            }else {
                console.error(e);
            }
        })
    },
    info(id, success, error) {
        request({
            url: `article/${id}`,
            method: 'GET',
        }).then((res) => {
            success(res);
        }).catch((e) => {
            if (error) {
                error(e);
            }else {
                console.error(e);
            }
        })
    },
    save(data, success, error) {
        request({
            url: 'article',
            method: 'POST',
            data
        }).then((res) => {
            success(res);
        }).catch((e) => {
            if (error) {
                error(e);
            }else {
                console.error(e);
            }
        })
    },
    update(id, data, success, error) {
        request({
            url: `article/${id}`,
            method: 'PUT',
            data
        }).then((res) => {
            success(res);
        }).catch((e) => {
            if (error) {
                error(e);
            }else {
                console.error(e);
            }
        })
    }
}
