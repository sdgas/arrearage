<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/page/share/taglib.jsp" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path;
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <title>综合查询</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="content－Type" content="text/html;charset=UTF-8">
    <meta http-equiv="window-target" content="_top">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>

    <link rel="stylesheet" href="<%=basePath%>/css/base2.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="<%=basePath%>/js/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="<%=basePath%>/js/bootstrap.min.js"></script>
    <script language="javascript" type="text/javascript"
            src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap.min.css">
    <script type="text/javascript">

        $(document).ready(function () {

            departmentService.findAllDepartment(getResult);
            contractNameService.findAll(getContractName);

            //placeholder功能的实现
            var doc = document,
                    inputs = doc.getElementsByTagName('input'),
                    supportPlaceholder = 'placeholder'in doc.createElement('input'),

                    placeholder = function (input) {
                        var text = input.getAttribute('placeholder'),
                                defaultValue = input.defaultValue;
                        if (defaultValue == '') {
                            input.value = text
                        }
                        input.onfocus = function () {
                            if (input.value === text) {
                                this.value = ''
                            }
                        };
                        input.onblur = function () {
                            if (input.value === '') {
                                this.value = text
                            }
                        }
                    };

            if (!supportPlaceholder) {
                for (var i = 0, len = inputs.length; i < len; i++) {
                    var input = inputs[i],
                            text = input.getAttribute('placeholder');
                    if (input.type === 'text' && text) {
                        placeholder(input)
                    }
                }
            }
        });

        function topage(page) {
            var form = document.getElementById("myform");
            document.getElementById("page").setAttribute("value", page);
            form.submit();
        }

        function changebg(obj, cl) {
            var bgcolor;
            if (cl == "0") {
                bgcolor = "#E2EDFC";
            } else {
                bgcolor = "#F8FBFE";
            }
            obj.style.backgroundColor = bgcolor;
        }

        /*
         *全选
         */
        function allselect(allobj, items) {
            var state = allobj.checked;
            if (items.length > 0) {
                for (var i = 0; i < items.length; i++) {
                    if (!items[i].disabled) {
                        items[i].checked = state;
                    }
                }
            } else {
                if (!items.disabled)items.checked = state;
            }
        }

        /*
         *判断是否选择了记录
         */
        function ValidateIsSelect(allobj, items) {
            if (items.length > 0) {
                for (var i = 0; i < items.length; i++) {
                    if (items[i].checked) {
                        return true;
                    }
                }
            } else {
                if (items.checked)return true;
            }
            return false;
        }

        function actionEvent() {
            var form = document.getElementById("myform");
            if (ValidateIsSelect(form.all, form.userIds)) {
                form.action = "OwingMoney!due.action";
                form.submit();
            } else {
                alert("请选择要操作的记录");
            }

        }
    </script>
</head>
<body>
<%@ include file="/page/share/menu.jsp" %>
<div id="content">
    <form action="OwingMoney!findByUserId.action" method="post">
        <div class="form-group" align="center">
            <table>
                <tr align="center">
                    <td>用户号</td>
                    <td>
                        <input type="text" name="msg">
                    </td>
                    <td>
                        <button type="submit" class="btn btn-default">查询</button>
                    </td>
                </tr>
            </table>
        </div>
    </form>
    <form action="OwingMoney.action" method="post" id="myform">
        <input type="hidden" name="page" value="1" id="page"/>
        <table
                style="border: 1px #FFFFFF solid;margin: 20px auto 20px;opacity:0.9;font-family: '微软雅黑',serif;width:1000px;text-align: center;">
            <thead align="center">
            <tr>
                <td>
                    <label>
                        <input type="checkbox" onclick="allselect(this,this.form.userIds)">
                    </label>
                </td>
                <td>
                    <h2>用户号</h2>
                </td>
                <td>
                    <h2>用户名</h2>
                </td>
                <td>
                    <h2>欠费金额</h2>
                </td>
                <td>
                    <h2>欠费内容</h2>
                </td>
                <td>
                    <h2>欠费账期</h2>
                </td>
                <td>
                    <h2>录入日期</h2>
                </td>
                <td>
                    <h2>录入人</h2>
                </td>
            </tr>
            </thead>
            <s:iterator value="pageView.records" var="o" status="s">
                <tr style="background-color: #F8FBFE"
                    onmousemove="changebg(this,0)" onmouseout="changebg(this,1)">
                    <td><label>
                        <input type="checkbox" value="${o.userId}" name="userIds">
                    </label></td>
                    <td>${o.userId}</td>
                    <td>${o.userName}</td>
                    <td>${o.owingMoney}</td>
                    <td>${o.content}</td>
                    <td></td>
                    <td>${o.enterDate}</td>
                    <td>${o.enterUser}</td>

                </tr>
            </s:iterator>
        </table>
        <table align="center"
               style="font-family: '微软雅黑',serif;text-align: center">
            <tr>
                <td colspan="4" bgcolor="#114a93" align="right"
                    style="padding-right: 5px;height: 20px;">
                    <%@ include
                            file="/page/share/fenye.jsp" %>
                </td>
            </tr>
        </table>
        <input type="button" name="overdue" onclick="actionEvent()" value="已缴费"  onMouseOver="this.style.background='#2569c1'"
               onmouseout="this.style.background='#114a93'"
               style="width:200px;height:30px;margin:6px 0 0 600px ;cursor:pointer;border: none;
           background: #114a93;color: white;font-size: 16px;">
    </form>
</div>
<%@include file="/page/share/footer.jsp" %>
</body>
</html>
