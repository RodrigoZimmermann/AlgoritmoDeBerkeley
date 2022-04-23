

import java.rmi.registry.*;

/**
 *
 * @author Rodrigo Luís Zimmermann
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            AlgorithmInterface serverOne = new AlgorithmInterfaceImpl(1);
            AlgorithmInterface serverTwo = new AlgorithmInterfaceImpl(2);
            AlgorithmInterface serverThree = new AlgorithmInterfaceImpl(3);
            AlgorithmInterface serverFour = new AlgorithmInterfaceImpl(4);
            AlgorithmInterface serverFive = new AlgorithmInterfaceImpl(5);
            AlgorithmInterface serversSix = new AlgorithmInterfaceImpl(6);

            Registry registryOne = LocateRegistry.getRegistry();
            Registry registryTwo = LocateRegistry.getRegistry();
            Registry registryThree = LocateRegistry.getRegistry();
            Registry registryFour = LocateRegistry.getRegistry();
            Registry registryFive = LocateRegistry.getRegistry();
            Registry registrySix = LocateRegistry.getRegistry();

            registryOne.rebind("AlgorithmInterfaceImplOne", serverOne);
            registryTwo.rebind("AlgorithmInterfaceImplTwo", serverTwo);
            registryThree.rebind("AlgorithmInterfaceImplThree", serverThree);
            registryFour.rebind("AlgorithmInterfaceImplFour", serverFour);
            registryFive.rebind("AlgorithmInterfaceImplFive", serverFive);
            registrySix.rebind("AlgorithmInterfaceImplSix", serversSix);

            System.out.println("computador 1 registrado:" + serverOne);
            System.out.println("computador 2 registrado:" + serverTwo);
            System.out.println("computador 3 registrado:" + serverThree);
            System.out.println("computador 4 registrado:" + serverFour);
            System.out.println("computador 5 registrado:" + serverFive);
            System.out.println("computador 6 registrado:" + serversSix);
        } catch (Exception ex) {
            System.out.println("Houve um erro: " + ex.getMessage());
        }
    }

}
