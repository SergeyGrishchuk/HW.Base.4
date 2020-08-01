package HomeWork4;

import java.util.Random;
import java.util.Scanner;

public class Main {
    static final int SIZE = 5; // размер поля
    static final int DOTS_TO_WIN = 4; // обозначаем количество точек для победы

    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static final char DOT_EMPTY = '.';

    static char[][] map;

    static Scanner sc = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        setMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (theWinningLine(DOT_X)) {
                System.out.println("Ты победил! ");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья!");
                break;
            }

            pcTurn();
            printMap();
            if (theWinningLine(DOT_O)) {
                System.out.println("Компьютер победил! ");
                break;
            }
            if (isFull()) {
                System.out.println("Ничья!");
                break;
            }

        }
    }


    public static void setMap() { // задаем поле карты
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() { //печатаем карту
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void humanTurn() { //ход человека
        int x, y;

        do {
            System.out.println("Input X, Y ");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(y, x));

        map[y][x] = DOT_X;
    }

    public static boolean isCellValid(int y, int x) {  // проверка правильности хода
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static void pcTurn() { // ход компьютера
        int x, y;

        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(y, x));

        map[y][x] = DOT_O;
    }

    public static boolean isFull() { // проверка полноты заполнения поля
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkTheLine(int ny, int nx, int ly, int lx, char sing) { // проверка выхода за границы поля
        if(ny + ly * (DOTS_TO_WIN - 1) > SIZE - 1 || nx + lx * (DOTS_TO_WIN - 1) > SIZE
                || ny + ly * (DOTS_TO_WIN - 1) < 0) {
            return false;
        }

        for (int i = 0; i < DOTS_TO_WIN; i++) {
            if(map[ny + i * ly][nx + i * lx] != sing){
                return false;
            }
        }
        return true;
    }

    public static boolean theWinningLine(char sing) { // проверка победы
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkTheLine(i, j, 0, 1, sing) || checkTheLine(i, j, 1, 0, sing) ||
                        checkTheLine(i, j, 1, 1, sing) || checkTheLine(i, j, -1, 1, sing)) {
                    return true;
                }
            }
        }
        return false;
    }
}
