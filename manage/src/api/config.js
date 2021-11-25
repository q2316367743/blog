import request from '@/utils/request'

export default {
    info(success, error) {
        request({
            url: 'config',
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
    update(data, success, error){
        request({
            url: 'config',
            method: 'PUT',
            data
        }).then((res) => {
            success(res);
        }).catch((e) => {
            console.error(e);
            if (error) {
                error(e);
            }
        })
    }
}
