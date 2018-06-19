/**
 * Created by 云翔 on 2018/1/16.
 */
$(document).ready(function () {
    $(".register").click(function () {
        $.post("register", {
                name: $(".name").val(),
                password: $(".password").val(),
                nickname: $(".nickname").val(),
                mail: $(".mail").val(),
                qq: $(".qq").val(),
                weChat: $(".weChat").val(),
                phone: $(".phone").val(),
                address: $(".address").val(),
                hobby: $(".hobby").val(),
                introduction: $(".introduction").val(),
                reason: $(".reason").val(),
                radio: $("#checkbox input[name = 'sex']:checked").val(),
                url: "register"
            },
            function (data, status) {
                data = JSON.parse(data);
                if (data.result == "success") {
                    alert('注册申请成功，等待管理员审核');
                    setTimeout("", 3000)
                    window.location.href = "login";
                } else {
                    alert("注册失败，原因：" + data.message);
                }
            });
    });
});