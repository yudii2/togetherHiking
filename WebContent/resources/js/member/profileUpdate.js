
	/* 프로필 등록 */
   document.querySelector('.profile_img').addEventListener('click', function (e) {
    	 document.profile.target_url.value = document.getElementById('target_img').src;
         e.preventDefault();
		console.dir('클릭 전');
       	 $('#file').click();	//changeValue메서드 호출
   });

   
   let changeValue = function(obj) {
	console.dir('메서드 실행' + obj);
  	 document.profile.submit();
	
   }
   
