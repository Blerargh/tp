package cpp.ui;

import java.util.List;

import cpp.logic.parser.ParserUtil;
import cpp.model.ReadOnlyAddressBook;
import cpp.model.assignment.Assignment;
import cpp.model.assignment.ContactAssignment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/**
 * UI component that displays full details for a single assignment and its
 * per-contact data.
 */
public class UniqueAssignmentView extends UiPart<Region> {
    private static final String FXML = "UniqueAssignmentView.fxml";

    @FXML
    private Label assignmentName;

    @FXML
    private Label assignmentDeadline;

    @FXML
    private StackPane contactAssignmentsPlaceholder;

    private ContactAssignmentListPanel contactAssignmentListPanel;

    public UniqueAssignmentView() {
        super(UniqueAssignmentView.FXML);
    }

    public void setAssignment(Assignment assignment, List<ContactAssignment> cas, ReadOnlyAddressBook addressBook) {
        this.assignmentName.setText(assignment.getName().toString());
        this.assignmentDeadline
                .setText(assignment.getDeadline().format(ParserUtil.DATETIME_FORMATTER));

        ObservableList<ContactAssignment> observableCas = FXCollections.observableArrayList(cas);
        if (this.contactAssignmentListPanel != null) {
            this.contactAssignmentsPlaceholder.getChildren().clear();
        }
        this.contactAssignmentListPanel = new ContactAssignmentListPanel(observableCas, addressBook);
        this.contactAssignmentsPlaceholder.getChildren().add(this.contactAssignmentListPanel.getRoot());
    }
}
