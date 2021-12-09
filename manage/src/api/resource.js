import request from '@/utils/request'

export default {
    ls(path, success, error) {
        request({
            url: 'resource/ls',
            method: 'GET',
            params: {
                path: path
            }
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
    link(path, success, error) {
        request({
            url: 'resource/link',
            method: 'GET',
            params: {
                path: path
            }
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
    mkdir(record, success, error) {
        request({
            url: 'resource/mkdir',
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
    rm(path, success, error) {
        request({
            url: 'resource/rm',
            method: 'DELETE',
            params: {
                path: path
            }
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
    upload(form, success, error) {
        request({
            url: 'resource/upload',
            method: 'POST',
            headers: {
                'Content-Type': 'multipart/form-data',
            },
            data: form,
            timeout: 30 * 1000
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
    exec_command(path, command, success, error) {
        request({
            url: 'resource/command',
            method: 'GET',
            params: {
                path: path,
                command: command
            }
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