import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Car {   //define the variables to be input into the array
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
    public Car(String carId, String manufacturer, String model, int year, double mileage, double engineSize, String grade, double price) {
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

	
	//method to make a report
    public static void generateReport(ArrayList<Car> cars) {
        // print the header row
        System.out.printf("%-10s %-15s %-15s %-5s %-10s %-10s %-10s %-10s\n", "Car ID", "Manufacturer", "Model", "Year", "Mileage", "Engine Size", "Grade", "Price");
        // loop over each car with enhanced for: loop and print its data in a row
        for (Car car : cars) {
            System.out.printf("%-10s %-15s %-15s %-5d %-10.2f %-10.2f %-10s %-10.2f\n", car.getCarId(), car.getManufacturer(), car.getModel(), car.getYear(), car.getMileage(), car.getEngineSize(), car.getGrade(), car.getPrice());
        }
    }

    //grade conditions
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
    
    public static void generateReportSortedByModel(ArrayList<Car> cars) {
        // validate user input
        if (cars == null || cars.isEmpty()) {
            System.out.println("The list of cars cannot be null or empty.");
            return;
        }

        //use collections method with comparator to merge sort the cars array
        Collections.sort(cars, new Comparator<Car>() {
            public int compare(Car car1, Car car2) {
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
        for (Car car : cars) {
            System.out.printf("%-10s %-15s %-15s %-5d %-10.2f %-10.2f %-10s %-10.2f\n", car.getCarId(), car.getManufacturer(), car.getModel(), car.getYear(), car.getMileage(), car.getEngineSize(), car.getGrade(), car.getPrice());
        }
    }


    public static void generateReportSortedByPrice(ArrayList<Car> cars) {
    	// validate user input
        if (cars == null || cars.isEmpty()) {
            System.out.println("The list of cars cannot be null or empty.");
            return;
        }

    	
    	
    	// sort the list by price
        Collections.sort(cars, new Comparator<Car>() {
            public int compare(Car car1, Car car2) {
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
        for (Car car : cars) {
            System.out.printf("%-10s %-15s %-15s %-5d %-10.2f %-10.2f %-10s %-10.2f\n", car.getCarId(), car.getManufacturer(), car.getModel(), car.getYear(), car.getMileage(), car.getEngineSize(), car.getGrade(), car.getPrice());
        }
    }
    
    public static Car findCarWithLowestMileage(ArrayList<Car> cars) {
    	// validate user input
        if (cars == null || cars.isEmpty()) {
            System.out.println("The list of cars cannot be null or empty.");
            return null;
        }
        for (Car car : cars) {
            if (car.getMileage() < 0) {
                System.out.println("Mileage value cannot be negative.");
                return null;
            }
        }
    	
    	
    	
    	// initialize the lowest mileage to be the first car in the list
        Car lowestMileageCar = cars.get(0);
        
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


    


    public static Car findCarWithLowestPrice(ArrayList<Car> cars) {
    	// validate user input
        if (cars == null || cars.isEmpty()) {
            System.out.println("The list of cars cannot be null or empty.");
            return null;
        }
        for (Car car : cars) {
            if (car.getMileage() < 0) {
                System.out.println("Mileage value cannot be negative.");
                return null;
            }
        }
    	   	    	
    	
    	// initialize the lowest price to be the first car in the list
        Car carWithLowestPrice = cars.get(0);
        
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

    public static void generateReportForCarByID(ArrayList<Car> cars) {
    	// validate user input
        if (cars == null || cars.isEmpty()) {
            System.out.println("Invalid input: the list of cars cannot be null or empty.");
            return;
        }

    	
    	
    	
    	Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the car ID: ");
        String carID = scanner.nextLine().trim();
        if (carID == null || carID.isEmpty()) {
            System.out.println("Invalid input: car ID cannot be null or empty.");
            return;
        }

        for (Car car : cars) {
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

    public static void generateManufacturerReport(ArrayList<Car> cars) {
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
        for (Car car : cars) {
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
    	
        ArrayList<Car> cars = new ArrayList<>();

        // add some cars to the list
        cars.add(new Car("001", "Toyota", "Prius", 2010, 100000, 1.8, "D", 8000));
        cars.add(new Car("002", "Honda", "Civic", 2012, 80000, 1.6, "C", 9000));
        cars.add(new Car("003", "Ford", "Mustang", 2015, 50000, 3.7, "B", 15000));
        cars.add(new Car("004", "Chevrolet", "Tahoe", 2018, 25000, 3.6, "A", 12000));
        cars.add(new Car("005", "Tesla", "Model S", 2019, 10000, 0.0, "B", 45000));
        cars.add(new Car("006", "Nissan", "Altima", 2016, 60000, 2.5, "D", 9500));
        cars.add(new Car("007", "BMW", "X5", 2017, 40000, 3.0, "A", 27000));
        cars.add(new Car("008", "Mercedes", "GLC", 2015, 75000, 2.0, "B", 18000));
        cars.add(new Car("009", "Mercedes", "GLC", 2015, 80000, 2.0, "B", 16000));
        cars.add(new Car("010", "Mercedes", "GLC", 2015, 99000, 2.0, "C", 12000));

        // print the details of each car in the list
      
        Car.generateReportForCarByID(cars);
	};
    
    
    
}