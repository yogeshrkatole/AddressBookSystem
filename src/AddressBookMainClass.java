import java.util.Scanner;

public class AddressBookMainClass {
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");

		Scanner scanner = new Scanner(System.in);
		AddressBook addressBook = new AddressBook();
        boolean b=true;
        while (b) { 
            System.out.println("Choose an option:");
            System.out.println("1 for Add New Contact");
            System.out.println("2 for Display All Contacts");
            System.out.println("3 for Exit");
            System.out.print("Enter option in integer like 1 or 2 ");
            
            int option = Integer.parseInt(scanner.nextLine());

            switch (option) {
                case 1:
                    addContactaddress(scanner, addressBook); 
                    break;

                case 2:
                    addressBook.showContactAddresses();
                    break;
                case 3:
                    System.out.println("Exit from AddressBook");
                    b=false;
                    break;  
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
       
	}
	
	private static void addContactaddress(Scanner scanner, AddressBook addressBook) {
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
		addressBook.addContactAddress(contactAddress);
	}
}
