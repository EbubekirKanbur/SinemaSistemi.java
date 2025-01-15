import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Temel sınıf
class BaseEntity {
    int id;
    String name;
}

// Film sınıfı
class Film extends BaseEntity {
    int duration; // Süre
    String genre; // Tür
}

// Müşteri sınıfı
class Musteri extends BaseEntity {
    int filmId; // İzlediği film ID
}

// Salon sınıfı
class Salon extends BaseEntity {
    int filmId; // Gösterimdeki film ID
    String showTime; // Gösterim saati
    List<Integer> customerIds = new ArrayList<>(); // Kaydedilen müşteriler
}

// IKayit interface'i
interface IKayit {
    void kaydet();
    void listele();
}

public class SinemaSistemi implements IKayit {
    static List<Film> filmler = new ArrayList<>();
    static List<Musteri> musteriler = new ArrayList<>();
    static List<Salon> salonlar = new ArrayList<>();
    static Gson gson = new Gson();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SinemaSistemi sistem = new SinemaSistemi();
        sistem.menu();
    }

    public void menu() {
        while (true) {
            System.out.println("================================");
            System.out.println("|        Sinema Sistemi        |");
            System.out.println("================================");
            System.out.println("1. Film Ekle");
            System.out.println("2. Salon Ekle");
            System.out.println("3. Müşteri Ekle");
            System.out.println("4. Filmleri Listele");
            System.out.println("5. Salonları Listele");
            System.out.println("6. Film Seç ve Salon Gösterimi Gör");
            System.out.println("7. Çıkış");
            System.out.print("Seçim: ");
            int secim = scanner.nextInt();
            scanner.nextLine(); // Satır sonu temizliği
            switch (secim) {
                case 1 -> filmEkle();
                case 2 -> salonEkle();
                case 3 -> musteriEkle();
                case 4 -> filmleriListele();
                case 5 -> salonlariListele();
                case 6 -> filmSecVeSalonGoster();
                case 7 -> {
                    System.out.println("Çıkış yapılıyor...");
                    return;
                }
                default -> System.out.println("Geçersiz seçim!");
            }
        }
    }

    @Override
    public void kaydet() {
        try (FileWriter filmYazici = new FileWriter("Film.json");
             FileWriter musteriYazici = new FileWriter("Musteri.json");
             FileWriter salonYazici = new FileWriter("Salon.json")) {

            gson.toJson(filmler, filmYazici);
            gson.toJson(musteriler, musteriYazici);
            gson.toJson(salonlar, salonYazici);
        } catch (Exception e) {
            System.out.println("Kaydetme hatası: " + e.getMessage());
        }
    }

    @Override
    public void listele() {
        try (FileReader filmOkuyucu = new FileReader("Film.json");
             FileReader musteriOkuyucu = new FileReader("Musteri.json");
             FileReader salonOkuyucu = new FileReader("Salon.json")) {

            Type filmTipi = new TypeToken<List<Film>>() {}.getType();
            Type musteriTipi = new TypeToken<List<Musteri>>() {}.getType();
            Type salonTipi = new TypeToken<List<Salon>>() {}.getType();

            filmler = gson.fromJson(filmOkuyucu, filmTipi);
            musteriler = gson.fromJson(musteriOkuyucu, musteriTipi);
            salonlar = gson.fromJson(salonOkuyucu, salonTipi);

        } catch (Exception e) {
            System.out.println("Listeleme hatası: " + e.getMessage());
        }
    }

    private void filmEkle() {
        Film film = new Film();
        film.id = filmler.size() + 1;
        System.out.print("Film Adı: ");
        film.name = scanner.nextLine();
        System.out.print("Film Süresi (dk): ");
        film.duration = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Film Türü: ");
        film.genre = scanner.nextLine();
        filmler.add(film);
        kaydet();
        System.out.println("Film eklendi!");
    }

    private void salonEkle() {
        Salon salon = new Salon();
        salon.id = salonlar.size() + 1;
        System.out.print("Salon Adı: ");
        salon.name = scanner.nextLine();
        System.out.print("Gösterilecek Film ID: ");
        salon.filmId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Gösterim Saati (örn: 18:30): ");
        salon.showTime = scanner.nextLine();
        salonlar.add(salon);
        kaydet();
        System.out.println("Salon eklendi!");
    }

    private void musteriEkle() {
        Musteri musteri = new Musteri();
        musteri.id = musteriler.size() + 1;
        System.out.print("Müşteri Adı: ");
        musteri.name = scanner.nextLine();
        System.out.print("Film ID (seçilen): ");
        musteri.filmId = scanner.nextInt();
        scanner.nextLine();
        musteriler.add(musteri);

        // İlgili salonun müşteri listesine ekle
        for (Salon salon : salonlar) {
            if (salon.filmId == musteri.filmId) {
                salon.customerIds.add(musteri.id);
                break;
            }
        }
        kaydet();
        System.out.println("Müşteri eklendi!");
    }

    private void filmleriListele() {
        listele();
        System.out.println("---- Filmler ----");
        for (Film film : filmler) {
            System.out.println("ID: " + film.id + ", Adı: " + film.name + ", Süresi: " + film.duration + " dk, Türü: " + film.genre);
        }
    }

    private void salonlariListele() {
        listele();
        System.out.println("---- Salonlar ----");
        for (Salon salon : salonlar) {
            System.out.print("ID: " + salon.id + ", Adı: " + salon.name);
            System.out.print(", Gösterimdeki Film ID: " + salon.filmId);
            System.out.print(", Gösterim Saati: " + salon.showTime);
            System.out.println(", Kayıtlı Müşteri Sayısı: " + salon.customerIds.size());
        }
    }

    private void filmSecVeSalonGoster() {
        listele();

        System.out.println("---- Mevcut Filmler ----");
        for (Film film : filmler) {
            System.out.println("ID: " + film.id + ", Adı: " + film.name);
        }

        System.out.print("Film ID seçin: ");
        int filmId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("---- Seçilen Film İçin Salonlar ----");
        for (Salon salon : salonlar) {
            if (salon.filmId == filmId) {
                System.out.println("Salon ID: " + salon.id + ", Adı: " + salon.name + ", Gösterim Saati: " + salon.showTime);
            }
        }

        System.out.print("Salon ID seçin: ");
        int salonId = scanner.nextInt();
        scanner.nextLine();

        Salon secilenSalon = salonlar.stream().filter(s -> s.id == salonId).findFirst().orElse(null);
        if (secilenSalon != null) {
            System.out.println("Seçtiğiniz salon: " + secilenSalon.name + ", Gösterim Saati: " + secilenSalon.showTime);
        } else {
            System.out.println("Geçersiz salon ID!");
        }
    }
}
