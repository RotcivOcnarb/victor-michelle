package pack;

public class Quadrado extends Quadrilatero implements Diagonal{
	
	public Quadrado(float lado){
		super(lado, lado);
	}

	@Override
	public float diagonal(){
		return (float) Math.sqrt(2)*base;
	}
}
