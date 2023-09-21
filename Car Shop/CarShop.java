import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class CarShop {   //define the variables to be input into the array
	public ArrayList<CarShop> cars = new ArrayList<CarShop>();
	String carId;
    String manufacturer;
    String model;
    int year;
    double mileage;
    double engineSize;
    String grade;
    double price;

    	//creating constructor
    	//String properties are initialized to null: int = 0, double = 0.0, boolean = false
    public CarShop(String carId, String manufacturer, String model, int year, double mileage, double engineSize, String grade, double price) {
        this.carId = carId;
        // setting the value of the carId property of the current Car object to the value that is passed as an argument to the constructor. this keyword distinguishes between the parameter variables and the instance variables of the Car class.
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.engineSize = engineSize;
        this.grade = grade;
        this.price = price; 
    }


    // getters and setters
    
    public String getCarId() {
        return carId;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getMileage() {
        return mileage;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public String getGrade() {
        return grade;
    }

    public double getPrice() {
        return price;
    }


    //below I created a menu to display the methods
    public static void displayMenu(ArrayList<CarShop> cars) {
    	Scanner scanner = new Scanner(System.in);
    	int nextCarId = cars.size() + 1;

        System.out.println("Welcome to John's Car Shop software!");
        System.out.println("Please select an option from the following menu:");
        System.out.println("1. Add a car to the inventory");
        System.out.println("2. Generate a report of all cars sorted by model");
        System.out.println("3. Generate a report of all cars sorted by price");
        System.out.println("4. Search for Car with Highest Mileage");
        System.out.println("5. Search for a Car with lowest price");
        System.out.println("6. Search car by ID");
        System.out.println("7. Generate Stock Volume Report");
        System.out.println("8. Exit the program");

        
        
        while (true) {
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    CarShop.addCar(cars, nextCarId);
                    nextCarId++;
                    break;
                case "2":
                	CarShop.generateReportSortedByModel(cars);
                    break;
                case "3":
                    CarShop.generateReportSortedByPrice(cars);
                    break;
                case "4":
                	CarShop.findCarWithLowestMileage(cars);
                    break;
                case "5":
                	CarShop.findCarWithLowestPrice(cars);
                    break;
                case "6":
                    CarShop.generateReportForCarByID(cars);
                    break;
                case "7":
                    CarShop.generateManufacturerReport(cars);
                    break;
                case "8":
                    System.out.println("Thank you for using John's Car Shop software. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }

    
    
    //method to add a car
    public static void addCar(ArrayList<CarShop> cars, int nextCarId) {
    	Scanner scanner = new Scanner(System.in);
    	

        //  manufacturer
        String manufacturer = "";
        while (manufacturer.isEmpty()) {
            System.out.print("Enter the car manufacturer: ");
            manufacturer = scanner.nextLine().trim();
            if (manufacturer.isEmpty()) {
                System.out.println("Invalid input: manufacturer cannot be empty.");
            }
        }

        // model
        String model = "";
        while (model.isEmpty()) {
            System.out.print("Enter the car model: ");
            model = scanner.nextLine().trim();
            if (model.isEmpty()) {
                System.out.println("Invalid input: model cannot be empty.");
            }
        }

        // year
        int year = 0;
        while (year < 1900 || year > 2023) {
            System.out.print("Enter the car year: ");
            try {
                year = Integer.parseInt(scanner.nextLine());
                if (year < 1900 || year > 2023) {
                    System.out.println("Invalid input: year must be between 1900 and 2023.");
                }
            } catch (NumberFormatException error) {
                System.out.println("Invalid input: year must be a valid integer.");
            }
        }

        // mileage
        double mileage = -1;
        while (mileage < 0) {
            System.out.print("Enter the car mileage: ");
            try {
                mileage = Double.parseDouble(scanner.nextLine());
                if (mileage < 0) {
                    System.out.println("Invalid input: mileage cannot be negative.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: mileage must be a valid number.");
            }
        }

        // car engine size
        double engineSize = -1;
        while (engineSize < 0) {
            System.out.print("Enter the car engine size: ");
            try {
                engineSize = Double.parseDouble(scanner.nextLine());
                if (engineSize < 0) {
                    System.out.println("Invalid input: engine size must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: engine size must be a valid number.");
            }
        }

        // car grade
        String grade = "";
        while (!grade.matches("[A-D]")) {
            System.out.print("Enter the car grade (A, B, C, or D): ");
            grade = scanner.nextLine().trim();
            if (!grade.matches("[A-D]")) {
                System.out.println("Invalid input: grade must be A, B, C, or D.");
            }
        }

        // car price
        double price = -1;
        while (price < 0) {
            System.out.print("Enter the car price: ");
            try {
                price = Double.parseDouble(scanner.nextLine());
                if (price < 0) {
                    System.out.println("Invalid input: price must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input: price must be a valid number.");
            }
        }

        // generate the car ID and create the car object
        String carId = String.format("%03d", nextCarId);
        CarShop newCar = new CarShop(carId, manufacturer, model, year, mileage, engineSize, grade, price);
        cars.add(newCar);
        System.out.println("Car with ID " + carId + " has been added");

    }
    
	//method to make a report
    public static void generateReport(ArrayList<CarShop> cars) {
        // print the header row
        System.out.printf("%-10s %-15s %-15s %-5s %-10s %-10s %-10s %-10s\n", "Car ID", "Manufacturer", "Model", "Year", "Mileage", "Engine Size", "Grade", "Price");
        // loop over each car with enhanced for: loop and print its data in a row
        for (CarShop car : cars) {
            System.out.printf("%-10s %-15s %-15s %-5d %-10.2f %-10.2f %-10s %-10.2f\n", car.getCarId(), car.getManufacturer(), car.getModel(), car.getYear(), car.getMileage(), car.getEngineSize(), car.getGrade(), car.getPrice());
        }
    }

    //grade conditions method display
    public static void displayGradeConditions() {
        System.out.printf("%-5s %-10s %-50s\n", "Grade", "Condition", "Description");
        System.out.printf("%-5s %-10s %-50s\n", "A", "Excellent", "The vehicle is in outstanding condition and requires no reconditioning.");
        System.out.printf("%-5s %-10s %-50s\n", "B", "Good", "The vehicle shows some signs of normal wear and tear, but has no major mechanical or cosmetic problems.");
        System.out.printf("%-5s %-10s %-50s\n", "C", "Fair", "The vehicle may have some repairable cosmetic or mechanical defects, but is still in reasonable running condition.");
        System.out.printf("%-5s %-10s %-50s\n", "D", "Poor", "The vehicle has significant cosmetic or mechanical defects and requires repairs in order to be functional.");
    }
    
    //method to call for retrieving description
    public static String getGradeDescription(String grade) {
        switch (grade) {
            case "A":
                return "Excellent: The vehicle is in outstanding condition and requires no reconditioning.";
            case "B":
                return "Good: The vehicle shows some signs of normal wear and tear, but has no major mechanical or cosmetic problems.";
            case "C":
                return "Average: The vehicle may have some repairable cosmetic or mechanical defects, but is still in reasonable running condition.";
            case "D":
                return "Poor: The vehicle has significant cosmetic or mechanical defects and requires repairs in order to be functional.";
            default:
                return "Unknown grade.";
        }
    }
    
    //method for alphabetical model report
    public static void generateReportSortedByModel(ArrayList<CarShop> cars) {
        // validate user input
        if (cars == null || cars.isEmpty()) {
            System.out.println("The list of cars cannot be null or empty.");
            return;
        }

        //use collections method with comparator to merge sort the cars array
        Collections.sort(cars, new Comparator<CarShop>() {
            public int compare(CarShop car1, CarShop car2) {
                String model1 = car1.getModel();
                String model2 = car2.getModel();
                if (model1.equals(model2)) {
                    return 0;
                } else if (model1.compareTo(model2) < 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });
/*
 * 
 * bubble sort
 * int n = cars.size();
    for (int i = 0; i < n-1; i++) {
        for (int j = 0; j < n-i-1; j++) {
            if (cars.get(j).getModel().compareTo(cars.get(j+1).getModel()) > 0) {
                // swap cars at positions j and j+1
                Car temp = cars.get(j);
                cars.set(j, cars.get(j+1));
                cars.set(j+1, temp);
            }
        }
 * 
 */
        // print the header row
        System.out.printf("%-10s %-15s %-15s %-5s %-10s %-10s %-10s %-10s\n", "Car ID", "Manufacturer", "Model", "Year", "Mileage", "Engine Size", "Grade", "Price");

        // loop over each car and print its data in a row
        for (CarShop car : cars) {
            System.out.printf("%-10s %-15s %-15s %-5d %-10.2f %-10.2f %-10s %-10.2f\n", car.getCarId(), car.getManufacturer(), car.getModel(), car.getYear(), car.getMileage(), car.getEngineSize(), car.getGrade(), car.getPrice());
        }
    }

    //price sort method
    public static void generateReportSortedByPrice(ArrayList<CarShop> cars) {
    	// validate user input
        if (cars == null || cars.isEmpty()) {
            System.out.println("The list of cars cannot be null or empty.");
            return;
        }

    	
    	
    	// sort the list by price
        Collections.sort(cars, new Comparator<CarShop>() {
            public int compare(CarShop car1, CarShop car2) {
                if (car1.getPrice() > car2.getPrice()) {
                    return -1;
                } else if (car1.getPrice() < car2.getPrice()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        // print the header row
        System.out.printf("%-10s %-15s %-15s %-5s %-10s %-10s %-10s %-10s\n", "Car ID", "Manufacturer", "Model", "Year", "Mileage", "Engine Size", "Grade", "Price");

        // loop over each car and print its data in a row
        for (CarShop car : cars) {
            System.out.printf("%-10s %-15s %-15s %-5d %-10.2f %-10.2f %-10s %-10.2f\n", car.getCarId(), car.getManufacturer(), car.getModel(), car.getYear(), car.getMileage(), car.getEngineSize(), car.getGrade(), car.getPrice());
        }
    }
    
    //lowest mileage search method
    public static CarShop findCarWithLowestMileage(ArrayList<CarShop> cars) {
    	// validate user input
        if (cars == null || cars.isEmpty()) {
            System.out.println("The list of cars cannot be null or empty.");
            return null;
        }
        for (CarShop car : cars) {
            if (car.getMileage() < 0) {
                System.out.println("Mileage value cannot be negative.");
                return null;
            }
        }
    	
    	// initialize the lowest mileage to be the first car in the list
        CarShop lowestMileageCar = cars.get(0);
        
        // loop through the rest of the cars in the list
        for (int i = 1; i < cars.size(); i++) {
            if (cars.get(i).getMileage() < lowestMileageCar.getMileage()) {
                lowestMileageCar = cars.get(i);
            }
        }
        
        // return the car with the lowest mileage
        System.out.println("The car with the lowest mileage is:");
        System.out.printf("Car ID: %s, Manufacturer: %s, Model: %s, Year: %d, Mileage: %.2f, Engine Size: %.2f, Grade: %s, Price: %.2f\n", 
                lowestMileageCar.getCarId(), lowestMileageCar.getManufacturer(), lowestMileageCar.getModel(), lowestMileageCar.getYear(), 
                lowestMileageCar.getMileage(), lowestMileageCar.getEngineSize(), lowestMileageCar.getGrade(), lowestMileageCar.getPrice());
		return lowestMileageCar;
        }


    

    //lowest price method
    public static CarShop findCarWithLowestPrice(ArrayList<CarShop> cars) {
    	// validate user input
        if (cars == null || cars.isEmpty()) {
            System.out.println("The list of cars cannot be null or empty.");
            return null;
        }
        for (CarShop car : cars) {
            if (car.getMileage() < 0) {
                System.out.println("Mileage value cannot be negative.");
                return null;
            }
        }
    	   	    	
    	
    	// initialize the lowest price to be the first car in the list
        CarShop carWithLowestPrice = cars.get(0);
        
        // loop through the rest of the cars in the list
        for (int i = 1; i < cars.size(); i++) {
            if (cars.get(i).getPrice() < carWithLowestPrice.getPrice()) {
                carWithLowestPrice = cars.get(i);
            }
        }
        
        // return the car with the lowest price
        System.out.println("The car with the lowest price is:");
        System.out.printf("Car ID: %s, Manufacturer: %s, Model: %s, Year: %d, Mileage: %.2f, Engine Size: %.2f, Grade: %s, Price: %.2f\n", 
                carWithLowestPrice.getCarId(), carWithLowestPrice.getManufacturer(), carWithLowestPrice.getModel(), carWithLowestPrice.getYear(), 
                carWithLowestPrice.getMileage(), carWithLowestPrice.getEngineSize(), carWithLowestPrice.getGrade(), carWithLowestPrice.getPrice());
        return carWithLowestPrice;
    }

    //generate report method
    public static void generateReportForCarByID(ArrayList<CarShop> cars) {
    	// validate user input
        if (cars == null || cars.isEmpty()) {
            System.out.println("Invalid input: the list of cars cannot be null or empty.");
            return;
        }
    	
        // scanner instance
    	Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the car ID: ");
        String carID = scanner.nextLine().trim();
        if (carID == null || carID.isEmpty()) {
            System.out.println("Invalid input: car ID cannot be null or empty.");
            return;
        }

        for (CarShop car : cars) {
            if (car.getCarId().equals(carID)) {
                System.out.println("Details for car with ID " + carID + ":");
                System.out.println("Manufacturer: " + car.getManufacturer());
                System.out.println("Model: " + car.getModel());
                System.out.println("Year: " + car.getYear());
                System.out.println("Mileage: " + car.getMileage());
                System.out.println("Engine Size: " + car.getEngineSize());
                System.out.println("Grade: " + car.getGrade() +  " - " + getGradeDescription(car.getGrade()));
                System.out.println("Price: " + car.getPrice());
                System.out.println();
                return; // exit the method after printing the details
            }
        }
        
        // if the method gets here, the car ID was not found in the list
        System.out.println("No car found with ID " + carID);
    }

    public void updateCar(String carId, String manufacturer, String model, int year, double mileage, double engineSize, String grade, double price) {
        this.carId = carId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.engineSize = engineSize;
        this.grade = grade;
        this.price = price;
    }

    public static void generateManufacturerReport(ArrayList<CarShop> cars) {
        // validate user input
        if (cars == null || cars.isEmpty()) {
            System.out.println("Invalid input: the list of cars cannot be null or empty.");
            return;
        }

        // map to store the total stock price by manufacturer
        Map<String, Double> stockByManufacturer = new HashMap<>();

        // map to store the number of cars by manufacturer
        Map<String, Integer> numCarsByManufacturer = new HashMap<>();

        // calculate the total stock price and number of cars for each manufacturer
        for (CarShop car : cars) {
            String manufacturer = car.getManufacturer();
            double price = car.getPrice();
            double stock = stockByManufacturer.getOrDefault(manufacturer, 0.0);
            stockByManufacturer.put(manufacturer, stock + price);

            int numCars = numCarsByManufacturer.getOrDefault(manufacturer, 0);
            numCarsByManufacturer.put(manufacturer, numCars + 1);
        }

        // print the report header
        System.out.printf("%-20s %-10s %-10s\n", "Manufacturer", "Num Cars", "Stock Price");

        // loop through the keys in the map and print the report rows
        for (String manufacturer : stockByManufacturer.keySet()) {
            double stockPrice = stockByManufacturer.get(manufacturer);
            int numCars = numCarsByManufacturer.get(manufacturer);
            System.out.printf("%-20s %-10d $%-10.2f\n", manufacturer, numCars, stockPrice);
        }
    }


    	

    	
    
    public static void main(String[] args) {
    	
        ArrayList<CarShop> cars = new ArrayList<>();

        // add some cars to the list
        cars.add(new CarShop("001", "Toyota", "Prius", 2010, 100000, 1.8, "D", 8000));
        cars.add(new CarShop("002", "Honda", "Civic", 2012, 80000, 1.6, "C", 9000));
        cars.add(new CarShop("003", "Ford", "Mustang", 2015, 50000, 3.7, "B", 15000));
        cars.add(new CarShop("004", "Chevrolet", "Tahoe", 2018, 25000, 3.6, "A", 12000));
        cars.add(new CarShop("005", "Tesla", "Model S", 2019, 10000, 0.0, "B", 45000));
        cars.add(new CarShop("006", "Nissan", "Altima", 2016, 60000, 2.5, "D", 9500));
        cars.add(new CarShop("007", "BMW", "X5", 2017, 40000, 3.0, "A", 27000));
        cars.add(new CarShop("008", "Mercedes", "GLC", 2015, 75000, 2.0, "B", 18000));
        cars.add(new CarShop("009", "Mercedes", "GLC", 2015, 80000, 2.0, "B", 16000));
        cars.add(new CarShop("010", "Mercedes", "GLC", 2015, 99000, 2.0, "C", 12000));

        // print the details of each car in the list
      
        CarShop.displayMenu(cars);
	};
    
    
    
}