//������������ ������ 2. ������� ����. ������� 13 
//��� ������ ������� ������ ��������� � ������� ����������
//������� � ��������� ���� �������� �����.
import java.util.Scanner;

public class Lab2 {

    public static void main(String[] args) {
        String str;
        String vowels = "���������";
        String consonants = "����������������������������������������������";
        Scanner in = new Scanner(System.in);
        int v = 0, c = 0, len;

        System.out.println("Type your string");

        while(in.hasNextLine()) {
            str = in.nextLine();
            len = str.length();
            for(int i = 0; i < len; i++) {
                if (vowels.indexOf(str.charAt(i)) != -1)
                    v++;
                else if (consonants.indexOf(str.charAt(i)) != -1)
                    c++;
            }
            System.out.println("vowels: " + v + "\nconsonants: " + c);
            System.out.println("Type another string");
            v = c = 0;
        }
        in.close();
    }
}