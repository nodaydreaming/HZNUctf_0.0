window.onload = function () {
    var createbt = document.getElementById('createTeam');
    var addbt = document.getElementById('addTeam');
    var login = document.getElementsByClassName('nav-right-a')[0];

    $.ajax({
        url: '../../user/getUserMap',
        type: 'post',
        scriptCharset: 'utf-8',
        success : function (result) {
            if (result != null) {

                if (result.message == null) {
                    result.loginUser;
                    rightTop(result);
                }
                else{
                    alert(result.message);
                }
            }
        },
        error: function () {
            alert("请求失败！");
        }
    });

    //判断是否登陆
    function rightTop(result) {
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

            createbt.addEventListener('click', () => {
                window.location.href = '../html/login.html';
            })
            addbt.addEventListener('click', () => {
                window.location.href = '../html/login.html';
            })
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

            //--- 登录成功后为按钮添加事件--
            createbt.onclick = function() {

                var createTeamName = prompt('创建队伍：', '队伍名');
                if (createTeamName == '' || createTeamName.length > 8) {
                    alert('格式错误！队伍名非法或过长');
                } else {
                    $.ajax({
                        url: '../../team/insertTeam',
                        data: {
                            "teamName": createTeamName,
                            "teamCompetitionId":31       //给后端比赛id
                        },
                        type: 'post',
                        scriptCharset: 'utf-8',
                        success: function (result) {
                            if (result.message == null) {
                                alert(`成功创建队伍 ${createTeamName}`);
                                //---------成功创建队伍后调用比赛选手ajax
                                $.ajax({
                                    url: '../../CompetitionUser/addCompetitionUser',
                                    data: {
                                        "competitionId":parseInt(31)       //给后端比赛id
                                    },
                                    type: 'post',
                                    scriptCharset: 'utf-8',
                                    success: function (result) {
                                        if (result.message == null) {
                                        }
                                        else{
                                            alert(result.message);
                                        }
                                    },
                                    error: function () {
                                        alert("请求失败！");
                                    }
                                });
                                //--------
                            }
                            else{
                                alert(result.message);
                            }
                        },
                        error: function () {
                            alert("请求失败！");
                        }
                    });
                }


            }

            addbt.onclick = function() {

                var addTeamName = prompt('加入队伍：', '队伍名');
                if (addTeamName == '' || addTeamName.length > 8) {
                    alert('格式错误！队伍名非法或过长');
                } else {
                    $.ajax({
                        url: '../../TeamCompetitor/insertTeamCompetitor',
                        data: {
                            "teamName": addTeamName
                        },
                        type: 'post',
                        scriptCharset: 'utf-8',
                        success: function (result) {
                            if (result.message == null) {
                                alert(`成功加入队伍 ${addTeamName}`);
                                //---------成功加入队伍后调用比赛选手ajax
                                $.ajax({
                                    url: '../../CompetitionUser/addCompetitionUser',
                                    data: {
                                        "competitionId":parseInt(31)       //给后端比赛id
                                    },
                                    type: 'post',
                                    scriptCharset: 'utf-8',
                                    success: function (result) {
                                        if (result.message == null) {
                                        }
                                        else{
                                            alert(result.message);
                                        }
                                    },
                                    error: function () {
                                        alert("请求失败！");
                                    }
                                });
                                //--------
                            }
                            else{
                                alert(result.message);
                            }
                        },
                        error: function () {
                            alert("请求失败！");
                        }
                    });
                }


            }

            //--- 登录成功后为按钮添加事件--
        }
    }


}
