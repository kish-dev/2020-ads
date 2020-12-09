package ru.mail.polis.ads;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

class TopologicalSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int v = scanner.nextInt();
        int e = scanner.nextInt();
        TopologicalSort graph = new TopologicalSort(v);
        for (int i = 0; i < e; ++i) {
            graph.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        graph.topologicalSort();
        graph.printTopologicalSortedGraph();
    }

    private final int countOfVertexes;
    private final HashSet<Integer>[] vertexes;
    private LinkedList<Integer> listOfVertices;
    private boolean[] visited;
    private int[] colors;
    public boolean haveCycles = false;

    TopologicalSort(int v) {
        colors = new int[v];
        for (int i = 0; i < v; ++i) {
            colors[i] = 0;
        }
        visited = new boolean[v];
        for (int i = 0; i < v; ++i) {
            visited[i] = false;
        }
        listOfVertices = new LinkedList<>();
        countOfVertexes = v;
        vertexes = new HashSet[v];
        for (int i = 0; i < v; ++i)
            vertexes[i] = new HashSet();
    }

    void printTopologicalSortedGraph() {
        for (int i : listOfVertices) {
            System.out.print(i + 1 + " ");
        }
    }

    //добавляем ребро в граф
    void addEdge(int root, int outGoing) {
        vertexes[root - 1].add(outGoing - 1);
    }

    void visit(int v) {
        visited[v] = true;
        listOfVertices.addFirst(v);
    }

    void haveCycle(int v) {
        colors[v] = 1;
        for (int n : vertexes[v]) {
            if (colors[n] == 0) {
                haveCycle(n);
            }
            if (colors[n] == 1) {
                this.haveCycles = true;
                return;
            }
        }
        colors[v] = 2;
        return;
    }

    void dfs(int v) {
        colors[v] = 1;
        visited[v] = true;
        for (int n : vertexes[v]) {
            if (colors[n] == 0) {
                dfs(n);
            }
            if (colors[n] == 1) {
                System.out.println(-1);
                System.exit(0);
            }
        }
        colors[v] = 2;
        listOfVertices.addFirst(v);
    }

    void topologicalSort() {
        for (int i = 0; i < countOfVertexes; ++i) {
            if (colors[i] == 0) {
                dfs(i);
            }
        }

    }
}
