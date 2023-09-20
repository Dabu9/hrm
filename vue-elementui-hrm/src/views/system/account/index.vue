<template>
    <div class="common-table">
      <el-table
        :data="table.tableData"
        height="85%"
        stripe
        row-key="id"
        fit
        :header-cell-style="{background: '#eef1f6',color: '#606266',
        textAlign:'center',fontWeight:'bold',borderWidth:'3px'}"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" width="50" textAlign="center"/>
        <el-table-column prop="id" label="用户id" min-width="180" align="center"/>
        <el-table-column prop="code" label="账户名" min-width="180" align="center"/>
        <el-table-column prop="name" label="用户名" min-width="180" align="center"/>
        <el-table-column prop="isForget" label="是否申请重置密码" min-width="180" align="center"/>
        <el-table-column label="操作" width="280" fixed="right" textAlign="center">
          <template slot-scope="scope">
            <el-button size="mini" type="primary" @click="returnApp(scope.row.id)"
            >退回申请 <i class="el-icon-edit"></i
            ></el-button>
            <el-popconfirm
              style="margin-left:10px;margin-right:10px"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="确定重置吗"
              @confirm="resetPWD(scope.row.id)"
            >
              <el-button size="mini" type="danger" slot="reference"
              >重置密码 <i class="el-icon-remove-outline"></i
              ></el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        class="pager"
        layout="total,sizes,prev,pager,next,jumper"
        :page-size="table.pageConfig.size ? table.pageConfig.size : 10"
        :page-sizes="[5, 10, 15, 20]"
        :total="table.pageConfig.total"
        :current-page.sync="table.pageConfig.current"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      ></el-pagination>
    </div>
</template>

<script>
import { getList,resetAsk,returnAsk} from '../../../api/account'
export default {
  name: 'Accounts',
  data () {
    return {
      editForm: {
        isShow: false,
        formData: {}
      },
      searchForm: {
        formData: {}
      },
      table: {
        tableData: [],
        pageConfig: {
          total: 10, // 记录总数
          current: 1, // 起始页
          size: 10 // 每页展示的记录数
        }
      },
      ids: []
    }
  },
  methods: {
    resetPWD (data) {
        console.log(data);
        resetAsk(data).then(response=>{
            if(response.code===200){
                this.$message.success(response.message);
            } else{
                this.$message.error(response.message);
            }
        })
    },
    returnApp (data) {
        returnAsk(data).then(response=>{
            if(response.code===200){
                this.$message.success(response.message);
            } else{
                this.$message.error(response.message);
            }
        })    
    },
    handleSi4eChange (size) {
      this.table.pageConfig.size = size
      this.loading()
    },
    handleCurrentChange (current) {
      this.table.pageConfig.current = current
      this.loading()
    },
    handleSelectionChange (list) {
      this.ids = list.map(item => item.id)
    },
    handleStatusChange (row) {
      edit(row)
    },
    // 将数据渲染到模板
    loading () {
      getList({
        current: this.table.pageConfig.current,
        size: this.table.pageConfig.size,
        name: this.searchForm.formData.name
      }).then(response => {
        if (response.code === 200) {
          this.table.tableData = response.data.list
          this.table.pageConfig.total = response.data.total
        } else {
          this.$message.error(response.message)
        }
      })
    }
  },
  created () {
    this.loading()
  }
}

</script>
