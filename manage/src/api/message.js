import request from '@/utils/request'

export default {
    list(condition, success, error) {
        request({
            url: 'message',
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
    read(id, is_read, success, error) {
        request({
            url: `message/${id}`,
            method: 'PUT',
            data: {
                is_read: is_read
            }
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
