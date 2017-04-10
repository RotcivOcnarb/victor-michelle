package pack;

public class Quadrilatero extends Poligono{
	
	public Quadrilatero(float base, float altura) {
		super(base, altura);
	}

	@Override
	public float area() {
		return base*altura;
	}

	@Override
	public float perimetro() {
		return base*2 + altura*2;
	}

}
