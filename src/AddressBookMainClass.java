import java.util.Scanner;

public class AddressBookMainClass {
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");

		Scanner scanner = new Scanner(System.in);
		AddressBook addressBook = new AddressBook();
		boolean b = true;
		while (b) {
			System.out.println("Choose an option:");
			System.out.println("1 for Add New Contacts");
			System.out.println("2 for Display All Contacts");
			System.out.println("3 for Edit");
			System.out.println("4 for delete");
			System.out.println("5 for Exit");
			System.out.print("Enter option in integer like 1 or 2 ");

			int option = Integer.parseInt(scanner.nextLine());

			switch (option) {
			case 1:
				addMultipleContactAddresses(scanner, addressBook);
				break;

			case 2:
				addressBook.showContactAddresses();
				break;
			case 3:
				editContactAddress(scanner, addressBook);
				break;
			case 4:
				deleteContactAddress(scanner, addressBook);
				break;
			case 5:
				System.out.println("Exit from AddressBook");
				b = false;
				break;
			default:
				System.out.println("Invalid option, please try again.");
			}
		}

	}

	private static void addContactAddress(Scanner scanner, AddressBook addressBook) {
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
	 private static void addMultipleContactAddresses(Scanner scanner, AddressBook addressBook) {
	        boolean adding = true;
	        while (adding) {
	            addContactAddress(scanner, addressBook);
	            System.out.print("Do you want to add another contact? (yes/no): ");
	            String response = scanner.nextLine().trim().toLowerCase();
	            if (!response.equals("yes")) {
	                adding = false;
	            }
	        }
	    }
	private static void editContactAddress(Scanner scanner, AddressBook addressBook) {
		System.out.println("Enter exact First and Last name which you want to edit: ");
		String fullName = scanner.nextLine();

		ContactAddress contactAddress = addressBook.getContactAddressByName(fullName);
		if (contactAddress == null) {
			System.out.println("contactAddress not exist");
			return;
		}

		System.out.println(
				"Editing ContactAddress: " + contactAddress.getFirstName() + " " + contactAddress.getLastName());

		System.out.print("Enter First Name: ");
		String firstName = scanner.nextLine();
		if (!firstName.isEmpty()) {
			contactAddress.setFirstName(firstName);
		}

		System.out.print("Enter Last Name: ");
		String lastName = scanner.nextLine();
		if (!lastName.isEmpty()) {
			contactAddress.setLastName(lastName);
		}

		System.out.print("Enter Address: ");
		String address = scanner.nextLine();
		if (!address.isEmpty()) {
			contactAddress.setAddress(address);
		}

		System.out.print("Enter City: ");
		String city = scanner.nextLine();
		if (!city.isEmpty()) {
			contactAddress.setCity(city);
		}

		System.out.print("Enter State: ");
		String state = scanner.nextLine();
		if (!state.isEmpty()) {
			contactAddress.setState(state);
		}

		System.out.print("Enter ZIP: ");
		String zip = scanner.nextLine();
		if (!zip.isEmpty()) {
			contactAddress.setZip(zip);
		}

		System.out.print("Enter Phone Number: ");
		String phoneNumber = scanner.nextLine();
		if (!phoneNumber.isEmpty()) {
			contactAddress.setPhoneNumber(phoneNumber);
		}

		System.out.print("Enter Email: ");
		String email = scanner.nextLine();
		if (!email.isEmpty()) {
			contactAddress.setEmail(email);
		}

		System.out.println("Contact updated successfully!");
	}
	private static void deleteContactAddress(Scanner scanner, AddressBook addressBook) { 
        System.out.print("Enter exact First and Last name which you want to delete: "); 
        String fullName = scanner.nextLine();

        boolean isDeleted = addressBook.deleteContactAddress(fullName); 
        if (isDeleted) {
            System.out.println("ContactAddress deleted successfully."); 
        } else {
            System.out.println("ContactAddress not exist."); 
        }
    }
}
