let boardList = "<c:out value='${boardList}'/>";

let renderBoard = (boardList)=>{
	// 이미 그려졌던 div를 초기화
	document.querySelectorAll('tbody>tr').forEach(e=>{
		e.remove();
	});
	
	// 배열의 값을 div에 다시 그려주기
	boardList.forEach(board=>{
		let tr = document.createElement('tr'); // CSS 스타일 적용을 위해 div 사용
		tr.style.height= '30px';
		tr.style.lineHeight= '30px';
		
		let td = document.createElement('td');
		td.innerHTML = board.getBdIdx();
		tr.append(td);
		
		td.innerHTML = board.getSubject();
		tr.append(td);
		
		td.innerHTML = board.getTitle();
		tr.append(td);
		
		td.innerHTML = board.getUserId();
		tr.append(td);

		td.innerHTML = board.getRegDate();
		tr.append(td);
		
	});
	
}

let renderPagination = (event)=>{
	let dir = Number(event.target.dataset.dir);
	let curPage = Number(document.querySelector('#currentPage').textContent);
	let lastPage = 1;
	let renderPage = curPage + dir;
	
	let boardList = "<c:out value='${boardList}'/>";
	
	if(boardList != null){
		let boardCnt = boardList.size;
		lastPage = Math.ceil(boardCnt/10); // ceil: 올림처리
	}
	
	if(renderPage > lastPage){
		alert('마지막 페이지입니다.');
		return;
	}
	
	if(renderPage < 1){
		alert('첫 페이지입니다.');
		return;
	}
	
	let end = renderPage * 10;
	let begin = end - 10;
	
	renderBoard(boardList.slice(begin,end));
	document.querySelector('#currentPage').textContent = renderPage;
}

(()=>{
	document.querySelector('#leftArrow').addEventListener('click',renderPagination);
	document.querySelector('#rightArrow').addEventListener('click',renderPagination);
	
	let boardList = boardList;
	
	if(boardList){
		renderBoard(boardList.slice(0,10));
	}
	
})();