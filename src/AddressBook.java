
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collections;
public class AddressBook {
	private List<ContactAddress> contactAddresses;
	private Map<String, List<ContactAddress>> cityDictionary;
    private Map<String, List<ContactAddress>> stateDictionary;


	public AddressBook() {
		this.contactAddresses = new ArrayList<>();
		this.cityDictionary = new HashMap<>();
        this.stateDictionary = new HashMap<>();
	}

	public void addContactAddress(ContactAddress contactAddress) {
        if (contactAddresses.stream()
                .anyMatch(existingContact -> existingContact.equals(contactAddress))) {
            System.out.println("Duplicate contact. A person with this name already exists in the Address Book.");
        } else {
            contactAddresses.add(contactAddress);
            updateDictionaries(contactAddress);
            System.out.println("Contact added successfully!");
        }
    }

	private void updateDictionaries(ContactAddress contactAddress) {
	    List<ContactAddress> cityContacts = cityDictionary.get(contactAddress.getCity());
	    if (cityContacts == null) {
	        cityContacts = new ArrayList<>();
	        cityDictionary.put(contactAddress.getCity(), cityContacts);
	    }
	    cityContacts.add(contactAddress);

	    List<ContactAddress> stateContacts = stateDictionary.get(contactAddress.getState());
	    if (stateContacts == null) {
	        stateContacts = new ArrayList<>();
	        stateDictionary.put(contactAddress.getState(), stateContacts);
	    }
	    stateContacts.add(contactAddress);
	}
	
	private void removeFromDictionaries(ContactAddress contactAddress) {
        cityDictionary.getOrDefault(contactAddress.getCity(), new ArrayList<>()).remove(contactAddress);
        stateDictionary.getOrDefault(contactAddress.getState(), new ArrayList<>()).remove(contactAddress);
    }
	public void showContactsByCity(String city) {
        cityDictionary.getOrDefault(city, Collections.emptyList())
                .forEach(ContactAddress::showInfo);
    }

    public void showContactsByState(String state) {
        stateDictionary.getOrDefault(state, Collections.emptyList())
                .forEach(ContactAddress::showInfo);
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
	
	public List<ContactAddress> getContactAddresses() {
        return contactAddresses;
    }
	
	public boolean deleteContactAddress(String fullName) { 
        ContactAddress contactAddress = getContactAddressByName(fullName); 
        if (contactAddress != null) { 
            contactAddresses.remove(contactAddress); 
            return true; 
        }
        return false;
    }
	
	public Map<String, Long> countContactsByCity() {
        return contactAddresses.stream()
            .collect(Collectors.groupingBy(ContactAddress::getCity, Collectors.counting()));
    }

    public Map<String, Long> countContactsByState() {
        return contactAddresses.stream()
            .collect(Collectors.groupingBy(ContactAddress::getState, Collectors.counting()));
    }

    public void displayContactCountsByCity() {
        Map<String, Long> countsByCity = countContactsByCity();
        if (countsByCity.isEmpty()) {
            System.out.println("No contacts available to display counts by city.");
        } else {
            countsByCity.forEach((city, count) ->
                System.out.println("City: " + city + ", Number of Contacts: " + count));
        }
    }

    public void displayContactCountsByState() {
        Map<String, Long> countsByState = countContactsByState();
        if (countsByState.isEmpty()) {
            System.out.println("No contacts available to display counts by state.");
        } else {
            countsByState.forEach((state, count) ->
                System.out.println("State: " + state + ", Number of Contacts: " + count));
        }
    }
	
}