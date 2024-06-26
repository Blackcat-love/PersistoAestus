package com.example.financing;

import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class KeyGenerator {



//    缺少时间性
//    生成的密钥可以被重复使用！应该增加时间的因素，比如只能在十分钟内使用密钥。


    // 生成密钥方法
    public static String generateKey() {
        // 获取当前时间
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeString = dateFormat.format(currentTime);

        // 密钥加密对照表
        HashMap<Character, String> cipherTable = createCipherTable();

        // 替换时间字符串中的数字为密码表中对应的值
        StringBuilder encryptedKey = new StringBuilder();
        for (char c : timeString.toCharArray()) {
            String cipher = cipherTable.get(c);
            encryptedKey.append(cipher);
        }

        return encryptedKey.toString();
    }

    public static String generateKey_two(String timeString){
//        在这里可以加入时间验证来防止密钥重复 或者 通过租借服务器创建远程数据库所有被使用的密钥都将添加到数据库中，通过遍历远程数据库验证当前key有没有被使用过
//        使用过则返回false
//        目前先加入时间戳验证
        // 获取当前时间
        Date currentTime = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = dateFormat.format(currentTime);
//        计算时间差
// 获取参数时间的mm部分
        int paramMinute = Integer.parseInt(timeString.substring(10, 12));
// 获取当前时间的mm部分
        int currentMinute = Integer.parseInt(time.substring(10, 12));
        // 计算时间差
        int minuteDiff = Math.abs(currentMinute - paramMinute);
        if (minuteDiff <= 3) {
            System.out.println("该密钥目前可以使用");
        }else {
            System.out.println("该密钥已失效");
        }
        // 密钥加密对照表
        HashMap<Character, String> cipherTable = createCipherTable();
        // 替换时间字符串中的数字为密码表中对应的值
        StringBuilder encryptedKey = new StringBuilder();
        for (char c : timeString.toCharArray()) {
            String cipher = cipherTable.get(c);
            encryptedKey.append(cipher);
        }
        return encryptedKey.toString();
    }

    // 创建密码表
    private static HashMap<Character, String> createCipherTable() {
        HashMap<Character, String> cipherTable = new HashMap<>();

        // 添加密码表的对应关系
        cipherTable.put('0', "QW");
        cipherTable.put('1', "ER");
        cipherTable.put('2', "TY");
        cipherTable.put('3', "UI");
        cipherTable.put('4', "OP");
        cipherTable.put('5', "AS");
        cipherTable.put('6', "DF");
        cipherTable.put('7', "GH");
        cipherTable.put('8', "JK");
        cipherTable.put('9', "LZ");

        return cipherTable;
    }

    public static String decryptKey(String encryptedKey) {
//        解决调用length后程序报错空指针异常
        if (encryptedKey == null || encryptedKey.isEmpty()) {
            return "error"; // 或者 throw new IllegalArgumentException("encryptedKey cannot be null or empty");
        }

        // 密钥解密对照表
        HashMap<String, Character> decipherTable = createDecipherTable();

        // 根据密码表中的值替换加密密钥中的字符串为数字
        StringBuilder decryptedTime = new StringBuilder();
        for (int i = 0; i < encryptedKey.length(); i += 2) {
            String subString = encryptedKey.substring(i, i + 2);
            char plain = decipherTable.get(subString);
            decryptedTime.append(plain);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date currentTime;
        try {
            currentTime = dateFormat.parse(decryptedTime.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            currentTime = new Date();
        }

        return dateFormat.format(currentTime);
    }

    // 创建密码解密表
    private static HashMap<String, Character> createDecipherTable() {
        HashMap<String, Character> decipherTable = new HashMap<>();

        // 添加密码解密表的对应关系
        decipherTable.put("QW", '0');
        decipherTable.put("ER", '1');
        decipherTable.put("TY", '2');
        decipherTable.put("UI", '3');
        decipherTable.put("OP", '4');
        decipherTable.put("AS", '5');
        decipherTable.put("DF", '6');
        decipherTable.put("GH", '7');
        decipherTable.put("JK", '8');
        decipherTable.put("LZ", '9');

        return decipherTable;
    }


    public static boolean Verification_key(String t_key){
        if (t_key.isEmpty()){
            System.out.println("为空");
            return false;
        }else {
            String lock = "error";
            try {
                lock = EncryptionUtils.encrypt(KeyGenerator.generateKey_two(KeyGenerator.decryptKey(
                        Objects.requireNonNull(EncryptionUtils.decrypt(t_key))
                )));
            } catch (Exception e) {
                Log.e("Error","输入检测到无法识别字符");
            }
            if (t_key.equals(lock)){
                System.out.println("匹配成功");
                return true;
            }else {
                System.out.println("匹配失败");
                return false;
            }
        }
    }


    public static void printKey(){
        //        密码表测试
        String key = KeyGenerator.generateKey();
        Log.e("KEY",key);

        String encrypt = EncryptionUtils.encrypt(key);
        Log.e("加密Key",encrypt);

        String decrypt = EncryptionUtils.decrypt(encrypt);
        Log.e("解密",decrypt);

        String key_decrypt = KeyGenerator.decryptKey(decrypt);
        Log.e("源key",key_decrypt);
//        密码表测试END
    }



}
