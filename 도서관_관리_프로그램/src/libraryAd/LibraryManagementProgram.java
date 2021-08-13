package libraryAd;

import java.util.*;
/* 
���� ���� ���� Ŭ����. ���� �޴���
�������� ������ ���� ȸ�� ���� ������ ���� �α��� �� �� �ش��ϴ� ��
���� ������ �� �ִ�. �����ڴ� ������ ȸ�� ������ �ʿ�� ���� ������ �̸�
������(pre-dened) ������ ������ �Է��� ID, ��й�ȣ�� ��ġ�ϸ� �α���
�� �� �ִ� 
*/
public class LibraryManagementProgram {
	private static Scanner scanner = new Scanner(System.in);
	private static final String managerID = "0000";
	private static final String managerName = "ȫ�浿";
	private static final String managerPWD = "1234";
	static Manager manager = new Manager(managerID, managerName, managerPWD);
	private static TotalLibraryManage libManager = new TotalLibraryManage();
	static Person currentPerson = null;
	
	public static void main(String[] args) {
		// (N)
	while (true) {
		printMainMenu();
		System.out.print("�Է� >> ");
		int selectNum = scanner.nextInt();
		scanner.nextLine();
		if (selectNum == 1) { // ������ ���
			System.out.println("-------������ ����Դϴ�. �α��� �Ͻʽÿ�.---------------");
			System.out.print("ID : ");
			String id = scanner.nextLine();
			
			System.out.print("��й�ȣ : ");
			String pw = scanner.nextLine();
			if(id.equals(managerID) == false || pw.equals(managerPWD) == false) {
				System.out.println("\n������ ���̵� Ȥ�� ��й�ȣ�� ��ġ���� �ʾ� �ʱ� ȭ������ ���ư��ϴ�.\n");
				continue;
			}
			// �α��� �� �̿� ����. ������ ��ġ���� ������ �ʱ� �޴���...
			while(true) {
				printManagerMenu();
				System.out.print("<����> ");
				int inp = scanner.nextInt();
				scanner.nextLine();
				if(inp == 1) {
					currentPerson = manager;
					libManager.ManagerRun(currentPerson);
				}
				else if(inp == 2) {
					currentPerson = null;
					break;
				}
				// 1. ���� ����
				// 2. Logout (currentPerson = null; �� break;)
			}
		}  else if (selectNum == 2) { // ȸ�� �޴�
			while(true) {
				printMemberMenu();
				System.out.print("<����> ");
				int inp = scanner.nextInt();
				scanner.nextLine();
				if(inp == 1) {
					libManager.getmemberManage().signup();
					continue;
				}
				else if (inp == 2) {
					currentPerson = libManager.getmemberManage().Login();
					if(currentPerson == null) {
						System.out.println("���� ���̵��̰ų� ��й�ȣ�� Ʋ�Ƚ��ϴ�.");
						continue;
					}
					libManager.MemberRun(currentPerson);
					continue;
				}
				else if (inp == 3) {
					System.out.println("�α��� �ؾ� �̿��� �� �ֽ��ϴ�.");
					continue;
				}
				else if (inp == 4) {
				currentPerson = null;
				break;
				}
				else {
					continue;
				}
				// 1. ȸ�� ����
				// 2. �α���
				// 3. ���� ����(�α��� �� �̿� ���� ����)
				// 4. Logout (currentPerson = null; �� break;)
			}
		} else if (selectNum == 3) { // ����
			System.out.println("���α׷��� �����մϴ�.");
			break;
		}

	}
	}
	public static void printMainMenu() {
		// (O)
		System.out.println("------���ϴ��б� ��ǻ�� ���а� ������ ���� ���α׷��Դϴ�.---------");
		System.out.println("1. ������ �޴� | 2. ȸ�� �޴� | 3. ����");
		System.out.println("------------------------------------------------------");
		// ���� �޴� �ۼ�
	}
	
	public static void printManagerMenu() {
		// (P)
		System.out.println("----------------������ ���----------------");
		System.out.println("1. ���� ���� | 2. �α׾ƿ�" );
		System.out.println("----------------------------------------");
		// ������ �޴� �ۼ�
	}
	
	public static void printMemberMenu() {
		// (Q)
		System.out.println("--------------------------ȸ�� ���--------------------------");
		System.out.println("1. ȸ������ | 2. �α��� | 3. ���� ���� | 4. �α׾ƿ�" );
		System.out.println("-----------------------------------------------------------");
		// ȸ�� �޴� �ۼ�
	}
}
