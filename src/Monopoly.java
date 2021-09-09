import javafx.application.Application;
import javafx.stage.Stage;

public class Monopoly extends Application {
    private Dice dice = new Dice();

    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("test");
        for (int i = 0; i < 10; i++) {
            dice.rollDice();
            System.out.println( "Dice 1: " + dice.getDice1() );
            System.out.println( "Dice 2: " + dice.getDice2() );
            System.out.println( "Sum of dice throws: " + dice.getSum() );
        }
    }
}
