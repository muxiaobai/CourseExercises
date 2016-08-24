package Memory;
public class MemoryDemo {
	public MemoryDemo() {
	}
	public String name() {
		return "无参数";
	}
	public String name(char name) {
		return "char:"+name;
	}
	public String name(String name) {
		return "String:"+name;
	}
	public String name(byte name) {
		return "byte:"+name;	
	}
	public String name(short name) {
		return "short:"+name;	
	}
	public String name(int name) {
		return "int:"+name;
	}
	public String  name(long name) {
		return "long:"+name;
	}
	public String name(float name) {
		return "float:"+name;
	}
	public String name(double name) {
		return "double:"+name;
	}
	public String name(boolean name) {
		return "boolean:"+name;
	}
	public static void main(String[] args) {
		MemoryDemo memoryDemo=new MemoryDemo();
		System.out.println(memoryDemo.name("zhang"));
		System.out.println(memoryDemo.name());
		System.out.println(memoryDemo.name(1));
		System.out.println(memoryDemo.name(1L));
		System.out.println(memoryDemo.name(1F));
		System.out.println(memoryDemo.name(1D));
		System.out.println(memoryDemo.name(true));
		
	}
}
