package Fase3.P11.HashingAbierto;

public class Register{
	private int key;
	private String name;
	public Register(int key, String name) {
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "Register [key=" + key + ", name=" + name + "]";
	}
	
}
