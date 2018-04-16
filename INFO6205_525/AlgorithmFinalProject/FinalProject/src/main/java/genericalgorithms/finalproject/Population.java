/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericalgorithms.finalproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author vinya
 */
public class Population {

    private List<Genotype> gtList;
    private double cutoff;
    private List<City> baseOrder;

    public Comparator<Genotype> genoTypeComparator
            = new Comparator<Genotype>() {

        public int compare(Genotype gA, Genotype gB) {

            double fitnessA = gA.getPhenotype().getFitnessScore();
            double fitnessB = gB.getPhenotype().getFitnessScore();


            return gB.compareTo(gA);

            //descending order
            //return fruitName2.compareTo(fruitName1);
        }

    };

    public Population(double cutoff,
            List<City> baseOrder) {
        this.gtList = new ArrayList<>();
        this.cutoff = cutoff;
        this.baseOrder = baseOrder;

    }

    public List<Genotype> getGtList() {
        return gtList;
    }

    public void setGtList(List<Genotype> gtList) {
        this.gtList = gtList;
    }
    
    //Initializing the population
    public void initializePopulation(int populationSize,
            int phenoTypeLength) {

        Random r = new Random();

        for (int i = 0; i < populationSize; i++) {
            Genotype g = new Genotype(phenoTypeLength, i);
            this.gtList.add(g);
        }

        String[] geneBaseOrder = new String[phenoTypeLength];
        
        //Creating GeneBase Order
        for (int i = 0; i < baseOrder.size(); i++) {
            String p = String.format("%4s", Integer.toBinaryString(i)).replace(' ', '0');
            geneBaseOrder[i] = p;
        }
        
        //Using GeneBase order creating different Geneotype
        for (int i = 0; i < gtList.size(); i++) {
            Genotype gt = gtList.get(i);
            String[] arr = new String[geneBaseOrder.length];
            arr = Arrays.copyOf(geneBaseOrder, geneBaseOrder.length);
            Collections.shuffle(Arrays.asList(arr));

            gt.setRepresentation(arr);
            //Generating Phenotype using Geneotype
            gt.generatePhenotype(gt, baseOrder);

        }

    }
    //Sorting the population based on genoType
    public void sortPopulation() {
        Collections.sort(this.gtList, this.genoTypeComparator);
    }
    
    //RegenerationAndCulling
    public void regeneration() {
        Random r = new Random();

        int upperbound = (int) ((1 - this.cutoff) * this.gtList.size());
        List<Genotype> newGeneration = new ArrayList<Genotype>();
        for (int i = 0; i < this.gtList.size(); i++) {
            int firstParent = getRandomGenotypeIndex(upperbound, r);

            int secondParent = getRandomGenotypeIndex(upperbound, r);

            while (firstParent == secondParent) {
                secondParent = getRandomGenotypeIndex(upperbound, r);
            }
            Genotype child = crossover(firstParent, secondParent, i);
            newGeneration.add(child);
        }
        this.gtList = newGeneration;

    }
    
    //Crossover Method
    private Genotype crossover(int firstParent, int secondParent, int newMemberId) {
        Genotype genoFirst = this.gtList.get(firstParent);
        Genotype genoSecond = this.gtList.get(secondParent);
        int i = 0;
        String[] childRepresentation = new String[genoFirst.getRepresentation().length];
        String[] tempRepresentation = new String[childRepresentation.length / 2];
        for (i = 0; i < childRepresentation.length / 2; i++) {

            if (i < childRepresentation.length / 2) {

                childRepresentation[i] = genoFirst.getRepresentation()[i];
                tempRepresentation[i] = genoFirst.getRepresentation()[i];
            } else {

            }

        }
        int temp = 0;

        for (int j = 0; j < genoSecond.getRepresentation().length; j++) {
            temp = 0;
            for (int k = 0; k < tempRepresentation.length; k++) {
                if (genoSecond.getRepresentation()[j].equals(tempRepresentation[k])) {
                    temp = 1;
                    break;
                }

            }
            if (temp == 0) {
                childRepresentation[i] = genoSecond.getRepresentation()[j];
                i++;
            }
        }


        Genotype child = new Genotype(newMemberId);
        child.setRepresentation(childRepresentation);
        child.generatePhenotype(child, baseOrder);
        return child;
    }
    
//    public void mutation(){
//        //sorting based on the fitnessFunction
//        sortPopulation();
//        for(int i=0;i<gtList.size();i++){
//            List<Genotype> newMutation = new ArrayList<Genotype>();
//            if(i<gtList.size()/2){
//            String[] mutatedRepresentation = new String[gtList.get(i).getRepresentation().length];
//            Genotype gt = gtList.get(i);
//            Random r = new Random();
//            int randomFirstIndex = getRandomGenotypeIndex(gtList.get(i).getRepresentation().length/2, r);
//            int randomSecondIndex = getRandomGenotypeIndex(gtList.get(i).getRepresentation().length, r);
//            
//            String temp =gt.getRepresentation()[randomFirstIndex];
//            gt.getRepresentation()[randomFirstIndex]=gt.getRepresentation()[randomSecondIndex];
//            gt.getRepresentation()[randomSecondIndex]=temp;
//            newMutation.add(gt);
//            }
//        }
//    }

    private int getRandomGenotypeIndex(int upperbound, Random r) {

        int randInt = r.nextInt((upperbound));
        return randInt;
    }
}
