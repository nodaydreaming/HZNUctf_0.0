function my$(className) {
    return document.getElementsByClassName(className)[0];
}

//获取各元素
var header=my$('header-show');
var box=my$("show-box");
var inner=box.children[0];
var ulObj=inner.children[0];
var list=ulObj.children;
var olObj=inner.children[1];
// var arr=document.getElementsByClassName('show-side');
var imgWidth=inner.offsetWidth;
var arrow=document.getElementsByClassName('show-arrow');
var left=arrow[0].getElementsByTagName('img')[0];
var right=arrow[1].getElementsByTagName('img')[0];
var pic=0;
var isAnimate = false;

for(var i=0;i<list.length;i++){
    var liObj=document.createElement("li");
    olObj.appendChild(liObj);
    liObj.setAttribute("index",i);

    liObj.onmouseover=function () {
        for (var j=0;j<olObj.children.length;j++){
            olObj.children[j].removeAttribute("class");
        }
        this.className="current";
        pic=this.getAttribute("index");
        animate(ulObj,-pic*imgWidth,2);
    }
}

olObj.children[0].className = "current";
ulObj.appendChild(ulObj.children[0].cloneNode(true));

var timeId=setInterval(onmouseclickHandle,2000);
box.onmouseover=function () {
    clearInterval(timeId);
};
box.onmouseout=function () {
    timeId=setInterval(onmouseclickHandle,2000);
};
left.onmouseover=function () {
    clearInterval(timeId);
};
left.onmouseout=function () {
    timeId=setInterval(onmouseclickHandle,2000);
};
right.onmouseover=function () {
    clearInterval(timeId);
};
right.onmouseout=function () {
    timeId=setInterval(onmouseclickHandle,2000);
};

right.onclick=onmouseclickHandle;
function onmouseclickHandle() {
    if(isAnimate == false) {
        if (pic == list.length - 1) {
            pic = 0;
            ulObj.style.left = 0 + "px";
        }
        pic++;
        animate(ulObj, -pic * imgWidth, 10);
        if (pic == list.length - 1) {
            olObj.children[olObj.children.length - 1].className = "";
            olObj.children[0].className = "current";
        } else {
            for (var i = 0; i < olObj.children.length; i++) {
                olObj.children[i].removeAttribute("class");
            }
            olObj.children[pic].className = "current";
        }
    }
}

left.onclick=function () {
    if(isAnimate == false) {
        if (pic==0){
            pic=list.length-1;
            ulObj.style.left=-pic*imgWidth+"px";
        }
        pic--;
        animate(ulObj,-pic*imgWidth,10);
        for (var i = 0; i < olObj.children.length; i++) {
            olObj.children[i].removeAttribute("class");
        }
        olObj.children[pic].className = "current";
    }
};

//设置任意的一个元素,移动到指定的目标位置
function animate(element, target, time) {               
    clearInterval(element.timeId);
    element.timeId = setInterval(function () {
        isAnimate = true;
        var current = element.offsetLeft;
        var step = 10;
        step = current < target ? step : -step;
        current += step;
        if (Math.abs(current - target) > Math.abs(step)) {
            element.style.left = current + "px";
        } else {
            clearInterval(element.timeId);
            isAnimate = false;
            element.style.left = target + "px";
        }
    }, time);
}