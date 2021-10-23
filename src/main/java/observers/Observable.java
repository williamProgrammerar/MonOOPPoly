package observers;

public interface Observable {
     void attachObserver(Observer observer);


     void removeObserver(Observer observer);

     void notifyObservers(Object arg);

     void notifyObservers();
}
