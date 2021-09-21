package com.togetherHiking.common.code.member;

//enum(enumerated type) : 열거형
//서로 연관된 상수들의 집합
//서로 연관된 상수들을 하나의 묶음으로 다루기 위한 enum만의 문법적 형식과 메서드를 제공해준다.
public enum MemberGrade {
	
	//회원등급코드가 'ME00'이면 등급명은 '일반'이고 연장가능횟수 1회
	//내부적으로 enum은 class이다.
	//ME00("일반",1) -> public static final MemberGrade ME00 = new MemberGrade("일반",1);
	//public이기 때문에 어디에서나 접근이 가능하고, static이기 때문에 언제나 접근이 가능한 인스턴스에
	//등급명과 연장횟수를 저장해두고 getter를 통해서 반환받아 사용한다.
	
	//회원등급코드가 'NORMAL'이면 등급명은 일반 -> 글쓰기(board, schedule)
	
	//				 'HOST'이면 등급명은 호스트 -> 수정, 삭제(schedule) : 인당 호스팅은 한번! 글쓰기 권한x
	//				 'ADMIN'이면 등급명은 관리자 -> 전체 게시글 관리, 전체 모임 관리(수정은x, 삭제만o), 스케줄 승인 & 등록
	//						*** ADMIN관리자 페이지 별도 필요 *** 
	
	NORMAL("일반","user"),		//member접근 o	,admin x 
	HOST("주최자","host"),		//board-form접근o, admin x
	ADMIN("관리자","admin");		//admin접근o
	
	public final String DESC;
	public final String ROLE;
	
	private MemberGrade(String desc, String role) {
		this.DESC = desc;
		this.ROLE = role;

	}
	

}
