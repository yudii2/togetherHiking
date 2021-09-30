(()=>{
	/* 프로필 등록 */
   document.querySelector('.profile_img').addEventListener('click', function (e) {
    	 document.profile.target_url.value = document.getElementById('target_img').src;
         e.preventDefault();
       	 $('#file').click();	//changeValue메서드 호출
   });

   
   let changeValue = function(obj) {
  	 document.profile.submit();

   }
   
})();