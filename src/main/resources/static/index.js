layui.use('table', function () {
    var table = layui.table;

    table.render({
        elem: '#db_tables'
        , url: '/list'
        , cols: [[
            {type: 'checkbox'}
            , {field: 'tableName', title: '表名称', style: 'background-color: #eee;'}
            , {field: 'engine', title: 'engine'}
            , {field: 'tableComment', title: '表描述'}
            , {field: 'createTime', title: '创建时间'}
        ]], page: true
        , id: 'Gen_table'
    });

    var $ = layui.$, active = {
        reload: function () {
            var demoReload = $('#demoReload');
            //执行重载
            table.reload('Gen_table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    tbName: demoReload.val()
                }
            });
        },
        getCheckData: function () { //获取选中数据
            var checkStatus = table.checkStatus('Gen_table')
                , data = checkStatus.data, tablesName = new Array();
            for (var p in data) {
                tablesName[p] = data[p].tableName;
            }
            if (data.length > 0) {
                location.href = "/code?tables=" + tablesName.toString();
            } else {
                layer.msg("请选择表格！！！")
            }
        }
    };

    $('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});


function openEditWindow() {
    $('#modal_form').modal('show');
}

function submit_Modify() {
    var author = $("#author_name").val();
    var package = $("#package_name").val();
    var autoRemovePre = $("[name=autoRemovePre]:checked").val();
    var table_pre = $("#table_pre").val();
    $.ajax({
        type:'post',
        url:'/modify/strategy',
        cache: false,
        data: {"author":author,"packageName":package,"autoRemovePre":autoRemovePre,"tablePre":table_pre},
        success:function (resp) {

        }
    });
}