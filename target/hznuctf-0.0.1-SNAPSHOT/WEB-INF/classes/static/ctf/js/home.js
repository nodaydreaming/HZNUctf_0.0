window.onload = function () {
    $.ajax({
        url: '../user/getUserMap',
        type: 'post',
        scriptCharset: 'utf-8',
        success: function (result) {
            if (result != null) {
                rightTop(result);

            }
        },
        error: function () {
            console.log("请求失败！");
        }
    });

    function rightTop(result) {
        var login = document.getElementsByClassName('nav-right-a')[0];
        if (result.loginUser == null) {
            var unLogin_0 = document.createElement('a');
            unLogin_0.className = 'unLogin nav-right-a-first';
            unLogin_0.innerText = 'Login';
            unLogin_0.href = 'html/login.html';
            login.appendChild(unLogin_0);

            var unLogin_1 = document.createElement('a');
            unLogin_1.className = 'unLogin';
            unLogin_1.innerText = 'Register';
            unLogin_1.href = 'html/register.html';
            login.appendChild(unLogin_1);
        } else {
            var login = document.getElementsByClassName('nav-right-a')[0];

            var isLogin_1 = document.createElement('a');
            isLogin_1.className = 'nav-info isLogin';
            isLogin_1.href = 'javascript:void(0)';
            login.appendChild(isLogin_1);
            var isLogin_1img = document.createElement('img');
            isLogin_1img.src = 'img/loginout.png';
            isLogin_1.title = "退出登录";
            isLogin_1.appendChild(isLogin_1img);

            var isLogin_0 = document.createElement('a');
            isLogin_0.className = 'isLogin';
            isLogin_0.innerText = result.loginUser.competitorNickname;
            isLogin_0.href = 'html/personal.html';
            isLogin_0.title = "个人信息";
            login.appendChild(isLogin_0);

            isLogin_1.onclick = function() {
                $.ajax({
                    url: '../user/quxiao',
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

}