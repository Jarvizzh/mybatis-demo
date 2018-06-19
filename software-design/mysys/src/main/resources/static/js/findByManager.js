/**
 * Created by 云翔 on 2018/1/16.
 */
$(document).ready(function () {
    $(".name").click(function () {
        $.get("../finder/byName/" + $(".name-text").val(),
            function (data, status) {
                data = JSON.parse(data);
                if (data.result == "success") {
                    var text = "";
                    for (var i = 0; i < data.message.length; i++) {
                        text = text + "<tr userid='" + data.message[i].userId + "'>";
                        text = text + "<td>" + data.message[i].nickname + "</td>";
                        var sex = "男";
                        if (data.message[i].sex == 1) {
                            sex = "女";
                        }
                        text = text + "<td>" + sex + "</td>";
                        text = text + "<td>" + data.message[i].qq + "</td>";
                        text = text + "<td>" + data.message[i].phone + "</td>";
                        text = text + "<td>" + data.message[i].weChat + "</td>";
                        text = text + "<td>" + data.message[i].mail + "</td>";
                        text = text + "<td>" + data.message[i].address + "</td>";
                        text = text + "<td>" + data.message[i].introduction + "</td>";
                        text = text + "<td>" + data.message[i].hobby + "</td>";
                        text = text + "<td><button class='btn btn-primary frozen'>冻结10天</button></td>";
                        text = text + "<td><button class='btn btn-primary delete'>删除</button></td>";
                        text = text + "<tr/>";
                    }

                    $(".t-body").html('').append(text);

                    $(".frozen").click(function () {
                        var frozen = window.confirm("确定冻结吗");
                        if (frozen == true) {
                            var that = $(this).parent().parent().attr('userid')
                            console.log(that)

                            $.post("frozenUser", {
                                    userId: that,
                                    days: 10
                                },
                                function (data, status) {
                                    data = JSON.parse(data);
                                    if (data.result == "success") {
                                        alert('冻结成功');
                                        setTimeout("", 3000)
                                        window.location.reload();
                                    } else {
                                        alert("冻结失败，原因：" + data.message);
                                    }
                                });
                        }

                    })

                    $(".delete").click(function () {
                        var frozen = window.confirm("确定删除吗");
                        if (frozen == true) {
                            var that = $(this).parent().parent().attr('userid')

                            $.post("deleteUser", {
                                    userId: that
                                },
                                function (data, status) {
                                    data = JSON.parse(data);
                                    if (data.result == "success") {
                                        alert('删除成功');
                                        setTimeout("", 3000)
                                        window.location.reload();
                                    } else {
                                        alert("删除失败，原因：" + data.message);
                                    }
                                });
                        }

                    })
                } else {
                    alert("查找失败，原因：" + data.message);
                }
            });
    });

    $(".phone").click(function () {
        $.get("../finder/byPhone/" + $(".phone-text").val(),
            function (data, status) {
                data = JSON.parse(data);
                if (data.result == "success") {
                    var text = "";
                    for (var i = 0; i < data.message.length; i++) {
                        text = text + "<tr userid='" + data.message[i].userId + "'>";
                        text = text + "<td>" + data.message[i].nickname + "</td>";
                        var sex = "男";
                        if (data.message[i].sex == 1) {
                            sex = "女";
                        }
                        text = text + "<td>" + sex + "</td>";
                        text = text + "<td>" + data.message[i].qq + "</td>";
                        text = text + "<td>" + data.message[i].phone + "</td>";
                        text = text + "<td>" + data.message[i].weChat + "</td>";
                        text = text + "<td>" + data.message[i].mail + "</td>";
                        text = text + "<td>" + data.message[i].address + "</td>";
                        text = text + "<td>" + data.message[i].introduction + "</td>";
                        text = text + "<td>" + data.message[i].hobby + "</td>";
                        text = text + "<td><button class='btn btn-primary frozen'>冻结10天</button></td>";
                        text = text + "<td><button class='btn btn-primary delete'>删除</button></td>";
                        text = text + "<td><button class='btn btn-primary update'>修改</button></td>";
                        text = text + "<tr/>";
                    }

                    $(".t-body").html('').append(text);

                    $(".frozen").click(function () {
                        var frozen = window.confirm("确定冻结吗");
                        if (frozen == true) {
                            var that = $(this).parent().parent().attr('userid')
                            console.log(that)

                            $.post("frozenUser", {
                                    userId: that,
                                    days: 10
                                },
                                function (data, status) {
                                    data = JSON.parse(data);
                                    if (data.result == "success") {
                                        alert('冻结成功');
                                        setTimeout("", 3000)
                                        window.location.reload();
                                    } else {
                                        alert("冻结失败，原因：" + data.message);
                                    }
                                });
                        }

                    })

                    $(".delete").click(function () {
                        var frozen = window.confirm("确定删除吗");
                        if (frozen == true) {
                            var that = $(this).parent().parent().attr('userid')

                            $.post("deleteUser", {
                                    userId: that
                                },
                                function (data, status) {
                                    data = JSON.parse(data);
                                    if (data.result == "success") {
                                        alert('删除成功');
                                        setTimeout("", 3000)
                                        window.location.reload();
                                    } else {
                                        alert("删除失败，原因：" + data.message);
                                    }
                                });
                        }

                    })
                } else {
                    alert("查找失败，原因：" + data.message);
                }
            });
    });

    $(".hobby").click(function () {
        $.get("../finder/byHobby/" + $(".hobby-text").val(),
            function (data, status) {
                data = JSON.parse(data);
                if (data.result == "success") {
                    var text = "";
                    for (var i = 0; i < data.message.length; i++) {
                        text = text + "<tr userid='" + data.message[i].userId + "'>";
                        text = text + "<td>" + data.message[i].nickname + "</td>";
                        var sex = "男";
                        if (data.message[i].sex == 1) {
                            sex = "女";
                        }
                        text = text + "<td>" + sex + "</td>";
                        text = text + "<td>" + data.message[i].qq + "</td>";
                        text = text + "<td>" + data.message[i].phone + "</td>";
                        text = text + "<td>" + data.message[i].weChat + "</td>";
                        text = text + "<td>" + data.message[i].mail + "</td>";
                        text = text + "<td>" + data.message[i].address + "</td>";
                        text = text + "<td>" + data.message[i].introduction + "</td>";
                        text = text + "<td>" + data.message[i].hobby + "</td>";
                        text = text + "<td><button class='btn btn-primary frozen'>冻结10天</button></td>";
                        text = text + "<td><button class='btn btn-primary delete'>删除</button></td>";
                        text = text + "<tr/>";
                    }

                    $(".t-body").html('').append(text);

                    $(".frozen").click(function () {
                        var frozen = window.confirm("确定冻结吗");
                        if (frozen == true) {
                            var that = $(this).parent().parent().attr('userid')
                            console.log(that)

                            $.post("frozenUser", {
                                    userId: that,
                                    days: 10
                                },
                                function (data, status) {
                                    data = JSON.parse(data);
                                    if (data.result == "success") {
                                        alert('冻结成功');
                                        setTimeout("", 3000)
                                        window.location.reload();
                                    } else {
                                        alert("冻结失败，原因：" + data.message);
                                    }
                                });
                        }

                    })

                    $(".delete").click(function () {
                        var frozen = window.confirm("确定删除吗");
                        if (frozen == true) {
                            var that = $(this).parent().parent().attr('userid')

                            $.post("deleteUser", {
                                    userId: that
                                },
                                function (data, status) {
                                    data = JSON.parse(data);
                                    if (data.result == "success") {
                                        alert('删除成功');
                                        setTimeout("", 3000)
                                        window.location.reload();
                                    } else {
                                        alert("删除失败，原因：" + data.message);
                                    }
                                });
                        }

                    })
                } else {
                    alert("查找失败，原因：" + data.message);
                }
            });
    });
});