import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AddressBookMainClass {
	private static Map<String, AddressBook> addressBookSystem = new HashMap<>();
	public static void main(String[] args) {
		System.out.println("Welcome to Address Book Program");

		Scanner scanner = new Scanner(System.in);
        boolean b = true;

        while (b) {
            System.out.println("Choose an option:");
            System.out.println("1 for Add a New Address Book");
            System.out.println("2 for Select Address Book");
            System.out.println("3 for Display All Address Book Names");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addNewAddressBook(scanner);
                    break;
                case 2:
                    selectAddressBook(scanner);
                    break;
                case 3:
                    displayAddressBookNames();
                    break;
                case 4:
                    System.out.println("Exit");
                    b = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();

	}
	private static void addNewAddressBook(Scanner scanner) {
        System.out.print("Enter a unique name for the new Address Book: ");
        String name = scanner.nextLine().trim();
        if (addressBookSystem.containsKey(name)) {
            System.out.println("Address Book with this name already exists. Please choose different name.");
        } else {
            AddressBook newAddressBook = new AddressBook();
            addressBookSystem.put(name, newAddressBook);
            System.out.println("Address Book '" + name + "' added successfully.");
        }
    }
	private static void selectAddressBook(Scanner scanner) {
        System.out.print("Enter the name of the Address Book you want to select: ");
        String name = scanner.nextLine().trim();
        AddressBook selectedAddressBook = addressBookSystem.get(name);

        if (selectedAddressBook != null) {
            System.out.println("You are now managing Address Book: " + name);
            manageAddressBook(scanner, selectedAddressBook);
        } else {
            System.out.println("Address Book with name '" + name + "' does not exist.");
        }
    }
	private static void displayAddressBookNames() {
        if (addressBookSystem.isEmpty()) {
            System.out.println("No Address Book available.");
        } else {
            System.out.println("Existing Address Books:");
            for (String name : addressBookSystem.keySet()) {
                System.out.println("- " + name);
            }
        }
    }
	private static void manageAddressBook(Scanner scanner, AddressBook addressBook) {
        boolean b = true;
        while (b) {
           System.out.println("Choose an option for this AddressBook:");
			System.out.println("1 for Add New Contacts");
			System.out.println("2 for Display All Contacts");
			System.out.println("3 for Edit");
			System.out.println("4 for delete");
			System.out.println("5  for Search by City or State");
			System.out.println("6 for Display Contacts by City");
	        System.out.println("7 for Display Contacts by State");
	        System.out.println("8 Display Contact Counts by City");
	        System.out.println("9 Display Contact Counts by State");
			System.out.println("10 for Exit");
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
				searchByCityOrState(scanner);
				break;
			case 6:
                System.out.print("Enter city: ");
                String city = scanner.nextLine();
                addressBook.showContactsByCity(city);
                break;
            case 7:
                System.out.print("Enter state: ");
                String state = scanner.nextLine();
                addressBook.showContactsByState(state);
                break;
            case 8:
                addressBook.displayContactCountsByCity();
                break;
            case 9:
                addressBook.displayContactCountsByState();
                break;
			case 10:
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
	
	private static void searchByCityOrState(Scanner scanner) {
	    System.out.print("Enter city or state to search: ");
	    String place = scanner.nextLine().trim(); 

	    List<ContactAddress> searchResults = new ArrayList<>();
	    for (AddressBook addressBook : addressBookSystem.values()) {
	        List<ContactAddress> filteredResults = addressBook.getContactAddresses().stream()
	                .filter(contact -> contact.getCity().equalsIgnoreCase(place) || contact.getState().equalsIgnoreCase(place))
	                .collect(Collectors.toList());
	        
	        searchResults.addAll(filteredResults);
	    }

	    if (!searchResults.isEmpty()) {
	        System.out.println("Contacts in the city or state " + place + ":");
	        for (ContactAddress contact : searchResults) {
	            contact.showInfo();
	        }
	    } else {
	        System.out.println("No contacts found in the city or state " + place);
	    }
	}



}
