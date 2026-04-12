package cpp.model.classgroup;

import java.util.Objects;

import cpp.commons.util.AppUtil;

/**
 * Represents a Class Group's name in the class group list.
 */
public class ClassGroupName {

    public static final String MESSAGE_CONSTRAINTS = """
            Names should only contain alphanumeric characters and spaces. Special characters such as hyphens (-), forward slashes (/), and parentheses () are allowed to support naming conventions like hyphenated names and ethnic names in brackets. The name should not be blank""";

    /*
     * Names must:
     * - Start with an alphanumeric character
     * - Contain only alphanumeric, spaces, hyphens (between alphanumeric),
     * parentheses (with content), and s/o, d/o patterns
     * - Hyphens must be between two alphanumeric characters (not at start/end)
     * - Slashes only allowed in patterns: s/o, S/O, d/o, D/O
     * - Parentheses must contain at least one alphanumeric character
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}]([\\p{Alnum} ]|(?<=[sSdD])/[oO]|-(?=[\\p{Alnum}])|\\([\\p{Alnum} ]*[\\p{Alnum}][\\p{Alnum} ]*\\))*";

    public final String fullName;

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public ClassGroupName(String name) {
        Objects.requireNonNull(name);
        AppUtil.checkArgument(ClassGroupName.isValidName(name), ClassGroupName.MESSAGE_CONSTRAINTS);
        this.fullName = name;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidName(String test) {
        return test.matches(ClassGroupName.VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.fullName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClassGroupName)) {
            return false;
        }

        ClassGroupName otherName = (ClassGroupName) other;
        return this.fullName.toLowerCase().equals(otherName.fullName.toLowerCase());
    }

    @Override
    public int hashCode() {
        return this.fullName.toLowerCase().hashCode();
    }

}
