import java.util.Scanner;

public class AddressBookMainClass {
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");

		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter First Name: ");
		String firstName = scanner.nextLine();

		System.out.print("Enter Last Name: ");
		String lastName = scanner.nextLine();

		System.out.print("Enter Address: ");
		String address = scanner.nextLine();

		System.out.print("Enter City: ");
		String city = scanner.nextLine();

		System.out.print("Enter State: ");
		String state = scanner.nextLine();

		System.out.print("Enter ZIP: ");
		String zip = scanner.nextLine();

		System.out.print("Enter Phone Number: ");
		String phoneNumber = scanner.nextLine();

		System.out.print("Enter Email: ");
		String email = scanner.nextLine();

		ContactAddress contactAddress = new ContactAddress(firstName, lastName, address, city, state, zip, phoneNumber,
				email);
		contactAddress.showInfo();
		scanner.close();
	}
}
