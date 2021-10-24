package observers;
/**
 * @author JonEmilsson
 */
public interface Observable {
     void attachObserver(Observer observer);


     void removeObserver(Observer observer);

     void notifyObservers(Object arg);

     void notifyObservers();
}
