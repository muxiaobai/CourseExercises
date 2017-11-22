/**
 * Project Name:ProjectTest
 * File Name:HeapDump.java
 * Package Name:org.sun.demo.memoryTest
 * Date:2017年11月22日下午3:16:20
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package org.sun.demo.memoryTest;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:HeapDump <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年11月22日 下午3:16:20 <br/>
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class HeapDump {
        public static void main(String[] args) {  
            List<User> persons = new ArrayList<User> ();  
            while( 1>0){  
                persons.add( new User("liuhai","male",25));  
            }  
        }  
}
class User {  
    private String name;  
    private String sex;  
    private int age;  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public String getSex() {  
        return sex;  
    }  
    public void setSex(String sex) {  
        this.sex = sex;  
    }  
    public int getAge() {  
        return age;  
    }  
    public void setAge(int age) {  
        this.age = age;  
    }  
    public User( String name,String sex,int age){  
        this.name=name;  
        this.sex=sex;  
        this.age=age;  
    }  
}  

