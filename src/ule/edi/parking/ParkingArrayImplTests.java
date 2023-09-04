package ule.edi.parking;

import static org.junit.Assert.*;

import org.junit.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import ule.edi.parking.vehicles.Car;
import ule.edi.parking.vehicles.Caravan;
import ule.edi.parking.vehicles.Motorcycle;

public class ParkingArrayImplTests {

	private DateFormat dformat = null;
	
	private Date parseLocalDate(String spec) throws ParseException {
        return dformat.parse(spec);
	}
	
	private Parking cheapParking;

	
	public ParkingArrayImplTests() {
		//	ayuda para las fechas
		dformat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		
	}

	
	@Before
	public void doSomethingBeforeEachTestMethod() {
		//	este método se ejecuta andes de cada método de test @Test
		//	antes de cada método de test, se crea un nuevo aparcamiento
		
		// crea un parking con 8 plazas y coste por minuto=0.01
		this.cheapParking = new ParkingArrayImpl(8, 0.01);
	}

		
	@Test
	public void testAlreadyParkedHere() throws Exception {
		
		//	ejemplo de método de prueba
		
		//	se crea un objeto sobre el que hacer pruebas :4 plazas y 1.0 de coste/minuto
		Parking p = new ParkingArrayImpl(4, 1.0);
		
		//	llega un coche con matrícula "A"; debe devolver true, porque no está en el aparcamiento
		Assert.assertTrue(p.addVehicle(new Car("A")));
		
		//	llega otra vez ese coche; debe devolver false, porque ya está en el aparcamiento
		Assert.assertFalse(p.addVehicle(new Car("A")));
	}

	@Test
	public void testCheapParking() throws Exception {
		
		//	ejemplo para trabajar con fechas
		
		Assert.assertTrue(cheapParking.addVehicleWithTimeOfEntry(new Motorcycle("A"), parseLocalDate("01/03/2017 16:50:00")));
		Assert.assertTrue(cheapParking.addVehicleWithTimeOfEntry(new Motorcycle("X"), parseLocalDate("01/03/2017 16:51:00")));

		//	a las 16:52:00 del mismo día, la moto A lleva 2' y la X 1', a 0.01 por minuto ; debe ser exáctamente 0.03
		Assert.assertEquals(0.03, cheapParking.getAmountWithReferenceDate(parseLocalDate("01/03/2017 16:52:00")), 0.0);
		Assert.assertEquals(0.05, cheapParking.getAmountWithReferenceDate(parseLocalDate("01/03/2017 16:53:00")), 0.0);
		Date dte = new Date();
		assertEquals(cheapParking.getAmountWithReferenceDate(dte), this.cheapParking.getCurrentAmount(), 0.0);
	}
	
	@Test
	public void testGetCostPerMinute() {
		assertEquals(0.01 , this.cheapParking.getCostPerMinute(), 0.0);
	}
	
	@Test
	public void testGetNumberOfSpaces() {
		assertEquals(8, this.cheapParking.getNumberOfSpaces());
	}
	
	@Test
	public void testGetUsedSpaces() {
		Parking p2 = new ParkingArrayImpl(4, 1.0);
		assertEquals(0, p2.getUsedSpaces());
		p2.addVehicle(new Motorcycle("A"));
		assertEquals(1, p2.getUsedSpaces());
		
	}
	
	@Test
	public void testRemoveVehicle() {
		Parking p2 = new ParkingArrayImpl(4, 1.0);
		Car car1 = new Car("A");
		p2.addVehicle(car1);
		Motorcycle moto1 = new Motorcycle("B");
		p2.addVehicle(moto1);
		p2.removeVehicle(moto1);
		assertEquals(1, p2.getUsedSpaces());
		
	}

	@Test
	public void testGetAvailableSpaces() {
		this.cheapParking.addVehicle(new Car("A"));
		assertEquals(7 , this.cheapParking.getAvailableSpaces());
	}
	
	@Test
	public void testGetNumberOfCars() {
		cheapParking.addVehicle(new Car("A"));
		cheapParking.addVehicle(new Car("B"));
		cheapParking.addVehicle(new Car("C"));
		assertEquals(3, cheapParking.getNumberOfCars());
	}
	
