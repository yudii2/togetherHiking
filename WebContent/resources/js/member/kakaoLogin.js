Kakao.init('b48910cb5f06aa478cc2184431f97be9');
function loginFormWithKakao() {
    Kakao.Auth.loginForm({
             success : function(authObj) {
                Kakao.Auth.login({
                         scope : 'profile_nickname,account_email,gender',
                         success : function(e) {
                            console.dir(e)
                            Kakao.API.request({
                                     url : '/v2/user/me',
                                     success : function(res) {
										fetch("/member/kakao-join?userId="  + res.id);

	
                                        console.dir(res.id)
                                        
                                     },fail : function(error) {
                                        alert('login success, but failed to request user information: '
                                              + JSON.stringify(error))
                                     }
                                  })
                         },fail : function(error) {
                            console.dir(error)
                         },

                      })

             },fail : function(err) {
                showResult(JSON.stringify(err))
             },
          })
 }