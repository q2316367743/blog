import axios from '@/utils/axios'

function visit(success) {
    axios({
        url: '/home/visit',
        method: 'GET',
    }).then(res => {
        success(res.data)
    })
}

function base(success) {
    axios({
        url: '/home/base',
        method: 'GET',
    }).then(res => {
        success(res.data)
    })
}

function comment(success) {
    axios({
        url: '/home/comment',
        method: 'GET',
    }).then(res => {
        success(res.data)
    })
}

function user(success) {
    axios({
        url: '/home/user',
        method: 'GET',
    }).then(res => {
        success(res.data)
    })
}

export default {
    visit,
    base,
    comment,
    user
}