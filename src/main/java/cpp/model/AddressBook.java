package cpp.model;

import java.util.List;
import java.util.Objects;

import cpp.commons.util.ToStringBuilder;
import cpp.model.assignment.Assignment;
import cpp.model.assignment.UniqueAssignmentList;
import cpp.model.classgroup.ClassGroup;
import cpp.model.classgroup.UniqueClassGroupList;
import cpp.model.person.Person;
import cpp.model.person.UniquePersonList;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueAssignmentList assignments;
    private final UniqueClassGroupList classGroups;

    /*
     * The 'unusual' code block below is a non-static initialization block,
     * sometimes used to avoid duplication
     * between constructors. See
     * https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other
     * ways to avoid duplication among constructors.
     */
    {
        this.persons = new UniquePersonList();
        this.assignments = new UniqueAssignmentList();
        this.classGroups = new UniqueClassGroupList();
    }

    public AddressBook() {
    }

    /**
     * Creates an AddressBook using the Persons in the {@code toBeCopied}
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        this.resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the assignment list with {@code assignments}.
     * {@code assignments} must not contain duplicate assignments.
     */
    public void setAssignments(List<Assignment> assignments) {
        this.assignments.setAssignments(assignments);
    }

    /**
     * Replaces the contents of the class group list with {@code classGroups}
     * {@code classGroups} must not contain duplicate class groups
     */
    public void setClassGroups(List<ClassGroup> classGroups) {
        this.classGroups.setClassGroups(classGroups);
    }

    /**
     * Resets the existing data of this {@code AddressBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAddressBook newData) {
        Objects.requireNonNull(newData);

        this.setPersons(newData.getPersonList());
        this.setAssignments(newData.getAssignmentList());
        this.setClassGroups(newData.getClassGroupList());
        // class groups
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    public boolean hasPerson(Person person) {
        Objects.requireNonNull(person);
        return this.persons.contains(person);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Person p) {
        this.persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with
     * {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another
     * existing person in the address book.
     */
    public void setPerson(Person target, Person editedPerson) {
        Objects.requireNonNull(editedPerson);
        this.persons.setPerson(target, editedPerson);
    }

    /**
     * Removes {@code key} from this {@code AddressBook}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Person key) {
        this.persons.remove(key);
    }

    //// assignment level operations
    /**
     * Returns true if an assignment with the same identity as {@code assignment}
     * exists in the address book.
     */
    public boolean hasAssignment(Assignment assignment) {
        Objects.requireNonNull(assignment);
        return this.assignments.contains(assignment);
    }

    /**
     * Adds an assignment to the address book.
     * The assignment must not already exist in the address book.
     */
    public void addAssignment(Assignment assignment) {
        Objects.requireNonNull(assignment);
        this.assignments.add(assignment);
    }

    /**
     * Returns true if a class group with the same identity as {@code classGroup}
     * exists in the address book.
     */
    public boolean hasClassGroup(ClassGroup classGroup) {
        Objects.requireNonNull(classGroup);
        return this.classGroups.contains(classGroup);
    }

    /**
     * Adds a class group to the address book.
     * The class group must not already exist in the address book.
     */
    public void addClassGroup(ClassGroup classGroup) {
        Objects.requireNonNull(classGroup);
        this.classGroups.add(classGroup);
    }

    /**
     * Replaces the given class group {@code target} in the list with
     * {@code editedClassGroup}.
     *
     * {@code target} must exist in the address book.
     * The class group identity of {@code editedClassGroup} must not be the same as
     * another existing class group in the address book.
     */
    public void setClassGroup(ClassGroup target, ClassGroup editedClassGroup) {
        Objects.requireNonNull(editedClassGroup);
        this.classGroups.setClassGroup(target, editedClassGroup);
    }

    //// util methods

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", this.persons)
                .add("assignments", this.assignments)
                .add("classGroups", this.classGroups)
                .toString();
    }

    @Override
    public ObservableList<Person> getPersonList() {
        return this.persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Assignment> getAssignmentList() {
        return this.assignments.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<ClassGroup> getClassGroupList() {
        return this.classGroups.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressBook)) {
            return false;
        }

        AddressBook otherAddressBook = (AddressBook) other;
        return this.persons.equals(otherAddressBook.persons) && this.assignments.equals(otherAddressBook.assignments)
                && this.classGroups.equals(otherAddressBook.classGroups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.persons, this.assignments, this.classGroups);
    }
}
