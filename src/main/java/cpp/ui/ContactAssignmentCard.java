package cpp.ui;

import cpp.model.assignment.ContactAssignment;
import cpp.model.contact.Contact;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * UI component that displays information of a {@code ContactAssignment}.
 */
public class ContactAssignmentCard extends UiPart<Region> {

    private static final String FXML = "ContactAssignmentCard.fxml";

    public final ContactAssignment contactAssignment;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label submitted;
    @FXML
    private Label graded;

    /**
     * Creates a {@code ContactAssignmentCard} with the given
     * {@code ContactAssignment}
     * and the associated {@code Contact} for display.
     */
    public ContactAssignmentCard(ContactAssignment ca, Contact contact, int displayedIndex) {
        super(ContactAssignmentCard.FXML);
        this.contactAssignment = ca;
        this.id.setText(displayedIndex + ". ");
        this.name.setText(contact == null ? "<unknown>" : contact.getName().fullName);
        this.submitted.setText(ca.isSubmitted() ? "submitted" : "not submitted");
        this.graded.setText(ca.isGraded() ? String.format("graded (%.1f)", ca.getScore()) : "not graded");
    }
}
