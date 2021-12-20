package coursework.operations;

import coursework.models.Access;
import coursework.models.Hall;
import coursework.models.Seance;

import java.util.HashMap;
import java.util.List;

public class Session {

    public List<Integer> getCount() {
        return count;
    }

    public void setCount(List<Integer> count) {
        this.count = count;
    }

    public static void setInstance(Session instance) {
        Session.instance = instance;
    }

    private Access access;

    private Long price;

    private Seance seance;

    public Seance getHall() {
        return seance;
    }

    public void setHall(Seance seance) {
        this.seance = seance;
    }

    private List<Integer> count;

    private HashMap<Integer, Integer> seat;

    public HashMap<Integer, Integer> getSeat() {
        return seat;
    }

    public void setSeat(HashMap<Integer, Integer> seat) {
        this.seat = seat;
    }

    public List<Integer> getProductId() {
        return productId;
    }

    public void setProductId(List<Integer> productId) {
        this.productId = productId;
    }

    private List<Integer> productId;

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String user;

    private static Session instance;

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    private Session() { }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

}
