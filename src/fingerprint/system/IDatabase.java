/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fingerprint.system;

/**
 *
 * @author stephane.kibonge
 */
public interface IDatabase {
    public String[] processCmd(String cmd, String data);
}
