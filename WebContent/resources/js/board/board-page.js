/*
let renderPagination = (event)=>{
	let dir = Number(event.target.dataset.dir); // 형변환?
	let curPage = Number(document.querySelector('#currentPage').textContent);
	let lastPage = 1;
	let renderPage = curPage + dir;
	let boardList = ${datas.board};
	
	if(boardList){
		boardList = JSON.parse(boardList);
		let boardCnt = boardList.length;
		lastPage = Math.ceil(boardCnt/8); // ceil: 올림처리
	}
	
	if(renderPage > lastPage){
		alert('마지막 페이지입니다.');
		return;
	}
	
	if(renderPage < 1){
		alert('첫 페이지입니다.');
		return;
	}
	
	let end = renderPage * 8;
	let begin = end - 8;
	
	renderBoard(boardList.slice(begin,end));
	document.querySelector('#currentPage').textContent = renderPage;
};

(()=>{
	document.querySelectorAll(".far").addEventListener("click",renderPagination);
})();*/
