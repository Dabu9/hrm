import request from '../utils/request'

const url = '/forget'
// 忘记密码
export const forget = (data) => {
    return request({
        url: url,
        method: 'post',
        data
    })
}