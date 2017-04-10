package pack;

public class Retangulo extends Quadrilatero implements Diagonal{

	public Retangulo(float base, float altura) {
		super(base, altura);
	}
	
	@Override
	public float diagonal(){
		return (float) Math.sqrt(base*base + altura*altura);
	}

}
