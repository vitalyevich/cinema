package Server.RMI;

import Server.DB.*;
import Server.Model.*;
import javafx.collections.ObservableList;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.time.LocalDate;
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
    }

    @Override
    public void AddNewStaff(Staff staff) throws RemoteException, SQLException {
        staffDAO.save(staff);
    }

    @Override
    public void AddNewAccess(Access access) throws RemoteException {
        accessDAO.save(access);
    }

    @Override
    public void EditStaff(Staff staff) throws RemoteException {
        staffDAO.update(staff);
    }

    @Override
    public void DeleteStaff(String lastName, String firstName, String middleName) throws RemoteException, SQLException {
        staffDAO.delete(lastName,firstName,middleName);
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
    public List<Department> getDepartmentList(Department department) throws RemoteException {
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
        buffetDAO.save(product);
    }

    @Override
    public void EditProduct(Product product) throws RemoteException {
        buffetDAO.update(product);
    }

    @Override
    public void DeleteProductById(int id) throws RemoteException {
        buffetDAO.deleteById(id);
    }

    @Override
    public void DeleteProductByName(String name) throws RemoteException {
        buffetDAO.deleteByName(name);
    }

    public void TruncateTableProduct() throws RemoteException {
        buffetDAO.truncate();
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
    public void AddNewFilm(Film film) throws RemoteException {
        filmDAO.save(film);
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
    public void AddNewFilmGenre(GenresName genresName) throws RemoteException {
        genreDAO.addTotal(genresName);
    }

    @Override
    public void AddNewFilmCountry(CountriesName countriesName) throws RemoteException {
        countryDAO.addTotal(countriesName);
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
    public void DeleteSeanceByDate(LocalDate date) throws RemoteException {
        sessionDAO.deleteByDate(date);
    }

    @Override
    public void DeleteSeanceByName(Film id) throws RemoteException {
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

    public int getSeatByRow(int row) throws  RemoteException {
        return seatDAO.searchSeatByRow(row);
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

    public static void main(String[] args) {

        String localhost = "127.0.0.1";
        String RMI_HOSTNAME = "java.rmi.server.hostname";

        try {
            System.setProperty(RMI_HOSTNAME, localhost);
            // Создание удаленного RMI объекта
            BillingService service = new BillingServiceImpl();

            // Определение имени удаленного RMI объекта
            String serviceName = "BillingService";

            System.out.println("Инициализация " + serviceName);

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
