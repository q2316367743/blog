import request from '@/utils/request'

export default {
    base(success, error) {
        request({
            url: 'statistics/base',
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
    dynamic(success, error) {
        request({
            url: 'statistics/dynamic',
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
}