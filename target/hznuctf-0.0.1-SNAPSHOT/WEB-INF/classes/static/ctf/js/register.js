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
    unLogin_1.href = 'javascript:void(0)';
    login.appendChild(unLogin_1);
}

var register = document.getElementsByClassName('register-btn')[0];
register.onclick = function() {
    var usr = document.getElementsByClassName('userName')[0].value;
    var pwd = document.getElementsByClassName('passWord')[0].value;
    var gender = document.getElementsByName('gender');
    var genvalue = '';
    var nike = document.getElementsByClassName('nikeName')[0].value;
    var pho = document.getElementsByClassName('phoneNumber')[0].value;
    var stu = document.getElementsByClassName('studentNumber')[0].value;
    var index = document.getElementsByClassName('register-select')[0];
    var aca = '';
    for(i=1; i<index.length; i++) {
        if (index[i].selected == true) {
            aca = index[i].text;
            break;
        }
    }
    var clas = document.getElementsByClassName('className')[0].value;
    var real = document.getElementsByClassName('realName')[0].value;
    var message = document.getElementsByClassName('register-msg')[0];

    //数据检测
    var flag = [true, true, true, true, true, true, true, true, true];
    var reg = new RegExp("^[a-z0-9]+$");
    var regNumber = new RegExp("^[0-9]+$");

    for (var i=1; i<=9; i++) {
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
            } else if (pwd.length < 5 || pwd.length > 15 || !reg.test(pwd)) {
                message.innerText = '密码必须是5-15个小写字母或数字';
                flag[1] = false;
            }
            if (flag[1] == false) break;
        } else if (i == 8) {
            if (gender[0].checked) {
                genvalue = gender[0].value;
            } else if (gender[1].checked) {
                genvalue = gender[1].value;
            } else {
                genvalue = '';
                message.innerText = '请选择性别！';
                flag[7] = false;
            }
            if (flag[7] == false) break;
        } else if (i == 3) {
            if (nike == '' || nike == null) {
                message.innerText = '昵称不能为空！';
                flag[2] = false;
            } else if (nike.length < 1 || nike.length > 15 ) {
                message.innerText = '昵称必须是1-15个字符';
                flag[2] = false;
            }
            if (flag[2] == false) break;
        } else if (i == 4) {
            if (pho == '' || pho == null) {
                message.innerText = '手机号码不能为空！';
                flag[3] = false;
            } else if (pho.length != 11 || !regNumber.test(pho)) {
                message.innerText = '手机号码必须是11位长号';
                flag[3] = false;
            }
            if (flag[3] == false) break;
        } else if (i == 5) {
            if (stu == '' || stu == null) {
                message.innerText = '学号不能为空！';
                flag[4] = false;
            } else if (stu.length != 13 || !regNumber.test(stu)) {
                message.innerText = '学号必须是13位数字';
                flag[4] = false;
            }
            if (flag[4] == false) break;
        } else if (i == 6) {
            if (aca == '' || aca == null) {
                message.innerText = '学院名称不能为空！';
                flag[5] = false;
            } else if (stu.length > 13) {
                message.innerText = '学院名称过长';
                flag[5] = false;
            }
            if (flag[5] == false) break;
        } else if (i == 7) {
            if (clas == '' || clas == null) {
                message.innerText = '班级名称不能为空！';
                flag[6] = false;
            } else if (clas.length > 13 ) {
                message.innerText = '班级名称过长';
                flag[6] = false;
            }
            if (flag[6] == false) break;
        } else if (i == 9) {
            if (real == '' || real == null) {
                message.innerText = '真实姓名不能为空！';
                flag[8] = false;
            } else if (real.length < 2) {
                message.innerText = '姓名过短';
                flag[8] = false;
            } else if (real.length > 6) {
                message.innerText = '姓名过长';
                flag[8] = false;
            }
        }

    }
    
    if (flag[0] && flag[1] && flag[2] && flag[3] && flag[4] && flag[5] && flag[6] && flag[7] && flag[8]) {
        message.style.display = 'none';
        //检测通过，数据发送
        $.ajax ({
            url : '../../user/adduser',
            data : {
                "competitorUsername": usr,
                "competitorPassword": pwd,
                "competitorGender": genvalue,
                "competitorNickname": nike,
                "competitorName": real,
                "competitorTel": pho,
                "competitorStudentnumber": stu,
                "competitorAcademy": aca,
                "competitorClass": clas,
                
            },
            type : 'post',
            scriptCharset : 'utf-8',
            success : function (result) {
                console.log(result);
                if(result.message == null) {
                    window.location.href = '../html/login.html';
                } else {
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