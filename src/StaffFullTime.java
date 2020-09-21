public class StaffFullTime extends Staff {
    private double bonus;
    private double forfeit;
    private double salary;


    public StaffFullTime(int id, String name, int age, String phone, String email, double salary) {
        super(id, name, age, phone, email);
        this.salary = salary;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getForfeit() {
        return forfeit;
    }

    public void setForfeit(double forfeit) {
        this.forfeit = forfeit;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double realSalary(){
        return salary + (bonus - forfeit);
    }

    @Override
    public String toString() {
        String salaryFormat = String.format("%1$,.0f",salary);
        return "StaffFullTime{ "+ super.toString() + " bonus= " + bonus + ", forfeit= " + forfeit + ", salary = " + salaryFormat + '}';
    }
}