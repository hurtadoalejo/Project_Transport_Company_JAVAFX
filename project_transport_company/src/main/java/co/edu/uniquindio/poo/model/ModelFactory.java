package co.edu.uniquindio.poo.model;

public class ModelFactory {
    private static ModelFactory instance;
    private TransportCompany transportCompany;

    /**
     * Method constructor fro the class Model Factory
     */
    private ModelFactory() {
        transportCompany = initializeData();
    }

    /**
     * Method to get the model factory's only instance
     * @return
     */
    public static ModelFactory getInstance() {
        if (instance == null) {
            instance = new ModelFactory();
        }
        return instance;
    }

    /**
     * Method to initialize data in the transport company's only instance
     * @return
     */
    public TransportCompany initializeData() {
        TransportCompany transportCompany = new TransportCompany("La Carreta");
        Proprietor proprietor = new Proprietor("Alejandro", "alejo@gmail.com", "3161971519", "1");
        Proprietor proprietor2 = new Proprietor("Veronica", "vero@gmail.com", "3161971519", "2");
        Proprietor proprietor3 = new Proprietor("Sofia", "sofia@gmail.com", "3161971519", "3");
        transportCompany.addProprietor(proprietor);
        transportCompany.addProprietor(proprietor2);
        transportCompany.addProprietor(proprietor3);
        PassengerVehicle passengerVehicle = new PassengerVehicle("VAD01", "Mazda", "Red", 2020, proprietor, 5);
        PassengerVehicle passengerVehicle2 = new PassengerVehicle("VAD02", "Toyota", "Black", 2018, proprietor2, 1);
        transportCompany.addVehicle(passengerVehicle);
        transportCompany.addVehicle(passengerVehicle2);
        User user = new User("Valentina", 22, 56);
        User user2 = new User("Jorge", 40, 87);
        transportCompany.addUser(user);
        transportCompany.addUser(user2);
        transportCompany.addUserToVehicle(passengerVehicle, user);
        transportCompany.addProprietorAssociated(passengerVehicle, proprietor);
        return transportCompany;
    }

    /**
     * Method to obtain the only transport company instance
     * @return
     */
    public TransportCompany getTransportCompany() {
        return transportCompany;
    }
}
