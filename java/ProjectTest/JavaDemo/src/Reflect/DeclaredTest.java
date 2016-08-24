package Reflect;
public class DeclaredTest {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException {
	Class clazz=use.class;
	System.out.println("=====getConstructors()==============");
	for (int i = 0; i < clazz.getConstructors().length; i++) {
		System.out.println(clazz.getConstructors()[i]);
	}
	System.out.println("=====getDeclaredConstructors()==============");
	for (int i = 0; i < clazz.getDeclaredConstructors().length; i++) {
		System.out.println(clazz.getDeclaredConstructors()[i]);
	}
	System.out.println("=====getFields()==============");
	for (int i = 0; i < clazz.getFields().length; i++) {
		System.out.println(clazz.getFields()[i]);
	}
	System.out.println("=====getDeclaredFields()==============");
	for (int i = 0; i < clazz.getDeclaredFields().length; i++) {
		System.out.println(clazz.getDeclaredFields()[i]);
	}
	System.out.println("=====getMethods()==============");
	for (int i = 0; i < clazz.getMethods().length; i++) {
		System.out.println(clazz.getMethods()[i]);
	}
	
	System.out.println("=====getDeclaredMethods()==============");
	for (int i = 0; i < clazz.getDeclaredMethods().length; i++) {
		System.out.println(clazz.getDeclaredMethods()[i]);
	}
	}
}
class use {
	private Integer id;
	private String name;
	public String test;
	private use(Integer id,String name){
		this.id=id;
	}
	private use(){
	
	}
	public use(Integer id){
		this.id=id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	private void doSomething() {
		System.out.println("===private Method======");
	}
}
