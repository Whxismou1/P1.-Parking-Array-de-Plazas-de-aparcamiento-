package ule.edi.parking;

import java.util.Date;

import ule.edi.parking.vehicles.*;

/**
 * Implementa la interfaz parking.
 * 
 * Almacena las plazas de parking en un array de objetos. Las posiciones no
 * utilizadas son <code>null</code>, de forma que en el array pueden aparecer
 * "huecos" cuando se borran vehículos.
 * 
 * El constructor recibe como parámetro el número de plazas de parking. El
 * número de las plazas de aparcamiento empezará en 1.
 * 
 * @author profesor
 *
 */
public class ParkingArrayImpl implements Parking {

	private Space[] spaces;

	private double costPerMinute;

	public ParkingArrayImpl(int maxNumberOfSpaces, double costPerMinute) {
		this.spaces = new Space[maxNumberOfSpaces];
		this.costPerMinute = costPerMinute;
	}

	@Override
	public boolean addVehicleWithTimeOfEntry(Vehicle r, Date toe) {

		// TODO resolver según la especificación en la interfaz
		for (int i = 0; i < spaces.length; i++) {
			if (spaces[i] != null && spaces[i].getVehicle().equals(r)) {
			
				return false;
			}
		}

		for (int i = 0; i < spaces.length; i++) {
			if (spaces[i] == null) {
				spaces[i] = new Space(toe, r);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean addVehicle(Vehicle r) {
		// TODO resolver según la especificación en la interfaz, se puede llamar al
		// método anterior pasandole la fecha actual (new Date())
		Date date = new Date();
		return addVehicleWithTimeOfEntry(r, date);
	}

	@Override
	public int getUsedSpaces() {
		// TODO resolver según la especificación en la interfaz
		int count = 0;
		for (int i = 0; i < spaces.length; i++) {
			if (spaces[i] != null && spaces[i].getVehicle() != null) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int getNumberOfSpaces() {
		// TODO resolver según la especificación en la interfaz
		return spaces.length;
	}

	@Override
	public int getAvailableSpaces() {
		// TODO resolver según la especificación en la interfaz
		int count = 0;
		for (int i = 0; i < spaces.length; i++) {
			if (spaces[i] == null) {
				count++;
			}
		}
		return count;
	}

	@Override
	public void removeVehicle(Vehicle r) {
		// TODO resolver según la especificación en la interfaz

		for (int i = 0; i < spaces.length; i++) {

			if (spaces[i] != null && spaces[i].getVehicle().equals(r)) {
				spaces[i] = null;
			}
		}
	}

	@Override
	public int getNumberOfCars() {
		// TODO resolver según la especificación en la interfaz
		int count = 0;
		for (int i = 0; i < spaces.length; i++) {
			if (spaces[i] != null && spaces[i].getVehicle().getClass().equals(Car.class)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int getNumberOfMotorcycles() {
		// TODO resolver según la especificación en la interfaz
		int count = 0;
		for (int i = 0; i < spaces.length; i++) {
			if (spaces[i] != null && spaces[i].getVehicle().getClass().equals(Motorcycle.class)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public int getNumberOfCaravans() {
		// TODO resolver según la especificación en la interfaz
		int count = 0;
		for (int i = 0; i < spaces.length; i++) {
			if (spaces[i] != null && spaces[i].getVehicle().getClass().equals(Caravan.class)) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Devuelve una representación en texto del objeto.
	 * 
	 * Aquí se ha decidido usar un formato similar a
	 * <a href="http://www.json.org/">JSON</a>.
	 */
	@Override
	public String toString() {

		// Indicado para construir el resultado añadiendo varias cadenas
		StringBuffer buffer = new StringBuffer();

		// Las comillas dobles hay que protegerlas con \, para que no sean
		// interpretadas como el final del literal.
		buffer.append("{\"Aparcamiento\":[");
		for (int i = 0; i < spaces.length; i++) {

			if (spaces[i] != null) {
				// A su vez usa el toString() que definan las clases de Vehicle
				buffer.append(spaces[i].toString());
				buffer.append(", ");
			} else {
				buffer.append("null, ");
			}
		}
		// Quita la última coma añadida
		if (spaces.length > 0) {
			buffer.delete(buffer.length() - 2, buffer.length());
		}
		buffer.append("]}");

		return buffer.toString();
	}

	@Override
	public double getCurrentAmount() {
		// TODO Auto-generated method stub

		Date date = new Date();
		return (getAmountWithReferenceDate(date));

	}

	@Override
	public double getAmountWithReferenceDate(Date reference) {
		// TODO Auto-generated method stub
		
		long timeResult = 0;
		double result = 0;
		long timeReference = reference.getTime();;
		for (int i = 0; i < spaces.length; i++) {
			if (spaces[i] != null && spaces[i].getTimeOfEntry().getTime() < timeReference) {
				timeResult += ((reference.getTime()) - (spaces[i].getTimeOfEntry().getTime()));
			}
		}
		result = (double) ((timeResult / 60000.0) * this.costPerMinute);

		return result;
	}

	@Override
	public double getCostPerMinute() {
		// TODO Auto-generated method stub
		return this.costPerMinute;
	}
}
