package pack;

public class Circulo extends Figura{

	float raio;
	
	public Circulo(float raio){
		this.raio = raio;
	}
	
	@Override
	public float area() {
		return (float) (Math.PI*raio*raio);
	}

	@Override
	public float perimetro() {
		return (float) (Math.PI*2*raio);
	}

}
