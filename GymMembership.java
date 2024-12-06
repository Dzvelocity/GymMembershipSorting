import java.util.LinkedList;
import java.util.Scanner;

class Member {
    String name;
    int age;
    int duration;
    double costPerMonth = 150_000; // Biaya per bulan

    public Member(String name, int age, int duration) {
        this.name = name;
        this.age = age;
        this.duration = duration;
    }

    public double calculateTotalCost() {
        return duration * costPerMonth;
    }

    private String formatCurrency(double amount) {
        String amountStr = String.format("%.0f", amount);
        String result = "";
        int length = amountStr.length();
        
        for (int i = 0; i < length; i++) {
            if (i > 0 && (length - i) % 3 == 0) {
                result += ".";
            }
            result += amountStr.charAt(i);
        }
        return "Rp " + result;
    }

    @Override
    public String toString() {
        return String.format("Nama: %s | Umur: %d | Durasi: %d bulan | Total Biaya: %s",
                name, age, duration, formatCurrency(calculateTotalCost()));
    }
}

public class GymMembership {
    private LinkedList<Member> members = new LinkedList<>();

    private boolean isGreaterThan(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int minLength = Math.min(len1, len2);
        
        for (int i = 0; i < minLength; i++) {
            char c1 = Character.toLowerCase(str1.charAt(i));
            char c2 = Character.toLowerCase(str2.charAt(i));
            
            if (c1 != c2) {
                return c1 > c2;
            }
        }
        return len1 > len2;
    }

    public void addMember(String name, int age, int duration) {
        members.add(new Member(name, age, duration));
    }

    public void displayMembers() {
        if (members.isEmpty()) {
            System.out.println("Belum ada member yang terdaftar.\n");
            return;
        }
        System.out.println("Daftar Member:");
        for (Member member : members) {
            System.out.println(member);
        }
        System.out.println();
    }

    // Selection Sort untuk nama
    public void sortByName() {
        int n = members.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (!isGreaterThan(members.get(j).name, members.get(minIndex).name)) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Member temp = members.get(i);
                members.set(i, members.get(minIndex));
                members.set(minIndex, temp);
            }
        }
    }

    // Insertion Sort untuk umur
    public void sortByAge() {
        int n = members.size();
        for (int i = 1; i < n; i++) {
            Member key = members.get(i);
            int j = i - 1;
            
            while (j >= 0 && members.get(j).age > key.age) {
                members.set(j + 1, members.get(j));
                j = j - 1;
            }
            members.set(j + 1, key);
        }
    }

    // Bubble Sort untuk durasi
    public void sortByDuration() {
        int n = members.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (members.get(j).duration > members.get(j + 1).duration) {
                    Member temp = members.get(j);
                    members.set(j, members.get(j + 1));
                    members.set(j + 1, temp);
                }
            }
        }
    }

    public double calculateTotalIncome() {
        double totalIncome = 0;
        for (Member member : members) {
            totalIncome += member.calculateTotalCost();
        }
        return totalIncome;
    }

    private String formatCurrency(double amount) {
        String amountStr = String.format("%.0f", amount);
        String result = "";
        int length = amountStr.length();
        
        for (int i = 0; i < length; i++) {
            if (i > 0 && (length - i) % 3 == 0) {
                result += ".";
            }
            result += amountStr.charAt(i);
        }
        return "Rp " + result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GymMembership gym = new GymMembership();

        while (true) {
            System.out.println("=== Sistem Membership Gym ===");
            System.out.println("1. Tambahkan Member Baru");
            System.out.println("2. Lihat Daftar Member");
            System.out.println("3. Urutkan Member");
            System.out.println("4. Hitung Total Pendapatan Gym");
            System.out.println("5. Keluar");
            System.out.print("Pilih: ");
            
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); 
            } catch (Exception e) {
                System.out.println("Input tidak valid. Silakan masukkan angka.\n");
                scanner.nextLine(); 
                continue;
            }

            switch (choice) {
                case 1: 
                    System.out.print("Masukkan Nama Member: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan Umur Member: ");
                    int age = scanner.nextInt();
                    System.out.print("Masukkan Durasi Membership (bulan): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine(); 

                    gym.addMember(name, age, duration);
                    System.out.println("Member berhasil ditambahkan!\n");
                    break;

                case 2: 
                    gym.displayMembers();
                    break;

                case 3: 
                    System.out.println("Urutkan berdasarkan:");
                    System.out.println("1. Nama (A-Z) - Selection Sort");
                    System.out.println("2. Umur (Termuda ke Tertua) - Insertion Sort");
                    System.out.println("3. Durasi Membership (Terkecil ke Terbesar) - Bubble Sort");
                    System.out.print("Pilih: ");
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine(); 

                    switch (sortChoice) {
                        case 1:
                            gym.sortByName();
                            break;
                        case 2:
                            gym.sortByAge();
                            break;
                        case 3:
                            gym.sortByDuration();
                            break;
                        default:
                            System.out.println("Pilihan tidak valid.\n");
                            continue;
                    }
                    System.out.println("Data berhasil diurutkan!\n");
                    break;

                case 4: 
                    double totalIncome = gym.calculateTotalIncome();
                    System.out.printf("Total Pendapatan Gym: %s\n\n", 
                        gym.formatCurrency(totalIncome));
                    break;

                case 5:
                    System.out.println("Terima kasih telah menggunakan sistem ini!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.\n");
            }
        }
    }
}