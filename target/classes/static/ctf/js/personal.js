window.onload = function () {
    let login = document.getElementsByClassName('nav-right-a')[0];
    let change = document.getElementsByClassName('login-btn')[0];

    let usr = document.getElementsByClassName('userName')[0];
    let pwd = document.getElementsByClassName('passWord')[0];
    let npwd = document.getElementsByClassName('newPassWord')[0];
    let apwd = document.getElementsByClassName('againPassWord')[0];
    let nike = document.getElementsByClassName('nikeName')[0];
    let realName = document.getElementsByClassName('realName')[0];
    let pho = document.getElementsByClassName('phoneNumber')[0];
    let stu = document.getElementsByClassName('studentNumber')[0];
    let aca = document.getElementsByClassName('academy')[0];
    let clas = document.getElementsByClassName('className')[0];
    let email = document.getElementsByClassName('email')[0];
    let message = document.getElementsByClassName('login-msg')[0];

    $.ajax({
        url: '../../user/getUserMap',
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
        let login = document.getElementsByClassName('nav-right-a')[0];
        if (result.loginUser == null) {
            let unLogin_0 = document.createElement('a');
            unLogin_0.className = 'unLogin nav-right-a-first';
            unLogin_0.innerText = 'Login';
            unLogin_0.href = 'login.html';
            login.appendChild(unLogin_0);

            let unLogin_1 = document.createElement('a');
            unLogin_1.className = 'unLogin';
            unLogin_1.innerText = 'Register';
            unLogin_1.href = 'register.html';
            login.appendChild(unLogin_1);
        } else {
            let login = document.getElementsByClassName('nav-right-a')[0];

            let isLogin_1 = document.createElement('a');
            isLogin_1.className = 'nav-info isLogin';
            isLogin_1.href = 'javascript:void(0)';
            login.appendChild(isLogin_1);
            let isLogin_1img = document.createElement('img');
            isLogin_1img.src = '../img/loginout.png';
            isLogin_1.title = "退出登录";
            isLogin_1.appendChild(isLogin_1img);

            let isLogin_0 = document.createElement('a');
            isLogin_0.className = 'isLogin';
            isLogin_0.innerText = result.loginUser.competitorNickname;
            isLogin_0.href = 'personal.html';
            isLogin_0.title = "个人信息";
            login.appendChild(isLogin_0);

            isLogin_1.addEventListener('click', () => {
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
            })
        }
    }

    $.ajax({
        url: '../../user/getuserbyUsername',
        data: {},
        type: 'post',
        scriptCharset: 'utf-8',
        success: function (result) {
            if (result != null) {
                if (result.message == null) {
                    result.loginUser;
                    result.getUserByUsername;
                    usr.value = result.getUserByUsername.competitorUsername;
                    pwd.value = '';
                    npwd.value = '';
                    apwd.value = '';
                    nike.value = result.getUserByUsername.competitorNickname;
                    realName.value = result.getUserByUsername.competitorName;
                    pho.value = result.getUserByUsername.competitorTel;
                    stu.value = result.getUserByUsername.competitorStudentnumber;
                    aca.value = result.getUserByUsername.competitorAcademy;
                    clas.value = result.getUserByUsername.competitorClass;
                    email.value = result.getUserByUsername.competitorEmail;
                }
            }
        },
        error: function () {
            console.log("请求失败！");
        }
    });

    change.addEventListener('click', () => {
        // let usr = document.getElementsByClassName('userName')[0].value;
        let pwd = document.getElementsByClassName('passWord')[0].value;
        let npwd = document.getElementsByClassName('newPassWord')[0].value;
        let apwd = document.getElementsByClassName('againPassWord')[0].value;
        // let nike = document.getElementsByClassName('nikeName')[0].value;
        // let realName = document.getElementsByClassName('realName')[0].value;
        // let pho = document.getElementsByClassName('phoneNumber')[0].value;
        // let stu = document.getElementsByClassName('studentNumber')[0].value;
        // let aca = document.getElementsByClassName('academy')[0].value;
        // let clas = document.getElementsByClassName('className')[0].value;
        let email = document.getElementsByClassName('email')[0].value;

        let flag = [null, true, true, true];
        let reg = new RegExp("^[a-z0-9]+$");

        for (var i = 1; i <= flag.length-1; i++) {
            if (i == 1) {
                if (pwd == '' || pwd == null) {
                    message.innerText = '密码不能为空！';
                    flag[1] = false;
                } else if (pwd.length < 5 || pwd.length > 15 || !reg.test(pwd)) {
                    message.innerText = '密码必须是5-15个小写字母或数字';
                    flag[1] = false;
                }
                if (flag[1] == false) break;
            } else if (i == 2) {
                if (npwd == '' || npwd == null) {
                    continue;
                } else if (npwd.length < 5 || npwd.length > 15 || !reg.test(npwd)) {
                    message.innerText = '新密码必须是5-15个小写字母或数字';
                    flag[2] = false;
                } else if (npwd != apwd) {
                    message.innerText = '两次输入的新密码不一样';
                    flag[2] = false;
                }
                if (flag[2] == false) break;
            } else if (i == 3) {
                if (email.length > 20) {
                    message.innerText = '邮箱字符过长';
                    flag[3] = false;
                }
                if (flag[3] == false) break;
            } else if (i == 4) {
                if (nike == '' || nike == null) {
                    message.innerText = '昵称不能为空！';
                    flag[4] = false;
                } else if (nike.length < 1 || nike.length > 15 ) {
                    message.innerText = '昵称必须是1-15个字符';
                    flag[4] = false;
                }
                if (flag[4] == false) break;
            }
        }

        if (flag[1] && flag[2] && flag[3] && flag[3]) {
            message.style.display = 'none';
            //检测通过，数据发送
            // let usr = document.getElementsByClassName('userName')[0].value;
            let pwd = document.getElementsByClassName('passWord')[0].value;
            let nike = document.getElementsByClassName('nikeName')[0].value;
            // let pho = document.getElementsByClassName('phoneNumber')[0].value;
            // let stu = document.getElementsByClassName('studentNumber')[0].value;
            // let aca = document.getElementsByClassName('academy')[0].value;
            // let clas = document.getElementsByClassName('className')[0].value;
            let email = document.getElementsByClassName('email')[0].value;
            $.ajax({
                url: '../../user/updateuser',
                data: {
                    // "competitorUsername": usr,
                    "competitorPassword": pwd,
                    "newPassword": npwd,
                    "competitorNickname": nike,
                    // "competitorTel": pho,
                    // "competitorStudentnumber": stu,
                    // "competitorAcademy": aca,
                    // "competitorClass": clas,
                    "competitorEmail": email
                },
                type: 'post',
                scriptCharset: 'utf-8',
                success: function (result) {
                    if (result.message == null) {
                        if(npwd != null && npwd != ""){
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
                        else{
                            //window.location.reload();
                        }
                    } else {
                        
                    }
                    message.innerText = result.message;
                    message.style.display = 'block';
                    scrollTo(0, 0);
                },
                error: function () {
                    console.log("请求失败！");
                }
            });

        } else {
            message.style.display = 'block';
        }
    })
}