
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
}