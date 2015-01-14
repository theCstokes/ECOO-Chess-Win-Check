/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ecoo4;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Chris
 */
public class ECOO4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println(verify(("BQRRKRNN").toCharArray()));
        Scanner fin = new Scanner(new File("DATA41.txt"));
        for (int i = 0; i < 10; ++i) {
            String input = fin.nextLine();
            System.out.println(solve(input));
        }
    }
    
    public static int solve(String input) {
        char[] pieces = new char[8];
        for (int i = 0; i < 8; ++i) {
            pieces[i] = input.charAt(i);
        }
        ArrayList<Character> available = new ArrayList<>();
        available.add('K');
        available.add('Q');
        available.add('R');
        available.add('R');
        available.add('N');
        available.add('N');
        available.add('B');
        available.add('B');
        for (int i = 0; i < 8; ++i) {
            if (pieces[i] != '_') {
                if (!available.remove((Character) pieces[i])) {
                    return 0;
                }
            }
        }
        
        return realSolve(pieces, available);
    }
    
    public static int realSolve(char[] pieces, ArrayList<Character> available) {
        if (available.isEmpty()) {
            return verify(pieces) ? 1 : 0;
        }
        
        int sum = 0;
        for (int i = 0; i < 8; ++i) {
            if (pieces[i] == '_') {
                ArrayList<Character> done = new ArrayList<>();
                for (int j = 0; j < available.size(); ++j) {
                    if (done.contains(available.get(j))) {
                        
                    }
                    else {
                        pieces[i] = available.get(j);
                        available.remove(j);
                        sum += realSolve(pieces, available);
                        available.add(pieces[i]);
                        done.add(pieces[i]);
                    }
                }
                pieces[i] = '_';
            }
        }
        return sum;
    }
    
    public static boolean verify(char[] pieces) {
        int nR = 0;
        int nN = 0;
        int nB = 0;
        int nK = 0;
        int nQ = 0;
        int b1 = -1;
        int foundRook = 0;
        for (int i = 0; i < 8; ++i) {
            if (pieces[i] == 'K') {
                if (foundRook == 1) {
                    foundRook = 2;
                }
                nK += 1;
            }
            else if (pieces[i] == 'Q') {
                nQ += 1;
            }
            else if (pieces[i] == 'B') {
                nB += 1;
                if (b1 != -1) {
                    if (Math.abs(b1 - i) % 2 == 0) {
                        return false;
                    }
                }
                else {
                    b1 = i;
                }
            }
            else if (pieces[i] == 'N') {
                nN += 1;
            }
            else if (pieces[i] == 'R') {
                if (foundRook == 0) {
                    foundRook = 1;
                }
                else if (foundRook == 1) {
                    return false;
                }
                nR += 1;
            }
        }
        if (nK != 1 || nQ != 1 || nB != 2 || nN != 2 || nR != 2) {
            return false;
        }
        
        return true;
    }
    
}
