abstract class Employee {
    protected String name;
    protected int age;
    protected double salary;
    protected static int employeeCount = 0;

    public Employee() {
        employeeCount++;
    }

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        employeeCount++;
    }

    public abstract void work();
    public void displayInfo() {
        System.out.println("Имя: " + name);
        System.out.println("Возраст: " + age);
        System.out.println("Зарплата: " + salary);
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public static void showEmployeeCount() {
        System.out.println("Количество созданных сотрудников: " + employeeCount);
    }
}

class Administrator extends Employee {
    private String systemArea;
    private boolean hasRootAccess;

    public Administrator() {
        super();
    }

    public Administrator(String name, int age, double salary, String systemArea, boolean hasRootAccess) {
        super(name, age, salary);
        this.systemArea = systemArea;
        this.hasRootAccess = hasRootAccess;
    }

    @Override
    public void work() {
        System.out.println(name + " администрирует систему в зоне: " + systemArea);
    }
    public void restartServer() {
        System.out.println(name + " перезапускает сервер");
    }

    public String getSystemArea() { return systemArea; }
    public void setSystemArea(String systemArea) { this.systemArea = systemArea; }
    public boolean isHasRootAccess() { return hasRootAccess; }
    public void setHasRootAccess(boolean hasRootAccess) { this.hasRootAccess = hasRootAccess; }
}

class Programmer extends Employee {
    private String language;
    private int experience;

    public Programmer() {
        super();
    }

    public Programmer(String name, int age, double salary, String language, int experience) {
        super(name, age, salary);
        this.language = language;
        this.experience = experience;
    }

    @Override
    public void work() {
        System.out.println(name + " пишет код на " + language);
    }
    public void debugCode() {
        System.out.println(name + " отлаживает программу");
    }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }
    public int getExperience() { return experience; }
    public void setExperience(int experience) { this.experience = experience; }
}

class Manager extends Employee {
    private int teamSize;
    private String projectName;

    public Manager() {
        super();
    }

    public Manager(String name, int age, double salary, int teamSize, String projectName) {
        super(name, age, salary);
        this.teamSize = teamSize;
        this.projectName = projectName;
    }

    @Override
    public void work() {
        System.out.println(name + " управляет проектом: " + projectName);
    }
    public void holdMeeting() {
        System.out.println(name + " проводит собрание с командой из " + teamSize + " человек.");
    }

    public int getTeamSize() { return teamSize; }
    public void setTeamSize(int teamSize) { this.teamSize = teamSize; }
    public String getProjectName() { return projectName; }
    public void setProjectName(String projectName) { this.projectName = projectName; }
}

public class laba2 {
    public static void main(String[] args) {
        Administrator admin = new Administrator("Алексей", 30, 85000, "Серверная", true);
        Programmer prog = new Programmer("Мария", 25, 120000, "Java", 3);
        Manager manager = new Manager("Иван", 40, 150000, 8, "CRM-система");

        admin.displayInfo();
        admin.work();
        admin.restartServer();
        prog.displayInfo();
        prog.work();
        prog.debugCode();
        manager.displayInfo();
        manager.work();
        manager.holdMeeting();
        Employee.showEmployeeCount();
    }
}