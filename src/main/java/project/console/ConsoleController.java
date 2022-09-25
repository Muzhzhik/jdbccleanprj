package project.console;

import project.service.ActionManager;
import project.utils.Action;

import java.util.Scanner;

/**
 * @author Sergey Muzhzukhin
 * ¯\_(ツ)_/¯
 */
public class ConsoleController {
    public void showMainMenu() {
        Action action;
        Scanner scanner = new Scanner(System.in);
        System.out.println("--------------------------");
        System.out.println(Action.GET_ALL.ordinal() + " - показывать все заказы");
        System.out.println(Action.GET.ordinal() + " - показывать конкретный заказ");
        System.out.println(Action.CREATE.ordinal() + " - создать заказ");
        System.out.println(Action.UPDATE.ordinal() + " - изменить заказ");
        System.out.println(Action.DELETE.ordinal() + " - удалить заказ");
        System.out.println("\n" + Action.EXIT.ordinal() + " - выход");
        do {
            System.out.println("> ");
            String read = scanner.nextLine();
            try{
                int index = Integer.parseInt(read);
                action = Action.getAction(index);
                if (action != null) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                //do nothing
            }
            System.err.println("Значение нужно выбрать из списка");
        } while (true);
        new ActionManager(action).doAction();
    }
}
