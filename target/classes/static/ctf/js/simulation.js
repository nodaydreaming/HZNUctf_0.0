var competitionNumber = "HZNU_CTF_00";
var loginUser;

window.onload = function () {
    $.ajax({
        url: '../../user/getUserMap',
        type: 'post',
        scriptCharset: 'utf-8',
        success: function (result) {
            if (result != null) {
                rightTop(result);
                if(result.loginUser != null) {
                    loginUser = result.loginUser;
                    getCompetitionStatus();
                }
            }
            else {
                layer.msg(result.message);
            }
        },
        error: function () {
            console.log("请求失败！");
        }
    });
}

function getCompetitionStatus() {
    $.ajax({
        url: '../../competition/getcompetitionbynumber',
        data : {"number": competitionNumber},
        type: 'post',
        scriptCharset: 'utf-8',
        success: function (result) {
            // console.log(result);
            if (result.message == null) {
                if(result.competition != null){
                    var c = result.competition;
                    // console.log(c);
                    if(parseInt(c.competitionCanregister) == 0){
                        $('.layui-btn').remove();
                        var btn = document.createElement("button");
                        btn.className = "layui-btn layui-btn-radius layui-btn-disabled";
                        btn.style.height = "60px";
                        btn.style.width = "250px";
                        btn.style.fontSize = "25px";
                        btn.style.backgroundColor = "rgba(136,136,136,0.5)";
                        btn.style.color = "white";
                        btn.innerText = "报名暂未开启";
                        $('#btns').append(btn);
                    }
                    else if(parseInt(c.competitionCanregister) == 2){
                        $('.layui-btn').remove();
                        var btn = document.createElement("button");
                        btn.className = "layui-btn layui-btn-radius layui-btn-disabled";
                        btn.style.height = "60px";
                        btn.style.width = "250px";
                        btn.style.fontSize = "25px";
                        btn.style.backgroundColor = "rgba(136,136,136,0.5)";
                        btn.style.color = "white";
                        btn.innerText = "报名已结束";
                        $('#btns').append(btn);
                    }
                    else if(parseInt(c.competitionCanregister) == 1){
                        getUserStatus(c);
                    }
                }
            }
            else {
                console.log(result.message);
            }
        },
        error: function () {
            console.log("请求失败！");
        }
    });
}
//获得用户和此场比赛的关系，是否报名，
//如果报名了，显示队伍名，和队伍成员，队长信息等
//出现取消报名的按钮
function getUserStatus(competition) {
    var now  = new Date();
    $.ajax({
        url: '../../send/getUserStatus',
        data : {"number": competitionNumber},
        type: 'post',
        scriptCharset: 'utf-8',
        success: function (result) {
            if (result.message == null) {
                // console.log(result);
                if(parseInt(result.status) == 1){
                    $('.layui-btn').remove();
                    var p = document.createElement("p");
                    var memberStr;
                    var pText = "已报名<br>";
                    if(result.teamName != null){
                        pText += "队伍名：" + result.teamName + "<br>";
                    }
                    if(result.captainName != null){
                        pText += "队长：" + result.captainName + "<br>";
                    }
                    if(result.members != null){
                        var members = result.members;
                        memberStr = members[0];
                        for(var i=1;i<members.length;++i){
                            memberStr += "、" + members[i];
                        }
                        pText += "队伍成员：" + memberStr + "<br>";
                    }
                    p.innerHTML = pText;
                    $('#btns').append(p);
                    if(now > new Date(competition.competitionStart)){
                        //跳转到比赛页面
                        var btn = document.createElement("button");
                        btn.className = "layui-btn layui-btn-radius";
                        btn.style.height = "60px";
                        btn.style.width = "150px";
                        btn.style.fontSize = "25px";
                        btn.style.marginTop = "5%";
                        btn.innerText = "开始答题";
                        btn.onclick = goCompetition;
                        $('#btns').append(btn);
                    }
                    else{
                        //取消报名按钮
                        var btn = document.createElement("button");
                        btn.className = "layui-btn layui-btn-radius layui-btn-danger";
                        btn.style.height = "60px";
                        btn.style.width = "150px";
                        btn.style.fontSize = "25px";
                        btn.style.marginTop = "5%";
                        btn.innerText = "取消报名";
                        btn.onclick = cancelRegistration;
                        $('#btns').append(btn);
                    }
                }
                else{
                    //用户还未报名
                }
            }
            else {
                console.log(result.message);
            }
        },
        error: function () {
            console.log("请求失败！");
        }
    });
}

