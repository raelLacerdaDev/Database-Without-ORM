// este codigo e uma conversao do gemini

public record Employee(
        int id,
        String name,
        double salary,
        int departmentId
) {
    @Override
    public String toString() {
        return id + ": " + name + ", $" + salary;
    }
}
