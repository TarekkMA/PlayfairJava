package com.tarekkma;

public class Playfair {

    public static void enc(String plainText, String key) {

        //1- create 5*5 arr
        //   no dup
        //   no j
        //   start with the key chars then the alphabet
        char[][] matrix = new char[5][5];
        String matrixChars = "";
        String charset = key + "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < charset.length(); i++) {
            char c = charset.charAt(i);
            if (c != 'j' && matrixChars.indexOf(c) == -1) {
                matrixChars += c;
            }
            if (matrixChars.length() >= 25)
                break;
        }

        System.out.println("GRID:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                matrix[i][j] = matrixChars.charAt(i * 5 + j);
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        //2-pre pros plaintext
        StringBuilder plaintextBuilder = new StringBuilder(plainText);
        for (int i = 0; i < plaintextBuilder.length() - 1; i += 2) {
            char c1 = plaintextBuilder.charAt(i);
            char c2 = plaintextBuilder.charAt(i + 1);

            if (c1 == c2) {
                if (c1 == 'x') {
                    plaintextBuilder.insert(i + 1, 'z');
                } else {
                    plaintextBuilder.insert(i + 1, 'x');
                }
            }
        }

        if (plaintextBuilder.length() % 2 != 0) {
            if (plaintextBuilder.charAt(plaintextBuilder.length() - 1) == 'x') {
                plaintextBuilder.append('z');
            } else {
                plaintextBuilder.append('x');
            }
        }

        System.out.println("MESSAGE:");
        for (int i = 0; i < plaintextBuilder.length() - 1; i += 2) {
            char c1 = plaintextBuilder.charAt(i);
            char c2 = plaintextBuilder.charAt(i + 1);
            System.out.print(c1+""+c2+" ");
        }
        System.out.println();

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < plaintextBuilder.length() - 1; i += 2) {
            char c1 = plaintextBuilder.charAt(i);
            char c2 = plaintextBuilder.charAt(i + 1);

            int[] pos1 = findPos(matrix, c1);
            int[] pos2 = findPos(matrix, c2);

            char res1, res2;
            if (pos1[0] == pos2[0]) {
                res1 = matrix[pos1[0]][Math.floorMod(pos1[1] + 1, 5)];
                res2 = matrix[pos2[0]][Math.floorMod(pos2[1] + 1, 5)];
            } else if (pos1[1] == pos2[1]) {
                res1 = matrix[Math.floorMod(pos1[0] + 1, 5)][pos1[1]];
                res2 = matrix[Math.floorMod(pos2[0] + 1, 5)][pos2[1]];
            } else {
                res1 = matrix[pos1[0]][pos2[1]];
                res2 = matrix[pos2[0]][pos1[1]];
            }
            result.append(res1);
            result.append(res2);
        }

        System.out.println("DEC:");
        System.out.println(result.toString());
    }

    static int[] findPos(char[][] matrix, char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c)
                    return new int[]{i, j};
            }
        }
        return new int[]{};
    }


}
