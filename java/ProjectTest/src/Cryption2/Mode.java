package Cryption2;

import java.util.Map;

/** 
 * 
 * @author zhang
 * @Date  2016年9月17日 下午3:45:30
 * @doing 两种加解密方法  摩斯密码 和小键盘
 */

public interface Mode {
    
    public Map<String, String> getMaplist();
    public String getValue(String key);
    public String getKey(String value);
    public Boolean containsValue(String value);
    public Boolean containsKey(String key);  
    
}