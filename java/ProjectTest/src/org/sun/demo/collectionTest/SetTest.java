package org.sun.demo.collectionTest;

import java.util.HashSet;
import java.util.Set;

/** 
 * 
 * @author zhang
 * @Date  2016年7月20日 下午2:32:48
 * @doing 
 */

public class SetTest {
	public static void main(String[] args) {
		Set<Person> persons=new HashSet<>();
		Person person1=new Person();
		Person person2=new Person();
		person1.setId(1);
		persons.add(person1);
		persons.add(person2);
		System.out.println(person1.hashCode());
		System.out.println(person2.hashCode());
		System.out.println(persons);
		
	}
}
class Person {
	private Integer id;
	private String name;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		System.out.println("==========");
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		System.out.println("==========equals");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((id == null) ? 0 : id.hashCode());
//		result = prime * result + ((name == null) ? 0 : name.hashCode());
//		return result;
//	}
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		Person other = (Person) obj;
//		if (id == null) {
//			if (other.id != null)
//				return false;
//		} else if (!id.equals(other.id))
//			return false;
//		if (name == null) {
//			if (other.name != null)
//				return false;
//		} else if (!name.equals(other.name))
//			return false;
//		return true;
//	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}
	
}

