import request from '@/utils/request'

export default {
    list(condition, success, error) {
        request({
            url: 'page',
            method: 'GET',
            params: condition
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
    info(id, success, error) {
        request({
            url: `page/${id}`,
            method: 'GET',
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
    save(data, success, error) {
        request({
            url: 'page',
            method: 'POST',
            data
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
    update(id, data, success, error) {
        request({
            url: `page/${id}`,
            method: 'PUT',
            data
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