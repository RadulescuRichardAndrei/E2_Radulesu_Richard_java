package Data;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static utils.Hashing.passwordHashing;

public class ClientData {
    private String username;
    private byte[] passwordHash;

    public ClientData(ClientData cl){
        username=cl.getUsername();
        passwordHash= cl.getPasswordHash();
    }

    public ClientData(String username, byte[] passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public ClientData(String username, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
    this.username=username;
    passwordHash=passwordHashing(password);
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(byte[] passwordHash) {
        this.passwordHash = passwordHash;
    }

    public boolean authenticateByHash(byte[] hash){
        if(Arrays.equals(hash,passwordHash))
            return true;
        return false;
    }
    public boolean authenticateByPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        if(passwordHash.equals(passwordHashing(password)))
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "username='" + username + '\'' +
                ", passwordHash=" + Arrays.toString(passwordHash) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientData that = (ClientData) o;
        return Objects.equals(username, that.username) && Objects.equals(passwordHash, that.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, passwordHash);
    }

}
