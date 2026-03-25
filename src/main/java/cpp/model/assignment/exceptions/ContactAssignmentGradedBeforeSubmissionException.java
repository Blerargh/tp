package cpp.model.assignment.exceptions;

public class ContactAssignmentGradedBeforeSubmissionException extends RuntimeException {
    public ContactAssignmentGradedBeforeSubmissionException() {
        super("A contact's assignment cannot be graded before the time of submission");
    }

}
