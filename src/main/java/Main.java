import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int amountOfPeople; // количество человек
        while (true) {

            System.out.println("Здравствуйте! Скажите пожалуйста, на скольких человек необходимо разделить счёт?");
            amountOfPeople = scanner.nextInt();

            if (amountOfPeople == 1) {
                System.out.println("Количество человек, введённых пользователем, равно 1. В этом случае нет смысла ничего считать и делить.");
            } else if (amountOfPeople < 1) {
                System.out.print("Количество человек меньше 1. Это некорректное значение для подсчёта.");
            } else {
                break;
            }
        }
        System.out.println("Количество человек " + amountOfPeople);

        TradingCalculator tradingCalculator = new TradingCalculator(amountOfPeople);
        tradingCalculator.calculateAndDisp();
    }
}

class TradingCalculator {
    private double totalCost = 0.0; // итоговая стоимость
    private int amountOfPeople; // количество человек
    private List<String> productsList = new ArrayList<>();
    public TradingCalculator(int amountOfPeople) {
        this.amountOfPeople = amountOfPeople    ;
    }

    public void calculateAndDisp() {
        Scanner scanner = new Scanner(System.in);
        String productName;

        System.out.println("Напишите 'Завершить' для завершения подсчётов.");

        while (true) {
            System.out.println("Введите название товара:");
            productName = scanner.nextLine();
            if (productName.equalsIgnoreCase("завершить")) {
                break;
            }

            System.out.println("Введите стоимость товара: ");
            double productPrice = scanner.nextDouble();
            scanner.nextLine();

            totalCost += productPrice;
            productsList.add(productName);
            System.out.println("Товар " + productName + " добавлен");
        }


        System.out.println("\nДобавленные товары:");
        for (String product : productsList) {
            System.out.println(product);
        }
        double individualCost = totalCost / amountOfPeople;
        String formattedIndividualCost = formatter(individualCost);


        for (int i = 0; i < amountOfPeople; i++) {
            System.out.println("Гость " + (i + 1) + " должен заплатить " + formattedIndividualCost);
        }

        System.out.println("Общая стоимость всех товаров: " + totalCost);
    }
    private String formatter(double amount) {
        int rubles = (int) amount;
        int kopecks = (int) ((amount - rubles) * 100);

        String rublesText = "";
        String kopecksText = "";

        if (rubles % 10 == 1 && rubles % 100 != 11) {
            rublesText = "рубль";
        } else if ((rubles % 10 >= 2 && rubles % 10 <= 4) && (rubles % 100 < 10 || rubles % 100 >= 20)) {
            rublesText = "рубля";
        } else {
            rublesText = "рублей";
        }
        if (kopecks % 10 == 1 && kopecks % 100 != 11) {
            kopecksText = "копейка";
        } else if ((kopecks % 10 >= 2 && kopecks % 10 <= 4) && (kopecks % 100 < 10 || kopecks % 100 >= 20)) {
            kopecksText = "копейки";
        } else {
            kopecksText = "копеек";
        }

        return rubles + " " + rublesText + " " + kopecks + " " + kopecksText;
    }
}