function goCompetition() {
    window.location.href = "../../../ctf";
}

function cancelRegistration() {
    $.ajax({
        url : '../../CompetitionUser/deleteCompetitionUser',
        type : 'post',
        data : {"number": competitionNumber},
        scriptCharset : 'utf-8',
        success : function (result) {
            if(result.message == null){
                layer.open({
                    type: 1,
                    offset: 'auto',
                    id: 'layerDemo2', //防止重复弹出
                    content: '<div style="padding: 20px 100px;">' + "取消报名成功！" + '</div>',
                    btn: '关闭',
                    btnAlign: 'c', //按钮居中
                    shade: 0.5, //不显示遮罩
                    title: "HZNUCTF",
                    yes: function () {
                        layer.closeAll();
                        window.location.reload();
                    }
                });
            }
            else{
                layer.msg(result.message);
            }
        },
        error : function () {
            console.log('请求失败！');
        }
    });
}

//判断是否登陆
function rightTop(result) {
    var login = document.getElementsByClassName('nav-right-a')[0];
    if (result.loginUser == null) {
        var unLogin_0 = document.createElement('a');
        unLogin_0.className = 'unLogin nav-right-a-first';
        unLogin_0.innerText = 'Login';
        unLogin_0.href = '../html/login.html';
        login.appendChild(unLogin_0);

        var unLogin_1 = document.createElement('a');
        unLogin_1.className = 'unLogin';
        unLogin_1.innerText = 'Register';
        unLogin_1.href = '../html/register.html';
        login.appendChild(unLogin_1);
    } else {
        var isLogin_1 = document.createElement('a');
        isLogin_1.className = 'nav-info isLogin';
        isLogin_1.href = '../html/personal.html';
        login.appendChild(isLogin_1);
        var isLogin_1img = document.createElement('img');
        isLogin_1img.src = '../img/loginout.png';
        isLogin_1.title = "退出登录";
        isLogin_1.appendChild(isLogin_1img);

        var isLogin_0 = document.createElement('a');
        isLogin_0.className = 'isLogin';
        isLogin_0.innerText = result.loginUser.competitorNickname;
        isLogin_0.href = '../html/personal.html';
        isLogin_0.title = "个人信息";
        login.appendChild(isLogin_0);

        isLogin_1.onclick = function() {
            $.ajax({
                url: '../../user/quxiao',
                type: 'post',
                scriptCharset: 'utf-8',
                success: function (result) {
                    if (result.message == null) {
                        window.location.reload();
                    }
                    else{
                        console.log(result.message);
                    }
                },
                error: function () {
                    console.log("请求失败！");
                }
            });
        }
    }
}

function createTeam() {
    if(loginUser == null){
        window.location.href="login.html";
    }
    else{
        layer.open({
            type: 1,
            offset: 'auto',
            skin: 'layui-layer-lan',
            id: 'layerDemo1', //防止重复弹出
            area: ['450px', '250px'],
            content:
                '<form class="layui-form" action="" style="margin:30px 0 0 5%; font-size:15px">\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">队伍名称</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" id="team_name" autocompvare="off" placeholder="请输入要创建的队伍名称，2-10个字符" required class="layui-input" style="width: 80%;" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '</form>\n' +
                '<script>\n' +
                'layui.use(\'form\', function(){\n' +
                '  var form = layui.form;\n' +
                '  form.render();\n' +
                '});' +
                '</script>',
            btn: ['确定', '取消'],
            btnAlign: 'c',
            shade: 0.5,
            title: "创建队伍",
            btn1 : function () {
                create_team();
                return false;
            },
            btn2 : function () {
                layer.closeAll();
            }
        });
    }
}

