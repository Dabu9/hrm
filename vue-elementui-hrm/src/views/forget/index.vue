<template>
<div class="wrapper">
    <div style="margin: 130px auto; background-color: #fff; width:350px; height:380px;padding: 20px; border-radius:10px">
        <div style="margin: 10px 10px;text-align: center;">开始重置密码</div>
        <div style="margin: 10px 10px;text-align: center;">账户名格式：staff_xx</div>
        <el-form :rules="rules" :model="forgetInfo" ref="forgetForm">
            <el-form-item prop="code">
                <el-input size="medium" placeholder="请输入要重置的用户名" style="margin: 10px 0" v-model.trim="forgetInfo.code">
                </el-input>
            </el-form-item>
            <el-form-item prop="number">
                <div>
                    <el-input placeholder="请输入手机号" class="pnum-input" v-model.trim="forgetInfo.number">
                    </el-input>
                    <el-button :disabled="disabled" position="absolute" class="pnum-button" @click="getCode">获取验证码</el-button>
                    <b v-if="disabled">{{ countDown }}</b>
                </div>
            </el-form-item>
            <el-form-item prop="ucode">
                <el-input v-if="ifCode" size="medium" placeholder="输入验证码" style="margin: 5px 0" v-model.trim="forgetInfo.ucode">
                </el-input>
            </el-form-item>
            <div style="margin: 20px 0; text-align: center">
                <el-button type="primary" size="center" style="width:40%" @click="cancel">取 消</el-button>
                <el-button type="primary" size="center" style="width:40%" @click="submitforget">确 认</el-button>
            </div>
            <p style="margin: 10px; text-align: center;">点击确认提交管理员审核 </p>
            <p style="margin: 10px; text-align: center;">点击取消返回登陆页面 </p>
        </el-form>
    </div>
    
</div>

</template>

<script>
import { forget } from '../../api/forget'

export default{
    data() {
        return {
            forgetInfo: {},
            ifCode: false,
            countDown: 60,
            disabled: false,
            // 效验规则
            rules: {
                code: [
                { required: true, message: '请输入账户名', trigger: 'blur' },
                { min: 1, max: 10, message: '长度在3到10个字符', trigger: 'blur' }
                ],
                number: [
                { required: true, message: '请输入手机号码', trigger: 'blur' },
                { min: 11, max: 11, message: '手机号码格式有误', trigger: 'blur' }
                ],
                ucode: [
                { required: true, message: '请输入验证码', trigger: 'blur'},
                { min: 6, max:6, message: '请输入6位数验证码', trigger: 'blur'}
                ]
            }
        }
    },
    methods: {
        submitforget () {
            this.$refs.forgetForm.validate(valid => {
                if(valid){
                    forget(this.forgetInfo).then(response => {
                            console.log(response);
                            if(response.code===200){
                                this.$message.success('成功提交，等待管理员审核')
                            }
                            else if(response.code===300){
                                this.$message.error(response.message)
                            }
                            this.cancel();
                        }
                    )
                } else
                return false;
            })
        },
        cancel () {
            this.$router.push({
                path: "/login" // 点击菜单，进行跳转
            })
        },
        getCode () {
            this.ifCode = true;
            this.startCountDown();
        },
        startCountDown() {
            this.disabled = true; // 禁用按钮
            let timer = setInterval(() => {
            if (this.countDown > 0) {
                this.countDown--;
            } else {
                clearInterval(timer);
                this.disabled = false; // 启用按钮
                this.countDown = 60; // 重置倒计时时间
            }
            }, 1000);
        }

    }
}
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #FC466B, #3f5EFB);
  overflow: hidden;
}
.pnum-input {
  display: inline-block;
  width: 180px;
}
.pnum-button {
  display: inline-block;
  margin: 4px;
}
</style>
