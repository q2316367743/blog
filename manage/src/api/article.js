import axios from '@/utils/axios'

function category(success) {
    axios({
        url: '/article/category',
        method: 'GET',
    }).then(res => {
        success(res.data)
    })
}

function tag(name, success) {
    axios({
        url: '/article/tag',
        method: 'POST',
        data: {
            name: name
        }
    }).then(res => {
        success(res.data)
    })
}

function article(data, success) {
    axios({
        url: '/article',
        method: 'POST',
        data: data
    }).then(res => {
        success(res.data)
    })
}

function list(data, success) {
    axios({
        url: '/article',
        method: 'GET',
        params: data
    }).then(res => {
        success(res.data)
    })
}

function info(id, success) {
    axios({
        url: `/article/${id}`,
        method: 'GET',
    }).then(res => {
        success(res.data)
    })
}

export default {
    category,
    tag,
    article,
    list,
    info
}