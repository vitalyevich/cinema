package coursework.interfaces;

import coursework.models.Check;
import coursework.models.Order;
import coursework.models.OrderProduct;

import java.util.List;


public interface OrderDAO {

    public int saveOrder(Order order);
    public void saveOrderProduct(OrderProduct orderProduct);
    public void saveOrderCheck(Check Check);
    public List<Check> viewCheck();
    public List<OrderProduct> viewOrder();
}
