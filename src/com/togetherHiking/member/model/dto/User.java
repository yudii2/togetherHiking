package com.togetherHiking.member.model.dto;


//Builder 패턴 학습용 클래스
public class User {

	//객체 생성 패턴
	//객체의 속성을 초기화 하고 생성하는 디자인패턴

	//1. 점층적 생성자 패턴
	// 생성자의 매개변수를 통해 객체의 속성을 초기화하고 생성하는 패턴
	// 단점 : 코드만 보고 생성자의 매개변수가 어떤 객체를 초기화하는지 알기 어렵다. 가독성이 좋지 않다.
	
	//2. 자바빈 패턴
	// getter/setter
	// 단점 : public 메서드인 setter를 통해 속성을 초기화 하기 때문에, 변경불가능한 객체(immutable)를 만들 수 없다.
	
	//3. 빌더패턴
	// Effective Java 책에 나온Builder패턴
	private String userId;
	private String password;
	private String email;
	private String tell;
	
	private User(UserBuilder builder) {
		this.userId = builder.userId;
		this.password = builder.password;
		this.email = builder.email;
		this.tell = builder.tell;
	}
	
	public static UserBuilder builder() {
		return new UserBuilder();
	}
	
	//생성될 User객체의 속성을 초기화하기 위한 값을 전달 받아, 해당 값으로 속성을 초기화하고 User 인스턴스를 반환할 inner Class
	public static class UserBuilder{
		private String userId;
		private String password;
		private String email;
		private String tell;
		
		public UserBuilder userId(String userId) {
			this.userId = userId;
			return this;
		}
		
		public UserBuilder password(String password) {
			this.password = password;
			return this;
		}
		
		public UserBuilder email(String email) {
			this.email = email;
			return this;
		}
		
		public UserBuilder tell(String tell) {
			this.tell = tell;
			return this;
		}
		
		public User build() {
			return new User(this);
		}
	}

	
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", email=" + email + ", tell=" + tell + "]";
	}
	
	
	
}
