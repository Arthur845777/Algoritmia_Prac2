package Fase3.P11.HashingCerrado;

public class Register<E>{
	private int key;
	private E name;

	public Register(int key, E name) {
		super();
		this.key = key;
		this.name = name;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public E getName() {
		return name;
	}

	public void setName(E name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Register [key=" + key + ", name=" + name + "]";
	}

}
