package divide.quick;

import java.io.*;
import java.util.function.BiPredicate;

/**
 * Задача на программирование: точки и отрезки
 *
 * ﻿
 *
 * В первой строке задано два целых числа 1≤n≤50000
 *  и 1≤m≤50000 — количество отрезков и точек на прямой, соответственно. Следующие n строк содержат по два целых числа
 *  ai и bi (ai≤bi) — координаты концов отрезков. Последняя строка содержит m целых чисел — координаты точек.
 *  Все координаты не превышают 10^8 по модулю. Точка считается принадлежащей отрезку, если она находится внутри
 *  него или на границе. Для каждой точки в порядке появления во вводе выведите, скольким отрезкам она принадлежит.
 */
public class PointsAndSegments {

    private int segmentsQuantity;
    private int pointsQuantity;
    private int[] segmentsBegin;
    private int[] segmentsEnd;
    private int[] points;

    public static final BiPredicate<Integer, Integer> CHECKER_LEFT = (key, p) -> key >= p;
    public static final BiPredicate<Integer, Integer> CHECKER_RIGHT = (key, p) -> key > p;

    public void read(Reader reader) throws IOException {
        BufferedReader br = new BufferedReader(reader);
        String[] firstLine = br.readLine().split(" ");
        segmentsQuantity = Integer.parseInt(firstLine[0]);
        segmentsBegin = new int[segmentsQuantity];
        segmentsEnd = new int[segmentsQuantity];
        pointsQuantity = Integer.parseInt(firstLine[1]);
        points = new int[pointsQuantity];
        for (int i = 0; i < segmentsQuantity; i++) {
            String[] borders = br.readLine().split(" ");
            segmentsBegin[i] = Integer.parseInt(borders[0]);
            segmentsEnd[i] = Integer.parseInt(borders[1]);
        }
        String[] pointsLine = br.readLine().split(" ");
        for (int i = 0; i < pointsQuantity; i++) {
            points[i] = Integer.parseInt(pointsLine[i]);
        }
    }

    public int[] result() {
        int[] resultPoints = new int[pointsQuantity];
        new QuickSort3().quickSort(segmentsBegin);
        new QuickSort3().quickSort(segmentsEnd);
        for (int i = 0; i < pointsQuantity; i++) {
            resultPoints[i] = lessThanByLeft(points[i]) - lessThanByRight(points[i]);
        }
        return resultPoints;
    }

    private int lessThanByLeft(int point) {
        return lessThanQuantity(segmentsBegin, point, (key, p) -> key >= p,
                0, segmentsQuantity - 1);
    }

    private int lessThanByRight(int point) {
        return lessThanQuantity(segmentsEnd, point, (key, p) -> key > p,
                0, segmentsQuantity - 1);
    }

    public int lessThanQuantity(int[] segmentPoints, int key, BiPredicate<Integer, Integer> checker,
                                int left, int right) {
        if (left == right) {
            return checker.test(key, segmentPoints[left]) ? left + 1 : left;
        }
        int middle = (left + right) / 2;
        if (checker.test(key, segmentPoints[middle])) {
            return lessThanQuantity(segmentPoints, key, checker, middle + 1, right);
        } else {
            return lessThanQuantity(segmentPoints, key, checker, left, middle);
        }
    }

}
