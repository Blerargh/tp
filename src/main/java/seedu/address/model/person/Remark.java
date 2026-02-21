package seedu.address.model.person;

/**
 * Represents a Person's remark in the address book.
 */
public class Remark {
    /*
     * The first character of the remark must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public final String value;

    /**
     * Constructs an {@code Remark}.
     *
     * @param remark A valid remark.
     */
    public Remark(String remark) {
        this.value = remark;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Remark)) {
            return false;
        }

        Remark otherRemark = (Remark) other;
        return this.value.equals(otherRemark.value);
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
