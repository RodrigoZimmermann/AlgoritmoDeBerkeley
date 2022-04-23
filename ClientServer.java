

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Rodrigo Luís Zimmermann
 */
public class ClientServer {

    public static LocalTime localTime;

    public static void main(String[] args) {
        Registry registry;
        try {
            Random generator = new Random();
            LocalTime time = LocalTime.MIN.plusMinutes(generator.nextLong());
            localTime = time;
            System.out.println("Tempo do servidor:" + localTime);

            registry = LocateRegistry.getRegistry("localhost");

            AlgorithmInterface aOne = (AlgorithmInterface) registry.lookup("AlgorithmInterfaceImplOne");
            AlgorithmInterface aTwo = (AlgorithmInterface) registry.lookup("AlgorithmInterfaceImplTwo");
            AlgorithmInterface aThree = (AlgorithmInterface) registry.lookup("AlgorithmInterfaceImplThree");
            AlgorithmInterface aFour = (AlgorithmInterface) registry.lookup("AlgorithmInterfaceImplFour");
            AlgorithmInterface aFive = (AlgorithmInterface) registry.lookup("AlgorithmInterfaceImplFive");
            AlgorithmInterface aSix = (AlgorithmInterface) registry.lookup("AlgorithmInterfaceImplSix");

            System.out.println("Computador encontrado: " + aOne);
            System.out.println("Computador encontrado: " + aTwo);
            System.out.println("Computador encontrado: " + aThree);
            System.out.println("Computador encontrado: " + aFour);
            System.out.println("Computador encontrado: " + aFive);
            System.out.println("Computador encontrado: " + aSix);

            ArrayList<AlgorithmInterface> timer = new ArrayList<>();
            timer.add(aOne);
            timer.add(aTwo);
            timer.add(aThree);
            timer.add(aFour);
            timer.add(aFive);
            timer.add(aSix);

            System.out.println("Solicitando as horas para os clientes");
            clientsTimerAdjusments(timer);

        } catch (RemoteException | NotBoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void clientsTimerAdjusments(ArrayList<AlgorithmInterface> timer) throws RemoteException {
        long average = 0;
        ArrayList<Long> diferrence = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            long lt = timer.get(i).getTimeDifference(localTime);
            diferrence.add(lt);
            average = average + lt;
        }
        average = average / 7;
        System.out.println("Media em minutos: " + average);
        System.out.println("Chamando o ajuste de tempo dos clientes");
        for (int i = 0; i < 6; i++) {
            timer.get(i).timeAdjustment(-diferrence.get(i) + average);
        }
        System.out.println("Chamando o ajuste de tempo do servidor");
        timeAdjustment(average);
    }

    private static void timeAdjustment(long time) throws RemoteException {
        LocalTime t = localTime.plusMinutes(time);
        System.out.println("Tempo em minutos para ser ajustado: " + time);
        System.out.println("Novo tempo do servidor: " + t);
    }
}
