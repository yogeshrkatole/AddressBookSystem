
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.util.Collections;
import java.util.Comparator;

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
	
    public void displayContactsSortedByName() {
        if (contactAddresses.isEmpty()) {
            System.out.println("No contacts to display.");
            return;
        }

        contactAddresses.stream()
            .sorted(Comparator.comparing(contact -> (contact.getFirstName() + " " + contact.getLastName())))
            .forEach(System.out::println);
    }
    
    public void displayContactsSortedByCity() {
        List<ContactAddress> sortedByCity = contactAddresses.stream()
            .sorted(Comparator.comparing(ContactAddress::getCity))
            .collect(Collectors.toList());
        sortedByCity.forEach(System.out::println);
    }

    public void displayContactsSortedByState() {
        List<ContactAddress> sortedByState = contactAddresses.stream()
            .sorted(Comparator.comparing(ContactAddress::getState))
            .collect(Collectors.toList());
        sortedByState.forEach(System.out::println);
    }

    public void displayContactsSortedByZipCode() {
        List<ContactAddress> sortedByZipCode = contactAddresses.stream()
            .sorted(Comparator.comparing(ContactAddress::getZip))
            .collect(Collectors.toList());
        sortedByZipCode.forEach(System.out::println);
    }
    
    public void saveToFile(String filename) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename))) {
            os.writeObject(contactAddresses);
            System.out.println("Contacts have been saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving contacts: " + e.getMessage());
        }
    }
    
	public void loadContactsFromFile(String filename) {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
			List<ContactAddress> loadContactAddressesFromFile = (List<ContactAddress>) ois.readObject();
			System.out.println("Contacts have been loaded from " + filename);
			if (loadContactAddressesFromFile != null && !loadContactAddressesFromFile.isEmpty()) {
				for (ContactAddress contactAddress : loadContactAddressesFromFile) {
					contactAddress.showInfo();
					System.out.println();
				}
			} else {
				System.out.println("No contacts to display.");
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println("Error loading contacts: " + e.getMessage());
		}
	}
	
	public void saveToCSV(String filename) {
	    try (CSVWriter writer = new CSVWriter(new FileWriter(filename))) {
	        // Header row
	        String[] header = {"First Name", "Last Name", "Address", "City", "State", "ZIP", "Phone", "Email"};
	        writer.writeNext(header);

	        // Data rows
	        for (ContactAddress contact : contactAddresses) {
	            String[] row = {
	                contact.getFirstName(),
	                contact.getLastName(),
	                contact.getAddress(),
	                contact.getCity(),
	                contact.getState(),
	                contact.getZip(),
	                contact.getPhoneNumber(),
	                contact.getEmail()
	            };
	            writer.writeNext(row);
	        }
	        System.out.println("Contacts have been saved to " + filename);
	    } catch (IOException e) {
	        System.err.println("Error saving contacts to CSV: " + e.getMessage());
	    }
	}

	public void loadFromCSV(String filename) {
	    try (CSVReader reader = new CSVReader(new FileReader(filename))) {
	        List<String[]> allData = reader.readAll();
	        contactAddresses.clear();

	        for (int i = 1; i < allData.size(); i++) { // Assuming the first row is headers
	            String[] row = allData.get(i);
	            ContactAddress contact = new ContactAddress(row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]);
	            contactAddresses.add(contact);
	        }

	        System.out.println("Contacts have been loaded from " + filename);
	        System.out.println("Loaded Contacts:");

	      
	        for (ContactAddress contact : contactAddresses) {
	            contact.showInfo(); 
	        }
	    } catch (IOException | CsvException e) {
	        System.err.println("Error loading contacts from CSV: " + e.getMessage());
	    }
	}

}