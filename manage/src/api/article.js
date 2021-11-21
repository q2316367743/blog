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
    }
}
