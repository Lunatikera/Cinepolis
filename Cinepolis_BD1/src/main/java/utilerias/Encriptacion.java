/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilerias;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 *
 * @author Chris
 */
public class Encriptacion {

    public static final int COST = 12;

    private Encriptacion() {
        throw new IllegalStateException("Utility class");
    }

    public static String encriptarPassword(String password) {
        return BCrypt.withDefaults().hashToString(COST, password.toCharArray());
    }

    public static String encriptarPassword(char[] password) {
        return BCrypt.withDefaults().hashToString(COST, password);
    }

    public static boolean verificarPasswordConHash(String password, String hashedPassword) {
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
        return result.verified;
    }
}
