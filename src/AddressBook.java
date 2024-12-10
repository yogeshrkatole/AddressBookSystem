
import java.util.ArrayList;
import java.util.List;

public class AddressBook {
	private List<ContactAddress> contactAddresses;

	public AddressBook() {
		this.contactAddresses = new ArrayList<>();
	}

	public void addContactAddress(ContactAddress contactAddress) {
        if (contactAddresses.stream()
                .anyMatch(existingContact -> existingContact.equals(contactAddress))) {
            System.out.println("Duplicate contact.A person with this name already exists in the Address Book.");
        } else {
            contactAddresses.add(contactAddress);
            System.out.println("Contact added successfully!");
        }
    }

	public void showContactAddresses() {
		if (!contactAddresses.isEmpty()) {

			for (ContactAddress contactAddress : contactAddresses) {
				contactAddress.showInfo();
				System.out.println();

			}
		} else {
            System.out.println("No contacts to display.");
        }
	}

	public ContactAddress getContactAddressByName(String fullName) {
        return contactAddresses.stream()
                .filter(contact -> (contact.getFirstName() + " " + contact.getLastName()).equalsIgnoreCase(fullName))
                .findFirst()
                .orElse(null);
    }
	
	public boolean deleteContactAddress(String fullName) { 
        ContactAddress contactAddress = getContactAddressByName(fullName); 
        if (contactAddress != null) { 
            contactAddresses.remove(contactAddress); 
            return true; 
        }
        return false;
    }
}