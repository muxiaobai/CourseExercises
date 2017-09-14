/**
 * Project Name:ProjectTest
 * File Name:ModeImpl.java
 * Package Name:Cryption2
 * Date:2017年9月14日下午9:27:56
 * Copyright (c) 2017, All Rights Reserved.
 *
*/

package Cryption2;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * ClassName:ModeImpl <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2017年9月14日 下午9:27:56 <br/>
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
public class ModeImpl implements Mode {
    Map<String, String> maplist = new HashMap<String, String>();
    public Mode mode;
    public void setMode(Mode mode) {
        this.mode = mode;
    }
    public Mode getMode() {
        return mode;
    }
    @Override
    public Map<String, String> getMaplist() {
        return maplist;
    }
    
    @Override
    public String getValue(String key) {
        return maplist.get(key);
    }
    @Override
    public Boolean containsKey(String key) {
        return maplist.containsKey(key);
    }
    
    @Override
    public  Boolean containsValue(String value){
        return maplist.containsValue(value);
    }
    @Override
    public String getKey(String value) {
        StringBuffer result =new StringBuffer();
        if(maplist.containsValue(value)){  
            Boolean flag=true;
            for (Entry<String, String> s :maplist.entrySet()) {  
                if(value.equals(s.getValue())&&flag){  
                    result.append(s.getKey().toString().toLowerCase());  
                    flag=false;
                    break;
                }  
            }  
        } 
        return result.toString();
    } 

}

