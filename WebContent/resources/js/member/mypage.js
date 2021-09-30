   
   document.querySelector('#selectAll').addEventListener('click', (e) => {
	   if(e.target.checked == true){
		   document.querySelectorAll('.checkbox').forEach((chk) => {
			   chk.checked = true;
		   })
	   }else{
		   document.querySelectorAll('.checkbox').forEach((chk) => {
			   chk.checked = false;
		   })		   
	   }
   });
  

   
   document.querySelector('#btn_del').addEventListener('click',(btn) => {
	   btn.preventDefault();
	   
	   let chkArr = [];
	   document.querySelectorAll('.checkbox').forEach((chk) => {
		   if(chk.checked == true){
			   chkArr.push(chk);
		   } 
	   });
	   if(chkArr.length > 1){
		   alert("선택하신 게시글을 모두 삭제하시겠습니까?");
	   }else if(chkArr.length == 0){
		   alert("삭제할 게시글을 선택해 주세요.");   
	   }
	   

		document.deletePost.submit();
	   

   });