package coursework.operations;

import coursework.models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;
    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Access.class);
                configuration.addAnnotatedClass(AccessId.class);
                configuration.addAnnotatedClass(Category.class);
                configuration.addAnnotatedClass(Check.class);
                configuration.addAnnotatedClass(CheckId.class);
                configuration.addAnnotatedClass(CountriesName.class);
                configuration.addAnnotatedClass(CountriesNameId.class);
                configuration.addAnnotatedClass(Country.class);
                configuration.addAnnotatedClass(Department.class);
                configuration.addAnnotatedClass(Film.class);
                configuration.addAnnotatedClass(Genre.class);
                configuration.addAnnotatedClass(GenresName.class);
                configuration.addAnnotatedClass(GenresNameId.class);
                configuration.addAnnotatedClass(Hall.class);
                configuration.addAnnotatedClass(Order.class);
                configuration.addAnnotatedClass(OrderProduct.class);
                configuration.addAnnotatedClass(OrderProductId.class);
                configuration.addAnnotatedClass(OrderTicket.class);
                configuration.addAnnotatedClass(OrderTicketId.class);
                configuration.addAnnotatedClass(Payment.class);
                configuration.addAnnotatedClass(Product.class);
                configuration.addAnnotatedClass(ProductType.class);
                configuration.addAnnotatedClass(Seance.class);
                configuration.addAnnotatedClass(Seat.class);
                configuration.addAnnotatedClass(SeatId.class);
                configuration.addAnnotatedClass(Staff.class);
                configuration.addAnnotatedClass(Ticket.class);
                configuration.addAnnotatedClass(TicketsPrice.class);
                configuration.addAnnotatedClass(TicketsPriceId.class);
                configuration.addAnnotatedClass(WorkingHour.class);

                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
