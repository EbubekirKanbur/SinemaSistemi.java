Proje Hakkında
Bu proje, bir sinema sisteminin temel işlevlerini simüle etmek için geliştirilmiş bir Java uygulamasıdır. Sistem, filmler, salonlar ve müşteriler üzerinde işlemler yapmanıza olanak tanır. Veriler, JSON formatında dosyalara kaydedilir ve ihtiyaç duyulduğunda bu dosyalardan yüklenir.

Kullanılan Teknolojiler
Java 17 veya üstü
Gson (JSON işleme için)
Java I/O (dosya okuma ve yazma için)
Sınıf ve Bileşenler
1. BaseEntity
Ortak özellikleri tanımlayan bir temel sınıftır.
Özellikler:
id: Her varlık için benzersiz bir kimlik.
name: Varlığın adı.
2. Film
Sinema sistemindeki filmleri temsil eder.
Ek Özellikler:
duration: Filmin süresi (dakika).
genre: Filmin türü.
3. Musteri
Sistemdeki müşterileri temsil eder.
Ek Özellikler:
filmId: Müşterinin izlediği filmin kimliği.
4. Salon
Sinemadaki salonları temsil eder.
Ek Özellikler:
filmId: Salonda gösterimde olan filmin kimliği.
showTime: Gösterim saati.
customerIds: Salona kayıtlı müşteri kimlikleri.
5. IKayit
Kaydetme ve listeleme işlemlerini zorunlu kılmak için kullanılan bir arayüzdür.
Metotlar:
kaydet(): Verileri JSON dosyalarına kaydeder.
listele(): JSON dosyalarından verileri okur.
Uygulama İşlevleri
Film Ekle

Yeni bir film eklenir.
Film bilgileri: Adı, Süresi, Türü.
Salon Ekle

Yeni bir salon eklenir.
Salon bilgileri: Adı, Gösterimdeki Film ID, Gösterim Saati.
Müşteri Ekle

Yeni bir müşteri eklenir.
Müşteri bilgileri: Adı, Film ID.
Müşteri, seçtiği filmin salonuna otomatik olarak kaydedilir.
Filmleri Listele

Tüm filmler ID, ad, süre ve tür bilgileriyle görüntülenir.
Salonları Listele

Tüm salonlar ID, ad, film ID, gösterim saati ve müşteri sayısıyla listelenir.
Film Seç ve Salon Gösterimi Gör

Belirli bir film için uygun salonlar listelenir ve kullanıcı bir salon seçebilir.
Çıkış

Uygulamadan çıkılır.
JSON Dosyaları
Film.json: Filmler ile ilgili bilgiler.
Musteri.json: Müşteriler ile ilgili bilgiler.
Salon.json: Salonlar ile ilgili bilgiler.
Kurulum ve Çalıştırma
Projeyi bir Java IDE'sinde (ör. IntelliJ IDEA, Eclipse) açın.
Gson kütüphanesini projeye ekleyin.
SinemaSistemi sınıfını çalıştırın.
Menüden bir işlem seçerek uygulamayı kullanmaya başlayın.
Örnek Akış
Bir film ekleyin (örn: "Film Adı: Matrix", "Süresi: 120", "Türü: Bilim Kurgu").
Bir salon ekleyin (örn: "Salon Adı: Salon 1", "Film ID: 1", "Gösterim Saati: 18:30").
Bir müşteri ekleyin (örn: "Müşteri Adı: Ali", "Film ID: 1").
Filmleri ve salonları listeleyin.
