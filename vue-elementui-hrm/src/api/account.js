import request from '../utils/request'
const url = '/account'

export const getList = (params) => {
    return request({
      url: url,
      method: 'get',
      params
    })
}

export const resetAsk = (data) => {
return request({
    url: url + '/' + data,
    method: 'get'
})
}

export const returnAsk = (data) => {
return request({
    url: url + '/' + 'cancel'+ '/' + data,
    method: 'get'
})
}