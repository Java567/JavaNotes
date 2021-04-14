var App=function () {

    //iCheck
    var _masterChecked;
    var _checkbox;
    var _idArray;

    //用于存放ID的数组


    /**
     *私有方法，初始化 ICheck
     */
    var handleInitChecked=function () {
        //激活
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });
        //获取控制端的 IChecked
        _masterChecked=$('input[type="checkbox"].minimal.icheck_master');

        //获取全部Checkbox集合
        _checkbox=$('input[type="checkbox"].minimal');
    };

    var handelCheckBoxAll=function(){
        _masterChecked.on("ifClicked",function (e) {
            //返回 true 表示未选中
            if(e.target.checked){
                _checkbox.iCheck("uncheck");
            }
            //选中状态
            else {
                _checkbox.iCheck("check");
            }
        });
    };

    /**
     *   批量删除
     */
    var handelDeleteMulti=function (url) {
        _idArray=new Array();

        _checkbox.each(function(){
            var _id=$(this).attr("id")
            if(_id!=null && _id!=="undefine" &&$(this).is(":checked")){
                _idArray.push(_id);
            }
        });

        if(_idArray.length===0){
            $("#modal-message").html("您还没有选择任何数据项，请至少选择一项");
        }

        else {
            $("#modal-message").html("您确定删除数据项吗?");
        }
        $("#modal-default").modal("show");

        $("#btnModalOk").bind("click",function () {
            del();
        });

        /**
         * 当前私有函数的函数 删除数据
         */
        function del() {
            $("#modal-default").modal("hide");
            //如果没有选择数据项的处理
            if (_idArray.length===0){

            }

            //删除操作
            else {
                $.ajax({
                    "url":url,
                    "type":"POST",
                    "data":{"ids":_idArray.toString()},
                    "dataType":"JSON",
                    "success":function (data) {
                        //删除成功
                        if(data.status===200){
                            window.location.reload();
                        }

                        //删除失败
                        else {
                            $("#modal-message").html(data.message);
                            $("#modal-default").modal("show");
                        }
                    }
                })
            }
        }
    };

    return{
        init: function () {
            handleInitChecked();
            handelCheckBoxAll();
        },

        getCheckbox: function () {
            return _checkbox;
        },

        deleteMulti: function (url) {
            handelDeleteMulti(url);
        }
    }
}();

$(document).ready(function () {
    App.init();
})