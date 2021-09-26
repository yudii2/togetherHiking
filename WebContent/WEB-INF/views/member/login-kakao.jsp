<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="/resources/css/member/check-id.css" />
</head>
<body>
<%@ include file="/WEB-INF/views/include/fixed-header.jsp" %>

 <a href="javascript:kakaoLogin();"><img src="/resources/img/kakao_login_large_wide.png" width="300px""></a>
    <script src="http://developers.kakao.com/sdk/js/kakao.js"></script>
        // b48910cb5f06aa478cc2184431f97be9
        <script>
        window.Kakao.init("b48910cb5f06aa478cc2184431f97be9");

        function kakaoLogin() {  
            window.kakpo.Auth.Login({
                scope:'profile_nickname, profile_image, account_email, gender,friends,birthday',
                success: function(authObj) {
                    console.log(authObj);
                    window.kakao.API.request({
                        url:'/v2/user/me',
                        success: res => {
                            const kakao_account = res.kakao_account;
                            console.log(kakao_account);
                        }

                    })
                    
                }
            })
      
        </script>
</body>

</html>