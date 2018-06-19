/**
 * Created by 云翔 on 2018/1/16.
 */
/**
 * Created by 云翔 on 2018/1/16.
 */
$(document).ready(function () {
    $(".add").click(function () {
        $.post("addUser", {
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
                radio: $("#checkbox input[name = 'sex']:checked").val(),
                url: "addUser"
            },
            function (data, status) {
                data = JSON.parse(data);
                if (data.result == "success") {
                    alert('添加成功');
                    setTimeout("", 3000)
                    window.location.href = "../manager";
                } else {
                    alert("添加失败，原因：" + data.message);
                }
            });
    });
});