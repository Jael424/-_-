package libraryAd;

import java.util.*;
/*
���� ���� Ŭ������ �����, �����ڷκ��� ������ �޾� 
����� ������ ���� ������ ���� �����ϴ� Ŭ����. ȸ���� ����
�ϴ� MemberManage Ŭ������ BookManage Ŭ������ ��ü�� ��� ������
������ ������, ȸ���� ���� ������ ���(ManageRun, MemberRun)�� ����
�Ѵ�. MemberManage Ŭ������ BookManage Ŭ������ ���� ȸ���� ������
�����ϴ� �ؽø� ������ ����Լ��� ���� �̿��ڷκ��� ������ �Է¹޾� ��
���� ������Ʈ �Ѵ�.
*/
public class TotalLibraryManage {
	MemberManage memberManage;
	BookManage bookManage;
	
	private Scanner scanner;
	
	public TotalLibraryManage() {
		memberManage = new MemberManage();
		bookManage = new BookManage();
		scanner = new Scanner(System.in);
	}
	
	public MemberManage getmemberManage() { return memberManage; }
	
	public BookManage getbookManage() { return bookManage; }

	public void ManagerRun(Person person) { // ������
		// (D)
		while(true) {
			System.out.println("-----------------------------------------------------------------");
			System.out.println("1. ��ü ���� ��� ��� | 2. ���� ��� | 3. ���� ��� �߰� | 4. ȸ�� ��� ���� | 5. ���� ");
			System.out.println("-----------------------------------------------------------------");
			System.out.print("�Է� >> ");
			int inp = scanner.nextInt();
			scanner.nextLine();
			
			switch(inp) {
				case 1:
					bookManage.printBookList();
					break;
				case 2:
					bookManage.AddBook();
					break;
				case 3:
					bookManage.UpdateBookStock();
					break;
				case 4:
					memberManage.PrintMemberList();
					break;
				default:
					break;
			}
			if(inp == 5) {
				break;
			}
		}
		//������ ���鼭 ������ ��� ����
		// 1. ��ü ���� ��� ���
		// 2. ���� ���
		// 3. ���� ��� �߰�
		// 4. ȸ�� ��� ����
		// 5. ���ư���(���� Ż��)

	}
	
	public void MemberRun(Person person) { // ȸ��
		// (E)
		Member member = (Member)person;
		while(true) {
			System.out.println("-------------------------------------------------------------");
			System.out.println("1. ��ü ���� ��� ��� | 2. ���� �뿩 | 3. ���� �ݳ� | 4. �뿩 ���� ��� | 5. ���� ");
			System.out.println("-------------------------------------------------------------");
			System.out.print("�Է� >> ");
			int inp = scanner.nextInt();
			
			switch(inp) {
				case 1:
					bookManage.printBookList();
					break;
				case 2:
					bookManage.RentalBook(member);
					break;
				case 3:
					bookManage.ReturnBook(member);
					break;
				case 4:
					member.PrintRentalList();
					break;
				default:
					break;
			}
		if(inp == 5) {
			break;
			}
		}
		//������ ���鼭 ȸ�� ��� ����
		// 1. ��ü ���� ��� ���
		// 2. ���� �뿩
		// 3. ���� �ݳ�
		// 4. �뿩 ���� ���
		// 5. ���ư���(���� Ż��)
	}
}
