package pack;

import java.util.ArrayList;

public class Geometria {
	
	ArrayList<Figura> figuras;
	
	public Geometria(){
		figuras = new ArrayList<Figura>();
		
		figuras.add(new Circulo(5));
		figuras.add(new Losango(4, 7));
		figuras.add(new Quadrado(6));
		figuras.add(new Retangulo(6, 8));
		figuras.add(new Triangulo(2, 4));
		
		for(Figura f : figuras){
			System.out.println(f.getClass().getSimpleName() + ": ");
			System.out.println("\tÁrea: " + f.area());
			System.out.println("\tPerímetro: " + f.perimetro());
		}
	}
	
	public static void main(String args[]){
		new Geometria();
	}

}
