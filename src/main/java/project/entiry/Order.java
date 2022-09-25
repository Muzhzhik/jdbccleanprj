package project.entiry;

/**
 * @author Sergey Muzhzukhin
 * ¯\_(ツ)_/¯
 */
public class Order {
    private final int id;
    private String clientName;
    private String clientPhone;
    private String order;

    public Order(int id, String clientName, String clientPhone, String order) {
        this.id = id;
        this.clientName = clientName;
        this.clientPhone = clientPhone;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return  "\033[0;36m" + id +
                " | Имя: '" + clientName + '\'' +
                " | Телефон: '" + clientPhone + '\'' +
                " | Заказ: '" + order + '\'' +
                ";\033[0m";
    }

}
