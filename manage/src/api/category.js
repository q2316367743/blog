import request from '@/utils/request'

export default {
    list(success, error) {
        request({
            url: 'category',
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
            url: 'category',
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
            url: `category/${id}`,
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