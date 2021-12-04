import request from '@/utils/request'

export default {
    list(type, success, error) {
        request({
            url: `dict/${type}/list`,
            method: 'GET',
        }).then((res) => {
            success(res);
        }).catch((e) => {
            console.error(e);
            if (error) {
                error(e);
            }
        })
    },
    map(type, success, error) {
        request({
            url: `dict/${type}/map`,
            method: 'GET',
        }).then((res) => {
            success(res);
        }).catch((e) => {
            console.error(e);
            if (error) {
                error(e);
            }
        })
    },
    save(type, data, success, error) {
        request({
            url: `dict/${type}`,
            method: 'POST',
            data: data
        }).then((res) => {
            success(res);
        }).catch((e) => {
            console.error(e);
            if (error) {
                error(e);
            }
        })
    },
    update(id, data, success, error) {
        request({
            url: `dict/${id}`,
            method: 'PUT',
            data: data
        }).then((res) => {
            success(res);
        }).catch((e) => {
            console.error(e);
            if (error) {
                error(e);
            }
        })
    },
    remove(id, success, error) {
        request({
            url: `dict/${id}`,
            method: 'DELETE',
        }).then((res) => {
            success(res);
        }).catch((e) => {
            console.error(e);
            if (error) {
                error(e);
            }
        })
    },
}