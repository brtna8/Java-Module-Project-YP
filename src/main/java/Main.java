import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int amountOfPeople = 0;

        while (true) {
            System.out.println("Здравствуйте! Скажите пожалуйста, на скольких человек необходимо разделить счёт?");
            try {
                amountOfPeople = Integer.parseInt(scanner.nextLine());

                if (amountOfPeople == 1) {
                    System.out.println("Количество человек, введённых пользователем, равно 1. В этом случае нет смысла ничего считать и делить.");
                } else if (amountOfPeople < 1) {
                    System.out.print("Количество человек меньше 1. Это некорректное значение для подсчёта. ");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод. Пожалуйста, введите целое число.");
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
        this.amountOfPeople = amountOfPeople;
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

            double productPrice = 0.0;
            while (true) {
                try {
                    System.out.println("Введите стоимость товара: ");
                    productPrice = Double.parseDouble(scanner.nextLine());

                    if (productPrice < 0) {
                        System.out.println("Стоймость товара не может быть отрицательной.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Некорректный ввод. Пожалуйста, введите число.");
                }
            }

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

        String rublesText = (rubles % 10 == 1 && rubles % 100 != 11) ? "рубль" : ((rubles % 10 >= 2 && rubles % 10 <= 4) && (rubles % 100 < 10 || rubles % 100 >= 20)) ? "рубля" : "рублей";
        String kopecksText = (kopecks % 10 == 1 && kopecks % 100 != 11) ? "копейка" : ((kopecks % 10 >= 2 && kopecks % 10 <= 4) && (kopecks % 100 < 10 || kopecks % 100 >= 20)) ? "копейки" : "копеек";

        return rubles + " " + rublesText + " " + kopecks + " " + kopecksText;
    }
}