import java.util.Arrays;
import java.util.Date;

public class Draw {
    int id;
    Date date;
    int [] numbers;

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int[] getNumbers() {
        return numbers;
    }

    public Draw(int id, Date date, int[] numbers) {
        this.id = id;
        this.date = date;
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id + ". ");
        if(date.getDate()<10){
            sb.append(0);
        }
        sb.append(date.getDate() + ".");
        if(date.getMonth()<10){
            sb.append(0);
        }
        sb.append(date.getMonth() + ".");
        sb.append(date.getYear() + " " );

        for (int number : numbers) {
            sb.append(number +",");
        }
        sb.delete((sb.length()-1),sb.length());

        return sb.toString();
    }
}
