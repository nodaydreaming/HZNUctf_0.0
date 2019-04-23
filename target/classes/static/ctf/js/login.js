window.onload = function() {
    var login = document.getElementsByClassName('nav-right-a')[0];

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
}

var login = document.getElementsByClassName('login-btn')[0];

login.onclick = function() {
    var usr = document.getElementsByClassName('usr')[0].value;
    var pwd = document.getElementsByClassName('pwd')[0].value;
    var message = document.getElementsByClassName('login-msg')[0];
    //数据检测
    var flag = [true, true];
    var reg = new RegExp("^[a-z0-9]+$");

    for (var i=1; i<=2; i++) {
        if (i == 1) {
            if (usr == "" || usr == null) {
                message.innerText = '用户名不能为空！';
                flag[0] = false;
            }
            else if(usr.length < 3 || usr.length > 20 || !reg.test(usr)) {
                message.innerText = '用户名必须是3-20个小写字母或数字';
                flag[0] = false;
            }
            if (flag[0] == false) break;
        } else if (i == 2) {
            if (pwd == '' || pwd == null) {
                message.innerText = '密码不能为空！';
                flag[1] = false;
            } else if (pwd.length < 3 || pwd.length > 15 || !reg.test(pwd)) {
                message.innerText = '密码必须是5-15个小写字母或数字';
                flag[1] = false;
            }
            if (flag[1] == false) break;
        }
    }

    if (flag[0] && flag[1]) {
        message.style.display = 'none';

        //检测通过，数据发送
        $.ajax ({
            url : '../../user/login',
            data : {
                "username": usr,
                "password": pwd
            },
            type : 'post',
            scriptCharset : 'utf-8',
            success : function (result) {
                if(result.message == null) {
                    window.location.href = '../home.html';
                } else{
                    message.innerText = result.message;
                    message.style.display = 'block';
                }
            },
            error : function () {
                console.log("请求失败！");
            }
        });
    } else {
        message.style.display = 'block';
    }
}

