package ru.mail.polis.ads;


import java.util.*;

class Main {
    private final int countOfVertexes;
    private final ArrayList<Edge> edges; //список рёбер

    int[] d;

    private class Edge { //класс, представляющий вершину
        int root;
        int outGoing;
        int cost;

        Edge(int root, int outGoing, int cost) {
            this.root = root;
            this.outGoing = outGoing;
            this.cost = cost;
        }
    }

    Main(int v) {
        countOfVertexes = v;
        edges = new ArrayList<>(v);
        d = new int[v];
    }

    //добавляем ребро в граф
    void addEdge(int root, int outGoing, int cost) {
        edges.add(new Edge(root, outGoing, cost));
    }

    void fordBellman(int s) {
        Arrays.fill(d, 30000);
        d[s] = 0;
        for (int i = 0; i < countOfVertexes - 1; ++i) {
            for (int j = 0; j < edges.size(); ++j) {
                if(d[edges.get(j).root] != 30000)
                if (d[edges.get(j).outGoing] >
                        d[edges.get(j).root] + edges.get(j).cost) {
                    d[edges.get(j).outGoing] =
                            d[edges.get(j).root] + edges.get(j).cost;

                }
            }
        }
    }

    //метод, который печает вершины с кратчайшим расстоянием
    void printWeightOfShortestPath(int s) {
        for (int i = 0; i < countOfVertexes; ++i) {
            if(d[i] == 29990)
                System.out.print("30000 ");
            else
                System.out.print(d[i] + " ");
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int v = scanner.nextInt();
        int e = scanner.nextInt();

        Main graph = new Main(v);
        for (int i = 0; i < e; ++i) {
            graph.addEdge(scanner.nextInt() - 1, scanner.nextInt() - 1, scanner.nextInt());
        }
        int s = 0;
        graph.fordBellman(s);
        graph.printWeightOfShortestPath(s);
    }
}
