/*
 * @copyright (c) 2014, by Vitaliy Tsutsman
 *
 * @author Vitaliy Tsutsman <vtsutsman@softjourn.com>
 */

package com.coffeine.virtuoso.module.security;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

/**
 * Created by vitaliy on 11/12/14.
 */
public class ConfigEncryptor {

    public static void main(String [] args) {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        standardPBEStringEncryptor.setAlgorithm("PBEWithMD5AndDES");
        standardPBEStringEncryptor.setPassword("secret");
        String encodedPass = standardPBEStringEncryptor.encrypt("virtuoso");
        System.out.println("Encrypted Password for admin is : "+encodedPass);
    }
}
