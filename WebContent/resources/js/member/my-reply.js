(()=>{
	  
   document.querySelector('#tab_reply').addEventListener('click',(e) => {
	   e.preventDefault();
	   
	   document.querySelector('.title').innerHTML = '댓글';
	   document.querySelector('.views').innerHTML = '답글';
	   document.querySelector('#tab_post').style.border = '1px solid #bbb'
	   document.querySelector('#tab_post').style.backgroundColor = '#fff';
	   document.querySelector('#tab_post>a').style.color = '#555555';

	   document.querySelector('#tab_reply').style.backgroundColor = 'var(--point-color)';
	   document.querySelector('#tab_reply').style.border = 'none';
	   document.querySelector('#tab_reply').style.color = '#fff';

   });
   
   

   /* 전체선택 */
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
   
   /* 댓글 삭제 */
   document.querySelector('#btn_del').addEventListener('click',(btn) => {
	   btn.preventDefault();
	   
	   let chkArr = [];
	   document.querySelectorAll('.checkbox').forEach((chk) => {
		   if(chk.checked == true){
			   chkArr.push(chk);
		   } 
	   });
	   if(chkArr.length > 1){
		   alert("선택하신 댓글을 모두 삭제하시겠습니까?");
	   }else if(chkArr.length == 0){
		   alert("삭제할 댓글을 선택해 주세요.");   
	   }
	   

		document.deleteReply.submit();
	   

   });

})();
  