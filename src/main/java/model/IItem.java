package model;

public interface IItem<T> {
    public String getName();

    public T getItemOwner();
}
