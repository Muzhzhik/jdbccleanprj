package project.service;

import project.connection.DBConnection;
import project.console.ConsoleController;
import project.entiry.Order;
import project.repository.OrderRepository;
import project.utils.Action;

import java.util.List;
import java.util.Scanner;

/**
 * @author Sergey Muzhzukhin
 * ¯\_(ツ)_/¯
 */
public class ActionManager {

    private final Action action;

    public ActionManager(Action action) {
        this.action = action;
    }

    public void doAction() {
        if (action == Action.EXIT) {
            System.exit(0);
        }

        if (action == Action.GET_ALL) {
            getAll();
        }

        if (action == Action.CREATE) {
            createOrder();
        }

        if (action == Action.DELETE) {
            deleteOrder();
        }

        if (action == Action.GET) {
            getOrderById();
        }

        if (action == Action.UPDATE) {
            updateOrder();
        }

        System.out.println("Нажмите ENTER для продолжения");
        new Scanner(System.in).nextLine();
        new ConsoleController().showMainMenu();
    }

    private void updateOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id заказа:");
        do {
            String input = scanner.nextLine();
            try {
                int id = Integer.parseInt(input);
                OrderRepository orderRepository = new OrderRepository();
                Order order = orderRepository.getById(id);
                if (order == null) {
                    System.err.println("Заказ не найден");
                } else {
                    System.out.println(order);
                    System.out.println("Введите имя клиента:");
                    String clientName = scanner.nextLine();
                    if (!clientName.trim().equals("")) {
                        order.setClientName(clientName);
                    }
                    System.out.println("Введите номер телефона:");
                    String phone = scanner.nextLine();
                    if (!phone.trim().equals("")) {
                        order.setClientPhone(phone);
                    }
                    System.out.println("Что заказал:");
                    String orderList = scanner.nextLine();
                    if (!orderList.trim().equals("")) {
                        order.setOrder(orderList);
                    }

                    if (orderRepository.update(order)) {
                        System.out.println("Заказ изменен");
                    }

                }
                break;
            } catch (IllegalArgumentException e) {
                System.err.println("Введите число");
            }
        } while (true);
    }

    private void getAll() {
        List<Order> orderList = new OrderRepository().getAll();
        if (orderList != null && !orderList.isEmpty()) {
            orderList.forEach(System.out::println);
        }
    }

    private void createOrder() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя клиента:");
        String clientName = scanner.nextLine();
        System.out.println("Введите номер телефона:");
        String phone = scanner.nextLine();
        System.out.println("Что заказал:");
        String order = scanner.nextLine();
        if (new OrderRepository().create(clientName, phone, order)) {
            System.out.println("Заказ создан");
        }
    }

    private void deleteOrder() {
        OrderRepository orderRepository = new OrderRepository();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id заказа:");
        do {
            String input = scanner.nextLine();
            try {
                int id = Integer.parseInt(input);
                Order order = orderRepository.getById(id);
                if (order != null) {
                    if (orderRepository.delete(id))
                        System.out.println("Заказ " + id + " удален");
                } else {
                    System.err.println("Заказ не найден");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.err.println("Введите число");
            }
        } while (true);
    }

    private void getOrderById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите id заказа:");
        do {
            String input = scanner.nextLine();
            try {
                int id = Integer.parseInt(input);
                Order order = new OrderRepository().getById(id);
                if (order != null) {
                    System.out.println(order);
                } else {
                    System.err.println("Заказ не найден");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.err.println("Введите число");
            }
        } while (true);
    }
}
