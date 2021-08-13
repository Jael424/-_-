package libraryAd;

import java.util.*;
//로그인 관련
public class MemberManage {
	private Scanner scanner;
	private HashMap<String, Member> memberHash;
	public MemberManage() {
		this.scanner = new Scanner(System.in);
		this.memberHash = new HashMap<String, Member>();
	}
	
	public void signup() {
		// (K)
		while(true) {
			System.out.print("ID : ");
			String id = scanner.next();
			scanner.nextLine();
			if(memberHash.containsKey(id)) {
				System.out.println("이미 존재하는 ID입니다.");
				continue;
			}
			System.out.print("이름 : ");
			String name = scanner.nextLine();
			System.out.print("비밀번호 : ");
			String passward = scanner.nextLine();
			Member member = new Member(id, name, passward);
			memberHash.put(id, member);
			break;
		// 회원 ID, 이름, 비밀번호를 입력받아 memberHash에 추가
		// 루프를 돌면서 ID가 이미 존재하면 “이미 존재하는 ID입니다” 출력 후
		// 재입력. 재입력된 ID가 중복되지 않으면 루프 탈출
		}
	}
	
	public Member Login() {
		// (L)
		System.out.print("ID : ");
		String id = scanner.nextLine();
		System.out.print("비밀번호 : ");
		String passward = scanner.nextLine();
		
		if(memberHash.containsKey(id) == false) {
			return null;
		}
		
		if(memberHash.get(id).getPassward().equals(passward)) {
			return memberHash.get(id);
		}
		else {
			return null;
		}             
		// ID가 존재하지 않거나 비밀번호가 틀릴 경우 메시지 출력 후 null 반환
		// 로그인 성공 시 해당 멤버 객체 반환
	}
	
	public void PrintMemberList() {
		// (M)
		Iterator<String> k = memberHash.keySet().iterator();
		while(k.hasNext()) {
			String id = k.next();
			System.out.printf("ID : %s, 이름 : %s\n", id, memberHash.get(id).getName());
		}
		// memberHash로부터 회원 리스트 출력
	}
}
