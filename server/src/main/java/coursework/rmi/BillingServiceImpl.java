package coursework.rmi;

import coursework.operations.*;
import coursework.models.*;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class BillingServiceImpl extends UnicastRemoteObject implements BillingService {

    private StaffDAOImpl staffDAO;
    private AccessDAOImpl accessDAO;
    private DepartmentDAOImpl departmentDAO;
    private BuffetDAOImpl buffetDAO;
    private FilmDAOImpl filmDAO;
    private SeanceDAOImpl sessionDAO;
    private HallDAOImpl hallDAO;
    private SeatDAOImpl seatDAO;
    private CategoryDAOImpl categoryDAO;
    private GenreDAOImpl genreDAO;
    private CountryDAOImpl countryDAO;
    private OrderDAOImpl orderDAO;
    private TicketDAOImpl ticketDAO;

    public BillingServiceImpl() throws RemoteException {

        this.staffDAO = new StaffDAOImpl();
        this.departmentDAO = new DepartmentDAOImpl();
        this.buffetDAO = new BuffetDAOImpl();
        this.filmDAO = new FilmDAOImpl();
        this.sessionDAO = new SeanceDAOImpl();
        this.hallDAO = new HallDAOImpl();
        this.seatDAO = new SeatDAOImpl();
        this.categoryDAO = new CategoryDAOImpl();
        this.genreDAO = new GenreDAOImpl();
        this.countryDAO = new CountryDAOImpl();
        this.accessDAO = new AccessDAOImpl();
        this.orderDAO = new OrderDAOImpl();
        this.ticketDAO = new TicketDAOImpl();
    }

    @Override
    public int AddNewStaff(Staff staff) throws RemoteException, SQLException {
        return staffDAO.save(staff);
    }

    @Override
    public void AddNewAccess(Access access) throws RemoteException {
        accessDAO.save(access);
    }

    @Override
    public void EditAccess(Access access) throws RemoteException {
        accessDAO.update(access);
    }

    @Override
    public void EditStaff(Staff staff) throws RemoteException {
        staffDAO.update(staff);
    }

    @Override
    public void DeleteStaffById(int id) throws RemoteException {
        staffDAO.deleteById(id);
    }

    @Override
    public void DeleteStaffByPosition(Department role) throws RemoteException {
        staffDAO.deleteByPosition(role);
    }

    @Override
    public void TruncateTableStaff() throws RemoteException {
        staffDAO.truncate();
    }

    @Override
    public List<Access> getStaffList() throws RemoteException {
        return accessDAO.view();
    }

    @Override
    public List<Access> getStaff(int searchId) throws RemoteException {
        return accessDAO.search(searchId);
    }

    @Override
    public List<Access> findByAccess(String lastName, String password) throws RemoteException {
        return accessDAO.findByAccess(lastName, password);
    }

    @Override
    public void AddNewDepartment(Department department) throws RemoteException, SQLException {
        departmentDAO.save(department);
    }

    @Override
    public void EditDepSalary(Department department) throws RemoteException { // проверки
        departmentDAO.updateSalary(department);
    }

    @Override
    public void EditDepartment(Department department) throws RemoteException {
        departmentDAO.updateDep(department);
    }

    @Override
    public void DeleteDepartment(int id) throws RemoteException {
        departmentDAO.delete(id);
    }

    @Override
    public void TruncateTableDepartment() throws RemoteException {
        departmentDAO.truncate();
    }

    @Override
    public List<Department> getDepartmentList() throws RemoteException {
        return departmentDAO.view();
    }

    /*@Override
    public void AddNewDrink(Drink drink) throws RemoteException {

    }

    @Override
    public void EditDrink(Drink drink) throws RemoteException {

    }

    @Override
    public void DeleteDrink(Drink drink) throws RemoteException {

    }

    @Override
    public List<Drink> findByDrink(String drink) throws RemoteException {
        return null;
    }*/

    @Override
    public List<Product> getProductList() throws RemoteException {
        return buffetDAO.viewProduct();
    }

    @Override
    public List<Product> getProductList(int searchId) throws RemoteException {
        return buffetDAO.viewProduct(searchId);
    }

    @Override
    public List<Product> getProductList(String name) throws RemoteException {
        return buffetDAO.viewProduct(name);
    }

    @Override
    public void AddNewProduct(Product product) throws RemoteException {
        buffetDAO.saveProduct(product);
    }

    @Override
    public void EditProduct(Product product) throws RemoteException {
        buffetDAO.updateProduct(product);
    }

    @Override
    public void DeleteProduct(int id) throws RemoteException {
        buffetDAO.deleteProduct(id);
    }

    @Override
    public void DeleteProductType(int id) throws RemoteException {
        buffetDAO.deleteType(id);
    }

    @Override
    public void AddNewProductType(ProductType product) throws RemoteException {
        buffetDAO.saveProductType(product);
    }

    @Override
    public void EditProductType(ProductType product) throws RemoteException {
        buffetDAO.updateProductType(product);
    }

    @Override
    public List<ProductType> getProductTypeList() throws RemoteException {
        return buffetDAO.viewProductType();
    }

    @Override
    public List<ProductType> findByProduct(ProductType productType) throws RemoteException {
        return null;
    }

    @Override
    public int AddNewFilm(Film film) throws RemoteException, SQLException {
        return filmDAO.save(film);
    }

    @Override
    public void EditFilm(Film film) throws RemoteException {
        filmDAO.update(film);
    }

    @Override
    public void DeleteFilmById(int id) throws RemoteException {
        filmDAO.deleteById(id);
    }

    @Override
    public void DeleteFilm(String name) throws RemoteException {
        filmDAO.deleteByName(name);
    }

    @Override
    public void TruncateTableFilm() throws RemoteException {
        filmDAO.truncate();
    }

    @Override
    public List<Film> getFilmList() throws RemoteException {
        return filmDAO.view();
    }

    @Override
    public List<Film> getFilm(int searchId) throws RemoteException {
        return filmDAO.search(searchId);
    }

    @Override
    public List<GenresName> getFilmGenre(int searchId) throws RemoteException {
        return genreDAO.searchGenre(searchId);
    }

    @Override
    public List<CountriesName> getFilmCountry(int searchId) throws RemoteException {
        return countryDAO.searchCountry(searchId);
    }

    @Override
    public void AddNewFilmGenre(GenresName genresName) throws RemoteException, SQLException {
        genreDAO.addTotal(genresName);
    }

    @Override
    public void AddNewFilmCountry(CountriesName countriesName) throws RemoteException, SQLException {
        countryDAO.addTotal(countriesName);
    }

    @Override
    public void EditFilmGenre(int id) throws RemoteException {
        genreDAO.updateTotal(id);
    }

    @Override
    public void EditFilmCountry(int id) throws RemoteException {
        countryDAO.updateTotal(id);
    }

    @Override
    public void AddNewSeance(Seance seance) throws RemoteException, SQLException {
        sessionDAO.save(seance);
    }

    @Override
    public void EditSeance(Seance seance) throws RemoteException {
        sessionDAO.update(seance);
    }

    @Override
    public void DeleteSeanceById(int id) throws RemoteException {
        sessionDAO.deleteById(id);
    }

    @Override
    public void DeleteSeanceByDate(LocalDate date, LocalTime time) throws RemoteException {
        sessionDAO.deleteByDate(date, time);
    }

    @Override
    public void DeleteSeanceByName(int id) throws RemoteException {
        sessionDAO.deleteByName(id);
    }

    @Override
    public void TruncateTableSeance() throws RemoteException {
        sessionDAO.truncate();
    }

    @Override
    public List<Seance> getSeanceList() throws RemoteException {
        return sessionDAO.view();
    }

    @Override
    public List<Seance> getSeance(int searchId) throws RemoteException {
        return sessionDAO.search(searchId);
    }

    @Override
    public void AddNewHall(Hall hall) throws RemoteException, SQLException {
        hallDAO.save(hall);
    }

    @Override
    public void EditHall(Hall hall) throws RemoteException {
        hallDAO.update(hall);
    }

    @Override
    public void DeleteHall(int id) throws RemoteException {
        hallDAO.delete(id);
    }

    @Override
    public void TruncateTableHall() throws RemoteException {
        hallDAO.truncate();
    }

    @Override
    public List<Hall> getHallList() throws RemoteException {
        return hallDAO.view();
    }

    @Override
    public List<Hall> getHall(int searchId) throws RemoteException {
        return hallDAO.search(searchId);
    }

    @Override
    public void AddNewSeat(Seat seat) throws RemoteException, SQLException {
        seatDAO.save(seat);
    }

    @Override
    public void EditSeat(Seat seat) throws RemoteException {
        seatDAO.update(seat);
    }

    @Override
    public void DeleteSeat(Seat seat) throws RemoteException {
        seatDAO.delete(seat);
    }

    @Override
    public void TruncateTableSeat() throws RemoteException {
        seatDAO.truncate();
    }

    @Override
    public List<Seat> getSeatList() throws RemoteException {
        return seatDAO.view();
    }

    @Override
    public List<Seat> getSeat(int searchId) throws RemoteException {
        return seatDAO.search(searchId);
    }

    @Override
    public List<Seat> getSeatCategoryName(int idHall, int row, int seat) throws RemoteException {
        return seatDAO.searchCategory(idHall, row, seat);
    }

    public int getSeatByRow(int row, int hallId) throws  RemoteException {
        return seatDAO.searchSeatByRow(row, hallId);
    }

    @Override
    public void AddNewCategory(Category category) throws RemoteException, SQLException {
        categoryDAO.save(category);
    }

    @Override
    public void EditCategory(Category category) throws RemoteException {
        categoryDAO.update(category);
    }

    @Override
    public void DeleteCategory(int id) throws RemoteException {
        categoryDAO.delete(id);
    }

    @Override
    public void TruncateTableCategory() throws RemoteException {
        categoryDAO.truncate();
    }

    @Override
    public List<Category> getCategoryList() throws RemoteException {
        return categoryDAO.view();
    }

    @Override
    public List<Category> getCategory(int searchId) throws RemoteException {
        return categoryDAO.search(searchId);
    }

    @Override
    public void AddNewGenre(Genre genre) throws RemoteException, SQLException {
        genreDAO.save(genre);
    }

    @Override
    public void EditGenre(Genre genre) throws RemoteException {
        genreDAO.update(genre);
    }

    @Override
    public void DeleteGenre(int id) throws RemoteException {
        genreDAO.delete(id);
    }

    @Override
    public void TruncateTableGenre() throws RemoteException {
        genreDAO.truncate();
    }

    @Override
    public List<Genre> getGenreList() throws RemoteException {
        return genreDAO.view();
    }

    @Override
    public List<Genre> getGenre(int searchId) throws RemoteException {
        return genreDAO.search(searchId);
    }

    @Override
    public void AddNewCountry(Country country) throws RemoteException, SQLException {
        countryDAO.save(country);
    }

    @Override
    public void EditCountry(Country country) throws RemoteException {
        countryDAO.update(country);
    }

    @Override
    public void DeleteCountry(int id) throws RemoteException {
        countryDAO.delete(id);
    }

    @Override
    public void TruncateTableCountry() throws RemoteException {
        countryDAO.truncate();
    }

    @Override
    public List<Country> getCountryList() throws RemoteException {
        return countryDAO.view();
    }

    @Override
    public List<Country> getCountry(int searchId) throws RemoteException {
        return countryDAO.search(searchId);
    }

    @Override
    public int AddNewOrder(Order order) throws RemoteException {
        return orderDAO.saveOrder(order);
    }

    @Override
    public void AddNewOrderProduct(OrderProduct orderProduct) throws RemoteException {
        orderDAO.saveOrderProduct(orderProduct);
    }

    @Override
    public void AddNewCheck(Check check) throws RemoteException {
        orderDAO.saveOrderCheck(check);
    }

    @Override
    public List<Check> getCheckList() throws RemoteException {
        return orderDAO.viewCheck();
    }

    @Override
    public List<OrderProduct> getOrderList() throws RemoteException {
        return orderDAO.viewOrder();
    }

    @Override
    public List<Ticket> getTicketList() throws RemoteException {
        return ticketDAO.viewTicket();
    }

    @Override
    public List<TicketsPrice> getTicketPriceList() throws RemoteException {
        return ticketDAO.viewPrice();
    }

    @Override
    public List<Seat> getSeatList(int hallId) throws RemoteException {
        return seatDAO.view(hallId);
    }

    @Override
    public List<Hall> getHallList(int hallId) throws RemoteException {
        return hallDAO.view(hallId);
    }

    public static void main(String[] args) {

        String localhost = "127.0.0.1";
        String RMI_HOSTNAME = "java.rmi.server.hostname";

        try {
            // Определение имени удаленного RMI объекта
            String serviceName = "BillingService";

            System.out.println("Инициализация " + serviceName);

            System.out.println("IP хоста " + localhost);
            System.out.println("Порт " + 1099);
            System.setProperty(RMI_HOSTNAME, localhost);
            // Создание удаленного RMI объекта
            BillingService service = new BillingServiceImpl();

            Registry registry = LocateRegistry.createRegistry(1099);

            registry.rebind(serviceName, service);

            System.out.println("Старт " + serviceName);

        } catch (RemoteException e) {
            System.err.println("RemoteException : "+e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Exception : " + e.getMessage());
            System.exit(2);
        }
    }
}
