<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Code-Generator</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>
<body style="background:darksalmon">

<div style="width:1000px;height:auto;margin:auto;padding-top:10%;">
<div class="layui-inline">
    <div class="layui-inline" align="right">
        搜索表名：
        <div class="layui-inline">
            <input class="layui-input" name="id" id="demoReload" autocomplete="off">
        </div>
        <button class="layui-btn" data-type="reload" id="seach_button">搜索</button>
    </div>

    <button class="layui-btn" data-type="getCheckData">生成代码</button>
    <button class="layui-btn" data-type="setGenType" onclick="openEditWindow();">设置策略</button>
</div>
    <table id="db_tables"></table>
</div>

<div class="modal fade"  tabindex="-1" id="modal_form">
    <div class="modal-dialog">
        <form class="form-horizontal">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">设置生成策略</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label for="author_name" class="col-sm-3 control-label">作者</label>
                        <div class="col-sm-6 input-group" style="padding-left: 0;">
                            <label>
                                <input type="text" class="form-control" id="author_name" name="author_name"
                                       style="width: 350px;" value="${property.author}"/>
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="package_name" class="col-sm-3 control-label">包名</label>
                        <div class="col-sm-6 input-group" style="padding-left: 0;">
                            <label>
                                <input type="text" class="form-control" id="package_name" name="package_name"
                                       style="width: 350px;" value="${property.package}"/>
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label">自动去掉表前缀：</label>
                        <div class="col-sm-2  i-checks icheckbox_square-green checked">
                            <input id="autoRemovePre" name="autoRemovePre" class="form-control" type="checkbox" <#if property.autoRemovePre=='true' >checked</#if>>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="table_pre" class="col-sm-3 control-label">去掉指定表前缀</label>
                        <div class="col-sm-6 input-group" style="padding-left: 0;">
                            <label>
                                <input type="text" class="form-control" id="table_pre" name="table_pre"
                                       style="width: 350px;" value="${property.tablePrefix}"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="buuton" class="btn btn-primary" onclick="submit_Modify();" data-loading-text="Saving...">保存
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="/layui/layui.js"></script>
<script src="/index.js"></script>
</body>
</html>