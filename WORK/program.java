/*
Подумать над структурой класса Ноутбук для магазина техники - выделить 
поля и методы. Реализовать в java.
Создать множество ноутбуков.
Написать метод, который будет запрашивать у пользователя критерий 
(или критерии) фильтрации и выведет ноутбуки, отвечающие фильтру. 
Критерии фильтрации можно хранить в Map. Например: “Введите цифру, 
соответствующую необходимому критерию:

1 - ОЗУ
2 - Объем ЖД
3 - Операционная система
4 - Цвет …

Далее нужно запросить минимальные значения для указанных критериев - 
сохранить параметры фильтрации можно также в Map.
Отфильтровать ноутбуки из первоначального множества и вывести
проходящие по условиям. 
*/

package WORK;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * program
 */
public class program {
    public static void main(String[] args) {
        Set<notebook> catalog = new HashSet<>();
        catalog.add(new notebook("Xiaomi", "mi notebook pro 15.6", 16, 512, "Windows 10",
                "СЕРЫЙ", 140000.0));
        catalog.add(new notebook("Xiaomi", "reedbook 14", 8, 256, "Windows 10",
                "СЕРЫЙ", 80000.0));
        catalog.add(new notebook("Apple", "MacBook Air 13", 8, 256, "macOS",
                "РОЗОВЫЙ", 120000.0));
        catalog.add(new notebook("Apple", "MacBook Air 15", 16, 512, "macOS",
                "СЕРЕБРО", 245000.0));
        catalog.add(new notebook("Lenovo", "Legion", 64, 1024, "Windows 11",
                "чёрный", 130000.0));
        catalog.add(new notebook("Razer", "Blade 18", 64, 1024, "Windows 11",
                "чёрный", 300000.0));

        mainMenu(catalog);

    }

    static void mainMenu(Set<notebook> catalog) {
        boolean flag = true;
        String menu = "1 - Производитель\n" +
                "2 - Объем ОЗУ\n" +
                "3 - Объем ЖД\n" +
                "4 - Операционная система\n" +
                "5 - Цвет\n" +
                "6 - Поиск\n" +
                "7 - Очистить запрос\n" +
                "8 - Выход\n";
        Map<String, String> request = new TreeMap<>();
        while (flag) {
            System.out.println(menu);
            System.out.println("ВВОД: " + request);
            String key;
            Scanner scanner = new Scanner(System.in);
            key = scanner.nextLine();
            switch (key) {
                case "1":
                    String text = inputMenu("Введите производителя: ");
                    request.put("Производитель", text);
                    break;
                case "2":
                    request.put("ОЗУ", inputMenu("Введите минимальный объём ОЗУ: "));
                    break;
                case "3":
                    request.put("ЖД", inputMenu("Введите минимальный объём ЖД: "));
                    break;
                case "4":
                    request.put("ОС", selectOSMenu());
                    break;
                case "5":
                    request.put("Цвет", selectColorMenu());
                    break;
                case "6":
                    showResultRequest(request, catalog);
                    break;
                case "7":
                    request.clear();
                    break;
                case "8":
                    return;

                default:
                    break;
            }
        }
    }

    static String inputMenu(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(text);
        String result = scanner.nextLine();
        return result;
    }

    static String selectOSMenu() {
        String menu = "1 - Windows 10\n" +
                "2 - Windows 11\n" +
                "3 - macOS\n";
        Scanner scanner = new Scanner(System.in);
        System.out.println(menu);
        String key = scanner.nextLine();
        switch (key) {
            case "1":
                return "Windows 10";
            case "2":
                return "Windows 11";
            case "3":
                return "macOS";

            default:
                return "ДРУГОЕ";
        }
    }

    static String selectColorMenu() {
        String menu = "1 - белый\n" +
                "2 - чёрный\n" +
                "3 - серый\n" +
                "4 - синий\n" +
                "5 - красный\n";
        Scanner scanner = new Scanner(System.in);
        System.out.println(menu);
        String key = scanner.nextLine();
        switch (key) {
            case "1":
                return "белый";
            case "2":
                return "чёрный";
            case "3":
                return "серый";
            case "4":
                return "синий";
    
            case "5":
                return "розовый";

            default:
                return "другой";
        }
    }

    static void showResultRequest(Map<String, String> request, Set<notebook> catalog) {
        Set<notebook> result = new HashSet<>();
        for (notebook notebook : catalog) {
            boolean check = true;
            if (request.keySet().contains("ПРОИЗВОДИТЕЛЬ")) {
                check &= notebook.getMaker().toLowerCase()
                        .equals(request.get("ПРОИЗВОДИТЕЛЬ").toLowerCase());
            }
            if (request.keySet().contains("ОЗУ")) {
                check &= notebook.getRam() > Integer.parseInt(request.get("ОЗУ"));
            }
            if (request.keySet().contains("ЖД")) {
                check &= notebook.getRom() > Integer.parseInt(request.get("ЖД"));
            }
            if (request.keySet().contains("ОС")) {
                check &= notebook.getOs().equals(request.get("ОС"));
            }
            if (request.keySet().contains("ЦВЕТ")) {
                check &= notebook.getColor().equals(request.get("ЦВЕЬ"));
            }
            if (check) {
                result.add(notebook);
            }
        }
        for (notebook notebook : result) {
            System.out.println(notebook);
        }
    }
}