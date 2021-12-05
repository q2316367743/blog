import request from '@/utils/request'

export default {
    list(success, error) {
        request({
            url: 'menu',
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
    save(record, success, error) {
        request({
            url: 'menu',
            method: 'POST',
            data: record
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
    update(id, record, success, error) {
        request({
            url: `menu/${id}`,
            method: 'PUT',
            data: record
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
    remove(id, success, error) {
        request({
            url: `menu/${id}`,
            method: 'DELETE',
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
}