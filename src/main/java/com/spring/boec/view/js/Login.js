var Login = (function () {
    // $('#add_name').val();
    var initial = function () {
        console.log("initial")
        bindEvent();
        bindRegister()
    };

    var bindEvent = function () {
        $(document).on('click', '#login', function () {
            var name = $('input[name=username]').val();
            var pass = $('input[name=password]').val();
            $.ajax({
                url: "http://localhost:8080/api/v1/users/login",
                type: "post",
                data: JSON.stringify({"username": name, "password": pass}),
                contentType: 'application/json',
                dataType: "json",
                success: function (result) {
                    if (result.role === "STUDENT") {
                        window.location.href = '../html/BangDiemCaNhanToanKhoa.html';
                        sessionStorage.setItem("user", JSON.stringify(result));
                    } else if (result.role === "LECTURER") {
                        window.location.href = '../html/trangChuSqa.html';
                        sessionStorage.setItem("user", JSON.stringify(result));
                    } else {
                        $.ajax({
                            url: 'http://localhost:8080/api/v1/users/getAllStudent',
                            type: "GET",
                            success: function (result) {
                                if (result.length > 0) {
                                    sessionStorage.setItem("Student", JSON.stringify(result));
                                } else {
                                    alert("không có danh sách");
                                }
                            },
                            error: function (error) {
                                console.log(error);
                            }
                        });
                        window.location.href = 'index.html';
                        sessionStorage.setItem("user", JSON.stringify(result));
                    }
                    sessionStorage.setItem("user", JSON.stringify(result));
                    console.log(result);
                },
                error: function (error) {
                    $('#errorField').html('<div>' + error.responseJSON.message + '<div>');
                    console.log(error);
                }
            });
        });
    };

    var bindRegister = function () {
        $(document).on('click', '#register', function () {
            var name = $('input[name=registerUsername]').val();
            var pass = $('input[name=registerPassword]').val();
            var firstName = $('input[name=firstName]').val();
            var middleName = $('input[name=middleName]').val();
            var lastName = $('input[name=lastName]').val();
            var phone = $('input[name=phone]').val();
            var gender = $('input[name=gender]').val();
            var city = $('input[name=city]').val();
            var street = $('input[name=street]').val();
            var body = JSON.stringify({
                "username": name,
                "password": pass,
                "phoneNo": phone,
                "role": "user",
                "gender": gender,
                "fullName": {"firstName": firstName, "middleName": middleName, "lastName": lastName},
                "address": {"city": city, "street": street},
            })

            $.ajax({
                url: "http://localhost:8080/api/v1/users/signup",
                type: "post",
                data: body,
                contentType: 'application/json',
                dataType: "json",
                success: function (result) {
                        $.ajax({
                            url: 'http://localhost:8080/api/v1/users/login',
                            type: "POST",
                            contentType: 'application/json',
                            dataType: "json",
                            data: JSON.stringify({"username": name, "password": pass}),
                            success: function (result) {
                                if (result.length > 0) {
                                    sessionStorage.setItem("Student", JSON.stringify(result));
                                } else {
                                    alert("không có danh sách");
                                }
                            },
                            error: function (error) {
                                console.log(error);
                            }
                        });
                        window.location.href = 'index.html';
                        sessionStorage.setItem("user", JSON.stringify(result));
                    },
                error: function (error) {
                    $('#errorField').html('<div>' + error.responseJSON.message + '<div>');
                    console.log(error);
                }
            });
        });
    }

    return {
        initial: initial
    }
})();
Login.initial()