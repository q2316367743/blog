import request from '@/utils/request'

export default {
    list(condition, success, error) {
        request({
            url: 'link',
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
    save(record, success, error) {
        request({
            url: 'link',
            method: 'POST',
            data: record
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
    update(id, record, success, error) {
        request({
            url: `link/${id}`,
            method: 'PUT',
            data: record
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
}
