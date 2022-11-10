function checkname() {
    //取输入框内容
    if (document.getElementById("name").value.length == 0) {
        document.getElementById("msg-name").style.visibility = "visible";
        return false;
    } else {
        document.getElementById("msg-name").style.visibility = "hidden";
        return true;
    }
}

function checkpwd() {
    //取输入框内容
    if (document.getElementById("pwd").value.length < 6) {
        document.getElementById("msg-pwd").style.visibility = "visible";
    } else {
        document.getElementById("msg-pwd").style.visibility = "hidden";
    }
}

function checkpwdc() {
    //取输入框内容
    if (document.getElementById("pwdc").value != document.getElementById("pwd").value) {
        document.getElementById("msg-pwdc").style.visibility = "visible";
        return false;
    } else {
        document.getElementById("msg-pwdc").style.visibility = "hidden";
        return true;
    }
}

function checkemail() {
    //正则表达式判断格式
    //  ^:匹配行首文件         \w: 匹配字母、数字、下划线
    //  []:匹配其中任意字符     $: 表示结束
    let re = /^\w+@[0-9a-z]+\.[a-z]+$/;
    let email = document.getElementById("email");
    if (re.test(email.value)) {
        document.getElementById("msg-email").style.visibility = "hidden";
        return true;
    } else {
        document.getElementById("msg-email").style.visibility = "visible";
        return false;
    }
}

function check() {
    if (checkname() && checkemail()) {
        return !!checkpwdc();
    } else {
        return false
    }
}
