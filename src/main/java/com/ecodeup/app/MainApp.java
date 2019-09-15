package com.ecodeup.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.ecodeup.model.Vehiculo;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int opcion = 0;
		Scanner scanner = new Scanner(System.in);
		Vehiculo vehiculo;
		
		EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
		while (opcion!=5) {
			System.out.println("1. Crear un Vehiculo");
			System.out.println("2. Buscar un Vehiculo");
			System.out.println("3. Actualizar informacion de un vehiculo");
			System.out.println("4. Eliminar un vehiculo");
			System.out.println("5. Salir");
			System.out.println("Elija una opcion");
			
			opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				System.out.println("Digite el numero de serie del vehiculo: ");
				vehiculo = new Vehiculo();
				vehiculo.setId(null);
				
				vehiculo.setNumSerie(scanner.nextInt());
				scanner.nextLine();
				
				System.out.println("Digite la marca del vehiculo: ");
				vehiculo.setMarcaVehiculo(scanner.nextLine());
				
				System.out.println("Digite la placa del vehiculo: ");
				vehiculo.setPlacaVehiculo(scanner.nextLine());
				
				System.out.println("Digite el numero de serie del motor del vehiculo");
				vehiculo.setNumSerieMotor(scanner.nextLine());
				System.out.println(vehiculo);
				
				entity.getTransaction().begin();
				entity.persist(vehiculo);
				entity.getTransaction().commit();
				
				System.out.println("Vehiculo registrado...");
				System.out.println();
				break;
			
			case 2:
				System.out.println("Digite el id del vehiculo a buscar:");
				vehiculo = new Vehiculo();
				vehiculo = entity.find(Vehiculo.class, scanner.nextLong());
				if (vehiculo != null) {
					System.out.println(vehiculo);
					System.out.println();
				}else {
					System.out.println();
					System.out.println("Producto no encontrado... Lista de productos completa");
					List<Vehiculo> listaVehiculos = new ArrayList<>();
					Query query = entity.createQuery("Select v from Vehiculo v");
					listaVehiculos=query.getResultList();
					for (Vehiculo v : listaVehiculos) {
						System.out.println(v);
					}
					
					System.out.println();
					
				}
				break;
				
			case 3:
				System.out.println("Digite el id del vehiculo a actualizar:");
				vehiculo = new Vehiculo();
				
				vehiculo = entity.find(Vehiculo.class, scanner.nextLong());
				if (vehiculo != null) {
					System.out.println(vehiculo);
					
					System.out.println("Digite el numero de serie del vehiculo:");
					vehiculo.setNumSerie(scanner.nextInt());
					scanner.nextLine();
					
					System.out.println("Digite la marca del vehiculo");
					vehiculo.setMarcaVehiculo(scanner.nextLine());
					
					System.out.println("Digite la placa del vehiculo");
					vehiculo.setPlacaVehiculo(scanner.nextLine());
					
					System.out.println("Digite el numero de serie del motor");
					vehiculo.setNumSerieMotor(scanner.nextLine());
					
					entity.getTransaction().begin();
					entity.merge(vehiculo);
					entity.getTransaction().commit();
					System.out.println("El vehiculo a sido actualizado");
					System.out.println();
					
				}else {
					System.out.println("Producto no encontrado");
					System.out.println();
				}
				break;
			
			case 4:
				System.out.println("digite el id del prodcuto a eliminar: ");
				vehiculo = new Vehiculo();
				
				vehiculo = entity.find(Vehiculo.class, scanner.nextLong());
				if (vehiculo != null) {
					System.out.println(vehiculo);
					entity.getTransaction().begin();
					entity.remove(vehiculo);
					entity.getTransaction().commit();
					System.out.println("Producto eliminado");
				}else {
					System.out.println("Producto no encontrado");
				}
				break;
				
			case 5:entity.close();JPAUtil.shutdown();
				break;
				
			default:
				System.out.println("Opcion no valida\n");
				break;
			}
		}
		
	}

}