	@Test
	public void testGetNumberOfMotorcycles() {
		cheapParking.addVehicle(new Motorcycle("A"));
		cheapParking.addVehicle(new Motorcycle("B"));
		cheapParking.addVehicle(new Motorcycle("C"));
		assertEquals(3, cheapParking.getNumberOfMotorcycles());
	}
	@Test
	public void testGetNumberOfMotorcycles2() throws ParseException {
		Parking parkingEmpty = new ParkingArrayImpl(3, 1.0);
		parkingEmpty.addVehicleWithTimeOfEntry(new Motorcycle("A"), parseLocalDate("24/02/2023 16:51:00"));
		parkingEmpty.addVehicleWithTimeOfEntry(new Motorcycle("B"), parseLocalDate("24/02/2023 16:52:00"));
		parkingEmpty.addVehicleWithTimeOfEntry(new Motorcycle("C"), parseLocalDate("24/02/2023 16:53:00"));
		assertFalse(parkingEmpty.addVehicleWithTimeOfEntry(new Motorcycle("X"), parseLocalDate("01/03/2017 16:51:00")));
	}
	
	@Test
	public void testGetNumberOfCaravans() {
		cheapParking.addVehicle(new Caravan("A"));
		cheapParking.addVehicle(new Caravan("B"));
		cheapParking.addVehicle(new Caravan("C"));
		assertEquals(3, cheapParking.getNumberOfCaravans());
	}
	
	@Test
	public void testToString() throws ParseException {
		Parking p2 = new ParkingArrayImpl(4, 1.0);
		assertEquals("{\"Aparcamiento\":[null, null, null, null]}", p2.toString());
		
		p2.addVehicleWithTimeOfEntry(new Car("A"), parseLocalDate("24/02/2023 21:25:30"));
		assertEquals("{\"Aparcamiento\":[{ \"Vehículo\":{\"Tipo\":\"Car\", \"Matrícula\":\"A\"}, \"FechaDeEntrada\":\"24/02/2023 21:25:30\"}, null, null, null]}", p2.toString());
	
	}
	
	@Test
	public void testEqualsVehicles() {
		
		Car car1 = new Car("A");
		Car car2 = new Car("B");
		Car car3 = new Car("B");
		
		Motorcycle moto1 = new Motorcycle("A");
		Motorcycle moto2 = new Motorcycle("B");
		Motorcycle moto3 = new Motorcycle("B");
		
		Caravan carava1 = new Caravan("A");
		Caravan carava2 = new Caravan("B");
		Caravan carava3 = new Caravan("B");
		
		
		//tests cars
		assertTrue(car2.equals(car3));
		assertTrue(car2.equals(car2));
		
		assertFalse(car1.equals(car2));
		assertFalse(car1.equals(carava1));
		assertFalse(car1.equals(carava2));
		assertFalse(car1.equals(moto1));
		assertFalse(car1.equals(moto2));
		
		//test motos
		assertTrue(moto2.equals(moto3));
		
		assertFalse(moto1.equals(moto2));
		assertFalse(moto1.equals(car1));
		assertFalse(moto1.equals(car2));
		assertFalse(moto1.equals(carava2));
		assertFalse(moto1.equals(carava1));
		
		//test caravans
		assertTrue(carava1.equals(carava1));
		assertTrue(carava2.equals(carava3));
		
		assertFalse(carava1.equals(carava2));
		assertFalse(carava1.equals(moto1));
		assertFalse(carava1.equals(moto2));
		assertFalse(carava1.equals(car1));
		assertFalse(carava1.equals(car2));
	}
	
	@Test
	public void testGetResgistrationVehicles() {
		Car car1 = new Car("A");
		Motorcycle moto1 = new Motorcycle("B");
		Caravan carava1 = new Caravan("A");
		
		assertEquals("A", car1.getRegistration());
		assertEquals("B", moto1.getRegistration());
		assertEquals("A", carava1.getRegistration());
	}
	
	@Test
	public void testToStringVehicles() {
		Motorcycle moto1 = new Motorcycle("A");
		Caravan carava1 = new Caravan("A");
		assertEquals("{\"Tipo\":\"Motocicleta\", \"Matrícula\":\"A\"}", moto1.toString());
		assertEquals("{\"Tipo\":\"Caravana\", \"Matrícula\":\"A\"}", carava1.toString());
	}
	
	@Test
	public void testHashCodeVehicles() {
		Car car1 = new Car("A");
		Motorcycle moto1 = new Motorcycle("B");
		Caravan carava1 = new Caravan("A");
		
		assertEquals(2093774, car1.hashCode());
		assertEquals(747928690, moto1.hashCode());
		assertEquals(54637036, carava1.hashCode());
	}
	
	
}
