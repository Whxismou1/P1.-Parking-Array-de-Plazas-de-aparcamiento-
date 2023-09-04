# Gestión de Parking en Java - Proyecto 1

Este es el primer proyecto de la asignatura de estructura de datos, enfocado en la gestión de un parking. El objetivo de este proyecto es implementar la interfaz `Parking`, que define las operaciones para administrar plazas de aparcamiento y calcular costos en un parking.

## Descripción del Proyecto

En este proyecto, se define una interfaz llamada `Parking` que contiene las operaciones básicas para gestionar un parking, como agregar vehículos, verificar plazas disponibles, calcular costos, y más. Además, se proporciona una clase de implementación llamada `ParkingArrayImpl` que cumple con la interfaz `Parking` y gestiona el parking utilizando un arreglo de plazas.

## Funcionalidades
Entre muchas otras puedes:
- **Agregar Vehículo con Hora de Entrada**: La función `addVehicleWithTimeOfEntry` permite registrar la llegada de un vehículo al parking y guardar su hora de entrada. Si ya existe un vehículo igual en el parking, la función no realiza ninguna acción.

- **Verificar Plazas Disponibles**: Puedes utilizar la función `getAvailableSpaces` para obtener el número de plazas disponibles en el parking.

- **Calcular Costo de Estacionamiento**: La función `calculateCost` te permite calcular el costo de estacionamiento para un vehículo en función del tiempo que ha estado en el parking.

- **Eliminar Vehículo**: Utiliza la función `removeVehicle` para eliminar un vehículo específico del parking.

- **Contar Tipos de Vehículos**: Las funciones `getNumberOfCars`, `getNumberOfMotorcycles` y `getNumberOfCaravans` te permiten contar la cantidad de vehículos de cada tipo en el parking.

## Uso

1. Clona este repositorio o descarga el proyecto como un archivo ZIP.

2. Abre el proyecto en tu entorno de desarrollo Java preferido.

3. Utiliza la clase `ParkingArrayImpl` para acceder a las funcionalidades de gestión del parking.

4. Sigue las operaciones definidas en la interfaz `Parking` para administrar las plazas de aparcamiento y calcular costos.

## Notas
Este es un proyecto muy básico para demostrar la implementación del cifrado César en Java. Puedes personalizarlo y expandirlo según tus necesidades.

## Contribución
Si deseas contribuir a este proyecto, ¡siéntete libre de hacerlo!

## Ejemplo de Uso

```java
public static void main(String[] args) {
    Parking parking = new ParkingArrayImpl(50, 0.10); // 50 plazas, 10 centimos por minuto
    int plazaDisponible = parking.getAvailableSpaces();
    System.out.println("Plazas disponibles: " + plazaDisponible);
}

