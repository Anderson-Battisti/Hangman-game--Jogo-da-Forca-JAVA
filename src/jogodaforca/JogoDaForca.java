package jogodaforca;

import javax.swing.JOptionPane;
//@author ander.battisti

public class JogoDaForca 
{
    public static void main(String[] args) 
    {
        char proceed = 's';
        String[] words = {"TOMATE", "COMPUTADOR", "BICICLETA", "ESPANHA", "CAFETERIA", "CADEIRA", "PALHETA", "HELICOPTERO",
            "VERMELHO", "LIVRO", "CADERNO", "CHOCOLATE", "TECLADO", "NUVENS", "DINHEIRO", "MELANCIA"};

        while (proceed == 's') 
        {
            String wrongLetters = "", hiddenWord = "";
            boolean wordRevealed = false;
            int remainingAttempts = 10;
            int randomIndex = (int) (Math.random() * (15 - 0) + 0);  //Generates a random int between 0 and 15

            for (int i = 0; i < words[randomIndex].length(); i++) //Generates the hiddenWord ("_ _ _ _ _") according to the character quantity of the choosen word
            {  
                hiddenWord += "-";
            }

            while (remainingAttempts > 0 && wordRevealed == false) 
            {
                char attempt = ' ';
                boolean gotRight = false, flag = true;
                String doll = getDollDrawing(remainingAttempts);  //Calls a method which contains the doll drawings compatible with the number of remaining attempts

                while (flag == true)
                {
                    flag = false;
                    attempt = Character.toUpperCase(Entrada.leiaChar("JOGO DA FORCA\n" + doll + "\n\nTentativas restantes: " + remainingAttempts + "\n\nPalavra: " + hiddenWord
                                                                 + "\nLetras erradas jogadas: " + wrongLetters + "\n\nTente uma letra"));

                    for (int i = 0; i < wrongLetters.length(); i++) 
                    {
                        if (attempt == wrongLetters.charAt(i) || !Character.isLetter(attempt)) 
                        {
                            flag = true;
                        }
                    }
                    
                    if (wrongLetters == "" && !Character.isLetter(attempt))
                    {
                        flag = true;
                    }
                }

                char hiddenChars[] = hiddenWord.toCharArray();   //Convert the String hiddenWord to an Array of chars

                for (int i = 0; i < words[randomIndex].length(); i++) //Checks if the user already got it right (to stop asking letters if the player got it right)
                { 
                    if (attempt == words[randomIndex].charAt(i)) 
                    {
                        hiddenChars[i] = attempt;
                        gotRight = true;
                    }
                }

                if (gotRight == false) 
                {
                    wrongLetters += (" " + attempt);
                    remainingAttempts--;
                    
                    if (remainingAttempts == 0) 
                    {
                        doll = getDollDrawing(remainingAttempts);
                        JOptionPane.showMessageDialog(null, doll + "\n\nVocê perdeu!\n\n");
                    }
                }

                hiddenWord = new String(hiddenChars);  //Convert the array hiddenChars back to a String 

                if (hiddenWord.equals(words[randomIndex])) 
                {
                    wordRevealed = true;
                    doll = getDollDrawing(remainingAttempts);
                    JOptionPane.showMessageDialog(null, doll + "\n\nParabéns, você acertou!\n\nA palavra era: " + hiddenWord);
                }
            }
            
            proceed = ' ';
            while (proceed != 's' && proceed != 'n') 
            {
                proceed = Character.toLowerCase(Entrada.leiaChar("Quer jogar de novo? [S/N]"));
            }
        }
        System.exit(0);
    }

    public static String getDollDrawing(int remainingAttempts) 
    {
        String dollDrawing = "";
        if (remainingAttempts == 10) {
            dollDrawing = "_____\n|   |\n|\n|\n|\n|";
        } else if (remainingAttempts == 9) {
            dollDrawing = "_____\n|   |\n|   O\n|\n|\n|";
        } else if (remainingAttempts == 8) {
            dollDrawing = "_____\n|   |\n|   O\n|   |\n|\n|";
        } else if (remainingAttempts == 7) {
            dollDrawing = "_____\n|   |\n|   O\n|  /|\n|\n|";
        } else if (remainingAttempts == 6) {
            dollDrawing = "_____\n|   |\n|   O\n|  /|\\\n|\n|";
        } else if (remainingAttempts == 5) {
            dollDrawing = "_____\n|   |\n|   O\n|  /|\\\n|  /\n|";
        } else if (remainingAttempts == 4) {
            dollDrawing = "_____\n|   |\n|   O\n|  /|\\\n|  / \\\n|";
        } else if (remainingAttempts == 3) {
            dollDrawing = "_____\n|   |\n|   O\n| _/|\\\n| _/ \\\n|";
        } else if (remainingAttempts == 2) {
            dollDrawing = "_____\n|   |\n|   O\n| _/|\\\n| _/ \\_\n|";
        } else if (remainingAttempts == 1) {
            dollDrawing = "_____\n|   |\n|   X\n| _/|\\\n| _/ \\_\n|";
        } else if (remainingAttempts == 0) {
            dollDrawing = "_____\n|   |\n|   X\n| _/|\\\n| _/ \\_\n|";
        }
        return dollDrawing;
    }
}