function create_team() {
    var team_name = $("#team_name").val();
    if(team_name == "" || team_name == null){
        layer.tips("请填写队伍名称", "#team_name");
    }
    else{
        $.ajax({
            url: '../../team/insertTeam',
            data: {
                "teamName": team_name,
                "number":competitionNumber       //给后端比赛id
            },
            type: 'post',
            scriptCharset: 'utf-8',
            success: function (result) {
                if (result.message == null) {
                    layer.open({
                        type: 1,
                        offset: 'auto',
                        id: 'layerDemo2', //防止重复弹出
                        content: '<div style="padding: 20px 100px;">' + "成功创建队伍 "+team_name+ '</div>',
                        btn: '关闭',
                        btnAlign: 'c', //按钮居中
                        shade: 0.5, //不显示遮罩
                        title: "HZNUCTF",
                        yes: function () {
                            layer.closeAll();
                            window.location.reload();
                        }
                    });
                    $.ajax({
                        url: '../../CompetitionUser/addCompetitionUser',
                        data: {
                            "number":competitionNumber      //给后端比赛id
                        },
                        type: 'post',
                        scriptCharset: 'utf-8',
                        success: function (result) {
                            if (result.message == null) {
                                // layer.msg("报名成功！");
                            }
                            else{
                                layer.msg(result.message);
                            }
                        },
                        error: function () {
                            console.log("请求失败！");
                        }
                    });
                    //--------
                }
                else{
                    layer.msg(result.message);
                }
            },
            error: function () {
                console.log("请求失败！");
            }
        });
    }
}

function joinTeam() {
    if(loginUser == null){
        window.location.href="login.html";
    }
    else{
        layer.open({
            type: 1,
            offset: 'auto',
            skin: 'layui-layer-lan',
            id: 'layerDemo1', //防止重复弹出
            area: ['450px', '250px'],
            content:
                '<form class="layui-form" action="" style="margin:30px 0 0 5%; font-size:15px">\n' +
                '    <div class="layui-form-item">\n' +
                '        <label class="layui-form-label">队伍名称</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" id="team_name" autocompvare="off" placeholder="请输入要加入的队伍名称" required class="layui-input" style="width: 80%;" onchange="inputLimit()" onkeydown="inputLimit()" onkeyup="inputLimit()">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '</form>\n' +
                '<script>\n' +
                'layui.use(\'form\', function(){\n' +
                '  var form = layui.form;\n' +
                '  form.render();\n' +
                '});' +
                '</script>',
            btn: ['确定', '取消'],
            btnAlign: 'c',
            shade: 0.5,
            title: "加入队伍",
            btn1 : function () {
                join_team();
                return false;
            },
            btn2 : function () {
                layer.closeAll();
            }
        });
    }
}

function join_team() {
    var team_name = $("#team_name").val();
    if(team_name == "" || team_name == null){
        layer.tips("请填写队伍名称", "#team_name");
    }
    else{
        $.ajax({
            url: '../../TeamCompetitor/insertTeamCompetitor',
            data: {
                "teamName": team_name,
                "number": competitionNumber       //给后端比赛id
            },
            type: 'post',
            scriptCharset: 'utf-8',
            success: function (result) {
                if (result.message == null) {
                    layer.open({
                        type: 1,
                        offset: 'auto',
                        id: 'layerDemo2', //防止重复弹出
                        content: '<div style="padding: 20px 100px;">' + "成功加入队伍 "+team_name+ '</div>',
                        btn: '关闭',
                        btnAlign: 'c', //按钮居中
                        shade: 0.5, //不显示遮罩
                        title: "HZNUCTF",
                        yes: function () {
                            layer.closeAll();
                            window.location.reload();
                        }
                    });
                    //---------成功加入队伍后调用比赛选手ajax
                    $.ajax({
                        url: '../../CompetitionUser/addCompetitionUser',
                        data: {
                            "number":competitionNumber     //给后端比赛id
                        },
                        type: 'post',
                        scriptCharset: 'utf-8',
                        success: function (result) {
                            if (result.message == null) {
                                // layer.msg("报名成功！");
                            }
                            else{
                                layer.msg(result.message);
                            }
                        },
                        error: function () {
                            console.log("请求失败！");
                        }
                    });
                    //--------
                }
                else{
                    layer.msg(result.message);
                }
            },
            error: function () {
                console.log("请求失败！");
            }
        });
    }
}

function inputLimit(){
    var input = window.event.target;
    input.value = input.value.substring(0,100); if(window.event.keyCode ==13) return false;
}

