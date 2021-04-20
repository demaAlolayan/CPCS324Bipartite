/* 
Group 5 - Dimah Alolayan - Majd Gezan - Razan Aljuhani 
CPCS324 Project - implementation of the maximum bipartite matching algorithm
*/
package cpcs324bipartite;

import java.util.*;

public class CPCS324Bipartite {
    //creating graph of the applicants and their hospitals
    static class Graph {
        int hospitals;
        int applicants;
        int adjMatrix[][];
        
        // number of applicants that are able to attend hospital
        public Graph(int applicants, int hospitals) {
            this.hospitals = hospitals;
            this.applicants = applicants;
            adjMatrix = new int[applicants][hospitals];
        }

        public void canGoToHospital(int applicant, int hospital) {
            //add edge to the applicant that can have a place at the hospital
            adjMatrix[applicant][hospital] = 1;
        }
    }
    
    // method return the available hospitals for the applicants
    public int maxMatching(Graph graph) {
        int applicants = graph.applicants;
        int hospitals = graph.hospitals;   
        //array used to assign applicant to hospital
        int assign[] = new int[hospitals];    
        for (int i = 0; i < hospitals; i++)
            assign[i] = -1; //as a starting point, set all hospitals to available
        int hospitalCount = 0;
        //loop through all applicants
        for (int applicant = 0; applicant < applicants; applicant++) {  
            //array to determine if applicant has visited hospital
            boolean visited[] = new boolean[hospitals];
            //check to see if applicant can get into a hospital
            if (bipartiteMatch(graph, applicant, visited, assign)) {
                //increase the hospital count
                hospitalCount++;
            }
        }
        return hospitalCount;
    }

    // method return if the applicant can be matched
    boolean bipartiteMatch(Graph graph, int applicant, boolean visited[], int assign[]) {
        
        String[] applicants = {"Ahmed", "Mahmoud", "Emad", "Fatimah", "Kamel", "Nojood"};
        String[] hospitals = {"King Abdelaziz University", "King Fahd", "East Jeddah", "King Fahad Armed Forces", "King Faisal Specialist", "Ministry of National Guard"};

        //check each hospital for each applicant
        for (int hospital = 0; hospital < graph.hospitals; hospital++) {
            //check if applicant can attend specific hospital --> adjMatrix[applicant][hospital] == 1
            //if the applicant has not considered a given hospital --> visited[hospital]==false
            if (graph.adjMatrix[applicant][hospital] == 1 && !visited[hospital]) {
                //mark as hospital has been visited, which the applicant is considered for attending the hospital
                visited[hospital] = true;

               //check if hospital has not been assigned previously then it can be assigned to applicant
                int assignedApplicant = assign[hospital];

                if (assignedApplicant < 0 ) {
                    assign[hospital] = applicant; //assign hospital to applicant 
                    System.out.println("applicant " + applicants[applicant] + " -> " + hospitals[hospital]);
                    return true;
                }
            }
        } 
        System.out.println("------------------------------------------------------------");
        return false;
    }

    public static void main(String[] args) {
        //Construct Graph with applicants and hospitals
        int applicants = 6;
        int hospitals = 6;
        Graph graph = new Graph(applicants, hospitals);
        graph.canGoToHospital(0, 0);
        graph.canGoToHospital(0, 1);
        graph.canGoToHospital(1, 5);
        graph.canGoToHospital(2, 0);
        graph.canGoToHospital(2, 3);
        graph.canGoToHospital(3, 2);
        graph.canGoToHospital(4, 3);
        graph.canGoToHospital(4, 4);
        graph.canGoToHospital(5, 5);
        
        System.out.println("\tCPCS324 - Project(Phase three) - Maximum Bipartite Matching Algorithm");
        System.out.println("\t---------------------------------------------------------------------");
        CPCS324Bipartite m = new CPCS324Bipartite();
        System.out.println("Maximum number of applicants that could" +
                " get hospitals are: " + m.maxMatching(graph));
    }
    
}
