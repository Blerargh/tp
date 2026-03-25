package cpp.ui;

import cpp.model.ReadOnlyAddressBook;
import cpp.model.assignment.ContactAssignment;
import cpp.model.contact.Contact;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

/**
 * Panel containing the list of contact-assignment entries for an assignment.
 */
public class ContactAssignmentListPanel extends UiPart<Region> {
    private static final String FXML = "ContactAssignmentListPanel.fxml";

    @FXML
    private ListView<ContactAssignment> contactAssignmentListView;

    private final ReadOnlyAddressBook addressBook;

    /**
     * Creates a {@code ContactAssignmentListPanel} with the given
     * {@code ObservableList}
     * of {@code ContactAssignment} and the address book for contact lookup.
     */
    public ContactAssignmentListPanel(ObservableList<ContactAssignment> cas, ReadOnlyAddressBook addressBook) {
        super(ContactAssignmentListPanel.FXML);
        this.addressBook = addressBook;
        this.contactAssignmentListView.setItems(cas);
        this.contactAssignmentListView.setCellFactory(listView -> new ContactAssignmentListViewCell());
    }

    class ContactAssignmentListViewCell extends ListCell<ContactAssignment> {
        @Override
        protected void updateItem(ContactAssignment ca, boolean empty) {
            super.updateItem(ca, empty);

            if (empty || ca == null) {
                this.setGraphic(null);
                this.setText(null);
            } else {
                Contact contact = ContactAssignmentListPanel.this.addressBook.getContactList().stream()
                        .filter(c -> c.getId().equals(ca.getContactId()))
                        .findFirst().orElse(null);
                this.setGraphic(new ContactAssignmentCard(ca, contact, this.getIndex() + 1).getRoot());
            }
        }
    }
}
