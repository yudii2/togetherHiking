 (() => {    
	let confirmNickname = '';
	 document.querySelector('#checkNickname').addEventListener('click',function(){
         
         let nickname = document.querySelector('#nickname').value;
         console.dir(nickname)
         if(!nickname){
            document.querySelector('#nickname2').innerHTML = '닉네임을 입력하지 않았습니다.';
            return;
         }
         
         fetch("/member/check-nickname?nickname="  + nickname)
         .then(response =>{
			if(response.ok){
				return response.text()
			}else{
				throw new Error(response.status);
			}
		})
         .then(text => {
            if(text == 'available'){
               confirmNickname = nickname;
               document.querySelector('#nickname2').innerHTML = '사용 가능한 닉네임 입니다.';
            }else{
               
               document.querySelector('#nickname2').innerHTML = '사용 불가능한 닉네임 입니다.';
            }
         })
		.catch(error=>{
			 document.querySelector('#nickname2').innerHTML = '응답에 실패했습니다. 상태코드 ' + error;
		})
      });

 })();

