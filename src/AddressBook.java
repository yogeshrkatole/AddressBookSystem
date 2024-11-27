
import java.util.ArrayList;
import java.util.List;

public class AddressBook {
	private List<ContactAddress> contactAddresses;

	public AddressBook() {
		this.contactAddresses = new ArrayList<>();
	}

	public void addContactAddress(ContactAddress contactAddress) {
		contactAddresses.add(contactAddress);
		System.out.println("ContactAddress  added successfully!");
	}

	public void showContactAddresses() {
		if (!contactAddresses.isEmpty()) {

			for (ContactAddress contactAddress : contactAddresses) {
				contactAddress.showInfo();
				System.out.println();

			}
		}
	}

	public ContactAddress getContactAddressByName(String fullName) {
		for (ContactAddress contactAddress : contactAddresses) {
			if ((contactAddress.getFirstName() + " " + contactAddress.getLastName()).equalsIgnoreCase(fullName)) {
				return contactAddress;
			}
		}
		return null;
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