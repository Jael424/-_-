package libraryAd;

import java.util.*;
//�α��� ����
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
				System.out.println("�̹� �����ϴ� ID�Դϴ�.");
				continue;
			}
			System.out.print("�̸� : ");
			String name = scanner.nextLine();
			System.out.print("��й�ȣ : ");
			String passward = scanner.nextLine();
			Member member = new Member(id, name, passward);
			memberHash.put(id, member);
			break;
		// ȸ�� ID, �̸�, ��й�ȣ�� �Է¹޾� memberHash�� �߰�
		// ������ ���鼭 ID�� �̹� �����ϸ� ���̹� �����ϴ� ID�Դϴ١� ��� ��
		// ���Է�. ���Էµ� ID�� �ߺ����� ������ ���� Ż��
		}
	}
	
	public Member Login() {
		// (L)
		System.out.print("ID : ");
		String id = scanner.nextLine();
		System.out.print("��й�ȣ : ");
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
		// ID�� �������� �ʰų� ��й�ȣ�� Ʋ�� ��� �޽��� ��� �� null ��ȯ
		// �α��� ���� �� �ش� ��� ��ü ��ȯ
	}
	
	public void PrintMemberList() {
		// (M)
		Iterator<String> k = memberHash.keySet().iterator();
		while(k.hasNext()) {
			String id = k.next();
			System.out.printf("ID : %s, �̸� : %s\n", id, memberHash.get(id).getName());
		}
		// memberHash�κ��� ȸ�� ����Ʈ ���
	}
}
