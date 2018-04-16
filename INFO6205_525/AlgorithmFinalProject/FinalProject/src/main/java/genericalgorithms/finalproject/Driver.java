package genericalgorithms.finalproject;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author vinya
 */
public class Driver {

    public static void main(String[] args) {

        int generationCount = 0;
        Scanner sc = new Scanner(System.in);

        //Enter values more than 4 and less than 16 - PhenoTypeLength
        System.out.println("Please enter the Number of Cities: ");
        int phenoTypeLength = sc.nextInt();

        System.out.println("Please enter Population Number: ");
        int populationSize = sc.nextInt();
        

        List<City> baseOrder = getBaseOrder(phenoTypeLength);
        double avgFitnessScorePreGeneration = 0.0;
        Population population = new Population(0.1, baseOrder);
        population.initializePopulation(populationSize,phenoTypeLength);
        population.sortPopulation();
        System.out.println("Best possible Result in each Generation");
        System.out.println("Generation 0" + " " + population.getGtList().get(0).getPhenotype().toString());
        
        for (int i = 1; i < 50; i++) {
            generationCount = i;
            
            double avgFitnessScore = 0.0;
            double sumOfFitnessScores = 0.0;
            for (int j = 0; j < populationSize; j++) {
                sumOfFitnessScores += population.getGtList().get(j).getPhenotype().getFitnessScore();
            }
            avgFitnessScore = ((sumOfFitnessScores / populationSize));

            //checking for Increase in FitnessScore for every 5 generations
            if ((i % 5 == 0) && avgFitnessScore < (avgFitnessScorePreGeneration + (avgFitnessScorePreGeneration * 0.005))) {
                //Existing if the fitness score is not increasing more than 0.5%
                System.out.println("Fitness score is changing marginally hence terminating the code");
                System.exit(0);
            } else {
                avgFitnessScorePreGeneration = avgFitnessScore;
                population.regeneration();
                population.sortPopulation();
                System.out.println("Generation " + generationCount + " " + population.getGtList().get(0).getPhenotype().toString());
            }
        }

    }

    //BaseOrder
    public static List<City> getBaseOrder(int phenoTypeLength) {
        Random r = new Random();
        double min = -100;
        double max = 100;
        List<City> cityList = new ArrayList<City>();
        for (int i = 0; i < phenoTypeLength; i++) {
            double x = ThreadLocalRandom.current().nextDouble(min, max + 1);
            double y = ThreadLocalRandom.current().nextDouble(min, max + 1);
            
            City city = new City(x,y,"City"+i, i);
            cityList.add(city);
        }
        return cityList;


    }

}
