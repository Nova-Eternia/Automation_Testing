package Mini_Project_01;

import java.io.*;

public class FileHandler {

    public static void save(BankAccount acc) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("bank.dat"));
        out.writeObject(acc);
        out.close();
    }

    public static BankAccount load() throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("bank.dat"));
        BankAccount acc = (BankAccount) in.readObject();
        in.close();
        return acc;
    }
}

