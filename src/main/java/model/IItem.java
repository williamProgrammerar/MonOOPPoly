package model;

/**
 * An interface for items of the TradeController.
 *
 * @param <T> The owner of the item, for example a player or a property
 */
public interface IItem<T> {
    public String getName();

    /**
     * Returns an item of type T.
     * @return item.
     */
    public T getItemOwner();
}
