

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

/**
 *
 * @author Rodrigo Luís Zimmermann
 */
public class AlgorithmInterfaceImpl extends UnicastRemoteObject implements AlgorithmInterface {

    public LocalTime localTime;
    public int client;

    public AlgorithmInterfaceImpl(int client) throws RemoteException {
        this.client = client;
        Random generator = new Random();
        this.localTime = LocalTime.MIN.plusMinutes(generator.nextLong());
        System.out.println("Tempo do cliente " + this.client + ": " + this.localTime);
    }

    @Override
    public long getTimeDifference(LocalTime lt) throws RemoteException {
        long retorno = lt.until(localTime, ChronoUnit.MINUTES);
        System.out.println("Diferenca cliente  " + client + ": " + retorno);
        return retorno;
    }

    @Override
    public void timeAdjustment(long time) throws RemoteException {
        LocalTime t = localTime.plusMinutes(time);
        System.out.println("Tempo em minutos para ser ajustado: " + time);
        System.out.println("Novo tempo do cliente " + client + ": " + t);
    }

}
